package com.example.app;

import org.flywaydb.core.Flyway;

import io.github.cdimascio.dotenv.Dotenv;

class DatabaseMigrator {
	public static void migrate(Dotenv dotenv) {
		String url = "jdbc:postgresql://localhost:" + dotenv.get("DB_PORT") + "/" + dotenv.get("DB_NAME");

		Flyway flyway = Flyway.configure()
				.dataSource(url,
						dotenv.get("DB_USER"),
						dotenv.get("DB_PASSWORD"))
				.load();
		flyway.migrate();
	}
}
