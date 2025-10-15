import java.util.*;
import java.util.function.Function;
import static java.lang.Math.*;

/**
 * Lifetime estimation using a logarithmic approximation cycleLife(DoD) = alpha * ln(DoD) + beta .
 * Provides:
 *  - Miner’s rule lifetime (recommended)
 *
 * Long/descriptive variable names for readability.
 */

public final class J_CalculateBatteryLifetime {

    private J_CalculateBatteryLifetime() {
    	
    }

    /** SoC tolerance for micro cycles used in turning-point extraction */
    private static final double microCycleSoCTolerance = 0.002;

    /** cycleLife(DoD) = alpha * ln(DoD) + beta, clamped to positive values. Depth d in (0,1]. */
    public static Function<Double, Double> calculateCycleLifeFromLogarithmicRegression(double alpha, double beta) {
    	
        return DoD_fr -> {
            double clampedDoD_fr = max(1e-6, min(1.0, DoD_fr)); // Clamp DoD in (0,1]; Safeguard for ln(0)
            double cycleLife = alpha * log(clampedDoD_fr) + beta; // https://www.researchgate.net/publication/330142356_Optimal_Operational_Planning_of_Scalable_DC_Microgrid_with_Demand_Response_Islanding_and_Battery_Degradation_Cost_Considerations
            return max(1e-9, cycleLife); // Safeguard for zero/negative cycles
        };
        
    }

    /** Calculate cycle damage per year using Miner’s rule and using the cycle life curve logarithmic approximation */
    public static double calculateYearlyCycleDamage(double[] arraySoC_fr, double alpha, double beta) {
    	// Step 1: Retrieve list with turning points
    	List<Double> turningPointsList = J_CalculateBatteryLifetimeHelpers.extractTurningPoints(arraySoC_fr, microCycleSoCTolerance);
    	// Step 2: Retrieve list of DoDs per counted cycle using three-point rainflow counting method
        List<J_CalculateBatteryLifetimeHelpers.CountedCycle> countedCycleList = J_CalculateBatteryLifetimeHelpers.threePointRainflowCountingFromTurningPoints(turningPointsList);
        // Step 3: Define the logarithmic approximation to determine cycle life from DoD;
        Function<Double, Double> cycleLifeAsFunctionOfDod = calculateCycleLifeFromLogarithmicRegression(alpha, beta);
        // Step 4: Apply Miner's rule
        double totalCycleDamage_fr = J_CalculateBatteryLifetimeHelpers.calculateDamageFromCycleLifeArray(countedCycleList, cycleLifeAsFunctionOfDod);

        return totalCycleDamage_fr;
    }

    /** Total lifetime in YEARS combining cycle damage (+ optional calendar life in years). */
    public static double estimateBatteryLifetimeUsingMinersRule(double[] arraySoC_fr, double alpha, double beta, Double optionalCalendarLife_yr) {
        
    	// Calculate damage from cycling ageing
    	double cycleDamage_p_yr = calculateYearlyCycleDamage(arraySoC_fr, alpha, beta);
    	// Calculate damage from calendar ageing
        double calendarDamage_p_yr = (optionalCalendarLife_yr == null || optionalCalendarLife_yr <= 0) ? 0 : 1/optionalCalendarLife_yr;
        // Calculate total damage
        double totalDamage_p_yr = cycleDamage_p_yr + calendarDamage_p_yr; // D = Dcycle + Dcalender
        // Calculate battery lifetime
        double batteryLifetime_yr = (totalDamage_p_yr > 0) ? (1/totalDamage_p_yr) : Double.POSITIVE_INFINITY; // lifetime = 1/D = 1/Dcycle + 1/Dcalendar
        
        return (totalDamage_p_yr > 0) ? (1.0 / totalDamage_p_yr) : Double.POSITIVE_INFINITY;
    }

}
