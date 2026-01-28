package com.example.yummy.ui.splash;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yummy.R;
import com.example.yummy.ui.auth.AuthActivity;
import com.example.yummy.ui.home.HomeActivity;
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
                    startActivity(new Intent(AppSplash.this, AuthActivity.class));
                    finish();
                }
            });
        }

    }
}