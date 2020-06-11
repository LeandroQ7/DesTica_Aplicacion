package com.example.desticaaplicacion.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavoritoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavoritoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Registro");
    }

    public LiveData<String> getText() {
        return mText;
    }
}