package com.example.yummy.ui.home.fav;

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
import com.example.yummy.data.meal.model.MealRoom;

import java.util.ArrayList;
import java.util.List;

public class FavMealAdapter extends RecyclerView.Adapter<FavMealAdapter.FavViewHolder> {


    private OnDeleteFavClickListener listener;
    private List<MealRoom> favMeals;

    public FavMealAdapter(OnDeleteFavClickListener listener) {
        this.favMeals = new ArrayList<>();
        this.listener = listener;
    }

    public void setFavMeals(List<MealRoom> meals) {
        this.favMeals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fav_meal, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        MealRoom meal = favMeals.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return favMeals != null ? favMeals.size() : 0;
    }


    class FavViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMeal;
        private TextView tvName, tvCountry;
        private ImageButton btnDelete;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMeal = itemView.findViewById(R.id.imgFavMeal);
            tvName = itemView.findViewById(R.id.tvFavMealName);
            tvCountry = itemView.findViewById(R.id.tvFavMealCountry);
            btnDelete = itemView.findViewById(R.id.btnRemoveFav);


        }

        public void bind(MealRoom meal) {
            tvName.setText(meal.getMealName());
            //  tvCountry.setText(meal.getStrArea());

            Glide.with(itemView.getContext())
                    .load(meal.getMealImg())
                    .placeholder(R.drawable.onboarding_first_screen)
                    .into(imgMeal);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDeleteFavClick(meal);
                }
            });

        }


    }
}
