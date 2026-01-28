package com.example.yummy.data.auth.datasource.remote;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
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

                        Exception e = task.getException();
                        String errorMessage;

                        if (e instanceof FirebaseAuthInvalidUserException) {

                            errorMessage = "Email not registered";

                        } else if (e instanceof FirebaseAuthInvalidCredentialsException) {

                            errorMessage = "Wrong password";

                        } else {
                            errorMessage = "Login failed, try again";
                        }

                        callback.onError(errorMessage);
                    }
                });
    }

}
