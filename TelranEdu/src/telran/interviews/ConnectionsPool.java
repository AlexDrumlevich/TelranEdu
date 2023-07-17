package telran.interviews;

public interface ConnectionsPool {
/**
 * adds new connection at beginning of Connections Pool
 * @param connection
 * @return true if added otherwise false if a connection already exists
 * in the case the Connection Pool is full the eldest connection will be removed
 */
	boolean addConnection(Connection connection);
	
	/**
	 * 
	 * @param id
	 * @return Connection if exists otherwise null
	 * moves the connection to the first order-access of Connection Pool
	 */
	Connection getConnection (int id);

}
