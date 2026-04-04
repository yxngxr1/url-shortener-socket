package com.example.app.mapper;

import org.json.JSONObject;

public interface Mapper<T> {
	T toDto(String json);

	JSONObject toJson(T dto);
}
