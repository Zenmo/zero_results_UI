double f_addOtherEnergyFlows_Year(I_EnergyData dataObject)
{/*ALCODESTART::1714746057322*/
double startTime_h = 0;

EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.getActiveAssetData().activeProductionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.getActiveAssetData().activeConsumptionEnergyCarriers);

activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChartYear.addDataSet( dataObject.getRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(EC_consumption).getDataSet(startTime_h), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
	else if(dataObject.getActiveAssetData().hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChartYear.addDataSet( dataObject.getRapidRunData().acc_dailyAverageDistrictHeatingConsumption_kW.getDataSet(startTime_h), "Warmte import", uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
}

for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChartYear.addDataSet( dataObject.getRapidRunData().am_dailyAverageProductionAccumulators_kW.get(EC_production).getDataSet(startTime_h), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production)); 
}


/*ALCODEEND*/}

double f_addElectricityFlows_Year(I_EnergyData dataObject)
{/*ALCODESTART::1714746057324*/
group_year.setVisible(true);

double startTime_h = 0;

if (uI_Results.v_selectedObjectType == OL_SelectedObjectType.COOP && b_subdivideEnergyCoopFlows) {
	List<Color> colorSpectrum = new ArrayList<>();
	List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getAllChildMemberGridConnections();
	
	for (int k = 0; k < memberGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, memberGridConnections.size()));
		energyDemandChartYear.addDataSet(memberGridConnections.get(k).getRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(startTime_h), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));
		energySupplyChartYear.addDataSet(memberGridConnections.get(k).getRapidRunData().am_dailyAverageProductionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(startTime_h), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));	
	}
}
else{	
	//Energy demand chart
	energyDemandChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageBaseloadElectricityConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte basis", uI_Results.v_electricityBaseloadDemandColor);
	if (dataObject.getActiveAssetData().hasElectricHeating) {
		energyDemandChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageHeatPumpElectricityConsumption_kW.getDataSet(startTime_h),"Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if (dataObject.getActiveAssetData().hasElectricTransport) { 
		energyDemandChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageElectricVehicleConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if (dataObject.getActiveAssetData().hasBattery) {
		energyDemandChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageBatteriesConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if (dataObject.getActiveAssetData().hasElectrolyser) {
		energyDemandChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageElectrolyserElectricityConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.getActiveAssetData().hasElectricCooking){
		energyDemandChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageElectricCookingConsumption_kW.getDataSet(startTime_h), "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Energy supply chart
	if (dataObject.getActiveAssetData().hasWindturbine) {
	energySupplyChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageWindProduction_kW.getDataSet(startTime_h), "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if (dataObject.getActiveAssetData().hasPV) {
		energySupplyChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAveragePVProduction_kW.getDataSet(startTime_h), "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if (dataObject.getActiveAssetData().hasBattery) {
		energySupplyChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageBatteriesProduction_kW.getDataSet(startTime_h), "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if (dataObject.getActiveAssetData().hasV2G) {
		energySupplyChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageV2GProduction_kW.getDataSet(startTime_h), "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if (dataObject.getActiveAssetData().hasCHP) {
		energySupplyChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageCHPElectricityProduction_kW.getDataSet(startTime_h), "Elektr. Opwek WKK", uI_Results.v_CHPElectricitySupplyColor);
	}
}
/*ALCODEEND*/}

double f_addOtherEnergyCarriers_Live(I_EnergyData dataObject)
{/*ALCODESTART::1714746057326*/
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.getActiveAssetData().activeConsumptionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.getActiveAssetData().activeProductionEnergyCarriers);

activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );


for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChart.addDataSet( dataObject.getLiveData().dsm_liveDemand_kW.get(EC_consumption), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
	else if(dataObject.getActiveAssetData().hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChart.addDataSet( dataObject.getLiveData().data_districtHeatDelivery_kW, "Warmte import", uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
}


for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChart.addDataSet( dataObject.getLiveData().dsm_liveSupply_kW.get(EC_production), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));  
}

/*ALCODEEND*/}

double f_addElectricityFlows_Live(I_EnergyData dataObject)
{/*ALCODESTART::1714746057328*/
group_week.setVisible(true);

if (uI_Results.v_selectedObjectType == OL_SelectedObjectType.COOP && b_subdivideEnergyCoopFlows) {
	List<Color> colorSpectrum = new ArrayList<>();
	List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.getLiveData().parentAgent).f_getAllChildMemberGridConnections();
	
	for (int k = 0; k < memberGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, memberGridConnections.size()));
		energyDemandChart.addDataSet(memberGridConnections.get(k).getLiveData().dsm_liveDemand_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));
		energySupplyChart.addDataSet(memberGridConnections.get(k).getLiveData().dsm_liveSupply_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));	
	}
}
else{
	//Demand
	energyDemandChart.addDataSet(dataObject.getLiveData().data_baseloadElectricityDemand_kW, "Elektr. behoefte basis", uI_Results.v_electricityBaseloadDemandColor);
	if(dataObject.getActiveAssetData().hasElectricHeating){
		energyDemandChart.addDataSet(dataObject.getLiveData().data_heatPumpElectricityDemand_kW, "Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if(dataObject.getActiveAssetData().hasElectricTransport){
		energyDemandChart.addDataSet(dataObject.getLiveData().data_electricVehicleDemand_kW, "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if(dataObject.getActiveAssetData().hasBattery){
		energyDemandChart.addDataSet(dataObject.getLiveData().data_batteryCharging_kW, "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if (dataObject.getLiveData().data_hydrogenElectricityDemand_kW != null && dataObject.getActiveAssetData().hasElectrolyser) {
		energyDemandChart.addDataSet(dataObject.getLiveData().data_hydrogenElectricityDemand_kW, "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.getLiveData().data_cookingElectricityDemand_kW != null && dataObject.getActiveAssetData().hasElectricCooking){
		energyDemandChart.addDataSet(dataObject.getLiveData().data_cookingElectricityDemand_kW, "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Supply
	if(dataObject.getActiveAssetData().hasWindturbine){
		energySupplyChart.addDataSet(dataObject.getLiveData().data_windGeneration_kW, "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasPV){
		energySupplyChart.addDataSet(dataObject.getLiveData().data_PVGeneration_kW, "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasV2G){	
		energySupplyChart.addDataSet(dataObject.getLiveData().data_V2GSupply_kW, "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasBattery){
		energySupplyChart.addDataSet(dataObject.getLiveData().data_batteryDischarging_kW, "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasCHP){
		energySupplyChart.addDataSet(dataObject.getLiveData().data_CHPElectricityProductionLiveWeek_kW, "Elektr. opwek WKK", uI_Results.v_CHPElectricitySupplyColor);
	}
}
/*ALCODEEND*/}

double f_addOtherEnergyFlows_SummerWeek(I_EnergyData dataObject)
{/*ALCODESTART::1714897296534*/
double startTime_h = uI_Results.energyModel.p_startHourSummerWeek;

EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.getActiveAssetData().activeProductionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.getActiveAssetData().activeConsumptionEnergyCarriers);

activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChart.addDataSet( dataObject.getRapidRunData().am_summerWeekConsumptionAccumulators_kW.get(EC_consumption).getDataSet(startTime_h), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption));
	}
	else if(dataObject.getActiveAssetData().hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChart.addDataSet( dataObject.getRapidRunData().acc_summerWeekDistrictHeatingConsumption_kW.getDataSet(startTime_h), "Warmte import", uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
}

for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChart.addDataSet( dataObject.getRapidRunData().am_summerWeekProductionAccumulators_kW.get(EC_production).getDataSet(startTime_h), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));
}
/*ALCODEEND*/}

double f_addElectricityFlows_SummerWeek(I_EnergyData dataObject)
{/*ALCODESTART::1714897296536*/
group_week.setVisible(true);

double startTime_h = uI_Results.energyModel.p_startHourSummerWeek;

if (uI_Results.v_selectedObjectType == OL_SelectedObjectType.COOP && b_subdivideEnergyCoopFlows) {
	List<Color> colorSpectrum = new ArrayList<>();
	List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getAllChildMemberGridConnections();
	
	for (int k = 0; k < memberGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, memberGridConnections.size()));
		energyDemandChart.addDataSet(memberGridConnections.get(k).getRapidRunData().am_summerWeekConsumptionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(startTime_h), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));
		energySupplyChart.addDataSet(memberGridConnections.get(k).getRapidRunData().am_summerWeekProductionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(startTime_h), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));	
	}
}
else{
	//Energy demand chart
	energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekBaseloadElectricityConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte basis", uI_Results.v_electricityBaseloadDemandColor);
	if(dataObject.getActiveAssetData().hasElectricHeating){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekHeatPumpElectricityConsumption_kW.getDataSet(startTime_h),"Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if(dataObject.getActiveAssetData().hasElectricTransport){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekElectricVehicleConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if(dataObject.getActiveAssetData().hasBattery){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekBatteriesConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if(dataObject.getActiveAssetData().hasElectrolyser){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekElectrolyserElectricityConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.getActiveAssetData().hasElectricCooking){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekElectricCookingConsumption_kW.getDataSet(startTime_h), "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Energy supply chart
	if(dataObject.getActiveAssetData().hasWindturbine){
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekWindProduction_kW.getDataSet(startTime_h), "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasPV){
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekPVProduction_kW.getDataSet(startTime_h), "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasBattery){
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekBatteriesProduction_kW.getDataSet(startTime_h), "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasV2G){
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekV2GProduction_kW.getDataSet(startTime_h), "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasCHP){
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekCHPElectricityProduction_kW.getDataSet(startTime_h), "Elektr. Opwek WKK", uI_Results.v_CHPElectricitySupplyColor);
	}
}
/*ALCODEEND*/}

AreaCollection f_resetCharts()
{/*ALCODESTART::1714897512553*/
group_week.setVisible(false);
group_year.setVisible(false);
group_day.setVisible(false);
group_trafo_week.setVisible(false);
group_trafo_year.setVisible(false);
group_trafo_day.setVisible(false);
group_netload_week.setVisible(false);
group_netload_year.setVisible(false);
group_netload_day.setVisible(false);
plot_trafo_week.removeAll();
plot_trafo_year.removeAll();
plot_trafo_day.removeAll();
plot_netload_week.removeAll();
plot_netload_year.removeAll();
plot_netload_day.removeAll();
energyDemandChart.removeAll();
energySupplyChart.removeAll();
energyDemandChartYear.removeAll();
energySupplyChartYear.removeAll();
energyDemandChartDay.removeAll();
energySupplyChartDay.removeAll();

radio_periodLive.setVisible(false);
/*ALCODEEND*/}

double f_addOtherEnergyFlows_WinterWeek(I_EnergyData dataObject)
{/*ALCODESTART::1714897923568*/
double startTime_h = uI_Results.energyModel.p_startHourWinterWeek;

EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.getActiveAssetData().activeProductionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.getActiveAssetData().activeConsumptionEnergyCarriers);

activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChart.addDataSet( dataObject.getRapidRunData().am_winterWeekConsumptionAccumulators_kW.get(EC_consumption).getDataSet(startTime_h), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption));
	}
	else if(dataObject.getActiveAssetData().hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChart.addDataSet( dataObject.getRapidRunData().acc_winterWeekDistrictHeatingConsumption_kW.getDataSet(startTime_h), "Warmte import", uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
}

for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChart.addDataSet( dataObject.getRapidRunData().am_winterWeekProductionAccumulators_kW.get(EC_production).getDataSet(startTime_h), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production)); 
}

/*ALCODEEND*/}

double f_addElectricityFlows_WinterWeek(I_EnergyData dataObject)
{/*ALCODESTART::1714897923570*/
group_week.setVisible(true);

double startTime_h = uI_Results.energyModel.p_startHourWinterWeek;

if (uI_Results.v_selectedObjectType == OL_SelectedObjectType.COOP && b_subdivideEnergyCoopFlows) {
	List<Color> colorSpectrum = new ArrayList<>();
	List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getAllChildMemberGridConnections();
	
	for (int k = 0; k < memberGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, memberGridConnections.size()));
		energyDemandChart.addDataSet(memberGridConnections.get(k).getRapidRunData().am_winterWeekConsumptionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(startTime_h), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));
		energySupplyChart.addDataSet(memberGridConnections.get(k).getRapidRunData().am_winterWeekProductionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(startTime_h), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));	
	}
}
else{
	//Energy demand chart
	energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekBaseloadElectricityConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte basis", uI_Results.v_electricityBaseloadDemandColor);
	if(dataObject.getActiveAssetData().hasElectricHeating){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekHeatPumpElectricityConsumption_kW.getDataSet(startTime_h),"Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if(dataObject.getActiveAssetData().hasElectricTransport){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekElectricVehicleConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if(dataObject.getActiveAssetData().hasBattery){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekBatteriesConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if (dataObject.getActiveAssetData().hasElectrolyser) {
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekElectrolyserElectricityConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.getActiveAssetData().hasElectricCooking){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekElectricCookingConsumption_kW.getDataSet(startTime_h), "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Energy supply chart
	if(dataObject.getActiveAssetData().hasWindturbine){
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekWindProduction_kW.getDataSet(startTime_h), "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasPV){
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekPVProduction_kW.getDataSet(startTime_h), "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasBattery){	
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekBatteriesProduction_kW.getDataSet(startTime_h), "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasV2G){	
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekV2GProduction_kW.getDataSet(startTime_h), "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasCHP){	
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekCHPElectricityProduction_kW.getDataSet(startTime_h), "Elektr. Opwek WKK", uI_Results.v_CHPElectricitySupplyColor);
	}
}
/*ALCODEEND*/}

double f_setCharts()
{/*ALCODESTART::1714899014782*/
f_resetCharts();
if (radio_period.getValue() == 0) {
	radio_periodLive.setVisible(true);
}
I_EnergyData dataObject = uI_Results.f_getSelectedObjectData();

if (radio_energyType.getValue() == 2) { // Line Plot (Net Load)
	switch (radio_period.getValue()) {
		case 0: // Live
			if (uI_Results.v_selectedObjectType == OL_SelectedObjectType.GRIDNODE) {
				if (radio_periodLive.getValue() == 0) {
					f_addElectricityFlowsTrafo_Live(uI_Results.v_gridNode);
				}
				else {
					f_addElectricityFlowsTrafo_LiveDay(uI_Results.v_gridNode);
				}
			}
			else {
				if (radio_periodLive.getValue() == 0) {
					f_addElectricityNetLoad_Live(dataObject);
				}
				else {
					f_addElectricityNetLoad_LiveDay(dataObject);
				}
			}
			break;
		
		case 1: // Summer
			if (uI_Results.v_selectedObjectType == OL_SelectedObjectType.GRIDNODE) {
				f_addElectricityFlowsTrafo_SummerWeek(uI_Results.v_gridNode);
			}
			else {
				f_addElectricityNetLoad_SummerWeek(dataObject);
			}
			break;
		
		case 2: // Winter
			if (uI_Results.v_selectedObjectType == OL_SelectedObjectType.GRIDNODE) {
				f_addElectricityFlowsTrafo_WinterWeek(uI_Results.v_gridNode);
			}
			else {
				f_addElectricityNetLoad_WinterWeek(dataObject);
			}
			break;
		
		case 3: // Year
			if (uI_Results.v_selectedObjectType == OL_SelectedObjectType.GRIDNODE) {
				f_addElectricityFlowsTrafo_Year(uI_Results.v_gridNode);
			}
			else {
				//f_addElectricityNetLoad_Year(dataObject);
			}
			break;
			
		default:
			break;
	}
}
else { // Stack Chart
	switch (radio_period.getValue()) {
		case 0: // Live
			if (uI_Results.v_selectedObjectType == OL_SelectedObjectType.GRIDNODE) {
				if (radio_periodLive.getValue() == 0) {
					f_addElectricityFlowsTrafo_Live(uI_Results.v_gridNode);
				}
				else {
					f_addElectricityFlowsTrafo_LiveDay(uI_Results.v_gridNode);
				}				
			}
			else {
				if (radio_periodLive.getValue() == 0) {
					f_addElectricityFlows_Live(dataObject);				
				}
				else {
					f_addElectricityFlows_LiveDay(dataObject);				
				}
				if( radio_energyType.getValue() == 1){
					if (radio_periodLive.getValue() == 0) {
						f_addOtherEnergyCarriers_Live(dataObject);
					}
					else {
						f_addOtherEnergyCarriers_LiveDay(dataObject);
					}
				}
			}
			break;
		
		case 1: // Summer
			if (uI_Results.v_selectedObjectType == OL_SelectedObjectType.GRIDNODE) {
				f_addElectricityFlowsTrafo_SummerWeek(uI_Results.v_gridNode);
			}
			else {
				f_addElectricityFlows_SummerWeek(dataObject);
				if( radio_energyType.getValue() == 1){
					f_addOtherEnergyFlows_SummerWeek(dataObject);
				}
			}
			break;
			
		case 2: // Winter
			if (uI_Results.v_selectedObjectType == OL_SelectedObjectType.GRIDNODE) {
				f_addElectricityFlowsTrafo_WinterWeek(uI_Results.v_gridNode);
			} else {
				f_addElectricityFlows_WinterWeek(dataObject);
				if( radio_energyType.getValue() == 1){
					f_addOtherEnergyFlows_WinterWeek(dataObject);
				}
			}
			break;
			
		case 3: // Year
			if (uI_Results.v_selectedObjectType == OL_SelectedObjectType.GRIDNODE) {
				f_addElectricityFlowsTrafo_Year(dataObject);
			} else {
				f_addElectricityFlows_Year(dataObject);
				if( radio_energyType.getValue() == 1){
					f_addOtherEnergyFlows_Year(dataObject);
				}
			}
			break;
		
		default:
			break;
	}
}
/*ALCODEEND*/}

double f_setTrafoPlotScale(AreaCollection dataObject)
{/*ALCODESTART::1714908011118*/
int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW.getYMin(), -dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMin()));
plot_trafo_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);
/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_Live(AreaCollection dataObject)
{/*ALCODESTART::1715169261226*/
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

group_trafo_week.setVisible(true);
plot_trafo_week.addDataSet(dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW, feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW.getYMin()));
plot_trafo_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_Year(AreaCollection dataObject)
{/*ALCODESTART::1715169641896*/
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

group_trafo_year.setVisible(true);
plot_trafo_year.addDataSet(uI_Results.f_createFlatDataset(0, 8760, dataObject.v_gridCapacityDelivery_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_year.addDataSet(uI_Results.f_createFlatDataset(0, 8760, -dataObject.v_gridCapacityFeedIn_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_year.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionYear_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 0.25, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionYear_kW.getYMax(), dataObject.v_gridCapacityDelivery_kW));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionYear_kW.getYMin(), -dataObject.v_gridCapacityFeedIn_kW));
plot_trafo_year.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_SummerWeek(AreaCollection dataObject)
{/*ALCODESTART::1715171161720*/
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

group_trafo_week.setVisible(true);

//Add datasets to plot
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourSummerWeek, 168, dataObject.v_gridCapacityDelivery_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourSummerWeek, 168, -dataObject.v_gridCapacityFeedIn_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionSummerWeek_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionSummerWeek_kW.getYMax(), dataObject.v_gridCapacityDelivery_kW));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionSummerWeek_kW.getYMin(), -dataObject.v_gridCapacityFeedIn_kW));
plot_trafo_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_WinterWeek(AreaCollection dataObject)
{/*ALCODESTART::1715171298729*/
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

group_trafo_week.setVisible(true);
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourWinterWeek, 168, dataObject.v_gridCapacityDelivery_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourWinterWeek, 168, -dataObject.v_gridCapacityFeedIn_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionWinterWeek_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionWinterWeek_kW.getYMax(), dataObject.v_gridCapacityDelivery_kW));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionWinterWeek_kW.getYMin(), -dataObject.v_gridCapacityFeedIn_kW));
plot_trafo_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addElectricityNetLoad_Live(AreaCollection dataObject)
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

//Add datasets to plot
plot_netload_week.addDataSet(dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW, feedinCapacityLabel, feedinCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.v_dataNetLoadLiveWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

//Specific coop plot additions
if (dataObject.p_areaType == OL_GISObjectType.COOP ) {

	if(uI_Results.b_showGroupContractValues) {
		//plot_netload_week.addDataSet(uI_Results.v_dataEHubDeliveryCapacityLiveWeek_kW, "Groeps GTV afname (Rekenmethode Stedin)", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		//plot_netload_week.addDataSet(uI_Results.v_dataEHubFeedInCapacityLiveWeek_kW, "Groeps GTV teruglevering (Rekenmethode Stedin)", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
	if (b_subdivideEnergyCoopFlows) {
		List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.v_engineAgent).f_getAllChildMemberGridConnections();
		for (GridConnection GC : memberGridConnections) {
			plot_netload_week.addDataSet(GC.data_liveElectricityBalance_kW, GC.p_gridConnectionID, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
		}
	}
}

int maxValue = roundToInt(max(dataObject.v_dataNetLoadLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadLiveWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW.getYMin()));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.3, maxValue + maxValue * 0.3);

group_netload_week.setVisible(true);
/*ALCODEEND*/}

double f_addElectricityNetLoad_Year(AreaCollection dataObject)
{/*ALCODESTART::1736430560714*/
group_netload_year.setVisible(true);
plot_netload_year.addDataSet(dataObject.v_dataElectricityDemandCapacityLiveWeek_kW, "Piek leveringscapaciteit", uI_Results.v_electricityCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_year.addDataSet(dataObject.v_dataElectricitySupplyCapacityLiveWeek_kW, "Piek terugleveringscapaciteit", uI_Results.v_electricityCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_year.addDataSet(dataObject.v_dataNetLoadLiveWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataNetLoadLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDemandCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadLiveWeek_kW.getYMin(), dataObject.v_dataElectricitySupplyCapacityLiveWeek_kW.getYMin()));
plot_netload_year.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addElectricityNetLoad_SummerWeek(AreaCollection dataObject)
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

//Add datasets to plot
plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourSummerWeek, 168, dataObject.v_gridCapacityDelivery_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourSummerWeek, 168, -dataObject.v_gridCapacityFeedIn_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.v_dataNetLoadSummerWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

//Specific coop plot additions
if (dataObject.p_areaType == OL_GISObjectType.COOP ) {

	if(uI_Results.b_showGroupContractValues) {
		plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourSummerWeek, 168, dataObject.v_gridCapacityDelivery_groupcontract_kW), "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourSummerWeek, 168, dataObject.v_gridCapacityFeedin_groupcontract_kW), "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
	if (b_subdivideEnergyCoopFlows) {
		List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.v_engineAgent).f_getAllChildMemberGridConnections();
		for (GridConnection GC : memberGridConnections) {
			plot_netload_week.addDataSet(GC.am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(uI_Results.energyModel.p_startHourSummerWeek), GC.p_gridConnectionID, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
		}
	}
}

//Set vertical scale
int maxValue = roundToInt(max(dataObject.v_dataNetLoadSummerWeek_kW.getYMax(), dataObject.v_gridCapacityDelivery_kW));
int minValue = roundToInt(min(dataObject.v_dataNetLoadSummerWeek_kW.getYMin(), -dataObject.v_gridCapacityFeedIn_kW));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

group_netload_week.setVisible(true);

/*ALCODEEND*/}

double f_addElectricityNetLoad_WinterWeek(AreaCollection dataObject)
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

//Add datasets to plot
plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourWinterWeek, 168, dataObject.v_gridCapacityDelivery_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourWinterWeek, 168, -dataObject.v_gridCapacityFeedIn_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.v_dataNetLoadWinterWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

//Specific coop plot additions
if (dataObject.p_areaType == OL_GISObjectType.COOP ) {

	if(uI_Results.b_showGroupContractValues) {
		plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourWinterWeek, 168, dataObject.v_gridCapacityDelivery_groupcontract_kW), "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourWinterWeek, 168, dataObject.v_gridCapacityFeedin_groupcontract_kW), "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
	if (b_subdivideEnergyCoopFlows){
		List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.v_engineAgent).f_getAllChildMemberGridConnections();
		for (GridConnection GC : memberGridConnections) {
			plot_netload_week.addDataSet(GC.am_winterWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(uI_Results.energyModel.p_startHourWinterWeek), GC.p_gridConnectionID, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
		}
	}
}

//Set vertical scale
int maxValue = roundToInt(max(dataObject.v_dataNetLoadWinterWeek_kW.getYMax(), dataObject.v_gridCapacityDelivery_kW));
int minValue = roundToInt(min(dataObject.v_dataNetLoadWinterWeek_kW.getYMin(), -dataObject.v_gridCapacityFeedIn_kW));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

group_netload_week.setVisible(true);

/*ALCODEEND*/}

double f_addOtherEnergyCarriers_LiveDay(I_EnergyData dataObject)
{/*ALCODESTART::1739804290044*/
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.getActiveAssetData().activeConsumptionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.getActiveAssetData().activeProductionEnergyCarriers);

activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );


for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChartDay.addDataSet( dataObject.getLiveData().dsm_liveDemand_kW.get(EC_consumption), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
	else if(dataObject.getActiveAssetData().hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChartDay.addDataSet( dataObject.getLiveData().data_districtHeatDelivery_kW, "Warmte import", uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
}


for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChartDay.addDataSet( dataObject.getLiveData().dsm_liveSupply_kW.get(EC_production), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));  
}

/*ALCODEEND*/}

double f_addElectricityFlows_LiveDay(I_EnergyData dataObject)
{/*ALCODESTART::1739804290048*/
group_day.setVisible(true);

if (uI_Results.v_selectedObjectType == OL_SelectedObjectType.COOP && b_subdivideEnergyCoopFlows) {
	List<Color> colorSpectrum = new ArrayList<>();
	List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.getLiveData().parentAgent).f_getAllChildMemberGridConnections();
	
	for (int k = 0; k < memberGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, memberGridConnections.size()));
		energyDemandChartDay.addDataSet(memberGridConnections.get(k).getLiveData().dsm_liveDemand_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));
		energySupplyChartDay.addDataSet(memberGridConnections.get(k).getLiveData().dsm_liveSupply_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));	
	}
}
else{
	//Demand
	energyDemandChartDay.addDataSet(dataObject.getLiveData().data_baseloadElectricityDemand_kW, "Elektr. behoefte basis", uI_Results.v_electricityBaseloadDemandColor);
	if(dataObject.getActiveAssetData().hasElectricHeating){
	energyDemandChartDay.addDataSet(dataObject.getLiveData().data_heatPumpElectricityDemand_kW, "Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if(dataObject.getActiveAssetData().hasElectricTransport){
		energyDemandChartDay.addDataSet(dataObject.getLiveData().data_electricVehicleDemand_kW, "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if(dataObject.getActiveAssetData().hasBattery){
		energyDemandChartDay.addDataSet(dataObject.getLiveData().data_batteryCharging_kW, "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if (dataObject.getLiveData().data_hydrogenElectricityDemand_kW != null && dataObject.getActiveAssetData().hasElectrolyser) {
		energyDemandChartDay.addDataSet(dataObject.getLiveData().data_hydrogenElectricityDemand_kW, "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.getLiveData().data_cookingElectricityDemand_kW != null && dataObject.getActiveAssetData().hasElectricCooking){
		energyDemandChartDay.addDataSet(dataObject.getLiveData().data_cookingElectricityDemand_kW, "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Supply
	if(dataObject.getActiveAssetData().hasWindturbine){
		energySupplyChartDay.addDataSet(dataObject.getLiveData().data_windGeneration_kW, "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasPV){
		energySupplyChartDay.addDataSet(dataObject.getLiveData().data_PVGeneration_kW, "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasV2G){	
		energySupplyChartDay.addDataSet(dataObject.getLiveData().data_V2GSupply_kW, "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasBattery){
		energySupplyChartDay.addDataSet(dataObject.getLiveData().data_batteryDischarging_kW, "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if(dataObject.getActiveAssetData().hasCHP){
		energySupplyChartDay.addDataSet(dataObject.getLiveData().data_CHPElectricityProductionLiveWeek_kW, "Elektr. opwek WKK", uI_Results.v_CHPElectricitySupplyColor);
	}

}
/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_LiveDay(AreaCollection dataObject)
{/*ALCODESTART::1739804290051*/
group_trafo_day.setVisible(true);

String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor = uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor = uI_Results.v_electricityCapacityColor_estimated;

if(dataObject.b_isRealDeliveryCapacityAvailable){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor = uI_Results.v_electricityCapacityColor_known;
}
if(dataObject.b_isRealFeedinCapacityAvailable){
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor = uI_Results.v_electricityCapacityColor_known;
}

plot_trafo_day.addDataSet(dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_day.addDataSet(dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW, feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_day.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

// TODO: Change these to only look at the day instead of the week?
int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW.getYMin()));
plot_trafo_day.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addElectricityNetLoad_LiveDay(AreaCollection dataObject)
{/*ALCODESTART::1739804290055*/
String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor = uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor = uI_Results.v_electricityCapacityColor_estimated;

if(dataObject.b_isRealDeliveryCapacityAvailable){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor = uI_Results.v_electricityCapacityColor_known;
}
if(dataObject.b_isRealFeedinCapacityAvailable){
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor	= uI_Results.v_electricityCapacityColor_known;
}

//Add datasets to plot
plot_netload_day.addDataSet(dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_day.addDataSet(dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW, feedinCapacityLabel, feedinCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_day.addDataSet(dataObject.v_dataNetLoadLiveWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

//Specific coop plot additions
if (dataObject.p_areaType == OL_GISObjectType.COOP ) {

	if(uI_Results.b_showGroupContractValues) {
		//plot_netload_day.addDataSet(uI_Results.v_dataEHubDeliveryCapacityLiveWeek_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		//plot_netload_day.addDataSet(uI_Results.v_dataEHubFeedInCapacityLiveWeek_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
	if (b_subdivideEnergyCoopFlows) {
		List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.v_engineAgent).f_getAllChildMemberGridConnections();
		for (GridConnection GC : memberGridConnections) {
			plot_netload_day.addDataSet(GC.data_liveElectricityBalance_kW, GC.p_gridConnectionID, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
		}
	}
}

int maxValue = roundToInt(max(dataObject.v_dataNetLoadLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadLiveWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW.getYMin()));
plot_netload_day.setFixedVerticalScale(minValue + minValue * 0.3, maxValue + maxValue * 0.3);

group_netload_day.setVisible(true);
/*ALCODEEND*/}

double f_addOtherEnergyFlows_Year1(AreaCollection dataObject)
{/*ALCODESTART::1741337336542*/
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeProductionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeConsumptionEnergyCarriers);

activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChartYear.addDataSet( dataObject.dsm_dailyAverageConsumptionDataSets_kW.get(EC_consumption), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
	else if(dataObject.b_hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		if (dataObject.v_dataDistrictHeatConsumptionYear_kW != null) { // TODO: Fix engine and then remove this null check
			energyDemandChartYear.addDataSet( dataObject.v_dataDistrictHeatConsumptionYear_kW, "Warmte import", uI_Results.cm_consumptionColors.get(EC_consumption)); 
		}
	}
}

for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChartYear.addDataSet( dataObject.dsm_dailyAverageProductionDataSets_kW.get(EC_production), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production)); 
}


/*ALCODEEND*/}

double f_addElectricityFlows_Year1(AreaCollection dataObject)
{/*ALCODESTART::1741337336544*/
group_year.setVisible(true);

if (dataObject.p_areaType == OL_GISObjectType.COOP && b_subdivideEnergyCoopFlows) {
	List<Color> colorSpectrum = new ArrayList<>();
	List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.v_engineAgent).f_getAllChildMemberGridConnections();
	
	for (int k = 0; k < memberGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, memberGridConnections.size()));
		energyDemandChartYear.addDataSet(memberGridConnections.get(k).dsm_dailyAverageDemandDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));
		energySupplyChartYear.addDataSet(memberGridConnections.get(k).dsm_dailyAverageSupplyDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));	
	}
}
else{	
	//Energy demand chart
	if (dataObject.v_dataElectricityBaseloadConsumptionYear_kW != null) {
		energyDemandChartYear.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionYear_kW, "Elektr. behoefte basis", uI_Results.v_electricityBaseloadDemandColor);
	}
	if (dataObject.v_dataElectricityForHeatConsumptionYear_kW != null && dataObject.b_hasElectricHeating) {
		energyDemandChartYear.addDataSet(dataObject.v_dataElectricityForHeatConsumptionYear_kW,"Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if (dataObject.v_dataElectricityForTransportConsumptionYear_kW != null && dataObject.b_hasElectricTransport) { 
		energyDemandChartYear.addDataSet(dataObject.v_dataElectricityForTransportConsumptionYear_kW, "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if (dataObject.v_dataElectricityForStorageConsumptionYear_kW != null && dataObject.b_hasBattery) {
		energyDemandChartYear.addDataSet(dataObject.v_dataElectricityForStorageConsumptionYear_kW, "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if (dataObject.v_dataElectricityForHydrogenConsumptionYear_kW != null && dataObject.b_hasElectrolyser) {
		energyDemandChartYear.addDataSet(dataObject.v_dataElectricityForHydrogenConsumptionYear_kW, "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.v_dataElectricityForCookingConsumptionYear_kW != null && dataObject.b_hasElectricCooking){
		energyDemandChartYear.addDataSet(dataObject.v_dataElectricityForCookingConsumptionYear_kW, "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Energy supply chart
	if (dataObject.v_dataElectricityWindProductionYear_kW != null && dataObject.b_hasWindturbine) {
	energySupplyChartYear.addDataSet(dataObject.v_dataElectricityWindProductionYear_kW, "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if (dataObject.v_dataElectricityPVProductionYear_kW != null && dataObject.b_hasPV) {
		energySupplyChartYear.addDataSet(dataObject.v_dataElectricityPVProductionYear_kW, "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if (dataObject.v_dataElectricityStorageProductionYear_kW != null && dataObject.b_hasBattery) {
		energySupplyChartYear.addDataSet(dataObject.v_dataElectricityStorageProductionYear_kW, "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if (dataObject.v_dataElectricityV2GProductionYear_kW != null && dataObject.b_hasV2G) {
		energySupplyChartYear.addDataSet(dataObject.v_dataElectricityV2GProductionYear_kW, "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if (dataObject.v_dataElectricityCHPProductionYear_kW != null && dataObject.b_hasCHP) {
		energySupplyChartYear.addDataSet(dataObject.v_dataElectricityCHPProductionYear_kW, "Elektr. Opwek WKK", uI_Results.v_CHPElectricitySupplyColor);
	}
}
/*ALCODEEND*/}

double f_addOtherEnergyCarriers_Live1(AreaCollection dataObject)
{/*ALCODESTART::1741337336546*/
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeConsumptionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeProductionEnergyCarriers);

activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );


for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChart.addDataSet( dataObject.dsm_liveConsumption_kW.get(EC_consumption), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
	else if(dataObject.b_hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChart.addDataSet( dataObject.v_dataDistrictHeatConsumptionLiveWeek_kW, "Warmte import", uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
}


for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChart.addDataSet( dataObject.dsm_liveProduction_kW.get(EC_production), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));  
}

/*ALCODEEND*/}

double f_addElectricityFlows_Live1(AreaCollection dataObject)
{/*ALCODESTART::1741337336548*/
group_week.setVisible(true);

if (dataObject.p_areaType == OL_GISObjectType.COOP && b_subdivideEnergyCoopFlows) {
	List<Color> colorSpectrum = new ArrayList<>();
	List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.v_engineAgent).f_getAllChildMemberGridConnections();
	
	for (int k = 0; k < memberGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, memberGridConnections.size()));
		energyDemandChart.addDataSet(memberGridConnections.get(k).dsm_liveDemand_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));
		energySupplyChart.addDataSet(memberGridConnections.get(k).dsm_liveSupply_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));	
	}
}
else{
	//Demand
	energyDemandChart.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW, "Elektr. behoefte basis", uI_Results.v_electricityBaseloadDemandColor);
	if(dataObject.b_hasElectricHeating){
	energyDemandChart.addDataSet(dataObject.v_dataElectricityForHeatConsumptionLiveWeek_kW, "Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if(dataObject.b_hasElectricTransport){
		energyDemandChart.addDataSet(dataObject.v_dataElectricityForTransportConsumptionLiveWeek_kW, "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if(dataObject.b_hasBattery){
		energyDemandChart.addDataSet(dataObject.v_dataElectricityForStorageConsumptionLiveWeek_kW, "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if (dataObject.v_dataElectricityForHydrogenConsumptionLiveWeek_kW != null && dataObject.b_hasElectrolyser) {
		energyDemandChart.addDataSet(dataObject.v_dataElectricityForHydrogenConsumptionLiveWeek_kW, "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.v_dataElectricityForCookingConsumptionLiveWeek_kW != null && dataObject.b_hasElectricCooking){
		energyDemandChart.addDataSet(dataObject.v_dataElectricityForCookingConsumptionLiveWeek_kW, "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Supply
	if(dataObject.b_hasWindturbine){
		energySupplyChart.addDataSet(dataObject.v_dataWindElectricityProductionLiveWeek_kW, "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if(dataObject.b_hasPV){
		energySupplyChart.addDataSet(dataObject.v_dataPVElectricityProductionLiveWeek_kW, "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if(dataObject.b_hasV2G){	
		energySupplyChart.addDataSet(dataObject.v_dataV2GElectricityProductionLiveWeek_kW, "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if(dataObject.b_hasBattery){
		energySupplyChart.addDataSet(dataObject.v_dataStorageElectricityProductionLiveWeek_kW, "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if(dataObject.b_hasCHP){
		energySupplyChart.addDataSet(dataObject.v_dataCHPElectricityProductionLiveWeek_kW, "Elektr. opwek WKK", uI_Results.v_CHPElectricitySupplyColor);
	}
}
/*ALCODEEND*/}

double f_addOtherEnergyFlows_SummerWeek1(AreaCollection dataObject)
{/*ALCODESTART::1741337336550*/
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeProductionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeConsumptionEnergyCarriers);

activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChart.addDataSet( dataObject.dsm_summerWeekConsumptionDataSets_kW.get(EC_consumption), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption));
	}
	else if(dataObject.b_hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		if (dataObject.v_dataDistrictHeatConsumptionSummerWeek_kW!= null) { // TODO: Fix engine and then remove this null check
			energyDemandChart.addDataSet( dataObject.v_dataDistrictHeatConsumptionSummerWeek_kW, "Warmte import", uI_Results.cm_consumptionColors.get(EC_consumption)); 
		}
	}
}

for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChart.addDataSet( dataObject.dsm_summerWeekProductionDataSets_kW.get(EC_production), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));
}
/*ALCODEEND*/}

double f_addElectricityFlows_SummerWeek1(AreaCollection dataObject)
{/*ALCODESTART::1741337336552*/
group_week.setVisible(true);

if (dataObject.p_areaType == OL_GISObjectType.COOP && b_subdivideEnergyCoopFlows) {
	List<Color> colorSpectrum = new ArrayList<>();
	List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.v_engineAgent).f_getAllChildMemberGridConnections();
	
	for (int k = 0; k < memberGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, memberGridConnections.size()));
		energyDemandChart.addDataSet(memberGridConnections.get(k).dsm_summerWeekDemandDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));
		energySupplyChart.addDataSet(memberGridConnections.get(k).dsm_summerWeekSupplyDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));	
	}
}
else{
	//Energy demand chart
	energyDemandChart.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionSummerWeek_kW, "Elektr. behoefte basis", uI_Results.v_electricityBaseloadDemandColor);
	if(dataObject.b_hasElectricHeating){
		energyDemandChart.addDataSet(dataObject.v_dataElectricityForHeatConsumptionSummerWeek_kW,"Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if(dataObject.b_hasElectricTransport){
		energyDemandChart.addDataSet(dataObject.v_dataElectricityForTransportConsumptionSummerWeek_kW, "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if(dataObject.b_hasBattery){
		energyDemandChart.addDataSet(dataObject.v_dataElectricityForStorageConsumptionSummerWeek_kW, "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if(dataObject.b_hasElectrolyser){
		energyDemandChart.addDataSet(dataObject.v_dataElectricityForHydrogenConsumptionSummerWeek_kW, "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.v_dataElectricityForCookingConsumptionSummerWeek_kW != null && dataObject.b_hasElectricCooking){
		energyDemandChart.addDataSet(dataObject.v_dataElectricityForCookingConsumptionSummerWeek_kW, "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Energy supply chart
	if(dataObject.b_hasWindturbine){
		energySupplyChart.addDataSet(dataObject.v_dataElectricityWindProductionSummerWeek_kW, "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if(dataObject.b_hasPV){
		energySupplyChart.addDataSet(dataObject.v_dataElectricityPVProductionSummerWeek_kW, "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if(dataObject.b_hasBattery){
		energySupplyChart.addDataSet(dataObject.v_dataElectricityStorageProductionSummerWeek_kW, "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if(dataObject.b_hasV2G){
		energySupplyChart.addDataSet(dataObject.v_dataElectricityV2GProductionSummerWeek_kW, "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if(dataObject.b_hasCHP){
		energySupplyChart.addDataSet(dataObject.v_dataElectricityCHPProductionSummerWeek_kW, "Elektr. Opwek WKK", uI_Results.v_CHPElectricitySupplyColor);
	}
}
/*ALCODEEND*/}

AreaCollection f_resetCharts1()
{/*ALCODESTART::1741337336554*/
group_week.setVisible(false);
group_year.setVisible(false);
group_day.setVisible(false);
group_trafo_week.setVisible(false);
group_trafo_year.setVisible(false);
group_trafo_day.setVisible(false);
group_netload_week.setVisible(false);
group_netload_year.setVisible(false);
group_netload_day.setVisible(false);
plot_trafo_week.removeAll();
plot_trafo_year.removeAll();
plot_trafo_day.removeAll();
plot_netload_week.removeAll();
plot_netload_year.removeAll();
plot_netload_day.removeAll();
energyDemandChart.removeAll();
energySupplyChart.removeAll();
energyDemandChartYear.removeAll();
energySupplyChartYear.removeAll();
energyDemandChartDay.removeAll();
energySupplyChartDay.removeAll();

radio_periodLive.setVisible(false);
/*ALCODEEND*/}

double f_addOtherEnergyFlows_WinterWeek1(AreaCollection dataObject)
{/*ALCODESTART::1741337336556*/
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeProductionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeConsumptionEnergyCarriers);

activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChart.addDataSet( dataObject.dsm_winterWeekConsumptionDataSets_kW.get(EC_consumption), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption));
	}
	else if(dataObject.b_hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		if (dataObject.v_dataDistrictHeatConsumptionWinterWeek_kW!= null) { // TODO: Fix engine and then remove this null check
			energyDemandChart.addDataSet( dataObject.v_dataDistrictHeatConsumptionWinterWeek_kW, "Warmte import", uI_Results.cm_consumptionColors.get(EC_consumption)); 
		}
	}
}

for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChart.addDataSet( dataObject.dsm_winterWeekProductionDataSets_kW.get(EC_production), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production)); 
}

/*ALCODEEND*/}

double f_addElectricityFlows_WinterWeek1(AreaCollection dataObject)
{/*ALCODESTART::1741337336558*/
group_week.setVisible(true);

if (dataObject.p_areaType == OL_GISObjectType.COOP && b_subdivideEnergyCoopFlows) {
	List<Color> colorSpectrum = new ArrayList<>();
	List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.v_engineAgent).f_getAllChildMemberGridConnections();
	
	for (int k = 0; k < memberGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, memberGridConnections.size()));
		energyDemandChart.addDataSet(memberGridConnections.get(k).dsm_winterWeekDemandDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));
		energySupplyChart.addDataSet(memberGridConnections.get(k).dsm_winterWeekSupplyDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));	
	}
}
else{
	//Energy demand chart
	energyDemandChart.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionWinterWeek_kW, "Elektr. behoefte basis", uI_Results.v_electricityBaseloadDemandColor);
	if(dataObject.b_hasElectricHeating){
		energyDemandChart.addDataSet(dataObject.v_dataElectricityForHeatConsumptionWinterWeek_kW,"Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if(dataObject.b_hasElectricTransport){
		energyDemandChart.addDataSet(dataObject.v_dataElectricityForTransportConsumptionWinterWeek_kW, "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if(dataObject.b_hasBattery){
		energyDemandChart.addDataSet(dataObject.v_dataElectricityForStorageConsumptionWinterWeek_kW, "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if (dataObject.v_dataElectricityForHydrogenConsumptionWinterWeek_kW != null && dataObject.b_hasElectrolyser) {
		energyDemandChart.addDataSet(dataObject.v_dataElectricityForHydrogenConsumptionWinterWeek_kW, "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.v_dataElectricityForCookingConsumptionWinterWeek_kW != null && dataObject.b_hasElectricCooking){
		energyDemandChart.addDataSet(dataObject.v_dataElectricityForCookingConsumptionWinterWeek_kW, "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Energy supply chart
	if(dataObject.b_hasWindturbine){
		energySupplyChart.addDataSet(dataObject.v_dataElectricityWindProductionWinterWeek_kW, "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if(dataObject.b_hasPV){
		energySupplyChart.addDataSet(dataObject.v_dataElectricityPVProductionWinterWeek_kW, "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if(dataObject.b_hasBattery){	
		energySupplyChart.addDataSet(dataObject.v_dataElectricityStorageProductionWinterWeek_kW, "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if(dataObject.b_hasV2G){	
		energySupplyChart.addDataSet(dataObject.v_dataElectricityV2GProductionWinterWeek_kW, "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if(dataObject.b_hasCHP){	
		energySupplyChart.addDataSet(dataObject.v_dataElectricityCHPProductionWinterWeek_kW, "Elektr. Opwek WKK", uI_Results.v_CHPElectricitySupplyColor);
	}
}
/*ALCODEEND*/}

double f_setCharts1()
{/*ALCODESTART::1741337336560*/
f_resetCharts();
if (radio_period.getValue() == 0) {
	radio_periodLive.setVisible(true);
}
AreaCollection dataObject = uI_Results.f_getDataObject();

if (radio_energyType.getValue() == 2) { // Line Plot (Net Load)
	switch (radio_period.getValue()) {
		case 0: // Live
			if (uI_Results.v_selectedObjectType == OL_GISObjectType.GRIDNODE) {
				if (radio_periodLive.getValue() == 0) {
					f_addElectricityFlowsTrafo_Live(dataObject);
				}
				else {
					f_addElectricityFlowsTrafo_LiveDay(dataObject);
				}
			}
			else {
				if (radio_periodLive.getValue() == 0) {
					f_addElectricityNetLoad_Live(dataObject);
				}
				else {
					f_addElectricityNetLoad_LiveDay(dataObject);
				}
			}
			break;
		
		case 1: // Summer
			if (uI_Results.v_selectedObjectType == OL_GISObjectType.GRIDNODE) {
				f_addElectricityFlowsTrafo_SummerWeek(dataObject);
			}
			else {
				f_addElectricityNetLoad_SummerWeek(dataObject);
			}
			break;
		
		case 2: // Winter
			if (uI_Results.v_selectedObjectType == OL_GISObjectType.GRIDNODE) {
				f_addElectricityFlowsTrafo_WinterWeek(dataObject);
			}
			else {
				f_addElectricityNetLoad_WinterWeek(dataObject);
			}
			break;
		
		case 3: // Year
			if (uI_Results.v_selectedObjectType == OL_GISObjectType.GRIDNODE) {
				f_addElectricityFlowsTrafo_Year(dataObject);
			}
			else {
				//f_addElectricityNetLoad_Year(dataObject);
			}
			break;
			
		default:
			break;
	}
}
else { // Stack Chart
	switch (radio_period.getValue()) {
		case 0: // Live
			if (uI_Results.v_selectedObjectType == OL_GISObjectType.GRIDNODE) {
				if (radio_periodLive.getValue() == 0) {
					f_addElectricityFlowsTrafo_Live(dataObject);
				}
				else {
					f_addElectricityFlowsTrafo_LiveDay(dataObject);
				}				
			}
			else {
				if (radio_periodLive.getValue() == 0) {
					f_addElectricityFlows_Live(dataObject);				
				}
				else {
					f_addElectricityFlows_LiveDay(dataObject);				
				}
				if( radio_energyType.getValue() == 1){
					if (radio_periodLive.getValue() == 0) {
						f_addOtherEnergyCarriers_Live(dataObject);
					}
					else {
						f_addOtherEnergyCarriers_LiveDay(dataObject);
					}
				}
			}
			break;
		
		case 1: // Summer
			if (uI_Results.v_selectedObjectType == OL_GISObjectType.GRIDNODE) {
				f_addElectricityFlowsTrafo_SummerWeek(dataObject);
			}
			else {
				f_addElectricityFlows_SummerWeek(dataObject);
				if( radio_energyType.getValue() == 1){
					f_addOtherEnergyFlows_SummerWeek(dataObject);
				}
		}
			break;
			
		case 2: // Winter
			if (uI_Results.v_selectedObjectType == OL_GISObjectType.GRIDNODE) {
				f_addElectricityFlowsTrafo_WinterWeek(dataObject);
			} else {
				f_addElectricityFlows_WinterWeek(dataObject);
				if( radio_energyType.getValue() == 1){
					f_addOtherEnergyFlows_WinterWeek(dataObject);
				}
			}
			break;
			
		case 3: // Year
			if (uI_Results.v_selectedObjectType == OL_GISObjectType.GRIDNODE) {
				f_addElectricityFlowsTrafo_Year(dataObject);
			} else {
				f_addElectricityFlows_Year(dataObject);
				if( radio_energyType.getValue() == 1){
					f_addOtherEnergyFlows_Year(dataObject);
				}
			}
			break;
		
		default:
			break;
	}
}
/*ALCODEEND*/}

double f_setTrafoPlotScale1(AreaCollection dataObject)
{/*ALCODESTART::1741337336562*/
int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW.getYMin(), -dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMin()));
plot_trafo_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);
/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_Live1(AreaCollection dataObject)
{/*ALCODESTART::1741337336564*/
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

group_trafo_week.setVisible(true);
plot_trafo_week.addDataSet(dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW, feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW.getYMin()));
plot_trafo_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_Year1(AreaCollection dataObject)
{/*ALCODESTART::1741337336566*/
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

group_trafo_year.setVisible(true);
plot_trafo_year.addDataSet(uI_Results.f_createFlatDataset(0, 8760, dataObject.v_gridCapacityDelivery_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_year.addDataSet(uI_Results.f_createFlatDataset(0, 8760, -dataObject.v_gridCapacityFeedIn_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_year.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionYear_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 0.25, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionYear_kW.getYMax(), dataObject.v_gridCapacityDelivery_kW));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionYear_kW.getYMin(), -dataObject.v_gridCapacityFeedIn_kW));
plot_trafo_year.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_SummerWeek1(AreaCollection dataObject)
{/*ALCODESTART::1741337336568*/
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

group_trafo_week.setVisible(true);

//Add datasets to plot
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourSummerWeek, 168, dataObject.v_gridCapacityDelivery_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourSummerWeek, 168, -dataObject.v_gridCapacityFeedIn_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionSummerWeek_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionSummerWeek_kW.getYMax(), dataObject.v_gridCapacityDelivery_kW));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionSummerWeek_kW.getYMin(), -dataObject.v_gridCapacityFeedIn_kW));
plot_trafo_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_WinterWeek1(AreaCollection dataObject)
{/*ALCODESTART::1741337336570*/
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

group_trafo_week.setVisible(true);
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourWinterWeek, 168, dataObject.v_gridCapacityDelivery_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourWinterWeek, 168, -dataObject.v_gridCapacityFeedIn_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionWinterWeek_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionWinterWeek_kW.getYMax(), dataObject.v_gridCapacityDelivery_kW));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionWinterWeek_kW.getYMin(), -dataObject.v_gridCapacityFeedIn_kW));
plot_trafo_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addElectricityNetLoad_Live1(AreaCollection dataObject)
{/*ALCODESTART::1741337336572*/
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

//Add datasets to plot
plot_netload_week.addDataSet(dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW, feedinCapacityLabel, feedinCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.v_dataNetLoadLiveWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

//Specific coop plot additions
if (dataObject.p_areaType == OL_GISObjectType.COOP ) {

	if(uI_Results.b_showGroupContractValues) {
		//plot_netload_week.addDataSet(uI_Results.v_dataEHubDeliveryCapacityLiveWeek_kW, "Groeps GTV afname (Rekenmethode Stedin)", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		//plot_netload_week.addDataSet(uI_Results.v_dataEHubFeedInCapacityLiveWeek_kW, "Groeps GTV teruglevering (Rekenmethode Stedin)", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
	if (b_subdivideEnergyCoopFlows) {
		List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.v_engineAgent).f_getAllChildMemberGridConnections();
		for (GridConnection GC : memberGridConnections) {
			plot_netload_week.addDataSet(GC.data_liveElectricityBalance_kW, GC.p_gridConnectionID, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
		}
	}
}

int maxValue = roundToInt(max(dataObject.v_dataNetLoadLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadLiveWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW.getYMin()));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.3, maxValue + maxValue * 0.3);

group_netload_week.setVisible(true);
/*ALCODEEND*/}

double f_addElectricityNetLoad_Year1(AreaCollection dataObject)
{/*ALCODESTART::1741337336574*/
group_netload_year.setVisible(true);
plot_netload_year.addDataSet(dataObject.v_dataElectricityDemandCapacityLiveWeek_kW, "Piek leveringscapaciteit", uI_Results.v_electricityCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_year.addDataSet(dataObject.v_dataElectricitySupplyCapacityLiveWeek_kW, "Piek terugleveringscapaciteit", uI_Results.v_electricityCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_year.addDataSet(dataObject.v_dataNetLoadLiveWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataNetLoadLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDemandCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadLiveWeek_kW.getYMin(), dataObject.v_dataElectricitySupplyCapacityLiveWeek_kW.getYMin()));
plot_netload_year.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addElectricityNetLoad_SummerWeek1(AreaCollection dataObject)
{/*ALCODESTART::1741337336576*/
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

//Add datasets to plot
plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourSummerWeek, 168, dataObject.v_gridCapacityDelivery_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourSummerWeek, 168, -dataObject.v_gridCapacityFeedIn_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.v_dataNetLoadSummerWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

//Specific coop plot additions
if (dataObject.p_areaType == OL_GISObjectType.COOP ) {

	if(uI_Results.b_showGroupContractValues) {
		plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourSummerWeek, 168, dataObject.v_gridCapacityDelivery_groupcontract_kW), "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourSummerWeek, 168, dataObject.v_gridCapacityFeedin_groupcontract_kW), "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
	if (b_subdivideEnergyCoopFlows) {
		List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.v_engineAgent).f_getAllChildMemberGridConnections();
		for (GridConnection GC : memberGridConnections) {
			plot_netload_week.addDataSet(GC.am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(uI_Results.energyModel.p_startHourSummerWeek), GC.p_gridConnectionID, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
		}
	}
}

//Set vertical scale
int maxValue = roundToInt(max(dataObject.v_dataNetLoadSummerWeek_kW.getYMax(), dataObject.v_gridCapacityDelivery_kW));
int minValue = roundToInt(min(dataObject.v_dataNetLoadSummerWeek_kW.getYMin(), -dataObject.v_gridCapacityFeedIn_kW));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

group_netload_week.setVisible(true);

/*ALCODEEND*/}

double f_addElectricityNetLoad_WinterWeek1(AreaCollection dataObject)
{/*ALCODESTART::1741337336578*/
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

//Add datasets to plot
plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourWinterWeek, 168, dataObject.v_gridCapacityDelivery_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourWinterWeek, 168, -dataObject.v_gridCapacityFeedIn_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.v_dataNetLoadWinterWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

//Specific coop plot additions
if (dataObject.p_areaType == OL_GISObjectType.COOP ) {

	if(uI_Results.b_showGroupContractValues) {
		plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourWinterWeek, 168, dataObject.v_gridCapacityDelivery_groupcontract_kW), "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourWinterWeek, 168, dataObject.v_gridCapacityFeedin_groupcontract_kW), "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
	if (b_subdivideEnergyCoopFlows){
		List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.v_engineAgent).f_getAllChildMemberGridConnections();
		for (GridConnection GC : memberGridConnections) {
			plot_netload_week.addDataSet(GC.am_winterWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(uI_Results.energyModel.p_startHourWinterWeek), GC.p_gridConnectionID, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
		}
	}
}

//Set vertical scale
int maxValue = roundToInt(max(dataObject.v_dataNetLoadWinterWeek_kW.getYMax(), dataObject.v_gridCapacityDelivery_kW));
int minValue = roundToInt(min(dataObject.v_dataNetLoadWinterWeek_kW.getYMin(), -dataObject.v_gridCapacityFeedIn_kW));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

group_netload_week.setVisible(true);

/*ALCODEEND*/}

double f_addOtherEnergyCarriers_LiveDay1(AreaCollection dataObject)
{/*ALCODESTART::1741337336580*/
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeConsumptionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeProductionEnergyCarriers);

activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );


for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChartDay.addDataSet( dataObject.dsm_liveConsumption_kW.get(EC_consumption), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
	else if(dataObject.b_hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChartDay.addDataSet( dataObject.v_dataDistrictHeatConsumptionLiveWeek_kW, "Warmte import", uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
}


for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChartDay.addDataSet( dataObject.dsm_liveProduction_kW.get(EC_production), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));  
}

/*ALCODEEND*/}

double f_addElectricityFlows_LiveDay1(AreaCollection dataObject)
{/*ALCODESTART::1741337336582*/
group_day.setVisible(true);

if (dataObject.p_areaType == OL_GISObjectType.COOP && b_subdivideEnergyCoopFlows) {
	List<Color> colorSpectrum = new ArrayList<>();
	List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.v_engineAgent).f_getAllChildMemberGridConnections();
	
	for (int k = 0; k < memberGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, memberGridConnections.size()));
		energyDemandChartDay.addDataSet(memberGridConnections.get(k).dsm_liveDemand_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));
		energySupplyChartDay.addDataSet(memberGridConnections.get(k).dsm_liveSupply_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_gridConnectionID, colorSpectrum.get(k));	
	}
}
else{
	//Demand
	energyDemandChartDay.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW, "Elektr. behoefte basis", uI_Results.v_electricityBaseloadDemandColor);
	if(dataObject.b_hasElectricHeating){
	energyDemandChartDay.addDataSet(dataObject.v_dataElectricityForHeatConsumptionLiveWeek_kW, "Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if(dataObject.b_hasElectricTransport){
		energyDemandChartDay.addDataSet(dataObject.v_dataElectricityForTransportConsumptionLiveWeek_kW, "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if(dataObject.b_hasBattery){
		energyDemandChartDay.addDataSet(dataObject.v_dataElectricityForStorageConsumptionLiveWeek_kW, "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if (dataObject.v_dataElectricityForHydrogenConsumptionLiveWeek_kW != null && dataObject.b_hasElectrolyser) {
		energyDemandChartDay.addDataSet(dataObject.v_dataElectricityForHydrogenConsumptionLiveWeek_kW, "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.v_dataElectricityForCookingConsumptionLiveWeek_kW != null && dataObject.b_hasElectricCooking){
		energyDemandChartDay.addDataSet(dataObject.v_dataElectricityForCookingConsumptionLiveWeek_kW, "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Supply
	if(dataObject.b_hasWindturbine){
		energySupplyChartDay.addDataSet(dataObject.v_dataWindElectricityProductionLiveWeek_kW, "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if(dataObject.b_hasPV){
		energySupplyChartDay.addDataSet(dataObject.v_dataPVElectricityProductionLiveWeek_kW, "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if(dataObject.b_hasV2G){	
		energySupplyChartDay.addDataSet(dataObject.v_dataV2GElectricityProductionLiveWeek_kW, "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if(dataObject.b_hasBattery){
		energySupplyChartDay.addDataSet(dataObject.v_dataStorageElectricityProductionLiveWeek_kW, "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if(dataObject.b_hasCHP){
		energySupplyChartDay.addDataSet(dataObject.v_dataCHPElectricityProductionLiveWeek_kW, "Elektr. opwek WKK", uI_Results.v_CHPElectricitySupplyColor);
	}

}
/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_LiveDay1(AreaCollection dataObject)
{/*ALCODESTART::1741337336584*/
group_trafo_day.setVisible(true);

String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor = uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor = uI_Results.v_electricityCapacityColor_estimated;

if(dataObject.b_isRealDeliveryCapacityAvailable){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor = uI_Results.v_electricityCapacityColor_known;
}
if(dataObject.b_isRealFeedinCapacityAvailable){
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor = uI_Results.v_electricityCapacityColor_known;
}

plot_trafo_day.addDataSet(dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_day.addDataSet(dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW, feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_day.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

// TODO: Change these to only look at the day instead of the week?
int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionLiveWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW.getYMin()));
plot_trafo_day.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addElectricityNetLoad_LiveDay1(AreaCollection dataObject)
{/*ALCODESTART::1741337336586*/
String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor = uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor = uI_Results.v_electricityCapacityColor_estimated;

if(dataObject.b_isRealDeliveryCapacityAvailable){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor = uI_Results.v_electricityCapacityColor_known;
}
if(dataObject.b_isRealFeedinCapacityAvailable){
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor	= uI_Results.v_electricityCapacityColor_known;
}

//Add datasets to plot
plot_netload_day.addDataSet(dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_day.addDataSet(dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW, feedinCapacityLabel, feedinCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_day.addDataSet(dataObject.v_dataNetLoadLiveWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

//Specific coop plot additions
if (dataObject.p_areaType == OL_GISObjectType.COOP ) {

	if(uI_Results.b_showGroupContractValues) {
		//plot_netload_day.addDataSet(uI_Results.v_dataEHubDeliveryCapacityLiveWeek_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		//plot_netload_day.addDataSet(uI_Results.v_dataEHubFeedInCapacityLiveWeek_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
	if (b_subdivideEnergyCoopFlows) {
		List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.v_engineAgent).f_getAllChildMemberGridConnections();
		for (GridConnection GC : memberGridConnections) {
			plot_netload_day.addDataSet(GC.data_liveElectricityBalance_kW, GC.p_gridConnectionID, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
		}
	}
}

int maxValue = roundToInt(max(dataObject.v_dataNetLoadLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadLiveWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW.getYMin()));
plot_netload_day.setFixedVerticalScale(minValue + minValue * 0.3, maxValue + maxValue * 0.3);

group_netload_day.setVisible(true);
/*ALCODEEND*/}

