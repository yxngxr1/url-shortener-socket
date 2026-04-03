package com.example.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.reflect.Proxy;

public class SimplePoolConnectionProvider implements ConnectionProvider {
	private final String url;
	private final String username;
	private final String password;
	private final List<Connection> pool = new ArrayList<>();
	private final List<Connection> used = new ArrayList<>();
	private final int maxPoolSize;
	private final int minIdle;
	private final AtomicInteger totalCreated = new AtomicInteger(0);

	public SimplePoolConnectionProvider(DatabaseConfig config) {
		this.url = config.getUrl();
		this.username = config.getUsername();
		this.password = config.getPassword();
		this.maxPoolSize = config.getMaxPoolSize();
		this.minIdle = config.getMinIdle();

		initializeMinConnections();
	}

	private void initializeMinConnections() {
		try {
			for (int i = 0; i < minIdle; i++) {
				pool.add(createNewConnection());
				totalCreated.incrementAndGet();
			}
		} catch (SQLException e) {
			throw new RuntimeException("Failed to initialize connection pool", e);
		}
	}

	private Connection createNewConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public synchronized Connection getConnection() throws SQLException {
		Connection real = null;

		if (!pool.isEmpty()) {
			real = pool.remove(pool.size() - 1);
		} else if (totalCreated.get() < maxPoolSize) {
			real = createNewConnection();
			totalCreated.incrementAndGet();
		} else {
			throw new SQLException("Connection pool exhausted. Max size: " + maxPoolSize);
		}

		used.add(real);
		return createProxy(real);
	}

	private Connection createProxy(Connection real) {
		return (Connection) Proxy.newProxyInstance(
				getClass().getClassLoader(),
				new Class[] { Connection.class },
				(proxy, method, args) -> {
					if (method.getName().equals("close")) {
						synchronized (SimplePoolConnectionProvider.this) {
							used.remove(real);
							pool.add(real);
						}
						return null;
					}
					return method.invoke(real, args);
				});
	}

	public synchronized int getActiveCount() {
		return used.size();
	}

	public synchronized int getIdleCount() {
		return pool.size();
	}

	public synchronized int getTotalCount() {
		return totalCreated.get();
	}

	public synchronized void close() {
		for (Connection conn : pool) {
			try {
				conn.close();
			} catch (SQLException e) {
				/* ignore */
			}
		}
		for (Connection conn : used) {
			try {
				conn.close();
			} catch (SQLException e) {
				/* ignore */
			}
		}
		pool.clear();
		used.clear();
		totalCreated.set(0);
	}
}
