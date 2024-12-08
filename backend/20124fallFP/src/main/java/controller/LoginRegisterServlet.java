package controller;

import com.google.gson.Gson;
import services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Servlet to handle login and registration requests.
 * Handles POST requests to /login and /register endpoints.
 */
@WebServlet(urlPatterns = {"/login", "/register"})
public class LoginRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1;
	private final UserService userService = new UserService(); // Business logic layer instance

    /**
     * Handles POST requests for login and registration.
     *
     * @param req  HttpServletRequest from the client
     * @param resp HttpServletResponse to the client
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getServletPath(); // Determine if the request is for login or register
        BufferedReader reader = req.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;

        // Read JSON data from the request body
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // Parse JSON request into UserRequest object
        Gson gson = new Gson();
        UserRequest userRequest = gson.fromJson(requestBody.toString(), UserRequest.class);

        // Determine action based on endpoint path
        String responseMessage = null;
        if ("/login".equals(path)) {
            try {
				responseMessage = handleLogin(userRequest);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
        } else if ("/register".equals(path)) {
            try {
				responseMessage = handleRegister(userRequest);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
        } else {
            responseMessage = "{\"status\": \"error\", \"message\": \"Invalid endpoint\"}";
        }

        // Send JSON response back to the client
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(responseMessage);
    }

    private String handleLogin(UserRequest userRequest) throws ClassNotFoundException {
        if (userService.validateUser(userRequest.getUsername(), userRequest.getPassword())) {
            return createResponse("success", "Login successful");
        }
        return createResponse("error", "Invalid username or password");
    }

    private String handleRegister(UserRequest userRequest) throws ClassNotFoundException {
        if (userService.registerUser(userRequest.getUsername(), userRequest.getPassword())) {
            return createResponse("success", "Registration successful");
        }
        return createResponse("error", "Username already exists");
    }


    private String createResponse(String status, String message) {
        return String.format("{\"status\": \"%s\", \"message\": \"%s\"}", status, message);
    }
}