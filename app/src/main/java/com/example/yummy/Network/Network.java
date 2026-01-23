package com.example.yummy.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    public static Network instance = null;
    public MealService mealService;

    private Network() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
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
