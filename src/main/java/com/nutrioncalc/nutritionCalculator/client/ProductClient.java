package com.nutrioncalc.nutritionCalculator.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutrioncalc.nutritionCalculator.models.dto.ProductFromApi;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ProductClient {
    public ProductFromApi getProductInfo(String productName) {
        URL url;
        HttpURLConnection connection = null;
        try {
            productName = productName.replace(" ", "%20");
            url = new URL("https://api.api-ninjas.com/v1/nutrition?query=" + productName);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            String apiKey = "ZgFb3FbuGCZ4t8hnH9dbAg==7LzrtT64xV4zhNia";
            connection.setRequestProperty("X-Api-Key", apiKey);

            ObjectMapper mapper = new ObjectMapper();
            try (InputStream responseStream = connection.getInputStream()) {
                ProductFromApi[] products = mapper.readValue(responseStream, ProductFromApi[].class);
                if (products.length > 0) {
                    return products[0];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return new ProductFromApi();
    }
}
