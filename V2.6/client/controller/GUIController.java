package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import client.view.*;
/**
 * 
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 * this is the Gui controller, it deals with all the event listeners for the buttons. 
 */
public class GUIController{
	@SuppressWarnings("unused")
	private CommunicationController communicator;
	private GUI gui;
	private LoginScreen loginScreen;
	
	/**
	 * This is the constructor for the Gui controller, it has all the action listeners for the buttions 
	 * on the Gui home page, we were going to make separate functions for each of the actions in the listeners
	 * but decided to keep it like it is for now. It is hard to read, so for the next mile stone we will try to clean
	 * it up.
	 * @param gui the Gui.
	 * @param communicator The communication communicator.
	 */
	public GUIController (CommunicationController communicator) {
		this.communicator = communicator;
		loginScreen = new LoginScreen();
		gui = new GUI();
		
		loginScreen.addLoginButtonListener((ActionEvent e) ->{
			String username =loginScreen.getUserResult();
			String password =loginScreen.getPasswordResult();
			
			int result;
			if (username.length()==0) {
				result = -1;
			} else if (password.length()==0) {
				result= -1;
			} else {
				communicator.communicateToServer(username);
				communicator.communicateToServer(password);
				result = communicator.recieveIntegerInput();
			}
			
			
			
			if (result==0) {
				//User is normal user
				loginScreen.close();
				gui.displayMenu();
			} else if (result ==1) {
				//User is admin
				AddNewUser newGuy = new AddNewUser();
				loginScreen.close();
				newGuy.display();
				newGuy.addNewUserButtonListener((ActionEvent f) ->{
						MakeNewUser newUser = new MakeNewUser();
						newUser.display();
						newUser.addFinishButtonListener((ActionEvent g) ->{
							//1 indicates adding
							communicator.communicateToServer("1");
							
							
							communicator.communicateToServer(newUser.getUserResult());
							communicator.communicateToServer(newUser.getPasswordResult());
							
							int outcome = communicator.recieveIntegerInput();
							
							if (outcome ==0) {
								//Successfully created
								JOptionPane.showMessageDialog(null, "User has been added!");
							} else if (outcome ==1) {
								//Username is already used
								JOptionPane.showMessageDialog(null, "User is already used!", "Error", JOptionPane.ERROR_MESSAGE);
							} else {
								//Password too short
								JOptionPane.showMessageDialog(null, "Password was too short!", "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						});
						newUser.addBackButtonListener((ActionEvent g) ->{
							newUser.close();
						});
				});
				newGuy.addConButtonListener((ActionEvent f) ->{
					communicator.communicateToServer("0");
					newGuy.close();
					gui.displayMenu();
				});
				//gui.displayMenu();
			} else {
				JOptionPane.showMessageDialog(null, "User or password are incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
		});
		loginScreen.addCancelButtonListener((ActionEvent e) ->{
			loginScreen.dispatchEvent(new WindowEvent(loginScreen, WindowEvent.WINDOW_CLOSING));
			System.exit(0);
		});
		
		
		
		
		
		/**
		 * this is the listeners for the search catalogue button, it will deal whatever happens after the 
		 * button is pressed, which in this case is what is will allow the user to input the name and id 
		 * of the course they are looking for, and give them the appropriate answer.
		 */
		gui.addSearchCatalogueListener((ActionEvent e) ->{
			SearchCat search = new SearchCat();
			
			if (search.getResult() != JOptionPane.CANCEL_OPTION&&search.getResult() != JOptionPane.CLOSED_OPTION) {
				//Check if course exist
				communicator.communicateToServer("6=1="+search.getCourseName()+"="+search.getCourseID());
				
				if (communicator.recieveIntegerInput()==1) {
					//Course DO NOT EXIST
					JOptionPane.showMessageDialog(null, "Course cannot be found", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					communicator.communicateToServer("1="+search.getCourseName()+"="+search.getCourseID());
					JOptionPane.showMessageDialog(null, communicator.reciveRawInput());
				}
	      	}  
		});
		/**
		 * this is the listener for the add course to student, it will open a new window, in which it will as the 
		 * user to enter the same of the student that they which to give a course, if the student exists, then they will 
		 * be given the option of what course to give to the student.
		 */
		gui.addAddCourseListener((ActionEvent e) ->{
			communicator.communicateToServer("5");
			
			ChooseStudent stu = new ChooseStudent(communicator.reciveRawInput());
			
			stu.addContinueListener((ActionEvent f) ->{
				communicator.communicateToServer("6=2="+stu.getStuName());
				int result = communicator.recieveIntegerInput();
				if (result == 0) {
					communicator.communicateToServer("6=3="+stu.getStuName());
					result = communicator.recieveIntegerInput();
					if (result ==0) {
						ChooseCourse course = new ChooseCourse(1);
						course.addContinueListener((ActionEvent d) ->{
							String studentName = stu.getStuName();
							String courseName = course.getCName();
							String courseID = course.getCId();
							String sectionID = course.getSecNum();
							if (courseName.equals("")) {
								courseName = "-+-";
							}
							if (courseID.equals("")) {
								courseID = "-1";
							}
							if (sectionID.equals("")) {
								sectionID= "-1";
							}
							
							communicator.communicateToServer("6=5="+studentName+"="+courseName+"="+courseID+"="+sectionID);
							int response = communicator.recieveIntegerInput();
							if (response == 0) {
								communicator.communicateToServer("2="+studentName+"="+courseName+"="+courseID+"="+sectionID);
								JOptionPane.showMessageDialog(null, "Course successfully added!");
								
								communicator.communicateToServer("5");
								stu.setConsole(communicator.reciveRawInput());
							} else if (response == 1) {
								JOptionPane.showMessageDialog(null, "Course cannot be found!", "Error", JOptionPane.ERROR_MESSAGE);
							} else if (response == 2) {
								JOptionPane.showMessageDialog(null, "Section is not available", "Error", JOptionPane.ERROR_MESSAGE);
							} else if (response == 3) {
								JOptionPane.showMessageDialog(null, "Student is already in the course", "Error", JOptionPane.ERROR_MESSAGE);
							}
						    course.dispatchEvent(new WindowEvent(course, WindowEvent.WINDOW_CLOSING));
						    
						});
						course.addBackListener((ActionEvent d) ->{
							course.dispatchEvent(new WindowEvent(course, WindowEvent.WINDOW_CLOSING));
						});
					} else {
						JOptionPane.showMessageDialog(null, "Student can have a maximum of 6 courses!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Student cannot be found", "Error", JOptionPane.ERROR_MESSAGE);
				}
			});
		});
		/**
		 * this is the listener for the remove course from student button, it uses the exact same frames
		 * as the add course to student button, except that this will remove rather than add the course 
		 * from the student, granted they are enrolled.
		 */
		gui.addRemoveCourseListener((ActionEvent e) ->{
			communicator.communicateToServer("5");
			
			ChooseStudent stu = new ChooseStudent(communicator.reciveRawInput());
			stu.addContinueListener((ActionEvent f) ->{
				communicator.communicateToServer("6=2="+stu.getStuName());
				int result = communicator.recieveIntegerInput();
				if (result == 0) {
					ChooseCourse course = new ChooseCourse(0);
					
					course.addContinueListener((ActionEvent d) ->{
						String studentName = stu.getStuName();
						String courseName = course.getCName();
						String courseID = course.getCId();
						
						communicator.communicateToServer("6=1="+courseName+"="+courseID);
						
						if (communicator.recieveIntegerInput()==0) {
							communicator.communicateToServer("3="+studentName+"="+courseName+"="+courseID);
							
							int response = communicator.recieveIntegerInput();
							if (response == 0) {
								JOptionPane.showMessageDialog(null, "Course successfully removed!");
								
								communicator.communicateToServer("5");
								stu.setConsole(communicator.reciveRawInput());
							} else if (response == 1) {
								JOptionPane.showMessageDialog(null, "Course was not taken by student!", "Error", JOptionPane.ERROR_MESSAGE);
							} 
						    course.dispatchEvent(new WindowEvent(course, WindowEvent.WINDOW_CLOSING));
						} else {
							JOptionPane.showMessageDialog(null, "Course cannot be found", "Error", JOptionPane.ERROR_MESSAGE);
						}
						
					});
					course.addBackListener((ActionEvent d) ->{
						course.dispatchEvent(new WindowEvent(course, WindowEvent.WINDOW_CLOSING));
					});
				} else {
					JOptionPane.showMessageDialog(null, "Student cannot be found", "Error", JOptionPane.ERROR_MESSAGE);
				}
			});

		});
		/**
		 * will open a frame that will display the course Catalogue.
		 */
		gui.addViewCourseCatalogueListener((ActionEvent e) ->{
			communicator.communicateToServer("4");
			@SuppressWarnings("unused")
			ViewCourseCat viewCat = new ViewCourseCat(communicator.reciveRawInput());
		});
		/**
		 * this will open a frame that will display the student, and the courses that 
		 * they are taking.
		 */
		gui.addviewCourseTakenListener((ActionEvent e) ->{
			communicator.communicateToServer("5");
			@SuppressWarnings("unused")
			ViewCourseTaken viewTaken = new ViewCourseTaken(communicator.reciveRawInput());
		});
	}
	
	public void setGUI(GUI gui) {
		this.gui = gui;
	}
	
	public void startClient() {
		loginScreen.display();
	}
	
	
	
}
