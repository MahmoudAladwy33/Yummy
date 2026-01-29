package com.example.yummy.ui.auth.login.presenter;

import android.content.Context;

import com.example.yummy.data.auth.LoginRepo;
import com.example.yummy.data.auth.datasource.remote.LoginResponse;
import com.example.yummy.data.common.SessionManager;
import com.example.yummy.ui.auth.login.view.LoginView;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenterImp implements LoginPresenter {

    private LoginRepo loginRepo;
    private LoginView view;
    private SessionManager sessionManager;


    public LoginPresenterImp(LoginRepo loginRepo, LoginView view, Context context) {
        this.loginRepo = loginRepo;
        this.view = view;
        this.sessionManager = SessionManager.getInstance(context);

    }

    @Override
    public void login(String email, String password) {
        email = email.trim();
        password = password.trim();


        if (email.isEmpty() || password.isEmpty()) {
            view.showMessage("Please fill all fields");
            return;
        }


        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showMessage("Please enter a valid email");
            return;
        }


        if (password.length() < 6) {
            view.showMessage("Password must be at least 6 characters");
            return;
        }


        if (password.contains(" ")) {
            view.showMessage("Password cannot contain spaces");
            return;
        }


        if (email.contains(" ")) {
            view.showMessage("Email cannot contain spaces");
            return;
        }
        view.showLoading();

        loginRepo.login(email, password, new LoginResponse() {
            @Override
            public void onSuccess(FirebaseUser user) {
                view.hideLoading();
                view.showMessage("Login Success");
                view.navigateToHome();
            }

            @Override
            public void onError(String error) {
                view.hideLoading();
                view.showMessage(error);
            }
        });
    }

    @Override
    public void loginAsGuest() {
        sessionManager.setGuest(true);
        view.navigateToHome();

    }
}
