package com.example.app.handler;

import com.example.app.server.HttpRequest;
import com.example.app.server.HttpResponse;

public class HealthHandler implements Handler {
	public HttpResponse handle(HttpRequest request) {
		return new HttpResponse(200);
	}
}
