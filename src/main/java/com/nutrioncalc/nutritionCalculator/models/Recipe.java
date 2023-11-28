package com.nutrioncalc.nutritionCalculator.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredient> ingredients;
}
