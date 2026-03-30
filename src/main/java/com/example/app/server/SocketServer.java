package com.example.app.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.app.handler.Handler;
import com.example.app.handler.Router;

public class SocketServer {
	private final int PORT = 8095;
	private final ExecutorService execcutor = Executors.newFixedThreadPool(1);
	private final Router router;

	public SocketServer(Router router) {
		this.router = router;
	}

	public void start() throws IOException {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server start on address: " + serverSocket.getInetAddress().getHostAddress() + ":" + PORT);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				execcutor.submit(() -> handleClient(clientSocket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleClient(Socket clientSocket) {
		try (clientSocket;
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
			System.out.println(clientSocket.getPort());

			HttpRequest request = RequestParser.parse(reader);

			Handler handler = router.get(request.getPath());
			HttpResponse response = handler.handle(request);

			writer.write(response.build());
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
