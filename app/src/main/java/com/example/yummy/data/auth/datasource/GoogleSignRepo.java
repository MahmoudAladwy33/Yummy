package com.example.yummy.data.auth.datasource;

import android.content.Context;

import com.example.yummy.data.auth.datasource.remote.GoogleSignDataSource;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Single;

public class GoogleSignRepo {

    private GoogleSignDataSource googleSignDataSource;

    public GoogleSignRepo(Context context) {
        this.googleSignDataSource = new GoogleSignDataSource();
    }

    public Single<FirebaseUser> signWithGoogle(String idToken) {
        return googleSignDataSource.signWithGoogle(idToken);
    }
}
