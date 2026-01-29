package com.example.yummy.ui.auth.Google.presenter;

import android.content.Context;

import com.example.yummy.data.auth.datasource.GoogleSignRepo;
import com.example.yummy.data.auth.datasource.remote.GoogleSignResponse;
import com.example.yummy.data.common.SessionManager;
import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.ui.auth.Google.view.GoogleView;
import com.google.firebase.auth.FirebaseUser;

public class GooglePresenterImp implements GooglePresenter {

    SessionManager sessionManager;
    private GoogleView view;
    private GoogleSignRepo googleSignRepo;
    private MealRepo mealRepo;


    public GooglePresenterImp(GoogleView view, GoogleSignRepo googleSignRepo, Context context) {
        this.view = view;
        this.googleSignRepo = googleSignRepo;
        this.sessionManager = SessionManager.getInstance(context);
        this.mealRepo = new MealRepo(context);
    }


    @Override
    public void signInWithGoogle(String idToken) {

        googleSignRepo.SignWithGoogle(idToken, new GoogleSignResponse() {
            @Override
            public void onSuccess(FirebaseUser user) {
                sessionManager.setGuest(false);
                mealRepo.syncFavFromFirestore();
                mealRepo.syncPlanedFromFirestore();
                view.hideLoading();
                view.onGoogleSignInSuccess(user);
                view.navigateToHome();
            }

            @Override
            public void onError(String errorMessage) {
                view.hideLoading();
                view.onGoogleSignInFailed(errorMessage);

            }
        });

    }
}
