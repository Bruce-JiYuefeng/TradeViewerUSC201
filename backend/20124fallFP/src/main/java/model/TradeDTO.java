package model;

import java.math.BigDecimal;

public class TradeDTO {
	private String id;
    private String date;
    private String symbol;
    private String type;
    private BigDecimal quantity;
    private BigDecimal entryPrice;
    private BigDecimal exitPrice;
    private BigDecimal profitLoss;

    // Constructor
    public TradeDTO(String id, String date, String symbol, String type, BigDecimal quantity,
                    BigDecimal entryPrice, BigDecimal exitPrice, BigDecimal profitLoss) {
        this.id = id;
        this.date = date;
        this.symbol = symbol;
        this.type = type;
        this.quantity = quantity;
        this.entryPrice = entryPrice;
        this.exitPrice = exitPrice;
        this.profitLoss = profitLoss;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getDate() { return date; }
    public String getSymbol() { return symbol; }
    public String getType() { return type; }
    public BigDecimal getQuantity() { return quantity; }
    public BigDecimal getEntryPrice() { return entryPrice; }
    public BigDecimal getExitPrice() { return exitPrice; }
    public BigDecimal getProfitLoss() { return profitLoss; }
}
