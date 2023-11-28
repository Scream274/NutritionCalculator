package com.nutrioncalc.nutritionCalculator.repositories;

import com.nutrioncalc.nutritionCalculator.models.UserNutrition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserNutrition, Long> {
    Optional<UserNutrition> findByEmail(String email);
}
