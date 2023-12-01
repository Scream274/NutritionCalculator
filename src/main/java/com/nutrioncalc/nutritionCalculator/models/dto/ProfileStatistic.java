package com.nutrioncalc.nutritionCalculator.models.dto;

import com.nutrioncalc.nutritionCalculator.models.DishIngredient;
import com.nutrioncalc.nutritionCalculator.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileStatistic {
    private Double calories = 0.0;
    private Double fat = 0.0;
    private Double carbohydrates = 0.0;

    public static ProfileStatistic fromIngredients(List<DishIngredient> ingredients) {
        double totalCalories = 0.0;
        double totalFat = 0.0;
        double totalCarbohydrates = 0.0;

        for (DishIngredient ingredient : ingredients) {
            Product product = ingredient.getProduct();
            Integer ingredientAmount = ingredient.getAmount();

            if (product != null) {
                totalCalories += product.getCalories()/100 * ingredientAmount;
                totalFat += product.getFat_total_g()/100 * ingredientAmount;
                totalCarbohydrates += product.getCarbohydrates_total_g()/100 * ingredientAmount;
            }
        }

        totalCalories =  Math.round(totalCalories * 10.0) / 10.0;
        totalFat =  Math.round(totalFat * 10.0) / 10.0;
        totalCarbohydrates =  Math.round(totalCarbohydrates * 10.0) / 10.0;

        return new ProfileStatistic(totalCalories, totalFat, totalCarbohydrates);
    }
}
