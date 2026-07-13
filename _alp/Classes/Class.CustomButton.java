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
	
	//Constructor for button without image
	CustomButton(ShapeRectangle button, ShapeText text, CustomButtonStyling customButtonStyling){
		this(button, text, null, customButtonStyling);
	}
	
	//Constructor for button with image
	CustomButton(ShapeRectangle button, ShapeText text, ShapeImage image, CustomButtonStyling customButtonStyling){
	    this.button = button;
	    this.text = text;
	    this.image = image;
	    this.customButtonStyling = customButtonStyling;
	}
	
	/*
	public ShapeRectangle getButton() {
	    return button;
	}

	public ShapeText getText() {
	    return text;
	}

	public ShapeImage getImage() {
	    return image;
	}*/
	

	public void setText(String txt){
		text.setText(txt);
	}

	
	private void style(Color fillColor, Color textColor, Color lineColor, LineStyle lineStyle, double lineWidth) {
		button.setFillColor(fillColor);
		text.setColor(textColor);
		button.setLineColor(lineColor);
		button.setLineStyle(lineStyle);
		button.setLineWidth(lineWidth);
	}
	
	public void setImageIndex(int index) {
		image.setIndex(index);
	}

	private void updateStyling() {
		if(this.enabled) {
			if(this.selected) {
				style(customButtonStyling.selectionButtonColor(), customButtonStyling.selectionTextColor(), customButtonStyling.selectionLineColor(), customButtonStyling.selectionLineStyle(), customButtonStyling.selectionLineWidth());
			}
			else {
				style(customButtonStyling.defaultButtonColor(), customButtonStyling.defaultTextColor(), customButtonStyling.defaultLineColor(), customButtonStyling.defaultLineStyle(), customButtonStyling.defaultLineWidth());
			}
		}
		else {
			style(customButtonStyling.disabledButtonColor(), customButtonStyling.disabledTextColor(), customButtonStyling.disabledLineColor(), customButtonStyling.disabledLineStyle(), customButtonStyling.disabledLineWidth());
		}
	}
	
	public void clickButton() {
		button.onClick(0, 0); // Needs clickx and clicky, does nothing with it -> 0,0 is used.
	}
	
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        this.selected = false;
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
		text.setVisible(visible);
		if(image != null) {
			image.setVisible(visible);
		}
	}

    public boolean isEnabled() { return enabled; }
    public boolean isSelected() { return enabled; }
    public boolean isVisible() { return button.isVisible(); }
           
    public void reStyle(CustomButtonStyling customButtonStyling) {
    	this.customButtonStyling = customButtonStyling;
    }
	
    public static record CustomButtonStyling(Color defaultButtonColor,  Color defaultTextColor,  Color defaultLineColor, LineStyle defaultLineStyle, double defaultLineWidth,
    					 Color selectionButtonColor, Color selectionTextColor, Color selectionLineColor, LineStyle selectionLineStyle, double selectionLineWidth,
    					 Color disabledButtonColor, Color disabledTextColor, Color disabledLineColor, LineStyle disabledLineStyle, double disabledLineWidth) {
    }
}