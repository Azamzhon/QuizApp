package com.azamzhon.ui.question;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.azamzhon.App;
import com.azamzhon.data.models.QuestionModel;
import com.azamzhon.data.network.IQuizApiClient;

import java.util.ArrayList;

public class QuestionViewModel extends ViewModel {

    public MutableLiveData<ArrayList<QuestionModel>> questionLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> correctQuestionPosition = new MutableLiveData<>();
    public MutableLiveData<Integer> isLast = new MutableLiveData<>();
    public ObservableField<Boolean> isLoading = new ObservableField<>();

    public ArrayList<String> categories = new ArrayList<>();
    public ArrayList<QuestionModel> mQuestion;
    private int correctAnswer = 0;


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
                correctQuestionPosition.setValue(0);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("onFailure",e.getMessage());
                isLoading.set(false);
            }
        },amount,id,difficulty);
    }

    public void moveToQuestionFinish(int position, long createdAT) {
        if (position == mQuestion.size() - 1) {
            finish();
        } else {
            correctQuestionPosition.setValue(position);
        }
    }

    void finish() {
        isLast.setValue(correctAnswer);
    }

    void skip(int questionPosition) {
        if (questionPosition == mQuestion.size() - 1) {
            return;
        } else {
            mQuestion.get(questionPosition).setClicked(true);
            questionLiveData.setValue(mQuestion);
            finish();
        }
    }

    public void onAnswerClick(int questionPosition,int answerPosition){
        if (correctQuestionPosition.getValue() == null || mQuestion == null){
            return;
        }

        QuestionModel question = mQuestion.get(questionPosition);

        question.setSelectQuestionPosition(answerPosition);

        mQuestion.set(questionPosition, question);

        mQuestion.get(questionPosition).setClicked(true);

        questionLiveData.setValue(mQuestion);

        if (question.getIncorrectAnswers().get(answerPosition).equals(question.getCorrectAnswer())) {
            correctAnswer++;
        }
    }
}