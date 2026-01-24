package com.example.yummy.ui.home.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummy.R;
import com.example.yummy.data.meal.model.Meal;
import com.example.yummy.ui.home.home.presenter.RandomMealPresenter;
import com.example.yummy.ui.home.home.presenter.RandomMealPresenterImp;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements RandomMealViews, OnItemClickListener {

    private static final int RANDOM_COUNT = 10;
    RecyclerView recyclerView;
    ImageView iv_randomMeal;
    TextView tv_random_meal_name;
    TextView tv_random_meal_country;
    ProgressBar progressBar;
    MealAdapter adapter;
    RandomMealPresenter randomMealPresenter;
    CardView random_meal_card_view;
    List<Meal> randomMeals = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_meals);
        iv_randomMeal = view.findViewById(R.id.imgRandomMeal);
        tv_random_meal_name = view.findViewById(R.id.tvRandomMealName);
        tv_random_meal_country = view.findViewById(R.id.tvRandomMealCountry);
        random_meal_card_view = view.findViewById(R.id.random_meal_card_view);
        progressBar = view.findViewById(R.id.progress_home);


        random_meal_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragmentToMealDetailsFragment action =
                        HomeFragmentDirections.actionHomeFragmentToMealDetailsFragment(randomMeals.get(0));
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(action);
            }
        });

        recyclerView.setLayoutManager(
                new LinearLayoutManager(requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false)
        );
        randomMeals.clear();
        adapter = new MealAdapter(this);
        recyclerView.setAdapter(adapter);

        randomMealPresenter = new RandomMealPresenterImp(getContext(), this);


        for (int i = 0; i < RANDOM_COUNT; i++) {
            randomMealPresenter.getRandomMeals();
        }
    }


    @Override
    public void showRandomMeal(Meal meal) {
        if (meal == null) return;


        randomMeals.add(meal);


        if (randomMeals.size() == 1) {
            Glide.with(iv_randomMeal)
                    .load(meal.getMealImg())
                    .into(iv_randomMeal);

            tv_random_meal_name.setText(meal.getMealName());
            tv_random_meal_country.setText(meal.getArea());
        }


        if (randomMeals.size() == RANDOM_COUNT) {
            progressBar.setVisibility(View.GONE);
            List<Meal> mealsForRecycler = randomMeals.subList(1, randomMeals.size());
            adapter.setMealList(mealsForRecycler);
        }


    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(Meal meal) {
        HomeFragmentDirections.ActionHomeFragmentToMealDetailsFragment action =
                HomeFragmentDirections.actionHomeFragmentToMealDetailsFragment(meal);
        NavHostFragment.findNavController(HomeFragment.this)
                .navigate(action);
    }
}
