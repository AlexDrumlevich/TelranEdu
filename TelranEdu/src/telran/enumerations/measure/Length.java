package telran.enumerations.measure;

public class Length implements Comparable<Length>{

	private float amount;
	private LengthUnit lengthUnit;

	public Length(float amount, LengthUnit lengthUnit) {
		this.amount = amount;
		this.lengthUnit = lengthUnit;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = this == obj;
		if((obj != null || !(Length.class.isInstance(obj)) && !result)) {
			float eps = 0.0000000000000001f;
			result = Math.abs(getAmountInMeters() - ((Length) obj).getAmountInMeters()) < eps;
		}
		return result;
	}

	@Override
	public int compareTo(Length o) {
		return Float.compare(getAmountInMeters(), o.getAmountInMeters());
	}

	@Override
	public String toString() {	
		return String.format("%.1f%s", amount, getUnitStringName()).replaceAll(",", ".");
	}
	private String getUnitStringName() {
		return lengthUnit.unitName;
	}

	public Length convert(LengthUnit newLengthUnit) {
		return new Length(lengthUnit.convert(newLengthUnit, amount), newLengthUnit);
	}

	float getAmountInMeters() {
		return lengthUnit.getAmountInStandatdMeterUnit(amount);
	}

	public float getAmount() {
		return amount;
	}

	public LengthUnit getUnit() {
		return lengthUnit;
	}

}
