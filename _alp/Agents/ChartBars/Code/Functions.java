double f_setCharts()
{/*ALCODESTART::1762958101313*/
f_resetCharts();
f_setVisiblity();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 90, true);

if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
	// ?
	}
else {
	I_EnergyData data = uI_Results.f_getSelectedObjectData();
	if( radio_energyType.getValue() == 0){
		switch(radio_period.getValue()){
			case 0:
				if (v_showingElectricitySelfConsumption) {
					f_setElectricitySelfConsumptionBarChartTotal(data);
				}
				else {
					f_setElectricityAssetsBarChartTotal(data);
				}
				break;
			case 1:
				if (v_showingElectricitySelfConsumption) {
					f_setElectricitySelfConsumptionBarChartSummerWinter(data);
				}
				else {
					f_setElectricityAssetsBarChartSummerWinter(data);
				}
				break;
			case 2:
				if (v_showingElectricitySelfConsumption) {
					f_setElectricitySelfConsumptionBarChartDayNight(data);
				}
				else {
					f_setElectricityAssetsBarChartDayNight(data);
				}						
				break;
			case 3:
				if (v_showingElectricitySelfConsumption) {
					f_setElectricitySelfConsumptionBarChartWeekdayWeekend(data);
				}
				else {
					f_setElectricityAssetsBarChartWeekdayWeekend(data);
				}
				break;
		}
	}
	else if( radio_energyType.getValue() == 1){
		switch(radio_period.getValue()){
			case 0:
				f_setHeatBarChartTotal(data);
				break;
			case 1:
				f_setHeatBarChartSummerWinter(data);
				break;
			case 2:
				f_setHeatBarChartDayNight(data);
				break;
			case 3:
				f_setHeatBarChartWeekdayWeekend(data);
				break;
		}
	}
	else if( radio_energyType.getValue() == 2){
		switch(radio_period.getValue()){
			case 0:
				f_setEnergyBarChartTotal(data);
				break;
			case 1:
				f_setEnergyBarChartSummerWinter(data);
				break;
			case 2:
				f_setEnergyBarChartDayNight(data);
				break;
			case 3:
				f_setEnergyBarChartWeekdayWeekend(data);
				break;
		}
	}
}
/*ALCODEEND*/}

double f_setElectricitySelfConsumptionBarChartTotal(I_EnergyData dataObject)
{/*ALCODESTART::1762958101315*/
DataItem annualSelfConsumed = new DataItem();
annualSelfConsumed.setValue(dataObject.getRapidRunData().getTotalElectricitySelfConsumed_MWh());
pl_productionChartBalanceTotal.addDataItem(annualSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartBalanceTotal.addDataItem(annualSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem annualImport = new DataItem();
annualImport.setValue(dataObject.getRapidRunData().getTotalImport_MWh(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartBalanceTotal.addDataItem(annualImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem annualExport = new DataItem();
annualExport.setValue(dataObject.getRapidRunData().getTotalExport_MWh(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartBalanceTotal.addDataItem(annualExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);

double production_MWh = dataObject.getRapidRunData().getTotalElectricityProduced_MWh(); 
double consumption_MWh = dataObject.getRapidRunData().getTotalElectricityConsumed_MWh();
double chartScale_MWh = max(production_MWh, consumption_MWh);
pl_consumptionChartBalanceTotal.setFixedScale(chartScale_MWh);
pl_productionChartBalanceTotal.setFixedScale(chartScale_MWh);


if (chartScale_MWh<10) {
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToInt(production_MWh*1000) + " kWh");
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToInt(consumption_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToInt(production_MWh) + " MWh");
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToInt(consumption_MWh) + " MWh");
} else {
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToDecimal(production_MWh/1000, 1) + " GWh");
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToDecimal(consumption_MWh/1000,1) + " GWh");
}
/*ALCODEEND*/}

double f_setElectricitySelfConsumptionBarChartSummerWinter(I_EnergyData dataObject)
{/*ALCODESTART::1762958101317*/
// Summer
DataItem summerSelfConsumed = new DataItem();
summerSelfConsumed.setValue(dataObject.getRapidRunData().getSummerWeekElectricitySelfConsumed_MWh());
pl_productionChartSummer.addDataItem(summerSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartSummer.addDataItem(summerSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem summerImport = new DataItem();
summerImport.setValue(dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getIntegralPos_MWh());
pl_consumptionChartSummer.addDataItem(summerImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem summerExport = new DataItem();
summerExport.setValue(-dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getIntegralNeg_MWh());
pl_productionChartSummer.addDataItem(summerExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Winter
DataItem winterSelfConsumed = new DataItem();
winterSelfConsumed.setValue(dataObject.getRapidRunData().getWinterWeekElectricitySelfConsumed_MWh());
pl_productionChartWinter.addDataItem(winterSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartWinter.addDataItem(winterSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem winterImport = new DataItem();
winterImport.setValue(dataObject.getRapidRunData().am_winterWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getIntegralPos_MWh());
pl_consumptionChartWinter.addDataItem(winterImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem winterExport = new DataItem();
winterExport.setValue(-dataObject.getRapidRunData().am_winterWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getIntegralNeg_MWh());
pl_productionChartWinter.addDataItem(winterExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);


double summerProduction_MWh = dataObject.getRapidRunData().getSummerWeekElectricityProduced_MWh();
double summerConsumption_MWh = dataObject.getRapidRunData().getSummerWeekElectricityConsumed_MWh();
double winterProduction_MWh = dataObject.getRapidRunData().getWinterWeekElectricityProduced_MWh();
double winterConsumption_MWh = dataObject.getRapidRunData().getWinterWeekElectricityConsumed_MWh();
double chartScale_MWh = max(max(summerProduction_MWh, winterProduction_MWh), max(summerConsumption_MWh, winterConsumption_MWh));
pl_productionChartSummer.setFixedScale(chartScale_MWh);
pl_productionChartWinter.setFixedScale(chartScale_MWh);
pl_consumptionChartSummer.setFixedScale(chartScale_MWh);
pl_consumptionChartWinter.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_productionTextSummer.setText("Opwek" + System.lineSeparator() + roundToInt(summerProduction_MWh*1000) + " kWh");
	t_consumptionTextSummer.setText("Gebruik" + System.lineSeparator() + roundToInt(summerConsumption_MWh*1000) + " kWh");
	t_productionTextWinter.setText("Opwek" + System.lineSeparator() + roundToInt(winterProduction_MWh*1000) + " kWh");
	t_consumptionTextWinter.setText("Gebruik" + System.lineSeparator() + roundToInt(winterConsumption_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_productionTextSummer.setText("Opwek" + System.lineSeparator() + roundToInt(summerProduction_MWh) + " MWh");
	t_consumptionTextSummer.setText("Gebruik" + System.lineSeparator() + roundToInt(summerConsumption_MWh) + " MWh");
	t_productionTextWinter.setText("Opwek" + System.lineSeparator() + roundToInt(winterProduction_MWh) + " MWh");
	t_consumptionTextWinter.setText("Gebruik" + System.lineSeparator() + roundToInt(winterConsumption_MWh) + " MWh");
} else {
	t_productionTextSummer.setText("Opwek" + System.lineSeparator() + roundToDecimal(summerProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextSummer.setText("Gebruik" + System.lineSeparator() + roundToDecimal(summerConsumption_MWh/1000,1) + " GWh");
	t_productionTextWinter.setText("Opwek" + System.lineSeparator() + roundToDecimal(winterProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextWinter.setText("Gebruik" + System.lineSeparator() + roundToDecimal(winterConsumption_MWh/1000,1) + " GWh");
}
/*ALCODEEND*/}

double f_setElectricitySelfConsumptionBarChartDayNight(I_EnergyData dataObject)
{/*ALCODESTART::1762958101319*/
// Day
DataItem daytimeSelfConsumed = new DataItem();
daytimeSelfConsumed.setValue(dataObject.getRapidRunData().getDaytimeElectricitySelfConsumed_MWh());
pl_productionChartDay.addDataItem(daytimeSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartDay.addDataItem(daytimeSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);



DataItem daytimeImport = new DataItem();
daytimeImport.setValue(dataObject.getRapidRunData().getDaytimeImport_MWh(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartDay.addDataItem(daytimeImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);


DataItem daytimeExport = new DataItem();
daytimeExport.setValue(dataObject.getRapidRunData().getDaytimeExport_MWh(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartDay.addDataItem(daytimeExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);


// Night
DataItem nighttimeSelfConsumed = new DataItem();
nighttimeSelfConsumed.setValue(dataObject.getRapidRunData().getNighttimeElectricitySelfConsumed_MWh());
pl_productionChartNight.addDataItem(nighttimeSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartNight.addDataItem(nighttimeSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem nighttimeImport = new DataItem();
nighttimeImport.setValue(dataObject.getRapidRunData().getNighttimeImport_MWh(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartNight.addDataItem(nighttimeImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem nighttimeExport = new DataItem();
nighttimeExport.setValue(dataObject.getRapidRunData().getNighttimeExport_MWh(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartNight.addDataItem(nighttimeExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);



double dayProduction_MWh = dataObject.getRapidRunData().getDaytimeElectricityProduced_MWh();
double dayConsumption_MWh = dataObject.getRapidRunData().getDaytimeElectricityConsumed_MWh();
double nightProduction_MWh = dataObject.getRapidRunData().getNighttimeElectricityProduced_MWh();
double nightConsumption_MWh = dataObject.getRapidRunData().getNighttimeElectricityConsumed_MWh();
double chartScale_MWh = max(max(dayProduction_MWh, nightProduction_MWh), max(dayConsumption_MWh, nightConsumption_MWh));
pl_productionChartDay.setFixedScale(chartScale_MWh);
pl_productionChartNight.setFixedScale(chartScale_MWh);
pl_consumptionChartDay.setFixedScale(chartScale_MWh);
pl_consumptionChartNight.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_productionTextDay.setText("Opwek" + System.lineSeparator() + roundToInt(dayProduction_MWh*1000) + " kWh");
	t_consumptionTextDay.setText("Gebruik" + System.lineSeparator() + roundToInt(dayConsumption_MWh*1000) + " kWh");
	t_productionTextNight.setText("Opwek" + System.lineSeparator() + roundToInt(nightProduction_MWh*1000) + " kWh");
	t_consumptionTextNight.setText("Gebruik" + System.lineSeparator() + roundToInt(nightConsumption_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_productionTextDay.setText("Opwek" + System.lineSeparator() + roundToInt(dayProduction_MWh) + " MWh");
	t_consumptionTextDay.setText("Gebruik" + System.lineSeparator() + roundToInt(dayConsumption_MWh) + " MWh");
	t_productionTextNight.setText("Opwek" + System.lineSeparator() + roundToInt(nightProduction_MWh) + " MWh");
	t_consumptionTextNight.setText("Gebruik" + System.lineSeparator() + roundToInt(nightConsumption_MWh) + " MWh");
} else {
	t_productionTextDay.setText("Opwek" + System.lineSeparator() + roundToDecimal(dayProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextDay.setText("Gebruik" + System.lineSeparator() + roundToDecimal(dayConsumption_MWh/1000,1) + " GWh");
	t_productionTextNight.setText("Opwek" + System.lineSeparator() + roundToDecimal(nightProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextNight.setText("Gebruik" + System.lineSeparator() + roundToDecimal(nightConsumption_MWh/1000,1) + " GWh");
}

/*ALCODEEND*/}

double f_setElectricitySelfConsumptionBarChartWeekdayWeekend(I_EnergyData dataObject)
{/*ALCODESTART::1762958101321*/
// Weekday
DataItem weekdaySelfConsumed = new DataItem();
weekdaySelfConsumed.setValue(dataObject.getRapidRunData().getWeekdayElectricitySelfConsumed_MWh());
pl_productionChartWeekday.addDataItem(weekdaySelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartWeekday.addDataItem(weekdaySelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem weekdayimeImport = new DataItem();
weekdayimeImport.setValue(dataObject.getRapidRunData().getWeekdayImport_MWh(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartWeekday.addDataItem(weekdayimeImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem weekdayExport = new DataItem();
weekdayExport.setValue(dataObject.getRapidRunData().getWeekdayExport_MWh(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartWeekday.addDataItem(weekdayExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Weekend
DataItem weekendSelfConsumed = new DataItem();
weekendSelfConsumed.setValue(dataObject.getRapidRunData().getWeekendElectricitySelfConsumed_MWh());
pl_productionChartWeekend.addDataItem(weekendSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartWeekend.addDataItem(weekendSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem weekendImport = new DataItem();
weekendImport.setValue(dataObject.getRapidRunData().getWeekendImport_MWh(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartWeekend.addDataItem(weekendImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem weekendExport = new DataItem();
weekendExport.setValue(dataObject.getRapidRunData().getWeekendExport_MWh(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartWeekend.addDataItem(weekendExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);



double weekdayProduction_MWh = dataObject.getRapidRunData().getWeekdayElectricityProduced_MWh();
double weekdayConsumption_MWh = dataObject.getRapidRunData().getWeekdayElectricityConsumed_MWh();
double weekendProduction_MWh = dataObject.getRapidRunData().getWeekendElectricityProduced_MWh();
double weekendConsumption_MWh = dataObject.getRapidRunData().getWeekendElectricityConsumed_MWh();
double chartScale_MWh = max(max(weekdayProduction_MWh, weekendProduction_MWh), max(weekdayConsumption_MWh, weekendConsumption_MWh));
pl_productionChartWeekday.setFixedScale(chartScale_MWh);
pl_productionChartWeekend.setFixedScale(chartScale_MWh);
pl_consumptionChartWeekday.setFixedScale(chartScale_MWh);
pl_consumptionChartWeekend.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_productionTextWeekday.setText("Opwek" + System.lineSeparator() + roundToInt(weekdayProduction_MWh*1000) + " kWh");
	t_consumptionTextWeekday.setText("Gebruik" + System.lineSeparator() + roundToInt(weekdayConsumption_MWh*1000) + " kWh");
	t_productionTextWeekend.setText("Opwek" + System.lineSeparator() + roundToInt(weekendProduction_MWh*1000) + " kWh");
	t_consumptionTextWeekend.setText("Gebruik" + System.lineSeparator() + roundToInt(weekendConsumption_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_productionTextWeekday.setText("Opwek" + System.lineSeparator() + roundToInt(weekdayProduction_MWh) + " MWh");
	t_consumptionTextWeekday.setText("Gebruik" + System.lineSeparator() + roundToInt(weekdayConsumption_MWh) + " MWh");
	t_productionTextWeekend.setText("Opwek" + System.lineSeparator() + roundToInt(weekendProduction_MWh) + " MWh");
	t_consumptionTextWeekend.setText("Gebruik" + System.lineSeparator() + roundToInt(weekendConsumption_MWh) + " MWh");
} else {
	t_productionTextWeekday.setText("Opwek" + System.lineSeparator() + roundToDecimal(weekdayProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextWeekday.setText("Gebruik" + System.lineSeparator() + roundToDecimal(weekdayConsumption_MWh/1000,1) + " GWh");
	t_productionTextWeekend.setText("Opwek" + System.lineSeparator() + roundToDecimal(weekendProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextWeekend.setText("Gebruik" + System.lineSeparator() + roundToDecimal(weekendConsumption_MWh/1000,1) + " GWh");
}
/*ALCODEEND*/}

double f_resetCharts()
{/*ALCODESTART::1762958101323*/
pl_productionChartBalanceTotal.removeAll();
t_productionTextYear.setText("Opwek");
pl_consumptionChartBalanceTotal.removeAll();
t_consumptionTextYear.setText("Gebruik");
pl_productionChartSummer.removeAll();
t_productionTextSummer.setText("Opwek");
pl_consumptionChartSummer.removeAll();
t_consumptionTextSummer.setText("Gebruik");
pl_productionChartWinter.removeAll();
t_productionTextWinter.setText("Opwek");
pl_consumptionChartWinter.removeAll();
t_consumptionTextWinter.setText("Gebruik");
pl_productionChartDay.removeAll();
t_productionTextDay.setText("Opwek");
pl_consumptionChartDay.removeAll();
t_consumptionTextDay.setText("Gebruik");
pl_productionChartNight.removeAll();
t_productionTextNight.setText("Opwek");
pl_consumptionChartNight.removeAll();
t_consumptionTextNight.setText("Gebruik");
pl_productionChartWeekday.removeAll();
t_productionTextWeekday.setText("Opwek");
pl_consumptionChartWeekday.removeAll();
t_consumptionTextWeekday.setText("Gebruik");
pl_productionChartWeekend.removeAll();
t_productionTextWeekend.setText("Opwek");
pl_consumptionChartWeekend.removeAll();
t_consumptionTextWeekend.setText("Gebruik");
/*ALCODEEND*/}

double f_setVisiblity()
{/*ALCODESTART::1762958101333*/
gr_Total.setVisible(false);
gr_SummerWinter.setVisible(false);
gr_DayNight.setVisible(false);
gr_WeekdayWeekend.setVisible(false);
gr_monthlyTotals.setVisible(false);

if (radio_period.getValue() == 0) {
	gr_Total.setVisible(true);
} else if (radio_period.getValue() == 1) {
	gr_SummerWinter.setVisible(true);
} else if (radio_period.getValue() == 2) {
	gr_DayNight.setVisible(true);
} else if (radio_period.getValue() == 3) {
	gr_WeekdayWeekend.setVisible(true);
}
/*ALCODEEND*/}

double f_setMonthlyCharts()
{/*ALCODESTART::1762958101347*/
bc_productionMonthlyTotals.removeAll();
bc_consumptionMonthlyTotals.removeAll();

I_EnergyData data = uI_Results.f_getSelectedObjectData();

ZeroAccumulator acc_annualElectricityBalance_kW;
if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
	acc_annualElectricityBalance_kW = uI_Results.v_gridNode.acc_annualElectricityBalance_kW;
}
else {
	acc_annualElectricityBalance_kW = data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY);
}

double[] monthlyExport_kWh = new double[12];
double[] monthlyImport_kWh = new double[12];

int[] daysPerMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
if (uI_Results.energyModel.p_year % 4 == 0 && uI_Results.energyModel.p_year % 100 != 0 && uI_Results.energyModel.p_year % 400 == 0) {
	daysPerMonth[1] += 1;
}

double signalResolution_h = acc_annualElectricityBalance_kW.getSignalResolution_h();
double[] electricityBalance_kW = acc_annualElectricityBalance_kW.getTimeSeries_kW();

int stepsInPreviousMonths = 0;
for (int i = 0; i < 12; i++) {
	int daysInThisMonth = daysPerMonth[i];
	int stepsInThisMonth = roundToInt(daysInThisMonth * 24 / signalResolution_h);
	for (int j = stepsInPreviousMonths; j < stepsInPreviousMonths + stepsInThisMonth; j++) {
		if (electricityBalance_kW[j] < 0) {
			monthlyExport_kWh[i] += -electricityBalance_kW[j] * signalResolution_h;
		}
		else {
			monthlyImport_kWh[i] += electricityBalance_kW[j] * signalResolution_h;
		}
	}
	stepsInPreviousMonths += stepsInThisMonth;
}

String[] monthNames = new String[]{"Januari", "Februari", "Maart", "April", "Mei", "Juni", "Juli", "Augustus", "September", "Oktober", "November", "December"};
for (int i = 0; i < 12; i++) {
	DataItem di_export = new DataItem();
	di_export.setValue(monthlyExport_kWh[i] / 1000);
	bc_productionMonthlyTotals.addDataItem(di_export, monthNames[i], new Color(210,255,191));
	DataItem di_import = new DataItem();
	di_import.setValue(monthlyImport_kWh[i] / 1000);
	bc_consumptionMonthlyTotals.addDataItem(di_import, monthNames[i], new Color(210, 35, 55));
}
/*ALCODEEND*/}

double f_setHeatBarChartTotal(I_EnergyData dataObject)
{/*ALCODESTART::1762958502278*/
if ( !dataObject.getRapidRunData().activeEnergyCarriers.contains(OL_EnergyCarriers.HEAT) ) {
	return;
}

double totalConsumption_MWh = 0;
double totalProduction_MWh = 0;
double consumption_MWh = 0;
double production_MWh= 0;
DataItem di = null;

// Consumption
// Space heating

// DHW
if (dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.keySet().contains(OL_AssetFlowCategories.hotWaterConsumption_kW)) {
	consumption_MWh = dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.hotWaterConsumption_kW).getIntegral_MWh();
	if ( consumption_MWh > uI_Results.p_cutOff_MWh ) {
		totalConsumption_MWh += consumption_MWh;
		di = new DataItem();
		di.setValue(consumption_MWh);
		pl_consumptionChartBalanceTotal.addDataItem(di, "Warmwater vraag", darkSlateBlue);
	}
}

// Production
// Standard Heating Assets
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().am_totalHeatFromEnergyCarrier_kW.keySet()) {
	production_MWh = dataObject.getRapidRunData().am_totalHeatFromEnergyCarrier_kW.get(EC).getIntegral_MWh();
	if ( production_MWh > uI_Results.p_cutOff_MWh ) {
		totalProduction_MWh += production_MWh;
		di = new DataItem();
		di.setValue(production_MWh);
		pl_productionChartBalanceTotal.addDataItem(di, uI_Results.f_getName(EC), uI_Results.cm_consumptionColors.get(EC));
	}
}
// Heatpumps
if (dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.keySet().contains(OL_AssetFlowCategories.heatPumpElectricityConsumption_kW)) {
	production_MWh = dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.heatPumpElectricityConsumption_kW).getIntegral_MWh();
	if (production_MWh > uI_Results.p_cutOff_MWh) {
		totalProduction_MWh += production_MWh;
		di = new DataItem();
		di.setValue(production_MWh);
		pl_productionChartBalanceTotal.addDataItem(di, "Warmtepomp Electriciteit", uI_Results.cm_assetFlowColors.get(OL_AssetFlowCategories.heatPumpElectricityConsumption_kW));
	
		production_MWh = dataObject.getRapidRunData().acc_totalPrimaryEnergyProductionHeatpumps_kW.getIntegral_MWh();
		totalProduction_MWh += production_MWh;
		di = new DataItem();
		di.setValue(production_MWh);
		pl_productionChartBalanceTotal.addDataItem(di, "Warmtepomp Omgevingswarmte", lightSkyBlue);
	}
}
// PVT
if (dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.keySet().contains(OL_AssetFlowCategories.ptProductionHeat_kW)) {
	production_MWh = dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.ptProductionHeat_kW).getIntegral_MWh();
	if (production_MWh > uI_Results.p_cutOff_MWh) {
		totalProduction_MWh += production_MWh;
		di = new DataItem();
		di.setValue(production_MWh);
		pl_productionChartBalanceTotal.addDataItem(di, "PT Opwek", uI_Results.cm_assetFlowColors.get(OL_AssetFlowCategories.ptProductionHeat_kW));
	}
}
// Heatgrid
if (dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.keySet().contains(OL_AssetFlowCategories.districtHeatDelivery_kW)) {
	production_MWh = dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.districtHeatDelivery_kW).getIntegral_MWh();
	if (production_MWh > uI_Results.p_cutOff_MWh) {
		totalProduction_MWh += production_MWh;
		di = new DataItem();
		di.setValue(production_MWh);
		pl_productionChartBalanceTotal.addDataItem(di, "Warmtenet Verbruik", uI_Results.cm_assetFlowColors.get(OL_AssetFlowCategories.districtHeatDelivery_kW));
		
		double import_MWh = dataObject.getRapidRunData().getTotalImport_MWh(OL_EnergyCarriers.HEAT);
		traceln("prod %s", production_MWh);
		traceln("import %s", import_MWh);
		double losses_MWh = max(0, import_MWh - production_MWh);
		
		di = new DataItem();
		di.setValue(losses_MWh);
		pl_productionChartBalanceTotal.addDataItem(di, "Warmtenet Verliezen", gray);	
	}
}


double chartScale_MWh = max(totalConsumption_MWh, totalProduction_MWh);

if (chartScale_MWh<10) {
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToInt(totalConsumption_MWh*1000) + " kWh");
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToInt(totalProduction_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToInt(totalConsumption_MWh) + " MWh");
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToInt(totalProduction_MWh) + " MWh");
} else {
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToDecimal(totalConsumption_MWh/1000,1) + " GWh");
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToDecimal(totalProduction_MWh/1000, 1) + " GWh");
}
/*ALCODEEND*/}

double f_setHeatBarChartSummerWinter(I_EnergyData dataObject)
{/*ALCODESTART::1762958502280*/

/*ALCODEEND*/}

double f_setHeatBarChartDayNight(I_EnergyData dataObject)
{/*ALCODESTART::1762958502282*/

/*ALCODEEND*/}

double f_setHeatBarChartWeekdayWeekend(I_EnergyData dataObject)
{/*ALCODESTART::1762958502284*/

/*ALCODEEND*/}

double f_setEnergyBarChartTotal(I_EnergyData dataObject)
{/*ALCODESTART::1762958604004*/
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeConsumptionEnergyCarriers) {
	if (dataObject.getRapidRunData().getTotalImport_MWh(EC) > uI_Results.p_cutOff_MWh) {
		DataItem totalImport = new DataItem();
		totalImport.setValue(dataObject.getRapidRunData().getTotalImport_MWh(EC));
		pl_consumptionChartBalanceTotal.addDataItem(totalImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_consumptionColors.get(EC));
	}
}
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeProductionEnergyCarriers) {
	if (dataObject.getRapidRunData().getTotalExport_MWh(EC) > uI_Results.p_cutOff_MWh) {
		DataItem totalExport = new DataItem();
		totalExport.setValue(dataObject.getRapidRunData().getTotalExport_MWh(EC));
		pl_productionChartBalanceTotal.addDataItem(totalExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
}

double consumption_MWh = dataObject.getRapidRunData().getTotalEnergyImport_MWh();
double production_MWh = dataObject.getRapidRunData().getTotalEnergyExport_MWh();
double chartScale_MWh = max(production_MWh, consumption_MWh);
pl_consumptionChartBalanceTotal.setFixedScale(chartScale_MWh);
pl_productionChartBalanceTotal.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_consumptionTextYear.setText("Import" + System.lineSeparator() + roundToInt(consumption_MWh*1000) + " kWh");
	t_productionTextYear.setText("Export" + System.lineSeparator() + roundToInt(production_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_consumptionTextYear.setText("Import" + System.lineSeparator() + roundToInt(consumption_MWh) + " MWh");
	t_productionTextYear.setText("Export" + System.lineSeparator() + roundToInt(production_MWh) + " MWh");
} else {
	t_consumptionTextYear.setText("Import" + System.lineSeparator() + roundToDecimal(consumption_MWh/1000,1) + " GWh");
	t_productionTextYear.setText("Export" + System.lineSeparator() + roundToDecimal(production_MWh/1000, 1) + " GWh");
}

/*ALCODEEND*/}

double f_setEnergyBarChartSummerWinter(I_EnergyData dataObject)
{/*ALCODESTART::1762958604006*/
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeConsumptionEnergyCarriers) {
	if (dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(EC).getIntegralPos_MWh() > uI_Results.p_cutOff_MWh) {
		DataItem summerImport = new DataItem();
		summerImport.setValue(dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(EC).getIntegralPos_MWh());
		pl_consumptionChartSummer.addDataItem(summerImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	if (dataObject.getRapidRunData().am_winterWeekBalanceAccumulators_kW.get(EC).getIntegralPos_MWh() > uI_Results.p_cutOff_MWh) {
		DataItem winterImport = new DataItem();
		winterImport.setValue(dataObject.getRapidRunData().am_winterWeekBalanceAccumulators_kW.get(EC).getIntegralPos_MWh());
		pl_consumptionChartWinter.addDataItem(winterImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
}
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeProductionEnergyCarriers) {
	if (-dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(EC).getIntegralNeg_MWh() > uI_Results.p_cutOff_MWh) {
		DataItem summerExport = new DataItem();
		summerExport.setValue(-dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(EC).getIntegralNeg_MWh());
		pl_productionChartSummer.addDataItem(summerExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	if (-dataObject.getRapidRunData().am_winterWeekBalanceAccumulators_kW.get(EC).getIntegralNeg_MWh() > uI_Results.p_cutOff_MWh) {
		DataItem winterExport = new DataItem();
		winterExport.setValue(-dataObject.getRapidRunData().am_winterWeekBalanceAccumulators_kW.get(EC).getIntegralNeg_MWh());
		pl_productionChartWinter.addDataItem(winterExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
}

double summerConsumption_MWh = dataObject.getRapidRunData().getSummerWeekEnergyConsumed_MWh();
double summerProduction_MWh = dataObject.getRapidRunData().getSummerWeekEnergyProduced_MWh();
double winterConsumption_MWh = dataObject.getRapidRunData().getWinterWeekEnergyConsumed_MWh();
double winterProduction_MWh = dataObject.getRapidRunData().getWinterWeekEnergyProduced_MWh();
double chartScale_MWh = max(max(summerProduction_MWh, winterProduction_MWh), max(summerConsumption_MWh, winterConsumption_MWh));
pl_productionChartSummer.setFixedScale(chartScale_MWh);
pl_productionChartWinter.setFixedScale(chartScale_MWh);
pl_consumptionChartSummer.setFixedScale(chartScale_MWh);
pl_consumptionChartWinter.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_consumptionTextSummer.setText("Import" + System.lineSeparator() + roundToInt(summerConsumption_MWh*1000) + " kWh");
	t_productionTextSummer.setText("Export" + System.lineSeparator() + roundToInt(summerProduction_MWh*1000) + " kWh");
	t_consumptionTextWinter.setText("Import" + System.lineSeparator() + roundToInt(winterConsumption_MWh*1000) + " kWh");
	t_productionTextWinter.setText("Export" + System.lineSeparator() + roundToInt(winterProduction_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_consumptionTextSummer.setText("Import" + System.lineSeparator() + roundToInt(summerConsumption_MWh) + " MWh");
	t_productionTextSummer.setText("Export" + System.lineSeparator() + roundToInt(summerProduction_MWh) + " MWh");
	t_consumptionTextWinter.setText("Import" + System.lineSeparator() + roundToInt(winterConsumption_MWh) + " MWh");
	t_productionTextWinter.setText("Export" + System.lineSeparator() + roundToInt(winterProduction_MWh) + " MWh");
} else {
	t_consumptionTextSummer.setText("Import" + System.lineSeparator() + roundToDecimal(summerConsumption_MWh/1000,1) + " GWh");
	t_productionTextSummer.setText("Export" + System.lineSeparator() + roundToDecimal(summerProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextWinter.setText("Import" + System.lineSeparator() + roundToDecimal(winterConsumption_MWh/1000,1) + " GWh");
	t_productionTextWinter.setText("Export" + System.lineSeparator() + roundToDecimal(winterProduction_MWh/1000, 1) + " GWh");
}
/*ALCODEEND*/}

double f_setEnergyBarChartDayNight(I_EnergyData dataObject)
{/*ALCODESTART::1762958604008*/
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeConsumptionEnergyCarriers) {
	if (dataObject.getRapidRunData().getDaytimeImport_MWh(EC) > uI_Results.p_cutOff_MWh) {
		DataItem daytimeImport = new DataItem();
		daytimeImport.setValue(dataObject.getRapidRunData().getDaytimeImport_MWh(EC));
		pl_consumptionChartDay.addDataItem(daytimeImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	if (dataObject.getRapidRunData().getNighttimeImport_MWh(EC) > uI_Results.p_cutOff_MWh) {
		DataItem nighttimeImport = new DataItem();
		nighttimeImport.setValue(dataObject.getRapidRunData().getNighttimeImport_MWh(EC));
		pl_consumptionChartNight.addDataItem(nighttimeImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}	
}
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeProductionEnergyCarriers) {
	if (dataObject.getRapidRunData().getDaytimeExport_MWh(EC) > uI_Results.p_cutOff_MWh) {	
		DataItem daytimeExport = new DataItem();
		daytimeExport.setValue(dataObject.getRapidRunData().getDaytimeExport_MWh(EC));
		pl_productionChartDay.addDataItem(daytimeExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}	
	if (dataObject.getRapidRunData().getNighttimeExport_MWh(EC) > uI_Results.p_cutOff_MWh) {
		DataItem nighttimeExport = new DataItem();
		nighttimeExport.setValue(dataObject.getRapidRunData().getNighttimeExport_MWh(EC));
		pl_productionChartNight.addDataItem(nighttimeExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
}

double dayProduction_MWh = dataObject.getRapidRunData().getDaytimeEnergyProduced_MWh();
double dayConsumption_MWh = dataObject.getRapidRunData().getDaytimeEnergyConsumed_MWh();
double nightProduction_MWh = dataObject.getRapidRunData().getNighttimeEnergyProduced_MWh();
double nightConsumption_MWh = dataObject.getRapidRunData().getNighttimeEnergyConsumed_MWh();
double chartScale_MWh = max(max(dayProduction_MWh, nightProduction_MWh), max(dayConsumption_MWh, nightConsumption_MWh));
pl_productionChartDay.setFixedScale(chartScale_MWh);
pl_productionChartNight.setFixedScale(chartScale_MWh);
pl_consumptionChartDay.setFixedScale(chartScale_MWh);
pl_consumptionChartNight.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_consumptionTextDay.setText("Import" + System.lineSeparator() + roundToInt(dayConsumption_MWh*1000) + " kWh");
	t_productionTextDay.setText("Export" + System.lineSeparator() + roundToInt(dayProduction_MWh*1000) + " kWh");
	t_consumptionTextNight.setText("Import" + System.lineSeparator() + roundToInt(nightConsumption_MWh*1000) + " kWh");
	t_productionTextNight.setText("Export" + System.lineSeparator() + roundToInt(nightProduction_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_consumptionTextDay.setText("Import" + System.lineSeparator() + roundToInt(dayConsumption_MWh) + " MWh");
	t_productionTextDay.setText("Export" + System.lineSeparator() + roundToInt(dayProduction_MWh) + " MWh");
	t_consumptionTextNight.setText("Import" + System.lineSeparator() + roundToInt(nightConsumption_MWh) + " MWh");
	t_productionTextNight.setText("Export" + System.lineSeparator() + roundToInt(nightProduction_MWh) + " MWh");
} else {
	t_consumptionTextDay.setText("Import" + System.lineSeparator() + roundToDecimal(dayConsumption_MWh/1000,1) + " GWh");
	t_productionTextDay.setText("Export" + System.lineSeparator() + roundToDecimal(dayProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextNight.setText("Import" + System.lineSeparator() + roundToDecimal(nightConsumption_MWh/1000,1) + " GWh");
	t_productionTextNight.setText("Export" + System.lineSeparator() + roundToDecimal(nightProduction_MWh/1000, 1) + " GWh");
}

/*ALCODEEND*/}

double f_setEnergyBarChartWeekdayWeekend(I_EnergyData dataObject)
{/*ALCODESTART::1762958604010*/
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeConsumptionEnergyCarriers) {
	if (dataObject.getRapidRunData().getWeekdayImport_MWh(EC) > uI_Results.p_cutOff_MWh) {	
		DataItem weekdayImport = new DataItem();
		weekdayImport.setValue(dataObject.getRapidRunData().getWeekdayImport_MWh(EC));
		pl_consumptionChartWeekday.addDataItem(weekdayImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	if (dataObject.getRapidRunData().getWeekendImport_MWh(EC) > uI_Results.p_cutOff_MWh) {	
		DataItem weekendImport = new DataItem();
		weekendImport.setValue(dataObject.getRapidRunData().getWeekendImport_MWh(EC));
		pl_consumptionChartWeekend.addDataItem(weekendImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
}
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeProductionEnergyCarriers) {
	if (dataObject.getRapidRunData().getWeekdayExport_MWh(EC) > uI_Results.p_cutOff_MWh) {	
		DataItem weekdayExport = new DataItem();
		weekdayExport.setValue(dataObject.getRapidRunData().getWeekdayExport_MWh(EC));
		pl_productionChartWeekday.addDataItem(weekdayExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	if (dataObject.getRapidRunData().getWeekendExport_MWh(EC) > uI_Results.p_cutOff_MWh) {	
		DataItem weekendExport = new DataItem();
		weekendExport.setValue(dataObject.getRapidRunData().getWeekendExport_MWh(EC));
		pl_productionChartWeekend.addDataItem(weekendExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
}

double weekdayProduction_MWh = dataObject.getRapidRunData().getWeekdayEnergyProduced_MWh();
double weekdayConsumption_MWh = dataObject.getRapidRunData().getWeekdayEnergyConsumed_MWh();
double weekendProduction_MWh = dataObject.getRapidRunData().getWeekendEnergyProduced_MWh();
double weekendConsumption_MWh = dataObject.getRapidRunData().getWeekendEnergyConsumed_MWh();
double chartScale_MWh = max(max(weekdayProduction_MWh, weekendProduction_MWh), max(weekdayConsumption_MWh, weekendConsumption_MWh));
pl_productionChartWeekday.setFixedScale(chartScale_MWh);
pl_productionChartWeekend.setFixedScale(chartScale_MWh);
pl_consumptionChartWeekday.setFixedScale(chartScale_MWh);
pl_consumptionChartWeekend.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_consumptionTextWeekday.setText("Import" + System.lineSeparator() + roundToInt(weekdayConsumption_MWh*1000) + " kWh");
	t_productionTextWeekday.setText("Export" + System.lineSeparator() + roundToInt(weekdayProduction_MWh*1000) + " kWh");
	t_consumptionTextWeekend.setText("Import" + System.lineSeparator() + roundToInt(weekendConsumption_MWh*1000) + " kWh");
	t_productionTextWeekend.setText("Export" + System.lineSeparator() + roundToInt(weekendProduction_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_consumptionTextWeekday.setText("Import" + System.lineSeparator() + roundToInt(weekdayConsumption_MWh) + " MWh");
	t_productionTextWeekday.setText("Export" + System.lineSeparator() + roundToInt(weekdayProduction_MWh) + " MWh");
	t_consumptionTextWeekend.setText("Import" + System.lineSeparator() + roundToInt(weekendConsumption_MWh) + " MWh");
	t_productionTextWeekend.setText("Export" + System.lineSeparator() + roundToInt(weekendProduction_MWh) + " MWh");
} else {
	t_consumptionTextWeekday.setText("Import" + System.lineSeparator() + roundToDecimal(weekdayConsumption_MWh/1000,1) + " GWh");
	t_productionTextWeekday.setText("Export" + System.lineSeparator() + roundToDecimal(weekdayProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextWeekend.setText("Import" + System.lineSeparator() + roundToDecimal(weekendConsumption_MWh/1000,1) + " GWh");
	t_productionTextWeekend.setText("Export" + System.lineSeparator() + roundToDecimal(weekendProduction_MWh/1000, 1) + " GWh");
}
/*ALCODEEND*/}

double f_setElectricityAssetsBarChartTotal(I_EnergyData dataObject)
{/*ALCODESTART::1762963209071*/
double totalConsumption_MWh = 0;
double totalProduction_MWh = 0;
double consumption_MWh = 0;
double production_MWh= 0;
DataItem di = null;

for (OL_AssetFlowCategories AFC : dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.keySet()) {
	if (uI_Results.v_electricAssetFlows.contains(AFC)) {
		if (uI_Results.v_consumptionAssetFlows.contains(AFC)) {
			consumption_MWh = dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(AFC).getIntegral_MWh();
			totalConsumption_MWh += consumption_MWh;
			di = new DataItem();
			di.setValue(consumption_MWh);
			pl_consumptionChartBalanceTotal.addDataItem(di, uI_Results.lm_assetFlowLabels.get(AFC), uI_Results.cm_assetFlowColors.get(AFC));
		}
		else {
			production_MWh = dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(AFC).getIntegral_MWh();
			totalProduction_MWh += production_MWh;
			di = new DataItem();
			di.setValue(production_MWh);
			pl_productionChartBalanceTotal.addDataItem(di, uI_Results.lm_assetFlowLabels.get(AFC), uI_Results.cm_assetFlowColors.get(AFC));	
		}
	}
}

double chartScale_MWh = max(totalConsumption_MWh, totalProduction_MWh);

if (chartScale_MWh<10) {
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToInt(totalConsumption_MWh*1000) + " kWh");
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToInt(totalProduction_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToInt(totalConsumption_MWh) + " MWh");
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToInt(totalProduction_MWh) + " MWh");
} else {
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToDecimal(totalConsumption_MWh/1000,1) + " GWh");
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToDecimal(totalProduction_MWh/1000, 1) + " GWh");
}
/*ALCODEEND*/}

double f_setElectricityAssetsBarChartSummerWinter(I_EnergyData dataObject)
{/*ALCODESTART::1762963209166*/
double totalSummerConsumption_MWh = 0;
double totalSummerProduction_MWh = 0;
double totalWinterConsumption_MWh = 0;
double totalWinterProduction_MWh = 0;
double consumption_MWh = 0;
double production_MWh = 0;
DataItem di = null;

for (OL_AssetFlowCategories AFC : dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.keySet()) {
	if (uI_Results.v_electricAssetFlows.contains(AFC)) {
		if (uI_Results.v_consumptionAssetFlows.contains(AFC)) {
			// Summer
			consumption_MWh = dataObject.getRapidRunData().am_assetFlowsSummerWeek_kW.get(AFC).getIntegral_MWh();
			totalSummerConsumption_MWh += consumption_MWh;
			di = new DataItem();
			di.setValue(consumption_MWh);
			pl_consumptionChartSummer.addDataItem(di, uI_Results.lm_assetFlowLabels.get(AFC), uI_Results.cm_assetFlowColors.get(AFC));
			// Winter
			consumption_MWh = dataObject.getRapidRunData().am_assetFlowsWinterWeek_kW.get(AFC).getIntegral_MWh();
			totalWinterConsumption_MWh += consumption_MWh;
			di = new DataItem();
			di.setValue(consumption_MWh);
			pl_consumptionChartWinter.addDataItem(di, uI_Results.lm_assetFlowLabels.get(AFC), uI_Results.cm_assetFlowColors.get(AFC));
		}
		else {
			// Summer
			production_MWh = dataObject.getRapidRunData().am_assetFlowsSummerWeek_kW.get(AFC).getIntegral_MWh();
			totalSummerProduction_MWh += production_MWh;
			di = new DataItem();
			di.setValue(production_MWh);
			pl_productionChartSummer.addDataItem(di, uI_Results.lm_assetFlowLabels.get(AFC), uI_Results.cm_assetFlowColors.get(AFC));	
			// Winter
			production_MWh = dataObject.getRapidRunData().am_assetFlowsWinterWeek_kW.get(AFC).getIntegral_MWh();
			totalWinterProduction_MWh += production_MWh;
			di = new DataItem();
			di.setValue(production_MWh);
			pl_productionChartWinter.addDataItem(di, uI_Results.lm_assetFlowLabels.get(AFC), uI_Results.cm_assetFlowColors.get(AFC));	
		}
	}
}

double chartScale_MWh = max(max(totalSummerProduction_MWh, totalWinterProduction_MWh), max(totalSummerConsumption_MWh, totalWinterConsumption_MWh));
pl_productionChartSummer.setFixedScale(chartScale_MWh);
pl_productionChartWinter.setFixedScale(chartScale_MWh);
pl_consumptionChartSummer.setFixedScale(chartScale_MWh);
pl_consumptionChartWinter.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_productionTextSummer.setText("Opwek" + System.lineSeparator() + roundToInt(totalSummerProduction_MWh*1000) + " kWh");
	t_consumptionTextSummer.setText("Gebruik" + System.lineSeparator() + roundToInt(totalSummerConsumption_MWh*1000) + " kWh");
	t_productionTextWinter.setText("Opwek" + System.lineSeparator() + roundToInt(totalWinterProduction_MWh*1000) + " kWh");
	t_consumptionTextWinter.setText("Gebruik" + System.lineSeparator() + roundToInt(totalWinterConsumption_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_productionTextSummer.setText("Opwek" + System.lineSeparator() + roundToInt(totalSummerProduction_MWh) + " MWh");
	t_consumptionTextSummer.setText("Gebruik" + System.lineSeparator() + roundToInt(totalSummerConsumption_MWh) + " MWh");
	t_productionTextWinter.setText("Opwek" + System.lineSeparator() + roundToInt(totalWinterProduction_MWh) + " MWh");
	t_consumptionTextWinter.setText("Gebruik" + System.lineSeparator() + roundToInt(totalWinterConsumption_MWh) + " MWh");
} else {
	t_productionTextSummer.setText("Opwek" + System.lineSeparator() + roundToDecimal(totalSummerProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextSummer.setText("Gebruik" + System.lineSeparator() + roundToDecimal(totalSummerConsumption_MWh/1000,1) + " GWh");
	t_productionTextWinter.setText("Opwek" + System.lineSeparator() + roundToDecimal(totalWinterProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextWinter.setText("Gebruik" + System.lineSeparator() + roundToDecimal(totalWinterConsumption_MWh/1000,1) + " GWh");
}
/*ALCODEEND*/}

double f_setElectricityAssetsBarChartDayNight(I_EnergyData dataObject)
{/*ALCODESTART::1762963209261*/
double totalDayConsumption_MWh = 0;
double totalDayProduction_MWh = 0;
double totalNightConsumption_MWh = 0;
double totalNightProduction_MWh = 0;
double dayConsumption_MWh = 0;
double dayProduction_MWh = 0;
double nightConsumption_MWh = 0;
double nightProduction_MWh = 0;

DataItem di = null;

for (OL_AssetFlowCategories AFC : dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.keySet()) {
	if (uI_Results.v_electricAssetFlows.contains(AFC)) {
		if (uI_Results.v_consumptionAssetFlows.contains(AFC)) {
			// Day
			dayConsumption_MWh = dataObject.getRapidRunData().am_assetFlowsDaytime_kW.get(AFC).getIntegral_MWh();
			totalDayConsumption_MWh += dayConsumption_MWh;
			di = new DataItem();
			di.setValue(dayConsumption_MWh);
			pl_consumptionChartDay.addDataItem(di, uI_Results.lm_assetFlowLabels.get(AFC), uI_Results.cm_assetFlowColors.get(AFC));
			// Night
			nightConsumption_MWh = dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(AFC).getIntegral_MWh() - dayConsumption_MWh;
			totalNightConsumption_MWh += nightConsumption_MWh;
			di = new DataItem();
			di.setValue(nightConsumption_MWh);
			pl_consumptionChartNight.addDataItem(di, uI_Results.lm_assetFlowLabels.get(AFC), uI_Results.cm_assetFlowColors.get(AFC));
		}
		else {
			// Day
			dayProduction_MWh = dataObject.getRapidRunData().am_assetFlowsDaytime_kW.get(AFC).getIntegral_MWh();
			totalDayProduction_MWh += dayProduction_MWh;
			di = new DataItem();
			di.setValue(dayProduction_MWh);
			pl_productionChartDay.addDataItem(di, uI_Results.lm_assetFlowLabels.get(AFC), uI_Results.cm_assetFlowColors.get(AFC));	
			// Night
			nightProduction_MWh = dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(AFC).getIntegral_MWh() - dayProduction_MWh;
			totalNightProduction_MWh += nightProduction_MWh;
			di = new DataItem();
			di.setValue(nightProduction_MWh);
			pl_productionChartNight.addDataItem(di, uI_Results.lm_assetFlowLabels.get(AFC), uI_Results.cm_assetFlowColors.get(AFC));	
		}
	}
}

double chartScale_MWh = max(max(totalDayProduction_MWh, totalNightProduction_MWh), max(totalDayConsumption_MWh, totalNightConsumption_MWh));
pl_productionChartDay.setFixedScale(chartScale_MWh);
pl_productionChartNight.setFixedScale(chartScale_MWh);
pl_consumptionChartDay.setFixedScale(chartScale_MWh);
pl_consumptionChartNight.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_productionTextDay.setText("Opwek" + System.lineSeparator() + roundToInt(totalDayProduction_MWh*1000) + " kWh");
	t_consumptionTextDay.setText("Gebruik" + System.lineSeparator() + roundToInt(totalDayConsumption_MWh*1000) + " kWh");
	t_productionTextNight.setText("Opwek" + System.lineSeparator() + roundToInt(totalNightProduction_MWh*1000) + " kWh");
	t_consumptionTextNight.setText("Gebruik" + System.lineSeparator() + roundToInt(totalNightConsumption_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_productionTextDay.setText("Opwek" + System.lineSeparator() + roundToInt(totalDayProduction_MWh) + " MWh");
	t_consumptionTextDay.setText("Gebruik" + System.lineSeparator() + roundToInt(totalDayConsumption_MWh) + " MWh");
	t_productionTextNight.setText("Opwek" + System.lineSeparator() + roundToInt(totalNightProduction_MWh) + " MWh");
	t_consumptionTextNight.setText("Gebruik" + System.lineSeparator() + roundToInt(totalNightConsumption_MWh) + " MWh");
} else {
	t_productionTextDay.setText("Opwek" + System.lineSeparator() + roundToDecimal(totalDayProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextDay.setText("Gebruik" + System.lineSeparator() + roundToDecimal(totalDayConsumption_MWh/1000,1) + " GWh");
	t_productionTextNight.setText("Opwek" + System.lineSeparator() + roundToDecimal(totalNightProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextNight.setText("Gebruik" + System.lineSeparator() + roundToDecimal(totalNightConsumption_MWh/1000,1) + " GWh");
}

/*ALCODEEND*/}

double f_setElectricityAssetsBarChartWeekdayWeekend(I_EnergyData dataObject)
{/*ALCODESTART::1762963209355*/
double totalWeekendConsumption_MWh = 0;
double totalWeekendProduction_MWh = 0;
double totalWeekdayConsumption_MWh = 0;
double totalWeekdayProduction_MWh = 0;
double weekendConsumption_MWh = 0;
double weekendProduction_MWh = 0;
double weekdayConsumption_MWh = 0;
double weekdayProduction_MWh = 0;

DataItem di = null;

for (OL_AssetFlowCategories AFC : dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.keySet()) {
	if (uI_Results.v_electricAssetFlows.contains(AFC)) {
		if (uI_Results.v_consumptionAssetFlows.contains(AFC)) {			
			// Weekend
			weekendConsumption_MWh = dataObject.getRapidRunData().am_assetFlowsWeekend_kW.get(AFC).getIntegral_MWh();
			totalWeekendConsumption_MWh += weekendConsumption_MWh;
			di = new DataItem();
			di.setValue(weekendConsumption_MWh);
			pl_consumptionChartWeekend.addDataItem(di, uI_Results.lm_assetFlowLabels.get(AFC), uI_Results.cm_assetFlowColors.get(AFC));
			// Weekday
			weekdayConsumption_MWh = dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(AFC).getIntegral_MWh() - weekendConsumption_MWh;
			totalWeekdayConsumption_MWh += weekdayConsumption_MWh;
			di = new DataItem();
			di.setValue(weekdayConsumption_MWh);
			pl_consumptionChartWeekday.addDataItem(di, uI_Results.lm_assetFlowLabels.get(AFC), uI_Results.cm_assetFlowColors.get(AFC));
		}
		else {
			// Weekend
			weekendProduction_MWh = dataObject.getRapidRunData().am_assetFlowsWeekend_kW.get(AFC).getIntegral_MWh();
			totalWeekendProduction_MWh += weekendProduction_MWh;
			di = new DataItem();
			di.setValue(weekendProduction_MWh);
			pl_productionChartWeekend.addDataItem(di, uI_Results.lm_assetFlowLabels.get(AFC), uI_Results.cm_assetFlowColors.get(AFC));	
			// Weekday
			weekdayProduction_MWh = dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(AFC).getIntegral_MWh() - weekendProduction_MWh;
			totalWeekdayProduction_MWh += weekdayProduction_MWh;
			di = new DataItem();
			di.setValue(weekdayProduction_MWh);
			pl_productionChartWeekday.addDataItem(di, uI_Results.lm_assetFlowLabels.get(AFC), uI_Results.cm_assetFlowColors.get(AFC));	
		}
	}
}

double chartScale_MWh = max(max(totalWeekdayProduction_MWh, totalWeekendProduction_MWh), max(totalWeekdayConsumption_MWh, totalWeekendConsumption_MWh));
pl_productionChartWeekday.setFixedScale(chartScale_MWh);
pl_productionChartWeekend.setFixedScale(chartScale_MWh);
pl_consumptionChartWeekday.setFixedScale(chartScale_MWh);
pl_consumptionChartWeekend.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_productionTextWeekday.setText("Opwek" + System.lineSeparator() + roundToInt(totalWeekdayProduction_MWh*1000) + " kWh");
	t_consumptionTextWeekday.setText("Gebruik" + System.lineSeparator() + roundToInt(totalWeekdayConsumption_MWh*1000) + " kWh");
	t_productionTextWeekend.setText("Opwek" + System.lineSeparator() + roundToInt(totalWeekendProduction_MWh*1000) + " kWh");
	t_consumptionTextWeekend.setText("Gebruik" + System.lineSeparator() + roundToInt(totalWeekendConsumption_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_productionTextWeekday.setText("Opwek" + System.lineSeparator() + roundToInt(totalWeekdayProduction_MWh) + " MWh");
	t_consumptionTextWeekday.setText("Gebruik" + System.lineSeparator() + roundToInt(totalWeekdayConsumption_MWh) + " MWh");
	t_productionTextWeekend.setText("Opwek" + System.lineSeparator() + roundToInt(totalWeekendProduction_MWh) + " MWh");
	t_consumptionTextWeekend.setText("Gebruik" + System.lineSeparator() + roundToInt(totalWeekendConsumption_MWh) + " MWh");
} else {
	t_productionTextWeekday.setText("Opwek" + System.lineSeparator() + roundToDecimal(totalWeekdayProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextWeekday.setText("Gebruik" + System.lineSeparator() + roundToDecimal(totalWeekdayConsumption_MWh/1000,1) + " GWh");
	t_productionTextWeekend.setText("Opwek" + System.lineSeparator() + roundToDecimal(totalWeekendProduction_MWh/1000, 1) + " GWh");
	t_consumptionTextWeekend.setText("Gebruik" + System.lineSeparator() + roundToDecimal(totalWeekendConsumption_MWh/1000,1) + " GWh");
}
/*ALCODEEND*/}

