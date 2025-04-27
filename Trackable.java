package tracker;

public interface Trackable {
    String getTicker();
    double getCurrentValue();
    void updatePrice(double price);
    String getCompanyName();
}