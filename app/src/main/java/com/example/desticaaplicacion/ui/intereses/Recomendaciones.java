package com.example.desticaaplicacion.ui.intereses;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.desticaaplicacion.ConnectionClass;
import com.example.desticaaplicacion.MainActivity;
import com.example.desticaaplicacion.R;
import com.example.desticaaplicacion.ui.creditos.CreditosMain;
import com.example.desticaaplicacion.ui.destino.InfoAtractivo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Recomendaciones extends AppCompatActivity {

    ConnectionClass connectionClass;
    TextView txtresultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recomendaciones);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Destinos Encontrados");

        txtresultado = findViewById(R.id.txtresultado);
        connectionClass = new ConnectionClass();
        new Recomendaciones.getRecomendacion().execute();

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

    public class getRecomendacion extends AsyncTask<String,Void, ResultSet> {

        @SuppressLint("WrongThread")
        @Override
        protected ResultSet doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                Statement estado = con.createStatement();
                String peticion = "SELECT min, max, ambiente, paquete FROM dbdestica.tbsearch;";
                ResultSet result = estado.executeQuery(peticion);
                return result;
            } catch (SQLException error) {
                Log.e("ERRORR", error.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(ResultSet result) {
            String parametros;
            try {
                while (result.next()) {
                    parametros=result.getString("min")+" "+result.getString("max")+" "+
                            result.getString("ambiente")+" "+result.getString("paquete");
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
