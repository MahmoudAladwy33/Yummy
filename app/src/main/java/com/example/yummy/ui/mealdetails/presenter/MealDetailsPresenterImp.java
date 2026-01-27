package com.example.yummy.ui.mealdetails.presenter;

import android.content.Context;

import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.data.meal.datasource.remote.MealsNetworkResponse;
import com.example.yummy.data.meal.model.FavMealRoom;
import com.example.yummy.data.meal.model.IngredientItem;
import com.example.yummy.data.meal.model.Meal;
import com.example.yummy.data.meal.model.PlannedMealRoom;
import com.example.yummy.ui.mealdetails.View.MealDetailsViews;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MealDetailsPresenterImp implements MealDetailsPresenter {

    MealDetailsViews mealDetailsViews;
    private MealRepo mealRepo;

    public MealDetailsPresenterImp(Context context, MealDetailsViews mealDetailsViews) {
        this.mealRepo = new MealRepo(context);
        this.mealDetailsViews = mealDetailsViews;
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
        FavMealRoom favMealRoom = new FavMealRoom(meal);
        mealRepo.insertFavMeal(favMealRoom);
        mealDetailsViews.addToFavSuccess();
    }

    @Override
    public void removeMealFromFavorites(Meal meal) {
        FavMealRoom favMealRoom = new FavMealRoom(meal);
        mealRepo.deleteFavMeal(favMealRoom);
        mealDetailsViews.removeFromFavSuccess();

    }

    @Override
    public void getMealById(String id) {
        mealDetailsViews.showLoading();
        mealRepo.getMealById(id, new MealsNetworkResponse() {
            @Override
            public void onSuccess(List<Meal> mealList) {
                mealDetailsViews.hideLoading();
                mealDetailsViews.showMealById(mealList.get(0));
            }

            @Override
            public void onError(String errorMessage) {
                mealDetailsViews.hideLoading();
                mealDetailsViews.showError(errorMessage);
            }
        });
    }

    @Override
    public void addMealToCalendar(Meal meal, int year, int month, int day, int hour, int minute) {
        Calendar selectedDateTime = Calendar.getInstance();
        selectedDateTime.set(year, month, day, hour, minute, 0);
        selectedDateTime.set(Calendar.MILLISECOND, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, EEEE HH:mm");
        String displayDate = sdf.format(selectedDateTime.getTime());

        PlannedMealRoom plannedMealRoom = new PlannedMealRoom(meal);
        plannedMealRoom.setPlannedDate(displayDate);

        mealRepo.insertPlannedMeal(plannedMealRoom);
        mealDetailsViews.addToCalendarSuccess(displayDate);
    }


}
