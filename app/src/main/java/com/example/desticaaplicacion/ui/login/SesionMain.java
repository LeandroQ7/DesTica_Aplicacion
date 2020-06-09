package com.example.desticaaplicacion.ui.login;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.desticaaplicacion.MainActivity;
import com.example.desticaaplicacion.R;
import com.example.desticaaplicacion.ui.destino.InfoAtractivo;


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
                startActivity(mainIntent);
            }
        });
        return root;


    }


}
