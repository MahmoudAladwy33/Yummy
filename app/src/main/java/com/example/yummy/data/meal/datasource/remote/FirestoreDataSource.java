package com.example.yummy.data.meal.datasource.remote;


import com.example.yummy.data.meal.model.FavMealRoom;
import com.example.yummy.data.meal.model.PlannedMealRoom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FirestoreDataSource {

    private final FirebaseFirestore firestore;
    private final FirebaseAuth auth;

    public FirestoreDataSource() {
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    private String getUserId() {
        return auth.getCurrentUser().getUid();
    }


    public void addToFavorites(FavMealRoom favMeal, OnCompleteFirestoreListener listener) {
        firestore
                .collection("users")
                .document(getUserId())
                .collection("favorites")
                .document(favMeal.getMealId())
                .set(favMeal)
                .addOnSuccessListener(aVoid -> listener.onSuccess())
                .addOnFailureListener(listener::onFailure);
    }

    public void removeFromFavorites(String mealId, OnCompleteFirestoreListener listener) {
        firestore
                .collection("users")
                .document(getUserId())
                .collection("favorites")
                .document(mealId)
                .delete()
                .addOnSuccessListener(aVoid -> listener.onSuccess())
                .addOnFailureListener(listener::onFailure);
    }

    public void getFavorites(OnFavMealsFetchedFirestore callback) {
        firestore
                .collection("users")
                .document(getUserId())
                .collection("favorites")
                .get()
                .addOnSuccessListener(snapshot -> {
                    List<FavMealRoom> meals = snapshot.toObjects(FavMealRoom.class);
                    callback.onFavSuccess(meals);

                });
    }


    public void addToPlanned(PlannedMealRoom plannedMeal, OnCompleteFirestoreListener listener) {
        firestore
                .collection("users")
                .document(getUserId())
                .collection("plannedMeals")
                .document(plannedMeal.getMealId())
                .set(plannedMeal)
                .addOnSuccessListener(aVoid -> listener.onSuccess())
                .addOnFailureListener(listener::onFailure);
    }

    public void removeFromPlanned(String mealId, OnCompleteFirestoreListener listener) {
        firestore
                .collection("users")
                .document(getUserId())
                .collection("plannedMeals")
                .document(mealId)
                .delete()
                .addOnSuccessListener(aVoid -> listener.onSuccess())
                .addOnFailureListener(listener::onFailure);
    }


    public void getPlanned(OnPlannedMealsFetchedFirestore callback) {
        firestore
                .collection("users")
                .document(getUserId())
                .collection("plannedMeals")
                .get()
                .addOnSuccessListener(snapshot -> {
                    List<PlannedMealRoom> meals = snapshot.toObjects(PlannedMealRoom.class);
                    callback.onPlannedSuccess(meals);

                });
    }
}
