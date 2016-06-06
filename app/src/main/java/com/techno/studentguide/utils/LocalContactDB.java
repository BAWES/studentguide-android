package com.techno.studentguide.utils;


import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.db.Category;
import com.techno.studentguide.db.CategoryDao;
import com.techno.studentguide.db.SQLConfig;
import com.techno.studentguide.db.Vendor;
import com.techno.studentguide.db.VendorAreaLink;
import com.techno.studentguide.db.VendorAreaLinkDao;
import com.techno.studentguide.db.VendorAreaList;
import com.techno.studentguide.db.VendorAreaListDao;
import com.techno.studentguide.db.VendorCategoryLink;
import com.techno.studentguide.db.VendorCategoryLinkDao;
import com.techno.studentguide.db.VendorDao;
import com.techno.studentguide.db.VendorPhoto;
import com.techno.studentguide.db.VendorPhotoDao;

import java.util.List;

/**
 * Created by Android on 4/16/2016.
 */
public class LocalContactDB {
    private static LocalContactDB ourInstance = new LocalContactDB();

    public static LocalContactDB getInstance() {
        return ourInstance;
    }

    private LocalContactDB() {
    }

    public static void deleteAll() {
        SQLConfig.mCategoryDao.deleteAll();
    }


    public static Boolean add(String category_id, String category_en,
                              String category_ar, String parent_category, boolean filter_by,
                              String count, String created_time, String updated_time) {
        Category categorylistsupdate = SQLConfig.mCategoryDao.queryBuilder().where(CategoryDao.Properties.Category_id.eq(category_id)).limit(1).unique();

        if (categorylistsupdate != null) {
            categorylistsupdate.setCategory_id(category_id);
            categorylistsupdate.setCategory_name_ar(category_ar);
            categorylistsupdate.setCategory_name_en(category_en);
            categorylistsupdate.setCount(count);
            categorylistsupdate.setFilter_by(filter_by);
            categorylistsupdate.setName("");
            categorylistsupdate.setParent_category_id(parent_category);
            categorylistsupdate.setCategory_created_datetime(created_time);
            categorylistsupdate.setCategory_updated_datetime(updated_time);
            SQLConfig.mCategoryDao.update(categorylistsupdate);
        } else {
            Category categorylistsinsert = new Category();
            categorylistsinsert.setCategory_id(category_id);
            categorylistsinsert.setCategory_name_ar(category_ar);
            categorylistsinsert.setCategory_name_en(category_en);
            categorylistsinsert.setCount(count);
            categorylistsinsert.setFilter_by(filter_by);
            categorylistsinsert.setName("");
            categorylistsinsert.setParent_category_id(parent_category);
            categorylistsinsert.setCategory_created_datetime(created_time);
            categorylistsinsert.setCategory_updated_datetime(updated_time);
            SQLConfig.mCategoryDao.insert(categorylistsinsert);
        }
        return true;
    }


    public static Boolean addCategoryListLinks(String link_id, String vendor_id, String category_id) {

        VendorCategoryLink vendorCategoryLinkupdate = SQLConfig.mVendorCategoryLinkDao.queryBuilder().where(VendorCategoryLinkDao.Properties.Link_id.eq(link_id)).limit(1).unique();
        if (vendorCategoryLinkupdate != null) {
            vendorCategoryLinkupdate.setLink_id(link_id);
            vendorCategoryLinkupdate.setVendor_id(vendor_id);
            vendorCategoryLinkupdate.setCategory_id(category_id);
            SQLConfig.mVendorCategoryLinkDao.update(vendorCategoryLinkupdate);
        } else {
            VendorCategoryLink vendorCategoryLinkinsert = new VendorCategoryLink();
            vendorCategoryLinkinsert.setLink_id(link_id);
            vendorCategoryLinkinsert.setVendor_id(vendor_id);
            vendorCategoryLinkinsert.setCategory_id(category_id);
            SQLConfig.mVendorCategoryLinkDao.insert(vendorCategoryLinkinsert);
        }
        return true;
    }

    public static Boolean addVendorGallery(String photo, String vendor_id, String gallery_id) {

        VendorPhoto mVendorPhotoUpdate = SQLConfig.mVendorPhotoDao.queryBuilder().where(VendorPhotoDao.Properties.Photo_id.eq(gallery_id)).limit(1).unique();
        if (mVendorPhotoUpdate != null) {
            mVendorPhotoUpdate.setPhoto_url(photo);
            mVendorPhotoUpdate.setVendor_id(vendor_id);
            mVendorPhotoUpdate.setPhoto_id(gallery_id);
            SQLConfig.mVendorPhotoDao.update(mVendorPhotoUpdate);
        } else {
            VendorPhoto vendorPhotoinsert = new VendorPhoto();
            vendorPhotoinsert.setPhoto_url(photo);
            vendorPhotoinsert.setVendor_id(vendor_id);
            vendorPhotoinsert.setPhoto_id(gallery_id);
            SQLConfig.mVendorPhotoDao.insert(vendorPhotoinsert);
        }
        return true;
    }


    public static Boolean addAreaList(String id, String area_name_en, String area_name_ar) {
        VendorAreaList mVendorAreaListUpdate = SQLConfig.mVendorAreaListDao.queryBuilder().where(VendorAreaListDao.Properties.Area_id.eq(id)).limit(1).unique();
        if (mVendorAreaListUpdate != null) {
            mVendorAreaListUpdate.setArea_id(id);
            mVendorAreaListUpdate.setArea_name_ar(area_name_ar);
            mVendorAreaListUpdate.setArea_name_en(area_name_en);
            SQLConfig.mVendorAreaListDao.update(mVendorAreaListUpdate);
        } else {
            VendorAreaList vendorAreaListInsert = new VendorAreaList();
            vendorAreaListInsert.setArea_id(id);
            vendorAreaListInsert.setArea_name_ar(area_name_ar);
            vendorAreaListInsert.setArea_name_en(area_name_en);
            SQLConfig.mVendorAreaListDao.insert(vendorAreaListInsert);
        }


        return true;
    }

    public static Boolean addAreaLinkLists(String link_id, String vendor_id, String area_id) {
        VendorAreaLink mVendorAreaLinkUpdate = SQLConfig.mVendorAreaLinkDao.queryBuilder().where(VendorAreaLinkDao.Properties.Link_id.eq(link_id)).limit(1).unique();
        if (mVendorAreaLinkUpdate != null) {
            mVendorAreaLinkUpdate.setLink_id(link_id);
            mVendorAreaLinkUpdate.setVendor_id(vendor_id);
            mVendorAreaLinkUpdate.setArea_id(area_id);
            SQLConfig.mVendorAreaLinkDao.update(mVendorAreaLinkUpdate);
        } else {
            VendorAreaLink vendorAreaLinkInsert = new VendorAreaLink();
            vendorAreaLinkInsert.setLink_id(link_id);
            vendorAreaLinkInsert.setVendor_id(vendor_id);
            vendorAreaLinkInsert.setArea_id(area_id);
            SQLConfig.mVendorAreaLinkDao.insert(vendorAreaLinkInsert);
        }
        return true;
    }

    public static Boolean addVendor(String vendor_id, String vendor_logo, String vendor_name_en, String vendor_name_ar, String vendor_description_en,
                                    String vendor_description_ar, String vendor_phone1, String vendor_phone2, String vendor_youtube_video,
                                    String vendor_social_instagram, String vendor_social_twitter, String vendor_location, String vendor_address_text_en,
                                    String vendor_address_text_ar, String vendor_account_start_date, String vendor_account_end_date, String sort_order) {

        Vendor vendorlistsupdate = SQLConfig.mVendorDao.queryBuilder().where(VendorDao.Properties.Vendor_id.eq(vendor_id)).limit(1).unique();

        if (vendorlistsupdate != null) {
            vendorlistsupdate.setVendor_id(vendor_id);
            vendorlistsupdate.setVendor_logo(vendor_logo);
            vendorlistsupdate.setVendor_name_en(vendor_name_en);
            vendorlistsupdate.setVendor_name_ar(vendor_name_ar);
            vendorlistsupdate.setVendor_description_en(vendor_description_en);
            vendorlistsupdate.setVendor_description_ar(vendor_description_ar);
            vendorlistsupdate.setVendor_phone1(vendor_phone1);
            vendorlistsupdate.setVendor_phone2(vendor_phone2);
            vendorlistsupdate.setVendor_youtube_video(vendor_youtube_video);
            vendorlistsupdate.setVendor_social_instagram(vendor_social_instagram);
            vendorlistsupdate.setVendor_social_twitter(vendor_social_twitter);
            vendorlistsupdate.setVendor_location(vendor_location);
            vendorlistsupdate.setVendor_address_text_en(vendor_address_text_en);
            vendorlistsupdate.setVendor_address_text_ar(vendor_address_text_ar);
            vendorlistsupdate.setVendor_account_start_date(vendor_account_start_date);
            vendorlistsupdate.setVendor_account_end_date(vendor_account_end_date);
            vendorlistsupdate.setSort_order(sort_order);
            SQLConfig.mVendorDao.update(vendorlistsupdate);
        } else {
            Vendor vendorlistsinsert = new Vendor();
            vendorlistsinsert.setVendor_id(vendor_id);
            vendorlistsinsert.setVendor_logo(vendor_logo);
            vendorlistsinsert.setVendor_name_en(vendor_name_en);
            vendorlistsinsert.setVendor_name_ar(vendor_name_ar);
            vendorlistsinsert.setVendor_description_en(vendor_description_en);
            vendorlistsinsert.setVendor_description_ar(vendor_description_ar);
            vendorlistsinsert.setVendor_phone1(vendor_phone1);
            vendorlistsinsert.setVendor_phone2(vendor_phone2);
            vendorlistsinsert.setVendor_youtube_video(vendor_youtube_video);
            vendorlistsinsert.setVendor_social_instagram(vendor_social_instagram);
            vendorlistsinsert.setVendor_social_twitter(vendor_social_twitter);
            vendorlistsinsert.setVendor_location(vendor_location);
            vendorlistsinsert.setVendor_address_text_en(vendor_address_text_en);
            vendorlistsinsert.setVendor_address_text_ar(vendor_address_text_ar);
            vendorlistsinsert.setVendor_account_start_date(vendor_account_start_date);
            vendorlistsinsert.setVendor_account_end_date(vendor_account_end_date);
            vendorlistsinsert.setSort_order(sort_order);
            SQLConfig.mVendorDao.insert(vendorlistsinsert);
        }
        return true;
    }


    public static List<Category> GetCategory() {
        return SQLConfig.mCategoryDao.queryBuilder().where(CategoryDao.Properties.Parent_category_id.eq(0)).list();
        //return SQLConfig.mCategoryDao.queryBuilder().list();
    }

    public static List<Category> GetSubCategory(String category_id) {
        return SQLConfig.mCategoryDao.queryBuilder().where(CategoryDao.Properties.Parent_category_id.eq(category_id)).list();
//        return SQLConfig.mCategoryDao.queryBuilder().list();
    }

    public static List<Vendor> GetVendor(List<String> mVendorId) {
        if (AppConfig.getLanguage_code().equalsIgnoreCase("en")){
            return SQLConfig.mVendorDao.queryBuilder().where(VendorDao.Properties.Vendor_id.in(mVendorId)).orderRaw("sort_order, vendor_name_en").list();
        }else{
            return SQLConfig.mVendorDao.queryBuilder().where(VendorDao.Properties.Vendor_id.in(mVendorId)).orderRaw("sort_order, vendor_name_ar").list();
        }
    }

    public static List<Vendor> GetFilterVendor(String mDate, String mEndDate) {
        return SQLConfig.mVendorDao.queryBuilder().where(VendorDao.Properties.Vendor_account_start_date.between(mDate, mEndDate)).list();
    }

    public static List<Vendor> GetVendorList() {
        return SQLConfig.mVendorDao.queryBuilder().list();
    }

    public static List<Vendor> GetVendorList(String vendor_id) {
        return SQLConfig.mVendorDao.queryBuilder().where(VendorDao.Properties.Vendor_id.eq(vendor_id)).list();
    }

    public static List<VendorPhoto> GetVendorGallery(String mVendorId) {
        return SQLConfig.mVendorPhotoDao.queryBuilder().where(VendorPhotoDao.Properties.Vendor_id.eq(mVendorId)).list();
    }

    public static List<VendorAreaList> GetAreaList() {
        return SQLConfig.mVendorAreaListDao.queryBuilder().list();
    }

    public static List<VendorAreaList> GetAreaList(List<String> mAreaIds) {
        return SQLConfig.mVendorAreaListDao.queryBuilder().where(VendorAreaListDao.Properties.Area_id.in(mAreaIds)).list();
    }
}
