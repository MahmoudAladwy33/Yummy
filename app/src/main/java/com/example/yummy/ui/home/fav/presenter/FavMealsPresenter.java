package com.example.yummy.ui.home.fav.presenter;

import androidx.lifecycle.LiveData;

import com.example.yummy.data.meal.model.MealRoom;

import java.util.List;

public interface FavMealsPresenter {
    LiveData<List<MealRoom>> loadFavMeals();

    void deleteFavMeal(MealRoom mealRoom);
}
