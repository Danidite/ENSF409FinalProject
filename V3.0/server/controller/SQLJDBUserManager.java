package server.controller;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class reads and loads user data and credentials
 * @author Pin Long (30068063) and Hashir Ahmed (30070165)
 */
public class SQLJDBUserManager implements IDBCredentials {
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
	 * Validate if the username and password is correct and return their permission level
	 * Return 0 if successfully login, return 1 if admin, return -1 if failed.
	 * @param username the username of a account
	 * @param password the password of a account
	 * @return the true false value in 1 or 0
	*/
	public int validateLogin(String username, String password) {
		try {
			Statement myStmt = conn.createStatement();
			
			
			String query= "SELECT * FROM users WHERE (username = '"+ username+ "' ) COLLATE latin1_general_cs and (password ='"+ password+ "'" +") COLLATE latin1_general_cs" ;
			
			ResultSet myRs = myStmt.executeQuery(query);
			
			if (myRs.next()) {
				if (myRs.getString("username").equals(username)&&myRs.getString("password").equals(password)) {
					if (myRs.getInt("permission")==1) {
						return 1;
					}
					return 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Create a account with the inputed username
	 * and passwor, and also
	 * return 0 if successfully created account, 
	 * return 1 if username is already used,
	 * return 2 if password is too short.
	 * @param username the username of a account
	 * @param password the password of a account
	 * @return the true false value in 0, 1 or 2
	*/
	public int createAccount(String username, String password) {
		if (username.length()==0) {
			return 3;
		}
		if (password.length()==0) {
			return 2;
		}
		try {
			String query= "INSERT INTO users (username, password, permission) values(?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setString(1, username);
			pStat.setString(2, password);
			pStat.setInt(3, 0);
			pStat.executeUpdate();
			return 0;
		} catch (SQLException e) {
			//Failed to add user
			System.err.println("Failed to add account");
		}
		return 1;
	}
}
