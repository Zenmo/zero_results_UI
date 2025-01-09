double f_setSummaryCharts()
{/*ALCODESTART::1730395813825*/
if (rb_chartType.getValue()==0) {
	gr_gespreksleidraad1.setVisible(true);
	gr_gespreksleidraad3.setVisible(false);
	f_setChartsSummary1(uI_Results.v_area);
} else if (rb_chartType.getValue()==1) {
	gr_gespreksleidraad1.setVisible(false);
	gr_gespreksleidraad3.setPos(gr_gespreksleidraad1.getX(), gr_gespreksleidraad1.getY());
	gr_gespreksleidraad3.setVisible(true);
	f_setChartsSummary2(uI_Results.v_area);
}


/*ALCODEEND*/}

double f_setChartsSummary1(AreaCollection area)
{/*ALCODESTART::1730395813827*/
f_setEnergyBalanceChartFull(area.v_totalEnergySelfConsumed_MWh, area.fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY), area.fm_totalImports_MWh.get(OL_EnergyCarriers.METHANE),
					 area.fm_totalImports_MWh.get(OL_EnergyCarriers.DIESEL), area.fm_totalImports_MWh.get(OL_EnergyCarriers.HYDROGEN), area.v_totalEnergyExport_MWh, 
					 pl_consumptionChartGespreksleidraad1, pl_productionChartGespreksleidraad1, t_opwekTextGespreksleidraad1, t_gebruikTextGespreksleidraad1);

//f_setDemandAndSupplyGespreksleidraad1();
//Electricity demand chart
energyDemandChartYearGespreksleidraad1.removeAll();
energyDemandChartYearGespreksleidraad1.addDataSet(area.dsm_dailyAverageConsumptionDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY), v_electricityDemandText, v_energyDemandColor, true, false, Chart.INTERPOLATION_LINEAR, 2, Chart.POINT_NONE);

//Electricity supply chart
energySupplyChartYearGespreksleidraad1.removeAll();
//energySupplyChartYearGespreksleidraad1.addDataSet(area.data_petroleumProductsSupplyYear_MWh, v_petroleumProductsSupplyText, v_petroleumProductsSupplyColor);
//energySupplyChartYearGespreksleidraad1.addDataSet(area.data_districtHeatHeatSupplyYear_MWh, v_districtHeatHeatSupplyText, v_districtHeatHeatSupplyColor);
energySupplyChartYearGespreksleidraad1.addDataSet(area.v_dataElectricityWindProductionYear_kW, v_windElectricitySupplyText, v_windElectricitySupplyColor);
energySupplyChartYearGespreksleidraad1.addDataSet(area.v_dataElectricityPVProductionYear_kW, v_PVElectricitySupplyText, v_PVElectricitySupplyColor);
energySupplyChartYearGespreksleidraad1.addDataSet(area.v_dataElectricityStorageProductionYear_kW, v_storageElectricitySupplyText, v_storageElectricitySupplyColor);
energySupplyChartYearGespreksleidraad1.addDataSet(area.v_dataElectricityV2GProductionYear_kW, v_V2GElectricitySupplyText, v_V2GElectricitySupplyColor);

double maxScale = max(area.dsm_dailyAverageConsumptionDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY).getYMax(),area.dsm_dailyAverageProductionDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY).getYMax());
//double maxScale = max(uI_Results.energySupplyChartYearGespreksleidraad1.getScaleY(), uI_Results.energyDemandChartYearGespreksleidraad1.getScaleY());
energyDemandChartYearGespreksleidraad1.setFixedVerticalScale(0, maxScale);
energySupplyChartYearGespreksleidraad1.setFixedVerticalScale(maxScale);

energyDemandChartYearGespreksleidraad1.setVisible(false);
energySupplyChartYearGespreksleidraad1.setVisible(false);
energyDemandChartYearGespreksleidraad1.setVisible(true);
energySupplyChartYearGespreksleidraad1.setVisible(true);


/*ALCODEEND*/}

double f_setEnergyBalanceChartFull(double selfConsumedEnergy_MWh,double importE_MWh,double importG_MWh,double importF_MWh,double importH_MWh,double exportE_MWh,StackChart pl_consumptionChart,StackChart pl_productionChart,ShapeText t_OpwekText,ShapeText t_GebruikText)
{/*ALCODESTART::1730395813829*/
//gr_onbalansGraphs.setVisible(false);
pl_productionChart.removeAll();
pl_consumptionChart.removeAll();


DataItem d1 = new DataItem();
d1.setValue(selfConsumedEnergy_MWh);
DataItem d3 = new DataItem();
d3.setValue(exportE_MWh);

/*pl_productionChart.addDataItem(d1,"Zelfconsumptie gebied [MWh]",yellowGreen);
pl_productionChart.addDataItem(d3,"Teruggeleverde Electriciteit [MWh]",navy);
//pl_productionChart.addDataItem(d5,"Overbelasting [MWh]",red);
pl_consumptionChart.addDataItem(d1,"Zelfconsumptie gebied [MWh]",yellowGreen);*/

pl_consumptionChart.addDataItem(d1,"Lokaal opgewekt [MWh]",v_selfConsumedEnergyColor);
//pl_consumptionChart.addDataItem(d2,"Externe bronnen [MWh]",brown);
//pl_consumptionChart.addDataItem(d4,"Overbelasting [MWh]",red);
pl_productionChart.addDataItem(d1,"Lokaal gebruik gebied [MWh]",v_selfConsumedEnergyColor);
pl_productionChart.addDataItem(d3,"Teruggeleverde elektriciteit [MWh]",v_exportedEnergyColor);


if (importE_MWh > 0) {
	DataItem d2 = new DataItem();
	d2.setValue(importE_MWh);
	pl_consumptionChart.addDataItem(d2,"Electriciteit uit het net [MWh]",v_importedElectricityColor);
}
if (importG_MWh > 0 ) {
	DataItem d4 = new DataItem();
	d4.setValue(importG_MWh); //
	pl_consumptionChart.addDataItem(d4,"Aardgas [MWh]",v_naturalGasDemandColor);
}	
if (importF_MWh > 0 ) {
	DataItem d5 = new DataItem();
	d5.setValue(importF_MWh); // fuel
	pl_consumptionChart.addDataItem(d5,"Benzine/diesel[MWh]",v_petroleumProductsDemandColor);
}
if (importH_MWh > 0 ) {
	DataItem d6 = new DataItem();
	d6.setValue(importH_MWh); // Hydrogen
	pl_consumptionChart.addDataItem(d6,"Waterstof [MWh]",v_hydrogenDemandColor);
}


//pl_consumptionChart.addDataItem(d4,"Overbelasting [MWh]",red);
double chartScale_MWh = max(selfConsumedEnergy_MWh+exportE_MWh, selfConsumedEnergy_MWh+importE_MWh+importG_MWh+importF_MWh+importH_MWh);
pl_consumptionChart.setFixedScale(chartScale_MWh);
pl_productionChart.setFixedScale(chartScale_MWh);

//t_OpwekText.setText("Lokale" + System.lineSeparator() + "opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+exportE_MWh)/1000) + " GWh");
//t_GebruikText.setText("Lokaal" + System.lineSeparator() + "verbruik" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+importE_MWh+importG_MWh+importF_MWh+importH_MWh)/1000) + " GWh");

if (chartScale_MWh<10) {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+exportE_MWh)*1000) + " kWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+importE_MWh+importG_MWh+importF_MWh+importH_MWh)*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+exportE_MWh)) + " MWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+importE_MWh+importG_MWh+importF_MWh+importH_MWh)) + " MWh");
} else {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToDecimal((selfConsumedEnergy_MWh+exportE_MWh)/1000, 1) + " GWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToDecimal((selfConsumedEnergy_MWh+importE_MWh+importG_MWh+importF_MWh+importH_MWh)/1000,1) + " GWh");
}
/*ALCODEEND*/}

double f_setChartsSummary2(AreaCollection area)
{/*ALCODESTART::1730395813831*/
t_KPIselfsufficiencyCollective_pct.setText(roundToInt(area.v_modelSelfSufficiency_fr*100) + "%");
t_KPIselfsufficiencyIndividual_pct.setText(roundToInt(area.v_individualSelfSufficiency_fr*100) + "%");

double annualSelfConsumedElectricityIndividual_MWh;

if (! (area.v_individualSelfconsumption_fr > 0) ) {
	annualSelfConsumedElectricityIndividual_MWh = 0.0;
	traceln("NaN detected!");
} else {
	annualSelfConsumedElectricityIndividual_MWh = (area.v_totalElectricitySelfConsumed_MWh + area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY)) * area.v_individualSelfconsumption_fr;
}

f_setSelfConsumptionChart(annualSelfConsumedElectricityIndividual_MWh, area.v_totalElectricitySelfConsumed_MWh, area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY),
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
pl_individualChart.addDataItem(d1,"Zelf gebruikt [MWh]",v_selfConsumedElectricityColor);
pl_individualChart.addDataItem(d4,"Teruggeleverd [MWh]",v_exportedEnergyColor);
//pl_consumptionChart.addDataItem(d4,"Overbelasting [MWh]",red);
pl_collectiveChart.addDataItem(d2,"Lokaal gebruikt [MWh]",v_selfConsumedElectricityColor);
pl_collectiveChart.addDataItem(d3,"Geëxporteerd [MWh]",v_exportedEnergyColor);
//pl_productionChart.addDataItem(d5,"Overbelasting [MWh]",red);
double chartScale_MWh = selfConsumedEnergyCollective_MWh+export_MWh;
pl_individualChart.setFixedScale(chartScale_MWh);
pl_collectiveChart.setFixedScale(chartScale_MWh);


/*ALCODEEND*/}

