package com.example.yummy.ui.auth.signup.presenter;

public interface SignUpPresenter {
    void signUp(String email, String password, String confirmPassword);

    void dispose();
}
