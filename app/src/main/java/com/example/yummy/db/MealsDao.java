package com.example.yummy.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.yummy.data.meal.model.MealRoom;

import java.util.List;

@Dao
public interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavMeal(MealRoom meal);

    @Query("SELECT * FROM meals")
    LiveData<List<MealRoom>> getFavMeals();

    @Delete
    void deleteFavMeal(MealRoom meal);
}
