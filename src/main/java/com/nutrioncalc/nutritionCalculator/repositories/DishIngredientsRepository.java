package com.nutrioncalc.nutritionCalculator.repositories;

import com.nutrioncalc.nutritionCalculator.models.DishIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishIngredientsRepository extends JpaRepository<DishIngredient, Long> {
}
