double f_setYearlyKPIs(Double totalCO2Emission_kg,Double previousTotalCO2Emission_kg)
{/*ALCODESTART::1772441389069*/
//Set new values text
DecimalFormat df = new DecimalFormat("0.00");

t_totalCO2Emission_kg.setText(df.format(totalCO2Emission_kg/1000.0) + " ton");

if(previousTotalCO2Emission_kg != null){
	t_previousTotalCO2Emission_kg.setText(df.format(previousTotalCO2Emission_kg/1000.0) + " ton");
	
	////Set arrows
	if(previousTotalCO2Emission_kg > totalCO2Emission_kg){
		arrow_down_green_CO2Emission.setVisible(true);
	}
	else if(totalCO2Emission_kg > previousTotalCO2Emission_kg){
		arrow_up_red_CO2Emission.setVisible(true);
	}
	else{
		line_CO2Emission.setVisible(true);
	}
}
else{ // No previous rapid data -> dont show previous values
	t_previousTotalCO2Emission_kg.setText("-");
}
/*ALCODEEND*/}

double f_styleBackground_override(Color backgroundColor,Color lineColor,Double lineWidth,LineStyle lineStyle)
{/*ALCODESTART::1772441389071*/
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
{/*ALCODESTART::1772441389073*/
//Reset all arrow visibility
arrow_down_green_CO2Emission.setVisible(false);
arrow_up_red_CO2Emission.setVisible(false);
line_CO2Emission.setVisible(false);

//Reset KPIS
t_totalCO2Emission_kg.setText("-");
t_previousTotalCO2Emission_kg.setText("-");

//Clear monthly chart
bar_CO2EmissionMonthly.removeAll();
gr_monthlyCO2EmissionCharts.setVisible(false);

//Clear pie chart
pieChart_totalSubdivision.removeAll();
gr_subChart_totalSubdivision.setVisible(false);
/*ALCODEEND*/}

double f_setMonthlyChart(double[] monthlyCO2Emission_kg)
{/*ALCODESTART::1772441389081*/
DataSet netCosts_eur = new DataSet(12);

double maxChartValue_eur = 0;

for (int i = 0; i < 12; i++) {
	//Import cost
	DataItem CO2Emission_kg = new DataItem();
	CO2Emission_kg.setValue(monthlyCO2Emission_kg[i]/1000.0);
	bar_CO2EmissionMonthly.addDataItem(CO2Emission_kg, c_monthsOfTheYear.get(i), uI_Results.v_electricityDemandColor);
	
	maxChartValue_eur = max(maxChartValue_eur, monthlyCO2Emission_kg[i]/1000.0);
}

//Set fixed scale
maxChartValue_eur *=1.2;
bar_CO2EmissionMonthly.setFixedScale(0, maxChartValue_eur);
gr_monthlyCO2EmissionCharts.setVisible(true);
/*ALCODEEND*/}

double[] f_calculateMonthlyECCO2Emission_kg(double[] ECBalance_kW,double signalResolution_h,OL_EnergyCarriers EC)
{/*ALCODESTART::1772643915089*/
double[] startHourPerMonth = uI_Results.energyModel.p_timeParameters.getMonthStartHours();
double timeStep_h = uI_Results.energyModel.p_timeParameters.getTimeStep_h();

double energyCarrierCO2Emission_kg_p_kWh = uI_Results.energyModel.avgc_data.map_avgCO2EmissionOfEnergyCarrier_kgpkWh.get(EC);

double[] monthlyElectricityImportCO2Emission_kg = new double[12];

int currentMonth = 0;

for (int i = 0; i < ECBalance_kW.length; i++) {
	if(currentMonth != 11 && startHourPerMonth[currentMonth+1] < i*signalResolution_h){
		currentMonth += 1;
	}
	
	if(EC == OL_EnergyCarriers.ELECTRICITY){
		energyCarrierCO2Emission_kg_p_kWh = uI_Results.energyModel.pp_CO2EmissionFactorElectricityImport_kgpkWh.getAllValues()[(int) Math.floor(i * signalResolution_h)];
	}
	
    double timestepCO2Emission_kg = energyCarrierCO2Emission_kg_p_kWh * max(0, ECBalance_kW[i] ) * signalResolution_h;
    
    monthlyElectricityImportCO2Emission_kg[currentMonth] += timestepCO2Emission_kg;
}

return monthlyElectricityImportCO2Emission_kg;
/*ALCODEEND*/}

double f_setCustomCO2Map(Map<String, Pair<Double, Color>> customCO2AdditionsMap)
{/*ALCODESTART::1774608778627*/
map_customCO2Additions_kg = customCO2AdditionsMap;
/*ALCODEEND*/}

double f_setChartCO2()
{/*ALCODESTART::1774618389588*/
//Initialization of data object.
I_EnergyData data = uI_Results.f_getSelectedObjectData();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 60, true);

//Initialize the EnergyCarrier selection ComboBox
f_initializeECSelectionComboBox(data);

//Set support chart defauls as monthly
v_selectedSupportChart = "Monthly";

//Set the actual values of the chart (while trying to maintain the previous selected EC)
String currentSelectedECReadableName = v_selectedEnergyCarrier.equals(p_totalName) ? p_totalName : uI_Results.f_getECName(OL_EnergyCarriers.valueOf(v_selectedEnergyCarrier));
if (Arrays.asList(cb_energyCarrierSelection.getItems()).contains(currentSelectedECReadableName)) {
	cb_energyCarrierSelection.setValue(currentSelectedECReadableName, true);
}
else{
	cb_energyCarrierSelection.setValue(uI_Results.f_getECName(OL_EnergyCarriers.ELECTRICITY), true);
}
/*ALCODEEND*/}

double f_setChartCO2Values()
{/*ALCODESTART::1774618389602*/
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
		selectedECList = new ArrayList<>(data.getRapidRunData().activeConsumptionEnergyCarriers);
	}
}
else{
	selectedECList = new ArrayList<>(List.of(OL_EnergyCarriers.valueOf(v_selectedEnergyCarrier)));
}


double[] monthlyCO2Emissions_kg = new double[12];
Map<OL_EnergyCarriers, double[]> map_monthlyCO2EmissionsPerEC_kg = new HashMap<>();

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
	double[] monthlyECCO2Emissions_kg = f_calculateMonthlyECCO2Emission_kg(ECBalance_kW, signalResolution_h, EC);
	
	//Save totals also per EC
	map_monthlyCO2EmissionsPerEC_kg.put(EC, monthlyECCO2Emissions_kg);
	
	//Add values of this EC to the total
	for(int i = 0; i < 12; i++){
		monthlyCO2Emissions_kg[i] += monthlyECCO2Emissions_kg[i];
	}	
}

//Calculate total CO2 emissions due EC import
double totalCO2Emissions_kg = ZeroMath.arraySum(monthlyCO2Emissions_kg);


//Add custom co2 additions
if(map_customCO2Additions_kg != null && map_customCO2Additions_kg.size()>0){
	totalCO2Emissions_kg += sum(map_customCO2Additions_kg.values(), value -> max(0, value.getFirst().doubleValue()));
}


//Previous values
Double previoustotalCO2Emissions_kg = null;

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
	previoustotalCO2Emissions_kg = 0.0;		

	for(OL_EnergyCarriers EC : selectedECList_previousRapidRun){
		if(EC == OL_EnergyCarriers.HEAT && !data.getPreviousRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.districtHeatDelivery_kW)){
			continue;
		}
		
		double[] previousECBalance_kW = data.getPreviousRapidRunData().am_totalBalanceAccumulators_kW.get(EC).getTimeSeries_kW();
		double previousSignalResolution_h = data.getPreviousRapidRunData().am_totalBalanceAccumulators_kW.get(EC).getSignalResolution_h();
		
		double[] previousMonthlyECCO2Emissions_kg = f_calculateMonthlyECCO2Emission_kg(previousECBalance_kW, previousSignalResolution_h, EC);
	
		previoustotalCO2Emissions_kg += ZeroMath.arraySum(previousMonthlyECCO2Emissions_kg);
	}
	
	//Add custom co2 additions previous run to previous total
	if(map_customCO2Additions_previous_kg != null && map_customCO2Additions_previous_kg.size()>0){
		previoustotalCO2Emissions_kg += sum(map_customCO2Additions_previous_kg.values(), value -> max(0, value.getFirst().doubleValue()));
	}
}



//Set yearly kpis
f_setYearlyKPIs(totalCO2Emissions_kg, previoustotalCO2Emissions_kg);

//Set monthly chart
if(uI_Results.energyModel.p_timeParameters.getRunDuration_h() >= 8760 && v_selectedSupportChart.equals("Monthly")){
	f_setMonthlyChart(monthlyCO2Emissions_kg);
}
else if(v_selectedEnergyCarrier.equals(p_totalName)){
	f_setPieChart(map_monthlyCO2EmissionsPerEC_kg);
}

/*ALCODEEND*/}

double f_initializeECSelectionComboBox(I_EnergyData data)
{/*ALCODESTART::1774618389606*/
//Get energy carrier options (also previous run EC that are not in current).
Set<OL_EnergyCarriers> energyCarriers = new HashSet<>();
if(uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE){
	energyCarriers.add(uI_Results.v_gridNode.p_energyCarrier);
}
else{
	energyCarriers.addAll(data.getRapidRunData().activeConsumptionEnergyCarriers);
	boolean hasHadDistrictHeating = data.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.districtHeatDelivery_kW);
	
	if(data.getPreviousRapidRunData() != null){
		energyCarriers.addAll(data.getPreviousRapidRunData().activeConsumptionEnergyCarriers);
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

double f_storePreviousCustomCO2AdditionsMap()
{/*ALCODESTART::1774623387767*/
map_customCO2Additions_previous_kg = map_customCO2Additions_kg;
/*ALCODEEND*/}

double f_setPieChart(Map<OL_EnergyCarriers, double[]> map_totalCO2EmissionsPerEC_kg)
{/*ALCODESTART::1774888957180*/
for(OL_EnergyCarriers EC : map_totalCO2EmissionsPerEC_kg.keySet()){
	DataItem CO2Emission_ton = new DataItem();
	CO2Emission_ton.setValue(ZeroMath.arraySum(map_totalCO2EmissionsPerEC_kg.get(EC))/1000.0);
	pieChart_totalSubdivision.addDataItem(CO2Emission_ton, uI_Results.f_getECName(EC), uI_Results.cm_consumptionColors.get(EC));
}

if(map_customCO2Additions_kg != null){
	for(String customEntry : map_customCO2Additions_kg.keySet()){
		DataItem CO2Emission_ton = new DataItem();
		CO2Emission_ton.setValue(map_customCO2Additions_kg.get(customEntry).getFirst()/1000.0);
		pieChart_totalSubdivision.addDataItem(CO2Emission_ton, customEntry, map_customCO2Additions_kg.get(customEntry).getSecond());
	}
}

v_selectedSupportChart = "Pie";
gr_subChart_totalSubdivision.setVisible(true);
/*ALCODEEND*/}

