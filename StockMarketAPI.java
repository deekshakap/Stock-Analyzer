package tracker;

import java.util.*;
import java.io.*;

public class StockMarketAPI {
    private static final String PRICE_FILE = "wsj_mock_prices.txt"; 

    public static double getPrice(String ticker) {
        try (BufferedReader br = new BufferedReader(new FileReader(PRICE_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts[0].equalsIgnoreCase(ticker)) {
                    return Double.parseDouble(parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 100.0;
    }
}
