package controller;

import model.Trade;
import services.TradeService;
import util.TradeParser;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

@WebServlet("/api/upload-csv")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(FileUploadServlet.class.getName());
    private final TradeService tradeService = new TradeService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Get user_id from session
            Long userId = (Long) request.getSession().getAttribute("userId");
            LOGGER.info("Session ID: " + request.getSession().getId()); // added for debugging
            LOGGER.info("userId stored in session: " + userId); // added for debugging
            if (userId == null) {
                throw new IllegalStateException("User not logged in");
            }

            Part filePart = request.getPart("file");
            if (!filePart.getSubmittedFileName().toLowerCase().endsWith(".csv")) {
                throw new IllegalArgumentException("Only CSV files are allowed");
            }

            List<Trade> trades = TradeParser.parseCSV(filePart, userId);
            if (trades.isEmpty()) {
                throw new IllegalArgumentException("No valid trades found in CSV");
            }

            boolean success = tradeService.saveTrades(trades);
            if (success) {
                out.println("{\"status\":\"success\",\"message\":\"Trades imported successfully\"}");
            } else {
                throw new Exception("Failed to save trades");
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error processing CSV upload", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
        }
    }
}
