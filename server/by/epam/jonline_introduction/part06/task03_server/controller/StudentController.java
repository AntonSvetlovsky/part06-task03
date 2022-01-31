package by.epam.jonline_introduction.part06.task03_server.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import by.epam.jonline_introduction.part06.task03_server.bean.Response;

public class StudentController {

	private boolean flag = true;
	private final CommandProvider provider = new CommandProvider();

	private class ClientHandler implements Runnable {

		private Socket socket;

		public ClientHandler(ServerSocket server) {
			try {
				this.socket = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

				String request = reader.readLine();
				System.out.println(request);// test
				String response = doAction(request);
				if (response.equals(Response.STOP_SERVER.toString())) {
					flag = false;
				}
				writer.write(response);
				System.out.println(response);// test
				writer.newLine();
				writer.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	public String doAction(String request) {
		String[] params = new String[2];
		String[] tmpArray;
		String commandName;
		String response;
		Command currentCommand;

		request = request.trim();
		tmpArray = request.split("\\|", 2);
		for (int i = 0; i < tmpArray.length; i++) {
			params[i] = tmpArray[i].trim();
		}
		commandName = params[0];
		currentCommand = provider.getCommand(commandName);

		if (currentCommand == null) {
			response = Response.ERROR.toString();
			return response;
		}

		response = currentCommand.execute(params[1]);

		return response;
	}

	public void go() {

		try (ServerSocket server = new ServerSocket(8000)) {

			while (flag) {

				Thread thread = new Thread(new ClientHandler(server));
				thread.start();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
