package com.techno.studentguide.model;

/**
 * Created by Android on 5/13/2016.
 */
public class Category {

    private String Category_id;
    private String Parent_category_id;
    private String Category_name_en;
    private String Category_name_ar;
    private String Category_vendors_filterable_by_area;
    private String Category_created_datetime;
    private String Category_updated_datetime;

    public String getCategory_id() {
        return Category_id;
    }

    public void setCategory_id(String category_id) {
        Category_id = category_id;
    }

    public String getParent_category_id() {
        return Parent_category_id;
    }

    public void setParent_category_id(String parent_category_id) {
        Parent_category_id = parent_category_id;
    }

    public String getCategory_name_en() {
        return Category_name_en;
    }

    public void setCategory_name_en(String category_name_en) {
        Category_name_en = category_name_en;
    }

    public String getCategory_name_ar() {
        return Category_name_ar;
    }

    public void setCategory_name_ar(String category_name_ar) {
        Category_name_ar = category_name_ar;
    }

    public String getCategory_vendors_filterable_by_area() {
        return Category_vendors_filterable_by_area;
    }

    public void setCategory_vendors_filterable_by_area(String category_vendors_filterable_by_area) {
        Category_vendors_filterable_by_area = category_vendors_filterable_by_area;
    }

    public String getCategory_created_datetime() {
        return Category_created_datetime;
    }

    public void setCategory_created_datetime(String category_created_datetime) {
        Category_created_datetime = category_created_datetime;
    }

    public String getCategory_updated_datetime() {
        return Category_updated_datetime;
    }

    public void setCategory_updated_datetime(String category_updated_datetime) {
        Category_updated_datetime = category_updated_datetime;
    }
}
