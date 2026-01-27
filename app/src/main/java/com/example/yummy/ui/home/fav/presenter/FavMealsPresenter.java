package com.example.yummy.ui.home.fav.presenter;

import androidx.lifecycle.LiveData;

import com.example.yummy.data.meal.model.FavMealRoom;

import java.util.List;

public interface FavMealsPresenter {
    LiveData<List<FavMealRoom>> loadFavMeals();

    void deleteFavMeal(FavMealRoom favMealRoom);
}
