package com.example.windows7.myapplication.service;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;
import com.example.windows7.myapplication.AppWidget;
import com.example.windows7.myapplication.MainActivity;
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
        String statInfo = sharedPreferences.getString("row","");

        String widgetDisplay = "Total Problems Solved: " + statInfo;

        RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.app_widget);
        views.setTextViewText(R.id.subTitle, widgetDisplay);


        Intent intentUpdate = new Intent(this, AppWidget.class);
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        Intent intentButton = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intentButton,0);
        views.setOnClickPendingIntent(R.id.widgetButton ,pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }
}
