package com.example.desticaaplicacion.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SesionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SesionViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is Interes");
    }

    public LiveData<String> getText() {
        return mText;
    }
}