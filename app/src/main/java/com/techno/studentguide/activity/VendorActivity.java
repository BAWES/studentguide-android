package com.techno.studentguide.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.techno.studentguide.R;
import com.techno.studentguide.adapter.FilterAdapter;
import com.techno.studentguide.adapter.VendorListAdapter;
import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.customview.CustomTextView;
import com.techno.studentguide.db.SQLConfig;
import com.techno.studentguide.db.Vendor;
import com.techno.studentguide.db.VendorCategoryLink;
import com.techno.studentguide.db.VendorCategoryLinkDao;
import com.techno.studentguide.utils.ControllerClass;
import com.techno.studentguide.utils.FilterRefresh;
import com.techno.studentguide.utils.LocalContactDB;
import com.techno.studentguide.utils.NoDataFoundInterface;
import com.techno.studentguide.utils.VendorListInterface;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import de.greenrobot.event.EventBus;

public class VendorActivity extends AppCompatActivity implements View.OnClickListener {

    ListView vVendor;
    List<Vendor> mVendorList = new ArrayList<Vendor>();
    VendorListAdapter mVendorListAdapter;
    CustomTextView vNoDataFound;
    ImageView vHome, vMessage, vSettings, mFilterLocation, mClickSearch;
    LinearLayout mFilterLayout;
    RelativeLayout mVendorlistLayout;
    private EventBus bus = EventBus.getDefault();
    List<Vendor> myVendor = new ArrayList<>();
    FrameLayout mfooter_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        // Get the layout from activity_vendor.xml
        setContentView(R.layout.activity_vendor);
        // Find your IDS in your activity_vendor.xml layout
        initView();


        // Toolbar event initiate here
        ToolbarHelper.getInstance().intializeUi((Toolbar) findViewById(R.id.LHT_TB_toolbar), VendorActivity.this);
        ToolbarHelper.getInstance().toolBarVisiblity(VendorActivity.this, 0, (ImageView) findViewById(R.id.LHT_location), (ImageView) findViewById(R.id.LHT_IV_dot_line));
        ToolbarHelper.getInstance().setTitle(AppConfig.getCategory_name(), 1);
        if (AppConfig.getLanguage_code().equalsIgnoreCase("en")) {
            ToolbarHelper.getInstance().getToolBar().setNavigationIcon(R.drawable.ic_back_arrow);
        } else {
            ToolbarHelper.getInstance().getToolBar().setNavigationIcon(R.drawable.right_arrow);
        }
        vSettings.setVisibility(View.GONE);
        // Button click event triggered for home and message
        vHome.setOnClickListener(this);
        vMessage.setOnClickListener(this);
        // To show vendor list items
        showVendorList();
        SearchMethod();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void showVendorList() {
        List<String> mVendorId = new ArrayList<String>();
        List<VendorCategoryLink> mVendorCategoryLink = SQLConfig.mVendorCategoryLinkDao.queryBuilder().where(VendorCategoryLinkDao.Properties.Category_id.eq(AppConfig.getCategory_id())).list();
        if (mVendorCategoryLink != null) {
            for (int vendorid = 0; vendorid < mVendorCategoryLink.size(); vendorid++) {
                mVendorId.add(mVendorCategoryLink.get(vendorid).getVendor_id());
            }
            if (mVendorId != null) {
                AppConfig.setmVendorList(mVendorId);
                mVendorList = LocalContactDB.GetVendor(AppConfig.getmVendorList());
                if (mVendorList.size() > 0) {
                    vNoDataFound.setVisibility(View.GONE);
                    mVendorlistLayout.setBackgroundColor(R.color.clr_white);
                    for (int i = 0; i < mVendorList.size(); i++) {
                        Vendor vendor = new Vendor();
                        String mStartDate = mVendorList.get(i).getVendor_account_start_date();
                        String mEndDate = mVendorList.get(i).getVendor_account_end_date();
                        getDate(mStartDate);
                        getDate(mEndDate);
                        getDate(getCurrentDate());
                        long millStart = getDate(mStartDate).getTime();
                        long millEnd = getDate(mEndDate).getTime();
                        long millCurrent = getDate(getCurrentDate()).getTime();
                        if (millCurrent >= millStart && millCurrent <= millEnd) {
                            vendor.setVendor_id(mVendorList.get(i).getVendor_id());
                            vendor.setVendor_logo(mVendorList.get(i).getVendor_logo());
                            vendor.setVendor_name_en(mVendorList.get(i).getVendor_name_en());
                            vendor.setVendor_name_ar(mVendorList.get(i).getVendor_name_ar());
                            vendor.setVendor_description_en(mVendorList.get(i).getVendor_description_en());
                            vendor.setVendor_description_ar(mVendorList.get(i).getVendor_description_ar());
                            vendor.setVendor_phone1(mVendorList.get(i).getVendor_phone1());
                            vendor.setVendor_phone2(mVendorList.get(i).getVendor_phone2());
                            vendor.setVendor_youtube_video(mVendorList.get(i).getVendor_youtube_video());
                            vendor.setVendor_social_instagram(mVendorList.get(i).getVendor_social_instagram());
                            vendor.setVendor_social_twitter(mVendorList.get(i).getVendor_social_twitter());
                            vendor.setVendor_location(mVendorList.get(i).getVendor_location());
                            vendor.setVendor_address_text_en(mVendorList.get(i).getVendor_address_text_en());
                            vendor.setVendor_address_text_ar(mVendorList.get(i).getVendor_address_text_ar());
                            vendor.setVendor_account_start_date(mVendorList.get(i).getVendor_account_start_date());
                            vendor.setVendor_account_end_date(mVendorList.get(i).getVendor_account_end_date());
                            vendor.setSort_order(mVendorList.get(i).getSort_order());
                            myVendor.add(vendor);
                        }
                    }
                    if (myVendor.size() > 0) {
                        /*if (AppConfig.getLanguage_code().equalsIgnoreCase("en")) {
                            Collections.sort(myVendor, new Comparator<Vendor>() {
                                @Override
                                public int compare(Vendor lhs, Vendor rhs) {
                                    return lhs.getVendor_name_en().compareToIgnoreCase(rhs.getVendor_name_en());
                                }
                            });
                        } else {
                            Collections.sort(myVendor, new Comparator<Vendor>() {
                                @Override
                                public int compare(Vendor lhs, Vendor rhs) {
                                    return lhs.getVendor_name_ar().compareToIgnoreCase(rhs.getVendor_name_ar());
                                }
                            });
                        }
*/

                        mVendorListAdapter = new VendorListAdapter(VendorActivity.this, bus, myVendor);
                        vVendor.setAdapter(mVendorListAdapter);

                    } else {
                        bus.post(new NoDataFoundInterface(myVendor.size()));
                        ToolbarHelper.getInstance().toolBarVisiblity(VendorActivity.this, 1, (ImageView) findViewById(R.id.LHT_location), (ImageView) findViewById(R.id.LHT_IV_dot_line));
                    }

                } else {
                    vNoDataFound.setVisibility(View.VISIBLE);
                    vNoDataFound.setText(R.string.txt_title_no_data_found);
                    vVendor.setBackgroundResource(R.drawable.bg_category_lists);
                    ToolbarHelper.getInstance().toolBarVisiblity(VendorActivity.this, 1, (ImageView) findViewById(R.id.LHT_location), (ImageView) findViewById(R.id.LHT_IV_dot_line));
                }

                vVendor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AppConfig.setVendor_id(myVendor.get(position).getVendor_id());
                        AppConfig.setVendor_name(AppConfig.getLanguage_code().equalsIgnoreCase("en")
                                ? myVendor.get(position).getVendor_name_en() : myVendor.get(position).getVendor_name_ar());
                        Intent inCategory = new Intent(VendorActivity.this, DetailsActivity.class);
                        startActivity(inCategory);
                    }
                });
            }

        }
    }

    private Date getStringtoDate(String date) {
        String startDateString = date;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = df.parse(startDateString);
            String newDateString = df.format(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return startDate;
    }

    private Date getDate(String mStartDate) {
        String startDateString = mStartDate;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = df.parse(startDateString);
            String newDateString = df.format(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    private void initView() {
        vVendor = (ListView) findViewById(R.id.ASC_LV_vendor);
        vHome = (ImageView) findViewById(R.id.LFT_IV_home);
        vMessage = (ImageView) findViewById(R.id.LFT_IV_message);
        vSettings = (ImageView) findViewById(R.id.LFT_IV_settings);
        mFilterLocation = (ImageView) findViewById(R.id.LHT_location);
        mFilterLayout = (LinearLayout) findViewById(R.id.filter_layout);
        mClickSearch = (ImageView) findViewById(R.id.LHT_IV_search);
        vNoDataFound = (CustomTextView) findViewById(R.id.AV_TV_no_data_found);
        mVendorlistLayout = (RelativeLayout) findViewById(R.id.AV_RL_parent);
        if (AppConfig.getLanguage_code().equalsIgnoreCase("en")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                mFilterLayout.setGravity(Gravity.RIGHT);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                mFilterLayout.setGravity(Gravity.LEFT);
            }
        }

    }

    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        String strDate = "" + mdformat.format(calendar.getTime());
        return strDate;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LFT_IV_home:
                ControllerClass.CallHome(VendorActivity.this, ChooseLanguage.class);
                finish();
                break;
            case R.id.LFT_IV_message:
                ControllerClass.CallContactus(VendorActivity.this, ContactUsActivity.class);
                break;
        }
    }

    private void SearchMethod() {
        mClickSearch.setOnClickListener(new SearchItemListener());
    }


    // search click listener
    private class SearchItemListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(VendorActivity.this, SearchActivity.class));
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

    // search filter event
    public void onEvent(final FilterRefresh ev) {
        runOnUiThread(new Runnable() {
            public void run() {
                List<Vendor> mVendorFilterArea = new ArrayList<Vendor>();
                int sizeValue = myVendor.size();
                Set<String> duplicates = new LinkedHashSet<>(FilterAdapter.vendorId);
                int sizeduplicatesValue = duplicates.size();
                mVendorFilterArea.clear();
                /*if (duplicates.size() == 0) {
                    bus.post(new VendorListInterface());
                } else {*/
                for (int i = 0; i < sizeValue; i++) {
                    for (String s : duplicates) {
                        if (myVendor.get(i).getVendor_id().equalsIgnoreCase(s)) {
                            Vendor vendorlistsinsert = new Vendor();
                            vendorlistsinsert.setVendor_id(myVendor.get(i).getVendor_id());
                            vendorlistsinsert.setVendor_logo(myVendor.get(i).getVendor_logo());
                            vendorlistsinsert.setVendor_name_en(myVendor.get(i).getVendor_name_en());
                            vendorlistsinsert.setVendor_name_ar(myVendor.get(i).getVendor_name_ar());
                            vendorlistsinsert.setVendor_description_en(myVendor.get(i).getVendor_description_en());
                            vendorlistsinsert.setVendor_description_ar(myVendor.get(i).getVendor_description_ar());
                            vendorlistsinsert.setVendor_phone1(myVendor.get(i).getVendor_phone1());
                            vendorlistsinsert.setVendor_phone2(myVendor.get(i).getVendor_phone2());
                            vendorlistsinsert.setVendor_youtube_video(myVendor.get(i).getVendor_youtube_video());
                            vendorlistsinsert.setVendor_social_instagram(myVendor.get(i).getVendor_social_instagram());
                            vendorlistsinsert.setVendor_social_twitter(myVendor.get(i).getVendor_social_twitter());
                            vendorlistsinsert.setVendor_location(myVendor.get(i).getVendor_location());
                            vendorlistsinsert.setVendor_address_text_en(myVendor.get(i).getVendor_address_text_en());
                            vendorlistsinsert.setVendor_address_text_ar(myVendor.get(i).getVendor_address_text_ar());
                            vendorlistsinsert.setVendor_account_start_date(myVendor.get(i).getVendor_account_start_date());
                            vendorlistsinsert.setVendor_account_end_date(myVendor.get(i).getVendor_account_end_date());
                            vendorlistsinsert.setSort_order(myVendor.get(i).getSort_order());
                            mVendorFilterArea.add(vendorlistsinsert);
                        }
                    }
                    mVendorListAdapter = new VendorListAdapter(VendorActivity.this, bus, mVendorFilterArea);
                    vVendor.setAdapter(mVendorListAdapter);
                    mVendorListAdapter.notifyChanges();
//                    }
                }
            }
        });
    }

    // reset filter event
    public void onEvent(final VendorListInterface ev) {
        runOnUiThread(new Runnable() {
            public void run() {
//                mVendorList = LocalContactDB.GetVendor(AppConfig.getmVendorList());
                mVendorListAdapter = new VendorListAdapter(VendorActivity.this, bus, myVendor);
                vVendor.setAdapter(mVendorListAdapter);
                mVendorListAdapter.notifyChanges();
            }
        });
    }

    // reset filter event
    public void onEvent(final NoDataFoundInterface ev) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (ev.getSize() == 0) {
                    vNoDataFound.setVisibility(View.VISIBLE);
                    vNoDataFound.setText(R.string.txt_title_no_data_found);
                    vVendor.setBackgroundResource(R.drawable.bg_category_lists);
                } else {
                    vNoDataFound.setVisibility(View.GONE);
                    mVendorlistLayout.setBackgroundColor(R.color.clr_white);
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
