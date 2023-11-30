package com.nutrioncalc.nutritionCalculator.repositories;

import com.nutrioncalc.nutritionCalculator.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
