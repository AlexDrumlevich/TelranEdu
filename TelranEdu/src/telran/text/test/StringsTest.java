package telran.text.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.text.Strings;

class StringsTest {

	@Test
	void test() {
		//String regex = "gray|grey|griy";
//		String regex = "gr(a|e|i)y";
//		assertTrue("gray".matches(regex));
//		assertTrue("grey".matches(regex));
//		assertTrue("griy".matches(regex));
//		assertFalse("groy".matches(regex));
//		String regex = "a?1234";
//		assertTrue("1234".matches(regex));
//		assertTrue("a1234".matches(regex));
//		assertFalse("b1234".matches(regex));
//		assertFalse("aa1234".matches(regex));
//		String regex = "a*1234";
//		assertTrue("1234".matches(regex));
//		assertTrue("a1234".matches(regex));
//		assertFalse("b1234".matches(regex));
//		assertTrue("aa1234".matches(regex));
//		String regex = "a+1234";
//		assertFalse("1234".matches(regex));
//		assertTrue("a1234".matches(regex));
//		assertFalse("b1234".matches(regex));
//		assertTrue("aa1234".matches(regex));
//		String regex = "(a|b)+1234";
//		assertFalse("1234".matches(regex));
//		assertTrue("a1234".matches(regex));
//		assertTrue("b1234".matches(regex));
//		assertTrue("aa1234".matches(regex));
		
		
	}
	@Test
	void javaVariableNameTest() {
		String regex = Strings.javaVariableName();
		assertTrue("a".matches(regex));
		assertTrue("$".matches(regex));
		assertTrue("$2".matches(regex));
		assertTrue("$_".matches(regex));
		assertTrue("__".matches(regex));
		assertTrue("_2".matches(regex));
		assertTrue("a_b".matches(regex));
		assertTrue("A_B".matches(regex));
		assertTrue("abc12345678900000".matches(regex));
		assertFalse("1a".matches(regex));
		assertFalse("_".matches(regex));
		assertFalse("a#".matches(regex));
		assertFalse("a b".matches(regex));
		assertFalse("a-b".matches(regex));
		assertFalse(" ab".matches(regex));
		
	}
	@Test
	void zero_300Test() {
		String regex = Strings.zero_300();
		assertTrue("0".matches(regex));
		assertTrue("9".matches(regex));
		assertTrue("299".matches(regex));
		assertTrue("300".matches(regex));
		assertTrue("99".matches(regex));
		assertFalse("01".matches(regex));
		assertFalse("00".matches(regex));
		assertFalse("1111".matches(regex));
		assertFalse("301".matches(regex));
		assertFalse("3000".matches(regex));
		assertFalse("310".matches(regex));
		assertFalse("-1".matches(regex));
		assertFalse("3 ".matches(regex));
		
		
		
	}
	@Test
	void ipV4OctetTest() {
		String regex = Strings.ipV4Octet();
		assertTrue("000".matches(regex));
		assertTrue("00".matches(regex));
		assertTrue("0".matches(regex));
		assertTrue("99".matches(regex));
		assertTrue("1".matches(regex));
		assertTrue("10".matches(regex));
		assertTrue("199".matches(regex));
		assertTrue("200".matches(regex));
		assertTrue("249".matches(regex));
		assertTrue("250".matches(regex));
		assertTrue("255".matches(regex));
		assertFalse("0000".matches(regex));
		assertFalse(" 1".matches(regex));
		assertFalse(".0".matches(regex));
		assertFalse("-1".matches(regex));
		assertFalse("256".matches(regex));
		assertFalse("1000".matches(regex));
	}
	@Test
	void IpV4Test() {
		String regex = Strings.ipV4();
		assertTrue("0.0.0.0".matches(regex));
		assertTrue("1.1.1.1".matches(regex));
		assertTrue("99.99.12.09".matches(regex));
		assertTrue("100.199.200.255".matches(regex));
		assertFalse(".1.2.3.4".matches(regex));
		assertFalse("1.2.3.4.".matches(regex));
		assertFalse(".1.&2.3.4".matches(regex));
		assertFalse("1.2.3".matches(regex));
		assertFalse("1.2.3.4.5".matches(regex));
		assertFalse("123 123 123 123".matches(regex));
	}
	@Test
	void arithmeticExpressionTest() {
		assertTrue(Strings.isArithmeticExpression(" 12. "));//12
		assertTrue(Strings.isArithmeticExpression(" .5 + .8 "));//1.3
		assertTrue(Strings.isArithmeticExpression(" 12./ 6."));//2
		assertTrue(Strings.isArithmeticExpression("12./2."));//6
		assertTrue(Strings.isArithmeticExpression(" 12.*  2. / 10.0 + 1000.0000 "));//1002.4
		assertTrue(Strings.isArithmeticExpression(" 120. / 50. + 100. - 2. * 3. / 10. "));//30.12
		
		assertFalse(Strings.isArithmeticExpression(" 12 + 18"));
		assertFalse(Strings.isArithmeticExpression(" 12"));
		assertFalse(Strings.isArithmeticExpression(" 12. 18."));
		assertFalse(Strings.isArithmeticExpression(" 12 18"));
		assertFalse(Strings.isArithmeticExpression(" 12.2 + 18"));
		assertFalse(Strings.isArithmeticExpression(" 12/3&4"));
		assertFalse(Strings.isArithmeticExpression(" 12+20-"));
		assertFalse(Strings.isArithmeticExpression(" 12/ 18 + 100 10"));
		
	}
	@Test
	void computeExpressionTest() {
		HashMap<String, Double> mapVariables = new HashMap<>();
		mapVariables.put("x", .5);
		mapVariables.put("y", 5.5);
		mapVariables.put("z", 10.);
		
		
		
		assertEquals(12, Strings.computeExpression(" 12. ", mapVariables));
		assertEquals(1.3, Strings.computeExpression(" .5 + .8" , mapVariables));
		assertEquals(2, Strings.computeExpression(" 12./ 6.", mapVariables));
		assertEquals(6, Strings.computeExpression("12./2.", mapVariables));
		assertEquals(1002.4, Strings.computeExpression(" 12.*  2. / 10.0 + 1000.0000 ", mapVariables));
		assertEquals(((((120. / 50.) + 100.) - 2.) * 3.) / 10., Strings.computeExpression(" 120. / 50. + 100. - 2. * 3. / 10. ", mapVariables));
		assertEquals(55, Strings.computeExpression(" y *  z", mapVariables));
		assertEquals(1.5, Strings.computeExpression(" 3. *  x", mapVariables));
		assertEquals(0.05, Strings.computeExpression(" x / z", mapVariables));
		assertEquals(-9.5, Strings.computeExpression(" x - 10.", mapVariables));
		
		assertThrowsExactly(IllegalArgumentException.class,
				() -> Strings.computeExpression(" 12/ 18 + 100 10", mapVariables));
			assertThrowsExactly(IllegalArgumentException.class,
						() -> Strings.computeExpression(" n/ 18 + 100 10", mapVariables));
			assertThrowsExactly(NoSuchElementException.class,
					() -> Strings.computeExpression(" n/ x", mapVariables));
	}

}
