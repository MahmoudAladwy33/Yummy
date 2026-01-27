package com.example.yummy.ui.mealdetails.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummy.R;
import com.example.yummy.data.meal.model.IngredientItem;
import com.example.yummy.data.meal.model.Meal;
import com.example.yummy.data.meal.model.MealRoom;
import com.example.yummy.ui.home.HomeActivity;
import com.example.yummy.ui.mealdetails.presenter.MealDetailsPresenter;
import com.example.yummy.ui.mealdetails.presenter.MealDetailsPresenterImp;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class MealDetailsFragment extends Fragment implements MealDetailsViews {

    ImageView iv_meal_image;
    TextView tv_meal_name;
    TextView tv_meal_instructions;
    TextView tvMealDetailsCountry;
    Button btnStartCooking;
    YouTubePlayerView youtubePlayerView;
    RecyclerView rvIngredients;
    IngredientsAdapter adapter;
    List<IngredientItem> ingredients;
    MealDetailsPresenter mealDetailsPresenter;
    Meal meal;

    ImageButton btn_add_to_fav;
    private YouTubePlayer activeYouTubePlayer;
    private boolean isFavorite = false;

    public MealDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initialization
        mealDetailsPresenter = new MealDetailsPresenterImp(requireContext(), this);

        iv_meal_image = view.findViewById(R.id.imgMealDetails);
        tv_meal_name = view.findViewById(R.id.tvMealNameDetails);
        tvMealDetailsCountry = view.findViewById(R.id.tvMealDetailsCountry);
        btnStartCooking = view.findViewById(R.id.btnStartCooking);
        tv_meal_instructions = view.findViewById(R.id.tvInstructions);
        youtubePlayerView = view.findViewById(R.id.youtubePlayerView);
        rvIngredients = view.findViewById(R.id.rvIngredients);
        btn_add_to_fav = view.findViewById(R.id.btnAddToFav);
        adapter = new IngredientsAdapter();
        getLifecycle().addObserver(youtubePlayerView);
        ((HomeActivity) requireActivity()).findViewById(R.id.bottom_nav_view).setVisibility(View.GONE);

        rvIngredients.setLayoutManager(
                new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        );

        // get mealId from arguments
        MealDetailsFragmentArgs args = MealDetailsFragmentArgs.fromBundle(getArguments());
        String mealId = args.getMealId();
        String source = args.getSource();
        mealDetailsPresenter.getMealById(mealId);

        if ("fromFav".equals(source)) {
            btn_add_to_fav.setImageResource(R.drawable.ic_details_fav_fill);
            isFavorite = true;
        } else {
            btn_add_to_fav.setImageResource(R.drawable.ic_details_fav_unfill);
            isFavorite = false;
        }

        // start cooking button
        btnStartCooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activeYouTubePlayer != null) {
                    btnStartCooking.setVisibility(View.GONE);
                    youtubePlayerView.setVisibility(View.VISIBLE);
                    activeYouTubePlayer.play();
                }
            }
        });

        // add/remove favorite click
        btn_add_to_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (meal == null) return;
                MealRoom mealRoom = new MealRoom(meal);
                if (!isFavorite) {
                    mealDetailsPresenter.addMealToFavorites(mealRoom);
                } else {
                    mealDetailsPresenter.removeMealFromFavorites(mealRoom);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((HomeActivity) requireActivity()).findViewById(R.id.bottom_nav_view).setVisibility(View.VISIBLE);
    }

    @Override
    public void addToFavSuccess() {
        btn_add_to_fav.setImageResource(R.drawable.ic_details_fav_fill);
        isFavorite = true;
        Toast.makeText(requireContext(), "Added to Favorites", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeFromFavSuccess() {
        btn_add_to_fav.setImageResource(R.drawable.ic_details_fav_unfill);
        isFavorite = false;
        Toast.makeText(requireContext(), "Removed from Favorites", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMealById(Meal meal) {
        this.meal = meal;

        Glide.with(this).load(meal.getMealImg()).into(iv_meal_image);
        tv_meal_name.setText(meal.getMealName());
        tv_meal_instructions.setText(meal.getInstructions());
        tvMealDetailsCountry.setText(meal.getArea());

        ingredients = mealDetailsPresenter.getIngredientsList(meal);
        adapter.setList(ingredients);
        rvIngredients.setAdapter(adapter);

        // YouTube player listener (only once)
        if (activeYouTubePlayer == null) {
            youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    activeYouTubePlayer = youTubePlayer;
                    String videoId = meal.getYoutubeUrl()
                            .substring(meal.getYoutubeUrl().lastIndexOf("v=") + 2);
                    activeYouTubePlayer.cueVideo(videoId, 0);
                }
            });
        }
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
