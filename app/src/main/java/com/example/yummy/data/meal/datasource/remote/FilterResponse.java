package com.example.yummy.data.meal.datasource.remote;

import com.example.yummy.data.meal.model.FilterMeal;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterResponse {

    @SerializedName("meals")
    public List<FilterMeal> mealList;

    public FilterResponse(List<FilterMeal> mealList) {
        this.mealList = mealList;
    }
}
