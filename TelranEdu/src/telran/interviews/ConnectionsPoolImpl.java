package telran.interviews;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConnectionsPoolImpl implements ConnectionsPool {

	
	private LinkedHashMap <Integer, Connection> mapConnections;
	private int limit; //limit of connections number in a pool
	public ConnectionsPoolImpl(int limit) {
		if(limit < 1) {
			throw new IllegalArgumentException("limin must be positive");
		}
		this.limit = limit;
		mapConnections = new LinkedHashMap<>(limit, 0.75f, true) {
			protected boolean removeEldestEntry(Map.Entry<Integer, Connection> eldest) {
				return size() > limit;
			}
		};
	}
	
	@Override
	public boolean addConnection(Connection connection) {
		if(connection == null) {
			throw new IllegalArgumentException("Connection could not be Null");
		}
		Connection resultConnection = mapConnections.putIfAbsent(connection.id, connection);
		return resultConnection == null;
	}

	@Override
	public Connection getConnection(int id) {
		return mapConnections.get(id);
	}

	

}
