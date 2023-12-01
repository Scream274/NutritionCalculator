package com.nutrioncalc.nutritionCalculator.models.dto;

import com.nutrioncalc.nutritionCalculator.models.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserForUpdate {

    private String fullName;
    
    @Column(length = 100, unique = true)
    @NotBlank(message = "Email cant be empty")
    @Email
    private String email;
    
    @Column(nullable = false)
    @NotBlank(message = "Password cant be empty")
    private String password;
    
    private Integer weight;
    
    private Integer height;
    
    private Integer age;
    
    private Double BMI;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private Gender gender;
}
