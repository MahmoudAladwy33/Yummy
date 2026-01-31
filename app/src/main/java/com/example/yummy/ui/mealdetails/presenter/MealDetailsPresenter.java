package com.example.yummy.ui.mealdetails.presenter;

import com.example.yummy.data.meal.model.IngredientItem;
import com.example.yummy.data.meal.model.Meal;

import java.util.List;

public interface MealDetailsPresenter {

    List<IngredientItem> getIngredientsList(Meal meal);

    void addMealToFavorites(Meal meal);

    void removeMealFromFavorites(Meal meal);

    void getMealById(String id);

    void addMealToCalendar(Meal meal, int year, int month, int day, int hour, int minute);

    void dispose();


}
