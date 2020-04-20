import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import server.controller.IDBCredentials;

/**
 * This class Sets up the database, the tables and all
 * the initial values used in the course registration system.
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 */
public class setUpDatabase implements IDBCredentials {
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
			// rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create a new data table in the database to represent the list of students
	 */
	public void createStudentTable() {
		String sql = "CREATE TABLE STUDENT " + "(id INTEGER not NULL, " + " first_name VARCHAR(255), "
				+ " last_name VARCHAR(255), " + " PRIMARY KEY ( id ))";

		try {
			Statement stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Student table can NOT be created!");
		}
		System.out.println("Student table in given database...");
	}
	
	/**
	 * Create a new data table in the database to represent the list of courses
	 */
	public void createCourseTable() {
		String sql = "CREATE TABLE COURSE " + "(id INTEGER not NULL, " + " course_name VARCHAR(255), "
				+ " course_id INTEGER, " + " PRIMARY KEY ( id ))";

		try {
			Statement stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Course table can NOT be created!");
		}
		System.out.println("Course table in given database...");
	}
	
	/**
	 * Create a new data table in the database to represent the list of course offerings
	 */
	public void createCourseOfferingTable() {
		String sql = "CREATE TABLE COURSE_OFFERING " + "(id INTEGER not NULL, " + " course_name VARCHAR(255), "+ " course_id INTEGER, "
				+ " section_id INTEGER, " + " section_capacity INTEGER, " + " PRIMARY KEY ( id ))";

		try {
			Statement stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Course offering table can NOT be created!");
		}
		System.out.println("Course offering table in given database...");
	}
	
	/**
	 * Create a new data table in the database to represent the courses a student is enrolled in
	 */
	private void createStudentCourses() {
		String sql = "CREATE TABLE student_courses " + "(id VARCHAR(255) not NULL, " + " student_name VARCHAR(255), "
				+ " course_name VARCHAR(255), " + " course_ID INTEGER, " +" section_ID INTEGER, "
				+ " PRIMARY KEY ( id ))";
		try {
			Statement stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Student Courses table can NOT be created!");
		}
		System.out.println("Student Courses created in given database...");
	}
	
	/**
	 * Create a new data table in the database that stores user login and credentials
	 */	
	private void createAccountList() {
		String sql = "CREATE TABLE users " + "(username VARCHAR(255) not NULL, " + " password VARCHAR(255), "
				+ " permission INTEGER, " + " PRIMARY KEY ( username ))";
		
		try {
			Statement stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("User table can NOT be created!");
		}
		System.out.println("User table created in given database...");
	}
	
	/**
	 * Inserts a student to the student data table
	 * @param id the id of the student
	 * @param fName the first name of the student
	 * @param lName the last name of the student
	 */
	public void insertStudent(int id, String fName, String lName) {
		try {
			String query = "INSERT INTO STUDENT (ID,first_name , last_name) values(?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, fName);
			pStat.setString(3, lName);
			int rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
			pStat.close();
		} catch (SQLException e) {
			System.out.println("problem inserting user");
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserts a course in the course list data table
	 * @param id the id of the course
	 * @param courseName the name of the course
	 * @param courseID the id of the course
	 */
	public void insertCourse(int id, String courseName, int courseID) {
		try {
			String query = "INSERT INTO COURSE (ID, course_name , course_id) values(?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, courseName);
			pStat.setInt(3, courseID);
			int rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
			pStat.close();
		} catch (SQLException e) {
			System.out.println("problem inserting course");
			e.printStackTrace();
		}
	}
	
	/**
	 * Insert a course offering to the course offering data table
	 * @param id the id of the course offering
	 * @param courseName the name of the course
	 * @param courseID the id of the course
	 * @param sectionID the section id of the course offering
	 * @param sectionCapacity the capacity of the section
	 */
	public void insertCourseOffering(int id, String courseName, int courseID, int sectionID, int sectionCapacity) {
		try {
			String query = "INSERT INTO COURSE_OFFERING (ID, course_name , course_id, section_id, section_capacity) values(?,?,?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, courseName);
			pStat.setInt(3, courseID);
			pStat.setInt(4, sectionID);
			pStat.setInt(5, sectionCapacity);
			int rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
			pStat.close();
		} catch (SQLException e) {
			System.out.println("problem inserting course offering");
			e.printStackTrace();
		}
	}
	
	/**
	 * Create a new account with specified permission level 0=normal user, 1=admin
	 * @param permissionLevel the permission level of the new account
	 * @param username the username of the new account
	 * @param password the password of the new account
	 */
	public void createAccount(int permissionLevel, String username, String password) {
		try {
			String query= "INSERT INTO users (username, password, permission) values(?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			//0 = normal user
			//1 = admin
			pStat.setString(1, username);
			pStat.setString(2, password);
			pStat.setInt(3, permissionLevel);
			int rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
		} catch (SQLException e) {
			//Failed to add user
			System.err.println("Failed to add account");
			e.printStackTrace();
		}
	}
	
	/**
	 * This function calls all the functions, it creates all the necessary
	 * data tables and then puts in the list of predefined values for each table
	 * @param args the argument of the program
	 */
	public static void main(String[] args) {
		setUpDatabase myApp = new setUpDatabase();
		myApp.initializeConnection();
		myApp.createStudentTable();
		myApp.createCourseTable();
		myApp.createCourseOfferingTable();
		myApp.createStudentCourses();
		myApp.createAccountList();
		
		//Inserting student
		myApp.insertStudent(1, "Sara", "Johnson");
		myApp.insertStudent(2, "Victoria", "Young");
		myApp.insertStudent(3, "John", "Smith");
		myApp.insertStudent(4, "Boris", "Luckerson");
		myApp.insertStudent(5, "Justin", "Obsira");
		myApp.insertStudent(6, "Macdonald", "Donaldsing");
		myApp.insertStudent(7, "Adam", "Vinjatson");
		myApp.insertStudent(8, "David", "Edwardson");
		myApp.insertStudent(9, "Richard", "Richardson");
		myApp.insertStudent(10, "Hashir", "Pandragon");		
		
		//Inserting courses and their offerings
		myApp.insertCourse(1, "ENGG", 233);
		myApp.insertCourseOffering(1, "ENGG", 233, 1, 34);
		myApp.insertCourseOffering(2, "ENGG", 233, 2, 38);
		
		myApp.insertCourse(2, "ENSF", 409);
		myApp.insertCourseOffering(3, "ENSF", 409, 1, 42);
		myApp.insertCourseOffering(4, "ENSF", 409, 2, 61);
		
		myApp.insertCourse(3, "PHYS", 259);
		myApp.insertCourseOffering(5, "PHYS", 259, 1, 26);
		myApp.insertCourseOffering(6, "PHYS", 259, 2, 234);
		
		myApp.insertCourse(4, "Math", 271);
		myApp.insertCourseOffering(7, "Math", 271, 1, 31);
		myApp.insertCourseOffering(8, "Math", 271, 2, 33);
		
		myApp.insertCourse(5, "ENCM", 275);
		myApp.insertCourseOffering(9, "ENCM", 275, 1, 76);
		myApp.insertCourseOffering(10, "ENCM", 275, 2, 51);
		
		myApp.insertCourse(6, "CPSC", 332);
		myApp.insertCourseOffering(11, "CPSC", 332, 1, 46);
		myApp.insertCourseOffering(12, "CPSC", 332, 2, 251);
		
		myApp.createAccount(1, "admin", "1234");
		
		myApp.close();
	}

	
}
