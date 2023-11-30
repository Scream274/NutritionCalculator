package com.nutrioncalc.nutritionCalculator.utils;

import com.nutrioncalc.nutritionCalculator.models.DishIngredient;
import com.nutrioncalc.nutritionCalculator.models.RecipeIngredient;

public class IngredientMapper {

    public static DishIngredient toDishIngredient(RecipeIngredient recipeIngredient) {
        DishIngredient dishIngredient = new DishIngredient();
        dishIngredient.setAmount(recipeIngredient.getAmount());
        dishIngredient.setProduct(recipeIngredient.getProduct());
        return dishIngredient;
    }
}
