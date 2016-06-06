package com.techno.studentguide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.techno.studentguide.R;
import com.techno.studentguide.activity.Constants;
import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.customview.CustomCheckBox;
import com.techno.studentguide.db.SQLConfig;
import com.techno.studentguide.db.VendorAreaLink;
import com.techno.studentguide.db.VendorAreaLinkDao;
import com.techno.studentguide.db.VendorAreaList;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by tech on 5/26/2016.
 */
public class FilterAdapter extends BaseAdapter {

    Context mContext;
    List<VendorAreaList> vendorAreaLists;
    LayoutInflater mInflater;
    EventBus bus;
//    public static boolean checkedStatus = false;
    public static List<String> vendorId = new ArrayList<String>();

    public FilterAdapter(Context mContext, EventBus bus, List<VendorAreaList> vendorAreaLists) {
        this.mContext = mContext;
        this.vendorAreaLists = vendorAreaLists;
        this.bus = bus;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return vendorAreaLists.size();
    }

    @Override
    public VendorAreaList getItem(int position) {
        return vendorAreaLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_filter, null);
            holder = new ViewHolder();
            holder.areaNames = (CustomCheckBox) convertView.findViewById(R.id.AF_CB_filterl);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.areaNames.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                VendorAreaList vendorAreaList = (VendorAreaList) cb.getTag();

//                 areaId = new ArrayList<String>();
                List<String> mAreaIDS = new ArrayList<String>();
                List<VendorAreaLink> mVendorAreaLink = new ArrayList<VendorAreaLink>();
//                checkedStatus = true;
                if (Constants.areaId.contains(vendorAreaLists.get(position).getArea_id())) {
                    Constants.areaId.remove(vendorAreaList.getArea_id());

                } else {
                    Constants.areaId.add(vendorAreaList.getArea_id());

                }
                if (cb.isChecked()) {
                    Constants.mCheckBoxList.add(cb);
                } else {
                    Constants.mCheckBoxList.remove(cb);
                }

                for (int id = 0; id < Constants.areaId.size(); id++) {
                    if (id == Constants.areaId.size() - 1) {
                        mAreaIDS.add(Constants.areaId.get(id));
                    } else {
                        mAreaIDS.add(Constants.areaId.get(id) + ",");
                    }
                }
                        /*Query VendorAreaLinkDao list based on area choosed*/
                mVendorAreaLink = SQLConfig.mVendorAreaLinkDao.queryBuilder().where(VendorAreaLinkDao.Properties.Area_id.in(mAreaIDS)).list();
                vendorId.clear();
                if (mVendorAreaLink != null) {
                    for (int vendor = 0; vendor < mVendorAreaLink.size(); vendor++) {
                        vendorId.add(mVendorAreaLink.get(vendor).getVendor_id());
                    }
                    vendorAreaList.setSelected(cb.isChecked());

                }
            }
        });

        VendorAreaList mVendorAreaList = vendorAreaLists.get(position);
        if (Constants.areaId.contains(vendorAreaLists.get(position).getArea_id())) {
            holder.areaNames.setChecked(true);
        } else {
            holder.areaNames.setChecked(false);

        }

        holder.areaNames.setText(AppConfig.getLanguage_code().equalsIgnoreCase("en") ? mVendorAreaList.getArea_name_en() :
                mVendorAreaList.getArea_name_ar());

        holder.areaNames.setTag(mVendorAreaList);
        return convertView;

    }

}


class ViewHolder {
    CustomCheckBox areaNames;
}
