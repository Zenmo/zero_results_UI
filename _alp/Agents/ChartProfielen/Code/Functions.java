double f_addOtherEnergyFlows_Year(I_EnergyData dataObject)
{/*ALCODESTART::1714746057322*/
double startTime_h = 0;

EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.getRapidRunData().activeProductionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.getRapidRunData().activeConsumptionEnergyCarriers);

activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChartYear.addDataSet( dataObject.getRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(EC_consumption).getDataSet(startTime_h, 24.0), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
	else if(dataObject.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.districtHeatDelivery_kW)){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChartYear.addDataSet( dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.districtHeatDelivery_kW).getDataSet(startTime_h, 24.0), "Warmte net", uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
}

for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	if(EC_production != OL_EnergyCarriers.HEAT){
		energySupplyChartYear.addDataSet( dataObject.getRapidRunData().am_dailyAverageProductionAccumulators_kW.get(EC_production).getDataSet(startTime_h, 24.0), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production)); 
	}
	else if(dataObject.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.ptProductionHeat_kW)){
		energySupplyChartYear.addDataSet( dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.ptProductionHeat_kW).getDataSet(startTime_h, 24.0), "PT", uI_Results.cm_productionColors.get(EC_production)); 
	}
}

/*ALCODEEND*/}

double f_addElectricityFlows_Year(I_EnergyData dataObject)
{/*ALCODESTART::1714746057324*/
gr_year.setVisible(true);

double startTime_h = 0;

if (uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYCOOP && b_subdivideEnergyCoopFlows) {
	List<Color> colorSpectrum = new ArrayList<>();
	List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getAllChildMemberGridConnections();
	
	for (int k = 0; k < memberGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, memberGridConnections.size()).darker());
		energyDemandChartYear.addDataSet(memberGridConnections.get(k).getRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(startTime_h, 24.0), memberGridConnections.get(k).p_ownerID, colorSpectrum.get(k));
		energySupplyChartYear.addDataSet(memberGridConnections.get(k).getRapidRunData().am_dailyAverageProductionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(startTime_h, 24.0), memberGridConnections.get(k).p_ownerID, colorSpectrum.get(k));	
	}
}
else{	
	// Still need to convert to daily averages!
	for (OL_AssetFlowCategories AC : dataObject.getRapidRunData().assetsMetaData.activeAssetFlows) {
		if (uI_Results.v_electricAssetFlows.contains(AC)) {
			if (uI_Results.v_consumptionAssetFlows.contains(AC)) {
				energyDemandChartYear.addDataSet(dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(AC).getDataSet(startTime_h, 24.0), uI_Results.lm_assetFlowLabels.get(AC), get_UI_Results().cm_assetFlowColors.get(AC));
			} else {
				energySupplyChartYear.addDataSet(dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(AC).getDataSet(startTime_h, 24.0), uI_Results.lm_assetFlowLabels.get(AC), get_UI_Results().cm_assetFlowColors.get(AC));
			}
		}
	}
}
/*ALCODEEND*/}

double f_addOtherEnergyCarriers_Live(I_EnergyData dataObject)
{/*ALCODESTART::1714746057326*/
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.getLiveData().activeConsumptionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.getLiveData().activeProductionEnergyCarriers);

activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChart.addDataSet( dataObject.getLiveData().dsm_liveDemand_kW.get(EC_consumption), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
	else if(dataObject.getLiveData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.districtHeatDelivery_kW)){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChart.addDataSet( dataObject.getLiveData().dsm_liveAssetFlows_kW.get(OL_AssetFlowCategories.districtHeatDelivery_kW), "Warmte net", uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
}


for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	if(EC_production != OL_EnergyCarriers.HEAT){
		energySupplyChart.addDataSet( dataObject.getLiveData().dsm_liveSupply_kW.get(EC_production), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));  
	}
	else if(dataObject.getLiveData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.ptProductionHeat_kW)){
		energySupplyChart.addDataSet( dataObject.getLiveData().dsm_liveAssetFlows_kW.get(OL_AssetFlowCategories.ptProductionHeat_kW), "PT", uI_Results.cm_productionColors.get(EC_production)); 
	}
}

/*ALCODEEND*/}

double f_addElectricityFlows_Live(I_EnergyData dataObject)
{/*ALCODESTART::1714746057328*/
gr_week.setVisible(true);
v_weekLabel.setX(220);
v_weekLabel.setText("");

if (uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYCOOP && b_subdivideEnergyCoopFlows) {
	List<Color> colorSpectrum = new ArrayList<>();
	List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.getLiveData().parentAgent).f_getAllChildMemberGridConnections();
	
	for (int k = 0; k < memberGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, memberGridConnections.size()).darker());
		energyDemandChart.addDataSet(memberGridConnections.get(k).getLiveData().dsm_liveDemand_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_ownerID, colorSpectrum.get(k));
		energySupplyChart.addDataSet(memberGridConnections.get(k).getLiveData().dsm_liveSupply_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_ownerID, colorSpectrum.get(k));	
	}
}
else{
	for (OL_AssetFlowCategories AC : dataObject.getLiveData().assetsMetaData.activeAssetFlows) {
		if (uI_Results.v_electricAssetFlows.contains(AC)) {
			if (uI_Results.v_consumptionAssetFlows.contains(AC)) {
				energyDemandChart.addDataSet(dataObject.getLiveData().dsm_liveAssetFlows_kW.get(AC), uI_Results.lm_assetFlowLabels.get(AC), get_UI_Results().cm_assetFlowColors.get(AC));
			} else {
				energySupplyChart.addDataSet(dataObject.getLiveData().dsm_liveAssetFlows_kW.get(AC), uI_Results.lm_assetFlowLabels.get(AC), get_UI_Results().cm_assetFlowColors.get(AC));
			}
		}
	}
}

/*ALCODEEND*/}

double f_addOtherEnergyFlows_Week(I_EnergyData dataObject,boolean isSummerWeek)
{/*ALCODESTART::1714897296534*/

EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.getRapidRunData().activeProductionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.getRapidRunData().activeConsumptionEnergyCarriers);

activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.HEAT );

if (dataObject.getRapidRunData().getStoreTotalAssetFlows()) {
	double dataSetStartTime_h = uI_Results.energyModel.p_runStartTime_h; //
	double peakTime_h;
	double peak_kW;
	if (isSummerWeek) {
		peakTime_h = dataObject.getRapidRunData().getPeakFeedinTime_h();
		peak_kW = dataObject.getRapidRunData().getPeakFeedin_kW();
	} else {
		peakTime_h = dataObject.getRapidRunData().getPeakDeliveryTime_h();
		peak_kW = dataObject.getRapidRunData().getPeakDelivery_kW();
	}
	double peakWeekStart_h = dataObject.getRapidRunData().getWeekStart_h(peakTime_h);
	for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
		if(EC_consumption != OL_EnergyCarriers.HEAT){
			energyDemandChart.addDataSet( dataObject.getRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(EC_consumption).getDataSet(dataSetStartTime_h, peakWeekStart_h, peakWeekStart_h+24*7), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption));
			//dataObject.getRapidRunData().am_dailyAverageConsumptionAccumulators_kW
		} else if(dataObject.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.districtHeatDelivery_kW)){//Only heat import, not all consumption (part of gas, elec, etc. already)
			energyDemandChart.addDataSet( dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.districtHeatDelivery_kW).getDataSet(dataSetStartTime_h, peakWeekStart_h, peakWeekStart_h+24*7), "Warmte net", uI_Results.cm_consumptionColors.get(EC_consumption)); 
		}
	}
	
	for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
		if(EC_production != OL_EnergyCarriers.HEAT){
			energySupplyChart.addDataSet( dataObject.getRapidRunData().am_dailyAverageProductionAccumulators_kW.get(EC_production).getDataSet(dataSetStartTime_h, peakWeekStart_h, peakWeekStart_h+24*7), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));
		} else if(dataObject.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.ptProductionHeat_kW)){
			energySupplyChart.addDataSet( dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.ptProductionHeat_kW).getDataSet(dataSetStartTime_h, peakWeekStart_h, peakWeekStart_h+24*7), "PT", uI_Results.cm_productionColors.get(EC_production)); 
		}
	}
} else {
	double startTime_h;
	if (isSummerWeek) {
		startTime_h = uI_Results.energyModel.p_startOfSummerWeek_h - uI_Results.energyModel.p_runStartTime_h;
	} else {
		startTime_h = uI_Results.energyModel.p_startOfWinterWeek_h - uI_Results.energyModel.p_runStartTime_h;
	}
	if (startTime_h<0) {
		startTime_h +=8760;
	}
	for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
		if(EC_consumption != OL_EnergyCarriers.HEAT){
			if (isSummerWeek) {
				energyDemandChart.addDataSet( dataObject.getRapidRunData().am_summerWeekConsumptionAccumulators_kW.get(EC_consumption).getDataSet(startTime_h), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption));
			} else {
				energyDemandChart.addDataSet( dataObject.getRapidRunData().am_winterWeekConsumptionAccumulators_kW.get(EC_consumption).getDataSet(startTime_h), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption));
			}
		}
		else if(dataObject.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.districtHeatDelivery_kW)){//Only heat import, not all consumption (part of gas, elec, etc. already)
			if (isSummerWeek) {
				energyDemandChart.addDataSet( dataObject.getRapidRunData().am_assetFlowsSummerWeek_kW.get(OL_AssetFlowCategories.districtHeatDelivery_kW).getDataSet(startTime_h), "Warmte net", uI_Results.cm_consumptionColors.get(EC_consumption)); 
			} else {
				energyDemandChart.addDataSet( dataObject.getRapidRunData().am_assetFlowsWinterWeek_kW.get(OL_AssetFlowCategories.districtHeatDelivery_kW).getDataSet(startTime_h), "Warmte net", uI_Results.cm_consumptionColors.get(EC_consumption)); 
			}
		}
	}
	
	for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
		if(EC_production != OL_EnergyCarriers.HEAT){
			if (isSummerWeek) {
				energySupplyChart.addDataSet( dataObject.getRapidRunData().am_summerWeekProductionAccumulators_kW.get(EC_production).getDataSet(startTime_h), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));
			} else {
				energySupplyChart.addDataSet( dataObject.getRapidRunData().am_winterWeekProductionAccumulators_kW.get(EC_production).getDataSet(startTime_h), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));
			}
		}
		else if(dataObject.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.ptProductionHeat_kW)){
			if (isSummerWeek) {
				energySupplyChart.addDataSet( dataObject.getRapidRunData().am_assetFlowsSummerWeek_kW.get(OL_AssetFlowCategories.ptProductionHeat_kW).getDataSet(startTime_h), "PT", uI_Results.cm_productionColors.get(EC_production)); 
			} else {
				energySupplyChart.addDataSet( dataObject.getRapidRunData().am_assetFlowsWinterWeek_kW.get(OL_AssetFlowCategories.ptProductionHeat_kW).getDataSet(startTime_h), "PT", uI_Results.cm_productionColors.get(EC_production)); 
			}
		}
	}
}
/*ALCODEEND*/}

double f_addElectricityFlows_Week(I_EnergyData dataObject,boolean isSummerWeek)
{/*ALCODESTART::1714897296536*/
gr_week.setVisible(true);
double dataSetWeekStartTime_h;

if (dataObject.getRapidRunData().getStoreTotalAssetFlows()) {
	double dataSetStartTime_h = uI_Results.energyModel.p_runStartTime_h; //
	double peakTime_h;
	double peak_kW;
	if (isSummerWeek) {
		peakTime_h = dataObject.getRapidRunData().getPeakFeedinTime_h();
		peak_kW = dataObject.getRapidRunData().getPeakFeedin_kW();
	} else {
		peakTime_h = dataObject.getRapidRunData().getPeakDeliveryTime_h();
		peak_kW = dataObject.getRapidRunData().getPeakDelivery_kW();
	}
	 
	//traceln("Plotting peak feedin week instead of fixed summer week! Peak feedin occured at: %s hours, power was: %s", peakFeedinTime_h, peakFeedin_kW);
	
	// Output the result
	String dateTimeString = f_getDateTimeFromHour(peakTime_h);
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
	}
	v_weekLabel.setX(80);
	double peakWeekStart_h = dataObject.getRapidRunData().getWeekStart_h(peakTime_h);
	//for (OL_AssetFlowCategories AC : dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.keySet()) {
	for (OL_AssetFlowCategories AC : dataObject.getRapidRunData().assetsMetaData.activeAssetFlows) {
		if (uI_Results.v_electricAssetFlows.contains(AC)) {
			if (uI_Results.v_consumptionAssetFlows.contains(AC)) {
				energyDemandChart.addDataSet(dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(AC).getDataSet(dataSetStartTime_h,peakWeekStart_h, peakWeekStart_h + 7*24), uI_Results.lm_assetFlowLabels.get(AC), get_UI_Results().cm_assetFlowColors.get(AC));
			} else {
				energySupplyChart.addDataSet(dataObject.getRapidRunData().am_assetFlowsAccumulators_kW.get(AC).getDataSet(dataSetStartTime_h,peakWeekStart_h, peakWeekStart_h + 7*24), uI_Results.lm_assetFlowLabels.get(AC), get_UI_Results().cm_assetFlowColors.get(AC));
			}
		}
	}
} else {
	if (isSummerWeek) {
		dataSetWeekStartTime_h = uI_Results.energyModel.p_startOfSummerWeek_h - uI_Results.energyModel.p_runStartTime_h;
	} else {
		dataSetWeekStartTime_h = uI_Results.energyModel.p_startOfWinterWeek_h - uI_Results.energyModel.p_runStartTime_h;
	}
	if (dataSetWeekStartTime_h<0) {
		dataSetWeekStartTime_h +=8760;
	}
	if (uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYCOOP && b_subdivideEnergyCoopFlows) {
		List<Color> colorSpectrum = new ArrayList<>();
		List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getAllChildMemberGridConnections();
		
		for (int k = 0; k < memberGridConnections.size(); k++) {
		    colorSpectrum.add(UtilitiesColor.spectrumColor(k, memberGridConnections.size()).darker());
		    if (isSummerWeek){
				energyDemandChart.addDataSet(memberGridConnections.get(k).getRapidRunData().am_summerWeekConsumptionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(dataSetWeekStartTime_h), memberGridConnections.get(k).p_ownerID, colorSpectrum.get(k));
				energySupplyChart.addDataSet(memberGridConnections.get(k).getRapidRunData().am_summerWeekProductionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(dataSetWeekStartTime_h), memberGridConnections.get(k).p_ownerID, colorSpectrum.get(k));	
			} else {
				energyDemandChart.addDataSet(memberGridConnections.get(k).getRapidRunData().am_winterWeekConsumptionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(dataSetWeekStartTime_h), memberGridConnections.get(k).p_ownerID, colorSpectrum.get(k));
				energySupplyChart.addDataSet(memberGridConnections.get(k).getRapidRunData().am_winterWeekProductionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(dataSetWeekStartTime_h), memberGridConnections.get(k).p_ownerID, colorSpectrum.get(k));	
			}
		}
	}
	else{
		for (OL_AssetFlowCategories AC : dataObject.getRapidRunData().am_assetFlowsSummerWeek_kW.keySet()) {
			if (uI_Results.v_electricAssetFlows.contains(AC)) {
				if (isSummerWeek){
					if (uI_Results.v_consumptionAssetFlows.contains(AC)) {
						energyDemandChart.addDataSet(dataObject.getRapidRunData().am_assetFlowsSummerWeek_kW.get(AC).getDataSet(dataSetWeekStartTime_h), uI_Results.lm_assetFlowLabels.get(AC), get_UI_Results().cm_assetFlowColors.get(AC));
					} else {
						energySupplyChart.addDataSet(dataObject.getRapidRunData().am_assetFlowsSummerWeek_kW.get(AC).getDataSet(dataSetWeekStartTime_h), uI_Results.lm_assetFlowLabels.get(AC), get_UI_Results().cm_assetFlowColors.get(AC));
					}
				} else {
					if (uI_Results.v_consumptionAssetFlows.contains(AC)) {
						energyDemandChart.addDataSet(dataObject.getRapidRunData().am_assetFlowsWinterWeek_kW.get(AC).getDataSet(dataSetWeekStartTime_h), uI_Results.lm_assetFlowLabels.get(AC), get_UI_Results().cm_assetFlowColors.get(AC));
					} else {
						energySupplyChart.addDataSet(dataObject.getRapidRunData().am_assetFlowsWinterWeek_kW.get(AC).getDataSet(dataSetWeekStartTime_h), uI_Results.lm_assetFlowLabels.get(AC), get_UI_Results().cm_assetFlowColors.get(AC));
					}
				}
			}
		}	
	}
}
/*ALCODEEND*/}

AreaCollection f_resetCharts()
{/*ALCODESTART::1714897512553*/
gr_week.setVisible(false);
gr_year.setVisible(false);
gr_day.setVisible(false);
gr_trafoWeek.setVisible(false);
gr_trafoDay.setVisible(false);
gr_netLoadWeek.setVisible(false);
gr_netLoadDay.setVisible(false);

plot_trafo_week.removeAll();
plot_trafo_day.removeAll();
plot_netload_week.removeAll();
plot_netload_day.removeAll();
energyDemandChart.removeAll();
energySupplyChart.removeAll();
energyDemandChartYear.removeAll();
energySupplyChartYear.removeAll();
energyDemandChartDay.removeAll();
energySupplyChartDay.removeAll();

radio_periodLive.setVisible(false);
rb_periodIncludingYear.setVisible(false);
rb_periodExcludingYear.setVisible(false);
rb_periodPeaksIncludingYear.setVisible(false);
rb_periodPeaksExcludingYear.setVisible(false);
v_weekLabel.setText("");

/*ALCODEEND*/}

double f_setCharts()
{/*ALCODESTART::1714899014782*/
f_resetCharts();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 115, true);

if (v_periodRadioButton.getValue() == 0) {
	radio_periodLive.setVisible(true);
}

I_EnergyData dataObject = uI_Results.f_getSelectedObjectData();


if (dataObject.getRapidRunData()!=null && dataObject.getRapidRunData().getStoreTotalAssetFlows()) {
	if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
		v_periodRadioButton = rb_periodPeaksExcludingYear;
	} else {
		v_periodRadioButton = rb_periodPeaksIncludingYear;
	}
} else {
	if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
		v_periodRadioButton = rb_periodExcludingYear;
	} else {
		v_periodRadioButton = rb_periodIncludingYear;
	}
}
v_periodRadioButton.setVisible(true);
int radioValue = v_periodRadioButton.getValue();

if (radio_energyType.getValue() == 2) { // Line Plot (Net Load)
	switch (radioValue) {
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
		
		case 2: // Summer
			if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
				f_addElectricityFlowsTrafo_Week(uI_Results.v_gridNode,true);
			}
			else {
				f_addElectricityNetLoad_Week(dataObject, true);
			}
			break;
		
		case 1: // Winter
			if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
				f_addElectricityFlowsTrafo_Week(uI_Results.v_gridNode, false);
			}
			else {
				f_addElectricityNetLoad_Week(dataObject, false);
			}
			break;
		
		case 3: // Year
			if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
				//f_addElectricityFlowsTrafo_Year(uI_Results.v_gridNode);
				throw new RuntimeException("The Year Graph does not exist for GridNodes.");				
			}
			else {
				//f_addElectricityNetLoad_Year(dataObject);
				throw new RuntimeException("The Net Balance Year Graph does not exist.");				
			}
			//break;
			
		default:
			throw new RuntimeException("Unrecognized option selected in Period Radio Button for Line Plots (Net Load).");
			//break;
	}
}
else { // Stack Chart
	switch (radioValue) {
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
		
		case 2: // Summer
			if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
				f_addElectricityFlowsTrafo_Week(uI_Results.v_gridNode, true);
			}
			else {
				f_addElectricityFlows_Week(dataObject, true);
				if( radio_energyType.getValue() == 1){
					f_addOtherEnergyFlows_Week(dataObject, true);
				}
			}
			break;
			
		case 1: // Winter
			if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
				f_addElectricityFlowsTrafo_Week(uI_Results.v_gridNode, false);
			} else {
				f_addElectricityFlows_Week(dataObject, false);
				if( radio_energyType.getValue() == 1){
					f_addOtherEnergyFlows_Week(dataObject, false);
				}
			}
			break;
			
		case 3: // Year
			if (uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE) {
				// This graph does not exist, defaulting back to the live plot
				throw new RuntimeException("The Year Graph does not exist for GridNodes.");				
				//radio_period.setValue(0);
				//radio_period_peaks.setValue(0);
				//f_setCharts();
			} else {
				f_addElectricityFlows_Year(dataObject);
				if( radio_energyType.getValue() == 1){
					f_addOtherEnergyFlows_Year(dataObject);
				}
			}
			break;
		
		default:
			throw new RuntimeException("Unrecognized option selected in Period Radio Button for Stack Charts.");	
			//break;
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

gr_trafoWeek.setVisible(true);
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

gr_trafoYear.setVisible(true);
plot_trafo_year.addDataSet(uI_Results.f_createFlatDataset(0, 8760, GN.p_capacity_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_year.addDataSet(uI_Results.f_createFlatDataset(0, 8760, -GN.p_capacity_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_year.addDataSet(GN.data_totalLoad_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 0.25, Chart.PointStyle.POINT_NONE);

/*
int maxValue = roundToInt(max(GN.v_dataElectricityBaseloadConsumptionYear_kW.getYMax(), GN.p_capacity_kW));
int minValue = roundToInt(min(GN.v_dataElectricityBaseloadConsumptionYear_kW.getYMin(), -GN.p_capacity_kW));
plot_trafo_year.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);
*/
/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_Week(GridNode GN,boolean isSummerWeek)
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

gr_trafoWeek.setVisible(true);

/*
//Add datasets to plot summer week
//double startTime_h = uI_Results.energyModel.p_startOfSummerWeek_h - uI_Results.energyModel.p_runStartTime_h;
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(startTime_h, 168, GN.p_capacity_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(startTime_h, 168, -GN.p_capacity_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(GN.data_summerWeekLoad_kW, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
*/
//Add datasets to plot peak feedin week
DataSet ds;
if (isSummerWeek) {
	ds = GN.f_getPeakExportWeekDataSet();
} else {
	ds = GN.f_getPeakImportWeekDataSet();
}

plot_trafo_week.addDataSet(ds, "Netto vermogen afname", uI_Results.v_electricityDemandColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1.5, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(ds.getXMin(), 168, GN.p_capacity_kW), deliveryCapacityLabel, deliveryCapacityColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
plot_trafo_week.addDataSet(uI_Results.f_createFlatDataset(ds.getXMin(), 168, -GN.p_capacity_kW), feedinCapacityLabel, feedinCapacityColor,true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);



/*
int maxValue = roundToInt(max(GN.v_dataElectricityBaseloadConsumptionSummerWeek_kW.getYMax(), GN.p_capacity_kW));
int minValue = roundToInt(min(GN.v_dataElectricityBaseloadConsumptionSummerWeek_kW.getYMin(), -GN.p_capacity_kW));
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
gr_netLoadWeek.setVisible(true);
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

double f_addElectricityNetLoad_Week(I_EnergyData dataObject,boolean isSummerWeek)
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
double startTime_h = uI_Results.energyModel.p_startOfSummerWeek_h - uI_Results.energyModel.p_runStartTime_h;


if (dataObject.getRapidRunData().getStoreTotalAssetFlows()) { // 
	double dataSetStartTime_h = uI_Results.energyModel.p_runStartTime_h; //
	double peakTime_h;
	double peak_kW;
	if (isSummerWeek) {
		peakTime_h = dataObject.getRapidRunData().getPeakFeedinTime_h();
		peak_kW = dataObject.getRapidRunData().getPeakFeedin_kW();
	} else {
		peakTime_h = dataObject.getRapidRunData().getPeakDeliveryTime_h();
		peak_kW = dataObject.getRapidRunData().getPeakDelivery_kW();
	}

	double peakWeekStart_h = dataObject.getRapidRunData().getWeekStart_h(peakTime_h);
	String dateTimeString = f_getDateTimeFromHour(peakTime_h);
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
}

/*
//Set vertical scale
int maxValue = roundToInt(max(dataObject.v_dataNetLoadSummerWeek_kW.getYMax(), dataObject.v_gridCapacityDelivery_kW));
int minValue = roundToInt(min(dataObject.v_dataNetLoadSummerWeek_kW.getYMin(), -dataObject.v_gridCapacityFeedIn_kW));
plot_netload_week.setFixedVerticalScale(minValue + minValue * 0.15, maxValue + maxValue * 0.15);
*/
gr_netLoadWeek.setVisible(true);

/*ALCODEEND*/}

double f_addOtherEnergyCarriers_LiveDay(I_EnergyData dataObject)
{/*ALCODESTART::1739804290044*/
EnumSet<OL_EnergyCarriers> activeConsumptionEnergyCarriers = EnumSet.copyOf(dataObject.getLiveData().activeConsumptionEnergyCarriers);
EnumSet<OL_EnergyCarriers> activeProductionEnergyCarriers = EnumSet.copyOf(dataObject.getLiveData().activeProductionEnergyCarriers);

activeConsumptionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );
activeProductionEnergyCarriers.remove( OL_EnergyCarriers.ELECTRICITY );

for (OL_EnergyCarriers EC_consumption : activeConsumptionEnergyCarriers) {
	if(EC_consumption != OL_EnergyCarriers.HEAT){
		energyDemandChartDay.addDataSet( dataObject.getLiveData().dsm_liveDemand_kW.get(EC_consumption), uI_Results.f_getName(EC_consumption), uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
	else if(dataObject.getLiveData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.districtHeatDelivery_kW)){//Only heat import, not all consumption (part of gas, elec, etc. already)
		energyDemandChartDay.addDataSet( dataObject.getLiveData().dsm_liveAssetFlows_kW.get(OL_AssetFlowCategories.districtHeatDelivery_kW), "Warmte net", uI_Results.cm_consumptionColors.get(EC_consumption)); 
	}
}


for (OL_EnergyCarriers EC_production : activeProductionEnergyCarriers) {
	if(EC_production != OL_EnergyCarriers.HEAT){
		energySupplyChartDay.addDataSet( dataObject.getLiveData().dsm_liveSupply_kW.get(EC_production), uI_Results.f_getName(EC_production), uI_Results.cm_productionColors.get(EC_production));  
	}
	else if(dataObject.getLiveData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.ptProductionHeat_kW)){
		energySupplyChartDay.addDataSet( dataObject.getLiveData().dsm_liveAssetFlows_kW.get(OL_AssetFlowCategories.ptProductionHeat_kW), "PT", uI_Results.cm_productionColors.get(EC_production));
	}
}

/*ALCODEEND*/}

double f_addElectricityFlows_LiveDay(I_EnergyData dataObject)
{/*ALCODESTART::1739804290048*/
//Set speed to slower.
getExperimentHost().setSpeed(5);

gr_day.setVisible(true);


if (uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYCOOP && b_subdivideEnergyCoopFlows) {
	List<Color> colorSpectrum = new ArrayList<>();
	List<GridConnection> memberGridConnections = ((EnergyCoop)dataObject.getLiveData().parentAgent).f_getAllChildMemberGridConnections();
	
	for (int k = 0; k < memberGridConnections.size(); k++) {
	    colorSpectrum.add(UtilitiesColor.spectrumColor(k, memberGridConnections.size()).darker());
		energyDemandChartDay.addDataSet(memberGridConnections.get(k).getLiveData().dsm_liveDemand_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_ownerID, colorSpectrum.get(k));
		energySupplyChartDay.addDataSet(memberGridConnections.get(k).getLiveData().dsm_liveSupply_kW.get(OL_EnergyCarriers.ELECTRICITY), memberGridConnections.get(k).p_ownerID, colorSpectrum.get(k));	
	}
}
else{
	for (OL_AssetFlowCategories AC : dataObject.getLiveData().assetsMetaData.activeAssetFlows) {
		if (uI_Results.v_electricAssetFlows.contains(AC)) {
			if (uI_Results.v_consumptionAssetFlows.contains(AC)) {
				energyDemandChartDay.addDataSet(dataObject.getLiveData().dsm_liveAssetFlows_kW.get(AC), uI_Results.lm_assetFlowLabels.get(AC), get_UI_Results().cm_assetFlowColors.get(AC));
			} else {
				energySupplyChartDay.addDataSet(dataObject.getLiveData().dsm_liveAssetFlows_kW.get(AC), uI_Results.lm_assetFlowLabels.get(AC), get_UI_Results().cm_assetFlowColors.get(AC));
			}
		}
	}
}
/*ALCODEEND*/}

double f_addElectricityFlowsTrafo_LiveDay(GridNode GN)
{/*ALCODESTART::1739804290051*/
gr_trafoDay.setVisible(true);

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
gr_netLoadDay.setVisible(true);
/*ALCODEEND*/}

String f_getDateTimeFromHour(double peakTime_h)
{/*ALCODESTART::1754485454881*/
int dayOfYear = (int)Math.floor(peakTime_h / 24) + 1;
int hourOfDay = roundToInt(peakTime_h % 24);
LocalDate date = LocalDate.ofYearDay(uI_Results.energyModel.p_year, dayOfYear);
LocalDateTime dateTime = date.atStartOfDay().plusHours(hourOfDay);

// Output the result
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
return dateTime.format(formatter);
/*ALCODEEND*/}

