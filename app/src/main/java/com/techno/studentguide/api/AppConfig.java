package com.techno.studentguide.api;

import com.techno.studentguide.offline.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tech on 5/23/2016.
 */
public class AppConfig {

    //local url
//    public static String domainName = "http://192.168.1.114:1095/api/web/index.php/v1";
//    public static String domainName = "http://192.168.1.112:1019/api/web/index.php/v1";
    public static String domainName = "http://demo10.duceapps.com/api/web/index.php/v1";

    public static String language_code, mTermsandConditions;
    public static double latitude, longitude;
    private static String category_id, category_name, sub_category_name, vendor_id, vendor_name, parent_category_id, area_id,start_date, end_date;
    public static List<String> mVendorList = new ArrayList<String>();

    public static List<Category> mCategoryLists;

    public static String getDomainName() {
        return domainName;
    }

    public static void setDomainName(String domainName) {
        AppConfig.domainName = domainName;
    }

    public static String getLanguage_code() {
        return language_code;
    }

    public static void setLanguage_code(String language_code) {
        AppConfig.language_code = language_code;
    }

    public static String getStart_date() {
        return start_date;
    }

    public static void setStart_date(String start_date) {
        AppConfig.start_date = start_date;
    }

    public static String getEnd_date() {
        return end_date;
    }

    public static void setEnd_date(String end_date) {
        AppConfig.end_date = end_date;
    }

    public static String getCategory_id() {
        return category_id;

    }

    public static void setCategory_id(String category_id) {
        AppConfig.category_id = category_id;
    }

    public static List<Category> getmCategoryLists() {
        return mCategoryLists;
    }

    public static void setmCategoryLists(List<Category> mCategoryLists) {
        AppConfig.mCategoryLists = mCategoryLists;
    }

    public static String getCategory_name() {
        return category_name;
    }

    public static void setCategory_name(String category_name) {
        AppConfig.category_name = category_name;
    }

    public static String getSub_category_name() {
        return sub_category_name;
    }

    public static void setSub_category_name(String sub_category_name) {
        AppConfig.sub_category_name = sub_category_name;
    }

    public static String getVendor_id() {
        return vendor_id;
    }

    public static void setVendor_id(String vendor_id) {
        AppConfig.vendor_id = vendor_id;
    }

    public static String getVendor_name() {
        return vendor_name;
    }

    public static void setVendor_name(String vendor_name) {
        AppConfig.vendor_name = vendor_name;
    }

    public static String getParent_category_id() {
        return parent_category_id;
    }

    public static void setParent_category_id(String parent_category_id) {
        AppConfig.parent_category_id = parent_category_id;
    }

    public static List<String> getmVendorList() {
        return mVendorList;
    }

    public static void setmVendorList(List<String> mVendorList) {
        AppConfig.mVendorList = mVendorList;
    }

    public static String getArea_id() {
        return area_id;
    }

    public static void setArea_id(String area_id) {
        AppConfig.area_id = area_id;
    }
}
