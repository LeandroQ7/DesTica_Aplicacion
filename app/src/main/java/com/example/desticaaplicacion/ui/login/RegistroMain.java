package com.example.desticaaplicacion.ui.login;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.desticaaplicacion.ConnectionClass;
import com.example.desticaaplicacion.MainActivity;
import com.example.desticaaplicacion.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistroMain extends Fragment {

    private RegistroViewModel registroViewModel;
    ConnectionClass connectionClass;
    EditText user;
    EditText password1;
    EditText password2;
    String txtUser;
    String txtPass1;
    String txtPass2;
    TextView edit2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registroViewModel =
                ViewModelProviders.of(this).get(RegistroViewModel.class);
        View root = inflater.inflate(R.layout.layout_registro, container, false);

        user = root.findViewById(R.id.etNombre);
        password1 = root.findViewById(R.id.etpassword);
        password2 = root.findViewById(R.id.etpassword2);

        Button btnRegister = root.findViewById(R.id.btnRegister);
       edit2= root.findViewById(R.id.edit);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtUser= user.getText().toString();
                txtPass1= password1.getText().toString();
                txtPass2= password2.getText().toString();


                connectionClass = new ConnectionClass();
                new RegistroMain.setUser().execute();
            }
        });
        return root;
    }
    public class setUser extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                edit2.setText(txtUser);
                String query="INSERT INTO tbuser \n" +
                        "(nameUser,\n" +
                        "passwordUser)\n" +
                        "VALUES\n" +
                        "('"+txtUser+"','"+txtPass1+"');";

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
