package services;

import models.ConversionRequest;

public class ConversionService
{
    private final ExchangeRateFetcher exchangeRateFetcher;

    public ConversionService(ExchangeRateFetcher exchangeRateFetcher) {
        this.exchangeRateFetcher = exchangeRateFetcher;
    }

    public String convert (ConversionRequest conversionRequest)
    {
        String result = exchangeRateFetcher.getConversion(
                conversionRequest.getBaseCurrency(),
                conversionRequest.getTargetCurrency(),
                conversionRequest.getAmount()
        );

        return String.format(
                "El valor %.2f [%s] corresponde al valor final de =>>> %s [%s]",
                conversionRequest.getAmount(),
                conversionRequest.getBaseCurrency(),
                result,
                conversionRequest.getTargetCurrency()
        );
    }
}
