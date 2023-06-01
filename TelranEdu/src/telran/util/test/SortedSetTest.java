package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;


import telran.util.SortedSet;

public abstract class SortedSetTest extends SetTest {

	//start { 10, -20, 7, 50, 100, 30 }
	
	@Override
	protected Integer[] getActual(Integer[] array, int size) {
		//System.out.println("Sorted test");
		return array;
	}
	@Test
	void firstTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		assertEquals(-20, sortedSet.first());
		sortedSet.clear();
		assertThrows(NoSuchElementException.class, () -> sortedSet.first());
	}
	@Test
	void lastTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		assertEquals(100, sortedSet.last());
		sortedSet.clear();
		assertThrows(NoSuchElementException.class, () -> sortedSet.last());
	}
	@Test
	void ceilingTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		assertEquals(50, sortedSet.ceiling(50));
		assertEquals(-20, sortedSet.ceiling(-25));
		assertEquals(50, sortedSet.ceiling(40));
		assertEquals(100, sortedSet.ceiling(60));
		assertEquals(null, sortedSet.ceiling(101));
		assertThrows(NullPointerException.class, () -> sortedSet.ceiling(null));
	}
	@Test
	void floorTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		assertEquals(50, sortedSet.floor(50));
		assertEquals(-20, sortedSet.floor(-10));
		assertEquals(30, sortedSet.floor(40));
		assertEquals(50, sortedSet.floor(60));
		assertEquals(100, sortedSet.floor(101));
		assertEquals(null, sortedSet.floor(-25));
		assertThrows(NullPointerException.class, () -> sortedSet.floor(null));
	}

}
