package com.example.yummy.data.auth;

import android.content.Context;

import com.example.yummy.data.auth.datasource.remote.LoginRemoteDataSource;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Single;

public class LoginRepo {
    private LoginRemoteDataSource loginRemoteDataSource;

    public LoginRepo(Context context) {
        this.loginRemoteDataSource = new LoginRemoteDataSource();
    }

    public Single<FirebaseUser> login(String email, String password) {
        return loginRemoteDataSource.login(email, password);
    }
}