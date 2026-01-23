package com.example.yummy.ui.home.home.view;

import com.example.yummy.data.meal.model.Meal;

public interface RandomMealViews {
    void showRandomMeal(Meal meal);

    void showError(String message);

    void showLoading();

    void hideLoading();
}
