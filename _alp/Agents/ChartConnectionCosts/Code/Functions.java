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

double f_setMonthlyChart(double yearlyPhysicalConnectionCosts_eur,double yearlyContractConnectionCosts_eur,double[] monthlyTransportCosts_eur,double[] monthlyPeakCosts_eur)
{/*ALCODESTART::1773161385166*/
DataSet netCosts_eur = new DataSet(12);

double maxChartValue_eur = 0;

for (int i = 0; i < 12; i++) {
	StackChart chart_monthlyConnectionCosts = c_stackCharts.get(i);
	
	//Physical connection cost
	DataItem physicalConnectionCosts_eur = new DataItem();
	physicalConnectionCosts_eur.setValue(yearlyPhysicalConnectionCosts_eur/12.0);
	chart_monthlyConnectionCosts.addDataItem(physicalConnectionCosts_eur, "Fysieke aansluitings kosten", purple);
	
	//Contract connection cost
	DataItem contractConnectionCosts_eur = new DataItem();
	contractConnectionCosts_eur.setValue(yearlyContractConnectionCosts_eur/12.0);
	chart_monthlyConnectionCosts.addDataItem(contractConnectionCosts_eur, "Vaste contract kosten", red);
		
	//Transport cost
	DataItem transportCosts_eur = new DataItem();
	transportCosts_eur.setValue(monthlyTransportCosts_eur[i]);
	chart_monthlyConnectionCosts.addDataItem(transportCosts_eur, "Transport kosten", orange);
		
	//Peak costs
	DataItem peakCosts_eur = new DataItem();
	peakCosts_eur.setValue(monthlyPeakCosts_eur[i]);
	chart_monthlyConnectionCosts.addDataItem(peakCosts_eur, "Fysieke aansluitings kosten", green);
			
	//Determine max value of the bars 
	maxChartValue_eur = max(maxChartValue_eur, 
							yearlyPhysicalConnectionCosts_eur/12.0 + 
							yearlyContractConnectionCosts_eur/12.0 +
							monthlyTransportCosts_eur[0] +
							monthlyPeakCosts_eur[0]
							);
}

//Set fixed scale
maxChartValue_eur *=1.2;
for (int i = 0; i < 12; i++) {
	c_stackCharts.get(i).setFixedScale(maxChartValue_eur);
}
chart_layout.setFixedScale(maxChartValue_eur);
/*ALCODEEND*/}

double f_setYearlyKPIs()
{/*ALCODESTART::1773161419537*/
//Set new values text
DecimalFormat df = new DecimalFormat("#,###");
DecimalFormat df_r = new DecimalFormat("#.#");
DecimalFormat df_2decimal = new DecimalFormat("0.00");

t_totalImportCosts_eur.setText("€ " + df.format(roundToInt(totalImportCosts_eur)));
t_totalExportRevenue_eur.setText("€ " + df.format(roundToInt(totalExportRevenue_eur)));
t_totalNetElectricityCosts_eur.setText("€ " + df.format(roundToInt(totalNetElectricityCosts_eur)));

if(previousTotalImportCosts_eur != null){
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
}
else{ // No previous rapid data -> dont show previous values
	t_previousTotalImportCosts_eur.setText("-");
	t_previousTotalExportRevenue_eur.setText("-");
	t_previousTotalNetElectricityCosts_eur.setText("-");
}
/*ALCODEEND*/}

double f_calculatePhysicalConnectionCosts_eurpyr(I_EnergyData data)
{/*ALCODESTART::1773162212251*/
return 0;
/*ALCODEEND*/}

double f_calculateContractConnectionCosts_eurpyr(I_EnergyData data)
{/*ALCODESTART::1773162241728*/
return 0;
/*ALCODEEND*/}

double f_calculateTotalTransportCosts_eurpyr()
{/*ALCODESTART::1773162256135*/
return 0;
/*ALCODEEND*/}

double f_calculateTotalPeakCosts_eurpyr()
{/*ALCODESTART::1773162293336*/
return 0;
/*ALCODEEND*/}

