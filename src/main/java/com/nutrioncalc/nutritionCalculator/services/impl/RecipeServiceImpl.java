package com.nutrioncalc.nutritionCalculator.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutrioncalc.nutritionCalculator.client.ProductClient;
import com.nutrioncalc.nutritionCalculator.models.Product;
import com.nutrioncalc.nutritionCalculator.models.Recipe;
import com.nutrioncalc.nutritionCalculator.models.RecipeIngredient;
import com.nutrioncalc.nutritionCalculator.models.dto.IngredientDto;
import com.nutrioncalc.nutritionCalculator.models.dto.ProductFromApi;
import com.nutrioncalc.nutritionCalculator.models.dto.RecipeDto;
import com.nutrioncalc.nutritionCalculator.repositories.RecipeRepository;
import com.nutrioncalc.nutritionCalculator.services.ProductService;
import com.nutrioncalc.nutritionCalculator.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final ProductClient productClient;
    private final RecipeRepository recipeRepository;
    private final ObjectMapper objectMapper;
    private final ProductService productService;

    @Override
    public Recipe create(RecipeDto recipeDto) {

        Recipe recipe = new Recipe();

        List<RecipeIngredient> ingredients = recipeDto.getIngredients()
                .stream()
                .map(recipeIngredientsFromIngredientsDto())
                .peek(i-> i.setRecipe(recipe))
                .toList();

        recipe.setRecipeName(recipeDto.getRecipeName());
        recipe.setIngredients(ingredients);

        return recipe;
    }

    private Function<IngredientDto, RecipeIngredient> recipeIngredientsFromIngredientsDto() {
        return pr -> {
            ProductFromApi productFromApi = productClient.getProductInfo(pr.getName());
            Product product = objectMapper.convertValue(productFromApi, Product.class);
            product = productService.save(product);
            RecipeIngredient ingredient = new RecipeIngredient();

            ingredient.setProduct(product);
            ingredient.setAmount(pr.getAmount());

            return ingredient;
        };
    }

    @Override
    public void addRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElseThrow();
    }
}
