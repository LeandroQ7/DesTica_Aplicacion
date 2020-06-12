package com.example.desticaaplicacion.ui.creditos;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.desticaaplicacion.R;
import com.example.desticaaplicacion.*;
import com.example.desticaaplicacion.ui.encuesta.EncuestaMain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class CreditosMain extends Fragment {
    ConnectionClass connectionClass;
    TextView puntaje;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.layout_creditos, container, false);
        puntaje = root.findViewById(R.id.txtpuntaje);
        connectionClass = new ConnectionClass();
        new CreditosMain.getPromedio().execute();
        return root;
    }

    public class getPromedio extends AsyncTask<String,Void, ResultSet> {

        @SuppressLint("WrongThread")
        @Override
        protected ResultSet doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                Statement estado = con.createStatement();
                String peticion = "SELECT AVG(calification) as promedio FROM tbcalification";
                ResultSet result = estado.executeQuery(peticion);
                return result;
            } catch (SQLException error) {
                Log.e("ERRORR", Objects.requireNonNull(error.getMessage()));
            }
            return null;
        }

        @Override
        protected void onPostExecute(ResultSet result) {
            try {
                while (result.next()) {
                    puntaje.setText(result.getString("promedio")); /*CON ESTO SE CAE*/
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
