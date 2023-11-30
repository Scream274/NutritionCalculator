package com.nutrioncalc.nutritionCalculator.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutrioncalc.nutritionCalculator.models.UserNutrition;
import com.nutrioncalc.nutritionCalculator.models.dto.UserForUpdate;
import com.nutrioncalc.nutritionCalculator.services.UserService;
import com.nutrioncalc.nutritionCalculator.utils.UserMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @GetMapping("/my-account")
    public String showProfilePage(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            UserNutrition user = userService.findByEmail(email);
            UserForUpdate userForUpdate = UserMapper.toUserForUpdate(user);
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
