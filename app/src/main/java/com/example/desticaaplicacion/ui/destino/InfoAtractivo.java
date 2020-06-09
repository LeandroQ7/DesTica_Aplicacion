package com.example.desticaaplicacion.ui.destino;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.desticaaplicacion.R;

public class InfoAtractivo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info_atractivo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Actividad");
    }
}
