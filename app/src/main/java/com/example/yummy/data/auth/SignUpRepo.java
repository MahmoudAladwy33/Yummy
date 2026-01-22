package com.example.yummy.data.auth;

import android.content.Context;

import com.example.yummy.data.auth.datasource.remote.SignUpRemoteDataSource;
import com.example.yummy.data.auth.datasource.remote.SignUpResponse;

public class SignUpRepo {
    private SignUpRemoteDataSource signUpRemoteDataSource;

    public SignUpRepo(Context context) {
        this.signUpRemoteDataSource = new SignUpRemoteDataSource();
    }

    public void signUp(String email, String password, SignUpResponse callback) {
        signUpRemoteDataSource.signUp(email, password, callback);
    }
}


