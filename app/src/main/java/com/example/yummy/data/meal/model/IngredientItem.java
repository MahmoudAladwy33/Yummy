package com.example.yummy.data.meal.model;

public class IngredientItem {

    private String ingredient;
    private String measure;

    public IngredientItem(String ingredient, String measure) {
        this.ingredient = ingredient;
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getMeasure() {
        return measure;
    }
}
