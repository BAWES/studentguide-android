package com.techno.studentguide.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.techno.studentguide.R;
import com.techno.studentguide.customview.CustomTextView;
import com.techno.studentguide.model.Area;

import java.util.ArrayList;

/*********
 * Adapter class extends with BaseAdapter and implements with OnClickListener
 ************/
public class AreaListAdapter extends BaseAdapter {

    /***********
     * Declare Used Variables
     *********/
    private Activity activity;
    private static LayoutInflater inflater = null;
    ArrayList<Area> alAreaList;

    public AreaListAdapter(Activity a, ArrayList<Area> mAreaList) {
        activity = a;
        alAreaList = mAreaList;
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {

        if (alAreaList.size() <= 0) {
            return 1;
        }
        return alAreaList.size();
    }

    public Area getItem(int position) {
        return alAreaList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public CustomTextView vAreaName;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {
            vi = inflater.inflate(R.layout.adapter_area, null);
            holder = new ViewHolder();
            holder.vAreaName = (CustomTextView) vi.findViewById(R.id.AL_CTV_AreaName);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        if (alAreaList.size() <= 0) {
            holder.vAreaName.setText("No Data Found");
        } else {
            holder.vAreaName.setText(alAreaList.get(position).getArea_name_ar());
        }
        return vi;
    }


}