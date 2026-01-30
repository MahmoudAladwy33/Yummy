package com.example.yummy.data.meal.datasource.remote;

import com.example.yummy.data.meal.model.SearchMeal;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchMealResponse {

    @SerializedName("meals")
    public List<SearchMeal> mealList;

    public SearchMealResponse(List<SearchMeal> mealList) {
        this.mealList = mealList;
    }
}
