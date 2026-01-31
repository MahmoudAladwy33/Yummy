package com.example.yummy.ui.home.fav.view;

import com.example.yummy.data.meal.model.FavMealRoom;

import java.util.List;

public interface FavMealsViews {


    void showFavMeals(List<FavMealRoom> favMeals);

    void deleteFavMealSuccess();


    void showErrorMessage(String error);
}