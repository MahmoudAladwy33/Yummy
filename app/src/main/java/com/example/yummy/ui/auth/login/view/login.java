package com.example.yummy.ui.auth.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.yummy.R;
import com.example.yummy.data.auth.LoginRepo;
import com.example.yummy.data.auth.datasource.GoogleSignRepo;
import com.example.yummy.ui.auth.GoogleView;
import com.example.yummy.ui.auth.login.presenter.LoginPresenter;
import com.example.yummy.ui.auth.login.presenter.LoginPresenterImp;
import com.example.yummy.ui.auth.presenter.GooglePresenter;
import com.example.yummy.ui.auth.presenter.GooglePresenterImp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

public class login extends Fragment implements LoginView, GoogleView {

    private static final int RC_SIGN_IN = 100;
    EditText et_email;
    EditText et_pass;
    Button btn_login;
    ProgressBar progressBar;
    LoginPresenter presenter;
    GooglePresenter gPresenter;
    Button btn_google;
    private GoogleSignInClient googleSignInClient; // Class-level variable

    public login() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenterImp(new LoginRepo(getContext()), this);
        gPresenter = new GooglePresenterImp(this, new GoogleSignRepo(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv_signup = view.findViewById(R.id.tv_sign_up);
        et_email = view.findViewById(R.id.et_email);
        et_pass = view.findViewById(R.id.et_password);
        btn_login = view.findViewById(R.id.btn_login);
        btn_google = view.findViewById(R.id.btn_google);
        progressBar = view.findViewById(R.id.progress_login);

        tv_signup.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_login_to_sign_up)
        );

        btn_login.setOnClickListener(v -> {
            String email = et_email.getText().toString().trim();
            String pass = et_pass.getText().toString().trim();

            presenter.login(email, pass);
        });

        // Initialize GoogleSignInClient
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);

        btn_google.setOnClickListener(v -> {
            Intent signInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }

    // Handle Google Sign-In result
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    gPresenter.signInWithGoogle(account.getIdToken());
                }
            } catch (ApiException e) {
                onGoogleSignInFailed(e.getMessage());
            }
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        btn_login.setEnabled(false);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        btn_login.setEnabled(true);
        btn_login.setText(getString(R.string.btn_login));
    }

    @Override
    public void onGoogleSignInSuccess(FirebaseUser user) {
        Toast.makeText(getContext(), "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGoogleSignInFailed(String message) {
        Toast.makeText(getContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
    }
}
