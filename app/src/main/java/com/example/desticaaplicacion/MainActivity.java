package com.example.desticaaplicacion;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    ConnectionClass connectionClass;
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        texto = (TextView)findViewById(R.id.textbd);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_intereses, R.id.nav_creditos, R.id.nav_destinos, R.id.nav_opinion,
                R.id.nav_registro, R.id.nav_sesion,R.id.nav_favorito)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        final Button btnPrueba = (Button) findViewById(R.id.btnPrueba);

        //modifica las opciones del menu, con un usuario logueado
        String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        if (TextUtils.isEmpty(sessionId)) {

        }else{
            if(TextUtils.equals(sessionId,"login")){
                Menu menuNav = navigationView.getMenu();
                MenuItem loginItems= menuNav.findItem(R.id.nav_registro);
                loginItems.setVisible(false);
                loginItems= menuNav.findItem(R.id.nav_sesion);
                loginItems.setVisible(false);

                loginItems= menuNav.findItem(R.id.nav_logout);
                loginItems.setVisible(true);
                loginItems= menuNav.findItem(R.id.nav_favorito);
                loginItems.setVisible(true);

            }

        }


        //bd action


        btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


                //MenuItem mi = navigationView.getMenu().add(Menu.NONE, Menu.NONE, Menu.NONE, "Menu Item 1");
                Menu menuNav = navigationView.getMenu();
                MenuItem logoutItem = menuNav.findItem(R.id.nav_registro);
                logoutItem.setVisible(false);
                btnPrueba.setText(logoutItem.getTitle());
                //connectionClass = new ConnectionClass();
                //new getCurso().execute();
            }
        });


    }




public class getCurso extends AsyncTask<String,Void, ResultSet> {

    @Override
    protected ResultSet doInBackground(String... strings) {

        try {

            Connection con = connectionClass.CONN();

            Statement estado = con.createStatement();

            String peticion ="SELECT * FROM dbdestica.tbuser limit 1";
            ResultSet result = estado.executeQuery(peticion);

            return result;
        } catch (SQLException e) {

        }
        return null;
    }

    @Override
    protected void onPostExecute(ResultSet result) {

        try {


            while (result.next()) {
                texto.setText(result.getString("email"));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
