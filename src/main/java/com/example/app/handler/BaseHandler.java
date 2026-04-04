package com.example.app.handler;

import org.json.JSONException;

import com.example.app.server.HttpRequest;
import com.example.app.server.HttpResponse;
import com.example.app.server.ResponseFactory;

public abstract class BaseHandler implements Handler {

	@Override
	public final HttpResponse handle(HttpRequest request) {
		try {
			return doHandle(request);
		} catch (IllegalArgumentException e) {
			return ResponseFactory.badRequest(e.getMessage());
		} catch (JSONException e) {
			return ResponseFactory.badRequest("Json exception: " + e.getMessage());
		} catch (RuntimeException e) {
			return ResponseFactory.serverError(e.getMessage());
		} catch (Exception e) {
			return ResponseFactory.serverError(e.getMessage());
		}
	}

	protected abstract HttpResponse doHandle(HttpRequest request) throws Exception;
}
