package com.example.yummy.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.yummy.data.meal.model.PlannedMealRoom;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface PlannedMealsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertPlannedMeal(PlannedMealRoom meal);


    @Query("SELECT * FROM planned_meals ORDER BY mealId ASC")
    Observable<List<PlannedMealRoom>> getPlannedMeals();

    @Delete
    Completable deletePlannedMeal(PlannedMealRoom meal);

    @Query("DELETE FROM Planned_meals")
    Completable clearPlannedMeals();
}
