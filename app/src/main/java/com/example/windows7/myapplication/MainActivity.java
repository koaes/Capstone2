package com.example.windows7.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MainMenuFragment.Communicator {

    android.support.v4.app.FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        MainMenuFragment mainMenuFragment = new MainMenuFragment();

        manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, mainMenuFragment, "Main");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ActivityMenuFragment activityMenuFragment = new ActivityMenuFragment();
            fragmentTransaction.add(R.id.fragment_container2, activityMenuFragment, "Activity");

        }
        fragmentTransaction.commit();


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        //savedInstanceState.putParcelable("recipe", currentRecipe);

    }

    @Override
    public void respond(int position) {

        Locale primary = getApplicationContext().getResources().getConfiguration().locale;

        if(position==1){
            primary = new Locale("de");
        } else{
            primary = new Locale("us");
        }
        Configuration configuration = getBaseContext().getResources().getConfiguration();
        configuration.locale = primary;
        getBaseContext().getResources().updateConfiguration(configuration,
                getBaseContext().getResources().getDisplayMetrics());

        if((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)) {
            ActivityMenuFragment newFragment = new ActivityMenuFragment();

            //Bundle bundle = new Bundle();
            //bundle.putParcelable("Package", currentRecipe);
            //bundle.putInt("Position",position);
            //newFragment.setArguments(bundle);

            //android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //transaction.replace(R.id.fragment_container2, newFragment);
            //transaction.addToBackStack(null);
            //transaction.commit();
        }else{

            Intent intent = new Intent(this, ActivityMenu.class);
            //intent.putExtra("Package",currentRecipe);
            //intent.putExtra("Position", position);
            startActivity(intent);
        }

    }

}
