package com.example.yummy.data.meal;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.yummy.data.meal.datasource.local.MealsLocalDataSource;
import com.example.yummy.data.meal.datasource.remote.FirestoreDataSource;
import com.example.yummy.data.meal.datasource.remote.MealsNetworkResponse;
import com.example.yummy.data.meal.datasource.remote.MealsRemoteDataSource;
import com.example.yummy.data.meal.datasource.remote.OnCompleteFirestoreListener;
import com.example.yummy.data.meal.datasource.remote.OnFavMealsFetchedFirestore;
import com.example.yummy.data.meal.datasource.remote.OnPlannedMealsFetchedFirestore;
import com.example.yummy.data.meal.model.FavMealRoom;
import com.example.yummy.data.meal.model.MealResponse;
import com.example.yummy.data.meal.model.PlannedMealRoom;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class MealRepo {

    private MealsRemoteDataSource mealsRemoteDataSource;
    private MealsLocalDataSource mealsLocalDataSource;

    private FirestoreDataSource firestoreDataSource;

    public MealRepo(Context context) {
        this.mealsRemoteDataSource = new MealsRemoteDataSource();
        this.mealsLocalDataSource = new MealsLocalDataSource(context);
        this.firestoreDataSource = new FirestoreDataSource();
    }


    public void getRandomMeal(MealsNetworkResponse mealsNetworkResponse) {
        mealsRemoteDataSource.getRandomMeal(mealsNetworkResponse);
    }

    public LiveData<List<FavMealRoom>> getFavMeals() {

        return mealsLocalDataSource.getFavMeals();
    }


    public void clearFavMeals() {
        mealsLocalDataSource.clearAllTables();
    }


    public void insertFavMeal(FavMealRoom favMealRoom) {

        firestoreDataSource.addToFavorites(favMealRoom, new OnCompleteFirestoreListener() {

            @Override
            public void onSuccess() {
                mealsLocalDataSource.insertFavMeal(favMealRoom);
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });


    }


    public void deleteFavMeal(FavMealRoom favMealRoom) {
        firestoreDataSource.removeFromFavorites(favMealRoom.getMealId(), new OnCompleteFirestoreListener() {
            @Override
            public void onSuccess() {
                mealsLocalDataSource.deleteFavMeal(favMealRoom);
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });


    }

    public void getMealById(String id, MealsNetworkResponse mealsNetworkResponse) {
        mealsRemoteDataSource.getMealById(id, mealsNetworkResponse);
    }


    public void insertPlannedMeal(PlannedMealRoom meal) {

        firestoreDataSource.addToPlanned(meal, new OnCompleteFirestoreListener() {
            @Override
            public void onSuccess() {
                mealsLocalDataSource.insertPlannedMeal(meal);
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });


    }

    public LiveData<List<PlannedMealRoom>> getPlanbedMeals() {

        return mealsLocalDataSource.getPlannedMeals();

    }

    public void deletePlannedMeal(PlannedMealRoom meal) {
        firestoreDataSource.removeFromPlanned(meal.getMealId(), new OnCompleteFirestoreListener() {
            @Override
            public void onSuccess() {
                mealsLocalDataSource.deletePlannedMeal(meal);
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });


    }

    public void syncFavFromFirestore() {
        firestoreDataSource.getFavorites(new OnFavMealsFetchedFirestore() {
            @Override
            public void onFavSuccess(List<FavMealRoom> mealList) {
                for (FavMealRoom meal : mealList) {
                    mealsLocalDataSource.insertFavMeal(meal);
                }
            }

        });
    }

    public void syncPlanedFromFirestore() {
        firestoreDataSource.getPlanned(new OnPlannedMealsFetchedFirestore() {
            @Override
            public void onPlannedSuccess(List<PlannedMealRoom> mealList) {
                for (PlannedMealRoom meal : mealList) {
                    mealsLocalDataSource.insertPlannedMeal(meal);
                }
            }
        });

    }


    public Observable<MealResponse> searchMeals(String ingredient, String category, String area) {
        return mealsRemoteDataSource.searchMeals(ingredient, category, area);
    }


}
