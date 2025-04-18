package dao;

import config.DatabaseConfig;
import controller.User;

//import model.UserDetails;
import java.sql.*;

/**
 * Data Access Object (DAO) class to interact with the MySQL database for
 * user-related operations.
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
		try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, username); // Bind the username parameter
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String password = rs.getString("password");
				System.out.println("Query successful! Password: " + password + "(UserDao.getPasswordByUsername)"); // Debug
																													// message
				return password;
			} else {
				System.out.println("No user found with username: " + username + "(UserDao.getPasswordByUsername)"); // Debug
																													// message
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
	public boolean isUsernameExists(String username) {
		System.out.println("Checking if username exists: " + username); // Debug message
		String query = "SELECT 1 FROM users WHERE name = ?";
		try (Connection conn = DatabaseConfig.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			boolean exists = rs.next();
			System.out.println("Username " + username + " exists: " + exists); // Debug message
			return exists;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL error in UserDao.isUsernameExists for username: " + username);
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
			System.out.println("Rows inserted: " + rowsInserted); // Debug message
			return rowsInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL error in UserDao.saveUser for username: " + username);
		}
		return false;
	}

	public User getUserByUsername(String username) {
		String query = "SELECT user_id, name, password FROM users WHERE name = ?";
	    try (Connection conn = DatabaseConfig.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, username);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            Long id = rs.getLong("user_id");  // retrieve the user ID
	            String name = rs.getString("name");
	            String password = rs.getString("password");
	            return new User(id, name, password);  // return a User object with id
	        }
	    } 
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;  // return null if user is not found
		
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
