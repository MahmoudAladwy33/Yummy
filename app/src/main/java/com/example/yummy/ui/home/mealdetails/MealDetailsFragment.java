package com.example.yummy.ui.home.mealdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.yummy.R;
import com.example.yummy.data.meal.model.Meal;
import com.example.yummy.ui.home.HomeActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MealDetailsFragment extends Fragment {

    ImageView iv_meal_image;
    TextView tv_meal_name;
    TextView tv_meal_instructions;
    TextView tvMealDetailsCountry;
    Button btnStartCooking;
    YouTubePlayerView youtubePlayerView;
    private YouTubePlayer activeYouTubePlayer;

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

        // Initialization
        iv_meal_image = view.findViewById(R.id.imgMealDetails);
        tv_meal_name = view.findViewById(R.id.tvMealNameDetails);
        tvMealDetailsCountry = view.findViewById(R.id.tvMealDetailsCountry);
        btnStartCooking = view.findViewById(R.id.btnStartCooking);
        tv_meal_instructions = view.findViewById(R.id.tvInstructions);
        youtubePlayerView = view.findViewById(R.id.youtubePlayerView);

        getLifecycle().addObserver(youtubePlayerView);
        ((HomeActivity) requireActivity()).findViewById(R.id.bottom_nav_view).setVisibility(View.GONE);

        MealDetailsFragmentArgs args = MealDetailsFragmentArgs.fromBundle(getArguments());
        Meal meal = args.getMealArgs();

        // UI Setup
        Glide.with(this).load(meal.getMealImg()).into(iv_meal_image);
        tv_meal_name.setText(meal.getMealName());
        tv_meal_instructions.setText(meal.getInstructions());
        tvMealDetailsCountry.setText(meal.getArea());


        youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                activeYouTubePlayer = youTubePlayer;
                String videoId = extractYoutubeId(meal.getYoutubeUrl());
                if (videoId != null) {
                    activeYouTubePlayer.cueVideo(videoId, 0);
                }
            }
        });

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((HomeActivity) requireActivity()).findViewById(R.id.bottom_nav_view).setVisibility(View.VISIBLE);
    }


    private String extractYoutubeId(String url) {
        if (url == null || url.trim().isEmpty()) return null;
        String pattern = "(?<=watch\\?v=|/videos/|embed/|youtu.be/|/v/|/e/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%2F|youtu.be%2F|%2Fv%2F)[^#&?\\n]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url);

        if (matcher.find()) {
            return matcher.group();
        }
        return url;
    }
}