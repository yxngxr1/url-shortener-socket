package com.example.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseManager {
	private static DatabaseManager instance;
	private String url = "jdbc:postgresql://localhost:";
	private final String dbUser;
	private final String dbPassword;

	private DatabaseManager(Dotenv dotenv) {
		url = url + dotenv.get("DB_PORT") + "/" + dotenv.get("DB_NAME");
		this.dbUser = dotenv.get("DB_USER");
		this.dbPassword = dotenv.get("DB_PASSWORD");
	}

	public static synchronized void init(Dotenv dotenv) {
		if (instance == null) {
			instance = new DatabaseManager(dotenv);
		}
	}

	public static DatabaseManager getInstance() {
		if (instance == null) {
			throw new IllegalStateException("DatabaseManager not initialized");
		}
		return instance;

	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, dbUser, dbPassword);
	}
}
