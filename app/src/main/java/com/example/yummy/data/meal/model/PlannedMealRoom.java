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

    private String plannedDate;

    public PlannedMealRoom() {
    }

    public PlannedMealRoom(@NonNull String mealId, String mealName, String area, String mealImg, String plannedDate) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.area = area;
        this.mealImg = mealImg;
        this.plannedDate = plannedDate;
    }

    public PlannedMealRoom(Meal meal) {
        this.mealId = meal.getMealId();
        this.mealName = meal.getMealName();
        this.area = meal.getArea();
        this.mealImg = meal.getMealImg();
    }

    public String getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(String plannedDate) {
        this.plannedDate = plannedDate;
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
