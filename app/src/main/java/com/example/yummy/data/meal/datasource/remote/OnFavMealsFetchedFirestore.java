package com.example.yummy.data.meal.datasource.remote;

import com.example.yummy.data.meal.model.FavMealRoom;

import java.util.List;

public interface OnFavMealsFetchedFirestore {
    void onFavSuccess(List<FavMealRoom> mealList);


}
