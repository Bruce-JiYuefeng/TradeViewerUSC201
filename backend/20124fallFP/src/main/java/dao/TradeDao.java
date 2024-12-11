//package dao;
//
//import config.DatabaseConfig;
//import model.Trade;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TradeDao {
//    public boolean saveTrades(List<Trade> trades) throws SQLException, ClassNotFoundException {
//        String query = "INSERT INTO trades (instrument_id, user_id, date_time, buy_sell, " +
//                      "quantity, price, commissions, pnl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//                      
//        try (Connection conn = DatabaseConfig.getConnection()) {
//            conn.setAutoCommit(false);
//            
//            try (PreparedStatement stmt = conn.prepareStatement(query)) {
//                for (Trade trade : trades) {
//                    stmt.setInt(1, trade.getInstrumentId());
//                    stmt.setLong(2, trade.getUserId());
//                    stmt.setTimestamp(3, Timestamp.valueOf(trade.getDateTime()));
//                    stmt.setString(4, trade.getBuySell());
//                    stmt.setBigDecimal(5, trade.getQuantity());
//                    stmt.setBigDecimal(6, trade.getPrice());
//                    stmt.setBigDecimal(7, trade.getCommissions());
//                    stmt.setBigDecimal(8, trade.getPnl());
//                    stmt.addBatch();
//                }
//                
//                stmt.executeBatch();
//                conn.commit();
//                return true;
//            } catch (SQLException e) {
//                conn.rollback();
//                throw e;
//            }
//        }
//    }
//
//    public List<Trade> getTradesByUserId(Long userId) throws SQLException, ClassNotFoundException {
//        String query = "SELECT * FROM trades WHERE user_id = ? ORDER BY date_time DESC";
//        List<Trade> trades = new ArrayList<>();
//        
//        try (Connection conn = DatabaseConfig.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setLong(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            
//            while (rs.next()) {
//                trades.add(new Trade(
//                    rs.getInt("instrument_id"),
//                    rs.getLong("user_id"),
//                    rs.getTimestamp("date_time").toLocalDateTime(),
//                    rs.getString("buy_sell"),
//                    rs.getBigDecimal("quantity"),
//                    rs.getBigDecimal("price"),
//                    rs.getBigDecimal("commissions"),
//                    rs.getBigDecimal("pnl")
//                ));
//            }
//        }
//        return trades;
//    }
//} 