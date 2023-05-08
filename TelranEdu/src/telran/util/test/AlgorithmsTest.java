package telran.util.test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import drumlevich.algorithms.Algorithms;



class AlgorithmsTest {
	

private static final int BIG_LENGTH = 100000;

short[] numbers = {10, 0, 7, 50, 0, 20, 100, 80, 30, 15, 3};
short[] numbers2 = {10, 0, -80, 7, 50, 0, 20, -20, 100, 80, 30, -50, 15, 3};

static short[] randomArray;
static short[] sortedArray;

@BeforeEach
void setUp() {
	createArraysForTest(BIG_LENGTH);
	
}
	
private void createArraysForTest(int size) {
	//create and initialize random array
	randomArray = new short[size];
	for(int i = 0; i < size; i++) {
		randomArray[i] = (short)(Math.random() * Short.MAX_VALUE);
	}

	//create sorted array
	sortedArray = Arrays.copyOf(randomArray, size);
	Arrays.sort(sortedArray);
	
	//if random array was created like sorted => call current method one more time
	if(Arrays.compare(randomArray, sortedArray) == 0) {
		createArraysForTest(size);
	}

}

	@Test
	void testIsSum2() {
	//sum can`t be negative	
	assertTrue(Algorithms.isSum2SumCantBeNegative(numbers, 10));
	assertTrue(Algorithms.isSum2SumCantBeNegative(numbers, 0));
	assertTrue(Algorithms.isSum2SumCantBeNegative(numbers, 130));

	assertFalse(Algorithms.isSum2SumCantBeNegative(numbers, 300));
	assertFalse(Algorithms.isSum2SumCantBeNegative(numbers, 24));
	
	short[] array3 = {30000, 1, 5, 2, 10000, 0, 500,0};
	assertTrue(Algorithms.isSum2SumCantBeNegative(array3, (short)30000));
	assertTrue(Algorithms.isSum2SumCantBeNegative(array3, (short)7));
	assertFalse(Algorithms.isSum2SumCantBeNegative(array3, (short)30003));
	assertFalse(Algorithms.isSum2SumCantBeNegative(array3, (short)8));
	
	// sum can be negative
	short[] array4 = {30000, 1, 5, 2, 10000, 0, 500,0, Short.MAX_VALUE};
	assertTrue(Algorithms.isSum2SumCanBeNegative(array3, (short)30000));
	assertTrue(Algorithms.isSum2SumCanBeNegative(array3, (short)7));
	assertFalse(Algorithms.isSum2SumCanBeNegative(array3, (short)30003));
	assertFalse(Algorithms.isSum2SumCanBeNegative(array3, (short)8));
	assertTrue(Algorithms.isSum2SumCanBeNegative(array4, Short.MIN_VALUE));
	}
	
	
	
	@Test
	void testGetMaxPositiveWithNegativeReflect() {
		assertEquals(80, Algorithms.getMaxPositiveWithNegativeReflect(numbers2));
		assertEquals(-1, Algorithms.getMaxPositiveWithNegativeReflect(numbers));
		
		short[] array3 = {1, 1, 1, -1, 20, 100,200, 100 -100, -100, -20, -40, 80};
		short[] array4 = {-40, 1, -40, -6, 2, 3, 40};
		short[] array5 = {40, 1, 2, 3, 40, -30};
		assertEquals(100,
				Algorithms.getMaxPositiveWithNegativeReflect(array3));
		assertEquals(40,
				Algorithms.getMaxPositiveWithNegativeReflect(array4));
		assertEquals(-1,
				Algorithms.getMaxPositiveWithNegativeReflect(array5));
		
	}
	
	@Test
	void testSortShortPositiveWithHelperArray() {
	    assertNotEquals(0, Arrays.compare(randomArray, sortedArray));
		Algorithms.sortShortPositive(randomArray);
		assertArrayEquals(randomArray, sortedArray);

	}
	
	@Test
	@Disabled
	void testBubbleSortShort() {
		assertNotEquals(0, Arrays.compare(randomArray, sortedArray));
		Algorithms.bubbleSortShortsPositive(randomArray);
		assertArrayEquals(randomArray, sortedArray);

	}
	
	
	@Test
	@Disabled
	void maxValueComplexityNTest() {
		assertEquals(Long.MAX_VALUE, getMaxValueComplexityN());
	}
	@Test
	void maxValueComplexityLogNTest() {
		assertEquals(Long.MAX_VALUE, getMaxValueComplexityLogN());
	}
	//after overflowing it`ll be max negative, so we just take previous value  
	private Long getMaxValueComplexityN() {
		long res = 1;
	
		while(res > 0) {
			res++;
		}
		return res - 1;
	}
	//we can multiplication by 2 (because of binary system, last figure always EVEN)    
	private Long getMaxValueComplexityLogN() {
		long res = 1;
		while(res > 0) {
			res *= 2;
		}
		return res - 1;
	}
	
	//binary searching tests
	
		@Test
		void binarySearchTest() {
			Integer ar[] = { 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 20, 40 };
			assertEquals(-14, Algorithms.binarySearch(ar, 3, Integer::compare));
			assertEquals(3, Algorithms.binarySearch(ar, 2, Integer::compare ));
			assertEquals(-1, Algorithms.binarySearch(ar, 0, Integer::compare));
			assertEquals(13, Algorithms.binarySearch(ar, 4, Integer::compare));
			assertEquals(0, Algorithms.binarySearch(ar, 1, Integer::compare));
			assertEquals(-16, Algorithms.binarySearch(ar, 25, Integer::compare));
			assertEquals(-17, Algorithms.binarySearch(ar, 45, Integer::compare));
		
			Integer ar2[] = {1, 1, 1, 2, 3, 3, 3};
			assertEquals(3, Algorithms.binarySearch(ar2, 2, Integer::compare));
		}

		
	//binary searching tests
	@Test
	void testBinarySearchWithExpandedReturn() {
		Short[] array3 = {-100, -100, -20, -40, -1, 1, 1, 1, 20, 80, 100, 100, 200};
		Short[] array4 = {-40, -40, -6, 1, 2, 3, 40};
		Short[] array5 = {-30, 1, 2, 3, 40, 40};
		
		assertEquals(0, Algorithms.binarySearchWithExpandedReturn(array3, Short.valueOf((short) -100), Comparator.naturalOrder()));
		assertEquals(12, Algorithms.binarySearchWithExpandedReturn(array3, Short.valueOf((short) 200), Comparator.naturalOrder()));
		
		assertEquals(-1, Algorithms.binarySearchWithExpandedReturn(array3, Short.valueOf((short) -102), Comparator.naturalOrder()));
		assertNotEquals(1, Algorithms.binarySearchWithExpandedReturn(array3, Short.valueOf((short) -100), Comparator.naturalOrder()));
		
		assertEquals(3, Algorithms.binarySearchWithExpandedReturn(array4, Short.valueOf((short) 1), Comparator.naturalOrder()));
		assertEquals(-4, Algorithms.binarySearchWithExpandedReturn(array4, Short.valueOf((short) 0), Comparator.naturalOrder()));
		assertEquals(6, Algorithms.binarySearchWithExpandedReturn(array4, Short.valueOf((short) 40), Comparator.naturalOrder()));
		assertNotEquals(-1, Algorithms.binarySearchWithExpandedReturn(array4, Short.valueOf((short) 40), Comparator.naturalOrder()));
		
		assertEquals(4, Algorithms.binarySearchWithExpandedReturn(array5, Short.valueOf((short) 40), Comparator.naturalOrder()));
		assertEquals(-7, Algorithms.binarySearchWithExpandedReturn(array5, Short.valueOf((short) 50), Comparator.naturalOrder()));
		assertNotEquals(0, Algorithms.binarySearchWithExpandedReturn(array5, Short.valueOf((short) 1), Comparator.naturalOrder()));
		assertNotEquals(2, Algorithms.binarySearchWithExpandedReturn(array5, Short.valueOf((short) 1), Comparator.naturalOrder()));

	}
	

	
}
