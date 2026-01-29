package com.example.yummy.ui.home.profile;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yummy.R;
import com.example.yummy.ui.auth.AuthActivity;
import com.example.yummy.ui.home.profile.presenter.ProfilePresenter;
import com.example.yummy.ui.home.profile.presenter.ProfilePresenterImp;


public class ProfileFragment extends Fragment implements ProfileViews {

    TextView tv_email;
    Button logoutButton;
    private ProfilePresenter logoutPresenter;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_email = view.findViewById(R.id.tv_email);
        logoutButton = view.findViewById(R.id.btn_logout);
        logoutPresenter = new ProfilePresenterImp(requireContext(), this);
        logoutPresenter.showUserEmail();
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogoutDialog();
            }
        });


    }

    @Override
    public void showLogoutDialog() {

        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", (dialogInterface, which) -> {
                    logoutPresenter.logout();
                })
                .setNegativeButton("No", null)
                .show();


        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(getResources().getColor(R.color.MainColor));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(getResources().getColor(R.color.red));


        TextView title = dialog.findViewById(
                requireContext().getResources().getIdentifier("alertTitle", "id", "android"));
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

    @Override
    public void showUserEmail(String email) {
        tv_email.setText(email);
    }

    @Override
    public void navigateToLoginScreen() {
        Intent intent = new Intent(requireContext(), AuthActivity.class);
        startActivity(intent);
        requireActivity().finish();

    }
}