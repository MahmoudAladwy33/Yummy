package com.example.yummy.ui.home.calendar.view;

import com.example.yummy.data.meal.model.PlannedMealRoom;

public interface OnPlannedClickListener {

    void onDeletePlannedClick(PlannedMealRoom meal);

    void onPlannedMealClick(String mealId);
}
