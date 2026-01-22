package com.example.yummy.data.auth.datasource.remote;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleSignDataSource {

   private FirebaseAuth auth ;

    public GoogleSignDataSource() {
         auth = FirebaseAuth.getInstance();
    }


    public void signWithGoogle(String idToken , GoogleSignResponse callback){
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(auth.getCurrentUser());
                    } else {
                        callback.onError(task.getException().getMessage());
                    }
                });
    }

    }

