package com.example.yummy.data.auth.datasource.remote;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Single;

public class SignUpRemoteDataSource {

    private FirebaseAuth auth;

    public SignUpRemoteDataSource() {
        auth = FirebaseAuth.getInstance();
    }

    public Single<FirebaseUser> signUp(String email, String password) {
        return Single.create(emitter -> {
            auth.createUserWithEmailAndPassword(email, password)
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