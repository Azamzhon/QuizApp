package com.azamzhon.ui.main.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.azamzhon.App;
import com.azamzhon.data.models.Category;
import com.azamzhon.data.network.IQuizApiClient;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    public  MutableLiveData<ArrayList<Category>> data = new MutableLiveData<>();
    public ObservableField<Boolean> isLoading = new ObservableField<>();
    public MutableLiveData<Integer> progressBarSuccess = new MutableLiveData<>();
    public Integer num = 10;
    public String[] names;

    public void onClick(View view, int amount, int id, String difficulty) {

    }

    public void getCategories(){
        isLoading.set(true);
        App.quizRepository.getCategories(new IQuizApiClient.CategoriesApiCallback() {
            @Override
            public void onSuccess(ArrayList<Category> result) {
                names = new String[result.size()];
                for (int i = 0; i < result.size(); i++) {
                    names[i] = result.get(i).getName();
                }
                data.setValue(result);
                isLoading.set(false);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("quizF",e.getMessage());
                isLoading.set(false);
            }
        });
    }

    public void increase() {
        if (num < 20) {
            num++;
        }
        progressBarSuccess.setValue(num);
    }

    public void decrease() {
        if (num > 0) {
            num--;
        }
        progressBarSuccess.setValue(num);
    }
}