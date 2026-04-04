package com.example.app.dto.UrlShorten;

public class UrlShortenerResponse {
	private String code;

	public UrlShortenerResponse() {
	}

	public UrlShortenerResponse(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
