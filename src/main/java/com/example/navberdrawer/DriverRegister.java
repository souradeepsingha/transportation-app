package com.example.navberdrawer;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.navberdrawer.adapter.PlaceAutoSuggestAdapter;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class DriverRegister extends AppCompatActivity {


    private EditText nameEditText;
    private EditText phoneEditText;
    private Spinner amPmSpinner1;
    private Spinner amPmSpinner2;
    private EditText startRouteEditText;
    private EditText finishRouteEditText;
    private RadioGroup amPmRadioGroup1;
    private RadioGroup amPmRadioGroup2;
    private Button registerButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_register);

        nameEditText = findViewById(R.id.name);
        phoneEditText = findViewById(R.id.phonnumber);
        amPmSpinner1 = findViewById(R.id.am_pm_spinner);
        amPmSpinner2 = findViewById(R.id.am_pm_spinner2);
//        startRouteEditText = findViewById(R.id.StartRoute);
//        finishRouteEditText = findViewById(R.id.FinishRoute);







        amPmRadioGroup1 = findViewById(R.id.am_pm_radio_group1);
        amPmRadioGroup2 = findViewById(R.id.am_pm_radio_group2);
        registerButton = findViewById(R.id.register_button);





        final AutoCompleteTextView autoCompleteTextView=findViewById(R.id.autocomplete);
        final AutoCompleteTextView autoCompleteTextView1=findViewById(R.id.autocomplete1);
        autoCompleteTextView.setAdapter(new PlaceAutoSuggestAdapter(DriverRegister.this,android.R.layout.simple_list_item_1));


        autoCompleteTextView1.setAdapter(new PlaceAutoSuggestAdapter(DriverRegister.this,android.R.layout.simple_list_item_1));






        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ",autoCompleteTextView.getText().toString());
                LatLng latLng=getLatLngFromAddress(autoCompleteTextView.getText().toString());
                if(latLng!=null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address=getAddressFromLatLng(latLng);
                    if(address!=null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ",""+address.getAddressLine(0));
                        Log.d("Phone : ",""+address.getPhone());
                        Log.d("Pin Code : ",""+address.getPostalCode());
                        Log.d("Feature : ",""+address.getFeatureName());
                        Log.d("More : ",""+address.getLocality());
                    }
                    else {
                        Log.d("Adddress","Address Not Found");
                    }
                }
                else {
                    Log.d("Lat Lng","Lat Lng Not Found");
                }

            }
        });







        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Address : ",autoCompleteTextView.getText().toString());
                LatLng latLng=getLatLngFromAddress(autoCompleteTextView.getText().toString());
                if(latLng!=null) {
                    Log.d("Lat Lng : ", " " + latLng.latitude + " " + latLng.longitude);
                    Address address=getAddressFromLatLng(latLng);
                    if(address!=null) {
                        Log.d("Address : ", "" + address.toString());
                        Log.d("Address Line : ",""+address.getAddressLine(0));
                        Log.d("Phone : ",""+address.getPhone());
                        Log.d("Pin Code : ",""+address.getPostalCode());
                        Log.d("Feature : ",""+address.getFeatureName());
                        Log.d("More : ",""+address.getLocality());
                    }
                    else {
                        Log.d("Adddress","Address Not Found");
                    }
                }
                else {
                    Log.d("Lat Lng","Lat Lng Not Found");
                }

            }
        });









        registerButton.setEnabled(false); // Disable the register button initially

        amPmRadioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId != -1 && amPmRadioGroup2.getCheckedRadioButtonId() != -1) {
                    registerButton.setEnabled(true); // Enable the register button if both radio groups have a selection
                } else {
                    registerButton.setEnabled(false); // Disable the register button if any radio group has no selection
                }
            }
        });

        amPmRadioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId != -1 && amPmRadioGroup1.getCheckedRadioButtonId() != -1) {
                    registerButton.setEnabled(true); // Enable the register button if both radio groups have a selection
                } else {
                    registerButton.setEnabled(false); // Disable the register button if any radio group has no selection
                }
            }
        });




        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String serviceTime = amPmSpinner1.getSelectedItem().toString() ;
                String finishservicetime=amPmSpinner2.getSelectedItem().toString();
                String startRoute = autoCompleteTextView.getText().toString();
                String finishRoute = autoCompleteTextView1.getText().toString();

                int selectedAmPmRadioId1 = amPmRadioGroup1.getCheckedRadioButtonId();
                RadioButton selectedAmPmRadio1 = findViewById(selectedAmPmRadioId1);
                String amPm1 = selectedAmPmRadio1.getText().toString();

                int selectedAmPmRadioId2 = amPmRadioGroup2.getCheckedRadioButtonId();
                RadioButton selectedAmPmRadio2 = findViewById(selectedAmPmRadioId2);
                String amPm2 = selectedAmPmRadio2.getText().toString();


//
///
//         to show error
                boolean hasError = false;

                if (name.isEmpty()) {
                    nameEditText.setError("Please enter a name");
                    hasError = true;
                }

                if (phone.isEmpty()) {
                    phoneEditText.setError("Please enter a phone number");
                    hasError = true;
                }
                if (startRoute.isEmpty()) {
                    startRouteEditText.setError("Please enter a phone number");
                    hasError = true;
                }
                if (finishRoute.isEmpty()) {
                    finishRouteEditText.setError("Please enter a phone number");
                    hasError = true;
                }






                if (serviceTime.isEmpty()) {
                    // Show error for amPmSpinner1 or handle it accordingly
                    hasError = true;
                }

                // Check other fields similarly...

                if (hasError) {
                    return;
                }





                // Prepare the data to be sent to the server

                // Replace the URL below with your server endpoint


//                https://brick-red-whistle.000webhostapp.com/app/registerdriver.php?NAME=John&PHONE=123456789&sts=10:00&stsampm=AM&stf=11:00&stsamm=PM&stringroute=Start&finishroute=Finish

                String url="https://brick-red-whistle.000webhostapp.com/app/registerdriver.php?NAME="+name+"&PHONE_NUMBER="+phone
                        +"&sts="+serviceTime+"&stsampm="+amPm1+"&stf="+finishservicetime+"&stsamm="+amPm2+"&stringroute="+startRoute+"&finishroute="+finishRoute;


                RequestQueue requestQueue = Volley.newRequestQueue(DriverRegister.this);

                StringRequest request = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                new AlertDialog.Builder(DriverRegister.this)
                                        .setTitle("Server Response")
                                        .setMessage(response)
                                        .show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                            }
                        });

                requestQueue.add(request);
            }
        });
    }


    private LatLng getLatLngFromAddress(String address){

        Geocoder geocoder=new Geocoder(DriverRegister.this);
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(address, 1);
            if(addressList!=null){
                Address singleaddress=addressList.get(0);
                LatLng latLng=new LatLng(singleaddress.getLatitude(),singleaddress.getLongitude());
                return latLng;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    private Address getAddressFromLatLng(LatLng latLng){
        Geocoder geocoder=new Geocoder(DriverRegister.this);
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 5);
            if(addresses!=null){
                Address address=addresses.get(0);
                return address;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }



}
