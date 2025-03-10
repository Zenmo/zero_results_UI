double f_setKPISummaryChart()
{/*ALCODESTART::1726830495435*/
I_EnergyData data = uI_Results.f_getSelectedObjectData();

/*
//At least for now grid nodes are not supported
if(uI_Results.v_selectedObjectType == OL_SelectedObjectType.GRIDNODE){
	dataObject = uI_Results.v_area;
}
*/

//Reset chart
f_resetChart();

//Set KPIs
f_setKPIs(data);

//Set visible
uI_Results.chartKPISummary_presentation.setVisible(true);
/*ALCODEEND*/}

double f_setKPIs(AreaCollection area)
{/*ALCODESTART::1726830499836*/
////Calculate the values

//Calculate and set new values
double totalEnergyConsumption_MWh = area.v_totalEnergyConsumed_MWh;
double totalImport_MWh = area.v_totalEnergyImport_MWh;
double totalExport_MWh = area.v_totalEnergyExport_MWh;
	
double elecConsumption_pct = area.fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY) / totalEnergyConsumption_MWh * 100;
double gasConsumption_pct = area.fm_totalImports_MWh.get(OL_EnergyCarriers.METHANE) / totalEnergyConsumption_MWh * 100;
double FFconsumption_pct = area.fm_totalImports_MWh.get(OL_EnergyCarriers.DIESEL) / totalEnergyConsumption_MWh * 100;
double h2consumption_pct = area.fm_totalImports_MWh.get(OL_EnergyCarriers.HYDROGEN) / totalEnergyConsumption_MWh * 100;
double production_MWh = area.v_totalEnergyProduced_MWh;

double KPIselfsufficiency_pct = area.v_totalEnergySelfConsumed_MWh / totalEnergyConsumption_MWh * 100;

//Overload of GC for GC and overload of all gridnodes combined for Region (GN is not supported and shows same KPIs as whole region)
double KPIOverloadHours_pct;
if(uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYMODEL){
	KPIOverloadHours_pct = area.v_totalTimeOverloaded_h/8760*100;
}
else{
	KPIOverloadHours_pct = (area.v_annualOverloadDurationDelivery_hr + area.v_annualOverloadDurationFeedin_hr)/8760 * 100;
}


//Set new values text
DecimalFormat df = new DecimalFormat("0.0");

if(roundToInt(totalEnergyConsumption_MWh) >= 1000){
	t_totalconsumption_MWh.setText(df.format(totalEnergyConsumption_MWh/1000));
	t_totalImport_MWh.setText(df.format(totalImport_MWh/1000));
	t_totalExport_MWh.setText(df.format(totalExport_MWh/1000));
	t_totals.setText("Jaar totalen energie (GWh)");
}
else{
	t_totalconsumption_MWh.setText(df.format(totalEnergyConsumption_MWh));
	t_totalImport_MWh.setText(df.format(totalImport_MWh));
	t_totalExport_MWh.setText(df.format(totalExport_MWh));
	t_totals.setText("Jaar totalen energie (MWh)");
}

t_elecConsumption_pct.setText(df.format(elecConsumption_pct) + " %");
t_gasconsumption_pct.setText(df.format(gasConsumption_pct) + " %");
t_FFconsumption_pct.setText(df.format(FFconsumption_pct) + " %");
t_h2consumption_pct.setText(df.format(h2consumption_pct) + " %");

//Convert to GWh when over 1000 MWh
if(roundToInt(production_MWh) >= 1000){
	t_production_pct.setText(df.format(production_MWh/1000) + " GWh");
}
else{
	t_production_pct.setText(df.format(production_MWh) + " MWh");
}

t_KPIselfsufficiency_pct.setText(df.format(KPIselfsufficiency_pct) + " %");
t_KPIOverloadHours_pct.setText(df.format(KPIOverloadHours_pct) + " %");


//Calculate and set previous values (if they exist) + arrows and styling
if(area.v_previousTotals.getPreviousTotalConsumedEnergy_MWh() != null && area.v_previousTotals.getPreviousTotalConsumedEnergy_MWh() != 0){

	double previousTotalEnergyConsumption_MWh = area.v_previousTotals.getPreviousTotalConsumedEnergy_MWh();
	double previousTotalImport_MWh = area.v_previousTotals.getPreviousImportedEnergy_MWh();
	double previousTotalExport_MWh = area.v_previousTotals.getPreviousExportedEnergy_MWh();
	
	
	double previousElectricityConsumption_pct = area.v_previousTotals.getPreviousTotalImports_MWh().get(OL_EnergyCarriers.ELECTRICITY) / previousTotalEnergyConsumption_MWh * 100;
	double previousGasConsumption_pct = area.v_previousTotals.getPreviousTotalImports_MWh().get(OL_EnergyCarriers.METHANE) / previousTotalEnergyConsumption_MWh * 100;
	double previousFFConsumption_pct = area.v_previousTotals.getPreviousTotalImports_MWh().get(OL_EnergyCarriers.DIESEL) / previousTotalEnergyConsumption_MWh * 100;
	double previousH2Consumption_pct = area.v_previousTotals.getPreviousTotalImports_MWh().get(OL_EnergyCarriers.HYDROGEN) / previousTotalEnergyConsumption_MWh * 100; 
	double previousProduction_MWh = area.v_previousTotals.getPreviousTotalProducedEnergy_MWh();  
	
	double previousKPIselfsufficiency_pct = area.v_previousTotals.getPreviousSelfConsumedEnergy_MWh() / previousTotalEnergyConsumption_MWh * 100; 
	
	//Overload of GC for GC and overload of all gridnodes combined for Region (GN is not supported and shows same KPIs as whole region)
	double previousKPIOverloadHours_pct;
	if(uI_Results.v_selectedObjectScope == OL_ResultScope.ENERGYMODEL || uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE){
		previousKPIOverloadHours_pct = area.v_previousTotals.getPreviousTotalTimeOverloadedTransformers_hr()/8760*100;
	}
	else{
	previousKPIOverloadHours_pct = (area.v_previousTotals.getPreviousOverloadDurationDelivery_hr() + area.v_previousTotals.getPreviousOverloadDurationFeedin_hr())/ 8760.0 * 100;
	}
	
	//Set previous values text (convert to same unit as totalEnergyConsumption text, so same if statement)
	if(roundToInt(totalEnergyConsumption_MWh) >= 1000){
		t_previousTotalconsumption_MWh.setText(df.format(previousTotalEnergyConsumption_MWh/1000));
		t_previousTotalImport_MWh.setText(df.format(previousTotalImport_MWh/1000));
		t_previousTotalExport_MWh.setText(df.format(previousTotalExport_MWh/1000));
	}
	else{
		t_previousTotalconsumption_MWh.setText(df.format(previousTotalEnergyConsumption_MWh));
		t_previousTotalImport_MWh.setText(df.format(previousTotalImport_MWh));
		t_previousTotalExport_MWh.setText(df.format(previousTotalExport_MWh));
	}

	t_previousElectricityConsumption_pct.setText(df.format(previousElectricityConsumption_pct) + " %");
	t_previousGasConsumption_pct.setText(df.format(previousGasConsumption_pct) + " %");
	t_previousFFConsumption_pct.setText(df.format(previousFFConsumption_pct) + " %");
	t_previousH2Consumption_pct.setText(df.format(previousH2Consumption_pct) + " %");
	
	//Convert to GWh when over 1000
	if(roundToInt(previousProduction_MWh) >= 1000){
		t_previousProduction_pct.setText(df.format(previousProduction_MWh/1000) + " GWh");
	}
	else{
		t_previousProduction_pct.setText(df.format(previousProduction_MWh) + " MWh");
	}

	t_previousKPIselfsufficiency_pct.setText(df.format(previousKPIselfsufficiency_pct) + " %");
	t_previousKPIOverloadHours_pct.setText(df.format(previousKPIOverloadHours_pct) + " %");
	
	////Set correct arrow and colors
	//Total consumption
	if(t_totalconsumption_MWh.getText().equals(t_previousTotalconsumption_MWh.getText())){
		line_total.setVisible(true);
	} else if(totalEnergyConsumption_MWh > previousTotalEnergyConsumption_MWh){
		arrow_up_red_totalconsumption.setVisible(true);
	} else if(totalEnergyConsumption_MWh < previousTotalEnergyConsumption_MWh){
		arrow_down_green_totalconsumption.setVisible(true);
	}
	
	//Import
	if(t_totalImport_MWh.getText().equals(t_previousTotalImport_MWh.getText())){
		line_import.setVisible(true);
	} else if(totalImport_MWh > previousTotalImport_MWh){
		arrow_up_red_import.setVisible(true);
	} else if(totalImport_MWh < previousTotalImport_MWh){
		arrow_down_green_import.setVisible(true);
	}
	
	//Export
	if(t_totalExport_MWh.getText().equals(t_previousTotalExport_MWh.getText())){
		line_export.setVisible(true);
	} else if(totalExport_MWh > previousTotalExport_MWh){
		arrow_up_red_export.setVisible(true);
	} else if(totalExport_MWh < previousTotalExport_MWh){
		arrow_down_green_export.setVisible(true);
	}
	
	//Electricity
	if(t_elecConsumption_pct.getText().equals(t_previousElectricityConsumption_pct.getText())){
		line_electricity.setVisible(true);
	} else if(elecConsumption_pct > previousElectricityConsumption_pct){
		arrow_up_green_electricity.setVisible(true);
	} else if(elecConsumption_pct < previousElectricityConsumption_pct){
		arrow_down_red_electricity.setVisible(true);
	}
	
	//Gas
	if(t_gasconsumption_pct.getText().equals(t_previousGasConsumption_pct.getText())){
		line_gas.setVisible(true);
	} else if(gasConsumption_pct > previousGasConsumption_pct){
		arrow_up_red_gas.setVisible(true);
	} else if(gasConsumption_pct < previousGasConsumption_pct){
		arrow_down_green_gas.setVisible(true);
	}
	
	//FF
	if(t_FFconsumption_pct.getText().equals(t_previousFFConsumption_pct.getText())){
		line_FF.setVisible(true);
	} else if(FFconsumption_pct > previousFFConsumption_pct){
		arrow_up_red_FF.setVisible(true);
	} else if(FFconsumption_pct < previousFFConsumption_pct){
		arrow_down_green_FF.setVisible(true);
	}
	
	//H2
	if(t_h2consumption_pct.getText().equals(t_previousH2Consumption_pct.getText())){
		line_h2.setVisible(true);
	} else if(h2consumption_pct > previousH2Consumption_pct){
		arrow_up_green_h2.setVisible(true);
	} else if(h2consumption_pct < previousH2Consumption_pct){
		arrow_down_red_h2.setVisible(true);
	}
	
	//Production
	if(t_production_pct.getText().equals(t_previousProduction_pct.getText())){
		line_production.setVisible(true);
	} else if(production_MWh > previousProduction_MWh){
		arrow_up_green_production.setVisible(true);
	} else if(production_MWh < previousProduction_MWh){
		arrow_down_red_production.setVisible(true);
	}
	
	//Self sufficiency
	if(t_KPIselfsufficiency_pct.getText().equals(t_previousKPIselfsufficiency_pct.getText())){
		line_selfsufficiency.setVisible(true);
	} else if(KPIselfsufficiency_pct > previousKPIselfsufficiency_pct){
		arrow_up_green_selfsufficiency.setVisible(true);
	} else if(KPIselfsufficiency_pct < previousKPIselfsufficiency_pct){
		arrow_down_red_selfsufficiency.setVisible(true);
	}
	
	//Gridload
	if(t_KPIOverloadHours_pct.getText().equals(t_previousKPIOverloadHours_pct.getText())){
		line_gridload.setVisible(true);
	} else if(KPIOverloadHours_pct > previousKPIOverloadHours_pct){
		arrow_up_red_gridload.setVisible(true);
	} else if(KPIOverloadHours_pct < previousKPIOverloadHours_pct){
		arrow_down_green_gridload.setVisible(true);
	}
}



/*ALCODEEND*/}

double f_styleBackground_override(Color backgroundColor,Color lineColor,Double lineWidth,LineStyle lineStyle)
{/*ALCODESTART::1726834752079*/
//Function used to style the background of the graphs

v_backgroundColor = v_backgroundColor_override;

if(lineColor != null){
	v_lineColor = lineColor;
}

if(lineWidth != null){
 v_lineWidth = lineWidth;
}
if(lineStyle != null){
 v_lineStyle = lineStyle;
}
/*ALCODEEND*/}

double f_resetChart()
{/*ALCODESTART::1727107912139*/
//Reset all previous text
t_previousTotalconsumption_MWh.setText("");
t_previousTotalImport_MWh.setText("");
t_previousTotalExport_MWh.setText("");

t_previousElectricityConsumption_pct.setText("");
t_previousGasConsumption_pct.setText("");
t_previousFFConsumption_pct.setText("");
t_previousH2Consumption_pct.setText("");
t_previousProduction_pct.setText("");

t_previousKPIselfsufficiency_pct.setText("");
t_previousKPIOverloadHours_pct.setText("");

//Reset all arrow visibility
arrow_down_green_totalconsumption.setVisible(false);
arrow_down_green_import.setVisible(false);
arrow_down_green_export.setVisible(false);
arrow_up_red_totalconsumption.setVisible(false);
arrow_up_red_export.setVisible(false);
arrow_up_red_import.setVisible(false);
line_total.setVisible(false);
line_import.setVisible(false);
line_export.setVisible(false);

arrow_down_red_electricity.setVisible(false);
arrow_down_green_gas.setVisible(false);
arrow_down_green_FF.setVisible(false);
arrow_down_red_h2.setVisible(false);
arrow_down_red_production.setVisible(false);
arrow_up_green_electricity.setVisible(false);
arrow_up_red_gas.setVisible(false);
arrow_up_red_FF.setVisible(false);
arrow_up_green_h2.setVisible(false);
arrow_up_green_production.setVisible(false);
line_electricity.setVisible(false);
line_gas.setVisible(false);
line_FF.setVisible(false);
line_h2.setVisible(false);
line_production.setVisible(false);

arrow_down_red_selfsufficiency.setVisible(false);
arrow_down_green_gridload.setVisible(false);
arrow_up_green_selfsufficiency.setVisible(false);
arrow_up_red_gridload.setVisible(false);
line_selfsufficiency.setVisible(false);
line_gridload.setVisible(false);
/*ALCODEEND*/}

