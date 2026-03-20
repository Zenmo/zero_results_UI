double f_setChartTotalCosts()
{/*ALCODESTART::1772471828102*/
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

DecimalFormat df = new DecimalFormat("#,###");
//DecimalFormat df_r = new DecimalFormat("#.#");
//DecimalFormat df_2decimal = new DecimalFormat("0.00");

double totalEnergyCosts_eurpyr = 0;//uI_Results.chartEnergyCosts.f_calculateTotalEnergyCosts_eurpyr(data.getRapidRunData());
t_totalEnergyCosts.setText(" € " + df.format(roundToInt(totalEnergyCosts_eurpyr)));
	
double totalConnectionCosts_eurpyr = uI_Results.chartConnectionCosts.f_calculateTotalConnectionCosts_eurpyr(data.getRapidRunData().connectionMetaData, netLoad_kW);
t_totalConnectionCosts.setText(" € " + df.format(roundToInt(totalConnectionCosts_eurpyr)));

double totalCAPEXAndOPEXCosts_eurpyr = 0;
t_CAPEXAndOPEXCosts.setText(" € " + df.format(roundToInt(totalCAPEXAndOPEXCosts_eurpyr)));

double totalCosts_eurpyr = totalEnergyCosts_eurpyr + totalConnectionCosts_eurpyr + totalCAPEXAndOPEXCosts_eurpyr;	
t_totalCosts.setText(" € " + df.format(roundToInt(totalCosts_eurpyr)));

if(data.getPreviousRapidRunData() != null){
	double previousTotalEnergyCosts_eurpyr = 0;//uI_Results.chartEnergyCosts.f_calculateTotalEnergyCosts_eurpyr(data.getPreviousRapidRunData());
	t_previousTotalEnergyCosts.setText(" € " + df.format(roundToInt(previousTotalEnergyCosts_eurpyr)));
	
	double previousTotalConnectionCosts_eurpyr = uI_Results.chartConnectionCosts.f_calculateTotalConnectionCosts_eurpyr(data.getPreviousRapidRunData().connectionMetaData, netLoad_kW);
	t_previousTotalConnectionCosts.setText(" € " + df.format(roundToInt(previousTotalConnectionCosts_eurpyr)));
	
	double previousTotalCAPEXAndOPEXCosts_eurpyr = 0;
	t_previousCAPEXAndOPEXCosts.setText(" € " + df.format(roundToInt(previousTotalCAPEXAndOPEXCosts_eurpyr)));
	
	double previousTotalCosts_eurpyr = previousTotalEnergyCosts_eurpyr + previousTotalConnectionCosts_eurpyr + previousTotalCAPEXAndOPEXCosts_eurpyr;	
	t_previousTotalCosts.setText(" € " + df.format(roundToInt(previousTotalCosts_eurpyr)));
}
/*ALCODEEND*/}

double f_resetChart()
{/*ALCODESTART::1772472124331*/
t_previousTotalEnergyCosts.setText("-");
t_previousTotalConnectionCosts.setText("-");
t_previousCAPEXAndOPEXCosts.setText("-");
t_previousTotalCosts.setText("-");

/*ALCODEEND*/}

