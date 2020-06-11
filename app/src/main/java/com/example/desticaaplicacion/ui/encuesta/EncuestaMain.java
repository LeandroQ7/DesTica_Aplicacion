package com.example.desticaaplicacion.ui.encuesta;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.example.desticaaplicacion.*;

import com.example.desticaaplicacion.R;

import java.sql.Connection;
import java.sql.Statement;

public class EncuestaMain extends Fragment {

    private EncuestaViewModel homeViewModel;
    ConnectionClass connectionClass;

    EditText puntos;
    EditText comentario;
    String txtpuntos="";
    String txtcomentario="";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.layout_encuesta, container, false);

        final RadioGroup radioGroup = root.findViewById(R.id.radioEncuesta);
        comentario = root.findViewById(R.id.editText2);

        Button buttonencuesta = root.findViewById(R.id.buttonencuesta);
        buttonencuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonID != -1) {
                     RadioButton selectedRadioButton = root.findViewById(selectedRadioButtonID);
                     txtpuntos = selectedRadioButton.getText().toString();
                    //txtpuntos="4";
                    txtcomentario = comentario.getText().toString();
                } else {}
                connectionClass = new ConnectionClass();
                new EncuestaMain.setEncuesta().execute();
            }
        });

        return root;
    }

    public class setEncuesta extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                comentario.setText(txtcomentario);
                String query = "INSERT INTO tbcalification  (calification, opinion) VALUES ("+txtpuntos+",'" + txtcomentario + "');";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);
            } catch (Exception ex) {
                Log.e("ERRORR", ex.getMessage());
            }
            return "0";
        }
    }
}
