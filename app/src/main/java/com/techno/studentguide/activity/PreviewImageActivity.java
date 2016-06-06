package com.techno.studentguide.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.techno.studentguide.R;
import com.techno.studentguide.adapter.CustomPagerAdapter;
import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.db.VendorPhoto;
import com.techno.studentguide.utils.LocalContactDB;

import java.util.List;

/**
 * Created by tech on 5/31/2016.
 */
public class PreviewImageActivity extends AppCompatActivity {
    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    List<VendorPhoto> galleryImages;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_gallery);
        galleryImages = LocalContactDB.GetVendorGallery(AppConfig.getVendor_id());;
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mToolbar = (Toolbar) findViewById(R.id.AC_TB_details_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        if (AppConfig.getLanguage_code().equalsIgnoreCase("en")) {
            mToolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        } else {
            mToolbar.setNavigationIcon(R.drawable.ic_arabic_back_arrow);
        }

        mCustomPagerAdapter = new CustomPagerAdapter(this, galleryImages);
        mViewPager.setAdapter(mCustomPagerAdapter);
        mViewPager.setCurrentItem(getIntent().getIntExtra("position", 0));


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
