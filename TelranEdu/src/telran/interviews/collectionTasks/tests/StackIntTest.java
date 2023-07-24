package telran.interviews.collectionTasks.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.interviews.collectionTasks.StackInt;

class StackIntTest {
	private static final long N_NUMBERS = 10000;
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 100;
	StackInt stack = new StackInt();
	LinkedList<Integer> list = new LinkedList<>();
	StackInt stackEmpty = new StackInt();
	Random gen = new Random();
		@BeforeEach
		void setUp() throws Exception {
			gen.ints(N_NUMBERS, MIN_NUMBER, MAX_NUMBER).
					forEach(n -> {
						stack.push(n);
						list.add(n);
					});
		}
	@Test
	void testPop() {
		assertEquals(list.removeLast(), stack.pop());
		
		assertThrows(NoSuchElementException.class, ()-> stackEmpty.pop());
	}

	@Test
	void testPush() {
		stack.push(-10);
		assertNotEquals(list.removeLast(), stack.pop());
	}

	@Test
	void testIsEmpty() {
		assertFalse(stack.isEmpty());
		assertTrue(stackEmpty.isEmpty());
	}

	@Test
	void testGetMaxNumber() {
		testRandom();
		predefinedMaxTest();
	}
	void testRandom() {
		for (int i = 0; i < N_NUMBERS; i++) {
			if (Math.random() * 100 < 50) {
				try {
					stack.pop();
					list.removeLast();
				} catch (Exception e) {
					
				}
				
			}
			else {
				int number =  gen.nextInt(MIN_NUMBER, MAX_NUMBER);
				
				stack.push(number);
				list.add(number);
			}
		}
		assertEquals((int)Collections.max(list), stack.max());
	}
	void predefinedMaxTest() {
		int ar[]= {100000,50000,100000,20,20,20,2000000};
		for (int i = 0; i < ar.length; i++) {
			stack.push(ar[i]);
		}
		assertEquals(2000000, stack.max());
		stack.pop();stack.pop();stack.pop();
		assertEquals(100000, stack.max());
	}
	

}
