double f_addOtherEnergyFlows_Year(I_EnergyData dataObject)
{/*ALCODESTART::1714746057322*/
double startTime_h = 0;

EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.getRapidRunData().activeProductionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.getRapidRunData().activeConsumptionEnergyCarriers);

activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChartYear.addDataSet( dataObject.getRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(EC_consumption).getDataSet(startTime_h), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
	else if(dataObject.getRapidRunData().assetsMetaData.hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChartYear.addDataSet( dataObject.getRapidRunData().acc_dailyAverageDistrictHeatingConsumption_kW.getDataSet(startTime_h), "Warmte net", uI_Results.cm_consumptionColors.get(EC_consumption)); 
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

if (uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYCOOP && b_subdivideEnergyCoopFlows) {
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
	if (dataObject.getRapidRunData().assetsMetaData.hasElectricHeating) {
		energyDemandChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageHeatPumpElectricityConsumption_kW.getDataSet(startTime_h),"Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if (dataObject.getRapidRunData().assetsMetaData.hasElectricTransport) { 
		energyDemandChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageElectricVehicleConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if (dataObject.getRapidRunData().assetsMetaData.hasBattery) {
		energyDemandChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageBatteriesConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if (dataObject.getRapidRunData().assetsMetaData.hasElectrolyser) {
		energyDemandChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageElectrolyserElectricityConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.getRapidRunData().assetsMetaData.hasElectricCooking){
		energyDemandChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageElectricCookingConsumption_kW.getDataSet(startTime_h), "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Energy supply chart
	if (dataObject.getRapidRunData().assetsMetaData.hasWindturbine) {
	energySupplyChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageWindProduction_kW.getDataSet(startTime_h), "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if (dataObject.getRapidRunData().assetsMetaData.hasPV) {
		energySupplyChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAveragePVProduction_kW.getDataSet(startTime_h), "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if (dataObject.getRapidRunData().assetsMetaData.hasBattery) {
		energySupplyChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageBatteriesProduction_kW.getDataSet(startTime_h), "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if (dataObject.getRapidRunData().assetsMetaData.hasV2G) {
		energySupplyChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageV2GProduction_kW.getDataSet(startTime_h), "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if (dataObject.getRapidRunData().assetsMetaData.hasCHP) {
		energySupplyChartYear.addDataSet(dataObject.getRapidRunData().acc_dailyAverageCHPElectricityProduction_kW.getDataSet(startTime_h), "Elektr. Opwek WKK", uI_Results.v_CHPElectricitySupplyColor);
	}
}
/*ALCODEEND*/}

double f_addOtherEnergyCarriers_Live(I_EnergyData dataObject)
{/*ALCODESTART::1714746057326*/
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.getLiveData().activeConsumptionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.getLiveData().activeProductionEnergyCarriers);

activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );


for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChart.addDataSet( dataObject.getLiveData().dsm_liveDemand_kW.get(EC_consumption), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
	else if(dataObject.getLiveData().assetsMetaData.hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChart.addDataSet( dataObject.getLiveData().data_districtHeatDelivery_kW, "Warmte net", uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
}


for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChart.addDataSet( dataObject.getLiveData().dsm_liveSupply_kW.get(EC_production), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));  
}

/*ALCODEEND*/}

double f_addElectricityFlows_Live(I_EnergyData dataObject)
{/*ALCODESTART::1714746057328*/
group_week.setVisible(true);

if (uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYCOOP && b_subdivideEnergyCoopFlows) {
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
	if(dataObject.getLiveData().assetsMetaData.hasElectricHeating){
		energyDemandChart.addDataSet(dataObject.getLiveData().data_heatPumpElectricityDemand_kW, "Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if(dataObject.getLiveData().assetsMetaData.hasElectricTransport){
		energyDemandChart.addDataSet(dataObject.getLiveData().data_electricVehicleDemand_kW, "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if(dataObject.getLiveData().assetsMetaData.hasBattery){
		energyDemandChart.addDataSet(dataObject.getLiveData().data_batteryCharging_kW, "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if (dataObject.getLiveData().data_hydrogenElectricityDemand_kW != null && dataObject.getLiveData().assetsMetaData.hasElectrolyser) {
		energyDemandChart.addDataSet(dataObject.getLiveData().data_hydrogenElectricityDemand_kW, "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.getLiveData().data_cookingElectricityDemand_kW != null && dataObject.getLiveData().assetsMetaData.hasElectricCooking){
		energyDemandChart.addDataSet(dataObject.getLiveData().data_cookingElectricityDemand_kW, "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Supply
	if(dataObject.getLiveData().assetsMetaData.hasWindturbine){
		energySupplyChart.addDataSet(dataObject.getLiveData().data_windGeneration_kW, "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if(dataObject.getLiveData().assetsMetaData.hasPV){
		energySupplyChart.addDataSet(dataObject.getLiveData().data_PVGeneration_kW, "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if(dataObject.getLiveData().assetsMetaData.hasV2G){	
		energySupplyChart.addDataSet(dataObject.getLiveData().data_V2GSupply_kW, "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if(dataObject.getLiveData().assetsMetaData.hasBattery){
		energySupplyChart.addDataSet(dataObject.getLiveData().data_batteryDischarging_kW, "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if(dataObject.getLiveData().assetsMetaData.hasCHP){
		energySupplyChart.addDataSet(dataObject.getLiveData().data_CHPElectricityProductionLiveWeek_kW, "Elektr. opwek WKK", uI_Results.v_CHPElectricitySupplyColor);
	}
}
/*ALCODEEND*/}

double f_addOtherEnergyFlows_SummerWeek(I_EnergyData dataObject)
{/*ALCODESTART::1714897296534*/
double startTime_h = uI_Results.energyModel.p_startHourSummerWeek - uI_Results.energyModel.p_runStartTime_h;

EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.getRapidRunData().activeProductionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.getRapidRunData().activeConsumptionEnergyCarriers);

activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChart.addDataSet( dataObject.getRapidRunData().am_summerWeekConsumptionAccumulators_kW.get(EC_consumption).getDataSet(startTime_h), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption));
	}
	else if(dataObject.getRapidRunData().assetsMetaData.hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChart.addDataSet( dataObject.getRapidRunData().acc_summerWeekDistrictHeatingConsumption_kW.getDataSet(startTime_h), "Warmte net", uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
}

for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChart.addDataSet( dataObject.getRapidRunData().am_summerWeekProductionAccumulators_kW.get(EC_production).getDataSet(startTime_h), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));
}
/*ALCODEEND*/}

double f_addElectricityFlows_SummerWeek(I_EnergyData dataObject)
{/*ALCODESTART::1714897296536*/
group_week.setVisible(true);

double startTime_h = uI_Results.energyModel.p_startHourSummerWeek - uI_Results.energyModel.p_runStartTime_h;

if (uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYCOOP && b_subdivideEnergyCoopFlows) {
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
	if(dataObject.getRapidRunData().assetsMetaData.hasElectricHeating){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekHeatPumpElectricityConsumption_kW.getDataSet(startTime_h),"Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if(dataObject.getRapidRunData().assetsMetaData.hasElectricTransport){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekElectricVehicleConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if(dataObject.getRapidRunData().assetsMetaData.hasBattery){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekBatteriesConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if(dataObject.getRapidRunData().assetsMetaData.hasElectrolyser){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekElectrolyserElectricityConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.getRapidRunData().assetsMetaData.hasElectricCooking){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekElectricCookingConsumption_kW.getDataSet(startTime_h), "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Energy supply chart
	if(dataObject.getRapidRunData().assetsMetaData.hasWindturbine){
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekWindProduction_kW.getDataSet(startTime_h), "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if(dataObject.getRapidRunData().assetsMetaData.hasPV){
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekPVProduction_kW.getDataSet(startTime_h), "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if(dataObject.getRapidRunData().assetsMetaData.hasBattery){
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekBatteriesProduction_kW.getDataSet(startTime_h), "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if(dataObject.getRapidRunData().assetsMetaData.hasV2G){
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_summerWeekV2GProduction_kW.getDataSet(startTime_h), "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if(dataObject.getRapidRunData().assetsMetaData.hasCHP){
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
double startTime_h = uI_Results.energyModel.p_startHourWinterWeek - uI_Results.energyModel.p_runStartTime_h;

EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.getRapidRunData().activeProductionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.getRapidRunData().activeConsumptionEnergyCarriers);

activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChart.addDataSet( dataObject.getRapidRunData().am_winterWeekConsumptionAccumulators_kW.get(EC_consumption).getDataSet(startTime_h), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption));
	}
	else if(dataObject.getRapidRunData().assetsMetaData.hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChart.addDataSet( dataObject.getRapidRunData().acc_winterWeekDistrictHeatingConsumption_kW.getDataSet(startTime_h), "Warmte net", uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
}

for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChart.addDataSet( dataObject.getRapidRunData().am_winterWeekProductionAccumulators_kW.get(EC_production).getDataSet(startTime_h), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production)); 
}

/*ALCODEEND*/}

double f_addElectricityFlows_WinterWeek(I_EnergyData dataObject)
{/*ALCODESTART::1714897923570*/
group_week.setVisible(true);

double startTime_h = uI_Results.energyModel.p_startHourWinterWeek - uI_Results.energyModel.p_runStartTime_h;

if (uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYCOOP && b_subdivideEnergyCoopFlows) {
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
	if(dataObject.getRapidRunData().assetsMetaData.hasElectricHeating){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekHeatPumpElectricityConsumption_kW.getDataSet(startTime_h),"Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if(dataObject.getRapidRunData().assetsMetaData.hasElectricTransport){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekElectricVehicleConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if(dataObject.getRapidRunData().assetsMetaData.hasBattery){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekBatteriesConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if (dataObject.getRapidRunData().assetsMetaData.hasElectrolyser) {
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekElectrolyserElectricityConsumption_kW.getDataSet(startTime_h), "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.getRapidRunData().assetsMetaData.hasElectricCooking){
		energyDemandChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekElectricCookingConsumption_kW.getDataSet(startTime_h), "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Energy supply chart
	if(dataObject.getRapidRunData().assetsMetaData.hasWindturbine){
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekWindProduction_kW.getDataSet(startTime_h), "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if(dataObject.getRapidRunData().assetsMetaData.hasPV){
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekPVProduction_kW.getDataSet(startTime_h), "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if(dataObject.getRapidRunData().assetsMetaData.hasBattery){	
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekBatteriesProduction_kW.getDataSet(startTime_h), "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if(dataObject.getRapidRunData().assetsMetaData.hasV2G){	
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekV2GProduction_kW.getDataSet(startTime_h), "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if(dataObject.getRapidRunData().assetsMetaData.hasCHP){	
		energySupplyChart.addDataSet(dataObject.getRapidRunData().acc_winterWeekCHPElectricityProduction_kW.getDataSet(startTime_h), "Elektr. Opwek WKK", uI_Results.v_CHPElectricitySupplyColor);
	}
}
/*ALCODEEND*/}

double f_setCharts()
{/*ALCODESTART::1714899014782*/
f_resetCharts();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 115, true);

if (radio_period.getValue() == 0) {
	radio_periodLive.setVisible(true);
}
I_EnergyData dataObject = uI_Results.f_getSelectedObjectData();

if (radio_energyType.getValue() == 2) { // Line Plot (Net Load)
	switch (radio_period.getValue()) {
		case 0: // Live
			if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
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
			if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
				f_addElectricityFlowsTrafo_SummerWeek(uI_Results.v_gridNode);
			}
			else {
				f_addElectricityNetLoad_SummerWeek(dataObject);
			}
			break;
		
		case 2: // Winter
			if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
				f_addElectricityFlowsTrafo_WinterWeek(uI_Results.v_gridNode);
			}
			else {
				f_addElectricityNetLoad_WinterWeek(dataObject);
			}
			break;
		
		case 3: // Year
			if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
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
			if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
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
			if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
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
			if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
				f_addElectricityFlowsTrafo_WinterWeek(uI_Results.v_gridNode);
			} else {
				f_addElectricityFlows_WinterWeek(dataObject);
				if( radio_energyType.getValue() == 1){
					f_addOtherEnergyFlows_WinterWeek(dataObject);
				}
			}
			break;
			
		case 3: // Year
			if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
				f_addElectricityFlowsTrafo_Year(uI_Results.v_gridNode);
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

double f_addElectricityFlowsTrafo_Live(GridNode GN)
{/*ALCODESTART::1715169261226*/
String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

if(GN.p_realCapacityAvailable){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}

group_trafo_week.setVisible(true);
plot_trafo_week.addDataSet(GN.data_liveCapacityDemand_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(GN.data_liveCapacitySupply_kW, feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(GN.data_liveLoad_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

/*
int maxValue = roundToInt(max(GN.data_liveLoad_kW.getYMax(), GN.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(GN.data_liveLoad_kW.getYMin(), GN.v_dataElectricityFeedInCapacityLiveWeek_kW.getYMin()));
plot_trafo_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);
*/
/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_Year(GridNode GN)
{/*ALCODESTART::1715169641896*/
String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

if(GN.p_realCapacityAvailable){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}

group_trafo_year.setVisible(true);
plot_trafo_year.addDataSet(uI_Results.f_createFlatDataset(0, 8760, GN.p_capacity_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_year.addDataSet(uI_Results.f_createFlatDataset(0, 8760, -GN.p_capacity_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_year.addDataSet(GN.data_totalLoad_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 0.25, Chart.PointStyle.POINT_NONE);

/*
int maxValue = roundToInt(max(GN.v_dataElectricityBaseloadConsumptionYear_kW.getYMax(), GN.p_capacity_kW));
int minValue = roundToInt(min(GN.v_dataElectricityBaseloadConsumptionYear_kW.getYMin(), -GN.p_capacity_kW));
plot_trafo_year.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);
*/
/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_SummerWeek(GridNode GN)
{/*ALCODESTART::1715171161720*/
String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

if(GN.p_realCapacityAvailable){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}

group_trafo_week.setVisible(true);

//Add datasets to plot
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourSummerWeek, 168, GN.p_capacity_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourSummerWeek, 168, -GN.p_capacity_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(GN.data_summerWeekLoad_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

/*
int maxValue = roundToInt(max(GN.v_dataElectricityBaseloadConsumptionSummerWeek_kW.getYMax(), GN.p_capacity_kW));
int minValue = roundToInt(min(GN.v_dataElectricityBaseloadConsumptionSummerWeek_kW.getYMin(), -GN.p_capacity_kW));
plot_trafo_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);
*/
/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_WinterWeek(GridNode GN)
{/*ALCODESTART::1715171298729*/
String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

if(GN.p_realCapacityAvailable){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
}

group_trafo_week.setVisible(true);
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourWinterWeek, 168, GN.p_capacity_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(uI_Results.energyModel.p_startHourWinterWeek, 168, -GN.p_capacity_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(GN.data_winterWeekLoad_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

/*
int maxValue = roundToInt(max(GN.v_dataElectricityBaseloadConsumptionWinterWeek_kW.getYMax(), GN.p_capacity_kW));
int minValue = roundToInt(min(GN.v_dataElectricityBaseloadConsumptionWinterWeek_kW.getYMin(), -GN.p_capacity_kW));
plot_trafo_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);
*/
/*ALCODEEND*/}

double f_addElectricityNetLoad_Live(I_EnergyData dataObject)
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

//Add datasets to plot
plot_netload_week.addDataSet(dataObject.getLiveData().data_gridCapacityDemand_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.getLiveData().data_gridCapacitySupply_kW, feedinCapacityLabel, feedinCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.getLiveData().data_liveElectricityBalance_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

//Specific coop plot additions
if (dataObject.getScope() == OL_ResultScope.ENERGYCOOP ) {

	if(uI_Results.b_showGroupContractValues) {
		//plot_netload_week.addDataSet(uI_Results.v_dataEHubDeliveryCapacityLiveWeek_kW, "Groeps GTV afname (Rekenmethode Stedin)", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		//plot_netload_week.addDataSet(uI_Results.v_dataEHubFeedInCapacityLiveWeek_kW, "Groeps GTV teruglevering (Rekenmethode Stedin)", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
	if (b_subdivideEnergyCoopFlows) {
		List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.getLiveData().parentAgent).f_getAllChildMemberGridConnections();
		for (GridConnection GC : memberGridConnections) {
			plot_netload_week.addDataSet(GC.getLiveData().data_liveElectricityBalance_kW, GC.p_gridConnectionID, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
		}
	}
}

/*
int maxValue = roundToInt(max(dataObject.v_dataNetLoadLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadLiveWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW.getYMin()));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.3, maxValue + maxValue * 0.3);
*/
group_netload_week.setVisible(true);
/*ALCODEEND*/}

double f_addElectricityNetLoad_Year(I_EnergyData dataObject)
{/*ALCODESTART::1736430560714*/
group_netload_year.setVisible(true);
plot_netload_year.addDataSet(dataObject.v_dataElectricityDemandCapacityLiveWeek_kW, "Piek leveringscapaciteit", uI_Results.v_electricityCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_year.addDataSet(dataObject.v_dataElectricitySupplyCapacityLiveWeek_kW, "Piek terugleveringscapaciteit", uI_Results.v_electricityCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_year.addDataSet(dataObject.v_dataNetLoadLiveWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataNetLoadLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDemandCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadLiveWeek_kW.getYMin(), dataObject.v_dataElectricitySupplyCapacityLiveWeek_kW.getYMin()));
plot_netload_year.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

double f_addElectricityNetLoad_SummerWeek(I_EnergyData dataObject)
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

//Add datasets to plot
double startTime_h = uI_Results.energyModel.p_startHourSummerWeek - uI_Results.energyModel.p_runStartTime_h;
plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_summerWeekDeliveryCapacity_kW.getDataSet(startTime_h), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_summerWeekFeedinCapacity_kW.getDataSet(startTime_h), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.getRapidRunData().am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(startTime_h), "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

//Specific coop plot additions
if (dataObject.getScope() == OL_ResultScope.ENERGYCOOP ) {

	if(uI_Results.b_showGroupContractValues) {
		plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(startTime_h, 168, ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getGroupContractDeliveryCapacity_kW()), "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(startTime_h, 168, ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getGroupContractFeedinCapacity_kW()), "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
	if (b_subdivideEnergyCoopFlows) {
		List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getAllChildMemberGridConnections();
		for (GridConnection GC : memberGridConnections) {
			plot_netload_week.addDataSet(GC.v_rapidRunData.am_summerWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(uI_Results.energyModel.p_startHourSummerWeek), GC.p_gridConnectionID, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
		}
	}
}

/*
//Set vertical scale
int maxValue = roundToInt(max(dataObject.v_dataNetLoadSummerWeek_kW.getYMax(), dataObject.v_gridCapacityDelivery_kW));
int minValue = roundToInt(min(dataObject.v_dataNetLoadSummerWeek_kW.getYMin(), -dataObject.v_gridCapacityFeedIn_kW));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);
*/
group_netload_week.setVisible(true);

/*ALCODEEND*/}

double f_addElectricityNetLoad_WinterWeek(I_EnergyData dataObject)
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

//Add datasets to plot
double startTime_h = uI_Results.energyModel.p_startHourWinterWeek - uI_Results.energyModel.p_runStartTime_h;
plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_winterWeekDeliveryCapacity_kW.getDataSet(startTime_h), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.getRapidRunData().acc_winterWeekFeedinCapacity_kW.getDataSet(startTime_h), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_week.addDataSet(dataObject.getRapidRunData().am_winterWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(startTime_h), "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

//Specific coop plot additions
if (dataObject.getScope() == OL_ResultScope.ENERGYCOOP ) {

	if(uI_Results.b_showGroupContractValues) {
		plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(startTime_h, 168, ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getGroupContractDeliveryCapacity_kW()), "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		plot_netload_week.addDataSet(uI_Results.f_createFlatDataset(startTime_h, 168, ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getGroupContractFeedinCapacity_kW()), "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
	if (b_subdivideEnergyCoopFlows){
		List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getAllChildMemberGridConnections();
		for (GridConnection GC : memberGridConnections) {
			plot_netload_week.addDataSet(GC.v_rapidRunData.am_winterWeekBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(uI_Results.energyModel.p_startHourWinterWeek), GC.p_gridConnectionID, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
		}
	}
}

/*
//Set vertical scale
int maxValue = roundToInt(max(dataObject.v_dataNetLoadWinterWeek_kW.getYMax(), dataObject.v_gridCapacityDelivery_kW));
int minValue = roundToInt(min(dataObject.v_dataNetLoadWinterWeek_kW.getYMin(), -dataObject.v_gridCapacityFeedIn_kW));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);
*/
group_netload_week.setVisible(true);

/*ALCODEEND*/}

double f_addOtherEnergyCarriers_LiveDay(I_EnergyData dataObject)
{/*ALCODESTART::1739804290044*/
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.getLiveData().activeConsumptionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.getLiveData().activeProductionEnergyCarriers);

activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );


for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChartDay.addDataSet( dataObject.getLiveData().dsm_liveDemand_kW.get(EC_consumption), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
	else if(dataObject.getLiveData().assetsMetaData.hasHeatGridConnection){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChartDay.addDataSet( dataObject.getLiveData().data_districtHeatDelivery_kW, "Warmte net", uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
}


for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChartDay.addDataSet( dataObject.getLiveData().dsm_liveSupply_kW.get(EC_production), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));  
}

/*ALCODEEND*/}

double f_addElectricityFlows_LiveDay(I_EnergyData dataObject)
{/*ALCODESTART::1739804290048*/
group_day.setVisible(true);

if (uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYCOOP && b_subdivideEnergyCoopFlows) {
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
	if(dataObject.getLiveData().assetsMetaData.hasElectricHeating){
	energyDemandChartDay.addDataSet(dataObject.getLiveData().data_heatPumpElectricityDemand_kW, "Elektr. behoefte warmte", uI_Results.v_electricityForHeatDemandColor);
	}
	if(dataObject.getLiveData().assetsMetaData.hasElectricTransport){
		energyDemandChartDay.addDataSet(dataObject.getLiveData().data_electricVehicleDemand_kW, "Elektr. behoefte transport", uI_Results.v_electricityForTransportDemandColor);
	}
	if(dataObject.getLiveData().assetsMetaData.hasBattery){
		energyDemandChartDay.addDataSet(dataObject.getLiveData().data_batteryCharging_kW, "Elektr. behoefte batterijen", uI_Results.v_electricityForStorageDemandColor);
	}
	if (dataObject.getLiveData().data_hydrogenElectricityDemand_kW != null && dataObject.getLiveData().assetsMetaData.hasElectrolyser) {
		energyDemandChartDay.addDataSet(dataObject.getLiveData().data_hydrogenElectricityDemand_kW, "Elektr. behoefte elektrolysers", uI_Results.v_electricityForHydrogenDemandColor);
	}
	if (dataObject.getLiveData().data_cookingElectricityDemand_kW != null && dataObject.getLiveData().assetsMetaData.hasElectricCooking){
		energyDemandChartDay.addDataSet(dataObject.getLiveData().data_cookingElectricityDemand_kW, "Elektr. cooking", uI_Results.v_electricityForCookingDemandColor);
	}
	
	//Supply
	if(dataObject.getLiveData().assetsMetaData.hasWindturbine){
		energySupplyChartDay.addDataSet(dataObject.getLiveData().data_windGeneration_kW, "Opwek wind", uI_Results.v_windElectricitySupplyColor);
	}
	if(dataObject.getLiveData().assetsMetaData.hasPV){
		energySupplyChartDay.addDataSet(dataObject.getLiveData().data_PVGeneration_kW, "Opwek zonne-pv", uI_Results.v_PVElectricitySupplyColor);
	}
	if(dataObject.getLiveData().assetsMetaData.hasV2G){	
		energySupplyChartDay.addDataSet(dataObject.getLiveData().data_V2GSupply_kW, "Teruglevering V2G", uI_Results.v_V2GElectricitySupplyColor);
	}
	if(dataObject.getLiveData().assetsMetaData.hasBattery){
		energySupplyChartDay.addDataSet(dataObject.getLiveData().data_batteryDischarging_kW, "Teruglevering batterijen", uI_Results.v_storageElectricitySupplyColor);
	}
	if(dataObject.getLiveData().assetsMetaData.hasCHP){
		energySupplyChartDay.addDataSet(dataObject.getLiveData().data_CHPElectricityProductionLiveWeek_kW, "Elektr. opwek WKK", uI_Results.v_CHPElectricitySupplyColor);
	}

}
/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_LiveDay(GridNode GN)
{/*ALCODESTART::1739804290051*/
group_trafo_day.setVisible(true);

String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor = uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor = uI_Results.v_electricityCapacityColor_estimated;

if(GN.p_realCapacityAvailable){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor = uI_Results.v_electricityCapacityColor_known;
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor = uI_Results.v_electricityCapacityColor_known;
}

plot_trafo_day.addDataSet(GN.data_liveCapacityDemand_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_day.addDataSet(GN.data_liveCapacitySupply_kW, feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_day.addDataSet(GN.data_liveLoad_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

/*
// TODO: Change these to only look at the day instead of the week?
int maxValue = roundToInt(max(GN.v_dataElectricityBaseloadConsumptionLiveWeek_kW.getYMax(), GN.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(GN.v_dataElectricityBaseloadConsumptionLiveWeek_kW.getYMin(), GN.v_dataElectricityFeedInCapacityLiveWeek_kW.getYMin()));
plot_trafo_day.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);
*/
/*ALCODEEND*/}

double f_addElectricityNetLoad_LiveDay(I_EnergyData dataObject)
{/*ALCODESTART::1739804290055*/
String deliveryCapacityLabel = "Geschatte piek leveringscapaciteit";
String feedinCapacityLabel = "Geschatte piek terugleveringscapaciteit";
Color  deliveryCapacityColor = uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor = uI_Results.v_electricityCapacityColor_estimated;

if(dataObject.getLiveData().connectionMetaData.contractedDeliveryCapacityKnown){
	deliveryCapacityLabel = "Piek leveringscapaciteit";
	deliveryCapacityColor = uI_Results.v_electricityCapacityColor_known;
}
if(dataObject.getLiveData().connectionMetaData.contractedFeedinCapacityKnown){
	feedinCapacityLabel = "Piek terugleveringscapaciteit";
	feedinCapacityColor	= uI_Results.v_electricityCapacityColor_known;
}

//Add datasets to plot
plot_netload_day.addDataSet(dataObject.getLiveData().data_gridCapacityDemand_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_day.addDataSet(dataObject.getLiveData().data_gridCapacitySupply_kW, feedinCapacityLabel, feedinCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_netload_day.addDataSet(dataObject.getLiveData().data_liveElectricityBalance_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

//Specific coop plot additions
if (dataObject.getScope() == OL_ResultScope.ENERGYCOOP ) {

	if(uI_Results.b_showGroupContractValues) {
		//plot_netload_day.addDataSet(uI_Results.v_dataEHubDeliveryCapacityLiveWeek_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
		//plot_netload_day.addDataSet(uI_Results.v_dataEHubFeedInCapacityLiveWeek_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	}
	if (b_subdivideEnergyCoopFlows) {
		List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.getLiveData().parentAgent).f_getAllChildMemberGridConnections();
		for (GridConnection GC : memberGridConnections) {
			plot_netload_day.addDataSet(GC.getLiveData().data_liveElectricityBalance_kW, GC.p_gridConnectionID, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
		}
	}
}

/*
int maxValue = roundToInt(max(dataObject.v_dataNetLoadLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadLiveWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW.getYMin()));
plot_netload_day.setFixedVerticalScale(minValue + minValue * 0.3, maxValue + maxValue * 0.3);
*/
group_netload_day.setVisible(true);
/*ALCODEEND*/}

