package com.example.desticaaplicacion.ui.intereses;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InteresesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public InteresesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Recomendaciones turisticas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}