package com.azamzhon.ui.question;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.azamzhon.App;
import com.azamzhon.data.models.QuestionModel;
import com.azamzhon.data.models.QuizResult;
import com.azamzhon.data.network.IQuizApiClient;

import java.util.ArrayList;
import java.util.Date;

public class QuestionViewModel extends ViewModel {

    public MutableLiveData<ArrayList<QuestionModel>> questionLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    public MutableLiveData<QuizResult> isLast = new MutableLiveData<>();
    public ObservableField<Boolean> isLoading = new ObservableField<>();

    public ArrayList<String> categories = new ArrayList<>();
    public ArrayList<QuestionModel> mQuestion;


    public void getQuestions(int amount,int id,String difficulty){
        isLoading.set(true);
        App.quizRepository.getQuestions(new IQuizApiClient.QuestionsApiCallback() {
            @Override
            public void onSuccess(ArrayList<QuestionModel> result) {
                for (int i = 0; i < result.size(); i++) {
                    categories.add(result.get(i).getCategory());
                }
                mQuestion = result;
                Log.d("response", mQuestion.get(0).getIncorrectAnswers().size() + "got");
                questionLiveData.setValue(mQuestion);
                isLoading.set(false);
                currentQuestionPosition.setValue(0);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("onFailure",e.getMessage());
                isLoading.set(false);
            }
        },amount,id,difficulty);
    }

    public void moveToQuestionFinish(int position,long createdAT){
        if (position == mQuestion.size() - 1){
            finish(position,createdAT);
        }else {
            currentQuestionPosition.setValue(position);
        }
    }

    void finish(int position,long createdAt){
        QuestionModel question = mQuestion.get(position);
        QuizResult quizResult = new QuizResult(question.getCategory(),
                question.getDifficulty(),
                5,
                new Date(createdAt),
                mQuestion.size());
        isLast.setValue(quizResult);
    }

    void skip(int questionPosition){
        if (questionPosition == mQuestion.size() - 1){
            return;
        }else{
            mQuestion.get(questionPosition).setClicked(true);
            questionLiveData.setValue(mQuestion);
        }
    }

    public void onAnswerClick(int questionPosition,int answerPosition){
        if (currentQuestionPosition.getValue() == null || mQuestion == null){
            return;
        }

        QuestionModel question = mQuestion.get(questionPosition);

        question.setSelectQuestionPosition(answerPosition);

        mQuestion.set(questionPosition,question);

        mQuestion.get(questionPosition).setClicked(true);

        questionLiveData.setValue(mQuestion);
    }
}