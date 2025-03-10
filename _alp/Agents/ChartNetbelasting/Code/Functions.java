double f_setBelastingPlots()
{/*ALCODESTART::1714746143909*/
f_resetPlots();
I_EnergyData data = uI_Results.f_getSelectedObjectData();
f_addDataToPlots(data);
f_addTrafoLimits(data); 

double maxDemand_kW = max(0, data.getRapidRunData().v_dataNetbelastingDuurkrommeYear_kW.getYMax());
double maxSupply_kW = abs(min(0, data.getRapidRunData().v_dataNetbelastingDuurkrommeYear_kW.getYMin()));
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


f_setNetAverageLoad(data);
/*ALCODEEND*/}

double f_setNetAverageLoad(AreaCollection area)
{/*ALCODESTART::1714746143911*/
double benuttingsgraad = 0;
double totalAbsoluteLoad_kW = 0;
for(int i=0; i< area.v_dataNetbelastingDuurkrommeYear_kW.size(); i++){
	totalAbsoluteLoad_kW += abs(area.v_dataNetbelastingDuurkrommeYear_kW.getY(i))* uI_Results.energyModel.p_timeStep_h;
}
benuttingsgraad = totalAbsoluteLoad_kW / ((area.v_gridCapacityDelivery_kW+area.v_gridCapacityFeedIn_kW) * 8760) * 100;
t_KPIBenuttingsgraad.setText(roundToDecimal(benuttingsgraad, 0) + "%");
/*ALCODEEND*/}

double f_resetPlots()
{/*ALCODESTART::1714922345475*/
plot_jaar.removeAll();
plot_week.removeAll();
plot_dagnacht.removeAll();
plot_seizoen.removeAll();
/*ALCODEEND*/}

double f_addDataToPlots(AreaCollection area)
{/*ALCODESTART::1714923776978*/
double scaleMin_kW;
double scaleMax_kW;

//Jaar
plot_jaar.addDataSet(area.v_dataNetbelastingDuurkrommeYear_kW,"Belasting jaar");
scaleMin_kW = -1 + min(-area.v_gridCapacityFeedIn_kW*1.2, area.v_dataNetbelastingDuurkrommeYear_kW.getYMin());
scaleMax_kW = 1 + max(area.v_gridCapacityDelivery_kW*1.2, area.v_dataNetbelastingDuurkrommeYear_kW.getYMax());
plot_jaar.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);
if (area.v_dataNetbelastingDuurkrommeYearVorige_kW != null) {
	//plot_jaar.addDataSet(area.v_dataNetbelastingDuurkrommeYearVorige_kW,"Vorige situatie");
	//plot_jaar.setColor(3,gray);
}

//summer/winter
if( area.v_dataNetbelastingDuurkrommeSummer_kW != null){
	plot_seizoen.addDataSet(area.v_dataNetbelastingDuurkrommeSummer_kW,"Belasting zomerweek");
	plot_seizoen.addDataSet(area.v_dataNetbelastingDuurkrommeWinter_kW,"Belasting winterweek");
	plot_seizoen.setColor(0,blue);
	plot_seizoen.setColor(1,green);
	scaleMin_kW = -1 + min(-area.v_gridCapacityFeedIn_kW*1.2, area.v_dataNetbelastingDuurkrommeYear_kW.getYMin());
	scaleMax_kW = 1 + max(area.v_gridCapacityDelivery_kW*1.2, area.v_dataNetbelastingDuurkrommeYear_kW.getYMax());
	plot_seizoen.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);
}

// Day/night
if( area.v_dataNetbelastingDuurkrommeDaytime_kW != null){
	plot_dagnacht.addDataSet(area.v_dataNetbelastingDuurkrommeDaytime_kW,"Belasting overdag");
	plot_dagnacht.addDataSet(area.v_dataNetbelastingDuurkrommeNighttime_kW,"Belasting 's nachts");
	plot_dagnacht.setColor(0,blue);
	plot_dagnacht.setColor(1,green);	
	scaleMin_kW = -1 + min(-area.v_gridCapacityFeedIn_kW*1.2, area.v_dataNetbelastingDuurkrommeYear_kW.getYMin());
	scaleMax_kW = 1 + max(area.v_gridCapacityDelivery_kW*1.2, area.v_dataNetbelastingDuurkrommeYear_kW.getYMax());
	plot_dagnacht.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);
}

// Weekday/weekend
if( area.v_dataNetbelastingDuurkrommeWeekday_kW != null){
	plot_week.addDataSet(area.v_dataNetbelastingDuurkrommeWeekday_kW,"Belasting weekdagen");
	plot_week.addDataSet(area.v_dataNetbelastingDuurkrommeWeekend_kW,"Belasting weekenddagen");
	plot_week.setColor(0,blue);
	plot_week.setColor(1,green);
	scaleMin_kW = -1 + min(-area.v_gridCapacityFeedIn_kW*1.2, area.v_dataNetbelastingDuurkrommeYear_kW.getYMin());
	scaleMax_kW = 1 + max(area.v_gridCapacityDelivery_kW*1.2, area.v_dataNetbelastingDuurkrommeYear_kW.getYMax());
	plot_week.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);
}

/*ALCODEEND*/}

double f_addTrafoLimits(AreaCollection area)
{/*ALCODESTART::1740584474407*/
//Add and color grid capacities
String deliveryCapacityLabel = "Geschatte capaciteit afname";
String feedinCapacityLabel = "Geschatte capaciteit teruglevering";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

//Create delivery and capacity year datasets
DataSet gridCapacityDelivery_kW = uI_Results.f_createFlatDataset(0, 8760, area.v_gridCapacityDelivery_kW);
DataSet gridCapacityFeedin_kW = uI_Results.f_createFlatDataset(0, 8760, -area.v_gridCapacityFeedIn_kW);
	
if(uI_Results.b_showGroupContractValues && uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYCOOP){
	deliveryCapacityLabel = "Cumulatieve GTV afname van bedrijven";
	deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	feedinCapacityLabel = "Cumulatieve GTV teruglevering van bedrijven";
	feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	
	//And: add group contract values
	DataSet groupContractCapacityDelivery_kW = uI_Results.f_createFlatDataset(0, 8760, area.v_gridCapacityDelivery_groupcontract_kW);
	DataSet groupContractCapacityFeedin_kW = uI_Results.f_createFlatDataset(0, 8760, -area.v_gridCapacityFeedin_groupcontract_kW);
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
	if(area.b_isRealDeliveryCapacityAvailable){
		deliveryCapacityLabel = "Capaciteit afname";
		deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	}
	if(area.b_isRealFeedinCapacityAvailable){
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

