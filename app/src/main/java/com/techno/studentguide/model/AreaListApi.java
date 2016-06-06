package com.techno.studentguide.model;

import com.techno.studentguide.api.ApiCallBaseConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by tech on 5/26/2016.
 */
public class AreaListApi {
    private static AreaListApi ourInstance = new AreaListApi();

    public static AreaListApi getInstance() {
        return ourInstance;
    }

    private AreaListApi() {
    }


    public void Callresponse(String language, Callback<AreaDetailsLists> mCallback) {
        Vendorapi mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(Vendorapi.class);
        mGitapi.mVendor(language, mCallback);
    }

    public interface Vendorapi {
        @GET("/vendor/arealist")
        public void mVendor(@Query("language") String language, Callback<AreaDetailsLists> response);
    }


    public class AreaDetailsLists {

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

    public class Data {

        private List<Vendor> vendors = new ArrayList<Vendor>();
        private String key;
        private Integer cache;

        /**
         * @return The vendors
         */
        public List<Vendor> getVendors() {
            return vendors;
        }

        /**
         * @param vendors The vendors
         */
        public void setVendors(List<Vendor> vendors) {
            this.vendors = vendors;
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

        private String id;
        private String area_name_en;
        private String area_name_ar;

        /**
         * @return The id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id The id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return The area_name_en
         */
        public String getArea_name_en() {
            return area_name_en;
        }

        /**
         * @param area_name_en The area_name_en
         */
        public void setArea_name_en(String area_name_en) {
            this.area_name_en = area_name_en;
        }

        /**
         * @return The area_name_ar
         */
        public String getArea_name_ar() {
            return area_name_ar;
        }

        /**
         * @param area_name_ar The area_name_ar
         */
        public void setArea_name_ar(String area_name_ar) {
            this.area_name_ar = area_name_ar;
        }

    }
}
