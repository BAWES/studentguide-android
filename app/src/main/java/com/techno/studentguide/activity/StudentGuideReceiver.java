package com.techno.studentguide.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;
import com.techno.studentguide.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tech on 5/30/2016.
 */
public class StudentGuideReceiver extends GcmListenerService {

    public StudentGuideReceiver() {

    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        Context context = getBaseContext();
        String message = data.getString("message");
        NotificationCompat.Builder mBuilder = null;
        mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle((data.getString("title").isEmpty()) ? getString(R.string.app_name) : data.getString("title"))
                .setContentText(message)
                .setLights(Color.RED, 1, 2)
                .setAutoCancel(true);

        mBuilder.setColor(getResources().getColor(android.R.color.holo_red_dark));


        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, ChooseLanguage.class), PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(contentIntent);
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());

        /*} else {
            mBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle((data.getString("title").isEmpty()) ? getString(R.string.app_name) : data.getString("title"))
                    .setContentText(message)
                    .setLights(Color.RED, 1, 2)
                    .setAutoCancel(true);
            mBuilder.setColor(getResources().getColor(android.R.color.holo_red_dark));
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                    new Intent(this, ChooseLanguage.class), PendingIntent.FLAG_UPDATE_CURRENT);

            mBuilder.setContentIntent(contentIntent);
            NotificationManager mNotificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());

        }*/
    }


    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private void sendNotification(String msg) {
        //logger.log(Log.INFO, msg);
    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
