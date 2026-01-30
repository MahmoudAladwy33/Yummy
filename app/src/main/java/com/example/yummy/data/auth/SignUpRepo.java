package com.example.yummy.data.auth;

import android.content.Context;

import com.example.yummy.data.auth.datasource.remote.SignUpRemoteDataSource;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Single;

public class SignUpRepo {
    private SignUpRemoteDataSource signUpRemoteDataSource;

    public SignUpRepo(Context context) {
        this.signUpRemoteDataSource = new SignUpRemoteDataSource();
    }

    public Single<FirebaseUser> signUp(String email, String password) {
        return signUpRemoteDataSource.signUp(email, password);
    }
}