package com.example.yummy.ui.auth.Google.presenter;

import android.content.Context;

import com.example.yummy.data.auth.datasource.GoogleSignRepo;
import com.example.yummy.data.common.SessionManager;
import com.example.yummy.data.meal.MealRepo;
import com.example.yummy.ui.auth.Google.view.GoogleView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GooglePresenterImp implements GooglePresenter {

    private SessionManager sessionManager;
    private GoogleView view;
    private GoogleSignRepo googleSignRepo;
    private MealRepo mealRepo;


    private CompositeDisposable disposables = new CompositeDisposable();

    public GooglePresenterImp(GoogleView view, GoogleSignRepo googleSignRepo, Context context) {
        this.view = view;
        this.googleSignRepo = googleSignRepo;
        this.sessionManager = SessionManager.getInstance(context);
        this.mealRepo = new MealRepo(context);
    }

    @Override
    public void signInWithGoogle(String idToken) {
        view.showLoading();

        disposables.add(
                googleSignRepo.signWithGoogle(idToken)
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
