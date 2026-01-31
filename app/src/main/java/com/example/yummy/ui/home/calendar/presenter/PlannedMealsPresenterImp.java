package com.example.yummy.ui.home.calendar.presenter;

import android.content.Context;

import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.data.meal.model.PlannedMealRoom;
import com.example.yummy.ui.home.calendar.view.PlannedMealsViews;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlannedMealsPresenterImp implements PlannedMealsPresenter {

    private MealRepo mealRepo;
    private PlannedMealsViews plannedMealsViews;
    private CompositeDisposable disposables = new CompositeDisposable();

    public PlannedMealsPresenterImp(Context context, PlannedMealsViews plannedMealsViews) {
        this.mealRepo = new MealRepo(context);
        this.plannedMealsViews = plannedMealsViews;
    }

    @Override
    public void loadPlannedMeals() {
        disposables.add(
                mealRepo.getPlannedMeals()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                plannedMeals -> {

                                    plannedMealsViews.showPlannedMeals(plannedMeals);
                                },
                                throwable -> {
                                    plannedMealsViews.showErrorMessage(throwable.getMessage());
                                }
                        )
        );
    }

    @Override
    public void deletePlannedMeal(PlannedMealRoom meal) {
        disposables.add(
                mealRepo.deletePlannedMeal(meal)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    plannedMealsViews.deletePlannedMealSuccess();
                                },
                                throwable -> {
                                    plannedMealsViews.showErrorMessage(throwable.getMessage());
                                }
                        )
        );
    }

    @Override
    public void dispose() {
        disposables.clear();
    }
}