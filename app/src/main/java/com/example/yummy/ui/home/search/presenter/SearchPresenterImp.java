package com.example.yummy.ui.home.search.presenter;

import android.content.Context;
import android.util.Log;

import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.data.meal.model.FilterMeal;
import com.example.yummy.ui.home.search.view.SearchViews;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements SearchPresenter {

    MealRepo mealRepo;
    SearchViews views;

    public SearchPresenterImp(Context context, SearchViews views) {
        this.mealRepo = new MealRepo(context);
        this.views = views;
    }


    @Override
    public void searchMeal(String filterType, String query) {
        String ingredient = null, category = null, area = null;

        switch (filterType) {
            case "ingredient":
                ingredient = query;
                break;
            case "category":
                category = query;
                break;
            case "area":
                area = query;
                break;
        }

        mealRepo.searchMeals(ingredient, category, area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    Log.d("SearchPresenter", "Result size: " + (response.mealList != null ? response.mealList.size() : 0));
                    if (response != null && response.mealList != null) {
                        views.onSearchResult(response.mealList);
                    } else {
                        views.onSearchResultEmpty();
                    }
                }, throwable -> {
                    views.onSearchError(throwable.getMessage());
                });

    }


    @Override
    public void getFilterSuggestions(String filterType) {
        String ingredient = null, category = null, area = null;

        switch (filterType) {
            case "ingredient":
                ingredient = "list";
                break;
            case "category":
                category = "list";
                break;
            case "area":
                area = "list";
                break;
        }

        mealRepo.getFilter(ingredient, category, area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filterResponse -> {
                    List<String> suggestions = new ArrayList<>();
                    for (FilterMeal item : filterResponse.mealList) {
                        switch (filterType) {
                            case "ingredient":
                                if (item.getIngredientName() != null)
                                    suggestions.add(item.getIngredientName());
                                break;
                            case "category":
                                if (item.getCategoryName() != null)
                                    suggestions.add(item.getCategoryName());
                                break;
                            case "area":
                                if (item.getAreaName() != null) suggestions.add(item.getAreaName());
                                break;
                        }
                    }

                    views.onFilterSuggestionsReady(suggestions);

                }, throwable -> {
                    views.onSearchError(throwable.getMessage());
                });
    }

}
