package com.example.yummy.ui.auth.signup.presenter;

import android.content.Context;

import com.example.yummy.data.auth.SignUpRepo;
import com.example.yummy.data.auth.datasource.remote.SignUpResponse;
import com.example.yummy.data.common.SessionManager;
import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.ui.auth.signup.view.SignUpView;
import com.google.firebase.auth.FirebaseUser;

public class SignUpPresenterImp implements SignUpPresenter {

    private SignUpView view;
    private SignUpRepo signUpRepo;
    private SessionManager sessionManager;

    private MealRepo mealRepo;

    public SignUpPresenterImp(SignUpView view, SignUpRepo signUpRepo, Context context) {
        this.view = view;
        this.signUpRepo = signUpRepo;
        this.sessionManager = SessionManager.getInstance(context);
        this.mealRepo = new MealRepo(context);
    }


    @Override
    public void signUp(String email, String password, String confirmPassword) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            view.showMessage("Please fill all fields");
            return;
        }

        if (!password.equals(confirmPassword)) {
            view.showMessage("Passwords do not match");
            return;
        }

        view.showLoading();

        signUpRepo.signUp(email, password, new SignUpResponse() {
            @Override
            public void onSuccess(FirebaseUser user) {
                sessionManager.setGuest(false);
                mealRepo.syncFromFirestore();
                view.hideLoading();
                view.showMessage("Sign up Success");
                view.navigateToHome();
            }

            @Override
            public void onError(String error) {
                view.hideLoading();
                view.showMessage(error);
            }
        });
    }

}
