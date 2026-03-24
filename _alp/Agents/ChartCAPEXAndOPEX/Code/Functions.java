double f_setChartCAPEXAndOPEX()
{/*ALCODESTART::1772471794038*/
I_EnergyData data = uI_Results.f_getSelectedObjectData();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 60, true);

//Reset chart
f_resetChart();


////Get the netload values
f_initializeAssetSelectionComboBox(data);

//Set the actual values of the chart (while trying to maintain the previous selected EC)
String currentSelectedAssetReadableName = v_selectedAsset.equals(p_totalName) ? p_totalName : uI_Results.f_getAssetName(OL_EnergyAssetType.valueOf(v_selectedAsset));
if (Arrays.asList(cb_assetSelection.getItems()).contains(currentSelectedAssetReadableName)) {
	cb_assetSelection.setValue(currentSelectedAssetReadableName, true);
}
else{
	cb_assetSelection.setValue(p_totalName, true);
}

/*ALCODEEND*/}

double f_resetChart()
{/*ALCODESTART::1772472111433*/
gr_includeAssetSelection.setVisible(false);

//Previous values
t_previousEstimatedLifeTime.setText("-");
t_previousCAPEXTotal.setText("-");
t_previousCAPEXPerYear.setText("-");
t_previousOPEXTotal.setText("-");
t_previousOPEXPerYear.setText("-");
t_previousCAPEXAndOPEXTotal.setText("-");
t_previousCAPEXAndOPEXPerYear.setText("-");

/*ALCODEEND*/}

double f_getTotalCAPEXAndOPEXCosts_eurpyr(J_RapidRunData rapidRunData)
{/*ALCODESTART::1774272795145*/
double totalCAPEXAndOPEXCosts_eur = 0;
return totalCAPEXAndOPEXCosts_eur;


/*ALCODEEND*/}

double f_initializeAssetSelectionComboBox(I_EnergyData data)
{/*ALCODESTART::1774277754383*/
//Get energy carrier options (also previous run EC that are not in current).
Set<OL_EnergyAssetType> assets = new HashSet<>();

assets.addAll(data.getRapidRunData().assetsMetaData.getActiveAssets());
if(data.getPreviousRapidRunData() != null){
	assets.addAll(data.getPreviousRapidRunData().assetsMetaData.getActiveAssets());
}

//Order the list to always have the same order
List<OL_EnergyAssetType> orderedAssets = new ArrayList<>();
for(OL_EnergyAssetType EAType : c_defaultOrderAssets){
	if(assets.contains(EAType)){
		orderedAssets.add(EAType);
	}
}

//Convert to the readable combobox options
String[] comboBoxOptions = new String[orderedAssets.size() + 1];
int i = 0;
for(OL_EnergyAssetType EAType : orderedAssets){
	comboBoxOptions[i] = uI_Results.f_getAssetName(EAType);
	i++;
}
comboBoxOptions[i] = p_totalName;

cb_assetSelection.setItems(comboBoxOptions);

/*ALCODEEND*/}

double f_setChartCAPEXAndOPEXValues()
{/*ALCODESTART::1774278746224*/
I_EnergyData data = uI_Results.f_getSelectedObjectData();

//Reset chart
f_resetChart();

//Initialize variables
double lifeTime_yr = 0;
double CAPEX_eurpyr = 0;
double OPEX_eurpyr = 0;
Double previousLifeTime_yr = null;
Double previousCAPEX_eurpyr = null;
Double previousOPEX_eurpyr = null;


//Get selected List
List<OL_EnergyAssetType> selectedAssetList;
if(v_selectedAsset.equals(p_totalName)){
	selectedAssetList = new ArrayList<>(data.getRapidRunData().assetsMetaData.getActiveAssets());
	selectedAssetList.retainAll(c_includeAssetSelection); //Only keep 'included' asset types
}
else{
	selectedAssetList = new ArrayList<>(List.of(OL_EnergyAssetType.valueOf(v_selectedAsset)));
}

for(OL_EnergyAssetType assetType : selectedAssetList){
	//Get economicAVGC values
	double assetExpectedLifeTime_yr = uI_Results.energyModel.avgc_data.economicAVGC.map_avgAssetLifeTime_yr.get(assetType);
	double assetCAPEX_eurpkW = uI_Results.energyModel.avgc_data.economicAVGC.map_avgAssetCAPEX_eurpkW.get(assetType);
	double assetOPEX_eurpkWpyr = uI_Results.energyModel.avgc_data.economicAVGC.map_avgAssetOPEX_eurpkWpyr.get(assetType);
	//Get Current asset capacity
	double assetCapacity_kW = data.getRapidRunData().assetsMetaData.getActiveAssetCapacity_kW(assetType);
	if(assetCapacity_kW > 0){
		lifeTime_yr = assetExpectedLifeTime_yr;
		CAPEX_eurpyr += assetCapacity_kW * assetCAPEX_eurpkW / assetExpectedLifeTime_yr;
		OPEX_eurpyr += assetCapacity_kW * assetOPEX_eurpkWpyr;
	}
	
	//Get previous values, if previous rapid is available
	if(data.getPreviousRapidRunData() != null){
		if(previousCAPEX_eurpyr == null){
			previousLifeTime_yr = 0.0;
			previousCAPEX_eurpyr = 0.0;
			previousOPEX_eurpyr = 0.0;
		}
		//Get previous asset capacity		
		double previousAssetCapacity_kW = data.getPreviousRapidRunData().assetsMetaData.getActiveAssetCapacity_kW(assetType);
		if(previousAssetCapacity_kW > 0){
			previousLifeTime_yr = assetExpectedLifeTime_yr;
			previousCAPEX_eurpyr += previousAssetCapacity_kW * assetCAPEX_eurpkW / assetExpectedLifeTime_yr;
			previousOPEX_eurpyr += previousAssetCapacity_kW * assetOPEX_eurpkWpyr;
		}
	}
}

// For 'total' capex and opex (so multiple assets), the default life time is set to 20, so all calculations are done with the same length
if(v_selectedAsset.equals(p_totalName)){
	lifeTime_yr = 20;
	if(data.getPreviousRapidRunData() != null){
		previousLifeTime_yr = 20.0; 
	}
}

//Set KPIs in chart
f_setYearlyKPIs(lifeTime_yr, CAPEX_eurpyr, OPEX_eurpyr, previousLifeTime_yr, previousCAPEX_eurpyr, previousOPEX_eurpyr);

/*ALCODEEND*/}

double f_setYearlyKPIs(double lifeTime_yr,double CAPEX_eurpyr,double OPEX_eurpyr,Double previousLifeTime_yr,Double previousCAPEX_eurpyr,Double previousOPEX_eurpyr)
{/*ALCODESTART::1774279941226*/
//Set new values text
DecimalFormat df = new DecimalFormat("#,###0.00");

double CAPEXLifeTimeTotal_eur = CAPEX_eurpyr * lifeTime_yr;
double OPEXLifeTimeTotal_eur = OPEX_eurpyr * lifeTime_yr;
double CAPEXAndOPEXLifeTimeTotal_eur = CAPEXLifeTimeTotal_eur + OPEXLifeTimeTotal_eur;
double CAPEXAndOPEX_eurpyr = CAPEX_eurpyr + OPEX_eurpyr;

//Set chart texts
t_estimatedLifeTime.setText(roundToDecimal(lifeTime_yr, 2) + " jaar");
t_CAPEXTotal.setText("€ " + df.format(CAPEXLifeTimeTotal_eur));
t_CAPEXPerYear.setText("€ " + df.format(CAPEX_eurpyr));
t_OPEXTotal.setText("€ " + df.format(OPEXLifeTimeTotal_eur));
t_OPEXPerYear.setText("€ " + df.format(OPEX_eurpyr));
t_CAPEXAndOPEXTotal.setText("€ " + df.format(CAPEXAndOPEXLifeTimeTotal_eur));
t_CAPEXAndOPEXPerYear.setText("€ " + df.format(CAPEXAndOPEX_eurpyr));

//If previous values are known, set them as well
if(previousCAPEX_eurpyr != null){

	double previousCAPEXLifeTimeTotal_eur = CAPEX_eurpyr * lifeTime_yr;
	double previousOPEXLifeTimeTotal_eur = OPEX_eurpyr * lifeTime_yr;
	double previousCAPEXAndOPEXLifeTimeTotal_eur = CAPEXLifeTimeTotal_eur + OPEXLifeTimeTotal_eur;
	double previousCAPEXAndOPEX_eurpyr = CAPEX_eurpyr + OPEX_eurpyr;
	
	t_previousEstimatedLifeTime.setText(roundToDecimal(previousLifeTime_yr, 2) + " jaar");
	t_previousCAPEXTotal.setText("€ " + df.format(previousCAPEXLifeTimeTotal_eur));
	t_previousCAPEXPerYear.setText("€ " + df.format(previousCAPEX_eurpyr));
	t_previousOPEXTotal.setText("€ " + df.format(previousOPEXLifeTimeTotal_eur));
	t_previousOPEXPerYear.setText("€ " + df.format(previousOPEX_eurpyr));
	t_previousCAPEXAndOPEXTotal.setText("€ " + df.format(previousCAPEXAndOPEXLifeTimeTotal_eur));
	t_previousCAPEXAndOPEXPerYear.setText("€ " + df.format(previousCAPEXAndOPEX_eurpyr));
}	
	/*
	////Set arrows
	//Import
	if(previousTotalImportCosts_eur > totalImportCosts_eur){
		arrow_down_green_import.setVisible(true);
	}
	else if(totalImportCosts_eur > previousTotalImportCosts_eur){
		arrow_up_red_import.setVisible(true);
	}
	else{
		line_import.setVisible(true);
	}
	
	//Export
	if(previousTotalExportRevenue_eur > totalExportRevenue_eur){
		arrow_down_red_export.setVisible(true);
	}
	else if(totalExportRevenue_eur > previousTotalExportRevenue_eur){
		arrow_up_green_export.setVisible(true);
	}
	else{
		line_export.setVisible(true);
	}
	
	//Net
	if(previousTotalNetElectricityCosts_eur > totalNetElectricityCosts_eur){
		arrow_down_green_netEnergy.setVisible(true);
	}
	else if(totalNetElectricityCosts_eur > previousTotalNetElectricityCosts_eur){
		arrow_up_red_netEnergy.setVisible(true);
	}
	else{
		line_total.setVisible(true);
	}
}
else{ // No previous rapid data -> dont show previous values
	//t_previousTotalImportCosts_eur.setText("-");
	//t_previousTotalExportRevenue_eur.setText("-");
	//t_previousTotalNetEnergyCosts_eur.setText("-");
}
*/
/*ALCODEEND*/}

