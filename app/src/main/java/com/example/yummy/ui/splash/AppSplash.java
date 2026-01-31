package com.example.yummy.ui.splash;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yummy.R;
import com.example.yummy.ui.home.HomeActivity;
import com.example.yummy.ui.onboarding.OnBoarding;
import com.google.firebase.auth.FirebaseAuth;

public class AppSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_splash);

        LottieAnimationView lottie = findViewById(R.id.lottieView);
        TextView appName = findViewById(R.id.appNameText);

        appName.animate()
                .alpha(1f)
                .setDuration(1900)
                .setStartDelay(500)
                .start();

        FirebaseAuth auth = FirebaseAuth.getInstance();

        SharedPreferences prefs =
                getSharedPreferences("yummy_prefs", MODE_PRIVATE);

        boolean isFirstRun = prefs.getBoolean("FIRST_RUN", true);

        if (isFirstRun) {
            auth.signOut();
            prefs.edit()
                    .putBoolean("FIRST_RUN", false)
                    .apply();
        }

        if (auth.getCurrentUser() != null) {
            lottie.addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    startActivity(new Intent(AppSplash.this, HomeActivity.class));
                    finish();
                }
            });
        } else {
            lottie.addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    startActivity(new Intent(AppSplash.this, OnBoarding.class));
                    finish();
                }
            });
        }
    }
}
