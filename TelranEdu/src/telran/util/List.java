package telran.util;

import java.util.Comparator;
import java.util.function.Predicate;

public interface List<T> extends Collection<T> {

//add
void add(int index, T obj);

//remove
T remove(int index);

//get
T get(int index);
int indexOf(T pattern);
int lastIndexOf(T pattern);
int indexOf(Predicate<T> predicate);
int lastIndexOf(Predicate<T> predicate);

//sort
void sort();
void sort(Comparator<T> comp);

}
