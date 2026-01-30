package com.example.yummy.data.auth.datasource.remote;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import io.reactivex.rxjava3.core.Single;

public class GoogleSignDataSource {
    private FirebaseAuth auth;

    public GoogleSignDataSource() {
        auth = FirebaseAuth.getInstance();
    }

    public Single<FirebaseUser> signWithGoogle(String idToken) {
        return Single.create(emitter -> {
            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
            auth.signInWithCredential(credential)
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

