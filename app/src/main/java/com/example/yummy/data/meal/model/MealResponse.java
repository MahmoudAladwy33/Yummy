package com.example.yummy.data.meal.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealResponse {

    @SerializedName("meals")
    public List<Meal> mealList;

    public MealResponse(List<Meal> mealList) {
        this.mealList = mealList;
    }
}
