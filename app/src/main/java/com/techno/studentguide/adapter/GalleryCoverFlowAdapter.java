package com.techno.studentguide.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.techno.studentguide.R;

/**
 * Created by tech on 5/13/2016.
 */
public class GalleryCoverFlowAdapter extends BaseAdapter {
    Context mContext;
    int[] galleryImages;
    LayoutInflater mInflater;

    public GalleryCoverFlowAdapter(Context mContext, int[] galleryImages) {
        this.mContext = mContext;
        this.galleryImages = galleryImages;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return galleryImages.length;
    }

    @Override
    public Integer getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.adapter_gallery_cover_flow, null);
            mHolder.mGalleryFlow = (ImageView) convertView.findViewById(R.id.iv_image_cover_flow);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(mContext).load(galleryImages[position]).fit().into(mHolder.mGalleryFlow);
        return convertView;
    }

    class ViewHolder {
        ImageView mGalleryFlow;
    }
}
