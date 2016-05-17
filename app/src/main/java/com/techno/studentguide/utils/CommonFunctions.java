package com.techno.studentguide.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.techno.studentguide.R;


/**
 * Created by tech on 1/29/2016.
 */
public class CommonFunctions {
    private static CommonFunctions ourInstance = new CommonFunctions();

    public static CommonFunctions getInstance() {
        return ourInstance;
    }

    public boolean isNetworkAvailable(Context mContext) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public String getDeviceId(Context mContext) {
        return Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);

    }

    public void showToast(Context mContext, String mMessage) {
        Toast.makeText(mContext, mMessage, Toast.LENGTH_SHORT).show();
    }

    public Double setTwoDigitDecimal(double twoDigitDecimal) {
        String val = String.format("%.2f", twoDigitDecimal);
        double d = 0;
        try {
            d = Double.parseDouble(val);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static void setNotificationBarColor(Activity context) {
        Context cns = context;
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(cns.getResources().getColor(R.color.clr_green));
            //  window.setNavigationBarColor(getResources().getColor(R.color.background_green));
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

}
