package com.example.yummy.data.meal.datasource.remote;

import android.content.Context;

public class MealRepo {

    private MealsRemoteDataSource mealsRemoteDataSource;

    public MealRepo(Context context) {
        this.mealsRemoteDataSource = new MealsRemoteDataSource();
    }


    public void getRandomMeal(MealsNetworkResponse mealsNetworkResponse) {
        mealsRemoteDataSource.getRandomMeal(mealsNetworkResponse);
    }
}
