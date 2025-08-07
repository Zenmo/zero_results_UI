double f_setGespreksleidraadBedrijvenCharts()
{/*ALCODESTART::1730395813825*/
gr_GSLDSummary1.setVisible(false);
gr_GSLDSummary2.setVisible(false);
gr_GSLDSummary3.setVisible(false);
f_setWarningScreen(false);


		
I_EnergyData data = uI_Results.f_getSelectedObjectData();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 85, true);

if(uI_Results.v_selectedObjectScope != OL_ResultScope.ENERGYCOOP && uI_Results.v_selectedObjectScope != OL_ResultScope.ENERGYMODEL){
	f_setWarningScreen(true);
	return;
}

if (rb_gespreksleidraadBedrijvenChartType.getValue()==0) {
	gr_GSLDSummary1.setVisible(true);
	f_setChartsGespreksleidraadBedrijven1(data);
} else if (rb_gespreksleidraadBedrijvenChartType.getValue()==1) {
	gr_GSLDSummary2.setVisible(true);
	f_setChartsGespreksleidraadBedrijven2(data);
} else if (rb_gespreksleidraadBedrijvenChartType.getValue()==2) {
	gr_GSLDSummary3.setVisible(true);
	if(uI_Results.b_showGroupContractValues && uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYCOOP){
		f_setGroupContractChart((EnergyCoop)data.getRapidRunData().parentAgent);
	}
	else{
		f_setChartsGespreksleidraadBedrijven3(data);
	}
}

/*ALCODEEND*/}

double f_setChartsGespreksleidraadBedrijven1(I_EnergyData data)
{/*ALCODESTART::1730395813827*/
f_setEnergyBalanceChartTotal(data);

f_setDemandAndSupplyGespreksleidraadBedrijven1(data);



/*ALCODEEND*/}

double f_setChartsGespreksleidraadBedrijven2(I_EnergyData data)
{/*ALCODESTART::1730395813831*/
double annualSelfConsumedElectricityIndividual_MWh = 0;
if (data.getRapidRunData().parentAgent instanceof EnergyCoop){
	annualSelfConsumedElectricityIndividual_MWh = ((EnergyCoop)data.getRapidRunData().parentAgent).v_cumulativeIndividualSelfconsumptionElectricity_MWh;
}
else if(data.getRapidRunData().parentAgent instanceof EnergyModel){
	annualSelfConsumedElectricityIndividual_MWh = sum(((EnergyModel)data.getRapidRunData().parentAgent).f_getActiveGridConnections(), GC -> GC.v_rapidRunData.getTotalElectricitySelfConsumed_MWh());
}

annualSelfConsumedElectricityIndividual_MWh = max(0, annualSelfConsumedElectricityIndividual_MWh);

f_setSelfConsumptionChart(annualSelfConsumedElectricityIndividual_MWh, data.getRapidRunData().getTotalElectricitySelfConsumed_MWh(), data.getRapidRunData().getTotalExport_MWh(OL_EnergyCarriers.ELECTRICITY),
					pl_productionChartIndividual, pl_productionChartCollective);



/*ALCODEEND*/}

double f_setSelfConsumptionChart(double selfConsumedEnergyIndividual_MWh,double selfConsumedEnergyCollective_MWh,double export_MWh,StackChart pl_individualChart,StackChart pl_collectiveChart)
{/*ALCODESTART::1730395813833*/
//gr_onbalansGraphs.setVisible(false);

DataItem d1 = new DataItem();
d1.setValue(selfConsumedEnergyIndividual_MWh);
DataItem d2 = new DataItem();
d2.setValue(selfConsumedEnergyCollective_MWh);
DataItem d3 = new DataItem();
d3.setValue(export_MWh);
DataItem d4 = new DataItem();
d4.setValue((selfConsumedEnergyCollective_MWh+export_MWh)-selfConsumedEnergyIndividual_MWh);
pl_individualChart.removeAll();
pl_collectiveChart.removeAll();
pl_individualChart.addDataItem(d1,"Zelf gebruikt",v_selfConsumedElectricityColor);
pl_individualChart.addDataItem(d4,"Teruggeleverd",v_exportedEnergyColor);
//pl_consumptionChart.addDataItem(d4,"Overbelasting [MWh]",red);
pl_collectiveChart.addDataItem(d2,"Lokaal gebruikt",v_selfConsumedElectricityColor);
pl_collectiveChart.addDataItem(d3,"Geëxporteerd",v_exportedEnergyColor);
//pl_productionChart.addDataItem(d5,"Overbelasting [MWh]",red);
double chartScale_MWh = selfConsumedEnergyCollective_MWh+export_MWh;
pl_individualChart.setFixedScale(chartScale_MWh);
pl_collectiveChart.setFixedScale(chartScale_MWh);


/*ALCODEEND*/}

double f_setDemandAndSupplyGespreksleidraadBedrijven1(I_EnergyData data)
{/*ALCODESTART::1737988539936*/
//Electricity demand chart
energyDemandChartYearGespreksleidraad1.removeAll();

double startTime_h = 0;
energyDemandChartYearGespreksleidraad1.addDataSet(data.getRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(startTime_h), v_electricityDemandText, v_energyDemandColor, true, false, Chart.INTERPOLATION_LINEAR, 2, Chart.POINT_NONE);

//Electricity supply chart
energySupplyChartYearGespreksleidraad1.removeAll();
if(data.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.windProductionElectric_kW)){
	energySupplyChartYearGespreksleidraad1.addDataSet(data.getRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.windProductionElectric_kW).getDataSet(startTime_h), v_windElectricitySupplyText, v_windElectricitySupplyColor);
}
if(data.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.pvProductionElectric_kW)){
	energySupplyChartYearGespreksleidraad1.addDataSet(data.getRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.pvProductionElectric_kW).getDataSet(startTime_h), v_PVElectricitySupplyText, v_PVElectricitySupplyColor);
}
if(data.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.batteriesDischargingPower_kW)){
	energySupplyChartYearGespreksleidraad1.addDataSet(data.getRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.batteriesDischargingPower_kW).getDataSet(startTime_h), v_storageElectricitySupplyText, v_storageElectricitySupplyColor);
}
if(data.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.V2GPower_kW)){
	energySupplyChartYearGespreksleidraad1.addDataSet(data.getRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.V2GPower_kW).getDataSet(startTime_h), v_V2GElectricitySupplyText, v_V2GElectricitySupplyColor);
}
if(data.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.CHPProductionElectric_kW)){
	energySupplyChartYearGespreksleidraad1.addDataSet(data.getRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.CHPProductionElectric_kW).getDataSet(startTime_h), "WKK plec prod.", gray);
}
double maxScale = max(data.getRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMaxPower_kW(),data.getRapidRunData().am_dailyAverageProductionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMaxPower_kW());
//double maxScale = max(energySupplyChartYearGespreksleidraad1.getScaleY(), energyDemandChartYearGespreksleidraad1.getScaleY());
energyDemandChartYearGespreksleidraad1.setFixedVerticalScale(0, maxScale);
energySupplyChartYearGespreksleidraad1.setFixedVerticalScale(maxScale);

//Reset view
energyDemandChartYearGespreksleidraad1.setVisible(false);
energySupplyChartYearGespreksleidraad1.setVisible(false);
energyDemandChartYearGespreksleidraad1.setVisible(true);
energySupplyChartYearGespreksleidraad1.setVisible(true);
/*ALCODEEND*/}

double f_setChartsGespreksleidraadBedrijven3(I_EnergyData data)
{/*ALCODESTART::1739206434585*/
chart_barChartGSLDSummary3.removeAll();

t_titleGespreksleidraadBedrijven3.setText("Wat is de maximale piek van\n het collectief vs individueel");
DataItem totalGTV_kW = new DataItem();
DataItem peakIndividual_kW = new DataItem();
DataItem peakCollective_kW = new DataItem();
String text_peakType = "";

if (data.getRapidRunData().parentAgent instanceof EnergyCoop){
	EnergyCoop COOP = (EnergyCoop)data.getRapidRunData().parentAgent;
	if(rb_GSLDSummary3_delivery_or_feedin.getValue() == 0){//Delivery
		totalGTV_kW.setValue(data.getRapidRunData().connectionMetaData.contractedDeliveryCapacity_kW);
		peakIndividual_kW.setValue(COOP.v_cumulativeIndividualPeakDelivery_kW);
		peakCollective_kW.setValue(data.getRapidRunData().getPeakDelivery_kW());
		text_peakType = "levering";
	}
	else if(rb_GSLDSummary3_delivery_or_feedin.getValue() == 1){//Feedin
		totalGTV_kW.setValue(data.getRapidRunData().connectionMetaData.contractedFeedinCapacity_kW);
		peakIndividual_kW.setValue(COOP.v_cumulativeIndividualPeakFeedin_kW);
		peakCollective_kW.setValue(data.getRapidRunData().getPeakFeedin_kW());
		text_peakType = "teruglevering";
	}
}
else if(data.getRapidRunData().parentAgent instanceof EnergyModel){
	if(rb_GSLDSummary3_delivery_or_feedin.getValue() == 0){//Delivery
		totalGTV_kW.setValue(data.getRapidRunData().connectionMetaData.contractedDeliveryCapacity_kW);
		peakIndividual_kW.setValue(sum(((EnergyModel)data.getRapidRunData().parentAgent).f_getActiveGridConnections(), GC -> GC.v_rapidRunData.getPeakDelivery_kW()));
		peakCollective_kW.setValue(data.getRapidRunData().getPeakDelivery_kW());
		text_peakType = "levering";
	}
	else if(rb_GSLDSummary3_delivery_or_feedin.getValue() == 1){//Feedin
		totalGTV_kW.setValue(data.getRapidRunData().connectionMetaData.contractedFeedinCapacity_kW);
		peakIndividual_kW.setValue(sum(((EnergyModel)data.getRapidRunData().parentAgent).f_getActiveGridConnections(), GC -> GC.v_rapidRunData.getPeakFeedin_kW()));
		peakCollective_kW.setValue(data.getRapidRunData().getPeakFeedin_kW());
		text_peakType = "teruglevering";
	}
}



chart_barChartGSLDSummary3.addDataItem(totalGTV_kW,"Totaal GTV " + text_peakType,darkMagenta);
chart_barChartGSLDSummary3.addDataItem(peakIndividual_kW,"Piek " + text_peakType + " individueel",orange);
chart_barChartGSLDSummary3.addDataItem(peakCollective_kW,"Piek " + text_peakType + " collectief",green);

/*ALCODEEND*/}

double f_setGroupContractChart(EnergyCoop COOP)
{/*ALCODESTART::1740754239000*/
chart_barChartGSLDSummary3.removeAll();

t_titleGespreksleidraadBedrijven3.setText("Groepscontract capaciteit");

DataItem totalGTV_kW = new DataItem();
DataItem totalGTVgroupcontract_kW = new DataItem();
DataItem peakIndividual_kW = new DataItem();
DataItem peakCollective_kW = new DataItem();
String text_peakType = "";
if(rb_GSLDSummary3_delivery_or_feedin.getValue() == 0){//Delivery
	totalGTV_kW.setValue(COOP.v_liveConnectionMetaData.contractedDeliveryCapacity_kW);
	totalGTVgroupcontract_kW.setValue(COOP.f_getGroupContractDeliveryCapacity_kW());
	peakIndividual_kW.setValue(COOP.v_cumulativeIndividualPeakDelivery_kW);
	peakCollective_kW.setValue(COOP.v_rapidRunData.getPeakDelivery_kW());
	text_peakType = "levering";
}
else if(rb_GSLDSummary3_delivery_or_feedin.getValue() == 1){//Feedin
	totalGTV_kW.setValue(COOP.v_liveConnectionMetaData.contractedFeedinCapacity_kW);
	totalGTVgroupcontract_kW.setValue(COOP.f_getGroupContractFeedinCapacity_kW());
	peakIndividual_kW.setValue(COOP.v_cumulativeIndividualPeakFeedin_kW);
	peakCollective_kW.setValue(COOP.v_rapidRunData.getPeakFeedin_kW());
	text_peakType = "teruglevering";
}


chart_barChartGSLDSummary3.addDataItem(totalGTV_kW,"Totaal GTV Cumulatief " + text_peakType,darkMagenta);
chart_barChartGSLDSummary3.addDataItem(peakIndividual_kW,"Piek " + text_peakType + " individueel",orange);
chart_barChartGSLDSummary3.addDataItem(peakCollective_kW,"Piek " + text_peakType + " collectief",green);
chart_barChartGSLDSummary3.addDataItem(totalGTVgroupcontract_kW,"GTV Groepscontract " + text_peakType, darkBlue);
/*ALCODEEND*/}

double f_setWarningScreen(boolean showWarningScreen)
{/*ALCODESTART::1741342546947*/
gr_warningScreen.setVisible(showWarningScreen);
/*ALCODEEND*/}

double f_setEnergyBalanceChartTotal(I_EnergyData dataObject)
{/*ALCODESTART::1744283648638*/
//Reset charts
pl_productionChartBalanceTotal.removeAll();
pl_consumptionChartBalanceTotal.removeAll();

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

