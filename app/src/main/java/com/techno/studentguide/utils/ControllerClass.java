package com.techno.studentguide.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.IntentCompat;

/**
 * Created by Android on 5/13/2016.
 */
public class ControllerClass {

    public static void CallScreen(Context mContext, Class mClass) {
        Intent in = new Intent(mContext, mClass);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(in);
    }

    public static void CallHome(Context mContext, Class mClass) {
        Intent intent = new Intent(mContext, mClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    public static void CallTerms(Context mContext, Class mClass) {
        Intent in = new Intent(mContext, mClass);
        mContext.startActivity(in);
    }

    public static void CallContactus(Context mContext, Class mClass) {
        Intent in = new Intent(mContext, mClass);
        mContext.startActivity(in);
    }

}
