package com.example.app;

import java.io.IOException;
import com.example.app.server.SocketServer;

public class Main {
	public static void main(String[] args) throws IOException {
		SocketServer server = new SocketServer();
		server.start();
	}
}
