package com.example.desticaaplicacion.ui.destino;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.desticaaplicacion.R;



public class DestinoMain extends Fragment {

    private DestinoViewModel homeViewModel;


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

        img1.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        InfoAtractivo.class);
                startActivity(mainIntent);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        InfoAtractivo.class);
                startActivity(mainIntent);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        InfoAtractivo.class);
                startActivity(mainIntent);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        InfoAtractivo.class);
                startActivity(mainIntent);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        InfoAtractivo.class);
                startActivity(mainIntent);
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        InfoAtractivo.class);
                startActivity(mainIntent);
            }
        });img7.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        InfoAtractivo.class);
                startActivity(mainIntent);
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(),
                        InfoAtractivo.class);
                startActivity(mainIntent);
            }
        });


        return root;

    }



}
