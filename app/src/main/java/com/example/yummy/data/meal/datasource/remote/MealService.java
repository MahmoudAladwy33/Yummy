package com.example.yummy.data.meal.datasource.remote;

import com.example.yummy.data.meal.model.MealResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {

    @GET("random.php")
    Single<MealResponse> getRandomMeal();

    @GET("lookup.php")
    Single<MealResponse> getMealById(@Query("i") String id);


    @GET("filter.php")
    Observable<SearchMealResponse> searchMeals(
            @Query("i") String ingredient,
            @Query("c") String category,
            @Query("a") String area
    );

    @GET("list.php")
    Observable<FilterResponse> getFilter(
            @Query("i") String ingredient,
            @Query("c") String category,
            @Query("a") String area
    );
}

