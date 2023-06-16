package telran.util.stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PrimitiveIterator.OfInt;
import java.util.Random;
import java.util.stream.IntStream;

import telran.util.ArrayList;
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
	
	//V.R.
	static public int[] getSuffledArray2(int[] array) {
		//TODO 
		//returns new array with shuffled numbers
		//Implementation hints: two stream pipes
		//first stream pipe fills telran.util.ArrayList<Integer> with array's numbers in the
		// random order (apply the same approach
		// as in the randomUnique method with forEach instead of toArray for
		// adding numbers to the ArrayList
		// 
		//second stream pipe creates array of int's from telran.util.ArrayList as we have done at class
		ArrayList<Integer> list = new ArrayList<>();
		new Random().ints(0, array.length)
		.distinct().limit(array.length).forEach(ind -> list.add(array[ind]));
		return 	list.stream().mapToInt(n -> n).toArray();
	}
	
	//Luda
	static public int[] getSuffledArray3(int[] array) {
		
		return	new Random().
				ints(0, array.length)
					.distinct()
					.limit(array.length)
					.map(n -> array[n])
					.toArray();
	}
	
	//Yri
	static public int[] getSuffledArray4(int[] array) {
		
//		List<Integer> list = new ArrayList<>();
		int [] res = new int[array.length];
		
			//if we use local variable to pass it into stream, it must be a Final (it is final by default)	
			//But each time (each iteration) it pass this value, and it value can not be change inside stream  
		//int index = 0;
		
			//!!!But was found the next way: passing reference to stream let change value in array, if use Integer - it will not help because it is immutable
			//using operator =  lid to creating new object Integer with new reference and value   
		int []index = {0};
	
	    new Random().ints(0, array.length).distinct().limit(array.length)
	    //.forEach(i -> list.add(array[i]));
		.forEach(i -> res[index[0]++] = array[i]);
		
		//return list.stream().mapToInt(n -> n).toArray();
		return res;
	}
	
}
