package telran.interviews.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.interviews.MultiCounters;
import telran.interviews.MultiCountersImpl;

class MultiCountersTests {
MultiCounters multiCounters;
@BeforeEach
void setUp() {
	multiCounters = new MultiCountersImpl();
	multiCounters.addItem("a");
	multiCounters.addItem("a");
	multiCounters.addItem("a");
	multiCounters.addItem(123);
	multiCounters.addItem(123);
	multiCounters.addItem("b");
	
}
	@Test
	void addItemTest() {
		assertEquals(4, multiCounters.addItem("a"));
		assertEquals(1, multiCounters.addItem("abcd12"));
		assertEquals(1, multiCounters.addItem(LocalDate.now()));
		assertEquals(2, multiCounters.addItem(LocalDate.now()));
		
	}
	@Test
	void getValueTest() {
		assertEquals(3, multiCounters.getValue("a"));
		assertEquals(2, multiCounters.getValue(123));
		assertEquals(1, multiCounters.getValue("b"));
		assertNull(multiCounters.getValue("c"));
	}
	@Test
	void removeTest() {
		assertTrue(multiCounters.remove(123));
		assertFalse(multiCounters.remove(123));
	}
	@Test
	void getMaxItemsTest() {
		Object expected[] = {"a"};
		runArraySet(expected, multiCounters.getMaxItems());
		multiCounters.addItem(123);
		Object expected1[] = {"a", 123};
		runArraySet(expected1, multiCounters.getMaxItems());
		multiCounters.remove(123);
		runArraySet(expected, multiCounters.getMaxItems());
		multiCounters.remove("a");
		Object expected2[] = {"b"};
		runArraySet(expected2, multiCounters.getMaxItems());
		multiCounters.remove("b");
		runArraySet(new Object[0], multiCounters.getMaxItems());
		
	}
	private void runArraySet(Object[] expected, Set<Object> maxItems) {
		assertEquals(expected.length, maxItems.size());
		for(Object item: expected) {
			assertTrue(maxItems.contains(item));
		}
		
	}

}
