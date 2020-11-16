package com.azamzhon.data.local;

import androidx.lifecycle.LiveData;

import com.azamzhon.data.models.QuizResult;

import java.util.ArrayList;

public interface IHistoryStorage {

    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    LiveData<ArrayList<QuizResult>> getAll();

    void delete(int id);

    void deleteAll();

}