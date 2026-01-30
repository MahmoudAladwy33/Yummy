package com.example.yummy.ui.home.search.view;

import com.example.yummy.data.meal.model.SearchMeal;

import java.util.List;

public interface SearchViews {
    void onSearchResult(List<SearchMeal> meals);

    void onSearchResultEmpty();

    void onSearchError(String errorMessage);
}
