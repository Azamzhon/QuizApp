package com.azamzhon.ui.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.azamzhon.R;
import com.azamzhon.data.models.QuizResult;
import com.azamzhon.databinding.ActivityResultBinding;
import com.azamzhon.ui.main.home.HomeFragment;
import com.azamzhon.ui.question.OnAnswerClickListener;

import java.text.SimpleDateFormat;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

    ResultViewModel resultViewModel;
    ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_result);
        resultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);
        resultViewModel.getData(getIntent());
        resultViewModel.liveData.observe(this, (Observer<QuizResult>) quizResult -> binding.setResult(quizResult));
        binding.btnFinish.setOnClickListener(this);
    }

    void getDate(long time){
        String dateString = new SimpleDateFormat("MM/dd/yyyy").format(time);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ResultActivity.this, HomeFragment.class);
        startActivity(intent);
    }
}