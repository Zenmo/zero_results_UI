double f_resetPlots()
{/*ALCODESTART::1714922345475*/
plot_jaar.removeAll();
plot_week.removeAll();
plot_dagnacht.removeAll();
plot_seizoen.removeAll();
gr_flexGrowthPotential.setVisible(false);
gr_growthPotential.setVisible(false);

gr_absoluteDeliveryInfo.setPos(175, 300);
gr_relativeDeliveryInfo.setPos(155, 420);
gr_absoluteFeedinInfo.setPos(305, 300);
gr_relativeFeedinInfo.setPos(305, 420);

gr_concurrencyKPI.setVisible(false);
/*ALCODEEND*/}

double f_addDataToPlots(I_EnergyData dataObject,J_LoadDurationCurves loadDurationCurves)
{/*ALCODESTART::1714923776978*/
double divisionAmountForCorrectUnit = 1;
String power_unit = "kW";

////Get the peaks
double maxDelivery_kW = max(0, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMax());
double maxFeedin_kW = abs(min(0, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMin()));
double maxDeliveryAndFeedin_kW = max(maxDelivery_kW, maxFeedin_kW);

////Put it in a usefull unit type
if(maxDeliveryAndFeedin_kW < 1){
	power_unit = "W";
	divisionAmountForCorrectUnit = 1/pow(10,3);
}
else if(maxDeliveryAndFeedin_kW < 1 * pow(10,3)){
	//Do nothing, already in right format
}
else if(maxDeliveryAndFeedin_kW < 1 * pow(10,6)){
	power_unit = "MW";
	divisionAmountForCorrectUnit = pow(10,3);
}
else{
	power_unit = "GW";
	divisionAmountForCorrectUnit = pow(10,6);
}

//Adjust y label to selected unit type
f_setYlabels(power_unit);

DataSet loadDurationCurveTotal = uI_Results.f_createNewDataSetDividedByValue(loadDurationCurves.ds_loadDurationCurveTotal_kW, divisionAmountForCorrectUnit);
double minValue = loadDurationCurveTotal.getYMin();
double maxValue = loadDurationCurveTotal.getYMax();

double gridCapacityDelivery_kW = dataObject.getRapidRunData().connectionMetaData.getContractedDeliveryCapacity_kW() / divisionAmountForCorrectUnit;
double gridCapacityFeedin_kW = dataObject.getRapidRunData().connectionMetaData.getContractedFeedinCapacity_kW() / divisionAmountForCorrectUnit;

double scaleMax = 1 + max(gridCapacityDelivery_kW*1.2, maxValue);
double scaleMin = -1 + min(-gridCapacityFeedin_kW*1.2, minValue);



//Jaar
plot_jaar.addDataSet(loadDurationCurveTotal,"Belasting jaar");
plot_jaar.setFixedVerticalScale(scaleMin, scaleMax);
plot_jaar.setColor(0, teal);

//summer/winter
DataSet loadDurationCurveSummer = uI_Results.f_createNewDataSetDividedByValue(loadDurationCurves.ds_loadDurationCurveSummer_kW, divisionAmountForCorrectUnit);
DataSet loadDurationCurveWinter = uI_Results.f_createNewDataSetDividedByValue(loadDurationCurves.ds_loadDurationCurveWinter_kW, divisionAmountForCorrectUnit);
plot_seizoen.addDataSet(loadDurationCurveSummer,"Belasting week laagste afname");
plot_seizoen.addDataSet(loadDurationCurveWinter,"Belasting week hoogste afname");
plot_seizoen.setColor(0,blue);
plot_seizoen.setColor(1,green);
plot_seizoen.setFixedVerticalScale(scaleMin, scaleMax);

// Day/night
DataSet loadDurationCurveDaytime = uI_Results.f_createNewDataSetDividedByValue(loadDurationCurves.ds_loadDurationCurveDaytime_kW, divisionAmountForCorrectUnit);
DataSet loadDurationCurveNighttime = uI_Results.f_createNewDataSetDividedByValue(loadDurationCurves.ds_loadDurationCurveNighttime_kW, divisionAmountForCorrectUnit);
plot_dagnacht.addDataSet(loadDurationCurveDaytime,"Belasting overdag");
plot_dagnacht.addDataSet(loadDurationCurveNighttime,"Belasting 's nachts");
plot_dagnacht.setColor(0,blue);
plot_dagnacht.setColor(1,green);	
plot_dagnacht.setFixedVerticalScale(scaleMin, scaleMax);

// Weekday/weekend
DataSet loadDurationCurveWeekday = uI_Results.f_createNewDataSetDividedByValue(loadDurationCurves.ds_loadDurationCurveWeekday_kW, divisionAmountForCorrectUnit);
DataSet loadDurationCurveWeekend = uI_Results.f_createNewDataSetDividedByValue(loadDurationCurves.ds_loadDurationCurveWeekend_kW, divisionAmountForCorrectUnit);
plot_week.addDataSet(loadDurationCurveWeekday,"Belasting weekdagen");
plot_week.addDataSet(loadDurationCurveWeekend,"Belasting weekenddagen");
plot_week.setColor(0,blue);
plot_week.setColor(1,green);
plot_week.setFixedVerticalScale(scaleMin, scaleMax);

//Add connection limits
f_addConnectionLimits(dataObject, divisionAmountForCorrectUnit); 


/*ALCODEEND*/}

double f_addConnectionLimits(I_EnergyData dataObject,double divisionAmountForCorrectUnit)
{/*ALCODESTART::1740584474407*/
//Add and color grid capacities
String deliveryCapacityLabel = "";
String feedinCapacityLabel = "";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;

//Create delivery and capacity year datasets
DataSet gridCapacityDelivery_kW = uI_Results.f_createFlatDataset(0, 8760, dataObject.getRapidRunData().connectionMetaData.getContractedDeliveryCapacity_kW() / divisionAmountForCorrectUnit);
DataSet gridCapacityFeedin_kW = uI_Results.f_createFlatDataset(0, 8760, -dataObject.getRapidRunData().connectionMetaData.getContractedFeedinCapacity_kW() / divisionAmountForCorrectUnit);

//Give specific capacity names based on selected scope	
if(uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDCONNECTION){
	deliveryCapacityLabel = "Gecontracteerde afname capaciteit";
	feedinCapacityLabel = "Gecontracteerde terugleverings capaciteit";
}

else if(uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYCOOP){
	deliveryCapacityLabel = "Cumulatieve GTV afname van de aansluitingen";
	feedinCapacityLabel = "Cumulatieve GTV teruglevering van de aansluitingen";
	
	if(uI_Results.b_showGroupContractValues){
		//And: add group contract values
		DataSet groupContractCapacityDelivery_kW = uI_Results.f_createFlatDataset(0, 8760, ((EnergyCoop)dataObject).f_getGroupContractDeliveryCapacity_kW(dataObject.getRapidRunData(), uI_Results.energyModel.p_timeParameters) / divisionAmountForCorrectUnit);
		DataSet groupContractCapacityFeedin_kW = uI_Results.f_createFlatDataset(0, 8760, -((EnergyCoop)dataObject).f_getGroupContractFeedinCapacity_kW(dataObject.getRapidRunData(), uI_Results.energyModel.p_timeParameters) / divisionAmountForCorrectUnit);
		
		plot_jaar.addDataSet(groupContractCapacityDelivery_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_jaar.addDataSet(groupContractCapacityFeedin_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
		plot_week.addDataSet(groupContractCapacityDelivery_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_week.addDataSet(groupContractCapacityFeedin_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
		plot_dagnacht.addDataSet(groupContractCapacityDelivery_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_dagnacht.addDataSet(groupContractCapacityFeedin_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
		plot_seizoen.addDataSet(groupContractCapacityDelivery_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_seizoen.addDataSet(groupContractCapacityFeedin_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
}
else if(uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYMODEL){
	deliveryCapacityLabel = "Fysieke afname capaciteit van het gebied";
	feedinCapacityLabel = "Fysieke terugleverings capaciteit van het gebied";
}

//Add estimation tag and color if the capacities are not known
if(!dataObject.getRapidRunData().connectionMetaData.getContractedDeliveryCapacityKnown()){
	deliveryCapacityLabel = "Geschatte " + deliveryCapacityLabel.substring(0, 1).toLowerCase() + deliveryCapacityLabel.substring(1);
	deliveryCapacityColor = uI_Results.v_electricityCapacityColor_estimated;
}
if(!dataObject.getRapidRunData().connectionMetaData.getContractedFeedinCapacityKnown()){
	feedinCapacityLabel = "Geschatte " + feedinCapacityLabel.substring(0, 1).toLowerCase() + feedinCapacityLabel.substring(1);
	feedinCapacityColor	= uI_Results.v_electricityCapacityColor_estimated;
}

        	
plot_jaar.addDataSet(gridCapacityDelivery_kW, deliveryCapacityLabel);
plot_jaar.addDataSet(gridCapacityFeedin_kW, feedinCapacityLabel);
plot_jaar.setColor(1, deliveryCapacityColor);
plot_jaar.setColor(2, feedinCapacityColor);

plot_week.addDataSet(gridCapacityDelivery_kW, deliveryCapacityLabel);
plot_week.addDataSet(gridCapacityFeedin_kW, feedinCapacityLabel);
plot_week.setColor(plot_week.getCount() - 2, deliveryCapacityColor);
plot_week.setColor(plot_week.getCount() - 1, feedinCapacityColor);

plot_dagnacht.addDataSet(gridCapacityDelivery_kW, deliveryCapacityLabel);
plot_dagnacht.addDataSet(gridCapacityFeedin_kW, feedinCapacityLabel);
plot_dagnacht.setColor(plot_dagnacht.getCount() - 2, deliveryCapacityColor);
plot_dagnacht.setColor(plot_dagnacht.getCount() - 1, feedinCapacityColor);

plot_seizoen.addDataSet(gridCapacityDelivery_kW, deliveryCapacityLabel);
plot_seizoen.addDataSet(gridCapacityFeedin_kW, feedinCapacityLabel);
plot_seizoen.setColor(plot_seizoen.getCount() - 2, deliveryCapacityColor);
plot_seizoen.setColor(plot_seizoen.getCount() - 1, feedinCapacityColor);

/*ALCODEEND*/}

double f_setLoadPlotsGN()
{/*ALCODESTART::1741699130819*/
GridNode GN = uI_Results.v_gridNode;

f_addDataToPlotsGN(GN);

f_setKPIValuesGN(GN); 

/*ALCODEEND*/}

double f_addDataToPlotsGN(GridNode GN)
{/*ALCODESTART::1741699130825*/
double divisionAmountForCorrectUnit = 1;
String power_unit = "kW";

////Get the peaks
J_LoadDurationCurves loadCurves = GN.f_getDuurkrommes(uI_Results.energyModel.p_timeParameters);
double maxDelivery_kW = max(0, loadCurves.ds_loadDurationCurveTotal_kW.getYMax());
double maxFeedin_kW = abs(min(0, loadCurves.ds_loadDurationCurveTotal_kW.getYMin()));
double maxDeliveryAndFeedin_kW = max(maxDelivery_kW, maxFeedin_kW);

////Put it in a usefull unit type
if(maxDeliveryAndFeedin_kW < 1){
	power_unit = "W";
	divisionAmountForCorrectUnit = 1/pow(10,3);
}
else if(maxDeliveryAndFeedin_kW < 1 * pow(10,3)){
	//Do nothing, already in right format
}
else if(maxDeliveryAndFeedin_kW < 1 * pow(10,6)){
	power_unit = "MW";
	divisionAmountForCorrectUnit = pow(10,3);
}
else{
	power_unit = "GW";
	divisionAmountForCorrectUnit = pow(10,6);
}

//Adjust y label to selected unit type
f_setYlabels(power_unit);

DataSet loadDurationCurveTotal = uI_Results.f_createNewDataSetDividedByValue(loadCurves.ds_loadDurationCurveTotal_kW, divisionAmountForCorrectUnit);
double minValue = loadDurationCurveTotal.getYMin();
double maxValue = loadDurationCurveTotal.getYMax();

double gridCapacityDelivery = GN.p_capacity_kW / divisionAmountForCorrectUnit;
double gridCapacityFeedin = GN.p_capacity_kW / divisionAmountForCorrectUnit;

double scaleMin = -1 + min(-gridCapacityFeedin*1.2, minValue);
double scaleMax = 1 + max(gridCapacityDelivery*1.2, maxValue);


//Jaar
plot_jaar.addDataSet(loadDurationCurveTotal,"Belasting jaar");
plot_jaar.setFixedVerticalScale(scaleMin, scaleMax);
if (loadCurves.ds_previousLoadDurationCurveTotal_kW != null) {
	//plot_jaar.addDataSet(area.v_dataNetbelastingDuurkrommeYearVorige_kW,"Vorige situatie");
	//plot_jaar.setColor(3,gray);
}

//summer/winter
if( loadCurves.ds_loadDurationCurveSummer_kW != null){
	DataSet loadDurationCurveSummer = uI_Results.f_createNewDataSetDividedByValue(loadCurves.ds_loadDurationCurveSummer_kW, divisionAmountForCorrectUnit);
	DataSet loadDurationCurveWinter = uI_Results.f_createNewDataSetDividedByValue(loadCurves.ds_loadDurationCurveWinter_kW, divisionAmountForCorrectUnit);
	plot_seizoen.addDataSet(loadDurationCurveSummer,"Belasting zomerweek");
	plot_seizoen.addDataSet(loadDurationCurveWinter,"Belasting winterweek");
	plot_seizoen.setColor(0,blue);
	plot_seizoen.setColor(1,green);
	plot_seizoen.setFixedVerticalScale(scaleMin, scaleMax);
}

// Day/night
if( loadCurves.ds_loadDurationCurveDaytime_kW != null){
	DataSet loadDurationCurveDaytime = uI_Results.f_createNewDataSetDividedByValue(loadCurves.ds_loadDurationCurveDaytime_kW, divisionAmountForCorrectUnit);
	DataSet loadDurationCurveNighttime = uI_Results.f_createNewDataSetDividedByValue(loadCurves.ds_loadDurationCurveNighttime_kW, divisionAmountForCorrectUnit);
	plot_dagnacht.addDataSet(loadDurationCurveDaytime,"Belasting overdag");
	plot_dagnacht.addDataSet(loadDurationCurveNighttime,"Belasting 's nachts");
	plot_dagnacht.setColor(0,blue);
	plot_dagnacht.setColor(1,green);	
	plot_dagnacht.setFixedVerticalScale(scaleMin, scaleMax);
}

// Weekday/weekend
if( loadCurves.ds_loadDurationCurveWeekday_kW != null){
	DataSet loadDurationCurveWeekday = uI_Results.f_createNewDataSetDividedByValue(loadCurves.ds_loadDurationCurveWeekday_kW, divisionAmountForCorrectUnit);
	DataSet loadDurationCurveWeekend = uI_Results.f_createNewDataSetDividedByValue(loadCurves.ds_loadDurationCurveWeekend_kW, divisionAmountForCorrectUnit);
	plot_week.addDataSet(loadDurationCurveWeekday,"Belasting weekdagen");
	plot_week.addDataSet(loadDurationCurveWeekend,"Belasting weekenddagen");
	plot_week.setColor(0,blue);
	plot_week.setColor(1,green);
	plot_week.setFixedVerticalScale(scaleMin, scaleMax);
}

//Add trafo limits
f_addTrafoLimitsGN(GN, divisionAmountForCorrectUnit); 
/*ALCODEEND*/}

double f_addTrafoLimitsGN(GridNode GN,double divisionAmountForCorrectUnit)
{/*ALCODESTART::1741699130827*/
//Add and color grid capacities
String deliveryCapacityLabel = "Geschatte fysieke trafo capaciteit afname";
String feedinCapacityLabel = "Geschatte fysieke trafo capaciteit teruglevering";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

//Create delivery and capacity year datasets
DataSet gridCapacityDelivery_kW = uI_Results.f_createFlatDataset(0, 8760, GN.p_capacity_kW / divisionAmountForCorrectUnit);
DataSet gridCapacityFeedin_kW = uI_Results.f_createFlatDataset(0, 8760, -GN.p_capacity_kW / divisionAmountForCorrectUnit);
	
if(GN.p_realCapacityAvailable){
		deliveryCapacityLabel = "Fysieke trafo capaciteit afname";
		deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
		feedinCapacityLabel = "Fysieke trafo capaciteit teruglevering";
		feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}
	
plot_jaar.addDataSet(gridCapacityDelivery_kW, deliveryCapacityLabel);
plot_jaar.addDataSet(gridCapacityFeedin_kW, feedinCapacityLabel);
plot_jaar.setColor(1, deliveryCapacityColor);
plot_jaar.setColor(2, feedinCapacityColor);

plot_week.addDataSet(gridCapacityDelivery_kW, deliveryCapacityLabel);
plot_week.addDataSet(gridCapacityFeedin_kW, feedinCapacityLabel);
plot_week.setColor(plot_week.getCount() - 2, deliveryCapacityColor);
plot_week.setColor(plot_week.getCount() - 1, feedinCapacityColor);

plot_dagnacht.addDataSet(gridCapacityDelivery_kW, deliveryCapacityLabel);
plot_dagnacht.addDataSet(gridCapacityFeedin_kW, feedinCapacityLabel);
plot_dagnacht.setColor(plot_dagnacht.getCount() - 2, deliveryCapacityColor);
plot_dagnacht.setColor(plot_dagnacht.getCount() - 1, feedinCapacityColor);

plot_seizoen.addDataSet(gridCapacityDelivery_kW, deliveryCapacityLabel);
plot_seizoen.addDataSet(gridCapacityFeedin_kW, feedinCapacityLabel);
plot_seizoen.setColor(plot_seizoen.getCount() - 2, deliveryCapacityColor);
plot_seizoen.setColor(plot_seizoen.getCount() - 1, feedinCapacityColor);

/*ALCODEEND*/}

String f_setKPIValues(I_EnergyData dataObject,J_LoadDurationCurves loadDurationCurves)
{/*ALCODESTART::1743519606875*/
////Get the peaks
double maxDelivery_kW = max(0, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMax());
double maxFeedin_kW = abs(min(0, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMin()));
double maxDeliveryAndFeedin_kW = max(maxDelivery_kW, maxFeedin_kW);


////Put it in a usefull unit format
DecimalFormat df_1decimal = new DecimalFormat("0.0");
DecimalFormat df_2decimal = new DecimalFormat("0.00");

String peakLoad_unit = " kW";
double maxDelivery_unit = maxDelivery_kW;
double maxFeedin_unit = maxFeedin_kW;

if(maxDeliveryAndFeedin_kW < 1 * pow(10,3)){
	//Do nothing, already in right format
}
else if(maxDeliveryAndFeedin_kW < 1 * pow(10,6)){
	peakLoad_unit = " MW";
	maxDelivery_unit = maxDelivery_kW/ pow(10,3);
	maxFeedin_unit = maxFeedin_kW/ pow(10,3);
}
else{
	peakLoad_unit = " GW";
	maxDelivery_unit = maxDelivery_kW/ pow(10,6);
	maxFeedin_unit = maxFeedin_kW/ pow(10,6);
}

if(maxDelivery_unit > 100){
	t_maxDelivery_MW.setText(roundToInt(maxDelivery_unit) + peakLoad_unit);
}
else if(maxDelivery_unit > 10){
	t_maxDelivery_MW.setText(df_1decimal.format(maxDelivery_unit)+ peakLoad_unit);
}
else{
	t_maxDelivery_MW.setText(df_2decimal.format(maxDelivery_unit)+ peakLoad_unit);
}

if(maxFeedin_unit > 100){
	t_maxFeedin_MW.setText(roundToInt(maxFeedin_unit)+ peakLoad_unit);
}
else if(maxFeedin_unit > 10){
	t_maxFeedin_MW.setText(df_1decimal.format(maxFeedin_unit)+ peakLoad_unit);
}
else{
	t_maxFeedin_MW.setText(df_2decimal.format(maxFeedin_unit)+ peakLoad_unit);
}

////Max peaks in percentages
if (dataObject.getRapidRunData().connectionMetaData.getContractedDeliveryCapacityKnown() && dataObject.getRapidRunData().connectionMetaData.getContractedDeliveryCapacity_kW() > 0) {
	double deliveryCapacity_kW = dataObject.getRapidRunData().connectionMetaData.getContractedDeliveryCapacity_kW();
	t_maxDelivery_pct.setText(roundToInt(maxDelivery_kW/dataObject.getRapidRunData().connectionMetaData.getContractedDeliveryCapacity_kW() * 100) + " %");
	gr_relativeDeliveryInfo.setVisible(true);
}
else{
	gr_relativeDeliveryInfo.setVisible(false);
}
if (dataObject.getRapidRunData().connectionMetaData.getContractedFeedinCapacityKnown() && dataObject.getRapidRunData().connectionMetaData.getContractedFeedinCapacity_kW() > 0) {
	double feedinCapacity_kW = dataObject.getRapidRunData().connectionMetaData.getContractedFeedinCapacity_kW();
	t_maxFeedin_pct.setText(roundToInt(maxFeedin_kW/dataObject.getRapidRunData().connectionMetaData.getContractedFeedinCapacity_kW() * 100) + " %");
	gr_relativeFeedinInfo.setVisible(true);
}
else{
	gr_relativeFeedinInfo.setVisible(false);
}


/**
* Growth KPIs
* These KPIs are based on the day with the highest average delivery
* We only show these KPIs if the (delivery) connection capacity is known
* If there is room to grow, i.e. the highest average delivery is still below the capacity we also show what flex (a battery) could add
* We do not show exact percentages above 1000%, i.e. 10x growth.
*/
if (dataObject.getRapidRunData().connectionMetaData.getContractedDeliveryCapacityKnown() && dataObject.getRapidRunData().connectionMetaData.getContractedDeliveryCapacity_kW() > 0) {
	Pair<Double, Double> pair = dataObject.getRapidRunData().getFlexPotential();
	double deliveryPeak_fr = maxDelivery_kW / dataObject.getRapidRunData().connectionMetaData.getContractedDeliveryCapacity_kW();
	int growthWithoutFlex_pct = roundToInt((100 / deliveryPeak_fr) - 100);
	if (pair.getFirst() < 1) { 
		// The current usage is already above the capacity already, do not show flex option
		gr_growthPotential.setVisible(true);
		if (growthWithoutFlex_pct < 1000) {
			t_growthPercentage.setText( growthWithoutFlex_pct + " %" );
		}
		else {
			t_growthPercentage.setText( " >1000%" );		
		}
	}
	else {
		// Also show flex option
		gr_flexGrowthPotential.setVisible(true);
		if (growthWithoutFlex_pct < 1000) {
			t_flexGrowthWithoutBatteryPercentage.setText( growthWithoutFlex_pct + " %" );
		}
		else {
			t_flexGrowthWithoutBatteryPercentage.setText( " >1000%" );		
		}
		if (pair.getSecond() < 1000) {
			t_batterySize.setText( roundToDecimal(pair.getSecond(), 1) + " kWh" );
		}
		else if (pair.getSecond() < 1_000_000) {
			t_batterySize.setText( roundToDecimal(pair.getSecond() / 1000, 1) + " MWh" );	
		}
		else {
			t_batterySize.setText( roundToDecimal(pair.getSecond() / 1_000_000, 1) + " GWh" );
		}
		int growthWithFlex_pct = roundToInt((pair.getFirst() - 1 ) * 100);
		if (growthWithFlex_pct < 1000) {
			t_flexGrowthWithBatteryPercentage.setText( growthWithFlex_pct + " %" );
		}
		else {
			t_flexGrowthWithBatteryPercentage.setText( " >1000%" );		
		}
	}
}
else {
	// Delivery Connection Capacity Unkown, do not show these KPIs
}
/*ALCODEEND*/}

double f_setKPIValuesGN(GridNode GN)
{/*ALCODESTART::1743519634438*/
////Get the peaks
J_LoadDurationCurves loadCurves = GN.f_getDuurkrommes(uI_Results.energyModel.p_timeParameters);
double maxDelivery_kW = max(0, loadCurves.ds_loadDurationCurveTotal_kW.getYMax());
double maxFeedin_kW = abs(min(0, loadCurves.ds_loadDurationCurveTotal_kW.getYMin()));
double maxDeliveryAndFeedin_kW = max(maxDelivery_kW, maxFeedin_kW);


////Put it in a usefull unit format
DecimalFormat df_1decimal = new DecimalFormat("0.0");
DecimalFormat df_2decimal = new DecimalFormat("0.00");

String peakLoad_unit = " kW";
double maxDelivery_unit = maxDelivery_kW;
double maxFeedin_unit = maxFeedin_kW;

if(maxDeliveryAndFeedin_kW < 1 * pow(10,3)){
	//Do nothing, already in right format
}
else if(maxDeliveryAndFeedin_kW < 1 * pow(10,6)){
	peakLoad_unit = " MW";
	maxDelivery_unit = maxDelivery_kW/ pow(10,3);
	maxFeedin_unit = maxFeedin_kW/ pow(10,3);
}
else{
	peakLoad_unit = " GW";
	maxDelivery_unit = maxDelivery_kW/ pow(10,6);
	maxFeedin_unit = maxFeedin_kW/ pow(10,6);
}

if(maxDelivery_unit > 100){
	t_maxDelivery_MW.setText(roundToInt(maxDelivery_unit) + peakLoad_unit);
}
else if(maxDelivery_unit > 10){
	t_maxDelivery_MW.setText(df_1decimal.format(maxDelivery_unit)+ peakLoad_unit);
}
else{
	t_maxDelivery_MW.setText(df_2decimal.format(maxDelivery_unit)+ peakLoad_unit);
}

if(maxFeedin_unit > 100){
	t_maxFeedin_MW.setText(roundToInt(maxFeedin_unit)+ peakLoad_unit);
}
else if(maxFeedin_unit > 10){
	t_maxFeedin_MW.setText(df_1decimal.format(maxFeedin_unit)+ peakLoad_unit);
}
else{
	t_maxFeedin_MW.setText(df_2decimal.format(maxFeedin_unit)+ peakLoad_unit);
}


////Max peaks in percentages
if (GN.p_realCapacityAvailable) {
	t_maxDelivery_pct.setText(roundToInt(maxDelivery_kW/GN.p_capacity_kW * 100) + " %");
	t_maxFeedin_pct.setText(roundToInt(maxFeedin_kW/GN.p_capacity_kW * 100) + " %");

	gr_relativeDeliveryInfo.setVisible(true);
	gr_relativeFeedinInfo.setVisible(true);
}
else {
	gr_relativeDeliveryInfo.setVisible(false);
	gr_relativeFeedinInfo.setVisible(false);
}

gr_flexGrowthPotential.setVisible(false);
gr_growthPotential.setVisible(false);


//Concurrency KPI
f_setGNConcurrencyKPI(GN, maxDelivery_kW);
/*ALCODEEND*/}

double f_setYlabels(String power_unit)
{/*ALCODESTART::1743761224093*/
t_netLoadDurationCurve_ylabel.setText("Vermogen [" + power_unit + "]");
t_netLoadDurationCurveSummerWinter_ylabel.setText("Vermogen [" + power_unit + "]");
t_netLoadDurationCurveDayNight_ylabel.setText("Vermogen [" + power_unit + "]");
t_netLoadDurationCurveWeekWeekend_ylabel.setText("Vermogen [" + power_unit + "]");
/*ALCODEEND*/}

double f_setCharts()
{/*ALCODESTART::1746451303236*/
f_resetPlots();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 50, true);

if(uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE){
	f_setLoadPlotsGN();
	return;
}
else {
	f_setLoadPlots();
}

f_initHeatmap();

/*ALCODEEND*/}

double f_setLoadPlots()
{/*ALCODESTART::1746451351928*/
I_EnergyData data = uI_Results.f_getSelectedObjectData();
J_LoadDurationCurves loadDurationCurves = data.getRapidRunData().getLoadDurationCurves();
f_addDataToPlots(data, loadDurationCurves);
f_setKPIValues(data, loadDurationCurves);

/*ALCODEEND*/}

double f_setGNConcurrencyKPI(GridNode GN,double maxDelivery_kW)
{/*ALCODESTART::1751374464755*/
List<GridConnection> allLowerLevelGridConnections = findAll(GN.f_getAllLowerLVLConnectedGridConnections(), gc -> gc.v_isActive);

if(allLowerLevelGridConnections.size() < 10){
	//If less than 10 GC connected to GN, KPI is not usefull to show.
	return;
}

int nrOfHouses = 0;
int nrOfChargers = 0;
int nrOfRemainingConnections = 0;
int totalNrOfConnections = 0;
for(GridConnection connectedGC : allLowerLevelGridConnections){
	if(connectedGC instanceof GCHouse){
		nrOfHouses++;
	}
	else if(connectedGC instanceof GCPublicCharger){
		nrOfChargers++;
	}
	else{
		nrOfRemainingConnections++;
	}
	totalNrOfConnections++;
}


t_numberOfHouses.setText("Huizen: " + nrOfHouses);
t_numberOfChargers.setText("Laadpalen: " + nrOfChargers);
t_numberOfRemainingConnections.setText("Overige connecties: " + nrOfRemainingConnections);

//Calculate concurrency
double concurrencyValue_kW = roundToDecimal(maxDelivery_kW/totalNrOfConnections,2);
t_concurrencyValue.setText(concurrencyValue_kW + " kW max per aansluiting");

//Set position and visiblity
gr_concurrencyKPI.setVisible(true);

gr_absoluteDeliveryInfo.setPos(105, 370);
gr_relativeDeliveryInfo.setPos(85, 490);
gr_absoluteFeedinInfo.setPos(235, 370);
gr_relativeFeedinInfo.setPos(235, 490);

/*ALCODEEND*/}

double f_makeHeatmapTrafo()
{/*ALCODESTART::1780986968762*/
I_EnergyData data = uI_Results.f_getSelectedObjectData();
J_LoadDurationCurves loadDurationCurves = data.getRapidRunData().getLoadDurationCurves();

////Get the peaks
double maxDelivery_kW = max(0, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMax());
double maxFeedin_kW = abs(min(0, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMin()));
double maxDeliveryAndFeedin_kW = max(maxDelivery_kW, maxFeedin_kW);

double heatmapScalingLimit;

if( v_heatmapColorSetting == OL_heatmapColorSetting.FIVEKW){
	heatmapScalingLimit = 5;
}
else if( v_heatmapColorSetting == OL_heatmapColorSetting.MAX){
	heatmapScalingLimit = maxDeliveryAndFeedin_kW;
}
else if( v_heatmapColorSetting == OL_heatmapColorSetting.TRAFO){
	heatmapScalingLimit = uI_Results.f_getSelectedObjectData().getRapidRunData().connectionMetaData.getContractedDeliveryCapacity_kW();
}
else {
	heatmapScalingLimit = maxDeliveryAndFeedin_kW; // by default the max setting
}

J_AccumulatorMap<OL_AssetFlowCategories> dataset = data.getRapidRunData().am_assetFlowsAccumulators_kW;


double[][] peakMap = new double[12][24]; // max kW per (maand, uur)
    // Initialiseer op 0 (niet op MIN_VALUE) want we willen 0 als baseline
    
    int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  	for (var EC : dataset.keySet()) {
	    if (!uI_Results.v_electricAssetFlows.contains(EC)) continue;
	    ZeroAccumulator acc = dataset.get(EC);
	    double res_h = acc.getSignalResolution_h();
	    double[] ts =  
	    acc.getTimeSeries_kW();
	    boolean isConsumption = uI_Results.v_consumptionAssetFlows.contains(EC);
	    double sign = isConsumption ? 1.0 : -1.0;
	    int sampleIndex = 0;
	    for (int m = 0; m < 12; m++) {
	        int samplesInMonth = (int) Math.round(daysInMonth[m] * 24.0 / res_h);
	        for (int s = 0; s < samplesInMonth; s++) {
	            int hour = (int) ((s * res_h) % 24);
	            double val = ts[sampleIndex] * sign;
	            if (Math.abs(val) > Math.abs(peakMap[m][hour])) {
	                peakMap[m][hour] = val;
	            }
	            sampleIndex++;
	        }
	    }
	}
    // Tiles inkleuren
    if (v_heatmapTiles == null || v_heatmapTiles.length != 12) {
        v_heatmapTiles = new ShapeRectangle[][] {
            {jan0,  jan1,  jan2,  jan3,  jan4,  jan5,  jan6,  jan7,  jan8,  jan9,  jan10, jan11, jan12, jan13, jan14, jan15, jan16, jan17, jan18, jan19, jan20, jan21, jan22, jan23},
            {feb0,  feb1,  feb2,  feb3,  feb4,  feb5,  feb6,  feb7,  feb8,  feb9,  feb10, feb11, feb12, feb13, feb14, feb15, feb16, feb17, feb18, feb19, feb20, feb21, feb22, feb23},
            {mar0,  mar1,  mar2,  mar3,  mar4,  mar5,  mar6,  mar7,  mar8,  mar9,  mar10, mar11, mar12, mar13, mar14, mar15, mar16, mar17, mar18, mar19, mar20, mar21, mar22, mar23},
            {apr0,  apr1,  apr2,  apr3,  apr4,  apr5,  apr6,  apr7,  apr8,  apr9,  apr10, apr11, apr12, apr13, apr14, apr15, apr16, apr17, apr18, apr19, apr20, apr21, apr22, apr23},
            {may0,  may1,  may2,  may3,  may4,  may5,  may6,  may7,  may8,  may9,  may10, may11, may12, may13, may14, may15, may16, may17, may18, may22, may22, may22, may22, may23},
            {jun0,  jun1,  jun2,  jun3,  jun4,  jun5,  jun6,  jun7,  jun8,  jun9,  jun10, jun11, jun12, jun13, jun14, jun15, jun16, jun17, jun18, jun19, jun20, jun21, jun22, jun23},
            {jul0,  jul1,  jul2,  jul3,  jul4,  jul5,  jul6,  jul7,  jul8,  jul9,  jul10, jul11, jul12, jul13, jul14, jul15, jul16, jul17, jul18, jul19, jul20, jul21, jul22, jul23},
            {aug0,  aug1,  aug2,  aug3,  aug4,  aug5,  aug6,  aug7,  aug8,  aug9,  aug10, aug11, aug12, aug13, aug14, aug15, aug16, aug17, aug18, aug19, aug20, aug21, aug22, aug23},
            {sep0,  sep1,  sep2,  sep3,  sep4,  sep5,  sep6,  sep7,  sep8,  sep9,  sep10, sep11, sep12, sep13, sep14, sep15, sep16, sep17, sep18, sep19, sep20, sep21, sep22, sep23},
            {okt0,  okt1,  okt2,  okt3,  okt4,  okt5,  okt6,  okt7,  okt8,  okt9,  okt10, okt11, okt12, okt13, okt14, okt15, okt16, okt17, okt18, okt19, okt20, okt21, okt22, okt23},
            {nov0,  nov1,  nov2,  nov3,  nov4,  nov5,  nov6,  nov7,  nov8,  nov9,  nov10, nov11, nov12, nov13, nov14, nov15, nov16, nov17, nov18, nov19, nov20, nov21, nov22, nov23},
            {dec0,  dec1,  dec2,  dec3,  dec4,  dec5,  dec6,  dec7,  dec8,  dec9,  dec10, dec11, dec12, dec13, dec14, dec15, dec16, dec17, dec18, dec19, dec20, dec21, dec22, dec23}
        };
    }
    for (int m = 0; m < 12; m++) {
        for (int h = 0; h < 24; h++) {
            v_heatmapTiles[m][h].setFillColor(f_heatmapColorTrafo(peakMap[m][h], heatmapScalingLimit));
        }
    }
/*ALCODEEND*/}

Color f_heatmapColorTrafo(double value_kW,double trafoCapacity_kW)
{/*ALCODESTART::1780986968789*/
int r, g, b;
    if (value_kW >= 0) {
        // Positief: wit -> geel -> oranje -> rood -> donkerrood
         traceln("Positieve waarde: " + value_kW + ", trafo cap: " + trafoCapacity_kW);
        double t = Math.min(value_kW / trafoCapacity_kW, 1.2); // cap iets boven 1 voor donkerrood
        if (t < 0.5) {
            // wit -> geel
            r = 255;
            g = 255;
            b = (int)(255 * (1 - t / 0.5));
        } else if (t < 0.9) {
            // geel -> oranje
            r = 255;
            g = (int)(255 * (1 - (t - 0.5) / 0.4 * 0.5)); // 255 -> 128
            b = 0;
        } else if (t < 1.0) {
            // oranje -> rood
            r = 255;
            g = (int)(128 * (1 - (t - 0.9) / 0.1));
            b = 0;
        } else {
            // rood -> donkerrood
            r = (int)(255 * (1 - Math.min((t - 1.0) / 0.2, 1.0) * 0.5));
            g = 0;
            b = 0;
        }
    } else {
        // Negatief: wit -> groen -> blauw -> donkerblauw
        traceln("Negatieve waarde: " + value_kW + ", trafo cap: " + trafoCapacity_kW);
        double t = Math.min(Math.abs(value_kW) / trafoCapacity_kW, 1.2);
        if (t < 0.5) {
            // wit -> groen
            r = (int)(255 * (1 - t / 0.5));
            g = 255;
            b = (int)(255 * (1 - t / 0.5));
        } else if (t < 0.9) {
            // groen -> blauw
            r = 0;
            g = (int)(255 * (1 - (t - 0.5) / 0.4));
            b = (int)(255 * (t - 0.5) / 0.4);
        } else if (t < 1.0) {
            // blauw -> donkerblauw
            r = 0;
            g = 0;
            b = 255;
        } else {
            // donkerblauw
            r = 0;
            g = 0;
            b = (int)(255 * (1 - Math.min((t - 1.0) / 0.2, 1.0) * 0.5));
        }
    }
    return new Color(r, g, b);
/*ALCODEEND*/}

double f_initHeatmap()
{/*ALCODESTART::1780986987293*/
v_heatmapTiles = new ShapeRectangle[][] {
      {jan0,  jan1,  jan2,  jan3,  jan4,  jan5,  jan6,  jan7,  jan8,  jan9,  jan10, jan11, jan12, jan13, jan14, jan15, jan16, jan17, jan18, jan19, jan20, jan21, jan22, jan23},
        {feb0,  feb1,  feb2,  feb3,  feb4,  feb5,  feb6,  feb7,  feb8,  feb9,  feb10, feb11, feb12, feb13, feb14, feb15, feb16, feb17, feb18, feb19, feb20, feb21, feb22, feb23},
        {mar0,  mar1,  mar2,  mar3,  mar4,  mar5,  mar6,  mar7,  mar8,  mar9,  mar10, mar11, mar12, mar13, mar14, mar15, mar16, mar17, mar18, mar19, mar20, mar21, mar22, mar23},
        {apr0,  apr1,  apr2,  apr3,  apr4,  apr5,  apr6,  apr7,  apr8,  apr9,  apr10, apr11, apr12, apr13, apr14, apr15, apr16, apr17, apr18, apr19, apr20, apr21, apr22, apr23},
        {may0,  may1,  may2,  may3,  may4,  may5,  may6,  may7,  may8,  may9,  may10, may11, may12, may13, may14, may15, may16, may17, may18, may19, may20, may21, may22, may23},
        {jun0,  jun1,  jun2,  jun3,  jun4,  jun5,  jun6,  jun7,  jun8,  jun9,  jun10, jun11, jun12, jun13, jun14, jun15, jun16, jun17, jun18, jun19, jun20, jun21, jun22, jun23},
        {jul0,  jul1,  jul2,  jul3,  jul4,  jul5,  jul6,  jul7,  jul8,  jul9,  jul10, jul11, jul12, jul13, jul14, jul15, jul16, jul17, jul18, jul19, jul20, jul21, jul22, jul23},
        {aug0,  aug1,  aug2,  aug3,  aug4,  aug5,  aug6,  aug7,  aug8,  aug9,  aug10, aug11, aug12, aug13, aug14, aug15, aug16, aug17, aug18, aug19, aug20, aug21, aug22, aug23},
        {sep0,  sep1,  sep2,  sep3,  sep4,  sep5,  sep6,  sep7,  sep8,  sep9,  sep10, sep11, sep12, sep13, sep14, sep15, sep16, sep17, sep18, sep19, sep20, sep21, sep22, sep23},
        {okt0,  okt1,  okt2,  okt3,  okt4,  okt5,  okt6,  okt7,  okt8,  okt9,  okt10, okt11, okt12, okt13, okt14, okt15, okt16, okt17, okt18, okt19, okt20, okt21, okt22, okt23},
        {nov0,  nov1,  nov2,  nov3,  nov4,  nov5,  nov6,  nov7,  nov8,  nov9,  nov10, nov11, nov12, nov13, nov14, nov15, nov16, nov17, nov18, nov19, nov20, nov21, nov22, nov23},
        {dec0,  dec1,  dec2,  dec3,  dec4,  dec5,  dec6,  dec7,  dec8,  dec9,  dec10, dec11, dec12, dec13, dec14, dec15, dec16, dec17, dec18, dec19, dec20, dec21, dec22, dec23}
    };
/*ALCODEEND*/}

double f_resetButtonColors()
{/*ALCODESTART::1781000471002*/
button_1.setFillColor( v_chartButtonBaseColor);
button_2.setFillColor( v_chartButtonBaseColor);
button_3.setFillColor( v_chartButtonBaseColor);
//button_4.setFillColor( v_chartButtonBaseColor);


text_button1.setColor(white);
text_button2.setColor(white);
text_button3.setColor(white);
//text_button4.setColor(white);

/*ALCODEEND*/}

