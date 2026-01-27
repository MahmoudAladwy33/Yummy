package com.example.yummy.data.meal.datasource.remote;

import com.example.yummy.Network.Network;
import com.example.yummy.data.meal.model.Meal;
import com.example.yummy.data.meal.model.MealResponse;

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

                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(mealList);
                } else {

                    callback.onError("No Meal Found");
                }


            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {

            }
        });


    }


    public void getMealById(String id, MealsNetworkResponse callback) {
        mealService.getMealById(id).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                mealList = response.body().mealList;

                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(mealList);
                } else {

                    callback.onError("No Meal Found");
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                callback.onError(t.getMessage());

            }
        });
    }
}
