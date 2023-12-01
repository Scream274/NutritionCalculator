package com.nutrioncalc.nutritionCalculator.controllers;

import com.nutrioncalc.nutritionCalculator.models.*;
import com.nutrioncalc.nutritionCalculator.models.dto.ProfileStatistic;
import com.nutrioncalc.nutritionCalculator.models.dto.RecipeDto;
import com.nutrioncalc.nutritionCalculator.services.DailyStatService;
import com.nutrioncalc.nutritionCalculator.services.DishIngredientService;
import com.nutrioncalc.nutritionCalculator.services.RecipeService;
import com.nutrioncalc.nutritionCalculator.services.UserService;
import com.nutrioncalc.nutritionCalculator.utils.IngredientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final DailyStatService dailyStatService;
    private final UserService userService;
    private final DishIngredientService dishIngredientService;

    @GetMapping("/recipes")
    public String showAllRecipes(Model model) {
        List<Recipe> recipes = recipeService.getAll();
        model.addAttribute("recipes", recipes);
        return "recipes";
    }

    @GetMapping("/create-recipe")
    public String showRecipeForm(Model model) {
        model.addAttribute("recipeDto", new RecipeDto());
        return "recipe-form";
    }

    @PostMapping("/save-recipe")
    public String saveRecipe(RecipeDto recipeDto) {
        Recipe recipe = recipeService.create(recipeDto);
        recipeService.addRecipe(recipe);

        return "redirect:/recipes";
    }

    @PostMapping("/eat-recipe/{id}")
    @Transactional
    public String eatRecipe(@PathVariable("id") Long id,
                            @RequestParam("amount") int amount) {
        UserNutrition user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        DailyStat stat = dailyStatService.findByDateAndUser(LocalDate.now(), user).orElseGet(() -> {
            DailyStat dailyStat = new DailyStat();
            dailyStat.setUser(user);
            dailyStat.setDate(LocalDate.now());
            dailyStat = dailyStatService.save(dailyStat);

            return dailyStat;
        });

        Recipe recipe = recipeService.findById(id);

        Integer sumAmount = recipe.getIngredients()
                .stream()
                .map(RecipeIngredient::getAmount)
                .reduce(Integer::sum)
                .orElse(0);

        List<DishIngredient> dishIngredients = recipe.getIngredients().stream()
                .map(IngredientMapper::toDishIngredient)
                .peek(i -> i.setDailyStat(stat))
                .peek(i -> i.setAmount(i.getAmount() * amount / sumAmount))
                .map(dishIngredientService::save)
                .toList();

        stat.addDishIngredients(dishIngredients);

        return "redirect:/recipes";
    }

}
