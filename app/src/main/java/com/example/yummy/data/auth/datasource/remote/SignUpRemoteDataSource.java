package com.example.yummy.data.auth.datasource.remote;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpRemoteDataSource {

    private FirebaseAuth auth;

    public SignUpRemoteDataSource() {
        auth = FirebaseAuth.getInstance();
    }

    public void signUp(String email, String password, SignUpResponse callback) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        callback.onSuccess(user);
                    } else {
                        callback.onError(task.getException() != null ? task.getException().getMessage() : "Unknown Error");
                    }
                });
    }
}

