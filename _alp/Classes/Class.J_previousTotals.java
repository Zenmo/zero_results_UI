/**
 * J_previousTotals
 */	
//Used to store the previous totals of an AreaCollection. 
//This is done for all grid connections and the main region.
public class J_previousTotals {

	private J_FlowsMap previousTotalImports_MWh;
	private J_FlowsMap previousTotalExports_MWh;
	private Double previousTotalConsumedEnergy_MWh;
	private Double previousTotalProducedEnergy_MWh;
	private	Double previousSelfConsumedEnergy_MWh;
	private	Double previousImportedEnergy_MWh;
	private	Double previousExportedEnergy_MWh;
	private	Double previousSelfConsumedElectricity_MWh;
	private	Double previousSelfProducedElectricity_MWh;
	private	Double previousElectricityConsumed_MWh;
	private	Double previousElectricityForTransportDemand_MWh;
	private Double previousOverloadDurationDelivery_hr;
	private Double previousOverloadDurationFeedin_hr;
	private Double previousTotalTimeOverloadedTransformers_hr;
    /**
     * Default constructor
     */
    public J_previousTotals() {
    }
    
    
    //Setters
    public void setPreviousTotalImports_MWh (J_FlowsMap previousTotalImports_MWh) {
    	this.previousTotalImports_MWh = previousTotalImports_MWh;
    }
    
    public void setPreviousTotalExports_MWh (J_FlowsMap previousTotalExports_MWh) {
    	this.previousTotalExports_MWh = previousTotalExports_MWh;
    }
	
    public void setPreviousTotalConsumedEnergy_MWh(Double previousTotalConsumedEnergy_MWh) {
		this.previousTotalConsumedEnergy_MWh = previousTotalConsumedEnergy_MWh;
	}
	
	public void setPreviousTotalProducedEnergy_MWh(Double previousTotalProducedEnergy_MWh) {
		this.previousTotalProducedEnergy_MWh = previousTotalProducedEnergy_MWh;
	}
	
	public void setPreviousSelfConsumedEnergy_MWh(Double previousSelfConsumedEnergy_MWh){
        this.previousSelfConsumedEnergy_MWh = previousSelfConsumedEnergy_MWh;
	}
	
	public void setPreviousImportedEnergy_MWh(Double previousImportedEnergy_MWh){
        this.previousImportedEnergy_MWh = previousImportedEnergy_MWh;
	}
	
	public void setPreviousExportedEnergy_MWh(Double previousExportedEnergy_MWh){
        this.previousExportedEnergy_MWh = previousExportedEnergy_MWh;
	}
	
	public void setPreviousSelfConsumedElectricity_MWh(Double previousSelfConsumedElectricity_MWh){
        this.previousSelfConsumedElectricity_MWh = previousSelfConsumedElectricity_MWh;
	}
	
	public void setPreviousSelfProducedElectricity_MWh(Double previousSelfProducedElectricity_MWh){
        this.previousSelfProducedElectricity_MWh = previousSelfProducedElectricity_MWh;
	}
	
	public void setPreviousElectricityConsumed_MWh(Double previousElectricityConsumed_MWh){
        this.previousElectricityConsumed_MWh = previousElectricityConsumed_MWh;
	}
	
	public void setPreviousElectricityForTransportDemand_MWh(Double previousElectricityForTransportDemand_MWh){
        this.previousElectricityForTransportDemand_MWh = previousElectricityForTransportDemand_MWh;
	}
	    
	public void setPreviousOverloadDurationDelivery_hr(Double previousOverloadDurationDelivery_hr){
        this.previousOverloadDurationDelivery_hr = previousOverloadDurationDelivery_hr;
	}
	
	public void setPreviousOverloadDurationFeedin_hr(Double previousOverloadDurationFeedin_hr){
        this.previousOverloadDurationFeedin_hr = previousOverloadDurationFeedin_hr;
	}

	public void setPreviousTotalTimeOverloadedTransformers_hr(Double previousTotalTimeOverloadedTransformers_hr){
        this.previousTotalTimeOverloadedTransformers_hr = previousTotalTimeOverloadedTransformers_hr;
	}
	
	
	//Getters
    public J_FlowsMap getPreviousTotalImports_MWh () {
    	return this.previousTotalImports_MWh;
    }
    
    public J_FlowsMap getPreviousTotalExports_MWh () {
    	return this.previousTotalExports_MWh;
    }
    
	public Double getPreviousTotalConsumedEnergy_MWh() {
		return this.previousTotalConsumedEnergy_MWh;
	}
	
	public Double getPreviousTotalProducedEnergy_MWh() {
		return this.previousTotalProducedEnergy_MWh;
	}
	
	public Double getPreviousSelfConsumedEnergy_MWh(){
        return this.previousSelfConsumedEnergy_MWh;
	}
	
	public Double getPreviousImportedEnergy_MWh(){
        return this.previousImportedEnergy_MWh;
	}
	
	public Double getPreviousExportedEnergy_MWh(){
        return this.previousExportedEnergy_MWh;
	}
	
	public Double getPreviousSelfConsumedElectricity_MWh(){
        return this.previousSelfConsumedElectricity_MWh;
	}
	
	public Double getPreviousSelfProducedElectricity_MWh(){
        return this.previousSelfProducedElectricity_MWh;
	}
	
	public Double getPreviousElectricityConsumed_MWh(){
        return this.previousElectricityConsumed_MWh;
	}
	
	public Double getPreviousElectricityForTransportDemand_MWh(){
        return this.previousElectricityForTransportDemand_MWh;
	}
    
	public Double getPreviousOverloadDurationDelivery_hr(){
        return this.previousOverloadDurationDelivery_hr;
	}
	
	public Double getPreviousOverloadDurationFeedin_hr(){
        return this.previousOverloadDurationFeedin_hr;
	}
	
	public Double getPreviousTotalTimeOverloadedTransformers_hr(){
        return this.previousTotalTimeOverloadedTransformers_hr;
	}
       
    
    
	@Override
	public String toString() {
		return super.toString();
	}

}