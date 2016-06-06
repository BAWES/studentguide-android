package com.techno.studentguide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.techno.studentguide.R;
import com.techno.studentguide.adapter.SearchAdapter;
import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.customview.CustomEditText;
import com.techno.studentguide.db.Vendor;
import com.techno.studentguide.utils.ControllerClass;
import com.techno.studentguide.utils.LocalContactDB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by tech on 5/25/2016.
 */
public class SearchActivity extends AppCompatActivity {
    ImageView mCloseSearch, vHome, vMessage;
    ListView mSearchView;
    SearchAdapter aSearchAdapter;
    List<Vendor> mSearchlists = new ArrayList<Vendor>();
    CustomEditText mEditSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        vHome = (ImageView) findViewById(R.id.LFT_IV_home);
        vMessage = (ImageView) findViewById(R.id.LFT_IV_message);
        mCloseSearch = (ImageView) findViewById(R.id.AS_IV_search);
        mSearchView = (ListView) findViewById(R.id.AS_LV_search);
        mEditSearch = (CustomEditText) findViewById(R.id.AS_ET_search_view);

        vHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerClass.CallHome(SearchActivity.this, ChooseLanguage.class);
                //Set the transition -> method available from Android 2.0 and beyond
                overridePendingTransition(R.anim.pull_up_from_bottom, R.anim.push_out_to_bottom);
            }
        });

        vMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerClass.CallContactus(SearchActivity.this, ContactUsActivity.class);
                //Set the transition -> method available from Android 2.0 and beyond
                overridePendingTransition(R.anim.pull_up_from_bottom, R.anim.push_out_to_bottom);
            }
        });

        if (CategoryActivity.searchClicked == true) {
            if (AppConfig.getCategory_id() == null) {
                mSearchlists = LocalContactDB.GetVendorList();


                if (AppConfig.getLanguage_code().equalsIgnoreCase("en")) {
                    Collections.sort(mSearchlists, new Comparator<Vendor>() {
                        @Override
                        public int compare(Vendor lhs, Vendor rhs) {
                            return lhs.getVendor_name_en().compareToIgnoreCase(rhs.getVendor_name_en());
                        }
                    });
                } else {
                    Collections.sort(mSearchlists, new Comparator<Vendor>() {
                        @Override
                        public int compare(Vendor lhs, Vendor rhs) {
                            return lhs.getVendor_name_ar().compareToIgnoreCase(rhs.getVendor_name_ar());
                        }
                    });
                }
                aSearchAdapter = new SearchAdapter(SearchActivity.this, mSearchlists);
                mSearchView.setAdapter(aSearchAdapter);
                mSearchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AppConfig.setVendor_id(mSearchlists.get(position).getVendor_id());
                        AppConfig.setVendor_name(AppConfig.getLanguage_code().equalsIgnoreCase("en") ? mSearchlists.get(position).getVendor_name_en() :
                                mSearchlists.get(position).getVendor_name_ar());
                        startActivity(new Intent(SearchActivity.this, DetailsActivity.class));
                        //Set the transition -> method available from Android 2.0 and beyond
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    }
                });


                mEditSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        aSearchAdapter.getFilter().filter(s);
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        aSearchAdapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        aSearchAdapter.getFilter().filter(s);
                    }
                });
            }
        } else {
            mSearchlists = LocalContactDB.GetVendorList();
            aSearchAdapter = new SearchAdapter(SearchActivity.this, mSearchlists);
            mSearchView.setAdapter(aSearchAdapter);
            aSearchAdapter.notifyDataSetChanged();
            mSearchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AppConfig.setVendor_id(mSearchlists.get(position).getVendor_id());
                    AppConfig.setVendor_name(AppConfig.getLanguage_code().equalsIgnoreCase("en")
                            ? mSearchlists.get(position).getVendor_name_en() : mSearchlists.get(position).getVendor_name_ar());
                    Intent inCategory = new Intent(SearchActivity.this, DetailsActivity.class);
                    startActivity(inCategory);
                    //Set the transition -> method available from Android 2.0 and beyond
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }
            });
            mEditSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    aSearchAdapter.getFilter().filter(s);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    aSearchAdapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    aSearchAdapter.getFilter().filter(s);
                }
            });
        }


        mCloseSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Set the transition -> method available from Android 2.0 and beyond
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
