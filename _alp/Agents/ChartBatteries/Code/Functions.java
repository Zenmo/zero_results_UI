double f_addSOC_total(I_EnergyData dataObject)
{/*ALCODESTART::1714746057324*/

if (dataObject.getRapidRunData().assetsMetaData.totalInstalledBatteryStorageCapacity_MWh>0){
	double startTime_h = 0;
	SOCChart_year.addDataSet(dataObject.getRapidRunData().getBatteriesSOCts_fr().getDataSet(startTime_h), "Batterij SOC", uI_Results.v_electricityBaseloadDemandColor);
}

/*ALCODEEND*/}

double f_addSOC_live(I_EnergyData dataObject)
{/*ALCODESTART::1714746057328*/
SOCChart_week.addDataSet(dataObject.getLiveData().data_batterySOC_fr, "Batterij SOC", uI_Results.v_electricityBaseloadDemandColor);

/*ALCODEEND*/}

double f_addSOC_Week(I_EnergyData dataObject,boolean isSummerWeek)
{/*ALCODESTART::1714897296536*/
if (dataObject.getRapidRunData().assetsMetaData.totalInstalledBatteryStorageCapacity_MWh>0){
	if (dataObject.getRapidRunData().getStoreTotalAssetFlows()) {
		double dataSetStartTime_h = uI_Results.energyModel.p_runStartTime_h; //
		double peakTime_h;
		double peak_kW;
	if (isSummerWeek) {
		peakTime_h = dataObject.getRapidRunData().getLowestBalanceTime_h(OL_EnergyCarriers.ELECTRICITY);
		peak_kW = -1 * dataObject.getRapidRunData().getLowestBalance_kW(OL_EnergyCarriers.ELECTRICITY);
	} else {
		peakTime_h = dataObject.getRapidRunData().getHighestBalanceTime_h(OL_EnergyCarriers.ELECTRICITY);
		peak_kW = dataObject.getRapidRunData().getHighestBalance_kW(OL_EnergyCarriers.ELECTRICITY);
	}
		//traceln("Plotting peak feedin week instead of fixed summer week! Peak feedin occured at: %s hours, power was: %s", peakFeedinTime_h, peakFeedin_kW);
		
		// Output the result
		/*String dateTimeString = f_getDateTimeFromHour(peakTime_h);
	    if (isSummerWeek) {
		    if (peak_kW > 0) {
				v_weekLabel.setText("Hoogste invoeding op: " + dateTimeString);
			} else {
				v_weekLabel.setText("Laagste afname op: " + dateTimeString);
			}
		} else {
			if (peak_kW > 0) {
				v_weekLabel.setText("Hoogste afname op: " + dateTimeString);
			} else {
				v_weekLabel.setText("Laagste invoeding op: " + dateTimeString);
			}
		}*/
		double peakWeekStart_h = dataObject.getRapidRunData().getPeakWeekStart_h(peakTime_h);
		SOCChart_week.addDataSet(dataObject.getRapidRunData().getBatteriesSOCts_fr().getDataSet(dataSetStartTime_h, peakWeekStart_h, peakWeekStart_h+24*7), "Batterij SOC", uI_Results.v_electricityBaseloadDemandColor);
	} else {
		if (isSummerWeek){
			double startTime_h = uI_Results.energyModel.p_startOfSummerWeek_h - uI_Results.energyModel.p_runStartTime_h;
			if (startTime_h<0) {
				startTime_h +=8760;
			}
			
			SOCChart_week.addDataSet(dataObject.getRapidRunData().ts_summerWeekBatteriesSOC_fr.getDataSet(startTime_h), "Batterij SOC", uI_Results.v_electricityBaseloadDemandColor);
		} else {
			double startTime_h = uI_Results.energyModel.p_startOfWinterWeek_h - uI_Results.energyModel.p_runStartTime_h;
			if (startTime_h<0) {
				startTime_h +=8760;
			}
			
			SOCChart_week.addDataSet(dataObject.getRapidRunData().ts_winterWeekBatteriesSOC_fr.getDataSet(startTime_h), "Batterij SOC", uI_Results.v_electricityBaseloadDemandColor);
		
		}
	}
}
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

double f_setChartsBatteries()
{/*ALCODESTART::1714899014782*/
f_resetCharts();
I_EnergyData data = uI_Results.f_getSelectedObjectData();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 60, true);

//Set battery capacity
f_addBatteryCapacity(data);

//Set battery lifetime
f_addBatteryLifetime(data);

/*
String[] stringArray = {"live", "week 1", "week 2", "jaar"};
ShapeRadioButtonGroup radio_period2 = new ShapeRadioButtonGroup(this.presentation.getPresentable(), true, 50, 100, 300, 50, Color.BLACK, true, new Font("Calibri", 0, 15), false, stringArray){
	@Override
	public void action() {
		f_setChartsBatteries();
	}
};
*/

//Set correct period chart
switch(radio_period.getValue()){
	case 0:
		gr_week.setVisible(true);
		f_addSOC_live(data);
		f_addBatteryGridLoad_live(data);
		break;
	case 1:
		gr_week.setVisible(true);
		f_addSOC_Week(data,false);
		f_addElectricityGridLoad_Week(data, false);//f_addBatteryNetLoad_summerWeek(data);
		f_addBatteryKPIs_Week(data, false);
		break;
	case 2:
		gr_week.setVisible(true);
		f_addSOC_Week(data,true);
		f_addElectricityGridLoad_Week(data, true);
		f_addBatteryKPIs_Week(data, true);
		break;
	case 3:
		gr_year.setVisible(true);
		f_addSOC_total(data);
		f_addBatteryNetLoad_total(data);
		f_addBatteryKPIs_total(data);
		break;
}



/*ALCODEEND*/}

double f_addBatteryGridLoad_live(I_EnergyData dataObject)
{/*ALCODESTART::1736430560710*/
txt_netload_week.setText("Netto Belasting net");

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

//plot_netload_year.addDataSet(dataObject.getRapidRunData().acc_summerWeekDeliveryCapacity_kW.getDataSet(), "Piek leveringscapaciteit", uI_Results.v_electricityCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
//plot_netload_year.addDataSet(dataObject.getRapidRunData().acc_summerWeekFeedinCapacity_kW.getDataSet(dataSetWeekStartTime_h), "Piek terugleveringscapaciteit", uI_Results.v_electricityCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_year.addDataSet(dataObject.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(0), "Netto vermogen", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

//int maxValue = roundToInt(max(dataObject.v_dataNetLoadLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDemandCapacityLiveWeek_kW.getYMax()));
//int minValue = roundToInt(min(dataObject.v_dataNetLoadLiveWeek_kW.getYMin(), dataObject.v_dataElectricitySupplyCapacityLiveWeek_kW.getYMin()));
//plot_netload_year.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addBatteryKPIs_Week(I_EnergyData dataObject,boolean isSummerWeek)
{/*ALCODESTART::1743417074489*/
gr_batteryCycles.setVisible(true);
gr_batteryLifetime.setVisible(true);

DecimalFormat df_1decimal = new DecimalFormat("0.0");
if(dataObject.getRapidRunData().assetsMetaData.totalInstalledBatteryStorageCapacity_MWh > 0){
	if (isSummerWeek) {
		t_batteryCycles.setText(df_1decimal.format(dataObject.getRapidRunData().getSummerWeekBatteryCycles()));
	} else {
		t_batteryCycles.setText(df_1decimal.format(dataObject.getRapidRunData().getWinterWeekBatteryCycles()));
	}
}
else{
	t_batteryCycles.setText("0");
}
/*ALCODEEND*/}

double f_addBatteryKPIs_total(I_EnergyData dataObject)
{/*ALCODESTART::1743417159083*/
gr_batteryCycles.setVisible(true);
gr_batteryLifetime.setVisible(true);

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

String f_getDateTimeFromHour(double peakTime_h)
{/*ALCODESTART::1754498008083*/
int dayOfYear = (int)Math.floor(peakTime_h / 24) + 1;
int hourOfDay = roundToInt(peakTime_h % 24);
LocalDate date = LocalDate.ofYearDay(uI_Results.energyModel.p_year, dayOfYear);
LocalDateTime dateTime = date.atStartOfDay().plusHours(hourOfDay);

// Output the result
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
return dateTime.format(formatter);
/*ALCODEEND*/}

double f_addElectricityGridLoad_Week(I_EnergyData dataObject,boolean isSummerWeek)
{/*ALCODESTART::1754550135509*/
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

//Add datasets to plot
double startTime_h = uI_Results.energyModel.p_startOfSummerWeek_h - uI_Results.energyModel.p_runStartTime_h;


if (dataObject.getRapidRunData().getStoreTotalAssetFlows()) { // 
	double dataSetStartTime_h = uI_Results.energyModel.p_runStartTime_h; //
	double peakTime_h;
	double peak_kW;
	if (isSummerWeek) {
		peakTime_h = dataObject.getRapidRunData().getLowestBalanceTime_h(OL_EnergyCarriers.ELECTRICITY);
		peak_kW = -1 * dataObject.getRapidRunData().getLowestBalance_kW(OL_EnergyCarriers.ELECTRICITY);
	} else {
		peakTime_h = dataObject.getRapidRunData().getHighestBalanceTime_h(OL_EnergyCarriers.ELECTRICITY);
		peak_kW = dataObject.getRapidRunData().getHighestBalance_kW(OL_EnergyCarriers.ELECTRICITY);
	}

	double peakWeekStart_h = dataObject.getRapidRunData().getPeakWeekStart_h(peakTime_h);
	String dateTimeString = f_getDateTimeFromHour(peakTime_h);
    if (isSummerWeek) {
	    if (peak_kW > 0) {
			txt_netload_week.setText("Hoogste invoeding op: " + dateTimeString);
		} else {
			txt_netload_week.setText("Laagste afname op: " + dateTimeString);
		}
	} else {
		if (peak_kW > 0) {
			txt_netload_week.setText("Hoogste afname op: " + dateTimeString);
		} else {
			txt_netload_week.setText("Laagste invoeding op: " + dateTimeString);
		}
	}
	v_weekLabel.setX(80);
	if (isSummerWeek) {
		plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_summerWeekDeliveryCapacity_kW.getDataSet(peakWeekStart_h-uI_Results.energyModel.p_runStartTime_h), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_summerWeekFeedinCapacity_kW.getDataSet(peakWeekStart_h-uI_Results.energyModel.p_runStartTime_h), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	} else {
		plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_winterWeekDeliveryCapacity_kW.getDataSet(peakWeekStart_h-uI_Results.energyModel.p_runStartTime_h), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_winterWeekFeedinCapacity_kW.getDataSet(peakWeekStart_h-uI_Results.energyModel.p_runStartTime_h), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
	plot_netload_week.addDataSet(dataObject.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(uI_Results.energyModel.p_runStartTime_h,peakWeekStart_h, peakWeekStart_h+24*7), "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);
} else {
	double dataSetWeekStartTime_h;
	if (isSummerWeek) {
		dataSetWeekStartTime_h = uI_Results.energyModel.p_startOfSummerWeek_h - uI_Results.energyModel.p_runStartTime_h;
	} else {
		dataSetWeekStartTime_h = uI_Results.energyModel.p_startOfWinterWeek_h - uI_Results.energyModel.p_runStartTime_h;
	}
	if (dataSetWeekStartTime_h<0) {
		dataSetWeekStartTime_h +=8760;
	}
	if (isSummerWeek) {
		plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_summerWeekDeliveryCapacity_kW.getDataSet(dataSetWeekStartTime_h), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_summerWeekFeedinCapacity_kW.getDataSet(dataSetWeekStartTime_h), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	
		//plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_summerWeekFeedinCapacity_kW.getDataSet(startTime_h), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_netload_week.addDataSet(dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(dataSetWeekStartTime_h), "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);
	} else {
		plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_winterWeekDeliveryCapacity_kW.getDataSet(dataSetWeekStartTime_h), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_winterWeekFeedinCapacity_kW.getDataSet(dataSetWeekStartTime_h), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	
		//plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_summerWeekFeedinCapacity_kW.getDataSet(startTime_h), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_netload_week.addDataSet(dataObject.getRapidRunData().am_winterWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(dataSetWeekStartTime_h), "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);
	}
}

/*
//Specific coop plot additions
if (dataObject.getScope() == OL_ResultScope.ENERGYCOOP ) {

	if(uI_Results.b_showGroupContractValues) {
		plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(startTime_h, 168, ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getGroupContractDeliveryCapacity_kW()), "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(startTime_h, 168, ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getGroupContractFeedinCapacity_kW()), "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
	if (b_subdivideEnergyCoopFlows) {
		List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getAllChildMemberGridConnections();
		for (GridConnection GC : memberGridConnections) {
			plot_netload_week.addDataSet(GC.v_rapidRunData.am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(uI_Results.energyModel.p_startOfSummerWeek_h), GC.p_gridConnectionID, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
		}
	}
}*/


//gr_netLoadWeek.setVisible(true);

/*ALCODEEND*/}

double f_addBatteryLifetime(I_EnergyData dataObject)
{/*ALCODESTART::1756460546640*/
DecimalFormat df_2decimal = new DecimalFormat("0.00");

double lifetimeBattery_yr = f_calculateBatteryLifetime_yr(dataObject, dataObject.getRapidRunData().getBatteriesSOCts_fr().getTimeSeries());
t_batteryLifetime.setText(df_2decimal.format(lifetimeBattery_yr));
/*ALCODEEND*/}

double f_calculateBatteryLifetime_yr(I_EnergyData dataObject,double[] arraySoC_fr)
{/*ALCODESTART::1756460635121*/
double averageDoD = f_calculateAverageDoD_fr(arraySoC_fr);
    	 
double totalYearlyCycles = dataObject.getRapidRunData().getTotalBatteryCycles(); // Defined that total annual energy charged/battery capacity
 
// function and parameters (li-ion) are extracted from source:
// https://www.researchgate.net/publication/330142356_Optimal_Operational_Planning_of_Scalable_DC_Microgrid_with_Demand_Response_Islanding_and_Battery_Degradation_Cost_Considerations
double alpha = -5440.35;
double beta = 1191.54;
 
double batteryCycleLife_cycles = alpha * Math.log(averageDoD) + beta;
traceln("Battery Lifetime from totalYearlyCycles is " + batteryCycleLife_cycles/totalYearlyCycles);
double lifetimeBattery_yr = batteryCycleLife_cycles/v_cycleCount;
return lifetimeBattery_yr;

/*ALCODEEND*/}

double f_calculateAverageDoD_fr(double[] arraySoC_fr)
{/*ALCODESTART::1756461006378*/
v_cycleCount = 0; // Cycle is NOT defined as amount of chargeEnergy/batteryCapacity; but rather as a count of activities
double cumulativeDoD = 0;

ArrayList<Double> localBatteryTurningPoints_fr = f_calculateTurningPoints(arraySoC_fr);

while (localBatteryTurningPoints_fr.size() > 2) {
	boolean hasFoundCycle = false;
	for (int i=0; i < localBatteryTurningPoints_fr.size()-2; i++) {
		
		if (abs(localBatteryTurningPoints_fr.get(i+1)-localBatteryTurningPoints_fr.get(i+2)) <= abs(localBatteryTurningPoints_fr.get(i)-localBatteryTurningPoints_fr.get(i+1))) { //abs(Y-Z) <= abs(Z-Y)
			cumulativeDoD += abs(localBatteryTurningPoints_fr.get(i+1)-localBatteryTurningPoints_fr.get(i+2));
			localBatteryTurningPoints_fr.remove(i+2); //Remove Z first, otherwise order changes
			localBatteryTurningPoints_fr.remove(i+1); //Remove Y
			v_cycleCount += 1; //Remove 2 point-> 2lines -> 2 half cycles -> 1 full cycle 
			hasFoundCycle = true;
			break;
			// charge or discharge is or is not included in DoD -> TBD; so amountOfCycles could be 0.5 (if we had additional while loop) or 1
		}
	}
	if (!hasFoundCycle) {
		break; //fail safe to prevent stuck in loop
	}
}

//Add final residual half-cycle
if (localBatteryTurningPoints_fr.size() > 1) {
	cumulativeDoD += abs(localBatteryTurningPoints_fr.get(0)-localBatteryTurningPoints_fr.get(1));
	v_cycleCount += 0.5;
}

double averageDoD = cumulativeDoD/v_cycleCount;
return averageDoD;
/*ALCODEEND*/}

ArrayList<Double> f_calculateTurningPoints(double[] arraySoC_fr)
{/*ALCODESTART::1756461059884*/
double[] previousTwoBatterySoC_fr = new double[]{0,0};
ArrayList<Double> batteryTurningPoints_fr = new ArrayList();

for(double SoC_fr: arraySoC_fr) {
	SoC_fr = roundToDecimal(SoC_fr,8);

	if (previousTwoBatterySoC_fr[0] >= previousTwoBatterySoC_fr[1] && previousTwoBatterySoC_fr[1] < SoC_fr) {
		batteryTurningPoints_fr.add(previousTwoBatterySoC_fr[1]);
	}
	else if (previousTwoBatterySoC_fr[0] <= previousTwoBatterySoC_fr[1] && previousTwoBatterySoC_fr[1] > SoC_fr) {
		batteryTurningPoints_fr.add(previousTwoBatterySoC_fr[1]);
	}
	previousTwoBatterySoC_fr[0] = previousTwoBatterySoC_fr[1];
	previousTwoBatterySoC_fr[1] = SoC_fr;
}
return batteryTurningPoints_fr;
/*ALCODEEND*/}

