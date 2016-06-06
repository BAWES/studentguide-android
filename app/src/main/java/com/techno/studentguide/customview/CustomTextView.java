package com.techno.studentguide.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.techno.studentguide.api.AppConfig;


/**
 * Created by tech on 1/29/2016.
 */
public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);
        setFont();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();

    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();

    }

    private void setFont() {
        if (AppConfig.getLanguage_code().equalsIgnoreCase("en")) {
            Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular.ttf");
            setTypeface(font, Typeface.NORMAL);
        } else {
            Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidKufi-Regular.ttf");
            setTypeface(font, Typeface.NORMAL);
        }

    }

}

