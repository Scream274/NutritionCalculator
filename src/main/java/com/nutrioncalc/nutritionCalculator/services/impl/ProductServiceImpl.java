package com.nutrioncalc.nutritionCalculator.services.impl;

import com.nutrioncalc.nutritionCalculator.client.ProductClient;
import com.nutrioncalc.nutritionCalculator.models.dto.ProductFromApi;
import com.nutrioncalc.nutritionCalculator.services.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductClient client;

    public ProductServiceImpl(ProductClient client) {
        this.client = client;
    }

    @Override
    public ProductFromApi getInfo(String name) {
        return client.getProductInfo(name);
    }
}
