package com.example.yummy.ui.home.search.view;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;
import com.example.yummy.data.meal.model.SearchMeal;
import com.example.yummy.ui.home.search.presenter.SearchPresenter;
import com.example.yummy.ui.home.search.presenter.SearchPresenterImp;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment
        implements SearchViews,
        OnSearchItemClickListener,
        OnSuggestionClickListener {

    private EditText etSearch;
    private Chip chipIngredient, chipCategory, chipArea;

    private RecyclerView rvSuggestions;
    private RecyclerView rvSearchResults;

    private SearchPresenter presenter;
    private SearchResultsAdapter resultsAdapter;
    private SearchSuggestionsAdapter suggestionsAdapter;

    public SearchFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(
            View view, Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        etSearch = view.findViewById(R.id.et_Search);
        chipIngredient = view.findViewById(R.id.chipIngredient);
        chipCategory = view.findViewById(R.id.chipCategory);
        chipArea = view.findViewById(R.id.chipArea);

        rvSuggestions = view.findViewById(R.id.rvSuggestions);
        rvSearchResults = view.findViewById(R.id.rvSearchResults);

        presenter = new SearchPresenterImp(requireContext(), this);

        // Search results
        rvSearchResults.setLayoutManager(new LinearLayoutManager(requireContext()));
        resultsAdapter = new SearchResultsAdapter(this);
        rvSearchResults.setAdapter(resultsAdapter);

        // Suggestions
        rvSuggestions.setLayoutManager(new LinearLayoutManager(requireContext()));
        suggestionsAdapter = new SearchSuggestionsAdapter(this);
        rvSuggestions.setAdapter(suggestionsAdapter);
        rvSuggestions.setVisibility(GONE);

        // focus + keyboard
        etSearch.requestFocus();
        etSearch.post(() -> {
            InputMethodManager imm =
                    (InputMethodManager) requireContext()
                            .getSystemService(requireContext().INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(etSearch, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        // Text watcher
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after
            ) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count
            ) {
                String filterType = getSelectedChip();
                String query = s.toString().trim();

                if (filterType != null && !query.isEmpty()) {
                    presenter.getFilterSuggestions(filterType);
                } else {
                    rvSuggestions.setVisibility(GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // keyboard search
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            String query = etSearch.getText().toString().trim();
            String filterType = getSelectedChip();

            rvSuggestions.setVisibility(GONE);

            if (filterType != null && !query.isEmpty()) {
                presenter.searchMeal(filterType, query);
            } else {
                resultsAdapter.setMealList(new ArrayList<>());
            }
            return true;
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dispose();
        }
    }

    private String getSelectedChip() {
        if (chipIngredient.isChecked()) return "ingredient";
        if (chipCategory.isChecked()) return "category";
        if (chipArea.isChecked()) return "area";
        return null;
    }


    @Override
    public void onSearchResult(List<SearchMeal> meals) {
        resultsAdapter.setMealList(meals);
    }

    @Override
    public void onSearchResultEmpty() {
        resultsAdapter.setMealList(new ArrayList<>());
    }

    @Override
    public void onSearchError(String errorMessage) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSearchItemClick(SearchMeal meal) {
        SearchFragmentDirections.ActionSearchFragmentToMealDetailsFragment action =
                SearchFragmentDirections
                        .actionSearchFragmentToMealDetailsFragment(
                                meal.getMealId()
                        );

        NavHostFragment
                .findNavController(SearchFragment.this)
                .navigate(action);
    }


    @Override
    public void onFilterSuggestionsReady(List<String> suggestions) {
        String query = etSearch.getText().toString().trim().toLowerCase();

        if (query.isEmpty()) {
            rvSuggestions.setVisibility(GONE);
            return;
        }

        List<String> filtered = new ArrayList<>();
        for (String s : suggestions) {
            if (s.toLowerCase().contains(query)) {
                filtered.add(s);
            }
        }

        if (filtered.isEmpty()) {
            rvSuggestions.setVisibility(GONE);
        } else {
            rvSuggestions.setVisibility(VISIBLE);
            suggestionsAdapter.setSuggestionsList(filtered);
        }
    }

    @Override
    public void onSuggestionClick(String suggestion) {
        etSearch.setText(suggestion);
        etSearch.setSelection(suggestion.length());

        rvSuggestions.setVisibility(GONE);

        String filterType = getSelectedChip();
        if (filterType != null) {
            presenter.searchMeal(filterType, suggestion);
        }
    }
}
