package com.example.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionProvider implements ConnectionProvider {
	private final String url;
	private final String username;
	private final String password;

	public SimpleConnectionProvider(DatabaseConfig config) {
		this.url = config.getUrl();
		this.username = config.getUsername();
		this.password = config.getPassword();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}
