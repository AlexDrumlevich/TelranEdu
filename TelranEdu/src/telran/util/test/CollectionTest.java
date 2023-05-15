package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.Collection;

public abstract class CollectionTest {
//TODO move tests of interface collection methods (5 methods) from ListTest
//	to here
	protected Integer[] numbers = {10, -20, 7, 50, 100, 30};
	protected Collection<Integer> collection;
	@BeforeEach
	void setUp() {
		collection = getCollection();
		for( int i = 0; i < numbers.length; i++) {
			collection.add(numbers[i]);
		}
	}
	abstract protected Collection<Integer> getCollection();
}
