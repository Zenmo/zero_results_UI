double f_showCorrectChart()
{/*ALCODESTART::1714902615653*/
gr_chartProfielen_presentation.setVisible(false);
gr_chartBalans_presentation.setVisible(false);
gr_chartNetbelasting_presentation.setVisible(false);
gr_chartSankey_presentation.setVisible(false);
gr_chartSummary_presentation.setVisible(false);
gr_chartGespreksLeidraad_presentation.setVisible(false);
gr_chartKPISummary_presentation.setVisible(false);
gr_chartBatteries_presentation.setVisible(false);

if(b_showKPISummary){
	gr_chartKPISummary_presentation.setVisible(true);
	chartKPISummary.f_setKPISummaryChart();
}

switch (v_selectedChartType) {
	case PROFIEL:
		gr_chartProfielen_presentation.setVisible(true);
		chartProfielen.f_setCharts();
		break;
	case DIAGRAM:
		gr_chartBalans_presentation.setVisible(true);
		chartBalans.f_setCharts();
		break;
	case BELASTING:
		gr_chartNetbelasting_presentation.setVisible(true);
		chartNetbelasting.f_setBelastingPlots();
		break;
	case SANKEY:
		gr_chartSankey_presentation.setVisible(true);
		chartSankey.f_setSankey();
		break;
	case GESPREKSLEIDRAAD_SAMENVATTING:
		gr_chartSummary_presentation.setVisible(true);
		chartSummary.f_setSummaryCharts();
		break;
	case GESPREKSLEIDRAAD:
		gr_chartGespreksLeidraad_presentation.setVisible(true);
		chartGespreksLeidraad.f_selectGespreksleidraadCharts();
		break;
	case BATTERY:
		gr_chartBatteries_presentation.setVisible(true);
		chartBatteries.f_setChartsBatteries();
		break;
}
/*ALCODEEND*/}

double f_setResultsUIHeader(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1725370602118*/
//Set the location and visibility of the radiobuttons

//Set x axis
if(location_x != null){
	gr_resultsUIHeader.setX(location_x);
}

//Set y axis
if(location_x != null){
	gr_resultsUIHeader.setY(location_y);
}

//Set visibility
gr_resultsUIHeader.setVisible(visible);
/*ALCODEEND*/}

double f_setChartSankey_Presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1725371353897*/
//Set the location and visibility of the sankey charts presentation

//Set x axis
if(location_x != null){
	gr_chartSankey_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartSankey_presentation.setY(location_y);
}

//Set visibility
gr_chartSankey_presentation.setVisible(visible);
/*ALCODEEND*/}

double f_setChartGridLoad_Presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1725371355774*/
//Set the location and visibility of the gridload charts presentation

//Set x axis
if(location_x != null){
	gr_chartNetbelasting_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartNetbelasting_presentation.setY(location_y);
}

//Set visibility
gr_chartNetbelasting_presentation.setVisible(visible);
/*ALCODEEND*/}

double f_setChartBalance_Presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1725371358501*/
//Set the location and visibility of the balance charts presentation

//Set x axis
if(location_x != null){
	gr_chartBalans_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartBalans_presentation.setY(location_y);
}

//Set visibility
gr_chartBalans_presentation.setVisible(visible);
/*ALCODEEND*/}

double f_setChartProfiles_Presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1725371361557*/
//Set the location and visibility of the profiles charts presentation

//Set x axis
if(location_x != null){
	gr_chartProfielen_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartProfielen_presentation.setY(location_y);
}

//Set visibility
gr_chartProfielen_presentation.setVisible(visible);
/*ALCODEEND*/}

double f_setAllCharts()
{/*ALCODESTART::1725373315876*/
//Function to set all charts, without changing visibilities
chartProfielen.f_setCharts();
chartBalans.f_setCharts();
chartNetbelasting.f_setBelastingPlots();
chartSankey.f_setSankey();
chartSummary.f_setSummaryCharts();

if(b_showKPISummary){
	chartKPISummary.f_setKPISummaryChart();
}
/*ALCODEEND*/}

double f_styleAllCharts(Color backgroundColor,Color lineColor,Double lineWidth,LineStyle lineStyle)
{/*ALCODESTART::1725374888930*/
//Function to style all chart (backgrounds)
chartProfielen.f_styleBackground(backgroundColor, lineColor, lineWidth, lineStyle);
chartBalans.f_styleBackground(backgroundColor, lineColor, lineWidth, lineStyle);
chartNetbelasting.f_styleBackground(backgroundColor, lineColor, lineWidth, lineStyle);
chartSankey.f_styleBackground(backgroundColor, lineColor, lineWidth, lineStyle);
chartKPISummary.f_styleBackground(backgroundColor, lineColor, lineWidth, lineStyle);
chartGespreksLeidraad.f_styleBackground(backgroundColor, lineColor, lineWidth, lineStyle);
chartSummary.f_styleBackground(backgroundColor, lineColor, lineWidth, lineStyle);
chartBatteries.f_styleBackground(backgroundColor, lineColor, lineWidth, lineStyle);


/*ALCODEEND*/}

double f_styleResultsUIHeader(Color backgroundColor,Color lineColor,double lineWidth,LineStyle lineStyle)
{/*ALCODESTART::1725375062400*/
//Function to style all chart (backgrounds)
rect_resultsMenuLarge.setFillColor(backgroundColor);
rect_resultsMenuLarge.setLineColor(lineColor);
rect_resultsMenuLarge.setLineWidth(lineWidth);
rect_resultsMenuLarge.setLineStyle(lineStyle);

/*ALCODEEND*/}

double f_setChartSummary_Presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1727084763017*/
//Set the location and visibility of the GSLD Summary charts presentation

//Set x axis
if(location_x != null){
	gr_chartSummary_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartSummary_presentation.setY(location_y);
}

//Set visibility
gr_chartSummary_presentation.setVisible(visible);
/*ALCODEEND*/}

double f_setChartKPISummary_Presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1726828282517*/
//Set the location and visibility of the KPI summary charts presentation

//Set x axis
if(location_x != null){
	gr_chartKPISummary_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartKPISummary_presentation.setY(location_y);
}

//Set visibility
gr_chartKPISummary_presentation.setVisible(visible);
/*ALCODEEND*/}

AreaCollection f_getDataObject()
{/*ALCODESTART::1727083916594*/
AreaCollection dataObject;

if (v_selectedObjectType == OL_GISObjectType.GRIDNODE) {
	dataObject = v_trafo;
} 
else if (v_selectedObjectType == OL_GISObjectType.NEIGHBOURHOOD ||
		 v_selectedObjectType == OL_GISObjectType.BUILDING ||
		 v_selectedObjectType == OL_GISObjectType.CHARGER ||
		 v_selectedObjectType == OL_GISObjectType.WINDFARM ||
		 v_selectedObjectType == OL_GISObjectType.SOLARFARM ||
		 v_selectedObjectType == OL_GISObjectType.ELECTROLYSER ||
		 v_selectedObjectType == OL_GISObjectType.BATTERY ) {
	dataObject = v_gridConnection;
}
else {
	dataObject = v_area;
}

return dataObject;
/*ALCODEEND*/}

double f_initializePreviousTotals()
{/*ALCODESTART::1727103171052*/
//Create previous totals instance for the whole region
v_area.v_previousTotals = new J_previousTotals();

//Create new instance of previousTotals for each GC and add to the hashmap
for(GridConnection GC : energyModel.f_getGridConnections()){
	J_previousTotals previousTotals = new J_previousTotals();
	c_previousTotals_GC.put(GC, previousTotals);
}

//Do this also for paused GC!!
for(GridConnection GC : energyModel.f_getPausedGridConnections()){
	J_previousTotals previousTotals = new J_previousTotals();
	c_previousTotals_GC.put(GC, previousTotals);
}
/*ALCODEEND*/}

double f_setAllChart_Presentations(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1729004655678*/
//Function used to set all charts at the same place
f_setChartProfiles_Presentation(location_x, location_y, visible);
f_setChartBalance_Presentation(location_x, location_y, visible);
f_setChartGridLoad_Presentation(location_x, location_y, visible);
f_setChartSankey_Presentation(location_x, location_y, visible);
f_setChartSummary_Presentation(location_x, location_y, visible);
f_setChartGSLD_Presentation(location_x, location_y, visible);
f_setChartKPISummary_Presentation(location_x, location_y, visible);
f_setChartBatteries_presentation(location_x, location_y, visible);
/*ALCODEEND*/}

double f_setChartGSLD_Presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1730396435330*/
//Set the location and visibility of the GSLD charts presentation

//Set x axis
if(location_x != null){
	gr_chartGespreksLeidraad_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartGespreksLeidraad_presentation.setY(location_y);
}

//Set visibility
gr_chartGespreksLeidraad_presentation.setVisible(visible);
/*ALCODEEND*/}

double f_setSelectedRadioButton()
{/*ALCODESTART::1732015499114*/
rb_DEFAULT.setVisible(false);
rb_DEFAULT_AND_GESPREKSLEIDRAAD.setVisible(false);
rb_DEFAULT_AND_GESPREKSLEIDRAADSUMMARY.setVisible(false);
rb_DEFAULT_AND_BATTERY.setVisible(false);
rb_DEFAULT.setEnabled(false);
rb_DEFAULT_AND_GESPREKSLEIDRAAD.setEnabled(false);
rb_DEFAULT_AND_GESPREKSLEIDRAADSUMMARY.setEnabled(false);
rb_DEFAULT_AND_BATTERY.setEnabled(false);

switch(v_selectedRadioButton){

case DEFAULT:
	rb_DEFAULT.setVisible(true);
	rb_DEFAULT.setEnabled(true);
	break;
case DEFAULT_AND_GESPREKSLEIDRAAD:
	rb_DEFAULT_AND_GESPREKSLEIDRAAD.setVisible(true);
	rb_DEFAULT_AND_GESPREKSLEIDRAAD.setEnabled(true);
	break;
case DEFAULT_AND_GESPREKSLEIDRAADSUMMARY:
	rb_DEFAULT_AND_GESPREKSLEIDRAADSUMMARY.setVisible(true);
	rb_DEFAULT_AND_GESPREKSLEIDRAADSUMMARY.setEnabled(true);
	break;
case DEFAULT_AND_BATTERY:
	rb_DEFAULT_AND_BATTERY.setVisible(true);
	rb_DEFAULT_AND_BATTERY.setEnabled(true);
	break;
case OFF:
	f_setResultsUIHeader(null, null, false);
}	
/*ALCODEEND*/}

String f_getName(OL_EnergyCarriers energyCarrier)
{/*ALCODESTART::1731578318216*/
switch (energyCarrier) {
	case ELECTRICITY:
		return "Electriciteit";
	case HEAT:
		return "Warmte";
	case METHANE:
		return "Gas";
	case DIESEL:
		return "Diesel";
	case HYDROGEN:
		return "Waterstof";
	default:
		throw new RuntimeException("Onbekende energiedrager, kan niet vertaald worden.");
}



// The code below return the name in English
/*
String s = energyCarrier.toString();
return s.substring(0, 1) + s.substring(1).toLowerCase();
*/
/*ALCODEEND*/}

double f_setChartGelijktijdigheid_Presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1732117856053*/
//Set the location and visibility of the balance charts presentation

//Set x axis
if(location_x != null){
	gr_chartGelijktijdigheid_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartGelijktijdigheid_presentation.setY(location_y);
}

//Set visibility
gr_chartGelijktijdigheid_presentation.setVisible(visible);
/*ALCODEEND*/}

double f_setChartBatteries_presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1737545003506*/
//Set the location and visibility of the Batteries charts presentation

//Set x axis
if(location_x != null){
	gr_chartBatteries_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartBatteries_presentation.setY(location_y);
}

//Set visibility
gr_chartBatteries_presentation.setVisible(visible);
/*ALCODEEND*/}

double f_setChartsBatteries()
{/*ALCODESTART::1737563326718*/
chartBatteries.f_setChartsBatteries();
/*ALCODEEND*/}

double f_initializeResultsUI()
{/*ALCODESTART::1739364390433*/
//Initialize main area collection
f_initializeMainAreaCollection();

//Initialize previous totals
f_initializePreviousTotals();

//Initialize other area collections
v_gridConnection = add_pop_areaResults(OL_GISObjectType.BUILDING, "GC");
v_trafo = add_pop_areaResults(OL_GISObjectType.GRIDNODE, "GN");
v_collective = add_pop_areaResults();

//Set the selected radiobutton setup
f_setSelectedRadioButton();

//Initialize profiles graph (starting chart)
chartProfielen.f_setCharts();

/*ALCODEEND*/}

double f_updateUIresultsMainArea()
{/*ALCODESTART::1739364390437*/
AreaCollection area = v_area;

//Set active energyCarriers
area.v_activeProductionEnergyCarriers = energyModel.v_activeProductionEnergyCarriers;
area.v_activeConsumptionEnergyCarriers = energyModel.v_activeConsumptionEnergyCarriers;

//Previous totals
area.v_previousTotals.setPreviousTotalImports_MWh(area.fm_totalImports_MWh);
area.v_previousTotals.setPreviousTotalExports_MWh(area.fm_totalExports_MWh);
area.v_previousTotals.setPreviousTotalConsumedEnergy_MWh(area.v_totalEnergyConsumed_MWh);
area.v_previousTotals.setPreviousTotalProducedEnergy_MWh(area.v_totalEnergyProduced_MWh);
area.v_previousTotals.setPreviousSelfConsumedEnergy_MWh(area.v_totalEnergySelfConsumed_MWh);
area.v_previousTotals.setPreviousImportedEnergy_MWh(area.v_totalEnergyImport_MWh);
area.v_previousTotals.setPreviousExportedEnergy_MWh(area.v_totalEnergyExport_MWh);
area.v_previousTotals.setPreviousSelfConsumedElectricity_MWh(area.v_totalElectricitySelfConsumed_MWh);
area.v_previousTotals.setPreviousElectricityConsumed_MWh(area.v_totalElectricityConsumed_MWh);
area.v_previousTotals.setPreviousTotalTimeOverloadedTransformers_hr(area.v_totalTimeOverloaded_h);

// Net Load
area.v_dataNetLoadYear_kW = energyModel.am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getTimeSeries();
area.data_gridCapacityDeliveryYear_kW.add(0, energyModel.f_getGridNodesTopLevel().get(0).p_capacity_kW);
area.data_gridCapacityDeliveryYear_kW.add(8760, energyModel.f_getGridNodesTopLevel().get(0).p_capacity_kW);
area.data_gridCapacityFeedInYear_kW.add(0, -energyModel.f_getGridNodesTopLevel().get(0).p_capacity_kW);
area.data_gridCapacityFeedInYear_kW.add(8760, -energyModel.f_getGridNodesTopLevel().get(0).p_capacity_kW);

//Datasets for live charts
//Demand
area.dsm_liveConsumption_kW = energyModel.dsm_liveDemand_kW;
area.v_dataElectricityBaseloadConsumptionLiveWeek_kW = energyModel.data_baseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionLiveWeek_kW = energyModel.data_heatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionLiveWeek_kW = energyModel.data_electricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionLiveWeek_kW = energyModel.data_batteryCharging_kW;
area.v_dataElectricityForHydrogenConsumptionLiveWeek_kW = energyModel.data_hydrogenElectricityDemand_kW;
area.v_dataElectricityForCookingConsumptionLiveWeek_kW = energyModel.data_cookingElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionLiveWeek_kW = energyModel.data_districtHeatingDemand_kW;

//Supply
area.dsm_liveProduction_kW = energyModel.dsm_liveSupply_kW;
area.v_dataWindElectricityProductionLiveWeek_kW = energyModel.data_windGeneration_kW;
area.v_dataPVElectricityProductionLiveWeek_kW = energyModel.data_PVGeneration_kW;
area.v_dataStorageElectricityProductionLiveWeek_kW = energyModel.data_batteryDischarging_kW;
area.v_dataV2GElectricityProductionLiveWeek_kW = energyModel.data_V2GSupply_kW;
area.v_dataCHPElectricityProductionLiveWeek_kW = energyModel.data_CHPElectricityProductionLiveWeek_kW;

//SOC
area.v_dataBatterySOCLiveWeek_.reset();
for (int i = 0; i < energyModel.data_batteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = energyModel.data_batteryStoredEnergy_MWh.getX(i);
    double y = energyModel.data_batteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCLiveWeek_.add(x, SOC);
}

//Total
area.v_dataNetLoadLiveWeek_kW = energyModel.data_totalGridLoad_kW;
area.v_batteryStorageCapacityInstalled_MWh = energyModel.v_totalInstalledBatteryStorageCapacity_MWh;

//Capacity
area.v_dataElectricityDeliveryCapacityLiveWeek_kW = energyModel.data_gridCapacityDemand_kW;
area.v_dataElectricityFeedInCapacityLiveWeek_kW = energyModel.data_gridCapacitySupply_kW;


//Datasets for live summerWeek chart
//Demand
area.dsm_summerWeekConsumptionDataSets_kW = energyModel.dsm_summerWeekDemandDataSets_kW;
area.v_dataElectricityBaseloadConsumptionSummerWeek_kW = energyModel.data_summerWeekBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionSummerWeek_kW = energyModel.data_summerWeekHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionSummerWeek_kW = energyModel.data_summerWeekElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionSummerWeek_kW = energyModel.data_summerWeekBatteriesDemand_kW;
area.v_dataElectricityForHydrogenConsumptionSummerWeek_kW = energyModel.data_summerWeekElectrolyserDemand_kW;
area.v_dataElectricityForCookingConsumptionSummerWeek_kW = energyModel.data_summerWeekCookingElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionSummerWeek_kW = energyModel.data_summerWeekDistrictHeatingDemand_kW;

//Supply
area.dsm_summerWeekProductionDataSets_kW = energyModel.dsm_summerWeekSupplyDataSets_kW;
area.v_dataElectricityWindProductionSummerWeek_kW = energyModel.data_summerWeekWindGeneration_kW;
area.v_dataElectricityPVProductionSummerWeek_kW = energyModel.data_summerWeekPVGeneration_kW;
area.v_dataElectricityStorageProductionSummerWeek_kW = energyModel.data_summerWeekBatteriesSupply_kW;
area.v_dataElectricityV2GProductionSummerWeek_kW = energyModel.data_summerWeekV2GSupply_kW;
area.v_dataElectricityCHPProductionSummerWeek_kW = energyModel.data_summerWeekCHPElectricityProduction_kW;

//Net load
area.v_dataNetLoadSummerWeek_kW = energyModel.data_summerWeekNetLoad_kW;
area.v_dataElectricityDeliveryCapacitySummerWeek_kW.add(energyModel.p_startHourSummerWeek, energyModel.f_getGridNodesTopLevel().get(0).p_capacity_kW);
area.v_dataElectricityDeliveryCapacitySummerWeek_kW.add(energyModel.p_startHourSummerWeek + 7*24, energyModel.f_getGridNodesTopLevel().get(0).p_capacity_kW);
area.v_dataElectricityFeedInCapacitySummerWeek_kW.add(energyModel.p_startHourSummerWeek, -energyModel.f_getGridNodesTopLevel().get(0).p_capacity_kW);
area.v_dataElectricityFeedInCapacitySummerWeek_kW.add(energyModel.p_startHourSummerWeek + 7*24, -energyModel.f_getGridNodesTopLevel().get(0).p_capacity_kW);

//SOC (summerweek)
area.v_dataBatterySOCSummerWeek_.reset();
for (int i = 0; i < energyModel.data_summerWeekBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = energyModel.data_summerWeekBatteryStoredEnergy_MWh.getX(i);
    double y = energyModel.data_summerWeekBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCSummerWeek_.add(x, SOC);
}

//Datasets for live winterWeek chart
//Demand
area.dsm_winterWeekConsumptionDataSets_kW = energyModel.dsm_winterWeekDemandDataSets_kW;
area.v_dataElectricityBaseloadConsumptionWinterWeek_kW = energyModel.data_winterWeekBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionWinterWeek_kW = energyModel.data_winterWeekHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionWinterWeek_kW = energyModel.data_winterWeekElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionWinterWeek_kW = energyModel.data_winterWeekBatteriesDemand_kW;
area.v_dataElectricityForCookingConsumptionWinterWeek_kW = energyModel.data_winterWeekCookingElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionWinterWeek_kW = energyModel.data_winterWeekDistrictHeatingDemand_kW;

//Supply
area.dsm_winterWeekProductionDataSets_kW = energyModel.dsm_winterWeekSupplyDataSets_kW;
area.v_dataElectricityWindProductionWinterWeek_kW = energyModel.data_winterWeekWindGeneration_kW;
area.v_dataElectricityPVProductionWinterWeek_kW = energyModel.data_winterWeekPVGeneration_kW;
area.v_dataElectricityStorageProductionWinterWeek_kW = energyModel.data_winterWeekBatteriesSupply_kW;
area.v_dataElectricityV2GProductionWinterWeek_kW = energyModel.data_winterWeekV2GSupply_kW;
area.v_dataElectricityCHPProductionWinterWeek_kW = energyModel.data_winterWeekCHPElectricityProduction_kW;

//Netload
area.v_dataNetLoadWinterWeek_kW = energyModel.data_winterWeekNetLoad_kW;
area.v_dataElectricityDeliveryCapacityWinterWeek_kW.add(energyModel.p_startHourWinterWeek, energyModel.f_getGridNodesTopLevel().get(0).p_capacity_kW);
area.v_dataElectricityDeliveryCapacityWinterWeek_kW.add(energyModel.p_startHourWinterWeek + 7*24, energyModel.f_getGridNodesTopLevel().get(0).p_capacity_kW);
area.v_dataElectricityFeedInCapacityWinterWeek_kW.add(energyModel.p_startHourWinterWeek, -energyModel.f_getGridNodesTopLevel().get(0).p_capacity_kW);
area.v_dataElectricityFeedInCapacityWinterWeek_kW.add(energyModel.p_startHourWinterWeek + 7*24, -energyModel.f_getGridNodesTopLevel().get(0).p_capacity_kW);

//SOC (Winterweek)
area.v_dataBatterySOCWinterWeek_.reset();
for (int i = 0; i < energyModel.data_winterWeekBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = energyModel.data_winterWeekBatteryStoredEnergy_MWh.getX(i);
    double y = energyModel.data_winterWeekBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCWinterWeek_.add(x, SOC);
}

//Datasets for yearly profiles chart
//Demand
area.dsm_dailyAverageConsumptionDataSets_kW = energyModel.dsm_dailyAverageDemandDataSets_kW;
area.dsm_dailyAverageProductionDataSets_kW = energyModel.dsm_dailyAverageSupplyDataSets_kW;
area.v_dataElectricityBaseloadConsumptionYear_kW = energyModel.data_annualBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionYear_kW = energyModel.data_annualHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionYear_kW = energyModel.data_annualElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionYear_kW = energyModel.data_annualBatteriesDemand_kW;
area.v_dataElectricityForHydrogenConsumptionYear_kW = energyModel.data_annualElectrolyserDemand_kW;
area.v_dataElectricityForCookingConsumptionYear_kW = energyModel.data_annualCookingElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionYear_kW = energyModel.data_annualDistrictHeatingDemand_kW;

//Supply
area.v_dataElectricityWindProductionYear_kW = energyModel.data_annualWindGeneration_kW;
area.v_dataElectricityPVProductionYear_kW = energyModel.data_annualPVGeneration_kW;
area.v_dataElectricityStorageProductionYear_kW = energyModel.data_annualBatteriesSupply_kW;
area.v_dataElectricityV2GProductionYear_kW = energyModel.data_annualV2GSupply_kW;
area.v_dataElectricityCHPProductionYear_kW = energyModel.data_annualCHPElectricityProduction_kW;

//SOC (Year)
area.v_dataBatterySOCYear_.reset();
for (int i = 0; i < energyModel.data_annualBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = energyModel.data_annualBatteryStoredEnergy_MWh.getX(i);
    double y = energyModel.data_annualBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCYear_.add(x, SOC);
}

// Data for gespreksleidraad1
area.v_dataNetLoadYear_kW = energyModel.am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getTimeSeries();

//Datasets for netloaddurationcurves
area.v_dataNetbelastingDuurkrommeYear_kW = energyModel.data_netbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeYearVorige_kW = energyModel.data_netbelastingDuurkrommeVorige_kW;

area.v_dataNetbelastingDuurkrommeSummer_kW = energyModel.data_summerWeekNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWinter_kW = energyModel.data_winterWeekNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeDaytime_kW = energyModel.data_daytimeNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeNighttime_kW = energyModel.data_nighttimeNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWeekend_kW = energyModel.data_weekendNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWeekday_kW = energyModel.data_weekdayNetbelastingDuurkromme_kW;

//Peak individuals
area.v_individualPeakDelivery_kW += sum(energyModel.f_getGridConnections(), gc -> gc.am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMax());
area.v_individualPeakFeedin_kW += -1*sum(energyModel.f_getGridConnections(), gc -> gc.am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMin());

// Can't use pointer for (immutable) primitives in Java, so need to manually update results after a year-sim!!
area.v_gridCapacityDelivery_kW = energyModel.f_getGridNodesTopLevel().get(0).p_capacity_kW;
area.v_gridCapacityFeedIn_kW = energyModel.f_getGridNodesTopLevel().get(0).p_capacity_kW;
area.b_isRealDeliveryCapacityAvailable = energyModel.f_getGridNodesTopLevel().get(0).p_realCapacityAvailable;
area.b_isRealFeedinCapacityAvailable = energyModel.f_getGridNodesTopLevel().get(0).p_realCapacityAvailable;

// KPIs for 'samenvatting' 
//area.v_collectiveSelfConsumptionElectricity_fr = energyModel.v_modelSelfConsumption_fr;
area.v_individualSelfSufficiencyElectricity_fr = energyModel.v_individualSelfSufficiency_fr;
area.v_individualSelfconsumptionElectricity_fr = energyModel.v_individualSelfConsumption_fr;
//area.v_collectiveSelfSufficiencyElectricity_fr = energyModel.v_modelSelfSufficiency_fr;
area.v_totalTimeOverloaded_h = energyModel.v_gridOverloadDuration_h;

//Yearly
area.fm_totalImports_MWh = energyModel.fm_totalImports_MWh;
area.fm_totalExports_MWh = energyModel.fm_totalExports_MWh;

area.v_totalEnergyImport_MWh = energyModel.v_totalEnergyImport_MWh;
area.v_totalEnergyExport_MWh = energyModel.v_totalEnergyExport_MWh;

area.v_totalEnergyProduced_MWh = energyModel.v_totalEnergyProduced_MWh;
area.v_totalEnergyConsumed_MWh = energyModel.v_totalEnergyConsumed_MWh;
area.v_totalEnergySelfConsumed_MWh = energyModel.v_totalEnergySelfConsumed_MWh;

// Where is the Produced Electricity ???
area.v_totalElectricityConsumed_MWh = energyModel.v_totalElectricityConsumed_MWh;
area.v_totalElectricitySelfConsumed_MWh = energyModel.v_totalElectricitySelfConsumed_MWh;

// Summer/winter
area.fm_summerWeekImports_MWh = energyModel.fm_summerWeekImports_MWh;
area.fm_summerWeekExports_MWh = energyModel.fm_summerWeekImports_MWh;
area.fm_winterWeekImports_MWh = energyModel.fm_summerWeekImports_MWh;
area.fm_winterWeekExports_MWh = energyModel.fm_summerWeekImports_MWh;

area.v_summerWeekEnergyImport_MWh = energyModel.v_summerWeekEnergyImport_MWh;
area.v_summerWeekEnergyExport_MWh = energyModel.v_summerWeekEnergyExport_MWh;

area.v_summerWeekEnergyProduced_MWh = energyModel.v_summerWeekEnergyProduced_MWh;
area.v_summerWeekEnergyConsumed_MWh = energyModel.v_summerWeekEnergyConsumed_MWh;
area.v_summerWeekEnergySelfConsumed_MWh = energyModel.v_summerWeekEnergySelfConsumed_MWh;

area.v_summerWeekElectricityProduced_MWh = energyModel.v_summerWeekElectricityProduced_MWh;
area.v_summerWeekElectricityConsumed_MWh = energyModel.v_summerWeekElectricityConsumed_MWh;
area.v_summerWeekElectricitySelfConsumed_MWh = energyModel.v_summerWeekElectricitySelfConsumed_MWh;

area.v_winterWeekEnergyImport_MWh = energyModel.v_winterWeekEnergyImport_MWh;
area.v_winterWeekEnergyExport_MWh = energyModel.v_winterWeekEnergyExport_MWh;

area.v_winterWeekEnergyProduced_MWh = energyModel.v_winterWeekEnergyProduced_MWh;
area.v_winterWeekEnergyConsumed_MWh = energyModel.v_winterWeekEnergyConsumed_MWh;
area.v_winterWeekEnergySelfConsumed_MWh = energyModel.v_winterWeekEnergySelfConsumed_MWh;

area.v_winterWeekElectricityProduced_MWh = energyModel.v_winterWeekElectricityProduced_MWh;
area.v_winterWeekElectricityConsumed_MWh = energyModel.v_winterWeekElectricityConsumed_MWh;
area.v_winterWeekElectricitySelfConsumed_MWh = energyModel.v_winterWeekElectricitySelfConsumed_MWh;

// Day/night
area.fm_daytimeImports_MWh = energyModel.fm_daytimeImports_MWh;
area.fm_daytimeExports_MWh = energyModel.fm_daytimeExports_MWh;
area.fm_nighttimeImports_MWh = energyModel.fm_nighttimeImports_MWh;
area.fm_nighttimeExports_MWh = energyModel.fm_nighttimeExports_MWh;

area.v_daytimeEnergyImport_MWh = energyModel.v_daytimeEnergyImport_MWh;
area.v_daytimeEnergyExport_MWh = energyModel.v_daytimeEnergyExport_MWh;

area.v_daytimeEnergyProduced_MWh = energyModel.v_daytimeEnergyProduced_MWh;
area.v_daytimeEnergyConsumed_MWh = energyModel.v_daytimeEnergyConsumed_MWh;
area.v_daytimeEnergySelfConsumed_MWh = energyModel.v_daytimeEnergySelfConsumed_MWh;

area.v_daytimeElectricityProduced_MWh = energyModel.v_daytimeElectricityProduced_MWh;
area.v_daytimeElectricityConsumed_MWh = energyModel.v_daytimeElectricityConsumed_MWh;
area.v_daytimeElectricitySelfConsumed_MWh = energyModel.v_daytimeElectricitySelfConsumed_MWh;

area.v_nighttimeEnergyImport_MWh = energyModel.v_nighttimeEnergyImport_MWh;
area.v_nighttimeEnergyExport_MWh = energyModel.v_nighttimeEnergyExport_MWh;

area.v_nighttimeEnergyProduced_MWh = energyModel.v_nighttimeEnergyProduced_MWh;
area.v_nighttimeEnergyConsumed_MWh = energyModel.v_nighttimeEnergyConsumed_MWh;
area.v_nighttimeEnergySelfConsumed_MWh = energyModel.v_nighttimeEnergySelfConsumed_MWh;

area.v_nighttimeElectricityProduced_MWh = energyModel.v_nighttimeElectricityProduced_MWh;
area.v_nighttimeElectricityConsumed_MWh = energyModel.v_nighttimeElectricityConsumed_MWh;
area.v_nighttimeElectricitySelfConsumed_MWh = energyModel.v_nighttimeElectricitySelfConsumed_MWh;

// Week/weekend
area.fm_weekdayImports_MWh = energyModel.fm_weekdayImports_MWh;
area.fm_weekdayExports_MWh = energyModel.fm_weekdayExports_MWh;
area.fm_weekendImports_MWh = energyModel.fm_weekendImports_MWh;
area.fm_weekendExports_MWh = energyModel.fm_weekendExports_MWh;

area.v_weekdayEnergyImport_MWh = energyModel.v_weekdayEnergyImport_MWh;
area.v_weekdayEnergyExport_MWh = energyModel.v_weekdayEnergyExport_MWh;

area.v_weekdayEnergyProduced_MWh = energyModel.v_weekdayEnergyProduced_MWh;
area.v_weekdayEnergyConsumed_MWh = energyModel.v_weekdayEnergyConsumed_MWh;
area.v_weekdayEnergySelfConsumed_MWh = energyModel.v_weekdayEnergySelfConsumed_MWh;

area.v_weekdayElectricityProduced_MWh = energyModel.v_weekdayElectricityProduced_MWh;
area.v_weekdayElectricityConsumed_MWh = energyModel.v_weekdayElectricityConsumed_MWh;
area.v_weekdayElectricitySelfConsumed_MWh = energyModel.v_weekdayElectricitySelfConsumed_MWh;

area.v_weekendEnergyImport_MWh = energyModel.v_weekendEnergyImport_MWh;
area.v_weekendEnergyExport_MWh = energyModel.v_weekendEnergyExport_MWh;

area.v_weekendEnergyProduced_MWh = energyModel.v_weekendEnergyProduced_MWh;
area.v_weekendEnergyConsumed_MWh = energyModel.v_weekendEnergyConsumed_MWh;
area.v_weekendEnergySelfConsumed_MWh = energyModel.v_weekendEnergySelfConsumed_MWh;

area.v_weekendElectricityProduced_MWh = energyModel.v_weekendElectricityProduced_MWh;
area.v_weekendElectricityConsumed_MWh = energyModel.v_weekendElectricityConsumed_MWh;
area.v_weekendElectricitySelfConsumed_MWh = energyModel.v_weekendElectricitySelfConsumed_MWh;


////Gespreksleidraad Additions
if(v_selectedRadioButton == OL_RadioButtonSetup.DEFAULT_AND_GESPREKSLEIDRAAD){
	//Final energy consumption dataset year
	area.data_dailyAverageFinalEnergyConsumption_kW = energyModel.data_totalFinalEnergyConsumption_kW;
	
	//Subdivision of heating assets
	area.v_totalElectricityConsumptionHeatpumps_MWh = energyModel.v_totalElectricityConsumptionHeatpumps_MWh;
	area.v_totalEnergyConsumptionForDistrictHeating_MWh = energyModel.v_totalEnergyConsumptionForDistrictHeating_MWh;
	
	//Subdivision of Production
	area.v_totalPVGeneration_MWh = energyModel.v_totalPVGeneration_MWh;
	area.v_totalWindGeneration_MWh = energyModel.v_totalWindGeneration_MWh;
	
	
	//Todo: make this generic!!!!
	
	//Current production capacity
	double totalInstalledCapacity_kW_companies = 0;
	double totalInstalledCapacity_kW_households = 0;
	for(GCNeighborhood NBH : energyModel.Neighborhoods){
		//Find the correct production asset for rooftop solar companies
		J_EAProduction PV_EA_companies = findFirst(NBH.c_productionAssets, EA -> EA.getName().equals("large scale rooftop PV "));
		//Get the current capacity of the asset and add to total
		totalInstalledCapacity_kW_companies += PV_EA_companies.getCapacityElectric_kW();
		
		//Find the correct production asset for rooftop solar households
		J_EAProduction PV_EA_households = findFirst(NBH.c_productionAssets, EA -> EA.getName().equals("houshold PV "));
		//Get the current capacity of the asset and add to total
		totalInstalledCapacity_kW_households += PV_EA_households.getCapacityElectric_kW();
		
	}
	
	//RooftopPVInstalled
	area.v_rooftopPVInstalled_kW = totalInstalledCapacity_kW_households + totalInstalledCapacity_kW_companies;
	
	//WindInstalled
	area.v_windInstalled_kW = 0;
	
	//Potential
	area.v_rooftopPVPotential_kW = 0;//p_rooftopPVPotential_kW;
	area.v_windPotential_kW = 0;
	
	//Curtailment
	area.v_totalEnergyCurtailed_MWh = energyModel.v_totalEnergyCurtailed_MWh;
	
	//Solar and wind: reset first, then add each seperate asset
	area.v_totalPVEnergyCurtailed_MWh = 0;
	area.v_totalWindEnergyCurtailed_MWh = 0;
	for(GridConnection GC :energyModel.f_getGridConnections()){
		
		for(J_EAProduction productionAsset : GC.c_productionAssets){
			if(productionAsset.energyAssetType == OL_EnergyAssetType.PHOTOVOLTAIC){
				area.v_totalPVEnergyCurtailed_MWh += productionAsset.getEnergyCurtailed_kWh()/1000;
			}
			else if(productionAsset.energyAssetType == OL_EnergyAssetType.WINDMILL){
				area.v_totalWindEnergyCurtailed_MWh += productionAsset.getEnergyCurtailed_kWh()/1000; 
			}
		}
	}
}
/*ALCODEEND*/}

double f_updateUIresultsEnergyCoop(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1739364390439*/
//Set active energyCarriers
area.v_activeProductionEnergyCarriers = energyModel.v_activeProductionEnergyCarriers;
area.v_activeConsumptionEnergyCarriers = energyModel.v_activeConsumptionEnergyCarriers;

// Can't use pointer for (immutable) primitives in Java, so need to manually update results after a year-sim!!
area.v_gridCapacityDelivery_kW = EC.p_connectionCapacity_kW;
area.v_gridCapacityFeedIn_kW = EC.p_connectionCapacity_kW;

// KPIs for 'samenvatting' 
//area.v_collectiveSelfConsumptionElectricity_fr = EC.v_totalEnergyProduced_MWh > 0 ? EC.v_totalEnergySelfConsumed_MWh/EC.v_totalEnergyProduced_MWh : 0.0;
//area.v_individualSelfSufficiency_fr = EC.v_individualSelfSufficiency_fr;
//area.v_collectiveSelfSufficiencyElectricity_fr = EC.v_totalEnergyProduced_MWh > 0 ? EC.v_totalEnergySelfConsumed_MWh/EC.v_totalEnergyConsumed_MWh: 0.0;
//area.v_totalTimeOverloadedTransformers_h = EC.v_netOverloadDuration_h;

//Datasets for live demand chart
//Demand
//AreaCollection companyArea = uI_Results.v_company;
area.dsm_liveConsumption_kW = EC.dsm_liveDemand_kW;
//area.v_dataPetroleumProductsDemandLiveWeek_kW = EC.data_dieselDemand_kW;
//area.v_dataNaturalGasDemandLiveWeek_kW = EC.data_naturalGasDemand_kW;
area.v_dataElectricityBaseloadConsumptionLiveWeek_kW = EC.data_baseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionLiveWeek_kW = EC.data_heatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionLiveWeek_kW = EC.data_electricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionLiveWeek_kW = EC.data_batteryCharging_kW;
area.v_dataElectricityForCookingConsumptionLiveWeek_kW = EC.data_cookingElectricityDemand_kW;

//Supply
area.dsm_liveProduction_kW = EC.dsm_liveSupply_kW;
area.v_dataWindElectricityProductionLiveWeek_kW = EC.data_windGeneration_kW;
area.v_dataPVElectricityProductionLiveWeek_kW = EC.data_PVGeneration_kW;
area.v_dataStorageElectricityProductionLiveWeek_kW = EC.data_batteryDischarging_kW;
area.v_dataV2GElectricityProductionLiveWeek_kW = EC.data_V2GSupply_kW;

//Datasets for live summerWeek chart
//Demand
//area.v_dataPetroleumProductsDemandSummerWeek_kW = EC.data_summerWeekDieselDemand_kW;
//area.v_dataNaturalGasDemandSummerWeek_kW = EC.data_summerWeekNaturalGasDemand_kW;
/*area.v_dataElectricityBaseloadDemandSummerWeek_kW = EC.data_summerWeekBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatDemandSummerWeek_kW = EC.data_summerWeekHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportDemandSummerWeek_kW = EC.data_summerWeekElectricTrucksDemand_kW;
area.v_dataElectricityForStorageDemandSummerWeek_kW = EC.data_summerWeekNeighborhoodBatteryDemand_kW;
//Supply
area.v_dataWindElectricitySupplySummerWeek_kW = EC.data_summerWeekWindGeneration_kW;
area.v_dataPVElectricitySupplySummerWeek_kW = EC.data_summerWeekPVGeneration_kW;
area.v_dataStorageElectricitySupplySummerWeek_kW = EC.data_summerWeekNeighborhoodBatterySupply_kW;
area.v_dataV2GElectricitySupplySummerWeek_kW = EC.data_summerWeekV2GSupply_kW;

//Datasets for live winterWeek chart
//Demand
//area.v_dataPetroleumProductsDemandWinterWeek_kW = EC.data_winterWeekDieselDemand_kW;
//area.v_dataNaturalGasDemandWinterWeek_kW = EC.data_winterWeekNaturalGasDemand_kW;
area.v_dataElectricityBaseloadDemandWinterWeek_kW = EC.data_winterWeekBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatDemandWinterWeek_kW = EC.data_winterWeekHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportDemandWinterWeek_kW = EC.data_winterWeekElectricTrucksDemand_kW;
area.v_dataElectricityForStorageDemandWinterWeek_kW = EC.data_winterWeekNeighborhoodBatteryDemand_kW;
//Supply
area.v_dataWindElectricitySupplyWinterWeek_kW = EC.data_winterWeekWindGeneration_kW;
area.v_dataPVElectricitySupplyWinterWeek_kW = EC.data_winterWeekPVGeneration_kW;
area.v_dataStorageElectricitySupplyWinterWeek_kW = EC.data_winterWeekNeighborhoodBatterySupply_kW;
area.v_dataV2GElectricitySupplyWinterWeek_kW = EC.data_winterWeekV2GSupply_kW;


//Datasets for yearly profiles chart
//Demand
//area.v_dataPetroleumProductsDemandYear_MWh = EC.data_dieselDemand_MWh;
//area.v_dataNaturalGasDemandYear_MWh = EC.data_naturalGasDemand_MWh;
area.v_dataElectricityBaseloadDemandYear_MWh = EC.data_annualBaseloadElectricityDemand_MWh;
area.v_dataElectricityForHeatDemandYear_MWh = EC.data_annualHeatPumpElectricityDemand_MWh;
area.v_dataElectricityForTransportDemandYear_MWh = EC.data_annualElectricTrucksDemand_MWh;
area.v_dataElectricityForStorageDemandYear_MWh = EC.data_annualNeighborhoodBatteryDemand_MWh;

//Supply
area.v_dataElectricityWindSupplyYear_MWh = EC.data_annualWindGeneration_MWh;
area.v_dataElectricityPVSupplyYear_MWh = EC.data_annualPVGeneration_MWh;
area.v_dataElectricityStorageSupplyYear_MWh = EC.data_annualNeighborhoodBatterySupply_MWh;
area.v_dataElectricityV2GSupplyYear_MWh = EC.data_annualV2GSupply_MWh;

// Data for gespreksleidraad1
area.v_dataElectricityDemandYear_MWh = EC.data_annualElectricityDemand_MWh;
area.v_dataElectricitySupplyYear_MWh = EC.data_annualElectricitySupply_MWh;*/
area.v_dataNetLoadYear_kW = EC.am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getTimeSeries();

//Datasets for netloaddurationcurves
//EC.f_duurkrommes();
area.v_dataNetbelastingDuurkrommeYear_kW = EC.f_getDuurkromme();
/*area.v_dataNetbelastingDuurkrommeSummer_kW = EC.data_summerWeekBusinessParkNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWinter_kW = EC.data_winterWeekBusinessParkNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeDaytime_kW = EC.data_daytimeNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeNighttime_kW = EC.data_nighttimeNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWeekend_kW = EC.data_weekendNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWeekday_kW = EC.data_weekdayNetbelastingDuurkromme_kW;*/



/////////////////////////////////////////


//Yearly
for (OL_EnergyCarriers energyCarrier : energyModel.v_activeEnergyCarriers) {
	area.fm_totalImports_MWh.put( energyCarrier, EC.fm_totalImports_MWh.get(energyCarrier));
	area.fm_totalExports_MWh.put( energyCarrier, EC.fm_totalExports_MWh.get(energyCarrier));
}
area.v_totalEnergyImport_MWh = EC.v_totalEnergyImport_MWh;
area.v_totalEnergyExport_MWh = EC.v_totalEnergyExport_MWh;

area.v_totalEnergyProduced_MWh = EC.v_totalEnergyProduced_MWh;
area.v_totalEnergyConsumed_MWh = EC.v_totalEnergyConsumed_MWh;
area.v_totalEnergySelfConsumed_MWh = EC.v_totalEnergySelfConsumed_MWh;

area.v_totalElectricityProduced_MWh = EC.v_totalElectricityProduced_MWh;
area.v_totalElectricityConsumed_MWh = EC.v_totalElectricityConsumed_MWh;
area.v_totalElectricitySelfConsumed_MWh = EC.v_totalElectricitySelfConsumed_MWh;


// Summer/winter
for (OL_EnergyCarriers energyCarrier : energyModel.v_activeEnergyCarriers) {
	area.fm_summerWeekImports_MWh.put( energyCarrier, EC.fm_summerWeekImports_MWh.get(energyCarrier));
	area.fm_summerWeekExports_MWh.put( energyCarrier, EC.fm_summerWeekExports_MWh.get(energyCarrier));
	area.fm_winterWeekImports_MWh.put( energyCarrier, EC.fm_winterWeekImports_MWh.get(energyCarrier));
	area.fm_winterWeekExports_MWh.put( energyCarrier, EC.fm_winterWeekExports_MWh.get(energyCarrier));
}

area.v_summerWeekEnergyImport_MWh = EC.v_summerWeekEnergyImport_MWh;
area.v_summerWeekEnergyExport_MWh = EC.v_summerWeekEnergyExport_MWh;

area.v_summerWeekEnergyProduced_MWh = EC.v_summerWeekEnergyProduced_MWh;
area.v_summerWeekEnergyConsumed_MWh = EC.v_summerWeekEnergyConsumed_MWh;
area.v_summerWeekEnergySelfConsumed_MWh = EC.v_summerWeekEnergySelfConsumed_MWh;

area.v_summerWeekElectricityProduced_MWh = EC.v_summerWeekElectricityProduced_MWh;
area.v_summerWeekElectricityConsumed_MWh = EC.v_summerWeekElectricityConsumed_MWh;
area.v_summerWeekElectricitySelfConsumed_MWh = EC.v_summerWeekElectricitySelfConsumed_MWh;

area.v_winterWeekEnergyImport_MWh = EC.v_winterWeekEnergyImport_MWh;
area.v_winterWeekEnergyExport_MWh = EC.v_winterWeekEnergyExport_MWh;

area.v_winterWeekEnergyProduced_MWh = EC.v_winterWeekEnergyProduced_MWh;
area.v_winterWeekEnergyConsumed_MWh = EC.v_winterWeekEnergyConsumed_MWh;
area.v_winterWeekEnergySelfConsumed_MWh = EC.v_winterWeekEnergySelfConsumed_MWh;

area.v_winterWeekElectricityProduced_MWh = EC.v_winterWeekElectricityProduced_MWh;
area.v_winterWeekElectricityConsumed_MWh = EC.v_winterWeekElectricityConsumed_MWh;
area.v_winterWeekElectricitySelfConsumed_MWh = EC.v_winterWeekElectricitySelfConsumed_MWh;

// Day/night
for (OL_EnergyCarriers energyCarrier : energyModel.v_activeEnergyCarriers) {
	area.fm_daytimeImports_MWh.put( energyCarrier, EC.fm_daytimeImports_MWh.get(energyCarrier));
	area.fm_daytimeExports_MWh.put( energyCarrier, EC.fm_daytimeExports_MWh.get(energyCarrier));
	area.fm_nighttimeImports_MWh.put( energyCarrier, EC.fm_nighttimeImports_MWh.get(energyCarrier));
	area.fm_nighttimeExports_MWh.put( energyCarrier, EC.fm_nighttimeExports_MWh.get(energyCarrier));
}

area.v_daytimeEnergyImport_MWh = EC.v_daytimeEnergyImport_MWh;
area.v_daytimeEnergyExport_MWh = EC.v_daytimeEnergyExport_MWh;

area.v_daytimeEnergyProduced_MWh = EC.v_daytimeEnergyProduced_MWh;
area.v_daytimeEnergyConsumed_MWh = EC.v_daytimeEnergyConsumed_MWh;
area.v_daytimeEnergySelfConsumed_MWh = EC.v_daytimeEnergySelfConsumed_MWh;

area.v_daytimeElectricityProduced_MWh = EC.v_daytimeElectricityProduced_MWh;
area.v_daytimeElectricityConsumed_MWh = EC.v_daytimeElectricityConsumed_MWh;
area.v_daytimeElectricitySelfConsumed_MWh = EC.v_daytimeElectricitySelfConsumed_MWh;


area.v_nighttimeEnergyImport_MWh = EC.v_nighttimeEnergyImport_MWh;
area.v_nighttimeEnergyExport_MWh = EC.v_nighttimeEnergyExport_MWh;

area.v_nighttimeEnergyProduced_MWh = EC.v_nighttimeEnergyProduced_MWh;
area.v_nighttimeEnergyConsumed_MWh = EC.v_nighttimeEnergyConsumed_MWh;
area.v_nighttimeEnergySelfConsumed_MWh = EC.v_nighttimeEnergySelfConsumed_MWh;

area.v_nighttimeElectricityProduced_MWh = EC.v_nighttimeElectricityProduced_MWh;
area.v_nighttimeElectricityConsumed_MWh = EC.v_nighttimeElectricityConsumed_MWh;
area.v_nighttimeElectricitySelfConsumed_MWh = EC.v_nighttimeElectricitySelfConsumed_MWh;

// Week/weekend
for (OL_EnergyCarriers energyCarrier : energyModel.v_activeEnergyCarriers) {
	area.fm_weekdayImports_MWh.put( energyCarrier, EC.fm_weekdayImports_MWh.get(energyCarrier));
	area.fm_weekdayExports_MWh.put( energyCarrier, EC.fm_weekdayExports_MWh.get(energyCarrier));
	area.fm_weekendImports_MWh.put( energyCarrier, EC.fm_weekendImports_MWh.get(energyCarrier));
	area.fm_weekendExports_MWh.put( energyCarrier, EC.fm_weekendExports_MWh.get(energyCarrier));
}

area.v_weekdayEnergyImport_MWh = EC.v_weekdayEnergyImport_MWh;
area.v_weekdayEnergyExport_MWh = EC.v_weekdayEnergyExport_MWh;

area.v_weekdayEnergyProduced_MWh = EC.v_weekdayEnergyProduced_MWh;
area.v_weekdayEnergyConsumed_MWh = EC.v_weekdayEnergyConsumed_MWh;
area.v_weekdayEnergySelfConsumed_MWh = EC.v_weekdayEnergySelfConsumed_MWh;

area.v_weekdayElectricityProduced_MWh = EC.v_weekdayElectricityProduced_MWh;
area.v_weekdayElectricityConsumed_MWh = EC.v_weekdayElectricityConsumed_MWh;
area.v_weekdayElectricitySelfConsumed_MWh = EC.v_weekdayElectricitySelfConsumed_MWh;


area.v_weekendEnergyImport_MWh = EC.v_weekendEnergyImport_MWh;
area.v_weekendEnergyExport_MWh = EC.v_weekendEnergyExport_MWh;

area.v_weekendEnergyProduced_MWh = EC.v_weekendEnergyProduced_MWh;
area.v_weekendEnergyConsumed_MWh = EC.v_weekendEnergyConsumed_MWh;
area.v_weekendEnergySelfConsumed_MWh = EC.v_weekendEnergySelfConsumed_MWh;

area.v_weekendElectricityProduced_MWh = EC.v_weekendElectricityProduced_MWh;
area.v_weekendElectricityConsumed_MWh = EC.v_weekendElectricityConsumed_MWh;
area.v_weekendElectricitySelfConsumed_MWh = EC.v_weekendElectricitySelfConsumed_MWh;

/*ALCODEEND*/}

double f_updateUIresultsGridNode(AreaCollection area,GridNode GN)
{/*ALCODESTART::1739364390441*/
// Can't use pointer for (immutable) primitives in Java, so need to manually update results after a year-sim!!
area.v_gridCapacityDelivery_kW = GN.p_capacity_kW;
area.v_gridCapacityFeedIn_kW = GN.p_capacity_kW;
area.b_isRealDeliveryCapacityAvailable = GN.p_realCapacityAvailable;
area.b_isRealFeedinCapacityAvailable = GN.p_realCapacityAvailable;


// Datasets for profile plot
area.v_dataElectricityBaseloadConsumptionLiveWeek_kW = GN.data_liveLoad_kW;
area.v_dataElectricityBaseloadConsumptionYear_kW = GN.data_totalLoad_kW;
area.v_dataElectricityBaseloadConsumptionSummerWeek_kW = GN.data_summerWeekLoad_kW;
area.v_dataElectricityBaseloadConsumptionWinterWeek_kW = GN.data_winterWeekLoad_kW;


area.v_dataElectricityDeliveryCapacityLiveWeek_kW = GN.data_liveCapacityDemand_kW;
area.v_dataElectricityFeedInCapacityLiveWeek_kW = GN.data_liveCapacitySupply_kW;
area.data_gridCapacityDeliveryYear_kW.add(0, GN.p_capacity_kW);
area.data_gridCapacityDeliveryYear_kW.add(8760, GN.p_capacity_kW);
area.data_gridCapacityFeedInYear_kW.add(0, -GN.p_capacity_kW);
area.data_gridCapacityFeedInYear_kW.add(8760, -GN.p_capacity_kW);
area.data_gridCapacityDeliverySummerWeek_kW.add(energyModel.p_startHourSummerWeek, GN.p_capacity_kW);
area.data_gridCapacityDeliverySummerWeek_kW.add(energyModel.p_startHourSummerWeek+24*7, GN.p_capacity_kW);
area.data_gridCapacityFeedInSummerWeek_kW.add(energyModel.p_startHourSummerWeek, -GN.p_capacity_kW);
area.data_gridCapacityFeedInSummerWeek_kW.add(energyModel.p_startHourSummerWeek+24*7, -GN.p_capacity_kW);
area.data_gridCapacityDeliveryWinterWeek_kW.add(energyModel.p_startHourWinterWeek, GN.p_capacity_kW);
area.data_gridCapacityDeliveryWinterWeek_kW.add(energyModel.p_startHourWinterWeek+24*7, GN.p_capacity_kW);
area.data_gridCapacityFeedInWinterWeek_kW.add(energyModel.p_startHourWinterWeek, -GN.p_capacity_kW);
area.data_gridCapacityFeedInWinterWeek_kW.add(energyModel.p_startHourWinterWeek+24*7, -GN.p_capacity_kW);

// Data for Opwek & Verbruik
// Year
area.fm_totalImports_MWh.put(GN.p_energyCarrier, GN.v_totalImport_MWh);
area.fm_totalExports_MWh.put(GN.p_energyCarrier, GN.v_totalExport_MWh);
area.v_annualExcessImport_MWh = GN.v_annualExcessImport_MWh;
area.v_annualExcessExport_MWh = GN.v_annualExcessExport_MWh;

// Summer / Winter
area.fm_summerWeekImports_MWh.put(GN.p_energyCarrier, GN.v_summerWeekImport_MWh);
area.fm_summerWeekExports_MWh.put(GN.p_energyCarrier, GN.v_summerWeekExport_MWh);
area.v_summerWeekExcessImport_MWh = GN.v_summerWeekExcessImport_MWh;
area.v_summerWeekExcessExport_MWh = GN.v_summerWeekExcessExport_MWh;

area.fm_winterWeekImports_MWh.put(GN.p_energyCarrier, GN.v_winterWeekImport_MWh);
area.fm_winterWeekExports_MWh.put(GN.p_energyCarrier, GN.v_winterWeekExport_MWh);
area.v_winterWeekExcessImport_MWh = GN.v_winterWeekExcessImport_MWh;
area.v_winterWeekExcessExport_MWh = GN.v_winterWeekExcessExport_MWh;

// Day / Night
area.fm_daytimeImports_MWh.put(GN.p_energyCarrier, GN.v_daytimeImport_MWh);
area.fm_daytimeExports_MWh.put(GN.p_energyCarrier, GN.v_daytimeExport_MWh);
area.v_daytimeExcessImport_MWh = GN.v_daytimeExcessImport_MWh;
area.v_daytimeExcessExport_MWh = GN.v_daytimeExcessExport_MWh;

area.fm_nighttimeImports_MWh.put(GN.p_energyCarrier, GN.v_nighttimeImport_MWh);
area.fm_nighttimeExports_MWh.put(GN.p_energyCarrier, GN.v_nighttimeExport_MWh);
area.v_nighttimeExcessImport_MWh = GN.v_nighttimeExcessImport_MWh;
area.v_nighttimeExcessExport_MWh = GN.v_nighttimeExcessExport_MWh;

// Weekday / Weekend
area.fm_weekdayImports_MWh.put(GN.p_energyCarrier, GN.v_weekdayImport_MWh);
area.fm_weekdayExports_MWh.put(GN.p_energyCarrier, GN.v_weekdayExport_MWh);
area.v_weekdayExcessImport_MWh = GN.v_weekdayExcessImport_MWh;
area.v_weekdayExcessExport_MWh = GN.v_weekdayExcessExport_MWh;

area.fm_weekendImports_MWh.put(GN.p_energyCarrier, GN.v_weekendImport_MWh);
area.fm_weekendExports_MWh.put(GN.p_energyCarrier, GN.v_weekendExport_MWh);
area.v_weekendExcessImport_MWh = GN.v_weekendExcessImport_MWh;
area.v_weekendExcessExport_MWh = GN.v_weekendExcessExport_MWh;

// Datasets for netloaddurationcurves
area.v_dataNetLoadYear_kW = GN.acc_annualElectricityBalance_kW.getTimeSeries();

// Year
area.v_dataNetbelastingDuurkrommeYear_kW = GN.f_getDuurkromme();;
area.v_dataNetbelastingDuurkrommeYearVorige_kW = GN.data_netbelastingDuurkrommeVorige_kW;

// Summer / Winter
area.v_dataNetbelastingDuurkrommeSummer_kW = GN.data_summerWeekNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWinter_kW = GN.data_winterWeekNetbelastingDuurkromme_kW;
// Day / Night
area.v_dataNetbelastingDuurkrommeDaytime_kW = GN.data_daytimeNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeNighttime_kW = GN.data_nighttimeNetbelastingDuurkromme_kW;

// Weekday / Weekend
area.v_dataNetbelastingDuurkrommeWeekday_kW = GN.data_weekdayNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWeekend_kW = GN.data_weekendNetbelastingDuurkromme_kW;
/*ALCODEEND*/}

double f_updateUIresultsGridConnection(AreaCollection area,ArrayList<GridConnection> selectedGridConnections)
{/*ALCODESTART::1739364390445*/
//Update to new variables
f_updateVariablesOfGCData(area, selectedGridConnections);
f_updateActiveAssetBooleansGC(area, selectedGridConnections);
f_updateLiveDataSets(area, selectedGridConnections);
f_updateYearlyGCData(area, selectedGridConnections);
f_updateWeeklyGCData(area, selectedGridConnections);
f_updateBelastingduurKromme(area, selectedGridConnections);
f_updateCollectiveSelfConsumption(area, selectedGridConnections); //--> NEEDS TO BE AFTER f_updateBelastingDuurKromme!
/*ALCODEEND*/}

double f_updateYearlyGCData(AreaCollection area,ArrayList<GridConnection> gcList)
{/*ALCODESTART::1739364390447*/
//ArrayList<GridConnection> gcList = c_gcList;
EnumSet<OL_EnergyCarriers> activeEnergyCarriers = EnumSet.noneOf(OL_EnergyCarriers.class);
for (GridConnection gc : gcList) {
	activeEnergyCarriers.addAll(gc.v_activeEnergyCarriers);
}

//reset the datasets (is this required)?
if( area.v_dataElectricityBaseloadConsumptionYear_kW != null ){
	area.v_dataElectricityBaseloadConsumptionYear_kW.reset();
	area.v_dataElectricityForHeatConsumptionYear_kW.reset();
	area.v_dataElectricityForTransportConsumptionYear_kW.reset();
	area.v_dataElectricityForStorageConsumptionYear_kW.reset();
	area.v_dataElectricityForHydrogenConsumptionYear_kW.reset();
	area.v_dataElectricityForCookingConsumptionYear_kW.reset();
	area.v_dataElectricityPVProductionYear_kW.reset();
	area.v_dataElectricityWindProductionYear_kW.reset(); 
	area.v_dataElectricityStorageProductionYear_kW.reset();
	area.v_dataElectricityV2GProductionYear_kW.reset();
	area.v_dataElectricityCHPProductionYear_kW.reset();
	area.v_dataBatterySOCYear_.reset();
}
else{
	area.v_dataElectricityBaseloadConsumptionYear_kW = new DataSet(365);
	area.v_dataElectricityForHeatConsumptionYear_kW = new DataSet(365);
	area.v_dataElectricityForTransportConsumptionYear_kW = new DataSet(365);
	area.v_dataElectricityForStorageConsumptionYear_kW = new DataSet(365);
	area.v_dataElectricityForHydrogenConsumptionYear_kW = new DataSet(365);
	area.v_dataElectricityForCookingConsumptionYear_kW = new DataSet(365);
	area.v_dataElectricityPVProductionYear_kW = new DataSet(365);
	area.v_dataElectricityWindProductionYear_kW = new DataSet(365);
	area.v_dataElectricityStorageProductionYear_kW = new DataSet(365);
	area.v_dataElectricityV2GProductionYear_kW = new DataSet(365);
	area.v_dataElectricityCHPProductionYear_kW = new DataSet(365);
	area.v_dataBatterySOCYear_ = new DataSet(365);
}

area.dsm_dailyAverageConsumptionDataSets_kW.createEmptyDataSets(activeEnergyCarriers, 365);
area.dsm_dailyAverageProductionDataSets_kW.createEmptyDataSets(activeEnergyCarriers, 365);

for (int i=0; i < gcList.get(0).data_annualBaseloadElectricityDemand_kW.size(); i++){ //we sume over the size of a random dataset (all datasets in this loop should ahve same size)
	
	//Create local variables
	double timeAxisValue = gcList.get(0).data_annualBaseloadElectricityDemand_kW.getX(i); // we get the X value from a random dataset 
	
	J_FlowsMap fm_dailyAverageDemand_kW = new J_FlowsMap();
	J_FlowsMap fm_dailyAverageSupply_kW = new J_FlowsMap();
	
	double electricityBaseloadDemandYear_kW = 0;
	double electricityForHeatDemandYear_kW = 0;
	double electricityForTransportDemandYear_kW = 0;
	double electricityForStorageDemandYear_kW = 0;
	double electricityForElectrolyser_kW = 0;
	double electricityForCookingConsumptionYear_kW = 0;
	double electricityPVSupplyYear_kW = 0;
	double electricityWindSupplyYear_kW = 0;
	double electricityStorageSupplyYear_kW = 0;
	double electricityV2GSupplyYear_kW = 0;
	double electricityCHPSupplyYear_kW = 0;
	double batteryStoredEnergy_MWh = 0;
	
	//accumulate values over gridcongestion
	for (GridConnection gc : gcList){
		
		for (OL_EnergyCarriers EC : gc.v_activeEnergyCarriers) {
			fm_dailyAverageDemand_kW.addFlow( EC, gc.dsm_dailyAverageDemandDataSets_kW.get(EC).getY(i) );
			fm_dailyAverageSupply_kW.addFlow( EC, gc.dsm_dailyAverageSupplyDataSets_kW.get(EC).getY(i) );
		}
	
		electricityBaseloadDemandYear_kW += gc.data_annualBaseloadElectricityDemand_kW.getY(i);
		electricityForHeatDemandYear_kW += gc.data_annualHeatPumpElectricityDemand_kW.getY(i);
		electricityForTransportDemandYear_kW += gc.data_annualElectricVehicleDemand_kW.getY(i);
		electricityForStorageDemandYear_kW += gc.data_annualBatteriesDemand_kW.getY(i);
		if (gc instanceof GCEnergyConversion) {
			electricityForElectrolyser_kW += ((GCEnergyConversion)gc).data_annualElectrolyserDemand_kW.getY(i);
		}
		electricityForCookingConsumptionYear_kW += gc.data_annualCookingElectricityDemand_kW.getY(i);
		electricityPVSupplyYear_kW += gc.data_annualPVGeneration_kW.getY(i);
		electricityWindSupplyYear_kW += gc.data_annualWindGeneration_kW.getY(i);
		electricityStorageSupplyYear_kW += gc.data_annualBatteriesSupply_kW.getY(i);
		electricityV2GSupplyYear_kW += gc.data_annualV2GSupply_kW.getY(i);
		electricityCHPSupplyYear_kW += gc.data_annualCHPElectricitySupply_kW.getY(i);
		batteryStoredEnergy_MWh += gc.data_annualBatteryStoredEnergy_MWh.getY(i);
	}
	
	//add accumulated values to dataset
	area.v_dataElectricityBaseloadConsumptionYear_kW.add(timeAxisValue, electricityBaseloadDemandYear_kW);
	area.v_dataElectricityForHeatConsumptionYear_kW.add(timeAxisValue, electricityForHeatDemandYear_kW);
	area.v_dataElectricityForTransportConsumptionYear_kW.add(timeAxisValue, electricityForTransportDemandYear_kW);
	area.v_dataElectricityForStorageConsumptionYear_kW.add(timeAxisValue, electricityForStorageDemandYear_kW);
	area.v_dataElectricityForHydrogenConsumptionYear_kW.add(timeAxisValue, electricityForElectrolyser_kW);
	area.v_dataElectricityForCookingConsumptionYear_kW.add(timeAxisValue, electricityForCookingConsumptionYear_kW);
	area.v_dataElectricityPVProductionYear_kW.add(timeAxisValue, electricityPVSupplyYear_kW);
	area.v_dataElectricityWindProductionYear_kW.add(timeAxisValue, electricityWindSupplyYear_kW);
	area.v_dataElectricityStorageProductionYear_kW.add(timeAxisValue, electricityStorageSupplyYear_kW);
	area.v_dataElectricityV2GProductionYear_kW.add(timeAxisValue, electricityV2GSupplyYear_kW);
	area.v_dataElectricityCHPProductionYear_kW.add(timeAxisValue, electricityCHPSupplyYear_kW);
	
	for (OL_EnergyCarriers EC : activeEnergyCarriers) {
		area.dsm_dailyAverageConsumptionDataSets_kW.get(EC).add(timeAxisValue, fm_dailyAverageDemand_kW.get(EC) );
		area.dsm_dailyAverageProductionDataSets_kW.get(EC).add(timeAxisValue, fm_dailyAverageSupply_kW.get(EC) );
	}
	
	//Other
	double SOC = sum(gcList, GC -> GC.v_totalInstalledBatteryStorageCapacity_MWh) > 0 ? batteryStoredEnergy_MWh/sum(gcList, GC -> GC.v_totalInstalledBatteryStorageCapacity_MWh) : 0;
	area.v_dataBatterySOCYear_.add(timeAxisValue, SOC);
}
/*ALCODEEND*/}

double f_updateWeeklyGCData(AreaCollection area,ArrayList<GridConnection> gcList)
{/*ALCODESTART::1739364390449*/
//ArrayList<GridConnection> gcList = c_gcList;
EnumSet<OL_EnergyCarriers> activeEnergyCarriers = EnumSet.noneOf(OL_EnergyCarriers.class);
for (GridConnection gc : gcList) {
	activeEnergyCarriers.addAll(gc.v_activeEnergyCarriers);
}

//reset the datasets (is this required)?
if( area.v_dataElectricityBaseloadConsumptionSummerWeek_kW != null ){
	area.v_dataElectricityBaseloadConsumptionSummerWeek_kW.reset();
	area.v_dataElectricityForHeatConsumptionSummerWeek_kW.reset();
	area.v_dataElectricityForTransportConsumptionSummerWeek_kW.reset();
	area.v_dataElectricityForStorageConsumptionSummerWeek_kW.reset();
	area.v_dataElectricityForHydrogenConsumptionSummerWeek_kW.reset();
	area.v_dataElectricityForCookingConsumptionSummerWeek_kW.reset();
	area.v_dataElectricityPVProductionSummerWeek_kW.reset();
	area.v_dataElectricityWindProductionSummerWeek_kW.reset(); 
	area.v_dataElectricityStorageProductionSummerWeek_kW.reset();
	area.v_dataElectricityV2GProductionSummerWeek_kW.reset();
	area.v_dataElectricityCHPProductionSummerWeek_kW.reset();
	
	area.v_dataElectricityBaseloadConsumptionWinterWeek_kW.reset();
	area.v_dataElectricityForHeatConsumptionWinterWeek_kW.reset();
	area.v_dataElectricityForTransportConsumptionWinterWeek_kW.reset();
	area.v_dataElectricityForStorageConsumptionWinterWeek_kW.reset();
	area.v_dataElectricityForCookingConsumptionWinterWeek_kW.reset();
	area.v_dataElectricityForHydrogenConsumptionWinterWeek_kW.reset();
	area.v_dataElectricityPVProductionWinterWeek_kW.reset();
	area.v_dataElectricityWindProductionWinterWeek_kW.reset(); 
	area.v_dataElectricityStorageProductionWinterWeek_kW.reset();
	area.v_dataElectricityV2GProductionWinterWeek_kW.reset();
	area.v_dataElectricityCHPProductionWinterWeek_kW.reset();
	
	area.v_dataNetLoadSummerWeek_kW.reset();
	area.v_dataNetLoadWinterWeek_kW.reset();
	area.v_dataElectricityDeliveryCapacitySummerWeek_kW.reset();
	area.v_dataElectricityDeliveryCapacityWinterWeek_kW.reset();
	area.v_dataElectricityFeedInCapacitySummerWeek_kW.reset();
	area.v_dataElectricityFeedInCapacityWinterWeek_kW.reset();
	
	area.v_dataBatterySOCSummerWeek_.reset();
	area.v_dataBatterySOCWinterWeek_.reset();
}
else {
	area.v_dataElectricityBaseloadConsumptionSummerWeek_kW = new DataSet(672);
	area.v_dataElectricityForHeatConsumptionSummerWeek_kW = new DataSet(672);
	area.v_dataElectricityForTransportConsumptionSummerWeek_kW = new DataSet(672);
	area.v_dataElectricityForStorageConsumptionSummerWeek_kW = new DataSet(672);
	area.v_dataElectricityForHydrogenConsumptionSummerWeek_kW = new DataSet(672);
	area.v_dataElectricityForCookingConsumptionSummerWeek_kW = new DataSet(672);
	area.v_dataElectricityPVProductionSummerWeek_kW = new DataSet(672);
	area.v_dataElectricityWindProductionSummerWeek_kW = new DataSet(672);
	area.v_dataElectricityStorageProductionSummerWeek_kW = new DataSet(672);
	area.v_dataElectricityV2GProductionSummerWeek_kW = new DataSet(672);
	area.v_dataElectricityCHPProductionSummerWeek_kW = new DataSet(672);
	
	area.v_dataElectricityBaseloadConsumptionWinterWeek_kW = new DataSet(672);
	area.v_dataElectricityForHeatConsumptionWinterWeek_kW = new DataSet(672);
	area.v_dataElectricityForTransportConsumptionWinterWeek_kW = new DataSet(672);
	area.v_dataElectricityForStorageConsumptionWinterWeek_kW = new DataSet(672);
	area.v_dataElectricityForHydrogenConsumptionWinterWeek_kW = new DataSet(672);
	area.v_dataElectricityForCookingConsumptionWinterWeek_kW = new DataSet(672);
	area.v_dataElectricityPVProductionWinterWeek_kW = new DataSet(672);
	area.v_dataElectricityWindProductionWinterWeek_kW = new DataSet(672);
	area.v_dataElectricityStorageProductionWinterWeek_kW = new DataSet(672);
	area.v_dataElectricityV2GProductionWinterWeek_kW = new DataSet(672);
	area.v_dataElectricityCHPProductionWinterWeek_kW = new DataSet(672);
	
	area.v_dataNetLoadSummerWeek_kW = new DataSet(672);
	area.v_dataNetLoadWinterWeek_kW = new DataSet(672);
	area.v_dataElectricityDeliveryCapacitySummerWeek_kW = new DataSet(672);
	area.v_dataElectricityDeliveryCapacityWinterWeek_kW = new DataSet(672);
	area.v_dataElectricityFeedInCapacitySummerWeek_kW = new DataSet(672);
	area.v_dataElectricityFeedInCapacityWinterWeek_kW = new DataSet(672);
	
	
	area.dsm_summerWeekConsumptionDataSets_kW.createEmptyDataSets(energyModel.v_activeEnergyCarriers, (int) (168 / energyModel.p_timeStep_h));
	area.dsm_summerWeekProductionDataSets_kW.createEmptyDataSets(energyModel.v_activeEnergyCarriers, (int) (168 / energyModel.p_timeStep_h));
	area.dsm_winterWeekConsumptionDataSets_kW.createEmptyDataSets(energyModel.v_activeEnergyCarriers, (int) (168 / energyModel.p_timeStep_h));
	area.dsm_winterWeekProductionDataSets_kW.createEmptyDataSets(energyModel.v_activeEnergyCarriers, (int) (168 / energyModel.p_timeStep_h));
	
	area.v_dataBatterySOCSummerWeek_ = new DataSet(672);
	area.v_dataBatterySOCWinterWeek_ = new DataSet(672);
}


for (int i=0; i < gcList.get(0).data_summerWeekBaseloadElectricityDemand_kW.size(); i++){ //we sum over the size of a random dataset (all datasets in this loop should ahve same size)
	
	//Create local variables
	double timeAxisValueSummer = gcList.get(0).data_summerWeekBaseloadElectricityDemand_kW.getX(i); // we get the X value from a random dataset 
	double timeAxisValueWinter = gcList.get(0).data_winterWeekBaseloadElectricityDemand_kW.getX(i); // we get the X value from a random dataset 
	
	J_FlowsMap demandSummerWeek_kW = new J_FlowsMap();
	J_FlowsMap supplySummerWeek_kW = new J_FlowsMap();
	
	J_FlowsMap demandWinterWeek_kW = new J_FlowsMap();
	J_FlowsMap supplyWinterWeek_kW = new J_FlowsMap();
	
	
	double electricityBaseloadDemandSummerWeek_kW = 0;
	double electricityForHeatDemandSummerWeek_kW = 0;
	double electricityForTransportDemandSummerWeek_kW = 0;
	double electricityForStorageDemandSummerWeek_kW = 0;
	double electricityForHydrogenDemandSummerWeek_kW = 0;
	double electricityForCookingConsumptionSummerWeek_kW = 0;
	double electricityPVSupplySummerWeek_kW = 0;
	double electricityWindSupplySummerWeek_kW = 0;
	double electricityStorageSupplySummerWeek_kW = 0;
	double electricityV2GSupplySummerWeek_kW = 0;
	double electricityCHPSupplySummerWeek_kW = 0;
	
	double electricityBaseloadDemandWinterWeek_kW = 0;
	double electricityForHeatDemandWinterWeek_kW = 0;
	double electricityForTransportDemandWinterWeek_kW = 0;
	double electricityForStorageDemandWinterWeek_kW = 0;
	double electricityForHydrogenDemandWinterWeek_kW = 0;
	double electricityForCookingConsumptionWinterWeek_kW = 0;
	double electricityPVSupplyWinterWeek_kW = 0;
	double electricityWindSupplyWinterWeek_kW = 0;
	double electricityStorageSupplyWinterWeek_kW = 0;
	double electricityV2GSupplyWinterWeek_kW = 0;
	double electricityCHPSupplyWinterWeek_kW = 0;
	
	double netLoadSummerWeek_kW = 0;
	double netLoadWinterWeek_kW = 0;
	double electricityDemandCapacitySummerWeek_kW = 0;
	double electricityDemandCapacityWinterWeek_kW = 0;
	double electricitySupplyCapacitySummerWeek_kW = 0;
	double electricitySupplyCapacityWinterWeek_kW = 0;
	
	double batteryStoredEnergySummerWeek_MWh = 0;
	double batteryStoredEnergyWinterWeek_MWh = 0;
	
	//accumulate values over gridcongestion
	for (GridConnection gc : gcList){
		for (OL_EnergyCarriers EC : gc.v_activeEnergyCarriers) {
				demandSummerWeek_kW.addFlow(EC, gc.dsm_summerWeekDemandDataSets_kW.get(EC).getY(i));
				supplySummerWeek_kW.addFlow(EC, gc.dsm_summerWeekSupplyDataSets_kW.get(EC).getY(i));
				demandWinterWeek_kW.addFlow(EC, gc.dsm_winterWeekDemandDataSets_kW.get(EC).getY(i));
				supplyWinterWeek_kW.addFlow(EC, gc.dsm_winterWeekSupplyDataSets_kW.get(EC).getY(i));
		}
	
		electricityBaseloadDemandSummerWeek_kW += gc.data_summerWeekBaseloadElectricityDemand_kW.getY(i);
		electricityForHeatDemandSummerWeek_kW += gc.data_summerWeekHeatPumpElectricityDemand_kW.getY(i);
		electricityForTransportDemandSummerWeek_kW += gc.data_summerWeekElectricVehicleDemand_kW.getY(i);
		electricityForStorageDemandSummerWeek_kW += gc.data_summerWeekBatteriesDemand_kW.getY(i);
		electricityForCookingConsumptionSummerWeek_kW += gc.data_summerWeekCookingElectricityDemand_kW.getY(i);
		
		electricityPVSupplySummerWeek_kW += gc.data_summerWeekPVGeneration_kW.getY(i);
		electricityWindSupplySummerWeek_kW += gc.data_summerWeekWindGeneration_kW.getY(i);
		electricityStorageSupplySummerWeek_kW += gc.data_summerWeekBatteriesSupply_kW.getY(i);
		electricityV2GSupplySummerWeek_kW += gc.data_summerWeekV2GSupply_kW.getY(i);
		electricityCHPSupplySummerWeek_kW += gc.data_summerWeekCHPElectricityProduction_kW.getY(i);
		
		if (gc instanceof GCEnergyConversion) {
			electricityForHydrogenDemandSummerWeek_kW += ((GCEnergyConversion)gc).data_summerWeekElectrolyserDemand_kW.getY(i);
		}
		
		electricityBaseloadDemandWinterWeek_kW += gc.data_winterWeekBaseloadElectricityDemand_kW.getY(i);
		electricityForHeatDemandWinterWeek_kW += gc.data_winterWeekHeatPumpElectricityDemand_kW.getY(i);
		electricityForTransportDemandWinterWeek_kW += gc.data_winterWeekElectricVehicleDemand_kW.getY(i);
		electricityForStorageDemandWinterWeek_kW += gc.data_winterWeekBatteriesDemand_kW.getY(i);
		if (gc instanceof GCEnergyConversion) {
			electricityForHydrogenDemandWinterWeek_kW += ((GCEnergyConversion)gc).data_winterWeekElectrolyserDemand_kW.getY(i);
		}
		electricityForCookingConsumptionWinterWeek_kW += gc.data_winterWeekCookingElectricityDemand_kW.getY(i);
		electricityPVSupplyWinterWeek_kW += gc.data_winterWeekPVGeneration_kW.getY(i);
		electricityWindSupplyWinterWeek_kW += gc.data_winterWeekWindGeneration_kW.getY(i);
		electricityStorageSupplyWinterWeek_kW += gc.data_winterWeekBatteriesSupply_kW.getY(i);
		electricityV2GSupplyWinterWeek_kW += gc.data_winterWeekV2GSupply_kW.getY(i);
		electricityCHPSupplyWinterWeek_kW += gc.data_winterWeekCHPElectricityProduction_kW.getY(i);
		netLoadWinterWeek_kW += gc.am_winterWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getY(i);
		electricityDemandCapacitySummerWeek_kW += gc.acc_summerWeekDeliveryCapacity_kW.getY(i);
		electricityDemandCapacityWinterWeek_kW += gc.acc_winterWeekDeliveryCapacity_kW.getY(i);
		electricitySupplyCapacitySummerWeek_kW -= gc.acc_summerWeekFeedinCapacity_kW.getY(i);
		electricitySupplyCapacityWinterWeek_kW -= gc.acc_winterWeekFeedinCapacity_kW.getY(i);
		
		batteryStoredEnergySummerWeek_MWh += gc.data_summerWeekBatteryStoredEnergy_MWh.getY(i);
		batteryStoredEnergyWinterWeek_MWh += gc.data_winterWeekBatteryStoredEnergy_MWh.getY(i);
	}
	
	//add accumulated values to dataset
	area.v_dataElectricityBaseloadConsumptionSummerWeek_kW.add(timeAxisValueSummer, electricityBaseloadDemandSummerWeek_kW);
	area.v_dataElectricityForHeatConsumptionSummerWeek_kW.add(timeAxisValueSummer, electricityForHeatDemandSummerWeek_kW);
	area.v_dataElectricityForTransportConsumptionSummerWeek_kW.add(timeAxisValueSummer, electricityForTransportDemandSummerWeek_kW);
	area.v_dataElectricityForStorageConsumptionSummerWeek_kW.add(timeAxisValueSummer, electricityForStorageDemandSummerWeek_kW);
	area.v_dataElectricityForHydrogenConsumptionSummerWeek_kW.add(timeAxisValueSummer, electricityForHydrogenDemandSummerWeek_kW);
	area.v_dataElectricityForCookingConsumptionSummerWeek_kW.add(timeAxisValueSummer, electricityForCookingConsumptionSummerWeek_kW);
	area.v_dataElectricityPVProductionSummerWeek_kW.add(timeAxisValueSummer, electricityPVSupplySummerWeek_kW);
	area.v_dataElectricityWindProductionSummerWeek_kW.add(timeAxisValueSummer, electricityWindSupplySummerWeek_kW);
	area.v_dataElectricityStorageProductionSummerWeek_kW.add(timeAxisValueSummer, electricityStorageSupplySummerWeek_kW);
	area.v_dataElectricityV2GProductionSummerWeek_kW.add(timeAxisValueSummer, electricityV2GSupplySummerWeek_kW);
	area.v_dataElectricityCHPProductionSummerWeek_kW.add(timeAxisValueSummer, electricityCHPSupplySummerWeek_kW);

	area.v_dataNetLoadSummerWeek_kW.add(timeAxisValueSummer, netLoadSummerWeek_kW);
	area.v_dataElectricityDeliveryCapacitySummerWeek_kW.add(timeAxisValueSummer, electricityDemandCapacitySummerWeek_kW);
	area.v_dataElectricityFeedInCapacitySummerWeek_kW.add(timeAxisValueSummer, electricitySupplyCapacitySummerWeek_kW);
	
	area.v_dataElectricityBaseloadConsumptionWinterWeek_kW.add(timeAxisValueWinter, electricityBaseloadDemandWinterWeek_kW);
	area.v_dataElectricityForHeatConsumptionWinterWeek_kW.add(timeAxisValueWinter, electricityForHeatDemandWinterWeek_kW);
	area.v_dataElectricityForTransportConsumptionWinterWeek_kW.add(timeAxisValueWinter, electricityForTransportDemandWinterWeek_kW);
	area.v_dataElectricityForStorageConsumptionWinterWeek_kW.add(timeAxisValueWinter, electricityForStorageDemandWinterWeek_kW);
	area.v_dataElectricityForHydrogenConsumptionWinterWeek_kW.add(timeAxisValueWinter, electricityForHydrogenDemandWinterWeek_kW);
	area.v_dataElectricityForCookingConsumptionWinterWeek_kW.add(timeAxisValueWinter, electricityForCookingConsumptionWinterWeek_kW);
	area.v_dataElectricityPVProductionWinterWeek_kW.add(timeAxisValueWinter, electricityPVSupplyWinterWeek_kW);
	area.v_dataElectricityWindProductionWinterWeek_kW.add(timeAxisValueWinter, electricityWindSupplyWinterWeek_kW);
	area.v_dataElectricityStorageProductionWinterWeek_kW.add(timeAxisValueWinter, electricityStorageSupplyWinterWeek_kW);
	area.v_dataElectricityV2GProductionWinterWeek_kW.add(timeAxisValueWinter, electricityV2GSupplyWinterWeek_kW);
	area.v_dataElectricityCHPProductionWinterWeek_kW.add(timeAxisValueWinter, electricityCHPSupplyWinterWeek_kW);
	
	area.v_dataNetLoadWinterWeek_kW.add(timeAxisValueWinter, netLoadWinterWeek_kW);
	area.v_dataElectricityDeliveryCapacityWinterWeek_kW.add(timeAxisValueWinter, electricityDemandCapacityWinterWeek_kW);
	area.v_dataElectricityFeedInCapacityWinterWeek_kW.add(timeAxisValueWinter, electricitySupplyCapacityWinterWeek_kW);
	
	for (OL_EnergyCarriers EC : activeEnergyCarriers) {
		area.dsm_summerWeekConsumptionDataSets_kW.get(EC).add(timeAxisValueSummer, demandSummerWeek_kW.get(EC));
		area.dsm_summerWeekProductionDataSets_kW.get(EC).add(timeAxisValueSummer, supplySummerWeek_kW.get(EC));
		area.dsm_winterWeekConsumptionDataSets_kW.get(EC).add(timeAxisValueWinter, demandWinterWeek_kW.get(EC));
		area.dsm_winterWeekProductionDataSets_kW.get(EC).add(timeAxisValueWinter, supplyWinterWeek_kW.get(EC));
	}
	
	//Other
	double SOC_summer = sum(gcList, GC -> GC.v_totalInstalledBatteryStorageCapacity_MWh) > 0 ? batteryStoredEnergySummerWeek_MWh/sum(gcList, GC -> GC.v_totalInstalledBatteryStorageCapacity_MWh) : 0;
	double SOC_winter = sum(gcList, GC -> GC.v_totalInstalledBatteryStorageCapacity_MWh) > 0 ? batteryStoredEnergyWinterWeek_MWh/sum(gcList, GC -> GC.v_totalInstalledBatteryStorageCapacity_MWh) : 0;
	
	area.v_dataBatterySOCSummerWeek_.add(timeAxisValueSummer, SOC_summer);
	area.v_dataBatterySOCWinterWeek_.add(timeAxisValueWinter, SOC_winter);
	
	
}
/*ALCODEEND*/}

double f_updateBelastingduurKromme(AreaCollection area,ArrayList<GridConnection> gcList)
{/*ALCODESTART::1739364390451*/
int arraySize = roundToInt((energyModel.p_runEndTime_h-energyModel.p_runStartTime_h)/energyModel.p_timeStep_h);

// loop over gcs and calculate the cumulative netload. Also sum the connection capacities
double totalDeliveryCapacity_kW = 0;
double totalFeedInCapacity_kW = 0;

area.v_dataNetLoadYear_kW = new double[arraySize];
area.v_individualPeakDelivery_kW = 0;
area.v_individualPeakFeedin_kW = 0;

for (GridConnection gc : gcList) {
	double[] balanceTimeSeries_kW = gc.am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getTimeSeries();
	
	for (int i = 0; i<arraySize; i++) {
		area.v_dataNetLoadYear_kW[i] += balanceTimeSeries_kW[i];
		
	}

	gc.f_nfatoSetConnectionCapacity(true);
	totalDeliveryCapacity_kW += gc.p_contractedDeliveryCapacity_kW;
	totalFeedInCapacity_kW += gc.p_contractedFeedinCapacity_kW;
	gc.f_nfatoSetConnectionCapacity(false);
	
	//Individual gc peaks cumulative
	area.v_individualPeakDelivery_kW += gc.am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMax();
	area.v_individualPeakFeedin_kW += -1*gc.am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMin();
}

//Recalculate total overload duration
area.v_totalTimeOverloaded_h = 0;
for(double netLoad : area.v_dataNetLoadYear_kW){
	if(netLoad > totalDeliveryCapacity_kW || netLoad < -1*totalFeedInCapacity_kW ){
		area.v_totalTimeOverloaded_h += energyModel.p_timeStep_h;
	}
} 
    
J_LoadDurationCurves j_duurkrommes = new J_LoadDurationCurves(area.v_dataNetLoadYear_kW, energyModel);

area.v_dataNetbelastingDuurkrommeYear_kW = j_duurkrommes.ds_loadDurationCurveTotal_kW;
area.v_dataNetbelastingDuurkrommeSummer_kW = j_duurkrommes.ds_loadDurationCurveSummer_kW;
area.v_dataNetbelastingDuurkrommeWinter_kW = j_duurkrommes.ds_loadDurationCurveWinter_kW;
area.v_dataNetbelastingDuurkrommeDaytime_kW = j_duurkrommes.ds_loadDurationCurveDaytime_kW;
area.v_dataNetbelastingDuurkrommeNighttime_kW = j_duurkrommes.ds_loadDurationCurveNighttime_kW;
area.v_dataNetbelastingDuurkrommeWeekday_kW = j_duurkrommes.ds_loadDurationCurveWeekday_kW;
area.v_dataNetbelastingDuurkrommeWeekend_kW = j_duurkrommes.ds_loadDurationCurveWeekend_kW;

// Connection Capacity
area.data_gridCapacityDeliveryYear_kW.add(0, totalDeliveryCapacity_kW);
area.data_gridCapacityDeliveryYear_kW.add(8760, totalDeliveryCapacity_kW);
area.data_gridCapacityFeedInYear_kW.add(0, -totalFeedInCapacity_kW);
area.data_gridCapacityFeedInYear_kW.add(8760, -totalFeedInCapacity_kW);

/*ALCODEEND*/}

double f_updatePreviousTotalsGC()
{/*ALCODESTART::1739364390454*/
for (GridConnection GC : energyModel.f_getGridConnections()){	
	J_previousTotals previousTotals = c_previousTotals_GC.get(GC);
	
	previousTotals.setPreviousTotalImports_MWh(GC.fm_totalImports_MWh);
	previousTotals.setPreviousTotalExports_MWh(GC.fm_totalExports_MWh);
	
	previousTotals.setPreviousTotalConsumedEnergy_MWh(GC.v_totalEnergyConsumed_MWh);
	previousTotals.setPreviousTotalProducedEnergy_MWh(GC.v_totalEnergyProduced_MWh);
	previousTotals.setPreviousSelfConsumedEnergy_MWh(GC.v_totalEnergySelfConsumed_MWh);
	previousTotals.setPreviousImportedEnergy_MWh(GC.v_totalEnergyImport_MWh);
	previousTotals.setPreviousExportedEnergy_MWh(GC.v_totalEnergyExport_MWh);
	previousTotals.setPreviousSelfConsumedElectricity_MWh(GC.v_totalElectricitySelfConsumed_MWh);
	previousTotals.setPreviousElectricityConsumed_MWh(GC.v_totalElectricityConsumed_MWh);
	
	//Overload
	previousTotals.setPreviousOverloadDurationDelivery_hr(GC.v_totalOverloadDurationDelivery_hr);
	previousTotals.setPreviousOverloadDurationFeedin_hr(GC.v_totalOverloadDurationFeedin_hr);
}

/*ALCODEEND*/}

double f_updateVariablesOfGCData(AreaCollection area,ArrayList<GridConnection> gcList)
{/*ALCODESTART::1739364390456*/
area.v_numberOfGridconnections = gcList.size();

EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.noneOf(OL_EnergyCarriers.class);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.noneOf(OL_EnergyCarriers.class);
for (GridConnection gc : gcList) {
	activeProductionEnergyCarriers.addAll(gc.v_activeProductionEnergyCarriers);
	activeConsumptionEnergyCarriers.addAll(gc.v_activeConsumptionEnergyCarriers);
}

//Set active energyCarriers
area.v_activeProductionEnergyCarriers = activeProductionEnergyCarriers;
area.v_activeConsumptionEnergyCarriers = activeConsumptionEnergyCarriers;


//Set boolean if it has connection with heatgrid
boolean hasHeatGridConnection = false;
for (GridConnection gc : gcList) {
	if(gc.l_parentNodeHeat != null){
		area.b_hasHeatGridConnection = true;
	}
}

// Can't use pointer for (immutable) primitives in Java, so need to manually update results after a year-sim!!
area.v_gridCapacityDelivery_kW = sum(gcList, x -> x.p_contractedDeliveryCapacity_kW);
area.v_gridCapacityFeedIn_kW = sum(gcList, x -> x.p_contractedFeedinCapacity_kW);
boolean isRealDeliveryCapacityAvailable = true;
boolean isRealFeedinCapacityAvailable = true;
for(GridConnection GC : gcList){
	if(!GC.b_isRealDeliveryCapacityAvailable){
		isRealDeliveryCapacityAvailable = false;
		break;
	}
}
for(GridConnection GC : gcList){
	if(!GC.b_isRealFeedinCapacityAvailable){
		isRealFeedinCapacityAvailable = false;
		break;
	}
}
area.b_isRealDeliveryCapacityAvailable = isRealDeliveryCapacityAvailable;
area.b_isRealFeedinCapacityAvailable = isRealFeedinCapacityAvailable;

//Installed Asset variables
area.v_batteryStorageCapacityInstalled_MWh = 0;
for(GridConnection GC : gcList){
	area.v_batteryStorageCapacityInstalled_MWh += GC.v_totalInstalledBatteryStorageCapacity_MWh;
}

/*
//Recalculation of self consumption
double groupSelfConsumption = 



double individualSelfConsumption = sum(gcList, x -> x.v_totalElectricitySelfConsumed_MWh);
*/


// KPIs for 'samenvatting' 
area.v_individualSelfconsumptionElectricity_fr = sum(gcList, x -> x.v_totalElectricityProduced_MWh) > 0 ? sum(gcList, x -> x.v_totalElectricitySelfConsumed_MWh) / sum(gcList, x -> x.v_totalElectricityProduced_MWh) : 0.0;
area.v_individualSelfSufficiencyElectricity_fr = sum(gcList, x -> x.v_totalElectricityConsumed_MWh) > 0 ? sum(gcList, x -> x.v_totalElectricitySelfConsumed_MWh) / sum(gcList, x -> x.v_totalElectricityConsumed_MWh): 0.0;

area.v_individualSelfconsumptionEnergy_fr = sum(gcList, x -> x.v_totalEnergyProduced_MWh) > 0 ? sum(gcList, x -> x.v_totalEnergySelfConsumed_MWh) / sum(gcList, x -> x.v_totalEnergyProduced_MWh) : 0.0;
area.v_individualSelfSufficiencyEnergy_fr = sum(gcList, x -> x.v_totalEnergyProduced_MWh) > 0 ? sum(gcList, x -> x.v_totalEnergySelfConsumed_MWh) / sum(gcList, x -> x.v_totalEnergyConsumed_MWh): 0.0;



//Yearly
area.fm_totalImports_MWh.clear();
area.fm_totalExports_MWh.clear();
for (OL_EnergyCarriers EC : activeProductionEnergyCarriers) {
	area.fm_totalExports_MWh.put( EC, sum(gcList, x -> x.fm_totalExports_MWh.get(EC)) );
}
for (OL_EnergyCarriers EC : activeConsumptionEnergyCarriers) {
	area.fm_totalImports_MWh.put( EC, sum(gcList, x -> x.fm_totalImports_MWh.get(EC)) );
}

area.v_totalEnergyImport_MWh = sum(gcList, x -> x.v_totalEnergyImport_MWh);
area.v_totalEnergyExport_MWh = sum(gcList, x -> x.v_totalEnergyExport_MWh);

area.v_totalEnergyProduced_MWh = sum(gcList, x -> x.v_totalEnergyProduced_MWh);
area.v_totalEnergyConsumed_MWh = sum(gcList, x -> x.v_totalEnergyConsumed_MWh);
area.v_totalEnergySelfConsumed_MWh = sum(gcList, x -> x.v_totalEnergySelfConsumed_MWh);

area.v_totalElectricityProduced_MWh = sum(gcList, x -> x.v_totalElectricityProduced_MWh);
area.v_totalElectricityConsumed_MWh = sum(gcList, x -> x.v_totalElectricityConsumed_MWh);
area.v_totalElectricitySelfConsumed_MWh = sum(gcList, x -> x.v_totalElectricitySelfConsumed_MWh);

//Overload duration (for multiple GC this does not really make sense right?, would be more interesting to see the influence of their combined contract capacity)
area.v_annualOverloadDurationDelivery_hr = sum(gcList, x -> x.v_totalOverloadDurationDelivery_hr);
area.v_annualOverloadDurationFeedin_hr = sum(gcList, x -> x.v_totalOverloadDurationFeedin_hr);

//Previous annual values: does not work (yet) for multiselect.
area.v_previousTotals = c_previousTotals_GC.get(gcList.get(0));


// Summer/winter
area.fm_summerWeekImports_MWh.clear();
area.fm_summerWeekExports_MWh.clear();
area.fm_winterWeekImports_MWh.clear();
area.fm_winterWeekExports_MWh.clear();
for (OL_EnergyCarriers EC : activeProductionEnergyCarriers) {
	area.fm_summerWeekExports_MWh.put( EC, sum(gcList, x -> x.fm_summerWeekExports_MWh.get(EC)) );
	area.fm_winterWeekExports_MWh.put( EC, sum(gcList, x -> x.fm_winterWeekExports_MWh.get(EC)) );
}
for (OL_EnergyCarriers EC : activeConsumptionEnergyCarriers) {
	area.fm_summerWeekImports_MWh.put( EC, sum(gcList, x -> x.fm_summerWeekImports_MWh.get(EC)) );
	area.fm_winterWeekImports_MWh.put( EC, sum(gcList, x -> x.fm_winterWeekImports_MWh.get(EC)) );
}

area.v_summerWeekEnergyImport_MWh = sum(gcList, x -> x.v_summerWeekEnergyImport_MWh);
area.v_summerWeekEnergyExport_MWh = sum(gcList, x -> x.v_summerWeekEnergyExport_MWh);

area.v_summerWeekEnergyProduced_MWh = sum(gcList, x -> x.v_summerWeekEnergyProduced_MWh);
area.v_summerWeekEnergyConsumed_MWh = sum(gcList, x -> x.v_summerWeekEnergyConsumed_MWh);
area.v_summerWeekEnergySelfConsumed_MWh = sum(gcList, x -> x.v_summerWeekEnergySelfConsumed_MWh);

area.v_summerWeekElectricityProduced_MWh = sum(gcList, x -> x.v_summerWeekElectricityProduced_MWh);
area.v_summerWeekElectricityConsumed_MWh = sum(gcList, x -> x.v_summerWeekElectricityConsumed_MWh);
area.v_summerWeekElectricitySelfConsumed_MWh = sum(gcList, x -> x.v_summerWeekElectricitySelfConsumed_MWh);

area.v_winterWeekEnergyImport_MWh = sum(gcList, x -> x.v_winterWeekEnergyImport_MWh);
area.v_winterWeekEnergyExport_MWh = sum(gcList, x -> x.v_winterWeekEnergyExport_MWh);

area.v_winterWeekEnergyProduced_MWh = sum(gcList, x -> x.v_winterWeekEnergyProduced_MWh);
area.v_winterWeekEnergyConsumed_MWh = sum(gcList, x -> x.v_winterWeekEnergyConsumed_MWh);
area.v_winterWeekEnergySelfConsumed_MWh = sum(gcList, x -> x.v_winterWeekEnergySelfConsumed_MWh);

area.v_winterWeekElectricityProduced_MWh = sum(gcList, x -> x.v_winterWeekElectricityProduced_MWh);
area.v_winterWeekElectricityConsumed_MWh = sum(gcList, x -> x.v_winterWeekElectricityConsumed_MWh);
area.v_winterWeekElectricitySelfConsumed_MWh = sum(gcList, x -> x.v_winterWeekElectricitySelfConsumed_MWh);

// Day/night
area.fm_daytimeImports_MWh.clear();
area.fm_daytimeExports_MWh.clear();
area.fm_nighttimeImports_MWh.clear();
area.fm_nighttimeExports_MWh.clear();
for (OL_EnergyCarriers EC : activeProductionEnergyCarriers) {
	area.fm_daytimeExports_MWh.put( EC, sum(gcList, x -> x.fm_daytimeExports_MWh.get(EC)) );
	area.fm_nighttimeExports_MWh.put( EC, sum(gcList, x -> x.fm_nighttimeExports_MWh.get(EC)) );
}
for (OL_EnergyCarriers EC : activeConsumptionEnergyCarriers) {
	area.fm_daytimeImports_MWh.put( EC, sum(gcList, x -> x.fm_daytimeImports_MWh.get(EC)) );
	area.fm_nighttimeImports_MWh.put( EC, sum(gcList, x -> x.fm_nighttimeImports_MWh.get(EC)) );
}

area.v_daytimeEnergyImport_MWh = sum(gcList, x -> x.v_daytimeEnergyImport_MWh);
area.v_daytimeEnergyExport_MWh = sum(gcList, x -> x.v_daytimeEnergyExport_MWh);

area.v_daytimeEnergyProduced_MWh = sum(gcList, x -> x.v_daytimeEnergyProduced_MWh);
area.v_daytimeEnergyConsumed_MWh = sum(gcList, x -> x.v_daytimeEnergyConsumed_MWh);
area.v_daytimeEnergySelfConsumed_MWh = sum(gcList, x -> x.v_daytimeEnergySelfConsumed_MWh);

area.v_daytimeElectricityProduced_MWh = sum(gcList, x -> x.v_daytimeElectricityProduced_MWh);
area.v_daytimeElectricityConsumed_MWh = sum(gcList, x -> x.v_daytimeElectricityConsumed_MWh);
area.v_daytimeElectricitySelfConsumed_MWh = sum(gcList, x -> x.v_daytimeElectricitySelfConsumed_MWh);


area.v_nighttimeEnergyImport_MWh = sum(gcList, x -> x.v_nighttimeEnergyImport_MWh);
area.v_nighttimeEnergyExport_MWh = sum(gcList, x -> x.v_nighttimeEnergyExport_MWh);

area.v_nighttimeEnergyProduced_MWh = sum(gcList, x -> x.v_nighttimeEnergyProduced_MWh);
area.v_nighttimeEnergyConsumed_MWh = sum(gcList, x -> x.v_nighttimeEnergyConsumed_MWh);
area.v_nighttimeEnergySelfConsumed_MWh = sum(gcList, x -> x.v_nighttimeEnergySelfConsumed_MWh);

area.v_nighttimeElectricityProduced_MWh = sum(gcList, x -> x.v_nighttimeElectricityProduced_MWh);
area.v_nighttimeElectricityConsumed_MWh = sum(gcList, x -> x.v_nighttimeElectricityConsumed_MWh);
area.v_nighttimeElectricitySelfConsumed_MWh = sum(gcList, x -> x.v_nighttimeElectricitySelfConsumed_MWh);

// Week/weekend
area.fm_weekdayImports_MWh.clear();
area.fm_weekdayExports_MWh.clear();
area.fm_weekendImports_MWh.clear();
area.fm_weekendExports_MWh.clear();
for (OL_EnergyCarriers EC : activeProductionEnergyCarriers) {
	area.fm_weekdayExports_MWh.put( EC, sum(gcList, x -> x.fm_weekdayExports_MWh.get(EC)) );
	area.fm_weekendExports_MWh.put( EC, sum(gcList, x -> x.fm_weekendExports_MWh.get(EC)) );
}
for (OL_EnergyCarriers EC : activeConsumptionEnergyCarriers) {
	area.fm_weekdayImports_MWh.put( EC, sum(gcList, x -> x.fm_weekdayImports_MWh.get(EC)) );
	area.fm_weekendImports_MWh.put( EC, sum(gcList, x -> x.fm_weekendImports_MWh.get(EC)) );
}


area.v_weekdayEnergyImport_MWh = sum(gcList, x -> x.v_weekdayEnergyImport_MWh);
area.v_weekdayEnergyExport_MWh = sum(gcList, x -> x.v_weekdayEnergyExport_MWh);

area.v_weekdayEnergyProduced_MWh = sum(gcList, x -> x.v_weekdayEnergyProduced_MWh);
area.v_weekdayEnergyConsumed_MWh = sum(gcList, x -> x.v_weekdayEnergyConsumed_MWh);
area.v_weekdayEnergySelfConsumed_MWh = sum(gcList, x -> x.v_weekdayEnergySelfConsumed_MWh);

area.v_weekdayElectricityProduced_MWh = sum(gcList, x -> x.v_weekdayElectricityProduced_MWh);
area.v_weekdayElectricityConsumed_MWh = sum(gcList, x -> x.v_weekdayElectricityConsumed_MWh);
area.v_weekdayElectricitySelfConsumed_MWh = sum(gcList, x -> x.v_weekdayElectricitySelfConsumed_MWh);


area.v_weekendEnergyImport_MWh = sum(gcList, x -> x.v_weekendEnergyImport_MWh);
area.v_weekendEnergyExport_MWh = sum(gcList, x -> x.v_weekendEnergyExport_MWh);

area.v_weekendEnergyProduced_MWh = sum(gcList, x -> x.v_weekendEnergyProduced_MWh);
area.v_weekendEnergyConsumed_MWh = sum(gcList, x -> x.v_weekendEnergyConsumed_MWh);
area.v_weekendEnergySelfConsumed_MWh = sum(gcList, x -> x.v_weekendEnergySelfConsumed_MWh);

area.v_weekendElectricityProduced_MWh = sum(gcList, x -> x.v_weekendElectricityProduced_MWh);
area.v_weekendElectricityConsumed_MWh = sum(gcList, x -> x.v_weekendElectricityConsumed_MWh);
area.v_weekendElectricitySelfConsumed_MWh = sum(gcList, x -> x.v_weekendElectricitySelfConsumed_MWh);

/*ALCODEEND*/}

double f_updateLiveDataSets(AreaCollection area,ArrayList<GridConnection> gcList)
{/*ALCODESTART::1739364390459*/
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.noneOf(OL_EnergyCarriers.class);
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.noneOf(OL_EnergyCarriers.class);

for (GridConnection gc : gcList) {	
	activeConsumptionEnergyCarriers.addAll(gc.v_activeConsumptionEnergyCarriers);
	activeProductionEnergyCarriers.addAll(gc.v_activeProductionEnergyCarriers);
}

int liveWeekSize = gcList.get(0).data_gridCapacityDemand_kW.size();

//traceln("List of selected gridconnections " + gcList.toString());

//reset the datasets (is this required)?
if( area.v_dataElectricityDeliveryCapacityLiveWeek_kW != null ){
	// Demand
	area.dsm_liveConsumption_kW.createEmptyDataSets(energyModel.v_activeConsumptionEnergyCarriers, (int)(168 / energyModel.p_timeStep_h));

	area.v_dataElectricityDeliveryCapacityLiveWeek_kW.reset();		
	area.v_dataElectricityFeedInCapacityLiveWeek_kW.reset();
	area.v_dataNetLoadLiveWeek_kW.reset();
	
	area.v_dataElectricityBaseloadConsumptionLiveWeek_kW.reset();
	area.v_dataElectricityForHeatConsumptionLiveWeek_kW.reset();
	area.v_dataElectricityForTransportConsumptionLiveWeek_kW.reset();
	area.v_dataElectricityForStorageConsumptionLiveWeek_kW.reset();
	area.v_dataElectricityForHydrogenConsumptionLiveWeek_kW.reset();
	area.v_dataElectricityForCookingConsumptionLiveWeek_kW.reset();
	
	area.v_dataDistrictHeatConsumptionLiveWeek_kW.reset();
	
	// Supply
	area.dsm_liveProduction_kW.createEmptyDataSets(energyModel.v_activeProductionEnergyCarriers, (int)(168 / energyModel.p_timeStep_h));

	area.v_dataWindElectricityProductionLiveWeek_kW.reset();
	area.v_dataPVElectricityProductionLiveWeek_kW.reset();
	area.v_dataStorageElectricityProductionLiveWeek_kW.reset();
	area.v_dataV2GElectricityProductionLiveWeek_kW.reset();
	area.v_dataCHPElectricityProductionLiveWeek_kW.reset();
	
	//Other
	area.v_dataBatterySOCLiveWeek_.reset();
}
else {
	// Demand
	area.dsm_liveConsumption_kW.createEmptyDataSets(energyModel.v_activeConsumptionEnergyCarriers, (int)(168 / energyModel.p_timeStep_h));
	
	area.v_dataElectricityDeliveryCapacityLiveWeek_kW = new DataSet(672);
	area.v_dataElectricityFeedInCapacityLiveWeek_kW = new DataSet(672);
	area.v_dataNetLoadLiveWeek_kW = new DataSet(672);
	
	area.v_dataElectricityBaseloadConsumptionLiveWeek_kW = new DataSet(672);
	area.v_dataElectricityForHeatConsumptionLiveWeek_kW = new DataSet(672);
	area.v_dataElectricityForTransportConsumptionLiveWeek_kW = new DataSet(672);
	area.v_dataElectricityForStorageConsumptionLiveWeek_kW = new DataSet(672);
	area.v_dataElectricityForHydrogenConsumptionLiveWeek_kW = new DataSet(672);
	area.v_dataElectricityForCookingConsumptionLiveWeek_kW = new DataSet(672);
	area.v_dataDistrictHeatConsumptionLiveWeek_kW = new DataSet(672);
	
	// Supply
	area.dsm_liveProduction_kW.createEmptyDataSets(energyModel.v_activeProductionEnergyCarriers, (int)(168 / energyModel.p_timeStep_h));

	area.v_dataWindElectricityProductionLiveWeek_kW = new DataSet(672);
	area.v_dataPVElectricityProductionLiveWeek_kW = new DataSet(672);
	area.v_dataStorageElectricityProductionLiveWeek_kW = new DataSet(672);
	area.v_dataV2GElectricityProductionLiveWeek_kW = new DataSet(672);
	area.v_dataCHPElectricityProductionLiveWeek_kW = new DataSet(672);
	
	//Other
	area.v_dataBatterySOCLiveWeek_ = new DataSet(672);
}

for (int i=0; i < liveWeekSize; i++){ //we go back to update the existing live week data
	
	double timeAxisValue = gcList.get(0).data_gridCapacityDemand_kW.getX(i); // we get the X value from a random dataset 
	
	// Demand
	J_FlowsMap fm_demand_kW = new J_FlowsMap();
	
	double electricityDemandCapacityLiveWeek_kW = 0;
	double electricitySupplyCapacityLiveWeek_kW = 0;
	double netLoadLiveWeek_kW = 0;
	
	double baseloadElectricityDemandLiveWeek_kW = 0;
	double electricityForHeatDemandLiveWeek_kW = 0;
	double electricityForTransportDemandLiveWeek_kW = 0;
	double petroleumProductsDemandLiveWeek_kW = 0;
	double naturalGasDemandLiveWeek_kW = 0;
	double electricityForStorageDemandLiveWeek_kW = 0;
	double electricityForHydrogenDemandLiveWeek_kW = 0;
	double electricityForCookingConsumptionLiveWeek_kW = 0;
	
	double districtHeatingDemandLiveWeek_kW = 0;
	
	// Supply
	J_FlowsMap fm_supply_kW = new J_FlowsMap();

	double windElectricitySupplyLiveWeek_kW = 0;
	double PVElectricitySupplyLiveWeek_kW = 0;
	double storageElectricitySupplyLiveWeek_kW = 0;
	double V2GElectricitySupplyLiveWeek_kW = 0;
	double hydrogenSupplyLiveWeek_kW = 0;
	double CHPElectricitySupplyLiveWeek_kW = 0;
	
	//Other
	double batteryStoredEnergyLiveWeek_MWh = 0;
	
	for (GridConnection gc : gcList){
		for (OL_EnergyCarriers EC_consumption : gc.v_activeConsumptionEnergyCarriers) {
			fm_demand_kW.addFlow( EC_consumption, gc.dsm_liveDemand_kW.get(EC_consumption).getY(i));
		}
		for (OL_EnergyCarriers EC_production : gc.v_activeProductionEnergyCarriers) {
			fm_supply_kW.addFlow( EC_production, gc.dsm_liveSupply_kW.get(EC_production).getY(i));
		}
		
		electricityDemandCapacityLiveWeek_kW += gc.data_gridCapacityDemand_kW.getY(i);
		electricitySupplyCapacityLiveWeek_kW += gc.data_gridCapacitySupply_kW.getY(i);
		netLoadLiveWeek_kW  += gc.data_liveElectricityBalance_kW.getY(i);
	
		baseloadElectricityDemandLiveWeek_kW  += gc.data_baseloadElectricityDemand_kW.getY(i);
		electricityForHeatDemandLiveWeek_kW  += gc.data_heatPumpElectricityDemand_kW.getY(i);
		electricityForTransportDemandLiveWeek_kW += gc.data_electricVehicleDemand_kW.getY(i);
		electricityForStorageDemandLiveWeek_kW  += gc.data_batteryCharging_kW.getY(i);
		electricityForHydrogenDemandLiveWeek_kW  += gc.data_hydrogenElectricityDemand_kW.getY(i);
		electricityForCookingConsumptionLiveWeek_kW += gc.data_cookingElectricityDemand_kW.getY(i);
		districtHeatingDemandLiveWeek_kW += gc.data_districtHeatDelivery_kW.getY(i);
		
		// Supply
		windElectricitySupplyLiveWeek_kW  += gc.data_windGeneration_kW.getY(i);
		PVElectricitySupplyLiveWeek_kW  += gc.data_PVGeneration_kW.getY(i);
		storageElectricitySupplyLiveWeek_kW  += gc.data_batteryDischarging_kW.getY(i);
		V2GElectricitySupplyLiveWeek_kW  += gc.data_V2GSupply_kW.getY(i);
		CHPElectricitySupplyLiveWeek_kW  += gc.data_CHPElectricityProductionLiveWeek_kW.getY(i);
		
		//Other 
		batteryStoredEnergyLiveWeek_MWh += 	gc.data_batteryStoredEnergyLiveWeek_MWh.getY(i);
	}
	
	for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
		area.dsm_liveConsumption_kW.get(EC_consumption).add(timeAxisValue, fm_demand_kW.get(EC_consumption));
	}
	for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
		area.dsm_liveProduction_kW.get(EC_production).add(timeAxisValue, fm_supply_kW.get(EC_production));
	}
	
		
	area.v_dataElectricityDeliveryCapacityLiveWeek_kW.add(timeAxisValue, electricityDemandCapacityLiveWeek_kW);
	area.v_dataElectricityFeedInCapacityLiveWeek_kW.add(timeAxisValue, electricitySupplyCapacityLiveWeek_kW);
	area.v_dataNetLoadLiveWeek_kW.add(timeAxisValue, netLoadLiveWeek_kW);
	
	area.v_dataElectricityBaseloadConsumptionLiveWeek_kW.add(timeAxisValue, baseloadElectricityDemandLiveWeek_kW);
	area.v_dataElectricityForHeatConsumptionLiveWeek_kW.add(timeAxisValue, electricityForHeatDemandLiveWeek_kW);
	area.v_dataElectricityForTransportConsumptionLiveWeek_kW.add(timeAxisValue, electricityForTransportDemandLiveWeek_kW);
	area.v_dataElectricityForStorageConsumptionLiveWeek_kW.add(timeAxisValue, electricityForStorageDemandLiveWeek_kW);
	area.v_dataElectricityForHydrogenConsumptionLiveWeek_kW.add(timeAxisValue, electricityForHydrogenDemandLiveWeek_kW);
	area.v_dataElectricityForCookingConsumptionLiveWeek_kW.add(timeAxisValue, electricityForCookingConsumptionLiveWeek_kW);
	area.v_dataDistrictHeatConsumptionLiveWeek_kW.add(timeAxisValue, districtHeatingDemandLiveWeek_kW);
	
	// Supply
	area.v_dataWindElectricityProductionLiveWeek_kW.add(timeAxisValue, windElectricitySupplyLiveWeek_kW);
	area.v_dataPVElectricityProductionLiveWeek_kW.add(timeAxisValue, PVElectricitySupplyLiveWeek_kW);
	area.v_dataStorageElectricityProductionLiveWeek_kW.add(timeAxisValue, storageElectricitySupplyLiveWeek_kW);
	area.v_dataV2GElectricityProductionLiveWeek_kW.add(timeAxisValue, V2GElectricitySupplyLiveWeek_kW);
	area.v_dataCHPElectricityProductionLiveWeek_kW.add(timeAxisValue, CHPElectricitySupplyLiveWeek_kW);
	
	//Other
	double SOC = sum(gcList, GC -> GC.v_totalInstalledBatteryStorageCapacity_MWh) > 0 ? batteryStoredEnergyLiveWeek_MWh/sum(gcList, GC -> GC.v_totalInstalledBatteryStorageCapacity_MWh) : 0;
	area.v_dataBatterySOCLiveWeek_.add(timeAxisValue, SOC); 	
}

/*ALCODEEND*/}

double f_addTimeStepLiveDataSetsGC(AreaCollection area,ArrayList<GridConnection> gcList)
{/*ALCODESTART::1739364390461*/

EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.noneOf(OL_EnergyCarriers.class);
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.noneOf(OL_EnergyCarriers.class);

for (GridConnection gc : gcList) {	
	activeConsumptionEnergyCarriers.addAll(gc.v_activeConsumptionEnergyCarriers);
	activeProductionEnergyCarriers.addAll(gc.v_activeProductionEnergyCarriers);
}


int i = max(0, gcList.get(0).data_gridCapacityDemand_kW.size() - 1);
	
double timeAxisValue = gcList.get(0).data_gridCapacityDemand_kW.getX(i); // we get the X value from a random dataset 

// Demand
J_FlowsMap fm_demand_kW = new J_FlowsMap();
double electricityDemandCapacityLiveWeek_kW = 0;
double electricitySupplyCapacityLiveWeek_kW = 0;
double netLoadLiveWeek_kW = 0;

double baseloadElectricityDemandLiveWeek_kW = 0;
double electricityForHeatDemandLiveWeek_kW = 0;
double electricityForTransportDemandLiveWeek_kW = 0;
double electricityForStorageDemandLiveWeek_kW = 0;
double electricityForHydrogenDemandLiveWeek_kW = 0;
double electricityForCookingDemandLiveWeek_kW = 0;
double districtHeatingDemandLiveWeek_kW = 0;

// Supply
J_FlowsMap fm_supply_kW = new J_FlowsMap();
double windElectricitySupplyLiveWeek_kW = 0;
double PVElectricitySupplyLiveWeek_kW = 0;
double storageElectricitySupplyLiveWeek_kW = 0;
double V2GElectricitySupplyLiveWeek_kW = 0;
double CHPElectricitySupplyLiveWeek_kW = 0;

//Other
double batteryStoredEnergyLiveWeek_MWh = 0;

for (GridConnection gc : gcList){
	
	for (OL_EnergyCarriers EC_consumption : gc.v_activeConsumptionEnergyCarriers) {
		fm_demand_kW.addFlow( EC_consumption, gc.dsm_liveDemand_kW.get(EC_consumption).getY(i));
	}
	for (OL_EnergyCarriers EC_production : gc.v_activeProductionEnergyCarriers) {
		fm_supply_kW.addFlow( EC_production, gc.dsm_liveSupply_kW.get(EC_production).getY(i));
	}
	
	// Demand
	electricityDemandCapacityLiveWeek_kW += gc.data_gridCapacityDemand_kW.getY(i);
	electricitySupplyCapacityLiveWeek_kW += gc.data_gridCapacitySupply_kW.getY(i);
	netLoadLiveWeek_kW  += gc.data_liveElectricityBalance_kW.getY(i);

	baseloadElectricityDemandLiveWeek_kW  += gc.data_baseloadElectricityDemand_kW.getY(i);
	electricityForHeatDemandLiveWeek_kW  += gc.data_heatPumpElectricityDemand_kW.getY(i);
	electricityForTransportDemandLiveWeek_kW += gc.data_electricVehicleDemand_kW.getY(i);
	electricityForStorageDemandLiveWeek_kW  += gc.data_batteryCharging_kW.getY(i);
	electricityForHydrogenDemandLiveWeek_kW  += gc.data_hydrogenElectricityDemand_kW.getY(i);
	electricityForCookingDemandLiveWeek_kW += gc.data_cookingElectricityDemand_kW.getY(i);
	districtHeatingDemandLiveWeek_kW += gc.data_districtHeatDelivery_kW.getY(i);
	
	
	// Supply
	windElectricitySupplyLiveWeek_kW  += gc.data_windGeneration_kW.getY(i);
	PVElectricitySupplyLiveWeek_kW  += gc.data_PVGeneration_kW.getY(i);
	storageElectricitySupplyLiveWeek_kW  += gc.data_batteryDischarging_kW.getY(i);
	V2GElectricitySupplyLiveWeek_kW  += gc.data_V2GSupply_kW.getY(i);
	CHPElectricitySupplyLiveWeek_kW  += gc.data_CHPElectricityProductionLiveWeek_kW.getY(i);
	
	//Other 
	batteryStoredEnergyLiveWeek_MWh += 	gc.data_batteryStoredEnergyLiveWeek_MWh.getY(i);	
}

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {	
	area.dsm_liveConsumption_kW.get(EC_consumption).add(timeAxisValue, fm_demand_kW.get(EC_consumption));	
}

for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {	
	area.dsm_liveProduction_kW.get(EC_production).add(timeAxisValue, fm_supply_kW.get(EC_production));	
}


area.v_dataElectricityDeliveryCapacityLiveWeek_kW.add(timeAxisValue, electricityDemandCapacityLiveWeek_kW);
area.v_dataElectricityFeedInCapacityLiveWeek_kW.add(timeAxisValue, electricitySupplyCapacityLiveWeek_kW);
area.v_dataNetLoadLiveWeek_kW.add(timeAxisValue, netLoadLiveWeek_kW);

area.v_dataElectricityBaseloadConsumptionLiveWeek_kW.add(timeAxisValue, baseloadElectricityDemandLiveWeek_kW);
area.v_dataElectricityForHeatConsumptionLiveWeek_kW.add(timeAxisValue, electricityForHeatDemandLiveWeek_kW);
area.v_dataElectricityForTransportConsumptionLiveWeek_kW.add(timeAxisValue, electricityForTransportDemandLiveWeek_kW);
area.v_dataElectricityForStorageConsumptionLiveWeek_kW.add(timeAxisValue, electricityForStorageDemandLiveWeek_kW);
area.v_dataElectricityForHydrogenConsumptionLiveWeek_kW.add(timeAxisValue, electricityForHydrogenDemandLiveWeek_kW);
area.v_dataElectricityForCookingConsumptionLiveWeek_kW.add(timeAxisValue, electricityForCookingDemandLiveWeek_kW);
area.v_dataDistrictHeatConsumptionLiveWeek_kW.add(timeAxisValue, districtHeatingDemandLiveWeek_kW);

// Supply
area.v_dataWindElectricityProductionLiveWeek_kW.add(timeAxisValue, windElectricitySupplyLiveWeek_kW);
area.v_dataPVElectricityProductionLiveWeek_kW.add(timeAxisValue, PVElectricitySupplyLiveWeek_kW);
area.v_dataStorageElectricityProductionLiveWeek_kW.add(timeAxisValue, storageElectricitySupplyLiveWeek_kW);
area.v_dataV2GElectricityProductionLiveWeek_kW.add(timeAxisValue, V2GElectricitySupplyLiveWeek_kW);
area.v_dataCHPElectricityProductionLiveWeek_kW.add(timeAxisValue, CHPElectricitySupplyLiveWeek_kW);
		

//Other
double SOC = sum(gcList, GC -> GC.v_totalInstalledBatteryStorageCapacity_MWh) > 0 ? batteryStoredEnergyLiveWeek_MWh/sum(gcList, GC -> GC.v_totalInstalledBatteryStorageCapacity_MWh) : 0;
area.v_dataBatterySOCLiveWeek_.add(timeAxisValue, SOC); 


/*ALCODEEND*/}

double f_updateActiveAssetBooleansGC(AreaCollection area,ArrayList<GridConnection> selectedGridConnections)
{/*ALCODESTART::1739364390464*/
area.b_hasElectricHeating = false;
area.b_hasElectricTransport = false;
area.b_hasPV = false;
area.b_hasWindturbine = false;
area.b_hasBattery = false;
area.b_hasHeatGridConnection = false;
area.b_hasElectrolyser = false;
area.b_hasCHP = false;
area.b_hasV2G = false;
area.b_hasElectricCooking = false;
area.v_batteryStorageCapacityInstalled_MWh = 0;

//Electric heating
for(GridConnection GC : selectedGridConnections){
	if(GC.c_electricHeatpumpAssets.size()>0 && GC.v_isActive){
		area.b_hasElectricHeating = true;
		break;
	}
}
//Electric vehicles
for(GridConnection GC : selectedGridConnections){
	if(GC.c_EvAssets.size()>0 && GC.v_isActive){
		area.b_hasElectricTransport = true;
		break;
	}
}
//PV
for(GridConnection GC : selectedGridConnections){
	if(GC.c_pvAssets.size()>0 && GC.v_isActive){
		area.b_hasPV = true;
		break;
	}
}
//Wind
for(GridConnection GC : selectedGridConnections){
	if(GC.c_windAssets.size()>0 && GC.v_isActive){
		area.b_hasWindturbine = true;
		break;
	}
}
//Battery
for(GridConnection GC : selectedGridConnections){
	if(GC.c_batteryAssets.size()>0 && GC.v_isActive){
		for(J_EA battery : GC.c_batteryAssets){
			if(((J_EAStorageElectric)battery).getStorageCapacity_kWh() > 0){
				area.b_hasBattery = true;
				area.v_batteryStorageCapacityInstalled_MWh += ((J_EAStorageElectric)battery).getStorageCapacity_kWh()/1000;
			}
		}
	}
}
//Heat grid
for(GridConnection GC : selectedGridConnections){
	if(GC.l_parentNodeHeat.getConnectedAgent() != null && GC.v_isActive){
		area.b_hasHeatGridConnection = true;
		break;
	}
}
//Electrolyser
for(GridConnection GC : selectedGridConnections){
	if(GC.c_electrolyserAssets.size()>0 && GC.v_isActive){
		area.b_hasElectrolyser = true;
		break;
	}
}
//CHP
for(GridConnection GC : selectedGridConnections){
	if(GC.c_chpAssets.size()>0 && GC.v_isActive){
		area.b_hasCHP = true;
		break;
	}
}
//V2g
for(GridConnection GC : selectedGridConnections){
	if(GC.p_chargingAttitudeVehicles == OL_ChargingAttitude.V2G && GC.c_EvAssets.size()>0 && GC.v_isActive){
		area.b_hasV2G = true;
		break;
	}
}
//Electric cooking
for(GridConnection GC : selectedGridConnections){
	if(GC.c_electricHobAssets.size()>0 && GC.v_isActive){
		area.b_hasElectricCooking = true;
		break;
	}
}

/*ALCODEEND*/}

double f_updateActiveAssetBooleans(boolean multiSelect,ArrayList<GridConnection> selectedGridConnections)
{/*ALCODESTART::1739364390466*/
f_updateActiveAssetBooleansGC(v_area, energyModel.f_getGridConnections());
if(multiSelect && selectedGridConnections.size() > 1){
	for (int i = 0; i < selectedGridConnections.size(); i++) {
		f_updateActiveAssetBooleansGC(c_individualGridConnections.get(i), new ArrayList<GridConnection>(selectedGridConnections.subList(i, i+1)));
	}
}
else{
	f_updateActiveAssetBooleansGC(v_gridConnection, selectedGridConnections);
}
f_showCorrectChart();
/*ALCODEEND*/}

double f_EHubCapacityDataSets(ArrayList<GridConnection> selectedGridConnections)
{/*ALCODESTART::1739364390468*/
if (selectedGridConnections.size() > 0) {
	
	v_dataEHubDeliveryCapacityLiveWeek_kW = new DataSet((int) (168 / energyModel.p_timeStep_h));
	v_dataEHubFeedInCapacityLiveWeek_kW = new DataSet((int) (168 / energyModel.p_timeStep_h));
	
	int liveWeekSize = selectedGridConnections.get(0).data_gridCapacityDemand_kW.size();
	
	for (int i=0; i < liveWeekSize; i++){ //we go back to update the existing live week data
		double timeAxisValue = selectedGridConnections.get(0).data_gridCapacityDemand_kW.getX(i); // we get the X value from a random dataset
		v_dataEHubDeliveryCapacityLiveWeek_kW.add(timeAxisValue, v_groupATODeliveryCapacity_kW);
		v_dataEHubFeedInCapacityLiveWeek_kW.add(timeAxisValue, -v_groupATOFeedInCapacity_kW);
	}
	
	// Year
	v_dataEHubDeliveryCapacityYear_kW = new DataSet(2);
	v_dataEHubDeliveryCapacityYear_kW.add(energyModel.p_runStartTime_h, v_groupATODeliveryCapacity_kW);
	v_dataEHubDeliveryCapacityYear_kW.add(energyModel.p_runEndTime_h - energyModel.p_runStartTime_h, v_groupATODeliveryCapacity_kW);
	
	v_dataEHubFeedInCapacityYear_kW = new DataSet(2);
	v_dataEHubFeedInCapacityYear_kW.add(energyModel.p_runStartTime_h, -v_groupATOFeedInCapacity_kW);
	v_dataEHubFeedInCapacityYear_kW.add(energyModel.p_runEndTime_h - energyModel.p_runStartTime_h, -v_groupATOFeedInCapacity_kW);
	
	// SummerWeek
	v_dataEHubDeliveryCapacitySummerWeek_kW = new DataSet((int) (168 / energyModel.p_timeStep_h));
	v_dataEHubDeliveryCapacitySummerWeek_kW.add(energyModel.p_startHourSummerWeek, v_groupATODeliveryCapacity_kW);
	v_dataEHubDeliveryCapacitySummerWeek_kW.add(energyModel.p_startHourSummerWeek + 168, v_groupATODeliveryCapacity_kW);
	
	v_dataEHubFeedInCapacitySummerWeek_kW = new DataSet((int) (168 / energyModel.p_timeStep_h));
	v_dataEHubFeedInCapacitySummerWeek_kW.add(energyModel.p_startHourSummerWeek, -v_groupATOFeedInCapacity_kW);
	v_dataEHubFeedInCapacitySummerWeek_kW.add(energyModel.p_startHourSummerWeek + 168, -v_groupATOFeedInCapacity_kW);
	
	// WinterWeek
	v_dataEHubDeliveryCapacityWinterWeek_kW = new DataSet((int) (168 / energyModel.p_timeStep_h));
	v_dataEHubDeliveryCapacityWinterWeek_kW.add(energyModel.p_startHourWinterWeek, v_groupATODeliveryCapacity_kW);
	v_dataEHubDeliveryCapacityWinterWeek_kW.add(energyModel.p_startHourWinterWeek + 168, v_groupATODeliveryCapacity_kW);
	
	v_dataEHubFeedInCapacityWinterWeek_kW = new DataSet((int) (168 / energyModel.p_timeStep_h));
	v_dataEHubFeedInCapacityWinterWeek_kW.add(energyModel.p_startHourWinterWeek, -v_groupATOFeedInCapacity_kW);
	v_dataEHubFeedInCapacityWinterWeek_kW.add(energyModel.p_startHourWinterWeek + 168, -v_groupATOFeedInCapacity_kW);
	
}

/*ALCODEEND*/}

double f_updateLiveEHubCapacityDataSets(ArrayList<GridConnection> selectedGridConnections)
{/*ALCODESTART::1739364390470*/
int i = max(0, selectedGridConnections.get(0).data_gridCapacityDemand_kW.size() - 1);	
double timeAxisValue = selectedGridConnections.get(0).data_gridCapacityDemand_kW.getX(i); // we get the X value from a random dataset 

v_dataEHubDeliveryCapacityLiveWeek_kW.add(timeAxisValue, v_groupATODeliveryCapacity_kW);
v_dataEHubFeedInCapacityLiveWeek_kW.add(timeAxisValue, -v_groupATOFeedInCapacity_kW);

/*ALCODEEND*/}

double f_fillAreaCollectionsOfIndividualGCs(ArrayList<GridConnection> selectedGridConnections)
{/*ALCODESTART::1739364390473*/
if (selectedGridConnections.size() >= 1) {
	c_individualGridConnections = new ArrayList<AreaCollection>();
	for (int i = 0; i < selectedGridConnections.size(); i++) {
		AreaCollection AC = new AreaCollection();
		AC.f_initializeMaps();
		c_individualGridConnections.add(AC);
		c_individualGridConnections.get(i).p_name = selectedGridConnections.get(i).p_ownerID;
		f_updateUIresultsGridConnection(c_individualGridConnections.get(i), new ArrayList<GridConnection>(selectedGridConnections.subList(i, i+1)));
	}
	
	//f_updateTotalLiveDataSets();
	//f_updateTotalYearlyDataSets();
	//f_updateTotalWeeklyDataSets();
}
/*ALCODEEND*/}

double f_addTimeStepLiveDataSetsMain(AreaCollection area)
{/*ALCODESTART::1739364390477*/

//Update SOC live plot
double batteryStoredEnergyLiveWeek_MWh = 0;
int i = max(0, energyModel.data_batteryStoredEnergy_MWh.size() - 1);
batteryStoredEnergyLiveWeek_MWh = 	energyModel.data_batteryStoredEnergy_MWh.getY(i);	
double timeAxisValue = energyModel.data_batteryStoredEnergy_MWh.getX(i);
double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? batteryStoredEnergyLiveWeek_MWh / area.v_batteryStorageCapacityInstalled_MWh : 0;
area.v_dataBatterySOCLiveWeek_.add(timeAxisValue, SOC); 

/*ALCODEEND*/}

double f_updateCollectiveSelfConsumption(AreaCollection area,ArrayList<GridConnection> gcList)
{/*ALCODESTART::1739364390479*/
double totalElectricityExport_MWh = 0;
double totalElectricityImport_MWh = 0;
for(double netLoad_kW : area.v_dataNetLoadYear_kW){
	if(netLoad_kW < 0){
		totalElectricityExport_MWh += -1*netLoad_kW*energyModel.p_timeStep_h/1000; 
	}
	if(netLoad_kW > 0){
		totalElectricityImport_MWh += netLoad_kW*energyModel.p_timeStep_h/1000; 
	}
} 

area.fm_totalExports_MWh.put( OL_EnergyCarriers.ELECTRICITY, totalElectricityExport_MWh);
area.fm_totalImports_MWh.put( OL_EnergyCarriers.ELECTRICITY, totalElectricityImport_MWh);
area.v_totalElectricitySelfConsumed_MWh = (area.v_totalElectricityProduced_MWh-totalElectricityExport_MWh);


//QUICK FIX (Cause cumulative energy import/export calculation is currently wrong!!).
double totalEnergySelfConsumed_MWh = area.v_totalElectricitySelfConsumed_MWh;
for (GridConnection GC : gcList) {
	totalEnergySelfConsumed_MWh += GC.v_currentPrimaryEnergyProductionHeatpumps_kW;
}

area.v_totalEnergySelfConsumed_MWh = totalEnergySelfConsumed_MWh;
area.v_totalEnergyExport_MWh = area.fm_totalExports_MWh.get( OL_EnergyCarriers.ELECTRICITY);

//IN progress!!!
//Energy
/*
double totalEnergySelfConsumed = area.v_totalElectricitySelfConsumed_MWh; // Initialize with selfconsumption of EC

int arraySize = roundToInt((energyModel.p_runEndTime_h-energyModel.p_runStartTime_h)/energyModel.p_timeStep_h);
for(OL_EnergyCarriers EC : area.v_activeProductionEnergyCarriers){
	if(EC != OL_EnergyCarriers.ELECTRICITY && area.v_activeConsumptionEnergyCarriers.contains(EC)){
		double[] dataBalanceYearEC_kW = new double[arraySize];
		for (GridConnection gc : gcList) {
			if(gc.v_activeEnergyCarriers.contains(EC)){
				double[] balanceTimeSeries_kW = gc.am_totalBalanceAccumulators_kW.get(EC).getTimeSeries();
				for (int i = 0; i<arraySize; i++) {
					 dataBalanceYearEC_kW[i] += balanceTimeSeries_kW[i];
				}
			}
		}
		double totalECExport_MWh = 0;
		for(double balanceEC_kW : dataBalanceYearEC_kW){
			if(balanceEC_kW < 0){
				totalECExport_MWh = -1*balanceEC_kW*energyModel.p_timeStep_h/1000; 
			}
		}
		double ECProduced = 1;
		double totalECSelfConsumed_MWh = 0; //(ECProduced - totalECExport_MWh)/ ECProduced;
		totalEnergySelfConsumed += totalECSelfConsumed_MWh;
	}
}

area.v_totalEnergySelfConsumed_MWh = totalEnergySelfConsumed;
*/
/*ALCODEEND*/}

double f_initializeMainAreaCollection()
{/*ALCODESTART::1739372586164*/
//Initialize main area collection
AreaCollection mainArea = add_pop_areaResults(OL_GISObjectType.REGION, "Main");
v_area = mainArea;

//Set active energyCarriers
mainArea.v_activeProductionEnergyCarriers = energyModel.v_activeProductionEnergyCarriers;
mainArea.v_activeConsumptionEnergyCarriers = energyModel.v_activeConsumptionEnergyCarriers;


//Set booleans
f_updateActiveAssetBooleansGC(mainArea, energyModel.f_getGridConnections());

//Initialize certain datasets
mainArea.v_dataBatterySOCLiveWeek_ = new DataSet(672);
mainArea.v_dataBatterySOCSummerWeek_ = new DataSet(672);
mainArea.v_dataBatterySOCWinterWeek_ = new DataSet(672);
mainArea.v_dataBatterySOCYear_ = new DataSet(365);
mainArea.v_dataElectricityDeliveryCapacitySummerWeek_kW = new DataSet((int) (168 / energyModel.p_timeStep_h));
mainArea.v_dataElectricityFeedInCapacitySummerWeek_kW = new DataSet((int) (168 / energyModel.p_timeStep_h));
mainArea.v_dataElectricityDeliveryCapacityWinterWeek_kW = new DataSet((int) (168 / energyModel.p_timeStep_h));
mainArea.v_dataElectricityFeedInCapacityWinterWeek_kW = new DataSet((int) (168 / energyModel.p_timeStep_h));

//Initialize the values
f_updateUIresultsMainArea();

/*ALCODEEND*/}

double f_setNonLivePlotRadioButtons(boolean active)
{/*ALCODESTART::1739884154258*/
rb_DEFAULT.setEnabled(active);
chartProfielen.radio_period.setEnabled(active);
chartNetbelasting.radio.setEnabled(active);
chartBalans.radio_period.setEnabled(active);

/*ALCODEEND*/}

