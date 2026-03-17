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

f_setYearlyKPIs(data, netLoad_kW, previousNetLoad_kW);

f_setMonthlyChart(data, netLoad_kW);

/*ALCODEEND*/}

double f_resetChart()
{/*ALCODESTART::1772472093328*/
c_orderedStackCharts.forEach(chart -> chart.removeAll());
/*ALCODEEND*/}

double[] f_calculateMonthlyPeakElectricityBalance_kW(double[] netLoad_kW)
{/*ALCODESTART::1773160205274*/
int[] startHourPerMonth = startHourPerMonthTemporary;
double timeStep_h = uI_Results.energyModel.p_timeParameters.getTimeStep_h();

double[] monthlyPeakElectricityBalance_kW = new double[12];

int currentMonth = 0;

for (int i = 0; i < netLoad_kW.length; i++) {
	if(currentMonth != 11 && startHourPerMonth[currentMonth+1] < i*timeStep_h){
		currentMonth += 1;
	}
	monthlyPeakElectricityBalance_kW[currentMonth] = max(monthlyPeakElectricityBalance_kW[currentMonth], abs(netLoad_kW[i]));
}

return monthlyPeakElectricityBalance_kW;
/*ALCODEEND*/}

double[] f_calculateMonthlyPeakElectricityDelivery_kW(double[] netLoad_kW)
{/*ALCODESTART::1773160205294*/
int[] startHourPerMonth = startHourPerMonthTemporary;
double timeStep_h = uI_Results.energyModel.p_timeParameters.getTimeStep_h();

double[] monthlyPeakElectricityDelivery_kW = new double[12];

int currentMonth = 0;

for (int i = 0; i < netLoad_kW.length; i++) {
	if(currentMonth != 11 && startHourPerMonth[currentMonth+1] < i*timeStep_h){
		currentMonth += 1;
	}
	monthlyPeakElectricityDelivery_kW[currentMonth] = max(monthlyPeakElectricityDelivery_kW[currentMonth], netLoad_kW[i]);
}

return monthlyPeakElectricityDelivery_kW;

/*ALCODEEND*/}

double[] f_calculateMonthlyPeakElectricityFeedin_kW(double[] netLoad_kW)
{/*ALCODESTART::1773160205312*/
int[] startHourPerMonth = startHourPerMonthTemporary;
double timeStep_h = uI_Results.energyModel.p_timeParameters.getTimeStep_h();

double[] monthlyPeakElectricityFeedin_kW = new double[12];

int currentMonth = 0;

for (int i = 0; i < netLoad_kW.length; i++) {
	if(currentMonth != 11 && startHourPerMonth[currentMonth+1] < i*timeStep_h){
		currentMonth += 1;
	}
	monthlyPeakElectricityFeedin_kW[currentMonth] = max(monthlyPeakElectricityFeedin_kW[currentMonth], -netLoad_kW[i]);
}

return monthlyPeakElectricityFeedin_kW;
/*ALCODEEND*/}

double[] f_calculateMonthlyTotalElectricityTransport_kWh(double[] netLoad_kW)
{/*ALCODESTART::1773160925451*/
int[] startHourPerMonth = startHourPerMonthTemporary;
double timeStep_h = uI_Results.energyModel.p_timeParameters.getTimeStep_h();

double[] monthlyTotalElectricityTransport_kWh = new double[12];

int currentMonth = 0;

for (int i = 0; i < netLoad_kW.length; i++) {
	if(currentMonth != 11 && startHourPerMonth[currentMonth+1] < i*timeStep_h){
		currentMonth += 1;
	}
	monthlyTotalElectricityTransport_kWh[currentMonth] += abs(netLoad_kW[i]) * timeStep_h;
}

return monthlyTotalElectricityTransport_kWh;
/*ALCODEEND*/}

double[] f_calculateMonthlyElectricityDelivery_kWh(double[] netLoad_kW)
{/*ALCODESTART::1773160925453*/
int[] startHourPerMonth = startHourPerMonthTemporary;
double timeStep_h = uI_Results.energyModel.p_timeParameters.getTimeStep_h();

double[] monthlyElectricityDelivery_kWh = new double[12];

int currentMonth = 0;

for (int i = 0; i < netLoad_kW.length; i++) {
	if(currentMonth != 11 && startHourPerMonth[currentMonth+1] < i*timeStep_h){
		currentMonth += 1;
	}
	monthlyElectricityDelivery_kWh[currentMonth] += max(0, netLoad_kW[i]) * timeStep_h;
}

return monthlyElectricityDelivery_kWh;

/*ALCODEEND*/}

double[] f_calculateMonthlyElectricityFeedin_kWh(double[] netLoad_kW)
{/*ALCODESTART::1773160925455*/
int[] startHourPerMonth = startHourPerMonthTemporary;
double timeStep_h = uI_Results.energyModel.p_timeParameters.getTimeStep_h();

double[] monthlyElectricityFeedin_kWh = new double[12];

int currentMonth = 0;

for (int i = 0; i < netLoad_kW.length; i++) {
	if(currentMonth != 11 && startHourPerMonth[currentMonth+1] < i*timeStep_h){
		currentMonth += 1;
	}
	monthlyElectricityFeedin_kWh[currentMonth] += max(0, -netLoad_kW[i]) * timeStep_h;
}

return monthlyElectricityFeedin_kWh;
/*ALCODEEND*/}

double f_setMonthlyChart(I_EnergyData data,double[] netLoad_kW)
{/*ALCODESTART::1773161385166*/
DataSet netCosts_eur = new DataSet(12);

double maxChartValue_eur = 0;

//Get monthly values
double monthlyPhysicalConnectionCosts_eur = f_calculatePhysicalConnectionCosts_eurpyr(data)/12.0;
double monthlyContractConnectionCosts_eur = f_calculateContractConnectionCosts_eurpyr(data)/12.0;
double[] monthlyTransportCosts_eur = ZeroMath.arrayMultiply(f_calculateMonthlyTotalElectricityTransport_kWh(netLoad_kW), 0.01);
double[] monthlyPeakCosts_eur = ZeroMath.arrayMultiply(f_calculateMonthlyPeakElectricityBalance_kW(netLoad_kW), 0.01);


for (int i = 0; i < 12; i++) {
	StackChart chart_monthlyConnectionCosts = c_orderedStackCharts.get(i);
	
	//Physical connection cost
	DataItem physicalConnectionCosts_eur = new DataItem();
	physicalConnectionCosts_eur.setValue(monthlyPhysicalConnectionCosts_eur);
	chart_monthlyConnectionCosts.addDataItem(physicalConnectionCosts_eur, "Fysieke aansluitings kosten", color_physicalConnectionCosts);
	
	//Contract connection cost
	DataItem contractConnectionCosts_eur = new DataItem();
	contractConnectionCosts_eur.setValue(monthlyContractConnectionCosts_eur);
	chart_monthlyConnectionCosts.addDataItem(contractConnectionCosts_eur, "Vaste contract kosten", color_contractConnectionCosts);
		
	//Transport cost
	DataItem transportCosts_eur = new DataItem();
	transportCosts_eur.setValue(monthlyTransportCosts_eur[i]);
	chart_monthlyConnectionCosts.addDataItem(transportCosts_eur, "Transport kosten", color_transportConnectionCosts);
		
	//Peak costs
	DataItem peakCosts_eur = new DataItem();
	peakCosts_eur.setValue(monthlyPeakCosts_eur[i]);
	chart_monthlyConnectionCosts.addDataItem(peakCosts_eur, "Fysieke aansluitings kosten", color_monthlyPeakConnectionCosts);
			
	//Determine max value of the bars 
	maxChartValue_eur = max(maxChartValue_eur, 
							monthlyPhysicalConnectionCosts_eur + 
							monthlyContractConnectionCosts_eur +
							monthlyTransportCosts_eur[i] +
							monthlyPeakCosts_eur[i]
							);
}

//Set fixed scale
maxChartValue_eur *=1.2;
for (int i = 0; i < 12; i++) {
	c_orderedStackCharts.get(i).setFixedScale(maxChartValue_eur);
}
chart_layout.setFixedScale(maxChartValue_eur);
/*ALCODEEND*/}

double f_setYearlyKPIs(I_EnergyData data,double[] netLoad_kW,double[] previousNetLoad_kW)
{/*ALCODESTART::1773161419537*/
//Set new values text
DecimalFormat df = new DecimalFormat("#,###");
DecimalFormat df_r = new DecimalFormat("#.#");
DecimalFormat df_2decimal = new DecimalFormat("0.00");


double physicalConnectionCosts_eur = f_calculatePhysicalConnectionCosts_eurpyr(data);
double contractConnectionCosts_eur = f_calculateContractConnectionCosts_eurpyr(data);
double totalTransportCosts_eur = f_calculateTotalTransportCosts_eurpyr(netLoad_kW);
double totalPeakCosts_eur = f_calculateTotalPeakCosts_eurpyr(netLoad_kW);
double totalConnectionCosts_eur = physicalConnectionCosts_eur + contractConnectionCosts_eur + 
								  totalTransportCosts_eur + totalPeakCosts_eur; 



t_physicalConnectionCosts.setText("€ " + df.format(roundToInt(physicalConnectionCosts_eur)));
t_defaultContractCosts.setText("€ " + df.format(roundToInt(contractConnectionCosts_eur)));
t_energyTransportCosts.setText("€ " + df.format(roundToInt(totalTransportCosts_eur)));
t_peakConsumptionCosts.setText("€ " + df.format(roundToInt(totalPeakCosts_eur)));
t_totalConnectionCosts.setText("€ " + df.format(roundToInt(totalConnectionCosts_eur)));


if(previousNetLoad_kW != null){

	double previousPhysicalConnectionCosts_eur = f_calculatePhysicalConnectionCosts_eurpyr(data);
	double previousContractConnectionCosts_eur = f_calculateContractConnectionCosts_eurpyr(data);
	double previousTotalTransportCosts_eur = f_calculateTotalTransportCosts_eurpyr(previousNetLoad_kW);
	double previousTotalPeakCosts_eur = f_calculateTotalPeakCosts_eurpyr(previousNetLoad_kW);
	double previousTotalConnectionCosts_eur = previousPhysicalConnectionCosts_eur + previousContractConnectionCosts_eur + 
									  previousTotalTransportCosts_eur + previousTotalPeakCosts_eur; 


	/*
	t_previousTotalImportCosts_eur.setText("€ " + df.format(roundToInt(previousTotalImportCosts_eur)));
	t_previousTotalExportRevenue_eur.setText("€ " + df.format(roundToInt(previousTotalExportRevenue_eur)));
	t_previousTotalNetElectricityCosts_eur.setText("€ " + df.format(roundToInt(previousTotalNetElectricityCosts_eur)));
	

	////Set arrows
	//Import
	if(previousTotalImportCosts_eur > totalImportCosts_eur){
		arrow_down_green_import.setVisible(true);
	}
	else if(totalImportCosts_eur > previousTotalImportCosts_eur){
		arrow_up_red_import.setVisible(true);
	}
	else{
		line_import.setVisible(true);
	}
	
	//Export
	if(previousTotalExportRevenue_eur > totalExportRevenue_eur){
		arrow_down_red_export.setVisible(true);
	}
	else if(totalExportRevenue_eur > previousTotalExportRevenue_eur){
		arrow_up_green_export.setVisible(true);
	}
	else{
		line_export.setVisible(true);
	}
	
	//Net
	if(previousTotalNetElectricityCosts_eur > totalNetElectricityCosts_eur){
		arrow_down_green_netElectricity.setVisible(true);
	}
	else if(totalNetElectricityCosts_eur > previousTotalNetElectricityCosts_eur){
		arrow_up_red_netElectricity.setVisible(true);
	}
	else{
		line_total.setVisible(true);
	}
	*/
}
else{ // No previous rapid data -> dont show previous values
	//t_previousTotalImportCosts_eur.setText("-");
	//t_previousTotalExportRevenue_eur.setText("-");
	//t_previousTotalNetElectricityCosts_eur.setText("-");
}

/*ALCODEEND*/}

double f_calculatePhysicalConnectionCosts_eurpyr(I_EnergyData data)
{/*ALCODESTART::1773162212251*/
return 10000;
/*ALCODEEND*/}

double f_calculateContractConnectionCosts_eurpyr(I_EnergyData data)
{/*ALCODESTART::1773162241728*/
return 15000;
/*ALCODEEND*/}

double f_calculateTotalTransportCosts_eurpyr(double[] netLoad_kW)
{/*ALCODESTART::1773162256135*/
return ZeroMath.arraySum(f_calculateMonthlyTotalElectricityTransport_kWh(netLoad_kW));
/*ALCODEEND*/}

double f_calculateTotalPeakCosts_eurpyr(double[] netLoad_kW)
{/*ALCODESTART::1773162293336*/
return ZeroMath.arraySum(f_calculateMonthlyPeakElectricityBalance_kW(netLoad_kW)) * 0.01;
/*ALCODEEND*/}

double f_calculateTotalConnectionCosts_eurpyr(I_EnergyData data,double[] netLoad_kW)
{/*ALCODESTART::1773219333056*/
return f_calculatePhysicalConnectionCosts_eurpyr(data)+
	   f_calculateContractConnectionCosts_eurpyr(data)+
	   f_calculateTotalTransportCosts_eurpyr(netLoad_kW)+
	   f_calculateTotalPeakCosts_eurpyr(netLoad_kW);
/*ALCODEEND*/}

