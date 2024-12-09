package services;

import dao.UserDao;

/**
 
Service layer for handling business logic related to user login and registration.*/
public class UserService {
    private final UserDao userDao = new UserDao(); // Data Access Layer instance

    /**
     
Validates a user's credentials by comparing input with database records.*
@param username The username provided by the user.
@param password The password provided by the user.
@return True if the credentials are valid, otherwise false.
@throws ClassNotFoundException */
public LoginResponse validateUser(String username, String password) throws ClassNotFoundException {
    UserDetails user = userDao.getUserByUsername(username);
    if (user != null && user.getPassword().equals(password)) {
        return new LoginResponse(true, user.getRole(), user.getUserId());
    }
    return new LoginResponse(false, "guest", null);
}

    /**
     
Registers a new user by ensuring the username is unique and storing their details.*
@param username The username provided by the user.
@param password The password provided by the user.
@return True if registration is successful, otherwise false.
@throws ClassNotFoundException */
public boolean registerUser(String username, String password) throws ClassNotFoundException  {
    if (userDao.isUsernameExists(username)) {
        return false;} // Username already exists
    return userDao.saveUser(username, password);}
}