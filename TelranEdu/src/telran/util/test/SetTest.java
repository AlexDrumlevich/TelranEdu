package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.Set;

public abstract class SetTest extends CollectionTest {
   Set<Integer> set = getSet();
   abstract protected <T> Set<T> getSet();
   @Override
   @Test
	void testAdd() {
	   // add second time the same value
		assertFalse(collection.add(numbers[0]));
		assertEquals(numbers.length, collection.size());
	}
	@Override
	protected Collection<Integer> getCollection() {
		
		return set;
	}
	protected void runTest(Integer[] expected) {
		Integer [] actual = collection.toArray(new Integer[0]);
		Integer expectedCopy[] = Arrays.copyOf(expected, expected.length);
		Arrays.sort(expectedCopy);
		Arrays.sort(actual);
		
		assertArrayEquals(expectedCopy, actual);
		
	}
	@Test
	void testToArrayForBigArray() {
		Integer bigArray[] = new Integer[BIG_LENGTH];
		for(int i = 0; i < BIG_LENGTH; i++) {
			bigArray[i] = 10;
		}
		Integer actualArray[] = collection.toArray(bigArray);
		Arrays.sort(actualArray,0,collection.size());
		int size = collection.size();
		Integer expected[] = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(expected);
		for(int i = 0; i < size; i++) {
			assertEquals(expected[i], actualArray[i]);
		}
		assertNull(actualArray[size]);
		assertTrue(bigArray == actualArray);
	}
	@Test
	void testToArrayForEmptyArray() {
		Integer actualArray[] =
				collection.toArray(new Integer[0]);
		Arrays.sort(actualArray);
		Integer expected[] = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(expected);
		assertArrayEquals(expected, actualArray);
	}

}
