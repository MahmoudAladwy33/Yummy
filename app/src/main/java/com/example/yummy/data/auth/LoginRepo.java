package com.example.yummy.data.auth;

import android.content.Context;

import com.example.yummy.data.auth.datasource.remote.LoginRemoteDataSource;
import com.example.yummy.data.auth.datasource.remote.LoginResponse;

public class LoginRepo {
    
    private LoginRemoteDataSource loginRemoteDataSource;

    public LoginRepo(Context context) {
        this.loginRemoteDataSource = new LoginRemoteDataSource();

    }

    public void login(String email , String password ,  LoginResponse callback){

        loginRemoteDataSource.login(email,password , callback);
    }
}
