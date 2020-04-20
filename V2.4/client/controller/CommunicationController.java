package client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * 
 * this class controls the communication from the client to the server. It works very much the same way 
 * as a regular client class.
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 */
public class CommunicationController {
	/**
	 * The socket of the client
	 */
	private Socket aSocket;
	/**
	 * The socket output of the client
	 */
	private PrintWriter socketOut;
	/**
	 * The socket input of the client
	 */
	private BufferedReader socketIn;
	
	/**
	 * 
	 * @param serverName the name of the server
	 * @param portNumber the port to be used
	 */
	public CommunicationController(String serverName, int portNumber) throws Exception{
		aSocket = new Socket (serverName, portNumber);
		socketIn = new BufferedReader (new InputStreamReader (aSocket.getInputStream()));
		socketOut = new PrintWriter((aSocket.getOutputStream()), true);
	}
	/**
	 * send a message to the server.
	 * @param message the message to send.
	 */
	public void communicateToServer(String message) {
		socketOut.println(message);
	}
	/**
	 * Will take in the input, without making any changes to it.
	 * @return the input, or what it read.
	 */
	public String reciveRawInput() {
		try {
			return socketIn.readLine().replace('_', '\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(1);
		return "error!";
	}
	/**
	 * Will take in the input, and reads it as an integer.
	 * @return the integer read from socket.
	 */
	public int recieveIntegerInput() {
		try {
			return Integer.parseInt(socketIn.readLine().trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(1);
		return -1;
	}
}
