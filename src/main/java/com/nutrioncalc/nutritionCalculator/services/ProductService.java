package com.nutrioncalc.nutritionCalculator.services;

import com.nutrioncalc.nutritionCalculator.models.Product;
import com.nutrioncalc.nutritionCalculator.models.dto.ProductFromApi;

import java.util.Optional;

public interface ProductService {
    ProductFromApi getInfo(String name);
    Product save(Product product);

    Optional<Product> findByName(String name);
}
