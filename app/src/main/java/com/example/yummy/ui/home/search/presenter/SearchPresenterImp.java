package com.example.yummy.ui.home.search.presenter;

import android.content.Context;

import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.data.meal.model.FilterMeal;
import com.example.yummy.ui.home.search.view.SearchViews;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenterImp implements SearchPresenter {

    private MealRepo mealRepo;
    private SearchViews views;
    private CompositeDisposable disposables = new CompositeDisposable();

    public SearchPresenterImp(Context context, SearchViews views) {
        this.mealRepo = new MealRepo(context);
        this.views = views;
    }

    @Override
    public void searchMeal(String filterType, String query) {

        String ingredient = null;
        String category = null;
        String area = null;

        if (query == null || query.trim().isEmpty()) {
            views.onSearchResultEmpty();
            return;
        }

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
            default:
                views.onSearchResultEmpty();
                return;
        }

        disposables.add(
                mealRepo.searchMeals(ingredient, category, area)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            if (response != null && response.mealList != null && !response.mealList.isEmpty()) {
                                views.onSearchResult(response.mealList);
                            } else {
                                views.onSearchResultEmpty();
                            }
                        }, throwable -> views.onSearchError(throwable.getMessage()))
        );
    }

    @Override
    public void getFilterSuggestions(String filterType) {

        String ingredient = null;
        String category = null;
        String area = null;

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
            default:
                views.onFilterSuggestionsReady(new ArrayList<>());
                return;
        }

        disposables.add(
                mealRepo.getFilter(ingredient, category, area)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(filterResponse -> {

                            if (filterResponse == null || filterResponse.mealList == null) {
                                views.onFilterSuggestionsReady(new ArrayList<>());
                                return;
                            }

                            List<String> suggestions = new ArrayList<>();

                            for (FilterMeal item : filterResponse.mealList) {
                                String value = getSuggestionValue(filterType, item);
                                if (value != null) {
                                    suggestions.add(value);
                                }
                            }

                            views.onFilterSuggestionsReady(suggestions);

                        }, throwable -> views.onSearchError(throwable.getMessage()))
        );
    }

    private String getSuggestionValue(String filterType, FilterMeal item) {
        switch (filterType) {
            case "ingredient":
                return item.getIngredientName();
            case "category":
                return item.getCategoryName();
            case "area":
                return item.getAreaName();
            default:
                return null;
        }
    }

    @Override
    public void dispose() {
        disposables.clear();
    }
}
