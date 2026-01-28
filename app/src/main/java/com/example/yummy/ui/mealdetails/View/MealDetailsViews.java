package com.example.yummy.ui.mealdetails.View;

import com.example.yummy.data.meal.model.Meal;

public interface MealDetailsViews {
    void addToFavSuccess();

    void removeFromFavSuccess();

    void showMealById(Meal meal);

    void showError(String errorMessage);

    void showLoading();

    void hideLoading();

    void addToCalendarSuccess(String displayDate);


}
