package controller;

import model.Trade;
import services.TradeService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/upload-csv")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private final TradeService tradeService = new TradeService();
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Get user_id from session
            Long userId = (Long) request.getSession().getAttribute("userId");
            if (userId == null) {
                throw new IllegalStateException("User not logged in");
            }

            Part filePart = request.getPart("file");
            if (!filePart.getSubmittedFileName().toLowerCase().endsWith(".csv")) {
                throw new IllegalArgumentException("Only CSV files are allowed");
            }

            List<Trade> trades = parseCSV(filePart, userId);
            boolean success = tradeService.saveTrades(trades);

            if (success) {
                out.println("{\"status\":\"success\",\"message\":\"Trades imported successfully\"}");
            } else {
                throw new Exception("Failed to save trades");
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    private List<Trade> parseCSV(Part filePart, Long userId) throws IOException {
        List<Trade> trades = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(filePart.getInputStream()))) {
            String headerLine = br.readLine(); // Skip header
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 7) {
                    continue; // Skip invalid lines
                }
                
                trades.add(new Trade(
                    Integer.parseInt(values[0].trim()),      // instrument_ids
                    userId,                                  // user_id
                    LocalDateTime.parse(values[1].trim(), DATE_FORMATTER), // date_time
                    values[2].trim(),                        // buy_sell
                    new BigDecimal(values[3].trim()),        // quantity
                    new BigDecimal(values[4].trim()),        // price
                    new BigDecimal(values[5].trim()),        // commissions
                    values[6].trim().isEmpty() ? null : new BigDecimal(values[6].trim()) // pnl
                ));
            }
        }
        return trades;
    }
} 