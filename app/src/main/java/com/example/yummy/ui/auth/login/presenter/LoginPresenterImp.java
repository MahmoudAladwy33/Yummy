package com.example.yummy.ui.auth.login.presenter;

import android.content.Context;
import android.util.Patterns;

import com.example.yummy.data.auth.AuthRepo;
import com.example.yummy.data.common.SessionManager;
import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.ui.auth.login.view.LoginView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginPresenterImp implements LoginPresenter {

    private static final String TAG = "LoginPresenter";
    private AuthRepo authRepo;
    private LoginView view;
    private SessionManager sessionManager;
    private MealRepo mealRepo;
    private CompositeDisposable disposables = new CompositeDisposable();

    public LoginPresenterImp(AuthRepo authRepo, LoginView view, Context context) {
        this.authRepo = authRepo;
        this.view = view;
        this.sessionManager = SessionManager.getInstance(context);
        this.mealRepo = new MealRepo(context);
    }

    @Override
    public void login(String email, String password) {

        if (isNotValid(email, password)) {
            return;
        }

        view.showLoading();
        disposables.add(
                authRepo.login(email.trim(), password.trim())
                        .subscribeOn(Schedulers.io())
                        .flatMapCompletable(user -> {
                            sessionManager.setGuest(false);
                            return mealRepo.clearAllTables()
                                    .andThen(mealRepo.syncFavFromFirestore())
                                    .andThen(mealRepo.syncPlanedFromFirestore());
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                () -> {
                                    view.hideLoading();
                                    view.showMessage("Login Success");
                                    view.navigateToHome();
                                },
                                throwable -> {
                                    view.hideLoading();
                                    view.showMessage("Login failed");
                                }
                        )
        );

    }

    @Override
    public void loginAsGuest() {
        sessionManager.setGuest(true);
        view.navigateToHome();
    }

    @Override
    public void dispose() {
        disposables.clear();
    }

    private boolean isNotValid(String email, String password) {
        String e = email.trim();
        String p = password.trim();

        if (e.isEmpty() || p.isEmpty()) {
            view.showMessage("Please fill all fields");
            return true;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(e).matches()) {
            view.showMessage("Please enter a valid email");
            return true;
        }

        if (p.length() < 6) {
            view.showMessage("Password must be at least 6 characters");
            return true;
        }

        if (p.contains(" ") || e.contains(" ")) {
            view.showMessage("Email or Password cannot contain spaces");
            return true;
        }

        return false;
    }
}