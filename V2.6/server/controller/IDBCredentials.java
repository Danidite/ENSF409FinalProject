package server.controller;

/**
 * The credentals used for all SQL methods, this specifies the database
 * path and also the login credentials to access the database
 * This is taken from D2L, likely a generalized code
 * It has been modified to fit in my program
 * @author UNKNOWN
 */
public interface IDBCredentials {
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/mydb";

	   //  Database credentials
	   static final String USERNAME = "user";
	   static final String PASSWORD = "password";
}
