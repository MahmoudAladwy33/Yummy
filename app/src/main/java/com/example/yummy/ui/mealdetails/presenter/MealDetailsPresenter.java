package com.example.yummy.ui.mealdetails.presenter;

import com.example.yummy.data.meal.model.IngredientItem;
import com.example.yummy.data.meal.model.Meal;
import com.example.yummy.data.meal.model.MealRoom;

import java.util.List;

public interface MealDetailsPresenter {

    List<IngredientItem> getIngredientsList(Meal meal);

    void addMealToFavorites(MealRoom meal);

    void removeMealFromFavorites(MealRoom meal);
}
