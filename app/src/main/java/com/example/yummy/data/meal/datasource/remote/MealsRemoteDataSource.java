package com.example.yummy.data.meal.datasource.remote;

import com.example.yummy.Network.Network;
import com.example.yummy.data.meal.model.MealResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class MealsRemoteDataSource {

    private MealService mealService;

    public MealsRemoteDataSource() {
        this.mealService = Network.getInstance().mealService;
    }

    public Single<MealResponse> getRandomMeal() {
        return mealService.getRandomMeal();
    }


    public Single<MealResponse> getMealById(String id) {
        return mealService.getMealById(id);
    }

    public Observable<SearchMealResponse> searchMeals(String ingredient, String category, String area) {
        return mealService.searchMeals(ingredient, category, area);
    }

    public Observable<FilterResponse> getFilter(String ingredient, String category, String area) {
        return mealService.getFilter(ingredient, category, area);
    }


}
