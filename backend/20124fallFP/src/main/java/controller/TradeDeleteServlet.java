package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.TradeService;

@WebServlet("/api/trades/*")
public class TradeDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TradeService tradeService;

    @Override
    public void init() throws ServletException {
        tradeService = new TradeService(); // Initialize your service here
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Trade ID is missing");
            return;
        }

        String[] pathParts = pathInfo.split("/");
        if (pathParts.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Trade ID");
            return;
        }

        try {
            long tradeId = Long.parseLong(pathParts[1]);
            boolean isDeleted = tradeService.deleteTradeById(tradeId);

            if (isDeleted) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Trade not found");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Trade ID format");
        }
    }
}
