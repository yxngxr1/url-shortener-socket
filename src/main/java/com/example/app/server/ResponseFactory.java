package com.example.app.server;

import org.json.JSONObject;

public class ResponseFactory {
	public static HttpResponse ok(String body) {
		return new HttpResponse.Builder(200)
				.body(body)
				.build();
	}

	public static HttpResponse created(String body) {
		return new HttpResponse.Builder(201)
				.body(body)
				.build();
	}

	public static HttpResponse badRequest(String msg) {
		return error(400, msg);
	}

	public static HttpResponse notFound(String msg) {
		return error(404, msg);
	}

	public static HttpResponse methodNotAllowed() {
		return new HttpResponse.Builder(405).build();
	}

	public static HttpResponse serverError(String msg) {
		return error(500, msg);
	}

	private static HttpResponse error(int status, String msg) {
		JSONObject json = new JSONObject().put("message", msg);
		return new HttpResponse.Builder(status)
				.body(json.toString())
				.build();
	}
}
