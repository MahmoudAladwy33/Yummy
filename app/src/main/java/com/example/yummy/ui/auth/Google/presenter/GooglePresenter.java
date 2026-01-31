package com.example.yummy.ui.auth.Google.presenter;

public interface GooglePresenter {
    void signInWithGoogle(String idToken);

    void dispose();
}
