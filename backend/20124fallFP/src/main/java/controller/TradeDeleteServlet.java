package controller;

import java.io.BufferedReader;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.TradeService;
import com.google.gson.Gson;

//@WebServlet("/tradesDelete")
public class TradeDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TradeService tradeService;

    @Override
    public void init() throws ServletException {
        tradeService = new TradeService(); 
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Read JSON request body
        BufferedReader reader = request.getReader();
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        // Deserialize JSON to extract tradeId and state
        Gson gson = new Gson();
        TradeDeleteRequest deleteRequest = gson.fromJson(json.toString(), TradeDeleteRequest.class);
        long tradeId = deleteRequest.getTradeId();
        String state = deleteRequest.getState();

        if ("delete".equals(state)) {
            boolean isDeleted = tradeService.deleteTradeById(tradeId);

            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Trade not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request state");
        }
    }

    // Add a new class to represent the delete request
    class TradeDeleteRequest {
        private long tradeId;
        private String state;

        public long getTradeId() {
            return tradeId;
        }

        public void setTradeId(long tradeId) {
            this.tradeId = tradeId;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
