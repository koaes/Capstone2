package com.example.windows7.myapplication.service;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.windows7.myapplication.AppWidget;
import com.example.windows7.myapplication.R;

public class AppIntentService extends IntentService{

    public AppIntentService() {
        super("AppIntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String dataString = intent.getDataString();

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);

        int appWidgetId = intent.getIntExtra("APPWIDGET_ID", 0);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String receipeName = sharedPreferences.getString("row","");

        Log.v("SharedPref", "In Service");
        Log.v("SharedPref", receipeName);

        RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.app_widget);
        views.setTextViewText(R.id.subTitle, receipeName);


        Intent intentUpdate = new Intent(this, AppWidget.class);
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        //int[] idArray = new int[]{appWidgetId};
        //intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }
}
