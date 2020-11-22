package com.azamzhon.ui.result;

import android.content.Intent;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.azamzhon.data.models.QuestionModel;
import com.azamzhon.ui.question.QuestionActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ResultViewModel extends ViewModel {

    public MutableLiveData<ArrayList<QuestionModel>> questionsData = new MutableLiveData<>();
    public ObservableField<String> percentF = new ObservableField<>();

    private ArrayList<QuestionModel> questions = new ArrayList<>();
    private int percent = 0;

    void getData(Intent intent){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<QuestionModel>>() {}.getType();
        questions = gson.fromJson(intent.getStringExtra(QuestionActivity.QUESTIONS), type);
        questionsData.setValue(questions);
    }

    public void getPercent(int questionsAmount, int correctAnswersAmount){
        percent = correctAnswersAmount * 100 / questionsAmount;
        percentF.set(percent + " " + "%");
    }
}