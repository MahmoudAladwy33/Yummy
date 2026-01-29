package com.example.yummy.utils;

import android.content.Context;

import com.example.yummy.data.common.SessionManager;


public class GuestGuard {

    private SessionManager sessionManager;

    public GuestGuard(Context context) {
        sessionManager = SessionManager.getInstance(context);
    }

    public void runIfNotGuest(Runnable action, Runnable onGuest) {
        if (sessionManager.isGuest()) {
            onGuest.run();
        } else {
            action.run();
        }
    }
}


