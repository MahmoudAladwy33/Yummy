package com.example.yummy.data.auth.datasource;

import android.content.Context;

import com.example.yummy.data.auth.datasource.remote.GoogleSignDataSource;
import com.example.yummy.data.auth.datasource.remote.GoogleSignResponse;

public class GoogleSignRepo {

    private GoogleSignDataSource googleSignDataSource;

    public GoogleSignRepo(Context context) {
        this.googleSignDataSource = new GoogleSignDataSource();
    }

    public  void SignWithGoogle(String idToken , GoogleSignResponse callback){

        googleSignDataSource.signWithGoogle(idToken,callback);
    }
}
