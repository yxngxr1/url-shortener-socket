package com.example.app;

import java.io.IOException;

import com.example.app.handler.HealthHandler;
import com.example.app.handler.Router;
import com.example.app.handler.UrlShortenerHandler;
import com.example.app.server.SocketServer;

public class Main {
	public static void main(String[] args) throws IOException {

		Router router = new Router();
		router.put("/health", new HealthHandler());
		router.put("/shorten", new UrlShortenerHandler());

		SocketServer server = new SocketServer(router);
		server.start();
	}
}
