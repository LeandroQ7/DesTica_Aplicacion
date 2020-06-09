package com.example.desticaaplicacion.ui.creditos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.desticaaplicacion.R;

public class CreditosMain extends Fragment {

    private CreditosViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(CreditosViewModel.class);
        View root = inflater.inflate(R.layout.layout_creditos, container, false);

        return root;
    }
}
