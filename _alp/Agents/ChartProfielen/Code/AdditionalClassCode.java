public ShapeRadioButtonGroup getPeriodRadioButton() {
	return this.v_periodRadioButton;
}

public List<ShapeRadioButtonGroup> getAllPeriodRadioButtons() {
	return Arrays.asList(this.rb_periodIncludingYear, this.rb_periodExcludingYear, this.rb_periodPeaksIncludingYear, this.rb_periodPeaksExcludingYear);
}

public ShapeRadioButtonGroup getEnergyTypeRadioButton() {
	return this.radio_energyType;
}

public ShapeGroup getChartGroupWeek() {
	return this.gr_week;
}

public ShapeGroup getChartGroupYear() {
	return this.gr_year;
}