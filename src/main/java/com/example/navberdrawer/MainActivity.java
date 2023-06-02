package com.example.navberdrawer;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    DrawerLayout drawerLayout;


    TextView textview;
    MaterialToolbar materialToolbar;
    FrameLayout frameLayout,frameLayout2;
    NavigationView navigationView;
TabLayout tabLayout;
LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5,linearLayout6;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawerlayout);
        materialToolbar=findViewById(R.id.materialtoolbar);
        frameLayout=findViewById(R.id.framelayout);
        frameLayout2=findViewById(R.id.framelayout2);
        navigationView=findViewById(R.id.navigationbar);
       linearLayout1=findViewById(R.id.linearlayout1);
        linearLayout2=findViewById(R.id.linearlayou2);
        linearLayout3=findViewById(R.id.linearlayou3);
        linearLayout4=findViewById(R.id.linearlayou4);
        linearLayout5=findViewById(R.id.linearlayou5);
        linearLayout6=findViewById(R.id.linearlayou6);




//        textview =findViewById(R.id.hello);


        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.SEND_SMS},
                MY_PERMISSIONS_REQUEST_SEND_SMS);





//
//      ///////  frame layout start//////////////////


        tabLayout=findViewById(R.id.tablayout1);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout,new firstfragment());
        fragmentTransaction.commit();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabposition = tab.getPosition();
                if (tabposition == 0){
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framelayout,new firstfragment());
                    fragmentTransaction.commit();

                } else if (tabposition==1) {

                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framelayout,new secondfragment());
                    fragmentTransaction.commit();
                }



            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


//
//
//      ///////  frame layout end//////////////////









        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle( MainActivity.this,drawerLayout,materialToolbar,R.string.close,R.string.open);
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.home){

                    Toast.makeText(MainActivity.this, "this is home", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    linearLayout2.setVisibility(View.INVISIBLE);
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.INVISIBLE);
                    linearLayout3.setVisibility(View.INVISIBLE);
                    linearLayout5.setVisibility(View.INVISIBLE);
                    linearLayout6.setVisibility(View.INVISIBLE);
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framelayout,new firstfragment());
                    fragmentTransaction.commit();

                } else if (item.getItemId()==R.id.Register) {
                    Toast.makeText(MainActivity.this, "THIS IS Register", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
//                    Intent myintent=new Intent(MainActivity.this,Register.class);
//                    startActivity(myintent);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.INVISIBLE);
                    linearLayout4.setVisibility(View.INVISIBLE);
                    linearLayout3.setVisibility(View.INVISIBLE);
                    linearLayout5.setVisibility(View.INVISIBLE);
                    linearLayout6.setVisibility(View.INVISIBLE);
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framelayout2,new Register());
                    fragmentTransaction.commit();
                }
                else if (item.getItemId()==R.id.userbooking) {
                    Toast.makeText(MainActivity.this, "THIS IS Your booking history", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
//                    Intent myintent=new Intent(MainActivity.this,Register.class);
//                    startActivity(myintent);
                    linearLayout2.setVisibility(View.INVISIBLE);
                    linearLayout1.setVisibility(View.INVISIBLE);
                    linearLayout4.setVisibility(View.INVISIBLE);
                    linearLayout5.setVisibility(View.INVISIBLE);
                    linearLayout6.setVisibility(View.INVISIBLE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framelayout3,new Yourbookings());
                    fragmentTransaction.commit();
                }

                else if (item.getItemId()==R.id.support) {
                    Toast.makeText(MainActivity.this, "THIS IS support", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
//                    Intent myintent=new Intent(MainActivity.this,Register.class);
//                    startActivity(myintent);
                    linearLayout2.setVisibility(View.INVISIBLE);
                    linearLayout1.setVisibility(View.INVISIBLE);
                    linearLayout3.setVisibility(View.INVISIBLE);
                    linearLayout5.setVisibility(View.INVISIBLE);
                    linearLayout6.setVisibility(View.INVISIBLE);
                    linearLayout4.setVisibility(View.VISIBLE);
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framelayout4,new Support());
                    fragmentTransaction.commit();
                }
                else if (item.getItemId()==R.id.about) {
                    Toast.makeText(MainActivity.this, "THIS IS about", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
//                    Intent myintent=new Intent(MainActivity.this,Register.class);
//                    startActivity(myintent);
                    linearLayout2.setVisibility(View.INVISIBLE);
                    linearLayout1.setVisibility(View.INVISIBLE);
                    linearLayout3.setVisibility(View.INVISIBLE);
                    linearLayout4.setVisibility(View.INVISIBLE);
                    linearLayout6.setVisibility(View.INVISIBLE);
                    linearLayout5.setVisibility(View.VISIBLE);
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framelayout5,new About());
                    fragmentTransaction.commit();
                }
                else if (item.getItemId()==R.id.settings) {
                    Toast.makeText(MainActivity.this, "THIS IS settings", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
//                    Intent myintent=new Intent(MainActivity.this,Register.class);
//                    startActivity(myintent);
                    linearLayout2.setVisibility(View.INVISIBLE);
                    linearLayout1.setVisibility(View.INVISIBLE);
                    linearLayout3.setVisibility(View.INVISIBLE);
                    linearLayout4.setVisibility(View.INVISIBLE);
                    linearLayout5.setVisibility(View.INVISIBLE);
                    linearLayout6.setVisibility(View.VISIBLE);
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.framelayout6,new Settings());
                    fragmentTransaction.commit();
                }
                return false;
            }
        });
    }
}