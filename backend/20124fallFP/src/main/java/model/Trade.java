package model;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public class Trade {
    private Long tradeId;
    private Long userId;
    private Integer instrumentId;
    private LocalDateTime dateTime;
    private String buySell;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal price_out;
    private BigDecimal pnl;

    public Trade(Integer instrumentId, LocalDateTime dateTime, String buySell,
                BigDecimal quantity, BigDecimal price, BigDecimal price_out, BigDecimal pnl) {
        this.instrumentId = instrumentId;
        this.dateTime = dateTime;
        this.buySell = buySell;
        this.quantity = quantity;
        this.price = price;
        this.price_out = price_out;
        this.pnl = pnl;
    }

    // Getters and setters
    public Long getTradeId() { return tradeId; }
    public Long getUserId() { return userId; }
    public Integer getInstrumentId() { return instrumentId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getBuySell() { return buySell; }
    public BigDecimal getQuantity() { return quantity; }
    public BigDecimal getPrice() { return price; }
    public BigDecimal getpriceOut() { return price_out; }
    public BigDecimal getPnl() { return pnl; }

    public void setUserId(Long userId) { this.userId = userId; }
    public void setTradeId(Long tradeId) {this.tradeId = tradeId;}
}