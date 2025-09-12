double f_setEnergyBalanceChartSimple(double selfConsumedEnergy_MWh,double import_MWh,double export_MWh,StackChart pl_consumptionChart,StackChart pl_productionChart,ShapeText t_OpwekText,ShapeText t_GebruikText)
{/*ALCODESTART::1732117759406*/
//gr_onbalansGraphs.setVisible(false);
pl_productionChart.removeAll();
pl_consumptionChart.removeAll();
DataItem d1 = new DataItem();
d1.setValue(selfConsumedEnergy_MWh);
DataItem d2 = new DataItem();
d2.setValue(import_MWh);
DataItem d3 = new DataItem();
d3.setValue(export_MWh);
pl_consumptionChart.addDataItem(d1,"Lokaal opgewekt [MWh]",v_selfConsumedElectricityColor);
pl_consumptionChart.addDataItem(d2,"Externe bronnen [MWh]",v_importedEnergyColor);
//pl_consumptionChart.addDataItem(d4,"Overbelasting [MWh]",red);
pl_productionChart.addDataItem(d1,"Lokaal gebruik gebied [MWh]",v_selfConsumedElectricityColor);
pl_productionChart.addDataItem(d3,"Teruggeleverde elektriciteit [MWh]",v_exportedEnergyColor);
//pl_productionChart.addDataItem(d5,"Overbelasting [MWh]",red);
double chartScale_MWh = max(selfConsumedEnergy_MWh+export_MWh, selfConsumedEnergy_MWh+import_MWh);
pl_consumptionChart.setFixedScale(chartScale_MWh);
pl_productionChart.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+export_MWh)*1000) + " kWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+import_MWh)*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+export_MWh)) + " MWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+import_MWh)) + " MWh");
} else {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+export_MWh)/1000) + " GWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+import_MWh)/1000) + " GWh");
}
	
/*ALCODEEND*/}

double f_setEnergyBalanceChartFull(double selfConsumedEnergy_MWh,double importE_MWh,double importG_MWh,double importF_MWh,double importH_MWh,double exportE_MWh,StackChart pl_consumptionChart,StackChart pl_productionChart,ShapeText t_OpwekText,ShapeText t_GebruikText)
{/*ALCODESTART::1732117759408*/
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
double chartScale_MWh = max(selfConsumedEnergy_MWh + exportE_MWh, selfConsumedEnergy_MWh + importE_MWh+importG_MWh + importF_MWh+importH_MWh);
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

double f_setEnergyBalanceChartSimpleBoth(double selfConsumedEnergy_MWh,double import_MWh,double export_MWh,StackChart pl_consumptionChart,StackChart pl_productionChart,ShapeText t_OpwekText,ShapeText t_GebruikText,double selfConsumedEnergyBottom_MWh,double importBottom_MWh,double exportBottom_MWh,StackChart pl_consumptionChartBottom,StackChart pl_productionChartBottom,ShapeText t_OpwekTextBottom,ShapeText t_GebruikTextBottom)
{/*ALCODESTART::1732117759410*/
//gr_onbalansGraphs.setVisible(false);

DataItem d1 = new DataItem();
d1.setValue(selfConsumedEnergy_MWh);
DataItem d2 = new DataItem();
d2.setValue(import_MWh);
DataItem d3 = new DataItem();
d3.setValue(export_MWh);
pl_productionChart.removeAll();
pl_consumptionChart.removeAll();
pl_consumptionChart.addDataItem(d1,"Lokaal opgewekt [MWh]",v_selfConsumedElectricityColor);
pl_consumptionChart.addDataItem(d2,"Externe bronnen [MWh]",v_importedEnergyColor);
//pl_consumptionChart.addDataItem(d4,"Overbelasting [MWh]",red);
pl_productionChart.addDataItem(d1,"Lokaal gebruik gebied [MWh]",v_selfConsumedElectricityColor);
pl_productionChart.addDataItem(d3,"Teruggeleverde elektriciteit [MWh]",v_exportedEnergyColor);

DataItem d4 = new DataItem();
d4.setValue(selfConsumedEnergyBottom_MWh);
DataItem d5 = new DataItem();
d5.setValue(importBottom_MWh);
DataItem d6 = new DataItem();
d6.setValue(exportBottom_MWh);
pl_productionChartBottom.removeAll();
pl_consumptionChartBottom.removeAll();
pl_consumptionChartBottom.addDataItem(d4,"Lokaal opgewekt [MWh]",v_selfConsumedElectricityColor);
pl_consumptionChartBottom.addDataItem(d5,"Externe bronnen [MWh]",v_importedEnergyColor);
//pl_consumptionChart.addDataItem(d4,"Overbelasting [MWh]",red);
pl_productionChartBottom.addDataItem(d4,"Lokaal gebruik gebied [MWh]",v_selfConsumedElectricityColor);
pl_productionChartBottom.addDataItem(d6,"Teruggeleverde elektriciteit [MWh]",v_exportedEnergyColor);

//pl_productionChart.addDataItem(d5,"Overbelasting [MWh]",red);
double chartScale_MWh = max(max(selfConsumedEnergy_MWh+export_MWh, selfConsumedEnergy_MWh+import_MWh),
							max(selfConsumedEnergyBottom_MWh+exportBottom_MWh, selfConsumedEnergyBottom_MWh+importBottom_MWh));
pl_consumptionChart.setFixedScale(chartScale_MWh);
pl_productionChart.setFixedScale(chartScale_MWh);
pl_consumptionChartBottom.setFixedScale(chartScale_MWh);
pl_productionChartBottom.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+export_MWh)*1000) + " kWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+import_MWh)*1000) + " kWh");
} else if (chartScale_MWh<10000) {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+export_MWh)) + " MWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+import_MWh)) + " MWh");
} else {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+export_MWh)/1000) + " GWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+import_MWh)/1000) + " GWh");
}
if (chartScale_MWh<10) {
	t_OpwekTextBottom.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergyBottom_MWh+exportBottom_MWh)*1000) + " kWh");
	t_GebruikTextBottom.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergyBottom_MWh+importBottom_MWh)*1000) + " kWh");
} else if (chartScale_MWh<10000) {
	t_OpwekTextBottom.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergyBottom_MWh+exportBottom_MWh)) + " MWh");
	t_GebruikTextBottom.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergyBottom_MWh+importBottom_MWh)) + " MWh");
} else {
	t_OpwekTextBottom.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergyBottom_MWh+exportBottom_MWh)/1000) + " GWh");
	t_GebruikTextBottom.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergyBottom_MWh+importBottom_MWh)/1000) + " GWh");
}
	
/*ALCODEEND*/}

double f_setEnergyBalanceChartFullBoth(double selfConsumedEnergy_MWh,double importE_MWh,double importG_MWh,double importF_MWh,double importH_MWh,double exportE_MWh,StackChart pl_consumptionChart,StackChart pl_productionChart,ShapeText t_OpwekText,ShapeText t_GebruikText,double selfConsumedEnergyBottom_MWh,double importEBottom_MWh,double importGBottom_MWh,double importFBottom_MWh,double importHBottom_MWh,double exportEBottom_MWh,StackChart pl_consumptionChartBottom,StackChart pl_productionChartBottom,ShapeText t_OpwekTextBottom,ShapeText t_GebruikTextBottom)
{/*ALCODESTART::1732117759412*/
//gr_onbalansGraphs.setVisible(false);
pl_productionChart.removeAll();
pl_consumptionChart.removeAll();
pl_productionChartBottom.removeAll();
pl_consumptionChartBottom.removeAll();

DataItem d1 = new DataItem();
d1.setValue(selfConsumedEnergy_MWh);
DataItem d3 = new DataItem();
d3.setValue(exportE_MWh);

pl_consumptionChart.addDataItem(d1,"Lokaal opgewekt [MWh]",v_selfConsumedEnergyColor);
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


DataItem d1bottom = new DataItem();
d1bottom.setValue(selfConsumedEnergyBottom_MWh);
DataItem d3bottom = new DataItem();
d3bottom.setValue(exportEBottom_MWh);

pl_consumptionChartBottom.addDataItem(d1bottom,"Lokaal opgewekt [MWh]",v_selfConsumedEnergyColor);
pl_productionChartBottom.addDataItem(d1bottom,"Lokaal gebruik gebied [MWh]",v_selfConsumedEnergyColor);
pl_productionChartBottom.addDataItem(d3bottom,"Teruggeleverde elektriciteit [MWh]",v_exportedEnergyColor);


if (importEBottom_MWh > 0) {
	DataItem d2bottom = new DataItem();
	d2bottom.setValue(importEBottom_MWh);
	pl_consumptionChartBottom.addDataItem(d2bottom,"Electriciteit uit het net [MWh]",v_importedElectricityColor);
}
if (importGBottom_MWh > 0 ) {
	DataItem d4bottom = new DataItem();
	d4bottom.setValue(importGBottom_MWh); //
	pl_consumptionChartBottom.addDataItem(d4bottom,"Aardgas [MWh]",v_naturalGasDemandColor);
}	
if (importFBottom_MWh > 0 ) {
	DataItem d5bottom = new DataItem();
	d5bottom.setValue(importFBottom_MWh); // fuel
	pl_consumptionChartBottom.addDataItem(d5bottom,"Benzine/diesel[MWh]",v_petroleumProductsDemandColor);
}
if (importHBottom_MWh > 0 ) {
	DataItem d6bottom = new DataItem();
	d6bottom.setValue(importHBottom_MWh); // Hydrogen
	pl_consumptionChartBottom.addDataItem(d6bottom,"Waterstof [MWh]",v_hydrogenDemandColor);
}


/*pl_consumptionChartBottom.addDataItem(d4,"Lokaal opgewekt [MWh]",yellowGreen);
pl_consumptionChartBottom.addDataItem(d5,"Externe bronnen [MWh]",brown);
//pl_consumptionChart.addDataItem(d4,"Overbelasting [MWh]",red);
pl_productionChartBottom.addDataItem(d4,"Lokaal gebruik gebied [MWh]",yellowGreen);
pl_productionChartBottom.addDataItem(d6,"Teruggeleverde elektriciteit [MWh]",navy);*/

//pl_productionChart.addDataItem(d5,"Overbelasting [MWh]",red);
double chartScale_MWh = max(max(selfConsumedEnergy_MWh+exportE_MWh, selfConsumedEnergy_MWh+importE_MWh+importG_MWh+importF_MWh+importH_MWh),
							max(selfConsumedEnergyBottom_MWh+exportEBottom_MWh, selfConsumedEnergyBottom_MWh+importEBottom_MWh+importGBottom_MWh+importFBottom_MWh+importHBottom_MWh));
pl_consumptionChart.setFixedScale(chartScale_MWh);
pl_productionChart.setFixedScale(chartScale_MWh);
pl_consumptionChartBottom.setFixedScale(chartScale_MWh);
pl_productionChartBottom.setFixedScale(chartScale_MWh);


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
if (chartScale_MWh<10) {
	t_OpwekTextBottom.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergyBottom_MWh+exportEBottom_MWh)*1000) + " kWh");
	t_GebruikTextBottom.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergyBottom_MWh+importEBottom_MWh+importGBottom_MWh+importFBottom_MWh+importHBottom_MWh)*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_OpwekTextBottom.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergyBottom_MWh+exportEBottom_MWh)) + " MWh");
	t_GebruikTextBottom.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergyBottom_MWh+importEBottom_MWh+importGBottom_MWh+importFBottom_MWh+importHBottom_MWh)) + " MWh");
} else {
	t_OpwekTextBottom.setText("Opwek" + System.lineSeparator() + roundToDecimal((selfConsumedEnergyBottom_MWh+exportEBottom_MWh)/1000,1) + " GWh");
	t_GebruikTextBottom.setText("Gebruik" + System.lineSeparator() + roundToDecimal((selfConsumedEnergyBottom_MWh+importEBottom_MWh+importGBottom_MWh+importFBottom_MWh+importHBottom_MWh)/1000,1) + " GWh");
}


/*ALCODEEND*/}

double f_setSupplyDemandGraphsFromString(String graph,AreaCollection area)
{/*ALCODESTART::1732117759420*/
gr_onbalansGraphs.setVisible(true);
//SupplyDemand
if( graph.contains( "Jaar")){	
	if( graph.contains( "Energie")){
		tx_topBarplotText1.setText("Energiebalans");
		f_setEnergyBalanceChartFull(area.v_annualSelfConsumedEnergy_MWh, area.v_annualElectricityImport_MWh, 
							area.v_annualNaturalGasDemand_MWh, area.v_annualPetroleumProductsDemand_MWh, area.v_annualHydrogenDemand_MWh, area.v_annualElectricityExport_MWh, 
							pl_consumptionChartTop, pl_productionChartTop,
							t_opwekTextTop, t_gebruikTextTop);
	}
	else if( graph.contains( "Elektriciteit")){	
		tx_topBarplotText1.setText("Elektriciteitsbalans");
		if (area == v_trafo) {
			f_setEnergyBalanceChartTrafo(area.v_annualSelfConsumedElectricity_MWh, area.v_annualSelfProducedElectricity_MWh, area.v_annualElectricityImport_MWh, area.v_annualElectricityExport_MWh, 
						pl_consumptionChartTop, pl_productionChartTop,
						t_opwekTextTop, t_gebruikTextTop);
		}
		else {
			f_setEnergyBalanceChartSimple(area.v_annualSelfConsumedElectricity_MWh, area.v_annualElectricityImport_MWh, area.v_annualElectricityExport_MWh, 
						pl_consumptionChartTop, pl_productionChartTop,
						t_opwekTextTop, t_gebruikTextTop);
		}
		t_topBarplotText.setText("Jaaroverzicht");
	}
	
}
else if( graph.contains( "Zomer/winterweek")){	
	if( graph.contains( "Energie")){			
		tx_topBarplotText1.setText("Energiebalans");
		f_setEnergyBalanceChartFullBoth(area.v_selfConsumedElectricitySummerWeek_MWh, area.v_importedElectricitySummerWeek_MWh, 
									area.v_naturalGasDemandSummerWeek_MWh, area.v_petroleumProductsDemandSummerWeek_MWh, area.v_hydrogenImportSummerWeek_MWh,
									area.v_exportedElectricitySummerWeek_MWh,
									pl_consumptionChartTop, pl_productionChartTop,
									t_opwekTextTop, t_gebruikTextTop,
									area.v_selfConsumedEnergyWinterWeek_MWh, area.v_importedElectricityWinterWeek_MWh, 
									area.v_naturalGasDemandWinterWeek_MWh, area.v_petroleumProductsDemandWinterWeek_MWh, area.v_hydrogenImportWinterWeek_MWh,
									area.v_exportedElectricityWinterWeek_MWh,
									pl_consumptionChartBottom, pl_productionChartBottom,
									t_opwekTextBottom, t_gebruikTextBottom);
		
	}
	else if( graph.contains( "Elektriciteit")){	
		tx_topBarplotText1.setText("Elektriciteitsbalans");	
		f_setEnergyBalanceChartSimpleBoth(area.v_selfConsumedElectricitySummerWeek_MWh, area.v_importedElectricitySummerWeek_MWh, 
									area.v_exportedElectricitySummerWeek_MWh,
									pl_consumptionChartTop, pl_productionChartTop,
									t_opwekTextTop, t_gebruikTextTop,
									area.v_selfConsumedElectricityWinterWeek_MWh, area.v_importedElectricityWinterWeek_MWh,								
									area.v_exportedElectricityWinterWeek_MWh,
									pl_consumptionChartBottom, pl_productionChartBottom,
									t_opwekTextBottom, t_gebruikTextBottom);
	}
	t_topBarplotText.setText("Zomerweek");
	t_bottomBarplotText.setText("Winterweek");
}
else if( graph.contains( "Dag/nacht")){	
	if( graph.contains( "Energie")){	
		tx_topBarplotText1.setText("Energiebalans");
		f_setEnergyBalanceChartFullBoth(area.v_selfConsumedElectricityDaytime_MWh, area.v_importedElectricityDaytime_MWh, 
									area.v_naturalGasDemandDaytime_MWh, area.v_petroleumProductsDemandDaytime_MWh, area.v_hydrogenImportDaytime_MWh,
									area.v_exportedElectricityDaytime_MWh,
									pl_consumptionChartTop, pl_productionChartTop,
									t_opwekTextTop, t_gebruikTextTop,
									area.v_selfConsumedEnergyNighttime_MWh, area.v_importedElectricityNighttime_MWh, 
									area.v_naturalGasDemandNighttime_MWh, area.v_petroleumProductsDemandNighttime_MWh, area.v_hydrogenImportNighttime_MWh,
									area.v_exportedElectricityNighttime_MWh,
									pl_consumptionChartBottom, pl_productionChartBottom,
									t_opwekTextBottom, t_gebruikTextBottom);
	}
	else if( graph.contains( "Elektriciteit")){
				tx_topBarplotText1.setText("Elektriciteitsbalans");
				f_setEnergyBalanceChartSimpleBoth(area.v_selfConsumedElectricityDaytime_MWh, area.v_importedElectricityDaytime_MWh, 
									area.v_exportedElectricityDaytime_MWh,
									pl_consumptionChartTop, pl_productionChartTop,
									t_opwekTextTop, t_gebruikTextTop,
									area.v_selfConsumedElectricityNighttime_MWh, area.v_importedElectricityNighttime_MWh,								
									area.v_exportedElectricityNighttime_MWh,
									pl_consumptionChartBottom, pl_productionChartBottom,
									t_opwekTextBottom, t_gebruikTextBottom);
	}
	t_topBarplotText.setText("Totaal overdag");
	t_bottomBarplotText.setText("Totaal 's nachts");
}
else if( graph.contains( "Week/weekend")){	
	if( graph.contains( "Energie")){	
		tx_topBarplotText1.setText("Energiebalans");
		f_setEnergyBalanceChartFullBoth(area.v_selfConsumedElectricityWeekday_MWh, area.v_importedElectricityWeekday_MWh, 
									area.v_naturalGasDemandWeekday_MWh, area.v_petroleumProductsDemandWeekday_MWh, area.v_hydrogenImportWeekday_MWh,
									area.v_exportedElectricityWeekday_MWh,
									pl_consumptionChartTop, pl_productionChartTop,
									t_opwekTextTop, t_gebruikTextTop,
									area.v_selfConsumedEnergyWeekend_MWh, area.v_importedElectricityWeekend_MWh, 
									area.v_naturalGasDemandWeekend_MWh, area.v_petroleumProductsDemandWeekend_MWh, area.v_hydrogenImportWeekend_MWh,
									area.v_exportedElectricityWeekend_MWh,
									pl_consumptionChartBottom, pl_productionChartBottom,
									t_opwekTextBottom, t_gebruikTextBottom);
	}
	else if( graph.contains( "Elektriciteit")){	
				tx_topBarplotText1.setText("Elektriciteitsbalans");
				f_setEnergyBalanceChartSimpleBoth(area.v_selfConsumedElectricityWeekday_MWh, area.v_importedElectricityWeekday_MWh, 
									area.v_exportedElectricityWeekday_MWh,
									pl_consumptionChartTop, pl_productionChartTop,
									t_opwekTextTop, t_gebruikTextTop,
									area.v_selfConsumedElectricityWeekend_MWh, area.v_importedElectricityWeekend_MWh,								
									area.v_exportedElectricityWeekend_MWh,
									pl_consumptionChartBottom, pl_productionChartBottom,
									t_opwekTextBottom, t_gebruikTextBottom);
	}
	t_topBarplotText.setText("Weekdagen");
	t_bottomBarplotText.setText("Weekend");
}


if(v_graphString.contains( "Jaar")){
	gr_onbalansBarplot2.setVisible(false);
}
else{
	gr_onbalansBarplot2.setVisible(true);
	
}


/*ALCODEEND*/}

double f_resetCharts()
{/*ALCODESTART::1732117759428*/
pl_productionChartYear.removeAll();
t_productionTextYear.setText("Opwek");
pl_consumptionChartYear.removeAll();
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
{/*ALCODESTART::1732117759438*/
gr_Year.setVisible(false);
gr_SummerWinter.setVisible(false);
gr_DayNight.setVisible(false);
gr_WeekdayWeekend.setVisible(false);

if (radio_period.getValue() == 0) {
	gr_Year.setVisible(true);
} else if (radio_period.getValue() == 1) {
	gr_SummerWinter.setVisible(true);
} else if (radio_period.getValue() == 2) {
	gr_DayNight.setVisible(true);
} else if (radio_period.getValue() == 3) {
	gr_WeekdayWeekend.setVisible(true);
}
/*ALCODEEND*/}

double f_setCharts()
{/*ALCODESTART::1732117901396*/
f_resetCharts();
f_setVisiblity();
I_EnergyData data = uI_Results.f_getSelectedObjectData();

uI_Results.f_setSelectedObjectDisplay(0, 0, false);

EnergyCoop coop = (EnergyCoop)data;
f_setElectricityBalanceChartYear(coop);

/*
if (uI_Results.v_selectedObjectType == OL_SelectedObjectType.GRIDNODE) {
	f_setTrafoBalanceChartYear(area);
	f_setTrafoBalanceChartSummerWinter(area);
	f_setTrafoBalanceChartDayNight(area);
	f_setTrafoBalanceChartWeekdayWeekend(area);
	}
else {
	if( radio_energyType.getValue() == 0){
		f_setElectricityBalanceChartYear(area);
		f_setElectricityBalanceChartSummerWinter(area);
		f_setElectricityBalanceChartDayNight(area);
		f_setElectricityBalanceChartWeekdayWeekend(area);
	}
	else if( radio_energyType.getValue() == 1){
		f_setEnergyBalanceChartYear(area);
		f_setEnergyBalanceChartSummerWinter(area);
		f_setEnergyBalanceChartDayNight(area);
		f_setEnergyBalanceChartWeekdayWeekend(area);
	}
}
*/
// chart scale?

/*ALCODEEND*/}

double f_setElectricityBalanceChartYear(EnergyCoop coop)
{/*ALCODESTART::1732117901398*/
//double production_MWh = coop.getRapidRunData().getTotalElectricitySelfConsumed_MWh() + coop.getRapidRunData().getTotalExport_MWh(OL_EnergyCarriers.ELECTRICITY);
double productionTotal_MWh = coop.getRapidRunData().getTotalElectricityProduced_MWh(); // is this the same?

//double consumption_MWh = coop.getRapidRunData().getTotalElectricitySelfConsumed_MWh() + coop.getRapidRunData().getTotalImport_MWh(OL_EnergyCarriers.ELECTRICITY);
//coop.getRapidRunData().getTotalElectricityConsumed_MWh(); // is this the same?

double import_MWh = coop.getRapidRunData().getTotalImport_MWh(OL_EnergyCarriers.ELECTRICITY);
double export_MWh = coop.getRapidRunData().getTotalExport_MWh(OL_EnergyCarriers.ELECTRICITY);
double simultaneousDelivery_MWh = coop.v_totalCustomerDelivery_MWh - import_MWh; //coop.getRapidRunData().getTotalElectricitySelfConsumed_MWh();
double batteryDeltaEnergy_MWh = productionTotal_MWh - export_MWh - simultaneousDelivery_MWh;

DataItem annualSelfConsumed = new DataItem();
annualSelfConsumed.setValue(simultaneousDelivery_MWh + min(0,batteryDeltaEnergy_MWh));
pl_productionChartYear.addDataItem(annualSelfConsumed, "Lokaal geleverd [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartYear.addDataItem(annualSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem annualImport = new DataItem();
annualImport.setValue(import_MWh);
pl_consumptionChartYear.addDataItem(annualImport, "Tekorten ingekocht [MWh]", uI_Results.v_importedEnergyColor);

DataItem annualExport = new DataItem();
annualExport.setValue(export_MWh);
pl_productionChartYear.addDataItem(annualExport, "Overschotten verkocht [MWh]", uI_Results.v_exportedEnergyColor);

boolean coopBattPresent = coop.v_rapidRunData.am_assetFlowsAccumulators_kW.keySet().contains(OL_AssetFlowCategories.batteriesChargingPower_kW);
//traceln("Battery present: %s", coopBattPresent);
if (coopBattPresent) {
	if (batteryDeltaEnergy_MWh>1) {
		DataItem batteryDelta = new DataItem();
		batteryDelta.setValue(batteryDeltaEnergy_MWh);
		pl_productionChartYear.addDataItem(batteryDelta, "Energie naar batterij [MWh]", uI_Results.v_electricityForStorageDemandColor);
	} else if (batteryDeltaEnergy_MWh < -1) {
		DataItem batteryDelta = new DataItem();
		batteryDelta.setValue(-batteryDeltaEnergy_MWh);
		pl_consumptionChartYear.addDataItem(batteryDelta, "Energie uit batterij [MWh]", uI_Results.v_electricityForStorageDemandColor);
	}
}
//double productionTotal_MWh = simultaneousDelivery_MWh + export_MWh + batteryDeltaEnergy_MWh;
double deliveryTotal_MWh = (simultaneousDelivery_MWh+import_MWh);
double chartScale_MWh = max(productionTotal_MWh, deliveryTotal_MWh);
pl_consumptionChartYear.setFixedScale(chartScale_MWh);
pl_productionChartYear.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_productionTextYear.setText("Opwek + teruglevering klanten" + System.lineSeparator() + roundToInt(productionTotal_MWh*1000) + " kWh");
	t_consumptionTextYear.setText("Levering" + System.lineSeparator() + roundToInt(deliveryTotal_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_productionTextYear.setText("Opwek + teruglevering klanten" + System.lineSeparator() + roundToInt(productionTotal_MWh) + " MWh");
	t_consumptionTextYear.setText("Levering" + System.lineSeparator() + roundToInt(deliveryTotal_MWh) + " MWh");
} else {
	t_productionTextYear.setText("Opwek + teruglevering klanten" + System.lineSeparator() + roundToDecimal(productionTotal_MWh/1000, 1) + " GWh");
	t_consumptionTextYear.setText("Levering" + System.lineSeparator() + roundToDecimal(deliveryTotal_MWh/1000,1) + " GWh");
}
/*ALCODEEND*/}

