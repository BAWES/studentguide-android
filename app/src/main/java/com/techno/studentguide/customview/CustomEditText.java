package com.techno.studentguide.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.techno.studentguide.utils.Constants;


/**
 * Created by tech on 1/29/2016.
 */
public class CustomEditText extends EditText {
    public CustomEditText ( Context context ) {
        super(context);
        setFont();
    }

    public CustomEditText ( Context context, AttributeSet attrs ) {
        super(context, attrs);
        setFont();
    }

    public CustomEditText ( Context context, AttributeSet attrs, int defStyle ) {
        super(context, attrs, defStyle);
        setFont();
    }

    private void setFont() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), Constants.appFont);
        setTypeface(font, Typeface.NORMAL);
    }
}
