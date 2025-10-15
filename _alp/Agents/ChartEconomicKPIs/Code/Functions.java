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


//Capex
double totalInstalledBatteryStorageCapacity_kWh = data.getRapidRunData().assetsMetaData.totalInstalledBatteryStorageCapacity_MWh.doubleValue()*1000;
double CAPEX_eur = 0;
if (totalInstalledBatteryStorageCapacity_kWh > 0){
	CAPEX_eur = totalInstalledBatteryStorageCapacity_kWh*v_turnkeyBatteryCost_eur_p_kWh;
}

//Economic Analysis


//Economic KPI conclusion
double paybackPeriod_yr = 0;
double lifetimeBattery_yr = 0;
double roiLifetimeBattery_yr = 0;
double equivalentAnnualCostBattery_eur_p_yr = 0;
if (totalInstalledBatteryStorageCapacity_kWh > 0){
	paybackPeriod_yr = f_calculatePaybackPeriod(data, totalNetElectricityCosts_eur, totalNetElectricityCostsBaseline_eur);
	lifetimeBattery_yr = f_calculateBatteryLifetime_yr(data.getRapidRunData().getBatteriesSOCts_fr().getTimeSeries());
	roiLifetimeBattery_yr = f_ROI_eur(data, totalNetElectricityCosts_eur, totalNetElectricityCostsBaseline_eur, CAPEX_eur, lifetimeBattery_yr);
	equivalentAnnualCostBattery_eur_p_yr = f_calculateEquivalentAnnualCostBESS(totalNetElectricityCosts_eur, CAPEX_eur, lifetimeBattery_yr);
}
double totalAnnualCosts_eur_p_yr = totalNetElectricityCosts_eur + equivalentAnnualCostBattery_eur_p_yr;

//Set new values text
DecimalFormat df = new DecimalFormat("#,###");
DecimalFormat df_r = new DecimalFormat("#.#");
DecimalFormat df_2decimal = new DecimalFormat("0.00");

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

// Capex costs
t_totalInstalledBatteryCapacityAmount.setText(df.format(roundToInt(totalInstalledBatteryStorageCapacity_kWh)));
t_totalCapex_eur.setText("€ " + df.format(roundToInt(CAPEX_eur)));

// Conclusion
t_paybackPeriod_yr.setText(df_2decimal.format(paybackPeriod_yr));
t_profitBatteryLifetime_eur.setText("€ " + df.format(roundToInt(roiLifetimeBattery_yr)));
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

double f_calculatePaybackPeriod(I_EnergyData data,double totalNetElectricityCosts_eur,double totalNetElectricityCostsBaseline_eur)
{/*ALCODESTART::1758447541994*/
double paybackPeriod_yr = 0;


if (totalNetElectricityCosts_eur < totalNetElectricityCostsBaseline_eur) {
	
	double installedCapacity_kWh = data.getRapidRunData().assetsMetaData.totalInstalledBatteryStorageCapacity_MWh.doubleValue()*1000;
	double CAPEX_eur = installedCapacity_kWh*v_turnkeyBatteryCost_eur_p_kWh;
    paybackPeriod_yr = CAPEX_eur/(totalNetElectricityCostsBaseline_eur - totalNetElectricityCosts_eur);
    return paybackPeriod_yr;
    		
}

paybackPeriod_yr = Double.POSITIVE_INFINITY;
return paybackPeriod_yr;
/*ALCODEEND*/}

double f_calculateBatteryLifetime_yr(double[] arraySoC_fr)
{/*ALCODESTART::1758450204619*/
double alpha = -15568.83;//-5440.35;
double beta = 2239.43;//1191.54;

double cycleDamage_p_yr = J_CalculateBatteryLifetime.calculateYearlyCycleDamage(arraySoC_fr, alpha, beta);
double lifetimeBattery_yr = (cycleDamage_p_yr > 0) ? 1 / cycleDamage_p_yr : Double.POSITIVE_INFINITY;

return lifetimeBattery_yr;
/*ALCODEEND*/}

double f_ROI_eur(I_EnergyData data,double totalNetElectricityCosts_eur,double totalNetElectricityCostsBaseline_eur,double CAPEX_eur,double batteryLifetime_yr)
{/*ALCODESTART::1758452338110*/
double ReturnOnInvestment_eur = 0;

ReturnOnInvestment_eur = batteryLifetime_yr * (totalNetElectricityCostsBaseline_eur - totalNetElectricityCosts_eur) - CAPEX_eur;
return ReturnOnInvestment_eur;
/*ALCODEEND*/}

double f_calculateEquivalentAnnualCostBESS(double totalNetElectricityCosts_eur,double CAPEX_eur,double lifetimeBattery_yr)
{/*ALCODESTART::1760533200747*/
double realDiscountRate = (1+v_discountRate)/(1+v_inflationRate) - 1;
capitalRecoveryFactor = (realDiscountRate*Math.pow(1+realDiscountRate,lifetimeBattery_yr))/(Math.pow(1+realDiscountRate,lifetimeBattery_yr) - 1);
equivalentAnnualCostCAPEX_eur_p_yr = CAPEX_eur*capitalRecoveryFactor;
equivalentAnnualCostOPEX_eur_p_yr = v_operationalMaintenanceCosts_eur_p_yr * CAPEX_eur;
equivalentAnnualCostBESS_eur_p_yr = equivalentAnnualCostCAPEX_eur_p_yr + equivalentAnnualCostOPEX_eur_p_yr

/*ALCODEEND*/}

