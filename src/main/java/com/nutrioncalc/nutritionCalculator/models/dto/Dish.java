package com.nutrioncalc.nutritionCalculator.models.dto;

import com.nutrioncalc.nutritionCalculator.models.DishIngredient;

import lombok.Data;

import java.util.List;

@Data
public class Dish {
    private List<DishIngredient> ingredients;
}
