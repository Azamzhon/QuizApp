package com.azamzhon.ui.main.settings;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.azamzhon.R;

public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    private ConstraintLayout btn_share, btn_rate, btn_feedback, btn_clear;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_share = view.findViewById(R.id.layout_1);
        btn_rate = view.findViewById(R.id.layout_2);
        btn_feedback = view.findViewById(R.id.layout_3);
        btn_clear = view.findViewById(R.id.layout_4);

        btn_share.setOnClickListener(view1 -> Toast.makeText(getContext(), "Thank you for sharing this shit", Toast.LENGTH_LONG).show());
        btn_rate.setOnClickListener(view1 -> Toast.makeText(getContext(), "Thank you for rating this shit", Toast.LENGTH_LONG).show());
        btn_feedback.setOnClickListener(view1 -> Toast.makeText(getContext(), "Thank you for leaving feedback for this shit", Toast.LENGTH_LONG).show());
        btn_clear.setOnClickListener(view1 -> Toast.makeText(getContext(), "Thank you for cleaning this shit", Toast.LENGTH_LONG).show());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        // TODO: Use the ViewModel
    }

}