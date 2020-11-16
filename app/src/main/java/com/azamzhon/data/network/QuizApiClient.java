package com.azamzhon.data.network;

import android.util.Log;

import com.azamzhon.data.models.CategoriesResponse;
import com.azamzhon.data.models.QuestionResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizApiClient implements IQuizApiClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);

    public void getQuestions(final QuestionsApiCallback questionsApiCallback, int amount, int category, String difficulty) {
        Call<QuestionResponse> call = apiInterface.getQuestions(amount,category,difficulty);
        call.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(@NotNull Call<QuestionResponse> call, @NotNull Response<QuestionResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    questionsApiCallback.onSuccess(response.body().getQuestions());
                    Log.d("response","api client");
                } else {
                    questionsApiCallback.onFailure(new Exception("response is failed" + response.code()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<QuestionResponse> call, @NotNull Throwable t) {
                Log.d("onFailure",t.getMessage());
            }
        });
    }

    public void getCategories(final CategoriesApiCallback categoriesApiCallback) {
        Call<CategoriesResponse> call = apiInterface.getCategories();
        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(@NotNull Call<CategoriesResponse> call, @NotNull Response<CategoriesResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    categoriesApiCallback.onSuccess(response.body().getCategories());
                }else {
                    categoriesApiCallback.onFailure(new Exception("response is failed" + response.code()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<CategoriesResponse> call, @NotNull Throwable t) {
                Log.d("onFailure",t.getMessage());
            }
        });
    }
}