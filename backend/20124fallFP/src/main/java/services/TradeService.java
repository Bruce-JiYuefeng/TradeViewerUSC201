package services;

import dao.TradeDao;
import model.Trade;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.sql.SQLException;

import model.TradeDTO;

import java.util.logging.Level;

public class TradeService {
    private static final Logger LOGGER = Logger.getLogger(TradeService.class.getName());
    private final TradeDao tradeDao = new TradeDao();

    public boolean saveTrades(List<Trade> trades) {
        try {
            return tradeDao.saveTrades(trades);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error saving trades", e);
            return false;
        }
    }

    public List<Trade> getTradesByUserId(Long userId) {
        try {
            return tradeDao.getTradesByUserId(userId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving trades for user: " + userId, e);
            return null;
        }
    }

    public boolean deleteTradeById(long tradeId) {
        try {
            return tradeDao.deleteTrade(tradeId);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting trade with ID: " + tradeId, e);
            return false;
        }
    }
    
//    // added
//    public List<Trade> saveTradesAndReturn(List<Trade> trades) throws Exception {
//        if (tradeDao.saveTrades(trades)) {
//            // Return the saved trades (already available in-memory here)
//            return trades;
//        } else {
//            throw new Exception("Failed to save trades");
//        }
//    }
//    
    public List<TradeDTO> convertToDTO(List<Trade> trades) {
        return trades.stream().map(trade -> {
            String date = trade.getDateTime().toLocalDate().toString();  
            String symbol = null;
			try {
				symbol = tradeDao.getSymbolByInstrumentId(trade.getInstrumentId());
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			String id = String.valueOf(trade.getTradeId());
		    String type = trade.getBuySell();
		    BigDecimal quantity = trade.getQuantity();
		    BigDecimal entryPrice = trade.getPrice();
		    BigDecimal exitPrice = entryPrice.add(trade.getpriceOut());
		    BigDecimal profitLoss = trade.getPnl(); 
		    
			TradeDTO TradeDTO = new TradeDTO(
					id,
	                date,
	                symbol,
	                type,
	                quantity,
	                entryPrice,  
	                exitPrice,  
	                profitLoss
	            );
            return TradeDTO; 
        }).collect(Collectors.toList());
    }
} 