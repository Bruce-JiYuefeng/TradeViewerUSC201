package util;

import model.Trade;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TradeParser {
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static List<Trade> parseCSV(Part filePart, Long userId) throws IOException {
        List<Trade> trades = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(filePart.getInputStream()))) {
            String headerLine = br.readLine(); // Skip header
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 7) {
                    continue; // Skip invalid lines
                }
                
                try {
                    Trade trade = new Trade(
                        Integer.parseInt(values[0].trim()),      // instrument_id
                        LocalDateTime.parse(values[1].trim(), DATE_FORMATTER),
                        values[2].trim(),                        // buy_sell
                        new BigDecimal(values[3].trim()),        // quantity
                        new BigDecimal(values[4].trim()),        // price
                        new BigDecimal(values[5].trim()),        // commissions
                        values[6].trim().isEmpty() ? null : new BigDecimal(values[6].trim()) // pnl
                    );
                    trades.add(trade);
                } catch (Exception e) {
                    // Log error but continue processing other lines
                    System.err.println("Error parsing line: " + line);
                }
            }
        }
        return trades;
    }
}