package com.example.yummy.ui.home.calendar.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummy.R;
import com.example.yummy.data.meal.model.PlannedMealRoom;

import java.util.ArrayList;
import java.util.List;

public class PlannedMealsAdapter extends RecyclerView.Adapter<PlannedMealsAdapter.PlannedViewHolder> {


    private OnPlannedClickListener listener;
    private List<PlannedMealRoom> plannedMeals;

    public PlannedMealsAdapter(OnPlannedClickListener listener) {
        this.listener = listener;
        this.plannedMeals = new ArrayList<>();
    }

    public void setPlannedMeals(List<PlannedMealRoom> meals) {
        this.plannedMeals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlannedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calendar_meal, parent, false);
        return new PlannedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlannedViewHolder holder, int position) {
        PlannedMealRoom meal = plannedMeals.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return plannedMeals != null ? plannedMeals.size() : 0;
    }

    class PlannedViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMeal;
        private TextView tvName, tvCountry, tvDate;
        private ImageButton btnDelete;


        public PlannedViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMeal = itemView.findViewById(R.id.imgCalendarMeal);
            tvName = itemView.findViewById(R.id.tvCalendarMealName);
            tvCountry = itemView.findViewById(R.id.tvCalendarMealCountry);
            tvDate = itemView.findViewById(R.id.tvCalendarMealDate);
            btnDelete = itemView.findViewById(R.id.btnRemoveCalendar);
        }

        public void bind(PlannedMealRoom meal) {
            tvName.setText(meal.getMealName());
            tvCountry.setText(meal.getArea());
            tvDate.setText(meal.getPlannedDate());


            Glide.with(itemView.getContext())
                    .load(meal.getMealImg())
                    .placeholder(R.drawable.onboarding_first_screen)
                    .into(imgMeal);

            btnDelete.setOnClickListener(v -> listener.onDeletePlannedClick(meal));

            itemView.setOnClickListener(v -> listener.onPlannedMealClick(meal.getMealId()));
        }
    }


}
