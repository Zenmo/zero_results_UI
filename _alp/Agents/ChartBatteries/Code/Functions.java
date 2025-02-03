double f_addSOC_Year(AreaCollection dataObject)
{/*ALCODESTART::1714746057324*/
if (dataObject.v_dataElectricityBaseloadConsumptionYear_kW != null) {
	SOCChart_year.addDataSet(dataObject.v_dataBatterySOCYear_, "Batterij SOC", uI_Results.v_electricityBaseloadDemandColor);
}
/*ALCODEEND*/}

double f_addSOC_Live(AreaCollection dataObject)
{/*ALCODESTART::1714746057328*/
SOCChart_week.addDataSet(dataObject.v_dataBatterySOCLiveWeek_, "Batterij SOC", uI_Results.v_electricityBaseloadDemandColor);

/*ALCODEEND*/}

double f_addSOC_SummerWeek(AreaCollection dataObject)
{/*ALCODESTART::1714897296536*/
SOCChart_week.addDataSet(dataObject.v_dataBatterySOCSummerWeek_, "Batterij SOC", uI_Results.v_electricityBaseloadDemandColor);

/*ALCODEEND*/}

AreaCollection f_resetCharts()
{/*ALCODESTART::1714897512553*/
gr_week.setVisible(false);
gr_year.setVisible(false);
SOCChart_week.removeAll();
SOCChart_year.removeAll();
plot_netload_week.removeAll();
plot_netload_year.removeAll();
/*ALCODEEND*/}

double f_addSOC_WinterWeek(AreaCollection dataObject)
{/*ALCODESTART::1714897923570*/
SOCChart_week.addDataSet(dataObject.v_dataBatterySOCWinterWeek_, "Batterij SOC", uI_Results.v_electricityBaseloadDemandColor);

/*ALCODEEND*/}

double f_setChartsBatteries()
{/*ALCODESTART::1714899014782*/
f_resetCharts();
AreaCollection dataObject = uI_Results.f_getDataObject();

switch(radio_period.getValue()){

case 0:
	gr_week.setVisible(true);
	f_addSOC_Live(dataObject);
	f_addBatteryNetLoad_Live(dataObject);
	break;
case 1:
	gr_week.setVisible(true);
	f_addSOC_SummerWeek(dataObject);
	f_addBatteryNetLoad_SummerWeek(dataObject);
	break;
case 2:
	gr_week.setVisible(true);
	f_addSOC_WinterWeek(dataObject);
	f_addBatteryNetLoad_WinterWeek(dataObject);
	break;
case 3:
	gr_year.setVisible(true);
	f_addSOC_Year(dataObject);
	//f_addBatteryNetLoad_Year(dataObject);
	break;
}



/*ALCODEEND*/}

double f_addBatteryNetLoad_Live(AreaCollection dataObject)
{/*ALCODESTART::1736430560710*/
String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

if(dataObject.b_isRealDeliveryCapacityAvailable){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}
if(dataObject.b_isRealFeedinCapacityAvailable){
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}

plot_netload_week.addDataSet(dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW, feedinCapacityLabel, feedinCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);

plot_netload_week.addDataSet(dataObject.v_dataNetLoadLiveWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataNetLoadLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadLiveWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW.getYMin()));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.3, maxValue + maxValue * 0.3);

/*ALCODEEND*/}

double f_addBatteryNetLoad_Year(AreaCollection dataObject)
{/*ALCODESTART::1736430560714*/
group_netload_year.setVisible(true);
plot_netload_year.addDataSet(dataObject.v_dataElectricityDemandCapacityLiveWeek_kW, "Piek leveringscapaciteit", uI_Results.v_electricityCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_year.addDataSet(dataObject.v_dataElectricitySupplyCapacityLiveWeek_kW, "Piek terugleveringscapaciteit", uI_Results.v_electricityCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_year.addDataSet(dataObject.v_dataNetLoadLiveWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataNetLoadLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDemandCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadLiveWeek_kW.getYMin(), dataObject.v_dataElectricitySupplyCapacityLiveWeek_kW.getYMin()));
plot_netload_year.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addBatteryNetLoad_SummerWeek(AreaCollection dataObject)
{/*ALCODESTART::1736430560716*/
String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

if(dataObject.b_isRealDeliveryCapacityAvailable){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}
if(dataObject.b_isRealFeedinCapacityAvailable){
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}


plot_netload_week.addDataSet(dataObject.v_dataElectricityDeliveryCapacitySummerWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.v_dataElectricityFeedInCapacitySummerWeek_kW, feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);

plot_netload_week.addDataSet(dataObject.v_dataNetLoadSummerWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataNetLoadSummerWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacitySummerWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadSummerWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacitySummerWeek_kW.getYMin()));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addBatteryNetLoad_WinterWeek(AreaCollection dataObject)
{/*ALCODESTART::1736430560718*/
String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

if(dataObject.b_isRealDeliveryCapacityAvailable){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}
if(dataObject.b_isRealFeedinCapacityAvailable){
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}

plot_netload_week.addDataSet(dataObject.v_dataElectricityDeliveryCapacityWinterWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.v_dataElectricityFeedInCapacityWinterWeek_kW, feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);

plot_netload_week.addDataSet(dataObject.v_dataNetLoadWinterWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);


int maxValue = roundToInt(max(dataObject.v_dataNetLoadWinterWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityWinterWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadWinterWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacityWinterWeek_kW.getYMin()));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

