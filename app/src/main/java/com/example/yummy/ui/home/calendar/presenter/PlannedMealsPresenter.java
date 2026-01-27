package com.example.yummy.ui.home.calendar.presenter;

import androidx.lifecycle.LiveData;

import com.example.yummy.data.meal.model.PlannedMealRoom;

import java.util.List;

public interface PlannedMealsPresenter {

    LiveData<List<PlannedMealRoom>> loadPlannedMeals();

    void deletePlannedMeal(PlannedMealRoom meal);

}
