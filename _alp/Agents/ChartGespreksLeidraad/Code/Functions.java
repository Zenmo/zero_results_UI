double f_selectGespreksleidraadCharts()
{/*ALCODESTART::1714765506606*/
f_setWarningScreen(true);

gr_gespreksleidraad1.setVisible(false);
gr_gespreksleidraad2.setVisible(false);
gr_gespreksleidraad3.setVisible(false);
gr_gespreksleidraad4.setVisible(false);
gr_gespreksleidraad5.setVisible(false);
gr_gespreksleidraad6.setVisible(false);
gr_gespreksleidraad3v2.setVisible(false);

I_EnergyData data = uI_Results.f_getSelectedObjectData();


if(data.getScope() != OL_ResultScope.ENERGYMODEL && data.getScope() != OL_ResultScope.ENERGYCOOP){
	f_setWarningScreen(false);
	return;
}


switch(rb_gespreksleidraad.getValue()+1){
	case 1:
		f_setChartsGespreksleidraad1(data);
		f_setChartDescriptionText("i_gespreksleidraad1", false);
		gr_gespreksleidraad1.setVisible(true);
		break;	
	case 2:
		f_setChartsGespreksleidraad2(data);
		f_setChartDescriptionText("i_gespreksleidraad2", false);
		gr_gespreksleidraad2.setVisible(true);	
		break;	
	case 3:
		f_setChartsGespreksleidraad3v2(data);
		f_setChartDescriptionText("i_gespreksleidraad3", false);
		gr_gespreksleidraad3v2.setVisible(true);	
		break;	
	case 4:
		f_setChartsGespreksleidraad4(data);
		f_setChartDescriptionText("i_gespreksleidraad4", false);
		gr_gespreksleidraad4.setVisible(true);	
		break;	
	case 5:
		f_setChartsGespreksleidraad5(data);
		f_setChartDescriptionText("i_gespreksleidraad5", false);
		gr_gespreksleidraad5.setVisible(true);		
		break;
}

/*ALCODEEND*/}

double f_setChartsGespreksleidraad1(I_EnergyData data)
{/*ALCODESTART::1727090219811*/
double selfConsumedEnergy_MWh = data.getRapidRunData().getTotalEnergySelfConsumed_MWh();
double importE_MWh = data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getIntegralPos_kWh()/1000; 
double importG_MWh = data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.METHANE).getIntegralPos_kWh()/1000;
double importF_MWh = data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.DIESEL).getIntegralPos_kWh()/1000; 
double importH_MWh = data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.HYDROGEN).getIntegralPos_kWh()/1000; 
double exportE_MWh = data.getRapidRunData().getTotalEnergyExport_MWh();
double districtHeating_consumption_MWh = data.getRapidRunData().acc_dailyAverageDistrictHeatingConsumption_kW.getIntegral_kWh()/1000;
StackChart pl_consumptionChart = pl_consumptionChartGespreksleidraad1; 
StackChart pl_productionChart = pl_productionChartGespreksleidraad1; 
ShapeText t_OpwekText = t_opwekTextGespreksleidraad1;
ShapeText t_GebruikText = t_gebruikTextGespreksleidraad1;

//Reset charts
pl_productionChart.removeAll();
pl_consumptionChart.removeAll();


//Get production values
DataItem d1 = new DataItem();
d1.setValue(selfConsumedEnergy_MWh/1000);
DataItem d2 = new DataItem();
d2.setValue(exportE_MWh/1000);


pl_consumptionChart.addDataItem(d1,"Lokaal opgewekt [MWh]",v_selfConsumedEnergyColor);
pl_productionChart.addDataItem(d1,"Lokaal gebruik gebied [MWh]",v_selfConsumedEnergyColor);
pl_productionChart.addDataItem(d2,"Teruggeleverde elektriciteit [MWh]",v_exportedEnergyColor);

//Add consumption values
if (importE_MWh > 0) {
	DataItem d3 = new DataItem();
	d3.setValue(importE_MWh/1000);
	pl_consumptionChart.addDataItem(d3,"Electriciteit uit het net [MWh]",v_importedElectricityColor);
}
if (importG_MWh > 0 ) {
	DataItem d4 = new DataItem();
	d4.setValue((importG_MWh)/1000); //Fix for now, cause fed by gas
	pl_consumptionChart.addDataItem(d4,"Aardgas [MWh]",v_naturalGasDemandColor);
}	
if (importF_MWh > 0 ) {
	DataItem d5 = new DataItem();
	d5.setValue(importF_MWh/1000); // fuel
	pl_consumptionChart.addDataItem(d5,"Benzine/diesel[MWh]",v_petroleumProductsDemandColor);
}
if (importH_MWh > 0 ) {
	DataItem d6 = new DataItem();
	d6.setValue(importH_MWh/1000); // Hydrogen
	pl_consumptionChart.addDataItem(d6,"Waterstof [MWh]",v_hydrogenDemandColor);
}
if (districtHeating_consumption_MWh > 0){
	DataItem d8 = new DataItem();
	d8.setValue(districtHeating_consumption_MWh/1000); // Warmtenet
	pl_consumptionChart.addDataItem(d8,"Warmte uit warmtenet [MWh]",v_districtHeatHeatSupplyColor);
}


double chartScale_MWh = max(selfConsumedEnergy_MWh+exportE_MWh, selfConsumedEnergy_MWh+importE_MWh+importG_MWh+importF_MWh+importH_MWh+districtHeating_consumption_MWh);
pl_consumptionChart.setFixedScale(chartScale_MWh/1000);
pl_productionChart.setFixedScale(chartScale_MWh/1000);

if (chartScale_MWh<10) {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+exportE_MWh)*1000) + " kWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+importE_MWh+importG_MWh+importF_MWh+importH_MWh+districtHeating_consumption_MWh)*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+exportE_MWh)) + " MWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+importE_MWh+importG_MWh+importF_MWh+importH_MWh+districtHeating_consumption_MWh)) + " MWh");
} else {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToDecimal((selfConsumedEnergy_MWh+exportE_MWh)/1000, 1) + " GWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToDecimal((selfConsumedEnergy_MWh+importE_MWh+importG_MWh+importF_MWh+importH_MWh+districtHeating_consumption_MWh)/1000,1) + " GWh");
}					 				 
/*ALCODEEND*/}

double f_setChartsGespreksleidraad3(AreaCollection area)
{/*ALCODESTART::1727091662396*/
double annualSelfConsumedElectricityIndividual_MWh;

if (! (area.v_individualSelfconsumptionElectricity_fr > 0) ) {
	annualSelfConsumedElectricityIndividual_MWh = 0.0;
	traceln("NaN detected!");
} else {
	annualSelfConsumedElectricityIndividual_MWh = (area.v_totalElectricitySelfConsumed_MWh + area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY)) * area.v_individualSelfconsumptionElectricity_fr;
}

f_setSelfConsumptionChartGSLD3(annualSelfConsumedElectricityIndividual_MWh, area.v_totalElectricitySelfConsumed_MWh, area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY),
					pl_productionChartIndividual, pl_productionChartCollective);
					
	
/*ALCODEEND*/}

double f_setSelfConsumptionChartGSLD3(double selfConsumedEnergyIndividual_MWh,double selfConsumedEnergyCollective_MWh,double export_MWh,StackChart pl_individualChart,StackChart pl_collectiveChart)
{/*ALCODESTART::1727091662399*/
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
pl_individualChart.addDataItem(d1,"Zelf gebruikt [MWh]",v_selfConsumedElectricityColor_individual);
pl_individualChart.addDataItem(d4,"Teruggeleverd [MWh]",v_exportedEnergyColor);
//pl_consumptionChart.addDataItem(d4,"Overbelasting [MWh]",red);
pl_collectiveChart.addDataItem(d2,"Lokaal gebruikt [MWh]",v_selfConsumedElectricityColor_individual);
pl_collectiveChart.addDataItem(d3,"Geëxporteerd [MWh]",v_exportedEnergyColor);
//pl_productionChart.addDataItem(d5,"Overbelasting [MWh]",red);
double chartScale_MWh = selfConsumedEnergyCollective_MWh+export_MWh;
pl_individualChart.setFixedScale(chartScale_MWh);
pl_collectiveChart.setFixedScale(chartScale_MWh);

/*if (chartScale_MWh<10) {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+export_MWh)*1000) + " kWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+import_MWh)*1000) + " kWh");
} else if (chartScale_MWh<1000) {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+export_MWh)) + " MWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+import_MWh)) + " MWh");
} else {
	t_OpwekText.setText("Opwek" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+export_MWh)/1000) + " GWh");
	t_GebruikText.setText("Gebruik" + System.lineSeparator() + roundToInt((selfConsumedEnergy_MWh+import_MWh)/1000) + " GWh");
}*/
	
/*ALCODEEND*/}

double f_setGespreksleidraadGraphsLocation(double location_x,double location_y,boolean isVisible)
{/*ALCODESTART::1730385220445*/
gr_gespreksleidraad1.setPos(location_x, location_y);
gr_gespreksleidraad2.setPos(location_x, location_y);
gr_gespreksleidraad3.setPos(location_x, location_y);
gr_gespreksleidraad3v2.setPos(location_x, location_y);
gr_gespreksleidraad4.setPos(location_x-30, location_y-130);
gr_gespreksleidraad5.setPos(location_x-30, location_y-30);
gr_gespreksleidraad6.setPos(location_x, location_y);
gr_infoButtonText.setPos(location_x-30, location_y);

gr_gespreksleidraad1.setVisible(isVisible);
gr_gespreksleidraad2.setVisible(isVisible);
gr_gespreksleidraad3.setVisible(isVisible);
gr_gespreksleidraad3v2.setVisible(isVisible);
gr_gespreksleidraad4.setVisible(isVisible);
gr_gespreksleidraad5.setVisible(isVisible);
gr_gespreksleidraad6.setVisible(isVisible);
gr_infoButtonText.setVisible(isVisible);
/*ALCODEEND*/}

double f_setChartDescriptionText(String infoName,boolean infoTextVisible)
{/*ALCODESTART::1730391982666*/
gr_infoButtonText.setVisible(infoTextVisible);
String legendInfo = "";

if(infoName.equals("i_samenvattingSupplyDemand")){
	legendInfo = "Deze grafieken laten zien wat er gebeurt met de totaal jaarlijkse energieopwek, en van welke bronnen het gebruik komt. In en uitvoer worden daarbij per uur bijgehouden, je kunt dus zowel invoer (op momenten van tekort) als uitvoer (op momenten van overschot) hebben in één jaar.";
}
else if(infoName.equals("i_samenvattingAnnual")){
	legendInfo = "In deze grafiek zie je de opwek per dag per energiebron. Daarovereen staat als lijn de totale vraag per dag. In het huidige systeem zal je zien dat de opwek nog in het niet valt bij de totale vraag.";
}
else if(infoName.equals("i_gespreksleidraad1")){
	legendInfo = "Deze grafieken laten zien wat er gebeurt met de totaal jaarlijkse energieopwek, en van welke bronnen het gebruik komt. In en uitvoer worden daarbij per uur bijgehouden, je kunt dus zowel invoer (op momenten van tekort) als uitvoer (op momenten van overschot) hebben in één jaar.";
}
else if(infoName.equals("i_gespreksleidraad2")){
	legendInfo = "In de bovenste grafieken zie je het totale energiegebruik per jaar uitgesplits naar sector. In een scenario met veel warmtepompen en elektrisch vervoer zul je een hoog elektriciteitsverbruik zien in deze sectoren, welke vervolgens ook wordt meegenomen in de elektriciteitsvraag. De onderste grafieken laten de opwek zien naar de totale potentie. De potentie van wind is nog onbekend, de potentie van zon is hierbij afgestemd op geschikt dakoppervlak voor zon op dak.";
}
else if(infoName.equals("i_gespreksleidraad3")){
	legendInfo = "Deze grafieken zijn belangrijk om te zien of buurten lokaal afhankelijk zijn, of dat ze juist gezamenlijk veel potentie tot uitwisseling hebben. Bij een laag aandeel gebruik eigen opwek in de buurt en een hoog aandeel gebruik eigen opwek in de regio betekent het dat buurten onderling goed kunnen uitwisselen. Bij gebruik eigen opwek betekent hetzelfde dat er veel potentie is tussen uitwisseling tussen regio's.";
}
else if(infoName.equals("i_gespreksleidraad4")){
	legendInfo = "De belastingduurkromme rangschikt de netbelasting voor ieder uur van het jaar op volgorde van hoogste levering tot hoogste teruglevering. Hieraan kun je zien of er enkele pieken zijn of langdurige overbelasting, en of dit plaats vindt op afname of op opwek. De netbelasting is indicatief aangezien de netbeheerder hier berekeningen met meer detail en betere data over kan uitvoeren";
}
else if(infoName.equals("i_gespreksleidraad5")){
	legendInfo = "Deze grafiek laat zien wat de effect is over de seizoenen, voor zowel de elektriciteitsvraag als de energievraag. Als het aandeel invoer geminimaliseerd kan worden zal de regio minder afhankelijk van het net worden en meer eigen opwek zelf kunnen gebruiken.";
}


String formattedText = formatText(legendInfo, 42);
t_infoButtonText.setText(formattedText);

/*ALCODEEND*/}

String formatText(String originalText,int maxCharacterCount)
{/*ALCODESTART::1730391982677*/
   
StringBuilder formattedText = new StringBuilder();
StringBuilder currentLine = new StringBuilder();

for (String word : originalText.split("\\s+")) {
	if (currentLine.length() + word.length() <= maxCharacterCount) {
		currentLine.append(word).append(" ");
	} else {
		formattedText.append(currentLine.toString().trim()).append("\n");
		currentLine.setLength(0); // reset current line
		currentLine.append(word).append(" ");
	}
}

// Append the last line
formattedText.append(currentLine.toString().trim());

return formattedText.toString();


/*ALCODEEND*/}

double f_setChartsGespreksleidraad2(I_EnergyData data)
{/*ALCODESTART::1730392911211*/
//Electricity consumption
sc_electricityDemandStack.removeAll();

DataItem annualSelfConsumed_GWh = new DataItem();
annualSelfConsumed_GWh.setValue(data.getRapidRunData().getTotalElectricitySelfConsumed_MWh()/1000);
sc_electricityDemandStack.addDataItem(annualSelfConsumed_GWh, "Lokaal opgewekt", uI_Results.v_selfConsumedElectricityColor);

DataItem annualImport_GWh = new DataItem();
annualImport_GWh.setValue(data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getIntegralPos_kWh()/1000000);
sc_electricityDemandStack.addDataItem(annualImport_GWh, "Invoer", uI_Results.v_importedEnergyColor);


//Heat consumption
sc_heatDemandStack.removeAll();

DataItem totalEnergyConsumptionForDistrictHeating_GWh = new DataItem();
totalEnergyConsumptionForDistrictHeating_GWh.setValue(data.getRapidRunData().acc_dailyAverageDistrictHeatingConsumption_kW.getIntegral_kWh()/1000000);
sc_heatDemandStack.addDataItem(totalEnergyConsumptionForDistrictHeating_GWh, "Energie voor warmtenet", v_districtHeatDemandColor);

DataItem totalElectricityConsumptionHeatpumps_GWh = new DataItem();
totalElectricityConsumptionHeatpumps_GWh.setValue(data.getRapidRunData().acc_dailyAverageHeatPumpElectricityConsumption_kW.getIntegral_kWh()/1000000);
sc_heatDemandStack.addDataItem(totalElectricityConsumptionHeatpumps_GWh, "Elek. voor warmtepompen", v_heatPumpHeatSupplyColor);

DataItem totalNaturalGasDemand_GWh = new DataItem();
totalNaturalGasDemand_GWh.setValue(data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.METHANE).getIntegralPos_kWh()/1000000);
sc_heatDemandStack.addDataItem(totalNaturalGasDemand_GWh, "Gas", v_naturalGasSupplyColor);


// Transport consumption
sc_transportDemandStack.removeAll();


//Set scales
double chartScale_MWh = max(data.getRapidRunData().getTotalElectricitySelfConsumed_MWh() + 
							(data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getIntegralPos_kWh()/1000), 
							(data.getRapidRunData().acc_dailyAverageDistrictHeatingConsumption_kW.getIntegral_kWh()/1000) + 
							(data.getRapidRunData().acc_dailyAverageHeatPumpElectricityConsumption_kW.getIntegral_kWh()/1000) + 
							(data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.METHANE).getIntegralPos_kWh()/1000));
							
sc_electricityDemandStack.setFixedScale(chartScale_MWh/1000);
sc_heatDemandStack.setFixedScale(chartScale_MWh/1000);
sc_transportDemandStack.setFixedScale(chartScale_MWh/1000);


////Production charts

//Calcualte total full load hours production assets for potential energy production

//Full load hours Wind
double totalFullLoadHoursWind_hr = 0;
for (double value : uI_Results.energyModel.tf_p_wind_e_normalized.getValues()) {
    totalFullLoadHoursWind_hr += value;
}

//Full load hours Solar
double totalFullLoadHoursSolar_hr = 0;
for (double value : uI_Results.energyModel.tf_p_solar_e_normalized.getValues()) {
    totalFullLoadHoursSolar_hr += value;
}

//Production (Wind)
sc_windSupplyStack.removeAll();

DataItem totalWindProduction_GWh = new DataItem();
totalWindProduction_GWh.setValue(data.getRapidRunData().acc_dailyAverageWindProduction_kW.getIntegral_kWh()/1000000);
sc_windSupplyStack.addDataItem(totalWindProduction_GWh, "Opwek wind", v_windElectricitySupplyColor);

DataItem totalCurtailedWindEnergy_GWh = new DataItem();
totalCurtailedWindEnergy_GWh.setValue(0); //data.getRapidRunData().v_totalWindEnergyCurtailed_MWh/1000);
sc_windSupplyStack.addDataItem(totalCurtailedWindEnergy_GWh, "Totaal gecurtailde wind energy", v_curtailedColor);

DataItem remainingWindPotential_GWh = new DataItem();
remainingWindPotential_GWh.setValue(max(0,((data.getRapidRunData().assetsMetaData.windPotential_kW - data.getRapidRunData().assetsMetaData.totalInstalledWindPower_kW)*totalFullLoadHoursWind_hr/1000000)));
sc_windSupplyStack.addDataItem(remainingWindPotential_GWh, "Resterende potentie wind energie", v_potentialColor);

//Production (Solar)			
sc_PVSupplyStack.removeAll();

DataItem totalSolarProduction_GWh = new DataItem();
totalSolarProduction_GWh.setValue(data.getRapidRunData().acc_dailyAveragePVProduction_kW.getIntegral_kWh()/1000000);
sc_PVSupplyStack.addDataItem(totalSolarProduction_GWh, "Opwek zon", v_PVElectricitySupplyColor);


DataItem totalCurtailedPVEnergy_GWh = new DataItem();
totalCurtailedPVEnergy_GWh.setValue(0); //data.getRapidRunData().v_totalPVEnergyCurtailed_MWh/1000);
sc_PVSupplyStack.addDataItem(totalCurtailedPVEnergy_GWh, "Totaal gecurtailde zonne energy", v_curtailedColor);

DataItem remainingRoofSolarPotential_GWh = new DataItem();
remainingRoofSolarPotential_GWh.setValue(max(0,((data.getRapidRunData().assetsMetaData.PVPotential_kW - data.getRapidRunData().assetsMetaData.totalInstalledPVPower_kW)*totalFullLoadHoursSolar_hr/1000000)));
sc_PVSupplyStack.addDataItem(remainingRoofSolarPotential_GWh, "Resterende potentie zon op dak", v_potentialColor);

//Set production charts scaling
double chartScaleSupply_GWh = max(totalWindProduction_GWh.getValue() + totalCurtailedWindEnergy_GWh.getValue() + remainingWindPotential_GWh.getValue(), 
								  totalSolarProduction_GWh.getValue() + totalCurtailedPVEnergy_GWh.getValue() + remainingRoofSolarPotential_GWh.getValue());
sc_windSupplyStack.setFixedScale(chartScaleSupply_GWh);
sc_PVSupplyStack.setFixedScale(chartScaleSupply_GWh);

/*ALCODEEND*/}

double f_setChartsGespreksleidraad4(I_EnergyData data)
{/*ALCODESTART::1730392914975*/
f_setBelastingPlots(data);
/*ALCODEEND*/}

double f_setChartsGespreksleidraad5(I_EnergyData data)
{/*ALCODESTART::1730392915853*/
//Electricity demand chart
energyDemandChartYearGespreksleidraad5.removeAll();
double startTime_h = 0;
double maxDemandChart = 0;
if(rb_gespreksleidraad5.getValue() == 0){ // Electricity
	energyDemandChartYearGespreksleidraad5.addDataSet(data.getRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getDataSet(startTime_h), "Elektriciteitsvraag", v_energyDemandColor, true, false, Chart.INTERPOLATION_LINEAR, 2, Chart.POINT_NONE);
	maxDemandChart = data.getRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMaxPower_kW();
}
else if(rb_gespreksleidraad5.getValue() == 1){ // Energy
	energyDemandChartYearGespreksleidraad5.addDataSet(data.getRapidRunData().acc_dailyAverageFinalEnergyConsumption_kW.getDataSet(startTime_h), "Energievraag", v_energyDemandColor, true, false, Chart.INTERPOLATION_LINEAR, 2, Chart.POINT_NONE);
	maxDemandChart = data.getRapidRunData().acc_dailyAverageFinalEnergyConsumption_kW.getMaxPower_kW();
}
energyDemandChartYearGespreksleidraad5.setVisible(true);

//Electricity supply chart
energySupplyChartYearGespreksleidraad5.removeAll();
energySupplyChartYearGespreksleidraad5.addDataSet(data.getRapidRunData().acc_dailyAverageWindProduction_kW.getDataSet(startTime_h), v_windElectricitySupplyText, v_windElectricitySupplyColor);
energySupplyChartYearGespreksleidraad5.addDataSet(data.getRapidRunData().acc_dailyAveragePVProduction_kW.getDataSet(startTime_h), v_PVElectricitySupplyText, v_PVElectricitySupplyColor);
energySupplyChartYearGespreksleidraad5.addDataSet(data.getRapidRunData().acc_dailyAverageBatteriesProduction_kW.getDataSet(startTime_h), v_storageElectricitySupplyText, v_storageElectricitySupplyColor);
energySupplyChartYearGespreksleidraad5.addDataSet(data.getRapidRunData().acc_dailyAverageV2GProduction_kW.getDataSet(startTime_h), v_V2GElectricitySupplyText, v_V2GElectricitySupplyColor);


//energySupplyChartYearGespreksleidraad1.addDataSet(area.data_petroleumProductsSupplyYear_MWh, v_petroleumProductsSupplyText, v_petroleumProductsSupplyColor);
//energySupplyChartYearGespreksleidraad1.addDataSet(area.data_districtHeatHeatSupplyYear_MWh, v_districtHeatHeatSupplyText, v_districtHeatHeatSupplyColor);

double maxScale = max(maxDemandChart, data.getRapidRunData().am_dailyAverageProductionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getMaxPower_kW());
//double maxScale = max(uI_Results.energySupplyChartYearGespreksleidraad1.getScaleY(), uI_Results.energyDemandChartYearGespreksleidraad1.getScaleY());
energyDemandChartYearGespreksleidraad5.setFixedVerticalScale(0, maxScale);
energySupplyChartYearGespreksleidraad5.setFixedVerticalScale(maxScale);

energySupplyChartYearGespreksleidraad5.setVisible(false);
energySupplyChartYearGespreksleidraad5.setVisible(true);

/*ALCODEEND*/}

double f_setChartsGespreksleidraad6(I_EnergyData data)
{/*ALCODESTART::1730392916575*/

/*ALCODEEND*/}

double f_setChartsGespreksleidraad3v2(I_EnergyData data)
{/*ALCODESTART::1732205673839*/
f_setChartsGespreksleidraad3v2_PieChart(data);
/*
//Clear
pl_consumptionChart3v2.removeAll();
pl_productionChart3v2.removeAll();


double annualSelfConsumedElectricityIndividual_MWh;

if (! (area.v_individualSelfconsumption_fr > 0) ) {
	annualSelfConsumedElectricityIndividual_MWh = 0.0;
	traceln("NaN detected!");
} else {
	annualSelfConsumedElectricityIndividual_MWh = (area.v_totalElectricitySelfConsumed_MWh + area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY)) * area.v_individualSelfconsumption_fr;
}
					
double selfConsumedElectricityIndividual_MWh = annualSelfConsumedElectricityIndividual_MWh;
double additionalSelfConsumedElectricityCollective_MWh = area.v_totalElectricitySelfConsumed_MWh - annualSelfConsumedElectricityIndividual_MWh;
double totalExportedElectricity_MWh = area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double totalImportedElectricity_MWh = area.fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY);



DataItem annualSelfConsumedNBH = new DataItem();
annualSelfConsumedNBH.setValue(selfConsumedElectricityIndividual_MWh);
pl_productionChart3v2.addDataItem(annualSelfConsumedNBH, "Lokaal gebruikt(Buurt)[MWh]", v_selfConsumedElectricityColor_individual);
pl_consumptionChart3v2.addDataItem(annualSelfConsumedNBH, "Lokaal opgewekt(Buurt)[MWh]", v_selfConsumedElectricityColor_individual);


DataItem annualSelfConsumedRegion = new DataItem();
annualSelfConsumedRegion.setValue(additionalSelfConsumedElectricityCollective_MWh);
pl_productionChart3v2.addDataItem(annualSelfConsumedRegion, "Lokaal gebruikt (Regio)[MWh]", v_selfConsumedElectricityColor_collective);
pl_consumptionChart3v2.addDataItem(annualSelfConsumedRegion, "Lokaal opgewekt (Regio)[MWh]", v_selfConsumedElectricityColor_collective);


DataItem annualImport = new DataItem();
annualImport.setValue(totalImportedElectricity_MWh);
pl_consumptionChart3v2.addDataItem(annualImport, "Invoer [MWh]", v_importedEnergyColor);

DataItem annualExport = new DataItem();
annualExport.setValue(totalExportedElectricity_MWh);
pl_productionChart3v2.addDataItem(annualExport, "Uitvoer [MWh]", v_exportedEnergyColor);

double production_MWh = area.v_totalElectricitySelfConsumed_MWh + area.fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double consumption_MWh = area.v_totalElectricitySelfConsumed_MWh + area.fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double chartScale_MWh = max(production_MWh, consumption_MWh);
pl_consumptionChart3v2.setFixedScale(chartScale_MWh);
pl_productionChart3v2.setFixedScale(chartScale_MWh);


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
*/
/*ALCODEEND*/}

double f_setChartsGespreksleidraad3v2_PieChart(I_EnergyData data)
{/*ALCODESTART::1732208184134*/
//Clear
pl_productionChart3v2_pieLegenda.removeAll();
pl_consumptionChart3v2_pieLegenda.removeAll();
pl_productionChart3v2_pie.removeAll();
pl_consumptionChart3v2_pie.removeAll();
pl_productionChart3v2_pie.setScale(1);
pl_consumptionChart3v2_pie.setScale(1);
pl_productionChart3v2_pie.setPos(-250, -50);
pl_consumptionChart3v2_pie.setPos(-20, -50);

double individualSelfconsumptionElectricity_fr = 0;
if (data.getRapidRunData().parentAgent instanceof EnergyCoop){
	individualSelfconsumptionElectricity_fr = ((EnergyCoop)data.getRapidRunData().parentAgent).v_cumulativeIndividualSelfconsumptionElectricity_fr;
}
else if(data.getRapidRunData().parentAgent instanceof EnergyModel){
	individualSelfconsumptionElectricity_fr = sum(((EnergyModel)data.getRapidRunData().parentAgent).f_getGridConnections(), GC -> GC.v_rapidRunData.getTotalElectricitySelfConsumed_MWh()/GC.v_rapidRunData.getTotalElectricityConsumed_MWh());
}

double annualSelfConsumedElectricityIndividual_MWh;

if (! (individualSelfconsumptionElectricity_fr > 0) ) {
	annualSelfConsumedElectricityIndividual_MWh = 0.0;
	traceln("NaN detected!");
} else {
	annualSelfConsumedElectricityIndividual_MWh = (data.getRapidRunData().getTotalElectricitySelfConsumed_MWh() + data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getIntegralNeg_kWh()/1000) * individualSelfconsumptionElectricity_fr;
}
					
double selfConsumedElectricityIndividual_MWh = annualSelfConsumedElectricityIndividual_MWh;
double additionalSelfConsumedElectricityCollective_MWh = data.getRapidRunData().getTotalElectricitySelfConsumed_MWh() - annualSelfConsumedElectricityIndividual_MWh;
double totalExportedElectricity_MWh = -data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getIntegralNeg_kWh()/1000;
double totalImportedElectricity_MWh = data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getIntegralPos_kWh()/1000;

double production_MWh = data.getRapidRunData().getTotalElectricityProduced_MWh(); //data.getRapidRunData().getTotalElectricitySelfConsumed_MWh + data.getRapidRunData().fm_totalExports_MWh.get(OL_EnergyCarriers.ELECTRICITY);
double consumption_MWh = data.getRapidRunData().getTotalElectricityConsumed_MWh(); //data.getRapidRunData().getTotalElectricitySelfConsumed_MWh + data.getRapidRunData().fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY);

double selfConsumedElectricityIndividual_scaledUnit;
double additionalSelfConsumedElectricityCollective_scaledUnit;
double totalExportedElectricity_scaledUnit;
double totalImportedElectricity_scaledUnit;



if (consumption_MWh < 10 || production_MWh < 10) { // --> Convert all to kWh
	selfConsumedElectricityIndividual_scaledUnit = selfConsumedElectricityIndividual_MWh*1000;
	additionalSelfConsumedElectricityCollective_scaledUnit = additionalSelfConsumedElectricityCollective_MWh*1000;
	totalExportedElectricity_scaledUnit = totalExportedElectricity_MWh*1000;	
	totalImportedElectricity_scaledUnit = totalImportedElectricity_MWh*1000;	
}
else if (consumption_MWh < 1000 || production_MWh < 1000) { // --> Convert all to MWh
	selfConsumedElectricityIndividual_scaledUnit = selfConsumedElectricityIndividual_MWh;
	additionalSelfConsumedElectricityCollective_scaledUnit = additionalSelfConsumedElectricityCollective_MWh;
	totalExportedElectricity_scaledUnit = totalExportedElectricity_MWh;
	totalImportedElectricity_scaledUnit = totalImportedElectricity_MWh;	
}
else {// --> Convert all to GWh
	selfConsumedElectricityIndividual_scaledUnit = selfConsumedElectricityIndividual_MWh/1000;
	additionalSelfConsumedElectricityCollective_scaledUnit = additionalSelfConsumedElectricityCollective_MWh/1000;
	totalExportedElectricity_scaledUnit = totalExportedElectricity_MWh/1000;
	totalImportedElectricity_scaledUnit = totalImportedElectricity_MWh/1000;
}



DataItem annualSelfConsumedNBH = new DataItem();
annualSelfConsumedNBH.setValue(selfConsumedElectricityIndividual_scaledUnit);
pl_productionChart3v2_pie.addDataItem(annualSelfConsumedNBH, "Lokaal gebruikt (Buurt)", v_selfConsumedElectricityColor_individual);
pl_consumptionChart3v2_pie.addDataItem(annualSelfConsumedNBH, "Lokaal opgewekt (Buurt)", v_selfConsumedElectricityColor_individual);
pl_productionChart3v2_pieLegenda.addDataItem(annualSelfConsumedNBH, "Lokaal gebruikt (Buurt)", v_selfConsumedElectricityColor_individual);
pl_consumptionChart3v2_pieLegenda.addDataItem(annualSelfConsumedNBH, "Lokaal opgewekt (Buurt)", v_selfConsumedElectricityColor_individual);


DataItem annualSelfConsumedRegion = new DataItem();
annualSelfConsumedRegion.setValue(additionalSelfConsumedElectricityCollective_scaledUnit);
pl_productionChart3v2_pie.addDataItem(annualSelfConsumedRegion, "Lokaal gebruikt (Regio)", v_selfConsumedElectricityColor_collective);
pl_consumptionChart3v2_pie.addDataItem(annualSelfConsumedRegion, "Lokaal opgewekt (Regio)", v_selfConsumedElectricityColor_collective);
pl_productionChart3v2_pieLegenda.addDataItem(annualSelfConsumedRegion, "Lokaal gebruikt (Regio)", v_selfConsumedElectricityColor_collective);
pl_consumptionChart3v2_pieLegenda.addDataItem(annualSelfConsumedRegion, "Lokaal opgewekt (Regio)", v_selfConsumedElectricityColor_collective);

DataItem annualExport = new DataItem();
annualExport.setValue(totalExportedElectricity_scaledUnit);
pl_productionChart3v2_pie.addDataItem(annualExport, "Uitvoer", v_exportedEnergyColor);
pl_productionChart3v2_pieLegenda.addDataItem(annualExport, "Uitvoer", v_exportedEnergyColor);

DataItem annualImport = new DataItem();
annualImport.setValue(totalImportedElectricity_scaledUnit);
pl_consumptionChart3v2_pie.addDataItem(annualImport, "Invoer", v_importedEnergyColor);
pl_consumptionChart3v2_pieLegenda.addDataItem(annualImport, "Invoer", v_importedEnergyColor);


//Scale pie charts
if(production_MWh < consumption_MWh){
	double v_scalePie3v2_production = sqrt(production_MWh/consumption_MWh);
	double newXlocation = pl_productionChart3v2_pie.getX() + pl_productionChart3v2_pie.getWidth()*(1-v_scalePie3v2_production)/2;
	double newYlocation = pl_productionChart3v2_pie.getY() + pl_productionChart3v2_pie.getHeight()*(1-v_scalePie3v2_production)/2;
	pl_productionChart3v2_pie.setPos(newXlocation, newYlocation);
	pl_productionChart3v2_pie.setScale(v_scalePie3v2_production);
}
else if(production_MWh > consumption_MWh){
	double v_scalePie3v2_consumption = sqrt(consumption_MWh/production_MWh);
	double newXlocation = pl_consumptionChart3v2_pie.getX() + pl_consumptionChart3v2_pie.getWidth()*(1-v_scalePie3v2_consumption)/2;
	double newYlocation = pl_consumptionChart3v2_pie.getY() + pl_consumptionChart3v2_pie.getHeight()*(1-v_scalePie3v2_consumption)/2;
	pl_consumptionChart3v2_pie.setPos(newXlocation, newYlocation);
	pl_consumptionChart3v2_pie.setScale(v_scalePie3v2_consumption);
}


//Set the total production and consumption text
if (consumption_MWh < 10 || production_MWh < 10) {
	t_totalProductionYear.setText("Opwek [kWh]" + System.lineSeparator() + "Totaal : " + roundToInt(production_MWh*1000));// + " kWh");
	t_totalConsumptionYear.setText("Gebruik [kWh]" + System.lineSeparator() + "Totaal : " + roundToInt(consumption_MWh*1000));// + " kWh");
} else if (consumption_MWh < 1000 || production_MWh < 1000) {
	t_totalProductionYear.setText("Opwek [MWh]" + System.lineSeparator() + "Totaal : " + roundToInt(production_MWh));// + " MWh");
	t_totalConsumptionYear.setText("Gebruik [MWh]" + System.lineSeparator() + "Totaal : " + roundToInt(consumption_MWh));// + " MWh");
} else {
	t_totalProductionYear.setText("Opwek [GWh]" + System.lineSeparator() + "Totaal : " + roundToDecimal(production_MWh/1000, 1));// + " GWh");
	t_totalConsumptionYear.setText("Gebruik [GWh]" + System.lineSeparator() + "Totaal : " + roundToDecimal(consumption_MWh/1000,1));// + " GWh");
}


/*ALCODEEND*/}

double f_setWarningScreen(boolean showWarningScreen)
{/*ALCODESTART::1741345541726*/
gr_warningScreen.setVisible(showWarningScreen);
/*ALCODEEND*/}

double f_setBelastingPlots(I_EnergyData data)
{/*ALCODESTART::1741706256154*/
f_resetPlots();

J_LoadDurationCurves loadDurationCurves = data.getRapidRunData().getLoadDurationCurves(uI_Results.energyModel);

f_addDataToPlots(data, loadDurationCurves);
f_addTrafoLimits(data); 

double maxDemand_kW = max(0, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMax());
double maxSupply_kW = abs(min(0, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMin()));
double maxDemandSupply_kW = max(maxDemand_kW, maxSupply_kW);
/*
if (maxDemandSupply_kW < 1 * pow(10,3)) {
	tx_maxDemand.setText(roundToDecimal(maxDemand_kW, 0) + " kW");
	tx_maxSupply.setText(roundToDecimal(maxSupply_kW, 0) + " kW");
} else if (maxDemandSupply_kW<1 * pow(10,6)) {
	tx_maxDemand.setText(roundToDecimal(maxDemand_kW / pow(10,3), 0) + " MW");
	tx_maxSupply.setText(roundToDecimal(maxSupply_kW / pow(10,3), 0) + " MW");
} else {
	tx_maxDemand.setText(roundToDecimal(maxDemand_kW / pow(10,6), 1) + " GW");
	tx_maxSupply.setText(roundToDecimal(maxSupply_kW / pow(10,6), 1) + " GW");
} */

if (maxDemandSupply_kW < 1 * pow(10,3)) {
	tx_maxDemand.setText(String.format("%.0f", maxDemand_kW) + " kW");
	tx_maxSupply.setText(String.format("%.0f", maxSupply_kW) + " kW");
} else if (maxDemandSupply_kW<1 * pow(10,6)) {
	tx_maxDemand.setText(String.format("%.2f", maxDemand_kW / pow(10,3)) + " MW");
	tx_maxSupply.setText(String.format("%.2f", maxSupply_kW / pow(10,3)) + " MW");
	//tx_maxDemand.setText(roundToDecimal(maxDemand_kW / pow(10,3), 0) + " MW");
	//tx_maxSupply.setText(roundToDecimal(maxSupply_kW / pow(10,3), 0) + " MW");
} else {
	tx_maxDemand.setText(String.format("%.2f", maxDemand_kW / pow(10,6)) + " GW");
	tx_maxSupply.setText(String.format("%.2f", maxSupply_kW / pow(10,6)) + " GW");
	//tx_maxDemand.setText(roundToDecimal(maxDemand_kW / pow(10,6), 1) + " GW");
	//tx_maxSupply.setText(roundToDecimal(maxSupply_kW / pow(10,6), 1) + " GW");
} 


f_setNetAverageLoad(data, loadDurationCurves);
/*ALCODEEND*/}

double f_setNetAverageLoad(I_EnergyData dataObject,J_LoadDurationCurves loadDurationCurves)
{/*ALCODESTART::1741706256165*/
double benuttingsgraad = 0;
double totalAbsoluteLoad_kW = 0;
for(int i=0; i< loadDurationCurves.ds_loadDurationCurveTotal_kW.size(); i++){
	totalAbsoluteLoad_kW += abs(loadDurationCurves.ds_loadDurationCurveTotal_kW.getY(i))* uI_Results.energyModel.p_timeStep_h;
}
benuttingsgraad = totalAbsoluteLoad_kW / ((dataObject.getRapidRunData().connectionMetaData.contractedDeliveryCapacity_kW + dataObject.getRapidRunData().connectionMetaData.contractedFeedinCapacity_kW) * 8760) * 100;
t_KPIBenuttingsgraad.setText(roundToDecimal(benuttingsgraad, 0) + "%");
/*ALCODEEND*/}

double f_resetPlots()
{/*ALCODESTART::1741706256171*/
plot_jaar.removeAll();
plot_week.removeAll();
plot_dagnacht.removeAll();
plot_seizoen.removeAll();
/*ALCODEEND*/}

double f_addDataToPlots(I_EnergyData dataObject,J_LoadDurationCurves loadDurationCurves)
{/*ALCODESTART::1741706256175*/
double scaleMin_kW;
double scaleMax_kW;

double gridCapacityDelivery_kW = dataObject.getRapidRunData().connectionMetaData.contractedDeliveryCapacity_kW;
double gridCapacityFeedin_kW = dataObject.getRapidRunData().connectionMetaData.contractedFeedinCapacity_kW;

//Jaar
plot_jaar.addDataSet(loadDurationCurves.ds_loadDurationCurveTotal_kW,"Belasting jaar");
scaleMin_kW = -1 + min(-gridCapacityFeedin_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMin());
scaleMax_kW = 1 + max(gridCapacityDelivery_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMax());
plot_jaar.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);
//if (area.v_dataNetbelastingDuurkrommeYearVorige_kW != null) {
	//plot_jaar.addDataSet(area.v_dataNetbelastingDuurkrommeYearVorige_kW,"Vorige situatie");
	//plot_jaar.setColor(3,gray);
//}

//summer/winter
plot_seizoen.addDataSet(loadDurationCurves.ds_loadDurationCurveSummer_kW,"Belasting zomerweek");
plot_seizoen.addDataSet(loadDurationCurves.ds_loadDurationCurveWinter_kW,"Belasting winterweek");
plot_seizoen.setColor(0,blue);
plot_seizoen.setColor(1,green);
scaleMin_kW = -1 + min(-gridCapacityFeedin_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMin());
scaleMax_kW = 1 + max(gridCapacityDelivery_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMax());
plot_seizoen.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);


// Day/night
plot_dagnacht.addDataSet(loadDurationCurves.ds_loadDurationCurveDaytime_kW,"Belasting overdag");
plot_dagnacht.addDataSet(loadDurationCurves.ds_loadDurationCurveNighttime_kW,"Belasting 's nachts");
plot_dagnacht.setColor(0,blue);
plot_dagnacht.setColor(1,green);	
scaleMin_kW = -1 + min(-gridCapacityFeedin_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMin());
scaleMax_kW = 1 + max(gridCapacityDelivery_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMax());
plot_dagnacht.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);


// Weekday/weekend
plot_week.addDataSet(loadDurationCurves.ds_loadDurationCurveWeekday_kW,"Belasting weekdagen");
plot_week.addDataSet(loadDurationCurves.ds_loadDurationCurveWeekend_kW,"Belasting weekenddagen");
plot_week.setColor(0,blue);
plot_week.setColor(1,green);
scaleMin_kW = -1 + min(-gridCapacityFeedin_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMin());
scaleMax_kW = 1 + max(gridCapacityDelivery_kW*1.2, loadDurationCurves.ds_loadDurationCurveTotal_kW.getYMax());
plot_week.setFixedVerticalScale(scaleMin_kW, scaleMax_kW);

/*ALCODEEND*/}

double f_addTrafoLimits(I_EnergyData dataObject)
{/*ALCODESTART::1741706256178*/
//Add and color grid capacities
String deliveryCapacityLabel = "Geschatte capaciteit afname";
String feedinCapacityLabel = "Geschatte capaciteit teruglevering";
Color  deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;
Color  feedinCapacityColor		= uI_Results.v_electricityCapacityColor_estimated;

//Create delivery and capacity year datasets
DataSet gridCapacityDelivery_kW = uI_Results.f_createFlatDataset(0, 8760, dataObject.getRapidRunData().connectionMetaData.contractedDeliveryCapacity_kW);
DataSet gridCapacityFeedin_kW = uI_Results.f_createFlatDataset(0, 8760, -dataObject.getRapidRunData().connectionMetaData.contractedFeedinCapacity_kW);
	
if(uI_Results.b_showGroupContractValues && uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYCOOP){
	deliveryCapacityLabel = "Cumulatieve GTV afname van bedrijven";
	deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	feedinCapacityLabel = "Cumulatieve GTV teruglevering van bedrijven";
	feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	
	//And: add group contract values
	DataSet groupContractCapacityDelivery_kW = uI_Results.f_createFlatDataset(0, 8760, ((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getGroupContractDeliveryCapacity_kW());
	DataSet groupContractCapacityFeedin_kW = uI_Results.f_createFlatDataset(0, 8760, -((EnergyCoop)dataObject.getRapidRunData().parentAgent).f_getGroupContractFeedinCapacity_kW());
	
	plot_jaar.addDataSet(groupContractCapacityDelivery_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_jaar.addDataSet(groupContractCapacityFeedin_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	plot_week.addDataSet(groupContractCapacityDelivery_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_week.addDataSet(groupContractCapacityFeedin_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	plot_dagnacht.addDataSet(groupContractCapacityDelivery_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_dagnacht.addDataSet(groupContractCapacityFeedin_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
	plot_seizoen.addDataSet(groupContractCapacityDelivery_kW, "Groeps GTV afname", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);
	plot_seizoen.addDataSet(groupContractCapacityFeedin_kW, "Groeps GTV teruglevering", uI_Results.v_groupGTVColor, true, false, Chart.InterpolationType.INTERPOLATION_LINEAR, 1, Chart.PointStyle.POINT_NONE);	
}
else{
	if(dataObject.getRapidRunData().connectionMetaData.contractedDeliveryCapacityKnown){
		deliveryCapacityLabel = "Capaciteit afname";
		deliveryCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	}
	if(dataObject.getRapidRunData().connectionMetaData.contractedFeedinCapacityKnown){
		feedinCapacityLabel = "Capaciteit teruglevering";
		feedinCapacityColor		= uI_Results.v_electricityCapacityColor_known;
	}
}
	
plot_jaar.addDataSet(gridCapacityDelivery_kW, deliveryCapacityLabel);
plot_jaar.addDataSet(gridCapacityFeedin_kW, feedinCapacityLabel);
plot_jaar.setColor(1, deliveryCapacityColor);
plot_jaar.setColor(2, feedinCapacityColor);

plot_week.addDataSet(gridCapacityDelivery_kW, deliveryCapacityLabel);
plot_week.addDataSet(gridCapacityFeedin_kW, feedinCapacityLabel);
plot_week.setColor(plot_week.getCount() - 2, deliveryCapacityColor);
plot_week.setColor(plot_week.getCount() - 1, feedinCapacityColor);

plot_dagnacht.addDataSet(gridCapacityDelivery_kW, deliveryCapacityLabel);
plot_dagnacht.addDataSet(gridCapacityFeedin_kW, feedinCapacityLabel);
plot_dagnacht.setColor(plot_dagnacht.getCount() - 2, deliveryCapacityColor);
plot_dagnacht.setColor(plot_dagnacht.getCount() - 1, feedinCapacityColor);

plot_seizoen.addDataSet(gridCapacityDelivery_kW, deliveryCapacityLabel);
plot_seizoen.addDataSet(gridCapacityFeedin_kW, feedinCapacityLabel);
plot_seizoen.setColor(plot_seizoen.getCount() - 2, deliveryCapacityColor);
plot_seizoen.setColor(plot_seizoen.getCount() - 1, feedinCapacityColor);

/*ALCODEEND*/}

