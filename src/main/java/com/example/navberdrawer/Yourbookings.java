package com.example.navberdrawer;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class Yourbookings extends Fragment {

    private SharedPreferences sharedPreferences;
    private ArrayList<String> dataList;
    private ListView listView;
    private MyAdapter myAdapter;
    private Button clearButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_yourbookings, container, false);

        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        String savedData = sharedPreferences.getString("data", "");

        // Split the saved data into an array of entries
        String[] entries = savedData.split("\t");
        dataList = new ArrayList<>(Arrays.asList(entries));

        listView = myview.findViewById(R.id.listview);
        myAdapter = new MyAdapter(dataList);
        listView.setAdapter(myAdapter);

        clearButton = myview.findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear the shared preferences data
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("data");
                editor.apply();

                // Clear the data list and update the adapter
                dataList.clear();
                myAdapter.notifyDataSetChanged();
            }
        });

        return myview;
    }

    private class MyAdapter extends BaseAdapter {
        private ArrayList<String> dataList;

        public MyAdapter(ArrayList<String> dataList) {
            this.dataList = dataList;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @SuppressLint("MissingInflatedId")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.mylists, parent, false);

             TextView textView = view.findViewById(R.id.textview1);
            String entry = dataList.get(position);
            textView.setText(entry);

            return view;
        }
    }
}
