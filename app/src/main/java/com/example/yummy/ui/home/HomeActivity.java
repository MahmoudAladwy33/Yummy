package com.example.yummy.ui.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.yummy.R;
import com.example.yummy.ui.auth.AuthActivity;
import com.example.yummy.utils.GuestGuard;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private GuestGuard guestGuard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        guestGuard = new GuestGuard(this);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_home);
        NavController navController = navHostFragment.getNavController();


        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        bottomNavigationView.setOnItemSelectedListener(menuItem -> {

            int id = menuItem.getItemId();

            if (id == R.id.favouriteMealsFragment || id == R.id.calenderFragment || id == R.id.profileFragment) {
                guestGuard.runIfNotGuest(
                        () -> NavigationUI.onNavDestinationSelected(menuItem, navController),
                        this::showLoginHint
                );
                return false;
            }


            return NavigationUI.onNavDestinationSelected(menuItem, navController);
        });

    }


    private void showLoginHint() {
        android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(this)
                .setTitle("Login Required")
                .setMessage("You need to login to use this feature")
                .setPositiveButton("Login", (dialogInterface, which) -> {
                    Intent intent = new Intent(this, AuthActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Cancel", null)
                .show();


        dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                .setTextColor(getResources().getColor(R.color.MainColor));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(getResources().getColor(R.color.red));


        TextView title = dialog.findViewById(
                this.getResources().getIdentifier("alertTitle", "id", "android"));
        if (title != null) {
            title.setTextColor(getResources().getColor(R.color.black));
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            title.setTypeface(title.getTypeface(), Typeface.BOLD);
        }


        TextView message = dialog.findViewById(android.R.id.message);
        if (message != null) {
            message.setTextColor(getResources().getColor(R.color.gray_800));
            message.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        }
    }
}
