package com.azamzhon.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.azamzhon.R;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private TextView number;
    private Button plus, minus;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
        setupListeners();
        observe();
    }

    private void initialize(View view) {
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        number = view.findViewById(R.id.questions_amount);
        plus = view.findViewById(R.id.btn_plus);
        minus = view.findViewById(R.id.btn_minus);
    }

    private void setupListeners() {
        plus.setOnClickListener(view -> {
            mViewModel.increase();
        });
        minus.setOnClickListener(view -> {
            mViewModel.decrease();
        });
    }

    private void observe() {
        mViewModel.progressBarSuccess.observe(getViewLifecycleOwner(), integer -> number.setText(String.valueOf(integer)));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}