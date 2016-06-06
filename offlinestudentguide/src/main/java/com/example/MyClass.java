package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {
    public static void main(String[] args) throws Exception {
        Schema mSchema = new Schema(1005, "com.techno.studentguide");
        AddCategory(mSchema);
        AddVendor(mSchema);
        VendorAreaList(mSchema);
        VendorCategoryLink(mSchema);
        VendorAreaLink(mSchema);
        VendorView(mSchema);
        VendorPhoto(mSchema);

        new DaoGenerator().generateAll(mSchema, "E:\\");
    }


    private static void VendorAreaList(Schema mSchema) {
        Entity note = mSchema.addEntity("VendorAreaList");
        note.addIdProperty();
        note.addStringProperty("area_id");
        note.addStringProperty("area_name_en");
        note.addStringProperty("area_name_ar");
    }

    private static void VendorPhoto(Schema mSchema) {
        Entity note = mSchema.addEntity("VendorPhoto");
        note.addIdProperty();
        note.addStringProperty("photo_id");
        note.addStringProperty("vendor_id");
        note.addStringProperty("photo_url");
        note.addStringProperty("photo_added_datetime");
    }

    private static void VendorView(Schema mSchema) {
        Entity note = mSchema.addEntity("VendorView");
        note.addIdProperty();
        note.addStringProperty("view_id");
        note.addStringProperty("vendor_id");
        note.addStringProperty("view_date");
        note.addStringProperty("view_number_of_views");
    }

    private static void VendorCategoryLink(Schema mSchema) {
        Entity note = mSchema.addEntity("VendorCategoryLink");
        note.addIdProperty();
        note.addStringProperty("link_id");
        note.addStringProperty("vendor_id");
        note.addStringProperty("category_id");
    }

    private static void VendorAreaLink(Schema mSchema) {
        Entity note = mSchema.addEntity("VendorAreaLink");
        note.addIdProperty();
        note.addStringProperty("link_id");
        note.addStringProperty("vendor_id");
        note.addStringProperty("area_id");
    }

    private static void AddCategory(Schema mSchema) {
        Entity note = mSchema.addEntity("Category");
        note.addIdProperty();
        note.addStringProperty("category_id");
        note.addStringProperty("parent_category_id");
        note.addStringProperty("name");
        note.addStringProperty("count");
        note.addStringProperty("category_name_en");
        note.addStringProperty("category_name_ar");
        note.addBooleanProperty("filter_by");
        note.addStringProperty("category_created_datetime");
        note.addStringProperty("category_updated_datetime");

    }

    private static void AddVendor(Schema mSchema) {
        Entity note = mSchema.addEntity("Vendor");
        note.addIdProperty();
        note.addStringProperty("vendor_id");
        note.addStringProperty("vendor_logo");
        note.addStringProperty("vendor_name_en");
        note.addStringProperty("vendor_name_ar");
        note.addStringProperty("vendor_description_en");
        note.addStringProperty("vendor_description_ar");
        note.addStringProperty("vendor_phone1");
        note.addStringProperty("vendor_phone2");
        note.addStringProperty("vendor_youtube_video");
        note.addStringProperty("vendor_social_instagram");
        note.addStringProperty("vendor_social_twitter");
        note.addStringProperty("vendor_location");
        note.addStringProperty("vendor_address_text_en");
        note.addStringProperty("vendor_address_text_ar");
        note.addStringProperty("vendor_account_start_date");
        note.addStringProperty("vendor_account_end_date");
        note.addStringProperty("sort_order");
    }

    private static void AddAreaList(Schema mSchema) {
        Entity note = mSchema.addEntity("AreaList");
        note.addIdProperty();
        note.addStringProperty("id");
        note.addStringProperty("area_name_en");
        note.addStringProperty("area_name_ar");
    }

}
