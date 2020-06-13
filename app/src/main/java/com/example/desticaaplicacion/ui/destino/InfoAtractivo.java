package com.example.desticaaplicacion.ui.destino;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.desticaaplicacion.*;
import com.example.desticaaplicacion.R;
import com.example.desticaaplicacion.ui.login.*;
import com.google.android.material.navigation.NavigationView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InfoAtractivo extends AppCompatActivity {

    ConnectionClass connectionClass;
    String destinationId;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info_atractivo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Actividad");
        connectionClass = new ConnectionClass();

        destinationId = getIntent().getStringExtra("EXTRA_SESSION_DESTINATION");
        userId = getIntent().getStringExtra("EXTRA_SESSION_USER");

        //Establecer datos del destino
        setDestinationData(destinationId);


        CheckBox satView = (CheckBox)findViewById(R.id.addFavorite);

        if(TextUtils.isEmpty(userId)){
                satView.setVisibility(View.GONE);
        }else{
            verifyFavorite();
        }



        satView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {



            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    TextView text= (TextView) findViewById(R.id.txtDescription);
                    text.setText("Seleccionado");

                            connectionClass = new ConnectionClass();
                            new InfoAtractivo.setFavorite().execute();

                }else{
                    TextView text= (TextView) findViewById(R.id.txtDescription);
                    text.setText("Desmarcado");

                            connectionClass = new ConnectionClass();
                            new InfoAtractivo.removeFavorite().execute();
                }

            }
        }
        );
    }

    private void verifyFavorite() {
        ResultSet result = null;

        try {

            Connection con = connectionClass.CONN();
            Statement estado = con.createStatement();
            String peticion ="SELECT * from tbfavorite where iduser="+userId+" and iddestination="+destinationId+" ;";
            result = estado.executeQuery(peticion);
        } catch (SQLException e) {
        }
        try {
            if (result.next() == false) {

            }else{
                CheckBox satView = (CheckBox)findViewById(R.id.addFavorite);
                satView.setChecked(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setDestinationData(String destinationId) {
        ResultSet result = null;

        try {

            Connection con = connectionClass.CONN();
            Statement estado = con.createStatement();
            String peticion ="SELECT amount, location, description, title, image FROM dbdestica.tbdestination" +
                    " where iddestination="+destinationId+" ;";
            result = estado.executeQuery(peticion);
        } catch (SQLException e) {
        }
        try {
            while (result.next()) {
                TextView text= (TextView) findViewById(R.id.txtDescription);
                text.setText(result.getString("description"));

                ImageView img= (ImageView) findViewById(R.id.img1);
                int imageSource= getImage(result.getString("image"));
                img.setImageResource(imageSource);

                text= (TextView) findViewById(R.id.txtPrice);
                text.setText(result.getString("amount"));

                text= (TextView) findViewById(R.id.txtPlace);
                text.setText(result.getString("amount"));

                text= (TextView) findViewById(R.id.txtOptionalInfo);
                text.setText(result.getString("title"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


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

    public class setFavorite extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();

                String query="INSERT INTO tbfavorite (iduser,iddestination)\n" +
                        "VALUES ("+userId+","+destinationId+");";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);
            }
            catch (Exception ex)
            {
                Log.e("ERRORR", ex.getMessage());
            }
            return "";
        }
    }
    public class removeFavorite extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();

                String query="DELETE FROM tbfavorite WHERE iduser="+userId+" and iddestination="+destinationId+";";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);
            }
            catch (Exception ex)
            {
                Log.e("ERRORR", ex.getMessage());
            }
            return "";
        }
    }
}
