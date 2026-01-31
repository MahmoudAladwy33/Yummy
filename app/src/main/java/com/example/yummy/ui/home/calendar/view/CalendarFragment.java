package com.example.yummy.ui.home.calendar.view;

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
import com.example.yummy.data.meal.model.PlannedMealRoom;
import com.example.yummy.ui.home.calendar.presenter.PlannedMealsPresenter;
import com.example.yummy.ui.home.calendar.presenter.PlannedMealsPresenterImp;

import java.util.List;

public class CalendarFragment extends Fragment implements PlannedMealsViews, OnPlannedClickListener {

    private RecyclerView rvFavMeals;
    private PlannedMealsAdapter adapter;
    private PlannedMealsPresenter presenter;
    private TextView tvEmptyPlanned;
    private ProgressBar progressBar;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        presenter.loadPlannedMeals();
    }

    @Override
    public void showPlannedMeals(List<PlannedMealRoom> plannedMeals) {
        progressBar.setVisibility(GONE);

        if (plannedMeals == null || plannedMeals.isEmpty()) {
            tvEmptyPlanned.setVisibility(VISIBLE);
            rvFavMeals.setVisibility(GONE);
        } else {
            tvEmptyPlanned.setVisibility(GONE);
            rvFavMeals.setVisibility(VISIBLE);
            adapter.setPlannedMeals(plannedMeals);
        }
    }

    @Override
    public void deletePlannedMealSuccess() {
        Toast.makeText(requireContext(), "Meal deleted successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String error) {
        progressBar.setVisibility(GONE);
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
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
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dispose();
        }
    }
}