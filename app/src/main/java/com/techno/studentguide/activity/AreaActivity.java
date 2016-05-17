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
import com.techno.studentguide.adapter.AreaListAdapter;
import com.techno.studentguide.model.Area;
import com.techno.studentguide.utils.ControllerClass;

import java.util.ArrayList;

public class AreaActivity extends AppCompatActivity implements View.OnClickListener {
    ListView vArea;
    ArrayList<Area> mAreaList = new ArrayList<>();
    AreaListAdapter mAreaListAdapter;

    ImageView vHome, vMessage, vSettings, mClickSearch;
    TextView mGetSearch;
    LinearLayout mSubHeaderToolbar, mSearchView;
    boolean isToggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

//        getSupportActionBar().hide();

        initView();
        vSettings.setVisibility(View.GONE);
        vHome.setOnClickListener(this);
        vMessage.setOnClickListener(this);

        /*To click to open search view method fires*/
        SearchClickFunction();


        ToolbarHelper.getInstance().intializeUi((Toolbar) findViewById(R.id.LHT_TB_toolbar), AreaActivity.this);


        mAreaList = new ArrayList<>();
        for (int areacount = 0; areacount < 15; areacount++) {
            Area areaModel = new Area();
            areaModel.setArea_id("1");
            areaModel.setArea_name_en("India");
            areaModel.setArea_name_ar("India_ar");
            mAreaList.add(areaModel);
        }

        showAreaList();
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
        mAreaListAdapter = new AreaListAdapter(AreaActivity.this, mAreaList);
        vArea.setAdapter(mAreaListAdapter);
        vArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent inCategory = new Intent(AreaActivity.this, CategoryActivity.class);
                startActivity(inCategory);
            }
        });
    }


    private class SearchItemListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
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

        switch (v.getId()) {
            case R.id.LFT_IV_home:
                ControllerClass.CallScreen(AreaActivity.this, ChooseLanguage.class);
                break;
            case R.id.LFT_IV_message:
                ControllerClass.CallScreen(AreaActivity.this, ContactUsActivity.class);
                break;
        }
    }
}
