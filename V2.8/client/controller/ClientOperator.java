package client.controller;

import java.io.IOException;

/**
 * This class contains the main method to launch the client side of the 
 * Course administration program
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 */
public class ClientOperator {
	/**
	 * the main method.
	 * @param args the command line argument sent to the program
	 * @throws IOException the IOException to be thrown
	 */
	public static void main (String [] args) throws IOException{
		//Atempt to create communicator object
		CommunicationController communicator;
		do {
			try {
				communicator = new CommunicationController ("localhost", 9898);
			} catch (Exception e) {
				System.err.println("Waiting for a server to connect!");
				communicator=null;
			}
		} while (communicator == null);
		
		GUIController controller = new GUIController(communicator);
		
		controller.startClient();
	}
}
