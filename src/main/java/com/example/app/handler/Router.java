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

	public Handler get(String path) {
		if (handleMap.containsKey(path)) {
			return handleMap.get(path);
		}

		if (path.matches("/[A-Za-z0-9]+")) {
			return handleMap.get("/redirect");
		}

		return new NotFoundHandler();
	}
}
