package com.example.desticaaplicacion.ui.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.desticaaplicacion.ConnectionClass;
import com.example.desticaaplicacion.MainActivity;
import com.example.desticaaplicacion.R;
import com.example.desticaaplicacion.ui.ListAdapter;
import com.example.desticaaplicacion.ui.destino.DestinoViewModel;
import com.example.desticaaplicacion.ui.destino.InfoAtractivo;
import com.example.desticaaplicacion.ui.intereses.Recomendaciones;
import com.google.android.material.navigation.NavigationView;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FavoritoMain extends Fragment implements View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    private DestinoViewModel homeViewModel;
    ConnectionClass connectionClass;
    String userID;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(DestinoViewModel.class);
        View root = inflater.inflate(R.layout.layout_favoritos, container, false);

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);

        Menu menuNav = navigationView.getMenu();
        MenuItem item= menuNav.findItem(R.id.nav_user);
        userID=item.getTitle()+"";


        connectionClass = new ConnectionClass();
        addRows(root);

        return root;

    }

    @Override
    public void onClick(View v) {
        String id= v.getId()+"";
        //t.setText(id);
        Intent mainIntent = new Intent(getActivity(),
                InfoAtractivo.class);
        mainIntent.putExtra("EXTRA_SESSION_DESTINATION", id);
        mainIntent.putExtra("EXTRA_SESSION_USER", userID);

        startActivity(mainIntent);
    }

    private void addRows(View root) {

        ResultSet result = null;

        try {


            Connection con = connectionClass.CONN();
            Statement estado = con.createStatement();

            String idUser="10";
            String peticion ="select d.title, f.iduser, f.iddestination, d.image from tbdestination d, tbfavorite f where" +
                    " iduser="+userID+" and \n" +
                    "d.iddestination = f.iddestination;";
            result = estado.executeQuery(peticion);


        } catch (SQLException e) {

        }

        try {
            LinearLayout ll = (LinearLayout) root.findViewById(R.id.LinearLayout1);
            while (result.next()) {


                ImageView ii= new ImageView(getActivity());

                int imageSource= getImage(result.getString("image"));

                ii.setImageResource(imageSource);
                RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(1000,500);
                ii.setLayoutParams(params);

                RelativeLayout.LayoutParams paramsBtn=new RelativeLayout.LayoutParams(400,100);
                TextView title = new TextView(getActivity());
                title.setText(result.getString("title"));
                title.setGravity(1);


                Button btnDestiny = new Button(getActivity());
                btnDestiny.setText("Mas Detalles");
                int id=result.getInt("iddestination");
                btnDestiny.setId(id);
                btnDestiny.setOnClickListener(this);


                btnDestiny.setLayoutParams(paramsBtn);

                ll.addView(ii);
                ll.addView(title);
                ll.addView(btnDestiny);

                //texto.setText(result.getString("email"));

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

        }
        return codImage;
    }



}
