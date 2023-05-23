package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.concurrent.ConcurrentCharInputReader;

public class Range implements Iterable<Integer> {
	private int min;
	private int max;
	public Range(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("min must be less than max");
		}
		this.min = min;
		this.max = max;
	}

	//to remove
	LinkedList<Integer> removedValuesList = new LinkedList<>();



	private class RangeIterator implements Iterator<Integer> {
		int current = min;
		int prev; 
		boolean flNext = false;
		
		Iterator<Integer> removedValuesIterator = removedValuesList.iterator();
		Integer currentRemovedValue;
		boolean removedValuesListFinished = false;

		
		

		@Override
		public boolean hasNext() {
			if(!removedValuesListFinished) {
				nextCalculation();
			}
			System.out.println(current);
			return current < max;
		}

		@Override
		public Integer next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			flNext = true;
			prev = current;
			
			return current++;
		}

		@Override
		public void remove() {
			if(!flNext) {
				throw new IllegalStateException();
			}
			flNext = false;
			removedValuesList.add(prev);
		}

		private void nextCalculation() {
			if(currentRemovedValue == null && removedValuesIterator.hasNext()) {
				currentRemovedValue = removedValuesIterator.next();
			} else if (currentRemovedValue == null && !removedValuesIterator.hasNext()){
				removedValuesListFinished = true;
				return;
			}

			if(currentRemovedValue == current) {
				current++;
				if(removedValuesIterator.hasNext()) {
					currentRemovedValue = removedValuesIterator.next();
					nextCalculation();
				} else {
					removedValuesListFinished = true;
				}
			}
		}
	}

	@Override
	public Iterator<Integer> iterator() {

		return new RangeIterator();
	}


	public Integer[] toArray() {
		Integer [] array = new Integer[max - min - removedValuesList.size()];
		int index = 0;
		//First way
		//		for(Integer num: this) {
		//			array[index++] = num;
		//		}
		//Second way
		Iterator<Integer> it = iterator();
		while(it.hasNext()) {
			int next = it.next();
			array[index++] = next;

		}

		return array;
	}

	public boolean removeIf(Predicate<Integer> predicate) {
		for(Integer element : this) {
			if(predicate.test(element)) {
				removedValuesList.add(element);
			}
		}
		return removedValuesList.size > 0;
	}

}

