package com.sgen.nonggam.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.widget.RemoteViews;

import com.sgen.nonggam.R;
import com.sgen.nonggam.api.CallApi;
import com.sgen.nonggam.data.AppWidgetData;
import com.tsengvn.typekit.Typekit;

/**
 * Implementation of App Widget functionality.
 */
public class NonggamAppWidgetProvider extends AppWidgetProvider {




    @Override
    public void onUpdate(final Context context,final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {

        Intent intent = new Intent(context.getApplicationContext(), NonggamAppWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        context.startService(intent);
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
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String action = intent.getAction();
        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)) {
            ComponentName thisWidget = new ComponentName(context,
                    NonggamAppWidgetProvider.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
            // Build the intent to call the service
            Intent in = new Intent(context.getApplicationContext(),
                    NonggamAppWidgetService.class);
            in.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
            context.startService(in);
        }
    }
}

