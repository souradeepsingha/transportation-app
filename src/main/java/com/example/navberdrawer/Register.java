package com.example.navberdrawer;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Register extends Fragment {
//TextView textView;
//Button button;
Button buttonu,buttond;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview=inflater.inflate(R.layout.activity_register,container,false);

//        textView = myview.findViewById(R.id.text1);
        buttonu = myview.findViewById(R.id.buttonu);
//        buttond=myview.findViewById(R.id.buttond);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText("Hello");
//            }
//        });
        buttonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(getContext(),DriverRegister.class);
                startActivity(myintent);
            }
        });



        return myview;
    }
}