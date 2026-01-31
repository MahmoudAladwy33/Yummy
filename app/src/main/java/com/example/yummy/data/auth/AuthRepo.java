package com.example.yummy.data.auth;

import android.content.Context;

import com.example.yummy.data.auth.datasource.remote.AuthDataSource;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Single;

public class AuthRepo {


    private AuthDataSource authDataSource;

    public AuthRepo(Context context) {
        this.authDataSource = new AuthDataSource();
    }

    public Single<FirebaseUser> signWithGoogle(String idToken) {
        return authDataSource.signWithGoogle(idToken);
    }

    public Single<FirebaseUser> login(String email, String password) {
        return authDataSource.login(email, password);
    }

    public Single<FirebaseUser> signUp(String email, String password) {
        return authDataSource.signUp(email, password);
    }
}
