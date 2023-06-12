package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Collection<T> extends Iterable<T> {
	boolean add(T obj);
	int size();
	boolean remove(T pattern);
	boolean contains(T pattern);

	default T[] toArray(T[] array) {
		int size = size();
		if (array.length < size) {
			array = Arrays.copyOf(array, size);
		}
		int index = 0;
		
		//1-st way
		//	Iterator<T> itererator = this.iterator();
		//		while(itrerator.hasNext()) {
		//			ar[index++] = itrerator.next();
		//		}
		
		//by default inside use itererator
		for (T element : this) {
			array[index++] = element;
		}
		
		if (array.length > size) {
			array[size] = null;
		}
		return array;
	}


	//default boolean contains(T pattern) {
	//		boolean contains = false;
	//		Iterator<T> itr = this.iterator();
	//		while(itr.hasNext() && !contains) {
	//			if(isEqual(pattern, itr.next())) {
	//				contains = true;
	//			}
	//		}
	//		return contains;
	//}

	default boolean removeIf(Predicate<T> predicate) {
		int oldSize = size();
		Iterator<T> it = iterator();
		while(it.hasNext()) {
			T obj = it.next();
			if(predicate.test(obj)) {
				it.remove();
			}
		}
		return oldSize > size();
	}
	
	default void clear() {
		removeIf(element -> true);
	}
	
	default  boolean isEqual(T object, T pattern) {
		return pattern == null ? object == pattern : pattern.equals(object);
	}

	default Stream<T> stream() {
		return StreamSupport.stream(spliterator(), false);
	}
	
	default Stream<T> parallelStream() {
		return StreamSupport.stream(spliterator(), true);
	}
	
}

