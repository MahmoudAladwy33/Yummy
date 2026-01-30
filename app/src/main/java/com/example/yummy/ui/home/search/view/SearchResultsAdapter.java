package com.example.yummy.ui.home.search.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummy.R;
import com.example.yummy.data.meal.model.SearchMeal;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.SearchViewHolder> {

    private List<SearchMeal> mealList;
    private OnSearchItemClickListener onSearchItemClickListener;

    public SearchResultsAdapter(OnSearchItemClickListener onSearchItemClickListener) {
        this.onSearchItemClickListener = onSearchItemClickListener;
        this.mealList = new ArrayList<>();

    }

    public void setMealList(List<SearchMeal> mealList) {
        this.mealList = mealList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_result, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        SearchMeal meal = mealList.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return mealList != null ? mealList.size() : 0;
    }


    class SearchViewHolder extends RecyclerView.ViewHolder {

        private TextView mealNameTextView;
        private ImageView mealImageView;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            mealNameTextView = itemView.findViewById(R.id.tvMealName);
            mealImageView = itemView.findViewById(R.id.imgMeal);
        }

        public void bind(SearchMeal meal) {
            mealNameTextView.setText(meal.getMealName());

            Glide.with(itemView)
                    .load(meal.getMealImg())
                    .into(mealImageView);

            itemView.setOnClickListener(v -> {
                if (onSearchItemClickListener != null) {
                    onSearchItemClickListener.onSearchItemClick(meal);
                }
            });
        }
    }
}
