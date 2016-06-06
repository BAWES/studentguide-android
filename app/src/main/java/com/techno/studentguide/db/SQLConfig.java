package com.techno.studentguide.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;



public class SQLConfig {
    public static DaoMaster.DevOpenHelper helper;
    public static SQLiteDatabase db;
    public static DaoMaster daoMaster;
    public static DaoSession daoSession;
    public static CategoryDao mCategoryDao;
    public static VendorDao mVendorDao;
    public static VendorAreaListDao mVendorAreaListDao;
    public static VendorPhotoDao mVendorPhotoDao;
    public static VendorCategoryLinkDao mVendorCategoryLinkDao;
    public static VendorAreaLinkDao mVendorAreaLinkDao;
    public static VendorViewDao mVendorViewDao;




    public SQLConfig(Context context) {

        try {
            helper = new DaoMaster.DevOpenHelper(context, "studentguide", null);
            db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
            mCategoryDao = daoSession.getCategoryDao();
            mVendorDao = daoSession.getVendorDao();
            mVendorAreaLinkDao=daoSession.getVendorAreaLinkDao();
            mVendorAreaListDao= daoSession.getVendorAreaListDao();
            mVendorPhotoDao = daoSession.getVendorPhotoDao();
            mVendorCategoryLinkDao = daoSession.getVendorCategoryLinkDao();
            mVendorViewDao= daoSession.getVendorViewDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
