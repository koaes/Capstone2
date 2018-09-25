package com.example.windows7.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityMenu extends AppCompatActivity {

    android.support.v4.app.FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if(savedInstanceState == null) {
            //current = getIntent().getParcelableExtra("Package");
            //position = getIntent().getIntExtra("Position",0);

            ActivityMenuFragment activityMenuFragment = new ActivityMenuFragment();
            //Bundle bundle = new Bundle();
            //bundle.putParcelable("Package", current);
            //bundle.putInt("Position", position);
            //stepInstructionFragment.setArguments(bundle);

            manager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, activityMenuFragment);
            fragmentTransaction.commit();

        }


    }
}
