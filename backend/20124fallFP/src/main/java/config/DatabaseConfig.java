package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Configuration class to manage MySQL database connections.
 */
public class DatabaseConfig {
	private static final String URL = "jdbc:mysql://localhost:3306/trade_journal";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	/*
	 * 
	 * Establishes and returns a connection to the database.*
	 * 
	 * @return A Connection object for database interaction.
	 * 
	 * @throws SQLException If there is an error connecting to the database.
	 * 
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Explicitly load the JDBC driver
		} catch (ClassNotFoundException e) {
			throw new SQLException("MySQL JDBC Driver not found", e);
		}
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Database connected successfully!（DatabaseConfig）"); // Debug message
        return conn;
    }
}


