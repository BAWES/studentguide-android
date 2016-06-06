package com.techno.studentguide.model;

import com.techno.studentguide.api.ApiCallBaseConfiguration;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by tech on 5/25/2016.
 */
public class ContactUsApi {
    private static ContactUsApi ourInstance = new ContactUsApi();

    public static ContactUsApi getInstance() {
        return ourInstance;
    }

    private ContactUsApi() {
    }

    public void Callresponse(String username, String message, String mobile_number, Callback<FeedbackDetails> mCallback) {
        Vendorapi mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(Vendorapi.class);
        mGitapi.mVendor(username, message, mobile_number, mCallback);
    }

    public interface Vendorapi {
        @FormUrlEncoded
        @POST("/vendor/enquiry")
        public void mVendor(@Field("name") String username, @Field("message") String message,
                            @Field("mobile") String mobile_number, Callback<FeedbackDetails> response);
    }

    public class Data {

        private Integer status;

        /**
         * @return The status
         */
        public Integer getStatus() {
            return status;
        }

        /**
         * @param status The status
         */
        public void setStatus(Integer status) {
            this.status = status;
        }

    }

    public class FeedbackDetails {

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
