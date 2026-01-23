package com.example.yummy.Network;

import com.example.yummy.data.meal.model.Meal;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealResponse {

    @SerializedName("meals")
    public List<Meal> mealList;

    public MealResponse(List<Meal> mealList) {
        this.mealList = mealList;
    }
}
