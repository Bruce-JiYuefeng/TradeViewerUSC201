package controller;

import com.google.gson.Gson;
import model.UserRequest;
import services.UserService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Login Servlet to handle user login requests.
 */
//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // Parse JSON request body
            BufferedReader reader = req.getReader();
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            // Deserialize JSON into UserRequest object
            Gson gson = new Gson();
            UserRequest userRequest = gson.fromJson(json.toString(), UserRequest.class);

            // Validate user credentials
            boolean isValid = userService.validateUser(userRequest.getUsername(), userRequest.getPassword());

            // Set response headers
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            // Send JSON response
            PrintWriter out = resp.getWriter();
            if (isValid) {
                out.print("{\"status\": \"success\", \"message\": \"Login successful\"}");
            } else {
                out.print("{\"status\": \"error\", \"message\": \"Invalid username or password\"}");
            }
            out.flush();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error processing login request", e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"status\": \"error\", \"message\": \"Internal server error\"}");
        }
    }
}
