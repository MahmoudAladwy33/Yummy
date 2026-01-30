package com.example.yummy.Network;

import com.example.yummy.data.meal.datasource.remote.MealService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    public static Network instance = null;
    public MealService mealService;

    private Network() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        mealService = retrofit.create(MealService.class);
    }


    public static Network getInstance() {

        if (instance == null) {

            instance = new Network();
        }
        return instance;
    }
}
