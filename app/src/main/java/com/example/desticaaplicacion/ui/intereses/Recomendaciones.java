package com.example.desticaaplicacion.ui.intereses;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.desticaaplicacion.MainActivity;
import com.example.desticaaplicacion.R;
import com.example.desticaaplicacion.ui.destino.InfoAtractivo;

public class Recomendaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recomendaciones);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Destinos Encontrados");

        final Button btn1 = (Button) findViewById(R.id.detalle1);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarActivity();

            }

        });
        final Button btn2 = (Button) findViewById(R.id.detalle2);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarActivity();
            }
        });
    }

    private void iniciarActivity() {
        Intent intent = new Intent(this, InfoAtractivo.class);

        startActivity(intent);
    }
}
