package com.example.yummy.data.meal.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "planned_meals")
public class PlannedMealRoom {
    @NonNull
    @PrimaryKey
    private String mealId;

    private String mealName;

    private String area;

    private String mealImg;

    public PlannedMealRoom() {
    }

    public PlannedMealRoom(@NonNull String mealId, String mealName, String area, String mealImg) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.area = area;
        this.mealImg = mealImg;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(@NonNull String mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMealImg() {
        return mealImg;
    }

    public void setMealImg(String mealImg) {
        this.mealImg = mealImg;
    }
}
