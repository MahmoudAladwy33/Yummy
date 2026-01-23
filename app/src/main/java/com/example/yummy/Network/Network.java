package com.example.yummy.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    public static Network instance = null;

    private Network() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static Network getInstance() {

        if (instance == null) {

            instance = new Network();
        }
        return instance;
    }
}
