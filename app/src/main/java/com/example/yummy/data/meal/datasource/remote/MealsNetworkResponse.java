package com.example.yummy.data.meal.datasource.remote;

import com.example.yummy.data.meal.model.Meal;

import java.util.List;

public interface MealsNetworkResponse {

    void onSuccess(List<Meal> mealList);

    void onError(String errorMessage);
}
