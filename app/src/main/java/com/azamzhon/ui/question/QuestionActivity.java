package com.azamzhon.ui.question;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.azamzhon.R;
import com.azamzhon.databinding.ActivityQuestionBinding;
import com.azamzhon.ui.customs.CustomLinearLayoutManager;
import com.azamzhon.ui.main.home.HomeFragment;
import com.azamzhon.ui.result.ResultActivity;
import com.google.gson.Gson;

public class QuestionActivity extends AppCompatActivity implements OnAnswerClickListener, View.OnClickListener {

    private ActivityQuestionBinding binding;
    private QuestionViewModel questionViewModel;
    private QuestionAdapter adapter;
    private int clickedPosition;
    private int position;

    public static final String CORRECT_ANSWERS = "correct_answers";
    public static final String QUESTIONS = "questions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question);
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        binding.setViewModel(questionViewModel);
        initRecyclerView();

        getQuestions();
        lastQuestion();
        setupListeners();
    }

    private void setupListeners() {
        binding.btnSkip.setOnClickListener(this);

        binding.questionRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                CustomLinearLayoutManager linearLayoutManager = (CustomLinearLayoutManager) recyclerView.getLayoutManager();
                if (linearLayoutManager != null)
                position = linearLayoutManager.findFirstVisibleItemPosition();
                binding.setPosition(position + 1);
                binding.progressTv.setText((position + 1) + "/" + questionViewModel.mQuestion.size());
                binding.categoryTitle.setText(questionViewModel.categories.get(position));
            }
        });
    }

    private void initRecyclerView() {
        adapter = new QuestionAdapter();
        adapter.setListener(this);
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.questionRecyclerview.setLayoutManager(layoutManager);
        binding.questionRecyclerview.setAdapter(adapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        binding.setPosition(1);
        snapHelper.attachToRecyclerView(binding.questionRecyclerview);

    }

    private void getQuestions() {
        Intent intent = getIntent();
        questionViewModel.getQuestions(intent.getIntExtra(HomeFragment.AMOUNT, 10),
                intent.getIntExtra(HomeFragment.ID, 9),
                intent.getStringExtra(HomeFragment.DIFFICULTY));
        questionViewModel.questionLiveData.observe(this, questionModels -> {
            adapter.setQuestions(questionModels);
            binding.progressBar.setMax(questionModels.size());
            binding.categoryTitle.setText(questionModels.get(0).getCategory());
        });
    }

    private void lastQuestion() {
        questionViewModel.isLast.observe(this, correctAnswer -> {
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            intent.putExtra(CORRECT_ANSWERS, correctAnswer);
            intent.putExtra(QUESTIONS, new Gson().toJson(questionViewModel.mQuestion));
            startActivity(intent);
        });
    }

    @Override
    public void onClick(int position, int answerPosition) {
        binding.questionRecyclerview.scrollToPosition(position + 1);
        clickedPosition = position;

        questionViewModel.onAnswerClick(position,answerPosition);
        questionViewModel.moveToQuestionFinish(position,System.currentTimeMillis());

        questionViewModel.mQuestion.get(position).setSelectQuestionPosition(answerPosition);
        questionViewModel.mQuestion.get(position).setClicked(true);

        adapter.notifyDataSetChanged();

        Toast.makeText(getApplicationContext(), "onClick:\nposition = " + position + "\nanswerPosition = " + answerPosition, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (position > 0){
            binding.questionRecyclerview.scrollToPosition(position - 1);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        if (position > clickedPosition){
            questionViewModel.skip(position);
        }
        binding.questionRecyclerview.scrollToPosition(position + 1);
    }
}