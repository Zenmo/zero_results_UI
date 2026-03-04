double f_setChartConnectionCosts()
{/*ALCODESTART::1772471760319*/
I_EnergyData data = uI_Results.f_getSelectedObjectData();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 60, true);

//Reset chart
f_resetChart();


////Get the netload values
double[] netLoad_kW;
double[] previousNetLoad_kW = null;
if(uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE){
	netLoad_kW = uI_Results.v_gridNode.acc_annualElectricityBalance_kW.getTimeSeries_kW();
}
else{
	netLoad_kW = data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getTimeSeries_kW();
	if(data.getPreviousRapidRunData() != null){
		previousNetLoad_kW = data.getPreviousRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getTimeSeries_kW();
	}
}



/*ALCODEEND*/}

double f_resetChart()
{/*ALCODESTART::1772472093328*/

/*ALCODEEND*/}

double f_calculateCapacityCosts_eur(double[] netLoad_kW)
{/*ALCODESTART::1772615497109*/
double costsCapacityRate_euro = 0;

GridNode GN_T0 = findFirst(uI_Results.energyModel.pop_gridNodes, p -> p.p_gridNodeID.equals("T0"));
double contractedCapacity_kW = GN_T0.p_capacity_kW;
double VAT_fr = 0.21;
double annualConnectionRate_euro_p_yr = 5351;
double annualFixedTransportRate_euro_p_yr = 2760;
double annualContractCapacityRate_euro_p_kW_yr = 42.10;
double monthlyPeakPowerRate_euro_p_kW_month = 4.48;
    	
double[] monthlyPeakDemand_kW = f_calculateMonthlyPeakDemand_kW(netLoad_kW);
    
costsCapacityRate_euro = (1+VAT_fr)*(annualConnectionRate_euro_p_yr + annualFixedTransportRate_euro_p_yr + annualContractCapacityRate_euro_p_kW_yr * contractedCapacity_kW + monthlyPeakPowerRate_euro_p_kW_month * Arrays.stream(monthlyPeakDemand_kW).sum());

return costsCapacityRate_euro;
/*ALCODEEND*/}

double[] f_calculateMonthlyPeakDemand_kW(double[] netLoad_kW)
{/*ALCODESTART::1772615497113*/
double[] monthlyPeakDemand_kW = new double[12];
int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
int sampleCounter = 0;
    	
for(int month=0; month < daysInMonth.length; month++) {
    
    double maxLoad_kW = 0;
    
    int samplesInMonth = daysInMonth[month] * 96;
    int startMonthIndex = sampleCounter;
    int endMonthIndex = sampleCounter + samplesInMonth;
    		
	for (int i = startMonthIndex; i < endMonthIndex && i < netLoad_kW.length; i++) {
    	double absLoad_kW = Math.abs(netLoad_kW[i]);
        if (absLoad_kW > maxLoad_kW) {
        	maxLoad_kW = absLoad_kW;
        }
    }
    monthlyPeakDemand_kW[month] = maxLoad_kW;
   	sampleCounter += samplesInMonth;
}
	
return monthlyPeakDemand_kW;
/*ALCODEEND*/}

