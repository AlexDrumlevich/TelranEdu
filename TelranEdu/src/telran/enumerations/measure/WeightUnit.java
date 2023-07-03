package telran.enumerations.measure;

public enum WeightUnit {
GR(1), LBS(453.592f), KG(1_000), CN(1_000_00), TN(1_000_000);
	float value;
	WeightUnit(float value) {
		this.value = value;
	}
	public float convert(WeightUnit to, float amount) {
		return value * amount / to.value;
	}
}
