package com.example.app.exceptions;

public class HttpRequestParseException extends Exception {
	public HttpRequestParseException(String message) {
		super(message);
	}

	public HttpRequestParseException(String message, Throwable cause) {
		super(message, cause);
	}
}
