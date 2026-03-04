double f_setChartCO2()
{/*ALCODESTART::1772441389067*/
I_EnergyData data = uI_Results.f_getSelectedObjectData();

//Set selected object display
uI_Results.f_setSelectedObjectDisplay(230, 60, true);

//Reset chart
f_resetChart();


////Get the netload values
double[] netLoad_kW;
double[] previousNetLoad_kW = null;
if(uI_Results.v_selectedObjectScope == OL_ResultScope.GRIDNODE){
	netLoad_kW = uI_Results.v_gridNode.acc_annualElectricityBalance_kW.getTimeSeries_kW();
}
else{
	netLoad_kW = data.getRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getTimeSeries_kW();
	if(data.getPreviousRapidRunData() != null){
		previousNetLoad_kW = data.getPreviousRapidRunData().am_totalBalanceAccumulators_kW.get(OL_EnergyCarriers.ELECTRICITY).getTimeSeries_kW();
	}
}

////Calculate the values

//Current values
double[] monthlyElectricityImportCO2Emissions_kg = f_calculateMonthlyElectricityImportCO2Emission_kg(netLoad_kW);

double totalElectricityImportCO2Emissions_kg = ZeroMath.arraySum(monthlyElectricityImportCO2Emissions_kg);

//Previous values
Double previousTotalElectricityImportCO2Emissions_kg = null;

if(previousNetLoad_kW != null){
	double[] previousMonthlyElectricityImportCO2Emissions_kg = f_calculateMonthlyElectricityImportCO2Emission_kg(previousNetLoad_kW);

	previousTotalElectricityImportCO2Emissions_kg = ZeroMath.arraySum(previousMonthlyElectricityImportCO2Emissions_kg);
}


//Set yearly kpis
f_setYearlyKPIs(totalElectricityImportCO2Emissions_kg, previousTotalElectricityImportCO2Emissions_kg);

//Set monthly chart
f_setMonthlyChart(monthlyElectricityImportCO2Emissions_kg);


/*ALCODEEND*/}

double f_setYearlyKPIs(Double totalCO2Emission_kg,Double previousTotalCO2Emission_kg)
{/*ALCODESTART::1772441389069*/
//Set new values text
DecimalFormat df = new DecimalFormat("0.00");

t_totalCO2Emission_kg.setText("€ " + df.format(roundToInt(totalCO2Emission_kg)));

if(previousTotalCO2Emission_kg != null){
	t_previousTotalCO2Emission_kg.setText("€ " + df.format(roundToInt(previousTotalCO2Emission_kg)));
	
	////Set arrows
	if(previousTotalCO2Emission_kg > totalCO2Emission_kg){
		arrow_down_green_CO2Emission.setVisible(true);
	}
	else if(totalCO2Emission_kg > previousTotalCO2Emission_kg){
		arrow_up_red_CO2Emission.setVisible(true);
	}
	else{
		line_CO2Emission.setVisible(true);
	}
}
else{ // No previous rapid data -> dont show previous values
	t_previousTotalCO2Emission_kg.setText("-");
}
/*ALCODEEND*/}

double f_styleBackground_override(Color backgroundColor,Color lineColor,Double lineWidth,LineStyle lineStyle)
{/*ALCODESTART::1772441389071*/
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
{/*ALCODESTART::1772441389073*/
//Reset all arrow visibility
arrow_down_green_CO2Emission.setVisible(false);
arrow_up_red_CO2Emission.setVisible(false);
line_CO2Emission.setVisible(false);

//Reset KPIS
t_totalCO2Emission_kg.setText("-");
t_previousTotalCO2Emission_kg.setText("-");

//Clear monthly chart
bar_CO2EmissionMonthly.removeAll();
/*ALCODEEND*/}

double f_calculateCapacityCosts_eur(double[] netLoad_kW)
{/*ALCODESTART::1772441389075*/
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
{/*ALCODESTART::1772441389077*/
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

double f_setMonthlyChart(double[] monthlyCO2Emission_kg)
{/*ALCODESTART::1772441389081*/
DataSet netCosts_eur = new DataSet(12);

double maxChartValue_eur = 0;

for (int i = 0; i < 12; i++) {
	//Import cost
	DataItem CO2Emission_kg = new DataItem();
	CO2Emission_kg.setValue(monthlyCO2Emission_kg[i]);
	bar_CO2EmissionMonthly.addDataItem(CO2Emission_kg, "", uI_Results.v_electricityDemandColor);
	
	maxChartValue_eur = max(maxChartValue_eur, monthlyCO2Emission_kg[i]);
}

//Set fixed scale
maxChartValue_eur *=1.2;
bar_CO2EmissionMonthly.setFixedScale(0, maxChartValue_eur);

/*ALCODEEND*/}

double[] f_calculateMonthlyElectricityImportCO2Emission_kg(double[] netLoad_kW)
{/*ALCODESTART::1772441389083*/
int[] startHourPerMonth = startHourPerMonthTemporary;
double timeStep_h = uI_Results.energyModel.p_timeParameters.getTimeStep_h();

double[] monthlyElectricityImportCO2Emission_kg = new double[12];

int currentMonth = 0;


for (int i = 0; i < netLoad_kW.length; i++) {
	if(currentMonth != 11 && startHourPerMonth[currentMonth+1] < i*timeStep_h){
		currentMonth += 1;
	}

    double currentCO2EmissionFactor_kgpkWh =
            uI_Results.energyModel.pp_CO2EmissionFactorElectricityImport_kgpkWh.getAllValues()[(int) Math.floor(i * timeStep_h)];

    double timestepCO2Emission_kg =
            currentCO2EmissionFactor_kgpkWh * max(0, netLoad_kW[i]) * timeStep_h;

    monthlyElectricityImportCO2Emission_kg[currentMonth] += timestepCO2Emission_kg;
}

return monthlyElectricityImportCO2Emission_kg;

/*ALCODEEND*/}

double f_calculateMonthlyECFixedCO2Emission_kg(OL_EnergyCarriers EC,double[] ECTotalBalance_kWh)
{/*ALCODESTART::1772643915089*/

/*ALCODEEND*/}

