package com.nutrioncalc.nutritionCalculator.services.impl;

import com.nutrioncalc.nutritionCalculator.models.DailyStat;
import com.nutrioncalc.nutritionCalculator.repositories.DailyStatRepository;
import com.nutrioncalc.nutritionCalculator.services.DailyStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DailStatServiceImpl implements DailyStatService {

    private final DailyStatRepository dailyStatRepository;

    @Override
    public Optional<DailyStat> findByDate(LocalDate date) {
        return dailyStatRepository.findDailyStatByDate(date);
    }

    @Override
    public DailyStat save(DailyStat dailyStat) {
        return dailyStatRepository.save(dailyStat);
    }
}
