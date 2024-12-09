package model;

public class LoginResponse {
    private boolean success;
    private String role;
    private Long userId;

    public LoginResponse(boolean success, String role, Long userId) {
        this.success = success;
        this.role = role;
        this.userId = userId;
    }

    // Add getters and setters
} 