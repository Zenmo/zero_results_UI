double f_setChartTotalCosts()
{/*ALCODESTART::1772471828102*/
I_EnergyData data = uI_Results.f_getSelectedObjectData();

//For now: Only support single GCs
if(uI_Results.v_selectedObjectScope != OL_ResultScope.GRIDCONNECTION){
	List<OL_ResultScope> supportedResultScopes = new ArrayList<>(List.of(OL_ResultScope.GRIDCONNECTION));
	uI_Results.f_activateChartBlocker(supportedResultScopes);
	return;
}

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 60, true);

//Reset chart
f_resetChart();

////Get and set the values with help from the economic charts
DecimalFormat df = new DecimalFormat("#,##0");
Map<String, Double> map_totalCostsPerCatagory_eur = new HashMap<>();
double totalEnergyCosts_eurpyr = uI_Results.chartEnergyCosts.f_getTotalEnergyCosts_eurpyr(data.getRapidRunData());
map_totalCostsPerCatagory_eur.put("Energiekosten", totalEnergyCosts_eurpyr);
t_totalEnergyCosts.setText("€ " + df.format(roundToInt(totalEnergyCosts_eurpyr)) + " /yr");

double[] netLoad_kW = data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getTimeSeries_kW();	
double totalConnectionCosts_eurpyr = uI_Results.chartConnectionCosts.f_calculateTotalConnectionCosts_eurpyr(data.getRapidRunData().connectionMetaData, netLoad_kW);
map_totalCostsPerCatagory_eur.put("Netaansluitingskosten", totalConnectionCosts_eurpyr);
t_totalConnectionCosts.setText("€ " + df.format(roundToInt(totalConnectionCosts_eurpyr)) + " /yr");

double totalCAPEXAndOPEXCosts_eurpyr = uI_Results.chartCAPEXAndOPEX.f_getTotalCAPEXAndOPEXCosts_eurpyr(data.getRapidRunData());
map_totalCostsPerCatagory_eur.put("CAPEX & OPEX", totalCAPEXAndOPEXCosts_eurpyr);
t_CAPEXAndOPEXCosts.setText("€ " + df.format(roundToInt(totalCAPEXAndOPEXCosts_eurpyr)) + " /yr");

double totalCosts_eurpyr = totalEnergyCosts_eurpyr + totalConnectionCosts_eurpyr + totalCAPEXAndOPEXCosts_eurpyr;	
t_totalCosts.setText("€ " + df.format(roundToInt(totalCosts_eurpyr)) + " /yr");

if(data.getPreviousRapidRunData() != null){
	double previousTotalEnergyCosts_eurpyr = uI_Results.chartEnergyCosts.f_getTotalEnergyCosts_eurpyr(data.getPreviousRapidRunData());
	t_previousTotalEnergyCosts.setText("€ " + df.format(roundToInt(previousTotalEnergyCosts_eurpyr)) + " /yr");
	
	double previousNetLoad_kW[] = data.getPreviousRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getTimeSeries_kW();
	double previousTotalConnectionCosts_eurpyr = uI_Results.chartConnectionCosts.f_calculateTotalConnectionCosts_eurpyr(data.getPreviousRapidRunData().connectionMetaData, netLoad_kW);
	t_previousTotalConnectionCosts.setText("€ " + df.format(roundToInt(previousTotalConnectionCosts_eurpyr)) + " /yr");
	
	double previousTotalCAPEXAndOPEXCosts_eurpyr = uI_Results.chartCAPEXAndOPEX.f_getTotalCAPEXAndOPEXCosts_eurpyr(data.getPreviousRapidRunData());
	t_previousCAPEXAndOPEXCosts.setText("€ " + df.format(roundToInt(previousTotalCAPEXAndOPEXCosts_eurpyr)) + " /yr");
	
	double previousTotalCosts_eurpyr = previousTotalEnergyCosts_eurpyr + previousTotalConnectionCosts_eurpyr + previousTotalCAPEXAndOPEXCosts_eurpyr;	
	t_previousTotalCosts.setText("€ " + df.format(roundToInt(previousTotalCosts_eurpyr)) + " /yr");
}

//Set Pie chart
f_setPieChart(map_totalCostsPerCatagory_eur);
/*ALCODEEND*/}

double f_resetChart()
{/*ALCODESTART::1772472124331*/
t_previousTotalEnergyCosts.setText("");
t_previousTotalConnectionCosts.setText("");
t_previousCAPEXAndOPEXCosts.setText("");
t_previousTotalCosts.setText("");

//Clear pie chart
pieChart_totalSubdivision.removeAll();
gr_chartTotalSubdivision.setVisible(false);
/*ALCODEEND*/}

double f_setPieChart(Map<String, Double> map_totalCostsPerCatagory_eur)
{/*ALCODESTART::1776685464029*/
for(String catagory : map_totalCostsPerCatagory_eur.keySet()){
	DataItem costs = new DataItem();
	costs.setValue(roundToInt(map_totalCostsPerCatagory_eur.get(catagory)));
	pieChart_totalSubdivision.addDataItem(costs, catagory + " [€/yr]", map_catagoryToColor.get(catagory));
}
gr_chartTotalSubdivision.setVisible(true);
/*ALCODEEND*/}

