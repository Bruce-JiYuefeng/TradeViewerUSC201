package model;

/**
 * Model class representing a user request, containing username and password fields.
 */
public class UserRequest {
    private String username; // Maps to the `name` column in the database
    private String password;
    private Long id;

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

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
        this.id = id;
    }
}
