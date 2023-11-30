package com.nutrioncalc.nutritionCalculator.services.impl;

import com.nutrioncalc.nutritionCalculator.client.ProductClient;
import com.nutrioncalc.nutritionCalculator.models.Product;
import com.nutrioncalc.nutritionCalculator.models.dto.ProductFromApi;
import com.nutrioncalc.nutritionCalculator.repositories.ProductRepository;
import com.nutrioncalc.nutritionCalculator.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductClient client;
    private final ProductRepository productRepository;

    @Override
    public ProductFromApi getInfo(String name) {
        return client.getProductInfo(name);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
