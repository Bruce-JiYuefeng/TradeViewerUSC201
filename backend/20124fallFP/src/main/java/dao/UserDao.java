package dao;

import config.DatabaseConfig;
//import model.UserDetails;
import java.sql.*;

/**
 * Data Access Object (DAO) class to interact with the MySQL database for user-related operations.
 */
public class UserDao {

    /**
     * Retrieves the password associated with a username.
     *
     * @param username The username to query.
     * @return The password if the username exists, otherwise null.
     * @throws ClassNotFoundException 
     */
	public String getPasswordByUsername(String username) {
	    System.out.println("Connecting to the database for username: " + username); // Debug message
	    String query = "SELECT password FROM users WHERE name = ?";
	    try (Connection conn = DatabaseConfig.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, username); // Bind the username parameter
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            String password = rs.getString("password");
	            System.out.println("Query successful! Password: " + password); // Debug message
	            return password;
	        } else {
	            System.out.println("No user found with username: " + username); // Debug message
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}


    /**
     * Checks if a username already exists in the database.
     *
     * @param username The username to check.
     * @return True if the username exists, otherwise false.
     * @throws ClassNotFoundException 
     */
    public boolean isUsernameExists(String username){
    	System.out.println("Connecting to the database for username: " + username);
        String query = "SELECT 1 FROM users WHERE name = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Saves a new user into the database.
     *
     * @param username The username to save.
     * @param password The password to save.
     * @return True if the operation is successful, otherwise false.
     * @throws ClassNotFoundException 
     */
    public boolean saveUser(String username, String password) {
        String query = "INSERT INTO users (name, password) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves user details by username.
     *
     * @param username The username to query.
     * @return The user details if the username exists, otherwise null.
     * @throws ClassNotFoundException 
     */
//    public UserDetails getUserByUsername(String username) throws ClassNotFoundException {
//        String query = "SELECT * FROM users WHERE name = ?";
//        
//        try (Connection conn = DatabaseConfig.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, username);
//            ResultSet rs = stmt.executeQuery();
//            
//            if (rs.next()) {
//                return new UserDetails(
//                    rs.getLong("user_id"),
//                    rs.getString("name"),
//                    rs.getString("password")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
