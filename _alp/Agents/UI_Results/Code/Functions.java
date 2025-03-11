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
		chartGespreksleidraadBedrijven.f_setGespreksleidraadBedrijvenCharts();
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
chartGespreksleidraadBedrijven.f_setGespreksleidraadBedrijvenCharts();

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
chartGespreksleidraadBedrijven.f_styleBackground(backgroundColor, lineColor, lineWidth, lineStyle);
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

I_EnergyData f_getSelectedObjectData()
{/*ALCODESTART::1727083916594*/
I_EnergyData objectInterface;

if (v_selectedObjectScope == OL_ResultScope.GRIDCONNECTION){
	objectInterface = v_selectedObjectInterface;
}
else if(v_selectedObjectScope == OL_ResultScope.ENERGYCOOP){
		objectInterface = v_selectedObjectInterface;
}
else {
	objectInterface = v_selectedObjectInterface;
}

return objectInterface;
/*ALCODEEND*/}

double f_initializePreviousTotalsGC()
{/*ALCODESTART::1727103171052*/
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
rb_DEFAULT_AND_GESPREKSLEIDRAADBEDRIJVEN.setVisible(false);
rb_DEFAULT_AND_BATTERY.setVisible(false);
rb_DEFAULT.setEnabled(false);
rb_DEFAULT_AND_GESPREKSLEIDRAAD.setEnabled(false);
rb_DEFAULT_AND_GESPREKSLEIDRAADBEDRIJVEN.setEnabled(false);
rb_DEFAULT_AND_BATTERY.setEnabled(false);

switch(v_selectedRadioButtonSetup){

case DEFAULT:
	v_selectedRadioButton = rb_DEFAULT;
	break;
case DEFAULT_AND_GESPREKSLEIDRAAD:
	v_selectedRadioButton = rb_DEFAULT_AND_GESPREKSLEIDRAAD;
	break;
case DEFAULT_AND_GESPREKSLEIDRAADBEDRIJVEN:
	v_selectedRadioButton = rb_DEFAULT_AND_GESPREKSLEIDRAADBEDRIJVEN;
	break;
case DEFAULT_AND_BATTERY:	
	v_selectedRadioButton = rb_DEFAULT_AND_BATTERY;
	break;
case OFF:
	f_setResultsUIHeader(null, null, false);
	break;
}

v_selectedRadioButton.setVisible(true);
v_selectedRadioButton.setEnabled(true);
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
//Set the selected radiobutton setup
f_setSelectedRadioButton();

//Initialize profiles graph (starting chart)
chartProfielen.f_setCharts();

/*ALCODEEND*/}

double f_updateUIresultsMainArea()
{/*ALCODESTART::1739364390437*/
AreaCollection area = v_area;
area.v_engineAgent = energyModel;

/*
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
*/

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

////Gespreksleidraad Additions
if(v_selectedRadioButtonSetup == OL_RadioButtonSetup.DEFAULT_AND_GESPREKSLEIDRAAD){
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

double f_updateUIresultsGridNode(GridNode GN)
{/*ALCODESTART::1739364390441*/
v_selectedObjectScope = OL_ResultScope.GRIDNODE;
v_gridNode = GN;

f_showCorrectChart();

/*
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
*/


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

double f_updateActiveAssetBooleans(ArrayList<GridConnection> selectedGridConnections)
{/*ALCODESTART::1739364390466*/
//Update main area
f_updateActiveAssetBooleansGC(v_area, energyModel.f_getGridConnections());

//Update coop
if(energyModel.pop_energyCoops.size()>0){
	f_updateActiveAssetBooleansGC(v_energyCoop, energyModel.pop_energyCoops.get(energyModel.pop_energyCoops.size()-1).f_getAllChildMemberGridConnections());
}	

//Update grid connection area collections
f_updateActiveAssetBooleansGC(v_gridConnection, selectedGridConnections);


//Show correct chart
f_showCorrectChart();
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

double f_enableNonLivePlotRadioButtons(boolean active)
{/*ALCODESTART::1739884154258*/
v_selectedRadioButton.setEnabled(active);
chartProfielen.radio_period.setEnabled(active);
chartNetbelasting.radio.setEnabled(active);
chartBalans.radio_period.setEnabled(active);

/*ALCODEEND*/}

double f_updateUIresultsEnergyCoop(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1740056800747*/
//Set engine object
area.v_engineAgent = EC;

//Set active energyCarriers
area.v_activeProductionEnergyCarriers = EC.v_activeProductionEnergyCarriers;
area.v_activeConsumptionEnergyCarriers = EC.v_activeConsumptionEnergyCarriers;

//Update active asset booleans
f_updateActiveAssetBooleansGC(area, EC.f_getAllChildMemberGridConnections());

/*ALCODEEND*/}

double f_updateVariablesOfEnergyCoop(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1740067768680*/
//Grid capacity
area.v_gridCapacityDelivery_kW = EC.p_contractedDeliveryCapacity_kW;
area.v_gridCapacityFeedIn_kW = EC.p_contractedFeedinCapacity_kW;

area.v_gridCapacityDelivery_groupcontract_kW = EC.f_getGroupContractDeliveryCapacity_kW();
area.v_gridCapacityFeedin_groupcontract_kW = EC.f_getGroupContractFeedinCapacity_kW();

area.b_isRealDeliveryCapacityAvailable = EC.b_isRealDeliveryCapacityAvailable;
area.b_isRealFeedinCapacityAvailable = EC.b_isRealFeedinCapacityAvailable;

//Installed Asset variables
area.v_batteryStorageCapacityInstalled_MWh += EC.v_totalInstalledBatteryStorageCapacity_MWh;


// KPIs for 'collectief vs individueel' 
area.v_individualSelfconsumptionElectricity_fr = EC.v_cumulativeIndividualSelfconsumptionElectricity_fr;
area.v_individualSelfSufficiencyElectricity_fr = EC.v_cumulativeIndividualSelfSufficiencyElectricity_fr;

area.v_individualSelfconsumptionEnergy_fr = EC.v_cumulativeIndividualSelfconsumptionEnergy_fr;
area.v_individualSelfSufficiencyEnergy_fr = EC.v_cumulativeIndividualSelfSufficiencyEnergy_fr;

area.v_individualPeakDelivery_kW = EC.v_cumulativeIndividualPeakDelivery_kW;
area.v_individualPeakFeedin_kW = EC.v_cumulativeIndividualPeakFeedin_kW;

//Yearly
area.fm_totalImports_MWh.clear();
area.fm_totalExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_totalExports_MWh.put( energyCarrier, EC.fm_totalExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_totalImports_MWh.put( energyCarrier, EC.fm_totalImports_MWh.get(energyCarrier) );
}

area.v_totalEnergyImport_MWh = EC.v_totalEnergyImport_MWh;
area.v_totalEnergyExport_MWh = EC.v_totalEnergyExport_MWh;

area.v_totalEnergyProduced_MWh = EC.v_totalEnergyProduced_MWh;
area.v_totalEnergyConsumed_MWh = EC.v_totalEnergyConsumed_MWh;
area.v_totalEnergySelfConsumed_MWh = EC.v_totalEnergySelfConsumed_MWh;

area.v_totalElectricityProduced_MWh = EC.v_totalElectricityProduced_MWh;
area.v_totalElectricityConsumed_MWh = EC.v_totalElectricityConsumed_MWh;
area.v_totalElectricitySelfConsumed_MWh = EC.v_totalElectricitySelfConsumed_MWh;

//Overload duration (for multiple GC this does not really make sense right?, would be more interesting to see the influence of their combined contract capacity)
area.v_annualOverloadDurationDelivery_hr = EC.v_totalOverloadDurationDelivery_hr;
area.v_annualOverloadDurationFeedin_hr = EC.v_totalOverloadDurationFeedin_hr;


// Summer/winter
area.fm_summerWeekImports_MWh.clear();
area.fm_summerWeekExports_MWh.clear();
area.fm_winterWeekImports_MWh.clear();
area.fm_winterWeekExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_summerWeekExports_MWh.put( energyCarrier, EC.fm_summerWeekExports_MWh.get(energyCarrier) );
	area.fm_winterWeekExports_MWh.put( energyCarrier, EC.fm_winterWeekExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_summerWeekImports_MWh.put( energyCarrier, EC.fm_summerWeekImports_MWh.get(energyCarrier) );
	area.fm_winterWeekImports_MWh.put( energyCarrier, EC.fm_winterWeekImports_MWh.get(energyCarrier) );
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
area.fm_daytimeImports_MWh.clear();
area.fm_daytimeExports_MWh.clear();
area.fm_nighttimeImports_MWh.clear();
area.fm_nighttimeExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_daytimeExports_MWh.put( energyCarrier, EC.fm_daytimeExports_MWh.get(energyCarrier) );
	area.fm_nighttimeExports_MWh.put( energyCarrier, EC.fm_nighttimeExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_daytimeImports_MWh.put( energyCarrier, EC.fm_daytimeImports_MWh.get(energyCarrier) );
	area.fm_nighttimeImports_MWh.put( energyCarrier, EC.fm_nighttimeImports_MWh.get(energyCarrier) );
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
area.fm_weekdayImports_MWh.clear();
area.fm_weekdayExports_MWh.clear();
area.fm_weekendImports_MWh.clear();
area.fm_weekendExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_weekdayExports_MWh.put( energyCarrier, EC.fm_weekdayExports_MWh.get(energyCarrier) );
	area.fm_weekendExports_MWh.put( energyCarrier, EC.fm_weekendExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_weekdayImports_MWh.put( energyCarrier, EC.fm_weekdayImports_MWh.get(energyCarrier) );
	area.fm_weekendImports_MWh.put( energyCarrier, EC.fm_weekendImports_MWh.get(energyCarrier) );
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

double f_updateLiveDatasetsEnergyCoop(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1740067795909*/
//Datasets for live charts
//Demand
area.dsm_liveConsumption_kW = EC.dsm_liveDemand_kW;
area.v_dataElectricityBaseloadConsumptionLiveWeek_kW = EC.data_baseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionLiveWeek_kW = EC.data_heatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionLiveWeek_kW = EC.data_electricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionLiveWeek_kW = EC.data_batteryCharging_kW;
area.v_dataElectricityForHydrogenConsumptionLiveWeek_kW = EC.data_hydrogenElectricityDemand_kW;
area.v_dataElectricityForCookingConsumptionLiveWeek_kW = EC.data_cookingElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionLiveWeek_kW = EC.data_districtHeatDelivery_kW;

//Supply
area.dsm_liveProduction_kW = EC.dsm_liveSupply_kW;
area.v_dataWindElectricityProductionLiveWeek_kW = EC.data_windGeneration_kW;
area.v_dataPVElectricityProductionLiveWeek_kW = EC.data_PVGeneration_kW;
area.v_dataStorageElectricityProductionLiveWeek_kW = EC.data_batteryDischarging_kW;
area.v_dataV2GElectricityProductionLiveWeek_kW = EC.data_V2GSupply_kW;
area.v_dataCHPElectricityProductionLiveWeek_kW = EC.data_CHPElectricityProductionLiveWeek_kW;

//SOC
area.v_dataBatterySOCLiveWeek_.reset();
for (int i = 0; i < EC.data_batteryStoredEnergyLiveWeek_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = EC.data_batteryStoredEnergyLiveWeek_MWh.getX(i);
    double y = EC.data_batteryStoredEnergyLiveWeek_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCLiveWeek_.add(x, SOC);
}

//Total
area.v_dataNetLoadLiveWeek_kW = EC.data_liveElectricityBalance_kW;
area.v_batteryStorageCapacityInstalled_MWh = EC.v_totalInstalledBatteryStorageCapacity_MWh;

//Capacity
area.v_dataElectricityDeliveryCapacityLiveWeek_kW = EC.data_gridCapacityDemand_kW;
area.v_dataElectricityFeedInCapacityLiveWeek_kW = EC.data_gridCapacitySupply_kW;

/*ALCODEEND*/}

double f_updateWeeklyDatasetsEnergyCoop(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1740067796943*/
//Datasets for live summerWeek chart
//Demand
area.dsm_summerWeekConsumptionDataSets_kW = EC.dsm_summerWeekDemandDataSets_kW;
area.v_dataElectricityBaseloadConsumptionSummerWeek_kW = EC.data_summerWeekBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionSummerWeek_kW = EC.data_summerWeekHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionSummerWeek_kW = EC.data_summerWeekElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionSummerWeek_kW = EC.data_summerWeekBatteriesDemand_kW;
area.v_dataElectricityForCookingConsumptionSummerWeek_kW = EC.data_summerWeekCookingElectricityDemand_kW;
area.v_dataElectricityForHydrogenConsumptionSummerWeek_kW = EC.data_summerWeekElectrolyserElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionSummerWeek_kW = EC.data_summerWeekDistrictHeatingDemand_kW;

//Supply
area.dsm_summerWeekProductionDataSets_kW = EC.dsm_summerWeekSupplyDataSets_kW;
area.v_dataElectricityWindProductionSummerWeek_kW = EC.data_summerWeekWindGeneration_kW;
area.v_dataElectricityPVProductionSummerWeek_kW = EC.data_summerWeekPVGeneration_kW;
area.v_dataElectricityStorageProductionSummerWeek_kW = EC.data_summerWeekBatteriesSupply_kW;
area.v_dataElectricityV2GProductionSummerWeek_kW = EC.data_summerWeekV2GSupply_kW;
area.v_dataElectricityCHPProductionSummerWeek_kW = EC.data_summerWeekCHPElectricityProduction_kW;

//Net load
area.v_dataNetLoadSummerWeek_kW = EC.am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(energyModel.p_startHourSummerWeek);
//EC.data_summerWeekNetLoad_kW;


//SOC (summerweek)
area.v_dataBatterySOCSummerWeek_.reset();
for (int i = 0; i < EC.data_summerWeekBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = EC.data_summerWeekBatteryStoredEnergy_MWh.getX(i);
    double y = EC.data_summerWeekBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCSummerWeek_.add(x, SOC);
}

//Datasets for live winterWeek chart
//Demand
area.dsm_winterWeekConsumptionDataSets_kW = EC.dsm_winterWeekDemandDataSets_kW;
area.v_dataElectricityBaseloadConsumptionWinterWeek_kW = EC.data_winterWeekBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionWinterWeek_kW = EC.data_winterWeekHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionWinterWeek_kW = EC.data_winterWeekElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionWinterWeek_kW = EC.data_winterWeekBatteriesDemand_kW;
area.v_dataElectricityForCookingConsumptionWinterWeek_kW = EC.data_winterWeekCookingElectricityDemand_kW;
area.v_dataElectricityForHydrogenConsumptionSummerWeek_kW = EC.data_summerWeekElectrolyserElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionWinterWeek_kW = EC.data_winterWeekDistrictHeatingDemand_kW;

//Supply
area.dsm_winterWeekProductionDataSets_kW = EC.dsm_winterWeekSupplyDataSets_kW;
area.v_dataElectricityWindProductionWinterWeek_kW = EC.data_winterWeekWindGeneration_kW;
area.v_dataElectricityPVProductionWinterWeek_kW = EC.data_winterWeekPVGeneration_kW;
area.v_dataElectricityStorageProductionWinterWeek_kW = EC.data_winterWeekBatteriesSupply_kW;
area.v_dataElectricityV2GProductionWinterWeek_kW = EC.data_winterWeekV2GSupply_kW;
area.v_dataElectricityCHPProductionWinterWeek_kW = EC.data_winterWeekCHPElectricityProduction_kW;

//Netload
area.v_dataNetLoadWinterWeek_kW = EC.data_winterWeekNetLoad_kW;

//SOC (Winterweek)
area.v_dataBatterySOCWinterWeek_.reset();
for (int i = 0; i < EC.data_winterWeekBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = EC.data_winterWeekBatteryStoredEnergy_MWh.getX(i);
    double y = EC.data_winterWeekBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCWinterWeek_.add(x, SOC);
}
/*ALCODEEND*/}

double f_updateYearlyDatasetsEnergyCoop(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1740067908197*/
//Datasets for yearly profiles chart
//Demand
area.dsm_dailyAverageConsumptionDataSets_kW = EC.dsm_dailyAverageDemandDataSets_kW;
area.dsm_dailyAverageProductionDataSets_kW = EC.dsm_dailyAverageSupplyDataSets_kW;
area.v_dataElectricityBaseloadConsumptionYear_kW = EC.data_annualBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionYear_kW = EC.data_annualHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionYear_kW = EC.data_annualElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionYear_kW = EC.data_annualBatteriesDemand_kW;
area.v_dataElectricityForHydrogenConsumptionYear_kW = EC.data_annualElectrolyserElectricityDemand_kW;
area.v_dataElectricityForCookingConsumptionYear_kW = EC.data_annualCookingElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionYear_kW = EC.data_annualDistrictHeatingDemand_kW;

//Supply
area.v_dataElectricityWindProductionYear_kW = EC.data_annualWindGeneration_kW;
area.v_dataElectricityPVProductionYear_kW = EC.data_annualPVGeneration_kW;
area.v_dataElectricityStorageProductionYear_kW = EC.data_annualBatteriesSupply_kW;
area.v_dataElectricityV2GProductionYear_kW = EC.data_annualV2GSupply_kW;
area.v_dataElectricityCHPProductionYear_kW = EC.data_annualCHPElectricitySupply_kW;
//SOC (Year)
area.v_dataBatterySOCYear_.reset();
for (int i = 0; i < EC.data_annualBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = EC.data_annualBatteryStoredEnergy_MWh.getX(i);
    double y = EC.data_annualBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCYear_.add(x, SOC);
}

/*ALCODEEND*/}

double f_initializeMainAreaCollection()
{/*ALCODESTART::1740497182921*/
//Initialize main area collection
AreaCollection mainArea = add_pop_areaResults(OL_GISObjectType.REGION, "Main");
v_area = mainArea;

//Set active energyCarriers
mainArea.v_activeProductionEnergyCarriers = energyModel.v_activeProductionEnergyCarriers;
mainArea.v_activeConsumptionEnergyCarriers = energyModel.v_activeConsumptionEnergyCarriers;


//Set booleans
f_updateActiveAssetBooleansGC(mainArea, energyModel.f_getGridConnections());

//Initialize the values
f_updateUIresultsMainArea();

/*ALCODEEND*/}

double f_addTimeStepLiveDataSetsEnergyCoop(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1740501467386*/
//Update SOC live plot
double batteryStoredEnergyLiveWeek_MWh = 0;
int i = max(0, EC.data_batteryStoredEnergyLiveWeek_MWh.size() - 1);
batteryStoredEnergyLiveWeek_MWh = 	EC.data_batteryStoredEnergyLiveWeek_MWh.getY(i);	
double timeAxisValue = EC.data_batteryStoredEnergyLiveWeek_MWh.getX(i);
double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? batteryStoredEnergyLiveWeek_MWh / area.v_batteryStorageCapacityInstalled_MWh : 0;
area.v_dataBatterySOCLiveWeek_.add(timeAxisValue, SOC); 


/*ALCODEEND*/}

double f_updateUIresultsGridConnection(AreaCollection area,GridConnection GC)
{/*ALCODESTART::1740657920600*/
//Set engine object
area.v_engineAgent = GC;

//Number of connected gcs
area.v_numberOfGridconnections = 1;

//Set active energyCarriers
area.v_activeProductionEnergyCarriers = GC.v_activeProductionEnergyCarriers;
area.v_activeConsumptionEnergyCarriers = GC.v_activeConsumptionEnergyCarriers;


//Update active asset booleans
f_updateActiveAssetBooleansGC(area, new ArrayList<GridConnection>(Arrays.asList(GC)));

/*ALCODEEND*/}

double f_updateVariablesOfGC(AreaCollection area,GridConnection GC)
{/*ALCODESTART::1740660879660*/
//Grid capacity
area.v_gridCapacityDelivery_kW = GC.p_contractedDeliveryCapacity_kW;
area.v_gridCapacityFeedIn_kW = GC.p_contractedFeedinCapacity_kW;

area.v_gridCapacityDelivery_groupcontract_kW = GC.p_contractedDeliveryCapacity_kW;
area.v_gridCapacityFeedin_groupcontract_kW = GC.p_contractedFeedinCapacity_kW;

area.b_isRealDeliveryCapacityAvailable = GC.b_isRealDeliveryCapacityAvailable;
area.b_isRealFeedinCapacityAvailable = GC.b_isRealFeedinCapacityAvailable;

//Installed Asset variables
area.v_batteryStorageCapacityInstalled_MWh += GC.v_totalInstalledBatteryStorageCapacity_MWh;


// KPIs for individual vs collective plots 
area.v_individualSelfconsumptionElectricity_fr = GC.v_totalElectricitySelfConsumed_MWh/GC.v_totalElectricityConsumed_MWh;
area.v_individualSelfSufficiencyElectricity_fr = GC.v_totalElectricitySelfConsumed_MWh/GC.v_totalElectricityProduced_MWh;

area.v_individualSelfconsumptionEnergy_fr = GC.v_totalEnergySelfConsumed_MWh/GC.v_totalEnergyConsumed_MWh;
area.v_individualSelfSufficiencyEnergy_fr = GC.v_totalEnergySelfConsumed_MWh/GC.v_totalEnergyProduced_MWh;

area.v_individualPeakDelivery_kW = GC.am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMaxPower_kW();
area.v_individualPeakFeedin_kW = GC.am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMinPower_kW();	

//Yearly
area.fm_totalImports_MWh.clear();
area.fm_totalExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_totalExports_MWh.put( energyCarrier, GC.fm_totalExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_totalImports_MWh.put( energyCarrier, GC.fm_totalImports_MWh.get(energyCarrier) );
}

area.v_totalEnergyImport_MWh = GC.v_totalEnergyImport_MWh;
area.v_totalEnergyExport_MWh = GC.v_totalEnergyExport_MWh;

area.v_totalEnergyProduced_MWh = GC.v_totalEnergyProduced_MWh;
area.v_totalEnergyConsumed_MWh = GC.v_totalEnergyConsumed_MWh;
area.v_totalEnergySelfConsumed_MWh = GC.v_totalEnergySelfConsumed_MWh;

area.v_totalElectricityProduced_MWh = GC.v_totalElectricityProduced_MWh;
area.v_totalElectricityConsumed_MWh = GC.v_totalElectricityConsumed_MWh;
area.v_totalElectricitySelfConsumed_MWh = GC.v_totalElectricitySelfConsumed_MWh;

//Overload duration (for multiple GC this does not really make sense right?, would be more interesting to see the influence of their combined contract capacity)
area.v_annualOverloadDurationDelivery_hr = GC.v_totalOverloadDurationDelivery_hr;
area.v_annualOverloadDurationFeedin_hr = GC.v_totalOverloadDurationFeedin_hr;


// Summer/winter
area.fm_summerWeekImports_MWh.clear();
area.fm_summerWeekExports_MWh.clear();
area.fm_winterWeekImports_MWh.clear();
area.fm_winterWeekExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_summerWeekExports_MWh.put( energyCarrier, GC.fm_summerWeekExports_MWh.get(energyCarrier) );
	area.fm_winterWeekExports_MWh.put( energyCarrier, GC.fm_winterWeekExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_summerWeekImports_MWh.put( energyCarrier, GC.fm_summerWeekImports_MWh.get(energyCarrier) );
	area.fm_winterWeekImports_MWh.put( energyCarrier, GC.fm_winterWeekImports_MWh.get(energyCarrier) );
}

area.v_summerWeekEnergyImport_MWh = GC.v_summerWeekEnergyImport_MWh;
area.v_summerWeekEnergyExport_MWh = GC.v_summerWeekEnergyExport_MWh;

area.v_summerWeekEnergyProduced_MWh = GC.v_summerWeekEnergyProduced_MWh;
area.v_summerWeekEnergyConsumed_MWh = GC.v_summerWeekEnergyConsumed_MWh;
area.v_summerWeekEnergySelfConsumed_MWh = GC.v_summerWeekEnergySelfConsumed_MWh;

area.v_summerWeekElectricityProduced_MWh = GC.v_summerWeekElectricityProduced_MWh;
area.v_summerWeekElectricityConsumed_MWh = GC.v_summerWeekElectricityConsumed_MWh;
area.v_summerWeekElectricitySelfConsumed_MWh = GC.v_summerWeekElectricitySelfConsumed_MWh;

area.v_winterWeekEnergyImport_MWh = GC.v_winterWeekEnergyImport_MWh;
area.v_winterWeekEnergyExport_MWh = GC.v_winterWeekEnergyExport_MWh;

area.v_winterWeekEnergyProduced_MWh = GC.v_winterWeekEnergyProduced_MWh;
area.v_winterWeekEnergyConsumed_MWh = GC.v_winterWeekEnergyConsumed_MWh;
area.v_winterWeekEnergySelfConsumed_MWh = GC.v_winterWeekEnergySelfConsumed_MWh;

area.v_winterWeekElectricityProduced_MWh = GC.v_winterWeekElectricityProduced_MWh;
area.v_winterWeekElectricityConsumed_MWh = GC.v_winterWeekElectricityConsumed_MWh;
area.v_winterWeekElectricitySelfConsumed_MWh = GC.v_winterWeekElectricitySelfConsumed_MWh;

// Day/night
area.fm_daytimeImports_MWh.clear();
area.fm_daytimeExports_MWh.clear();
area.fm_nighttimeImports_MWh.clear();
area.fm_nighttimeExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_daytimeExports_MWh.put( energyCarrier, GC.fm_daytimeExports_MWh.get(energyCarrier) );
	area.fm_nighttimeExports_MWh.put( energyCarrier, GC.fm_nighttimeExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_daytimeImports_MWh.put( energyCarrier, GC.fm_daytimeImports_MWh.get(energyCarrier) );
	area.fm_nighttimeImports_MWh.put( energyCarrier, GC.fm_nighttimeImports_MWh.get(energyCarrier) );
}

area.v_daytimeEnergyImport_MWh = GC.v_daytimeEnergyImport_MWh;
area.v_daytimeEnergyExport_MWh = GC.v_daytimeEnergyExport_MWh;

area.v_daytimeEnergyProduced_MWh = GC.v_daytimeEnergyProduced_MWh;
area.v_daytimeEnergyConsumed_MWh = GC.v_daytimeEnergyConsumed_MWh;
area.v_daytimeEnergySelfConsumed_MWh = GC.v_daytimeEnergySelfConsumed_MWh;

area.v_daytimeElectricityProduced_MWh = GC.v_daytimeElectricityProduced_MWh;
area.v_daytimeElectricityConsumed_MWh = GC.v_daytimeElectricityConsumed_MWh;
area.v_daytimeElectricitySelfConsumed_MWh = GC.v_daytimeElectricitySelfConsumed_MWh;


area.v_nighttimeEnergyImport_MWh = GC.v_nighttimeEnergyImport_MWh;
area.v_nighttimeEnergyExport_MWh = GC.v_nighttimeEnergyExport_MWh;

area.v_nighttimeEnergyProduced_MWh = GC.v_nighttimeEnergyProduced_MWh;
area.v_nighttimeEnergyConsumed_MWh = GC.v_nighttimeEnergyConsumed_MWh;
area.v_nighttimeEnergySelfConsumed_MWh = GC.v_nighttimeEnergySelfConsumed_MWh;

area.v_nighttimeElectricityProduced_MWh = GC.v_nighttimeElectricityProduced_MWh;
area.v_nighttimeElectricityConsumed_MWh = GC.v_nighttimeElectricityConsumed_MWh;
area.v_nighttimeElectricitySelfConsumed_MWh = GC.v_nighttimeElectricitySelfConsumed_MWh;

// Week/weekend
area.fm_weekdayImports_MWh.clear();
area.fm_weekdayExports_MWh.clear();
area.fm_weekendImports_MWh.clear();
area.fm_weekendExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_weekdayExports_MWh.put( energyCarrier, GC.fm_weekdayExports_MWh.get(energyCarrier) );
	area.fm_weekendExports_MWh.put( energyCarrier, GC.fm_weekendExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_weekdayImports_MWh.put( energyCarrier, GC.fm_weekdayImports_MWh.get(energyCarrier) );
	area.fm_weekendImports_MWh.put( energyCarrier, GC.fm_weekendImports_MWh.get(energyCarrier) );
}


area.v_weekdayEnergyImport_MWh = GC.v_weekdayEnergyImport_MWh;
area.v_weekdayEnergyExport_MWh = GC.v_weekdayEnergyExport_MWh;

area.v_weekdayEnergyProduced_MWh = GC.v_weekdayEnergyProduced_MWh;
area.v_weekdayEnergyConsumed_MWh = GC.v_weekdayEnergyConsumed_MWh;
area.v_weekdayEnergySelfConsumed_MWh = GC.v_weekdayEnergySelfConsumed_MWh;

area.v_weekdayElectricityProduced_MWh = GC.v_weekdayElectricityProduced_MWh;
area.v_weekdayElectricityConsumed_MWh = GC.v_weekdayElectricityConsumed_MWh;
area.v_weekdayElectricitySelfConsumed_MWh = GC.v_weekdayElectricitySelfConsumed_MWh;


area.v_weekendEnergyImport_MWh = GC.v_weekendEnergyImport_MWh;
area.v_weekendEnergyExport_MWh = GC.v_weekendEnergyExport_MWh;

area.v_weekendEnergyProduced_MWh = GC.v_weekendEnergyProduced_MWh;
area.v_weekendEnergyConsumed_MWh = GC.v_weekendEnergyConsumed_MWh;
area.v_weekendEnergySelfConsumed_MWh = GC.v_weekendEnergySelfConsumed_MWh;

area.v_weekendElectricityProduced_MWh = GC.v_weekendElectricityProduced_MWh;
area.v_weekendElectricityConsumed_MWh = GC.v_weekendElectricityConsumed_MWh;
area.v_weekendElectricitySelfConsumed_MWh = GC.v_weekendElectricitySelfConsumed_MWh;

/*ALCODEEND*/}

double f_updateLiveDatasetsGC(AreaCollection area,GridConnection GC)
{/*ALCODESTART::1740661244566*/
//Datasets for live charts
//Demand
area.dsm_liveConsumption_kW = GC.dsm_liveDemand_kW;
area.v_dataElectricityBaseloadConsumptionLiveWeek_kW = GC.data_baseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionLiveWeek_kW = GC.data_heatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionLiveWeek_kW = GC.data_electricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionLiveWeek_kW = GC.data_batteryCharging_kW;
area.v_dataElectricityForHydrogenConsumptionLiveWeek_kW = GC.data_hydrogenElectricityDemand_kW;
area.v_dataElectricityForCookingConsumptionLiveWeek_kW = GC.data_cookingElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionLiveWeek_kW = GC.data_districtHeatDelivery_kW;

//Supply
area.dsm_liveProduction_kW = GC.dsm_liveSupply_kW;
area.v_dataWindElectricityProductionLiveWeek_kW = GC.data_windGeneration_kW;
area.v_dataPVElectricityProductionLiveWeek_kW = GC.data_PVGeneration_kW;
area.v_dataStorageElectricityProductionLiveWeek_kW = GC.data_batteryDischarging_kW;
area.v_dataV2GElectricityProductionLiveWeek_kW = GC.data_V2GSupply_kW;
area.v_dataCHPElectricityProductionLiveWeek_kW = GC.data_CHPElectricityProductionLiveWeek_kW;

//SOC
area.v_dataBatterySOCLiveWeek_.reset();
for (int i = 0; i < GC.data_batteryStoredEnergyLiveWeek_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = GC.data_batteryStoredEnergyLiveWeek_MWh.getX(i);
    double y = GC.data_batteryStoredEnergyLiveWeek_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCLiveWeek_.add(x, SOC);
}

//Total
area.v_dataNetLoadLiveWeek_kW = GC.data_liveElectricityBalance_kW;
area.v_batteryStorageCapacityInstalled_MWh = GC.v_totalInstalledBatteryStorageCapacity_MWh;

//Capacity
area.v_dataElectricityDeliveryCapacityLiveWeek_kW = GC.data_gridCapacityDemand_kW;
area.v_dataElectricityFeedInCapacityLiveWeek_kW = GC.data_gridCapacitySupply_kW;

/*ALCODEEND*/}

double f_updateWeeklyDatasetsGC(AreaCollection area,GridConnection GC)
{/*ALCODESTART::1740661336358*/
//Datasets for live summerWeek chart
//Demand
area.dsm_summerWeekConsumptionDataSets_kW = GC.dsm_summerWeekDemandDataSets_kW;
area.v_dataElectricityBaseloadConsumptionSummerWeek_kW = GC.data_summerWeekBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionSummerWeek_kW = GC.data_summerWeekHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionSummerWeek_kW = GC.data_summerWeekElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionSummerWeek_kW = GC.data_summerWeekBatteriesDemand_kW;
area.v_dataElectricityForCookingConsumptionSummerWeek_kW = GC.data_summerWeekCookingElectricityDemand_kW;
area.v_dataElectricityForHydrogenConsumptionSummerWeek_kW = GC.data_summerWeekElectrolyserElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionSummerWeek_kW = GC.data_summerWeekDistrictHeatingDemand_kW;

//Supply
area.dsm_summerWeekProductionDataSets_kW = GC.dsm_summerWeekSupplyDataSets_kW;
area.v_dataElectricityWindProductionSummerWeek_kW = GC.data_summerWeekWindGeneration_kW;
area.v_dataElectricityPVProductionSummerWeek_kW = GC.data_summerWeekPVGeneration_kW;
area.v_dataElectricityStorageProductionSummerWeek_kW = GC.data_summerWeekBatteriesSupply_kW;
area.v_dataElectricityV2GProductionSummerWeek_kW = GC.data_summerWeekV2GSupply_kW;
area.v_dataElectricityCHPProductionSummerWeek_kW = GC.data_summerWeekCHPElectricityProduction_kW;

//Net load
area.v_dataNetLoadSummerWeek_kW = GC.am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(energyModel.p_startHourSummerWeek);

//SOC (summerweek)
area.v_dataBatterySOCSummerWeek_.reset();
for (int i = 0; i < GC.data_summerWeekBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = GC.data_summerWeekBatteryStoredEnergy_MWh.getX(i);
    double y = GC.data_summerWeekBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCSummerWeek_.add(x, SOC);
}

//Datasets for live winterWeek chart
//Demand
area.dsm_winterWeekConsumptionDataSets_kW = GC.dsm_winterWeekDemandDataSets_kW;
area.v_dataElectricityBaseloadConsumptionWinterWeek_kW = GC.data_winterWeekBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionWinterWeek_kW = GC.data_winterWeekHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionWinterWeek_kW = GC.data_winterWeekElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionWinterWeek_kW = GC.data_winterWeekBatteriesDemand_kW;
area.v_dataElectricityForCookingConsumptionWinterWeek_kW = GC.data_winterWeekCookingElectricityDemand_kW;
area.v_dataElectricityForHydrogenConsumptionSummerWeek_kW = GC.data_summerWeekElectrolyserElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionWinterWeek_kW = GC.data_winterWeekDistrictHeatingDemand_kW;

//Supply
area.dsm_winterWeekProductionDataSets_kW = GC.dsm_winterWeekSupplyDataSets_kW;
area.v_dataElectricityWindProductionWinterWeek_kW = GC.data_winterWeekWindGeneration_kW;
area.v_dataElectricityPVProductionWinterWeek_kW = GC.data_winterWeekPVGeneration_kW;
area.v_dataElectricityStorageProductionWinterWeek_kW = GC.data_winterWeekBatteriesSupply_kW;
area.v_dataElectricityV2GProductionWinterWeek_kW = GC.data_winterWeekV2GSupply_kW;
area.v_dataElectricityCHPProductionWinterWeek_kW = GC.data_winterWeekCHPElectricityProduction_kW;

//Netload
area.v_dataNetLoadWinterWeek_kW = GC.am_winterWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(energyModel.p_startHourWinterWeek);

//SOC (Winterweek)
area.v_dataBatterySOCWinterWeek_.reset();
for (int i = 0; i < GC.data_winterWeekBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = GC.data_winterWeekBatteryStoredEnergy_MWh.getX(i);
    double y = GC.data_winterWeekBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCWinterWeek_.add(x, SOC);
}
/*ALCODEEND*/}

double f_updateYearlyDatasetsGC(AreaCollection area,GridConnection GC)
{/*ALCODESTART::1740661736036*/
//Datasets for yearly profiles chart
//Demand
area.dsm_dailyAverageConsumptionDataSets_kW = GC.dsm_dailyAverageDemandDataSets_kW;
area.dsm_dailyAverageProductionDataSets_kW = GC.dsm_dailyAverageSupplyDataSets_kW;
area.v_dataElectricityBaseloadConsumptionYear_kW = GC.data_annualBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionYear_kW = GC.data_annualHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionYear_kW = GC.data_annualElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionYear_kW = GC.data_annualBatteriesDemand_kW;
area.v_dataElectricityForHydrogenConsumptionYear_kW = GC.data_annualElectrolyserElectricityDemand_kW;
area.v_dataElectricityForCookingConsumptionYear_kW = GC.data_annualCookingElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionYear_kW = GC.data_annualDistrictHeatingDemand_kW;

//Supply
area.v_dataElectricityWindProductionYear_kW = GC.data_annualWindGeneration_kW;
area.v_dataElectricityPVProductionYear_kW = GC.data_annualPVGeneration_kW;
area.v_dataElectricityStorageProductionYear_kW = GC.data_annualBatteriesSupply_kW;
area.v_dataElectricityV2GProductionYear_kW = GC.data_annualV2GSupply_kW;
area.v_dataElectricityCHPProductionYear_kW = GC.data_annualCHPElectricitySupply_kW;
//SOC (Year)
area.v_dataBatterySOCYear_.reset();
for (int i = 0; i < GC.data_annualBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = GC.data_annualBatteryStoredEnergy_MWh.getX(i);
    double y = GC.data_annualBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCYear_.add(x, SOC);
}

/*ALCODEEND*/}

double f_addTimeStepLiveDataSetsGC(AreaCollection area,GridConnection GC)
{/*ALCODESTART::1740661810239*/
//Update SOC live plot
double batteryStoredEnergyLiveWeek_MWh = 0;
int i = max(0, GC.data_batteryStoredEnergyLiveWeek_MWh.size() - 1);
batteryStoredEnergyLiveWeek_MWh = 	GC.data_batteryStoredEnergyLiveWeek_MWh.getY(i);	
double timeAxisValue = GC.data_batteryStoredEnergyLiveWeek_MWh.getX(i);
double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? batteryStoredEnergyLiveWeek_MWh / area.v_batteryStorageCapacityInstalled_MWh : 0;
area.v_dataBatterySOCLiveWeek_.add(timeAxisValue, SOC); 


/*ALCODEEND*/}

double f_updateDuurkrommeGC(AreaCollection area,GridConnection GC)
{/*ALCODESTART::1740671109745*/
GC.f_getDuurkromme();
area.v_dataNetbelastingDuurkrommeYear_kW = GC.data_netbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeYearVorige_kW = GC.data_netbelastingDuurkrommeVorige_kW;

area.v_dataNetbelastingDuurkrommeSummer_kW = GC.data_summerWeekNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWinter_kW = GC.data_winterWeekNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeDaytime_kW = GC.data_daytimeNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeNighttime_kW = GC.data_nighttimeNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWeekend_kW = GC.data_weekendNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWeekday_kW = GC.data_weekdayNetbelastingDuurkromme_kW;
/*ALCODEEND*/}

double f_updateDuurkrommeEnergyCoop(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1740671165669*/
//EC.f_getDuurkromme(); Not necessary, cause is called earlier already to calculate group contract values!!

area.v_dataNetbelastingDuurkrommeYear_kW = EC.data_netbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeYearVorige_kW = EC.data_netbelastingDuurkrommeVorige_kW;

area.v_dataNetbelastingDuurkrommeSummer_kW = EC.data_summerWeekNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWinter_kW = EC.data_winterWeekNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeDaytime_kW = EC.data_daytimeNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeNighttime_kW = EC.data_nighttimeNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWeekend_kW = EC.data_weekendNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWeekday_kW = EC.data_weekdayNetbelastingDuurkromme_kW;
/*ALCODEEND*/}

DataSet f_createFlatDataset(double startTime_hr,double duration_hr,double value)
{/*ALCODESTART::1740750008518*/
DataSet flatDataset = new DataSet(2);
flatDataset.add(startTime_hr, value);
flatDataset.add(startTime_hr + duration_hr, value);

return flatDataset;
/*ALCODEEND*/}

double f_updateUIresultsMainArea1()
{/*ALCODESTART::1741275394918*/
AreaCollection area = v_area;
area.v_engineAgent = energyModel;

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
area.v_individualPeakDelivery_kW += sum(energyModel.f_getGridConnections(), gc -> gc.am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMaxPower_kW());
area.v_individualPeakFeedin_kW += -1*sum(energyModel.f_getGridConnections(), gc -> gc.am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMinPower_kW());

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
if(v_selectedRadioButtonSetup == OL_RadioButtonSetup.DEFAULT_AND_GESPREKSLEIDRAAD){
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

double f_updatePreviousTotalsGC1()
{/*ALCODESTART::1741275394922*/
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

double f_addTimeStepLiveDataSetsMain1(AreaCollection area)
{/*ALCODESTART::1741275394924*/

//Update SOC live plot
double batteryStoredEnergyLiveWeek_MWh = 0;
int i = max(0, energyModel.data_batteryStoredEnergy_MWh.size() - 1);
batteryStoredEnergyLiveWeek_MWh = 	energyModel.data_batteryStoredEnergy_MWh.getY(i);	
double timeAxisValue = energyModel.data_batteryStoredEnergy_MWh.getX(i);
double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? batteryStoredEnergyLiveWeek_MWh / area.v_batteryStorageCapacityInstalled_MWh : 0;
area.v_dataBatterySOCLiveWeek_.add(timeAxisValue, SOC); 

/*ALCODEEND*/}

double f_updateUIresultsEnergyCoop1(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1741275394926*/
//Set engine object
area.v_engineAgent = EC;

//Number of connected gcs
area.v_numberOfGridconnections = EC.c_customerGridConnections.size() + EC.c_memberGridConnections.size();

//Set active energyCarriers
area.v_activeProductionEnergyCarriers = EC.v_activeProductionEnergyCarriers;
area.v_activeConsumptionEnergyCarriers = EC.v_activeConsumptionEnergyCarriers;


//Previous totals (Hier zie je nu de waardes van de vorige simulatie OF COOP!)
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

//Update active asset booleans
f_updateActiveAssetBooleansGC(area, EC.f_getAllChildMemberGridConnections());

//Update variables
f_updateVariablesOfEnergyCoop(area, EC);

//Update variables
f_updateLiveDatasetsEnergyCoop(area, EC);

//Update variables
f_updateWeeklyDatasetsEnergyCoop(area, EC);

//Update variables
f_updateYearlyDatasetsEnergyCoop(area, EC);

//Update duurkromme
f_updateDuurkrommeEnergyCoop(area, EC);
/*ALCODEEND*/}

double f_updateVariablesOfEnergyCoop1(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1741275394928*/
//Grid capacity
area.v_gridCapacityDelivery_kW = EC.p_contractedDeliveryCapacity_kW;
area.v_gridCapacityFeedIn_kW = EC.p_contractedFeedinCapacity_kW;

area.v_gridCapacityDelivery_groupcontract_kW = EC.f_getGroupContractDeliveryCapacity_kW();
area.v_gridCapacityFeedin_groupcontract_kW = EC.f_getGroupContractFeedinCapacity_kW();

area.b_isRealDeliveryCapacityAvailable = EC.b_isRealDeliveryCapacityAvailable;
area.b_isRealFeedinCapacityAvailable = EC.b_isRealFeedinCapacityAvailable;

//Installed Asset variables
area.v_batteryStorageCapacityInstalled_MWh += EC.v_totalInstalledBatteryStorageCapacity_MWh;


// KPIs for 'collectief vs individueel' 
area.v_individualSelfconsumptionElectricity_fr = EC.v_cumulativeIndividualSelfconsumptionElectricity_fr;
area.v_individualSelfSufficiencyElectricity_fr = EC.v_cumulativeIndividualSelfSufficiencyElectricity_fr;

area.v_individualSelfconsumptionEnergy_fr = EC.v_cumulativeIndividualSelfconsumptionEnergy_fr;
area.v_individualSelfSufficiencyEnergy_fr = EC.v_cumulativeIndividualSelfSufficiencyEnergy_fr;

area.v_individualPeakDelivery_kW = EC.v_cumulativeIndividualPeakDelivery_kW;
area.v_individualPeakFeedin_kW = EC.v_cumulativeIndividualPeakFeedin_kW;

//Yearly
area.fm_totalImports_MWh.clear();
area.fm_totalExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_totalExports_MWh.put( energyCarrier, EC.fm_totalExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_totalImports_MWh.put( energyCarrier, EC.fm_totalImports_MWh.get(energyCarrier) );
}

area.v_totalEnergyImport_MWh = EC.v_totalEnergyImport_MWh;
area.v_totalEnergyExport_MWh = EC.v_totalEnergyExport_MWh;

area.v_totalEnergyProduced_MWh = EC.v_totalEnergyProduced_MWh;
area.v_totalEnergyConsumed_MWh = EC.v_totalEnergyConsumed_MWh;
area.v_totalEnergySelfConsumed_MWh = EC.v_totalEnergySelfConsumed_MWh;

area.v_totalElectricityProduced_MWh = EC.v_totalElectricityProduced_MWh;
area.v_totalElectricityConsumed_MWh = EC.v_totalElectricityConsumed_MWh;
area.v_totalElectricitySelfConsumed_MWh = EC.v_totalElectricitySelfConsumed_MWh;

//Overload duration (for multiple GC this does not really make sense right?, would be more interesting to see the influence of their combined contract capacity)
area.v_annualOverloadDurationDelivery_hr = EC.v_totalOverloadDurationDelivery_hr;
area.v_annualOverloadDurationFeedin_hr = EC.v_totalOverloadDurationFeedin_hr;


// Summer/winter
area.fm_summerWeekImports_MWh.clear();
area.fm_summerWeekExports_MWh.clear();
area.fm_winterWeekImports_MWh.clear();
area.fm_winterWeekExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_summerWeekExports_MWh.put( energyCarrier, EC.fm_summerWeekExports_MWh.get(energyCarrier) );
	area.fm_winterWeekExports_MWh.put( energyCarrier, EC.fm_winterWeekExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_summerWeekImports_MWh.put( energyCarrier, EC.fm_summerWeekImports_MWh.get(energyCarrier) );
	area.fm_winterWeekImports_MWh.put( energyCarrier, EC.fm_winterWeekImports_MWh.get(energyCarrier) );
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
area.fm_daytimeImports_MWh.clear();
area.fm_daytimeExports_MWh.clear();
area.fm_nighttimeImports_MWh.clear();
area.fm_nighttimeExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_daytimeExports_MWh.put( energyCarrier, EC.fm_daytimeExports_MWh.get(energyCarrier) );
	area.fm_nighttimeExports_MWh.put( energyCarrier, EC.fm_nighttimeExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_daytimeImports_MWh.put( energyCarrier, EC.fm_daytimeImports_MWh.get(energyCarrier) );
	area.fm_nighttimeImports_MWh.put( energyCarrier, EC.fm_nighttimeImports_MWh.get(energyCarrier) );
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
area.fm_weekdayImports_MWh.clear();
area.fm_weekdayExports_MWh.clear();
area.fm_weekendImports_MWh.clear();
area.fm_weekendExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_weekdayExports_MWh.put( energyCarrier, EC.fm_weekdayExports_MWh.get(energyCarrier) );
	area.fm_weekendExports_MWh.put( energyCarrier, EC.fm_weekendExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_weekdayImports_MWh.put( energyCarrier, EC.fm_weekdayImports_MWh.get(energyCarrier) );
	area.fm_weekendImports_MWh.put( energyCarrier, EC.fm_weekendImports_MWh.get(energyCarrier) );
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

double f_updateLiveDatasetsEnergyCoop1(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1741275394930*/
//Datasets for live charts
//Demand
area.dsm_liveConsumption_kW = EC.dsm_liveDemand_kW;
area.v_dataElectricityBaseloadConsumptionLiveWeek_kW = EC.data_baseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionLiveWeek_kW = EC.data_heatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionLiveWeek_kW = EC.data_electricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionLiveWeek_kW = EC.data_batteryCharging_kW;
area.v_dataElectricityForHydrogenConsumptionLiveWeek_kW = EC.data_hydrogenElectricityDemand_kW;
area.v_dataElectricityForCookingConsumptionLiveWeek_kW = EC.data_cookingElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionLiveWeek_kW = EC.data_districtHeatDelivery_kW;

//Supply
area.dsm_liveProduction_kW = EC.dsm_liveSupply_kW;
area.v_dataWindElectricityProductionLiveWeek_kW = EC.data_windGeneration_kW;
area.v_dataPVElectricityProductionLiveWeek_kW = EC.data_PVGeneration_kW;
area.v_dataStorageElectricityProductionLiveWeek_kW = EC.data_batteryDischarging_kW;
area.v_dataV2GElectricityProductionLiveWeek_kW = EC.data_V2GSupply_kW;
area.v_dataCHPElectricityProductionLiveWeek_kW = EC.data_CHPElectricityProductionLiveWeek_kW;

//SOC
area.v_dataBatterySOCLiveWeek_.reset();
for (int i = 0; i < EC.data_batteryStoredEnergyLiveWeek_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = EC.data_batteryStoredEnergyLiveWeek_MWh.getX(i);
    double y = EC.data_batteryStoredEnergyLiveWeek_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCLiveWeek_.add(x, SOC);
}

//Total
area.v_dataNetLoadLiveWeek_kW = EC.data_liveElectricityBalance_kW;
area.v_batteryStorageCapacityInstalled_MWh = EC.v_totalInstalledBatteryStorageCapacity_MWh;

//Capacity
area.v_dataElectricityDeliveryCapacityLiveWeek_kW = EC.data_gridCapacityDemand_kW;
area.v_dataElectricityFeedInCapacityLiveWeek_kW = EC.data_gridCapacitySupply_kW;

/*ALCODEEND*/}

double f_updateWeeklyDatasetsEnergyCoop1(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1741275394932*/
//Datasets for live summerWeek chart
//Demand
area.dsm_summerWeekConsumptionDataSets_kW = EC.dsm_summerWeekDemandDataSets_kW;
area.v_dataElectricityBaseloadConsumptionSummerWeek_kW = EC.data_summerWeekBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionSummerWeek_kW = EC.data_summerWeekHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionSummerWeek_kW = EC.data_summerWeekElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionSummerWeek_kW = EC.data_summerWeekBatteriesDemand_kW;
area.v_dataElectricityForCookingConsumptionSummerWeek_kW = EC.data_summerWeekCookingElectricityDemand_kW;
area.v_dataElectricityForHydrogenConsumptionSummerWeek_kW = EC.data_summerWeekElectrolyserElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionSummerWeek_kW = EC.data_summerWeekDistrictHeatingDemand_kW;

//Supply
area.dsm_summerWeekProductionDataSets_kW = EC.dsm_summerWeekSupplyDataSets_kW;
area.v_dataElectricityWindProductionSummerWeek_kW = EC.data_summerWeekWindGeneration_kW;
area.v_dataElectricityPVProductionSummerWeek_kW = EC.data_summerWeekPVGeneration_kW;
area.v_dataElectricityStorageProductionSummerWeek_kW = EC.data_summerWeekBatteriesSupply_kW;
area.v_dataElectricityV2GProductionSummerWeek_kW = EC.data_summerWeekV2GSupply_kW;
area.v_dataElectricityCHPProductionSummerWeek_kW = EC.data_summerWeekCHPElectricityProduction_kW;

//Net load
area.v_dataNetLoadSummerWeek_kW = EC.am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(energyModel.p_startHourSummerWeek);
//EC.data_summerWeekNetLoad_kW;


//SOC (summerweek)
area.v_dataBatterySOCSummerWeek_.reset();
for (int i = 0; i < EC.data_summerWeekBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = EC.data_summerWeekBatteryStoredEnergy_MWh.getX(i);
    double y = EC.data_summerWeekBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCSummerWeek_.add(x, SOC);
}

//Datasets for live winterWeek chart
//Demand
area.dsm_winterWeekConsumptionDataSets_kW = EC.dsm_winterWeekDemandDataSets_kW;
area.v_dataElectricityBaseloadConsumptionWinterWeek_kW = EC.data_winterWeekBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionWinterWeek_kW = EC.data_winterWeekHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionWinterWeek_kW = EC.data_winterWeekElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionWinterWeek_kW = EC.data_winterWeekBatteriesDemand_kW;
area.v_dataElectricityForCookingConsumptionWinterWeek_kW = EC.data_winterWeekCookingElectricityDemand_kW;
area.v_dataElectricityForHydrogenConsumptionSummerWeek_kW = EC.data_summerWeekElectrolyserElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionWinterWeek_kW = EC.data_winterWeekDistrictHeatingDemand_kW;

//Supply
area.dsm_winterWeekProductionDataSets_kW = EC.dsm_winterWeekSupplyDataSets_kW;
area.v_dataElectricityWindProductionWinterWeek_kW = EC.data_winterWeekWindGeneration_kW;
area.v_dataElectricityPVProductionWinterWeek_kW = EC.data_winterWeekPVGeneration_kW;
area.v_dataElectricityStorageProductionWinterWeek_kW = EC.data_winterWeekBatteriesSupply_kW;
area.v_dataElectricityV2GProductionWinterWeek_kW = EC.data_winterWeekV2GSupply_kW;
area.v_dataElectricityCHPProductionWinterWeek_kW = EC.data_winterWeekCHPElectricityProduction_kW;

//Netload
area.v_dataNetLoadWinterWeek_kW = EC.data_winterWeekNetLoad_kW;

//SOC (Winterweek)
area.v_dataBatterySOCWinterWeek_.reset();
for (int i = 0; i < EC.data_winterWeekBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = EC.data_winterWeekBatteryStoredEnergy_MWh.getX(i);
    double y = EC.data_winterWeekBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCWinterWeek_.add(x, SOC);
}
/*ALCODEEND*/}

double f_updateYearlyDatasetsEnergyCoop1(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1741275394934*/
//Datasets for yearly profiles chart
//Demand
area.dsm_dailyAverageConsumptionDataSets_kW = EC.dsm_dailyAverageDemandDataSets_kW;
area.dsm_dailyAverageProductionDataSets_kW = EC.dsm_dailyAverageSupplyDataSets_kW;
area.v_dataElectricityBaseloadConsumptionYear_kW = EC.data_annualBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionYear_kW = EC.data_annualHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionYear_kW = EC.data_annualElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionYear_kW = EC.data_annualBatteriesDemand_kW;
area.v_dataElectricityForHydrogenConsumptionYear_kW = EC.data_annualElectrolyserElectricityDemand_kW;
area.v_dataElectricityForCookingConsumptionYear_kW = EC.data_annualCookingElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionYear_kW = EC.data_annualDistrictHeatingDemand_kW;

//Supply
area.v_dataElectricityWindProductionYear_kW = EC.data_annualWindGeneration_kW;
area.v_dataElectricityPVProductionYear_kW = EC.data_annualPVGeneration_kW;
area.v_dataElectricityStorageProductionYear_kW = EC.data_annualBatteriesSupply_kW;
area.v_dataElectricityV2GProductionYear_kW = EC.data_annualV2GSupply_kW;
area.v_dataElectricityCHPProductionYear_kW = EC.data_annualCHPElectricitySupply_kW;
//SOC (Year)
area.v_dataBatterySOCYear_.reset();
for (int i = 0; i < EC.data_annualBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = EC.data_annualBatteryStoredEnergy_MWh.getX(i);
    double y = EC.data_annualBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCYear_.add(x, SOC);
}

/*ALCODEEND*/}

double f_addTimeStepLiveDataSetsEnergyCoop1(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1741275394936*/
//Update SOC live plot
double batteryStoredEnergyLiveWeek_MWh = 0;
int i = max(0, EC.data_batteryStoredEnergyLiveWeek_MWh.size() - 1);
batteryStoredEnergyLiveWeek_MWh = 	EC.data_batteryStoredEnergyLiveWeek_MWh.getY(i);	
double timeAxisValue = EC.data_batteryStoredEnergyLiveWeek_MWh.getX(i);
double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? batteryStoredEnergyLiveWeek_MWh / area.v_batteryStorageCapacityInstalled_MWh : 0;
area.v_dataBatterySOCLiveWeek_.add(timeAxisValue, SOC); 


/*ALCODEEND*/}

double f_updateUIresultsGridConnection1(AreaCollection area,GridConnection GC)
{/*ALCODESTART::1741275394938*/
//Set engine object
area.v_engineAgent = GC;

//Number of connected gcs
area.v_numberOfGridconnections = 1;

//Set active energyCarriers
area.v_activeProductionEnergyCarriers = GC.v_activeProductionEnergyCarriers;
area.v_activeConsumptionEnergyCarriers = GC.v_activeConsumptionEnergyCarriers;


//Update active asset booleans
f_updateActiveAssetBooleansGC(area, new ArrayList<GridConnection>(Arrays.asList(GC)));

//Update variables
f_updateVariablesOfGC(area, GC);

//Update variables
f_updateLiveDatasetsGC(area, GC);

//Update variables
f_updateWeeklyDatasetsGC(area, GC);

//Update variables
f_updateYearlyDatasetsGC(area, GC);

//Get duurkromme
f_updateDuurkrommeGC(area, GC);
/*ALCODEEND*/}

double f_updateVariablesOfGC1(AreaCollection area,GridConnection GC)
{/*ALCODESTART::1741275394940*/
//Grid capacity
area.v_gridCapacityDelivery_kW = GC.p_contractedDeliveryCapacity_kW;
area.v_gridCapacityFeedIn_kW = GC.p_contractedFeedinCapacity_kW;

area.v_gridCapacityDelivery_groupcontract_kW = GC.p_contractedDeliveryCapacity_kW;
area.v_gridCapacityFeedin_groupcontract_kW = GC.p_contractedFeedinCapacity_kW;

area.b_isRealDeliveryCapacityAvailable = GC.b_isRealDeliveryCapacityAvailable;
area.b_isRealFeedinCapacityAvailable = GC.b_isRealFeedinCapacityAvailable;

//Installed Asset variables
area.v_batteryStorageCapacityInstalled_MWh += GC.v_totalInstalledBatteryStorageCapacity_MWh;


// KPIs for individual vs collective plots 
area.v_individualSelfconsumptionElectricity_fr = GC.v_totalElectricitySelfConsumed_MWh/GC.v_totalElectricityConsumed_MWh;
area.v_individualSelfSufficiencyElectricity_fr = GC.v_totalElectricitySelfConsumed_MWh/GC.v_totalElectricityProduced_MWh;

area.v_individualSelfconsumptionEnergy_fr = GC.v_totalEnergySelfConsumed_MWh/GC.v_totalEnergyConsumed_MWh;
area.v_individualSelfSufficiencyEnergy_fr = GC.v_totalEnergySelfConsumed_MWh/GC.v_totalEnergyProduced_MWh;

area.v_individualPeakDelivery_kW = GC.am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMaxPower_kW();
area.v_individualPeakFeedin_kW = GC.am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMinPower_kW();	

//Yearly
area.fm_totalImports_MWh.clear();
area.fm_totalExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_totalExports_MWh.put( energyCarrier, GC.fm_totalExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_totalImports_MWh.put( energyCarrier, GC.fm_totalImports_MWh.get(energyCarrier) );
}

area.v_totalEnergyImport_MWh = GC.v_totalEnergyImport_MWh;
area.v_totalEnergyExport_MWh = GC.v_totalEnergyExport_MWh;

area.v_totalEnergyProduced_MWh = GC.v_totalEnergyProduced_MWh;
area.v_totalEnergyConsumed_MWh = GC.v_totalEnergyConsumed_MWh;
area.v_totalEnergySelfConsumed_MWh = GC.v_totalEnergySelfConsumed_MWh;

area.v_totalElectricityProduced_MWh = GC.v_totalElectricityProduced_MWh;
area.v_totalElectricityConsumed_MWh = GC.v_totalElectricityConsumed_MWh;
area.v_totalElectricitySelfConsumed_MWh = GC.v_totalElectricitySelfConsumed_MWh;

//Overload duration (for multiple GC this does not really make sense right?, would be more interesting to see the influence of their combined contract capacity)
area.v_annualOverloadDurationDelivery_hr = GC.v_totalOverloadDurationDelivery_hr;
area.v_annualOverloadDurationFeedin_hr = GC.v_totalOverloadDurationFeedin_hr;


// Summer/winter
area.fm_summerWeekImports_MWh.clear();
area.fm_summerWeekExports_MWh.clear();
area.fm_winterWeekImports_MWh.clear();
area.fm_winterWeekExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_summerWeekExports_MWh.put( energyCarrier, GC.fm_summerWeekExports_MWh.get(energyCarrier) );
	area.fm_winterWeekExports_MWh.put( energyCarrier, GC.fm_winterWeekExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_summerWeekImports_MWh.put( energyCarrier, GC.fm_summerWeekImports_MWh.get(energyCarrier) );
	area.fm_winterWeekImports_MWh.put( energyCarrier, GC.fm_winterWeekImports_MWh.get(energyCarrier) );
}

area.v_summerWeekEnergyImport_MWh = GC.v_summerWeekEnergyImport_MWh;
area.v_summerWeekEnergyExport_MWh = GC.v_summerWeekEnergyExport_MWh;

area.v_summerWeekEnergyProduced_MWh = GC.v_summerWeekEnergyProduced_MWh;
area.v_summerWeekEnergyConsumed_MWh = GC.v_summerWeekEnergyConsumed_MWh;
area.v_summerWeekEnergySelfConsumed_MWh = GC.v_summerWeekEnergySelfConsumed_MWh;

area.v_summerWeekElectricityProduced_MWh = GC.v_summerWeekElectricityProduced_MWh;
area.v_summerWeekElectricityConsumed_MWh = GC.v_summerWeekElectricityConsumed_MWh;
area.v_summerWeekElectricitySelfConsumed_MWh = GC.v_summerWeekElectricitySelfConsumed_MWh;

area.v_winterWeekEnergyImport_MWh = GC.v_winterWeekEnergyImport_MWh;
area.v_winterWeekEnergyExport_MWh = GC.v_winterWeekEnergyExport_MWh;

area.v_winterWeekEnergyProduced_MWh = GC.v_winterWeekEnergyProduced_MWh;
area.v_winterWeekEnergyConsumed_MWh = GC.v_winterWeekEnergyConsumed_MWh;
area.v_winterWeekEnergySelfConsumed_MWh = GC.v_winterWeekEnergySelfConsumed_MWh;

area.v_winterWeekElectricityProduced_MWh = GC.v_winterWeekElectricityProduced_MWh;
area.v_winterWeekElectricityConsumed_MWh = GC.v_winterWeekElectricityConsumed_MWh;
area.v_winterWeekElectricitySelfConsumed_MWh = GC.v_winterWeekElectricitySelfConsumed_MWh;

// Day/night
area.fm_daytimeImports_MWh.clear();
area.fm_daytimeExports_MWh.clear();
area.fm_nighttimeImports_MWh.clear();
area.fm_nighttimeExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_daytimeExports_MWh.put( energyCarrier, GC.fm_daytimeExports_MWh.get(energyCarrier) );
	area.fm_nighttimeExports_MWh.put( energyCarrier, GC.fm_nighttimeExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_daytimeImports_MWh.put( energyCarrier, GC.fm_daytimeImports_MWh.get(energyCarrier) );
	area.fm_nighttimeImports_MWh.put( energyCarrier, GC.fm_nighttimeImports_MWh.get(energyCarrier) );
}

area.v_daytimeEnergyImport_MWh = GC.v_daytimeEnergyImport_MWh;
area.v_daytimeEnergyExport_MWh = GC.v_daytimeEnergyExport_MWh;

area.v_daytimeEnergyProduced_MWh = GC.v_daytimeEnergyProduced_MWh;
area.v_daytimeEnergyConsumed_MWh = GC.v_daytimeEnergyConsumed_MWh;
area.v_daytimeEnergySelfConsumed_MWh = GC.v_daytimeEnergySelfConsumed_MWh;

area.v_daytimeElectricityProduced_MWh = GC.v_daytimeElectricityProduced_MWh;
area.v_daytimeElectricityConsumed_MWh = GC.v_daytimeElectricityConsumed_MWh;
area.v_daytimeElectricitySelfConsumed_MWh = GC.v_daytimeElectricitySelfConsumed_MWh;


area.v_nighttimeEnergyImport_MWh = GC.v_nighttimeEnergyImport_MWh;
area.v_nighttimeEnergyExport_MWh = GC.v_nighttimeEnergyExport_MWh;

area.v_nighttimeEnergyProduced_MWh = GC.v_nighttimeEnergyProduced_MWh;
area.v_nighttimeEnergyConsumed_MWh = GC.v_nighttimeEnergyConsumed_MWh;
area.v_nighttimeEnergySelfConsumed_MWh = GC.v_nighttimeEnergySelfConsumed_MWh;

area.v_nighttimeElectricityProduced_MWh = GC.v_nighttimeElectricityProduced_MWh;
area.v_nighttimeElectricityConsumed_MWh = GC.v_nighttimeElectricityConsumed_MWh;
area.v_nighttimeElectricitySelfConsumed_MWh = GC.v_nighttimeElectricitySelfConsumed_MWh;

// Week/weekend
area.fm_weekdayImports_MWh.clear();
area.fm_weekdayExports_MWh.clear();
area.fm_weekendImports_MWh.clear();
area.fm_weekendExports_MWh.clear();
for (OL_EnergyCarriers energyCarrier : area.v_activeProductionEnergyCarriers) {
	area.fm_weekdayExports_MWh.put( energyCarrier, GC.fm_weekdayExports_MWh.get(energyCarrier) );
	area.fm_weekendExports_MWh.put( energyCarrier, GC.fm_weekendExports_MWh.get(energyCarrier) );
}
for (OL_EnergyCarriers energyCarrier : area.v_activeConsumptionEnergyCarriers) {
	area.fm_weekdayImports_MWh.put( energyCarrier, GC.fm_weekdayImports_MWh.get(energyCarrier) );
	area.fm_weekendImports_MWh.put( energyCarrier, GC.fm_weekendImports_MWh.get(energyCarrier) );
}


area.v_weekdayEnergyImport_MWh = GC.v_weekdayEnergyImport_MWh;
area.v_weekdayEnergyExport_MWh = GC.v_weekdayEnergyExport_MWh;

area.v_weekdayEnergyProduced_MWh = GC.v_weekdayEnergyProduced_MWh;
area.v_weekdayEnergyConsumed_MWh = GC.v_weekdayEnergyConsumed_MWh;
area.v_weekdayEnergySelfConsumed_MWh = GC.v_weekdayEnergySelfConsumed_MWh;

area.v_weekdayElectricityProduced_MWh = GC.v_weekdayElectricityProduced_MWh;
area.v_weekdayElectricityConsumed_MWh = GC.v_weekdayElectricityConsumed_MWh;
area.v_weekdayElectricitySelfConsumed_MWh = GC.v_weekdayElectricitySelfConsumed_MWh;


area.v_weekendEnergyImport_MWh = GC.v_weekendEnergyImport_MWh;
area.v_weekendEnergyExport_MWh = GC.v_weekendEnergyExport_MWh;

area.v_weekendEnergyProduced_MWh = GC.v_weekendEnergyProduced_MWh;
area.v_weekendEnergyConsumed_MWh = GC.v_weekendEnergyConsumed_MWh;
area.v_weekendEnergySelfConsumed_MWh = GC.v_weekendEnergySelfConsumed_MWh;

area.v_weekendElectricityProduced_MWh = GC.v_weekendElectricityProduced_MWh;
area.v_weekendElectricityConsumed_MWh = GC.v_weekendElectricityConsumed_MWh;
area.v_weekendElectricitySelfConsumed_MWh = GC.v_weekendElectricitySelfConsumed_MWh;

/*ALCODEEND*/}

double f_updateLiveDatasetsGC1(AreaCollection area,GridConnection GC)
{/*ALCODESTART::1741275394942*/
//Datasets for live charts
//Demand
area.dsm_liveConsumption_kW = GC.dsm_liveDemand_kW;
area.v_dataElectricityBaseloadConsumptionLiveWeek_kW = GC.data_baseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionLiveWeek_kW = GC.data_heatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionLiveWeek_kW = GC.data_electricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionLiveWeek_kW = GC.data_batteryCharging_kW;
area.v_dataElectricityForHydrogenConsumptionLiveWeek_kW = GC.data_hydrogenElectricityDemand_kW;
area.v_dataElectricityForCookingConsumptionLiveWeek_kW = GC.data_cookingElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionLiveWeek_kW = GC.data_districtHeatDelivery_kW;

//Supply
area.dsm_liveProduction_kW = GC.dsm_liveSupply_kW;
area.v_dataWindElectricityProductionLiveWeek_kW = GC.data_windGeneration_kW;
area.v_dataPVElectricityProductionLiveWeek_kW = GC.data_PVGeneration_kW;
area.v_dataStorageElectricityProductionLiveWeek_kW = GC.data_batteryDischarging_kW;
area.v_dataV2GElectricityProductionLiveWeek_kW = GC.data_V2GSupply_kW;
area.v_dataCHPElectricityProductionLiveWeek_kW = GC.data_CHPElectricityProductionLiveWeek_kW;

//SOC
area.v_dataBatterySOCLiveWeek_.reset();
for (int i = 0; i < GC.data_batteryStoredEnergyLiveWeek_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = GC.data_batteryStoredEnergyLiveWeek_MWh.getX(i);
    double y = GC.data_batteryStoredEnergyLiveWeek_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCLiveWeek_.add(x, SOC);
}

//Total
area.v_dataNetLoadLiveWeek_kW = GC.data_liveElectricityBalance_kW;
area.v_batteryStorageCapacityInstalled_MWh = GC.v_totalInstalledBatteryStorageCapacity_MWh;

//Capacity
area.v_dataElectricityDeliveryCapacityLiveWeek_kW = GC.data_gridCapacityDemand_kW;
area.v_dataElectricityFeedInCapacityLiveWeek_kW = GC.data_gridCapacitySupply_kW;

/*ALCODEEND*/}

double f_updateWeeklyDatasetsGC1(AreaCollection area,GridConnection GC)
{/*ALCODESTART::1741275394944*/
//Datasets for live summerWeek chart
//Demand
area.dsm_summerWeekConsumptionDataSets_kW = GC.dsm_summerWeekDemandDataSets_kW;
area.v_dataElectricityBaseloadConsumptionSummerWeek_kW = GC.data_summerWeekBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionSummerWeek_kW = GC.data_summerWeekHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionSummerWeek_kW = GC.data_summerWeekElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionSummerWeek_kW = GC.data_summerWeekBatteriesDemand_kW;
area.v_dataElectricityForCookingConsumptionSummerWeek_kW = GC.data_summerWeekCookingElectricityDemand_kW;
area.v_dataElectricityForHydrogenConsumptionSummerWeek_kW = GC.data_summerWeekElectrolyserElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionSummerWeek_kW = GC.data_summerWeekDistrictHeatingDemand_kW;

//Supply
area.dsm_summerWeekProductionDataSets_kW = GC.dsm_summerWeekSupplyDataSets_kW;
area.v_dataElectricityWindProductionSummerWeek_kW = GC.data_summerWeekWindGeneration_kW;
area.v_dataElectricityPVProductionSummerWeek_kW = GC.data_summerWeekPVGeneration_kW;
area.v_dataElectricityStorageProductionSummerWeek_kW = GC.data_summerWeekBatteriesSupply_kW;
area.v_dataElectricityV2GProductionSummerWeek_kW = GC.data_summerWeekV2GSupply_kW;
area.v_dataElectricityCHPProductionSummerWeek_kW = GC.data_summerWeekCHPElectricityProduction_kW;

//Net load
area.v_dataNetLoadSummerWeek_kW = GC.am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(energyModel.p_startHourSummerWeek);

//SOC (summerweek)
area.v_dataBatterySOCSummerWeek_.reset();
for (int i = 0; i < GC.data_summerWeekBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = GC.data_summerWeekBatteryStoredEnergy_MWh.getX(i);
    double y = GC.data_summerWeekBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCSummerWeek_.add(x, SOC);
}

//Datasets for live winterWeek chart
//Demand
area.dsm_winterWeekConsumptionDataSets_kW = GC.dsm_winterWeekDemandDataSets_kW;
area.v_dataElectricityBaseloadConsumptionWinterWeek_kW = GC.data_winterWeekBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionWinterWeek_kW = GC.data_winterWeekHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionWinterWeek_kW = GC.data_winterWeekElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionWinterWeek_kW = GC.data_winterWeekBatteriesDemand_kW;
area.v_dataElectricityForCookingConsumptionWinterWeek_kW = GC.data_winterWeekCookingElectricityDemand_kW;
area.v_dataElectricityForHydrogenConsumptionSummerWeek_kW = GC.data_summerWeekElectrolyserElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionWinterWeek_kW = GC.data_winterWeekDistrictHeatingDemand_kW;

//Supply
area.dsm_winterWeekProductionDataSets_kW = GC.dsm_winterWeekSupplyDataSets_kW;
area.v_dataElectricityWindProductionWinterWeek_kW = GC.data_winterWeekWindGeneration_kW;
area.v_dataElectricityPVProductionWinterWeek_kW = GC.data_winterWeekPVGeneration_kW;
area.v_dataElectricityStorageProductionWinterWeek_kW = GC.data_winterWeekBatteriesSupply_kW;
area.v_dataElectricityV2GProductionWinterWeek_kW = GC.data_winterWeekV2GSupply_kW;
area.v_dataElectricityCHPProductionWinterWeek_kW = GC.data_winterWeekCHPElectricityProduction_kW;

//Netload
area.v_dataNetLoadWinterWeek_kW = GC.am_winterWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(energyModel.p_startHourWinterWeek);

//SOC (Winterweek)
area.v_dataBatterySOCWinterWeek_.reset();
for (int i = 0; i < GC.data_winterWeekBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = GC.data_winterWeekBatteryStoredEnergy_MWh.getX(i);
    double y = GC.data_winterWeekBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCWinterWeek_.add(x, SOC);
}
/*ALCODEEND*/}

double f_updateYearlyDatasetsGC1(AreaCollection area,GridConnection GC)
{/*ALCODESTART::1741275394946*/
//Datasets for yearly profiles chart
//Demand
area.dsm_dailyAverageConsumptionDataSets_kW = GC.dsm_dailyAverageDemandDataSets_kW;
area.dsm_dailyAverageProductionDataSets_kW = GC.dsm_dailyAverageSupplyDataSets_kW;
area.v_dataElectricityBaseloadConsumptionYear_kW = GC.data_annualBaseloadElectricityDemand_kW;
area.v_dataElectricityForHeatConsumptionYear_kW = GC.data_annualHeatPumpElectricityDemand_kW;
area.v_dataElectricityForTransportConsumptionYear_kW = GC.data_annualElectricVehicleDemand_kW;
area.v_dataElectricityForStorageConsumptionYear_kW = GC.data_annualBatteriesDemand_kW;
area.v_dataElectricityForHydrogenConsumptionYear_kW = GC.data_annualElectrolyserElectricityDemand_kW;
area.v_dataElectricityForCookingConsumptionYear_kW = GC.data_annualCookingElectricityDemand_kW;
area.v_dataDistrictHeatConsumptionYear_kW = GC.data_annualDistrictHeatingDemand_kW;

//Supply
area.v_dataElectricityWindProductionYear_kW = GC.data_annualWindGeneration_kW;
area.v_dataElectricityPVProductionYear_kW = GC.data_annualPVGeneration_kW;
area.v_dataElectricityStorageProductionYear_kW = GC.data_annualBatteriesSupply_kW;
area.v_dataElectricityV2GProductionYear_kW = GC.data_annualV2GSupply_kW;
area.v_dataElectricityCHPProductionYear_kW = GC.data_annualCHPElectricitySupply_kW;
//SOC (Year)
area.v_dataBatterySOCYear_.reset();
for (int i = 0; i < GC.data_annualBatteryStoredEnergy_MWh.size(); i++) {
    // Get the x and y values from the source dataset
    double x = GC.data_annualBatteryStoredEnergy_MWh.getX(i);
    double y = GC.data_annualBatteryStoredEnergy_MWh.getY(i);
    
    // Modify the y value (e.g., divide it by 2)
    double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? y / area.v_batteryStorageCapacityInstalled_MWh : 0;
    
    // Add the new x and y values to the target dataset
    area.v_dataBatterySOCYear_.add(x, SOC);
}

/*ALCODEEND*/}

double f_addTimeStepLiveDataSetsGC1(AreaCollection area,GridConnection GC)
{/*ALCODESTART::1741275394948*/
//Update SOC live plot
double batteryStoredEnergyLiveWeek_MWh = 0;
int i = max(0, GC.data_batteryStoredEnergyLiveWeek_MWh.size() - 1);
batteryStoredEnergyLiveWeek_MWh = 	GC.data_batteryStoredEnergyLiveWeek_MWh.getY(i);	
double timeAxisValue = GC.data_batteryStoredEnergyLiveWeek_MWh.getX(i);
double SOC = area.v_batteryStorageCapacityInstalled_MWh > 0 ? batteryStoredEnergyLiveWeek_MWh / area.v_batteryStorageCapacityInstalled_MWh : 0;
area.v_dataBatterySOCLiveWeek_.add(timeAxisValue, SOC); 


/*ALCODEEND*/}

double f_updateDuurkrommeGC1(AreaCollection area,GridConnection GC)
{/*ALCODESTART::1741275394950*/
GC.f_getDuurkromme();
area.v_dataNetbelastingDuurkrommeYear_kW = GC.data_netbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeYearVorige_kW = GC.data_netbelastingDuurkrommeVorige_kW;

area.v_dataNetbelastingDuurkrommeSummer_kW = GC.data_summerWeekNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWinter_kW = GC.data_winterWeekNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeDaytime_kW = GC.data_daytimeNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeNighttime_kW = GC.data_nighttimeNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWeekend_kW = GC.data_weekendNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWeekday_kW = GC.data_weekdayNetbelastingDuurkromme_kW;
/*ALCODEEND*/}

double f_updateDuurkrommeEnergyCoop1(AreaCollection area,EnergyCoop EC)
{/*ALCODESTART::1741275394952*/
//EC.f_getDuurkromme(); Not necessary, cause is called earlier already to calculate group contract values!!

area.v_dataNetbelastingDuurkrommeYear_kW = EC.data_netbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeYearVorige_kW = EC.data_netbelastingDuurkrommeVorige_kW;

area.v_dataNetbelastingDuurkrommeSummer_kW = EC.data_summerWeekNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWinter_kW = EC.data_winterWeekNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeDaytime_kW = EC.data_daytimeNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeNighttime_kW = EC.data_nighttimeNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWeekend_kW = EC.data_weekendNetbelastingDuurkromme_kW;
area.v_dataNetbelastingDuurkrommeWeekday_kW = EC.data_weekdayNetbelastingDuurkromme_kW;
/*ALCODEEND*/}

double f_updateResultsUI(I_EnergyData selectedObjectInterface)
{/*ALCODESTART::1741337780594*/
v_selectedObjectInterface = selectedObjectInterface;
v_selectedObjectScope = v_selectedObjectInterface.getScope();

f_showCorrectChart();
/*ALCODEEND*/}

