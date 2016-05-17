package com.techno.studentguide.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tech on 2/3/2016.
 */
public class SessionManager {
    private static SessionManager ourInstance = new SessionManager();
    private String FOXTAXICUSTOMER = "StudentGuidePrefs";

    public static SessionManager getInstance() {
        return ourInstance;
    }

    private SessionManager() {
    }

    public void setDeviceToken(Context mContext, String deviceToken) {
        SharedPreferences prefs = mContext.getSharedPreferences(FOXTAXICUSTOMER, Context.MODE_PRIVATE);
        prefs.edit().putString("DeviceToken", deviceToken).apply();
    }
    public String getDeviceToken(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(FOXTAXICUSTOMER, Activity.MODE_PRIVATE);
        return prefs.getString("DeviceToken", "");
    }

}
