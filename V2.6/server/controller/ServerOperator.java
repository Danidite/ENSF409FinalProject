package server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class contains the main method to launch the server side of the 
 * Course administration program
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 */
public class ServerOperator {
	/**
	 * The Server socket
	 */
	private ServerSocket serverSocket;
	/**
	 * The thread pool of the Server
	 */
	private ExecutorService pool;
	
	/**
	 * Create a instance of the Server with the specified port.
	 * @param port the port of the new Server
	 */
	public ServerOperator(int port) {
		try {
			serverSocket = new ServerSocket(port);
			pool = Executors.newCachedThreadPool();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Wait for client to connect!
	 * @throws IOException the IOException to be thrown
	 */
	public void communicateWithClient() throws IOException {
		System.out.println("Client may now connect");
		System.out.println();
		try {
			while (true) {
				
				ServerCommunicationController communicator = new ServerCommunicationController(serverSocket.accept());
				
				DBController controller = new DBController(communicator);
				
				pool.execute(controller);
			}
		} catch (Exception e){
			System.err.println("Exception Caught! "+ e);
			pool.shutdown();
		}
	}
	
	
	/**
	 * The main function which will initialize a instance of the Server
	 * with predefined functions and establish communication with
	 * multiple clients.
	 * @param args the command line argument sent to the program
	 * @throws IOException the IOException to be thrown
	 */
	public static void main (String [] args) throws IOException{
		ServerOperator ServerOperator = new ServerOperator (9898);
		System.out.println("Server is now running");
		ServerOperator.communicateWithClient();
		System.out.println("\nServer is now closed, all connections terminated");
	}
}
