package com.nutrioncalc.nutritionCalculator.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutrioncalc.nutritionCalculator.models.UserNutrition;
import com.nutrioncalc.nutritionCalculator.models.dto.UserForCreate;
import com.nutrioncalc.nutritionCalculator.models.dto.UserForUpdate;
import com.nutrioncalc.nutritionCalculator.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/my-account")
    public String showProfilePage(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            UserNutrition user = userService.findByEmail(email);
            UserForUpdate userForUpdate = objectMapper.convertValue(user, UserForUpdate.class);
            model.addAttribute("user", userForUpdate);
            return "profile";
        }
        return "redirect:/login";
    }

    @PostMapping("/update-profile")
    public String updateUser(@Valid UserForUpdate user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        UserNutrition userNutrition = objectMapper.convertValue(user, UserNutrition.class);
        userNutrition.calculateBMI();

        userService.update(userNutrition);
        redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
        return "redirect:/my-account";
    }
}
