package com.nutrioncalc.nutritionCalculator.services.impl;

import com.nutrioncalc.nutritionCalculator.models.UserNutrition;
import com.nutrioncalc.nutritionCalculator.repositories.UserRepository;
import com.nutrioncalc.nutritionCalculator.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(UserNutrition user) {
        userRepository.save(user);
    }

    @Override
    public UserNutrition findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public void update(UserNutrition userNutrition) {
        UserNutrition currentUser = userRepository.findByEmail(userNutrition.getEmail()).orElseThrow();
        currentUser.setFullName(userNutrition.getFullName());
        currentUser.setAge(userNutrition.getAge());
        currentUser.setWeight(userNutrition.getWeight());
        currentUser.setHeight(userNutrition.getHeight());
        currentUser.setGender(userNutrition.getGender());
        currentUser.setBMI(userNutrition.getBMI());

        userRepository.save(currentUser);
    }
}
