package com.techno.studentguide.model;

import com.techno.studentguide.api.ApiCallBaseConfiguration;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by tech on 5/31/2016.
 */
public class LanguageApi {
    private static LanguageApi ourInstance = new LanguageApi();

    public static LanguageApi getInstance() {
        return ourInstance;
    }

    private LanguageApi() {
    }

    public void Callresponse(String device_token, String language, Callback<LanguageChanged> mCallback) {
        ChangeLanguage mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(ChangeLanguage.class);
        mGitapi.mChangeLanguage(device_token, language, mCallback);
    }

    public interface ChangeLanguage {
        @GET("/base/change_language")
        public void mChangeLanguage(@Query("device_token") String device_token, @Query("language") String language, Callback<LanguageChanged> response);
    }

    public class Data {
    }

    public class LanguageChanged {

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
