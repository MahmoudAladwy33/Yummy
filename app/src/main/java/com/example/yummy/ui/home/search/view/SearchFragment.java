package com.example.yummy.ui.home.search.view;

import static android.view.View.GONE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;
import com.example.yummy.data.meal.model.SearchMeal;
import com.example.yummy.ui.home.HomeActivity;
import com.example.yummy.ui.home.search.presenter.SearchPresenter;
import com.example.yummy.ui.home.search.presenter.SearchPresenterImp;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements SearchViews, OnSearchItemClickListener {

    EditText etSearch;
    Chip chipIngredient;
    Chip chipCategory;
    Chip chipArea;
    SearchPresenter presenter;
    RecyclerView rvSearchResults;
    SearchResultsAdapter adapter;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((HomeActivity) requireActivity()).findViewById(R.id.bottom_nav_view).setVisibility(GONE);
        etSearch = view.findViewById(R.id.et_Search);
        chipIngredient = view.findViewById(R.id.chipIngredient);
        chipCategory = view.findViewById(R.id.chipCategory);
        chipArea = view.findViewById(R.id.chipArea);
        rvSearchResults = view.findViewById(R.id.rvSearchResults);
        rvSearchResults.setLayoutManager(new LinearLayoutManager(requireContext()));
        presenter = new SearchPresenterImp(requireContext(), this);
        adapter = new SearchResultsAdapter(this);
        rvSearchResults.setAdapter(adapter);
        etSearch.requestFocus();
        etSearch.post(() -> {
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(requireContext().INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(etSearch, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            String query = etSearch.getText().toString().trim();
            String filterType = null;

            if (chipIngredient.isChecked()) {
                filterType = "ingredient";
            } else if (chipCategory.isChecked()) {
                filterType = "category";
            } else if (chipArea.isChecked()) {
                filterType = "area";
            }

            if (filterType != null && !query.isEmpty()) {
                presenter.searchMeal(filterType, query);
            } else {
               
                adapter.setMealList(new ArrayList<>());
            }
            return true;
        });


    }


    @Override
    public void onSearchResult(List<SearchMeal> meals) {

        adapter.setMealList(meals);

    }

    @Override
    public void onSearchResultEmpty() {

    }

    @Override
    public void onSearchError(String errorMessage) {

    }

    @Override
    public void onSearchItemClick(SearchMeal meal) {
        SearchFragmentDirections.ActionSearchFragmentToMealDetailsFragment action =
                SearchFragmentDirections.actionSearchFragmentToMealDetailsFragment(meal.getMealId());
        NavHostFragment.findNavController(SearchFragment.this)
                .navigate(action);
    }
}