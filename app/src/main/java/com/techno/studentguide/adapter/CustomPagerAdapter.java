package com.techno.studentguide.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.techno.studentguide.R;
import com.techno.studentguide.db.VendorPhoto;
import com.techno.studentguide.utils.CommonFunctions;

import java.util.List;

/**
 * Created by tech on 5/31/2016.
 */
public class CustomPagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    List<VendorPhoto> galleryImages;

    public CustomPagerAdapter(Context context, List<VendorPhoto> galleryImages) {
        mContext = context;
        this.galleryImages = galleryImages;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return galleryImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        if (CommonFunctions.getInstance().isNetworkAvailable(mContext)) {
            Picasso.with(mContext).load(galleryImages.get(position).getPhoto_url()).placeholder(R.drawable.no_preview_video).fit().into(imageView);
        } else {
            Picasso.with(mContext).load(R.drawable.no_preview_video).fit().into(imageView);
        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
