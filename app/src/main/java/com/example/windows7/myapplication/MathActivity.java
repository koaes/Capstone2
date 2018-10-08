package com.example.windows7.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MathActivity extends AppCompatActivity {

    android.support.v4.app.FragmentManager managerMath;
    String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        if(savedInstanceState == null) {

            String operation = getIntent().getStringExtra("Operation");
            lang = getIntent().getStringExtra("Lang");
            MathFragment mathFragment = new MathFragment();


            Bundle bundle = new Bundle();
            switch(operation) {
                case "add":
                    bundle.putString("Operation", "+");
                    bundle.putString("Lang", lang);
                    break;
                case "subtract":
                    bundle.putString("Operation", "-");
                    bundle.putString("Lang", lang);
                    break;
            }
            mathFragment.setArguments(bundle);

            managerMath = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = managerMath.beginTransaction();

            fragmentTransaction.add(R.id.fragment_container_math, mathFragment, "math");
            fragmentTransaction.commit();

        }
    }
}
