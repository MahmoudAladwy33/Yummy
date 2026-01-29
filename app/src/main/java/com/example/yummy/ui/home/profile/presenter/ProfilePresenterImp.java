package com.example.yummy.ui.home.profile.presenter;

import android.content.Context;

import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.ui.home.profile.ProfileViews;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfilePresenterImp implements ProfilePresenter {

    MealRepo mealRepo;
    ProfileViews view;

    public ProfilePresenterImp(Context context, ProfileViews view) {
        this.mealRepo = new MealRepo(context);
        this.view = view;
    }


    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        new Thread(() -> {
            mealRepo.clearFavMeals();
            new android.os.Handler(android.os.Looper.getMainLooper()).post(() -> {
                view.navigateToLoginScreen();
            });
        }).start();

    }

    @Override
    public void showUserEmail() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            String email = user.getEmail();
            view.showUserEmail(email);
        }

    }
}
