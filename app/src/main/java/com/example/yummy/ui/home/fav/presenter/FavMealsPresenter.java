package com.example.yummy.ui.home.fav.presenter;

import com.example.yummy.data.meal.model.FavMealRoom;

public interface FavMealsPresenter {

    void loadFavMeals();

    void deleteFavMeal(FavMealRoom favMealRoom);


    void dispose();
}