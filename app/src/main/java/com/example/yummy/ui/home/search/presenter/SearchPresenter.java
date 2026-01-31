package com.example.yummy.ui.home.search.presenter;

public interface SearchPresenter {
    void searchMeal(String filterType, String query);

    void getFilterSuggestions(String filterType);

    void dispose();
}
