package controller;

import com.google.gson.Gson;

import model.UserRequest;
import services.UserService;
import exceptions.UsernameAlreadyExistsException;

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
        PrintWriter out = resp.getWriter();
        String messageOut = "";
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

            // Register user
            boolean isRegistered = userService.registerUser(userRequest.getUsername(), userRequest.getPassword());

            // Set response headers
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            // Send success response
            if (isRegistered) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                messageOut = ("{\"status\": \"success\", \"message\": \"Registration successful\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                messageOut = ("{\"status\": \"error\", \"message\": \"Registration failed\"}");
            }
        } catch (UsernameAlreadyExistsException e) {
            LOGGER.log(Level.WARNING, "Caught UsernameAlreadyExistsException", e);
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            resp.setContentType("application/json");
            messageOut = ("{\"status\": \"error\", \"message\": \"Username already exists\"}");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error processing registration request", e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            messageOut = ("{\"status\": \"error\", \"message\": \"Internal server error\"}");
        } finally {
        	out.print(messageOut);
            out.flush();
            out.close();
        }
    }

}
