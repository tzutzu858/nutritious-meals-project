package com.example.bottom.ui.health;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class healthViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public healthViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("  ");
    }

    public LiveData<String> getText() {
        return mText;
    }
}