package com.nutrioncalc.nutritionCalculator.models;

import com.nutrioncalc.nutritionCalculator.models.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "users")
public class UserNutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String fullName;
    @Column(length = 100, unique = true)
    @NotBlank(message = "Email cant be empty")
    @Email    private String email;
    @Column(nullable = false)
    @NotBlank(message = "Password cant be empty")
    private String password;
    private Integer weight;
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private Gender gender = Gender.OTHER;
    private Double BMI;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<DailyStat> dailyStatistic;
}
