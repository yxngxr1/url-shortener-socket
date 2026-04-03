package com.example.app.database;

import java.sql.Connection;
import java.sql.SQLException;

public class Connections {
	private static volatile ConnectionProvider provider;

	public static void configure(ConnectionProvider provider) {
		Connections.provider = provider;
	}

	public static Connection getConnection() throws SQLException {
		if (provider == null) {
			throw new IllegalStateException("ConnectionProvider not configured");
		}
		return provider.getConnection();
	}
}
