package controller;

import com.google.gson.Gson;

import model.UserRequest;
import services.UserService;

//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Register Servlet to handle user registration requests.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class.getName());
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

            
            
            boolean isRegistered = userService.registerUser(userRequest.getUsername(), userRequest.getPassword());

            

            // Set response headers
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            // Send JSON response
            PrintWriter out = resp.getWriter();
            if (isRegistered) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                out.print("{\"status\": \"success\", \"message\": \"Registration successful\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.print("{\"status\": \"error\", \"message\": \"Registration failed\"}");
            }
            out.flush();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error processing registration request", e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"status\": \"error\", \"message\": \"Internal server error\"}");
            System.out.println("something wrong in RegisterServlet.");
        }
    }
}
