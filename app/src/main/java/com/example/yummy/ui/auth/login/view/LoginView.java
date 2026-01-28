package com.example.yummy.ui.auth.login.view;

public interface LoginView {
    void showMessage(String message);

    void showLoading();

    void hideLoading();

    void navigateToHome();
}
