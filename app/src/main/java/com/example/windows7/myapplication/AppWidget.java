package com.example.windows7.myapplication;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.example.windows7.myapplication.service.AppIntentService;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        for (int appWidgetId : appWidgetIds) {

            Intent intentService = new Intent(context, AppIntentService.class);
            intentService.putExtra("APPWIDGET_ID", appWidgetId);
            context.startService(intentService);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    @Override
    public void onReceive(final Context context, Intent intent){

        final String action = intent.getAction();
        if(action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)){

            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            ComponentName cn = new ComponentName(context, AppWidget.class);

            int appWidgetIds[] = mgr.getAppWidgetIds(new ComponentName(context, AppWidget.class));
            mgr.notifyAppWidgetViewDataChanged(appWidgetIds,R.id.subTitle);
            onUpdate(context,mgr,appWidgetIds);
        }
        super.onReceive(context, intent);
    }



    public static void sendRefreshBroadcast(Context context){

        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.setComponent(new ComponentName(context, AppWidget.class));
        context.sendBroadcast(intent);

    }
}

