package services;

import dao.UserDao;
import controller.User;
import exceptions.UsernameAlreadyExistsException;

/**
 * 
 * Service layer for handling business logic related to user login and
 * registration.
 */
public class UserService {
	private final UserDao userDao = new UserDao(); // Data Access Layer instance

	/**
	 * 
	 * Validates a user's credentials by comparing input with database records.*
	 * 
	 * @param username The username provided by the user.
	 * @param password The password provided by the user.
	 * @return True if the credentials are valid, otherwise false.
	 * @throws ClassNotFoundException
	 */
	public boolean validateUser(String username, String password) {
        String storedPassword = userDao.getPasswordByUsername(username);
        return storedPassword != null && storedPassword.equals(password);
    }

	/**
	 * 
	 * Registers a new user by ensuring the username is unique and storing their
	 * details.*
	 * 
	 * @param username The username provided by the user.
	 * @param password The password provided by the user.
	 * @return True if registration is successful, otherwise false.
	 * @throws ClassNotFoundException
	 */
	public boolean registerUser(String username, String password) throws UsernameAlreadyExistsException {
		boolean usernameExists = userDao.isUsernameExists(username);
		System.out.println("Username exists check for " + username + ": " + usernameExists); // Debug message
		if (usernameExists) {
			System.out.println("Username already exists, throwing exception."); // Debug message
			throw new UsernameAlreadyExistsException("Username already exists.");
		}
		return userDao.saveUser(username, password);
	}
	//added
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
}
