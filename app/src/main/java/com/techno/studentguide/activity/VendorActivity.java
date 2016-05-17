package com.techno.studentguide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.techno.studentguide.R;
import com.techno.studentguide.adapter.VendorListAdapter;
import com.techno.studentguide.model.Vendor;
import com.techno.studentguide.utils.ControllerClass;

import java.util.ArrayList;

public class VendorActivity extends AppCompatActivity implements View.OnClickListener {

    ListView vVendor;
    ArrayList<Vendor> mVendorList = new ArrayList<>();
    VendorListAdapter mVendorListAdapter;
    TextView vTitle;
    ImageView vHome, vMessage, vSettings, mFilterLocation, vBack;
    LinearLayout mSubToolbar, mFilter;
    boolean isToggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);

        initView();
        ToolbarHelper.getInstance().intializeUi((Toolbar) findViewById(R.id.LHT_TB_toolbar), VendorActivity.this);
        ToolbarHelper.getInstance().toolBarVisiblity(VendorActivity.this, 0, (ImageView) findViewById(R.id.LHT_location), (ImageView) findViewById(R.id.LHT_IV_dot_line));
        ToolbarHelper.getInstance().setTitle("School name", 1);
        vSettings.setVisibility(View.GONE);
        vHome.setOnClickListener(this);
        vMessage.setOnClickListener(this);

        mVendorList = new ArrayList<>();
        for (int vendorcount = 0; vendorcount < 15; vendorcount++) {
            Vendor mVendor = new Vendor();
            mVendor.setName("Vendor " + String.valueOf(vendorcount));
            mVendor.setPlace("Coimbatore : " + String.valueOf(vendorcount));
            mVendor.setImage_url("");
            mVendorList.add(mVendor);
        }
        showVendorList();
       /* mFilterLocation.setVisibility(View.VISIBLE);
        mFilter.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        mFilterLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isToggle) {
                    mFilter.setVisibility(View.GONE);
                    isToggle = false;
                } else {
                    mFilter.setVisibility(View.VISIBLE);
                    isToggle = true;
                }

            }
        });*/
    }

    private void showVendorList() {
        mVendorListAdapter = new VendorListAdapter(VendorActivity.this, mVendorList);
        vVendor.setAdapter(mVendorListAdapter);
        vVendor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent inCategory = new Intent(VendorActivity.this, DetailsActivity.class);
                startActivity(inCategory);
            }
        });
    }

    private void initView() {
        vVendor = (ListView) findViewById(R.id.ASC_LV_vendor);
        vHome = (ImageView) findViewById(R.id.LFT_IV_home);
        vMessage = (ImageView) findViewById(R.id.LFT_IV_message);
        vSettings = (ImageView) findViewById(R.id.LFT_IV_settings);
//        mFilterLocation = (ImageView) findViewById(R.id.LHT_location);
        /*vTitle = (TextView) findViewById(R.id.LHT_IV_title_name);
        vBack = (ImageView) findViewById(R.id.LHT_IV_back);
        mSubToolbar = (LinearLayout) findViewById(R.id.LHT_LL_search_view);
        mFilter = (LinearLayout) findViewById(R.id.LFBA_LL_filter);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LFT_IV_home:
                ControllerClass.CallScreen(VendorActivity.this, ChooseLanguage.class);
                break;
            case R.id.LFT_IV_message:
                ControllerClass.CallScreen(VendorActivity.this, ContactUsActivity.class);
                break;
        }
    }


}
