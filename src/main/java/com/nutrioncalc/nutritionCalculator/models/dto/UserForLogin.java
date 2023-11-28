package com.nutrioncalc.nutritionCalculator.models.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserForLogin {

    @Column(length = 100, unique = true)
    @NotBlank(message = "Email cant be empty")
    @Email    private String email;
    @Column(nullable = false)
    @NotBlank(message = "Password cant be empty")
    private String password;
}
