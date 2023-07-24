package telran.interviews.collectionTasks;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 
 * All methods have to have the Complexity O[1]
 *
 */
//max value
//StackNode uses LinkedList of StackNode class objects
//each node has max value
//if max value of head StackNode is more then new value => this max values 
//is used as max value for new StackNode,
//if not, new int value is set as value for new StackNode, but for second node  old max value doesn`t change
public class StackInt {
	//TODO fields
	/**
	 * 
	 * @param num
	 * adds num in the stack
	 */
	LinkedList<StackNode> linkedList = new LinkedList<>();

	static private class StackNode {
		int value;
		int localMax;

		public StackNode(int value, int localMax) {
			this.value = value;
			this.localMax = localMax;
		}
	}


	public void push(int num) {
		//TODO

		linkedList.addFirst(new StackNode(num, (!isEmpty() && max() > num) ? max() : num));
	}

	/**
	 * 
	 * @return
	 * takes out and returns the top stack number
	 * in the case the stack is empty the exception of the class NoSuchElement should be thrown
	 */
	public int pop() {
		if(isEmpty()) {
			throw new NoSuchElementException("stack is empty");
		}
		return linkedList.removeFirst().value;
	}

	/**
	 * 
	 * @return true if the stack is empty
	 */
	public boolean isEmpty() {
		//TODO
		return linkedList.isEmpty();
	}
	/**
	 * 
	 * @return maximal value existing in the stack
	 * throws NoSuchElementException in the case the stack is empty
	 */
	public int max() {
		//TODO
		if(isEmpty()) {
			throw new NoSuchElementException("stack is empty");
		}
		return linkedList.getFirst().localMax;
	}
}
