package com.example.yummy.ui.home.fav.view;

import com.example.yummy.data.meal.model.MealRoom;

public interface OnFavClickListener {
    void onDeleteFavClick(MealRoom meal);

    void onFavMealClick(String mealId);
}
