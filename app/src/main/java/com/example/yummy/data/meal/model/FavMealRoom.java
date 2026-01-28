package com.example.yummy.data.meal.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Fav_meals")
public class FavMealRoom {

    @PrimaryKey
    @NonNull
    private String mealId;

    private String mealName;

    private String area;

    private String mealImg;


    public FavMealRoom() {
    }

    public FavMealRoom(@NonNull String mealId, String mealName, String mealAlternate, String category, String area, String instructions, String mealImg, String tags, String youtubeUrl, String ingredient1, String ingredient2, String ingredient3, String ingredient4, String ingredient5, String ingredient6, String ingredient7, String ingredient8, String ingredient9, String ingredient10, String ingredient11, String ingredient12, String ingredient13, String ingredient14, String ingredient15, String ingredient16, String ingredient17, String ingredient18, String ingredient19, String ingredient20, String measure1, String measure2, String measure3, String measure4, String measure5, String measure6, String measure7, String measure8, String measure9, String measure10, String measure11, String measure12, String measure13, String measure14, String measure15, String measure16, String measure17, String measure18, String measure19, String measure20, String sourceUrl, String imageSource, String creativeCommons, String dateModified) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.area = area;

        this.mealImg = mealImg;

    }

    public FavMealRoom(Meal meal) {
        this.mealId = meal.getMealId();
        this.mealName = meal.getMealName();
        this.area = meal.getArea();
        this.mealImg = meal.getMealImg();
    }

    @NonNull
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
