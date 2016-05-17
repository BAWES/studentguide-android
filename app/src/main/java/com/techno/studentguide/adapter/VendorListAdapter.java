package com.techno.studentguide.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.techno.studentguide.R;
import com.techno.studentguide.customview.CustomTextView;
import com.techno.studentguide.model.Vendor;
import com.techno.studentguide.utils.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Android on 5/13/2016.
 */
public class VendorListAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater = null;
    ArrayList<Vendor> alVendorList;

    public VendorListAdapter ( Activity a, ArrayList<Vendor> alVendorList )
    {
        activity = a;
        this.alVendorList = alVendorList;
        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount ()
    {
        if ( alVendorList.size () <= 0 )
        {
            return 1;
        }
        return alVendorList.size ();
    }

    public Vendor getItem ( int position )
    {
        return alVendorList.get(position);
    }

    public long getItemId ( int position )
    {
        return position;
    }

    class ViewHolder
    {
        public CustomTextView vSchoolName, vSchoolPlace;
        public ImageView vSchoolImage;
    }

    public View getView ( int position, View convertView, ViewGroup parent )
    {

        View vi = convertView;
        ViewHolder holder;

        if ( convertView == null )
        {

            vi = inflater.inflate (R.layout.adapter_vendor, null);

            holder = new ViewHolder ();
            holder.vSchoolImage = (ImageView) vi.findViewById(R.id.AV_IV_school_image);
            holder.vSchoolName = (CustomTextView) vi.findViewById (R.id.AV_CTV_school_name);
            holder.vSchoolPlace = (CustomTextView) vi.findViewById (R.id.AV_CTV_school_place);

            vi.setTag (holder);
        }
        else
        {
            holder = (ViewHolder) vi.getTag ();
        }

        if ( alVendorList.size () <= 0 )
        {
            holder.vSchoolName.setText("No Data Found");

        }
        else
        {
            if(alVendorList.get(position).getPlace() != null && !alVendorList.get(position).getPlace().isEmpty())
            {
                holder.vSchoolPlace.setText(alVendorList.get(position).getPlace());
            }
            if(alVendorList.get(position).getName()!= null && !alVendorList.get(position).getName().isEmpty())
            {
                holder.vSchoolName.setText(alVendorList.get(position).getName());
            }
            if(alVendorList.get(position).getImage_url()!= null && !alVendorList.get(position).getImage_url().isEmpty())
            {
                ImageLoader.getInstance(activity).ShowImage(alVendorList.get(position).getImage_url(),holder.vSchoolImage);
            }
        }
        return vi;
    }
}
