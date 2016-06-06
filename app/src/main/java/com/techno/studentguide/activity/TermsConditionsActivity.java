package com.techno.studentguide.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.techno.studentguide.R;
import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.customview.CustomProgressDialog;
import com.techno.studentguide.utils.ControllerClass;

public class TermsConditionsActivity extends AppCompatActivity {
    // Showing Terms and Conditions URL Page

    TextView mHeading, vTerms;

    // Showing Progress bar
    CustomProgressDialog vCPD;
    ImageView vHome, vMessage;
    Toolbar mToolbar;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String terms_conditions = "terms_conditions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);
        initView();
        try {
            Typeface custom_english = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
            vTerms.setTypeface(custom_english);
            vTerms.setText(Html.fromHtml(sharedpreferences.getString(terms_conditions, null)));
            mHeading.setText(getResources().getString(R.string.txt_title_terms_and_conditions));
            mHeading.setTypeface(custom_english);
        } catch (Exception e) {
            e.printStackTrace();
        }


        vHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerClass.CallHome(TermsConditionsActivity.this, ChooseLanguage.class);
                //Set the transition -> method available from Android 2.0 and beyond
                overridePendingTransition(R.anim.pull_up_from_bottom, R.anim.push_out_to_bottom);
            }
        });

        vMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerClass.CallContactus(TermsConditionsActivity.this, ContactUsActivity.class);
                //Set the transition -> method available from Android 2.0 and beyond
                overridePendingTransition(R.anim.pull_up_from_bottom, R.anim.push_out_to_bottom);
            }
        });


    }


    // Initialize view
    private void initView() {
        vTerms = (TextView) findViewById(R.id.ATC_WV_terms_conditions);
        mToolbar = (Toolbar) findViewById(R.id.AC_TB_details_toolbar);
        mHeading = (TextView) findViewById(R.id.tv_title);
        vHome = (ImageView) findViewById(R.id.LFT_IV_home);
        vMessage = (ImageView) findViewById(R.id.LFT_IV_message);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        mToolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        if (AppConfig.getLanguage_code() != null) {
            if (AppConfig.getLanguage_code().equalsIgnoreCase("en")) {
                mToolbar.setNavigationIcon(R.drawable.ic_back_arrow);
            } else {
                mToolbar.setNavigationIcon(R.drawable.right_arrow);
            }
        } else {
            mToolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        }
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_terms_condition, menu);
        menu.findItem(R.id.action_settings).setVisible(false);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
