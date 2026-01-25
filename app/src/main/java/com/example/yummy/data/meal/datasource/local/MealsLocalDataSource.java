package com.example.yummy.data.meal.datasource.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.yummy.data.meal.model.MealRoom;
import com.example.yummy.db.AppDataBase;
import com.example.yummy.db.MealsDao;

import java.util.List;

public class MealsLocalDataSource {

    private MealsDao mealsDao;

    public MealsLocalDataSource(Context context) {
        AppDataBase dataBase = AppDataBase.getInstance(context);
        mealsDao = dataBase.mealsDao();
    }


    public void insertFavMeal(MealRoom meal) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mealsDao.insertFavMeal(meal);
            }
        }).start();

    }

    public void deleteFavMeal(MealRoom meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealsDao.deleteFavMeal(meal);
            }
        }).start();
    }


    public LiveData<List<MealRoom>> getFavMeals() {
        return mealsDao.getFavMeals();
    }

}
