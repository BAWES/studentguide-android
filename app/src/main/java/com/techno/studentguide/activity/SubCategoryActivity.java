package com.techno.studentguide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.techno.studentguide.R;
import com.techno.studentguide.adapter.SubCategoryListAdapter;
import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.db.Category;
import com.techno.studentguide.db.CategoryDao;
import com.techno.studentguide.db.SQLConfig;
import com.techno.studentguide.utils.ControllerClass;
import com.techno.studentguide.utils.LocalContactDB;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

public class SubCategoryActivity extends AppCompatActivity implements View.OnClickListener {
    //Variable declarations based on views used.
    ListView vSubCategory;
    List<Category> mSubCategoryList = new ArrayList<Category>();
    SubCategoryListAdapter mSubCategoryListAdapter;
    ImageView vHome, vMessage, vSettings, mClickSearch;
    List<String> ids = new ArrayList<>();
    public static int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from activity_category.xml
        setContentView(R.layout.activity_subcategory);
        // Find your IDS in your activity_category.xml layout
        initView();
        // Toolbar event initiate here
        ToolbarHelper.getInstance().intializeUi((Toolbar) findViewById(R.id.LHT_TB_toolbar), SubCategoryActivity.this);
        ToolbarHelper.getInstance().toolBarVisiblity(SubCategoryActivity.this, 1, (ImageView) findViewById(R.id.LHT_location), (ImageView) findViewById(R.id.LHT_IV_dot_line));
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
        // Sub category list items
        showCategotyList();
        /*To click to open search view method fires*/
        SearchClickFunction();

    }


    private void showCategotyList() {  // showCategotyList() method fires to show category items
        mSubCategoryList = LocalContactDB.GetSubCategory(AppConfig.getCategory_id()); // getData from localDB
        mSubCategoryListAdapter = new SubCategoryListAdapter(SubCategoryActivity.this, mSubCategoryList);// binding data to listview
        vSubCategory.setAdapter(mSubCategoryListAdapter);//setAdapter to listview
        vSubCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {  //OnItemClickListener() method fires to click list items
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;  // Assign selected item position to selectedPosistion variable
                mSubCategoryListAdapter.setSelectedIndex(selectedPosition);  // setMethod handles last item selected in the list items
                mSubCategoryListAdapter.notifyDataSetChanged();  // refresh listviews

                // AppConfig class handles get and set method to all category ids,sub category ids, vendor ids.
                AppConfig.setCategory_name(AppConfig.getLanguage_code().equalsIgnoreCase("en") ? mSubCategoryList.get(position).getCategory_name_en() : mSubCategoryList.get(position).getCategory_name_ar());
                AppConfig.setCategory_id(mSubCategoryList.get(position).getCategory_id());
                AppConfig.setParent_category_id(mSubCategoryList.get(position).getParent_category_id());

                // This query checks in the db tables to get subcategory items based on category id with where condition
                // if category id have subcategory or vendor query.
                Category mSubCategory = SQLConfig.mCategoryDao.queryBuilder().where(CategoryDao.Properties.Parent_category_id.eq(mSubCategoryList.get(position).getCategory_id())).limit(1).unique();
                // if subcategory is null its category has no child subcategory its redirects to vendor page and if subcategory is not null, this category has child.
                if (mSubCategory != null) {
                    Intent inCategory = new Intent(SubCategoryActivity.this, SubCategoryActivity.class);  // call subcategory page
                    startActivity(inCategory);
                    CategoryActivity.searchClicked = false;   // search boolean based on invisible and visible
                } else {
                    Intent inCategory = new Intent(SubCategoryActivity.this, VendorActivity.class);   // call vendor page
                    startActivity(inCategory);
                    CategoryActivity.searchClicked = false;
                }
            }
        });
    }

    private void initView() {
        vSubCategory = (ListView) findViewById(R.id.AC_LV_category);
        vHome = (ImageView) findViewById(R.id.LFT_IV_home);
        vMessage = (ImageView) findViewById(R.id.LFT_IV_message);
        vSettings = (ImageView) findViewById(R.id.LFT_IV_settings);
        mClickSearch = (ImageView) findViewById(R.id.LHT_IV_search);
        mSubCategoryListAdapter.setSelectedIndex(-1);
    }

    private void SearchClickFunction() {
        // search on click interface
        mClickSearch.setOnClickListener(new SearchItemListener());
    }

    private class SearchItemListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (AppConfig.getCategory_id() != null) {   // if category based on search function
                ids.clear();
                String searchCategoryId = AppConfig.getCategory_id();
                ids.add(searchCategoryId);
                GetCategoryIds(searchCategoryId);
                List<String> id = ids;
                LocalContactDB.GetVendor(id);   // get vendor list from local
                startActivity(new Intent(SubCategoryActivity.this, SearchActivity.class));  // call search page
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);  // animation fade in and fade out
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LFT_IV_home:
                ControllerClass.CallHome(SubCategoryActivity.this, ChooseLanguage.class);  // call language page
                finish();
                break;
            case R.id.LFT_IV_message:
                ControllerClass.CallContactus(SubCategoryActivity.this, ContactUsActivity.class);    // call contact us page
                break;
        }
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
        finish();
    }

    public void GetCategoryIds(String categoryId) {  // get vendors ids based on search
        QueryBuilder<Category> qb = SQLConfig.mCategoryDao.queryBuilder();
        List<Category> categories = qb.where(CategoryDao.Properties.Parent_category_id.eq(categoryId)).list();
        if (categories.size() <= 0) {
            return;
        } else {
            for (Category category : categories) {
                if (!ids.contains(category.getCategory_id().toString()) == true) {
                    ids.add(category.getCategory_id().toString());
                    GetCategoryIds(category.getCategory_id());
                }

            }
        }
    }

}
