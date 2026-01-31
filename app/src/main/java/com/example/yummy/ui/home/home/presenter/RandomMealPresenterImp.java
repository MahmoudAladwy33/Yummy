package com.example.yummy.ui.home.home.presenter;

import android.content.Context;

import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.ui.home.home.view.RandomMealViews;

import java.io.IOException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RandomMealPresenterImp implements RandomMealPresenter {

    private MealRepo mealRepo;
    private RandomMealViews randomMealViews;

    private CompositeDisposable disposables = new CompositeDisposable();

    public RandomMealPresenterImp(Context context, RandomMealViews randomMealViews) {
        this.mealRepo = new MealRepo(context);
        this.randomMealViews = randomMealViews;
    }

    @Override
    public void getRandomMeals() {
        randomMealViews.showLoading();


        disposables.add(
                mealRepo.getRandomMeal()
                        .map(mealResponse -> mealResponse.mealList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                mealList -> {
                                    randomMealViews.hideLoading();
                                    if (mealList != null && !mealList.isEmpty()) {
                                        randomMealViews.showRandomMeal(mealList.get(0));
                                    }
                                },
                                throwable -> {
                                    randomMealViews.hideLoading();
                                    if (throwable instanceof IOException)
                                        randomMealViews.showError("Please check your internet connection");
                                    else {
                                        randomMealViews.showError("UnKnown error occurred");
                                    }
                                }
                        )
        );
    }


    @Override
    public void dispose() {
        disposables.clear();
    }
}