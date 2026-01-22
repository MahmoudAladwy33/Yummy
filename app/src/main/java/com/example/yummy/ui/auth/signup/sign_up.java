package com.example.yummy.ui.auth.signup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yummy.R;
import com.example.yummy.data.auth.SignUpRepo;
import com.example.yummy.data.auth.datasource.remote.SignUpRemoteDataSource;
import com.example.yummy.ui.auth.signup.presenter.SignUpPresenter;
import com.example.yummy.ui.auth.signup.presenter.SignUpPresenterImp;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up extends Fragment  implements SignUpView{


    public sign_up() {
        // Required empty public constructor
    }
    EditText et_email ;
    EditText et_password ;
    EditText et_confirm_pass ;


    Button btn_sign_up;

    SignUpPresenter presenter;
    ProgressBar progressBar;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SignUpPresenterImp(this, new SignUpRepo(getContext()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_login= view.findViewById(R.id.tv_log_in);
        et_email = view.findViewById(R.id.et_sign_up_email);
        et_password = view.findViewById(R.id.et_sign_up_password);
        et_confirm_pass = view.findViewById(R.id.et_confirm_password);
        btn_sign_up = view.findViewById(R.id.btn_sig_up);
        progressBar = view.findViewById(R.id.progress_sign_up);


        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString().trim();
                String pass = et_password.getText().toString().trim();
                String confirmPassword = et_confirm_pass.getText().toString().trim();
                presenter.signUp(email, pass, confirmPassword);

            }
        });
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_sign_up_to_login);
            }
        });
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        btn_sign_up.setEnabled(false);

    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        btn_sign_up.setEnabled(true);
    }
}