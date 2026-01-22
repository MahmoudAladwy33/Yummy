package com.example.yummy.data.auth.datasource.remote;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseUser;

public interface GoogleSignResponse {
    void onSuccess(FirebaseUser user);
    void onError(String errorMessage);
}
