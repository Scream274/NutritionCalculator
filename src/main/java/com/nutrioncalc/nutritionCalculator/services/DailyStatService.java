package com.nutrioncalc.nutritionCalculator.services;

import com.nutrioncalc.nutritionCalculator.models.DailyStat;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyStatService {
    Optional<DailyStat> findByDate(LocalDate date);

    DailyStat save(DailyStat dailyStat);
}
