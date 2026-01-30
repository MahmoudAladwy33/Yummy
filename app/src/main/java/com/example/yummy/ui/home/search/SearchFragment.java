package com.example.yummy.ui.home.search;

import static android.view.View.GONE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yummy.R;
import com.example.yummy.ui.home.HomeActivity;


public class SearchFragment extends Fragment {

    EditText etSearch;


    public SearchFragment() {
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((HomeActivity) requireActivity()).findViewById(R.id.bottom_nav_view).setVisibility(GONE);
        etSearch = view.findViewById(R.id.et_Search);
        etSearch.requestFocus();
        etSearch.post(() -> {
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(requireContext().INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(etSearch, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }
}