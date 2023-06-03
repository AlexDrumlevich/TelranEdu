package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.SortedSet;

public abstract class SortedSetTest extends SetTest {

	private Integer[] numbersWithNull = { 10, -20, 7, null, 50, 100, 30 };
	private Integer[] numbersWithOutNull = { 10, -20, 7, 50, 100, 30 };
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
		assertEquals(10, sortedSet.ceiling(8));
		assertEquals(100, sortedSet.ceiling(60));
		assertEquals(null, sortedSet.ceiling(101));
		assertThrows(NullPointerException.class, () -> sortedSet.ceiling(null));
		
		sortedSet.clear();
		assertEquals(null, sortedSet.ceiling(10));
		
		//using null friendly comparator
		// there is null inside set
		SortedSet<Integer> newSortedSetIncludesNull = new telran.util.TreeSet<>(new IntegerNullTheLeastComparator());
		fillSortedSet(numbersWithNull, newSortedSetIncludesNull);
		assertEquals(null, newSortedSetIncludesNull.ceiling(null));
		assertDoesNotThrow(() -> newSortedSetIncludesNull.ceiling(null));
		
		// there is NO null inside set
		SortedSet<Integer> newSortedSetNotIncludesNull = new telran.util.TreeSet<>(new IntegerNullTheLeastComparator());
		fillSortedSet(numbersWithOutNull, newSortedSetNotIncludesNull);
		assertEquals(-20, newSortedSetNotIncludesNull.ceiling(null));
		assertDoesNotThrow(() -> newSortedSetNotIncludesNull.floor(null));
		
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
		
		
		sortedSet.clear();
		assertEquals(null, sortedSet.ceiling(10));
		
		
		//using null friendly comparator
			// there is null inside set
		SortedSet<Integer> newSortedSetIncludesNull = new telran.util.TreeSet<>(new IntegerNullTheLeastComparator());
		fillSortedSet(numbersWithNull, newSortedSetIncludesNull);
		assertEquals(null, newSortedSetIncludesNull.floor(null));
		assertDoesNotThrow(() -> newSortedSetIncludesNull.floor(null));
		
		
	}

	
	private void fillSortedSet(Integer[] array, SortedSet<Integer> sortedSet) {
		for (int i = 0; i < array.length; i++) {
			sortedSet.add(array[i]);
		}
	}
	
	class IntegerNullTheLeastComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			int result;
			if (o1 == null && o2 == null) {
				result = 0;
			} else if (o1 == null) {
				result = -1;
			} else if (o2 == null) {
				result = 1;
			} else {
				result = Integer.compare(o1, o2); 
			}
			return result;
		}
		
	}
	
}
