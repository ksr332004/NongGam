package com.sgen.nonggam.widget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.RemoteViews;

import com.sgen.nonggam.R;
import com.sgen.nonggam.api.CallApi;
import com.sgen.nonggam.data.AppWidgetData;
import com.sgen.nonggam.main.MainActivity;

/**
 * Created by SeRan on 2016-07-03.
 */
public class NonggamAppWidgetService extends IntentService {


    public NonggamAppWidgetService() {
        super("NonggamAppWidgetService");
    }

    @Override
    protected void onHandleIntent(final Intent intent) {

        final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        final int[] appIds=intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

        CallApi.getInstance().requestAppWidgetData(new CallApi.NetworkResult() {
            @Override
            public void onSuccess(AppWidgetData appWidgetData) {
                for (int appWidgetId : appIds) {
                    updateAppWidget(getApplicationContext(), appWidgetManager, appWidgetId, appWidgetData);
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
     void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, AppWidgetData appWidgetData) {

         RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);

         views.setTextViewText(R.id.area_text, getAreaText(appWidgetData.area) );
         views.setTextViewText(R.id.degree_txt, getSpannedText(appWidgetData.degree));
         views.setTextViewText(R.id.summary_txt, appWidgetData.summary);
         views.setTextViewText(R.id.yesterday_txt, getCompareYesterdayText(appWidgetData.compareYesterday));
         views.setTextViewText(R.id.comment_text, getCommentText(appWidgetData.comment, appWidgetData.warningComment));
         views.setTextViewText(R.id.time_text, getAnnounceTimeText(appWidgetData.announceTime));

         views.setTextColor(R.id.degree_txt, getWeatherTextColor(appWidgetData.backgroundImg));
         views.setTextColor(R.id.summary_txt, getWeatherTextColor(appWidgetData.backgroundImg));
         views.setTextColor(R.id.yesterday_txt, getWeatherTextColor(appWidgetData.backgroundImg));
         views.setTextColor(R.id.comment_text, getCommentTextColor(appWidgetData.warningComment));

         views.setImageViewResource(R.id.background_img,getWeatherBackground(appWidgetData.backgroundImg));
         views.setImageViewResource(R.id.weather_img,getWeatherImage(appWidgetData.backgroundImg));
         views.setImageViewResource(R.id.weather_icon,getWeatherIcon(appWidgetData.backgroundImg, appWidgetData.warningComment));
         views.setImageViewResource(R.id.comment_img,getCommentImage(appWidgetData.warningComment));

         Intent intent = new Intent(context, MainActivity.class);
         PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
         views.setOnClickPendingIntent(R.id.background_img, pendingIntent);

         appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private String getAreaText(String areaText){
        return areaText.split(" ")[0] + "/" + areaText.split(" ")[1];
    }

    private String getSpannedText(String degreeText){
        return degreeText + "˚";
    }

    private String getCompareYesterdayText(String yesterdayText){
        int yesterdayInt = Integer.parseInt(yesterdayText);

        if(yesterdayInt < 0){
            return "어제보다 " + getSpannedText(yesterdayText) + "낮음";
        }else if(yesterdayInt > 0){
            return "어제보다 " + getSpannedText(yesterdayText) + "높음";
        }else {
            return "기온이 어제와 같음";
        }
    }

    private String getCommentText(String commentText, String warningCommentText){
        if("null".equals(warningCommentText)){
            return commentText;
        } else {
            return warningCommentText;
        }
    }

    private String getAnnounceTimeText(String announceTimeText){
        return "("+announceTimeText+"시 기준)";
    }

    private int getWeatherTextColor(String weatherState){
        if ("mist".equals(weatherState)) {
            return Color.parseColor("#484848");
        } else if ("dust".equals(weatherState)) {
            return Color.parseColor("#484848");
        } else if ("night".equals(weatherState)) {
            return Color.parseColor("#484848");
        } else {
            return Color.parseColor("#ffffff");
        }
    }

    private int getCommentTextColor(String warningCommentText){
        if("null".equals(warningCommentText)){
            return Color.parseColor("#3d3d3d");
        } else {
            return Color.parseColor("#ffffff");
        }
    }

    private int getWeatherBackground(String weatherState) {
        if ("night".equals(weatherState)) {
            return R.drawable.bg_night;
        } else if ("mist".equals(weatherState)) {
            return R.drawable.bg_fog;
        } else if ("rain".equals(weatherState)) {
            return R.drawable.bg_rain;
        } else if ("typhoon".equals(weatherState)) {
            return R.drawable.bg_storm;
        } else if ("dust".equals(weatherState)) {
            return R.drawable.bg_yellowdust;
        } else if ("heat".equals(weatherState)) {
            return R.drawable.bg_heatwave;
        } else if ("windy".equals(weatherState)) {
            return R.drawable.bg_wind;
        } else if ("snow".equals(weatherState)) {
            return R.drawable.bg_snow;
        } else {
            return R.drawable.bg_clear;
        }
    }

     private int getWeatherImage(String weatherState){
         if ("sun".equals(weatherState)) {
             return R.drawable.img_sun;
         } else if ("rain".equals(weatherState)) {
             return R.drawable.img_rain;
         } else if ("typhoon".equals(weatherState)) {
             return R.drawable.img_rain;
         } else if ("heat".equals(weatherState)) {
             return R.drawable.img_heatsun;
         } else if ("windy".equals(weatherState)) {
             return R.drawable.img_leaf;
         } else if ("snow".equals(weatherState)) {
             return R.drawable.img_snow;
         } else {
             return R.drawable.img_moon;
         }
    }

    private int getWeatherIcon(String weatherState, String commentState){
        if(!"null".equals(commentState)){
            return R.drawable.icon_alert;
        } else {
            if ("night".equals(weatherState)) {
                return R.drawable.icon_moon;
            } else if ("mist".equals(weatherState)) {
                return R.drawable.icon_darkcloud;
            } else if ("rain".equals(weatherState)) {
                return R.drawable.icon_rain;
            } else if ("typhoon".equals(weatherState)) {
                return R.drawable.icon_rain;
            } else if ("dust".equals(weatherState)) {
                return R.drawable.icon_cloud;
            } else if ("heat".equals(weatherState)) {
                return R.drawable.icon_sun;
            } else if ("windy".equals(weatherState)) {
                return R.drawable.icon_wind;
            } else if ("snow".equals(weatherState)) {
                return R.drawable.icon_snow;
            } else {
                return R.drawable.icon_sun;
            }
        }
    }

    private int getCommentImage(String commentState){
        if("null".equals(commentState)){
            return  R.drawable.shape_whitebox;
        } else{
            return  R.drawable.shape_redbox;
        }
    }


}
