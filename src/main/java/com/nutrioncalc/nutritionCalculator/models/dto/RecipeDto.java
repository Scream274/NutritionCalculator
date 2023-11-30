package com.nutrioncalc.nutritionCalculator.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {
    private String recipeName;
    private List<IngredientDto> ingredients;
}
