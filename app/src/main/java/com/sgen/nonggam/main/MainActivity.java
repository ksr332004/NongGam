package com.sgen.nonggam.main;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sgen.nonggam.R;
import com.sgen.nonggam.nonggam.BackKeyPressCloseHandler;

public class MainActivity extends AppCompatActivity {

    private BackKeyPressCloseHandler backKeyPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        String url = "your_url";

        Log.v("로그", "url : " + url);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl(url);

        backKeyPressCloseHandler = new BackKeyPressCloseHandler(this);

    }

    @Override
    public void onBackPressed() {
        backKeyPressCloseHandler.onBackPressed();
    }

}

