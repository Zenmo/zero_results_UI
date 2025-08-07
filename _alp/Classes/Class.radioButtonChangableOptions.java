/**
 * radioButtonChangableOptions
 */	
public class radioButtonChangableOptions extends ShapeRadioButtonGroup implements Serializable {

    /**
     * Default constructor
     */
    public radioButtonChangableOptions(Presentable p, boolean ispublic, double x, double y, double width, double height, Color textColor, boolean enabled, Font font, boolean vertical, String[] texts) {
    	super(p, ispublic, x, y, width, height, textColor, enabled, font, vertical, texts);
    }

    public void setOptions(ArrayList<String> stringArray1, ArrayList<String> stringArray2) {
		this.a(stringArray1, stringArray2, true);
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