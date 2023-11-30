package com.nutrioncalc.nutritionCalculator.services;

import com.nutrioncalc.nutritionCalculator.models.UserNutrition;
import jakarta.transaction.Transactional;

public interface UserService {
    @Transactional
    void addUser(UserNutrition user);

    UserNutrition findByEmail(String email);

    @Transactional
    void update(UserNutrition userNutrition);
}
