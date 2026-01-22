package com.example.yummy.ui.auth;

import com.google.firebase.auth.FirebaseUser;

public interface GoogleView {
    void showLoading();
    void hideLoading();
    void onGoogleSignInSuccess(FirebaseUser user);
    void onGoogleSignInFailed(String message);
}
