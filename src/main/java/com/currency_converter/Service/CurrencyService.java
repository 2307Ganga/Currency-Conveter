package com.currency_converter.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {
    
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${currency.api.url}")
    private String apiUrl = "https://v6.exchangerate-api.com/v6/b30d2f2211e76468001f46aa/latest/";
    
    private Map<String, Object> fetchExchangeRatesFromApi(String base) {
        try {
            String url = String.format("%s%s", apiUrl, base);
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            if (response == null || !response.containsKey("conversion_rates")) {
                throw new IllegalArgumentException("No conversion rates available for the base currency.");
            }
            return response;
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch exchange rates from API.", e);
        }
    }
    
    public Map<String, Object> fetchExchangeRates(String base) {
        return fetchExchangeRatesFromApi(base);
    }

    public Map<String, Object> convertCurrency(Map<String, Object> request) {
        try {
            String from = (String) request.get("from");
            String to = (String) request.get("to");
            double amount = Double.parseDouble(request.get("amount").toString());
            
            Map<String, Object> response = fetchExchangeRatesFromApi(from);
            Map<String, Double> conversionRates = (Map<String, Double>) response.get("conversion_rates");

            if (!conversionRates.containsKey(to)) {
                throw new IllegalArgumentException("Invalid currency code provided for conversion.");
            }

            double rate = conversionRates.get(to);
            double convertedAmount = amount * rate;

            Map<String, Object> result = new HashMap<>();
            result.put("from", from);
            result.put("to", to);
            result.put("amount", amount);
            result.put("Rate", rate);
            result.put("convertedAmount", convertedAmount);
            return result;
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch conversion data from API.", e);
        } catch (NumberFormatException | ClassCastException e) {
            throw new IllegalArgumentException("Invalid input data format. Ensure 'amount' is a valid number.", e);
        }
    }

    public Map<String, Object> currencyEquals(Map<String, Object> request) {
        String from = (String) request.get("from");
        String to = (String) request.get("to");

        if (from.equalsIgnoreCase(to)) {
            Map<String, Object> response = new HashMap<>();
            response.put("from", from);
            response.put("to", to);
            response.put("amount", request.get("amount"));
            response.put("Rate", 1.0); // No conversion needed
            response.put("convertedAmount", request.get("amount"));
            return response;
        }

        return convertCurrency(request);
    }
}
