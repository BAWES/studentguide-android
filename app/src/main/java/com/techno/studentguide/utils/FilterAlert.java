package com.techno.studentguide.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.techno.studentguide.R;
import com.techno.studentguide.adapter.FilterAdapter;
import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.customview.CustomTextView;
import com.techno.studentguide.db.SQLConfig;
import com.techno.studentguide.db.VendorAreaLink;
import com.techno.studentguide.db.VendorAreaLinkDao;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by tech on 7/28/2015.
 */
public class FilterAlert implements View.OnClickListener {
    Context mContext;
    Dialog mDialog;
    ListView mListView;
    private EventBus bus = EventBus.getDefault();
    FilterAdapter mFilterAdapter;
    CustomTextView mResetFilter, mFilterText, mApplyFilter;

    public FilterAlert(Context mContext) {
        this.mContext = mContext;
    }

    public Dialog ShowAlert(int postionTop) {
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
                mDialog = null;
            }

        }
        if (AppConfig.getLanguage_code().equalsIgnoreCase("en")) {
            mDialog = new Dialog(mContext, R.style.customRightDialog);
        } else {
            mDialog = new Dialog(mContext, R.style.customLeftDialog);
        }
        mDialog.setContentView(R.layout.layout_filter_by_area);
        LinearLayout mParentView = (LinearLayout) mDialog.findViewById(R.id.LFBA_LL_filter);
        ImageView mCloseFilter = (ImageView) mDialog.findViewById(R.id.LFBA_IV_close_filter);
        mParentView.setVisibility(View.VISIBLE);
        mListView = (ListView) mDialog.findViewById(R.id.lv_area_filter_lists);
        mResetFilter = (CustomTextView) mDialog.findViewById(R.id.LFBA_TV_reset_filter);
        mApplyFilter = (CustomTextView) mDialog.findViewById(R.id.LFBA_TV_reset_apply);
        mFilterText = (CustomTextView) mDialog.findViewById(R.id.LFBA_CTV_filter_text);
        mParentView.setBackgroundColor(Color.TRANSPARENT);

        mCloseFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mApplyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                bus.post(new FilterRefresh());
            }
        });
        List<String> mArea_id = new ArrayList<String>();
        List<VendorAreaLink> mVendorAreaLink = SQLConfig.mVendorAreaLinkDao.queryBuilder().where(VendorAreaLinkDao.Properties.Vendor_id.in(AppConfig.getmVendorList())).list();
        if (mVendorAreaLink != null) {
            for (int i = 0; i < mVendorAreaLink.size(); i++) {
                mArea_id.add(mVendorAreaLink.get(i).getArea_id());
            }
        }
        mFilterAdapter = new FilterAdapter(mContext, bus, LocalContactDB.GetAreaList(mArea_id));
        mListView.setAdapter(mFilterAdapter);
        mResetFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.techno.studentguide.activity.Constants.areaId.clear();
                mListView.invalidateViews();
                bus.post(new VendorListInterface());
            }
        });
        /***This code will be used to avoid the margin in the left and righ side of alert in lollipop device **/
        WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
        params.y = postionTop;
        params.horizontalMargin = 0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        params.width = (int) (mContext.getResources().getDisplayMetrics().widthPixels * 1);
        params.height = (int) (mContext.getResources().getDisplayMetrics().heightPixels * 1);
        mDialog.getWindow().setAttributes(params);
        mDialog.show();
        /***This code will be used to avoid the margin in the left and righ side of alert in lollipop device **/
        return mDialog;
    }

    @Override
    public void onClick(View v) {

    }


}
