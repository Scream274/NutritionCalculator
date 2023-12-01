package com.nutrioncalc.nutritionCalculator.services;

import com.nutrioncalc.nutritionCalculator.models.DailyStat;
import com.nutrioncalc.nutritionCalculator.models.UserNutrition;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyStatService {
    Optional<DailyStat> findByDate(LocalDate date);
    Optional<DailyStat> findByDateAndUser(LocalDate date, UserNutrition user);

    DailyStat save(DailyStat dailyStat);
}
