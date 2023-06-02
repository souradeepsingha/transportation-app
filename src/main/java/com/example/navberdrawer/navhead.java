package com.example.navberdrawer;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class navhead extends AppCompatActivity {

    TextView textview;
    Button button;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_head);



//        textview = findViewById(R.id.textname);
////        button=findViewById(R.id.button3);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                textview.setText("hello");
//            }
//        });

        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String savedData = sharedPreferences.getString("name", "");
        textview.setText(savedData);

    }

}
