package com.example.yummy.data.auth.datasource.remote;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRemoteDataSource {

    private FirebaseAuth auth;

    public LoginRemoteDataSource() {
        auth = FirebaseAuth.getInstance();
    }
    public void login(String email, String password, LoginResponse callback) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        callback.onSuccess(user);
                    } else {
                        callback.onError(
                                task.getException() != null
                                        ? task.getException().getMessage()
                                        : "Login failed"
                        );
                    }
                });
    }

}
