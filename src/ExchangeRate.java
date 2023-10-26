
public class ExchangeRate {
    private double krwRate;

    public ExchangeRate() {
        this.krwRate = APIFetcher.fetchUSDKRW();
    }

    public double getConvertedAmount(double amount) {
        return amount * krwRate;
    }
}