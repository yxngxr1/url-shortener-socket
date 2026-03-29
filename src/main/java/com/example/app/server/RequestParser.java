package com.example.app.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.app.exceptions.*;

class RequestParser {
	public static HttpRequest parse(BufferedReader reader) throws IOException, HttpRequestParseException {
		StartLine startLine;
		Map<String, String> headers;
		String body;

		startLine = parseStartLine(reader);
		// System.out.println(startLine.method + startLine.path + startLine.protocol);

		headers = parseHeaders(reader);
		// headers.forEach((key, value) -> {
		// System.out.println(key + ":" + value);
		// });

		body = parseBody(reader, headers);
		// System.out.println(body);
		return new HttpRequest(startLine.method, startLine.path, startLine.protocol, headers, body);
	}

	private static String parseBody(BufferedReader reader, Map<String, String> headers) throws HttpRequestParseException {
		String contentLength = headers.get("Content-Length");
		if (contentLength == null) {
			return "";
		}

		int length;
		try {
			length = Integer.parseInt(contentLength);
		} catch (NumberFormatException e) {
			throw new HttpRequestParseException("Invalid contentLength");
		}
		char[] bodyBuf = new char[length];

		try {
			int read = reader.read(bodyBuf, 0, length);
			if (read != length) {
				throw new HttpRequestParseException("Content-Length not mathes with real body length");
			}
		} catch (IOException e) {
			throw new HttpRequestParseException("Failed to read");
		}

		return new String(bodyBuf);

	}

	private static StartLine parseStartLine(BufferedReader reader) throws HttpRequestParseException {
		String startLine;
		try {
			startLine = reader.readLine();
			if (startLine == null || startLine.length() == 0) {
				throw new HttpRequestParseException("Empty startLine");
			}
		} catch (IOException e) {
			throw new HttpRequestParseException("Failed to read");
		}
		String[] startLineArr = startLine.split(" ");

		if (startLineArr.length != 3) {
			throw new HttpRequestParseException("Invalid startLine: " + startLine);
		}

		String method = startLineArr[0];
		String path = startLineArr[1];
		String protocol = startLineArr[2];

		return new StartLine(method, path, protocol);

	}

	private static record StartLine(String method, String path, String protocol) {
	};

	private static Map<String, String> parseHeaders(BufferedReader reader) throws HttpRequestParseException {
		Map<String, String> headers = new HashMap<>();
		String header;
		try {
			while (!(header = reader.readLine()).isEmpty()) {
				int idx = header.indexOf(':');
				if (idx == -1) {
					throw new HttpRequestParseException("Invalid Header: " + header);
				}
				headers.put(header.substring(0, idx).trim(), header.substring(idx + 1, header.length()).trim());
			}
		} catch (IOException e) {
			throw new HttpRequestParseException("Failed to read");
		}
		return headers;
	}
}
