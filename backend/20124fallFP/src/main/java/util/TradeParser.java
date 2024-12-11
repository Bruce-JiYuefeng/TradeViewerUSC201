//package util;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import javax.servlet.http.Part;
//
//public class TradeParser {
//    public static List<Trade> parseCSV(Part filePart, Long userId) throws IOException {
//        List<Trade> trades = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(filePart.getInputStream()))) {
//            String headerLine = br.readLine(); // Skip header
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                trades.add(new Trade(
//                    Integer.parseInt(values[0].trim()),  // instrumentId
//                    userId,
//                    LocalDateTime.parse(values[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
//                    values[2].trim(),                    // buySell
//                    new BigDecimal(values[3].trim()),    // quantity
//                    new BigDecimal(values[4].trim()),    // price
//                    new BigDecimal(values[5].trim()),    // commissions
//                    values[6].trim().isEmpty() ? null : new BigDecimal(values[6].trim()) // pnl
//                ));
//            }
//        }
//        return trades;
//    }
//} 