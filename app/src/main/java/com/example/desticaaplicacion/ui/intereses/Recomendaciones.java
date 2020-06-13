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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.desticaaplicacion.*;
import com.example.desticaaplicacion.R;
import com.example.desticaaplicacion.ui.destino.InfoAtractivo;
import com.google.android.material.navigation.NavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Recomendaciones extends AppCompatActivity implements View.OnClickListener {

    ConnectionClass connectionClass;
    ArrayList<Destiny> lista = new ArrayList<Destiny>();
    TextView txtresultado;
    String userID;

    TextView Prueba;
    String ambiente;
    String paquete;
    String camino;
    String tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recomendaciones);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Destinos Encontrados");


        Prueba = findViewById(R.id.txtPrueba);


        //valores que ingreso el usuario
        ambiente = getIntent().getStringExtra("EXTRA_SESSION_AMBIENTE");
        paquete = getIntent().getStringExtra("EXTRA_SESSION_PAQUETE");
        camino = getIntent().getStringExtra("EXTRA_SESSION_CAMINO");
        tiempo = getIntent().getStringExtra("EXTRA_SESSION_TIEMPO");
        userID = getIntent().getStringExtra("EXTRA_SESSION_IDUSER");


        connectionClass = new ConnectionClass();
        new Recomendaciones.getEuclides().execute();






    }

    private void iniciarActivity() {
        Intent intent = new Intent(this, InfoAtractivo.class);

        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        String id= v.getId()+"";
        //t.setText(id);
        Intent mainIntent = new Intent(this,
                InfoAtractivo.class);
        mainIntent.putExtra("EXTRA_SESSION_DESTINATION", id);
        mainIntent.putExtra("EXTRA_SESSION_USER", userID);

        startActivity(mainIntent);
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

            switch (ambiente){ /*Digitado por Usuario*/
                case "Playa":  valorambiente=1; break;
                case "Ciudad":   valorambiente=2; break;
                case "Montaña":   valorambiente=3; break;
                case "Historico":   valorambiente=4; break;
                default: valorambiente=1; break;
            }
            switch (paquete){ /*Digitado por Usuario*/
                case "Familiar":  valorpaquete=1; break;
                case "Negocio":   valorpaquete=2; break;
                case "Deportivo": valorpaquete=3; break;
                case "Cultural":  valorpaquete=4; break;
                default: valorpaquete=1; break;
            }
            switch (camino){ /*Digitado por Usuario*/
                case "Asfalto":  valorcamino=1; break;
                case "Lastre":   valorcamino=2; break;
                case "Tierra":   valorcamino=3; break;
                default: valorcamino=1; break;
            }
            switch (tiempo){ /*Digitado por Usuario*/
                case "Llovioso":  valortiempo=1; break;
                case "Caluroso":   valortiempo=2; break;
                case "Humedo":   valortiempo=3; break;
                default: valortiempo=1; break;
            }
        

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


                         distanciaActual=sqrt(
                                pow(valorambiente-dblocation,2)+
                                pow(valorpaquete-dbtypetravel,2)+
                                pow(valorcamino-dbcamino,2)+
                                pow(valortiempo-dbtiempo,2)
                        ); //aplica formula de distancia eucladiana



                    Destiny destino = new Destiny(Integer.parseInt(RSiddestino),distanciaActual,RStitle, RSimage);
                   /*DEBUGEO
                    txtresultado.setText(" ambiente: "+valorambiente+" paquete:"+
                            valorpaquete+" camino: "+valorcamino+" tiempo:"+valortiempo+
                            " distanciaActual: "+distanciaActual+"  Objeto:"+destino);*/
                    lista.add(destino);


                }/*while*/
                
                setListOfDestiny();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }



    private void setListOfDestiny() {

        LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayout1);

        Collections.sort(lista, new Comparator<Destiny>() {

            @Override
            public int compare(Destiny p1, Destiny p2) {
                return Double.compare(p1.getDistanciacalculada(), p2.getDistanciacalculada());

            }

        });


        for (Destiny destiny: lista) {

            ImageView ii= new ImageView(this);

            int imageSource= getImage(destiny.getImage());

            ii.setImageResource(imageSource);
            RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(1000,500);
            ii.setLayoutParams(params);

            RelativeLayout.LayoutParams paramsBtn=new RelativeLayout.LayoutParams(400,100);
            TextView title = new TextView(this);
            title.setText(destiny.getTitle());
            title.setGravity(1);


            Button btnDestiny = new Button(this);
            btnDestiny.setText("Mas Detalles");
            int id=destiny.getIddestino();
            btnDestiny.setId(id);
            btnDestiny.setOnClickListener(this);


            btnDestiny.setLayoutParams(paramsBtn);

            ll.addView(ii);
            ll.addView(title);
            ll.addView(btnDestiny);


        }


            //texto.setText(result.getString("email"));

    }

    private int getImage(String image) {
        int codImage=0;
        switch (image){
            case "img1":
                codImage=R.drawable.img1;
                break;
            case "img2":
                codImage=R.drawable.img2;
                break;
            case "img3":
                codImage=R.drawable.img3;
                break;
            case "img4":
                codImage=R.drawable.img4;
                break;
            case "img5":
                codImage=R.drawable.img5;
                break;
            case "img6":
                codImage=R.drawable.img6;
                break;
            case "img7":
                codImage=R.drawable.img7;
                break;
            case "img8":
                codImage=R.drawable.img8;
                break;
            case "img9":
                codImage=R.drawable.img9;
                break;
            case "img10":
                codImage=R.drawable.img10;
                break;
            case "bosque":
                codImage=R.drawable.bosque;
                break;
            case "bosque2":
                codImage=R.drawable.bosque2;
                break;
            case "bosque3":
                codImage=R.drawable.bosque3;
                break;
            case "cascada":
                codImage=R.drawable.cascada;
                break;
            case "cascada2":
                codImage=R.drawable.cascada2;
                break;
            case "cascada3":
                codImage=R.drawable.cascada3;
                break;
            case "museo":
                codImage=R.drawable.museo;
                break;
            case "museo2":
                codImage=R.drawable.museo2;
                break;
            case "museo3":
                codImage=R.drawable.museo3;
                break;
            case "playa":
                codImage=R.drawable.playa;
                break;
            case "playa2":
                codImage=R.drawable.playa2;
                break;
            case "playa3":
                codImage=R.drawable.playa3;
                break;
            case "resort":
                codImage=R.drawable.resort;
                break;
            case "resort2":
                codImage=R.drawable.resort2;
                break;
            case "resort3":
                codImage=R.drawable.resort3;
                break;
            case "restaurante":
                codImage=R.drawable.restaurante;
                break;
            case "restaurante2":
                codImage=R.drawable.restaurante2;
                break;
            case "restaurante3":
                codImage=R.drawable.restaurante3;
                break;
            default:
                codImage=R.drawable.img1;
                break;

        }
        return codImage;
    }
}


