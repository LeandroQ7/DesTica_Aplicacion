package com.example.desticaaplicacion.ui.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.desticaaplicacion.ConnectionClass;
import com.example.desticaaplicacion.MainActivity;

import com.example.desticaaplicacion.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SesionMain extends Fragment {

    private SesionViewModel sesionViewModel;
    ConnectionClass connectionClass;
    String userName;
    String userID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        sesionViewModel =
                ViewModelProviders.of(this).get(SesionViewModel.class);
        View root = inflater.inflate(R.layout.layout_sesion, container, false);
        connectionClass = new ConnectionClass();


        final Button btnIniciar = root.findViewById(R.id.boton_iniciar_sesion);
        final TextView user= root.findViewById(R.id.input_user);
        final TextView password= root.findViewById(R.id.input_password);
        final TextView imprimir= root.findViewById(R.id.texto_conectar);



        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  Boolean searchUser=verifyUser(user.getText()+"",password.getText()+"");

                  if(searchUser){
                      Intent mainIntent = new Intent(getActivity(),
                              MainActivity.class);
                      mainIntent.putExtra("EXTRA_SESSION_ID", "login");
                      mainIntent.putExtra("EXTRA_SESSION_USER", userID);
                      mainIntent.putExtra("EXTRA_SESSION_USERNAME", userName);
                      startActivity(mainIntent);
                  }else{
                      AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                      alertDialog.setMessage("Credenciales incorrectas, o usuario no esta registrado");
                      alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                              new DialogInterface.OnClickListener() {
                                  public void onClick(DialogInterface dialog, int which) {
                                      dialog.dismiss();
                                  }
                              });
                      alertDialog.show();

                  }


            }
        });

        return root;

    }

    private Boolean verifyUser(String user, String password) {
        ResultSet result = null;
        Boolean userExists= true;

        try {

            Connection con = connectionClass.CONN();
            Statement estado = con.createStatement();
            String peticion ="SELECT nameUser,passwordUser,iduser from tbuser where nameUser='"+user+"' and passwordUser='"+password+"' ;";
            result = estado.executeQuery(peticion);
        } catch (SQLException e) {
        }
        try {
            if (result.next()) {
                userName=result.getString("nameUser");
                userID=result.getString("iduser");
                userExists=true;
            }else{
                userExists=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExists;
    }





}
