package com.example.app;

import java.io.IOException;

import com.example.app.handler.HealthHandler;
import com.example.app.handler.RedirectHandler;
import com.example.app.handler.Router;
import com.example.app.handler.UrlShortenerHandler;
import com.example.app.server.SocketServer;
import com.example.app.database.*;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
	public static void main(String[] args) throws IOException {
		Dotenv dotenv = Dotenv.load();

		DatabaseConfig config = new DatabaseConfig(dotenv);
		DatabaseMigrator.migrate(config);

		ConnectionProvider provider = new HikariCPConnectionProvider(config);
		// ConnectionProvider provider = new SimpleConnectionProvider(config);
		// ConnectionProvider provider = new SimplePoolConnectionProvider(config);
		Connections.configure(provider);

		Router router = new Router();
		router.put("/health", new HealthHandler());
		router.put("/shorten", new UrlShortenerHandler());
		router.put("/redirect", new RedirectHandler());

		SocketServer server = new SocketServer(router);
		server.start();
	}
}
