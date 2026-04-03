package com.example.app.database;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConfig {
	private final String url;
	private final String username;
	private final String password;
	private final int maxPoolSize;
	private final int minIdle;

	public DatabaseConfig(String url, String username, String password, int maxPoolSize, int minIdle) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.maxPoolSize = maxPoolSize;
		this.minIdle = minIdle;
	}

	public DatabaseConfig(Dotenv dotenv) {
		String url = String.format("jdbc:postgresql://%s:%s/%s",
				dotenv.get("DB_HOST", "localhost"),
				dotenv.get("DB_PORT", "5432"),
				dotenv.get("DB_NAME"));

		this.url = url;
		this.username = dotenv.get("DB_USER");
		this.password = dotenv.get("DB_PASSWORD");
		this.maxPoolSize = Integer.parseInt(dotenv.get("DB_MAX_POOL_SIZE", "10"));
		this.minIdle = Integer.parseInt(dotenv.get("DB_MIN_IDLE", "5"));
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public int getMinIdle() {
		return minIdle;
	}
}
