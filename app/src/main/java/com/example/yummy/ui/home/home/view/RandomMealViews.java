package com.example.yummy.ui.home.home.view;

import com.example.yummy.data.meal.model.Meal;

import java.util.List;

public interface RandomMealViews {
    void onSuccess(List<Meal> mealList);

    void onError(String message);
}
