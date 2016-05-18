package com.techno.studentguide.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.techno.studentguide.R;
import com.techno.studentguide.customview.CustomTextView;
import com.techno.studentguide.model.Category;

import java.util.ArrayList;

/**
 * Created by Android on 5/13/2016.
 */
public class SubCategoryListAdapter extends BaseAdapter {

    /***********
     * Declare Used Variables
     *********/
    private Activity activity;
    private static LayoutInflater inflater = null;
    ArrayList<Category> alCategoryList;

    public SubCategoryListAdapter(Activity a, ArrayList<Category> alCategoryList)
    {
        activity = a;
        this.alCategoryList = alCategoryList;
        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount ()
    {
        if (alCategoryList.size () <= 0 )
        {
            return 1;
        }
        return alCategoryList.size ();
    }

    public Category getItem ( int position )
    {
        return alCategoryList.get(position);
    }

    public long getItemId ( int position )
    {
        return position;
    }

    class ViewHolder
    {
        public CustomTextView vCategoryName;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        ViewHolder holder;

        if ( convertView == null )
        {
            vi = inflater.inflate (R.layout.adapter_area, null);

            holder = new ViewHolder ();
            holder.vCategoryName = (CustomTextView) vi.findViewById (R.id.AL_CTV_AreaName);
            vi.setTag (holder);
        }
        else
        {
            holder = (ViewHolder) vi.getTag ();
        }

        if (alCategoryList.size () <= 0 )
        {
            holder.vCategoryName.setText("No Data Found");
        }
        else
        {
            holder.vCategoryName.setText(alCategoryList.get(position).getCategory_name_en());
        }
        return vi;
    }

}
