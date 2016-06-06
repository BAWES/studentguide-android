package com.techno.studentguide.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.techno.studentguide.R;
import com.techno.studentguide.adapter.CategoryAdapter;
import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.db.Category;
import com.techno.studentguide.db.CategoryDao;
import com.techno.studentguide.db.SQLConfig;
import com.techno.studentguide.utils.ControllerClass;
import com.techno.studentguide.utils.LocalContactDB;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    ListView vCategoryLists;   // listview variable name
    List<Category> mCategoryLists = new ArrayList<Category>();   // category arraylist variable name
    CategoryAdapter mCategoryAdapter; // Adapter variable name
    ImageView vHome, vMessage, vSettings, mClickSearch;    // imageview variable name
    public static boolean searchClicked = false;   // boolean iconclicked variable name
    public static int selectedPosition = -1;  // listview selected position variable name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from activity_area.xml
        setContentView(R.layout.activity_category_layout);
        // Find your IDS in your activity_area.xml layout
        initView();
        vSettings.setVisibility(View.GONE);
        // Footer layout Home and message icon Click event triggered.
        vHome.setOnClickListener(this);
        vMessage.setOnClickListener(this);
        //To handle search icon as toggle mode
        SearchClickFunction();
        // Toolbar view class initiates
        ToolbarHelper.getInstance().intializeUi((Toolbar) findViewById(R.id.LHT_TB_toolbar), CategoryActivity.this);
        // Loaded category
        showCategoryList();
        searchClicked = true;
        AppConfig.setCategory_id(null);
    }

    // search click event
    private void SearchClickFunction() {
        // search on click interface
        mClickSearch.setOnClickListener(new SearchItemListener());
    }

    private void initView() {
        vCategoryLists = (ListView) findViewById(R.id.AA_LV_area);    // Listview
        vHome = (ImageView) findViewById(R.id.LFT_IV_home);   // Imageview
        vMessage = (ImageView) findViewById(R.id.LFT_IV_message); // Imageview
        vSettings = (ImageView) findViewById(R.id.LFT_IV_settings);  // Imageview
        mClickSearch = (ImageView) findViewById(R.id.LHT_IV_search); // Imageview
        mCategoryAdapter.setSelectedIndex(-1);
        if (AppConfig.getLanguage_code().equalsIgnoreCase("en")) {   // check language code equals "en" or "ar"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //searchClicked boolean visible and invisible
        searchClicked = true;
        AppConfig.setCategory_id(null);
    }

    private void showCategoryList() {// showCategotyList() method fires to show category items
        mCategoryLists = LocalContactDB.GetCategory();// getData from localDB
        mCategoryAdapter = new CategoryAdapter(CategoryActivity.this, mCategoryLists);// binding data to listview
        vCategoryLists.setAdapter(mCategoryAdapter);//setAdapter to listview
        vCategoryLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {//OnItemClickListener() method fires to click list items
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;// Assign selected item position to selectedPosistion variable
                mCategoryAdapter.setSelectedIndex(selectedPosition);// setMethod handles last item selected in the list items
                mCategoryAdapter.notifyDataSetChanged();// refresh listviews

                // AppConfig class handles get and set method to all category ids,sub category ids, vendor ids.
                AppConfig.setCategory_id(mCategoryLists.get(position).getCategory_id());
                AppConfig.setParent_category_id(mCategoryLists.get(position).getParent_category_id());
                AppConfig.setCategory_name(AppConfig.getLanguage_code().equalsIgnoreCase("en") ? mCategoryLists.get(position).getCategory_name_en() : mCategoryLists.get(position).getCategory_name_ar());

                // This query checks in the db tables to get subcategory items based on category id with where condition
                // if category id have subcategory or vendor query.
                Category mCategory = SQLConfig.mCategoryDao.queryBuilder().where(CategoryDao.Properties.Parent_category_id.eq(AppConfig.getCategory_id())).limit(1).unique();
                // if subcategory is null its category has no child subcategory its redirects to vendor page and if subcategory is not null, this category has child.
                if (mCategory != null) {
                    searchClicked = false;
                    Intent inCategory = new Intent(CategoryActivity.this, SubCategoryActivity.class);   // call subcategory page
                    startActivity(inCategory);
                } else {
                    searchClicked = false;
                    Intent inCategory = new Intent(CategoryActivity.this, VendorActivity.class);    // call vendor page
                    startActivity(inCategory);
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // native onBack pressed
    }

    private class SearchItemListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //Toggle event below
            startActivity(new Intent(CategoryActivity.this, SearchActivity.class));  // call search page
        }
    }

    @Override
    public void onClick(View v) {
        //Handle click event for home and message button bottom toolbar
        switch (v.getId()) {
            case R.id.LFT_IV_home:
                //Controller class handles another class name to call a class.
                ControllerClass.CallHome(CategoryActivity.this, ChooseLanguage.class);
                //Set the transition -> method available from Android 2.0 and beyond
                overridePendingTransition(R.anim.pull_up_from_bottom, R.anim.push_out_to_bottom);  // animation to open activity pull up
                finish();
                break;
            case R.id.LFT_IV_message:
                //Controller class handles another class name to call a class.
                ControllerClass.CallContactus(CategoryActivity.this, ContactUsActivity.class);
                //Set the transition -> method available from Android 2.0 and beyond
                overridePendingTransition(R.anim.pull_up_from_bottom, R.anim.push_out_to_bottom); // animation to open activity pull up
                break;
        }
    }


}
