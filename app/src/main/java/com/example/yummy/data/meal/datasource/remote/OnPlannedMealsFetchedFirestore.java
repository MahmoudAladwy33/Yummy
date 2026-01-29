package com.example.yummy.data.meal.datasource.remote;

import com.example.yummy.data.meal.model.PlannedMealRoom;

import java.util.List;

public interface OnPlannedMealsFetchedFirestore {
    void onPlannedSuccess(List<PlannedMealRoom> mealList);
}
