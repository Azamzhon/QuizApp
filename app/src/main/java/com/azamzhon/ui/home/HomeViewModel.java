package com.azamzhon.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    MutableLiveData<Integer> progressBarSuccess = new MutableLiveData<>();
    Integer num = 10;

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