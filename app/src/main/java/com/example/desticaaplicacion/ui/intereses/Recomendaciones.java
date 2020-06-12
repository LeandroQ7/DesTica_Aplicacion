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
    String ambiente;
    String paquete;
    String camino;
    String tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recomendaciones);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Destinos Encontrados");

        txtresultado = findViewById(R.id.txtresultado);

        //valores que ingreso el usuario
        ambiente = getIntent().getStringExtra("EXTRA_SESSION_AMBIENTE");
        paquete = getIntent().getStringExtra("EXTRA_SESSION_PAQUETE");
        camino = getIntent().getStringExtra("EXTRA_SESSION_CAMINO");
        tiempo = getIntent().getStringExtra("EXTRA_SESSION_TIEMPO");


        connectionClass = new ConnectionClass();
        new Recomendaciones.getEuclides().execute();

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







    public class getEuclides extends AsyncTask<String,Void, ResultSet> {

        @SuppressLint("WrongThread")
        @Override
        protected ResultSet doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                Statement estado = con.createStatement();
                String peticion = "SELECT iddestination, amount, location, typetravel, description, title, camino, tiempo, image, video FROM dbdestica.tbdestination;";
                ResultSet result = estado.executeQuery(peticion);
                return result;
            } catch (SQLException error) {
                Log.e("ERRORR", error.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(ResultSet result) {
            String txtiddestination, txtamount, dblocation, txttypetravel, txtdescription, txttitle, dbcamino, dbtiempo, txtimage, txtvideo;
            int iddestination, amount, location, typetravel, description, title, camino, tiempo, image, video;
            int sumaplaya=0, sumaciudad=0, sumamontaña=0, sumahistorico=0;
            try {
                while (result.next()) {
                    txtiddestination = result.getString("iddestination");
                    dblocation = result.getString("location");
                    txttypetravel = result.getString("typetravel");
                    txttitle = result.getString("title");
                    dbcamino = result.getString("camino");
                    dbtiempo = result.getString("tiempo");
                    txtimage = result.getString("image");

                    switch (dblocation){
                        case "Familiar":  location=1; break;
                        case "Negocio":   location=2; break;
                        case "Deportivo": location=3; break;
                        case "Cultural":  location=4; break;
                        default: location=1; break;
                    }
                    switch (dbcamino){
                        case "Asfalto":  camino=1; break;
                        case "Lastre":   camino=2; break;
                        case "Tierra":   camino=3; break;
                        default: camino=1; break;
                    }
                    switch (dbtiempo){
                        case "Llovioso":  tiempo=1; break;
                        case "Caluroso":   tiempo=2; break;
                        case "Humedo":   tiempo=3; break;
                        default: tiempo=1; break;
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void calcular(){

            /*
            ec = parseInt(document.estilo.c5.value)+parseInt(document.estilo.c9.value)+parseInt(document.estilo.c13.value)+parseInt(document.estilo.c17.value)+parseInt(document.estilo.c25.value)+parseInt(document.estilo.c29.value);
            or = parseInt(document.estilo.c2.value)+parseInt(document.estilo.c10.value)+parseInt(document.estilo.c22.value)+parseInt(document.estilo.c26.value)+parseInt(document.estilo.c30.value)+parseInt(document.estilo.c34.value);
            ca = parseInt(document.estilo.c7.value)+parseInt(document.estilo.c11.value)+parseInt(document.estilo.c15.value)+parseInt(document.estilo.c19.value)+parseInt(document.estilo.c31.value)+parseInt(document.estilo.c35.value);
            ea = parseInt(document.estilo.c4.value)+parseInt(document.estilo.c12.value)+parseInt(document.estilo.c24.value)+parseInt(document.estilo.c28.value)+parseInt(document.estilo.c32.value)+parseInt(document.estilo.c36.value);



            document.estilo.EC.value = ec;
            document.estilo.RO.value = or;
            document.estilo.CA.value = ca;
            document.estilo.EA.value = ea;

            */
        }
    }
}
