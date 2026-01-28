package com.example.yummy.ui.auth.Google.presenter;

import com.example.yummy.data.auth.datasource.GoogleSignRepo;
import com.example.yummy.data.auth.datasource.remote.GoogleSignResponse;
import com.example.yummy.ui.auth.Google.view.GoogleView;
import com.google.firebase.auth.FirebaseUser;

public class GooglePresenterImp implements GooglePresenter {

    private GoogleView view;
    private GoogleSignRepo googleSignRepo;


    public GooglePresenterImp(GoogleView view, GoogleSignRepo googleSignRepo) {
        this.view = view;
        this.googleSignRepo = googleSignRepo;
    }


    @Override
    public void signInWithGoogle(String idToken) {

        googleSignRepo.SignWithGoogle(idToken, new GoogleSignResponse() {
            @Override
            public void onSuccess(FirebaseUser user) {
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
