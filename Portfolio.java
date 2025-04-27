package tracker;

import java.util.*;
import java.io.*;

public class Portfolio implements Rebalanceable {
    private Map<String, Holding> holdings = new HashMap<>();
    private Map<String, Double> targetAllocations = new HashMap<>();
    private List<String> rebalanceSummary = new ArrayList<>();

    public void addHolding(Holding h) {
        holdings.put(h.getStock().getTicker(), h);
    }

    public void setTargetAllocation(String ticker, double percentage) {
        targetAllocations.put(ticker, percentage);
    }

    public double getTotalValue() {
        return holdings.values().stream().mapToDouble(Holding::getAmountOwned).sum();
    }

    public void rebalance() {
        double totalValue = getTotalValue();
        rebalanceSummary.clear();
        rebalanceSummary.add("+----------------------+-----------------+----------------+----------------+\n");
        rebalanceSummary.add("| Ticker               | Current %       | Target %       | Suggestion     |\n");
        rebalanceSummary.add("+----------------------+-----------------+----------------+----------------+\n");
        for (String ticker : targetAllocations.keySet()) {
            Holding h = holdings.get(ticker);
            if (h == null) continue;
            double currentPercentage = h.getWeight(totalValue);
            double targetPercentage = targetAllocations.get(ticker);
            double diff = targetPercentage - currentPercentage;
            double valueDiff = totalValue * (diff / 100.0);
            String action = (valueDiff > 0) ? String.format("Buy $%.2f", Math.abs(valueDiff))
                                            : String.format("Sell $%.2f", Math.abs(valueDiff));
            rebalanceSummary.add(String.format("| %-20s | %-15.2f | %-14.2f | %-14s |\n",
                ticker,
                currentPercentage,
                targetPercentage,
                action));
        }
        rebalanceSummary.add("+----------------------+-----------------+----------------+----------------+\n");
        rebalanceSummary.forEach(System.out::print);
    }

    public void printRebalanceSummary() {
        System.out.println("\n+----------------- Portfolio Summary -----------------+");
        double total = getTotalValue();
        for (Holding h : holdings.values()) {
            System.out.printf("| %-15s (%s): $%.2f | %.2f%%\n",
                h.getStock().getCompanyName(),
                h.getStock().getTicker(),
                h.getAmountOwned(),
                h.getWeight(total));
        }
        System.out.println("+-----------------------------------------------------+\n");
    }

    public void exportToExcel(String filename) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            writer.println("Ticker,Company Name,Amount Owned,Current %,Target %,Suggestion");
            double totalValue = getTotalValue();
            for (String ticker : targetAllocations.keySet()) {
                Holding h = holdings.get(ticker);
                if (h == null) continue;
                double currentPercentage = h.getWeight(totalValue);
                double targetPercentage = targetAllocations.get(ticker);
                double diff = targetPercentage - currentPercentage;
                double valueDiff = totalValue * (diff / 100.0);
                String action = (valueDiff > 0) ? String.format("Buy $%.2f", valueDiff) : String.format("Sell $%.2f", -valueDiff);
                writer.printf("%s,%s,%.2f,%.2f,%.2f,%s\n",
                        ticker,
                        h.getStock().getCompanyName(),
                        h.getAmountOwned(),
                        currentPercentage,
                        targetPercentage,
                        action);
            }
            System.out.println("\nPortfolio exported to: " + filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
