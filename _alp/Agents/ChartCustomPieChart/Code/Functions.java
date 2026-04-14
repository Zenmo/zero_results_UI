double f_setYearlyKPIs(Double totalPieChartValues,Double totalPieChartValuesPrevious)
{/*ALCODESTART::1772441389069*/
//Set new values text
DecimalFormat df = new DecimalFormat("0.00");

t_totalCustomPieChartValues.setText(df.format(totalPieChartValues) + p_unit);

if(totalPieChartValuesPrevious != null){
	t_previousTotalCustomPieChartValues.setText(df.format(totalPieChartValuesPrevious) + p_unit);
	
	////Set arrows
	if(totalPieChartValuesPrevious > totalPieChartValues){
		arrow_down_green_CustomPieChartTotal.setVisible(true);
	}
	else if(totalPieChartValues > totalPieChartValuesPrevious){
		arrow_up_red_CustomPieChartTotal.setVisible(true);
	}
	else{
		line_CustomPieChartTotal.setVisible(true);
	}
}
else{ // No previous rapid data -> dont show previous values
	t_previousTotalCustomPieChartValues.setText("-");
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
arrow_down_green_CustomPieChartTotal.setVisible(false);
arrow_up_red_CustomPieChartTotal.setVisible(false);
line_CustomPieChartTotal.setVisible(false);

//Reset KPIS
t_totalCustomPieChartValues.setText("-");
t_previousTotalCustomPieChartValues.setText("-");

//Clear pie chart
pieChart_totalSubdivision.removeAll();
gr_subChart_totalSubdivision.setVisible(false);
/*ALCODEEND*/}

double f_setCustomcustomPieChartValuesMap(Map<String, Pair<Double, Color>> customPieChartValuesMap)
{/*ALCODESTART::1774608778627*/
map_customPieChartValues = customPieChartValuesMap;
/*ALCODEEND*/}

double f_setChartCustomPieChart()
{/*ALCODESTART::1774618389588*/
//Initialization of data object.
I_EnergyData data = uI_Results.f_getSelectedObjectData();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 60, true);

//Reset chart
f_resetChart();

//Add custom co2 additions
double totalPieChartValues = 0;
if(map_customPieChartValues != null && map_customPieChartValues.size()>0 && data instanceof EnergyModel){
	totalPieChartValues = sum(map_customPieChartValues.values(), value -> max(0, value.getFirst().doubleValue()));
}

//Previous values
Double totalPieChartValuesPrevious = null;

//Add custom co2 additions previous run to previous total
if(map_customPieChartValues_previous != null && map_customPieChartValues_previous.size()>0 && data instanceof EnergyModel){
	totalPieChartValuesPrevious = sum(map_customPieChartValues_previous.values(), value -> max(0, value.getFirst().doubleValue()));
}


//Set total kpis
f_setYearlyKPIs(totalPieChartValues, totalPieChartValuesPrevious);

//Set pie chart
f_setPieChart(map_customPieChartValues, data);


/*ALCODEEND*/}

double f_storePreviousCustomCO2AdditionsMap()
{/*ALCODESTART::1774623387767*/
map_customPieChartValues_previous = map_customPieChartValues;
/*ALCODEEND*/}

double f_setPieChart(Map<String, Pair<Double, Color>> map_customPieChartValues,I_EnergyData data)
{/*ALCODESTART::1774888957180*/
if(map_customPieChartValues != null && data instanceof EnergyModel){
	for(String customEntry : map_customPieChartValues.keySet()){
		DataItem customPieChartValue = new DataItem();
		customPieChartValue.setValue(map_customPieChartValues.get(customEntry).getFirst());
		pieChart_totalSubdivision.addDataItem(customPieChartValue, customEntry, map_customPieChartValues.get(customEntry).getSecond());
	}
}
/*ALCODEEND*/}

