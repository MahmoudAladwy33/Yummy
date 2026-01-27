package com.example.yummy.ui.home.home.presenter;

import android.content.Context;

import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.data.meal.datasource.remote.MealsNetworkResponse;
import com.example.yummy.data.meal.model.Meal;
import com.example.yummy.ui.home.home.view.RandomMealViews;

import java.util.List;

public class RandomMealPresenterImp implements RandomMealPresenter {

    private MealRepo mealRepo;
    private RandomMealViews randomMealViews;

    public RandomMealPresenterImp(Context context, RandomMealViews randomMealViews) {
        this.mealRepo = new MealRepo(context);
        this.randomMealViews = randomMealViews;
    }

    @Override
    public void getRandomMeals() {
        randomMealViews.showLoading();
        mealRepo.getRandomMeal(new MealsNetworkResponse() {
            @Override
            public void onSuccess(List<Meal> mealList) {
                randomMealViews.hideLoading();
                randomMealViews.showRandomMeal(mealList.get(0));

            }

            @Override
            public void onError(String errorMessage) {
                randomMealViews.hideLoading();
                randomMealViews.showError(errorMessage);
            }
        });
    }
}
