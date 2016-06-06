package com.techno.studentguide.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.techno.studentguide.R;
import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.customview.CustomTextView;
import com.techno.studentguide.db.Vendor;
import com.techno.studentguide.utils.BaseAdapterInterface;
import com.techno.studentguide.utils.CommonFunctions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Android on 5/13/2016.
 */
public class VendorListAdapter extends BaseAdapter implements BaseAdapterInterface {

    private Context mContext;
    LayoutInflater inflater;
    List<Vendor> alVendorList;
    EventBus bus;

    public VendorListAdapter(Context mContext, EventBus bus, List<Vendor> alVendorList) {
        this.mContext = mContext;
        this.alVendorList = alVendorList;
        this.bus = bus;
        inflater = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return alVendorList.size();
    }

    public Vendor getItem(int position) {
        return alVendorList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View vendorLayout = convertView;
        ViewHolder holder;

        if (convertView == null) {
            vendorLayout = inflater.inflate(R.layout.adapter_vendor, null);
            holder = new ViewHolder();
            holder.vSchoolImage = (ImageView) vendorLayout.findViewById(R.id.AV_IV_school_image);
            holder.vSchoolName = (CustomTextView) vendorLayout.findViewById(R.id.AV_CTV_school_name);
            holder.vSchoolPlace = (CustomTextView) vendorLayout.findViewById(R.id.AV_CTV_school_place);
            holder.mImageViewLoc = (ImageView) vendorLayout.findViewById(R.id.image_location);
            holder.mVendorParent = (LinearLayout) vendorLayout.findViewById(R.id.ll_vendor_list_parent);
            vendorLayout.setTag(holder);
        } else {
            holder = (ViewHolder) vendorLayout.getTag();
        }
        holder.vSchoolPlace.setText(AppConfig.getLanguage_code().equalsIgnoreCase("en")
                ? alVendorList.get(position).getVendor_address_text_en() : alVendorList.get(position).getVendor_address_text_ar());
        holder.vSchoolName.setText(AppConfig.getLanguage_code().equalsIgnoreCase("en")
                ? alVendorList.get(position).getVendor_name_en() : alVendorList.get(position).getVendor_name_ar());
        if (CommonFunctions.getInstance().isNetworkAvailable(mContext)) {
            if (!alVendorList.get(position).getVendor_logo().isEmpty()) {
                Picasso.with(mContext).load(alVendorList.get(position).getVendor_logo()).placeholder(R.drawable.no_photo_available).resize(50, 50).config(Bitmap.Config.ARGB_8888)
                        .centerCrop().into(holder.vSchoolImage);
            } else {
                Picasso.with(mContext).load(R.drawable.no_photo_available).fit().into(holder.vSchoolImage);
            }

        } else {
            Picasso.with(mContext).load(R.drawable.no_photo_available).fit().into(holder.vSchoolImage);
        }
     /*   String mStartDate = alVendorList.get(position).getVendor_account_start_date();
        String mEndDate = alVendorList.get(position).getVendor_account_end_date();
        getDate(mStartDate);
        getDate(mEndDate);
        getDate(getCurrentDate());
        long millStart = getDate(mStartDate).getTime();
        long millEnd = getDate(mEndDate).getTime();
        long millCurrent = getDate(getCurrentDate()).getTime();*/
       /* if (alVendorList.size() == 1) {
            if (millCurrent >= millStart && millCurrent <= millEnd) {
                bus.post(new NoDataFoundInterface(alVendorList.size()));
                holder.vSchoolPlace.setText(AppConfig.getLanguage_code().equalsIgnoreCase("en")
                        ? alVendorList.get(position).getVendor_address_text_en() : alVendorList.get(position).getVendor_address_text_ar());
                holder.vSchoolName.setText(AppConfig.getLanguage_code().equalsIgnoreCase("en")
                        ? alVendorList.get(position).getVendor_name_en() : alVendorList.get(position).getVendor_name_ar());
                if (CommonFunctions.getInstance().isNetworkAvailable(mContext)) {
                    if (!alVendorList.get(position).getVendor_logo().isEmpty()) {
                        Picasso.with(mContext).load(alVendorList.get(position).getVendor_logo()).placeholder(R.drawable.no_photo_available).resize(50, 50).config(Bitmap.Config.ARGB_8888)
                                .centerCrop().into(holder.vSchoolImage);
                    } else {
                        Picasso.with(mContext).load(R.drawable.no_photo_available).fit().into(holder.vSchoolImage);
                    }
                } else {
                    Picasso.with(mContext).load(R.drawable.no_photo_available).fit().into(holder.vSchoolImage);
                }
            } else {
                bus.post(new NoDataFoundInterface(0));
                holder.vSchoolPlace.setVisibility(View.GONE);
                holder.vSchoolName.setVisibility(View.GONE);
                holder.vSchoolImage.setVisibility(View.GONE);
                holder.mImageViewLoc.setVisibility(View.GONE);
            }
        } else {
            if (millCurrent >= millStart && millCurrent <= millEnd) {
                holder.vSchoolPlace.setText(AppConfig.getLanguage_code().equalsIgnoreCase("en")
                        ? alVendorList.get(position).getVendor_address_text_en() : alVendorList.get(position).getVendor_address_text_ar());
                holder.vSchoolName.setText(AppConfig.getLanguage_code().equalsIgnoreCase("en")
                        ? alVendorList.get(position).getVendor_name_en() : alVendorList.get(position).getVendor_name_ar());
                if (CommonFunctions.getInstance().isNetworkAvailable(mContext)) {
                    if (!alVendorList.get(position).getVendor_logo().isEmpty()) {
                        Picasso.with(mContext).load(alVendorList.get(position).getVendor_logo()).placeholder(R.drawable.no_photo_available).resize(50, 50).config(Bitmap.Config.ARGB_8888)
                                .centerCrop().into(holder.vSchoolImage);
                    } else {
                        Picasso.with(mContext).load(R.drawable.no_photo_available).fit().into(holder.vSchoolImage);
                    }

                } else {
                    Picasso.with(mContext).load(R.drawable.no_photo_available).fit().into(holder.vSchoolImage);
                }
            } else {
                holder.vSchoolPlace.setVisibility(View.GONE);
                holder.vSchoolName.setVisibility(View.GONE);
                holder.vSchoolImage.setVisibility(View.GONE);
                holder.mImageViewLoc.setVisibility(View.GONE);
            }
        }*/


        return vendorLayout;
    }

    private Date getDate(String mStartDate) {
        String startDateString = mStartDate;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = df.parse(startDateString);
            String newDateString = df.format(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    @Override
    public void notifyChanges() {
//        this.alVendorList = LocalContactDB.GetVendorList();
        notifyDataSetChanged();
    }

    public void clear() {
        alVendorList.clear();
        notifyDataSetChanged();
    }

    class ViewHolder {
        public CustomTextView vSchoolName, vSchoolPlace;
        public ImageView vSchoolImage, mImageViewLoc;
        public LinearLayout mVendorParent;
    }

    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
        String strDate = "" + mdformat.format(calendar.getTime());

        return strDate;
    }

}
