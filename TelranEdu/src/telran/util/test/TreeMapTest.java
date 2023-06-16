package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.TreeMap;

public class TreeMapTest extends MapTest {
	@BeforeEach
	@Override
	void setUp() {
		map = new TreeMap<>();
		super.setUp();
	}
}
