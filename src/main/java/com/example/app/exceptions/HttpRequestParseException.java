package com.example.app.exceptions;

public class HttpRequestParseException extends RuntimeException {
	public HttpRequestParseException(String message) {
		super(message);
	}

	public HttpRequestParseException(String message, Throwable cause) {
		super(message, cause);
	}
}
