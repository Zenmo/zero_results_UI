double f_addSOC_total(I_EnergyData dataObject)
{/*ALCODESTART::1714746057324*/
double startTime_h = 0;
SOCChart_year.addDataSet(dataObject.getRapidRunData().ts_dailyAverageBatteriesSOC_fr.getDataSet(startTime_h), "Batterij SOC", uI_Results.v_electricityBaseloadDemandColor);

/*ALCODEEND*/}

double f_addSOC_live(I_EnergyData dataObject)
{/*ALCODESTART::1714746057328*/
SOCChart_week.addDataSet(dataObject.getLiveData().data_batterySOC_fr, "Batterij SOC", uI_Results.v_electricityBaseloadDemandColor);

/*ALCODEEND*/}

double f_addSOC_summerWeek(I_EnergyData dataObject)
{/*ALCODESTART::1714897296536*/
double startTime_h = uI_Results.energyModel.p_startOfSummerWeek_h - uI_Results.energyModel.p_runStartTime_h;
if (startTime_h<0) {
	startTime_h +=8760;
}

SOCChart_week.addDataSet(dataObject.getRapidRunData().ts_summerWeekBatteriesSOC_fr.getDataSet(startTime_h), "Batterij SOC", uI_Results.v_electricityBaseloadDemandColor);

/*ALCODEEND*/}

AreaCollection f_resetCharts()
{/*ALCODESTART::1714897512553*/
gr_week.setVisible(false);
gr_year.setVisible(false);
gr_batteryCycles.setVisible(false);
SOCChart_week.removeAll();
SOCChart_year.removeAll();
plot_netload_week.removeAll();
plot_netload_year.removeAll();

/*ALCODEEND*/}

double f_addSOC_winterWeek(I_EnergyData dataObject)
{/*ALCODESTART::1714897923570*/
double startTime_h = uI_Results.energyModel.p_startOfWinterWeek_h - uI_Results.energyModel.p_runStartTime_h;
if (startTime_h<0) {
	startTime_h +=8760;
}

SOCChart_week.addDataSet(dataObject.getRapidRunData().ts_winterWeekBatteriesSOC_fr.getDataSet(startTime_h), "Batterij SOC", uI_Results.v_electricityBaseloadDemandColor);

/*ALCODEEND*/}

double f_setChartsBatteries()
{/*ALCODESTART::1714899014782*/
f_resetCharts();
I_EnergyData data = uI_Results.f_getSelectedObjectData();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 60, true);

//Set battery capacity
f_addBatteryCapacity(data);

//Set correct period chart
switch(radio_period.getValue()){
	case 0:
		gr_week.setVisible(true);
		f_addSOC_live(data);
		f_addBatteryNetLoad_live(data);
		break;
	case 1:
		gr_week.setVisible(true);
		f_addSOC_summerWeek(data);
		f_addBatteryNetLoad_summerWeek(data);
		f_addBatteryKPIs_summerWeek(data);
		break;
	case 2:
		gr_week.setVisible(true);
		f_addSOC_winterWeek(data);
		f_addBatteryNetLoad_winterWeek(data);
		f_addBatteryKPIs_winterWeek(data);
		break;
	case 3:
		gr_year.setVisible(true);
		f_addSOC_total(data);
		//f_addBatteryNetLoad_total(dataObject);
		f_addBatteryKPIs_total(data);
		break;
}



/*ALCODEEND*/}

double f_addBatteryNetLoad_live(I_EnergyData dataObject)
{/*ALCODESTART::1736430560710*/
String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

if(dataObject.getLiveData().connectionMetaData.contractedDeliveryCapacityKnown){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}
if(dataObject.getLiveData().connectionMetaData.contractedFeedinCapacityKnown){
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}

plot_netload_week.addDataSet(dataObject.getLiveData().data_gridCapacityDemand_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.getLiveData().data_gridCapacitySupply_kW, feedinCapacityLabel, feedinCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);

plot_netload_week.addDataSet(dataObject.getLiveData().data_liveElectricityBalance_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

/*ALCODEEND*/}

double f_addBatteryNetLoad_total(I_EnergyData dataObject)
{/*ALCODESTART::1736430560714*/
group_netload_year.setVisible(true);
plot_netload_year.addDataSet(dataObject.v_dataElectricityDemandCapacityLiveWeek_kW, "Piek leveringscapaciteit", uI_Results.v_electricityCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_year.addDataSet(dataObject.v_dataElectricitySupplyCapacityLiveWeek_kW, "Piek terugleveringscapaciteit", uI_Results.v_electricityCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_year.addDataSet(dataObject.v_dataNetLoadLiveWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataNetLoadLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDemandCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadLiveWeek_kW.getYMin(), dataObject.v_dataElectricitySupplyCapacityLiveWeek_kW.getYMin()));
plot_netload_year.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addBatteryNetLoad_summerWeek(I_EnergyData dataObject)
{/*ALCODESTART::1736430560716*/
String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

if(dataObject.getRapidRunData().connectionMetaData.contractedDeliveryCapacityKnown){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}
if(dataObject.getRapidRunData().connectionMetaData.contractedFeedinCapacityKnown){
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}

double startTime_h = uI_Results.energyModel.p_startOfSummerWeek_h - uI_Results.energyModel.p_runStartTime_h;
plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_summerWeekDeliveryCapacity_kW.getDataSet(startTime_h), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_summerWeekFeedinCapacity_kW.getDataSet(startTime_h), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(startTime_h), "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMaxPower_kW(), dataObject.getRapidRunData().acc_summerWeekDeliveryCapacity_kW.getMaxPower_kW()));
int minValue = roundToInt(min(dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMinPower_kW(), dataObject.getRapidRunData().acc_summerWeekFeedinCapacity_kW.getMinPower_kW()));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addBatteryNetLoad_winterWeek(I_EnergyData dataObject)
{/*ALCODESTART::1736430560718*/
String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

if(dataObject.getRapidRunData().connectionMetaData.contractedDeliveryCapacityKnown){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}
if(dataObject.getRapidRunData().connectionMetaData.contractedFeedinCapacityKnown){
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}

double startTime_h = uI_Results.energyModel.p_startOfWinterWeek_h - uI_Results.energyModel.p_runStartTime_h;
plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_winterWeekDeliveryCapacity_kW.getDataSet(startTime_h), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_winterWeekFeedinCapacity_kW.getDataSet(startTime_h), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.getRapidRunData().am_winterWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(startTime_h), "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.getRapidRunData().am_winterWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMaxPower_kW(), dataObject.getRapidRunData().acc_winterWeekDeliveryCapacity_kW.getMaxPower_kW()));
int minValue = roundToInt(min(dataObject.getRapidRunData().am_winterWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMinPower_kW(), dataObject.getRapidRunData().acc_winterWeekFeedinCapacity_kW.getMinPower_kW()));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);


/*ALCODEEND*/}

double f_addBatteryKPIs_summerWeek(I_EnergyData dataObject)
{/*ALCODESTART::1743417074489*/
gr_batteryCycles.setVisible(true);

DecimalFormat df_1decimal = new DecimalFormat("0.0");
if(dataObject.getRapidRunData().assetsMetaData.totalInstalledBatteryStorageCapacity_MWh > 0){
	t_batteryCycles.setText(df_1decimal.format(dataObject.getRapidRunData().getSummerWeekBatteryCycles()));
}
else{
	t_batteryCycles.setText("0");
}
/*ALCODEEND*/}

double f_addBatteryKPIs_winterWeek(I_EnergyData dataObject)
{/*ALCODESTART::1743417142841*/
gr_batteryCycles.setVisible(true);

DecimalFormat df_1decimal = new DecimalFormat("0.0");
if(dataObject.getRapidRunData().assetsMetaData.totalInstalledBatteryStorageCapacity_MWh > 0){
	t_batteryCycles.setText(df_1decimal.format(dataObject.getRapidRunData().getWinterWeekBatteryCycles()));
}
else{
	t_batteryCycles.setText("0");
}
/*ALCODEEND*/}

double f_addBatteryKPIs_total(I_EnergyData dataObject)
{/*ALCODESTART::1743417159083*/
gr_batteryCycles.setVisible(true);

DecimalFormat df_1decimal = new DecimalFormat("0.0");
if(dataObject.getRapidRunData().assetsMetaData.totalInstalledBatteryStorageCapacity_MWh > 0){
	t_batteryCycles.setText(df_1decimal.format(dataObject.getRapidRunData().getTotalBatteryCycles()));
}
else{
	t_batteryCycles.setText("0");
}
/*ALCODEEND*/}

double f_addBatteryCapacity(I_EnergyData dataObject)
{/*ALCODESTART::1743419350262*/
DecimalFormat df_1decimal = new DecimalFormat("0.0");
DecimalFormat df_2decimal = new DecimalFormat("0.00");

double batteryStorageCapacity = 0;
if(radio_period.getValue() == 0){
	batteryStorageCapacity = dataObject.getLiveData().assetsMetaData.totalInstalledBatteryStorageCapacity_MWh;
}
else{
	batteryStorageCapacity = dataObject.getRapidRunData().assetsMetaData.totalInstalledBatteryStorageCapacity_MWh;
}
String batteryStorageCapacity_unitString = "[MWh]";

if(batteryStorageCapacity < 1){
	batteryStorageCapacity_unitString = "[kWh]";
	batteryStorageCapacity = batteryStorageCapacity*1000;
}
else if(batteryStorageCapacity < 1000){
	batteryStorageCapacity_unitString = "[MWh]";
	batteryStorageCapacity = batteryStorageCapacity;
}
else if(batteryStorageCapacity > 1000){
	batteryStorageCapacity_unitString = "[GWh]";
	batteryStorageCapacity = batteryStorageCapacity/1000;
}

batteryStorageCapacity = max(0, batteryStorageCapacity);

if(batteryStorageCapacity > 100){
	t_totalBatteryCapacity.setText(roundToInt(batteryStorageCapacity));
}
else if(batteryStorageCapacity > 10){
	t_totalBatteryCapacity.setText(df_1decimal.format(batteryStorageCapacity));
}
else{
	t_totalBatteryCapacity.setText(df_2decimal.format(batteryStorageCapacity));
}
	
t_batteryCapacityLabel.setText("Batterij capaciteit " + batteryStorageCapacity_unitString);

/*ALCODEEND*/}

