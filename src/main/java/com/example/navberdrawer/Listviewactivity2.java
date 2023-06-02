package com.example.navberdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;

public class Listviewactivity2 extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    private EditText phoneNumberEditText, messageEditText;
    private Button sendButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ListView listView;

    Button searchButton;
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    int i=0;

    public String cl,des="";
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewactivity2);


progressBar=findViewById(R.id.prog1);


        listView = findViewById(R.id.listview3);

        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        editor = sharedPreferences.edit();


        Intent intent = getIntent();

       String currentLoc = intent.getStringExtra("keycl");
       String dest = intent.getStringExtra("keydn");
        fetchData(currentLoc, dest);
 cl=currentLoc;
 des=dest;

progressBar.setVisibility(View.VISIBLE);










//
///////////


//        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber);
//        messageEditText = findViewById(R.id.editTextMessage);
//        sendButton = findViewById(R.id.buttonSend);

//        sendButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String phoneNumber = "8927645830";
//                String message = "this message from user";
//
//                if (ContextCompat.checkSelfPermission(Listviewactivity2.this,
//                        android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(Listviewactivity2.this,
//                            new String[]{Manifest.permission.SEND_SMS},
//                            MY_PERMISSIONS_REQUEST_SEND_SMS);
//                } else {
//                    sendSMS(phoneNumber, message);
//                }
//            }
//        });




























// ///////////
    }

    private void fetchData(String currentLoc, String dest) {
        String url = "https://brick-red-whistle.000webhostapp.com/app/driverdata.php?stringroute=" + currentLoc + "&finishroute=" + dest;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrayList.clear();
                        progressBar.setVisibility(View.GONE);
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject jsonObject = response.getJSONObject(i);
                                String name = jsonObject.getString("NAME");
                                String phone= jsonObject.getString("PHONE_NUMBER");

                                hashMap = new HashMap<>();
                                hashMap.put("NAME", name);
                                hashMap.put("PHONE_NUMBER", phone);
                                arrayList.add(hashMap);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (arrayList.size() > 0) {
                            MyAdapter myAdapter = new MyAdapter();
                            listView.setAdapter(myAdapter);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(Listviewactivity2.this);
            View myview = layoutInflater.inflate(R.layout.layoutfile, parent, false);

            TextView myname = myview.findViewById(R.id.myname);
            hashMap = arrayList.get(position);
            String name = hashMap.get("NAME");
            String phone = hashMap.get("PHONE_NUMBER");
            myname.setText(name);
//            myname.setText(phone);
            Button button=myview.findViewById(R.id.booknow);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phone1=phone;

                    // Retrieve the existing data from SharedPreferences
                    String existingData = sharedPreferences.getString("data", "");




                    String newData = existingData + "\t\n From " + cl + " TO " + des+"\n Driver "+name+"\n Phone number "+phone;

                    editor.putString("data", newData);
                    editor.apply();

                    if (ContextCompat.checkSelfPermission(Listviewactivity2.this,
                            Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Listviewactivity2.this,
                                new String[]{Manifest.permission.SEND_SMS},
                                MY_PERMISSIONS_REQUEST_SEND_SMS);
                    } else {
                        String phoneNumber =phone1;
                        String message = "your got a customer mr "+name+" Do you want to pick up him?";
                        sendSMS(phoneNumber, message);

                        Intent myintent=new Intent(Listviewactivity2.this,Bokingpage.class);
                        startActivity(myintent);

                    }



//
//                    String phoneNumber =phone1;
//                    String message ="hello";
//
//
//                        sendSMS(phoneNumber, message);







                }
            });



            return myview;
        }
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    String phoneNumber = phoneNumberEditText.getText().toString();
                    String message = messageEditText.getText().toString();
                    sendSMS(phoneNumber, message);
                } else {
                    Toast.makeText(Listviewactivity2.this, "SMS permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(Listviewactivity2.this, "SMS sent", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(Listviewactivity2.this, "SMS sending failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
