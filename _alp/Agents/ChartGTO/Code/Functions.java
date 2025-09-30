double f_setChartGTO()
{/*ALCODESTART::1726830495435*/
I_EnergyData data = uI_Results.f_getSelectedObjectData();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 60, true);

//At least for now grid nodes are not supported
if(uI_Results.v_selectedObjectScope != OL_ResultScope.ENERGYCOOP){
	f_setWarningScreen(true);
	return;
}

//Reset chart
f_resetChart();

//Set Charts
EnergyCoop energyCoop = (EnergyCoop)data.getLiveData().parentAgent;
f_setGTOBarChart(energyCoop);

/*ALCODEEND*/}

double f_resetChart()
{/*ALCODESTART::1727107912139*/
f_setWarningScreen(false);
chart_GTO.removeAll();
chart_peakIndividual.removeAll();
chart_peakCollective.removeAll();
chart_GTV.removeAll();
gr_GTOChart.setVisible(false); // Needed to refresh the graph
/*ALCODEEND*/}

double f_setGTOBarChart(EnergyCoop COOP)
{/*ALCODESTART::1759139695551*/
double originalGTO_kW = 0;
double originalPeakIndividual_kW = 0;
double originalPeakCollective_kW = 0;
double originalTotalGTV_kW = 0;

double scenarioDeltaGTO_kW = 0;
double scenarioDeltaPeakIndividual_kW = 0;
double scenarioDeltaPeakCollective_kW = 0;
double scenarioDeltaTotalGTV_kW = 0;
	
//Get the values
if(rb_GTO_delivery_or_feedin.getValue() == 0){//Delivery
	originalGTO_kW = COOP.f_getGroupContractDeliveryCapacity_kW(COOP.v_originalRapidRunData);
	originalPeakIndividual_kW = COOP.v_cumulativeIndividualPeakDeliveryOriginal_kW;
	originalPeakCollective_kW = max(0, COOP.v_originalRapidRunData.getPeakDelivery_kW());
	originalTotalGTV_kW = COOP.v_originalRapidRunData.connectionMetaData.contractedDeliveryCapacity_kW;

	scenarioDeltaGTO_kW = COOP.f_getGroupContractDeliveryCapacity_kW(COOP.v_rapidRunData)  - originalGTO_kW;
	scenarioDeltaPeakIndividual_kW = COOP.v_cumulativeIndividualPeakDelivery_kW  - originalPeakIndividual_kW;
	scenarioDeltaPeakCollective_kW = max(0, COOP.v_rapidRunData.getPeakDelivery_kW())  - originalPeakCollective_kW;
	scenarioDeltaTotalGTV_kW = COOP.v_rapidRunData.connectionMetaData.contractedDeliveryCapacity_kW  - originalTotalGTV_kW;
}
else if(rb_GTO_delivery_or_feedin.getValue() == 1){//Feedin
	originalGTO_kW = COOP.f_getGroupContractFeedinCapacity_kW(COOP.v_originalRapidRunData);
	originalPeakIndividual_kW = COOP.v_cumulativeIndividualPeakFeedinOriginal_kW;
	originalPeakCollective_kW = max(0, COOP.v_originalRapidRunData.getPeakFeedin_kW());
	originalTotalGTV_kW = COOP.v_originalRapidRunData.connectionMetaData.contractedFeedinCapacity_kW;

	scenarioDeltaGTO_kW = COOP.f_getGroupContractFeedinCapacity_kW(COOP.v_rapidRunData)  - originalGTO_kW;
	scenarioDeltaPeakIndividual_kW = COOP.v_cumulativeIndividualPeakFeedin_kW  - originalPeakIndividual_kW;
	scenarioDeltaPeakCollective_kW = max(0, COOP.v_rapidRunData.getPeakFeedin_kW())  - originalPeakCollective_kW;
	scenarioDeltaTotalGTV_kW = COOP.v_rapidRunData.connectionMetaData.contractedFeedinCapacity_kW  - originalTotalGTV_kW;
}


//Check wheter scenario should be smalles bar or original -> adjust accordingly (smaller bar is upfront, so difference can always be seen)
Pair<Double, Double> dataValues_chartGTO_kW = new Pair(originalGTO_kW, scenarioDeltaGTO_kW);
Pair<Color, Color> colors_chartGTO = new Pair(v_originalValueColor, v_scenarioDeltaValueColor);
Pair<String, String> legendLabels_chartGTO = new Pair("Huidige situatie", "Scenario");  
if(scenarioDeltaGTO_kW < 0){
	dataValues_chartGTO_kW = new Pair(originalGTO_kW + scenarioDeltaGTO_kW, -1*scenarioDeltaGTO_kW);
	colors_chartGTO = new Pair(v_scenarioDeltaValueColor, v_originalValueColor); 
	legendLabels_chartGTO = new Pair("Scenario", "Huidige situatie");   
}

Pair<Double, Double> dataValues_chartPeakIndividual_kW = new Pair(originalPeakIndividual_kW, scenarioDeltaPeakIndividual_kW);
Pair<Color, Color> colors_chartPeakIndividual = new Pair(v_originalValueColor, v_scenarioDeltaValueColor);
Pair<String, String> legendLabels_chartPeakIndividual = new Pair("Huidige situatie", "Scenario");   
if(scenarioDeltaPeakIndividual_kW < 0){
	dataValues_chartPeakIndividual_kW = new Pair(originalPeakIndividual_kW + scenarioDeltaPeakIndividual_kW, -1*scenarioDeltaPeakIndividual_kW);
	colors_chartPeakIndividual = new Pair(v_scenarioDeltaValueColor, v_originalValueColor); 
	legendLabels_chartPeakIndividual = new Pair("Scenario", "Huidige situatie");   
}

Pair<Double, Double> dataValues_chartPeakCollective_kW = new Pair(originalPeakCollective_kW, scenarioDeltaPeakCollective_kW);
Pair<Color, Color> colors_chartPeakCollective = new Pair(v_originalValueColor, v_scenarioDeltaValueColor);
Pair<String, String> legendLabels_chartPeakCollective = new Pair("Huidige situatie", "Scenario");   
if(scenarioDeltaPeakCollective_kW < 0){
	dataValues_chartPeakCollective_kW = new Pair(originalPeakCollective_kW + scenarioDeltaPeakCollective_kW, -1*scenarioDeltaPeakCollective_kW);
	colors_chartPeakCollective = new Pair(v_scenarioDeltaValueColor, v_originalValueColor); 
	legendLabels_chartPeakCollective = new Pair("Scenario", "Huidige situatie");   
}

Pair<Double, Double> dataValues_chartGTV_kW = new Pair(originalTotalGTV_kW, scenarioDeltaTotalGTV_kW);
Pair<Color, Color> colors_chartGTV = new Pair(v_originalValueColor, v_scenarioDeltaValueColor);
Pair<String, String> legendLabels_chartGTV = new Pair("Huidige situatie", "Scenario");   
if(scenarioDeltaTotalGTV_kW < 0){
	dataValues_chartGTV_kW = new Pair(originalTotalGTV_kW + scenarioDeltaTotalGTV_kW, -1*scenarioDeltaTotalGTV_kW);
	colors_chartGTV = new Pair(v_scenarioDeltaValueColor, v_originalValueColor); 
	legendLabels_chartGTV = new Pair("Scenario", "Huidige situatie");   
}	

//Create data items
DataItem first_DataItem_GTO_kW = new DataItem();
DataItem second_DataItem_GTO_kW = new DataItem();
DataItem first_DataItem_PeakIndividual_kW = new DataItem();
DataItem second_DataItem_PeakIndividual_kW = new DataItem();
DataItem first_DataItem_PeakCollective_kW = new DataItem();
DataItem second_DataItem_PeakCollective_kW = new DataItem();
DataItem first_DataItem_GTV_kW = new DataItem();
DataItem second_DataItem_GTV_kW = new DataItem();


//Set data item values
first_DataItem_GTO_kW.setValue(dataValues_chartGTO_kW.getFirst());
second_DataItem_GTO_kW.setValue(dataValues_chartGTO_kW.getSecond());
first_DataItem_PeakIndividual_kW.setValue(dataValues_chartPeakIndividual_kW.getFirst());
second_DataItem_PeakIndividual_kW.setValue(dataValues_chartPeakIndividual_kW.getSecond());
first_DataItem_PeakCollective_kW.setValue(dataValues_chartPeakCollective_kW.getFirst());
second_DataItem_PeakCollective_kW.setValue(dataValues_chartPeakCollective_kW.getSecond());
first_DataItem_GTV_kW.setValue(dataValues_chartGTV_kW.getFirst());
second_DataItem_GTV_kW.setValue(dataValues_chartGTV_kW.getSecond());
traceln("dataValues_chartGTV_kW.getFirst(): " + dataValues_chartGTV_kW.getFirst());
traceln("dataValues_chartGTV_kW.getSecond(): " + dataValues_chartGTV_kW.getSecond());

//Scale axis to same value for all 4 stack charts
double[] chartHeights_kW = {dataValues_chartGTO_kW.getFirst() + dataValues_chartGTO_kW.getSecond(), 
							dataValues_chartPeakIndividual_kW.getFirst() + dataValues_chartPeakIndividual_kW.getSecond(),
							dataValues_chartPeakCollective_kW.getFirst() + dataValues_chartPeakCollective_kW.getSecond(),
							dataValues_chartGTV_kW.getFirst() + dataValues_chartGTV_kW.getSecond()}; 
double chartScale_kW = max(chartHeights_kW);
chart_GTO.setFixedScale(chartScale_kW);
chart_peakIndividual.setFixedScale(chartScale_kW);
chart_peakCollective.setFixedScale(chartScale_kW);
chart_GTV.setFixedScale(chartScale_kW);


chart_GTO.addDataItem(first_DataItem_GTO_kW, legendLabels_chartGTO.getFirst(), colors_chartGTO.getFirst());
chart_GTO.addDataItem(second_DataItem_GTO_kW, legendLabels_chartGTO.getSecond(),  colors_chartGTO.getSecond());

chart_peakIndividual.addDataItem(first_DataItem_PeakIndividual_kW, legendLabels_chartPeakIndividual.getFirst(), colors_chartPeakIndividual.getFirst());
chart_peakIndividual.addDataItem(second_DataItem_PeakIndividual_kW, legendLabels_chartPeakIndividual.getSecond(), colors_chartPeakIndividual.getSecond());

chart_peakCollective.addDataItem(first_DataItem_PeakCollective_kW, legendLabels_chartPeakCollective.getFirst(), colors_chartPeakCollective.getFirst());
chart_peakCollective.addDataItem(second_DataItem_PeakCollective_kW, legendLabels_chartPeakCollective.getSecond(), colors_chartPeakCollective.getSecond());

chart_GTV.addDataItem(first_DataItem_GTV_kW, legendLabels_chartGTV.getFirst(), colors_chartGTV.getFirst());
chart_GTV.addDataItem(second_DataItem_GTV_kW, legendLabels_chartGTV.getSecond(), colors_chartGTV.getSecond());

gr_GTOChart.setVisible(true);  // Needed to refresh the graph
/*ALCODEEND*/}

double f_setWarningScreen(boolean showWarningScreen)
{/*ALCODESTART::1759139897063*/
gr_warningScreen.setVisible(showWarningScreen);
/*ALCODEEND*/}

