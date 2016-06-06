package com.techno.studentguide.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.techno.studentguide.R;
import com.techno.studentguide.activity.PreviewImageActivity;
import com.techno.studentguide.db.VendorPhoto;
import com.techno.studentguide.utils.CommonFunctions;

import java.util.List;

/**
 * Created by tech on 5/13/2016.
 */
public class GalleryCoverFlowAdapter extends BaseAdapter {
    Context mContext;
    List<VendorPhoto> galleryImages;
    LayoutInflater mInflater;

    public GalleryCoverFlowAdapter(Context mContext, List<VendorPhoto> galleryImages) {
        this.mContext = mContext;
        this.galleryImages = galleryImages;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return galleryImages.size();
    }

    @Override
    public VendorPhoto getItem(int position) {
        return galleryImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.adapter_gallery_cover_flow, null);
            mHolder.mGalleryFlow = (ImageView) convertView.findViewById(R.id.iv_image_cover_flow);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        if (CommonFunctions.getInstance().isNetworkAvailable(mContext)) {
            if (!galleryImages.get(position).getPhoto_url().isEmpty()) {
                Picasso.with(mContext).load(galleryImages.get(position).getPhoto_url()).placeholder(R.drawable.no_preview_video).fit().into(mHolder.mGalleryFlow);
            } else {
                Picasso.with(mContext).load(R.drawable.no_preview_video).fit().into(mHolder.mGalleryFlow);
            }

        } else {
            Picasso.with(mContext).load(R.drawable.no_preview_video).fit().into(mHolder.mGalleryFlow);
        }

        convertView.setOnClickListener(new ClickListener(position));
        return convertView;
    }

    class ViewHolder {
        ImageView mGalleryFlow;
    }

    private class ClickListener implements View.OnClickListener {
        int position;

        public ClickListener(int position) {
            this.position = position;
        }

        @Override

        public void onClick(View v) {
            Intent mCallPreview = new Intent(mContext, PreviewImageActivity.class);
            mCallPreview.putExtra("position", position);
            mContext.startActivity(mCallPreview);
        }
    }
}
