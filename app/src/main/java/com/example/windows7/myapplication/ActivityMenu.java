package com.example.windows7.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Locale;

public class ActivityMenu extends AppCompatActivity implements ActivityMenuFragment.Communicator {

    android.support.v4.app.FragmentManager manager;
    String lang;
    Intent intent;
    MathFragment mathFragment;
    android.support.v4.app.FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if(savedInstanceState == null) {
            lang = getIntent().getStringExtra("Lang");
            ActivityMenuFragment activityMenuFragment = new ActivityMenuFragment();

            manager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, activityMenuFragment);

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                CountingFragment countingFragment = new CountingFragment();
                fragmentTransaction.add(R.id.fragment_container2, countingFragment, "Activity");
            }
            fragmentTransaction.commit();
        }
    }

    @Override
    public void respond(int position) {

        if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)) {


            Bundle bundle = new Bundle();
            switch(position) {
                case 0:
                    CountingFragment countingFragment = new CountingFragment();
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container2, countingFragment);
                    transaction.addToBackStack(null);
                    break;
                case 1:
                    mathFragment = new MathFragment();
                    bundle.putString("Operation", "+");
                    bundle.putString("Lang", lang);
                    mathFragment.setArguments(bundle);
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container2, mathFragment);
                    transaction.addToBackStack(null);
                    break;
                case 2:
                    mathFragment = new MathFragment();
                    bundle.putString("Operation", "-");
                    bundle.putString("Lang", lang);
                    mathFragment.setArguments(bundle);
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container2, mathFragment);
                    transaction.addToBackStack(null);
                    break;
            }
            transaction.commit();

        }else{

            switch(position) {
                case 0:
                    intent = new Intent(this, CountingActivity.class);
                    intent.putExtra("Lang", lang);
                    break;
                case 1:
                    intent = new Intent(this, MathActivity.class);
                    intent.putExtra("Lang", lang);
                    intent.putExtra("Operation", "add");
                    break;
                case 2:
                    intent = new Intent(this, MathActivity.class);
                    intent.putExtra("Lang", lang);
                    intent.putExtra("Operation", "subtract");
                    break;
            }
            startActivity(intent);
        }

    }

}
