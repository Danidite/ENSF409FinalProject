package server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
/**
 * this is the server class, is built much like a traditional server.
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 *
 */
public class ServerCommunicationController {
	/**
	 * The socket of an client
	 */
	private Socket aSocket;
	/**
	 * The socket out of the server sent to a client
	 */
	private PrintWriter socketOut;
	/**
	 * The socket Input of the server
	 */
	private BufferedReader socketIn;
	
	/**
	 * Create a instance of the server with the specified port.
	 * @param port the port of the new server
	 */
	ServerCommunicationController(Socket aSocket) {
		this.aSocket = aSocket;
	}
	
	/**
	 * establishes a connection with sockets, to the client
	 */
	public void establishConnection() {
		try {
			socketIn = new BufferedReader (new InputStreamReader (aSocket.getInputStream()));
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
		}catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * communicates with the client.
	 * @param message prints this message to the socket.
	 */
	public void communicateToClient(String message) {
		socketOut.println(message.replace('\n', '_'));
	}
	/**
	 * just recieves input and calls processinput.
	 * @return
	 */
	public ArrayList<String> reciveInput() {
		while (true) {
			try {
				String input = socketIn.readLine();
				return processInput(input);
			} catch (IOException e) {
				//System.err.println("Client Disconnected");
				return null;
			}
		}
	}
	/**
	 * it will receive and input and transforms them into individual String arrays
	 * @param input
	 * @return
	 */
	private ArrayList<String> processInput(String input) {
		ArrayList<String> data = new ArrayList<String>();
		String[] text = input.split("=");
		for (int i=0; i < text.length; i++) {
			data.add(text[i]);
		}
		return data;
	}
	
	/**
	 * Return the raw string input recieved
	 * @return the raw string input
	 */
	public String reciveRawInput() {
		try {
			return socketIn.readLine();
		} catch (IOException e) {
		}
		return null;
	}
}
