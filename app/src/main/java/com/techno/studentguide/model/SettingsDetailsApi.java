package com.techno.studentguide.model;

import com.techno.studentguide.api.ApiCallBaseConfiguration;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by tech on 5/31/2016.
 */
public class SettingsDetailsApi {
    private static SettingsDetailsApi ourInstance = new SettingsDetailsApi();

    public static SettingsDetailsApi getInstance() {
        return ourInstance;
    }

    private SettingsDetailsApi() {
    }


    public void Callresponse(String device_type, String device_token, Callback<SettingsApi> mCallback) {
        SettingsInterface mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(SettingsInterface.class);
        mGitapi.mSettings(device_type, device_token, mCallback);
    }

    public interface SettingsInterface {
        @GET("/base/setting")
        public void mSettings(@Query("device_type") String device_type, @Query("device_token") String device_token, Callback<SettingsApi> response);
    }


    public class Data {

        private Keys keys;
        private String terms;

        /**
         * @return The keys
         */
        public Keys getKeys() {
            return keys;
        }

        /**
         * @param keys The keys
         */
        public void setKeys(Keys keys) {
            this.keys = keys;
        }

        /**
         * @return The terms
         */
        public String getTerms() {
            return terms;
        }

        /**
         * @param terms The terms
         */
        public void setTerms(String terms) {
            this.terms = terms;
        }

    }

    public class Keys {

        private String category_key;
        private String vendor_key;
        private String area_key;

        /**
         * @return The category_key
         */
        public String getCategory_key() {
            return category_key;
        }

        /**
         * @param category_key The category_key
         */
        public void setCategory_key(String category_key) {
            this.category_key = category_key;
        }

        /**
         * @return The vendor_key
         */
        public String getVendor_key() {
            return vendor_key;
        }

        /**
         * @param vendor_key The vendor_key
         */
        public void setVendor_key(String vendor_key) {
            this.vendor_key = vendor_key;
        }

        /**
         * @return The area_key
         */
        public String getArea_key() {
            return area_key;
        }

        /**
         * @param area_key The area_key
         */
        public void setArea_key(String area_key) {
            this.area_key = area_key;
        }

    }

    public class SettingsApi {

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
