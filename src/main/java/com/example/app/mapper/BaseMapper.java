package com.example.app.mapper;

import org.json.JSONObject;

public abstract class BaseMapper<T> implements Mapper<T> {

	@Override
	public T toDto(String httpBody) {
		if (httpBody == null || httpBody.trim().isEmpty()) {
			throw new IllegalArgumentException("Request body is empty");
		}

		JSONObject json = new JSONObject(httpBody);
		if (json == null || json.isEmpty()) {
			throw new IllegalArgumentException("JSON body is empty");
		}

		return doToDto(json);
	}

	@Override
	public JSONObject toJson(T dto) {
		if (dto == null) {
			throw new IllegalArgumentException("DTO cannot be null");
		}
		return doToJson(dto);
	}

	public abstract T doToDto(JSONObject json);

	public abstract JSONObject doToJson(T dto);
}
