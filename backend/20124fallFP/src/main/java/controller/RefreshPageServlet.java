package controller;

import model.Trade;
import model.TradeDTO;
import services.TradeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

@WebServlet("/refresh-trades")
public class RefreshPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(RefreshPageServlet.class.getName());
    private final TradeService tradeService = new TradeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            String userIdHeader = request.getHeader("userId");
            Long userId = userIdHeader != null ? Long.parseLong(userIdHeader) : null;
            LOGGER.info("Received userId: " + userId);
            if (userId == null) {
                throw new IllegalStateException("User not logged in");
            }
            
            System.out.println("UserID is" + userId);
            List<Trade> getTrade = tradeService.getTradesByUserId(userId);
            List<TradeDTO> tradeDTOs = tradeService.convertToDTO(getTrade);

            Gson gson = new GsonBuilder().create();
            String jsonResponse = gson.toJson(tradeDTOs);
            LOGGER.info("Sending saved trades to frontend.");
            out.println(jsonResponse);
            LOGGER.info("trades: " + jsonResponse);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error processing trade refresh", e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
        }
    }
}
