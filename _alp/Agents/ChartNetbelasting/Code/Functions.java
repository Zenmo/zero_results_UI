double f_setBelastingPlots()
{/*ALCODESTART::1714746143909*/
f_resetPlots();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 50, true);

if(uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE){
	f_setBelastingPlotsGN();
	return;
}

I_EnergyData data = uI_Results.f_getSelectedObjectData();
J_LoadDurationCurves loadDurationCurves = data.getRapidRunData().getLoadDurationCurves(uI_Results.energyModel);

f_addDataToPlots(data, loadDurationCurves);
f_setMaxPeakValues(data, loadDurationCurves);


f_setNetAverageLoad(data, loadDurationCurves);
/*ALCODEEND*/}

double f_setNetAverageLoad(I_EnergyData dataObject,J_LoadDurationCurves loadDurationCurves)
{/*ALCODESTART::1714746143911*/
double simulationDuration = uI_Results.energyModel.p_runEndTime_h - uI_Results.energyModel.p_runStartTime_h;
double benuttingsgraad_pct = 0;
double connectionCapacity_delivery_MW = dataObject.getRapidRunData().connectionMetaData.contractedDeliveryCapacity_kW/1000;
double connectionCapacity_feedin_MW = dataObject.getRapidRunData().connectionMetaData.contractedFeedinCapacity_kW/1000;
double totalImportElectricity_MWh = dataObject.getRapidRunData().getTotalImport_MWh(OL_EnergyCarriers.ELECTRICITY);
double totalExportElectricity_MWh = dataObject.getRapidRunData().getTotalExport_MWh(OL_EnergyCarriers.ELECTRICITY);

if(	connectionCapacity_delivery_MW > 0 && connectionCapacity_feedin_MW > 0){
	benuttingsgraad_pct = ((totalImportElectricity_MWh / connectionCapacity_delivery_MW) + (totalExportElectricity_MWh / connectionCapacity_feedin_MW)) / (simulationDuration) * 100;
}
else if( connectionCapacity_delivery_MW  > 0){
	benuttingsgraad_pct = (totalImportElectricity_MWh / connectionCapacity_delivery_MW)/ (simulationDuration) * 100;
}
else if(connectionCapacity_feedin_MW > 0){
	benuttingsgraad_pct = (totalExportElectricity_MWh / connectionCapacity_feedin_MW)/ (simulationDuration) * 100;
}

t_KPIBenuttingsgraad.setText(roundToDecimal(benuttingsgraad_pct, 0) + "%");
/*ALCODEEND*/}

double f_resetPlots()
{/*ALCODESTART::1714922345475*/
plot_jaar.removeAll();
plot_week.removeAll();
plot_dagnacht.removeAll();
plot_seizoen.removeAll();
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
f_setYlabels_loadDurationCurve(power_unit);

DataSet loadDurationCurveTotal = uI_Results.f_createNewDataSetDividedByValue(loadDurationCurves.ds_loadDurationCurveTotal_kW, divisionAmountForCorrectUnit);
double minValue = loadDurationCurveTotal.getYMin();
double maxValue = loadDurationCurveTotal.getYMax();

double gridCapacityDelivery = dataObject.getRapidRunData().connectionMetaData.contractedDeliveryCapacity_kW / divisionAmountForCorrectUnit;
double gridCapacityFeedin = dataObject.getRapidRunData().connectionMetaData.contractedFeedinCapacity_kW / divisionAmountForCorrectUnit;

double scaleMin = -1 + min(-gridCapacityFeedin*1.2, minValue);
double scaleMax = 1 + max(gridCapacityDelivery*1.2, maxValue);


//Jaar
plot_jaar.addDataSet(loadDurationCurveTotal,"Belasting jaar");
plot_jaar.setFixedVerticalScale(scaleMin, scaleMax);

//summer/winter
DataSet loadDurationCurveSummer = uI_Results.f_createNewDataSetDividedByValue(loadDurationCurves.ds_loadDurationCurveSummer_kW, divisionAmountForCorrectUnit);
DataSet loadDurationCurveWinter = uI_Results.f_createNewDataSetDividedByValue(loadDurationCurves.ds_loadDurationCurveWinter_kW, divisionAmountForCorrectUnit);
plot_seizoen.addDataSet(loadDurationCurveSummer,"Belasting zomerweek");
plot_seizoen.addDataSet(loadDurationCurveWinter,"Belasting winterweek");
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
DataSet gridCapacityDelivery_kW = uI_Results.f_createFlatDataset(0, 8760, dataObject.getRapidRunData().connectionMetaData.contractedDeliveryCapacity_kW / divisionAmountForCorrectUnit);
DataSet gridCapacityFeedin_kW = uI_Results.f_createFlatDataset(0, 8760, -dataObject.getRapidRunData().connectionMetaData.contractedFeedinCapacity_kW / divisionAmountForCorrectUnit);

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
		DataSet groupContractCapacityDelivery_kW = uI_Results.f_createFlatDataset(0, 8760, ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getGroupContractDeliveryCapacity_kW() / divisionAmountForCorrectUnit);
		DataSet groupContractCapacityFeedin_kW = uI_Results.f_createFlatDataset(0, 8760, -((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getGroupContractFeedinCapacity_kW() / divisionAmountForCorrectUnit);
		
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
if(!dataObject.getRapidRunData().connectionMetaData.contractedDeliveryCapacityKnown){
	deliveryCapacityLabel = "Geschatte " + deliveryCapacityLabel.substring(0, 1).toLowerCase() + deliveryCapacityLabel.substring(1);
	deliveryCapacityColor = uI_Results.v_electricityCapacityColor_estimated;
}
if(!dataObject.getRapidRunData().connectionMetaData.contractedFeedinCapacityKnown){
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

double f_setBelastingPlotsGN()
{/*ALCODESTART::1741699130819*/
GridNode GN = uI_Results.v_gridNode;

f_addDataToPlotsGN(GN);

f_setMaxPeakValuesGN(GN); 
f_setNetAverageLoadGN(GN);
/*ALCODEEND*/}

double f_setNetAverageLoadGN(GridNode GN)
{/*ALCODESTART::1741699130821*/
double simulationDuration = uI_Results.energyModel.p_runEndTime_h - uI_Results.energyModel.p_runStartTime_h;
double benuttingsgraad = 0;
double totalAbsoluteLoad_kW = 0;
for(int i=0; i< GN.data_netbelastingDuurkromme_kW.size(); i++){
	totalAbsoluteLoad_kW += abs(GN.data_netbelastingDuurkromme_kW.getY(i))* uI_Results.energyModel.p_timeStep_h;
}
benuttingsgraad = (totalAbsoluteLoad_kW / (GN.p_capacity_kW * simulationDuration )) * 100;
t_KPIBenuttingsgraad.setText(roundToDecimal(benuttingsgraad, 0) + "%");
/*ALCODEEND*/}

double f_addDataToPlotsGN(GridNode GN)
{/*ALCODESTART::1741699130825*/
double divisionAmountForCorrectUnit = 1;
String power_unit = "kW";

////Get the peaks
double maxDelivery_kW = max(0, GN.data_netbelastingDuurkromme_kW.getYMax());
double maxFeedin_kW = abs(min(0, GN.data_netbelastingDuurkromme_kW.getYMin()));
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
f_setYlabels_loadDurationCurve(power_unit);

DataSet loadDurationCurveTotal = uI_Results.f_createNewDataSetDividedByValue(GN.data_netbelastingDuurkromme_kW, divisionAmountForCorrectUnit);
double minValue = loadDurationCurveTotal.getYMin();
double maxValue = loadDurationCurveTotal.getYMax();

double gridCapacityDelivery = GN.p_capacity_kW / divisionAmountForCorrectUnit;
double gridCapacityFeedin = GN.p_capacity_kW / divisionAmountForCorrectUnit;

double scaleMin = -1 + min(-gridCapacityFeedin*1.2, minValue);
double scaleMax = 1 + max(gridCapacityDelivery*1.2, maxValue);


//Jaar
plot_jaar.addDataSet(loadDurationCurveTotal,"Belasting jaar");
plot_jaar.setFixedVerticalScale(scaleMin, scaleMax);
if (GN.data_netbelastingDuurkrommeVorige_kW != null) {
	//plot_jaar.addDataSet(area.v_dataNetbelastingDuurkrommeYearVorige_kW,"Vorige situatie");
	//plot_jaar.setColor(3,gray);
}

//summer/winter
if( GN.data_summerWeekNetbelastingDuurkromme_kW != null){
	DataSet loadDurationCurveSummer = uI_Results.f_createNewDataSetDividedByValue(GN.data_summerWeekNetbelastingDuurkromme_kW, divisionAmountForCorrectUnit);
	DataSet loadDurationCurveWinter = uI_Results.f_createNewDataSetDividedByValue(GN.data_winterWeekNetbelastingDuurkromme_kW, divisionAmountForCorrectUnit);
	plot_seizoen.addDataSet(loadDurationCurveSummer,"Belasting zomerweek");
	plot_seizoen.addDataSet(loadDurationCurveWinter,"Belasting winterweek");
	plot_seizoen.setColor(0,blue);
	plot_seizoen.setColor(1,green);
	plot_seizoen.setFixedVerticalScale(scaleMin, scaleMax);
}

// Day/night
if( GN.data_daytimeNetbelastingDuurkromme_kW != null){
	DataSet loadDurationCurveDaytime = uI_Results.f_createNewDataSetDividedByValue(GN.data_daytimeNetbelastingDuurkromme_kW, divisionAmountForCorrectUnit);
	DataSet loadDurationCurveNighttime = uI_Results.f_createNewDataSetDividedByValue(GN.data_nighttimeNetbelastingDuurkromme_kW, divisionAmountForCorrectUnit);
	plot_dagnacht.addDataSet(loadDurationCurveDaytime,"Belasting overdag");
	plot_dagnacht.addDataSet(loadDurationCurveNighttime,"Belasting 's nachts");
	plot_dagnacht.setColor(0,blue);
	plot_dagnacht.setColor(1,green);	
	plot_dagnacht.setFixedVerticalScale(scaleMin, scaleMax);
}

// Weekday/weekend
if( GN.data_weekdayNetbelastingDuurkromme_kW != null){
	DataSet loadDurationCurveWeekday = uI_Results.f_createNewDataSetDividedByValue(GN.data_weekdayNetbelastingDuurkromme_kW, divisionAmountForCorrectUnit);
	DataSet loadDurationCurveWeekend = uI_Results.f_createNewDataSetDividedByValue(GN.data_weekendNetbelastingDuurkromme_kW, divisionAmountForCorrectUnit);
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

String f_setMaxPeakValues(I_EnergyData dataObject,J_LoadDurationCurves loadDurationCurves)
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
double deliveryCapacity_kW = dataObject.getRapidRunData().connectionMetaData.contractedDeliveryCapacity_kW;
double feedinCapacity_kW = dataObject.getRapidRunData().connectionMetaData.contractedFeedinCapacity_kW;
if(deliveryCapacity_kW > 0){
	t_maxDelivery_pct.setText(roundToInt(maxDelivery_kW/dataObject.getRapidRunData().connectionMetaData.contractedDeliveryCapacity_kW * 100) + " %");
}
else{
	t_maxDelivery_pct.setText("-");
}
if(feedinCapacity_kW > 0){
	t_maxFeedin_pct.setText(roundToInt(maxFeedin_kW/dataObject.getRapidRunData().connectionMetaData.contractedFeedinCapacity_kW * 100) + " %");
}
else{
	t_maxFeedin_pct.setText("-");
}
/*ALCODEEND*/}

double f_setMaxPeakValuesGN(GridNode GN)
{/*ALCODESTART::1743519634438*/
////Get the peaks
double maxDelivery_kW = max(0, GN.data_netbelastingDuurkromme_kW.getYMax());
double maxFeedin_kW = abs(min(0, GN.data_netbelastingDuurkromme_kW.getYMin()));
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
t_maxDelivery_pct.setText(roundToInt(maxDelivery_kW/GN.p_capacity_kW * 100) + " %");
t_maxFeedin_pct.setText(roundToInt(maxFeedin_kW/GN.p_capacity_kW * 100) + " %");
/*ALCODEEND*/}

double f_setYlabels_loadDurationCurve(String power_unit)
{/*ALCODESTART::1743761224093*/
t_duurkromme_ylabel.setText("Vermogen [" + power_unit + "]");
t_duurkromme_ylabel1.setText("Vermogen [" + power_unit + "]");
t_duurkromme_ylabel2.setText("Vermogen [" + power_unit + "]");
t_duurkromme_ylabel3.setText("Vermogen [" + power_unit + "]");
/*ALCODEEND*/}

