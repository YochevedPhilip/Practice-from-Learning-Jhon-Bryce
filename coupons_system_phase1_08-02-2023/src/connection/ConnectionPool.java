package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



public class ConnectionPool {

	private Set<Connection> connections = new HashSet<>();
	private final int MAX_CONNECTIONS = 10;

	private String url = "jdbc:mysql://localhost:3306/coupons_project";
	private String user = "root";
	private String password = "1234";
	private static ConnectionPool instance = new ConnectionPool();

	private ConnectionPool() {
		for (int i = 0; i < MAX_CONNECTIONS; i++) {
			try {
				this.connections.add(DriverManager.getConnection(url, user, password));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	public synchronized Connection getConnection() {
		if (connections.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Iterator<Connection> it = connections.iterator();
		Connection con = it.next();
		it.remove();
		return con;
	}

	public synchronized void restoreConnection(Connection connection) {
		connections.add(connection);
		notify();
	}

	public synchronized void closeAllConnections() {

		while (connections.size() < MAX_CONNECTIONS) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (Connection connection : connections) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
