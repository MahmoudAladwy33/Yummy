package com.example.yummy.ui.mealdetails.presenter;

import com.example.yummy.data.meal.model.IngredientItem;
import com.example.yummy.data.meal.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealDetailsPresenterImp implements MealDetailsPresenter {
    @Override
    public List<IngredientItem> getIngredientsList(Meal meal) {
        List<IngredientItem> list = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            String ingredient = meal.getIngredient(i);
            String measure = meal.getMeasure(i);

            if (ingredient != null && !ingredient.isEmpty()) {
                list.add(new IngredientItem(ingredient, measure));
            }
        }
        return list;
    }
}
