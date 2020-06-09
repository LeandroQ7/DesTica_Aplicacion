package com.example.desticaaplicacion.ui.encuesta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EncuestaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EncuestaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Danos tu opinion");
    }

    public LiveData<String> getText() {
        return mText;
    }
}