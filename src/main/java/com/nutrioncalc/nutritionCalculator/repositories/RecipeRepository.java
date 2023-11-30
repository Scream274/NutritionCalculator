package com.nutrioncalc.nutritionCalculator.repositories;

import com.nutrioncalc.nutritionCalculator.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
