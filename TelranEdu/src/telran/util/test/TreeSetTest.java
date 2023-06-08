package telran.util.test;

import telran.util.TreeSet;
import telran.util.TreeSet.Node;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.LinkedList;
import telran.util.Set;

public class TreeSetTest extends SortedSetTest {
TreeSet<Integer> treeSet;
@BeforeEach
@Override
void setUp() {
	super.setUp();
	treeSet = (TreeSet<Integer>) set;
}
	@Override
	protected <T> Set<T> getSet() {
		
		return new TreeSet<>();
	}
//	@Override
//	@Test
//	void clearPerformance() {
//		
//	}
	@Test
	void displayTree() {
		treeSet.setInitialLevel(5);
		treeSet.setSpacesPerLevel(3);
		treeSet.displayRotated();
	}
	@Test
	void widthTest() {
		assertEquals(3, treeSet.width());
	}
	@Test
	void heightTest() {
		assertEquals(3, treeSet.height());
	}
	@Test
	void balanceTest() {
		TreeSet<Integer> treeBalanced = new TreeSet<>();
		int [] array = getRandomArray(255);
		fillCollection(treeBalanced, array);
		treeBalanced.balance();
		assertEquals(8, treeBalanced.height());
		assertEquals(128, treeBalanced.width());
	}
	@Test
	void balanceTestFromSorted() {
		int height = 20;
		int nNumbers =  (int) Math.pow(2, height);
		int [] array = new int[nNumbers - 1];
		for(int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		TreeSet<Integer> treeBalanced = new TreeSet<>();
		
		balanceOrder(array);
		fillCollection(treeBalanced, array);
		
		assertEquals(height, treeBalanced.height());
		assertEquals(nNumbers / 2, treeBalanced.width());
		
	}
	private void balanceOrder(int[] array) {
		// TODO 
		//reorder array such that adding to tree will get a balanced tree
	
		if(array.length > 2) {
			LinkedList<Integer> supportLinkedList = new LinkedList<>();  
			int left = 0;
			int right = array.length - 1;
			int index = 0;
			balance(array, supportLinkedList, left, right);
			
			for(int i : supportLinkedList) {
				array[index ++] = i;
			}
			
		}
	
	}
	
	private void balance(int[] array, LinkedList<Integer> supportLinkedList, int left, int right)  {
		int indexOfRoot = (right + left) / 2;
		if(left <= right) {
			supportLinkedList.add(array[indexOfRoot]);
			balance(array, supportLinkedList, left, indexOfRoot - 1);
			balance(array, supportLinkedList, indexOfRoot + 1, right);
		}
		
	}
	
	
	@Test
	void inversionTreeTest() {
		Integer[] expected = {100, 50, 30, 10, 7, -20};
		treeSet.inversion();
		for(Integer i : treeSet) {
			System.out.println(i);
		}
	    assertArrayEquals(expected, treeSet.toArray(new Integer[0]));
	    assertTrue(treeSet.contains(100));
	}

}
