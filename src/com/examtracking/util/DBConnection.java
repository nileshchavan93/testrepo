package com.examtracking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class used to establish connection with Database
 * @author BATCH-C
 *
 */
public class DBConnection {
	/***
	 * This method establish connection and return Connection Object
	 * @return Connection Obj
	 */
	public static Connection getDBConnection() {
		
		//creating connection object to establish the connection to the database
		Connection con = null;
		try {
			//registering to the mysql server
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			//connectint to the examtrackingsystemdb database in the local by providing the credentials ofd database
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/examtrackingsystemdb", "root",
					"innominds");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
