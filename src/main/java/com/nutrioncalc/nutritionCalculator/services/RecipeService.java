package com.nutrioncalc.nutritionCalculator.services;

import com.nutrioncalc.nutritionCalculator.models.Recipe;
import com.nutrioncalc.nutritionCalculator.models.dto.RecipeDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RecipeService {
    Recipe create(RecipeDto recipeDto);
    void addRecipe(Recipe recipe);

    List<Recipe> getAll();

    Recipe findById(Long id);
}
