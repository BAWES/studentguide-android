package com.techno.studentguide.model;

import com.techno.studentguide.api.ApiCallBaseConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by tech on 5/25/2016.
 */
public class VendorListApi {
    private static VendorListApi ourInstance = new VendorListApi();

    public static VendorListApi getInstance() {
        return ourInstance;
    }

    private VendorListApi() {
    }

    public void Callresponse(String language, Callback<VendorDetailList> mCallback) {
        Vendorapi mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(Vendorapi.class);
        mGitapi.mVendor(language, mCallback);
    }

    public interface Vendorapi {
        @GET("/vendors")
        public void mVendor(@Query("language") String language, Callback<VendorDetailList> response);
    }

    public class Data {

        private List<Vendor> vendor = new ArrayList<Vendor>();
        private String key;
        private Integer cache;

        /**
         * @return The vendor
         */
        public List<Vendor> getVendor() {
            return vendor;
        }

        /**
         * @param vendor The vendor
         */
        public void setVendor(List<Vendor> vendor) {
            this.vendor = vendor;
        }

        /**
         * @return The key
         */
        public String getKey() {
            return key;
        }

        /**
         * @param key The key
         */
        public void setKey(String key) {
            this.key = key;
        }

        /**
         * @return The cache
         */
        public Integer getCache() {
            return cache;
        }

        /**
         * @param cache The cache
         */
        public void setCache(Integer cache) {
            this.cache = cache;
        }

    }

    public class Vendor {

        private String logo;
        private String vendor_id;
        private String vendor_logo;
        private String vendor_name_en;
        private String vendor_name_ar;
        private String vendor_description_en;
        private String vendor_description_ar;
        private String vendor_phone1;
        private String vendor_phone2;
        private String vendor_youtube_video;
        private String vendor_social_instagram;
        private String vendor_social_twitter;
        private String vendor_location;
        private String vendor_address_text_en;
        private String vendor_address_text_ar;
        private String vendor_account_start_date;
        private String vendor_account_end_date;
        private String sort_order;

        /**
         * @return The logo
         */
        public String getLogo() {
            return logo;
        }

        /**
         * @param logo The logo
         */
        public void setLogo(String logo) {
            this.logo = logo;
        }

        /**
         * @return The vendor_id
         */
        public String getVendor_id() {
            return vendor_id;
        }

        /**
         * @param vendor_id The vendor_id
         */
        public void setVendor_id(String vendor_id) {
            this.vendor_id = vendor_id;
        }

        /**
         * @return The vendor_logo
         */
        public String getVendor_logo() {
            return vendor_logo;
        }

        /**
         * @param vendor_logo The vendor_logo
         */
        public void setVendor_logo(String vendor_logo) {
            this.vendor_logo = vendor_logo;
        }

        /**
         * @return The vendor_name_en
         */
        public String getVendor_name_en() {
            return vendor_name_en;
        }

        /**
         * @param vendor_name_en The vendor_name_en
         */
        public void setVendor_name_en(String vendor_name_en) {
            this.vendor_name_en = vendor_name_en;
        }

        /**
         * @return The vendor_name_ar
         */
        public String getVendor_name_ar() {
            return vendor_name_ar;
        }

        /**
         * @param vendor_name_ar The vendor_name_ar
         */
        public void setVendor_name_ar(String vendor_name_ar) {
            this.vendor_name_ar = vendor_name_ar;
        }

        /**
         * @return The vendor_description_en
         */
        public String getVendor_description_en() {
            return vendor_description_en;
        }

        /**
         * @param vendor_description_en The vendor_description_en
         */
        public void setVendor_description_en(String vendor_description_en) {
            this.vendor_description_en = vendor_description_en;
        }

        /**
         * @return The vendor_description_ar
         */
        public String getVendor_description_ar() {
            return vendor_description_ar;
        }

        /**
         * @param vendor_description_ar The vendor_description_ar
         */
        public void setVendor_description_ar(String vendor_description_ar) {
            this.vendor_description_ar = vendor_description_ar;
        }

        /**
         * @return The vendor_phone1
         */
        public String getVendor_phone1() {
            return vendor_phone1;
        }

        /**
         * @param vendor_phone1 The vendor_phone1
         */
        public void setVendor_phone1(String vendor_phone1) {
            this.vendor_phone1 = vendor_phone1;
        }

        /**
         * @return The vendor_phone2
         */
        public String getVendor_phone2() {
            return vendor_phone2;
        }

        /**
         * @param vendor_phone2 The vendor_phone2
         */
        public void setVendor_phone2(String vendor_phone2) {
            this.vendor_phone2 = vendor_phone2;
        }

        /**
         * @return The vendor_youtube_video
         */
        public String getVendor_youtube_video() {
            return vendor_youtube_video;
        }

        /**
         * @param vendor_youtube_video The vendor_youtube_video
         */
        public void setVendor_youtube_video(String vendor_youtube_video) {
            this.vendor_youtube_video = vendor_youtube_video;
        }

        /**
         * @return The vendor_social_instagram
         */
        public String getVendor_social_instagram() {
            return vendor_social_instagram;
        }

        /**
         * @param vendor_social_instagram The vendor_social_instagram
         */
        public void setVendor_social_instagram(String vendor_social_instagram) {
            this.vendor_social_instagram = vendor_social_instagram;
        }

        /**
         * @return The vendor_social_twitter
         */
        public String getVendor_social_twitter() {
            return vendor_social_twitter;
        }

        /**
         * @param vendor_social_twitter The vendor_social_twitter
         */
        public void setVendor_social_twitter(String vendor_social_twitter) {
            this.vendor_social_twitter = vendor_social_twitter;
        }

        /**
         * @return The vendor_location
         */
        public String getVendor_location() {
            return vendor_location;
        }

        /**
         * @param vendor_location The vendor_location
         */
        public void setVendor_location(String vendor_location) {
            this.vendor_location = vendor_location;
        }

        /**
         * @return The vendor_address_text_en
         */
        public String getVendor_address_text_en() {
            return vendor_address_text_en;
        }

        /**
         * @param vendor_address_text_en The vendor_address_text_en
         */
        public void setVendor_address_text_en(String vendor_address_text_en) {
            this.vendor_address_text_en = vendor_address_text_en;
        }

        /**
         * @return The vendor_address_text_ar
         */
        public String getVendor_address_text_ar() {
            return vendor_address_text_ar;
        }

        /**
         * @param vendor_address_text_ar The vendor_address_text_ar
         */
        public void setVendor_address_text_ar(String vendor_address_text_ar) {
            this.vendor_address_text_ar = vendor_address_text_ar;
        }

        /**
         * @return The vendor_account_start_date
         */
        public String getVendor_account_start_date() {
            return vendor_account_start_date;
        }

        /**
         * @param vendor_account_start_date The vendor_account_start_date
         */
        public void setVendor_account_start_date(String vendor_account_start_date) {
            this.vendor_account_start_date = vendor_account_start_date;
        }

        /**
         * @return The vendor_account_end_date
         */
        public String getVendor_account_end_date() {
            return vendor_account_end_date;
        }

        /**
         * @param vendor_account_end_date The vendor_account_end_date
         */
        public void setVendor_account_end_date(String vendor_account_end_date) {
            this.vendor_account_end_date = vendor_account_end_date;
        }

        /**
         * @return The sort_order
         */
        public String getSort_order() {
            return sort_order;
        }

        /**
         * @param sort_order The sort_order
         */
        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }

    }

    public class VendorDetailList {

        private Integer code;
        private String message;
        private Data data;

        /**
         * @return The code
         */
        public Integer getCode() {
            return code;
        }

        /**
         * @param code The code
         */
        public void setCode(Integer code) {
            this.code = code;
        }

        /**
         * @return The message
         */
        public String getMessage() {
            return message;
        }

        /**
         * @param message The message
         */
        public void setMessage(String message) {
            this.message = message;
        }

        /**
         * @return The data
         */
        public Data getData() {
            return data;
        }

        /**
         * @param data The data
         */
        public void setData(Data data) {
            this.data = data;
        }

    }
}
