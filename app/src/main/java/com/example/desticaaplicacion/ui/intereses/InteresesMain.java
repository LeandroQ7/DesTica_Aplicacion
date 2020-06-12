package com.example.desticaaplicacion.ui.intereses;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.desticaaplicacion.ConnectionClass;
import com.example.desticaaplicacion.MainActivity;
import com.example.desticaaplicacion.R;
import com.example.desticaaplicacion.ui.encuesta.EncuestaMain;

import java.sql.Connection;
import java.sql.Statement;

public class InteresesMain extends Fragment {

    private InteresesViewModel interesesViewModel;
    ConnectionClass connectionClass;
    EditText min, max;
    String txtmin, txtmax, txtambiente, txtpaquete, txtcamino, txttiempo;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        interesesViewModel =
                ViewModelProviders.of(this).get(InteresesViewModel.class);
        final View root = inflater.inflate(R.layout.layout_intereses, container, false);

        min = root.findViewById(R.id.txtmin);
        max = root.findViewById(R.id.txtmax);

        final RadioGroup radioGroup1 = root.findViewById(R.id.radioAmbiente);
        final RadioGroup radioGroup2 = root.findViewById(R.id.radioPaquete);
        final RadioGroup radioGroup3 = root.findViewById(R.id.radioCamino);
        final RadioGroup radioGroup4 = root.findViewById(R.id.radioTiempo);

        /*redireccionar a siguiente vista*/
        final Button btnIniciar = root.findViewById(R.id.buscar_destinos);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonID1 = radioGroup1.getCheckedRadioButtonId();
                int selectedRadioButtonID2 = radioGroup2.getCheckedRadioButtonId();
                int selectedRadioButtonID3 = radioGroup3.getCheckedRadioButtonId();
                int selectedRadioButtonID4 = radioGroup4.getCheckedRadioButtonId();
                if (selectedRadioButtonID1 != -1) {
                    RadioButton selectedRadioButton1 = root.findViewById(selectedRadioButtonID1);
                    txtambiente = selectedRadioButton1.getText().toString();
                } else {txtambiente="";}
                if (selectedRadioButtonID2 != -1) {
                    RadioButton selectedRadioButton2 = root.findViewById(selectedRadioButtonID2);
                    txtpaquete = selectedRadioButton2.getText().toString();
                } else {txtpaquete="";}
                if (selectedRadioButtonID3 != -1) {
                    RadioButton selectedRadioButton3 = root.findViewById(selectedRadioButtonID3);
                    txtcamino = selectedRadioButton3.getText().toString();
                } else {txtcamino="";}
                if (selectedRadioButtonID4 != -1) {
                    RadioButton selectedRadioButton4 = root.findViewById(selectedRadioButtonID4);
                    txttiempo = selectedRadioButton4.getText().toString();
                } else {txttiempo="";}

                txtmin = min.getText().toString();
                txtmax = max.getText().toString();
                connectionClass = new ConnectionClass();
                new InteresesMain.setSearch().execute();
                min.setText("");/*Borra texto del min*/
                max.setText("");/*Borra texto del max*/

                Intent mainIntent = new Intent(getActivity(),
                        Recomendaciones.class);
                startActivity(mainIntent);
                mensaje();
            }

            public void mensaje(){
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Búsqueda de Destinos Turísticos");
                alertDialog.setMessage("Busqueda completada.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

        return root;
    }

    public class setSearch extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                String query2 = "TRUNCATE TABLE tbsearch;";
                Statement stmt2 = con.createStatement();
                stmt2.executeUpdate(query2);

                String query = "INSERT INTO tbsearch (min, max, ambiente, paquete, camino, tiempo) "+
                "VALUES ('"+txtmin+"','" + txtmax + "','" + txtambiente + "','" + txtpaquete + "','" + txtcamino + "','" + txttiempo + "');";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);
            } catch (Exception ex) {
                Log.e("ERRORR", ex.getMessage());
            }
            return "0";
        }
    }
}
