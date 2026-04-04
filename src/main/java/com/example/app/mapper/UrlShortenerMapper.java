package com.example.app.mapper;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.app.dto.UrlShorten.UrlShortenerRequest;

public class UrlShortenerMapper extends BaseMapper<UrlShortenerRequest> {
	@Override
	public UrlShortenerRequest doToDto(JSONObject json) {
		UrlShortenerRequest urlReq = new UrlShortenerRequest();

		if (!json.has("url")) {
			throw new IllegalArgumentException("Field 'url' is required");
		}

		String url;
		try {
			url = json.getString("url");
		} catch (JSONException e) {
			throw new IllegalArgumentException("Field 'url' must be a string");
		}

		if (url == null || url.trim().isEmpty()) {
			throw new IllegalArgumentException("Field 'url' cannot be empty");
		}

		urlReq.setUrl(url);
		return urlReq;
	}

	@Override
	public JSONObject doToJson(UrlShortenerRequest dto) {
		return new JSONObject();
	}
}
