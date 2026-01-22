package com.example.yummy.ui.auth.login.presenter;

import com.example.yummy.data.auth.LoginRepo;
import com.example.yummy.data.auth.datasource.remote.LoginResponse;
import com.example.yummy.ui.auth.login.LoginView;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenterImp implements LoginPresenter{

    private LoginRepo loginRepo ;
    private LoginView view ;


    public LoginPresenterImp(LoginRepo loginRepo , LoginView view) {
        this.loginRepo = loginRepo;
        this.view = view ;

    }

    @Override
    public void login(String email, String password) {
        if(email.isEmpty() || password.isEmpty() ){
            view.showMessage("Please fill all fields");
            return;
        }
        view.showLoading();

        loginRepo.login(email, password, new LoginResponse() {
            @Override
            public void onSuccess(FirebaseUser user) {
                view.hideLoading();
                view.showMessage("Login Success");
            }

            @Override
            public void onError(String error) {
                view.hideLoading();
                view.showMessage(error);
            }
        });
    }
}
