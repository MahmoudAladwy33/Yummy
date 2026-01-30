package com.example.yummy.ui.auth.signup.presenter;

import android.content.Context;

import com.example.yummy.data.auth.AuthRepo;
import com.example.yummy.data.common.SessionManager;
import com.example.yummy.ui.auth.signup.view.SignUpView;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SignUpPresenterImp implements SignUpPresenter {

    private SignUpView view;
    private AuthRepo authRepo;
    private SessionManager sessionManager;
    private CompositeDisposable disposables = new CompositeDisposable();

    public SignUpPresenterImp(SignUpView view, AuthRepo authRepo, Context context) {
        this.view = view;
        this.authRepo = authRepo;
        this.sessionManager = SessionManager.getInstance(context);
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

        disposables.add(
                authRepo.signUp(email, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                user -> {
                                    sessionManager.setGuest(false);
                                    view.hideLoading();
                                    view.showMessage("Sign up Success");
                                    view.navigateToHome();
                                },
                                throwable -> {
                                    view.hideLoading();
                                    String errorMessage;
                                    if (throwable instanceof FirebaseAuthWeakPasswordException) {
                                        errorMessage = "Password is too weak. Must be at least 6 characters";
                                    } else if (throwable instanceof FirebaseAuthUserCollisionException) {
                                        errorMessage = "Email already registered";
                                    } else if (throwable instanceof FirebaseAuthInvalidCredentialsException) {
                                        errorMessage = "Invalid email format";
                                    } else {
                                        errorMessage = "Sign Up failed, please try again";
                                    }
                                    view.showMessage(errorMessage);
                                }
                        )
        );
    }

    @Override
    public void dispose() {
        disposables.clear();
    }
}