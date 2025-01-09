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
AreaCollection area = uI_Results.f_getDataObject();

if (uI_Results.v_selectedObjectType == OL_GISObjectType.GRIDNODE) {
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

// chart scale?

/*ALCODEEND*/}

double f_setElectricityBalanceChartYear(AreaCollection area)
{/*ALCODESTART::1732117901398*/
DataItem annualSelfConsumed = new DataItem();
annualSelfConsumed.setValue(area.v_totalElectricitySelfConsumed_MWh);
pl_productionChartYear.addDataItem(annualSelfConsumed, "Lokaal geleverd [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartYear.addDataItem(annualSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem annualImport = new DataItem();
annualImport.setValue(area.fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartYear.addDataItem(annualImport, "Tekorten ingekocht [MWh]", uI_Results.v_importedEnergyColor);

DataItem annualExport = new DataItem();
annualExport.setValue(area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartYear.addDataItem(annualExport, "Overschotten verkocht [MWh]", uI_Results.v_exportedEnergyColor);

double production_MWh = area.v_totalElectricitySelfConsumed_MWh + area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double consumption_MWh = area.v_totalElectricitySelfConsumed_MWh + area.fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double chartScale_MWh = max(production_MWh, consumption_MWh);
pl_consumptionChartYear.setFixedScale(chartScale_MWh);
pl_productionChartYear.setFixedScale(chartScale_MWh);


if (chartScale_MWh<10) {
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToInt(production_MWh*1000) + " kWh");
	t_consumptionTextYear.setText("Levering" + System.lineSeparator() + roundToInt(consumption_MWh*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToInt(production_MWh) + " MWh");
	t_consumptionTextYear.setText("Levering" + System.lineSeparator() + roundToInt(consumption_MWh) + " MWh");
} else {
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToDecimal(production_MWh/1000, 1) + " GWh");
	t_consumptionTextYear.setText("Levering" + System.lineSeparator() + roundToDecimal(consumption_MWh/1000,1) + " GWh");
}
/*ALCODEEND*/}

double f_setElectricityBalanceChartSummerWinter(AreaCollection area)
{/*ALCODESTART::1732117901400*/
// Summer
DataItem summerSelfConsumed = new DataItem();
summerSelfConsumed.setValue(area.v_summerWeekElectricitySelfConsumed_MWh);
pl_productionChartSummer.addDataItem(summerSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartSummer.addDataItem(summerSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem summerImport = new DataItem();
summerImport.setValue(area.fm_summerWeekImports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartSummer.addDataItem(summerImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem summerExport = new DataItem();
summerExport.setValue(area.fm_summerWeekExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartSummer.addDataItem(summerExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Winter
DataItem winterSelfConsumed = new DataItem();
winterSelfConsumed.setValue(area.v_winterWeekElectricitySelfConsumed_MWh);
pl_productionChartWinter.addDataItem(winterSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartWinter.addDataItem(winterSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem winterImport = new DataItem();
winterImport.setValue(area.fm_winterWeekImports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartWinter.addDataItem(winterImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem winterExport = new DataItem();
winterExport.setValue(area.fm_winterWeekExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartWinter.addDataItem(winterExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);


double summerProduction_MWh = area.v_summerWeekElectricityProduced_MWh;
double summerConsumption_MWh = area.v_summerWeekElectricityConsumed_MWh;
double winterProduction_MWh = area.v_winterWeekElectricityProduced_MWh;
double winterConsumption_MWh = area.v_winterWeekElectricityConsumed_MWh;
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

double f_setElectricityBalanceChartDayNight(AreaCollection area)
{/*ALCODESTART::1732117901402*/
// Day
DataItem daytimeSelfConsumed = new DataItem();
daytimeSelfConsumed.setValue(area.v_daytimeElectricitySelfConsumed_MWh);
pl_productionChartDay.addDataItem(daytimeSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartDay.addDataItem(daytimeSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);



DataItem daytimeImport = new DataItem();
daytimeImport.setValue(area.fm_daytimeImports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartDay.addDataItem(daytimeImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);


DataItem daytimeExport = new DataItem();
daytimeExport.setValue(area.fm_daytimeExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartDay.addDataItem(daytimeExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);


// Night
DataItem nighttimeSelfConsumed = new DataItem();
nighttimeSelfConsumed.setValue(area.v_nighttimeElectricitySelfConsumed_MWh);
pl_productionChartNight.addDataItem(nighttimeSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartNight.addDataItem(nighttimeSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem nighttimeImport = new DataItem();
nighttimeImport.setValue(area.fm_nighttimeImports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartNight.addDataItem(nighttimeImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem nighttimeExport = new DataItem();
nighttimeExport.setValue(area.fm_nighttimeExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartNight.addDataItem(nighttimeExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);



double dayProduction_MWh = area.v_daytimeElectricityProduced_MWh;
double dayConsumption_MWh = area.v_daytimeElectricityConsumed_MWh;
double nightProduction_MWh = area.v_nighttimeElectricityProduced_MWh;
double nightConsumption_MWh = area.v_nighttimeElectricityConsumed_MWh;
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

double f_setElectricityBalanceChartWeekdayWeekend(AreaCollection area)
{/*ALCODESTART::1732117901404*/
// Weekday
DataItem weekdaySelfConsumed = new DataItem();
weekdaySelfConsumed.setValue(area.v_weekdayElectricitySelfConsumed_MWh);
pl_productionChartWeekday.addDataItem(weekdaySelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartWeekday.addDataItem(weekdaySelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem weekdayimeImport = new DataItem();
weekdayimeImport.setValue(area.fm_weekdayImports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartWeekday.addDataItem(weekdayimeImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem weekdayExport = new DataItem();
weekdayExport.setValue(area.fm_weekdayExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartWeekday.addDataItem(weekdayExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Weekend
DataItem weekendSelfConsumed = new DataItem();
weekendSelfConsumed.setValue(area.v_weekendElectricitySelfConsumed_MWh);
pl_productionChartWeekend.addDataItem(weekendSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartWeekend.addDataItem(weekendSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem weekendImport = new DataItem();
weekendImport.setValue(area.fm_weekendImports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartWeekend.addDataItem(weekendImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem weekendExport = new DataItem();
weekendExport.setValue(area.fm_weekendExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartWeekend.addDataItem(weekendExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);



double weekdayProduction_MWh = area.v_weekdayElectricityProduced_MWh;
double weekdayConsumption_MWh = area.v_weekdayElectricityConsumed_MWh;
double weekendProduction_MWh = area.v_weekendElectricityProduced_MWh;
double weekendConsumption_MWh = area.v_weekendElectricityConsumed_MWh;
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

double f_setEnergyBalanceChartYear(AreaCollection area)
{/*ALCODESTART::1732117901406*/
// SelfConsumption
DataItem annualSelfConsumed = new DataItem();
annualSelfConsumed.setValue(area.v_totalEnergySelfConsumed_MWh);
pl_productionChartYear.addDataItem(annualSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedEnergyColor);
pl_consumptionChartYear.addDataItem(annualSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedEnergyColor);

/*
DataItem annualImport = new DataItem();
annualImport.setValue(area.fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartYear.addDataItem(annualImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem annualExport = new DataItem();
annualExport.setValue(area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartYear.addDataItem(annualExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);
*/

for (OL_EnergyCarriers EC : uI_Results.energyModel.v_activeEnergyCarriers) {
	// Production
	if (area.fm_totalExports_MWh.get(EC) > uI_Results.p_cutOff_MWh) {
		DataItem totalExport = new DataItem();
		totalExport.setValue(area.fm_totalExports_MWh.get(EC));
		pl_productionChartYear.addDataItem(totalExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Consumption
	if (area.fm_totalImports_MWh.get(EC) > uI_Results.p_cutOff_MWh) {
		DataItem totalImport = new DataItem();
		totalImport.setValue(area.fm_totalImports_MWh.get(EC));
		pl_consumptionChartYear.addDataItem(totalImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_consumptionColors.get(EC));
	}
}

/*
DataItem annualGasDemand = new DataItem();
annualGasDemand.setValue(area.fm_totalImports_MWh.get(OL_EnergyCarriers.METHANE));
pl_consumptionChartYear.addDataItem(annualGasDemand, "Aardgas [MWh]", uI_Results.v_naturalGasDemandColor);

DataItem annualPetroleumDemand = new DataItem();
annualPetroleumDemand.setValue(area.fm_totalImports_MWh.get(OL_EnergyCarriers.DIESEL));
pl_consumptionChartYear.addDataItem(annualPetroleumDemand, "Benzine/diesel[MWh]", uI_Results.v_petroleumProductsDemandColor);

DataItem annualHydrogenDemand = new DataItem();
annualHydrogenDemand.setValue(area.fm_totalImports_MWh.get(OL_EnergyCarriers.HYDROGEN));
pl_consumptionChartYear.addDataItem(annualHydrogenDemand, "Waterstof gebruikt [MWh]", uI_Results.v_hydrogenDemandColor);

DataItem annualHydrogenSupply = new DataItem();
annualHydrogenSupply.setValue(area.fm_totalExports_MWh.get(OL_EnergyCarriers.HYDROGEN));
pl_productionChartYear.addDataItem(annualHydrogenSupply, "Waterstof geproduceerd [MWh]", uI_Results.v_hydrogenSupplyColor);
*/

double production_MWh = area.v_totalEnergyProduced_MWh;
double consumption_MWh = area.v_totalEnergyConsumed_MWh;
double chartScale_MWh = max(production_MWh, consumption_MWh);
pl_consumptionChartYear.setFixedScale(chartScale_MWh);
pl_productionChartYear.setFixedScale(chartScale_MWh);

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

double f_setEnergyBalanceChartSummerWinter(AreaCollection area)
{/*ALCODESTART::1732117901408*/
// Summer SelfConsumption
DataItem summerSelfConsumed = new DataItem();
summerSelfConsumed.setValue(area.v_summerWeekEnergySelfConsumed_MWh);
pl_productionChartSummer.addDataItem(summerSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartSummer.addDataItem(summerSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);

// Winter SelfConsumption
DataItem winterSelfConsumed = new DataItem();
winterSelfConsumed.setValue(area.v_winterWeekEnergySelfConsumed_MWh);
pl_productionChartWinter.addDataItem(winterSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartWinter.addDataItem(winterSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);


/*
DataItem summerImport = new DataItem();
summerImport.setValue(area.fm_summerWeekImports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartSummer.addDataItem(summerImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem summerExport = new DataItem();
summerExport.setValue(area.fm_summerWeekExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartSummer.addDataItem(summerExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);
*/

for (OL_EnergyCarriers EC : uI_Results.energyModel.v_activeEnergyCarriers) {
	// Summer Production
	if (area.fm_summerWeekExports_MWh.get(EC) > uI_Results.p_cutOff_MWh) {
		DataItem summerExport = new DataItem();
		summerExport.setValue(area.fm_summerWeekExports_MWh.get(EC));
		pl_productionChartSummer.addDataItem(summerExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Summer Consumption
	if (area.fm_summerWeekImports_MWh.get(EC) > uI_Results.p_cutOff_MWh) {
		DataItem summerImport = new DataItem();
		summerImport.setValue(area.fm_summerWeekImports_MWh.get(EC));
		pl_consumptionChartSummer.addDataItem(summerImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Winter Production
	if (area.fm_winterWeekExports_MWh.get(EC) > uI_Results.p_cutOff_MWh) {
		DataItem winterExport = new DataItem();
		winterExport.setValue(area.fm_winterWeekExports_MWh.get(EC));
		pl_productionChartWinter.addDataItem(winterExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Winter Consumption
	if (area.fm_winterWeekImports_MWh.get(EC) > uI_Results.p_cutOff_MWh) {
		DataItem winterImport = new DataItem();
		winterImport.setValue(area.fm_winterWeekImports_MWh.get(EC));
		pl_consumptionChartWinter.addDataItem(winterImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
}
/*
DataItem summerGasDemand = new DataItem();
summerGasDemand.setValue(area.fm_summerWeekImports_MWh.get(OL_EnergyCarriers.METHANE));
pl_consumptionChartSummer.addDataItem(summerGasDemand, "Aardgas [MWh]", uI_Results.v_naturalGasDemandColor);

DataItem summerPetroleumDemand = new DataItem();
summerPetroleumDemand.setValue(area.fm_summerWeekImports_MWh.get(OL_EnergyCarriers.DIESEL));
pl_consumptionChartSummer.addDataItem(summerPetroleumDemand, "Benzine/diesel[MWh]", uI_Results.v_petroleumProductsDemandColor);

DataItem summerHydrogenDemand = new DataItem();
summerHydrogenDemand.setValue(area.fm_summerWeekImports_MWh.get(OL_EnergyCarriers.HYDROGEN));
pl_consumptionChartSummer.addDataItem(summerHydrogenDemand, "Waterstof gebruikt [MWh]", uI_Results.v_hydrogenDemandColor);

DataItem summerHydrogenSupply = new DataItem();
summerHydrogenSupply.setValue(area.fm_summerWeekExports_MWh.get(OL_EnergyCarriers.HYDROGEN));
pl_productionChartSummer.addDataItem(summerHydrogenSupply, "Waterstof geproduceerd [MWh]", uI_Results.v_hydrogenSupplyColor);
*/



/*
DataItem winterImport = new DataItem();
winterImport.setValue(area.fm_winterWeekImports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartWinter.addDataItem(winterImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem winterExport = new DataItem();
winterExport.setValue(area.fm_winterWeekExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartWinter.addDataItem(winterExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);


DataItem winterGasDemand = new DataItem();
winterGasDemand.setValue(area.fm_winterWeekImports_MWh.get(OL_EnergyCarriers.METHANE));
pl_consumptionChartWinter.addDataItem(winterGasDemand, "Aardgas [MWh]", uI_Results.v_naturalGasDemandColor);

DataItem winterPetroleumDemand = new DataItem();
winterPetroleumDemand.setValue(area.fm_winterWeekImports_MWh.get(OL_EnergyCarriers.DIESEL));
pl_consumptionChartWinter.addDataItem(winterPetroleumDemand, "Benzine/diesel[MWh]", uI_Results.v_petroleumProductsDemandColor);

DataItem winterHydrogenDemand = new DataItem();
winterHydrogenDemand.setValue(area.fm_winterWeekImports_MWh.get(OL_EnergyCarriers.HYDROGEN));
pl_consumptionChartWinter.addDataItem(winterHydrogenDemand, "Waterstof gebruikt [MWh]", uI_Results.v_hydrogenDemandColor);

DataItem winterHydrogenSupply = new DataItem();
winterHydrogenSupply.setValue(area.fm_winterWeekExports_MWh.get(OL_EnergyCarriers.HYDROGEN));
pl_productionChartWinter.addDataItem(winterHydrogenSupply, "Waterstof geproduceerd [MWh]", uI_Results.v_hydrogenSupplyColor);
*/


double summerProduction_MWh = area.v_summerWeekEnergyProduced_MWh;
double summerConsumption_MWh = area.v_summerWeekEnergyConsumed_MWh;
double winterProduction_MWh = area.v_winterWeekEnergyProduced_MWh;
double winterConsumption_MWh = area.v_winterWeekEnergyConsumed_MWh;
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

double f_setEnergyBalanceChartWeekdayWeekend(AreaCollection area)
{/*ALCODESTART::1732117901410*/
// Weekday SelfConsumption
DataItem weekdaySelfConsumed = new DataItem();
weekdaySelfConsumed.setValue(area.v_weekdayEnergySelfConsumed_MWh);
pl_productionChartWeekday.addDataItem(weekdaySelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartWeekday.addDataItem(weekdaySelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);

// Weekend SelfConsumption
DataItem weekendSelfConsumed = new DataItem();
weekendSelfConsumed.setValue(area.v_weekendEnergySelfConsumed_MWh);
pl_productionChartWeekend.addDataItem(weekendSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartWeekend.addDataItem(weekendSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);


/*
DataItem weekdayimeImport = new DataItem();
weekdayimeImport.setValue(area.fm_weekdayImports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartWeekday.addDataItem(weekdayimeImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem weekdayExport = new DataItem();
weekdayExport.setValue(area.fm_weekdayExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartWeekday.addDataItem(weekdayExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);
*/

for (OL_EnergyCarriers EC : uI_Results.energyModel.v_activeEnergyCarriers) {
	// Weekday Production
	if (area.fm_weekdayExports_MWh.get(EC) > uI_Results.p_cutOff_MWh) {	
		DataItem weekdayExport = new DataItem();
		weekdayExport.setValue(area.fm_weekdayExports_MWh.get(EC));
		pl_productionChartWeekday.addDataItem(weekdayExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Weekday Consumption
	if (area.fm_weekdayImports_MWh.get(EC) > uI_Results.p_cutOff_MWh) {	
		DataItem weekdayImport = new DataItem();
		weekdayImport.setValue(area.fm_weekdayImports_MWh.get(EC));
		pl_consumptionChartWeekday.addDataItem(weekdayImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Weekend Production
	if (area.fm_weekendExports_MWh.get(EC) > uI_Results.p_cutOff_MWh) {	
		DataItem weekendExport = new DataItem();
		weekendExport.setValue(area.fm_weekendExports_MWh.get(EC));
		pl_productionChartWeekend.addDataItem(weekendExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Weekend Consumption
	if (area.fm_weekendImports_MWh.get(EC) > uI_Results.p_cutOff_MWh) {	
		DataItem weekendImport = new DataItem();
		weekendImport.setValue(area.fm_weekendImports_MWh.get(EC));
		pl_consumptionChartWeekend.addDataItem(weekendImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
}

/*
DataItem weekdaytimeGasDemand = new DataItem();
weekdaytimeGasDemand.setValue(area.fm_weekdayImports_MWh.get(OL_EnergyCarriers.METHANE));
pl_consumptionChartWeekday.addDataItem(weekdaytimeGasDemand, "Aardgas [MWh]", uI_Results.v_naturalGasDemandColor);

DataItem weekdayPetroleumDemand = new DataItem();
weekdayPetroleumDemand.setValue(area.fm_weekdayImports_MWh.get(OL_EnergyCarriers.DIESEL));
pl_consumptionChartWeekday.addDataItem(weekdayPetroleumDemand, "Benzine/diesel[MWh]", uI_Results.v_petroleumProductsDemandColor);

DataItem weekdayHydrogenDemand = new DataItem();
weekdayHydrogenDemand.setValue(area.fm_weekdayImports_MWh.get(OL_EnergyCarriers.HYDROGEN));
pl_consumptionChartWeekday.addDataItem(weekdayHydrogenDemand, "Waterstof gebruikt [MWh]", uI_Results.v_hydrogenDemandColor);

DataItem weekdayHydrogenSupply = new DataItem();
weekdayHydrogenSupply.setValue(area.fm_weekdayExports_MWh.get(OL_EnergyCarriers.HYDROGEN));
pl_productionChartWeekday.addDataItem(weekdayHydrogenSupply, "Waterstof geproduceerd [MWh]", uI_Results.v_hydrogenSupplyColor);
*/



/*
DataItem weekendImport = new DataItem();
weekendImport.setValue(area.fm_weekendImports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartWeekend.addDataItem(weekendImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem weekendExport = new DataItem();
weekendExport.setValue(area.fm_weekendExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartWeekend.addDataItem(weekendExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);


DataItem weekendGasDemand = new DataItem();
weekendGasDemand.setValue(area.fm_weekendImports_MWh.get(OL_EnergyCarriers.METHANE));
pl_consumptionChartWeekend.addDataItem(weekendGasDemand, "Aardgas [MWh]", uI_Results.v_naturalGasDemandColor);

DataItem weekendPetroleumDemand = new DataItem();
weekendPetroleumDemand.setValue(area.fm_weekendImports_MWh.get(OL_EnergyCarriers.DIESEL));
pl_consumptionChartWeekend.addDataItem(weekendPetroleumDemand, "Benzine/diesel[MWh]", uI_Results.v_petroleumProductsDemandColor);

DataItem weekendHydrogenDemand = new DataItem();
weekendHydrogenDemand.setValue(area.fm_weekendImports_MWh.get(OL_EnergyCarriers.HYDROGEN));
pl_consumptionChartWeekend.addDataItem(weekendHydrogenDemand, "Waterstof gebruikt [MWh]", uI_Results.v_hydrogenDemandColor);					

DataItem weekendHydrogenSupply = new DataItem();
weekendHydrogenSupply.setValue(area.fm_weekendExports_MWh.get(OL_EnergyCarriers.HYDROGEN));
pl_productionChartWeekend.addDataItem(weekendHydrogenSupply, "Waterstof geproduceerd [MWh]", uI_Results.v_hydrogenSupplyColor);
*/

double weekdayProduction_MWh = area.v_weekdayEnergyProduced_MWh;
double weekdayConsumption_MWh = area.v_weekdayEnergyConsumed_MWh;
double weekendProduction_MWh = area.v_weekendEnergyProduced_MWh;
double weekendConsumption_MWh = area.v_weekendEnergyConsumed_MWh;
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

double f_setEnergyBalanceChartDayNight(AreaCollection area)
{/*ALCODESTART::1732117901412*/
// Daytime SelfConsumption
DataItem daytimeSelfConsumed = new DataItem();
daytimeSelfConsumed.setValue(area.v_daytimeEnergySelfConsumed_MWh);
pl_productionChartDay.addDataItem(daytimeSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartDay.addDataItem(daytimeSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);

// Nighttime SelfConsumption
DataItem nighttimeSelfConsumed = new DataItem();
nighttimeSelfConsumed.setValue(area.v_nighttimeEnergySelfConsumed_MWh);
pl_productionChartNight.addDataItem(nighttimeSelfConsumed, "Lokaal opgewekt [MWh]", uI_Results.v_selfConsumedElectricityColor);
pl_consumptionChartNight.addDataItem(nighttimeSelfConsumed, "Lokaal gebruikt [MWh]", uI_Results.v_selfConsumedElectricityColor);


/*
DataItem daytimeImport = new DataItem();
daytimeImport.setValue(area.v_daytimeImports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartDay.addDataItem(daytimeImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem daytimeExport = new DataItem();
daytimeExport.setValue(area.v_daytimeExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartDay.addDataItem(daytimeExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);
*/

for (OL_EnergyCarriers EC : uI_Results.energyModel.v_activeEnergyCarriers) {
	// Daytime Production
	if (area.fm_daytimeExports_MWh.get(EC) > uI_Results.p_cutOff_MWh) {	
		DataItem daytimeExport = new DataItem();
		daytimeExport.setValue(area.fm_daytimeExports_MWh.get(EC));
		pl_productionChartDay.addDataItem(daytimeExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Daytime Consumption
	if (area.fm_daytimeImports_MWh.get(EC) > uI_Results.p_cutOff_MWh) {
		DataItem daytimeImport = new DataItem();
		daytimeImport.setValue(area.fm_daytimeImports_MWh.get(EC));
		pl_consumptionChartDay.addDataItem(daytimeImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Nighttime Production
	if (area.fm_nighttimeExports_MWh.get(EC) > uI_Results.p_cutOff_MWh) {
		DataItem nighttimeExport = new DataItem();
		nighttimeExport.setValue(area.fm_nighttimeExports_MWh.get(EC));
		pl_productionChartNight.addDataItem(nighttimeExport, uI_Results.f_getName(EC) + " Export [MWh]", uI_Results.cm_productionColors.get(EC));
	}
	// Nighttime Consumption
	if (area.fm_nighttimeImports_MWh.get(EC) > uI_Results.p_cutOff_MWh) {
		DataItem nighttimeImport = new DataItem();
		nighttimeImport.setValue(area.fm_nighttimeImports_MWh.get(EC));
		pl_consumptionChartNight.addDataItem(nighttimeImport, uI_Results.f_getName(EC) + " Import [MWh]", uI_Results.cm_productionColors.get(EC));
	}	
}

/*
DataItem daytimeGasDemand = new DataItem();
daytimeGasDemand.setValue(area.v_daytimeImports_MWh.get(OL_EnergyCarriers.METHANE));
pl_consumptionChartDay.addDataItem(daytimeGasDemand, "Aardgas [MWh]", uI_Results.v_naturalGasDemandColor);

DataItem daytimePetroleumDemand = new DataItem();
daytimePetroleumDemand.setValue(area.v_daytimeImports_MWh.get(OL_EnergyCarriers.DIESEL));
pl_consumptionChartDay.addDataItem(daytimePetroleumDemand, "Benzine/diesel[MWh]", uI_Results.v_petroleumProductsDemandColor);

DataItem daytimeHydrogenDemand = new DataItem();
daytimeHydrogenDemand.setValue(area.v_daytimeImports_MWh.get(OL_EnergyCarriers.HYDROGEN));
pl_consumptionChartDay.addDataItem(daytimeHydrogenDemand, "Waterstof gebruikt [MWh]", uI_Results.v_hydrogenDemandColor);


DataItem daytimeHydrogenSupply = new DataItem();
daytimeHydrogenSupply.setValue(area.v_daytimeExports_MWh.get(OL_EnergyCarriers.HYDROGEN));
pl_productionChartDay.addDataItem(daytimeHydrogenSupply, "Waterstof geproduceerd [MWh]", uI_Results.v_hydrogenSupplyColor);
*/


/*
DataItem nighttimeImport = new DataItem();
nighttimeImport.setValue(area.fm_nighttimeImports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartNight.addDataItem(nighttimeImport, "Externe bronnen [MWh]", uI_Results.v_importedEnergyColor);

DataItem nighttimeExport = new DataItem();
nighttimeExport.setValue(area.fm_nighttimeExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_productionChartNight.addDataItem(nighttimeExport, "Teruggeleverde elektriciteit [MWh]", uI_Results.v_exportedEnergyColor);


DataItem nighttimeGasDemand = new DataItem();
nighttimeGasDemand.setValue(area.v_nighttimeImports_MWh.get(OL_EnergyCarriers.METHANE));
pl_consumptionChartNight.addDataItem(nighttimeGasDemand, "Aardgas [MWh]", uI_Results.v_naturalGasDemandColor);

DataItem nighttimePetroleumDemand = new DataItem();
nighttimePetroleumDemand.setValue(area.v_nighttimeImports_MWh.get(OL_EnergyCarriers.DIESEL));
pl_consumptionChartNight.addDataItem(nighttimePetroleumDemand, "Benzine/diesel[MWh]", uI_Results.v_petroleumProductsDemandColor);

DataItem nighttimeHydrogenDemand = new DataItem();
nighttimeHydrogenDemand.setValue(area.v_nighttimeImports_MWh.get(OL_EnergyCarriers.HYDROGEN));
pl_consumptionChartNight.addDataItem(nighttimeHydrogenDemand, "Waterstof gebruikt [MWh]", uI_Results.v_hydrogenDemandColor);					

DataItem nighttimeHydrogenSupply = new DataItem();
nighttimeHydrogenSupply.setValue(area.v_nighttimeExports_MWh.get(OL_EnergyCarriers.HYDROGEN));
pl_productionChartNight.addDataItem(nighttimeHydrogenSupply, "Waterstof geproduceerd [MWh]", uI_Results.v_hydrogenSupplyColor);					
*/

double dayProduction_MWh = area.v_daytimeEnergyProduced_MWh;
double dayConsumption_MWh = area.v_daytimeEnergyConsumed_MWh;
double nightProduction_MWh = area.v_nighttimeEnergyProduced_MWh;
double nightConsumption_MWh = area.v_nighttimeEnergyConsumed_MWh;
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

double f_setTrafoBalanceChartYear(AreaCollection area)
{/*ALCODESTART::1732117901414*/
DataItem annualSelfConsumed = new DataItem();
annualSelfConsumed.setValue(area.fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY) - area.v_annualExcessImport_MWh);
pl_consumptionChartYear.addDataItem(annualSelfConsumed, "Geleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem annualSelfProduced = new DataItem();
annualSelfProduced.setValue(area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY) - area.v_annualExcessExport_MWh);
pl_productionChartYear.addDataItem(annualSelfProduced, "Teruggeleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem annualImport = new DataItem();
annualImport.setValue(area.v_annualExcessExport_MWh);
pl_productionChartYear.addDataItem(annualImport, "Over Capaciteit [MWh]", uI_Results.v_importedEnergyColor);

DataItem annualExport = new DataItem();
annualExport.setValue(area.v_annualExcessImport_MWh);
pl_consumptionChartYear.addDataItem(annualExport, "Over Capaciteit [MWh]", uI_Results.v_exportedEnergyColor);

double chartScale_MWh = max(area.fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY), area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY));
pl_consumptionChartYear.setFixedScale(chartScale_MWh);
pl_productionChartYear.setFixedScale(chartScale_MWh);

if (chartScale_MWh<10) {
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToInt((area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY))*1000) + " kWh");
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToInt((area.fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY))*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToInt((area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY))) + " MWh");
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToInt((area.fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY))) + " MWh");
} else {
	t_productionTextYear.setText("Opwek" + System.lineSeparator() + roundToDecimal((area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY))/1000, 1) + " GWh");
	t_consumptionTextYear.setText("Gebruik" + System.lineSeparator() + roundToDecimal((area.fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY))/1000, 1) + " GWh");
}
/*ALCODEEND*/}

double f_setTrafoBalanceChartSummerWinter(AreaCollection area)
{/*ALCODESTART::1732117901416*/
double summerWeekImport_MWh = area.fm_summerWeekImports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double summerWeekExport_MWh = area.fm_summerWeekExports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double winterWeekImport_MWh = area.fm_winterWeekImports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double winterWeekExport_MWh = area.fm_winterWeekExports_MWh.get(OL_EnergyCarriers.ELECTRICITY);

//// Day
// Consumption
DataItem summerWeekSelfConsumed = new DataItem();
summerWeekSelfConsumed.setValue(summerWeekImport_MWh - area.v_summerWeekExcessImport_MWh);
pl_consumptionChartSummer.addDataItem(summerWeekSelfConsumed, "Geleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem summerWeekExport = new DataItem();
summerWeekExport.setValue(area.v_summerWeekExcessImport_MWh);
pl_consumptionChartSummer.addDataItem(summerWeekExport, "Over Capaciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Production
DataItem summerWeekSelfProduced = new DataItem();
summerWeekSelfProduced.setValue(summerWeekExport_MWh - area.v_summerWeekExcessExport_MWh);
pl_productionChartSummer.addDataItem(summerWeekSelfProduced, "Teruggeleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem summerWeekImport = new DataItem();
summerWeekImport.setValue(area.v_summerWeekExcessExport_MWh);
pl_productionChartSummer.addDataItem(summerWeekImport, "Over Capaciteit [MWh]", uI_Results.v_importedEnergyColor);


//// Night
// Consumption
DataItem winterWeekSelfConsumed = new DataItem();
winterWeekSelfConsumed.setValue(winterWeekImport_MWh - area.v_winterWeekExcessImport_MWh);
pl_consumptionChartWinter.addDataItem(winterWeekSelfConsumed, "Geleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem winterWeekExport = new DataItem();
winterWeekExport.setValue(area.v_winterWeekExcessImport_MWh);
pl_consumptionChartWinter.addDataItem(winterWeekExport, "Over Capaciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Production
DataItem winterWeekSelfProduced = new DataItem();
winterWeekSelfProduced.setValue(winterWeekExport_MWh - area.v_winterWeekExcessExport_MWh);
pl_productionChartWinter.addDataItem(winterWeekSelfProduced, "Teruggeleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem winterWeekImport = new DataItem();
winterWeekImport.setValue(area.v_winterWeekExcessExport_MWh);
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

double f_setTrafoBalanceChartDayNight(AreaCollection area)
{/*ALCODESTART::1732117901418*/
double daytimeImport_MWh = area.fm_daytimeImports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double daytimeExport_MWh = area.fm_daytimeExports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double nighttimeImport_MWh = area.fm_nighttimeImports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double nighttimeExport_MWh = area.fm_nighttimeExports_MWh.get(OL_EnergyCarriers.ELECTRICITY);

//// Day
// Consumption
DataItem daytimeSelfConsumed = new DataItem();
daytimeSelfConsumed.setValue(daytimeImport_MWh - area.v_daytimeExcessImport_MWh);
pl_consumptionChartDay.addDataItem(daytimeSelfConsumed, "Geleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem daytimeExport = new DataItem();
daytimeExport.setValue(area.v_daytimeExcessImport_MWh);
pl_consumptionChartDay.addDataItem(daytimeExport, "Over Capaciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Production
DataItem daytimeSelfProduced = new DataItem();
daytimeSelfProduced.setValue(daytimeExport_MWh - area.v_daytimeExcessExport_MWh);
pl_productionChartDay.addDataItem(daytimeSelfProduced, "Teruggeleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem daytimeImport = new DataItem();
daytimeImport.setValue(area.v_daytimeExcessExport_MWh);
pl_productionChartDay.addDataItem(daytimeImport, "Over Capaciteit [MWh]", uI_Results.v_importedEnergyColor);


//// Night
// Consumption
DataItem nighttimeSelfConsumed = new DataItem();
nighttimeSelfConsumed.setValue(nighttimeImport_MWh - area.v_nighttimeExcessImport_MWh);
pl_consumptionChartNight.addDataItem(nighttimeSelfConsumed, "Geleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem nighttimeExport = new DataItem();
nighttimeExport.setValue(area.v_nighttimeExcessImport_MWh);
pl_consumptionChartNight.addDataItem(nighttimeExport, "Over Capaciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Production
DataItem nighttimeSelfProduced = new DataItem();
nighttimeSelfProduced.setValue(nighttimeExport_MWh - area.v_nighttimeExcessExport_MWh);
pl_productionChartNight.addDataItem(nighttimeSelfProduced, "Teruggeleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem nighttimeImport = new DataItem();
nighttimeImport.setValue(area.v_nighttimeExcessExport_MWh);
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

double f_setTrafoBalanceChartWeekdayWeekend(AreaCollection area)
{/*ALCODESTART::1732117901420*/
double weekdayImport_MWh = area.fm_weekdayImports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double weekdayExport_MWh = area.fm_weekdayExports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double weekendImport_MWh = area.fm_weekendImports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double weekendExport_MWh = area.fm_weekendExports_MWh.get(OL_EnergyCarriers.ELECTRICITY);

//// Day
// Consumption
DataItem weekdaySelfConsumed = new DataItem();
weekdaySelfConsumed.setValue(weekdayImport_MWh - area.v_weekdayExcessImport_MWh);
pl_consumptionChartWeekday.addDataItem(weekdaySelfConsumed, "Geleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem weekdayExport = new DataItem();
weekdayExport.setValue(area.v_weekdayExcessImport_MWh);
pl_consumptionChartWeekday.addDataItem(weekdayExport, "Over Capaciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Production
DataItem weekdaySelfProduced = new DataItem();
weekdaySelfProduced.setValue(weekdayExport_MWh - area.v_weekdayExcessExport_MWh);
pl_productionChartWeekday.addDataItem(weekdaySelfProduced, "Teruggeleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem weekdayImport = new DataItem();
weekdayImport.setValue(area.v_weekdayExcessExport_MWh);
pl_productionChartWeekday.addDataItem(weekdayImport, "Over Capaciteit [MWh]", uI_Results.v_importedEnergyColor);


//// Night
// Consumption
DataItem weekendSelfConsumed = new DataItem();
weekendSelfConsumed.setValue(weekendImport_MWh - area.v_weekendExcessImport_MWh);
pl_consumptionChartWeekend.addDataItem(weekendSelfConsumed, "Geleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem weekendExport = new DataItem();
weekendExport.setValue(area.v_weekendExcessImport_MWh);
pl_consumptionChartWeekend.addDataItem(weekendExport, "Over Capaciteit [MWh]", uI_Results.v_exportedEnergyColor);

// Production
DataItem weekendSelfProduced = new DataItem();
weekendSelfProduced.setValue(weekendExport_MWh - area.v_weekendExcessExport_MWh);
pl_productionChartWeekend.addDataItem(weekendSelfProduced, "Teruggeleverde Stroom [MWh]", uI_Results.v_selfConsumedElectricityColor);

DataItem weekendImport = new DataItem();
weekendImport.setValue(area.v_weekendExcessExport_MWh);
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

