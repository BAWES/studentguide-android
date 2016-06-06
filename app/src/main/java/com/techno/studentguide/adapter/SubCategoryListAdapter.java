package com.techno.studentguide.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.techno.studentguide.R;
import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.customview.CustomTextView;
import com.techno.studentguide.db.Category;

import java.util.List;

/**
 * Created by Android on 5/13/2016.
 */
public class SubCategoryListAdapter extends BaseAdapter {

    /***********
     * Declare Used Variables
     *********/
    private Context mContext;
    LayoutInflater inflater;
    List<Category> alSubCategoryList;
    private static int selectedIndex;

    public SubCategoryListAdapter(Context mContext, List<Category> alCategoryList) {
        this.mContext = mContext;
        this.alSubCategoryList = alCategoryList;
        inflater = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return alSubCategoryList.size();
    }

    @Override
    public Category getItem(int position) {
        return alSubCategoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static void setSelectedIndex(int ind) {
        selectedIndex = ind;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View subCategoryLayout = convertView;
        ViewHolder holder;
        if (subCategoryLayout == null) {
            holder = new ViewHolder();
            subCategoryLayout = inflater.inflate(R.layout.adapter_area, null);
            holder.vCategoryName = (CustomTextView) subCategoryLayout.findViewById(R.id.AL_CTV_CategoryName);
            subCategoryLayout.setTag(holder);
        } else {
            holder = (ViewHolder) subCategoryLayout.getTag();
        }

        if (position == selectedIndex) {
            holder.vCategoryName.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.button_english_colored));
        } else {
            holder.vCategoryName.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.button_normal));
        }
        holder.vCategoryName.setText(AppConfig.getLanguage_code().equalsIgnoreCase("en") ? alSubCategoryList.get(position).getCategory_name_en() :
                alSubCategoryList.get(position).getCategory_name_ar());

        return subCategoryLayout;
    }

    class ViewHolder {
        public CustomTextView vCategoryName;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
