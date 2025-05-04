package models;

public class ConversionRequest
{
    private final String baseCurrency;
    private final String targetCurrency;
    private final double amount;

    public ConversionRequest(String baseCurrency, String targetCurrency, double amount) {
        this.baseCurrency = baseCurrency.toUpperCase();
        this.targetCurrency = targetCurrency.toUpperCase();
        this.amount = amount;
    }

    public String getBaseCurrency() { return baseCurrency; }
    public String getTargetCurrency() { return targetCurrency; }
    public double getAmount() { return amount; }
}
