package telran.util;

import java.util.function.Predicate;

public interface Collection<T> {
boolean add(T obj);
int size();
boolean remove(T pattern);
boolean removeIf(Predicate<T> predicate);
T[] toArray(T[] array);

}

