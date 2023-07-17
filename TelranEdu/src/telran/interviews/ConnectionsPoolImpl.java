package telran.interviews;

public class ConnectionsPoolImpl implements ConnectionsPool {


//TODO
	int limit; //limit of connections number in a pool
	public ConnectionsPoolImpl(int limit) {
		this.limit = limit;
	}
	@Override
	public boolean addConnection(Connection connection) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
