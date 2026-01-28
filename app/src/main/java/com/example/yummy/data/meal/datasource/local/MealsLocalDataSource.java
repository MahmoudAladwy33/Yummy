package com.example.yummy.data.meal.datasource.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.yummy.data.meal.model.FavMealRoom;
import com.example.yummy.data.meal.model.PlannedMealRoom;
import com.example.yummy.db.AppDataBase;
import com.example.yummy.db.MealsDao;

import java.util.List;

public class MealsLocalDataSource {

    private MealsDao mealsDao;

    public MealsLocalDataSource(Context context) {
        AppDataBase dataBase = AppDataBase.getInstance(context);
        mealsDao = dataBase.mealsDao();
    }


    public void insertFavMeal(FavMealRoom meal) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mealsDao.insertFavMeal(meal);
            }
        }).start();

    }

    public void deleteFavMeal(FavMealRoom meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealsDao.deleteFavMeal(meal);
            }
        }).start();
    }


    public LiveData<List<FavMealRoom>> getFavMeals() {
        return mealsDao.getFavMeals();
    }


    public void insertPlannedMeal(PlannedMealRoom meal) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mealsDao.insertPlannedMeal(meal);
            }
        }).start();

    }

    public LiveData<List<PlannedMealRoom>> getPlannedMeals() {
        return mealsDao.getPlannedMeals();
    }

    public void deletePlannedMeal(PlannedMealRoom meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealsDao.deletePlannedMeal(meal);
            }
        }).start();
    }

}
