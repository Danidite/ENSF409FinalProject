package server.controller;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.model.Model;

/**
 * This class is used to read from the database and put them into
 * a format in which the other codes would understant
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 */
public class SQLJDBReader implements IDBCredentials {
	/**
	 * The connection to the database
	 */
	private Connection conn;

	/**
	 * Establish communication with the database
	 */
	public void initializeConnection() {
		try {
			// Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Problem");
			e.printStackTrace();
		}
	}
	
	/**
	 * Close or exit communication with the database
	 */
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add everything relevant in the database to the model
	 * @param model the model of the data
	 */
	public void getAllModelData(Model model) {
		try {
			readStudentList(model);
			readCourseList(model);
			readCourseOfferingList(model);
			readStudentCourses(model);
		} catch (SQLException e) {
			System.err.println("Error in reading database!");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Read the student list from the database and put all of them into the model
	 * @param model the model of the data
	 * @throws SQLException
	 */
	private void readStudentList(Model model) throws SQLException {
		Statement myStmt = conn.createStatement();
		ResultSet myRs = myStmt.executeQuery("select * from student");
		
		while (myRs.next()) {
			model.addStudent(myRs.getInt("id"), myRs.getString("first_name"),  myRs.getString("last_name"));
		}
	}
	
	/**
	 * Read the course list from the database and put it all into the model
	 * @param model the model of the data
	 * @throws SQLException
	 */
	private void readCourseList(Model model) throws SQLException {
		Statement myStmt = conn.createStatement();
		ResultSet myRs = myStmt.executeQuery("select * from course");
		
		while (myRs.next()) {
			model.addCourse(myRs.getString("course_name"), myRs.getInt("course_id"));
		}
	}
	
	/**
	 * Read the course offering list from the database and put it all into the model
	 * @param model the model of the data
	 * @throws SQLException
	 */
	private void readCourseOfferingList(Model model) throws SQLException {
		Statement myStmt = conn.createStatement();
		ResultSet myRs = myStmt.executeQuery("select * from course_offering");
		
		while (myRs.next()) {
			model.addCourseOffering(myRs.getString("course_name"), myRs.getInt("course_id"), myRs.getInt("section_id"), myRs.getInt("section_capacity"));
		}
	}
	
	/**
	 * Read the student course registration list and put it all into the model
	 * @param model the model of the data
	 * @throws SQLException
	 */
	public void readStudentCourses(Model model) {
		try {
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from student_courses");
			
			while (myRs.next()) {
				model.addCourse(myRs.getString("student_name"), myRs.getString("course_name"), myRs.getInt("course_id"), myRs.getInt("section_id"));
			}
		} catch (SQLException e) {
			System.out.println("Error in adding student course registration list ot database");
			e.printStackTrace();
			System.exit(1);
		}
	}
}
