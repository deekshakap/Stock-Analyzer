package tracker;

public class MainApp {
    public static void main(String[] args) {
    	Portfolio portfolio = new Portfolio();

        Stock aapl = new Stock("AAPL", "Apple Inc.", StockMarketAPI.getPrice("AAPL"));
        Stock googl = new Stock("GOOGL", "Alphabet Inc.", StockMarketAPI.getPrice("GOOGL"));
        Stock tsla = new Stock("TSLA", "Tesla Inc.", StockMarketAPI.getPrice("TSLA"));
        Stock amzn = new Stock("AMZN", "Amazon.com Inc.", StockMarketAPI.getPrice("AMZN"));
        Stock msft = new Stock("MSFT", "Microsoft Corp.", StockMarketAPI.getPrice("MSFT"));
        Stock nvda = new Stock("NVDA", "NVIDIA Corporation", StockMarketAPI.getPrice("NVDA"));

        portfolio.addHolding(new Holding(aapl, 5000));
        portfolio.addHolding(new Holding(googl, 3000));
        portfolio.addHolding(new Holding(tsla, 2000));
        portfolio.addHolding(new Holding(amzn, 2500));
        portfolio.addHolding(new Holding(msft, 3500));
        portfolio.addHolding(new Holding(nvda, 1500));

        portfolio.setTargetAllocation("AAPL", 25.0);
        portfolio.setTargetAllocation("GOOGL", 20.0);
        portfolio.setTargetAllocation("TSLA", 10.0);
        portfolio.setTargetAllocation("AMZN", 15.0);
        portfolio.setTargetAllocation("MSFT", 20.0);
        portfolio.setTargetAllocation("NVDA", 10.0);

        portfolio.printRebalanceSummary();
        portfolio.rebalance();
        portfolio.exportToExcel("portfolio_rebalance_report.csv");
    }
}