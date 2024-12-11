package model;

/**
 * Model class representing a user request, containing username and password fields.
 */
public class UserRequest {
    private String username; // Maps to the `name` column in the database
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
