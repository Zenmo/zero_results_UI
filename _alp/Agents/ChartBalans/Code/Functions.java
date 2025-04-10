double f_setCharts()
{/*ALCODESTART::1714987683635*/
f_resetCharts();
f_setVisiblity();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 90, true);

if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
	f_setTrafoBalanceChartTotal(uI_Results.v_gridNode);
	f_setTrafoBalanceChartSummerWinter(uI_Results.v_gridNode);
	f_setTrafoBalanceChartDayNight(uI_Results.v_gridNode);
	f_setTrafoBalanceChartWeekdayWeekend(uI_Results.v_gridNode);
	}
else {
	I_EnergyData data = uI_Results.f_getSelectedObjectData();
	if( radio_energyType.getValue() == 0){
		switch(radio_period.getValue()){
			case 0:
				f_setElectricityBalanceChartTotal(data);
				break;
			case 1:
				f_setElectricityBalanceChartSummerWinter(data);
				break;
			case 2:
				f_setElectricityBalanceChartDayNight(data);
				break;
			case 3:
				f_setElectricityBalanceChartWeekdayWeekend(data);
				break;
		}
	}
	else if( radio_energyType.getValue() == 1){
	
			switch(radio_period.getValue()){
			case 0:
				f_setEnergyBalanceChartTotal(data);
				break;
			case 1:
				f_setEnergyBalanceChartSummerWinter(data);
				break;
			case 2:
				f_setEnergyBalanceChartDayNight(data);
				break;
			case 3:
				f_setEnergyBalanceChartWeekdayWeekend(data);
				break;
		}
	}
}
/*ALCODEEND*/}

double f_setElectricityBalanceChartTotal(I_EnergyData dataObject)
{/*ALCODESTART::1714988096086*/
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

double f_setElectricityBalanceChartSummerWinter(I_EnergyData dataObject)
{/*ALCODESTART::1714989305355*/
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

double f_setElectricityBalanceChartDayNight(I_EnergyData dataObject)
{/*ALCODESTART::1714989319026*/
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

double f_setElectricityBalanceChartWeekdayWeekend(I_EnergyData dataObject)
{/*ALCODESTART::1714989368829*/
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
{/*ALCODESTART::1714990422657*/
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

double f_setEnergyBalanceChartTotal(I_EnergyData dataObject)
{/*ALCODESTART::1714991716133*/
// SelfConsumption
double totalEnergySelfConsumed = dataObject.getRapidRunData().getTotalEnergySelfConsumed_MWh();
double totalPrimaryHeatPumpEnergyProductionSelfConsumed = dataObject.getRapidRunData().getTotalPrimaryEnergyProductionHeatpumps_MWh();

double remainingEnergySelfConsumed;
//If there is no heat export: divide total energy self consumed in to self consumed energy and self consumed energy heatpump heat
if((dataObject.getRapidRunData().activeProductionEnergyCarriers.contains(OL_EnergyCarriers.HEAT) && dataObject.getRapidRunData().getTotalExport_MWh(OL_EnergyCarriers.HEAT) > uI_Results.p_cutOff_MWh) || totalPrimaryHeatPumpEnergyProductionSelfConsumed == 0){
	remainingEnergySelfConsumed = totalEnergySelfConsumed;
}
else{
	remainingEnergySelfConsumed = max(0, totalEnergySelfConsumed - totalPrimaryHeatPumpEnergyProductionSelfConsumed);
	
	DataItem totalSelfConsumedHeatpumpHeat = new DataItem();
	totalSelfConsumedHeatpumpHeat.setValue(totalPrimaryHeatPumpEnergyProductionSelfConsumed);
	pl_productionChartBalanceTotal.addDataItem(totalSelfConsumedHeatpumpHeat, "Omgevingswarmte benut door warmtepomp [MWh]", uI_Results.v_heatPumpHeatSupplyColor);
	pl_consumptionChartBalanceTotal.addDataItem(totalSelfConsumedHeatpumpHeat, "Omgevingswarmte gewonnen door warmtepomp [MWh]", uI_Results.v_heatPumpHeatSupplyColor);
}
if(remainingEnergySelfConsumed < uI_Results.p_cutOff_MWh){
	remainingEnergySelfConsumed = 0;
}

//Add selfconsumed energy
DataItem totalSelfConsumedRemainingEnergy = new DataItem();
totalSelfConsumedRemainingEnergy.setValue(remainingEnergySelfConsumed);
pl_productionChartBalanceTotal.addDataItem(totalSelfConsumedRemainingEnergy, "Lokaal gebruikte energie [MWh]", uI_Results.v_selfConsumedEnergyColor);
pl_consumptionChartBalanceTotal.addDataItem(totalSelfConsumedRemainingEnergy, "Lokaal opgewekte energie [MWh]", uI_Results.v_selfConsumedEnergyColor);

//Export/Import
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeConsumptionEnergyCarriers) {
		// Consumption
	if (dataObject.getRapidRunData().getTotalImport_MWh(EC) > uI_Results.p_cutOff_MWh) {
		DataItem totalImport = new DataItem();
		totalImport.setValue(dataObject.getRapidRunData().getTotalImport_MWh(EC));
		pl_consumptionChartBalanceTotal.addDataItem(totalImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_consumptionColors.get(EC));
	}
}
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeProductionEnergyCarriers) {
	// Production
	if (dataObject.getRapidRunData().getTotalExport_MWh(EC) > uI_Results.p_cutOff_MWh) {
		DataItem totalExport = new DataItem();
		totalExport.setValue(dataObject.getRapidRunData().getTotalExport_MWh(EC));
		pl_productionChartBalanceTotal.addDataItem(totalExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
}

double production_MWh = dataObject.getRapidRunData().getTotalEnergyProduced_MWh();
double consumption_MWh = dataObject.getRapidRunData().getTotalEnergyConsumed_MWh();
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

double f_setEnergyBalanceChartSummerWinter(I_EnergyData dataObject)
{/*ALCODESTART::1714992323083*/
// Summer SelfConsumption
double totalEnergySelfConsumed_summer = dataObject.getRapidRunData().getSummerWeekEnergySelfConsumed_MWh();
double totalPrimaryHeatPumpEnergyProductionSelfConsumed_summer = dataObject.getRapidRunData().getSummerWeekPrimaryEnergyProductionHeatpumps_MWh();

double remainingEnergySelfConsumed_summer;
if((dataObject.getRapidRunData().activeProductionEnergyCarriers.contains(OL_EnergyCarriers.HEAT) && dataObject.getRapidRunData().getTotalExport_MWh(OL_EnergyCarriers.HEAT) > uI_Results.p_cutOff_MWh) || totalPrimaryHeatPumpEnergyProductionSelfConsumed_summer == 0){
	remainingEnergySelfConsumed_summer = totalEnergySelfConsumed_summer;
}
else{
	remainingEnergySelfConsumed_summer = max(0, totalEnergySelfConsumed_summer - totalPrimaryHeatPumpEnergyProductionSelfConsumed_summer);
	
	DataItem summerSelfConsumedHeatpumpHeat = new DataItem();
	summerSelfConsumedHeatpumpHeat.setValue(totalPrimaryHeatPumpEnergyProductionSelfConsumed_summer);
	pl_productionChartSummer.addDataItem(summerSelfConsumedHeatpumpHeat, "Omgevingswarmte benut door warmtepomp [MWh]", uI_Results.v_heatPumpHeatSupplyColor);
	pl_consumptionChartSummer.addDataItem(summerSelfConsumedHeatpumpHeat, "Omgevingswarmte gewonnen door warmtepomp [MWh]", uI_Results.v_heatPumpHeatSupplyColor);
}
if(remainingEnergySelfConsumed_summer < uI_Results.p_cutOff_MWh){
	remainingEnergySelfConsumed_summer = 0;
}

DataItem summerSelfConsumedRemainingEnergy = new DataItem();
summerSelfConsumedRemainingEnergy.setValue(remainingEnergySelfConsumed_summer);
pl_productionChartSummer.addDataItem(summerSelfConsumedRemainingEnergy, "Lokaal gebruikte energie [MWh]", uI_Results.v_selfConsumedEnergyColor);
pl_consumptionChartSummer.addDataItem(summerSelfConsumedRemainingEnergy, "Lokaal opgewekte energie [MWh]", uI_Results.v_selfConsumedEnergyColor);



// Winter SelfConsumption
double totalEnergySelfConsumed_winter = dataObject.getRapidRunData().getWinterWeekEnergySelfConsumed_MWh();
double totalPrimaryHeatPumpEnergyProductionSelfConsumed_winter = dataObject.getRapidRunData().getWinterWeekPrimaryEnergyProductionHeatpumps_MWh();

double remainingEnergySelfConsumed_winter;
if((dataObject.getRapidRunData().activeProductionEnergyCarriers.contains(OL_EnergyCarriers.HEAT) && dataObject.getRapidRunData().getTotalExport_MWh(OL_EnergyCarriers.HEAT) > uI_Results.p_cutOff_MWh) || totalPrimaryHeatPumpEnergyProductionSelfConsumed_winter == 0){
	remainingEnergySelfConsumed_winter = totalEnergySelfConsumed_winter;
}
else{
	remainingEnergySelfConsumed_winter = max(0, totalEnergySelfConsumed_winter - totalPrimaryHeatPumpEnergyProductionSelfConsumed_winter);
	
	DataItem winterSelfConsumedHeatpumpHeat = new DataItem();
	winterSelfConsumedHeatpumpHeat.setValue(totalPrimaryHeatPumpEnergyProductionSelfConsumed_winter);
	pl_productionChartWinter.addDataItem(winterSelfConsumedHeatpumpHeat, "Omgevingswarmte benut door warmtepomp [MWh]", uI_Results.v_heatPumpHeatSupplyColor);
	pl_consumptionChartWinter.addDataItem(winterSelfConsumedHeatpumpHeat, "Omgevingswarmte gewonnen door warmtepomp [MWh]", uI_Results.v_heatPumpHeatSupplyColor);
}
if(remainingEnergySelfConsumed_winter < uI_Results.p_cutOff_MWh){
	remainingEnergySelfConsumed_winter = 0;
}

DataItem winterSelfConsumedRemainingEnergy = new DataItem();
winterSelfConsumedRemainingEnergy.setValue(remainingEnergySelfConsumed_winter);

pl_productionChartWinter.addDataItem(winterSelfConsumedRemainingEnergy, "Lokaal gebruikte energie [MWh]", uI_Results.v_selfConsumedEnergyColor);
pl_consumptionChartWinter.addDataItem(winterSelfConsumedRemainingEnergy, "Lokaal opgewekte energie [MWh]", uI_Results.v_selfConsumedEnergyColor);


//Export and import
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeConsumptionEnergyCarriers) {
	// Summer Consumption
	if (dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(EC).getIntegralPos_MWh() > uI_Results.p_cutOff_MWh) {
		DataItem summerImport = new DataItem();
		summerImport.setValue(dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(EC).getIntegralPos_MWh());
		pl_consumptionChartSummer.addDataItem(summerImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Winter Consumption
	if (dataObject.getRapidRunData().am_winterWeekBalanceAccumulators_kW.get(EC).getIntegralPos_MWh() > uI_Results.p_cutOff_MWh) {
		DataItem winterImport = new DataItem();
		winterImport.setValue(dataObject.getRapidRunData().am_winterWeekBalanceAccumulators_kW.get(EC).getIntegralPos_MWh());
		pl_consumptionChartWinter.addDataItem(winterImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
}
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeProductionEnergyCarriers) {
	// Summer Production
	if (-dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(EC).getIntegralNeg_MWh() > uI_Results.p_cutOff_MWh) {
		DataItem summerExport = new DataItem();
		summerExport.setValue(-dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(EC).getIntegralNeg_MWh());
		pl_productionChartSummer.addDataItem(summerExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Winter Production
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

double f_setEnergyBalanceChartWeekdayWeekend(I_EnergyData dataObject)
{/*ALCODESTART::1714992338018*/
// Weekday SelfConsumption
double weekdayEnergySelfConsumed = dataObject.getRapidRunData().getWeekdayEnergySelfConsumed_MWh();
double weekdayPrimaryHeatPumpEnergyProductionSelfConsumed = dataObject.getRapidRunData().getWeekdayPrimaryEnergyProductionHeatpumps_MWh();

double remainingWeekdayEnergySelfConsumed;
//If there is no heat export: divide total energy self consumed in to self consumed energy and self consumed energy heatpump heat
if((dataObject.getRapidRunData().activeProductionEnergyCarriers.contains(OL_EnergyCarriers.HEAT) && dataObject.getRapidRunData().getTotalExport_MWh(OL_EnergyCarriers.HEAT) > uI_Results.p_cutOff_MWh) || weekdayPrimaryHeatPumpEnergyProductionSelfConsumed == 0){
	remainingWeekdayEnergySelfConsumed = weekdayEnergySelfConsumed;
}
else{
	remainingWeekdayEnergySelfConsumed = max(0, weekdayEnergySelfConsumed - weekdayPrimaryHeatPumpEnergyProductionSelfConsumed);
	
	DataItem weekdaySelfConsumedHeatpumpHeat = new DataItem();
	weekdaySelfConsumedHeatpumpHeat.setValue(weekdayPrimaryHeatPumpEnergyProductionSelfConsumed);
	pl_productionChartWeekday.addDataItem(weekdaySelfConsumedHeatpumpHeat, "Omgevingswarmte benut door warmtepomp [MWh]", uI_Results.v_heatPumpHeatSupplyColor);
	pl_consumptionChartWeekday.addDataItem(weekdaySelfConsumedHeatpumpHeat, "Omgevingswarmte gewonnen door warmtepomp [MWh]", uI_Results.v_heatPumpHeatSupplyColor);
}
if(remainingWeekdayEnergySelfConsumed < uI_Results.p_cutOff_MWh){
	remainingWeekdayEnergySelfConsumed = 0;
}

//Add selfconsumed energy
DataItem weekdaySelfConsumedRemainingEnergy = new DataItem();
weekdaySelfConsumedRemainingEnergy.setValue(remainingWeekdayEnergySelfConsumed);
pl_productionChartWeekday.addDataItem(weekdaySelfConsumedRemainingEnergy, "Lokaal gebruikte energie [MWh]", uI_Results.v_selfConsumedEnergyColor);
pl_consumptionChartWeekday.addDataItem(weekdaySelfConsumedRemainingEnergy, "Lokaal opgewekte energie [MWh]", uI_Results.v_selfConsumedEnergyColor);

// Weekend SelfConsumption
double weekendEnergySelfConsumed = dataObject.getRapidRunData().getWeekendEnergySelfConsumed_MWh();
double weekendPrimaryHeatPumpEnergyProductionSelfConsumed = dataObject.getRapidRunData().getWeekendPrimaryEnergyProductionHeatpumps_MWh();

double remainingWeekendEnergySelfConsumed;
//If there is no heat export: divide total energy self consumed in to self consumed energy and self consumed energy heatpump heat
if((dataObject.getRapidRunData().activeProductionEnergyCarriers.contains(OL_EnergyCarriers.HEAT) && dataObject.getRapidRunData().getTotalExport_MWh(OL_EnergyCarriers.HEAT) > uI_Results.p_cutOff_MWh) || weekendPrimaryHeatPumpEnergyProductionSelfConsumed == 0){
	remainingWeekendEnergySelfConsumed = weekendEnergySelfConsumed;
}
else{
	remainingWeekendEnergySelfConsumed = max(0, weekendEnergySelfConsumed - weekendPrimaryHeatPumpEnergyProductionSelfConsumed);
	
	DataItem weekendSelfConsumedHeatpumpHeat = new DataItem();
	weekendSelfConsumedHeatpumpHeat.setValue(weekendPrimaryHeatPumpEnergyProductionSelfConsumed);
	pl_productionChartWeekend.addDataItem(weekendSelfConsumedHeatpumpHeat, "Omgevingswarmte benut door warmtepomp [MWh]", uI_Results.v_heatPumpHeatSupplyColor);
	pl_consumptionChartWeekend.addDataItem(weekendSelfConsumedHeatpumpHeat, "Omgevingswarmte gewonnen door warmtepomp [MWh]", uI_Results.v_heatPumpHeatSupplyColor);
}
if(remainingWeekendEnergySelfConsumed < uI_Results.p_cutOff_MWh){
	remainingWeekendEnergySelfConsumed = 0;
}

//Add selfconsumed energy
DataItem weekendSelfConsumedRemainingEnergy = new DataItem();
weekendSelfConsumedRemainingEnergy.setValue(remainingWeekendEnergySelfConsumed);
pl_productionChartWeekend.addDataItem(weekendSelfConsumedRemainingEnergy, "Lokaal gebruikte energie [MWh]", uI_Results.v_selfConsumedEnergyColor);
pl_consumptionChartWeekend.addDataItem(weekendSelfConsumedRemainingEnergy, "Lokaal opgewekte energie [MWh]", uI_Results.v_selfConsumedEnergyColor);


for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeConsumptionEnergyCarriers) {
	// Weekday Consumption
	if (dataObject.getRapidRunData().getWeekdayImport_MWh(EC) > uI_Results.p_cutOff_MWh) {	
		DataItem weekdayImport = new DataItem();
		weekdayImport.setValue(dataObject.getRapidRunData().getWeekdayImport_MWh(EC));
		pl_consumptionChartWeekday.addDataItem(weekdayImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Weekend Consumption
	if (dataObject.getRapidRunData().getWeekendImport_MWh(EC) > uI_Results.p_cutOff_MWh) {	
		DataItem weekendImport = new DataItem();
		weekendImport.setValue(dataObject.getRapidRunData().getWeekendImport_MWh(EC));
		pl_consumptionChartWeekend.addDataItem(weekendImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
}
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeProductionEnergyCarriers) {
	// Weekday Production
	if (dataObject.getRapidRunData().getWeekdayExport_MWh(EC) > uI_Results.p_cutOff_MWh) {	
		DataItem weekdayExport = new DataItem();
		weekdayExport.setValue(dataObject.getRapidRunData().getWeekdayExport_MWh(EC));
		pl_productionChartWeekday.addDataItem(weekdayExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Weekend Production
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

double f_setEnergyBalanceChartDayNight(I_EnergyData dataObject)
{/*ALCODESTART::1714992339107*/
// Daytime SelfConsumption
double daytimeEnergySelfConsumed = dataObject.getRapidRunData().getDaytimeEnergySelfConsumed_MWh();
double daytimePrimaryHeatPumpEnergyProductionSelfConsumed = dataObject.getRapidRunData().getDaytimePrimaryEnergyProductionHeatpumps_MWh();

double remainingDaytimeEnergySelfConsumed;
//If there is no heat export: divide total energy self consumed in to self consumed energy and self consumed energy heatpump heat
if((dataObject.getRapidRunData().activeProductionEnergyCarriers.contains(OL_EnergyCarriers.HEAT) && dataObject.getRapidRunData().getTotalExport_MWh(OL_EnergyCarriers.HEAT) > uI_Results.p_cutOff_MWh) || daytimePrimaryHeatPumpEnergyProductionSelfConsumed == 0){
	remainingDaytimeEnergySelfConsumed = daytimeEnergySelfConsumed;
}
else{
	remainingDaytimeEnergySelfConsumed = max(0, daytimeEnergySelfConsumed - daytimePrimaryHeatPumpEnergyProductionSelfConsumed);
	
	DataItem daytimeSelfConsumedHeatpumpHeat = new DataItem();
	daytimeSelfConsumedHeatpumpHeat.setValue(daytimePrimaryHeatPumpEnergyProductionSelfConsumed);
	pl_productionChartDay.addDataItem(daytimeSelfConsumedHeatpumpHeat, "Omgevingswarmte benut door warmtepomp [MWh]", uI_Results.v_heatPumpHeatSupplyColor);
	pl_consumptionChartDay.addDataItem(daytimeSelfConsumedHeatpumpHeat, "Omgevingswarmte gewonnen door warmtepomp [MWh]", uI_Results.v_heatPumpHeatSupplyColor);
}
if(remainingDaytimeEnergySelfConsumed < uI_Results.p_cutOff_MWh){
	remainingDaytimeEnergySelfConsumed = 0;
}

//Add Daytime selfconsumed energy
DataItem daytimeSelfConsumedRemainingEnergy = new DataItem();
daytimeSelfConsumedRemainingEnergy.setValue(remainingDaytimeEnergySelfConsumed);
pl_productionChartDay.addDataItem(daytimeSelfConsumedRemainingEnergy, "Lokaal gebruikte energie [MWh]", uI_Results.v_selfConsumedEnergyColor);
pl_consumptionChartDay.addDataItem(daytimeSelfConsumedRemainingEnergy, "Lokaal opgewekte energie [MWh]", uI_Results.v_selfConsumedEnergyColor);


// Nighttime SelfConsumption
double nighttimeEnergySelfConsumed = dataObject.getRapidRunData().getNighttimeEnergySelfConsumed_MWh();
double nighttimePrimaryHeatPumpEnergyProductionSelfConsumed = dataObject.getRapidRunData().getNighttimePrimaryEnergyProductionHeatpumps_MWh();

double remainingNighttimeEnergySelfConsumed;
//If there is no heat export: divide total energy self consumed in to self consumed energy and self consumed energy heatpump heat
if((dataObject.getRapidRunData().activeProductionEnergyCarriers.contains(OL_EnergyCarriers.HEAT) && dataObject.getRapidRunData().getTotalExport_MWh(OL_EnergyCarriers.HEAT) > uI_Results.p_cutOff_MWh) || nighttimePrimaryHeatPumpEnergyProductionSelfConsumed == 0){
	remainingNighttimeEnergySelfConsumed = nighttimeEnergySelfConsumed;
}
else{
	remainingNighttimeEnergySelfConsumed = max(0, nighttimeEnergySelfConsumed - nighttimePrimaryHeatPumpEnergyProductionSelfConsumed);
	
	DataItem nighttimeSelfConsumedHeatpumpHeat = new DataItem();
	nighttimeSelfConsumedHeatpumpHeat.setValue(nighttimePrimaryHeatPumpEnergyProductionSelfConsumed);
	pl_productionChartNight.addDataItem(nighttimeSelfConsumedHeatpumpHeat, "Omgevingswarmte benut door warmtepomp [MWh]", uI_Results.v_heatPumpHeatSupplyColor);
	pl_consumptionChartNight.addDataItem(nighttimeSelfConsumedHeatpumpHeat, "Omgevingswarmte gewonnen door warmtepomp [MWh]", uI_Results.v_heatPumpHeatSupplyColor);
}
if(remainingNighttimeEnergySelfConsumed < uI_Results.p_cutOff_MWh){
	remainingNighttimeEnergySelfConsumed = 0;
}

//Add selfconsumed energy
DataItem nighttimeSelfConsumedRemainingEnergy = new DataItem();
nighttimeSelfConsumedRemainingEnergy.setValue(remainingNighttimeEnergySelfConsumed);
pl_productionChartNight.addDataItem(nighttimeSelfConsumedRemainingEnergy, "Lokaal gebruikte energie [MWh]", uI_Results.v_selfConsumedEnergyColor);
pl_consumptionChartNight.addDataItem(nighttimeSelfConsumedRemainingEnergy, "Lokaal opgewekte energie [MWh]", uI_Results.v_selfConsumedEnergyColor);



for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeConsumptionEnergyCarriers) {
	// Daytime Consumption
	if (dataObject.getRapidRunData().getDaytimeImport_MWh(EC) > uI_Results.p_cutOff_MWh) {
		DataItem daytimeImport = new DataItem();
		daytimeImport.setValue(dataObject.getRapidRunData().getDaytimeImport_MWh(EC));
		pl_consumptionChartDay.addDataItem(daytimeImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Nighttime Consumption
	if (dataObject.getRapidRunData().getNighttimeImport_MWh(EC) > uI_Results.p_cutOff_MWh) {
		DataItem nighttimeImport = new DataItem();
		nighttimeImport.setValue(dataObject.getRapidRunData().getNighttimeImport_MWh(EC));
		pl_consumptionChartNight.addDataItem(nighttimeImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}	
}
for (OL_EnergyCarriers EC : dataObject.getRapidRunData().activeProductionEnergyCarriers) {
	// Daytime Production
	if (dataObject.getRapidRunData().getDaytimeExport_MWh(EC) > uI_Results.p_cutOff_MWh) {	
		DataItem daytimeExport = new DataItem();
		daytimeExport.setValue(dataObject.getRapidRunData().getDaytimeExport_MWh(EC));
		pl_productionChartDay.addDataItem(daytimeExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}	
	// Nighttime Production
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

double f_setVisiblity()
{/*ALCODESTART::1715003482844*/
gr_Total.setVisible(false);
gr_SummerWinter.setVisible(false);
gr_DayNight.setVisible(false);
gr_WeekdayWeekend.setVisible(false);

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

double f_setTrafoBalanceChartTotal(GridNode GN)
{/*ALCODESTART::1715003709481*/
DataItem annualSelfConsumed = new DataItem();
annualSelfConsumed.setValue(GN.v_totalImport_MWh - GN.v_annualExcessImport_MWh);
pl_consumptionChartBalanceTotal.addDataItem(annualSelfConsumed, "Geleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem annualSelfProduced = new DataItem();
annualSelfProduced.setValue(GN.v_totalExport_MWh - GN.v_annualExcessExport_MWh);
pl_productionChartBalanceTotal.addDataItem(annualSelfProduced, "Teruggeleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem annualImport = new DataItem();
annualImport.setValue(GN.v_annualExcessExport_MWh);
pl_productionChartBalanceTotal.addDataItem(annualImport, "Over Capaciteit [MWh]", uI_Results.v_importedEnergyColor);

DataItem annualExport = new DataItem();
annualExport.setValue(GN.v_annualExcessImport_MWh);
pl_consumptionChartBalanceTotal.addDataItem(annualExport, "Over Capaciteit [MWh]", uI_Results.v_exportedEnergyColor);

double chartScale_MWh = max(GN.v_totalImport_MWh, GN.v_totalExport_MWh);
pl_consumptionChartBalanceTotal.setFixedScale(chartScale_MWh);
pl_productionChartBalanceTotal.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToInt(GN.v_totalExport_MWh*1000) + " kWh");
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToInt(GN.v_totalImport_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToInt(GN.v_totalExport_MWh) + " MWh");
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToInt(GN.v_totalImport_MWh) + " MWh");
} else {
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToDecimal(GN.v_totalExport_MWh/1000, 1) + " GWh");
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToDecimal(GN.v_totalImport_MWh/1000, 1) + " GWh");
}
/*ALCODEEND*/}

double f_setTrafoBalanceChartSummerWinter(GridNode GN)
{/*ALCODESTART::1715003741494*/
double summerWeekImport_MWh = GN.v_summerWeekImport_MWh;
double summerWeekExport_MWh = GN.v_summerWeekExport_MWh;;
double winterWeekImport_MWh = GN.v_winterWeekImport_MWh;
double winterWeekExport_MWh = GN.v_winterWeekExport_MWh;;

//// Summer
// Consumption
DataItem summerWeekSelfConsumed = new DataItem();
summerWeekSelfConsumed.setValue(summerWeekImport_MWh - GN.v_summerWeekExcessImport_MWh);
pl_consumptionChartSummer.addDataItem(summerWeekSelfConsumed, "Geleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem summerWeekExport = new DataItem();
summerWeekExport.setValue(GN.v_summerWeekExcessImport_MWh);
pl_consumptionChartSummer.addDataItem(summerWeekExport, "Over Capaciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Production
DataItem summerWeekSelfProduced = new DataItem();
summerWeekSelfProduced.setValue(summerWeekExport_MWh - GN.v_summerWeekExcessExport_MWh);
pl_productionChartSummer.addDataItem(summerWeekSelfProduced, "Teruggeleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem summerWeekImport = new DataItem();
summerWeekImport.setValue(GN.v_summerWeekExcessExport_MWh);
pl_productionChartSummer.addDataItem(summerWeekImport, "Over Capaciteit [MWh]", uI_Results.v_importedEnergyColor);


//// Winter
// Consumption
DataItem winterWeekSelfConsumed = new DataItem();
winterWeekSelfConsumed.setValue(winterWeekImport_MWh - GN.v_winterWeekExcessImport_MWh);
pl_consumptionChartWinter.addDataItem(winterWeekSelfConsumed, "Geleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem winterWeekExport = new DataItem();
winterWeekExport.setValue(GN.v_winterWeekExcessImport_MWh);
pl_consumptionChartWinter.addDataItem(winterWeekExport, "Over Capaciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Production
DataItem winterWeekSelfProduced = new DataItem();
winterWeekSelfProduced.setValue(winterWeekExport_MWh - GN.v_winterWeekExcessExport_MWh);
pl_productionChartWinter.addDataItem(winterWeekSelfProduced, "Teruggeleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem winterWeekImport = new DataItem();
winterWeekImport.setValue(GN.v_winterWeekExcessExport_MWh);
pl_productionChartWinter.addDataItem(winterWeekImport, "Over Capaciteit [MWh]", uI_Results.v_importedEnergyColor);


//// Scale & Text
double chartScale_MWh = max( max(summerWeekImport_MWh, summerWeekExport_MWh), max(winterWeekImport_MWh, winterWeekExport_MWh) );
pl_consumptionChartSummer.setFixedScale(chartScale_MWh);
pl_productionChartSummer.setFixedScale(chartScale_MWh);
pl_consumptionChartWinter.setFixedScale(chartScale_MWh);
pl_productionChartWinter.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_productionTextSummer.setText("Opwek" + System.lineSeparator() + roundToInt(summerWeekExport_MWh*1000) + " kWh");
	t_consumptionTextSummer.setText("Gebruik" + System.lineSeparator() + roundToInt(summerWeekImport_MWh*1000) + " kWh");
	t_productionTextWinter.setText("Opwek" + System.lineSeparator() + roundToInt(winterWeekExport_MWh*1000) + " kWh");
	t_consumptionTextWinter.setText("Gebruik" + System.lineSeparator() + roundToInt(winterWeekImport_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_productionTextSummer.setText("Opwek" + System.lineSeparator() + roundToInt(summerWeekExport_MWh) + " MWh");
	t_consumptionTextSummer.setText("Gebruik" + System.lineSeparator() + roundToInt(summerWeekImport_MWh) + " MWh");
	t_productionTextWinter.setText("Opwek" + System.lineSeparator() + roundToInt(winterWeekExport_MWh) + " MWh");
	t_consumptionTextWinter.setText("Gebruik" + System.lineSeparator() + roundToInt(winterWeekImport_MWh) + " MWh");
} else {
	t_productionTextSummer.setText("Opwek" + System.lineSeparator() + roundToDecimal(summerWeekExport_MWh/1000, 1) + " GWh");
	t_consumptionTextSummer.setText("Gebruik" + System.lineSeparator() + roundToDecimal(summerWeekImport_MWh/1000,1) + " GWh");
	t_productionTextWinter.setText("Opwek" + System.lineSeparator() + roundToDecimal(winterWeekExport_MWh/1000, 1) + " GWh");
	t_consumptionTextWinter.setText("Gebruik" + System.lineSeparator() + roundToDecimal(winterWeekImport_MWh/1000,1) + " GWh");
}
/*ALCODEEND*/}

double f_setTrafoBalanceChartDayNight(GridNode GN)
{/*ALCODESTART::1715003742134*/
double daytimeImport_MWh = GN.v_daytimeImport_MWh;
double daytimeExport_MWh = GN.v_daytimeExport_MWh;
double nighttimeImport_MWh = GN.v_nighttimeImport_MWh;
double nighttimeExport_MWh = GN.v_nighttimeExport_MWh;

//// Day
// Consumption
DataItem daytimeSelfConsumed = new DataItem();
daytimeSelfConsumed.setValue(daytimeImport_MWh - GN.v_daytimeExcessImport_MWh);
pl_consumptionChartDay.addDataItem(daytimeSelfConsumed, "Geleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem daytimeExport = new DataItem();
daytimeExport.setValue(GN.v_daytimeExcessImport_MWh);
pl_consumptionChartDay.addDataItem(daytimeExport, "Over Capaciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Production
DataItem daytimeSelfProduced = new DataItem();
daytimeSelfProduced.setValue(daytimeExport_MWh - GN.v_daytimeExcessExport_MWh);
pl_productionChartDay.addDataItem(daytimeSelfProduced, "Teruggeleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem daytimeImport = new DataItem();
daytimeImport.setValue(GN.v_daytimeExcessExport_MWh);
pl_productionChartDay.addDataItem(daytimeImport, "Over Capaciteit [MWh]", uI_Results.v_importedEnergyColor);


//// Night
// Consumption
DataItem nighttimeSelfConsumed = new DataItem();
nighttimeSelfConsumed.setValue(nighttimeImport_MWh - GN.v_nighttimeExcessImport_MWh);
pl_consumptionChartNight.addDataItem(nighttimeSelfConsumed, "Geleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem nighttimeExport = new DataItem();
nighttimeExport.setValue(GN.v_nighttimeExcessImport_MWh);
pl_consumptionChartNight.addDataItem(nighttimeExport, "Over Capaciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Production
DataItem nighttimeSelfProduced = new DataItem();
nighttimeSelfProduced.setValue(nighttimeExport_MWh - GN.v_nighttimeExcessExport_MWh);
pl_productionChartNight.addDataItem(nighttimeSelfProduced, "Teruggeleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem nighttimeImport = new DataItem();
nighttimeImport.setValue(GN.v_nighttimeExcessExport_MWh);
pl_productionChartNight.addDataItem(nighttimeImport, "Over Capaciteit [MWh]", uI_Results.v_importedEnergyColor);


//// Scale & Text
double chartScale_MWh = max( max(daytimeImport_MWh, daytimeExport_MWh), max(nighttimeImport_MWh, nighttimeExport_MWh) );
pl_consumptionChartDay.setFixedScale(chartScale_MWh);
pl_productionChartDay.setFixedScale(chartScale_MWh);
pl_consumptionChartNight.setFixedScale(chartScale_MWh);
pl_productionChartNight.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_productionTextDay.setText("Opwek" + System.lineSeparator() + roundToInt(daytimeExport_MWh*1000) + " kWh");
	t_consumptionTextDay.setText("Gebruik" + System.lineSeparator() + roundToInt(daytimeImport_MWh*1000) + " kWh");
	t_productionTextNight.setText("Opwek" + System.lineSeparator() + roundToInt(nighttimeExport_MWh*1000) + " kWh");
	t_consumptionTextNight.setText("Gebruik" + System.lineSeparator() + roundToInt(nighttimeImport_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_productionTextDay.setText("Opwek" + System.lineSeparator() + roundToInt(daytimeExport_MWh) + " MWh");
	t_consumptionTextDay.setText("Gebruik" + System.lineSeparator() + roundToInt(daytimeImport_MWh) + " MWh");
	t_productionTextNight.setText("Opwek" + System.lineSeparator() + roundToInt(nighttimeExport_MWh) + " MWh");
	t_consumptionTextNight.setText("Gebruik" + System.lineSeparator() + roundToInt(nighttimeImport_MWh) + " MWh");
} else {
	t_productionTextDay.setText("Opwek" + System.lineSeparator() + roundToDecimal(daytimeExport_MWh/1000, 1) + " GWh");
	t_consumptionTextDay.setText("Gebruik" + System.lineSeparator() + roundToDecimal(daytimeImport_MWh/1000,1) + " GWh");
	t_productionTextNight.setText("Opwek" + System.lineSeparator() + roundToDecimal(nighttimeExport_MWh/1000, 1) + " GWh");
	t_consumptionTextNight.setText("Gebruik" + System.lineSeparator() + roundToDecimal(nighttimeImport_MWh/1000,1) + " GWh");
}
/*ALCODEEND*/}

double f_setTrafoBalanceChartWeekdayWeekend(GridNode GN)
{/*ALCODESTART::1715003742758*/
double weekdayImport_MWh = GN.v_weekdayImport_MWh;
double weekdayExport_MWh = GN.v_weekdayExport_MWh;
double weekendImport_MWh = GN.v_weekendImport_MWh;
double weekendExport_MWh = GN.v_weekendExport_MWh;

//// Weekday
// Consumption
DataItem weekdaySelfConsumed = new DataItem();
weekdaySelfConsumed.setValue(weekdayImport_MWh - GN.v_weekdayExcessImport_MWh);
pl_consumptionChartWeekday.addDataItem(weekdaySelfConsumed, "Geleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem weekdayExport = new DataItem();
weekdayExport.setValue(GN.v_weekdayExcessImport_MWh);
pl_consumptionChartWeekday.addDataItem(weekdayExport, "Over Capaciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Production
DataItem weekdaySelfProduced = new DataItem();
weekdaySelfProduced.setValue(weekdayExport_MWh - GN.v_weekdayExcessExport_MWh);
pl_productionChartWeekday.addDataItem(weekdaySelfProduced, "Teruggeleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem weekdayImport = new DataItem();
weekdayImport.setValue(GN.v_weekdayExcessExport_MWh);
pl_productionChartWeekday.addDataItem(weekdayImport, "Over Capaciteit [MWh]", uI_Results.v_importedEnergyColor);


//// Weekend
// Consumption
DataItem weekendSelfConsumed = new DataItem();
weekendSelfConsumed.setValue(weekendImport_MWh - GN.v_weekendExcessImport_MWh);
pl_consumptionChartWeekend.addDataItem(weekendSelfConsumed, "Geleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem weekendExport = new DataItem();
weekendExport.setValue(GN.v_weekendExcessImport_MWh);
pl_consumptionChartWeekend.addDataItem(weekendExport, "Over Capaciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Production
DataItem weekendSelfProduced = new DataItem();
weekendSelfProduced.setValue(weekendExport_MWh - GN.v_weekendExcessExport_MWh);
pl_productionChartWeekend.addDataItem(weekendSelfProduced, "Teruggeleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem weekendImport = new DataItem();
weekendImport.setValue(GN.v_weekendExcessExport_MWh);
pl_productionChartWeekend.addDataItem(weekendImport, "Over Capaciteit [MWh]", uI_Results.v_importedEnergyColor);


//// Scale & Text
double chartScale_MWh = max( max(weekdayImport_MWh, weekdayExport_MWh), max(weekendImport_MWh, weekendExport_MWh) );
pl_consumptionChartWeekday.setFixedScale(chartScale_MWh);
pl_productionChartWeekday.setFixedScale(chartScale_MWh);
pl_consumptionChartWeekend.setFixedScale(chartScale_MWh);
pl_productionChartWeekend.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_productionTextWeekday.setText("Opwek" + System.lineSeparator() + roundToInt(weekdayExport_MWh*1000) + " kWh");
	t_consumptionTextWeekday.setText("Gebruik" + System.lineSeparator() + roundToInt(weekdayImport_MWh*1000) + " kWh");
	t_productionTextWeekend.setText("Opwek" + System.lineSeparator() + roundToInt(weekendExport_MWh*1000) + " kWh");
	t_consumptionTextWeekend.setText("Gebruik" + System.lineSeparator() + roundToInt(weekendImport_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_productionTextWeekday.setText("Opwek" + System.lineSeparator() + roundToInt(weekdayExport_MWh) + " MWh");
	t_consumptionTextWeekday.setText("Gebruik" + System.lineSeparator() + roundToInt(weekdayImport_MWh) + " MWh");
	t_productionTextWeekend.setText("Opwek" + System.lineSeparator() + roundToInt(weekendExport_MWh) + " MWh");
	t_consumptionTextWeekend.setText("Gebruik" + System.lineSeparator() + roundToInt(weekendImport_MWh) + " MWh");
} else {
	t_productionTextWeekday.setText("Opwek" + System.lineSeparator() + roundToDecimal(weekdayExport_MWh/1000, 1) + " GWh");
	t_consumptionTextWeekday.setText("Gebruik" + System.lineSeparator() + roundToDecimal(weekdayImport_MWh/1000,1) + " GWh");
	t_productionTextWeekend.setText("Opwek" + System.lineSeparator() + roundToDecimal(weekendExport_MWh/1000, 1) + " GWh");
	t_consumptionTextWeekend.setText("Gebruik" + System.lineSeparator() + roundToDecimal(weekendImport_MWh/1000,1) + " GWh");
}
/*ALCODEEND*/}

