package com.example.yummy.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.yummy.data.meal.model.FavMealRoom;
import com.example.yummy.data.meal.model.PlannedMealRoom;

@Database(entities = {FavMealRoom.class, PlannedMealRoom.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance = null;

    public static AppDataBase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext()
                            , AppDataBase.class
                            , "mealsdb")
                    .build();
        }
        return instance;
    }

    public abstract MealsDao mealsDao();

    public abstract PlannedMealsDao plannedMealsDao();
}
