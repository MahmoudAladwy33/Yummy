package com.example.yummy.data.meal.model;

import com.google.gson.annotations.SerializedName;

public class FilterMeal {

    @SerializedName("strIngredient")
    private String IngredientName;

    @SerializedName("strCategory")
    private String CategoryName;
    @SerializedName("strArea")
    private String AreaName;

    public FilterMeal() {
    }

    public FilterMeal(String ingredientId, String ingredientName, String ingredientDescription, String ingredientImg, String ingredientType, String categoryName, String areaName) {


        IngredientName = ingredientName;
        CategoryName = categoryName;
        AreaName = areaName;
    }


    public String getIngredientName() {
        return IngredientName;
    }

    public void setIngredientName(String ingredientName) {
        IngredientName = ingredientName;
    }


    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }
}
