package com.techno.studentguide.activity;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.techno.studentguide.R;
import com.techno.studentguide.customview.CustomButton;
import com.techno.studentguide.customview.CustomTextView;
import com.techno.studentguide.utils.ControllerClass;

import java.util.Locale;

public class ChooseLanguage extends AppCompatActivity implements View.OnClickListener {
    // Custom Buttons for English and Arabic
    CustomButton vEnglish, vArabic;

    // Custom TextView for Terms and Conditions
    CustomTextView vTermsConditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);
//				getSupportActionBar ().hide ();
        initView();
        vEnglish.setOnClickListener(this);
        vArabic.setOnClickListener(this);
        vTermsConditions.setOnClickListener(this);
    }

    // Initialize the view
    private void initView() {
        vEnglish = (CustomButton) findViewById(R.id.CL_btn_english);
        vArabic = (CustomButton) findViewById(R.id.CL_btn_arabic);
        vTermsConditions = (CustomTextView) findViewById(R.id.CL_tv_termscondition);
    }

    // Implement the click events
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.CL_btn_english) {
            Chooselanguage("en");
            ControllerClass.CallScreen(ChooseLanguage.this, AreaActivity.class);
        } else if (v.getId() == R.id.CL_btn_arabic) {
            Chooselanguage("ar");
            forceRTLIfSupported();
            ControllerClass.CallScreen(ChooseLanguage.this, AreaActivity.class);
        } else if (v.getId() == R.id.CL_tv_termscondition) {
            ControllerClass.CallScreen(ChooseLanguage.this, TermsConditionsActivity.class);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    public void Chooselanguage(String languageCode) {
        String languageToLoad = languageCode;
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
}
