package com.example.windows7.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CountingActivity extends AppCompatActivity {

    android.support.v4.app.FragmentManager managerCounting;
    String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);

        if(savedInstanceState == null) {



            lang = getIntent().getStringExtra("Lang");

            CountingFragment countingFragment = new CountingFragment();
            Bundle bundle = new Bundle();
            bundle.putString("Lang", lang);
            countingFragment.setArguments(bundle);

            managerCounting = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = managerCounting.beginTransaction();

            fragmentTransaction.add(R.id.fragment_container_counting, countingFragment, "math");
            fragmentTransaction.commit();

        }
    }
}
