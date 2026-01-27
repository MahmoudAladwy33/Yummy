package com.example.yummy.data.meal;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.yummy.data.meal.datasource.local.MealsLocalDataSource;
import com.example.yummy.data.meal.datasource.remote.MealsNetworkResponse;
import com.example.yummy.data.meal.datasource.remote.MealsRemoteDataSource;
import com.example.yummy.data.meal.model.FavMealRoom;
import com.example.yummy.data.meal.model.PlannedMealRoom;

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

    public LiveData<List<FavMealRoom>> getFavMeals() {

        return mealsLocalDataSource.getFavMeals();
    }

    public void insertFavMeal(FavMealRoom favMealRoom) {

        mealsLocalDataSource.insertFavMeal(favMealRoom);
    }


    public void deleteFavMeal(FavMealRoom favMealRoom) {
        mealsLocalDataSource.deleteFavMeal(favMealRoom);
    }

    public void getMealById(String id, MealsNetworkResponse mealsNetworkResponse) {
        mealsRemoteDataSource.getMealById(id, mealsNetworkResponse);
    }


    public void insertPlannedMeal(PlannedMealRoom meal) {

        mealsLocalDataSource.insertPlannedMeal(meal);
    }

    public LiveData<List<PlannedMealRoom>> getPlanbedMeals() {

        return mealsLocalDataSource.getPlannedMeals();
    }

    public void deletePlannedMeal(PlannedMealRoom meal) {
        mealsLocalDataSource.deletePlannedMeal(meal);
    }
}
