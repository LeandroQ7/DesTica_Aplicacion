package com.example.desticaaplicacion.ui.destino;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.desticaaplicacion.ConnectionClass;
import com.example.desticaaplicacion.R;
import com.example.desticaaplicacion.ui.ListAdapter;
import com.example.desticaaplicacion.ui.login.Favoritos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DestinoMain extends Fragment {

    private DestinoViewModel homeViewModel;
    ConnectionClass connectionClass;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(DestinoViewModel.class);
        View root = inflater.inflate(R.layout.layout_destino, container, false);



        final ImageView img1 = root.findViewById(R.id.img1);
        final ImageView img2 = root.findViewById(R.id.img2);
        final ImageView img3 = root.findViewById(R.id.img3);
        final ImageView img4 = root.findViewById(R.id.img4);
        final ImageView img5 = root.findViewById(R.id.img5);
        final ImageView img6 = root.findViewById(R.id.img6);
        final ImageView img7 = root.findViewById(R.id.img7);
        final ImageView img8 = root.findViewById(R.id.img8);

        final TextView txt = root.findViewById(R.id.txt1);


        connectionClass = new ConnectionClass();
        addRows(root);

        //img1.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
        //public void onClick(View v) {
        //Intent mainIntent = new Intent(getActivity(),
        // InfoAtractivoclass);

        //startActivity(mainIntent);
        // }
        // });



        return root;

    }

    private void addRows(View root) {

        ResultSet result = null;

        try {


            Connection con = connectionClass.CONN();
            Statement estado = con.createStatement();

            String peticion ="select title, amount, image from tbdestination";
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
