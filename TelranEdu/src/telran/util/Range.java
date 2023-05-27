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
		Integer current = getCurrent(min - 1);
		Integer prev = null;
		boolean flNext = false;
		@Override
		public boolean hasNext() {
			
			return current != null;
		}

		@Override
		public Integer next() {
			if(current == null) {
				throw new NoSuchElementException();
			}
			int currentNum = current;
			prev = current;
			current = getCurrent(current);
			flNext = true;
			return currentNum;
		}
		private Integer getCurrent(Integer current) {
			Integer res = null;
			current++;
			while(current < max && res == null) {
				if(!removedList.contains(current)) {
					res = current;
				}
				current++;
			}
			return res;
		}

		@Override
		public void remove() {
			if(!flNext) {
				throw new IllegalStateException();
			}
			removedList.add(prev);
			flNext = false;
			
		}
		
	}
	/*	
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
			removedValuesList.sort();
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
*/
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

//	public boolean removeIf(Predicate<Integer> predicate) {
//		int prevSize = removedValuesList.size;
//		for(Integer element : this) {
//			if(predicate.test(element)) {
//				removedValuesList.add(element);
//			}
//		}
//		return removedValuesList.size > prevSize;
//	}
	public boolean removeIf(Predicate<Integer> predicate) {
		int oldSize = getSize() ;
		Iterator<Integer> it = iterator();
		while(it.hasNext()) {
			int number = it.next();
			if (predicate.test(number)) {
				it.remove();
			}
		}
		return oldSize > getSize();
	}
	
	private int getSize() {
		return max - min - removedValuesList.size();
	}

}

