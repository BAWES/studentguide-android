package com.techno.studentguide.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.techno.studentguide.R;
import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.customview.CustomButton;
import com.techno.studentguide.customview.CustomEditText;
import com.techno.studentguide.customview.CustomProgressDialog;
import com.techno.studentguide.model.ContactUsApi;
import com.techno.studentguide.utils.CommonFunctions;
import com.techno.studentguide.utils.ControllerClass;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {
    //Variable declarations based on views used.
    ImageView vHome, vMessage, vSettings, vTwitter, vInstagram;
    CustomButton vSubmit;
    CustomEditText vName, vContactNo, vAddress;
    //Progress dialog
    CustomProgressDialog vCPD;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from activity_contact_us.xml
        setContentView(R.layout.activity_contact_us);
        // Find your IDS in your activity_choose_language.xml layout
        initView();
        vSettings.setVisibility(View.GONE);
        // Button click event triggered ie Home, Message, Submit
        vSubmit.setOnClickListener(this);
        vHome.setOnClickListener(this);
        vMessage.setOnClickListener(this);
    }

    private void initView() {
        vName = (CustomEditText) findViewById(R.id.ACU_CET_name);
        vContactNo = (CustomEditText) findViewById(R.id.ACU_CET_contact_no);
        vAddress = (CustomEditText) findViewById(R.id.ACU_CET_message);
        vSubmit = (CustomButton) findViewById(R.id.ACU_CBTN_submit);
        mToolbar = (Toolbar) findViewById(R.id.AC_TB_details_toolbar);
        vHome = (ImageView) findViewById(R.id.LFT_IV_home);
        vMessage = (ImageView) findViewById(R.id.LFT_IV_message);
        vSettings = (ImageView) findViewById(R.id.LFT_IV_settings);
        vTwitter = (ImageView) findViewById(R.id.IV_twitter);
        vInstagram = (ImageView) findViewById(R.id.IV_instagram);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        if (AppConfig.getLanguage_code().equalsIgnoreCase("en")) {
            mToolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        } else {
            mToolbar.setNavigationIcon(R.drawable.right_arrow);
        }

        vTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pkManager = getPackageManager();
                try {
                    PackageInfo pkgInfo = pkManager.getPackageInfo("com.twitter.android", 0);
                    String getPkgInfo = pkgInfo.toString();
                    if (getPkgInfo.contains("com.twitter.android")) {
                        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.twitter.android");
                        startActivity(launchIntent);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"));
                    startActivity(intent);
                    e.printStackTrace();
                }
            }
        });
        vInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pkManager = getPackageManager();
                try {
                    PackageInfo pkgInfo = pkManager.getPackageInfo("com.instagram.android", 0);
                    String getPkgInfo = pkgInfo.toString();
                    if (getPkgInfo.contains("com.instagram.android")) {
                        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
                        startActivity(launchIntent);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/"));
                    startActivity(intent);
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.LFT_IV_home:
                ControllerClass.CallHome(ContactUsActivity.this, ChooseLanguage.class);
                break;
            case R.id.LFT_IV_message:
                break;
            case R.id.ACU_CBTN_submit:
                //Message to be Alert

                if (vName.getText().toString().isEmpty()) {
                    vName.setError("Enter name");
                    vName.requestFocus();
                } else if (vContactNo.getText().toString().isEmpty()) {
                    vContactNo.setError("Enter contact no");
                    vContactNo.requestFocus();
                } else if (vAddress.getText().toString().isEmpty()) {
                    vAddress.setError("Enter Address");
                    vAddress.requestFocus();
                }

                if (!vContactNo.getText().toString().isEmpty() && !vName.getText().toString().isEmpty() && !vAddress.getText().toString().isEmpty()) {
                    if (CommonFunctions.getInstance().isNetworkAvailable(ContactUsActivity.this)) {
                        ContactUsApi.getInstance().Callresponse(vName.getText().toString(), vAddress.getText().toString(), vContactNo.getText().toString(), new Callback<ContactUsApi.FeedbackDetails>() {
                            @Override
                            public void success(ContactUsApi.FeedbackDetails feedbackDetails, Response response) {
                                if (feedbackDetails.getCode() == 200) {
                                    Toast.makeText(ContactUsActivity.this, feedbackDetails.getMessage(), Toast.LENGTH_SHORT).show();
                                    vName.getText().clear();
                                    vAddress.getText().clear();
                                    vContactNo.getText().clear();
                                } else {
                                    Toast.makeText(ContactUsActivity.this, feedbackDetails.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                error.printStackTrace();
                            }
                        });
                    } else {
                        Toast.makeText(ContactUsActivity.this, "Please enable internet connection.", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
