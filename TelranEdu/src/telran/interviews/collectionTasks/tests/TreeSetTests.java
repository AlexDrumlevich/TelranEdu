package telran.interviews.collectionTasks.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeSetTests {
	Integer[] numbers = {10, -30, 13, 48, 22,17, 23};
	TreeSet<Integer> treeSet;
	@BeforeEach
	void setUp() {
		treeSet = new TreeSet<>();
		Arrays.stream(numbers).forEach(treeSet::add);
	}
	@Test
	void subTreeTest() {
		NavigableSet<Integer> subSet =
				treeSet.subSet(-30, true, 13, true);
		Integer[] expected1 = {-30, 10, 13};
		assertArrayEquals(expected1, subSet.toArray(Integer[]::new));
		Integer[] expected2 = { -30, 10, 13, 17, 22, 23, 48  };
		assertArrayEquals(expected2, treeSet.toArray(Integer[]::new));
		Integer[] expected3 = {  10, 13, 17, 22, 23, 48  };
		subSet.remove(-30);
		assertArrayEquals(expected3, treeSet.toArray(Integer[]::new));
		treeSet.remove(13);
		Integer[] expected4 = {  10 };
		assertArrayEquals(expected4, subSet.toArray(Integer[]::new));
		treeSet.add(13);
		treeSet.add(12);
		treeSet.add(14);
		subSet.add(-20);
		subSet.add(11);
		treeSet.add(-30);
		SortedSet<Integer> subSet1 = treeSet.subSet(-100, 0);
		subSet1.add(-100);
		System.out.println(subSet);
		
		
		
	}
	
}
