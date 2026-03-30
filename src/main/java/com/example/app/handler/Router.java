package com.example.app.handler;

import java.util.HashMap;
import java.util.Map;

public class Router {
	private Map<String, Handler> handleMap;

	public Router() {
		handleMap = new HashMap<>();
	}

	public void put(String path, Handler handler) {
		handleMap.put(path, handler);
	}

	public Handler get(String method, String path) {
		return handleMap.getOrDefault(path, new NotFoundHandler());
	}
}
