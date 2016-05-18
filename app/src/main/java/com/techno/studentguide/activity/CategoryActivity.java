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
import com.techno.studentguide.model.Area;
import com.techno.studentguide.model.Search;
import com.techno.studentguide.utils.ControllerClass;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    ListView vArea;
    ArrayList<Area> mAreaList = new ArrayList<>();
    CategoryListAdapter mAreaListAdapter;

    ImageView vHome, vMessage, vSettings, mClickSearch;
    TextView mGetSearch;
    LinearLayout mSubHeaderToolbar, mSearchView;
    boolean isToggle = false;
    private static final String[] COUNTRIES = new String[]{"Belgium",
            "France", "France_", "Italy", "Germany", "Spain"};

    ArrayList<Search> mListSearch;

    Search mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from activity_area.xml
        setContentView(R.layout.activity_area);
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



        // Loaded dummy arraylist for category and subcategory
        mAreaList = new ArrayList<>();
        for (int areacount = 0; areacount < 15; areacount++) {
            Area areaModel = new Area();
            areaModel.setArea_id("1");
            areaModel.setArea_name_en("India");
            areaModel.setArea_name_ar("India_ar");
            mAreaList.add(areaModel);
        }
        //To show category list
        showAreaList();


//        Dummy data to load search
        /*mListSearch = new ArrayList<Search>();
        for (int i = 0; i < COUNTRIES.length; i++) {
            Search mSearch = new Search(COUNTRIES[i]);
            mListSearch.add(mSearch);
        }*/
        // Toolbar with search view event triggered.
        ToolbarHelper.getInstance().searchBoxEvent(COUNTRIES);

    }

    private void SearchClickFunction() {
        mClickSearch.setOnClickListener(new SearchItemListener());
    }

    private void initView() {
        vArea = (ListView) findViewById(R.id.AA_LV_area);
        vHome = (ImageView) findViewById(R.id.LFT_IV_home);
        vMessage = (ImageView) findViewById(R.id.LFT_IV_message);
        vSettings = (ImageView) findViewById(R.id.LFT_IV_settings);
        mClickSearch = (ImageView) findViewById(R.id.LHT_IV_search);
        /*mSubHeaderToolbar = (LinearLayout) findViewById(R.id.LHT_LL_search);
        mSearchView = (LinearLayout) findViewById(R.id.LHT_LL_search_view);
        mGetSearch = (TextView) findViewById(R.id.LHT_TV_search);
*/
    }


    private void showAreaList() {
        mAreaListAdapter = new CategoryListAdapter(CategoryActivity.this, mAreaList);
        vArea.setAdapter(mAreaListAdapter);
        vArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //To call subCategory class based on list item click event fires.
                Intent inCategory = new Intent(CategoryActivity.this, SubCategoryActivity.class);
                startActivity(inCategory);
                finish();
            }
        });
    }


    private class SearchItemListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //Toggle event below
            if (isToggle) {
                v.setBackgroundResource(R.drawable.ic_search_button);
                isToggle = false;
                ToolbarHelper.getInstance().showSearchBox(false, (ImageView) findViewById(R.id.LHT_IV_dot_line), (ListView) findViewById(R.id.LHT_LV_lists));
            } else {
                ToolbarHelper.getInstance().showSearchBox(true, (ImageView) findViewById(R.id.LHT_IV_dot_line), (ListView) findViewById(R.id.LHT_LV_lists));
                v.setBackgroundResource(R.drawable.ic_close);
                isToggle = true;
            }
        }
    }

    @Override
    public void onClick(View v) {
        //Handle click event for home and message button bottom toolbar
        switch (v.getId()) {
            case R.id.LFT_IV_home:
                //Controller class handles another class name to call a class.
                ControllerClass.CallScreen(CategoryActivity.this, ChooseLanguage.class);
                break;
            case R.id.LFT_IV_message:
                //Controller class handles another class name to call a class.
                ControllerClass.CallScreen(CategoryActivity.this, ContactUsActivity.class);
                break;
        }
    }
}
