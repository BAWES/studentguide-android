package com.techno.studentguide.model;

/**
 * Created by tech on 5/18/2016.
 */
public class Search {
    String mVendorImage, mVendorName, mVendorPlaces;
    String country;

    public Search(String country) {
        this.country = country;
    }

    public String getmVendorImage() {
        return mVendorImage;
    }

    public void setmVendorImage(String mVendorImage) {
        this.mVendorImage = mVendorImage;
    }

    public String getmVendorName() {
        return mVendorName;
    }

    public void setmVendorName(String mVendorName) {
        this.mVendorName = mVendorName;
    }

    public String getmVendorPlaces() {
        return mVendorPlaces;
    }

    public void setmVendorPlaces(String mVendorPlaces) {
        this.mVendorPlaces = mVendorPlaces;
    }
}
