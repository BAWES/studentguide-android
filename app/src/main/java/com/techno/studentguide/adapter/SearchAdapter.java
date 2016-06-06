package com.techno.studentguide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.techno.studentguide.R;
import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.customview.CustomTextView;
import com.techno.studentguide.db.Vendor;
import com.techno.studentguide.utils.CommonFunctions;
import com.techno.studentguide.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tech on 5/26/2016.
 */
public class SearchAdapter extends BaseAdapter implements Filterable {
    Context mContext;
    List<Vendor> mSearchlists;
    List<Vendor> mDuplicateSearchlists;
    LayoutInflater mInflater;

    public SearchAdapter(Context mContext, List<Vendor> mSearchlists) {
        this.mContext = mContext;
        this.mSearchlists = mSearchlists;
        this.mDuplicateSearchlists = mSearchlists;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mDuplicateSearchlists.size();
    }

    @Override
    public Vendor getItem(int position) {
        return mDuplicateSearchlists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vendorLayout = convertView;
        ViewHolder holder;
        if (vendorLayout == null) {
            vendorLayout = mInflater.inflate(R.layout.adapter_search, null);
            holder = new ViewHolder();
            holder.vSchoolImage = (ImageView) vendorLayout.findViewById(R.id.AS_IV_school_image);
            holder.vSchoolName = (CustomTextView) vendorLayout.findViewById(R.id.AS_CTV_school_name);
            holder.vSchoolPlace = (CustomTextView) vendorLayout.findViewById(R.id.AS_CTV_school_place);
            vendorLayout.setTag(holder);
        } else {
            holder = (ViewHolder) vendorLayout.getTag();
        }
        holder.vSchoolPlace.setText(AppConfig.getLanguage_code().equalsIgnoreCase("en")
                ? mDuplicateSearchlists.get(position).getVendor_address_text_en() : mDuplicateSearchlists.get(position).getVendor_address_text_ar());
        holder.vSchoolName.setText(AppConfig.getLanguage_code().equalsIgnoreCase("en")
                ? mDuplicateSearchlists.get(position).getVendor_name_en() : mDuplicateSearchlists.get(position).getVendor_name_ar());
        if (CommonFunctions.getInstance().isNetworkAvailable(mContext)) {
            if (mDuplicateSearchlists.get(position).getVendor_logo().isEmpty()) {
                Picasso.with(mContext).load(R.drawable.no_photo_available).fit().into(holder.vSchoolImage);
            } else {
                ImageLoader.getInstance(mContext).ShowImage(mDuplicateSearchlists.get(position).getVendor_logo(), holder.vSchoolImage);
            }
        } else {
            Picasso.with(mContext).load(R.drawable.no_photo_available).fit().into(holder.vSchoolImage);
        }

        return vendorLayout;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
                mDuplicateSearchlists = (ArrayList<Vendor>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<Vendor> FilteredList = new ArrayList<Vendor>();
                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = mSearchlists;
                    results.count = mSearchlists.size();
                } else {
                    for (int i = 0; i < mSearchlists.size(); i++) {
                        Vendor mVendor = new Vendor();
                        mVendor.setVendor_id(mSearchlists.get(i).getVendor_id());
                        mVendor.setVendor_logo(mSearchlists.get(i).getVendor_logo());
                        mVendor.setVendor_name_en(mSearchlists.get(i).getVendor_name_en());
                        mVendor.setVendor_name_ar(mSearchlists.get(i).getVendor_name_ar());
                        mVendor.setVendor_description_en(mSearchlists.get(i).getVendor_description_en());
                        mVendor.setVendor_description_ar(mSearchlists.get(i).getVendor_description_ar());
                        mVendor.setVendor_phone1(mSearchlists.get(i).getVendor_phone1());
                        mVendor.setVendor_phone2(mSearchlists.get(i).getVendor_phone2());
                        mVendor.setVendor_youtube_video(mSearchlists.get(i).getVendor_youtube_video());
                        mVendor.setVendor_social_instagram(mSearchlists.get(i).getVendor_social_instagram());
                        mVendor.setVendor_social_twitter(mSearchlists.get(i).getVendor_social_twitter());
                        mVendor.setVendor_location(mSearchlists.get(i).getVendor_location());
                        mVendor.setVendor_address_text_en(mSearchlists.get(i).getVendor_address_text_en());
                        mVendor.setVendor_address_text_ar(mSearchlists.get(i).getVendor_address_text_ar());
                        mVendor.setVendor_account_start_date(mSearchlists.get(i).getVendor_account_start_date());
                        mVendor.setVendor_account_end_date(mSearchlists.get(i).getVendor_account_end_date());
                        if (mVendor.getVendor_name_en().toLowerCase().contains(constraint.toString())
                                || mVendor.getVendor_name_ar().contains(constraint.toString())) {
                            FilteredList.add(mVendor);
                        }
                    }
                    results.values = FilteredList;
                    results.count = FilteredList.size();
                }
                return results;
            }
        };
        return filter;
    }

    class ViewHolder {
        CustomTextView vSchoolName, vSchoolPlace;
        ImageView vSchoolImage;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
