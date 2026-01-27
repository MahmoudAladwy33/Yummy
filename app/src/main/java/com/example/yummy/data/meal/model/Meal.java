package com.example.yummy.data.meal.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;


public class Meal implements Parcelable {

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };
    @SerializedName("idMeal")
    private String mealId;
    @SerializedName("strMeal")
    private String mealName;
    @SerializedName("strMealAlternate")
    private String mealAlternate;
    @SerializedName("strCategory")
    private String category;
    @SerializedName("strArea")
    private String area;
    @SerializedName("strInstructions")
    private String instructions;
    @SerializedName("strMealThumb")
    private String mealImg;
    @SerializedName("strTags")
    private String tags;
    @SerializedName("strYoutube")
    private String youtubeUrl;
    // Ingredients
    @SerializedName("strIngredient1")
    private String ingredient1;
    @SerializedName("strIngredient2")
    private String ingredient2;
    @SerializedName("strIngredient3")
    private String ingredient3;
    @SerializedName("strIngredient4")
    private String ingredient4;
    @SerializedName("strIngredient5")
    private String ingredient5;
    @SerializedName("strIngredient6")
    private String ingredient6;
    @SerializedName("strIngredient7")
    private String ingredient7;
    @SerializedName("strIngredient8")
    private String ingredient8;
    @SerializedName("strIngredient9")
    private String ingredient9;
    @SerializedName("strIngredient10")
    private String ingredient10;
    @SerializedName("strIngredient11")
    private String ingredient11;
    @SerializedName("strIngredient12")
    private String ingredient12;
    @SerializedName("strIngredient13")
    private String ingredient13;
    @SerializedName("strIngredient14")
    private String ingredient14;
    @SerializedName("strIngredient15")
    private String ingredient15;
    @SerializedName("strIngredient16")
    private String ingredient16;
    @SerializedName("strIngredient17")
    private String ingredient17;
    @SerializedName("strIngredient18")
    private String ingredient18;
    @SerializedName("strIngredient19")
    private String ingredient19;
    @SerializedName("strIngredient20")
    private String ingredient20;
    // Measures
    @SerializedName("strMeasure1")
    private String measure1;
    @SerializedName("strMeasure2")
    private String measure2;
    @SerializedName("strMeasure3")
    private String measure3;
    @SerializedName("strMeasure4")
    private String measure4;
    @SerializedName("strMeasure5")
    private String measure5;
    @SerializedName("strMeasure6")
    private String measure6;
    @SerializedName("strMeasure7")
    private String measure7;
    @SerializedName("strMeasure8")
    private String measure8;
    @SerializedName("strMeasure9")
    private String measure9;
    @SerializedName("strMeasure10")
    private String measure10;
    @SerializedName("strMeasure11")
    private String measure11;
    @SerializedName("strMeasure12")
    private String measure12;
    @SerializedName("strMeasure13")
    private String measure13;
    @SerializedName("strMeasure14")
    private String measure14;
    @SerializedName("strMeasure15")
    private String measure15;
    @SerializedName("strMeasure16")
    private String measure16;
    @SerializedName("strMeasure17")
    private String measure17;
    @SerializedName("strMeasure18")
    private String measure18;
    @SerializedName("strMeasure19")
    private String measure19;
    @SerializedName("strMeasure20")
    private String measure20;
    // Sources
    @SerializedName("strSource")
    private String sourceUrl;
    @SerializedName("strImageSource")
    private String imageSource;
    @SerializedName("strCreativeCommonsConfirmed")
    private String creativeCommons;
    @SerializedName("dateModified")
    private String dateModified;


    public Meal(String mealId, String mealName, String mealAlternate, String category, String area, String instructions, String mealImg, String tags, String youtubeUrl, String ingredient1, String ingredient2, String ingredient3, String ingredient4, String ingredient5, String ingredient6, String ingredient7, String ingredient8, String ingredient9, String ingredient10, String ingredient11, String ingredient12, String ingredient13, String ingredient14, String ingredient15, String ingredient16, String ingredient17, String ingredient18, String ingredient19, String ingredient20, String measure1, String measure2, String measure3, String measure4, String measure5, String measure6, String measure7, String measure8, String measure9, String measure10, String measure11, String measure12, String measure13, String measure14, String measure15, String measure16, String measure17, String measure18, String measure19, String measure20, String sourceUrl, String imageSource, String creativeCommons, String dateModified) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealAlternate = mealAlternate;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.mealImg = mealImg;
        this.tags = tags;
        this.youtubeUrl = youtubeUrl;
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.ingredient3 = ingredient3;
        this.ingredient4 = ingredient4;
        this.ingredient5 = ingredient5;
        this.ingredient6 = ingredient6;
        this.ingredient7 = ingredient7;
        this.ingredient8 = ingredient8;
        this.ingredient9 = ingredient9;
        this.ingredient10 = ingredient10;
        this.ingredient11 = ingredient11;
        this.ingredient12 = ingredient12;
        this.ingredient13 = ingredient13;
        this.ingredient14 = ingredient14;
        this.ingredient15 = ingredient15;
        this.ingredient16 = ingredient16;
        this.ingredient17 = ingredient17;
        this.ingredient18 = ingredient18;
        this.ingredient19 = ingredient19;
        this.ingredient20 = ingredient20;
        this.measure1 = measure1;
        this.measure2 = measure2;
        this.measure3 = measure3;
        this.measure4 = measure4;
        this.measure5 = measure5;
        this.measure6 = measure6;
        this.measure7 = measure7;
        this.measure8 = measure8;
        this.measure9 = measure9;
        this.measure10 = measure10;
        this.measure11 = measure11;
        this.measure12 = measure12;
        this.measure13 = measure13;
        this.measure14 = measure14;
        this.measure15 = measure15;
        this.measure16 = measure16;
        this.measure17 = measure17;
        this.measure18 = measure18;
        this.measure19 = measure19;
        this.measure20 = measure20;
        this.sourceUrl = sourceUrl;
        this.imageSource = imageSource;
        this.creativeCommons = creativeCommons;
        this.dateModified = dateModified;
    }

    protected Meal(Parcel in) {
        mealId = in.readString();
        mealName = in.readString();
        mealAlternate = in.readString();
        category = in.readString();
        area = in.readString();
        instructions = in.readString();
        mealImg = in.readString();
        tags = in.readString();
        youtubeUrl = in.readString();
        ingredient1 = in.readString();
        ingredient2 = in.readString();
        ingredient3 = in.readString();
        ingredient4 = in.readString();
        ingredient5 = in.readString();
        ingredient6 = in.readString();
        ingredient7 = in.readString();
        ingredient8 = in.readString();
        ingredient9 = in.readString();
        ingredient10 = in.readString();
        ingredient11 = in.readString();
        ingredient12 = in.readString();
        ingredient13 = in.readString();
        ingredient14 = in.readString();
        ingredient15 = in.readString();
        ingredient16 = in.readString();
        ingredient17 = in.readString();
        ingredient18 = in.readString();
        ingredient19 = in.readString();
        ingredient20 = in.readString();
        measure1 = in.readString();
        measure2 = in.readString();
        measure3 = in.readString();
        measure4 = in.readString();
        measure5 = in.readString();
        measure6 = in.readString();
        measure7 = in.readString();
        measure8 = in.readString();
        measure9 = in.readString();
        measure10 = in.readString();
        measure11 = in.readString();
        measure12 = in.readString();
        measure13 = in.readString();
        measure14 = in.readString();
        measure15 = in.readString();
        measure16 = in.readString();
        measure17 = in.readString();
        measure18 = in.readString();
        measure19 = in.readString();
        measure20 = in.readString();
        sourceUrl = in.readString();
        imageSource = in.readString();
        creativeCommons = in.readString();
        dateModified = in.readString();
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealAlternate() {
        return mealAlternate;
    }

    public void setMealAlternate(String mealAlternate) {
        this.mealAlternate = mealAlternate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getMealImg() {
        return mealImg;
    }

    public void setMealImg(String mealImg) {
        this.mealImg = mealImg;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getIngredient(int index) {
        switch (index) {
            case 1:
                return ingredient1;
            case 2:
                return ingredient2;
            case 3:
                return ingredient3;
            case 4:
                return ingredient4;
            case 5:
                return ingredient5;
            case 6:
                return ingredient6;
            case 7:
                return ingredient7;
            case 8:
                return ingredient8;
            case 9:
                return ingredient9;
            case 10:
                return ingredient10;
            case 11:
                return ingredient11;
            case 12:
                return ingredient12;
            case 13:
                return ingredient13;
            case 14:
                return ingredient14;
            case 15:
                return ingredient15;
            case 16:
                return ingredient16;
            case 17:
                return ingredient17;
            case 18:
                return ingredient18;
            case 19:
                return ingredient19;
            case 20:
                return ingredient20;
        }
        return null;
    }

    public String getMeasure(int index) {
        switch (index) {
            case 1:
                return measure1;
            case 2:
                return measure2;
            case 3:
                return measure3;
            case 4:
                return measure4;
            case 5:
                return measure5;
            case 6:
                return measure6;
            case 7:
                return measure7;
            case 8:
                return measure8;
            case 9:
                return measure9;
            case 10:
                return measure10;
            case 11:
                return measure11;
            case 12:
                return measure12;
            case 13:
                return measure13;
            case 14:
                return measure14;
            case 15:
                return measure15;
            case 16:
                return measure16;
            case 17:
                return measure17;
            case 18:
                return measure18;
            case 19:
                return measure19;
            case 20:
                return measure20;


        }
        return null;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getCreativeCommons() {
        return creativeCommons;
    }

    public void setCreativeCommons(String creativeCommons) {
        this.creativeCommons = creativeCommons;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(mealId);
        parcel.writeString(mealName);
        parcel.writeString(mealAlternate);
        parcel.writeString(category);
        parcel.writeString(area);
        parcel.writeString(instructions);
        parcel.writeString(mealImg);
        parcel.writeString(tags);
        parcel.writeString(youtubeUrl);
        parcel.writeString(ingredient1);
        parcel.writeString(ingredient2);
        parcel.writeString(ingredient3);
        parcel.writeString(ingredient4);
        parcel.writeString(ingredient5);
        parcel.writeString(ingredient6);
        parcel.writeString(ingredient7);
        parcel.writeString(ingredient8);
        parcel.writeString(ingredient9);
        parcel.writeString(ingredient10);
        parcel.writeString(ingredient11);
        parcel.writeString(ingredient12);
        parcel.writeString(ingredient13);
        parcel.writeString(ingredient14);
        parcel.writeString(ingredient15);
        parcel.writeString(ingredient16);
        parcel.writeString(ingredient17);
        parcel.writeString(ingredient18);
        parcel.writeString(ingredient19);
        parcel.writeString(ingredient20);
        parcel.writeString(measure1);
        parcel.writeString(measure2);
        parcel.writeString(measure3);
        parcel.writeString(measure4);
        parcel.writeString(measure5);
        parcel.writeString(measure6);
        parcel.writeString(measure7);
        parcel.writeString(measure8);
        parcel.writeString(measure9);
        parcel.writeString(measure10);
        parcel.writeString(measure11);
        parcel.writeString(measure12);
        parcel.writeString(measure13);
        parcel.writeString(measure14);
        parcel.writeString(measure15);
        parcel.writeString(measure16);
        parcel.writeString(measure17);
        parcel.writeString(measure18);
        parcel.writeString(measure19);
        parcel.writeString(measure20);
        parcel.writeString(sourceUrl);
        parcel.writeString(imageSource);
        parcel.writeString(creativeCommons);
        parcel.writeString(dateModified);
    }
}
