package telran.enumerations.measure.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.enumerations.measure.WeightUnit;

class WeightUnitTest {

	@Test
	void convertTest() {
		assertEquals(10, WeightUnit.GR.convert(WeightUnit.KG, 10000));
		assertEquals(453_592, WeightUnit.LBS.convert(WeightUnit.GR, 1000));
	}

}
