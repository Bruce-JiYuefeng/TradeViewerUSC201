package services;

import dao.TradeDao;
import model.Trade;
import java.util.List;

public class TradeService {
    private final TradeDao tradeDao = new TradeDao();

    public boolean saveTrades(List<Trade> trades) {
        try {
            return tradeDao.saveTrades(trades);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Trade> getTradesByUserId(Long userId) {
        try {
            return tradeDao.getTradesByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
} 