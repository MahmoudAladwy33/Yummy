package com.example.yummy.ui.home.calendar.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.data.meal.model.PlannedMealRoom;
import com.example.yummy.ui.home.calendar.PlannedMealsViews;

import java.util.List;

public class PlannedMealsPresenterImp implements PlannedMealsPresenter {

    MealRepo mealRepo;
    PlannedMealsViews plannedMealsViews;

    public PlannedMealsPresenterImp(Context context, PlannedMealsViews plannedMealsViews) {
        this.mealRepo = new MealRepo(context);
        this.plannedMealsViews = plannedMealsViews;
    }

    @Override
    public LiveData<List<PlannedMealRoom>> loadPlannedMeals() {
        return mealRepo.getPlanbedMeals();

    }

    @Override
    public void deletePlannedMeal(PlannedMealRoom meal) {
        mealRepo.deletePlannedMeal(meal);
        plannedMealsViews.deletePlannedMealSuccess();
    }
}
