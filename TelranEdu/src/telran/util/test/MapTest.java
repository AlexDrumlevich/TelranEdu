package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;

import telran.util.Map;

import org.junit.jupiter.api.Test;

abstract class MapTest {

	String[] keys = {"lmn", "abc", "ab", "a"};
	Integer[] values = {3, 2, 2, 1};

	protected Map<String, Integer> map;
	@BeforeEach
	void setUp() {
		for(int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
	}

	@Test
	void getTest() {
		for(int i = 0; i < keys.length; i++) {
			assertEquals(values[i], map.get(keys[i]));
		}
	}
	
	@Test
	void putTest() {
		assertNull(map.put("hundred", 100));
		assertEquals(100, map.put("hundred", 1000));
		assertEquals(1000, map.put("hundred", null));
	}
	

	@Test
	void containsKeyTest() {
		for(int i = 0; i < keys.length; i++) {
			assertTrue(map.containsKey(keys[i]));
		}
		
		String[] nonExistentKeys = {"slmn", "sabc", "sab", "sa"};
		for(int i = 0; i < nonExistentKeys.length; i++) {
			assertFalse(map.containsKey(nonExistentKeys[i]));
		}
	}
	
	@Test
	void containsValue() {
		for(int i = 0; i < values.length; i++) {
			assertTrue(map.containsValue(values[i]));
		}
		
		Integer[] nonExistentValues = {4, 100, -2, 0};
		for(int i = 0; i < nonExistentValues.length; i++) {
			assertFalse(map.containsValue(nonExistentValues[i]));
		}
		
		//null test
		assertFalse(map.containsValue(null));
		map.put("null", null);
		assertTrue(map.containsValue(null));
	}
	
	
	@Test
	void keysSetTest() {
		
		String[] actualSortedKeysArray = map.keySet().stream().sorted().toArray(String[]::new);
		Arrays.sort(keys, Comparator.naturalOrder());
		assertArrayEquals(actualSortedKeysArray, keys);
		
		String[] wrongKeysArray = new String[] {"lmna", "abca", "aba", "aa"};
		assertFalse(Arrays.equals(actualSortedKeysArray, wrongKeysArray));
	}
	
	@Test
	void collectionValuesTest() {
		
		Integer[] actualSortedValuesArray = map.values().stream().sorted().toArray(Integer[]::new);
		Arrays.sort(values, Comparator.naturalOrder());
		assertArrayEquals(actualSortedValuesArray, values);
		
		Integer[] wrongValuesArrayFirst = new Integer[] {1, 4, 7, 9};
		Integer[] wrongValuesArraySecond = new Integer[] {1, 7, 9};
		assertFalse(Arrays.equals(actualSortedValuesArray, wrongValuesArrayFirst));
		assertFalse(Arrays.equals(actualSortedValuesArray, wrongValuesArraySecond));
	}
	
	@Test
	void removeTest() {
		
		Integer value = map.get("lmn");
		assertTrue(value != null);
		assertTrue(value.equals(map.remove("lmn")));
		assertTrue(map.get("lmn") == null);		
		assertTrue(map.remove("lmn") == null);
		assertTrue(map.remove("abcdefg") == null);
		
	}

}
