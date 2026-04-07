double f_setChartEnergyCosts()
{/*ALCODESTART::1726830495435*/
//Initialization of data object.
I_EnergyData data = uI_Results.f_getSelectedObjectData();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 60, true);

//Initialize the EnergyCarrier selection ComboBox
f_initializeECSelectionComboBox(data);

//Set the actual values of the chart (while trying to maintain the previous selected EC)
String currentSelectedECReadableName = v_selectedEnergyCarrier.equals(p_totalName) ? p_totalName : uI_Results.f_getECName(OL_EnergyCarriers.valueOf(v_selectedEnergyCarrier));
if (Arrays.asList(cb_energyCarrierSelection.getItems()).contains(currentSelectedECReadableName)) {
	cb_energyCarrierSelection.setValue(currentSelectedECReadableName, true);
}
else{
	cb_energyCarrierSelection.setValue(uI_Results.f_getECName(OL_EnergyCarriers.ELECTRICITY), true);
}
/*ALCODEEND*/}

double f_setYearlyKPIs(double totalImportCosts_eur,double totalExportRevenue_eur,double totalNetElectricityCosts_eur,Double previousTotalImportCosts_eur,Double previousTotalExportRevenue_eur,Double previousTotalNetElectricityCosts_eur)
{/*ALCODESTART::1726830499836*/
//Set new values text
DecimalFormat df = new DecimalFormat("#,##0.00");

t_totalImportCosts_eur.setText("€ " + df.format(totalImportCosts_eur));
t_totalExportRevenue_eur.setText("€ " + df.format(totalExportRevenue_eur));
t_totalNetEnergyCosts_eur.setText("€ " + df.format(totalNetElectricityCosts_eur));

if(previousTotalImportCosts_eur != null){
	t_previousTotalImportCosts_eur.setText("€ " + df.format(previousTotalImportCosts_eur));
	t_previousTotalExportRevenue_eur.setText("€ " + df.format(previousTotalExportRevenue_eur));
	t_previousTotalNetEnergyCosts_eur.setText("€ " + df.format(previousTotalNetElectricityCosts_eur));
	
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
		arrow_down_green_netEnergy.setVisible(true);
	}
	else if(totalNetElectricityCosts_eur > previousTotalNetElectricityCosts_eur){
		arrow_up_red_netEnergy.setVisible(true);
	}
	else{
		line_total.setVisible(true);
	}
}
else{ // No previous rapid data -> dont show previous values
	t_previousTotalImportCosts_eur.setText("-");
	t_previousTotalExportRevenue_eur.setText("-");
	t_previousTotalNetEnergyCosts_eur.setText("-");
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
arrow_down_green_netEnergy.setVisible(false);
arrow_up_red_import.setVisible(false);
arrow_down_red_export.setVisible(false);
arrow_up_red_netEnergy.setVisible(false);
line_total.setVisible(false);
line_import.setVisible(false);
line_export.setVisible(false);

//Clear monthly chart
bar_importEnergyCostsMonthly.removeAll();
bar_exportEnergyRevenueMonthly.removeAll();
plot_netEnergyCostsMonthly.removeAll();
gr_monthlyECCostCharts.setVisible(false);
/*ALCODEEND*/}

double f_setMonthlyChart(double[] monthlyElectricityImportCosts_eur,double[] monthlyElectricityExportRevenue_eur,double[] monthlyNetElectricityCosts_eur)
{/*ALCODESTART::1769429961370*/
DataSet netCosts_eur = new DataSet(12);
int nDecimalDigits = 2;
double maxChartValue_eur = 0;

for (int i = 0; i < 12; i++) {
	//Import cost
	DataItem importCosts_eur = new DataItem();
	importCosts_eur.setValue(roundToDecimal(monthlyElectricityImportCosts_eur[i], nDecimalDigits));
	bar_importEnergyCostsMonthly.addDataItem(importCosts_eur, "", uI_Results.v_electricityDemandColor);

	//Export revenue
	DataItem exportRevenue_eur = new DataItem();
	exportRevenue_eur.setValue(roundToDecimal(monthlyElectricityExportRevenue_eur[i], nDecimalDigits));
	bar_exportEnergyRevenueMonthly.addDataItem(exportRevenue_eur, "", uI_Results.v_electricitySupplyColor);
	
	//Net balance
	netCosts_eur.add(i+1, roundToDecimal(monthlyNetElectricityCosts_eur[i], nDecimalDigits));
	
	//Determine max value of the bars 
	maxChartValue_eur = max(maxChartValue_eur, max(monthlyElectricityImportCosts_eur[i], monthlyElectricityExportRevenue_eur[i]));
}

//Net costs
plot_netEnergyCostsMonthly.addDataSet(netCosts_eur, "", v_netECCostColor, true, Chart.InterpolationType.INTERPOLATION_LINEAR, v_netECCostLineWidth, Chart.PointStyle.POINT_CIRCLE);

//Set fixed scale
maxChartValue_eur *=1.2;
maxChartValue_eur +=1; //To prevent [0 0] axis errors if EC is not active.
bar_importEnergyCostsMonthly.setFixedScale(0, maxChartValue_eur);
bar_exportEnergyRevenueMonthly.setFixedScale(0, maxChartValue_eur);
plot_netEnergyCostsMonthly.setFixedVerticalScale(-maxChartValue_eur, maxChartValue_eur);
plot_netEnergyCostsMonthly.setFixedHorizontalScale(0.5, 12.5);

gr_monthlyECCostCharts.setVisible(true);

/*ALCODEEND*/}

double[] f_calculateMonthlyEnergyImportCosts_eur(double[] ECBalance_kW,double signalResolution_h,OL_EnergyCarriers EC)
{/*ALCODESTART::1774021264167*/
double[] startHourPerMonth = uI_Results.energyModel.p_timeParameters.getMonthStartHours();
double timeStep_h = uI_Results.energyModel.p_timeParameters.getTimeStep_h();

double energyCarrierCost_eur_p_kWh = uI_Results.energyModel.avgc_data.economicAVGC.getAvgCostOfEnergyCarrier_eurpkWh(EC);
double[] values_dayAheadElectricityPricing_eurpMWh = uI_Results.energyModel.pp_dayAheadElectricityPricing_eurpMWh.getAllValues();
double energyTaxes_eur_p_kwh = uI_Results.energyModel.avgc_data.economicAVGC.getEnergyTaxesECImport_eurpkWh(EC, ZeroMath.arraySumPos(ECBalance_kW)*signalResolution_h);
double VAT_fr = uI_Results.energyModel.avgc_data.economicAVGC.getVAT_energy_fr();


double[] monthlyECImportCosts_euro = new double[12];

int hoursInYear = 8760;
double modelStartTime_h = uI_Results.energyModel.p_timeParameters.getRunStartTime_h();
int currentMonth = 11;
for(int i = 0; i< 11;i++){
	if(startHourPerMonth[i+1]>= modelStartTime_h){
		currentMonth = i; 
		break;
	}
}

for (int i = 0; i < ECBalance_kW.length; i++) {
	if(currentMonth == 11){
		if ((i*signalResolution_h + modelStartTime_h) > hoursInYear){
			currentMonth = 0;
		}
	}
	else if(startHourPerMonth[currentMonth+1] < (i*signalResolution_h + modelStartTime_h) % hoursInYear){
		currentMonth += 1;
	}
	
	double currentECImport_kW = max(0, ECBalance_kW[i]);
	if(currentECImport_kW == 0){
		continue;
	}
	
	
	if(EC == OL_EnergyCarriers.ELECTRICITY){
		energyCarrierCost_eur_p_kWh = uI_Results.energyModel.pp_dayAheadElectricityPricing_eurpMWh.getValue((i*signalResolution_h + modelStartTime_h) % hoursInYear)/1000.0;
	}
	
    monthlyECImportCosts_euro[currentMonth] += (1 + VAT_fr)*((energyCarrierCost_eur_p_kWh + energyTaxes_eur_p_kwh) * currentECImport_kW * signalResolution_h);
}

return monthlyECImportCosts_euro;

/*ALCODEEND*/}

double[] f_calculateMonthlyEnergyExportRevenue_eur(double[] ECBalance_kW,double signalResolution_h,OL_EnergyCarriers EC)
{/*ALCODESTART::1774023398668*/
double[] startHourPerMonth = uI_Results.energyModel.p_timeParameters.getMonthStartHours();
double timeStep_h = uI_Results.energyModel.p_timeParameters.getTimeStep_h();

double energyCarrierCost_eur_p_kWh = uI_Results.energyModel.avgc_data.economicAVGC.getAvgCostOfEnergyCarrier_eurpkWh(EC);
double[] values_dayAheadElectricityPricing_eurpMWh = uI_Results.energyModel.pp_dayAheadElectricityPricing_eurpMWh.getAllValues();

double[] monthlyECExportRevenue_euro = new double[12];


int hoursInYear = 8760;
double modelStartTime_h = uI_Results.energyModel.p_timeParameters.getRunStartTime_h();
int currentMonth = 11;
for(int i = 0; i< 11;i++){
	if(startHourPerMonth[i+1]>= modelStartTime_h){
		currentMonth = i; 
		break;
	}
}
for (int i = 0; i < ECBalance_kW.length; i++) {
	if(currentMonth == 11){
		if ((i*signalResolution_h + modelStartTime_h) > hoursInYear){
			currentMonth = 0;
		}
	}
	else if(startHourPerMonth[currentMonth+1] < (i*signalResolution_h + modelStartTime_h) % hoursInYear){
		currentMonth += 1;
	}
	
	double currentECExport_kW = max(0, -ECBalance_kW[i]);
	if(currentECExport_kW == 0){
		continue;
	}

	if(EC == OL_EnergyCarriers.ELECTRICITY){
		energyCarrierCost_eur_p_kWh = uI_Results.energyModel.pp_dayAheadElectricityPricing_eurpMWh.getValue((i*signalResolution_h + modelStartTime_h) % hoursInYear)/1000.0;
	}

    monthlyECExportRevenue_euro[currentMonth] += energyCarrierCost_eur_p_kWh * currentECExport_kW * signalResolution_h;
}

return monthlyECExportRevenue_euro;

/*ALCODEEND*/}

double[] f_calculateMonthlyNetEnergyCosts_eur(double[] monthlyECImportCosts_euro,double[] monthlyECExportRevenue_euro)
{/*ALCODESTART::1774023455637*/
if(monthlyECImportCosts_euro.length != monthlyECExportRevenue_euro.length){
	throw new RuntimeException("Trying to calculate the net energy cost per month for a monthly import and export array that is not the same size!");
}

double[] monthlyNetEnergyCost_euro = new double[monthlyECImportCosts_euro.length];

for (int i = 0; i < monthlyECImportCosts_euro.length; i++) {
    monthlyNetEnergyCost_euro[i] = monthlyECImportCosts_euro[i] - monthlyECExportRevenue_euro[i];
}

return monthlyNetEnergyCost_euro;

/*ALCODEEND*/}

double f_setChartEnergyCostsValues()
{/*ALCODESTART::1774259541182*/
I_EnergyData data = uI_Results.f_getSelectedObjectData();

//Reset chart
f_resetChart();


//Get selected List
List<OL_EnergyCarriers> selectedECList;
if(v_selectedEnergyCarrier.equals(p_totalName)){
	if(uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE){
		selectedECList = new ArrayList<>(List.of(uI_Results.v_gridNode.p_energyCarrier));
	}
	else{
		selectedECList = new ArrayList<>(data.getRapidRunData().activeEnergyCarriers);
	}
}
else{
	selectedECList = new ArrayList<>(List.of(OL_EnergyCarriers.valueOf(v_selectedEnergyCarrier)));
}


double[] monthlyImportCosts_eur = new double[12];
double[] monthlyExportRevenue_eur = new double[12];

for(OL_EnergyCarriers EC : selectedECList){
	//Get the ECBalance values
	double[] ECBalance_kW;
	double signalResolution_h;
	if(uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE){
		if(EC == OL_EnergyCarriers.ELECTRICITY){
			ECBalance_kW = uI_Results.v_gridNode.acc_annualElectricityBalance_kW.getTimeSeries_kW();
			signalResolution_h = uI_Results.v_gridNode.acc_annualElectricityBalance_kW.getSignalResolution_h();
		}
		else{ //if(EC == OL_EnergyCarriers.HEAT){
			ECBalance_kW = uI_Results.v_gridNode.acc_annualHeatBalance_kW.getTimeSeries_kW();
			signalResolution_h = uI_Results.v_gridNode.acc_annualHeatBalance_kW.getSignalResolution_h();
		}
	}
	else{
		if(EC == OL_EnergyCarriers.HEAT && !data.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.districtHeatDelivery_kW)){
			continue;
		}
		ECBalance_kW = data.getRapidRunData().am_totalBalanceAccumulators_kW.get(EC).getTimeSeries_kW();
		signalResolution_h = data.getRapidRunData().am_totalBalanceAccumulators_kW.get(EC).getSignalResolution_h();
	}
	
	//Calculate values
	double[] monthlyECImportCosts_eur = f_calculateMonthlyEnergyImportCosts_eur(ECBalance_kW, signalResolution_h, EC);
	double[] monthlyECExportRevenue_eur = f_calculateMonthlyEnergyExportRevenue_eur(ECBalance_kW, signalResolution_h, EC);
	
	//Add values of this EC to the total
	for(int i = 0; i < 12; i++){
		monthlyImportCosts_eur[i] += monthlyECImportCosts_eur[i];
		monthlyExportRevenue_eur[i] += monthlyECExportRevenue_eur[i];
	}	
}

double[] monthlyNetCosts_eur = f_calculateMonthlyNetEnergyCosts_eur(monthlyImportCosts_eur, monthlyExportRevenue_eur);
	
double totalImportCosts_eur = ZeroMath.arraySum(monthlyImportCosts_eur);
double totalExportRevenue_eur = ZeroMath.arraySum(monthlyExportRevenue_eur);
double totalNetCosts_eur = ZeroMath.arraySum(monthlyNetCosts_eur);


//Previous values
Double previousTotalImportCosts_eur = null;
Double previousTotalExportRevenue_eur = null;
Double previousTotalNetCosts_eur = null;

if(data.getPreviousRapidRunData() != null){
	//It is possible that previous rapid run has other set of EC than current rapid run, so list needs to be recreated.
	List<OL_EnergyCarriers> selectedECList_previousRapidRun =new ArrayList<>();
	if(v_selectedEnergyCarrier.equals("Totaal")){ 
		selectedECList_previousRapidRun.addAll(data.getPreviousRapidRunData().activeEnergyCarriers);
	}
	else if(data.getPreviousRapidRunData().activeEnergyCarriers.contains(selectedECList.get(0))){
		selectedECList_previousRapidRun = selectedECList;
	}
	//Initialize previous values with 0. (Even if selectedECList_previousRapidRun remains empty due to diff in EC between rapid runs this makes sense!)
	previousTotalImportCosts_eur = 0.0;
	previousTotalExportRevenue_eur = 0.0;
	previousTotalNetCosts_eur = 0.0;		

	for(OL_EnergyCarriers EC : selectedECList_previousRapidRun){
		if(EC == OL_EnergyCarriers.HEAT && !data.getPreviousRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.districtHeatDelivery_kW)){
			continue;
		}
		
		double[] previousECBalance_kW = data.getPreviousRapidRunData().am_totalBalanceAccumulators_kW.get(EC).getTimeSeries_kW();
		double previousSignalResolution_h = data.getPreviousRapidRunData().am_totalBalanceAccumulators_kW.get(EC).getSignalResolution_h();
		
		double[] previousMonthlyECImportCosts_eur = f_calculateMonthlyEnergyImportCosts_eur(previousECBalance_kW, previousSignalResolution_h, EC);
		double[] previousMonthlyECExportRevenue_eur = f_calculateMonthlyEnergyExportRevenue_eur(previousECBalance_kW, previousSignalResolution_h, EC);
		double[] previousMonthlyNetECCosts_eur = f_calculateMonthlyNetEnergyCosts_eur(previousMonthlyECImportCosts_eur, previousMonthlyECExportRevenue_eur);
		
	
		previousTotalImportCosts_eur += ZeroMath.arraySum(previousMonthlyECImportCosts_eur);
		previousTotalExportRevenue_eur += ZeroMath.arraySum(previousMonthlyECExportRevenue_eur);
		previousTotalNetCosts_eur += ZeroMath.arraySum(previousMonthlyNetECCosts_eur);
	}
}


//Set yearly kpis
f_setYearlyKPIs(totalImportCosts_eur, totalExportRevenue_eur, totalNetCosts_eur, previousTotalImportCosts_eur, previousTotalExportRevenue_eur, previousTotalNetCosts_eur);

//Set monthly chart
if(uI_Results.energyModel.p_timeParameters.getRunDuration_h() >= 8760){
	f_setMonthlyChart(monthlyImportCosts_eur, monthlyExportRevenue_eur, monthlyNetCosts_eur);
}

/*ALCODEEND*/}

double f_getTotalEnergyCosts_eurpyr(J_RapidRunData rapidRunData)
{/*ALCODESTART::1774263422526*/
double[] monthlyImportCosts_eur = new double[12];
double[] monthlyExportRevenue_eur = new double[12];

for(OL_EnergyCarriers EC : rapidRunData.activeEnergyCarriers){
	if(EC == OL_EnergyCarriers.HEAT && !rapidRunData.assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.districtHeatDelivery_kW)){
		continue;
	}
	//Get the ECBalance values
	double[] ECBalance_kW;
	double signalResolution_h;
	ECBalance_kW = rapidRunData.am_totalBalanceAccumulators_kW.get(EC).getTimeSeries_kW();
	signalResolution_h = rapidRunData.am_totalBalanceAccumulators_kW.get(EC).getSignalResolution_h();
	
	//Current values
	double[] monthlyECImportCosts_eur = f_calculateMonthlyEnergyImportCosts_eur(ECBalance_kW, signalResolution_h, EC);
	double[] monthlyECExportRevenue_eur = f_calculateMonthlyEnergyExportRevenue_eur(ECBalance_kW, signalResolution_h, EC);

	//Add values of this EC to the total
	for(int i = 0; i < 12; i++){
		monthlyImportCosts_eur[i] += monthlyECImportCosts_eur[i];
		monthlyExportRevenue_eur[i] += monthlyECExportRevenue_eur[i];
	}
}

double[] monthlyNetCosts_eur = f_calculateMonthlyNetEnergyCosts_eur(monthlyImportCosts_eur, monthlyExportRevenue_eur);

double totalNetCosts_eur = ZeroMath.arraySum(monthlyNetCosts_eur);

return totalNetCosts_eur;
/*ALCODEEND*/}

double f_initializeECSelectionComboBox(I_EnergyData data)
{/*ALCODESTART::1774276016703*/
//Get energy carrier options (also previous run EC that are not in current).
Set<OL_EnergyCarriers> energyCarriers = new HashSet<>();
if(uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE){
	energyCarriers.add(uI_Results.v_gridNode.p_energyCarrier);
}
else{
	energyCarriers.addAll(data.getRapidRunData().activeEnergyCarriers);
	boolean hasHadDistrictHeating = data.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.districtHeatDelivery_kW);
	
	if(data.getPreviousRapidRunData() != null){
		energyCarriers.addAll(data.getPreviousRapidRunData().activeEnergyCarriers);
		if(!hasHadDistrictHeating){
			hasHadDistrictHeating = data.getPreviousRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.districtHeatDelivery_kW);
		}
	}
	
	if(energyCarriers.contains(OL_EnergyCarriers.HEAT) && !hasHadDistrictHeating){
		energyCarriers.remove(OL_EnergyCarriers.HEAT);
	}
}

//Order the list to always have the same order
List<OL_EnergyCarriers> orderedEnergyCarriers = new ArrayList<>();
for(OL_EnergyCarriers EC : c_defaultOrderEC){
	if(energyCarriers.contains(EC)){
		orderedEnergyCarriers.add(EC);
	}
}


//Convert to the readable combobox options
String[] comboBoxOptions = new String[orderedEnergyCarriers.size() + 1];
int i = 0;
for(OL_EnergyCarriers EC : orderedEnergyCarriers){
	comboBoxOptions[i] = uI_Results.f_getECName(EC);
	i++;
}
comboBoxOptions[i] = p_totalName;

cb_energyCarrierSelection.setItems(comboBoxOptions);

/*ALCODEEND*/}

