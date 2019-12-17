package dataProcess;

import java.io.IOException;
import java.net.ServerSocket;

public class StationServer {

	/**
	 * main, runs the multi threaded server can support multiple clients at once
	 * 
	 * @param args, not needed for this class
	 * @throws IOException if error occurs
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {

		// make sure args are present
		// project log file
		if (args.length != 1) {
			System.out.println("Project log not found!");
			System.exit(1);
		}

		// setup server
		int portNumber = 27000;
		boolean online = true;
		System.out.println("BTP400 Assignment 1 AssemblyLine Server");
		System.out.println("Waiting for stations. . .\n");
		while (online) {
		// waits on port listening for request.
		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
			//System.out.println("\nStation up and running!");

			new StationServerThread(serverSocket.accept(), args[0]).start();
			//System.out.println("Waiting for a client connection. . .\n");
			System.out.println("Connected on port " + portNumber);
			portNumber++; // CHange port
			Thread.sleep(5000); // Compensate for delay from Station work
				
		} catch (IOException e) {
			System.out.println("Error:" + e);
			System.exit(-1);
		}
	}
	}
}
