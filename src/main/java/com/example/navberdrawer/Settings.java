package com.example.navberdrawer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Settings extends Fragment {


Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myview=inflater.inflate(R.layout.fragment_settings, container, false);
        button=myview.findViewById(R.id.signOutButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(getContext(),MainActivity.class);
                startActivity(myintent);
                Toast.makeText(getActivity(), "Successfully logout", Toast.LENGTH_SHORT).show();
            }
        });

        return myview;
    }
}