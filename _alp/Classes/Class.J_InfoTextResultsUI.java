/**
 * J_InfoTextResultsUI
 */	
public class J_InfoTextResultsUI implements Serializable {

	
	public String lorumIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui	officia deserunt mollit anim id est laborum.";
    
	//// Results UI \\\\
	// stack charts
	public String consumption = "De totale consumptie is de optelling van al het verbruik. Onderstaande grafiek splitst dit uit per groep apparaten. Er is een basisbehoefte voor alle apparaten die niet los gemodelleerd worden. Consumptie is niet hetgeen afgenomen wordt uit het net. Dit is namelijk het verschil tussen consumptie en productie. Zie hiervoor de grafieken onder het knopje 'Netto Balans' hierboven.";
	public String production = "De totale productie is de optelling van alle opwek. Onderstaande grafiek splitst dit uit per type zoals zon of wind. Productie is niet hetgeen teruggeleverd wordt aan het net. Dit is namelijk het verschil tussen productie en consumptie. Zie hiervoor de grafieken onder het knopje 'Netto Balans' hierboven.";
	public String consumptionYear = "In deze grafiek zie je een jaaroverzicht van alle consumptie. Per dag wordt de gemiddelde consumptie uitgerekend en toegevoegd als een datapunt in de plot. De totale consumptie is de optelling van al het verbruik. Onderstaande grafiek splitst dit uit per groep apparaten. Er is een basisbehoefte voor alle apparaten die niet los gemodelleerd worden. Consumptie is niet hetgeen afgenomen wordt uit het net. Dit is namelijk het verschil tussen consumptie en productie. Bij een andere tijdskeuze dan een heel jaar is hiervoor een aparte grafiek beschikbaar onder het knopje 'Netto Balans'. Dit is echter geen zinnige grafiek voor daggemiddelden en wordt daarom niet getoond voor een jaaroverzicht.";
	public String productionYear = "In deze grafiek zie je een jaaroverzicht van alle productie. Per dag wordt de gemiddelde productie uitgerekend en toegevoegd als een datapunt in de plot. De totale productie is de optelling van alle opwek. Onderstaande grafiek splitst dit uit per type zoals zon of wind. Productie is niet hetgeen teruggeleverd wordt aan het net. Dit is namelijk het verschil tussen productie en consumptie. Bij een andere tijdskeuze dan een heel jaar is hiervoor een aparte grafiek beschikbaar onder het knopje 'Netto Balans'. Dit is echter geen zinnige grafiek voor daggemiddelden en wordt daarom niet getoond voor een jaaroverzicht.";
	// bar charts
	
	// netloaddurationcurve
	public String netLoadDurationCurve = "De belastingduurkromme is een weergave van het gebruik van je aansluiting. Op de y-as zie je het vermogen. Een positief vermogen bij afname en een negatief vermogen bij teruglevering. Op de x-as zie je tijd. Alle uren van het jaar zijn gesorteerd van hoogste vermogen naar laagste. De belastingduurkromme lees je als volgt af: Als je bij 2000 uur een vermogen van 100 kW ziet dan neem je dus 2000 uur van het jaar méér dan 100 kW af. Als de belastingduurkromme aan de linkerkant (bijna) boven je afname capaciteit komt dan is er afname congestie. Als de belastingduurkromme aan de rechterkant (bijna) onder je teruglever capaciteit komt is er teruglever congestie.";
	public String batterySize = "Met een batterij van deze capaciteit kan het profiel op de dag met de meeste afname uit het net vlakgetrokken worden als het profiel opgeschaald wordt zodat de volledige afnamecapaciteit benut wordt.";
	public String growthPotential = "Dit is de hoeveel toename in stroom die maximaal te realiseren valt met de huidige aansluiting. Hiervoor wordt rekening gehouden met de dag met de meeste afname vauit het net. Er is vanuit gegaan dat er volledige flexibiliteit is in het verschuiven van de load over die dag. Dit kan gedaan worden met een batterij van bovenstaande capaciteit. Een percentage van 100% betekend dat het huidige verbruik verdubbeld kan worden. Een negatief percentage betekend dat er al over de huidige aansluiting heen gegaan wordt en er dus sowieso een groter aansluitingscontract nodig is.";
	public String peakDelivery = "De Max afname is het gemiddelde vermogen over het kwartier met de meeste levering vanuit het net. ";
	public String peakFeedin = "Analoog aan de Max afname is de Max teruglevering het hoogste gemiddelde vermogen dat aan het net geleverd wordt.";
	// sankey
	
	
	
	public String x = "Met deze slider kun je instellen ... De minimum waarde van de slider is gezet op de hoveelheid die al in het huidige scenario aanwezig is.";

	/**
     * Default constructor
     */
    public J_InfoTextResultsUI() {
    }
    
    //public Pair<String, Integer> getLorumIpsum(int width_ch, String descriptionText) {
    	//return this.restrictWidth(descriptionText, width_ch);
    //}

    public Pair<String, Integer> restrictWidth( String txt, int width_ch ) {
    	StringBuilder output = new StringBuilder();
    	int remainingTextSize = txt.length();
    	int currentIndex = 0;
    	int lines = 0;
    	while (remainingTextSize > width_ch) {
    		int i = 0;
    		while (!Character.isWhitespace(txt.charAt(currentIndex + width_ch - i))) {
	    		i++;
	    		if (i > width_ch) {
	    			throw new RuntimeException("Impossible to format string to fit within width.");
	    		}
    		}
			output.append(txt.substring(currentIndex, currentIndex + width_ch - i));
    		output.append('\n');
    		currentIndex += width_ch - i + 1;
    		remainingTextSize -= width_ch - i + 1;
    		lines++;
    	}
    	output.append(txt.substring(currentIndex, txt.length()));
		lines++;
    	return new Pair(output.toString(), lines);
    }
    
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * This number is here for model snapshot storing purpose<br>
	 * It needs to be changed when this class gets changed
	 */ 
	private static final long serialVersionUID = 1L;

}