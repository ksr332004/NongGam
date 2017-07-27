package com.sgen.nonggam.nonggam;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by SeRan on 2016-07-01.
 */
public class NonggamApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "NanumGothic.ttf"))
                .addBold(Typekit.createFromAsset(this, "NanumGothicBold.ttf"))
                .addCustom1(Typekit.createFromAsset(this, "NanumGothicExtraBold.ttf"));
    }
}
