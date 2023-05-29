package telran.util.test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

public abstract class CollectionTest {
	//TODO move tests of interface collection methods (5 methods) from ListTest
	//	to here

	protected static final int BIG_LENGTH = 100000;
	protected Integer[] numbers = {10, -20, 7, 50, 100, 30};

	protected Collection<Integer> collection;
	@BeforeEach
	void setUp() {
		collection = getCollection();
		for( int i = 0; i < numbers.length; i++) {
			collection.add(numbers[i]);
		}
	}
	abstract protected Collection<Integer> getCollection();

	//ADD
	@Test
	void testAdd() {
		assertTrue(collection.add(numbers[0]));
		assertEquals(numbers.length + 1, collection.size());
	}

	//TO ARRAY
	@Test
	void testToArrayForBigArray() {
		Integer bigArray[] = new Integer[BIG_LENGTH];
		for(int i = 0; i < BIG_LENGTH; i++) {
			bigArray[i] = 10;
		}
		Integer actualArray[] = collection.toArray(bigArray);
		int size = collection.size();
		for(int i = 0; i < size; i++) {
			assertEquals(numbers[i], actualArray[i]);
		}
		assertNull(actualArray[size]);
		assertTrue(bigArray == actualArray);
	}
	
	@Test
	void testToArrayForEmptyArray() {
		Integer actualArray[] =
				collection.toArray(new Integer[0]);
		assertArrayEquals(numbers, actualArray);
	}

	//REMOVE
	@Test
	void testRemovePattern() {
		Integer [] expectedNo10 = { -20, 7, 50, 100, 30};
		Integer [] expectedNo10_50 = { -20, 7,  100, 30};
		Integer [] expectedNo10_50_30 = { -20, 7,  100};
		assertTrue(collection.remove(numbers[0]));
		runTest(expectedNo10);
		Integer objToRemove = 50;
		assertTrue(collection.remove(objToRemove));
		runTest(expectedNo10_50);
		assertTrue(collection.remove((Integer)30));
		runTest(expectedNo10_50_30);
		assertFalse(collection.remove((Integer)50));
	}

	@Test
	void testRemoveIfAll() {
		assertTrue(collection.removeIf(a -> true));
		assertEquals(0, collection.size());
	}

	@Test
	void testRemoveIfPredicate() {
		//	start	{10, -20, 7, 50, 100, 30};
		Integer[] expected = {10, -20,  50, 100, 30};
		assertFalse(collection.removeIf(a -> a % 2 != 0
				&& a >= 10));
		assertTrue(collection.removeIf(a -> a % 2 != 0));
		runTest(expected);
	}
	
	protected void runTest(Integer[] expected) {
		/*
		int size = collection.size() ;
		Integer [] actual = new Integer[expected.length];
		for(int i = 0; i < size; i++) {
			actual[i] = collection.get(i);
		}
		*/
		Integer [] actual = collection.toArray(new Integer[0]);
	
		assertArrayEquals(expected, actual);
	}

	
	@Test
	void testContains() {
		assertTrue(collection.contains(numbers[0]));
		assertTrue(collection.contains(numbers[3]));
		assertTrue(collection.contains(numbers[numbers.length - 1]));
		assertFalse(collection.contains(1000000));
	}
	
	@Test
	void clearFunctionalTest() {
		collection.clear();
		assertEquals(0, collection.size());
	}
	
	@Test
	void iteratorTest() {
		Iterator<Integer> iterator = collection.iterator();
		Integer[] array = collection.toArray(new Integer[0]);
		for(int i = 0; i < array.length; i ++) {
			assertTrue(iterator.hasNext());
			assertTrue(iterator.next().equals(array[i]));
		}
		assertFalse(iterator.hasNext());
		assertThrows(NoSuchElementException.class, () -> iterator.next());

		//try collection with 0 elements
		collection.removeIf(a -> true);
		Iterator<Integer> iterator2 = collection.iterator();
		assertFalse(iterator2.hasNext());
		assertThrows(NoSuchElementException.class, () -> iterator2.next());	
	}
	
	@Test
	void testIteratorRemove() {
		Iterator<Integer> it = collection.iterator();
		Integer[] expectedFirst = { -20, 7, 50, 100, 30 };
		Integer[] expectedLast = { -20, 7, 50, 100};
		
		assertThrowsExactly(IllegalStateException.class, ()->it.remove());
		it.next();
		it.remove();
		runTest(expectedFirst);
		assertThrowsExactly(IllegalStateException.class, ()->it.remove());
		while(it.hasNext()) {
			it.next();
		}
		it.remove();
		runTest(expectedLast);
		
	}
	
	@Disabled
	@Test
	void testIteratorWithTwoIterators() {
		//protected Integer[] numbers = {10, -20, 7, 50, 100, 30};
		Iterator<Integer> it1 = collection.iterator();
		Iterator<Integer> it2 = collection.iterator();
		
		it1.next();
		it1.remove();
		Integer[] expected = {-20, 7, 50, 100, 30};
		runTest(expected);
		
		
		System.out.println(it2.next());
		
		//on next line we get No such element exception when returns current for one of iterator and removed in another (because of remove contains head.next = null)
		//System.out.println(it2.next());

		
		
	}
	
}
