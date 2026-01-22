package com.example.yummy.ui.auth.signup.presenter;

import com.example.yummy.data.auth.SignUpRepo;
import com.example.yummy.data.auth.datasource.remote.SignUpRemoteDataSource;
import com.example.yummy.data.auth.datasource.remote.SignUpResponse;
import com.example.yummy.ui.auth.signup.SignUpView;
import com.google.firebase.auth.FirebaseUser;

public class SignUpPresenterImp  implements SignUpPresenter{

    private SignUpView view;
    private SignUpRepo signUpRepo;

    public SignUpPresenterImp(SignUpView view, SignUpRepo signUpRepo ) {
        this.view = view;
        this.signUpRepo = signUpRepo;
    }


    @Override
    public void signUp(String email, String password, String confirmPassword) {
        if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            view.showMessage("Please fill all fields");
            return;
        }

        if(!password.equals(confirmPassword)){
            view.showMessage("Passwords do not match");
            return;
        }

        view.showLoading();

        signUpRepo.signUp(email, password, new SignUpResponse() {
            @Override
            public void onSuccess(FirebaseUser user) {
                view.hideLoading();
                view.showMessage("Sign up Success");
            }

            @Override
            public void onError(String error) {
                view.hideLoading();
                view.showMessage(error);
            }
        });
    }

}
