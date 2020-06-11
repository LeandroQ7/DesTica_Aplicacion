package com.example.desticaaplicacion.ui.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.desticaaplicacion.ConnectionClass;
import com.example.desticaaplicacion.MainActivity;
import com.example.desticaaplicacion.R;
import com.example.desticaaplicacion.ui.ListAdapter;
import com.example.desticaaplicacion.ui.intereses.Recomendaciones;


import java.sql.Connection;
import java.sql.Statement;

public class FavoritoMain extends  Fragment {


    ConnectionClass connectionClass;


    int[] images = {R.drawable.img1,
            R.drawable.img1};

    String[] version = {"Android Alpha", "Android Beta"};

    String[] versionNumber = {"1.0", "1.1"};

    ListView lView;

    ListAdapter lAdapter;
    Favoritos fav;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //View root = inflater.inflate(R.layout.layout_favoritos, container, false);
       // View customList = inflater.inflate(R.layout.layout_custom_listview, container, false);


        //metodo(root, customList);
        Intent mainIntent = new Intent(getActivity(), Favoritos.class);
        mainIntent.putExtra("EXTRA_SESSION_ID", "login");
        startActivity(mainIntent);

        getActivity().finish();


        return null;
    }




}

