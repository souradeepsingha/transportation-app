package com.example.navberdrawer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class TEST extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private CheckBox registerCheckBox;
    private TextView navHeaderText;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the user is already logged in
        if (isLoggedIn()) {
            // User is already logged in, redirect to MainActivity
            startActivity(new Intent(TEST.this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_loginpage);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        registerCheckBox = findViewById(R.id.checkBoxRegister);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        RequestQueue requestQueue= Volley.newRequestQueue(TEST.this);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                SharedPreferences sharedPref = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString("name", username);
                editor.apply();
                progressBar.setVisibility(View.VISIBLE);

                String NAME=usernameEditText.getText().toString();

                String MOBILE=passwordEditText.getText().toString();
                String url="https://brick-red-whistle.000webhostapp.com/app/userregister.php?NAME="+NAME+"&MOBILE="+MOBILE
                       ;
                StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        login();
                        new AlertDialog.Builder(TEST.this)
                                .setTitle("server response")
                                .setMessage(response)
                                .show();
                        progressBar.setVisibility(View.GONE);
                        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
                        String savedData = sharedPreferences.getString("name", "");
                        Toast.makeText(getApplicationContext(), "welcome "+savedData, Toast.LENGTH_SHORT).show();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                });

                requestQueue.add(request);






            }
        });

        boolean isRegisterShown = getRegisterOption();
        if (!isRegisterShown) {
            registerCheckBox.setVisibility(View.GONE);
        }
    }

    private void login() {
        // Check username and password here
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Example login logic (replace with your own)
        if (!username.isEmpty() && !password.isEmpty()) {
            saveRegisterOption();
            // Mark user as logged in
            setLoggedIn(true);

            startActivity(new Intent(TEST.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean getRegisterOption() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        return sharedPreferences.getBoolean("registerShown", true);
    }

    private void saveRegisterOption() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("registerShown", !registerCheckBox.isChecked());
        editor.apply();
    }

    private boolean isLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        return sharedPreferences.getBoolean("loggedIn", false);
    }

    private void setLoggedIn(boolean loggedIn) {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("loggedIn", loggedIn);
        editor.apply();
    }
}
