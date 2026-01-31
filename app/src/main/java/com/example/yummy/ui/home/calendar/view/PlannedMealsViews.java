package com.example.yummy.ui.home.calendar.view;

import com.example.yummy.data.meal.model.PlannedMealRoom;

import java.util.List;

public interface PlannedMealsViews {

    void showPlannedMeals(List<PlannedMealRoom> plannedMeals);

    void deletePlannedMealSuccess();

    void showErrorMessage(String error);
}
