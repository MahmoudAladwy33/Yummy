package com.example.yummy.data.meal.datasource.remote;

import com.example.yummy.data.meal.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {

    @GET("random.php")
    Call<MealResponse> getRandomMeal();
}
