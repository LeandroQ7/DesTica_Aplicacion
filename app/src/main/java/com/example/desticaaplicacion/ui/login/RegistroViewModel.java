package com.example.desticaaplicacion.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistroViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RegistroViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Registro");
    }

    public LiveData<String> getText() {
        return mText;
    }
}