package com.example.desticaaplicacion.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.desticaaplicacion.MainActivity;

import com.example.desticaaplicacion.R;


public class SesionMain extends Fragment {

    private SesionViewModel sesionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        sesionViewModel =
                ViewModelProviders.of(this).get(SesionViewModel.class);
        View root = inflater.inflate(R.layout.layout_sesion, container, false);


        final Button btnIniciar = root.findViewById(R.id.boton_iniciar_sesion);


        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(getActivity(),
                        MainActivity.class);
                mainIntent.putExtra("EXTRA_SESSION_ID", "login");
                mainIntent.putExtra("EXTRA_SESSION_USER", "4");
                startActivity(mainIntent);

            }
        });

        return root;

    }





}
