package com.example.yummy.ui.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.yummy.R;
import com.example.yummy.ui.auth.AuthActivity;

public class onboarding_second_screen extends Fragment {
    public onboarding_second_screen() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_onboarding_second_screen, container, false);
        Button btn = view.findViewById(R.id.btn_onboarding_second_screen_next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), AuthActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });
        return view;
    }
}