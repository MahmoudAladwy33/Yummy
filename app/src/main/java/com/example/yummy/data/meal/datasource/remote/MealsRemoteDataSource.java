package com.example.yummy.data.meal.datasource.remote;

import com.example.yummy.Network.MealResponse;
import com.example.yummy.Network.MealService;
import com.example.yummy.Network.Network;
import com.example.yummy.data.meal.model.Meal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsRemoteDataSource {

    private MealService mealService;

    private List<Meal> mealList;

    public MealsRemoteDataSource() {
        this.mealService = Network.getInstance().mealService;
    }

    public void getRandomMeal(MealsNetworkResponse callback) {
        mealService.getRandomMeal().enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                mealList = response.body().mealList;
                callback.onSuccess(mealList);
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });


    }
}
