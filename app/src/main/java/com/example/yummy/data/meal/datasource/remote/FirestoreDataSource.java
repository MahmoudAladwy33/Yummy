package com.example.yummy.data.meal.datasource.remote;

import com.example.yummy.data.meal.model.FavMealRoom;
import com.example.yummy.data.meal.model.PlannedMealRoom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class FirestoreDataSource {

    private final FirebaseFirestore firestore;
    private final FirebaseAuth auth;

    public FirestoreDataSource() {
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    private String getUserId() {
        return (auth.getCurrentUser() != null) ? auth.getCurrentUser().getUid() : "";
    }

    public Completable addToFavorites(FavMealRoom favMeal) {
        return Completable.create(emitter -> {
            firestore.collection("users").document(getUserId())
                    .collection("favorites").document(favMeal.getMealId())
                    .set(favMeal)
                    .addOnSuccessListener(aVoid -> emitter.onComplete())
                    .addOnFailureListener(emitter::onError);
        });
    }

    public Completable removeFromFavorites(String mealId) {
        return Completable.create(emitter -> {
            firestore.collection("users").document(getUserId())
                    .collection("favorites").document(mealId)
                    .delete()
                    .addOnSuccessListener(aVoid -> emitter.onComplete())
                    .addOnFailureListener(emitter::onError);
        });
    }

    public Single<List<FavMealRoom>> getFavorites() {
        return Single.create(emitter -> {
            firestore.collection("users").document(getUserId())
                    .collection("favorites").get()
                    .addOnSuccessListener(snapshot -> emitter.onSuccess(snapshot.toObjects(FavMealRoom.class)))
                    .addOnFailureListener(emitter::onError);
        });
    }

    public Completable addToPlanned(PlannedMealRoom plannedMeal) {
        return Completable.create(emitter -> {
            firestore.collection("users").document(getUserId())
                    .collection("plannedMeals").document(plannedMeal.getMealId())
                    .set(plannedMeal)
                    .addOnSuccessListener(aVoid -> emitter.onComplete())
                    .addOnFailureListener(emitter::onError);
        });
    }

    public Completable removeFromPlanned(String mealId) {
        return Completable.create(emitter -> {
            firestore.collection("users").document(getUserId())
                    .collection("plannedMeals").document(mealId)
                    .delete()
                    .addOnSuccessListener(aVoid -> emitter.onComplete())
                    .addOnFailureListener(emitter::onError);
        });
    }

    public Single<List<PlannedMealRoom>> getPlanned() {
        return Single.create(emitter -> {
            firestore.collection("users").document(getUserId())
                    .collection("plannedMeals").get()
                    .addOnSuccessListener(snapshot -> emitter.onSuccess(snapshot.toObjects(PlannedMealRoom.class)))
                    .addOnFailureListener(emitter::onError);
        });
    }
}