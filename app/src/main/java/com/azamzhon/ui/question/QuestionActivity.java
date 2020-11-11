package com.azamzhon.ui.question;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.azamzhon.R;
import com.azamzhon.data.models.QuestionModel;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    public static final String MULTI = "Multi Choice Question";
    public static final String DOUBLE = "Two-Way Question";
    private List<QuestionModel> data;
    private QuestionAdapter adapter;
    private RecyclerView recyclerView;
    private Button skip;
    private int posQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initialize();

        recyclerView.setAdapter(adapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        adapter.setData(data);
        dataAdd(data);

        setupListeners();
    }

    private void setupListeners() {
        adapter.setAnswerClickListener(isRightAns -> {
            if (isRightAns) {
                recyclerView.smoothScrollToPosition(posQuestion++);
            } else {
                Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
            }
        });
        skip.setOnClickListener(view -> recyclerView.smoothScrollToPosition(posQuestion++));
    }

    private void initialize() {
        skip = findViewById(R.id.btn_skip);
        data = new ArrayList<>();
        adapter = new QuestionAdapter();
        recyclerView = findViewById(R.id.question_recyclerview);
    }

    private void dataAdd(List<QuestionModel> data) {
        data.add(new QuestionModel(
                "Which year was the Premier League founded?",
                new String[]{"1901", "1992", "1934", "1994"},
                "1992",
                MULTI
        ));
        data.add(new QuestionModel(
                "Which player is the best in goals and assists in the football history ?",
                new String[]{"Zlatan Ibrahimovich", "Lionel Messi", "Cristiano Ronaldo", "Pele"},
                "Lionel Messi",
                MULTI
        ));
        data.add(new QuestionModel(
                "Which club won the 2017 UEFA Super Cup?",
                new String[]{"Manchester City", "Bayern Munchen", "Barcelona", "Real Madrid"},
                "Real Madrid",
                MULTI
        ));
        data.add(new QuestionModel(
                "Which Dutch player was voted 'European Player of the Century' in 1999?",
                new String[]{"Frank Rijkaard", "Johan Cruyff", "Ronald Koeman", "Clarence Seedorf"},
                "Johan Cruyff",
                MULTI
        ));
        data.add(new QuestionModel(
                "Who won the FIFA Women's World Cup in 2019?",
                new String[]{"France", "USA", "Belgium", "England"},
                "USA",
                MULTI
        ));
        data.add(new QuestionModel(
                "Which player scored the fastest hat-trick in the Premier League?",
                new String[]{"Sergio Aguero", "Sadio Mane"},
                "Sadio Mane",
                DOUBLE
        ));
        data.add(new QuestionModel(
                "When was the inaugural Premier League season?",
                new String[]{"1993-94", "1992-93"},
                "1992-93",
                DOUBLE
        ));
        data.add(new QuestionModel(
                "With 202 clean sheets, which goalkeeper has the best record in the Premier League?",
                new String[]{"Petr Cech", "Edwin Van Der Sar"},
                "Petr Cech",
                DOUBLE
        ));
        data.add(new QuestionModel(
                "Which player won the Premier League Golden Boot in 2014-15?",
                new String[]{"Sergio Aguero", "Harry Kane"},
                "Sergio Aguero",
                DOUBLE
        ));
        data.add(new QuestionModel(
                "Which player won the Premier League Player of the Season last year",
                new String[]{"Kevin De Bruyne", "Raheem Sterling"},
                "Kevin De Bruyne",
                DOUBLE
        ));
    }
}