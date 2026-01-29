package com.example.yummy.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.yummy.data.meal.model.FavMealRoom;

import java.util.List;

@Dao
public interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavMeal(FavMealRoom meal);


    @Query("SELECT * FROM Fav_meals ORDER BY mealId ASC")
    LiveData<List<FavMealRoom>> getFavMeals();


    @Delete
    void deleteFavMeal(FavMealRoom meal);


    @Query("DELETE FROM Fav_meals")
    void clearFavMeals();
}
