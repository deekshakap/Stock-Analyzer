package tracker;

public class Holding {
    private Stock stock;
    private double amountOwned; 

    public Holding(Stock stock, double amountOwned) {
        this.stock = stock;
        this.amountOwned = amountOwned;
    }

    public Stock getStock() { return stock; }
    public double getAmountOwned() { return amountOwned; }
    public void setAmountOwned(double value) { this.amountOwned = value; }
    public double getWeight(double totalValue) {
        return (amountOwned / totalValue) * 100.0;
    }
}