package com.techno.studentguide.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.techno.studentguide.R;
import com.techno.studentguide.customview.CustomButton;
import com.techno.studentguide.customview.CustomEditText;
import com.techno.studentguide.customview.CustomProgressDialog;
import com.techno.studentguide.utils.ControllerClass;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {
    //Variable declarations based on views used.
    ImageView vHome, vMessage, vSettings;
    CustomButton vSubmit;
    CustomEditText vName, vContactNo, vAddress;
    //Progress dialog
    CustomProgressDialog vCPD;

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

        vHome = (ImageView) findViewById(R.id.LFT_IV_home);
        vMessage = (ImageView) findViewById(R.id.LFT_IV_message);
        vSettings = (ImageView) findViewById(R.id.LFT_IV_settings);
    }
/*
    private void sendContactUs(String mName,String mContactNo,String mAddress)
    {
        boolean boolValidation = Validation.getInstance().contactUsValidation(this,mName,mContactNo,mAddress);
        if(boolValidation)
        {
           *//* vCPD = CustomProgressDialog.getInstance();
            vCPD.show(ContactUsActivity.this, "", getString(R.string.loading));
            ContactUsApi.getInstance().Callresponse(this,mName,mContactNo,mAddress);*//*
        }
    }*/

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.LFT_IV_home:
                ControllerClass.CallScreen(ContactUsActivity.this, ChooseLanguage.class);
                break;
            case R.id.LFT_IV_message:
                break;
            case R.id.ACU_CBTN_submit:
                //Message to be Alert
                Toast.makeText(ContactUsActivity.this, "Work inProgress", Toast.LENGTH_SHORT).show();
//                String mName = vName.getText().toString();
//                String mContactNo = vContactNo.getText().toString();
//                String mAddress = vAddress.getText().toString();
//                sendContactUs(mName,mContactNo,mAddress);
                break;
        }
    }

    /*@Override
    public void success(ContactUsApi.ContactUsInformation contactUsInformation, Response response) {
        if (vCPD != null) {
            if (vCPD.isShowing()) {
                vCPD.dismiss();
            }
        }
    }

    @Override
    public void failure(RetrofitError error) {
        if (vCPD != null) {
            if (vCPD.isShowing()) {
                vCPD.dismiss();
            }
        }
    }*/
}
