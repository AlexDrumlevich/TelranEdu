package telran.util.stream.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.HashSet;

import static telran.util.stream.PrimitiveStreams.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

class PrimitiveStreamTest {

	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 2_000_000_000;
	private static final int N_NUMBERS = 1000;
	private static final int N_RUNS = 100;

	@Test
	void uniqueRandomTest() {
		int[] arrayPrev = randomUnique(N_NUMBERS, MIN_NUMBER, MAX_NUMBER);
		runUniqueTest(arrayPrev);
		for (int i = 0; i < N_RUNS; i++) {
			int[] arrayNext = randomUnique(N_NUMBERS, MIN_NUMBER, MAX_NUMBER);
			runUniqueTest(arrayNext);
			runArrayNotEqualTest(arrayPrev, arrayNext);
			arrayPrev = arrayNext;

		}
	}

	private void runArrayNotEqualTest(int[] arrayPrev, int[] arrayNext) {

		int index = 0;
		if (arrayPrev.length == arrayNext.length) {
			while (index < arrayPrev.length && arrayPrev[index] == arrayNext[index]) {
				index++;
			}
		}
		assertTrue(index < arrayPrev.length);
	}

	private void runUniqueTest(int[] array) {
		HashSet<Integer> set = new HashSet<>();
		for (int num : array) {
			set.add(num);
		}
		assertEquals(array.length, set.size());
	}



	@Test
	void shuffleTest() {
		for (int i = 0; i < N_RUNS; i++) {
			int[] arrayPrevious = new Random().ints().limit(N_RUNS).toArray();
			int[] arraySuffled1 = getSuffledArray(Arrays.copyOf(arrayPrevious, arrayPrevious.length));
			int[] arraySuffled2 = getSuffledArray(Arrays.copyOf(arrayPrevious, arrayPrevious.length));

			// Equals length
			assertEquals(arrayPrevious.length, arraySuffled1.length);
			assertEquals(arrayPrevious.length, arraySuffled2.length);

			//arrays not equal
			assertFalse(Arrays.equals(arrayPrevious, arraySuffled1));
			assertFalse(Arrays.equals(arrayPrevious, arraySuffled2));
			assertFalse(Arrays.equals(arraySuffled1, arraySuffled2));

			//arrays equal	
			assertArrayEquals(Arrays.stream(arrayPrevious).sorted().toArray(), Arrays.stream(arraySuffled1).sorted().toArray());
			assertArrayEquals(Arrays.stream(arrayPrevious).sorted().toArray(), Arrays.stream(arraySuffled2).sorted().toArray());

		}
	}

	@Test
	void twoDimensionalRandomArrayTest() {
		int[][] array = twoDimensionalRandomArray(4, 6, MIN_NUMBER, MAX_NUMBER);
		for(int i = 0; i < array.length; i ++) {
			for(int j = 0; j < array[i].length; j ++) {
				System.out.print(" " + array[i][j]);
			}
			System.out.println();
		}
	}



}
