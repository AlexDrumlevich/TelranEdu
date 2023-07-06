package telran.enumerations.measure.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.enumerations.measure.*;

class LengthTest {
	Length length1 = new Length(1, LengthUnit.KM);
	Length length2 = new Length(500, LengthUnit.M);
	Length l3 = new Length(50000, LengthUnit.CM);

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testEqualsObject() {
		assertEquals(length2, l3);
		assertNotEquals(length1, length2);
	}

	@Test
	void testCompareTo() {
		assertTrue(length2.compareTo(length1) < 0);
		assertTrue(length1.compareTo(length2) > 0);
		assertTrue(length2.compareTo(l3) == 0);
	}
	@Test
	void testConvert() {
		Length l = l3.convert(LengthUnit.M);
		assertEquals(length2.getAmount(), l.getAmount());
		assertEquals(length2.getUnit(), l.getUnit());
	}
	@Test
	void testToString() {
		assertEquals("500.0M", length2.toString());
	}
	@Test
	void testBetween() {
		Length l = LengthUnit.M.between(length2, length1);
		assertEquals(l.getAmount(), length2.getAmount());
		assertEquals(l.getUnit(), length2.getUnit());
		assertEquals(l.getUnit(), LengthUnit.M);
	}

	@Test
	void isInstance() {
		Dog dogDog = new Dog(10);
		Runable dogRunable = new Dog(10);

		assertTrue(dogDog.equalsByGetClass(dogRunable));
		assertTrue(dogDog.equalsByIsInstance(dogRunable));
		assertTrue(dogDog.equalsByInstanceOf(dogRunable));
		assertTrue(((Dog) dogRunable).equalsByInstanceOf(dogDog));
	}

	interface Runable {
	
		int getMaxSpeed();
	}

	class Dog implements Runable {
		int maxSpeed;
		public Dog(int maxSpeed) {
			this.maxSpeed = maxSpeed;
		}

		@Override
		public int getMaxSpeed() {
			return maxSpeed;
		}

		public boolean equalsByGetClass(Object obj) {
			System.out.println("this get class: " + getClass() + "; obj.getClass: " + obj.getClass());
			return obj.getClass() == getClass();	
		}

		public boolean equalsByIsInstance (Object obj) {
			//				System.out.println("this instance of: " + getClass() + "; obj.getClass: " + obj.getClass());
			return Dog.class.isInstance(obj);	
		}
		
		public boolean equalsByInstanceOf (Object obj) {
			//				System.out.println("this instance of: " + getClass() + "; obj.getClass: " + obj.getClass());
			return obj instanceof Runable;	
		}

	}
}