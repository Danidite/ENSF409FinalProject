package server.controller;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is used to read from the database and put them into
 * a format in which the other codes would understant
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 */
public class SQLJDBWriter implements IDBCredentials {
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
	 * Remove a student's registration to a course from database
	 * @param studentName the full name of a student
	 * @param courseName the course name
	 * @param courseID the course id
	 */
	public void removeStudentCourseFromDB(String studentName, String courseName, int courseID) {
		Statement myStmt;
		
		try {
			myStmt = conn.createStatement();
			
			String delete = "DELETE FROM student_courses WHERE id = '"+studentName+courseName+courseID+"'";
			myStmt.executeUpdate(delete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add a student course registration to the database
	 * @param studentName the name of the student
	 * @param courseName the name of the course
	 * @param courseID the id of the course
	 * @param sectionID the section id of the course
	 */
	public void addStudentCourseToDB(String studentName, String courseName, int courseID, int sectionID) {
		
		try {
			String query = "INSERT INTO student_courses (ID, student_name, course_name, course_ID, section_ID) values(?,?,?,?,?)";
			
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setString(1, studentName+courseName+courseID);
			pStat.setString(2, studentName);
			pStat.setString(3, courseName);
			pStat.setInt(4, courseID);
			pStat.setInt(5, sectionID);
			pStat.executeUpdate();
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}