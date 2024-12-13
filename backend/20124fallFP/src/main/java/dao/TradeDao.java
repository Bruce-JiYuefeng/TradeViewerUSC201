package dao;

import config.DatabaseConfig;
import model.Trade;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class TradeDao {
	private static final Logger LOGGER = Logger.getLogger(TradeDao.class.getName());

	public boolean saveTrades(List<Trade> trades) throws SQLException {
		String query = "INSERT INTO trades (instrument_id, user_id, date_time, buy_sell, "
				+ "quantity, price, price_out, pnl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DatabaseConfig.getConnection()) {
			conn.setAutoCommit(false);
			// System.out.println("check 1 in tradeDAO");
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				// System.out.println("check 2 in tradeDAO");
				for (Trade trade : trades) {
					stmt.setInt(1, trade.getInstrumentId());
					stmt.setLong(2, trade.getUserId());
					stmt.setTimestamp(3, Timestamp.valueOf(trade.getDateTime()));
					stmt.setString(4, trade.getBuySell());
					stmt.setBigDecimal(5, trade.getQuantity());
					stmt.setBigDecimal(6, trade.getPrice());
					stmt.setBigDecimal(7, trade.getpriceOut());

					if (trade.getPnl() != null) {
						stmt.setBigDecimal(8, trade.getPnl());
					} else {
						stmt.setNull(8, Types.DECIMAL);
					}

					stmt.addBatch();
				}

				stmt.executeBatch();
				conn.commit();
				return true;
			} catch (SQLException e) {
				conn.rollback();
				LOGGER.log(Level.SEVERE, "Error saving trades", e);
				throw e;
			}
		}
	}

	public List<Trade> getTradesByUserId(Long userId) throws SQLException {
		String query = "SELECT * FROM trades WHERE user_id = ? ORDER BY date_time DESC";
		List<Trade> trades = new ArrayList<>();

		try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setLong(1, userId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Trade trade = (new Trade(rs.getInt("instrument_id"), 
						rs.getTimestamp("date_time").toLocalDateTime(),
						rs.getString("buy_sell"), 
						rs.getBigDecimal("quantity"), 
						rs.getBigDecimal("price"),
						rs.getBigDecimal("price_out"), 
						rs.getBigDecimal("pnl")));
				trade.setTradeId(rs.getLong("trade_id"));
				trades.add(trade);
			}
		}
		return trades;
	}

	// add
	public String getSymbolByInstrumentId(Integer instrumentId) throws SQLException {
		String symbol = null;
		String query = "SELECT instrument_name FROM instruments WHERE instrument_Id = ?";

		try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, instrumentId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					symbol = rs.getString("instrument_name");
				}
			}
		}
		return symbol;
	}

	public boolean deleteTrade(long tradeId) throws SQLException {
		String query = "DELETE FROM trades WHERE trade_id = ?";

		try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setLong(1, tradeId);
			int affectedRows = stmt.executeUpdate();

			return affectedRows > 0;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error deleting trade with ID: " + tradeId, e);
			throw e;
		}
	}
}