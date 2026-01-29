package com.example.yummy.data.meal.datasource.remote;

import com.example.yummy.data.meal.model.FavMealRoom;
import com.example.yummy.data.meal.model.PlannedMealRoom;

import java.util.List;

public interface OnMealsFetchedFirestore {
    void onFavSuccess(List<FavMealRoom> mealList);

    void onPlannedSuccess(List<PlannedMealRoom> mealList);

}
