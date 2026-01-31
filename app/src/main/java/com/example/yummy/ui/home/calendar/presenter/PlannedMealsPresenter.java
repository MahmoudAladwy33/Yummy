package com.example.yummy.ui.home.calendar.presenter;

import com.example.yummy.data.meal.model.PlannedMealRoom;

public interface PlannedMealsPresenter {

    void loadPlannedMeals();

    void deletePlannedMeal(PlannedMealRoom meal);

    void dispose();

}
