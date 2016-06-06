package com.techno.studentguide.model;

import com.techno.studentguide.api.ApiCallBaseConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by tech on 5/26/2016.
 */
public class AreaLinksApi {
    private static AreaLinksApi ourInstance = new AreaLinksApi();

    public static AreaLinksApi getInstance() {
        return ourInstance;
    }

    private AreaLinksApi() {
    }


    public void Callresponse(Callback<AreaLinksLists> mCallback) {
        Vendorapi mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(Vendorapi.class);
        mGitapi.mVendor(mCallback);
    }

    public interface Vendorapi {
        @GET("/vendor/arealink")
        public void mVendor(Callback<AreaLinksLists> response);
    }

    public class AreaLinksLists {

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

    public class Area_link {

        private String link_id;
        private String vendor_id;
        private String area_id;

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
         * @return The area_id
         */
        public String getArea_id() {
            return area_id;
        }

        /**
         * @param area_id The area_id
         */
        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

    }

    public class Data {

        private List<Area_link> area_link = new ArrayList<Area_link>();
        private String key;
        private Integer cache;

        /**
         * @return The area_link
         */
        public List<Area_link> getArea_link() {
            return area_link;
        }

        /**
         * @param area_link The area_link
         */
        public void setArea_link(List<Area_link> area_link) {
            this.area_link = area_link;
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
