double f_initialize()
{/*ALCODESTART::1714835206522*/
//Set the selected radiobutton setup
f_setSelectedRadioButton();

//Initialize profiles graph (starting chart)
chartProfielen.f_setCharts();

//Initialize previous totals
f_initializePreviousTotals();
/*ALCODEEND*/}

double f_showCorrectChart()
{/*ALCODESTART::1714902615653*/
gr_chartProfielen_presentation.setVisible(false);
gr_chartBalans_presentation.setVisible(false);
gr_chartNetbelasting_presentation.setVisible(false);
gr_chartSankey_presentation.setVisible(false);
gr_chartSummary_presentation.setVisible(false);
gr_chartGespreksLeidraad_presentation.setVisible(false);
gr_chartKPISummary_presentation.setVisible(false);

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
//Set the location and visibility of the sankey charts presentation

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
//Set the location and visibility of the sankey charts presentation

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
/*ALCODEEND*/}

double f_setChartGSLD_Presentation(Integer location_x,Integer location_y,boolean visible)
{/*ALCODESTART::1730396435330*/
//Set the location and visibility of the sankey charts presentation

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

