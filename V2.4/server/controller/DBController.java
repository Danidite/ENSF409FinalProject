package server.controller;

import java.util.ArrayList;

import server.model.Model;
/**
 * this class is the controller for the server side(data base controller). It deals mostly with communication with the client 
 * program, and what to do with the client input.
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 */
public class DBController implements Runnable {
	/**
	 * The server communicator used to communicate with the client
	 */
	private ServerCommunicationController serverCommunicator;
	
	/**
	 * The local storage for all the classes and their values
	 */
	private Model model;
	
	/**
	 * Contructs a DBController with the input ServerCommunicatorController
	 * @param serverCommunicator
	 */
	public DBController (ServerCommunicationController serverCommunicator) {
		this.serverCommunicator = serverCommunicator;
		
	}
	/**
	 * Makes the connection with the actual server.
	 */
	private void startServer() {
		readFromDatabase();
		System.out.println("A instance has Started!");
		
		serverCommunicator.establishConnection();
		
		askForCredencials();
		
		//Signals GUI TO DISPLAY (CAN BE ANY INTEGER)
		//serverCommunicator.communicateToClient("0");
		
		operate();
	}
	
	/**
	 * Ask client for their login credentials
	 */
	private void askForCredencials() {
		String result = "2";
		do {
			String userName = serverCommunicator.reciveRawInput();
			String password = serverCommunicator.reciveRawInput();
			
			if (userName==null || password==null) {
				result = "2";
			} else {
				result = atemptLogin(userName, password);
			}
			
			serverCommunicator.communicateToClient(result);
		} while (result.equals("2"));
	}
	
	/**
	 * This function atemps to login and it will return 0 or 1 if successfully login
	 * and it will return 2 if failed to do so
	 * @param username the username to be login
	 * @param password the password of the login
	 * @return the result of the atempt
	 */
	private String atemptLogin(String username, String password) {
		SQLJDBUserManager manager = new SQLJDBUserManager();
		manager.initializeConnection();
		int result = manager.validateLogin(username, password);
		if (result ==0) {
			//Normal user
			return "0";
		} if (result ==1) {
			//User is admin
			return "1";
		} else {
			//Login Failed, try again
			return "2";
		}
	}
	
	/**
	 * This class atemps to create a new account with the
	 * input username and password, it will return 0 if successfully 
	 * created 1 if username is already used and 2 if password is too short.
	 * @param username the username of the new account 
	 * @param password the pasword of the new account
	 * @return the result of the atempt
	 */
	private String createAccount(String username, String password) {
		SQLJDBUserManager manager = new SQLJDBUserManager();
		manager.initializeConnection();
		int result = manager.createAccount(username, password);
		if (result ==0) {
			//Successfully created
			return "0";
		} if (result ==1) {
			//Username is already used
			return "1";
		} else {
			//Password too short
			return "2";
		}
	}
	
	/**
	 * This function will read everything from the database,
	 * this will override all data stored
	 */
	private void readFromDatabase() {
		this.model = new Model();
		SQLJDBReader reader = new SQLJDBReader();
		reader.initializeConnection();
		reader.getAllModelData(model);
		reader.close();
	}
	
	/**
	 * this function starts the communication with the client and server, taking the input and translating
	 * them to the server so that the sever knows what to do.
	 */
	private void operate() {
		while (true) {
			ArrayList<String> data = serverCommunicator.reciveInput();
			
			int choice;
			
			if (data!=null) {
				choice = Integer.parseInt(data.get(0));
			} else {
				choice = 0;
			}
			
			switch (choice) {
			case 1:
				//Search catalogue
				serverCommunicator.communicateToClient(model.searchCatalogue(data.get(1), Integer.parseInt(data.get(2))));
				break;
			case 2:
				//Add course
				String studentName = model.addCourse(data.get(1),data.get(2),Integer.parseInt(data.get(3)),Integer.parseInt(data.get(4)));
				//addStudentCourseToDB();
				
				addStudentCourseToDB(studentName,data.get(2),Integer.parseInt(data.get(3)),Integer.parseInt(data.get(4)));
				break;
			case 3:
				//Remove course
				serverCommunicator.communicateToClient(model.removeCourse(data.get(1), data.get(2),Integer.parseInt(data.get(3))));
				removeStudentCourseFromDB(model.getStudentFullName(data.get(1)), data.get(2),Integer.parseInt(data.get(3)));
				break;
			case 4:
				//View Course Catalogue/List
				serverCommunicator.communicateToClient(model.viewCourseCatalogue());
				break;
			case 5:
				//View Course taken (Student: what course they took) Basically student list
				serverCommunicator.communicateToClient(model.viewCourseTaken());
				break;
			case 6:
				//Information request
				switch (Integer.parseInt(data.get(1))) {
				case 1:
					if (data.size()<4) {
						serverCommunicator.communicateToClient("1");
						break;
					}
					serverCommunicator.communicateToClient(model.courseExist(data.get(2), Integer.parseInt(data.get(3))));
					break;
				case 2:
					if (data.size()<3) {
						serverCommunicator.communicateToClient("1");
						break;
					}
					serverCommunicator.communicateToClient(model.studentExist(data.get(2)));
					break;
				case 3:
					if (data.size()<3) {
						serverCommunicator.communicateToClient("1");
						break;
					}
					serverCommunicator.communicateToClient(model.studentCanEnroll(data.get(2)));
					break;
				case 4:
					//REMOVED CODE
					break;
				case 5:
					serverCommunicator.communicateToClient(model.canEnroll(data.get(2), data.get(3), Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))));
					break;
				}
				break;
			default:
				//closes server if input is none of the above
				System.out.println("A instance has Closed.");
				return;
			}
			readFromDatabase();
		}
	}
	
	/**
	 * Remove a student's course registration from the database
	 * @param studentName the full name of the student
	 * @param courseName the name of the course
	 * @param courseID the id of the course
	 */
	private void removeStudentCourseFromDB(String studentName, String courseName, int courseID) {
		SQLJDBWriter writer = new SQLJDBWriter();
		writer.initializeConnection();
		writer.removeStudentCourseFromDB(studentName.toLowerCase(), courseName.toLowerCase(), courseID);
		writer.close();
	}
	
	/**
	 * Add a student's course registration to the databse
	 * @param studentName the full name of the student
	 * @param courseName the name of the course
	 * @param courseID the id of the course
	 * @param sectionID the section id
	 */
	private void addStudentCourseToDB(String studentName, String courseName, int courseID, int sectionID) {
		SQLJDBWriter writer = new SQLJDBWriter();
		writer.initializeConnection();
		writer.addStudentCourseToDB(studentName.toLowerCase(), courseName.toLowerCase(), courseID, sectionID);
		writer.close();
	}
	
	/**
	 * Runs the program
	 */
	@Override
	public void run() {
		startServer();
	}
}
