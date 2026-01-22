package com.example.yummy.data.auth.datasource.remote;

import com.google.firebase.auth.FirebaseUser;

public interface SignUpResponse {
    void onSuccess(FirebaseUser user);
    void onError(String error);
}
