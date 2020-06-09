package com.example.desticaaplicacion.ui.destino;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DestinoViewModel extends ViewModel {





    private MutableLiveData<String> mText;

    public DestinoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Lista de destinos");
    }

    public LiveData<String> getText() {
        return mText;
    }
}