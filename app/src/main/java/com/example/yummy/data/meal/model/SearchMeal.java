package com.example.yummy.data.meal.model;

import com.google.gson.annotations.SerializedName;

public class SearchMeal {
    @SerializedName("strMeal")
    private String MealName;
    @SerializedName("strMealThumb")
    private String MealImg;
    @SerializedName("idMeal")
    private String MealId;

    public SearchMeal() {
    }

    public SearchMeal(String mealName, String mealImg, String mealId) {
        MealName = mealName;
        MealImg = mealImg;
        MealId = mealId;
    }

    public String getMealName() {
        return MealName;
    }

    public void setMealName(String mealName) {
        MealName = mealName;
    }

    public String getMealImg() {
        return MealImg;
    }

    public void setMealImg(String mealImg) {
        MealImg = mealImg;
    }

    public String getMealId() {
        return MealId;
    }

    public void setMealId(String mealId) {
        MealId = mealId;
    }
}
