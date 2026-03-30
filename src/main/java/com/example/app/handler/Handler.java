package com.example.app.handler;

import com.example.app.server.HttpRequest;
import com.example.app.server.HttpResponse;

public interface Handler {
	public HttpResponse handle(HttpRequest request);
}
