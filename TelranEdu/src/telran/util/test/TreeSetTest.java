package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.Set;
import telran.util.TreeSet;

public class TreeSetTest extends SetTest {
	@Override
	protected <T> Set<T> getSet() {
		
		return new TreeSet<>();
	}
	
	TreeSet<Integer> treeSet;
	Integer[] startArray;
	
	@BeforeEach
	void setNewTheeSetAndArray() {
		treeSet = null;
		startArray = null;
	}
	
	@Test
	void testRemoveSingleRoot() {
		startArray = new Integer[]{10};
		treeSet = getTreeSetFromArray(startArray);
		assertTrue(treeSet.remove(startArray[0]));
		Integer[] expectedArray = {};	
		runTreeSetTest(expectedArray, treeSet);

	}
	
	@Test
	void testRemoveRootWithLeftAndRight() {
		startArray = new Integer[]{10, 6, 12};
		getTreeSetFromArray(startArray);
		assertTrue(treeSet.remove(startArray[0]));
		Integer[] expectedArray = {6, 12};	
		runTreeSetTest(expectedArray, treeSet);
	}
	
	@Test
	void testRemoveleftLeaf() {
		startArray = new Integer[]{10, 6, 4, 12};
		getTreeSetFromArray(startArray);
		assertTrue(treeSet.remove(4));
		Integer[] expectedArray = {10, 6, 12};	
		runTreeSetTest(expectedArray, treeSet);
	}
	
	@Test
	void testRemoveRightLeaf() {
		startArray = new Integer[]{10, 6, 4, 12};
		getTreeSetFromArray(startArray);
		assertTrue(treeSet.remove(12));
		Integer[] expectedArray = {10, 6, 4};	
		runTreeSetTest(expectedArray, treeSet);
	}
	
	@Test
	void testRemoveNodeWithLeftOnly() {
		startArray = new Integer[]{10, 6, 4, 12};
		getTreeSetFromArray(startArray);
		assertTrue(treeSet.remove(6));
		Integer[] expectedArray = {10, 4, 12};	
		runTreeSetTest(expectedArray, treeSet);
	}
	
	@Test
	void testRemoveNodeWithRightOnly() {
		startArray = new Integer[]{10, 6, 4, 12, 24};
		getTreeSetFromArray(startArray);
		assertTrue(treeSet.remove(12));
		Integer[] expectedArray = {10, 6, 4, 24};	
		runTreeSetTest(expectedArray, treeSet);
	}
	
	@Test
	void testRemoveJunctionBeforeGraterParent() {
		startArray = new Integer[]{10, 6, 4, 8, 12, 24};
		getTreeSetFromArray(startArray);
		assertTrue(treeSet.remove(6));
		Integer[] expectedArray = {10, 4, 8, 12, 24};	
		runTreeSetTest(expectedArray, treeSet);
	}
	
	@Test
	void testRemoveJunctionNotBeforeGraterParent() {
		startArray = new Integer[]{10, 3, 1, 5, 4, 7, -10, 20};
		getTreeSetFromArray(startArray);
		assertTrue(treeSet.remove(5));
		Integer[] expectedArray = {10, 3, 1, 4, 7, -10, 20};	
		runTreeSetTest(expectedArray, treeSet);
	}
	
	private TreeSet<Integer> getTreeSetFromArray(Integer[] startArray) {
		treeSet = new TreeSet<>();
		for(int i = 0; i < startArray.length; i++) {
			treeSet.add(startArray[i]);
		}
		return treeSet;
	}

	private void runTreeSetTest(Integer[] expected, TreeSet<Integer> treeSet) {
		Integer [] actual = treeSet.toArray(new Integer[0]);
		Arrays.sort(actual);
		Arrays.sort(expected);
		assertArrayEquals(expected, actual);
		
	}
		
}
