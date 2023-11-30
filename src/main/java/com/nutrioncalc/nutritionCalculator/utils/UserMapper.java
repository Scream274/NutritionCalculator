package com.nutrioncalc.nutritionCalculator.utils;

import com.nutrioncalc.nutritionCalculator.models.UserNutrition;
import com.nutrioncalc.nutritionCalculator.models.dto.UserForUpdate;

public class UserMapper {
    
    public static UserForUpdate toUserForUpdate(UserNutrition userNutrition) {
        return UserForUpdate
            .builder()
            .fullName(userNutrition.getFullName())
            .email(userNutrition.getEmail())
            .password(userNutrition.getPassword())
            .weight(userNutrition.getWeight())
            .height(userNutrition.getHeight())
            .age(userNutrition.getAge())
            .BMI(userNutrition.getBMI())
            .build();
    }
}
