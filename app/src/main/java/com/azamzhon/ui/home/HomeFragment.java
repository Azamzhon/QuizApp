package com.azamzhon.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.azamzhon.R;
import com.azamzhon.ui.question.QuestionActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private TextView number;
    private Button btnPlus, btnMinus, btnStart;

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
        btnPlus = view.findViewById(R.id.btn_plus);
        btnMinus = view.findViewById(R.id.btn_minus);
        btnStart = view.findViewById(R.id.btn_start);
    }

    private void setupListeners() {
        btnPlus.setOnClickListener(view -> mViewModel.increase());
        btnMinus.setOnClickListener(view -> mViewModel.decrease());
        btnStart.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), QuestionActivity.class);
            startActivity(intent);
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