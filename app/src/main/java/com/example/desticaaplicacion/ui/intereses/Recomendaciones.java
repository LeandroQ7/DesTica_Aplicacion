package com.example.desticaaplicacion.ui.intereses;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.desticaaplicacion.*;
import com.example.desticaaplicacion.R;
import com.example.desticaaplicacion.ui.destino.InfoAtractivo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Recomendaciones extends AppCompatActivity {

    ConnectionClass connectionClass;
    ArrayList<Destiny> lista = new ArrayList<Destiny>();
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

        txtresultado.setText("1");
        connectionClass = new ConnectionClass();
        new Recomendaciones.getEuclides().execute();
        txtresultado.setText("2");

        Iterator<Destiny> it = lista.iterator();
// mientras al iterador queda proximo juego
        while(it.hasNext()){
            Destiny item=it.next();
           // System.out.println(item.toString());
            //System.out.println("tipo: " + item.getImage());
            txtresultado.setText(item.toString());
        }


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
                String peticion = "SELECT iddestination, location, typetravel, title, camino, tiempo, image FROM dbdestica.tbdestination;";
                ResultSet result = estado.executeQuery(peticion);
                return result;
            } catch (SQLException error) {
                Log.e("ERRORR", error.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(ResultSet result) {
            String RSiddestino,RStitle,RSimage, RSlocation, RStypetravel, RScamino, RStiempo;
            int dblocation, dbtypetravel, dbcamino, dbtiempo;
            int iteracion=0;
            double distanciaActual=0, distanciaNueva=0, returalgoritmo=0;
            int valorambiente, valorpaquete, valorcamino, valortiempo;
            try {
                while (result.next()) {
                    RSiddestino = result.getString("iddestination");
                    RStitle = result.getString("title");
                    RSimage = result.getString("image");
                    RSlocation = result.getString("location");
                    RStypetravel = result.getString("typetravel");
                    RScamino = result.getString("camino");
                    RStiempo = result.getString("tiempo");

                    switch (RSlocation){ /*Base de datos*/
                        case "Playa":  dblocation=1; break;
                        case "Ciudad":   dblocation=2; break;
                        case "Montaña":   dblocation=3; break;
                        case "Historico":   dblocation=4; break;
                        default: dblocation=1; break;
                    }
                    switch (RStypetravel){ /*Base de datos*/
                        case "Familiar":  dbtypetravel=1; break;
                        case "Negocio":   dbtypetravel=2; break;
                        case "Deportivo": dbtypetravel=3; break;
                        case "Cultural":  dbtypetravel=4; break;
                        default: dbtypetravel=1; break;
                    }
                    switch (RScamino){ /*Base de datos*/
                        case "Asfalto":  dbcamino=1; break;
                        case "Lastre":   dbcamino=2; break;
                        case "Tierra":   dbcamino=3; break;
                        default: dbcamino=1; break;
                    }
                    switch (RStiempo){ /*Base de datos*/
                        case "Llovioso":  dbtiempo=1; break;
                        case "Caluroso":   dbtiempo=2; break;
                        case "Humedo":   dbtiempo=3; break;
                        default: dbtiempo=1; break;
                    }

                    switch (ambiente){ /*Digitado por Usuario*/
                        case "Playa":  valorambiente=1; break;
                        case "Ciudad":   valorambiente=2; break;
                        case "Montaña":   valorambiente=3; break;
                        case "Historico":   valorambiente=4; break;
                        default: valorambiente=1; break;
                    }
                    switch (paquete){ /*Base de datos*/
                        case "Familiar":  valorpaquete=1; break;
                        case "Negocio":   valorpaquete=2; break;
                        case "Deportivo": valorpaquete=3; break;
                        case "Cultural":  valorpaquete=4; break;
                        default: valorpaquete=1; break;
                    }
                    switch (camino){ /*Base de datos*/
                        case "Asfalto":  valorcamino=1; break;
                        case "Lastre":   valorcamino=2; break;
                        case "Tierra":   valorcamino=3; break;
                        default: valorcamino=1; break;
                    }
                    switch (tiempo){ /*Base de datos*/
                        case "Llovioso":  valortiempo=1; break;
                        case "Caluroso":   valortiempo=2; break;
                        case "Humedo":   valortiempo=3; break;
                        default: valortiempo=1; break;
                    }

                    if(iteracion==0){

                         distanciaActual=sqrt(
                                pow(valorambiente-dblocation,2)+
                                pow(valorpaquete-dbtypetravel,2)+
                                pow(valorcamino-dbcamino,2)+
                                pow(valortiempo-dbtiempo,2)
                        ); //aplica formula de distancia eucladiana
                        iteracion++;
                    }else{
                        distanciaNueva=sqrt(
                                pow(valorambiente-dblocation,2)+
                                        pow(valorpaquete-dbtypetravel,2)+
                                        pow(valorcamino-dbcamino,2)+
                                        pow(valortiempo-dbtiempo,2)
                        ); //aplica formula de distancia eucladiana
                        if(distanciaActual<=distanciaNueva){

                        }else{
                            distanciaActual=distanciaNueva;  //mantiene distancia actual como la menor
                            returalgoritmo=dblocation;
                        }/*else*/
                    }/*else*/

                    Destiny destino = new Destiny(Integer.parseInt(RSiddestino),distanciaActual,RStitle, RSimage);
                   /*DEBUGEO*/
                    txtresultado.setText(" ambiente: "+valorambiente+" paquete:"+
                            valorpaquete+" camino: "+valorcamino+" tiempo:"+valortiempo+
                            " distanciaActual: "+distanciaActual+"  Objeto:"+destino);
                    lista.add(destino);

                }/*while*/
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
