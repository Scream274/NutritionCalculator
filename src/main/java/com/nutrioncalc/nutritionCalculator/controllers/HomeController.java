package com.nutrioncalc.nutritionCalculator.controllers;

import com.nutrioncalc.nutritionCalculator.models.dto.ProductFromApi;
import com.nutrioncalc.nutritionCalculator.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/", "/home"})
    public String index() {
        return "index";
    }

    @PostMapping("/get-product-info")
    public String getProductInfo(@RequestParam("productName") String productName, Model model) {
        ProductFromApi productInfo = productService.getInfo(productName);

        if (productInfo.getName() == null) {
            model.addAttribute("errorMessage", "Product '" + productName + "' was not found!");
        } else {
            model.addAttribute("product", productInfo);
        }
        return "index";
    }
}
