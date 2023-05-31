package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


import telran.util.SortedSet;

public abstract class SortedSetTest extends SetTest {

	@Override
	protected Integer[] getActual(Integer[] array, int size) {
		//System.out.println("Sorted test");
		return array;
	}
	@Test
	void firstTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		assertEquals(-20, sortedSet.first());
	}
	@Test
	void lastTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		assertEquals(100, sortedSet.last());
	}
	@Test
	void ceilingTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		//TODO test for the method ceiling
	}
	@Test
	void floorTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		//TODO test for the method floor
	}

}
