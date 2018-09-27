package com.example.windows7.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MathActivity extends AppCompatActivity {

    android.support.v4.app.FragmentManager managerMath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        if(savedInstanceState == null) {

            MathFragment mathFragment = new MathFragment();

            managerMath = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = managerMath.beginTransaction();

            fragmentTransaction.add(R.id.fragment_container_math, mathFragment, "math");
            fragmentTransaction.commit();

        }
    }
}
