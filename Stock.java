package tracker;

public class Stock implements Trackable {
    private String ticker;
    private String companyName;
    private double currentValue;

    public Stock(String ticker, String companyName, double currentValue) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.currentValue = currentValue;
    }

    public String getTicker() { return ticker; }
    public double getCurrentValue() { return currentValue; }
    public String getCompanyName() { return companyName; }
    public void updatePrice(double price) { this.currentValue = price; }
}