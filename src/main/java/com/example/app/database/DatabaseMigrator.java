package com.example.app.database;

import org.flywaydb.core.Flyway;

public class DatabaseMigrator {
	public static void migrate(DatabaseConfig config) {
		Flyway flyway = Flyway.configure()
				.dataSource(
						config.getUrl(),
						config.getUsername(),
						config.getPassword())
				.load();
		flyway.migrate();
	}
}
