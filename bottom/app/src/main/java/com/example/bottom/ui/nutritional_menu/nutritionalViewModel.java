package com.example.bottom.ui.nutritional_menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class nutritionalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public nutritionalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}