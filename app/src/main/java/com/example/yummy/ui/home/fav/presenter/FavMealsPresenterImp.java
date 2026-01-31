package com.example.yummy.ui.home.fav.presenter;

import android.content.Context;

import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.data.meal.model.FavMealRoom;
import com.example.yummy.ui.home.fav.view.FavMealsViews;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavMealsPresenterImp implements FavMealsPresenter {

    private MealRepo mealRepo;
    private FavMealsViews favMealsViews;
    private CompositeDisposable disposables = new CompositeDisposable();

    public FavMealsPresenterImp(Context context, FavMealsViews favMealsViews) {
        this.mealRepo = new MealRepo(context);
        this.favMealsViews = favMealsViews;
    }

    @Override
    public void loadFavMeals() {
        disposables.add(
                mealRepo.getFavMeals()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                favMeals -> {

                                    favMealsViews.showFavMeals(favMeals);
                                },
                                throwable -> {

                                }
                        )
        );
    }

    @Override
    public void deleteFavMeal(FavMealRoom favMealRoom) {
        disposables.add(
                mealRepo.deleteFavMeal(favMealRoom)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {

                                    favMealsViews.deleteFavMealSuccess();
                                },
                                throwable -> {


                                }
                        )
        );
    }


    public void dispose() {
        disposables.clear();
    }
}