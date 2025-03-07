double f_setGespreksleidraadBedrijvenCharts()
{/*ALCODESTART::1730395813825*/
gr_GSLDSummary1.setVisible(false);
gr_GSLDSummary2.setVisible(false);
gr_GSLDSummary3.setVisible(false);
f_setWarningScreen(false);
		
I_EnergyData data = uI_Results.f_getSelectedObjectData();

if(uI_Results.v_selectedObjectType != OL_SelectedObjectType.COOP || uI_Results.v_selectedObjectType != OL_SelectedObjectType.ENERGYMODEL){
	f_setWarningScreen(true);
}

if (rb_gespreksleidraadBedrijvenChartType.getValue()==0) {
	gr_GSLDSummary1.setVisible(true);
	f_setChartsGespreksleidraadBedrijven1(data);
} else if (rb_gespreksleidraadBedrijvenChartType.getValue()==1) {
	gr_GSLDSummary2.setVisible(true);
	f_setChartsGespreksleidraadBedrijven2(data);
} else if (rb_gespreksleidraadBedrijvenChartType.getValue()==2) {
	gr_GSLDSummary3.setVisible(true);
	if(uI_Results.b_showGroupContractValues && uI_Results.v_selectedObjectType == OL_SelectedObjectType.COOP){
		f_setGroupContractChart(data);
	}
	else{
		f_setChartsGespreksleidraadBedrijven3(data);
	}
}

/*ALCODEEND*/}

double f_setChartsGespreksleidraadBedrijven1(I_EnergyData data)
{/*ALCODESTART::1730395813827*/
f_setEnergyBalanceChartFull(data);

f_setDemandAndSupplyGespreksleidraad1(data);



/*ALCODEEND*/}

double f_setEnergyBalanceChartFull(I_EnergyData data)
{/*ALCODESTART::1730395813829*/
double selfConsumedEnergy_MWh = data.getRapidRunData().v_totalEnergySelfConsumed_MWh; 	
double importE_MWh = data.getRapidRunData().fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY);	
double importG_MWh = data.getRapidRunData().fm_totalImports_MWh.get(OL_EnergyCarriers.METHANE);
double importF_MWh = data.getRapidRunData().fm_totalImports_MWh.get(OL_EnergyCarriers.DIESEL); 
double importH_MWh = data.getRapidRunData().fm_totalImports_MWh.get(OL_EnergyCarriers.HYDROGEN); 
double exportE_MWh = data.getRapidRunData().v_totalEnergyExport_MWh; 
StackChart pl_consumptionChart = pl_consumptionChartGespreksleidraad1; 
StackChart pl_productionChart = pl_productionChartGespreksleidraad1; 
ShapeText t_OpwekText = 	t_opwekTextGespreksleidraad1; 
ShapeText t_GebruikText =  t_gebruikTextGespreksleidraad1;

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

if(area.v_numberOfGridconnections > 1){
	pl_productionChart.addDataItem(d1,"Lokaal gebruik groep [MWh]",v_selfConsumedEnergyColor);
}
else{
	pl_productionChart.addDataItem(d1,"Lokaal gebruik gebied [MWh]",v_selfConsumedEnergyColor);
}
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

double f_setChartsGespreksleidraadBedrijven2(I_EnergyData data)
{/*ALCODESTART::1730395813831*/
double totalCollectiveSelfSufficiencyElectricity_fr = data.getRapidRunData().v_totalElectricitySelfConsumed_MWh/data.getRapidRunData().v_totalElectricityConsumed_MWh;


t_KPIselfsufficiencyCollective_pct.setText(roundToInt(totalCollectiveSelfSufficiencyElectricity_fr*100) + "%");
t_KPIselfsufficiencyIndividual_pct.setText(roundToInt(data.getRapidRunData().v_individualSelfSufficiencyElectricity_fr*100) + "%");


double annualSelfConsumedElectricityIndividual_MWh;

if (! (data.getRapidRunData().v_individualSelfconsumptionElectricity_fr > 0) ) {
	annualSelfConsumedElectricityIndividual_MWh = 0.0;
	traceln("NaN detected!");
} else {
	annualSelfConsumedElectricityIndividual_MWh = (data.getRapidRunData().v_totalElectricitySelfConsumed_MWh + data.getRapidRunData().fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY)) * data.getRapidRunData().v_individualSelfconsumptionElectricity_fr;
}

f_setSelfConsumptionChart(annualSelfConsumedElectricityIndividual_MWh, data.getRapidRunData().v_totalElectricitySelfConsumed_MWh, data.getRapidRunData().fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY),
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

double f_setDemandAndSupplyGespreksleidraad1(I_EnergyData data)
{/*ALCODESTART::1737988539936*/
//Electricity demand chart
energyDemandChartYearGespreksleidraad1.removeAll();
energyDemandChartYearGespreksleidraad1.addDataSet(data.getRapidRunData().dsm_dailyAverageConsumptionDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY), v_electricityDemandText, v_energyDemandColor, true, false, Chart.INTERPOLATION_LINEAR, 2, Chart.POINT_NONE);

//Electricity supply chart
energySupplyChartYearGespreksleidraad1.removeAll();
//energySupplyChartYearGespreksleidraad1.addDataSet(area.data_petroleumProductsSupplyYear_MWh, v_petroleumProductsSupplyText, v_petroleumProductsSupplyColor);
//energySupplyChartYearGespreksleidraad1.addDataSet(area.data_districtHeatHeatSupplyYear_MWh, v_districtHeatHeatSupplyText, v_districtHeatHeatSupplyColor);
energySupplyChartYearGespreksleidraad1.addDataSet(data.getRapidRunData().v_dataElectricityWindProductionYear_kW, v_windElectricitySupplyText, v_windElectricitySupplyColor);
energySupplyChartYearGespreksleidraad1.addDataSet(data.getRapidRunData().v_dataElectricityPVProductionYear_kW, v_PVElectricitySupplyText, v_PVElectricitySupplyColor);
energySupplyChartYearGespreksleidraad1.addDataSet(data.getRapidRunData().v_dataElectricityStorageProductionYear_kW, v_storageElectricitySupplyText, v_storageElectricitySupplyColor);
energySupplyChartYearGespreksleidraad1.addDataSet(data.getRapidRunData().v_dataElectricityV2GProductionYear_kW, v_V2GElectricitySupplyText, v_V2GElectricitySupplyColor);

double maxScale = max(data.getRapidRunData().dsm_dailyAverageConsumptionDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY).getYMax(),data.getRapidRunData().dsm_dailyAverageProductionDataSets_kW.get(OL_EnergyCarriers.ELECTRICITY).getYMax());
//double maxScale = max(energySupplyChartYearGespreksleidraad1.getScaleY(), energyDemandChartYearGespreksleidraad1.getScaleY());
traceln("maxScale: %s", maxScale);
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
if(rb_GSLDSummary3_delivery_or_feedin.getValue() == 0){//Delivery
	totalGTV_kW.setValue(data.getRapidRunData().v_gridCapacityDelivery_kW);
	peakIndividual_kW.setValue(data.getRapidRunData().v_individualPeakDelivery_kW);
	peakCollective_kW.setValue(max(0, data.getRapidRunData().v_dataNetbelastingDuurkrommeYear_kW.getYMax()));
	text_peakType = "levering";
}
else if(rb_GSLDSummary3_delivery_or_feedin.getValue() == 1){//Feedin
	totalGTV_kW.setValue(data.getRapidRunData().v_gridCapacityFeedIn_kW);
	peakIndividual_kW.setValue(data.getRapidRunData().v_individualPeakFeedin_kW);
	peakCollective_kW.setValue(-1*min(0, data.getRapidRunData().v_dataNetbelastingDuurkrommeYear_kW.getYMin()));
	text_peakType = "teruglevering";
}


chart_barChartGSLDSummary3.addDataItem(totalGTV_kW,"Totaal GTV " + text_peakType,darkMagenta);
chart_barChartGSLDSummary3.addDataItem(peakIndividual_kW,"Piek " + text_peakType + " individueel",orange);
chart_barChartGSLDSummary3.addDataItem(peakCollective_kW,"Piek " + text_peakType + " collectief",green);

/*ALCODEEND*/}

double f_setGroupContractChart(I_EnergyData data)
{/*ALCODESTART::1740754239000*/
chart_barChartGSLDSummary3.removeAll();

t_titleGespreksleidraadBedrijven3.setText("Groepscontract capaciteit");

DataItem totalGTV_kW = new DataItem();
DataItem totalGTVgroupcontract_kW = new DataItem();
DataItem peakIndividual_kW = new DataItem();
DataItem peakCollective_kW = new DataItem();
String text_peakType = "";
if(rb_GSLDSummary3_delivery_or_feedin.getValue() == 0){//Delivery
	totalGTV_kW.setValue(data.getRapidRunData().v_gridCapacityDelivery_kW);
	totalGTVgroupcontract_kW.setValue(data.getRapidRunData().v_gridCapacityDelivery_groupcontract_kW);
	peakIndividual_kW.setValue(data.getRapidRunData().v_individualPeakDelivery_kW);
	peakCollective_kW.setValue(max(0, data.getRapidRunData().v_dataNetbelastingDuurkrommeYear_kW.getYMax()));
	text_peakType = "levering";
}
else if(rb_GSLDSummary3_delivery_or_feedin.getValue() == 1){//Feedin
	totalGTV_kW.setValue(data.getRapidRunData().v_gridCapacityFeedIn_kW);
	totalGTVgroupcontract_kW.setValue(data.getRapidRunData().v_gridCapacityFeedin_groupcontract_kW);
	peakIndividual_kW.setValue(data.getRapidRunData().v_individualPeakFeedin_kW);
	peakCollective_kW.setValue(-1*min(0, data.getRapidRunData().v_dataNetbelastingDuurkrommeYear_kW.getYMin()));
	text_peakType = "teruglevering";
}


chart_barChartGSLDSummary3.addDataItem(totalGTV_kW,"Totaal GTV Cumulatief" + text_peakType,darkMagenta);
chart_barChartGSLDSummary3.addDataItem(totalGTVgroupcontract_kW,"GTV Groepscontract" + text_peakType, magenta);
chart_barChartGSLDSummary3.addDataItem(peakIndividual_kW,"Piek " + text_peakType + " individueel",orange);
chart_barChartGSLDSummary3.addDataItem(peakCollective_kW,"Piek " + text_peakType + " collectief",green);

/*ALCODEEND*/}

double f_setWarningScreen(boolean showWarningScreen)
{/*ALCODESTART::1741342546947*/
gr_warningScreen.setVisible(showWarningScreen);
/*ALCODEEND*/}

