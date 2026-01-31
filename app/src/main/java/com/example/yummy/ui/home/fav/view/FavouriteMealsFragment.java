package com.example.yummy.ui.home.fav.view;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;
import com.example.yummy.data.meal.model.FavMealRoom;
import com.example.yummy.ui.home.fav.presenter.FavMealsPresenter;
import com.example.yummy.ui.home.fav.presenter.FavMealsPresenterImp;

import java.util.List;

public class FavouriteMealsFragment extends Fragment implements FavMealsViews, OnFavClickListener {

    private RecyclerView rvFavMeals;
    private FavMealAdapter adapter;
    private FavMealsPresenter presenter;
    private TextView tvEmptyFav;
    private ProgressBar progressBar;

    public FavouriteMealsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourite_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rvFavMeals = view.findViewById(R.id.rvFavMeals);
        progressBar = view.findViewById(R.id.progress_fav_meals);
        tvEmptyFav = view.findViewById(R.id.tvEmptyFav);

        adapter = new FavMealAdapter(this);
        rvFavMeals.setAdapter(adapter);

        presenter = new FavMealsPresenterImp(getContext(), this);


        progressBar.setVisibility(VISIBLE);
        presenter.loadFavMeals();
    }

    @Override
    public void showFavMeals(List<FavMealRoom> favMeals) {
        progressBar.setVisibility(GONE);

        if (favMeals == null || favMeals.isEmpty()) {
            tvEmptyFav.setVisibility(VISIBLE);
            rvFavMeals.setVisibility(GONE);
        } else {
            tvEmptyFav.setVisibility(GONE);
            rvFavMeals.setVisibility(VISIBLE);

            adapter.setFavMeals(favMeals);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void deleteFavMealSuccess() {
        Toast.makeText(requireContext(), "Meal deleted successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String error) {
        progressBar.setVisibility(GONE);
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteFavClick(FavMealRoom meal) {
        presenter.deleteFavMeal(meal);
    }

    @Override
    public void onFavMealClick(String mealId) {
        FavouriteMealsFragmentDirections.ActionFavouriteMealsFragmentToMealDetailsFragment action =
                FavouriteMealsFragmentDirections
                        .actionFavouriteMealsFragmentToMealDetailsFragment(mealId);
        action.setSource("fromFav");

        NavHostFragment.findNavController(this).navigate(action);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dispose();
        }
    }
}