package com.example.app.handler;

import com.example.app.server.HttpRequest;
import com.example.app.server.HttpResponse;
import com.example.app.server.ResponseFactory;

public class HealthHandler implements Handler {
	public HttpResponse handle(HttpRequest request) {
		return ResponseFactory.ok("ok");
	}
}
