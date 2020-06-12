package com.example.desticaaplicacion.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.desticaaplicacion.ConnectionClass;
import com.example.desticaaplicacion.R;
import com.example.desticaaplicacion.ui.ListAdapter;

public class CerrarSesion extends  Fragment {




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        getActivity().finish();


        return null;
    }




}

