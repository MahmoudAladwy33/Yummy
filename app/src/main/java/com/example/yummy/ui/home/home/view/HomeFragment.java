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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummy.R;
import com.example.yummy.data.meal.model.Meal;
import com.example.yummy.ui.home.home.presenter.RandomMealPresenter;
import com.example.yummy.ui.home.home.presenter.RandomMealPresenterImp;

import java.util.List;


public class HomeFragment extends Fragment implements RandomMealViews {

    RecyclerView recyclerView;
    List<Meal> mealList;
    Meal meal;
    ImageView iv_randomMeal;
    MealAdapter adapter;
    TextView tv_random_meal_name;
    TextView tv_random_meal_country;
    ProgressBar progressBar;

    RandomMealPresenter randomMealPresenter;

    public HomeFragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_meals);
        iv_randomMeal = view.findViewById(R.id.imgRandomMeal);
        tv_random_meal_name = view.findViewById(R.id.tvRandomMealName);
        tv_random_meal_country = view.findViewById(R.id.tvRandomMealCountry);
        progressBar = view.findViewById(R.id.progress_home);
        randomMealPresenter = new RandomMealPresenterImp(getContext(), this);
        randomMealPresenter.getRandomMeals();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new MealAdapter();
        recyclerView.setAdapter(adapter);

        progressBar.setVisibility(View.VISIBLE);


    }

    @Override
    public void onSuccess(List<Meal> mealList) {
        progressBar.setVisibility(View.GONE);
        meal = mealList.get(0);
        Glide.with(iv_randomMeal)
                .load(meal.getMealImg())
                .into(iv_randomMeal);

        tv_random_meal_name.setText(meal.getMealName());
        tv_random_meal_country.setText(meal.getArea());
    }

    @Override
    public void onError(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Error: " + message, Toast.LENGTH_SHORT).show();

    }
}