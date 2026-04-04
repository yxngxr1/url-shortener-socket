package com.example.app.dto.UrlShorten;

import com.example.app.dto.ValidatableDto;

public class UrlShortenerRequest implements ValidatableDto {
	private String url;

	public UrlShortenerRequest() {
	}

	public UrlShortenerRequest(String url) {
		this.url = url;
	}

	@Override
	public void validate() {
		if (url == null || url.trim().isEmpty() ||
				(!url.startsWith("http://") && !url.startsWith("https://"))) {
			throw new IllegalArgumentException("Invalid URL format");
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
