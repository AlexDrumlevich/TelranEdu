package telran.util.stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PrimitiveIterator.OfInt;
import java.util.Random;
import java.util.stream.IntStream;

import telran.util.HashSet;

public class PrimitiveStreams {

	static public int[] randomUnique(int nNumbers, int minInclusive,
			int maxExclusive) {
		if (maxExclusive - minInclusive < nNumbers) {
			throw new IllegalArgumentException("impossible to get the given amount of unique random numbers");
		}
		return new Random().ints(minInclusive, maxExclusive)
				.distinct().limit(nNumbers).toArray();
	}

	static public int[][] twoDimensionalRandomArray(int countRows, int countColumns, int minInclusive,
			int maxExclusive) {
		int[][] array = new int[countRows][countColumns];
		return Arrays
				.stream(array)
				.map(n -> randomUnique(countColumns, minInclusive, maxExclusive))
				.toArray(int[][]::new);
	}

	
	static public int[] getSuffledArray(int[] array) {
		
		//Set to keep and get distinct indexes
		HashSet<Integer> setOfIndexes = new HashSet<>();
		for(int i = 0; i < array.length; i ++) {
			setOfIndexes.add(i);
		}
		//to get random position of setOfIndexes iterator 
		Random randomIndex = new Random();

		return Arrays
				.stream(array)
				.map(n -> {
			//get and remove random position in setOfIndexes
			Iterator<Integer> setOfIndexesIterator = setOfIndexes.iterator();
			int i = 0;
			while(i < randomIndex.nextInt(setOfIndexes.size())) {
				setOfIndexesIterator.next();
				i ++;
			}
			int calculatedRandomIndex = setOfIndexesIterator.next();
			setOfIndexesIterator.remove();
			//change current value to value from array by random index 
			return array[calculatedRandomIndex];
		})
				.toArray(); 
	}
}
