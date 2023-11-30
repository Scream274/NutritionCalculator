package com.nutrioncalc.nutritionCalculator.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutrioncalc.nutritionCalculator.models.UserNutrition;
import com.nutrioncalc.nutritionCalculator.models.dto.UserForCreate;
import com.nutrioncalc.nutritionCalculator.services.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder encoder;

    public LoginController(UserService userService, ObjectMapper objectMapper, PasswordEncoder encoder) {
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.encoder = encoder;
    }

    @GetMapping("/registration")
    public String registerView(Model model) {
        model.addAttribute("user", new UserForCreate());
        return "registration";
    }

    @PostMapping("/register")
    public String register(@Valid UserForCreate user, BindingResult bindingResult) {
        UserNutrition userNutrition = objectMapper.convertValue(user, UserNutrition.class);
        userNutrition.calculateBMI();
        userNutrition.setPassword(encoder.encode(user.getPassword()));

        userService.addUser(userNutrition);
        return "index";
    }
}
