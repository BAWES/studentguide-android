package com.techno.studentguide.model;

import com.techno.studentguide.api.ApiCallBaseConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by tech on 5/26/2016.
 */
public class VendorGalleryApi {
    private static VendorGalleryApi ourInstance = new VendorGalleryApi();

    public static VendorGalleryApi getInstance() {
        return ourInstance;
    }

    private VendorGalleryApi() {
    }

    public void Callresponse(Callback<VendorGalleryDetails> mCallback) {
        Galleryapi mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(Galleryapi.class);
        mGitapi.mGalleryapi(mCallback);
    }

    public interface Galleryapi {
        @GET("/vendors/vendorgallery")
        public void mGalleryapi(Callback<VendorGalleryDetails> response);
    }

    public class Data {

        private List<Vendor_gallery> vendor_gallery = new ArrayList<Vendor_gallery>();
        private String key;
        private Integer cache;

        /**
         * @return The vendor_gallery
         */
        public List<Vendor_gallery> getVendor_gallery() {
            return vendor_gallery;
        }

        /**
         * @param vendor_gallery The vendor_gallery
         */
        public void setVendor_gallery(List<Vendor_gallery> vendor_gallery) {
            this.vendor_gallery = vendor_gallery;
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

    public class VendorGalleryDetails {

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

    public class Vendor_gallery {

        private String photo;
        private String vendor_id;
        private String gallery_id;

        /**
         * @return The photo
         */
        public String getPhoto() {
            return photo;
        }

        /**
         * @param photo The photo
         */
        public void setPhoto(String photo) {
            this.photo = photo;
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
         * @return The gallery_id
         */
        public String getGallery_id() {
            return gallery_id;
        }

        /**
         * @param gallery_id The gallery_id
         */
        public void setGallery_id(String gallery_id) {
            this.gallery_id = gallery_id;
        }

    }
}
