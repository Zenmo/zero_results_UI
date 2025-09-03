double f_setChartEconomicKPIs()
{/*ALCODESTART::1726830495435*/
I_EnergyData data = uI_Results.f_getSelectedObjectData();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 60, true);

//At least for now grid nodes are not supported
if(uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE){
	return;
}


//Reset chart
f_resetChart();

//Set KPIs
f_setKPIs(data);

f_addChartEconomicCosts(data);

//Set visible
uI_Results.chartKPISummary_presentation.setVisible(true);

/*ALCODEEND*/}

double f_setKPIs(I_EnergyData data)
{/*ALCODESTART::1726830499836*/
double simulationLength_hr =  uI_Results.energyModel.p_runEndTime_h - uI_Results.energyModel.p_runStartTime_h;

////Calculate the values
double[] netLoad_kW = data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getTimeSeries_kW();

double totalImportCosts_eur = f_calculateImportCosts_eur(netLoad_kW);
double totalExportRevenue_eur = f_calculateExportRevenue_eur(netLoad_kW);
double totalCapacityCosts_eur = f_calculateCapacityCosts_eur(netLoad_kW);
double totalNetElectricityCosts_eur = f_calculateNetElectricityCosts_eur(netLoad_kW);

double totalImportCosts_r = 100*(totalImportCosts_eur / totalNetElectricityCosts_eur);
double totalExportRevenue_r = 100*(totalExportRevenue_eur / totalNetElectricityCosts_eur);
double totalCapacityCosts_r = 100*(totalCapacityCosts_eur / totalNetElectricityCosts_eur);
double totalNetElectricityCosts_r = 100*(totalNetElectricityCosts_eur / totalNetElectricityCosts_eur);

//Calculate electricity costs without battery
double[] netBaselineLoad_kW = f_calculateBaselineNetLoad(netLoad_kW.length);

double totalImportCostsBaseline_eur = f_calculateImportCosts_eur(netBaselineLoad_kW);
double totalExportRevenueBaseline_eur = f_calculateExportRevenue_eur(netBaselineLoad_kW);
double totalCapacityCostsBaseline_eur = f_calculateCapacityCosts_eur(netBaselineLoad_kW);
double totalNetElectricityCostsBaseline_eur = f_calculateNetElectricityCosts_eur(netBaselineLoad_kW);

double differenceImportCostsAgainstBaseline_eur = totalImportCosts_eur - totalImportCostsBaseline_eur;
double differenceExportRevenueAgainstBaseline_eur = totalExportRevenue_eur - totalExportRevenueBaseline_eur;
double differenceCapacityCostsAgainstBaseline_eur = totalCapacityCosts_eur - totalCapacityCostsBaseline_eur;
double differenceNetElectricityCostsAgainstBaseline_eur = totalNetElectricityCosts_eur - totalNetElectricityCostsBaseline_eur;

/*	
double elecConsumption_pct = data.getRapidRunData().activeConsumptionEnergyCarriers.contains(OL_EnergyCarriers.ELECTRICITY) ? data.getRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getIntegral_MWh() / totalEnergyConsumption_MWh * 100 : 0;
double gasConsumption_pct = data.getRapidRunData().activeConsumptionEnergyCarriers.contains(OL_EnergyCarriers.METHANE) ? data.getRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(OL_EnergyCarriers.METHANE).getIntegral_MWh() / totalEnergyConsumption_MWh * 100 : 0;
double FFconsumption_pct = data.getRapidRunData().activeConsumptionEnergyCarriers.contains(OL_EnergyCarriers.DIESEL) ? data.getRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(OL_EnergyCarriers.DIESEL).getIntegral_MWh() / totalEnergyConsumption_MWh * 100 : 0;
double h2consumption_pct = data.getRapidRunData().activeConsumptionEnergyCarriers.contains(OL_EnergyCarriers.HYDROGEN) ? data.getRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(OL_EnergyCarriers.HYDROGEN).getIntegral_MWh() / totalEnergyConsumption_MWh * 100 : 0;
double production_MWh = data.getRapidRunData().getTotalEnergyProduced_MWh();

double KPIselfsufficiency_pct = data.getRapidRunData().getTotalEnergySelfConsumed_MWh() / totalEnergyConsumption_MWh * 100;

//Total overload hours as a Pct of total simulation hours 
double KPIOverloadHours_pct = (data.getRapidRunData().getTotalOverloadDurationDelivery_hr() + data.getRapidRunData().getTotalOverloadDurationFeedin_hr())/simulationLength_hr * 100;
*/


//Set new values text
DecimalFormat df = new DecimalFormat("#,###");
DecimalFormat df_r = new DecimalFormat("#.#");

t_totalImportCosts_eur.setText("€ " + df.format(roundToInt(totalImportCosts_eur)));
t_totalExportRevenue_eur.setText("€ " + df.format(roundToInt(totalExportRevenue_eur)));
t_totalCapacityCosts_eur.setText("€ " + df.format(roundToInt(totalCapacityCosts_eur)));
t_totalNetElectricityCosts_eur.setText("€ " + df.format(roundToInt(totalNetElectricityCosts_eur)));

t_totalImportCosts_r.setText("(" + df.format(roundToInt(totalImportCosts_r)) + "%)");
t_totalExportRevenue_r.setText("(" + df.format(roundToInt(totalExportRevenue_r)) + "%)");
t_totalCapacityCosts_r.setText("(" + df.format(roundToInt(totalCapacityCosts_r)) + "%)");
t_totalNetElectricityCosts_r.setText("(" + df.format(roundToInt(totalNetElectricityCosts_r)) + "%)");

if (roundToInt(differenceImportCostsAgainstBaseline_eur) != 0) {
	t_diffTotalImportCosts_eur.setVisible(true);
	t_diffTotalImportCosts_eur.setText("€ " + df.format(roundToInt(differenceImportCostsAgainstBaseline_eur)));
	if(roundToInt(differenceImportCostsAgainstBaseline_eur) > 0){
		arrow_up_red_import.setVisible(true);
	} else if(roundToInt(differenceImportCostsAgainstBaseline_eur) < 0){
		arrow_down_green_import.setVisible(true);
	}
}
if (roundToInt(differenceExportRevenueAgainstBaseline_eur) != 0) {
	t_diffTotalExportRevenue_eur.setVisible(true);
	t_diffTotalExportRevenue_eur.setText("€ " + df.format(roundToInt(differenceExportRevenueAgainstBaseline_eur)));
	if(roundToInt(differenceExportRevenueAgainstBaseline_eur) > 0){
		arrow_up_green_export.setVisible(true);
	} else if(roundToInt(differenceExportRevenueAgainstBaseline_eur) < 0){
		arrow_down_red_export.setVisible(true);
	}
}
if (roundToInt(differenceCapacityCostsAgainstBaseline_eur) != 0) {
	t_diffTotalCapacityCosts_eur.setVisible(true);
	t_diffTotalCapacityCosts_eur.setText("€ " + df.format(roundToInt(differenceCapacityCostsAgainstBaseline_eur)));
	if(roundToInt(differenceCapacityCostsAgainstBaseline_eur) > 0){
		arrow_up_red_capacity.setVisible(true);
	} else if(roundToInt(differenceCapacityCostsAgainstBaseline_eur) < 0){
		arrow_down_green_capacity.setVisible(true);
	}
}
if (roundToInt(differenceNetElectricityCostsAgainstBaseline_eur) != 0) {
	t_diffTotalNetElectricityCosts_eur.setVisible(true);
	t_diffTotalNetElectricityCosts_eur.setText("€ " + df.format(roundToInt(differenceNetElectricityCostsAgainstBaseline_eur)));
	if(roundToInt(differenceNetElectricityCostsAgainstBaseline_eur) > 0){
		arrow_up_red_netElectricity.setVisible(true);
	} else if(roundToInt(differenceNetElectricityCostsAgainstBaseline_eur) < 0){
		arrow_down_green_netElectricity.setVisible(true);
	}
}

/*
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
*/


//Calculate and set previous values (if they exist) + arrows and styling
/*if(data.getPreviousRapidRunData() != null && data.getPreviousRapidRunData().getTotalEnergyConsumed_MWh() > 0){

	double previousTotalEnergyConsumption_MWh = data.getPreviousRapidRunData().getTotalEnergyConsumed_MWh();
	double previousTotalImport_MWh = data.getPreviousRapidRunData().getTotalEnergyImport_MWh();
	double previousTotalExport_MWh = data.getPreviousRapidRunData().getTotalEnergyExport_MWh();
	
	
	double previousElectricityConsumption_pct = data.getPreviousRapidRunData().activeConsumptionEnergyCarriers.contains(OL_EnergyCarriers.ELECTRICITY) ? data.getPreviousRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getIntegral_MWh() / previousTotalEnergyConsumption_MWh * 100 : 0;
	double previousGasConsumption_pct = data.getPreviousRapidRunData().activeConsumptionEnergyCarriers.contains(OL_EnergyCarriers.METHANE) ? data.getPreviousRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(OL_EnergyCarriers.METHANE).getIntegral_MWh() / previousTotalEnergyConsumption_MWh * 100 : 0;
	double previousFFConsumption_pct = data.getPreviousRapidRunData().activeConsumptionEnergyCarriers.contains(OL_EnergyCarriers.DIESEL) ? data.getPreviousRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(OL_EnergyCarriers.DIESEL).getIntegral_MWh() / previousTotalEnergyConsumption_MWh * 100 : 0;
	double previousH2Consumption_pct = data.getPreviousRapidRunData().activeConsumptionEnergyCarriers.contains(OL_EnergyCarriers.HYDROGEN) ? data.getPreviousRapidRunData().am_dailyAverageConsumptionAccumulators_kW.get(OL_EnergyCarriers.HYDROGEN).getIntegral_MWh() / previousTotalEnergyConsumption_MWh * 100 : 0; 
	double previousProduction_MWh = data.getPreviousRapidRunData().getTotalEnergyProduced_MWh();  
	
	double previousKPIselfsufficiency_pct = data.getPreviousRapidRunData().getTotalEnergySelfConsumed_MWh() / previousTotalEnergyConsumption_MWh * 100; 
	
	//Overload of GC for GC and overload of all gridnodes combined for Region (GN is not supported and shows same KPIs as whole region)
	double previousKPIOverloadHours_pct = (data.getPreviousRapidRunData().getTotalOverloadDurationDelivery_hr() + data.getPreviousRapidRunData().getTotalOverloadDurationFeedin_hr())/ simulationLength_hr * 100;
	
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
	if(t_totalImportCosts_eur.getText().equals(t_previousTotalImport_MWh.getText())){
		line_import.setVisible(true);
	} else if(totalImportCosts_eur > previousTotalImport_MWh){
		arrow_up_red_import.setVisible(true);
	} else if(totalImportCosts_eur < previousTotalImport_MWh){
		arrow_down_green_import.setVisible(true);
	}
	
	//Export
	if(t_totalExportRevenue_eur.getText().equals(t_previousTotalExport_MWh.getText())){
		line_export.setVisible(true);
	} else if(totalExportRevenue_eur > previousTotalExport_MWh){
		arrow_up_red_export.setVisible(true);
	} else if(totalExportRevenue_eur < previousTotalExport_MWh){
		arrow_down_green_export.setVisible(true);
	}*/
	/*
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
}*/



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
t_diffTotalImportCosts_eur.setVisible(false);
t_diffTotalExportRevenue_eur.setVisible(false);
t_diffTotalCapacityCosts_eur.setVisible(false);
t_diffTotalNetElectricityCosts_eur.setVisible(false);

//t_previousElectricityConsumption_pct.setText("");
//t_previousGasConsumption_pct.setText("");
//t_previousFFConsumption_pct.setText("");
//t_previousH2Consumption_pct.setText("");
//t_previousProduction_pct.setText("");

//t_previousKPIselfsufficiency_pct.setText("");
//t_previousKPIOverloadHours_pct.setText("");

//Reset all arrow visibility
arrow_down_green_import.setVisible(false);
arrow_up_green_export.setVisible(false);
arrow_down_green_capacity.setVisible(false);
arrow_down_green_netElectricity.setVisible(false);
arrow_up_red_import.setVisible(false);
arrow_down_red_export.setVisible(false);
arrow_up_red_capacity.setVisible(false);
arrow_up_red_netElectricity.setVisible(false);
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

double[] f_calculateBaselineNetLoad(int n)
{/*ALCODESTART::1755864693939*/
GridNode GN = findFirst(uI_Results.energyModel.pop_gridNodes, p -> p.p_gridNodeID.equals("T0"));

double p_timeStep_h = uI_Results.energyModel.p_timeStep_h;

double[] netLoad_kW = new double[n];

int startTimeDayIndex = 0;
int endTimeDayIndex = 35040;

for (GridConnection GC : GN.f_getAllLowerLVLConnectedGridConnections()){
	if(!GC.v_isActive) {
		continue;
	}
	List<J_EAProfile> elecConsumptionProfiles = new ArrayList<J_EAProfile>(); //survey inkoop profile data
	List<J_EAProfile> elecHeatPumpProfiles = new ArrayList<J_EAProfile>(); //survey WP profile data
	List<J_EAProfile> elecEVProfiles = new ArrayList<J_EAProfile>(); //Custom EV profile data
	List<J_EAProfile> surveyHeatDemandProfiles = new ArrayList<J_EAProfile>(); //survey gas to heat builing profiles
	List<J_EAConsumption> genericHeatDemandProfiles = new ArrayList<J_EAConsumption>(); //Generic gas to heat builing profiles
	List<J_EAConsumption> genericBuildingProfiles = new ArrayList<J_EAConsumption>(); //Generic inkoop builing profiles
	List<J_EAProduction> productionAssetProfiles = new ArrayList<J_EAProduction>(); // Production profiles
					
	elecConsumptionProfiles.addAll(findAll(GC.c_profileAssets, profile -> profile.assetFlowCategory == OL_AssetFlowCategories.fixedConsumptionElectric_kW));
	elecHeatPumpProfiles.addAll(findAll(GC.c_profileAssets, profile -> profile.assetFlowCategory == OL_AssetFlowCategories.heatPumpElectricityConsumption_kW));
	elecEVProfiles.addAll(findAll(GC.c_profileAssets, profile -> profile.assetFlowCategory == OL_AssetFlowCategories.evChargingPower_kW));
	if(GC.f_getCurrentHeatingType() == OL_GridConnectionHeatingType.ELECTRIC_HEATPUMP && !(GC.p_heatingManagement instanceof J_HeatingManagementGhost)) {
		surveyHeatDemandProfiles.addAll(findAll(GC.c_profileAssets, profile -> profile.energyCarrier == OL_EnergyCarriers.HEAT));
		genericHeatDemandProfiles.addAll(findAll(GC.c_consumptionAssets, cons -> cons.energyAssetType == OL_EnergyAssetType.HEAT_DEMAND));
	}
	genericBuildingProfiles.addAll(findAll(GC.c_consumptionAssets, cons -> cons.energyAssetType == OL_EnergyAssetType.ELECTRICITY_DEMAND));
	productionAssetProfiles.addAll(findAll(GC.c_productionAssets, prod -> prod.energyAssetType == OL_EnergyAssetType.PHOTOVOLTAIC));
				
	for(J_EAProfile elecConsumptionProfile : elecConsumptionProfiles) {
		if(elecConsumptionProfile != null){
			double[] tempNettoBalance_kW = Arrays.copyOfRange(elecConsumptionProfile.a_energyProfile_kWh, startTimeDayIndex, endTimeDayIndex);
	        for (int i = 0; i < tempNettoBalance_kW.length; i++) {
	            tempNettoBalance_kW[i] = tempNettoBalance_kW[i] / p_timeStep_h;
				netLoad_kW[i] += tempNettoBalance_kW[i];
			}
		}
	}
	for(J_EAProfile elecHeatPumpProfile : elecHeatPumpProfiles) {
		if(elecHeatPumpProfile != null){
			double[] tempNettoBalance_kW = Arrays.copyOfRange(elecHeatPumpProfile.a_energyProfile_kWh, startTimeDayIndex, endTimeDayIndex);
	        for (int i = 0; i < tempNettoBalance_kW.length; i++) {
	            tempNettoBalance_kW[i] = tempNettoBalance_kW[i] / p_timeStep_h;
				netLoad_kW[i] += tempNettoBalance_kW[i];
			}
		}
	}
	for(J_EAProfile elecEVProfile : elecEVProfiles) {
		if(elecEVProfile != null){
			double[] tempNettoBalance_kW = Arrays.copyOfRange(elecEVProfile.a_energyProfile_kWh, startTimeDayIndex, endTimeDayIndex);
	        for (int i = 0; i < tempNettoBalance_kW.length; i++) {
	            tempNettoBalance_kW[i] = tempNettoBalance_kW[i] / p_timeStep_h;
				netLoad_kW[i] += tempNettoBalance_kW[i];
			}
		}
	}
	for(J_EAProfile surveyHeatDemandProfile : surveyHeatDemandProfiles) {
		if(surveyHeatDemandProfile != null){
			double[] heatPower_kW = Arrays.copyOfRange(surveyHeatDemandProfile.a_energyProfile_kWh, startTimeDayIndex, endTimeDayIndex);
	        for (int i = 0; i < heatPower_kW.length; i++) {
	            heatPower_kW[i] = heatPower_kW[i] / p_timeStep_h;
			}
			double eta_r = uI_Results.energyModel.avgc_data.p_avgEfficiencyHeatpump;
			double outputTemperature_degC = uI_Results.energyModel.avgc_data.p_avgOutputTemperatureHeatpump_degC;
		    for(double time = 0; time < 8760; time += p_timeStep_h){
		    	double baseTemperature_degC = uI_Results.energyModel.pp_ambientTemperature_degC.getValue(time);
		    	double COP_r = eta_r * ( 273.15 + outputTemperature_degC ) / ( outputTemperature_degC - baseTemperature_degC );
				netLoad_kW[roundToInt(time/p_timeStep_h)] += heatPower_kW[roundToInt(time/p_timeStep_h)] / COP_r;
			}
		}
	}
	for(J_EAConsumption genericHeatDemandProfile : genericHeatDemandProfiles) {
		if(genericHeatDemandProfile != null){
			double eta_r = uI_Results.energyModel.avgc_data.p_avgEfficiencyHeatpump;
			double outputTemperature_degC = uI_Results.energyModel.avgc_data.p_avgOutputTemperatureHeatpump_degC;
			
			for(double time = 0; time < 8760; time += p_timeStep_h){
			    double baseTemperature_degC = uI_Results.energyModel.pp_ambientTemperature_degC.getValue(time);
			    double COP_r = eta_r * ( 273.15 + outputTemperature_degC ) / ( outputTemperature_degC - baseTemperature_degC );
				netLoad_kW[roundToInt(time/p_timeStep_h)] += genericHeatDemandProfile.getProfilePointer().getValue(time)*genericHeatDemandProfile.yearlyDemand_kWh*genericHeatDemandProfile.getConsumptionScaling_fr() / COP_r;
			}
		}
	}
	for(J_EAConsumption genericBuildingProfile : genericBuildingProfiles) {
		if(genericBuildingProfile != null){ //table function 
			for(double time = 0; time < 8760; time += p_timeStep_h){
				netLoad_kW[roundToInt(time/p_timeStep_h)] += genericBuildingProfile.getProfilePointer().getValue(time)*genericBuildingProfile.yearlyDemand_kWh*genericBuildingProfile.getConsumptionScaling_fr();
			}
		}
	}
	for(J_EAProduction productionAssetProfile : productionAssetProfiles) {
		if (productionAssetProfile != null) { //table function 
			for(double time = 0; time < 8760; time += p_timeStep_h){
				netLoad_kW[roundToInt(time/p_timeStep_h)] -= productionAssetProfile.getProfilePointer().getValue(time)*productionAssetProfile.getCapacityElectric_kW();
			}
		}
	}
}
return netLoad_kW;
/*ALCODEEND*/}

double f_calculateImportCosts_eur(double[] netLoad_kW)
{/*ALCODESTART::1755867186286*/
double VAT_fr = 0.21;
double ODE_eur_p_kwh = 0;
double EB_eur_p_kwh = 0.03868; // https://www.belastingdienst.nl/wps/wcm/connect/bldcontentnl/belastingdienst/zakelijk/overige_belastingen/belastingen_op_milieugrondslag/energiebelasting/
    	
double costsElectricityImport_euro = 0;
double currentElectricityPriceCharge_eurpkWh = 0;
    	
for (int i = 0; i < netLoad_kW.length; i++) {
    currentElectricityPriceCharge_eurpkWh = uI_Results.energyModel.pp_dayAheadElectricityPricing_eurpMWh.getAllValues()[(int) Math.floor(i*uI_Results.energyModel.p_timeStep_h)] / 1000;
    costsElectricityImport_euro += (1+VAT_fr)*((currentElectricityPriceCharge_eurpkWh + ODE_eur_p_kwh + EB_eur_p_kwh) * max(0,netLoad_kW[i]) * uI_Results.energyModel.p_timeStep_h);
}
    	
return costsElectricityImport_euro;
/*ALCODEEND*/}

double f_calculateExportRevenue_eur(double[] netLoad_kW)
{/*ALCODESTART::1755868450603*/
double costsElectricityExport_euro = 0;
double currentElectricityPriceCharge_eurpkWh = 0;
    	
for (int i = 0; i < netLoad_kW.length; i++) {
    currentElectricityPriceCharge_eurpkWh = uI_Results.energyModel.pp_dayAheadElectricityPricing_eurpMWh.getAllValues()[(int) Math.floor(i*uI_Results.energyModel.p_timeStep_h)] / 1000;
    costsElectricityExport_euro += currentElectricityPriceCharge_eurpkWh * max(0,-netLoad_kW[i]) * uI_Results.energyModel.p_timeStep_h;
}
    	
return costsElectricityExport_euro;
/*ALCODEEND*/}

double f_calculateCapacityCosts_eur(double[] netLoad_kW)
{/*ALCODESTART::1755868971285*/
double costsCapacityRate_euro = 0;

GridNode GN_T0 = findFirst(uI_Results.energyModel.pop_gridNodes, p -> p.p_gridNodeID.equals("T0"));
double contractedCapacity_kW = GN_T0.p_capacity_kW;
double VAT_fr = 0.21;
double annualConnectionRate_euro_p_yr = 5351;
double annualFixedTransportRate_euro_p_yr = 2760;
double annualContractCapacityRate_euro_p_kW_yr = 42.10;
double monthlyPeakPowerRate_euro_p_kW_month = 4.48;
    	
double[] monthlyPeakDemand_kW = f_calculateMonthlyPeakDemand_kW(netLoad_kW);
    
costsCapacityRate_euro = (1+VAT_fr)*(annualConnectionRate_euro_p_yr + annualFixedTransportRate_euro_p_yr + annualContractCapacityRate_euro_p_kW_yr * contractedCapacity_kW + monthlyPeakPowerRate_euro_p_kW_month * Arrays.stream(monthlyPeakDemand_kW).sum());

return costsCapacityRate_euro;
/*ALCODEEND*/}

double[] f_calculateMonthlyPeakDemand_kW(double[] netLoad_kW)
{/*ALCODESTART::1755869067049*/
double[] monthlyPeakDemand_kW = new double[12];
int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
int sampleCounter = 0;
    	
for(int month=0; month < daysInMonth.length; month++) {
    
    double maxLoad_kW = 0;
    
    int samplesInMonth = daysInMonth[month] * 96;
    int startMonthIndex = sampleCounter;
    int endMonthIndex = sampleCounter + samplesInMonth;
    		
	for (int i = startMonthIndex; i < endMonthIndex && i < netLoad_kW.length; i++) {
    	double absLoad_kW = Math.abs(netLoad_kW[i]);
        if (absLoad_kW > maxLoad_kW) {
        	maxLoad_kW = absLoad_kW;
        }
    }
    monthlyPeakDemand_kW[month] = maxLoad_kW;
   	sampleCounter += samplesInMonth;
}
	
return monthlyPeakDemand_kW;
/*ALCODEEND*/}

double f_calculateNetElectricityCosts_eur(double[] netLoad_kW)
{/*ALCODESTART::1755869113668*/
double costsElectricityNet_euro = 0;

costsElectricityNet_euro = f_calculateImportCosts_eur(netLoad_kW) - f_calculateExportRevenue_eur(netLoad_kW) + f_calculateCapacityCosts_eur(netLoad_kW);
    	
return costsElectricityNet_euro;
/*ALCODEEND*/}

double f_addChartEconomicCosts(I_EnergyData data)
{/*ALCODESTART::1756288376916*/
double[] netLoad_kW = data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getTimeSeries_kW();

double totalImportCosts_eur = f_calculateImportCosts_eur(netLoad_kW);
double totalExportRevenue_eur = f_calculateExportRevenue_eur(netLoad_kW);
double totalCapacityCosts_eur = f_calculateCapacityCosts_eur(netLoad_kW);
double totalNetElectricityCosts_eur = f_calculateNetElectricityCosts_eur(netLoad_kW);

double[] costs_eur = {
    totalImportCosts_eur,			// + cost
    -totalExportRevenue_eur,		// - revenue
    totalCapacityCosts_eur			// + cost
};
String[] labels = {"Import", "Export", "Capaciteit"};




DefaultCategoryDataset ds = new DefaultCategoryDataset();
String series = "EUR";

double cumulative = 0;
for (int i = 0; i < costs_eur.length; i++) {
	cumulative += costs_eur[i];
    ds.addValue(costs_eur[i], series, labels[i]);
    //traceln(cumulative);
}
ds.addValue(cumulative, series, "Netto");






// 2) Create the waterfall chart
JFreeChart chart = ChartFactory.createWaterfallChart("Elektriciteitskosten", "Kosten", "€", ds, PlotOrientation.VERTICAL, false, true, false);

// 3) Optional styling (colors for first/+, −, last bars)
CategoryPlot plot = (CategoryPlot) chart.getPlot();
plot.setBackgroundPaint(Color.WHITE);
plot.setOutlineVisible(false);
plot.setRangeGridlinesVisible(false);
plot.setDomainGridlinesVisible(false);

CategoryAxis x = (CategoryAxis) plot.getDomainAxis();
x.setLowerMargin(0);     
x.setUpperMargin(0.15);  
x.setCategoryMargin(0); 

WaterfallBarRenderer r = (WaterfallBarRenderer) plot.getRenderer();
r.setFirstBarPaint(new Color(200, 120, 120));
r.setPositiveBarPaint(new Color(200, 120, 120));
r.setNegativeBarPaint(new Color(120, 180, 120));
r.setLastBarPaint(new Color(120, 120, 200));
r.setDrawBarOutline(false);

int widthOfSVG = 460;
int heightOfSVG = 350;

chart.draw(svg2d,new Rectangle2D.Double(0, 0, widthOfSVG, heightOfSVG));
svgImgJfree.setSVG(svg2d.getSVGElement());
svgImgJfree.setVisible(true);
gr_waterfallChart.setVisible(true);

/*ALCODEEND*/}

