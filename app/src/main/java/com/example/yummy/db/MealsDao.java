package com.example.yummy.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.yummy.data.meal.model.FavMealRoom;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertFavMeal(FavMealRoom meal);


    @Query("SELECT * FROM Fav_meals ORDER BY mealId ASC")
    Observable<List<FavMealRoom>> getFavMeals();


    @Delete
    Completable deleteFavMeal(FavMealRoom meal);


    @Query("DELETE FROM Fav_meals")
    Completable clearFavMeals();
}
