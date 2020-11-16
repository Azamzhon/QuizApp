package com.azamzhon;

import android.app.Application;

import com.azamzhon.data.local.IHistoryStorage;
import com.azamzhon.data.local.QuizRepository;
import com.azamzhon.data.network.QuizApiClient;
import com.azamzhon.ui.main.history.HistoryStorage;

public class App extends Application {

    public static QuizApiClient apiClient;
    public static QuizRepository quizRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        apiClient = new QuizApiClient();
        IHistoryStorage historyStorage = new HistoryStorage();

        quizRepository = new QuizRepository(apiClient ,historyStorage);
    }
}