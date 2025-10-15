import java.util.*;
import java.util.function.Function;

/**
 * Utilities for SoC-based cycle extraction and lifetime estimation.
 * - Turning points with micro cycle filtering
 * - Rainflow counting (full + half cycles)
 * - EFC/year, Miner's Rule
 */

public final class J_CalculateBatteryLifetimeHelpers {

    private J_CalculateBatteryLifetimeHelpers() {}

    /** A counted cycle: depth (DoD), mean SoC, and multiplicity (1.0 full, 0.5 half). */
    public static final class CountedCycle {
        public final double cycleDoD;   // DoD in [0,1]
        public final double cycleMeanSoC;    // mean SoC of the swing
        public final double cycleCount;      // 1.0 or 0.5

        public CountedCycle(double cycleDoD, double cycleMeanSoC, double cycleCount) {
            this.cycleDoD = cycleDoD;
            this.cycleMeanSoC = cycleMeanSoC;
            this.cycleCount = cycleCount;
        }
    }

    
    
    /** Remove micro cyles from SoC array */
    public static double[] removeMicroCyclesFromSoC(double[] arraySoC_fr, double microCycleSoCTolerance) {
        if (arraySoC_fr == null || arraySoC_fr.length == 0) return new double[0];

        double[] filteredArraySoC_fr = new double[arraySoC_fr.length];
        int filteredLength = 0;
        double prevAcceptedSoC = Double.NaN;

        for (double SoC_fr : arraySoC_fr) {
            double clampedSoC_fr = clamp(SoC_fr, 0, 1);
            if (Double.isNaN(prevAcceptedSoC) || Math.abs(clampedSoC_fr - prevAcceptedSoC) >= microCycleSoCTolerance) {
            	filteredArraySoC_fr[filteredLength++] = clampedSoC_fr;
            	prevAcceptedSoC = clampedSoC_fr;
            }
        }
        return Arrays.copyOf(filteredArraySoC_fr, filteredLength);
    }

    
    
    /** Extract turning points (local extrema) from the SoC series after de-noising. Includes first and last points. */
    public static List<Double> extractTurningPoints(double[] arraySoC_fr, double microCycleSoCTolerance) {
        
    	double[] filteredArraySoC_fr = removeMicroCyclesFromSoC(arraySoC_fr, microCycleSoCTolerance);
        
        List<Double> turningPointsList = new ArrayList<>();
        if (filteredArraySoC_fr.length == 0) return turningPointsList;

        turningPointsList.add(filteredArraySoC_fr[0]); // Include the first point

        double previousSoC_fr = 0;
        
        for (int i = 1; i < filteredArraySoC_fr.length; i++) {
            double deltaSoC_fr = filteredArraySoC_fr[i] - filteredArraySoC_fr[i-1];
            if (Math.abs(deltaSoC_fr) < microCycleSoCTolerance) continue; // ignore flats/noise; Double check

            if (previousSoC_fr != 0 && Math.signum(previousSoC_fr) != Math.signum(deltaSoC_fr)) {
                // Direction reversal: the previous sample is a true turning point
                turningPointsList.add(filteredArraySoC_fr[i-1]);
            }
            previousSoC_fr = deltaSoC_fr;
        }

        double lastSampleArraySoC_fr = filteredArraySoC_fr[filteredArraySoC_fr.length-1];
        if (!turningPointsList.isEmpty() && turningPointsList.get(turningPointsList.size() - 1) != lastSampleArraySoC_fr) {
            turningPointsList.add(lastSampleArraySoC_fr); // Include the last point
        }
        return turningPointsList;
    }

    
    
    /**
     * Rainflow counting on a list of turning points using the standard closure test:
     *   Let X=stack[n-3], Y=stack[n-2], Z=stack[n-1].
     *   If |Z - Y| >= |Y - X|, a full cycle of range |Y - X| is closed between X and Y.
     *   Remove X and Y; keep Z for further comparisons.
     * Residual adjacent pairs become half cycles.
     */
    public static List<CountedCycle> threePointRainflowCountingFromTurningPoints(List<Double> turningPointsList) {
        
    	List<CountedCycle> countedCycleList = new ArrayList<>();
        if (turningPointsList == null || turningPointsList.size() < 2) return countedCycleList; // If not enough turning points -> return

        List<Double> turningPointStack = new ArrayList<>();

        for (double turningPoint : turningPointsList) {
            turningPointStack.add(turningPoint);

            while (turningPointStack.size() >= 3) {
                int n = turningPointStack.size(); // if size = 3 ->
                double xOlder = turningPointStack.get(n - 3); // index 0 = X
                double yMiddle = turningPointStack.get(n - 2); // index 1 = Y
                double zNewer = turningPointStack.get(n - 1); // index 2 = Z

                double rangeOlder = Math.abs(yMiddle - xOlder); // |Y-X|
                double rangeNewer = Math.abs(zNewer  - yMiddle); // |Z-Y|

                if (rangeNewer >= rangeOlder) { // |Z-Y| >= |Y-X|
                    double fullCycleDoD = rangeOlder; // DoD = |Y-X|
                    double fullCycleMean  = 0.5 * (xOlder + yMiddle);
                    countedCycleList.add(new CountedCycle(fullCycleDoD, fullCycleMean, 1.0));

                    // Remove X and Y, keep Z
                    turningPointStack.remove(n - 3); // remove X
                    turningPointStack.remove(n - 3); // remove Y (shifted)
                } else {
                    break; // no more closures possible
                }
            }
        }

        // Residual half-cycles
        if (turningPointStack.size() >= 2) {
            for (int i = 0; i < turningPointStack.size() - 1; i++) {
                double a = turningPointStack.get(i);
                double b = turningPointStack.get(i + 1);
                double halfCycleDoD = Math.abs(b - a);
                double halfCycleMean  = 0.5 * (a + b);
                countedCycleList.add(new CountedCycle(halfCycleDoD, halfCycleMean, 0.5));
            }
        }

        countedCycleList.removeIf(c -> c.cycleDoD <= 0);
        return countedCycleList;
    }
    
    
    
    /** Calculate damage over time using Miner's rule given cycleLife(DoD) array. */
    public static double calculateDamageFromCycleLifeArray(List<CountedCycle> countedCycleList, Function<Double, Double> calculateCycleLifeFromLogarithmicRegression) {
        
    	if (countedCycleList == null || countedCycleList.isEmpty()) return 0;
        
        double totalCycleDamage_fr = 0;
        for (CountedCycle countedCycle : countedCycleList) {
            double clampedDoD_fr = clamp(countedCycle.cycleDoD, 1e-6, 1);
            double cycleLife = Math.max(1e-9, calculateCycleLifeFromLogarithmicRegression.apply(clampedDoD_fr));
            totalCycleDamage_fr += countedCycle.cycleCount / cycleLife;
        }
        return totalCycleDamage_fr;
    }

    

    /**
     * Equivalent full cycles (EFC) over the series via charge throughput:
     * sum of positive SoC increments. Assumes SoC in [0,1].
     */
    public static double computeEFCsViaSoC(double[] arraySoC_fr) {
        if (arraySoC_fr == null || arraySoC_fr.length == 0) return 0.0;
        double totalChargeThroughputInSocUnits = 0.0;
        double previousSoc = clamp(arraySoC_fr[0], 0, 1);
        for (int i = 1; i < arraySoC_fr.length; i++) {
            double currentSoc = clamp(arraySoC_fr[i], 0, 1);
            double deltaSoc = currentSoc - previousSoc;
            if (deltaSoc > 0) totalChargeThroughputInSocUnits += deltaSoc; // charging only
            previousSoc = currentSoc;
        }
        return totalChargeThroughputInSocUnits;
    }
    
    

    /** Clamp helper */
    private static double clamp(double value, double minValue, double maxValue) {
        return Math.max(minValue, Math.min(maxValue, value));
    }
}
