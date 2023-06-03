package telran.util;

import java.awt.desktop.AboutHandler;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardUpLeftHandler;

public class TreeSet<T> implements SortedSet<T> {

	//PROPERTIES
	private Node<T> root;
	private Comparator<T> comp;
	private int size;

	//INIT
	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}
	public TreeSet(Comparator<T> comparator) {
		comp = comparator;
	}
	
	//INNER CLASSES
	//inner class Node 
	private static class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;
		Node(T obj) {
			this.obj = obj;
		}
	}

	//inner class Iterator 
	private class TreeSetIterator implements Iterator<T> {
		Node<T> current;
		Node<T> prev;
		boolean flNext = false;
		
		TreeSetIterator() {
			//get the the least object 
			current = root == null ? null : getLeast(root);
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			prev = current;
			//get next - bigger (depends on comparator)
			current = getCurrent(current);
			flNext = true;
			return res;
		}
		
		@Override
		public void remove() {
			if(!flNext) {
				throw new IllegalStateException();
			}
			//remove node, saved as a previous
			removeNode(prev);
			flNext = false;
		}
	}

	//METHODS
		//PUBLIC
		
	//add new object 
	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<>(obj);
		//result - change to true if add was successful
		boolean res = false;
		//if set is empty - add as a root
		if (size == 0) {
			root = node;
			res = true;
		} else {
			//try to find parent for new object, if it was successful - add new node as right or left to this parent (depends on comparator result)   
			Node<T> parent = getParent(obj);
			if (parent != null) {
				res = true;
				//binding parent and node with new object 
				node.parent = parent;
				if(comp.compare(obj, parent.obj) > 0) {
					parent.right = node;
				} else {
					parent.left = node;
				}
			}
		}
		if (res) {
			size++;
		}
		return res;
	}
	
	@Override
	public boolean contains(T pattern) {
		return getNode(pattern) != null;
	}

	//iterator
	@Override
	public Iterator<T> iterator() {
		return new TreeSetIterator();
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		Node<T> node = getNode(pattern);
		if (node != null) {
			removeNode(node);
			res = true;
		}
		return res;
	}
	
	@Override
	public T first() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		return getLeast(root).obj;
	}
	
	@Override
	public T last() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		return getGreater(root).obj;
	}
	
	@Override
	public T ceiling(T key) {
		/*
		Node<T> current = getNodeParent(key);
		T res = null;
		if(current != null && comp.compare(current.obj, key) >= 0) {
			res = current.obj;
		}
		return res;		
		*/
		
		Node<T> current = getNodeParent(key);
		if(current != null && comp.compare(current.obj, key) < 0 ) {
			while(current.parent != null && current == current.parent.right)		{
				//get parent from right
				current = current.parent;
			}
			//get parent from left or null
			current = current.parent;
		}
		return current != null ? current.obj : null;
		
		//return getObjectOrBigger(false, key);
	}
	
	@Override
	public T floor(T key) {
		/*
		Node<T> current = getNodeParent(key);
		T res = null;
		if(current != null && comp.compare(current.obj, key) <= 0) {
			res = current.obj;
		}
		return res;
		*/
		
		Node<T> current = getNodeParent(key);
		if(current != null && comp.compare(current.obj, key) > 0) {
			while(current.parent != null && current == current.parent.left) {
				//get parent from left
				current = current.parent;
			}
			//get parent from right or null 
			current = current.parent;
		}
		return current != null ? current.obj : null;
		
//		return getObjectOrBigger(true, key);
	}
	
	
		//PRIVATE
	
	private T getObjectOrBigger(boolean reversOrder, T key) {
		//if key was found => return key
		//if key was not found => parent was found (may be null) 
		//if parent is grater then key => return parent (it is next in order) (may be null) 
		//if parent is lower then key => look for grater parent then suggested parent (it will be next in order, it`ll be grater then object, otherwise searching goes to opposite side from that parent in the beginning) (may be null)      
		//if looks for floor - opposite 
		Node<T> currentNode = getNodeParent(key);
		int compRes = reversOrder ? comp.compare(currentNode.obj, key) * -1 : comp.compare(currentNode.obj, key);
		if(compRes < 0) {
			currentNode = reversOrder ? getLeastParent(currentNode) : getGreaterParent(currentNode);
		}
		return currentNode != null ? currentNode.obj : null;
	}
	
	private Node<T> getNodeParent(T obj) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes;
		// while there is any node below OR the same object was not found 
		while(current != null && (compRes = comp.compare(obj, current.obj)) != 0) {
			//go down along the tree 
			//current becomes parent and as current sets left of right of new parent depends on comparator result
			parent = current;
			current = compRes > 0 ? current.right : current.left;
		}
		//in the end returns current , but if current is null - parent
		//if current is null - there is no such element in three and method return previous witch will be parent for node with new object
		//if current not a null - it is the same to object in Set  
		return current == null ? parent : current;
	}
	
	//get node is contained obj
	private Node<T> getNode(T obj) {
		Node<T> node = getNodeParent(obj);
		Node<T> res = null;
		//if comparator result is 0 => object the same to object that inside getting node
		if (node != null && comp.compare(obj, node.obj) == 0) {
			res = node;
		}
		return res;

	}
	//returns parent if there is not the same element in Set, otherwise null 
	private Node<T> getParent(T obj) {
		Node<T> node = getNodeParent(obj);
		Node<T> res = null;
		//if compare result = 0 => the same element there is in Set, but we use this method to add new (we don`t add the same, so return null as a result)
		//is compare result not 0 => there is a parent for new object, it means there is not the same object in set (in this case getNodeParent returns previous to place where obj could be =>  the place for new object is empty, 
		if (node != null && comp.compare(obj, node.obj) != 0) {
			res = node;
		}
		return res;
	}
	//get the node contains the least object in relation to given node
	private Node<T> getLeast(Node<T> node) {
		Node<T> current = node;
		while(current.left != null) {
			current = current.left;
		}
		return current;
	}
	
	//get the node contains the greater object in relation to given node
	private Node<T> getGreater(Node<T> node) {
		Node<T> current = node;
		while(current.right != null) {
			current = current.right;	
		}
		return current;
	}
	
	//to get next (bigger - depends on comparator) in relation to given node 
	private Node<T> getCurrent(Node<T> current) {
		//if there is node in the right => look for the least in the right
		//if there is not node in the right => look for parent, that bigger than given object (the bigger parent - parent witch we got to from left (current == current.parent.left) 
		return current.right != null ? getLeast(current.right) :
			getGreaterParent(current);
	}
	
	//look for parent (parent parent and so on), that bigger than given object (the bigger parent - parent witch we got to from left (current == current.parent.left) 
	private Node<T> getGreaterParent(Node<T> current) {
		while(current.parent != null && current == current.parent.right) {
			current = current.parent;
		}
		return current.parent;
	}
	
	private Node<T> getLeastParent(Node<T> current) {
		while(current.parent != null && current == current.parent.left) {
			current = current.parent;
		}
		return current.parent;
	}
	

	private void removeNode(Node<T> node) {
		
		//no left AND no right
		if(node.left == null && node.right == null) {
			changeParentSubNodeRelationship(node, null);
		// there is only left
		} else if (node.left != null && node.right == null) {
			changeParentSubNodeRelationship(node, node.left);
		// there is only right
		} else if (node.left == null && node.right != null) {
			changeParentSubNodeRelationship(node, node.right);
		//there are left and right
		} else {
			//finding node for replacement (grater in left branch)
			//this node can`t have right (it is the grater), but may have left, so set to its parent left of such grater
			Node<T> nodeForReplacementNode = getGreater(node.left);
			changeParentSubNodeRelationship(nodeForReplacementNode, nodeForReplacementNode.left);
			//change sub node for parent of node to delete
			changeParentSubNodeRelationship(node, nodeForReplacementNode);
			//nodeForReplacementNode.parent = node.parent;
	
			//set left and right of node to delete for node to replacement and change parent for them to nodeForReplacementNode 
			//nodeForReplacementNode could be only from left
			if(node.left != nodeForReplacementNode) {
				nodeForReplacementNode.left = node.left;
				if(nodeForReplacementNode.left != null)
					nodeForReplacementNode.left.parent = nodeForReplacementNode;
			}
			nodeForReplacementNode.right = node.right;
			nodeForReplacementNode.right.parent = nodeForReplacementNode;
		}
		size--;
		node = null;
	}	
	
	private void changeParentSubNodeRelationship(Node<T> oldNode, Node<T> newNode) {
		//method:
		//newNode.parent = oldNode.parent
		//newNode.parent.left(right) = oldNode.parent.left(right)
		//newNode may be null
		
		
		//get parent for node to remove
		Node<T> parentNode = oldNode.parent;
		
		//if parent is null => root is deleting
		if(parentNode != null) {
		
			//newNode.parent.left(right) = oldNode.parent.left(right)
			if(comp.compare(oldNode.obj, parentNode.obj) < 0)
				parentNode.left = newNode;
			else
				parentNode.right = newNode;
			
			//newNode.parent = oldNode.parent
			if(newNode != null)
				newNode.parent = parentNode;
			
		} else {
			//if removing node is a root (oldNode.parent == null)
			root = newNode;
			if(newNode != null)
				newNode.parent = null;
		}	
	}
}
