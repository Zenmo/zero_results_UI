/**
 * J_ColorMap
 */	
import java.util.EnumMap;

public class J_ColorMap <E extends Enum<E>> extends EnumMap<E, Color> {
	//private final EnumSet<E> enumSet;
    /**
     * Default constructor
     */
    public J_ColorMap(Class<E> enumClass) {
    	super(enumClass);
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