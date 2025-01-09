double f_setSankey()
{/*ALCODESTART::1714746374796*/
AreaCollection area = uI_Results.f_getDataObject();

double selfConsumedEnergy_MWh = area.v_totalEnergySelfConsumed_MWh;
double importE_MWh = area.fm_totalImports_MWh.get(OL_EnergyCarriers.ELECTRICITY);;
double importG_MWh = area.fm_totalImports_MWh.get(OL_EnergyCarriers.METHANE);;
double importF_MWh = area.fm_totalImports_MWh.get(OL_EnergyCarriers.DIESEL);;
double importH_MWh = area.fm_totalImports_MWh.get(OL_EnergyCarriers.HYDROGEN);
double exportH_MWh = area.fm_totalExports_MWh.get(OL_EnergyCarriers.HYDROGEN);
double exportE_MWh = area.v_totalEnergyExport_MWh - exportH_MWh;
double heatProduced_MWh = 0;

flowDataset = new DefaultFlowDataset();

if (heatProduced_MWh > 0.001) {
	flowDataset.setFlow(0, "opwek warmte", "verbruik", heatProduced_MWh);
	NodeKey key1 = new NodeKey<>(0, "opwek warmte");
	flowPlot.setNodeFillColor(key1, yellowGreen);
	if (selfConsumedEnergy_MWh-heatProduced_MWh > 0.001) {
		flowDataset.setFlow(0, "opwek stroom", "verbruik", (selfConsumedEnergy_MWh-heatProduced_MWh));
	}
	if (exportE_MWh > 0.001) {
		flowDataset.setFlow(0, "opwek stroom", "export stroom", exportE_MWh);
	}
	if (selfConsumedEnergy_MWh-heatProduced_MWh > 0.001 || exportE_MWh > 0.001) {
		NodeKey key2 = new NodeKey<>(0, "opwek stroom");
		flowPlot.setNodeFillColor(key2, yellowGreen);
	}
} else {
	if (selfConsumedEnergy_MWh > 0.001) {
		flowDataset.setFlow(0, "eigen opwek", "verbruik", (selfConsumedEnergy_MWh));
	}
	if ( exportE_MWh > 0.001) {
		NodeKey exportKey = new NodeKey<>(1,"export stroom");
		flowPlot.setNodeFillColor(exportKey, darkBlue);
		flowDataset.setFlow(0, "eigen opwek", "export stroom", exportE_MWh);
	}
	if (selfConsumedEnergy_MWh > 0.001 ||  exportE_MWh > 0.001) {
		NodeKey key2 = new NodeKey<>(0, "eigen opwek");
		flowPlot.setNodeFillColor(key2, yellowGreen);
	}
}
//flowDataset.setFlow(0, "import", "export", 0);

if (importE_MWh > 0.001) {
	flowDataset.setFlow(0, "import stroom", "verbruik", importE_MWh);
	NodeKey key3 = new NodeKey<>(0, "import stroom");
	flowPlot.setNodeFillColor(key3, red);
}
if (importG_MWh > 0.001 ) {
	flowDataset.setFlow(0, "import gas", "verbruik", importG_MWh);	
	NodeKey key4 = new NodeKey<>(0, "import gas");
	flowPlot.setNodeFillColor(key4, new Color(200,150,0));
}	
if (importF_MWh > 0.001 ) {
	flowDataset.setFlow(0, "import brandstof", "verbruik", importF_MWh);
	NodeKey key5 = new NodeKey<>(0, "import brandstof");
	flowPlot.setNodeFillColor(key5, orange);
}
if (importH_MWh > 0.001 ) {
	flowDataset.setFlow(0, "import waterstof", "verbruik", importH_MWh);
	NodeKey key6 = new NodeKey<>(0, "import waterstof");
	flowPlot.setNodeFillColor(key6, dodgerBlue);
}
if (exportH_MWh > 0.001 ) {
	flowDataset.setFlow(0, "eigen opwek", "export waterstof", exportH_MWh);
	NodeKey key7 = new NodeKey<>(1, "export waterstof");
	flowPlot.setNodeFillColor(key7, dodgerBlue);
}

//traceln(flowDataset.getAllNodes());
//traceln(flowDataset.getAllFlows());



//NodeKey hydrogenExportKey = new NodeKey<>(1, "export waterstof");
//flowPlot.setNodeFillColor(hydrogenExportKey, dodgerBlue);

//JFreeChart flowChart = new JFreeChart(flowPlot);
flowChart.setTitle("Energiestromen");
flowChart.setAntiAlias(true);
// Increasing NodeMargin is a crude way to prevent overlapping text
flowPlot.setNodeMargin(0.05);
flowPlot.setDefaultNodeLabelFont(new Font("SansSerif", Font.BOLD, 18));
//flowPlot.setDefaultNodeLabelPaint(darkBlue);
flowPlot.setDataset(flowDataset);	
int widthOfSVG = 400;
int heightOfSVG = 600;

flowChart.draw(svg2d,new Rectangle2D.Double(0, 0, widthOfSVG, heightOfSVG));
svgImgJfree.setSVG(svg2d.getSVGElement());
svgImgJfree.setVisible(true);
gr_sankey.setVisible(true);
/*ALCODEEND*/}

