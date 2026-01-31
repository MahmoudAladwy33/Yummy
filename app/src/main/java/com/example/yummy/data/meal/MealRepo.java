package com.example.yummy.data.meal;

import android.content.Context;

import com.example.yummy.data.meal.datasource.local.MealsLocalDataSource;
import com.example.yummy.data.meal.datasource.remote.FilterResponse;
import com.example.yummy.data.meal.datasource.remote.FirestoreDataSource;
import com.example.yummy.data.meal.datasource.remote.MealsRemoteDataSource;
import com.example.yummy.data.meal.datasource.remote.SearchMealResponse;
import com.example.yummy.data.meal.model.FavMealRoom;
import com.example.yummy.data.meal.model.MealResponse;
import com.example.yummy.data.meal.model.PlannedMealRoom;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class MealRepo {

    private MealsRemoteDataSource mealsRemoteDataSource;
    private MealsLocalDataSource mealsLocalDataSource;

    private FirestoreDataSource firestoreDataSource;

    public MealRepo(Context context) {
        this.mealsRemoteDataSource = new MealsRemoteDataSource();
        this.mealsLocalDataSource = new MealsLocalDataSource(context);
        this.firestoreDataSource = new FirestoreDataSource();
    }


    public Single<MealResponse> getRandomMeal() {
        return mealsRemoteDataSource.getRandomMeal();

    }

    public Observable<List<FavMealRoom>> getFavMeals() {
        return mealsLocalDataSource.getFavMeals();
    }

    public Completable clearAllTables() {
        return mealsLocalDataSource.clearAllTables();
    }


    public Completable insertFavMeal(FavMealRoom favMealRoom) {
        return firestoreDataSource.addToFavorites(favMealRoom)
                .andThen(mealsLocalDataSource.insertFavMeal(favMealRoom));
    }


    public Completable deleteFavMeal(FavMealRoom favMealRoom) {
        return firestoreDataSource.removeFromFavorites(favMealRoom.getMealId())
                .andThen(mealsLocalDataSource.deleteFavMeal(favMealRoom));
    }


    public Single<MealResponse> getMealById(String id) {
        return mealsRemoteDataSource.getMealById(id);

    }


    public Completable insertPlannedMeal(PlannedMealRoom meal) {
        return firestoreDataSource.addToPlanned(meal)
                .andThen(mealsLocalDataSource.insertPlannedMeal(meal));
    }

    public Observable<List<PlannedMealRoom>> getPlannedMeals() {
        return mealsLocalDataSource.getPlannedMeals();
    }

    public Completable deletePlannedMeal(PlannedMealRoom meal) {
        return firestoreDataSource.removeFromPlanned(meal.getMealId())
                .andThen(mealsLocalDataSource.deletePlannedMeal(meal));
    }

    public Completable syncFavFromFirestore() {
        return firestoreDataSource.getFavorites()
                .flatMapCompletable(mealList -> {
                    return Observable.fromIterable(mealList)
                            .flatMapCompletable(meal -> mealsLocalDataSource.insertFavMeal(meal));
                });
    }

    public Completable syncPlanedFromFirestore() {
        return firestoreDataSource.getPlanned()
                .flatMapCompletable(mealList -> {
                    return Observable.fromIterable(mealList)
                            .flatMapCompletable(meal -> mealsLocalDataSource.insertPlannedMeal(meal));
                });
    }


    public Observable<SearchMealResponse> searchMeals(String ingredient, String category, String area) {
        return mealsRemoteDataSource.searchMeals(ingredient, category, area);
    }


    public Observable<FilterResponse> getFilter(String ingredient, String category, String area) {
        return mealsRemoteDataSource.getFilter(ingredient, category, area);
    }

}
