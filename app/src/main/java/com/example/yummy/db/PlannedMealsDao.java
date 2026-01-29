package com.example.yummy.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.yummy.data.meal.model.PlannedMealRoom;

import java.util.List;

@Dao
public interface PlannedMealsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPlannedMeal(PlannedMealRoom meal);


    @Query("SELECT * FROM planned_meals")
    LiveData<List<PlannedMealRoom>> getPlannedMeals();

    @Delete
    void deletePlannedMeal(PlannedMealRoom meal);
}
