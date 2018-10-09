package com.example.windows7.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MainMenuFragment.Communicator {

    android.support.v4.app.FragmentManager manager;
    private Tracker mTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        // Obtain the shared Tracker instance.

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        MainMenuFragment mainMenuFragment = new MainMenuFragment();

        manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, mainMenuFragment, "Main");

        fragmentTransaction.commit();

    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onResume() {
            super.onResume();
    // sets screen name and send it to google analytics<br />
                mTracker.setScreenName("Main Activity");
                mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        //savedInstanceState.putParcelable("recipe", currentRecipe);

    }

    @Override
    public void respond(int position) {

        Locale primary = getApplicationContext().getResources().getConfiguration().locale;

        Log.v("Language Position", Integer.toString(position));

        if(position==1){
            primary = new Locale("de");
        } else{
            primary = new Locale("us");
        }
        Configuration configuration = getBaseContext().getResources().getConfiguration();
        configuration.locale = primary;
        getBaseContext().getResources().updateConfiguration(configuration,
                getBaseContext().getResources().getDisplayMetrics());

        Intent intent = new Intent(this, ActivityMenu.class);
        switch(position){
            case 0: intent.putExtra("Lang", "en"); break;
            case 1: intent.putExtra("Lang", "de"); break;
        }
        startActivity(intent);
    }
}
