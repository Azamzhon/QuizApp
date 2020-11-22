package com.azamzhon.ui.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.azamzhon.R;
import com.azamzhon.data.models.QuestionModel;
import com.azamzhon.data.models.QuizResult;
import com.azamzhon.databinding.ActivityResultBinding;
import com.azamzhon.ui.main.home.HomeFragment;
import com.azamzhon.ui.question.QuestionActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultActivity extends AppCompatActivity{

    ResultViewModel resultViewModel;
    ActivityResultBinding binding;
    private QuizResult quizResult;
    private int correctAnswers;
    private Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_result);
        initialize();
        observe();
        btnFinish.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(ResultActivity.this, HomeFragment.class);
            startActivity(intent);
        });
    }

    private void initialize() {
        resultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);
        binding.setViewModel(resultViewModel);
        correctAnswers = getIntent().getIntExtra(QuestionActivity.CORRECT_ANSWERS, 0);
        resultViewModel.getData(getIntent());
        btnFinish = findViewById(R.id.btn_finish);
    }

    private void observe() {
        resultViewModel.questionsData.observe(this, questionModels -> {
            if (questionModels != null && questionModels.size() != 0){
                QuestionModel question = questionModels.get(0);
                quizResult = new QuizResult(
                        question.getCategory(),
                        question.getDifficulty(),
                        correctAnswers,
                        new Date(System.currentTimeMillis()),
                        questionModels.size());
                binding.setResult(quizResult);
                resultViewModel.getPercent(questionModels.size(), correctAnswers);
            }
        });
    }

    void getDate(long time){
        @SuppressLint("SimpleDateFormat") String dateString = new SimpleDateFormat("MM/dd/yyyy").format(time);
    }
}