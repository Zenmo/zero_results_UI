/**
 * CustomButton
 */	
public record CustomButton(ShapeRectangle button, ShapeText text, ShapeImage image) {
	
	public void setVisible(boolean visible){
		button.setVisible(visible);
		text.setVisible(visible);
		if(image != null) {
			image.setVisible(visible);
		}
	}
	public boolean isVisible() {
		return button.isVisible();
	}
	public void setText(String txt){
		text.setText(txt);
	}
	public void setTextColor(Color color) {
		text.setColor(color);
	}
	public void setFillColor(Color color) {
		button.setFillColor(color);
	}
	public void setLineColor(Color color) {
		button.setLineColor(color);
	}
	public void setLineStyle(LineStyle lineStyle) {
		button.setLineStyle(lineStyle);
	}
	public void setLineWidth(double lineWidth) {
		button.setLineWidth(lineWidth);
	}
	
	public void style(Color fillColor, Color textColor, Color lineColor, LineStyle lineStyle, double lineWidth) {
		setFillColor(fillColor);
		setTextColor(textColor);
		setLineColor(lineColor);
		setLineStyle(lineStyle);
		setLineWidth(lineWidth);
	}
	
	public void setImageIndex(int index) {
		image.setIndex(index);
	}
	
}