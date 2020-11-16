package com.azamzhon.data.network;

import com.azamzhon.data.core.IBaseCallBack;
import com.azamzhon.data.models.Category;
import com.azamzhon.data.models.QuestionModel;

import java.util.ArrayList;
import java.util.Locale;

public interface IQuizApiClient {

    void getQuestions(QuestionsApiCallback questionsApiCallback,int amount,int category,String difficulty);
    void getCategories(CategoriesApiCallback categoriesApiCallback);

    interface QuestionsApiCallback extends IBaseCallBack<ArrayList<QuestionModel>> {
        @Override
        void onSuccess(ArrayList<QuestionModel> result);

        @Override
        void onFailure(Exception e);
    }

    interface CategoriesApiCallback extends IBaseCallBack<ArrayList<Category>> {
        @Override
        void onSuccess(ArrayList<Category> result);

        @Override
        void onFailure(Exception e);
    }
}