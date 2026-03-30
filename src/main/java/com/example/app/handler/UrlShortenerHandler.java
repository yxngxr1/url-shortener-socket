package com.example.app.handler;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.app.server.HttpRequest;
import com.example.app.server.HttpResponse;

public class UrlShortenerHandler implements Handler {

	public HttpResponse handle(HttpRequest request) {
		switch (request.getMethod()) {
			case "POST":
				return handlePost(request);
			default:
				return methodNotAllowed(request.getMethod());
		}
	}

	private HttpResponse handlePost(HttpRequest request) {
		String body = request.getBody();
		if (body.trim().isEmpty()) {
			return getErrorResponse(400, "Request body is empty");
		}

		JSONObject requestBody;
		try {
			requestBody = new JSONObject(request.getBody());
		} catch (JSONException e) {
			return getErrorResponse(400, "Invalid json format");
		}

		if (!requestBody.has("url")) {
			return getErrorResponse(400, "Field 'url' is required");
		}

		String url;
		try {
			url = requestBody.getString("url");
		} catch (JSONException e) {
			return getErrorResponse(400, "Field 'url' must be a string");
		}

		if (url == null || url.trim().isEmpty()) {
			return getErrorResponse(400, "Field 'url' cannot be empty");
		}

		if (!isValidUrl(url)) {
			return getErrorResponse(400, "Invalid URL format");
		}

		JSONObject responseBody = new JSONObject();
		responseBody.put("shortUrl", url);

		return new HttpResponse(201, responseBody.toString());

	}

	private HttpResponse methodNotAllowed(String method) {
		return new HttpResponse(405);
	}

	private HttpResponse getErrorResponse(int status, String message) {
		JSONObject responseBody = new JSONObject();
		responseBody.put("message", message);
		return new HttpResponse(status, responseBody.toString());

	}

	private boolean isValidUrl(String url) {
		if (url == null)
			return false;

		if (!url.startsWith("http://") && !url.startsWith("https://")) {
			return false;
		}

		String withoutProtocol = url.replace("http://", "").replace("https://", "");
		if (!withoutProtocol.contains(".")) {
			return false;
		}

		if (url.contains(" ")) {
			return false;
		}

		return true;
	}

}
