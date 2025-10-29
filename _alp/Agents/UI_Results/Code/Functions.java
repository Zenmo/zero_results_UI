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
gr_chartEconomicKPIs_presentation.setVisible(false);
gr_chartGTO_presentation.setVisible(false);

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
		chartNetbelasting.f_setCharts();
		break;
	case SANKEY:
		gr_chartSankey_presentation.setVisible(true);
		chartSankey.f_setSankey();
		break;
	case GESPREKSLEIDRAAD_BEDRIJVEN:
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
	case ECONOMIC:
		gr_chartEconomicKPIs_presentation.setVisible(true);
		chartEconomicKPIs.f_setChartEconomicKPIs();
		break;
	case GTO:
		gr_chartGTO_presentation.setVisible(true);
		chartGTO.f_setChartGTO();
		break;
}

if(b_showKPISummary){
	gr_chartKPISummary_presentation.setVisible(true);
	chartKPISummary.f_setKPISummaryChart();
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
chartNetbelasting.f_setCharts();
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
chartEconomicKPIs.f_styleBackground(backgroundColor, lineColor, lineWidth, lineStyle);
chartGTO.f_styleBackground(backgroundColor, lineColor, lineWidth, lineStyle);
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
return v_selectedObjectInterface;
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
f_setChartEconomicKPI_presentation(location_x, location_y, visible);
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
rb_DEFAULT_AND_BATTERY_AND_GESPREKSLEIDRAADBEDRIJVEN.setVisible(false);
rb_DEFAULT_AND_BATTERY_AND_ECONOMIC.setVisible(false);
rb_DEFAULT_AND_GESPREKSLEIDRAADBEDRIJVEN_AND_GTO.setVisible(false);
rb_DEFAULT.setEnabled(false);
rb_DEFAULT_AND_GESPREKSLEIDRAAD.setEnabled(false);
rb_DEFAULT_AND_GESPREKSLEIDRAADBEDRIJVEN.setEnabled(false);
rb_DEFAULT_AND_BATTERY.setEnabled(false);
rb_DEFAULT_AND_BATTERY_AND_GESPREKSLEIDRAADBEDRIJVEN.setEnabled(false);
rb_DEFAULT_AND_BATTERY_AND_ECONOMIC.setEnabled(false);
rb_DEFAULT_AND_GESPREKSLEIDRAADBEDRIJVEN_AND_GTO.setEnabled(false);
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
case DEFAULT_AND_BATTERY_AND_GESPREKSLEIDRAADBEDRIJVEN:	
	v_selectedRadioButton = rb_DEFAULT_AND_BATTERY_AND_GESPREKSLEIDRAADBEDRIJVEN;
	break;
case DEFAULT_AND_BATTERY_AND_ECONOMIC:	
	v_selectedRadioButton = rb_DEFAULT_AND_BATTERY_AND_ECONOMIC;
	break;
case DEFAULT_AND_GESPREKSLEIDRAADBEDRIJVEN_AND_GTO:	
	v_selectedRadioButton = rb_DEFAULT_AND_GESPREKSLEIDRAADBEDRIJVEN_AND_GTO;
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
f_updateResultsUI(energyModel);

//Set the selected radiobutton setup
f_setSelectedRadioButton();

//Initialize profiles graph (starting chart)
chartProfielen.f_setCharts();

/*ALCODEEND*/}

double f_updateUIresultsGridNode(GridNode GN)
{/*ALCODESTART::1739364390441*/
v_selectedObjectScope = OL_ResultScope.GRIDNODE;
v_gridNode = GN;
f_setSelectedObjectText(null);
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

double f_enableNonLivePlotRadioButtons(boolean active)
{/*ALCODESTART::1739884154258*/
v_selectedRadioButton.setEnabled(active);
chartProfielen.rb_periodIncludingYear.setEnabled(active);
chartProfielen.rb_periodExcludingYear.setEnabled(active);
chartProfielen.rb_periodPeaksIncludingYear.setEnabled(active);
chartProfielen.rb_periodPeaksExcludingYear.setEnabled(active);
chartNetbelasting.radio.setEnabled(active);
chartBalans.radio_period.setEnabled(active);
/*ALCODEEND*/}

DataSet f_createFlatDataset(double startTime_hr,double duration_hr,double value)
{/*ALCODESTART::1740750008518*/
DataSet flatDataset = new DataSet(2);
flatDataset.add(startTime_hr, value);
flatDataset.add(startTime_hr + duration_hr, value);

return flatDataset;
/*ALCODEEND*/}

double f_updateResultsUI(I_EnergyData selectedObjectInterface)
{/*ALCODESTART::1741337780594*/
v_selectedObjectInterface = selectedObjectInterface;
v_selectedObjectScope = v_selectedObjectInterface.getScope();
f_setSelectedObjectText(null);

f_showCorrectChart();
/*ALCODEEND*/}

double f_setSelectedObjectText(String customSelectedObjectText)
{/*ALCODESTART::1742210371105*/
String selectedObjectText = "";

if(customSelectedObjectText != null){
	selectedObjectText = customSelectedObjectText;
}
else{
	if(v_selectedObjectScope == OL_ResultScope.GRIDCONNECTION){
		GridConnection GC = ((GridConnection)v_selectedObjectInterface.getLiveData().parentAgent);
		String connectionDisplayName = GC.p_ownerID;
		if(GC.c_connectedGISObjects.size() > 0 && GC.c_connectedGISObjects.get(0).p_annotation != null && GC.c_connectedGISObjects.get(0).c_containedGridConnections.size() == 1){
			connectionDisplayName = GC.c_connectedGISObjects.get(0).p_annotation;
		}
		
		if(connectionDisplayName == null){
			connectionDisplayName = GC.p_gridConnectionID;
		}
		
		if(connectionDisplayName.contains("verblijfsobject.") || connectionDisplayName.contains("pand.")){
			selectedObjectText = "Een generieke aansluiting";
		}
		else{
			selectedObjectText = connectionDisplayName;
		}
	}
	else if(v_selectedObjectScope == OL_ResultScope.GRIDNODE){
		selectedObjectText = "Trafo-station : " + v_gridNode.p_gridNodeID;
	}
	else if(v_selectedObjectScope == OL_ResultScope.ENERGYCOOP){
		List<GridConnection> memberGCList = findAll(((EnergyCoop)v_selectedObjectInterface.getLiveData().parentAgent).f_getAllChildMemberGridConnections(), GC -> !(GC instanceof GCGridBattery && GC.p_batteryAlgorithm instanceof J_BatteryManagementPeakShaving && ((J_BatteryManagementPeakShaving)GC.p_batteryAlgorithm).getTargetType() == OL_ResultScope.ENERGYCOOP));
		
		if (memberGCList.size() != 0) {
			boolean allGCInOneBuilding = false;
			ArrayList<GIS_Object> firstMemberGCBuildings = memberGCList.get(0).c_connectedGISObjects;
			
			for(GIS_Object gisobject : firstMemberGCBuildings){
				allGCInOneBuilding = true;
				for(GridConnection memberGC : memberGCList){
					if(!gisobject.c_containedGridConnections.contains(memberGC)){
						allGCInOneBuilding = false;
						break;
					}
				}
				if(allGCInOneBuilding){
					break;
				}
			}

			if(allGCInOneBuilding){
				selectedObjectText = memberGCList.size() + " aansluitingen in één pand";
			}
			else{
				selectedObjectText = "Een selectie van aansluitingen"; // Een selectie van aansluitinge in meerdere panden (door middel van bijv filter).
			}
		} else {
			selectedObjectText = "klanten van energiecoöperatie"; // Geen aansluitingen in deze selectie
		}
	
	}
	else if(v_selectedObjectScope == OL_ResultScope.ENERGYMODEL){
			selectedObjectText = "Het gehele model";
		if(((EnergyModel)v_selectedObjectInterface.getLiveData().parentAgent).p_regionName != null){
			selectedObjectText = ((EnergyModel)v_selectedObjectInterface.getLiveData().parentAgent).p_regionName;
		}
	}
}

//Limit visible length
int maxStringLength = 40;
if (selectedObjectText.length() > maxStringLength){
     selectedObjectText = selectedObjectText.substring(0, maxStringLength);
}

t_selectedObjectDisplayText.setText("Data van: " + selectedObjectText);
/*ALCODEEND*/}

double f_setCB_KPISummary_Presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1742218383411*/
//Set the location and visibility of the checkbox for the KPI summary chart

//Set x axis
if(location_x != null){
	checkbox_KPISummary.setX(location_x);
}

//Set y axis
if(location_y != null){
	checkbox_KPISummary.setY(location_y);
}

//Set visibility
checkbox_KPISummary.setVisible(visible);
/*ALCODEEND*/}

double f_setSelectedObjectDisplay(Integer location_x,Integer location_y,boolean setVisible)
{/*ALCODESTART::1742221943777*/
//Set the location and visibility of the checkbox for the 'selected object display' group

//Set x axis
if(location_x != null){
	gr_selectedObjectDisplay.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_selectedObjectDisplay.setY(location_y);
}


//Set visibility
if(b_isCompanyUIResultsUI){
	gr_selectedObjectDisplay.setVisible(false);
}
else{
	gr_selectedObjectDisplay.setVisible(setVisible);
}

/*ALCODEEND*/}

DataSet f_createNewDataSetDividedByValue(DataSet inputDataSet,double dividedByValue)
{/*ALCODESTART::1743689452038*/
if(dividedByValue == 0){
	new RuntimeException("Can't divide a dataset by zero!");
}

if(dividedByValue == 1){
	return inputDataSet;
}

DataSet newDataset = new DataSet(inputDataSet.size());
for (int i = 0; i < inputDataSet.size(); i++) {
    double newValue = inputDataSet.getY(i) / dividedByValue;
    newDataset.add(inputDataSet.getX(i), newValue);
}
return newDataset;
/*ALCODEEND*/}

double f_setInfoText(ShapeImage infoBubble,String descriptionText,double xPosition,double yPosition)
{/*ALCODESTART::1746102339921*/
if ( p_currentActiveInfoBubble.size() > 0 && p_currentActiveInfoBubble.get(0) == infoBubble ) {
	// If we click a second time on the same bubble it should close the window
	p_currentActiveInfoBubble.clear();
	gr_infoText.setVisible(false);
}
else {
	p_currentActiveInfoBubble.clear();
	p_currentActiveInfoBubble.add(infoBubble);
	
	int width_ch = 50;
	// Set Text
	Pair<String, Integer> p = v_infoText.restrictWidth(descriptionText, width_ch);
	t_infoTextDescription.setText(p.getFirst());
	
	// Set Size
	rect_infoText.setWidth(width_ch * 7.5); // about 7.5 px per char for sans serif 14 pt
	rect_infoText.setHeight(50 + p.getSecond() * 20); // about 50 px for title and 20 px per line for sans serif 14 pt
	
	// Set Position
	// The group position is on the top left, not the centre.
	double margin_px = 15;
	//double posX = f_getAbsolutePosition(infoBubble).getX();
	//double posY = f_getAbsolutePosition(infoBubble).getY();
	if ((v_interfaceViewAreaXOffset + v_resultsUIPresentationXOffset + xPosition) < (v_interfaceViewAreaXOffset + v_interfaceViewAreaWidth/2) ) {
		// bubble is on the left half, so text should appear to the right
		gr_infoText.setX( v_interfaceViewAreaXOffset + v_resultsUIPresentationXOffset + xPosition + margin_px + infoBubble.getWidth()/2);
	}
	else {
		// bubble is on the right half, so text should appear to the left
		gr_infoText.setX( v_interfaceViewAreaXOffset + v_resultsUIPresentationXOffset + xPosition - margin_px + infoBubble.getWidth()/2 - rect_infoText.getWidth());
	}
	
	// In AnyLogic the Y-Axis is inverted
	if ((v_interfaceViewAreaYOffset + v_resultsUIPresentationYOffset + yPosition) > (v_interfaceViewAreaYOffset + v_interfaceViewAreaHeight/2) ) {
		// bubble is on the bottom half, so text should appear above
		gr_infoText.setY( v_interfaceViewAreaYOffset + v_resultsUIPresentationYOffset + yPosition - margin_px  + infoBubble.getHeight()/2 - rect_infoText.getHeight());
	}
	else {
		// bubble is on the top half, so text should appear below
		gr_infoText.setY( v_interfaceViewAreaYOffset + v_resultsUIPresentationYOffset + yPosition + margin_px + infoBubble.getHeight()/2);
	}
	
	// Position of close button
	gr_closeInfoText.setX( width_ch * 7.5 - 20 ); // 20 px offset from the right hand side

	gr_infoText.setVisible(true);
}
/*ALCODEEND*/}

double f_setChartEconomicKPI_presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1755704458936*/
//Set the location and visibility of the Batteries charts presentation

//Set x axis
if(location_x != null){
	gr_chartEconomicKPIs_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartEconomicKPIs_presentation.setY(location_y);
}

//Set visibility
gr_chartEconomicKPIs_presentation.setVisible(visible);
/*ALCODEEND*/}

double f_setChartGTO_presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1759140579703*/
//Set the location and visibility of the Batteries charts presentation

//Set x axis
if(location_x != null){
	gr_chartGTO_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartGTO_presentation.setY(location_y);
}

//Set visibility
gr_chartGTO_presentation.setVisible(visible);
/*ALCODEEND*/}

