package com.techno.studentguide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;

import com.techno.studentguide.R;
import com.techno.studentguide.customview.CustomTextView;
import com.techno.studentguide.model.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tech on 5/18/2016.
 */
public class SearchViewAdapter extends ArrayAdapter<Search> {
    Context mContext;
    ArrayList<Search> mCountries, tempItems;
    int adapter_search_view;

    public SearchViewAdapter(Context mContext, int adapter_search_view, ArrayList<Search> mCountries) {
        super(mContext, 0, mCountries);
        this.mCountries = mCountries;
        tempItems = mCountries;
        this.adapter_search_view = adapter_search_view;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(adapter_search_view, null, true);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        Search searchData = tempItems.get(position);
        mHolder.mVendorName = (CustomTextView) convertView.findViewById(R.id.ASV_CTV_school_name);
        mHolder.mVendorImage = (ImageView) convertView.findViewById(R.id.ASV_IV_school_image);
        mHolder.mVendorPlaces = (CustomTextView) convertView.findViewById(R.id.ASV_CTV_school_place);
        mHolder.mVendorPlaces.setText(searchData.getmVendorPlaces());
        return convertView;
    }

    class ViewHolder {
        ImageView mVendorImage;
        CustomTextView mVendorName, mVendorPlaces;

    }
    @Override
    public Filter getFilter() {
        return nameFilter;
    }
    /**
     * Custom Filter implementation for custom suggestions we provide.
     */


    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((Search) resultValue).getmVendorPlaces();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                mCountries.clear();
                for (Search people : tempItems) {
                    if (people.getmVendorPlaces().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        mCountries.add(people);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mCountries;
                filterResults.count = mCountries.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Search> filterList = (ArrayList<Search>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Search people : filterList) {
                    add(people);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
