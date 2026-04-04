package com.example.app.handler;

import org.json.JSONObject;

import com.example.app.dto.UrlShorten.UrlShortenerRequest;
import com.example.app.mapper.UrlShortenerMapper;
import com.example.app.server.HttpRequest;
import com.example.app.server.HttpResponse;
import com.example.app.server.ResponseFactory;
import com.example.app.service.UrlShortenerService;

public class UrlShortenerHandler extends BaseHandler {
	private final UrlShortenerService service;
	private final UrlShortenerMapper mapper;

	public UrlShortenerHandler() {
		this.service = new UrlShortenerService();
		this.mapper = new UrlShortenerMapper();
	}

	@Override
	protected HttpResponse doHandle(HttpRequest request) throws Exception {
		if (!"POST".equals(request.getMethod())) {
			throw new IllegalArgumentException("Method not allowed");
		}
		UrlShortenerRequest urlRequest = mapper.toDto(request.getBody());
		urlRequest.validate();

		String shortCode = service.shorten(urlRequest.getUrl());
		JSONObject res = new JSONObject().put("shortUrl", "https://localhost:8095/" + shortCode);
		return ResponseFactory.created(res.toString());
	}

}
