package telran.interviews.collectionTasks;

import java.util.HashMap;
import java.util.UUID;

import telran.util.ArrayList;

/**
 * All methods of the class should have complexity O[1]
 * @author User
 *
 * @param <T>
 */


public class MyArray<T> {

	private T value = null;
	private int size;
	private HashMap<Integer, T> mapIndexValues;
	
	public MyArray(int size) {
		if(size < 0) {
			throw new IllegalArgumentException("size must be positive");
		}
		this.size = size;
		mapIndexValues = new HashMap<>();
	}
	/**
	 * sets all array's elements with a given value
	 * @param value
	 */
	public void setAll(T value) {
		this.value = value;
		mapIndexValues = new HashMap<>();
	}
	/**
	 * 
	 * @param index
	 * @return value at given index or null if index is wrong
	 */
	public T get(int index) {
		checkIndexWithEception(index);
		return isValidIndex(index) ? mapIndexValues.getOrDefault(index, value) : null;
	}
	/**
	 * sets a given value at a given index
	 * throws IndexOutOfBoundsException in the case of wrong index
	 * @param index
	 * @param value
	 */

	public void set(int index, T value) {
		checkIndexWithEception(index);
		mapIndexValues.put(index, value);
	}
	
	private boolean isValidIndex(int index) {
		
		return index >= 0 && index < size;
	}
	
	private void checkIndexWithEception(int index) {
		if (!isValidIndex(index)) {
			throw new IndexOutOfBoundsException("Index isn't valid");
		}
	}
}