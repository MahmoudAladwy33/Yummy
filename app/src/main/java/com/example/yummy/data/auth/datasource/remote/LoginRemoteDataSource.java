package com.example.yummy.data.auth.datasource.remote;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Single;

public class LoginRemoteDataSource {
    private FirebaseAuth auth;

    public LoginRemoteDataSource() {
        auth = FirebaseAuth.getInstance();
    }

    public Single<FirebaseUser> login(String email, String password) {
        return Single.create(emitter -> {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (!emitter.isDisposed()) {
                            if (task.isSuccessful()) {
                                emitter.onSuccess(auth.getCurrentUser());
                            } else {
                                emitter.onError(task.getException());
                            }
                        }
                    });
        });
    }
}