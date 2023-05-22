package telran.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import javax.swing.plaf.synth.SynthUI;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;

	//count of elements in array (!!!Not array length)
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T obj) {
		if (size == array.length) {
			reallocate();
		}
		array[size] = obj;
		size++;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public void add(int index, T obj) {
		//throw unchecked exception
		//index > size (it possible to add element in the last place (index == size))
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(index);
		}
		//if exception thrown, next code (after throw) doesn`t execute

		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
	}

	@Override
	public T remove(int index) {
		//throw unchecked exception
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		//if exception thrown, next code (after throw) doesn`t execute

		T res = array[index];
		//!!! arraycopy take address of start element to copy (array, index + 1), destination (adress 
		//where to copy:  array, index (we shift back on one element, so we rewrite that one element)
		// and size: count of elements (size - index - 1) for ex: we delete index 5, so we need to 
		// copy since 5 index 
		System.arraycopy(array, index + 1, array, index, size - index - 1);
		size--;
		return res;
	}

	@Override
	public T get(int index) {
		//throw unchecked exception
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		//if exception thrown, next code (after throw) doesn`t execute 
		T res = array[index];
		return res;
	}

	@Override
	public int size() {
		return size;
	}

	//SORT
	//STANDART SORT
	@SuppressWarnings("unchecked")
	@Override
	public void sort() {
		//we can get comparator and pass to our sort method witch use it
		sort((Comparator<T>) Comparator.naturalOrder());
		//or use the next:
		//Arrays.sort(array, 0, size);		
	}

	@Override
	public void sort(Comparator<T> comp) {
		Arrays.sort(array, 0, size, comp);

	}

	//OWN SORT REALISATION
	//Bubble sorting with comparable - we use natural order and we can use only Comparable objects
	public void bubleSortUsingComparable() {

		boolean swaped = true;

		for(int i = 0; i < size - 1 && swaped; i++) {
			swaped = false;

			//if we never get into if - so all elements are sorted and we do not need continue
			for(int j = 0; j < size - 1 - i; j++) {
				//cast to Comparable type to use compare method to define if first more or not more 
				//then second 
				if(((Comparable<T>)array[j]).compareTo(array[j+1]) > 0) {
					T tmp = array[j+1] = array[j];
					array[j] = tmp;
					swaped = true;
				}
			}
		}
	}

	//bubble sorting with comparator - we use our own order 
	public void bubleSortUsingComparator(Comparator<T> comparator) {

		boolean swaped = true;

		for(int i = 0; i < size - 1 && swaped; i++) {
			swaped = false;

			//if we never get into if - so all elements are sorted and we do not need continue
			for(int j = 0; j < size - 1 - i; j++) {
				//cast to Comparable type to use compare method to define if first more or not more 
				//then second 
				if(comparator.compare(array[j], array[j+1]) > 0) {
					T tmp = array[j+1] = array[j];
					array[j] = tmp;
					swaped = true;
				}
			}
		}
	}

	//bubble sorting using do-while (not for )
	public void sortBubllesAnotherRealization(Comparator<T> comp) {
		int n = size;
		boolean flUnSort = true;
		do {
			flUnSort = false;
			n--;
			for(int i = 0; i < n; i++) {
				if (comp.compare(array[i], array[i + 1]) > 0) {
					swap(i);
					flUnSort = true;
				}
			}
		} while(flUnSort);

	}

	private void swap(int i) {
		T tmp = array[i];
		array[i] = array[i + 1];
		array[i + 1] = tmp;

	}


	//GET

	//return index of first satisfaction to condition gets in Predicate
	public int indexOf(Predicate<T> predicate) {
		//return -1 if Predicate condition won`t be executed 
		int res = -1;

		int index = 0;
		while (index < size && res == -1) {
			if (predicate.test(array[index])) {
				res = index;
			}
			index++;
		}
		return res;
	}

	//return index of last satisfaction to condition gets in Predicate
	public int lastIndexOf(Predicate<T> predicate) {

		int res = -1;

		int index = size - 1;
		// index starts from the end, while:
		// 1. index doesn`t go below zero and 2. index hasn`t found yet    
		while (index >= 0 && res == -1) {
			if(predicate.test(array[index])) {
				res = index;
			}
			index--;
		}
		return res;
	}



	

	//ITERATOR
	
	private class ArrayListIterator implements Iterator<T> {
		//returns element by current index, after return current increases

		int currentIndex = 0;
		//next() -> set true; remove() -> set false
		boolean flNext = false;
		
		@Override
		public boolean hasNext() {
			return currentIndex < size;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			flNext = true;
			return get(currentIndex++); 
		}
		
		@Override
		public void remove() {
			//flNext == false, if there was not any next() before calling remove();
			if(!flNext) {
				throw new IllegalStateException();
			}
			//Method of ArrayList
			ArrayList.this.remove(--currentIndex);
			flNext = false;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

}



