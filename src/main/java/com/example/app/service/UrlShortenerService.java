package com.example.app.service;

import java.security.MessageDigest;

import com.example.app.repository.UrlRepository;

import java.math.BigInteger;

public class UrlShortenerService {
	private final UrlRepository repository;

	public UrlShortenerService() {
		this.repository = new UrlRepository();
	}

	public String shorten(String longUrl) throws Exception {
		String shortCode;
		shortCode = repository.findByUrl(longUrl);
		if (shortCode != null) {
			return shortCode;
		}

		shortCode = generateShortCode(longUrl);
		repository.save(shortCode, longUrl);
		return shortCode;
	}

	private String generateShortCode(String url) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] digest = md.digest(url.getBytes());
		String hash = new BigInteger(1, digest).toString(16);
		return hash.substring(0, 8);
	}
}
