package com.example.yummy.ui.auth.Google.presenter;

import android.content.Context;

import com.example.yummy.data.auth.AuthRepo;
import com.example.yummy.data.common.SessionManager;
import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.ui.auth.Google.view.GoogleView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GooglePresenterImp implements GooglePresenter {

    private SessionManager sessionManager;
    private GoogleView view;
    private AuthRepo authRepo;
    private MealRepo mealRepo;


    private CompositeDisposable disposables = new CompositeDisposable();

    public GooglePresenterImp(GoogleView view, AuthRepo authRepo, Context context) {
        this.view = view;
        this.authRepo = authRepo;
        this.sessionManager = SessionManager.getInstance(context);
        this.mealRepo = new MealRepo(context);
    }

    @Override
    public void signInWithGoogle(String idToken) {
        view.showLoading();

        disposables.add(
                authRepo.signWithGoogle(idToken)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                user -> {

                                    sessionManager.setGuest(false);
                                    mealRepo.syncFavFromFirestore();
                                    mealRepo.syncPlanedFromFirestore();
                                    view.hideLoading();
                                    view.onGoogleSignInSuccess(user);
                                    view.navigateToHome();
                                },
                                throwable -> {

                                    view.hideLoading();
                                    view.onGoogleSignInFailed(throwable.getMessage());
                                }
                        )
        );
    }

    @Override
    public void dispose() {
        disposables.clear();
    }
}
