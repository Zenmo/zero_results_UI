double f_addOtherEnergyFlows_Year(AreaCollection dataObject)
{/*ALCODESTART::1714746057322*/
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeProductionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeConsumptionEnergyCarriers);

activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );

for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChartYear.addDataSet( dataObject.dsm_dailyAverageProductionDataSets_kW.get(EC_production), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production)); 
}

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	energyDemandChartYear.addDataSet( dataObject.dsm_dailyAverageConsumptionDataSets_kW.get(EC_consumption), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption)); 
}

/*ALCODEEND*/}

double f_addElectricityFlows_Year(AreaCollection dataObject)
{/*ALCODESTART::1714746057324*/
group_year.setVisible(true);

if (uI_Results.c_individualGridConnections.size() == 0) {	
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
else {
	List<Color> colorSpectrum = new ArrayList<>();
	for (int k = 0; k < uI_Results.c_individualGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, uI_Results.c_individualGridConnections.size()));
		AreaCollection AC = uI_Results.c_individualGridConnections.get(k);
		energyDemandChartYear.addDataSet(AC.dsm_dailyAverageConsumptionDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY), AC.p_name, colorSpectrum.get(k));
		energySupplyChartYear.addDataSet(AC.dsm_dailyAverageProductionDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY), AC.p_name, colorSpectrum.get(k));	
	}
}
/*ALCODEEND*/}

double f_addOtherEnergyCarriers_Live(AreaCollection dataObject)
{/*ALCODESTART::1714746057326*/
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

double f_addElectricityFlows_Live(AreaCollection dataObject)
{/*ALCODESTART::1714746057328*/
group_week.setVisible(true);

if (uI_Results.c_individualGridConnections.size() == 0) {
	
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
	if(dataObject.b_hasElectricCooking){
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
else {
	List<Color> colorSpectrum = new ArrayList<>();
	for (int k = 0; k < uI_Results.c_individualGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, uI_Results.c_individualGridConnections.size()));
		AreaCollection AC = uI_Results.c_individualGridConnections.get(k);
		energyDemandChart.addDataSet(AC.dsm_liveConsumption_kW.get(OL_EnergyCarriers.ELECTRICITY), AC.p_name, colorSpectrum.get(k));
		energySupplyChart.addDataSet(AC.dsm_liveProduction_kW.get(OL_EnergyCarriers.ELECTRICITY), AC.p_name, colorSpectrum.get(k));	
	}
}
/*ALCODEEND*/}

double f_addOtherEnergyFlows_SummerWeek(AreaCollection dataObject)
{/*ALCODESTART::1714897296534*/
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeProductionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeConsumptionEnergyCarriers);

activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );

for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChart.addDataSet( dataObject.dsm_summerWeekProductionDataSets_kW.get(EC_production), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));
}

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	energyDemandChart.addDataSet( dataObject.dsm_summerWeekConsumptionDataSets_kW.get(EC_consumption), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption));
}

/*ALCODEEND*/}

double f_addElectricityFlows_SummerWeek(AreaCollection dataObject)
{/*ALCODESTART::1714897296536*/
group_week.setVisible(true);

if (uI_Results.c_individualGridConnections.size() == 0) {	
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
else {
	List<Color> colorSpectrum = new ArrayList<>();
	for (int k = 0; k < uI_Results.c_individualGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, uI_Results.c_individualGridConnections.size()));
		AreaCollection AC = uI_Results.c_individualGridConnections.get(k);
		energyDemandChart.addDataSet(AC.dsm_summerWeekConsumptionDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY), AC.p_name, colorSpectrum.get(k));
		energySupplyChart.addDataSet(AC.dsm_summerWeekProductionDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY), AC.p_name, colorSpectrum.get(k));	
	}
}


/*ALCODEEND*/}

AreaCollection f_resetCharts()
{/*ALCODESTART::1714897512553*/
group_week.setVisible(false);
group_year.setVisible(false);
group_trafo_week.setVisible(false);
group_trafo_year.setVisible(false);
group_netload_week.setVisible(false);
group_netload_year.setVisible(false);
plot_trafo_week.removeAll();
plot_trafo_year.removeAll();
plot_netload_week.removeAll();
plot_netload_year.removeAll();
energyDemandChartYear.removeAll();
energySupplyChartYear.removeAll();
energyDemandChart.removeAll();
energySupplyChart.removeAll();
/*ALCODEEND*/}

double f_addOtherEnergyFlows_WinterWeek(AreaCollection dataObject)
{/*ALCODESTART::1714897923568*/
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeProductionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.v_activeConsumptionEnergyCarriers);

activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );

for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	energySupplyChart.addDataSet( dataObject.dsm_winterWeekProductionDataSets_kW.get(EC_production), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production)); 
}

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	energyDemandChart.addDataSet( dataObject.dsm_winterWeekConsumptionDataSets_kW.get(EC_consumption), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption));
}

/*ALCODEEND*/}

double f_addElectricityFlows_WinterWeek(AreaCollection dataObject)
{/*ALCODESTART::1714897923570*/
group_week.setVisible(true);

if (uI_Results.c_individualGridConnections.size() == 0) {	
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
else {
	List<Color> colorSpectrum = new ArrayList<>();
	for (int k = 0; k < uI_Results.c_individualGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, uI_Results.c_individualGridConnections.size()));
		AreaCollection AC = uI_Results.c_individualGridConnections.get(k);
		energyDemandChart.addDataSet(AC.dsm_winterWeekConsumptionDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY), AC.p_name, colorSpectrum.get(k));
		energySupplyChart.addDataSet(AC.dsm_winterWeekProductionDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY), AC.p_name, colorSpectrum.get(k));	
	}
}
/*ALCODEEND*/}

double f_setCharts()
{/*ALCODESTART::1714899014782*/
f_resetCharts();
AreaCollection dataObject = uI_Results.f_getDataObject();

if (radio_energyType.getValue() == 2) { // Line Plot (Net Load)
	switch (radio_period.getValue()) {
		case 0: // Live
			if (uI_Results.v_selectedObjectType == OL_GISObjectType.GRIDNODE) {
				f_addElectricityFlowsTrafo_Live(dataObject);
			}
			else {
				f_addElectricityNetLoad_Live(dataObject);
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
				f_addElectricityFlowsTrafo_Live(dataObject);
			}
			else {
				f_addElectricityFlows_Live(dataObject);
				if( radio_energyType.getValue() == 1){
					f_addOtherEnergyCarriers_Live(dataObject);
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
plot_trafo_year.addDataSet(dataObject.data_gridCapacityDeliveryYear_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_year.addDataSet(dataObject.data_gridCapacityFeedInYear_kW, feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_year.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionYear_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 0.25, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionYear_kW.getYMax(), dataObject.data_gridCapacityDeliveryYear_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionYear_kW.getYMin(), dataObject.data_gridCapacityFeedInYear_kW.getYMin()));
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
plot_trafo_week.addDataSet(dataObject.data_gridCapacityDeliverySummerWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(dataObject.data_gridCapacityFeedInSummerWeek_kW, feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionSummerWeek_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionSummerWeek_kW.getYMax(), dataObject.data_gridCapacityDeliverySummerWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionSummerWeek_kW.getYMin(), dataObject.data_gridCapacityFeedInSummerWeek_kW.getYMin()));
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
plot_trafo_week.addDataSet(dataObject.data_gridCapacityDeliveryWinterWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(dataObject.data_gridCapacityFeedInWinterWeek_kW, feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(dataObject.v_dataElectricityBaseloadConsumptionWinterWeek_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);

int maxValue = roundToInt(max(dataObject.v_dataElectricityBaseloadConsumptionWinterWeek_kW.getYMax(), dataObject.data_gridCapacityDeliveryWinterWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataElectricityBaseloadConsumptionWinterWeek_kW.getYMin(), dataObject.data_gridCapacityFeedInWinterWeek_kW.getYMin()));
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

group_netload_week.setVisible(true);

if (uI_Results.b_EHubConfiguration && uI_Results.c_individualGridConnections.size() > 0) {
	plot_netload_week.addDataSet(dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW, "Cumulatieve GTV afname van bedrijven", uI_Results.v_cumulativeGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_netload_week.addDataSet(dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW, "Cumulatieve GTV afname van bedrijven", uI_Results.v_cumulativeGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_netload_week.addDataSet(uI_Results.v_dataEHubDeliveryCapacityLiveWeek_kW, "Groeps GTV afname (Rekenmethode Stedin)", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_netload_week.addDataSet(uI_Results.v_dataEHubFeedInCapacityLiveWeek_kW, "Groeps GTV teruglevering (Rekenmethode Stedin)", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
}
else {
	plot_netload_week.addDataSet(dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_netload_week.addDataSet(dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW, feedinCapacityLabel, feedinCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
}

plot_netload_week.addDataSet(dataObject.v_dataNetLoadLiveWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

for (AreaCollection AC : uI_Results.c_individualGridConnections) {
	plot_netload_week.addDataSet(AC.v_dataNetLoadLiveWeek_kW, AC.p_name, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
}

int maxValue = roundToInt(max(dataObject.v_dataNetLoadLiveWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityLiveWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadLiveWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacityLiveWeek_kW.getYMin()));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.3, maxValue + maxValue * 0.3);

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

group_netload_week.setVisible(true);

if (uI_Results.b_EHubConfiguration && uI_Results.c_individualGridConnections.size() > 0) {
	plot_netload_week.addDataSet(dataObject.v_dataElectricityDeliveryCapacitySummerWeek_kW, "Cumulatieve GTV afname van bedrijven", uI_Results.v_cumulativeGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_netload_week.addDataSet(dataObject.v_dataElectricityFeedInCapacitySummerWeek_kW, "Cumulatieve GTV afname van bedrijven", uI_Results.v_cumulativeGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_netload_week.addDataSet(uI_Results.v_dataEHubDeliveryCapacitySummerWeek_kW, "Groeps GTV afname (Rekenmethode Stedin)", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_netload_week.addDataSet(uI_Results.v_dataEHubFeedInCapacitySummerWeek_kW, "Groeps GTV teruglevering (Rekenmethode Stedin)", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
}
else {
	plot_netload_week.addDataSet(dataObject.v_dataElectricityDeliveryCapacitySummerWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_netload_week.addDataSet(dataObject.v_dataElectricityFeedInCapacitySummerWeek_kW, feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
}

plot_netload_week.addDataSet(dataObject.v_dataNetLoadSummerWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

for (AreaCollection AC : uI_Results.c_individualGridConnections) {
	plot_netload_week.addDataSet(AC.v_dataNetLoadSummerWeek_kW, AC.p_name, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
}

int maxValue = roundToInt(max(dataObject.v_dataNetLoadSummerWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacitySummerWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadSummerWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacitySummerWeek_kW.getYMin()));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

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

group_netload_week.setVisible(true);

if (uI_Results.b_EHubConfiguration && uI_Results.c_individualGridConnections.size() > 0) {
	plot_netload_week.addDataSet(dataObject.v_dataElectricityDeliveryCapacityWinterWeek_kW, "Cumulatieve GTV afname van bedrijven", uI_Results.v_cumulativeGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_netload_week.addDataSet(dataObject.v_dataElectricityFeedInCapacityWinterWeek_kW, "Cumulatieve GTV afname van bedrijven", uI_Results.v_cumulativeGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_netload_week.addDataSet(uI_Results.v_dataEHubDeliveryCapacityWinterWeek_kW, "Groeps GTV afname (Rekenmethode Stedin)", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_netload_week.addDataSet(uI_Results.v_dataEHubFeedInCapacityWinterWeek_kW, "Groeps GTV teruglevering (Rekenmethode Stedin)", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
}
else {
	plot_netload_week.addDataSet(dataObject.v_dataElectricityDeliveryCapacityWinterWeek_kW, deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_netload_week.addDataSet(dataObject.v_dataElectricityFeedInCapacityWinterWeek_kW, feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);

}

plot_netload_week.addDataSet(dataObject.v_dataNetLoadWinterWeek_kW, "Netto vermogen", uI_Results.v_electricityDemandColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 4.0, Chart.PointStyle.POINT_NONE);

for (AreaCollection AC : uI_Results.c_individualGridConnections) {
	plot_netload_week.addDataSet(AC.v_dataNetLoadWinterWeek_kW, AC.p_name, blue, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
}

int maxValue = roundToInt(max(dataObject.v_dataNetLoadWinterWeek_kW.getYMax(), dataObject.v_dataElectricityDeliveryCapacityWinterWeek_kW.getYMax()));
int minValue = roundToInt(min(dataObject.v_dataNetLoadWinterWeek_kW.getYMin(), dataObject.v_dataElectricityFeedInCapacityWinterWeek_kW.getYMin()));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);

/*ALCODEEND*/}

