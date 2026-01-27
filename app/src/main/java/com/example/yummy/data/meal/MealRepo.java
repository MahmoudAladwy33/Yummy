package com.example.yummy.data.meal;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.yummy.data.meal.datasource.local.MealsLocalDataSource;
import com.example.yummy.data.meal.datasource.remote.MealsNetworkResponse;
import com.example.yummy.data.meal.datasource.remote.MealsRemoteDataSource;
import com.example.yummy.data.meal.model.MealRoom;

import java.util.List;

public class MealRepo {

    private MealsRemoteDataSource mealsRemoteDataSource;
    private MealsLocalDataSource mealsLocalDataSource;

    public MealRepo(Context context) {
        this.mealsRemoteDataSource = new MealsRemoteDataSource();
        this.mealsLocalDataSource = new MealsLocalDataSource(context);
    }


    public void getRandomMeal(MealsNetworkResponse mealsNetworkResponse) {
        mealsRemoteDataSource.getRandomMeal(mealsNetworkResponse);
    }

    public LiveData<List<MealRoom>> getFavMeals() {

        return mealsLocalDataSource.getFavMeals();
    }

    public void insertFavMeal(MealRoom mealRoom) {

        mealsLocalDataSource.insertFavMeal(mealRoom);
    }


    public void deleteFavMeal(MealRoom mealRoom) {
        mealsLocalDataSource.deleteFavMeal(mealRoom);
    }

    public void getMealById(String id, MealsNetworkResponse mealsNetworkResponse) {
        mealsRemoteDataSource.getMealById(id, mealsNetworkResponse);
    }
}
