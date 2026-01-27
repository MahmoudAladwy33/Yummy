package com.example.yummy.ui.home.calendar;

import com.example.yummy.data.meal.model.PlannedMealRoom;

public interface OnPlannedClickListener {

    void onDeletePlannedClick(PlannedMealRoom meal);

    void onPlannedMealClick(String mealId);
}
