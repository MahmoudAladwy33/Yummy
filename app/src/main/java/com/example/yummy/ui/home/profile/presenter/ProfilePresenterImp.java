package com.example.yummy.ui.home.profile.presenter;

import android.content.Context;

import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.ui.home.profile.ProfileViews;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenterImp implements ProfilePresenter {

    private MealRepo mealRepo;
    private ProfileViews view;

    private CompositeDisposable disposables = new CompositeDisposable();

    public ProfilePresenterImp(Context context, ProfileViews view) {
        this.mealRepo = new MealRepo(context);
        this.view = view;
    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        disposables.add(
                mealRepo.clearAllTables()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    view.navigateToLoginScreen();
                                },
                                throwable -> {

                                }
                        )
        );
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


    public void dispose() {
        disposables.clear();
    }
}