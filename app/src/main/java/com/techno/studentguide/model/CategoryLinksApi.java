package com.techno.studentguide.model;

import com.techno.studentguide.api.ApiCallBaseConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by tech on 5/25/2016.
 */
public class CategoryLinksApi {
    private static CategoryLinksApi ourInstance = new CategoryLinksApi();

    public static CategoryLinksApi getInstance() {
        return ourInstance;
    }

    private CategoryLinksApi() {
    }

    public void Callresponse(Callback<CategoryListLink> mCallback) {
        Vendorapi mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(Vendorapi.class);
        mGitapi.mVendor(mCallback);
    }

    public interface Vendorapi {
        @GET("/vendors/categorylink")
        public void mVendor(Callback<CategoryListLink> response);
    }

    public class CategoryListLink {

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

    public class Category_link {

        private String link_id;
        private String vendor_id;
        private String category_id;

        /**
         * @return The link_id
         */
        public String getLink_id() {
            return link_id;
        }

        /**
         * @param link_id The link_id
         */
        public void setLink_id(String link_id) {
            this.link_id = link_id;
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
         * @return The category_id
         */
        public String getCategory_id() {
            return category_id;
        }

        /**
         * @param category_id The category_id
         */
        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

    }

    public class Data {

        private List<Category_link> category_link = new ArrayList<Category_link>();
        private String key;
        private Integer cache;

        /**
         * @return The category_link
         */
        public List<Category_link> getCategory_link() {
            return category_link;
        }

        /**
         * @param category_link The category_link
         */
        public void setCategory_link(List<Category_link> category_link) {
            this.category_link = category_link;
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

}
