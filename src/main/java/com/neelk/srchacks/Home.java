package com.neelk.srchacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class Home extends AppCompatActivity {

    //Only contains navigation bar logic, real Home is HomeFragment
    private android.support.v4.app.Fragment fragment;
    private BottomNavigationView mBottomNavigationView;
    private FragmentTransaction toHomeFragment;

    private int mSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toHomeFragment = getSupportFragmentManager().beginTransaction();
        toHomeFragment.replace(R.id.constraint_layout, HomeFragment.newInstance());
        toHomeFragment.addToBackStack(null);
        toHomeFragment.commit();

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);


        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });


    }

    private void selectFragment(MenuItem item) {
        fragment = null;
        FragmentTransaction ft;
        Intent intent;
        // init corresponding fragment
        switch (item.getItemId()) {

            case R.id.menu_home:
                fragment = HomeFragment.newInstance();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.constraint_layout, fragment, "Home");
                ft.addToBackStack(null);
                ft.commit();
                break;


            case R.id.menu_fragment2:
                fragment = Fragment2.newInstance();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.constraint_layout, fragment, "Recycling Center");
                ft.addToBackStack(null);
                ft.commit();
                break;

            case R.id.menu_settings:
                fragment = Settings.newInstance();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.constraint_layout, fragment, "Settings");
                ft.addToBackStack(null);
                ft.commit();
                break;

            case R.id.menu_pedometer:
                fragment = Pedometer.newInstance();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.constraint_layout, fragment, "Settings");
                ft.addToBackStack(null);
                ft.commit();
                break;
        }
    }



}
