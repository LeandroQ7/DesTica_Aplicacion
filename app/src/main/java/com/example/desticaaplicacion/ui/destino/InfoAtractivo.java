package com.example.desticaaplicacion.ui.destino;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.desticaaplicacion.ConnectionClass;
import com.example.desticaaplicacion.R;
import com.example.desticaaplicacion.ui.login.RegistroMain;

import java.sql.Connection;
import java.sql.Statement;

public class InfoAtractivo extends AppCompatActivity {

    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info_atractivo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Actividad");



        CheckBox satView = (CheckBox)findViewById(R.id.addFavorite);
        satView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            Button btnRegister = (Button) findViewById(R.id.btnRegister);

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
    public class setFavorite extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();

                String query="INSERT INTO tbfavorite (iduser,iddestination)\n" +
                        "VALUES (11,13);";
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

                String query="DELETE FROM tbfavorite WHERE iduser=11 and iddestination=13;";
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
