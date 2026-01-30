package com.example.yummy.ui.home.search.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;

import java.util.ArrayList;
import java.util.List;

public class SearchSuggestionsAdapter
        extends RecyclerView.Adapter<SearchSuggestionsAdapter.SuggestionViewHolder> {

    private List<String> suggestionsList;
    private OnSuggestionClickListener onSuggestionClickListener;

    public SearchSuggestionsAdapter(OnSuggestionClickListener listener) {
        this.onSuggestionClickListener = listener;
        this.suggestionsList = new ArrayList<>();
    }

    public void setSuggestionsList(List<String> suggestionsList) {
        this.suggestionsList = suggestionsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_suggestion, parent, false);
        return new SuggestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionViewHolder holder, int position) {
        String suggestion = suggestionsList.get(position);
        holder.bind(suggestion);
    }

    @Override
    public int getItemCount() {
        return suggestionsList != null ? suggestionsList.size() : 0;
    }

    class SuggestionViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSuggestionName;

        public SuggestionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSuggestionName = itemView.findViewById(R.id.tvSuggestionName);
        }

        public void bind(String suggestion) {
            tvSuggestionName.setText(suggestion);

            itemView.setOnClickListener(v -> {
                if (onSuggestionClickListener != null) {
                    onSuggestionClickListener.onSuggestionClick(suggestion);
                }
            });
        }
    }


}


