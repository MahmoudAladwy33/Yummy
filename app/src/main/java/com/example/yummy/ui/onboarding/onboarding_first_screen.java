package com.example.yummy.ui.onboarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yummy.R;

public class onboarding_first_screen extends Fragment {



    public onboarding_first_screen() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
             View view =   inflater.inflate(R.layout.fragment_onboarding_first_screen, container, false);
             Button btn = view.findViewById(R.id.btn_onboarding_first_screen_next);
             btn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Navigation.findNavController(view).navigate(R.id.action_onboarding_first_screen_to_onboarding_second_screen);
                 }
             });
             return  view;
    }
}