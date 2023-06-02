package com.example.navberdrawer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.navberdrawer.adapter.PlaceAutoSuggestAdapter;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class firstfragment extends Fragment {
   public EditText currentLocation;
    TextView textview;
   public EditText destination;
    Button searchButton,searchb;
   public ListView listView;
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myview = inflater.inflate(R.layout.fragment_firstfragement, container, false);
//
//        currentLocation = myview.findViewById(R.id.currentlocation);
//        destination = myview.findViewById(R.id.destination);

//        listView = myview.findViewById(R.id.listview);
        searchb=myview.findViewById(R.id.button2);









        final AutoCompleteTextView autoCompleteTextView=myview.findViewById(R.id.autocomplete);
        final AutoCompleteTextView autoCompleteTextView1=myview.findViewById(R.id.autocomplete1);
        autoCompleteTextView.setAdapter(new PlaceAutoSuggestAdapter(getContext(),android.R.layout.simple_list_item_1));



        autoCompleteTextView1.setAdapter(new PlaceAutoSuggestAdapter(getContext(),android.R.layout.simple_list_item_1));



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












//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://brick-red-whistle.000webhostapp.com/app/driverdata.php?stringroute=" + currentLocation.getText().toString()+"&finishroute="+destination.getText().toString(), null, new Response.Listener<JSONArray>() {
//
////          https://brick-red-whistle.000webhostapp.com/app/driverdata.php?stringroute=M&finishroute=N
//
//
//
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//                        arrayList.clear();
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//                                JSONObject jsonObject = response.getJSONObject(i);
//
//                                String name = jsonObject.getString("NAME");
//
//                                hashMap = new HashMap<>();
//
//                                hashMap.put("NAME", name);
//
//                                arrayList.add(hashMap);
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                        if (arrayList.size() > 0) {
//                            MyAdapter myAdapter = new MyAdapter();
//                            listView.setAdapter(myAdapter);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    }
//                });
//                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//                requestQueue.add(jsonArrayRequest);
//            }
//        });

        searchb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getContext(), Listviewactivity2.class);
                myintent.putExtra("keycl", autoCompleteTextView.getText().toString());
                myintent.putExtra("keydn", autoCompleteTextView1.getText().toString());
                startActivity(myintent);

            }
        });
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            String url =  "https://brick-red-whistle.000webhostapp.com/app/driverdata.php?stringroute=" + currentLocation.getText().toString()+"&finishroute="+destination.getText().toString();
//                StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                     textview.setText(response.toString());
//
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//
//                });
//                RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
//                requestQueue.add(request);
//
//
//            }
//        });

        return myview;
    }

    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint("MissingInflatedId")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View myview = layoutInflater.inflate(R.layout.layoutfile, null);


            TextView myname = myview.findViewById(R.id.myname);

            hashMap = arrayList.get(i);

            String name = hashMap.get("NAME");


            myname.setText(name);

            return myview;


        }
    }






    private LatLng getLatLngFromAddress(String address){

        Geocoder geocoder=new Geocoder(getContext());
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
        Geocoder geocoder=new Geocoder(getContext());
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
