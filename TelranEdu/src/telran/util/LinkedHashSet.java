package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedHashSet<T> implements Set<T> {

	//PROPERTIES
	int size;

	Node<T> head;
	Node<T> tail;

	HashMap<T, Node<T>> map = new HashMap<>();


	//INNER CLASSES
	private static class Node<T> {
		T obj;
		Node<T> prev;
		Node<T> next;
		Node(T obj) {
			this.obj = obj;
		}
	}

	private class LinkedHashSetIterator implements Iterator<T> {
		Node<T> currentNode = head;
		boolean flNext = false; 
		
		@Override
		public boolean hasNext() {
			return currentNode != null;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T object = currentNode.obj;
			currentNode = currentNode.next;
			flNext = true;
			return object;
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			LinkedHashSet.this.remove(hasNext() ? currentNode.prev.obj : tail.obj);
			flNext = false;
		}
	}


	@Override
	public boolean add(T obj) {
		boolean res = false;
		if (!map.containsKey(obj)) {
			res = true;
			Node<T> node = new Node<>(obj);
			map.put(obj, node);
			addNode(node);
			size++;
		}
		return res;
	}

	private void addNode(Node<T> node) {
		if(size == 0) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;			
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> node = map.get(pattern);
		if(node != null) {
			res = true;
			removeNode(node);
			map.remove(pattern);
			size--;
		}
		return res;
	}

	private void removeNode(Node<T> node) {
		if(size == 1) {
			head = tail = null;
		} else if (node == head) {
			head = head.next;
			head.prev = null;
		} else if (node == tail) {
			tail = tail.prev;
			tail.next = null;
		} else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}
	}

	@Override
	public boolean contains(T pattern) {
		return map.containsKey(pattern);
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedHashSetIterator();
	}

	@Override
	public T get(T pattern) {
		Node<T> resNode = map.get(pattern);
		return resNode != null ? resNode.obj : null;
	}

}
