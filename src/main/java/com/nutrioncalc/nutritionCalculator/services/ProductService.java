package com.nutrioncalc.nutritionCalculator.services;

import com.nutrioncalc.nutritionCalculator.models.Product;
import com.nutrioncalc.nutritionCalculator.models.dto.ProductFromApi;

public interface ProductService {
    ProductFromApi getInfo(String name);
    Product save(Product product);
}
