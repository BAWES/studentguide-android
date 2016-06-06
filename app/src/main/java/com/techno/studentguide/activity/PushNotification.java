package com.techno.studentguide.activity;

import android.app.Application;

import com.onesignal.OneSignal;

/**
 * Created by tech on 5/26/2016.
 */
public class PushNotification extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this).init();
        /*OneSignal.startInit(this)
                .setAutoPromptLocation(true)
                .init();*/
        OneSignal.enableInAppAlertNotification(true);
    }
}
