package com.example.app.handler;

import org.json.JSONObject;

import com.example.app.server.HttpRequest;
import com.example.app.server.HttpResponse;

public class HealthHandler implements Handler {
	public HttpResponse handle(HttpRequest request) {
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("status", "ok");
		return new HttpResponse(200, jsonBody.toString());
	}
}
