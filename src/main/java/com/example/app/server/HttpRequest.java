package com.example.app.server;

import java.util.Map;

public class HttpRequest {
	private final String method;
	private final String path;
	private final String protocol;
	private final Map<String, String> headers;
	private final String body;
	private final Map<String, String> queryParams;

	public HttpRequest(String method, String path, Map<String, String> queryParams, String protocol,
			Map<String, String> headers, String body) {
		this.method = method;
		this.path = path;
		this.queryParams = queryParams;
		this.protocol = protocol;
		this.headers = headers;
		this.body = body;
	}

	public String getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	public Map<String, String> getQueryParams() {
		return queryParams;
	}

	public String getProtocol() {
		return protocol;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public String getBody() {
		return body;
	}

}
