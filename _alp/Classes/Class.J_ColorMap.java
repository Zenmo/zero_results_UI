/**
 * J_ColorMap
 */	
import java.util.EnumMap;

public class J_ColorMap extends EnumMap<OL_EnergyCarriers, Color> {

    /**
     * Default constructor
     */
    public J_ColorMap() {
    	super(OL_EnergyCarriers.class);
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