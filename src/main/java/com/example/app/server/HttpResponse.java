package com.example.app.server;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
	private final int statusCode; // required
	private final Map<String, String> headers;
	private final String body;

	public HttpResponse(Builder builder) {
		this.statusCode = builder.statusCode;
		this.headers = builder.headers;
		this.body = builder.body;
	}

	public char[] build() {
		if (body != null) {
			headers.put("Content-Type", "application/json");
			headers.put("Content-Length", String.valueOf(body.getBytes().length));
		}
		StringBuilder sb = new StringBuilder();
		sb.append("HTTP/1.1").append(" ").append(statusCode).append(" ").append(getReasonPhrase()).append("\r\n");
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\r\n");
		}
		sb.append("\r\n");
		if (body != null) {
			sb.append(body);
		}
		return sb.toString().toCharArray();
	}

	private String getReasonPhrase() {
		return switch (statusCode) {
			// 2xx
			case 200 -> "Ok";
			case 201 -> "Created";

			// 4xx
			case 400 -> "Bad Request";
			case 401 -> "Unauthorized";
			case 403 -> "Forbidden";
			case 404 -> "Not Found";
			case 405 -> "Method Not Allowed";

			// 5xx
			case 500 -> "Internal Server Error";
			default -> "";
		};
	}

	public static class Builder {
		private final int statusCode;
		private Map<String, String> headers = new HashMap<>();
		private String body;

		public Builder(int statusCode) {
			this.statusCode = statusCode;
		}

		public Builder headers(Map<String, String> headers) {
			this.headers.putAll(headers);
			return this;
		}

		public Builder body(String body) {
			this.body = body;
			return this;
		}

		public HttpResponse build() {
			return new HttpResponse(this);
		}
	}
}
