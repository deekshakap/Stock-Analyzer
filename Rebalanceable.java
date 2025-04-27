package tracker;

public interface Rebalanceable {
    void rebalance();
    void setTargetAllocation(String ticker, double percentage);
    void printRebalanceSummary();
    void exportToExcel(String filename);
}