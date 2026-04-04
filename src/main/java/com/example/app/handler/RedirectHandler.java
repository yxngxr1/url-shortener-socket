package com.example.app.handler;

import org.json.JSONObject;

import com.example.app.server.HttpRequest;
import com.example.app.server.HttpResponse;
import com.example.app.server.ResponseFactory;
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
			return ResponseFactory.ok(jsonBody.toString());
		} catch (Exception e) {
			return ResponseFactory.notFound(e.getMessage());
		}
	}

}
