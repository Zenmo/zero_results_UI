double f_setKPISummaryChart()
{/*ALCODESTART::1726830495435*/
I_EnergyData data = uI_Results.f_getSelectedObjectData();
f_setWarningScreen(false);

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 60, false);

//At least for now grid nodes are not supported
if(uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE){
	f_setWarningScreen(true);
	return;
}

//Reset chart
f_resetChart();

//Set KPIs
f_setKPIs(data);

//Set visible
uI_Results.chartKPISummary_presentation.setVisible(true);

/*ALCODEEND*/}

double f_setKPIs(I_EnergyData data)
{/*ALCODESTART::1726830499836*/
double simulationLength_hr =  uI_Results.energyModel.p_timeParameters.getRunEndTime_h() - uI_Results.energyModel.p_timeParameters.getRunStartTime_h();

////Calculate the values
double peakDelivery_MW = data.getRapidRunData().getPeakDelivery_kW()/1000;
double peakDelivery_percentageEV_pct = 0;

if(data.getRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.evChargingPower_kW)){
	double dataSetStartTime_h = uI_Results.energyModel.p_timeParameters.getRunStartTime_h(); //
	double peakTime_h = data.getRapidRunData().getHighestBalanceTime_h(OL_EnergyCarriers.ELECTRICITY);
	double EVConsumption_during_peakDelivery_MW = data.getRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.evChargingPower_kW).getIntegral_MWh(peakTime_h, peakTime_h+0.25)*4;
	peakDelivery_percentageEV_pct = EVConsumption_during_peakDelivery_MW/peakDelivery_MW*100;
}
 
//Set new values text
DecimalFormat df = new DecimalFormat("0.00");

//t_KPIselfsufficiency_pct.setText(df.format(KPIselfsufficiency_pct) + " %");
t_KPIMaxPeak_MW.setText(df.format(peakDelivery_MW) + " MW");
t_KPIEVPeak_pct.setText(df.format(peakDelivery_percentageEV_pct) + " % komt door EVs");

//Calculate and set previous values (if they exist) + arrows and styling
if(data.getPreviousRapidRunData() != null && data.getPreviousRapidRunData().getTotalEnergyConsumed_MWh() > 0){
	
	//Get previous values
	double previousPeakDelivery_MW = data.getPreviousRapidRunData().getPeakDelivery_kW()/1000; 
	double previousPeakDelivery_percentageEV_pct = 0;

	if(data.getPreviousRapidRunData().assetsMetaData.activeAssetFlows.contains(OL_AssetFlowCategories.evChargingPower_kW)){
		double previousPeakTime_h = data.getPreviousRapidRunData().getHighestBalanceTime_h(OL_EnergyCarriers.ELECTRICITY);
		double previousEVConsumption_during_peakDelivery_MW = data.getPreviousRapidRunData().am_assetFlowsAccumulators_kW.get(OL_AssetFlowCategories.evChargingPower_kW).getIntegral_MWh(previousPeakTime_h, previousPeakTime_h+0.25)*4;
		previousPeakDelivery_percentageEV_pct = previousEVConsumption_during_peakDelivery_MW/previousPeakDelivery_MW*100;
	}
	
	t_previousKPIMaxPeak_MW.setText(df.format(previousPeakDelivery_MW) + " MW");
	t_previousKPIEVPeak_pct.setText("("+ df.format(previousPeakDelivery_percentageEV_pct) + " % door EVs)");
	
	////Set correct arrow and colors
	//Gridload
	if(peakDelivery_MW == previousPeakDelivery_MW){
		line_gridload.setVisible(true);
	} else if(peakDelivery_MW > previousPeakDelivery_MW){
		arrow_up_red_gridload.setVisible(true);
	} else if(peakDelivery_MW < previousPeakDelivery_MW){
		arrow_down_green_gridload.setVisible(true);
	}
	
	
	//Pct of peak is EV
	if(peakDelivery_percentageEV_pct == previousPeakDelivery_percentageEV_pct){
		line_KPIEVPeak.setVisible(true);
	} else if(peakDelivery_percentageEV_pct > previousPeakDelivery_percentageEV_pct){
		arrow_up_red_KPIEVPeak.setVisible(true);
	} else if(peakDelivery_percentageEV_pct < previousPeakDelivery_percentageEV_pct){
		arrow_down_green_KPIEVPeak.setVisible(true);
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
t_previousKPIEVPeak_pct.setText("-");
t_previousKPIMaxPeak_MW.setText("-");

//Reset all arrow visibility
arrow_down_green_KPIEVPeak.setVisible(false);
arrow_down_green_gridload.setVisible(false);
arrow_up_red_KPIEVPeak.setVisible(false);
arrow_up_red_gridload.setVisible(false);
line_KPIEVPeak.setVisible(false);
line_gridload.setVisible(false);
/*ALCODEEND*/}

double f_setWarningScreen(boolean showWarningScreen)
{/*ALCODESTART::1770903056378*/
gr_warningScreen.setVisible(showWarningScreen);
/*ALCODEEND*/}

