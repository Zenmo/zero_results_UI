/**
 * CustomButton
 */	
public class CustomButton {
	//Default
	private ShapeRectangle button;
	private ShapeText text;
	private ShapeImage image;
	
	//Enabled/disabled & selected
	private boolean enabled = true;
	private boolean selected = false;
	
	//Styling
	private CustomButtonStyling customButtonStyling;
	private CustomButtonImageSelection customButtonImageSelection;
	private static Color defaultDisabledColor = silver;
	
	////Constructors
	
	//Constructor for button with default styling, without text and image	
	public CustomButton(ShapeRectangle button){
		this(button, null, null, getDefaultStyling(button, null), null);
	}
	
	//Constructor for button with custom styling, without text and image	
	public CustomButton(ShapeRectangle button, CustomButtonStyling customButtonStyling){
		this(button, null, null, customButtonStyling, null);
	}
	
	//Constructor for button with default styling, without image
	public CustomButton(ShapeRectangle button, ShapeText text){
		this(button, text, null, getDefaultStyling(button, text), null);
	}
	
	//Constructor for button with custom styling, without image
	public CustomButton(ShapeRectangle button, ShapeText text, CustomButtonStyling customButtonStyling){
		this(button, text, null, customButtonStyling, null);
	}
	
	//Constructor for button with image, with default styling and image selection (index 0)
	public CustomButton(ShapeRectangle button, ShapeText text, ShapeImage image){
		this(button, text, image, getDefaultStyling(button, text), new CustomButtonImageSelection(0, 0, 0, 0));
	}
	
	//Constructor for button with image, with default image selection (index 0)
	public CustomButton(ShapeRectangle button, ShapeText text, ShapeImage image, CustomButtonStyling customButtonStyling){
		this(button, text, image, customButtonStyling, new CustomButtonImageSelection(0, 0, 0, 0));
	}
	
	//Constructor for button with image and image selection
	public CustomButton(ShapeRectangle button, ShapeText text, ShapeImage image, CustomButtonStyling customButtonStyling, CustomButtonImageSelection customButtonImageSelection){
	    //Check if button is constructed correctly
		if(button == null || customButtonStyling == null) {
	    	throw new RuntimeException("Can not create a custom button without shapeRectangle (or rounded) or styling!");
	    }
		
		//Initialize the fields
		this.button = button;
	    this.text = text;
	    this.image = image;
	    this.customButtonStyling = customButtonStyling;
	    this.customButtonImageSelection = customButtonImageSelection;
	    
	    //Initialize button styling
	    updateStyling();
	}
	
	//Adjust contents or styling of the button
	public void setText(String txt){
		if(text != null) {
			text.setText(txt);
		}
		else {
			throw new RuntimeException("Can not set the text of a button without a ShapeText");
		}
	}
	
    public void reStyle(CustomButtonStyling customButtonStyling) {
    	this.customButtonStyling = customButtonStyling;
    	updateStyling();
    }
    
    public void reConfigureImageIndexes(CustomButtonImageSelection customButtonImageSelection) {
    	this.customButtonImageSelection = customButtonImageSelection;
    	updateImageIndex();
    }
    
	//Call button on click
	public void clickButton() {
		button.onClick(0, 0); // Needs clickx and clicky, does nothing with it -> 0,0 is used.
	}
	
	////Button states
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        updateStyling();
    }
    
    public void setSelected(boolean select, boolean executeOnClick) {
    	this.selected = select;
    	updateStyling();
    	if(executeOnClick) {
    		clickButton();
    	}
    }
    
	public void setVisible(boolean visible){
		button.setVisible(visible);
		if(text != null) {
			text.setVisible(visible);
		}
		if(image != null) {
			image.setVisible(visible);
		}
	}

    public boolean isEnabled() { return enabled; }
    public boolean isSelected() { return selected; }
    public boolean isVisible() { return button.isVisible(); }
	
    ////Styling
    private void updateStyling() {
        if (this.enabled) {
            if (this.selected) {
                style(customButtonStyling.selectedStyling());
            }
            else {
                style(customButtonStyling.defaultStyling());
            }
        }
        else {
            if (this.selected) {
                style(customButtonStyling.disabledAndSelectedStyling());
            }
            else {
                style(customButtonStyling.disabledStyling());
            }
        }

        updateImageIndex();
    }

    private void updateImageIndex() {
        if (image != null) {
            if (this.enabled) {
                if (this.selected) {
                    image.setIndex(customButtonImageSelection.selectedImageIndex());
                }
                else {
                    image.setIndex(customButtonImageSelection.defaultImageIndex());
                }
            }
            else {
                if (this.selected) {
                    image.setIndex(customButtonImageSelection.disabledAndSelectedImageIndex());
                }
                else {
                    image.setIndex(customButtonImageSelection.disabledImageIndex());
                }
            }
        }
    }

    private void style(CustomButtonStateStyle customButtonStateStyle) {
        button.setFillColor(customButtonStateStyle.fillColor());
        if(text != null) {
        	text.setColor(customButtonStateStyle.textColor());
        }
        button.setLineColor(customButtonStateStyle.lineColor());
        button.setLineStyle(customButtonStateStyle.lineStyle());
        button.setLineWidth(customButtonStateStyle.lineWidth());
    }

    //Default styling (no difference between selected and not selected, disabled becomes lightGray)
    private static CustomButtonStyling getDefaultStyling(ShapeRectangle button, ShapeText text) {
		Color defaultTextColor = text != null ? text.getColor() : null;
        CustomButtonStateStyle base = new CustomButtonStateStyle(button.getFillColor(), defaultTextColor,
                                         button.getLineColor(), button.getLineStyle(), button.getLineWidth());
        CustomButtonStateStyle disabled = new CustomButtonStateStyle(defaultDisabledColor, defaultTextColor,
        															 defaultDisabledColor, button.getLineStyle(), button.getLineWidth());
        return new CustomButtonStyling(base, base, disabled, disabled);
    }

    //Helper records
    public static record CustomButtonStyling(CustomButtonStateStyle defaultStyling, CustomButtonStateStyle selectedStyling, CustomButtonStateStyle disabledStyling, CustomButtonStateStyle disabledAndSelectedStyling) {
    }
    public static record CustomButtonStateStyle(Color fillColor, Color textColor, Color lineColor, LineStyle lineStyle, double lineWidth) {
    }
    public static record CustomButtonImageSelection(int defaultImageIndex, int selectedImageIndex, int disabledImageIndex, int disabledAndSelectedImageIndex) {
    }
    public static CustomButtonImageSelection createCustomButtonImageSelection(int defaultImageIndex) {
    	return new CustomButtonImageSelection(defaultImageIndex, defaultImageIndex, defaultImageIndex, defaultImageIndex);
    }
}