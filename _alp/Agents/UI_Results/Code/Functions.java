double f_showCorrectChart()
{/*ALCODESTART::1714902615653*/
f_resetChartVisibility();

switch (v_selectedChartType) {
	case PROFILES:
		gr_chartProfielen_presentation.setVisible(true);
		chartProfielen.f_setCharts();
		break;
	case BAR_TOTALS:
		//gr_chartBalans_presentation.setVisible(true);
		gr_chartBars_presentation.setVisible(true);
		chartBars.f_setCharts();
		break;
	case LOAD_DURATION_CURVES:
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
	case GTO:
		gr_chartGTO_presentation.setVisible(true);
		chartGTO.f_setChartGTO();
		break;
	case CO2:
		gr_chartCO2_presentation.setVisible(true);
		chartCO2.f_setChartCO2();
		break;
	case ENERGY_COSTS:
		gr_chartEnergyCosts_presentation.setVisible(true);
		chartEnergyCosts.f_setChartEnergyCosts();
		break;
	case CONNECTION_COSTS:
		gr_chartConnectionCosts_presentation.setVisible(true);
		chartConnectionCosts.f_setChartConnectionCosts();
		break;
	case CAPEX_AND_OPEX:
		gr_chartCAPEXAndOPEX_presentation.setVisible(true);
		chartCAPEXAndOPEX.f_setChartCAPEXAndOPEX();
		break;
	case TOTAL_COSTS:
		gr_chartTotalCosts_presentation.setVisible(true);
		chartTotalCosts.f_setChartTotalCosts();
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
chartEnergyCosts.f_styleBackground(backgroundColor, lineColor, lineWidth, lineStyle);
chartGTO.f_styleBackground(backgroundColor, lineColor, lineWidth, lineStyle);
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
f_setChartBars_Presentation(location_x, location_y, visible);
f_setChartGridLoad_Presentation(location_x, location_y, visible);
f_setChartSankey_Presentation(location_x, location_y, visible);
f_setChartSummary_Presentation(location_x, location_y, visible);
f_setChartGSLD_Presentation(location_x, location_y, visible);
f_setChartKPISummary_Presentation(location_x, location_y, visible);
f_setChartBatteries_presentation(location_x, location_y, visible);
f_setChartCO2_presentation(location_x, location_y, visible);
f_setChartEnergyCosts_presentation(location_x, location_y, visible);
f_setChartConnectionCosts_presentation(location_x, location_y, visible);
f_setChartCAPEXAndOPEX_presentation(location_x, location_y, visible);
f_setChartTotalCosts_presentation(location_x, location_y, visible);
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

String f_getECName(OL_EnergyCarriers energyCarrier)
{/*ALCODESTART::1731578318216*/
switch (energyCarrier) {
	case ELECTRICITY:
		return "Elektriciteit";
	case HEAT:
		return "Warmte";
	case METHANE:
		return "Gas";
	case PETROLEUM_FUEL:
		return "Diesel & Benzine";
	case HYDROGEN:
		return "Waterstof";
	case IRON_POWDER:
		return "IJzerpoeder";
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

double f_initializeResultsUI(List<OL_ChartTypes> selectedChartTypes_Energy,List<OL_ChartTypes> selectedChartTypes_Economic,List<OL_ChartTypes> selectedCharts_Sustainability,boolean enablePublicVersion)
{/*ALCODESTART::1739364390433*/
f_updateResultsUI(energyModel);

//Set the selected button setup
f_initializeResultsUIHeaderButtons(selectedChartTypes_Energy, selectedChartTypes_Economic, selectedCharts_Sustainability);

//Enable public version if requested
if(enablePublicVersion){
	f_enablePublicVersion(true);
}
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
traceln("DONT FORGET TO DISABLE RESULTSUI HEADER BUTTONS IN f_enableNonLivePlotRadioButtons ");
chartProfielen.rb_periodIncludingYear.setEnabled(active);
chartProfielen.rb_periodExcludingYear.setEnabled(active);
chartProfielen.rb_periodPeaksIncludingYear.setEnabled(active);
chartProfielen.rb_periodPeaksExcludingYear.setEnabled(active);
chartProfielen.f_enableExportButton(active);
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
if(customSelectedObjectText != null){
	v_selectedObjectText = customSelectedObjectText;
}
else{
	if(v_selectedObjectScope == OL_ResultScope.GRIDCONNECTION){
		GridConnection GC = ((GridConnection)v_selectedObjectInterface);
		String connectionDisplayName = GC.p_ownerID;
		if(GC.c_connectedGISObjects.size() > 0 && GC.c_connectedGISObjects.get(0).p_annotation != null && GC.c_connectedGISObjects.get(0).c_containedGridConnections.size() == 1){
			connectionDisplayName = GC.c_connectedGISObjects.get(0).p_annotation;
		}
		
		if(connectionDisplayName == null){
			connectionDisplayName = GC.p_gridConnectionID;
		}
		
		if(connectionDisplayName.contains("verblijfsobject.") || connectionDisplayName.contains("pand.")){
			v_selectedObjectText = "een generieke aansluiting";
		}
		else{
			v_selectedObjectText = connectionDisplayName;
		}
	}
	else if(v_selectedObjectScope == OL_ResultScope.GRIDNODE){
		v_selectedObjectText = "transformatorstation : " + v_gridNode.p_gridNodeID;
	}
	else if(v_selectedObjectScope == OL_ResultScope.ENERGYCOOP){
		List<GridConnection> memberGCList = findAll(((EnergyCoop)v_selectedObjectInterface).f_getAllChildMemberGridConnections(), GC -> !(GC instanceof GCGridBattery && GC.f_getBatteryManagement() instanceof J_BatteryManagementPeakShaving && ((J_BatteryManagementPeakShaving)GC.f_getBatteryManagement()).getTargetType() == OL_ResultScope.ENERGYCOOP));
		
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
				v_selectedObjectText = memberGCList.size() + " aansluitingen in één pand";
			}
			else{
				v_selectedObjectText = "een selectie van aansluitingen"; // Een selectie van aansluitinge in meerdere panden (door middel van bijv filter).
			}
		} else {
			v_selectedObjectText = "klanten van energiecoöperatie"; // Geen aansluitingen in deze selectie
		}
	
	}
	else if(v_selectedObjectScope == OL_ResultScope.ENERGYMODEL){
			v_selectedObjectText = "het hele model";
		if(((EnergyModel)v_selectedObjectInterface).p_regionName != null){
			v_selectedObjectText = ((EnergyModel)v_selectedObjectInterface).p_regionName;
		}
	}
}

//Limit visible length
int maxStringLength = 40;
if (v_selectedObjectText.length() > maxStringLength){
     v_selectedObjectText = v_selectedObjectText.substring(0, maxStringLength);
}

//t_selectedObjectDisplayText.setText("Je bekijkt nu gegevens van: " + selectedObjectText);
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

double f_setChartEnergyCosts_presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1755704458936*/
//Set the location and visibility of the EnergyCosts charts presentation

//Set x axis
if(location_x != null){
	gr_chartEnergyCosts_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartEnergyCosts_presentation.setY(location_y);
}

//Set visibility
gr_chartEnergyCosts_presentation.setVisible(visible);
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

double f_setChartBars_Presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1765295944334*/
//Set the location and visibility of the balance charts presentation

//Set x axis
if(location_x != null){
	gr_chartBars_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartBars_presentation.setY(location_y);
}

//Set visibility
gr_chartBars_presentation.setVisible(visible);
/*ALCODEEND*/}

double f_initializeChartSelection_Energy(List<OL_ChartTypes> selectedCharts_Energy)
{/*ALCODESTART::1772200290396*/
List<OL_ChartTypes> loadedChartTypes_Energy = new ArrayList<>();

//Set active map overlay types if they are set in the project settings
if(selectedCharts_Energy != null && selectedCharts_Energy.size() > 0){
	loadedChartTypes_Energy.addAll(selectedCharts_Energy);
	
	// Force profiles to always be present and to be the first one (for now!). Needed to not break 'enable live plots only'.
	if(loadedChartTypes_Energy.contains(OL_ChartTypes.PROFILES)){
		loadedChartTypes_Energy.remove(OL_ChartTypes.PROFILES);
	}
	loadedChartTypes_Energy.add(0, OL_ChartTypes.PROFILES); 
	//Never allow more than 6 chart types (for now) (does not fit in header) -> If more than 6, remove final option(s)
	while(loadedChartTypes_Energy.size()>6){
		traceln("More than 6 Energy charts have been selected, this is not possible. ChartType: " + loadedChartTypes_Energy.get(6) + " has been removed.");
		loadedChartTypes_Energy.remove(loadedChartTypes_Energy.get(6));
	}
	
	//Perform check if chart type is in correct resultsUI Mode
	for(OL_ChartTypes selectedChart : loadedChartTypes_Energy){
		if(!map_ResultsUIModeToChartTypes.get(OL_ResultsUIMode.ENERGY).contains(selectedChart)){
			throw new RuntimeException("Selected chart for Energy charts found that is not an Energy Chart");
		}
	}
}
else{//No chart types loaded but profiles is required.
	loadedChartTypes_Energy.add(OL_ChartTypes.PROFILES); // Force profiles to always be present (for now!). Needed to not break 'enable live plots only'.
}

//Store selected charts in the ResultsUIModeToLoadedChartTypes map
map_ResultsUIModeToLoadedChartTypes.put(OL_ResultsUIMode.ENERGY, loadedChartTypes_Energy);

/*ALCODEEND*/}

double f_initializeChartSelection_Economic(List<OL_ChartTypes> selectedCharts_Economic)
{/*ALCODESTART::1772200450868*/
if(selectedCharts_Economic != null && selectedCharts_Economic.size() > 0){
	List<OL_ChartTypes> loadedChartTypes_Economic = new ArrayList<>(selectedCharts_Economic);
	
	//Perform check if chart type is in correct resultsUI Mode
	for(OL_ChartTypes selectedChart : loadedChartTypes_Economic){
		if(!map_ResultsUIModeToChartTypes.get(OL_ResultsUIMode.ECONOMIC).contains(selectedChart)){
			throw new RuntimeException("Selected chart for Economic charts found that is not an Economic Chart");
		}
	}
	
	//Never allow more than 6 chart types (for now) (does not fit in header) -> If more than 6, remove final option(s)
	while(loadedChartTypes_Economic.size()>6){
		traceln("More than 6 Economic charts have been selected, this is not possible. ChartType: " + loadedChartTypes_Economic.get(6) + " has been removed.");
		loadedChartTypes_Economic.remove(loadedChartTypes_Economic.get(6));
	}
	
	//Store selected charts in the ResultsUIModeToLoadedChartTypes map
	map_ResultsUIModeToLoadedChartTypes.put(OL_ResultsUIMode.ECONOMIC, loadedChartTypes_Economic);
}
/*ALCODEEND*/}

double f_initializeResultsUIHeaderButtons(List<OL_ChartTypes> selectedCharts_Energy,List<OL_ChartTypes> selectedCharts_Economic,List<OL_ChartTypes> selectedCharts_Sustainability)
{/*ALCODESTART::1772200556889*/
map_ResultsUIModeToLoadedChartTypes = new HashMap<>();
map_resultsUIModeButtonToResultsUIMode = new HashMap<>();
c_resultsUIModeButtons.forEach(button -> button.setVisible(false));
//c_chartButtons.forEach(button -> button.setVisible(false));
int currentResultsUIModeButtonIndex = 0;
CustomButton currentResultsUIModeButton;

//Initialize Energy Charts (Is mandatory, and has a fall back!)
f_initializeChartSelection_Energy(selectedCharts_Energy);
currentResultsUIModeButton = c_resultsUIModeButtons.get(currentResultsUIModeButtonIndex);
map_resultsUIModeButtonToResultsUIMode.put(currentResultsUIModeButton, OL_ResultsUIMode.ENERGY);
currentResultsUIModeButton.setText(map_resultsUIModeToName.get(OL_ResultsUIMode.ENERGY));
currentResultsUIModeButton.setVisible(true);
currentResultsUIModeButtonIndex++;

//Initialize Economic charts if selected
if(selectedCharts_Economic != null && selectedCharts_Economic.size() > 0){
	f_initializeChartSelection_Economic(selectedCharts_Economic);
	currentResultsUIModeButton = c_resultsUIModeButtons.get(currentResultsUIModeButtonIndex);
	map_resultsUIModeButtonToResultsUIMode.put(currentResultsUIModeButton, OL_ResultsUIMode.ECONOMIC);
	currentResultsUIModeButton.setText(map_resultsUIModeToName.get(OL_ResultsUIMode.ECONOMIC));
	currentResultsUIModeButton.setVisible(true);
	currentResultsUIModeButtonIndex++;
}

//Initialize Sustainability charts if selected
if(selectedCharts_Sustainability != null && selectedCharts_Sustainability.size() > 0){
	f_initializeChartSelection_Sustainability(selectedCharts_Sustainability);
	currentResultsUIModeButton = c_resultsUIModeButtons.get(currentResultsUIModeButtonIndex);
	map_resultsUIModeButtonToResultsUIMode.put(currentResultsUIModeButton, OL_ResultsUIMode.SUSTAINABILITY);
	currentResultsUIModeButton.setText(map_resultsUIModeToName.get(OL_ResultsUIMode.SUSTAINABILITY));
	currentResultsUIModeButton.setVisible(true);
	currentResultsUIModeButtonIndex++;
}

//Start model on Energy mode
f_selectResultsUIMode(0);
/*ALCODEEND*/}

double f_setChartCO2_presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1772472237697*/
//Set the location and visibility of the Batteries charts presentation

//Set x axis
if(location_x != null){
	gr_chartCO2_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartCO2_presentation.setY(location_y);
}

//Set visibility
gr_chartCO2_presentation.setVisible(visible);
/*ALCODEEND*/}

double f_setChartConnectionCosts_presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1772472253934*/
//Set the location and visibility of the ConnectionCosts charts presentation

//Set x axis
if(location_x != null){
	gr_chartConnectionCosts_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartConnectionCosts_presentation.setY(location_y);
}

//Set visibility
gr_chartConnectionCosts_presentation.setVisible(visible);
/*ALCODEEND*/}

double f_setChartCAPEXAndOPEX_presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1772472255353*/
//Set the location and visibility of the CAPEXAndOPEX charts presentation

//Set x axis
if(location_x != null){
	gr_chartCAPEXAndOPEX_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartCAPEXAndOPEX_presentation.setY(location_y);
}

//Set visibility
gr_chartCAPEXAndOPEX_presentation.setVisible(visible);
/*ALCODEEND*/}

double f_setChartTotalCosts_presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1772472257080*/
//Set the location and visibility of the TotalCosts charts presentation

//Set x axis
if(location_x != null){
	gr_chartTotalCosts_presentation.setX(location_x);
}

//Set y axis
if(location_y != null){
	gr_chartTotalCosts_presentation.setY(location_y);
}

//Set visibility
gr_chartTotalCosts_presentation.setVisible(visible);
/*ALCODEEND*/}

String f_getAssetName(OL_EnergyAssetType assetType)
{/*ALCODESTART::1774279163769*/
switch (assetType) {
	case STORAGE_ELECTRIC:
		return "Batterij";
	case PHOTOVOLTAIC:
		return "PV";
	case HEAT_PUMP_AIR:
		return "Warmtepomp";
	case PHOTOTHERMAL:
		return "PT-Panelen";
	case WINDMILL:
		return "Windturbine";
	case ELECTROLYSER:
		return "Electrolyser";
	case HYDROGEN_STORAGE:
		return "Waterstof tank";
	case FUEL_CELL:
		return "Fuelcell";
	case DIESEL_GENERATOR:
		return "Diesel generator";
	case METHANE_GENERATOR:
		return "Gas generator";
	case GAS_BURNER:
		return "Gasbrander";
	case ELECTRIC_VEHICLE:
		return "Elektrische autos";
	case ELECTRIC_VAN:
		return "Elektrische busjes";
	case ELECTRIC_TRUCK:
		return "Elektrische trucks";
	case HYDROGEN_TRUCK:
		return "Waterstof trucks";
		default:
			throw new RuntimeException("Onbekende assetType, kan niet vertaald worden.");
}



// The code below return the name in English
/*
String s = energyCarrier.toString();
return s.substring(0, 1) + s.substring(1).toLowerCase();
*/
/*ALCODEEND*/}

double f_activateChartBlocker(List<OL_ResultScope> supportedResultScopes)
{/*ALCODESTART::1776688304615*/
gr_chartBlocker.setVisible(true);
String warningScreenMessage = "";
for(OL_ResultScope supportedScope : supportedResultScopes){
	switch(supportedScope){
		case GRIDCONNECTION:
			warningScreenMessage += "- één aansluiting";
			break;
		case ENERGYMODEL:
			warningScreenMessage += "- het hele gebied";
			break;
		case ENERGYCOOP:
			warningScreenMessage += "- een groep van aansluitingen";
			break;
		case GRIDNODE:
			warningScreenMessage += "- één trafo";
			break;
	}
	warningScreenMessage += "\n"; 
}
t_chartBlockerMessage.setText(warningScreenMessage);
/*ALCODEEND*/}

double f_resetChartVisibility()
{/*ALCODESTART::1780332589329*/
gr_chartProfielen_presentation.setVisible(false);
gr_chartBalans_presentation.setVisible(false);
gr_chartNetbelasting_presentation.setVisible(false);
gr_chartSankey_presentation.setVisible(false);
gr_chartSummary_presentation.setVisible(false);
gr_chartGespreksLeidraad_presentation.setVisible(false);
gr_chartKPISummary_presentation.setVisible(false);
gr_chartBatteries_presentation.setVisible(false);
gr_chartGTO_presentation.setVisible(false);
gr_chartBars_presentation.setVisible(false);
gr_chartCO2_presentation.setVisible(false);
gr_chartEnergyCosts_presentation.setVisible(false);
gr_chartConnectionCosts_presentation.setVisible(false);
gr_chartCAPEXAndOPEX_presentation.setVisible(false);
gr_chartTotalCosts_presentation.setVisible(false);
gr_chartBlocker.setVisible(false);
/*ALCODEEND*/}

double f_enablePublicVersion(boolean enable)
{/*ALCODESTART::1780405232709*/
chartProfielen.f_enableExportFunctionality(!enable);
/*ALCODEEND*/}

double f_selectResultsUIMode(int selectedResultsUIModeButtonIndex)
{/*ALCODESTART::1782394255448*/
//Get selected resultsUIMode button
CustomButton selectedButton = c_resultsUIModeButtons.get(selectedResultsUIModeButtonIndex);

//Find selected resultsUIMode
OL_ResultsUIMode selectedResultsUIMode = map_resultsUIModeButtonToResultsUIMode.get(selectedButton);

//Color selected button
selectedButton.setFillColor(v_headerButtonSelectedColor);
selectedButton.setTextColor(v_headerButtonSelectedTextColor);
selectedButton.setImageIndex(map_ResultsUIModeToSelectedImageIndex.get(selectedResultsUIMode));
				
//Deselect other mode buttons
for(CustomButton customButton : c_resultsUIModeButtons){
	if(customButton != selectedButton && customButton.isVisible()){
		customButton.setFillColor(v_headerButtonDefaultColor);
		customButton.setTextColor(v_headerButtonDefaultTextColor);
		customButton.setImageIndex(map_ResultsUIModeToDefaultImageIndex.get(map_resultsUIModeButtonToResultsUIMode.get(customButton)));
	}
}

//Reconfigure the chart Buttons to the selected resultsUIMode
c_chartButtons.forEach(customButton -> customButton.setVisible(false));
map_chartButtonToCurrentChartType = new HashMap<>();
int currentButtonIndex = 0;
for(OL_ChartTypes loadedChartType : map_ResultsUIModeToLoadedChartTypes.get(selectedResultsUIMode)){
	map_chartButtonToCurrentChartType.put(c_chartButtons.get(currentButtonIndex), loadedChartType);
	c_chartButtons.get(currentButtonIndex).setVisible(true);
	c_chartButtons.get(currentButtonIndex).setText(map_chartTypeToName.get(loadedChartType));
	currentButtonIndex++;
}

//Select the first button
f_selectChart(0);
/*ALCODEEND*/}

double f_selectChart(int selectedChartButtonIndex)
{/*ALCODESTART::1782394276337*/
//Get selected button
CustomButton selectedButton = c_chartButtons.get(selectedChartButtonIndex);

//Find selected chart type
v_selectedChartType = map_chartButtonToCurrentChartType.get(selectedButton);

//Color selected button
selectedButton.setFillColor(v_headerButtonSelectedColor);
selectedButton.setTextColor(v_headerButtonSelectedTextColor);

//Deselect other chart buttons
for(CustomButton customButton : c_chartButtons){
	if(customButton != selectedButton){
		customButton.setFillColor(v_headerButtonDefaultColor);
		customButton.setTextColor(v_headerButtonDefaultTextColor);
	}
}

//Always disable kpi summary after clicking other chart
if(b_showKPISummary){
	checkbox_KPISummary.setSelected(false, true);
}

//Show correct chart
f_showCorrectChart();
/*ALCODEEND*/}

double f_initializeChartSelection_Sustainability(List<OL_ChartTypes> selectedCharts_Sustainability)
{/*ALCODESTART::1782401710519*/
if(selectedCharts_Sustainability != null && selectedCharts_Sustainability.size() > 0){
	List<OL_ChartTypes> loadedChartTypes_Sustainability = new ArrayList<>(selectedCharts_Sustainability);
	
	//Perform check if chart type is in correct resultsUI Mode
	for(OL_ChartTypes selectedChart : loadedChartTypes_Sustainability){
		if(!map_ResultsUIModeToChartTypes.get(OL_ResultsUIMode.SUSTAINABILITY).contains(selectedChart)){
			throw new RuntimeException("Selected chart for Sustainability charts found that is not a Sustainability Chart");
		}
	}
	
	//Never allow more than 6 chart types (for now) (does not fit in header) -> If more than 6, remove final option(s)
	while(loadedChartTypes_Sustainability.size()>6){
		traceln("More than 6 Sustainability charts have been selected, this is not possible. ChartType: " + loadedChartTypes_Sustainability.get(6) + " has been removed.");
		loadedChartTypes_Sustainability.remove(loadedChartTypes_Sustainability.get(6));
	}
	
	//Store selected charts in the ResultsUIModeToLoadedChartTypes map
	map_ResultsUIModeToLoadedChartTypes.put(OL_ResultsUIMode.SUSTAINABILITY, loadedChartTypes_Sustainability);
}
/*ALCODEEND*/}

double f_enableLivePlotsOnly()
{/*ALCODESTART::1782402066582*/
if (f_getSelectedObjectData() != null) {
	if(getGr_resultsUIHeader().isVisible()){
		traceln("DONT FORGET TO DISABLE RESULTSUI HEADER BUTTONS IN f_enableLivePlotsOnly ");
	}
	chartProfielen.getPeriodRadioButton().setValue(0, true);
	f_enableNonLivePlotRadioButtons(false);
}
for (ShapeRadioButtonGroup rb : chartProfielen.getAllPeriodRadioButtons()) {
	rb.setValue(0, false);
}
chartProfielen.getPeriodRadioButton().setValue(0, true);

f_enableNonLivePlotRadioButtons(false);
/*ALCODEEND*/}

