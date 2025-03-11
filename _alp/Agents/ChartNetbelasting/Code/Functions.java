double f_setBelastingPlots()
{/*ALCODESTART::1714746143909*/
f_resetPlots();

if(uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE){
	f_setBelastingPlotsGN();
	return;
}

I_EnergyData data = uI_Results.f_getSelectedObjectData();
J_LoadDurationCurves loadDurationCurves = data.getRapidRunData().getLoadDurationCurves(uI_Results.energyModel);

f_addDataToPlots(data, loadDurationCurves);
f_addTrafoLimits(data); 

double maxDemand_kW = max(0, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMax());
double maxSupply_kW = abs(min(0, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMin()));
double maxDemandSupply_kW = max(maxDemand_kW, maxSupply_kW);
/*
if (maxDemandSupply_kW < 1 * pow(10,3)) {
	tx_maxDemand.setText(roundToDecimal(maxDemand_kW, 0) + " kW");
	tx_maxSupply.setText(roundToDecimal(maxSupply_kW, 0) + " kW");
} else if (maxDemandSupply_kW<1 * pow(10,6)) {
	tx_maxDemand.setText(roundToDecimal(maxDemand_kW / pow(10,3), 0) + " MW");
	tx_maxSupply.setText(roundToDecimal(maxSupply_kW / pow(10,3), 0) + " MW");
} else {
	tx_maxDemand.setText(roundToDecimal(maxDemand_kW / pow(10,6), 1) + " GW");
	tx_maxSupply.setText(roundToDecimal(maxSupply_kW / pow(10,6), 1) + " GW");
} */

if (maxDemandSupply_kW < 1 * pow(10,3)) {
	tx_maxDemand.setText(String.format("%.0f", maxDemand_kW) + " kW");
	tx_maxSupply.setText(String.format("%.0f", maxSupply_kW) + " kW");
} else if (maxDemandSupply_kW<1 * pow(10,6)) {
	tx_maxDemand.setText(String.format("%.2f", maxDemand_kW / pow(10,3)) + " MW");
	tx_maxSupply.setText(String.format("%.2f", maxSupply_kW / pow(10,3)) + " MW");
	//tx_maxDemand.setText(roundToDecimal(maxDemand_kW / pow(10,3), 0) + " MW");
	//tx_maxSupply.setText(roundToDecimal(maxSupply_kW / pow(10,3), 0) + " MW");
} else {
	tx_maxDemand.setText(String.format("%.2f", maxDemand_kW / pow(10,6)) + " GW");
	tx_maxSupply.setText(String.format("%.2f", maxSupply_kW / pow(10,6)) + " GW");
	//tx_maxDemand.setText(roundToDecimal(maxDemand_kW / pow(10,6), 1) + " GW");
	//tx_maxSupply.setText(roundToDecimal(maxSupply_kW / pow(10,6), 1) + " GW");
} 


f_setNetAverageLoad(data, loadDurationCurves);
/*ALCODEEND*/}

double f_setNetAverageLoad(I_EnergyData dataObject,J_LoadDurationCurves loadDurationCurves)
{/*ALCODESTART::1714746143911*/
double benuttingsgraad = 0;
double totalAbsoluteLoad_kW = 0;
for(int i=0; i< loadDurationCurves.ds_loadDurationCurveTotal_kW.size(); i++){
	totalAbsoluteLoad_kW += abs(loadDurationCurves.ds_loadDurationCurveTotal_kW.getY(i))* uI_Results.energyModel.p_timeStep_h;
}
benuttingsgraad = totalAbsoluteLoad_kW / ((dataObject.getDeliveryCapacity_kW() + dataObject.getFeedinCapacity_kW()) * 8760) * 100;
t_KPIBenuttingsgraad.setText(roundToDecimal(benuttingsgraad, 0) + "%");
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
double scaleMin_kW;
double scaleMax_kW;

double gridCapacityDelivery_kW = 0;//dataObject.
double gridCapacityFeedin_kW = 0;//dataObject.

//Jaar
plot_jaar.addDataSet(loadDurationCurves.ds_loadDurationCurveTotal_kW,"Belasting jaar");
scaleMin_kW = -1 + min(-gridCapacityFeedin_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMin());
scaleMax_kW = 1 + max(gridCapacityDelivery_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMax());
plot_jaar.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);
//if (area.v_dataNetbelastingDuurkrommeYearVorige_kW != null) {
	//plot_jaar.addDataSet(area.v_dataNetbelastingDuurkrommeYearVorige_kW,"Vorige situatie");
	//plot_jaar.setColor(3,gray);
//}

//summer/winter
plot_seizoen.addDataSet(loadDurationCurves.ds_loadDurationCurveSummer_kW,"Belasting zomerweek");
plot_seizoen.addDataSet(loadDurationCurves.ds_loadDurationCurveWinter_kW,"Belasting winterweek");
plot_seizoen.setColor(0,blue);
plot_seizoen.setColor(1,green);
scaleMin_kW = -1 + min(-gridCapacityFeedin_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMin());
scaleMax_kW = 1 + max(gridCapacityDelivery_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMax());
plot_seizoen.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);


// Day/night
plot_dagnacht.addDataSet(loadDurationCurves.ds_loadDurationCurveDaytime_kW,"Belasting overdag");
plot_dagnacht.addDataSet(loadDurationCurves.ds_loadDurationCurveNighttime_kW,"Belasting 's nachts");
plot_dagnacht.setColor(0,blue);
plot_dagnacht.setColor(1,green);	
scaleMin_kW = -1 + min(-gridCapacityFeedin_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMin());
scaleMax_kW = 1 + max(gridCapacityDelivery_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMax());
plot_dagnacht.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);


// Weekday/weekend
plot_week.addDataSet(loadDurationCurves.ds_loadDurationCurveWeekday_kW,"Belasting weekdagen");
plot_week.addDataSet(loadDurationCurves.ds_loadDurationCurveWeekend_kW,"Belasting weekenddagen");
plot_week.setColor(0,blue);
plot_week.setColor(1,green);
scaleMin_kW = -1 + min(-gridCapacityFeedin_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMin());
scaleMax_kW = 1 + max(gridCapacityDelivery_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMax());
plot_week.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);

/*ALCODEEND*/}

double f_addTrafoLimits(I_EnergyData dataObject)
{/*ALCODESTART::1740584474407*/
//Add and color grid capacities
String deliveryCapacityLabel = "Geschatte capaciteit afname";
String feedinCapacityLabel = "Geschatte capaciteit teruglevering";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

//Create delivery and capacity year datasets
DataSet gridCapacityDelivery_kW = uI_Results.f_createFlatDataset(0, 8760, dataObject.getDeliveryCapacity_kW());
DataSet gridCapacityFeedin_kW = uI_Results.f_createFlatDataset(0, 8760, -dataObject.getFeedinCapacity_kW());
	
if(uI_Results.b_showGroupContractValues && uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYCOOP){
	deliveryCapacityLabel = "Cumulatieve GTV afname van bedrijven";
	deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	feedinCapacityLabel = "Cumulatieve GTV teruglevering van bedrijven";
	feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	
	//And: add group contract values
	DataSet groupContractCapacityDelivery_kW = uI_Results.f_createFlatDataset(0, 8760, ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getGroupContractDeliveryCapacity_kW());
	DataSet groupContractCapacityFeedin_kW = uI_Results.f_createFlatDataset(0, 8760, -((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getGroupContractFeedinCapacity_kW());
	
	plot_jaar.addDataSet(groupContractCapacityDelivery_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_jaar.addDataSet(groupContractCapacityFeedin_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	plot_week.addDataSet(groupContractCapacityDelivery_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_week.addDataSet(groupContractCapacityFeedin_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	plot_dagnacht.addDataSet(groupContractCapacityDelivery_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_dagnacht.addDataSet(groupContractCapacityFeedin_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	plot_seizoen.addDataSet(groupContractCapacityDelivery_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_seizoen.addDataSet(groupContractCapacityFeedin_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
}
else{
	if(dataObject.getDeliveryCapacityKnown()){
		deliveryCapacityLabel = "Capaciteit afname";
		deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	}
	if(dataObject.getFeedinCapacityKnown()){
		feedinCapacityLabel = "Capaciteit teruglevering";
		feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	}
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
f_addTrafoLimitsGN(GN); 

double maxDemand_kW = max(0, GN.data_netbelastingDuurkromme_kW.getYMax());
double maxSupply_kW = abs(min(0, GN.data_netbelastingDuurkromme_kW.getYMin()));
double maxDemandSupply_kW = max(maxDemand_kW, maxSupply_kW);
/*
if (maxDemandSupply_kW < 1 * pow(10,3)) {
	tx_maxDemand.setText(roundToDecimal(maxDemand_kW, 0) + " kW");
	tx_maxSupply.setText(roundToDecimal(maxSupply_kW, 0) + " kW");
} else if (maxDemandSupply_kW<1 * pow(10,6)) {
	tx_maxDemand.setText(roundToDecimal(maxDemand_kW / pow(10,3), 0) + " MW");
	tx_maxSupply.setText(roundToDecimal(maxSupply_kW / pow(10,3), 0) + " MW");
} else {
	tx_maxDemand.setText(roundToDecimal(maxDemand_kW / pow(10,6), 1) + " GW");
	tx_maxSupply.setText(roundToDecimal(maxSupply_kW / pow(10,6), 1) + " GW");
} */

if (maxDemandSupply_kW < 1 * pow(10,3)) {
	tx_maxDemand.setText(String.format("%.0f", maxDemand_kW) + " kW");
	tx_maxSupply.setText(String.format("%.0f", maxSupply_kW) + " kW");
} else if (maxDemandSupply_kW<1 * pow(10,6)) {
	tx_maxDemand.setText(String.format("%.2f", maxDemand_kW / pow(10,3)) + " MW");
	tx_maxSupply.setText(String.format("%.2f", maxSupply_kW / pow(10,3)) + " MW");
	//tx_maxDemand.setText(roundToDecimal(maxDemand_kW / pow(10,3), 0) + " MW");
	//tx_maxSupply.setText(roundToDecimal(maxSupply_kW / pow(10,3), 0) + " MW");
} else {
	tx_maxDemand.setText(String.format("%.2f", maxDemand_kW / pow(10,6)) + " GW");
	tx_maxSupply.setText(String.format("%.2f", maxSupply_kW / pow(10,6)) + " GW");
	//tx_maxDemand.setText(roundToDecimal(maxDemand_kW / pow(10,6), 1) + " GW");
	//tx_maxSupply.setText(roundToDecimal(maxSupply_kW / pow(10,6), 1) + " GW");
} 


f_setNetAverageLoadGN(GN);
/*ALCODEEND*/}

double f_setNetAverageLoadGN(GridNode GN)
{/*ALCODESTART::1741699130821*/
double benuttingsgraad = 0;
double totalAbsoluteLoad_kW = 0;
for(int i=0; i< GN.data_netbelastingDuurkromme_kW.size(); i++){
	totalAbsoluteLoad_kW += abs(GN.data_netbelastingDuurkromme_kW.getY(i))* uI_Results.energyModel.p_timeStep_h;
}
benuttingsgraad = totalAbsoluteLoad_kW / ((GN.p_capacity_kW + GN.p_capacity_kW) * 8760) * 100;
t_KPIBenuttingsgraad.setText(roundToDecimal(benuttingsgraad, 0) + "%");
/*ALCODEEND*/}

double f_addDataToPlotsGN(GridNode GN)
{/*ALCODESTART::1741699130825*/
double scaleMin_kW;
double scaleMax_kW;

//Jaar
plot_jaar.addDataSet(GN.data_netbelastingDuurkromme_kW,"Belasting jaar");
scaleMin_kW = -1 + min(-GN.p_capacity_kW*1.2, GN.data_netbelastingDuurkromme_kW.getYMin());
scaleMax_kW = 1 + max(GN.p_capacity_kW*1.2, GN.data_netbelastingDuurkromme_kW.getYMax());
plot_jaar.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);
if (GN.data_netbelastingDuurkrommeVorige_kW != null) {
	//plot_jaar.addDataSet(area.v_dataNetbelastingDuurkrommeYearVorige_kW,"Vorige situatie");
	//plot_jaar.setColor(3,gray);
}

//summer/winter
if( GN.data_summerWeekNetbelastingDuurkromme_kW != null){
	plot_seizoen.addDataSet(GN.data_summerWeekNetbelastingDuurkromme_kW,"Belasting zomerweek");
	plot_seizoen.addDataSet(GN.data_winterWeekNetbelastingDuurkromme_kW,"Belasting winterweek");
	plot_seizoen.setColor(0,blue);
	plot_seizoen.setColor(1,green);
	scaleMin_kW = -1 + min(-GN.p_capacity_kW*1.2, GN.data_netbelastingDuurkromme_kW.getYMin());
	scaleMax_kW = 1 + max(GN.p_capacity_kW*1.2, GN.data_netbelastingDuurkromme_kW.getYMax());
	plot_seizoen.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);
}

// Day/night
if( GN.data_daytimeNetbelastingDuurkromme_kW != null){
	plot_dagnacht.addDataSet(GN.data_daytimeNetbelastingDuurkromme_kW,"Belasting overdag");
	plot_dagnacht.addDataSet(GN.data_nighttimeNetbelastingDuurkromme_kW,"Belasting 's nachts");
	plot_dagnacht.setColor(0,blue);
	plot_dagnacht.setColor(1,green);	
	scaleMin_kW = -1 + min(-GN.p_capacity_kW*1.2, GN.data_netbelastingDuurkromme_kW.getYMin());
	scaleMax_kW = 1 + max(GN.p_capacity_kW*1.2, GN.data_netbelastingDuurkromme_kW.getYMax());
	plot_dagnacht.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);
}

// Weekday/weekend
if( GN.data_weekdayNetbelastingDuurkromme_kW != null){
	plot_week.addDataSet(GN.data_weekdayNetbelastingDuurkromme_kW,"Belasting weekdagen");
	plot_week.addDataSet(GN.data_weekendNetbelastingDuurkromme_kW,"Belasting weekenddagen");
	plot_week.setColor(0,blue);
	plot_week.setColor(1,green);
	scaleMin_kW = -1 + min(-GN.p_capacity_kW*1.2, GN.data_netbelastingDuurkromme_kW.getYMin());
	scaleMax_kW = 1 + max(GN.p_capacity_kW*1.2, GN.data_netbelastingDuurkromme_kW.getYMax());
	plot_week.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);
}

/*ALCODEEND*/}

double f_addTrafoLimitsGN(GridNode GN)
{/*ALCODESTART::1741699130827*/
//Add and color grid capacities
String deliveryCapacityLabel = "Geschatte capaciteit afname";
String feedinCapacityLabel = "Geschatte capaciteit teruglevering";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

//Create delivery and capacity year datasets
DataSet gridCapacityDelivery_kW = uI_Results.f_createFlatDataset(0, 8760, GN.p_capacity_kW);
DataSet gridCapacityFeedin_kW = uI_Results.f_createFlatDataset(0, 8760, -GN.p_capacity_kW);
	
if(GN.p_realCapacityAvailable){
		deliveryCapacityLabel = "Capaciteit afname";
		deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
		feedinCapacityLabel = "Capaciteit teruglevering";
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

