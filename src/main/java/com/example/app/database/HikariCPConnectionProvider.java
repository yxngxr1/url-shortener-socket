package com.example.app.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPConnectionProvider implements ConnectionProvider {
	private final HikariDataSource dataSource;

	public HikariCPConnectionProvider(DatabaseConfig config) {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(config.getUrl());
		hikariConfig.setUsername(config.getUsername());
		hikariConfig.setPassword(config.getPassword());
		hikariConfig.setMaximumPoolSize(config.getMaxPoolSize());
		hikariConfig.setMinimumIdle(config.getMinIdle());
		hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
		hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		this.dataSource = new HikariDataSource(hikariConfig);
	}

	@Override
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
