package com.example.yummy.ui.home.calendar;

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
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;
import com.example.yummy.data.meal.model.PlannedMealRoom;
import com.example.yummy.ui.home.calendar.presenter.PlannedMealsPresenter;
import com.example.yummy.ui.home.calendar.presenter.PlannedMealsPresenterImp;

import java.util.List;


public class CalendarFragment extends Fragment implements PlannedMealsViews, OnPlannedClickListener {

    RecyclerView rvFavMeals;
    PlannedMealsAdapter adapter;
    PlannedMealsPresenter presenter;

    TextView tvEmptyPlanned;
    ProgressBar progressBar;


    public CalendarFragment() {
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
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFavMeals = view.findViewById(R.id.rvCalendarMeals);
        progressBar = view.findViewById(R.id.progress_calendar_meals);
        tvEmptyPlanned = view.findViewById(R.id.tvEmptyCalendar);
        adapter = new PlannedMealsAdapter(this);
        rvFavMeals.setAdapter(adapter);
        presenter = new PlannedMealsPresenterImp(getContext(), this);
        progressBar.setVisibility(VISIBLE);
        presenter.loadPlannedMeals().observe(getViewLifecycleOwner(), new Observer<List<PlannedMealRoom>>() {
            @Override
            public void onChanged(List<PlannedMealRoom> plannedMealRooms) {
                if (plannedMealRooms == null || plannedMealRooms.isEmpty()) {
                    progressBar.setVisibility(GONE);
                    tvEmptyPlanned.setVisibility(View.VISIBLE);
                    rvFavMeals.setVisibility(GONE);
                } else {
                    progressBar.setVisibility(GONE);
                    tvEmptyPlanned.setVisibility(GONE);
                    rvFavMeals.setVisibility(View.VISIBLE);
                    adapter.setPlannedMeals(plannedMealRooms);
                }

            }
        });

    }

    @Override
    public void onDeletePlannedClick(PlannedMealRoom meal) {
        presenter.deletePlannedMeal(meal);

    }

    @Override
    public void onPlannedMealClick(String mealId) {

        CalendarFragmentDirections.ActionCalenderFragmentToMealDetailsFragment action =
                CalendarFragmentDirections.actionCalenderFragmentToMealDetailsFragment(mealId);
        action.setSource("fromPlanned");

        NavHostFragment.findNavController(this).navigate(action);
    }

    @Override
    public void deletePlannedMealSuccess() {
        Toast.makeText(requireContext(), "Meal deleted successfully", Toast.LENGTH_SHORT).show();
    }
}