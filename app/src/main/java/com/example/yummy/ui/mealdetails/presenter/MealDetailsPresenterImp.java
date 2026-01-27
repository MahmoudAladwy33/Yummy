package com.example.yummy.ui.mealdetails.presenter;

import android.content.Context;

import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.data.meal.datasource.remote.MealsNetworkResponse;
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
    public void addMealToFavorites(MealRoom meal) {
        mealRepo.insertFavMeal(meal);
        mealDetailsViews.addToFavSuccess();
    }

    @Override
    public void removeMealFromFavorites(MealRoom meal) {
        mealRepo.deleteFavMeal(meal);
        mealDetailsViews.removeFromFavSuccess();

    }

    @Override
    public void getMealById(String id) {
        mealRepo.getMealById(id, new MealsNetworkResponse() {
            @Override
            public void onSuccess(List<Meal> mealList) {
                mealDetailsViews.showMealById(mealList.get(0));
            }

            @Override
            public void onError(String errorMessage) {
                mealDetailsViews.showError(errorMessage);
            }
        });
    }


}
