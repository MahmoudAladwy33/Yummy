package com.example.yummy.ui.mealdetails.presenter;

import com.example.yummy.data.meal.model.IngredientItem;
import com.example.yummy.data.meal.model.Meal;

import java.util.List;

public interface MealDetailsPresenter {

    List<IngredientItem> getIngredientsList(Meal meal);
}
