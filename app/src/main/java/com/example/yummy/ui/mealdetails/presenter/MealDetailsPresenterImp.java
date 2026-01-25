package com.example.yummy.ui.mealdetails.presenter;

import android.content.Context;

import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.data.meal.model.IngredientItem;
import com.example.yummy.data.meal.model.Meal;
import com.example.yummy.data.meal.model.MealRoom;
import com.example.yummy.ui.mealdetails.View.MealDetailsViews;

import java.util.ArrayList;
import java.util.List;

public class MealDetailsPresenterImp implements MealDetailsPresenter {

    MealDetailsViews mealDetailsViews;
    private MealRepo mealRepo;

    public MealDetailsPresenterImp(Context context, MealDetailsViews mealDetailsViews) {
        this.mealRepo = new MealRepo(context);
        this.mealDetailsViews = mealDetailsViews;
    }

    @Override
    public List<IngredientItem> getIngredientsList(Meal meal) {
        List<IngredientItem> list = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            String ingredient = meal.getIngredient(i);
            String measure = meal.getMeasure(i);

            if (ingredient != null && !ingredient.isEmpty()) {
                list.add(new IngredientItem(ingredient, measure));
            }
        }
        return list;
    }

    @Override
    public void addMealToFavorites(Meal meal) {
        mealRepo.insertFavMeal(mapMealToMealRoom(meal));
        mealDetailsViews.addToFavSuccess();
    }

    public MealRoom mapMealToMealRoom(Meal meal) {
        return new MealRoom(
                meal.getMealId(),
                meal.getMealName(),
                meal.getMealAlternate(),
                meal.getCategory(),
                meal.getArea(),
                meal.getInstructions(),
                meal.getMealImg(),
                meal.getTags(),
                meal.getYoutubeUrl(),
                meal.getIngredient(1),
                meal.getIngredient(2),
                meal.getIngredient(3),
                meal.getIngredient(4),
                meal.getIngredient(5),
                meal.getIngredient(6),
                meal.getIngredient(7),
                meal.getIngredient(8),
                meal.getIngredient(9),
                meal.getIngredient(10),
                meal.getIngredient(11),
                meal.getIngredient(12),
                meal.getIngredient(13),
                meal.getIngredient(14),
                meal.getIngredient(15),
                meal.getIngredient(16),
                meal.getIngredient(17),
                meal.getIngredient(18),
                meal.getIngredient(19),
                meal.getIngredient(20),
                meal.getMeasure(1),
                meal.getMeasure(2),
                meal.getMeasure(3),
                meal.getMeasure(4),
                meal.getMeasure(5),
                meal.getMeasure(6),
                meal.getMeasure(7),
                meal.getMeasure(8),
                meal.getMeasure(9),
                meal.getMeasure(10),
                meal.getMeasure(11),
                meal.getMeasure(12),
                meal.getMeasure(13),
                meal.getMeasure(14),
                meal.getMeasure(15),
                meal.getMeasure(16),
                meal.getMeasure(17),
                meal.getMeasure(18),
                meal.getMeasure(19),
                meal.getMeasure(20),
                meal.getSourceUrl(),
                meal.getImageSource(),
                meal.getCreativeCommons(),
                meal.getDateModified()
        );
    }

}
