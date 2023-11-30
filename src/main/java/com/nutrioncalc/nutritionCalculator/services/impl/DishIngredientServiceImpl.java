package com.nutrioncalc.nutritionCalculator.services.impl;

import com.nutrioncalc.nutritionCalculator.models.DishIngredient;
import com.nutrioncalc.nutritionCalculator.repositories.DishIngredientsRepository;
import com.nutrioncalc.nutritionCalculator.services.DishIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishIngredientServiceImpl implements DishIngredientService {

    private final DishIngredientsRepository dishIngredientsRepository;
    @Override
    public DishIngredient save(DishIngredient dishIngredient) {
        return dishIngredientsRepository.save(dishIngredient);
    }
}
