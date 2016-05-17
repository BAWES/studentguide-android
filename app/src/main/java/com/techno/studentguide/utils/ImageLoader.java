package com.techno.studentguide.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Android on 5/10/2016.
 */
public class ImageLoader {
    private static ImageLoader ourInstance;
    Context mContext;

    public static ImageLoader getInstance(Context mContext) {

        if(ourInstance==null)
        {
            synchronized (ImageLoader.class)
            {
                if(ourInstance==null)
                {
                    ourInstance = new ImageLoader(mContext);
                }
            }
        }
        ourInstance.mContext=mContext;
        return ourInstance;
    }

    private ImageLoader(Context mContext) {

    }

    public void ShowImage(String mUrl, ImageView vIm){

        Picasso.with(mContext)
                .load(mUrl)
                .config(Bitmap.Config.RGB_565)
                .fit()
                .centerInside()
                .into(vIm);
    }
}
