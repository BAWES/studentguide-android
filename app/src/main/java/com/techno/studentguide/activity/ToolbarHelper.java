package com.techno.studentguide.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.techno.studentguide.R;

/**
 * Created by Android on 5/13/2016.
 * <p/>
 * This class handles toolbar event for each class have in these application
 */
public class ToolbarHelper {


    Context mContext;
    android.support.v7.widget.Toolbar toolbar;
    private static ToolbarHelper ourInstance = new ToolbarHelper();
    public final int ALIGN_LEFT = 0;
    public final int ALIGN_CENTER = 1;
    public final int ALIGN_RIGHT = 2;

    public final int SHOW_ON = 0;
    public final int SHOW_OFF = 1;

    public static ToolbarHelper getInstance() {
        return ourInstance;
    }

    private ToolbarHelper() {
    }

    //    Toolbar view intialize view method
    public void intializeUi(android.support.v7.widget.Toolbar toolbar, Context mContext) {
        this.toolbar = toolbar;
        boolean isIconAvailable = true;
        this.mContext = mContext;
        ((AppCompatActivity) mContext).setSupportActionBar(toolbar);
        ((AppCompatActivity) mContext).getSupportActionBar().setDisplayHomeAsUpEnabled(isIconAvailable);
        ((AppCompatActivity) mContext).getSupportActionBar().setHomeButtonEnabled(isIconAvailable);
    }

    //    set title in toolbar
    public void setTitle(String mTitle, int AlignTitle) {
        switch (AlignTitle) {
            case ALIGN_CENTER:
                toolbar.findViewById(R.id.LHT_TV_title).setVisibility(View.VISIBLE);
                ((TextView) toolbar.findViewById(R.id.LHT_TV_title)).setText(mTitle);
                break;
        }
    }

    //    Toolbar visibility event
    public void toolBarVisiblity(final Context mContext, int show_filter, ImageView FilterId, ImageView dotLine) {
        this.mContext = mContext;
        switch (show_filter) {
            //    Area list filter function
            case SHOW_ON:
                toolbar.findViewById(R.id.LHT_TB_toolbar).setVisibility(View.VISIBLE);
                dotLine.setVisibility(View.GONE);
                FilterId.setVisibility(View.VISIBLE);
                FilterId.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final PopupWindow mDialog = new PopupWindow(mContext);
                        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View layout = inflater.inflate(R.layout.layout_filter_by_area, null);
                        LinearLayout mParentView = (LinearLayout) layout.findViewById(R.id.LFBA_LL_filter);
                        ImageView mCloseFilter = (ImageView) layout.findViewById(R.id.LFBA_IV_close_filter);
                        mParentView.setVisibility(View.VISIBLE);
                        mDialog.setContentView(layout);
                        mDialog.setOutsideTouchable(false);
                        mDialog.showAtLocation(layout, Gravity.RIGHT, 0, 0);
                        mCloseFilter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDialog.dismiss();
                            }
                        });
                    }
                });

                break;
            case SHOW_OFF:
                toolbar.findViewById(R.id.LHT_TB_toolbar).setVisibility(View.VISIBLE);
                FilterId.setVisibility(View.GONE);
                dotLine.setVisibility(View.GONE);
                break;
        }

    }

    //    search box shows while click event search
    public void showSearchBox(boolean isVisible, ImageView dot_line, ListView searchLists) {
        if (isVisible) {
            toolbar.findViewById(R.id.LHT_TB_toolbar).setVisibility(View.VISIBLE);
            toolbar.findViewById(R.id.LHT_ACT_search_view).setVisibility(View.VISIBLE);
            searchLists.setVisibility(View.VISIBLE);
            dot_line.setVisibility(View.GONE);
            toolbar.findViewById(R.id.LHT_TV_title).setVisibility(View.GONE);
            ((AppCompatActivity) mContext).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) mContext).getSupportActionBar().setHomeButtonEnabled(false);
        } else {
            toolbar.findViewById(R.id.LHT_TB_toolbar).setVisibility(View.GONE);
            toolbar.findViewById(R.id.LHT_ACT_search_view).setVisibility(View.GONE);
            toolbar.findViewById(R.id.LHT_TV_title).setVisibility(View.VISIBLE);
            searchLists.setVisibility(View.GONE);
            dot_line.setVisibility(View.VISIBLE);
            ((AppCompatActivity) mContext).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) mContext).getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    //    Auto search listview data populates
    public void searchBoxEvent(String[] mCountries) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_dropdown_item_1line, mCountries);
        AutoCompleteTextView searchCategoryLists = (AutoCompleteTextView) toolbar.findViewById(R.id.LHT_ACT_search_view);
        searchCategoryLists.setAdapter(adapter);
    }
}
