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
import com.techno.studentguide.adapter.CategoryListAdapter;
import com.techno.studentguide.model.Category;
import com.techno.studentguide.utils.ControllerClass;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    ListView vCategory;
    ArrayList<Category> mCategoryList = new ArrayList<>();
    CategoryListAdapter mCategoryListAdapter;

    ImageView vHome, vMessage, vSettings, vBack, mClickSearch;
    TextView vTitleName;
    LinearLayout mSearchView;
    TextView mGetSearch;
    boolean isToggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

//        getSupportActionBar().hide();

        initView();

        ToolbarHelper.getInstance().intializeUi((Toolbar) findViewById(R.id.LHT_TB_toolbar), CategoryActivity.this);
        ToolbarHelper.getInstance().toolBarVisiblity(CategoryActivity.this, 1, (ImageView) findViewById(R.id.LHT_location), (ImageView) findViewById(R.id.LHT_IV_dot_line));
        ToolbarHelper.getInstance().setTitle("School name", 1);

        vSettings.setVisibility(View.GONE);
        vHome.setOnClickListener(this);
        vMessage.setOnClickListener(this);

        mCategoryList = new ArrayList<>();
        for (int categotycount = 0; categotycount < 15; categotycount++) {
            Category mCategory = new Category();
            mCategory.setCategory_id("Caregory " + String.valueOf(categotycount));
            mCategory.setCategory_created_datetime(" Created Time : " + String.valueOf(categotycount));
            mCategory.setCategory_name_ar("Category ar " + String.valueOf(categotycount));
            mCategory.setCategory_name_en("Category en " + String.valueOf(categotycount));
            mCategory.setParent_category_id("Caregory Parent " + String.valueOf(categotycount));
            mCategory.setCategory_vendors_filterable_by_area("Category Vendors filterable by area " + String.valueOf(categotycount));
            mCategory.setCategory_updated_datetime("Category Updated Time " + String.valueOf(categotycount));
            mCategoryList.add(mCategory);
        }

        showCategotyList();
/*        vBack.setVisibility(View.VISIBLE);
        vTitleName.setVisibility(View.VISIBLE);
        mSearchView.setVisibility(View.VISIBLE);*/

        /*To click to open search view method fires*/
//        SearchClickFunction();

    }

    private void showCategotyList() {
        mCategoryListAdapter = new CategoryListAdapter(CategoryActivity.this, mCategoryList);
        vCategory.setAdapter(mCategoryListAdapter);
        vCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent inCategory = new Intent(CategoryActivity.this, VendorActivity.class);
                startActivity(inCategory);
            }
        });
    }

    private void initView() {
        vCategory = (ListView) findViewById(R.id.AC_LV_category);
        vHome = (ImageView) findViewById(R.id.LFT_IV_home);
        vMessage = (ImageView) findViewById(R.id.LFT_IV_message);
        vSettings = (ImageView) findViewById(R.id.LFT_IV_settings);
        /*vBack = (ImageView) findViewById(R.id.LHT_IV_back);
        vTitleName = (TextView) findViewById(R.id.LHT_IV_title_name);
        mSearchView = (LinearLayout) findViewById(R.id.LHT_LL_search_view);
        mGetSearch = (TextView) findViewById(R.id.LHT_TV_search);
        mClickSearch = (ImageView) findViewById(R.id.LHT_IV_search);*/
    }

    private void SearchClickFunction() {
        mClickSearch.setOnClickListener(new SearchItemListener());
    }

    private class SearchItemListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (isToggle) {
                v.setBackgroundResource(R.drawable.ic_search_button);
                ToolbarHelper.getInstance().showSearchBox(false, (ImageView) findViewById(R.id.LHT_IV_dot_line), (ListView) findViewById(R.id.LHT_IV_dot_line));
                isToggle = false;
            } else {
                ToolbarHelper.getInstance().showSearchBox(true, (ImageView) findViewById(R.id.LHT_IV_dot_line), (ListView) findViewById(R.id.LHT_IV_dot_line));
                v.setBackgroundResource(R.drawable.ic_close);
                isToggle = true;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LFT_IV_home:
                ControllerClass.CallScreen(CategoryActivity.this, ChooseLanguage.class);
                break;
            case R.id.LFT_IV_message:
                ControllerClass.CallScreen(CategoryActivity.this, ContactUsActivity.class);
                break;
        }
    }
}
