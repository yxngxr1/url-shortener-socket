package com.example.app.handler;

import org.json.JSONObject;

import com.example.app.server.HttpRequest;
import com.example.app.server.HttpResponse;
import com.example.app.service.UrlShortenerService;

public class RedirectHandler implements Handler {
	private final UrlShortenerService service;

	public RedirectHandler() {
		this.service = new UrlShortenerService();
	}

	public HttpResponse handle(HttpRequest request) {
		try {
			String path = request.getPath();
			String url = service.getUrl(path.substring(1, path.length()));
			JSONObject jsonBody = new JSONObject();
			jsonBody.put("url", url);
			return new HttpResponse(200, jsonBody.toString());
		} catch (Exception e) {
			JSONObject responseBody = new JSONObject();
			responseBody.put("message", e.getMessage());
			return new HttpResponse(500, responseBody.toString());
		}
	}

}
