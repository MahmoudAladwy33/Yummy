package com.example.yummy.ui.home.fav.view;

import com.example.yummy.data.meal.model.FavMealRoom;

public interface OnFavClickListener {
    void onDeleteFavClick(FavMealRoom meal);

    void onFavMealClick(String mealId);
}
