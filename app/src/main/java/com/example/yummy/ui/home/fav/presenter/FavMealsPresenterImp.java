package com.example.yummy.ui.home.fav.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.data.meal.model.MealRoom;
import com.example.yummy.ui.home.fav.view.FavMealsViews;

import java.util.List;

public class FavMealsPresenterImp implements FavMealsPresenter {

    private MealRepo mealRepo;
    private FavMealsViews favMealsViews;

    public FavMealsPresenterImp(Context context, FavMealsViews favMealsViews) {
        this.mealRepo = new MealRepo(context);
        this.favMealsViews = favMealsViews;
    }

    @Override
    public LiveData<List<MealRoom>> loadFavMeals() {

        return mealRepo.getFavMeals();

    }

    @Override
    public void deleteFavMeal(MealRoom mealRoom) {
        mealRepo.deleteFavMeal(mealRoom);
        favMealsViews.deleteFavMealSuccess();
    }
}
