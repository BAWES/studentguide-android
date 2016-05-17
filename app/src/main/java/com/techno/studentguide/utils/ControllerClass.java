package com.techno.studentguide.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Android on 5/13/2016.
 */
public class ControllerClass {

    public static void CallScreen(Context mContext, Class mClass) {
        Intent in = new Intent(mContext, mClass);
        mContext.startActivity(in);
    }

}
