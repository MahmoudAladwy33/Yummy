package com.example.yummy.ui.mealdetails.presenter;

import android.content.Context;

import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.data.meal.model.FavMealRoom;
import com.example.yummy.data.meal.model.IngredientItem;
import com.example.yummy.data.meal.model.Meal;
import com.example.yummy.data.meal.model.PlannedMealRoom;
import com.example.yummy.ui.mealdetails.View.MealDetailsViews;
import com.example.yummy.utils.GuestGuard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImp implements MealDetailsPresenter {

    private MealDetailsViews mealDetailsViews;
    private MealRepo mealRepo;
    private GuestGuard guestGuard;
    private CompositeDisposable disposables = new CompositeDisposable();

    public MealDetailsPresenterImp(Context context, MealDetailsViews mealDetailsViews) {
        this.mealRepo = new MealRepo(context);
        this.mealDetailsViews = mealDetailsViews;
        this.guestGuard = new GuestGuard(context);
    }

    @Override
    public List<IngredientItem> getIngredientsList(Meal meal) {
        List<IngredientItem> list = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String ingredient = meal.getIngredient(i);
            String measure = meal.getMeasure(i);
            if (ingredient != null && !ingredient.isEmpty()) {
                list.add(new IngredientItem(ingredient, measure));
            }
        }
        return list;
    }

    @Override
    public void addMealToFavorites(Meal meal) {
        guestGuard.runIfNotGuest(
                () -> {
                    FavMealRoom favMealRoom = new FavMealRoom(meal);
                    disposables.add(
                            mealRepo.insertFavMeal(favMealRoom)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            () -> mealDetailsViews.addToFavSuccess(),
                                            throwable -> mealDetailsViews.showError(throwable.getMessage())
                                    )
                    );
                },
                () -> mealDetailsViews.showLoginHint()
        );
    }

    @Override
    public void removeMealFromFavorites(Meal meal) {
        FavMealRoom favMealRoom = new FavMealRoom(meal);
        disposables.add(
                mealRepo.deleteFavMeal(favMealRoom)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> mealDetailsViews.removeFromFavSuccess(),
                                throwable -> mealDetailsViews.showError(throwable.getMessage())
                        )
        );
    }

    @Override
    public void addMealToCalendar(Meal meal, int year, int month, int day, int hour, int minute) {
        guestGuard.runIfNotGuest(
                () -> {
                    Calendar selectedDateTime = Calendar.getInstance();
                    selectedDateTime.set(year, month, day, hour, minute, 0);
                    selectedDateTime.set(Calendar.MILLISECOND, 0);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, EEEE HH:mm", Locale.getDefault());
                    String displayDate = sdf.format(selectedDateTime.getTime());

                    PlannedMealRoom plannedMealRoom = new PlannedMealRoom(meal);
                    plannedMealRoom.setPlannedDate(displayDate);

                    disposables.add(
                            mealRepo.insertPlannedMeal(plannedMealRoom)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            () -> mealDetailsViews.addToCalendarSuccess(displayDate),
                                            throwable -> mealDetailsViews.showError(throwable.getMessage())
                                    )
                    );
                },
                () -> mealDetailsViews.showLoginHint()
        );
    }

    @Override
    public void getMealById(String id) {
        mealDetailsViews.showLoading();
        disposables.add(
                mealRepo.getMealById(id)
                        .map(mealResponse -> mealResponse.mealList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                mealList -> {
                                    mealDetailsViews.hideLoading();
                                    if (mealList != null && !mealList.isEmpty()) {
                                        mealDetailsViews.showMealById(mealList.get(0));
                                    }
                                },
                                throwable -> {
                                    mealDetailsViews.hideLoading();
                                    mealDetailsViews.showError(throwable.getMessage());
                                }
                        )
        );
    }

    public void dispose() {
        disposables.clear();
    }
}