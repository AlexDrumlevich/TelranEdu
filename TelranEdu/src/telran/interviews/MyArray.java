package telran.interviews;

import java.util.HashMap;
import java.util.UUID;

import telran.util.ArrayList;

/**
 * All methods of the class should have complexity O[1]
 * @author User
 *
 * @param <T>
 */

//if Id of MyArray and Id of NodeElement the same => set value by index was later then setAll
//Id of MyArray change when setAll() calls   
//Id of NodeElement set equals to Id of MyArray when set() by index calls 

public class MyArray<T> {

	HashMap<Integer, NodeElement<T>> innerMap;
	int size;
	T commonValue;
	String id = null;
	
	static private class NodeElement<T> {
		T value;
		String id;

		public NodeElement(T value, String id) {
			this.value = value;
			this.id = id;
		}
	}
	
	public MyArray(int size) {
		if(size < 0) {
			throw new IllegalArgumentException("size must be zero or positive");
		}
		id = UUID.randomUUID().toString();
		this.size = size;
		innerMap = new HashMap<>();
	}
	
	/**
	 * sets all array's elements with a given value
	 * @param value
	 */
	public void setAll(T value) {
		commonValue = value;
		id = UUID.randomUUID().toString();
	}
	/**
	 * 
	 * @param index
	 * @return value at given index or null if index is wrong
	 */
	public T get(int index) {
		
		if(index < 0 || index > size - 1) {
			throw new ArrayIndexOutOfBoundsException("index is out of bounds");
		}
		T res = commonValue;
		NodeElement<T> nodeElement = innerMap.get(index);
		
		if(nodeElement != null) {
			if(nodeElement.id != null) {
				res = nodeElement.id.equals(id) ? nodeElement.value : commonValue;
			} else {
				res = commonValue;
			}
		}
		return res;
	}
	
	/**
	 * sets a given value at a given index
	 * throws IndexOutOfBoundsException in the case of wrong index
	 * @param index
	 * @param value
	 */
	public void set(int index, T value) {
		innerMap.put(index, new NodeElement<T>(value, id));
	}
}
