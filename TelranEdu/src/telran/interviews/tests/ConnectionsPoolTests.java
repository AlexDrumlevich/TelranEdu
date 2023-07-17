package telran.interviews.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.interviews.Connection;
import telran.interviews.ConnectionsPool;
import telran.interviews.ConnectionsPoolImpl;

class ConnectionsPoolImplTest {

	private static int CONNECTIONS_POOL_LIMIT = 5;
	ConnectionsPool pool;

	@BeforeEach
	void setUp() {
		pool = new ConnectionsPoolImpl(CONNECTIONS_POOL_LIMIT);
		pool.addConnection(new Connection(11, "aaa", 1));
		pool.addConnection(new Connection(22, "bbb", 2));
		pool.addConnection(new Connection(33, "ccc", 3));
		pool.addConnection(new Connection(44, "ddd", 4));
		pool.addConnection(new Connection(55, "eee", 5));
	}

	@Test
	void addConnection() {
		// trying to add an existing element
		assertFalse(pool.addConnection(new Connection(33, "ccc", 3)));

		

		// added element to full pool
		assertTrue(pool.addConnection(new Connection(66, "fff", 6)));

		

		

		// checked that this element was removed from the pool and it can be added again
		assertTrue(pool.addConnection(new Connection(11, "aaa", 1)));

	}

	@Test
	void getConnection() {
		// take the last element and check that it is the first
		Connection con55 = pool.getConnection(55);
		

		// take the penultimate element and check that it became the last
		Connection con44 = pool.getConnection(44);
		

		assertEquals(33, pool.getConnection(33).id);

		assertEquals("bbb", pool.getConnection(22).ipAddress);
		assertEquals(2, pool.getConnection(22).port);

		// take a non-existent element
		assertNull(pool.getConnection(99));

	}


}
