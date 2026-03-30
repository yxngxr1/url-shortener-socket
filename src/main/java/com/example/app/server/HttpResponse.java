package com.example.app.server;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
	private final int statusCode;
	private Map<String, String> headers = new HashMap<>();
	private String body = null;

	public HttpResponse(int statusCode) {
		this.statusCode = statusCode;
	}

	public HttpResponse(int statusCode, Map<String, String> headers, String body) {
		this.statusCode = statusCode;
		this.headers.putAll(headers);
		this.body = body;
	}

	public char[] build() {
		if (body != null) {
			headers.put("Content-Type", "text/html");
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
			case 200 -> "Ok";
			default -> "";
		};
	}
}
