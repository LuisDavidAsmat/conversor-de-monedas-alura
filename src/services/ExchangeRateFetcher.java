package services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ExchangeRateFetcher
{
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6";
    private static final String API_KEY = System.getenv("API_KEY");
    private final HttpClient httpClient;


    public ExchangeRateFetcher() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getConversion(String baseCurrency, String targetCurrency, double amount)
    {
        try
        {
            URI conversionUri = buildConversionUri(baseCurrency, targetCurrency, amount);
            String responseJson = fetchApiResponse(conversionUri);

            return parseConversionResult(responseJson);

        }
        catch (Exception e)
        {
            throw new RuntimeException("Failed to retrieve currency conversion: " + e.getMessage(), e);
        }
    }

    private URI buildConversionUri(String baseCurrency, String targetCurrency, double amount)
    {
        String encodedBase = URLEncoder.encode(baseCurrency, StandardCharsets.UTF_8);
        String encodedTarget = URLEncoder.encode(targetCurrency, StandardCharsets.UTF_8);
        String encodedAmount = URLEncoder.encode(String.valueOf(amount), StandardCharsets.UTF_8);

        String finalUrl = String.format(
                "%s/%s/pair/%s/%s/%s",
                API_BASE_URL,
                API_KEY,
                encodedBase,
                encodedTarget,
                encodedAmount
        );

        return URI.create(finalUrl);
    }

    private String fetchApiResponse(URI conversionUri) throws IOException, InterruptedException
    {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(conversionUri)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(
                httpRequest,
                HttpResponse.BodyHandlers.ofString()
        );

        return response.body();
    }

    private String parseConversionResult(String responseJson)
    {
        JsonObject jsonObject = JsonParser.parseString(responseJson).getAsJsonObject();

        if (jsonObject.has("conversion_result"))
        {
            return jsonObject.get("conversion_result").getAsString();
        }
        else
        {
            throw new RuntimeException("API response missing 'conversion_result' field");
        }
    }

}
