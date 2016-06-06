package com.techno.studentguide.model;

import com.techno.studentguide.api.ApiCallBaseConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Android on 5/14/2016.
 */
public class CategoryListApi {
    private static CategoryListApi ourInstance = new CategoryListApi();

    public static CategoryListApi getInstance() {
        return ourInstance;
    }

    private CategoryListApi() {
    }

    public void Callresponse(String language, Callback<CategoryDetailsList> mCallback) {
        Categoryapi mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(Categoryapi.class);
        mGitapi.Category(language, mCallback);
    }

    public void SubCategoryresponse(String language, String categoryid, Callback<CategoryDetailsList> mCallback) {
        subCategoryapi mGitapi = ApiCallBaseConfiguration.getInstance().getApiBuilder().create(subCategoryapi.class);
        mGitapi.subategory(language, categoryid, mCallback);
    }

    public interface Categoryapi {
        @GET("/categories")
        public void Category(@Query("language") String language, Callback<CategoryDetailsList> response);
    }

    public interface subCategoryapi {
        @GET("/categories")
        public void subategory(@Query("language") String language, @Query("category") String subcategory, Callback<CategoryDetailsList> response);
    }

    public class Category {

        private String category_id;
        private String parent_category_id;
        private String category_name_en;
        private String category_name_ar;
        private String category_vendors_filterable_by_area;

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

        /**
         * @return The parent_category_id
         */
        public String getParent_category_id() {
            return parent_category_id;
        }

        /**
         * @param parent_category_id The parent_category_id
         */
        public void setParent_category_id(String parent_category_id) {
            this.parent_category_id = parent_category_id;
        }

        /**
         * @return The category_name_en
         */
        public String getCategory_name_en() {
            return category_name_en;
        }

        /**
         * @param category_name_en The category_name_en
         */
        public void setCategory_name_en(String category_name_en) {
            this.category_name_en = category_name_en;
        }

        /**
         * @return The category_name_ar
         */
        public String getCategory_name_ar() {
            return category_name_ar;
        }

        /**
         * @param category_name_ar The category_name_ar
         */
        public void setCategory_name_ar(String category_name_ar) {
            this.category_name_ar = category_name_ar;
        }

        /**
         * @return The category_vendors_filterable_by_area
         */
        public String getCategory_vendors_filterable_by_area() {
            return category_vendors_filterable_by_area;
        }

        /**
         * @param category_vendors_filterable_by_area The category_vendors_filterable_by_area
         */
        public void setCategory_vendors_filterable_by_area(String category_vendors_filterable_by_area) {
            this.category_vendors_filterable_by_area = category_vendors_filterable_by_area;
        }

    }

    public class CategoryDetailsList {

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

        private List<Category> categories = new ArrayList<Category>();
        private String key;
        private Integer cache;

        /**
         * @return The categories
         */
        public List<Category> getCategories() {
            return categories;
        }

        /**
         * @param categories The categories
         */
        public void setCategories(List<Category> categories) {
            this.categories = categories;
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
