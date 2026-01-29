package com.example.yummy.data.meal.datasource.remote;

public interface OnCompleteFirestoreListener {

    void onSuccess();

    void onFailure(Exception e);
}
