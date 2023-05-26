package telran.util;

import java.lang.StackWalker.Option;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.Position;

import org.hamcrest.SelfDescribing;

public class HashSet<T> implements Set<T> {
	private static final int DEFAULT_HASH_TABLE_SIZE = 16;
	private LinkedList<T>[] hashTable;
	private int size;

	//Iterator
	private class HashSetIterator implements Iterator<T> {

		int currentElementPosition = 0;
		int currentIndexHashTable = 0;
		boolean flNext = false;
		private Iterator<T> innerListIterator = null;

		@Override
		public boolean hasNext() {
			return currentElementPosition < size;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
	
			flNext = true;
			currentElementPosition++;
			return findNext();
		}

		@Override
		public void remove() {

			if (!flNext) {
				throw new IllegalStateException();
			} 
			innerListIterator.remove();
			size--;
			currentElementPosition--;
			flNext = false;
		}


		private T findNext() {
			//if there is not innerListIterator yet OR there is no next()
			if (innerListIterator == null || (innerListIterator != null && !innerListIterator.hasNext())) {
				LinkedList<T> innerLinkedList = null;
				//searching next innerLinkedList inside hashTable (witch is not null or in not empty) and get its iterator
				while (innerLinkedList == null || innerLinkedList.size() == 0) {
					innerLinkedList = hashTable[currentIndexHashTable++];
				}
				innerListIterator = innerLinkedList.iterator();
			}
			return innerListIterator.next();
		}

	}

	@SuppressWarnings("unchecked")
	public HashSet(int hashTableSize) {
		hashTable = new LinkedList[hashTableSize];
	}
	public HashSet() {
		this(DEFAULT_HASH_TABLE_SIZE);
	}
	@Override
	public Iterator<T> iterator() {

		return new HashSetIterator();
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if(size >= 0.75 * hashTable.length) {
			recreation();
		}
		int index = getHashTableIndex(obj);
		if(hashTable[index] == null) {
			hashTable[index] = new LinkedList<>();
		}
		if(!hashTable[index].contains(obj)) {
			hashTable[index].add(obj);
			size++;
			res = true;
		}

		return res;
	}

	private int getHashTableIndex(T obj) {

		return Math.abs(obj.hashCode()) % hashTable.length;
	}
	private void recreation() {
		HashSet<T> tmp = new HashSet<>(hashTable.length * 2);
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				for (T obj : hashTable[i]) {
					tmp.add(obj);
				} 
			}
		}
		this.hashTable = tmp.hashTable;

	}
	@Override
	public int size() {

		return size;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int index = getHashTableIndex(pattern);
		if (hashTable[index] != null) {
			res = hashTable[index].remove(pattern);
			if (res) {
				size--;
			}
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getHashTableIndex(pattern);
		return hashTable[index] != null && hashTable[index].contains(pattern);
	}
	@Override
	//FIXME method should be removed after writing iterator
	public T[] toArray(T[] ar) {
		int size = size();
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		int index = 0;

//				for(int i = 0; i < hashTable.length; i++) {
//					LinkedList<T> list = hashTable[i];
//					if(list != null) {
//						for(T obj: list) {
//							ar[index++] = obj;
//						}
//					}
//				}

		for(T obj : this) {
			ar[index++] = obj;
		}

		if (ar.length > size) {
			ar[size] = null;
		}

		return ar;
	}

}
