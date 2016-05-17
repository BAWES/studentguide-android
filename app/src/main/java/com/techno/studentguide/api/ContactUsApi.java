package com.techno.studentguide.api;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Android on 5/14/2016.
 */
public class ContactUsApi {
    private static ContactUsApi ourInstance = new ContactUsApi();

    public static ContactUsApi getInstance() {
        return ourInstance;
    }

    private ContactUsApi() {
    }

    public void Callresponse(Callback<ContactUsInformation> mCallback,String mName,String mContactNo,String mMessage)
    {
        SignInapi mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(SignInapi.class);
        mGitapi.ContactUs(mName, mContactNo, mMessage, mCallback);
    }
    public interface SignInapi {
        @FormUrlEncoded
        @POST("/contact_us.php")
        public void ContactUs(@Field("name") String name, @Field("contact_no") String contact_no, @Field("message") String message, Callback<ContactUsInformation> response);
    }

    public class ContactUsInformation extends ResponseBase {

        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }
    }
}
