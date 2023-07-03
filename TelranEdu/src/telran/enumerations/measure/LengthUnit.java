package telran.enumerations.measure;

public enum LengthUnit {
	
	MM(0.001f, "MM"), CM(0.01f, "CM"), DM(0.1f, "DM"), FT(0.3048f, "FT"), M(1, "M"), DAM(10, "DAM"), HM(100, "HM"), KM(1000, "KM");

	float value;
	String unitName;
	LengthUnit(float value, String unitName) {
		this.value = value;
		this.unitName = unitName;
	} 
	
	float getAmountInStandatdMeterUnit(float amount) {
		return value * amount;
	}
	
	float convert(LengthUnit to, float amount) {
		return amount * value / to.value; 
	}
	
	public Length between(Length firsLength, Length seconLength) {
		return new Length(Math.abs(firsLength.getAmountInMeters() - seconLength.getAmountInMeters()), LengthUnit.M);
	}
	
}
