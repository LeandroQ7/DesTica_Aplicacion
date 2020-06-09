package com.example.desticaaplicacion.ui.intereses;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.desticaaplicacion.MainActivity;
import com.example.desticaaplicacion.R;

public class InteresesMain extends Fragment {

    private InteresesViewModel interesesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        interesesViewModel =
                ViewModelProviders.of(this).get(InteresesViewModel.class);
        View root = inflater.inflate(R.layout.layout_intereses, container, false);

        final Button btnIniciar = root.findViewById(R.id.buscar_destinos);


        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        Recomendaciones.class);
                startActivity(mainIntent);
            }
        });

        return root;
    }
}
