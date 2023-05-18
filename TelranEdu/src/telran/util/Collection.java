package telran.util;

import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
	boolean add(T obj);
	int size();
	boolean remove(T pattern);
	boolean removeIf(Predicate<T> predicate);
	T[] toArray(T[] array);
	boolean contains(T pattern);


	default  boolean isEqual(T object, T pattern) {
		return pattern == null ? object == pattern : pattern.equals(object);
	}
	
}

