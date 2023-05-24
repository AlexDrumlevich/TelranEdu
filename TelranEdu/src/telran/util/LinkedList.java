package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	Node<T> head;
	Node<T> tail;
	int size;
	private static class Node<T> {
		T obj;
		Node<T> next;
		Node<T> prev;
		Node(T obj) {
			this.obj = obj;
		}
	}

	//ADD

	@Override
	public boolean add(T obj) {
		add(size, obj);
		return true;
	}


	//SIZE
	@Override
	public int size() {
		return size;
	}


	//REMOVE

	@Override
	public T remove(int index) {
		//check to index out of bounds
		if(index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException(index);
		}
		//get node by index
		Node<T> nodeToRemove = getNode(index);
		//remove node
		removeNode(nodeToRemove);
		//return object T
		return nodeToRemove.obj;
	}
	
	//ADD
	
	@Override
	public void add(int index, T obj) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(index);
		}
		Node<T> node = new Node<>(obj);
		addNode(index, node);
	}


	//GET

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		return getNode(index).obj;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		return getFirstIndexByPredicate(predicate);
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		return getLastIndexByPredicate(predicate);
	}

	//SORT

	@SuppressWarnings("unchecked")
	@Override
	public void sort() {
		sort((Comparator<T>) Comparator.naturalOrder());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void sort(Comparator<T> comp) {
		//1. call the method toArray
		//2. By applying Arrays.sort you sort the array from #1
		//3. Passing over all LinkedList nodes and setting references to objects (T)
		// in the appropriate order from #2

		//1
		T[] arrayT =  toArray((T[]) new Object[0]);

		//2
		Arrays.sort(arrayT, comp);

		//3
		Node<T> current = head;
		for(int i = 0; current != null; i++) {
			current.obj = arrayT[i];
			current = current.next;
		}	
	}	

	//SUPPORT

	//ADD support

	private void addNode(int index, Node<T> node) {
		if (head == null) {
			head = tail = node;
		} else {
			if (index == 0) {
				addNodeHead(node);
			} else if (index == size) {
				addNodeTail(node);
			} else {
				addNodeMiddle(index, node);
			}
		}
		size++;
	}

	private void addNodeHead(Node<T> node) {
		node.next = head;
		head.prev = node;
		head = node;
	}

	private void addNodeTail(Node<T> node) {
		node.prev = tail;
		tail.next = node;
		tail = node;
	}

	private void addNodeMiddle(int index, Node<T> node) {
		Node<T> nodeA = getNode(index);
		Node<T> nodeBefore = nodeA.prev;
		node.prev = nodeBefore;
		node.next = nodeA;
		nodeBefore.next = node;
		nodeA.prev = node;
	}


	//GET support

	private Node<T> getNode(int index) {	
		return index > size / 2 ? getNodeFromRight(index) :
			getNodeFromLeft(index);
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for(int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	//Predicate -> first index 
	private int getFirstIndexByPredicate(Predicate<T> predicate) {
		int index = -1;
		Node<T> currentNode = head;
		for(int i = 0;  currentNode != null && index == -1; i++) {
			if(predicate.test(currentNode.obj)) {
				index = i;
			} else {
				currentNode = currentNode.next;
			}
		}
		return index;
	}

	//Predicate -> last index 
	private int getLastIndexByPredicate(Predicate<T> predicate) {
		int index = -1;
		Node<T> currentNode = tail;
		for(int i = size - 1;  currentNode != null && index == -1; i--) {
			if(predicate.test(currentNode.obj)) {
				index = i;
			} else {
				currentNode = currentNode.prev;
			}
		}
		return index;
	}

	//removing manager
/*
	private void removeNode(Node<T> node) {
		if(node == head && node == tail) {
			removeSingle();
		} else if(node == head) {
			removeHead();
		} else if(node == tail) {
			removeTail();
		} else {
			removeMiddle(node);
		}
	}

	private void removeSingle() {
		head = null;
		tail = null;
		size --;
	}

	private void removeHead() {
		head = head.next;
		head.prev = null;
		size --;
	}

	private void removeTail() {
		tail = tail.prev;
		tail.next = null;
		size --;
	}

	private void removeMiddle(Node<T> nodeForRemove) {
		nodeForRemove.prev.next = nodeForRemove.next;
		nodeForRemove.next.prev = nodeForRemove.prev;
		size --;
	}

*/
	
	//by Yri
	private void removeHead() {
		Node<T> newHead = head.next;
		if (newHead != null) {
			newHead.prev = null;
		}
		NoSu
		head = newHead;

	}

	private void removeTail() {
		Node<T> newTail = tail.prev;
		if (newTail != null) {
			newTail.next = null;
		}
		tail.prev = null;
		tail = newTail;
	}

	private void removeMiddle(Node<T> node) {
		Node<T> nodeBefore = node.prev;
		Node<T> nodeAfter = node.next;
		nodeBefore.next = nodeAfter;
		nodeAfter.prev = nodeBefore;
		node.next = node.prev = null;
	}

	private void removeNode(Node<T> node) {
		if (node == head) {
			removeHead();
		} else if (node == tail) {
			removeTail();
		} else {
			removeMiddle(node);
		}
		size--;
	}


	//Iterator

	private class LinkedListIterator implements Iterator<T> {
		//returns and check current node, after return current gets next
		
		//next() -> set true; remove() -> set false
		boolean flNext = false;
		Node<T> current = head;
  
		@Override
		public boolean hasNext() {
			return current != null;
		}
		
		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T object = current.obj;
			current = current.next;
			flNext = true;
			return object;	
		}
		
		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			Node<T> removedNode = current != null ? current.prev : tail;
			removeNode(removedNode);
			flNext = false;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

}
