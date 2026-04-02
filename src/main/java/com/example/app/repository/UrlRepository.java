package com.example.app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.app.DatabaseManager;

public class UrlRepository {

	public void save(String code, String url) throws SQLException {
		String sql = "INSERT INTO short_links (code, url) VALUES (?, ?)";

		try (Connection conn = DatabaseManager.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, code);
			stmt.setString(2, url);
			stmt.executeUpdate();
		}
	}

	public String findByUrl(String longUrl) throws SQLException {
		String sql = "SELECT code from short_links WHERE url=?";
		try (Connection conn = DatabaseManager.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, longUrl);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("code");
			}
			return null;
		}
	}

	public String findByCode(String code) throws SQLException {
		String sql = "SELECT url from short_links WHERE code=?";
		try (Connection conn = DatabaseManager.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, code);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("url");
			}
		}
		return null;
	}
}
