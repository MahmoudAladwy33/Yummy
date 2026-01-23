package com.example.yummy.ui.home.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;
import com.example.yummy.data.meal.model.Meal;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    List<Meal> mealList;

    MealAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mealList = new ArrayList<>();

        mealList.add(new Meal("https://www.themealdb.com/images/media/meals/uwxusv1487344500.jpg"));
        mealList.add(new Meal("https://www.themealdb.com/images/media/meals/uwxusv1487344500.jpg"));
        mealList.add(new Meal("https://www.themealdb.com/images/media/meals/uwxusv1487344500.jpg"));
        mealList.add(new Meal("https://www.themealdb.com/images/media/meals/uwxusv1487344500.jpg"));
        mealList.add(new Meal("https://www.themealdb.com/images/media/meals/uwxusv1487344500.jpg"));
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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new MealAdapter(mealList);
        recyclerView.setAdapter(adapter);

    }
}