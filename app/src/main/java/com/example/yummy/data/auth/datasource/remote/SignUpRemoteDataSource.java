package com.example.yummy.data.auth.datasource.remote;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
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
                        Exception e = task.getException();
                        String errorMessage;

                        if (e instanceof FirebaseAuthWeakPasswordException) {

                            errorMessage = "Password is too weak. Must be at least 6 characters";

                        } else if (e instanceof FirebaseAuthUserCollisionException) {

                            errorMessage = "Email already registered";

                        } else if (e instanceof FirebaseAuthInvalidCredentialsException) {

                            errorMessage = "Invalid email format";

                        } else {
                            errorMessage = "Sign Up failed, please try again";
                        }

                        callback.onError(errorMessage);
                    }
                });
    }
}

