package com.example.yummy.data.meal.datasource.local;

import android.content.Context;

import com.example.yummy.data.meal.model.FavMealRoom;
import com.example.yummy.data.meal.model.PlannedMealRoom;
import com.example.yummy.db.AppDataBase;
import com.example.yummy.db.MealsDao;
import com.example.yummy.db.PlannedMealsDao;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealsLocalDataSource {

    private MealsDao mealsDao;
    private PlannedMealsDao plannedMealsDao;

    public MealsLocalDataSource(Context context) {
        AppDataBase dataBase = AppDataBase.getInstance(context);
        mealsDao = dataBase.mealsDao();
        plannedMealsDao = dataBase.plannedMealsDao();
    }


    public Completable insertFavMeal(FavMealRoom meal) {

        return mealsDao.insertFavMeal(meal);

    }

    public Completable deleteFavMeal(FavMealRoom meal) {
        return mealsDao.deleteFavMeal(meal);
    }


    public Observable<List<FavMealRoom>> getFavMeals() {
        return mealsDao.getFavMeals();
    }


    public Completable insertPlannedMeal(PlannedMealRoom meal) {
        return plannedMealsDao.insertPlannedMeal(meal);
    }

    public Observable<List<PlannedMealRoom>> getPlannedMeals() {
        return plannedMealsDao.getPlannedMeals();
    }

    public Completable deletePlannedMeal(PlannedMealRoom meal) {
        return plannedMealsDao.deletePlannedMeal(meal);
    }

    public Completable clearAllTables() {
        return mealsDao.clearFavMeals()
                .andThen(plannedMealsDao.clearPlannedMeals());
    }

}
