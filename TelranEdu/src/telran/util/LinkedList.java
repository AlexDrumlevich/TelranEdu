package telran.util;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.AbstractDocument.LeafElement;

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
	public boolean remove(T pattern) {
		boolean result = false;
		if(size > 0) {
			//remove first relevant to pattern
			/*
			Node<T> nodeToRemove = getFirstNodeByPattern(pattern);
			if(nodeToRemove != null) {
				removeNode(nodeToRemove);
				result = true;
			}
			*/

			//remove all relevant to pattern
			result = removeAllBy(pattern);
		}
		return result;
	}
	
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

	
	@Override
	public boolean removeIf(Predicate<T> predicate) {
		boolean result = false;
		if(size > 0) {
			//remove first relevant to predicate
			/*
			Node<T> nodeToRemove = getFirstNodeByPredicate(predicate);
			if(nodeToRemove != null) {
				removeNode(nodeToRemove);
				result = true;
			 */

			//remove all relevant to predicate
			result = removeAllBy(predicate);
		}
		return result;
	}

	
	//TO ARRAY
	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		Node<T> current = head;
		int index = 0;
		while(current != null) {
			ar[index++] = current.obj;
			current = current.next;
		}
		if (ar.length > size) {
			ar[size] = null;
		}
		return ar;
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
	public int indexOf(T pattern) {
		int result = -1;
		if (size > 0) {
			result = getFirstIndexByPattern(pattern);
		}
		return result;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int result = -1;
		if (size > 0) {
			result = getLastIndexByPattern(pattern);
		}
		return result;
	}

	
	@Override
	public int indexOf(Predicate<T> predicate) {
		int result = -1;
		if (size > 0) {
			result = getFirstIndexByPredicate(predicate);
		}
		return result;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		int result = -1;
		if (size > 0) {
			result = getLastIndexByPredicate(predicate);
		}
		return result;
	}
	
	
	//SORT
	
	@Override
	public void sort() {
		// no implement
	}

	@Override
	public void sort(Comparator<T> comp) {
		// no implement
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
	
	//Pattern -> first index 
	//return -1 if not such index
	private int getFirstIndexByPattern(T pattern) {
		int index = -1;
		//start from head
		Node<T> currentNode = head;
		//WHILE index is not found OR passing throw all the nodes
		for(int i = 0;  currentNode != null && index == -1; i++) {
			if(currentNode.obj.equals(pattern)) {
				//if object in current node equals pattern, set index, for cycle will be break 
				index = i;
			} else {
				currentNode = currentNode.next;
			}
		}
		return index;
	}
	
	//Pattern -> last index 
	//return -1 if not such index
	private int getLastIndexByPattern(T pattern) {
		int index = -1;
		//start from tail
		Node<T> currentNode = tail;
		for(int i = size - 1;  currentNode != null && index == -1; i--) {
			if(currentNode.obj.equals(pattern)) {
				index = i;
			} else {
				currentNode = currentNode.prev;
			}
		}
		return index;
	}
	
	//Pattern -> first node 
	//used to remove first node by pattern
	/*
	private Node<T> getFirstNodeByPattern(T pattern) {
		Node<T> resultNode = null;
		Node<T> currentNode = head;
		while(resultNode == null && currentNode != null) {
			if(currentNode.obj.equals(pattern)) {
				resultNode = currentNode;
			} else {
				currentNode = currentNode.next;
			}
		}
		return resultNode;
	}
	*/
	
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
	
	//Predicate -> first node 
	//used to remove first node by predicate
	/*
	private Node<T> getFirstNodeByPredicate(Predicate<T> predicate) {
		Node<T> resultNode = null;
		Node<T> currentNode = head;
		while(resultNode == null && currentNode != null) {
			if(predicate.test(currentNode.obj)) {
				resultNode = currentNode;
			} else {
				currentNode = currentNode.next;
			}
		}
		return resultNode;
	}
	*/
	
	
	//REMOVE support
	
	//Pattern - remove all relevant
	private boolean removeAllBy(T pattern) {
		int startSize = size;
		Node<T> currentNode = head;
		while(currentNode != null) {
			if(currentNode.obj.equals(pattern)) {
				removeNode(currentNode);
			}
			currentNode = currentNode.next;
		}
		return size < startSize;
	}
	
	//Predicate - remove all relevant
	private boolean removeAllBy(Predicate<T> predicate) {
		int startSize = size;
		Node<T> currentNode = head;
		while(currentNode != null) {
			if(predicate.test(currentNode.obj)) {
				removeNode(currentNode);
			}
			currentNode = currentNode.next;
		}
		return size < startSize;
	}
	
	//removing manager
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
	
	
}
