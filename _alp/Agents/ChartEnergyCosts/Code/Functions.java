double f_setChartEconomicKPIs()
{/*ALCODESTART::1726830495435*/
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

////Calculate the values

//Current values
double[] monthlyElectricityImportCosts_eur = f_calculateMonthlyElectricityImportCosts_eur(netLoad_kW);
double[] monthlyElectricityExportRevenue_eur = f_calculateMonthlyElectricityExportRevenue_eur(netLoad_kW);
double[] monthlyNetElectricityCosts_eur = f_calculateMonthlyNetElectricityCosts_eur(monthlyElectricityImportCosts_eur, monthlyElectricityExportRevenue_eur);

double totalImportCosts_eur = ZeroMath.arraySum(monthlyElectricityImportCosts_eur);
double totalExportRevenue_eur = ZeroMath.arraySum(monthlyElectricityExportRevenue_eur);
double totalNetElectricityCosts_eur = ZeroMath.arraySum(monthlyNetElectricityCosts_eur);

//Previous values
Double previousTotalImportCosts_eur = null;
Double previousTotalExportRevenue_eur = null;
Double previousTotalNetElectricityCosts_eur = null;

if(previousNetLoad_kW != null){
	double[] previousMonthlyElectricityImportCosts_eur = f_calculateMonthlyElectricityImportCosts_eur(previousNetLoad_kW);
	double[] previousMonthlyElectricityExportRevenue_eur = f_calculateMonthlyElectricityExportRevenue_eur(previousNetLoad_kW);
	double[] previousMonthlyNetElectricityCosts_eur = f_calculateMonthlyNetElectricityCosts_eur(previousMonthlyElectricityImportCosts_eur, previousMonthlyElectricityExportRevenue_eur);
	
	previousTotalImportCosts_eur = ZeroMath.arraySum(previousMonthlyElectricityImportCosts_eur);
	previousTotalExportRevenue_eur = ZeroMath.arraySum(previousMonthlyElectricityExportRevenue_eur);
	previousTotalNetElectricityCosts_eur = ZeroMath.arraySum(previousMonthlyNetElectricityCosts_eur);
}


//Set yearly kpis
f_setYearlyKPIs(totalImportCosts_eur, totalExportRevenue_eur, totalNetElectricityCosts_eur, previousTotalImportCosts_eur, previousTotalExportRevenue_eur, previousTotalNetElectricityCosts_eur);

//Set monthly chart
f_setMonthlyChart(monthlyElectricityImportCosts_eur, monthlyElectricityExportRevenue_eur, monthlyNetElectricityCosts_eur);


/*ALCODEEND*/}

double f_setYearlyKPIs(double totalImportCosts_eur,double totalExportRevenue_eur,double totalNetElectricityCosts_eur,Double previousTotalImportCosts_eur,Double previousTotalExportRevenue_eur,Double previousTotalNetElectricityCosts_eur)
{/*ALCODESTART::1726830499836*/
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

double f_styleBackground_override(Color backgroundColor,Color lineColor,Double lineWidth,LineStyle lineStyle)
{/*ALCODESTART::1726834752079*/
//Function used to style the background of the graphs

v_backgroundColor = v_backgroundColor_override;

if(lineColor != null){
	v_lineColor = lineColor;
}

if(lineWidth != null){
 v_lineWidth = lineWidth;
}
if(lineStyle != null){
 v_lineStyle = lineStyle;
}
/*ALCODEEND*/}

double f_resetChart()
{/*ALCODESTART::1727107912139*/
//Reset all arrow visibility
arrow_down_green_import.setVisible(false);
arrow_up_green_export.setVisible(false);
arrow_down_green_netElectricity.setVisible(false);
arrow_up_red_import.setVisible(false);
arrow_down_red_export.setVisible(false);
arrow_up_red_netElectricity.setVisible(false);
line_total.setVisible(false);
line_import.setVisible(false);
line_export.setVisible(false);

//Clear monthly chart
bar_importElectricityCostsMonthly.removeAll();
bar_exportElectricityRevenueMonthly.removeAll();
plot_netElectricityCostsMonthly.removeAll();
/*ALCODEEND*/}

double f_calculateCapacityCosts_eur(double[] netLoad_kW)
{/*ALCODESTART::1755868971285*/
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
{/*ALCODESTART::1755869067049*/
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

double[] f_calculateMonthlyNetElectricityCosts_eur(double[] monthlyElectricityImportCosts_euro,double[] monthlyElectricityExportRevenue_euro)
{/*ALCODESTART::1755869113668*/
if(monthlyElectricityImportCosts_euro.length != monthlyElectricityExportRevenue_euro.length){
	throw new RuntimeException("Trying to calculate the net electricity cost per month for a monthly import and export array that is not the same size!");
}

double[] monthlyNetElectricityCost_euro = new double[monthlyElectricityImportCosts_euro.length];

for (int i = 0; i < monthlyElectricityImportCosts_euro.length; i++) {
    monthlyNetElectricityCost_euro[i] = monthlyElectricityImportCosts_euro[i] - monthlyElectricityExportRevenue_euro[i];
}

return monthlyNetElectricityCost_euro;
/*ALCODEEND*/}

double f_setMonthlyChart(double[] monthlyElectricityImportCosts_eur,double[] monthlyElectricityExportRevenue_eur,double[] monthlyNetElectricityCosts_eur)
{/*ALCODESTART::1769429961370*/
DataSet netCosts_eur = new DataSet(12);

double maxChartValue_eur = 0;

for (int i = 0; i < 12; i++) {
	//Import cost
	DataItem importCosts_eur = new DataItem();
	importCosts_eur.setValue(monthlyElectricityImportCosts_eur[i]);
	bar_importElectricityCostsMonthly.addDataItem(importCosts_eur, "", uI_Results.v_electricityDemandColor);

	//Export revenue
	DataItem exportRevenue_eur = new DataItem();
	exportRevenue_eur.setValue(monthlyElectricityExportRevenue_eur[i]);
	bar_exportElectricityRevenueMonthly.addDataItem(exportRevenue_eur, "", uI_Results.v_electricitySupplyColor);
	
	//Net balance
	netCosts_eur.add(i+1, monthlyNetElectricityCosts_eur[i]);
	
	//Determine max value of the bars 
	maxChartValue_eur = max(maxChartValue_eur, max(monthlyElectricityImportCosts_eur[i], monthlyElectricityExportRevenue_eur[i]));
}

//Net costs
plot_netElectricityCostsMonthly.addDataSet(netCosts_eur, "", v_netElectricityCostColor, true, Chart.InterpolationType.INTERPOLATION_LINEAR, v_netElectricityCostLineWidth, Chart.PointStyle.POINT_CIRCLE);

//Set fixed scale
maxChartValue_eur *=1.2;
bar_importElectricityCostsMonthly.setFixedScale(0, maxChartValue_eur);
bar_exportElectricityRevenueMonthly.setFixedScale(0, maxChartValue_eur);
plot_netElectricityCostsMonthly.setFixedVerticalScale(-maxChartValue_eur, maxChartValue_eur);
plot_netElectricityCostsMonthly.setFixedHorizontalScale(0.5, 12.5);



/*ALCODEEND*/}

double[] f_calculateMonthlyElectricityImportCosts_eur(double[] netLoad_kW)
{/*ALCODESTART::1769430055658*/
int[] startHourPerMonth = startHourPerMonthTemporary;
double timeStep_h = uI_Results.energyModel.p_timeParameters.getTimeStep_h();


double VAT_fr = 0.21;
double EnergyTaxes_eur_p_kwh = 0.03868;

double[] monthlyElectricityImportCosts_euro = new double[12];

int currentMonth = 0;


for (int i = 0; i < netLoad_kW.length; i++) {
	if(currentMonth != 11 && startHourPerMonth[currentMonth+1] < i*timeStep_h){
		currentMonth += 1;
	}

    double currentElectricityPriceCharge_eurpkWh =
            uI_Results.energyModel.pp_dayAheadElectricityPricing_eurpMWh
                    .getAllValues()[
                        (int) Math.floor(i * timeStep_h)
                    ] / 1000.0;

    double timestepCost =
            (1 + VAT_fr) *
            (currentElectricityPriceCharge_eurpkWh + EnergyTaxes_eur_p_kwh) *
            max(0, netLoad_kW[i]) * 
            timeStep_h;

    monthlyElectricityImportCosts_euro[currentMonth] += timestepCost;
}

return monthlyElectricityImportCosts_euro;

/*ALCODEEND*/}

double[] f_calculateMonthlyElectricityExportRevenue_eur(double[] netLoad_kW)
{/*ALCODESTART::1769432836265*/
int[] startHourPerMonth = startHourPerMonthTemporary;
double timeStep_h = uI_Results.energyModel.p_timeParameters.getTimeStep_h();

double[] monthlyElectricityExportRevenue_euro = new double[12];

int currentMonth = 0;

for (int i = 0; i < netLoad_kW.length; i++) {
	if(currentMonth != 11 && startHourPerMonth[currentMonth+1] < i*timeStep_h){
		currentMonth += 1;
	}
    double currentElectricityPriceCharge_eurpkWh = uI_Results.energyModel.pp_dayAheadElectricityPricing_eurpMWh.getAllValues()[(int) Math.floor(i*timeStep_h)] / 1000;
    double timestepExportRevenue_euro = currentElectricityPriceCharge_eurpkWh * max(0,-netLoad_kW[i]) * timeStep_h;
	
	monthlyElectricityExportRevenue_euro[currentMonth] += timestepExportRevenue_euro;
}
 	
return monthlyElectricityExportRevenue_euro;
/*ALCODEEND*/}

