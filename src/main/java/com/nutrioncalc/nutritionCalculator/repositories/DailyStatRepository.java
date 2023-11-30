package com.nutrioncalc.nutritionCalculator.repositories;

import com.nutrioncalc.nutritionCalculator.models.DailyStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyStatRepository extends JpaRepository<DailyStat, Long> {
    Optional<DailyStat> findDailyStatByDate(LocalDate date);
}
