package telran.interviews.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.interviews.StackInt;

import java.util.*;

class ListTests {
Integer[] numbers = {10, -30, 13, 48, 22,17, 23};
List<Integer> immutableList = Arrays.asList(numbers);
List<Integer> mutableList;
	@BeforeEach
	void setUp() throws Exception {
		mutableList = new ArrayList<>(immutableList);
	}

	@Test
	void toArrayTest() {
		assertIterableEquals(mutableList, immutableList);
		assertArrayEquals(numbers, mutableList.toArray(Integer[]::new));
		assertArrayEquals(numbers, immutableList.toArray(Integer[]::new));
	}
	@Test
	void updateListsTest() {
		assertThrowsExactly(UnsupportedOperationException.class,
				() -> immutableList.remove(0));
		List<Integer> subList = mutableList.subList(2, 5);
		
		subList.clear();
		Integer[] expected1 = {10, -30, 17, 23};
		assertArrayEquals(expected1, mutableList.toArray(Integer[]::new));
		subList = mutableList.subList(2, 4);
		subList.set(1, 500);
		Integer[] expected2 = {10, -30, 17, 500};
		assertArrayEquals(expected2, mutableList.toArray(Integer[]::new));
		subList.add(0, -50);
		Integer[] expected3 = {10, -30, -50, 17, 500};
		assertArrayEquals(expected3, mutableList.toArray(Integer[]::new));
		Integer[] expected4 = {-50, 17, 500};
		Integer[] expected5 = {10, -30, -50, 17};
		subList.remove(2);
//		List<Integer> subList2 = mutableList.subList(0, 2);
//		subList.add(40);
//		//assertArrayEquals(expected5, mutableList.toArray(Integer[]::new));
//		System.out.println(subList2);
		
		
		
		
		
		
	}
	@Test
	void stackTest() {
		Stack<Integer> stack = new Stack<>();
		stack.push(10);
		assertFalse(stack.isEmpty());
		assertEquals(10, stack.pop());
		assertTrue(stack.isEmpty());
		
		
	}
	@Test
	void queueTest() {
		Queue<Integer> queue = new LinkedList<>(); 
		assertThrowsExactly(NoSuchElementException.class, ()->queue.remove());
	}

	
	@Test
	void stackIntTest() {
		StackInt stackInt = new StackInt();
		assertThrowsExactly(NoSuchElementException.class, () -> stackInt.pop());
		assertThrowsExactly(NoSuchElementException.class, () -> stackInt.max());
		assertTrue(stackInt.isEmpty());
		
		stackInt.push(30);
		assertFalse(stackInt.isEmpty());
		stackInt.push(100);
		stackInt.push(-100);
		stackInt.push(50);
		assertEquals(100, stackInt.max());
		assertEquals(50, stackInt.pop());
		assertEquals(100, stackInt.max());
		assertEquals(-100, stackInt.pop());
		assertEquals(100, stackInt.max());
		assertEquals(100, stackInt.pop());
		assertEquals(30, stackInt.max());
		assertEquals(30, stackInt.pop());
		
		assertThrowsExactly(NoSuchElementException.class, () -> stackInt.pop());
		assertThrowsExactly(NoSuchElementException.class, () -> stackInt.max());
		assertTrue(stackInt.isEmpty());
			
	}
	
}
