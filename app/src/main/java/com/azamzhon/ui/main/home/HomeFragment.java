package com.azamzhon.ui.main.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.azamzhon.R;
import com.azamzhon.databinding.HomeFragmentBinding;
import com.azamzhon.ui.customs.SimpleSeekBarChangeListener;
import com.azamzhon.ui.question.QuestionActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding binding;
    private TextView number;
    private Button btnPlus, btnMinus;

    public final static String AMOUNT = "amount";
    public final static String ID = "id";
    public final static String DIFFICULTY = "difficulty";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        observe();
        setupListeners();
    }

    private void observe() {
        mViewModel.getCategories();
        mViewModel.data.observe(getViewLifecycleOwner(), categories -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,mViewModel.names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinner1.setAdapter(adapter);
        });
        mViewModel.progressBarSuccess.observe(getViewLifecycleOwner(), integer -> {
                number.setText(String.valueOf(integer));
                binding.homeSeekBar.setProgress(integer);
        });

    }

    private void initialize(View view) {
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        number = view.findViewById(R.id.questions_amount);
        btnPlus = view.findViewById(R.id.btn_plus);
        btnMinus = view.findViewById(R.id.btn_minus);
    }

    private void setupListeners() {
        btnPlus.setOnClickListener(view -> mViewModel.increase());
        btnMinus.setOnClickListener(view -> mViewModel.decrease());
        binding.btnStart.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), QuestionActivity.class);
            intent.putExtra(HomeFragment.AMOUNT,binding.homeSeekBar.getProgress());
            intent.putExtra(HomeFragment.ID,mViewModel.data.getValue().get(binding.spinner1.getSelectedItemPosition()).getId());
            intent.putExtra(HomeFragment.DIFFICULTY,binding.spinner2.getSelectedItem().toString().toLowerCase());
            getContext().startActivity(intent);
        });
        binding.homeSeekBar.setOnSeekBarChangeListener(new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.questionsAmount.setText(String.valueOf(i));
            }
        });
    }
}