package com.techno.studentguide.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.onesignal.OneSignal;
import com.techno.studentguide.R;
import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.db.SQLConfig;
import com.techno.studentguide.model.AreaLinksApi;
import com.techno.studentguide.model.AreaListApi;
import com.techno.studentguide.model.CategoryLinksApi;
import com.techno.studentguide.model.CategoryListApi;
import com.techno.studentguide.model.LanguageApi;
import com.techno.studentguide.model.SettingsDetailsApi;
import com.techno.studentguide.model.VendorGalleryApi;
import com.techno.studentguide.model.VendorListApi;
import com.techno.studentguide.utils.CommonFunctions;
import com.techno.studentguide.utils.ControllerClass;
import com.techno.studentguide.utils.GPSTracker;
import com.techno.studentguide.utils.LocalContactDB;

import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ChooseLanguage extends AppCompatActivity implements View.OnClickListener {
    // Custom Buttons for English and Arabic
    Button vEnglish, vArabic;

    // Custom TextView for Terms and Conditions
    TextView vTermsConditions;
    SQLConfig mSQLConfig;  // local db config
    public static String android_id, player_id;   // device_id
    public static String device_type;   // mobile_type
    ProgressDialog mProgressDialog;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String category_key = "category_key";
    public static final String area_key = "area_key";
    public static final String vendor_key = "vendor_key";
    public static final String terms_conditions = "terms_conditions";
    static int languageSelected = -1;
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from activity_choose_language.xml
        setContentView(R.layout.activity_choose_language);
        // Find your IDS in your activity_choose_language.xml layout
        initView();
        //Click listener for change language : English and Arabic
        vEnglish.setOnClickListener(this);
        vArabic.setOnClickListener(this);
        //Click listener for Terms and conditions
        vTermsConditions.setOnClickListener(this);


        android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);  /*device token*/
        Log.i("device id", android_id + "id");
        device_type = "1";    /*Device type Android for 2 and IOS for 1*/
        // check if GPS enabled


        UserID();
        GPSTracker gpsTracker = new GPSTracker(this);
        if (gpsTracker.getIsGPSTrackingEnabled()) {
            AppConfig.latitude = gpsTracker.getLatitude();
            AppConfig.longitude = gpsTracker.getLongitude();
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] PERMISSIONS = {Manifest.permission.CALL_PHONE};
            if (!hasPermissions(ChooseLanguage.this, PERMISSIONS)) {
                ActivityCompat.requestPermissions(ChooseLanguage.this, PERMISSIONS, PERMISSION_REQUEST_CODE);
            }
        }

        mProgressDialog = new ProgressDialog(ChooseLanguage.this);  // Progress dialog handles progress for running background UI threads
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                player_id = userId;
                if (CommonFunctions.getInstance().isNetworkAvailable(ChooseLanguage.this)) {
                    showSettings(userId);   // calls settings Api
                    mProgressDialog.dismiss();
                } else {
                    mProgressDialog.dismiss();
                }

            }
        });
        if (languageSelected == 1) {
            findViewById(R.id.CL_btn_english).setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_normal));
            findViewById(R.id.CL_btn_arabic).setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_english_colored));
        } else if (languageSelected == 0) {
            findViewById(R.id.CL_btn_english).setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_english_colored));
            findViewById(R.id.CL_btn_arabic).setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_normal));
        } else {
            findViewById(R.id.CL_btn_english).setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_normal));
            findViewById(R.id.CL_btn_arabic).setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_normal));
        }
    }

    // Initialize the view
    private void initView() {
        vEnglish = (Button) findViewById(R.id.CL_btn_english);
        Typeface custom_english = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");
        vEnglish.setTypeface(custom_english);
        vArabic = (Button) findViewById(R.id.CL_btn_arabic);
        Typeface custom_arabic = Typeface.createFromAsset(getAssets(), "fonts/DroidKufi-Bold.ttf");
        vArabic.setTypeface(custom_arabic);
        vTermsConditions = (TextView) findViewById(R.id.CL_tv_termscondition);
        Typeface custom_TermsConditions = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");
        vEnglish.setTypeface(custom_TermsConditions);
        mSQLConfig = new SQLConfig(ChooseLanguage.this);
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
    }

    // Implement the click events
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.CL_btn_english) { // choose language bsaed on code "en"
            Chooselanguage("en");
            AppConfig.setLanguage_code(Chooselanguage("en"));
            findViewById(R.id.CL_btn_english).setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_english_colored));
            findViewById(R.id.CL_btn_arabic).setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_normal));
            languageSelected = 0;
            MyApplication.getInstance().trackEvent("English", "Click", "Choose Language");
            CategoryLocalDB();
            ChangeLanguageSettings();
        } else if (v.getId() == R.id.CL_btn_arabic) {     // choose language bsaed on code "ar"
            Chooselanguage("ar");
            AppConfig.setLanguage_code(Chooselanguage("ar"));
            findViewById(R.id.CL_btn_english).setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_normal));
            findViewById(R.id.CL_btn_arabic).setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_english_colored));
            forceRTLIfSupported();   // changes layout from left to right
            languageSelected = 1;
            MyApplication.getInstance().trackEvent("Arabic", "Click", "Choose Language");
            CategoryLocalDB();
            ChangeLanguageSettings();
        } else if (v.getId() == R.id.CL_tv_termscondition) {    // click fires terms and conditions
            mProgressDialog.dismiss();
            ControllerClass.CallTerms(ChooseLanguage.this, TermsConditionsActivity.class);  // call terms and conditions page
        }

    }

    private void ChangeLanguageSettings() {
        mProgressDialog.dismiss();
        LanguageApi.getInstance().Callresponse(UserID(), AppConfig.getLanguage_code(), new Callback<LanguageApi.LanguageChanged>() {
            @Override
            public void success(LanguageApi.LanguageChanged languageChanged, Response response) {
                if (languageChanged.getCode() == 200) {
                    Log.i("Language", " Sucess" + "200");
                } else {
                    Log.i("Language", languageChanged.getMessage() + languageChanged.getCode());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });

    }

    private void showSettings(String userId) {
        if (CommonFunctions.getInstance().isNetworkAvailable(ChooseLanguage.this)) {
            // checks wheather internet connection is enable or not condition.
            SettingsDetailsApi.getInstance().Callresponse(device_type, userId, new Callback<SettingsDetailsApi.SettingsApi>() {  // settings Api
                        @Override
                        public void success(SettingsDetailsApi.SettingsApi settingsApi, Response response) {
                            if (settingsApi.getCode() == 200) { // success
                                SharedPreferences.Editor editor = sharedpreferences.edit();     // Shared Preferences is used for store values likes keys and value pairs
                                String mCategoryKey = sharedpreferences.getString(category_key, null);   // get stored category value
                                String mAreaKey = sharedpreferences.getString(area_key, null);   // get stored area value
                                String mVendorKey = sharedpreferences.getString(vendor_key, null);  // get stored vendor value
                                String mAPICatergory = settingsApi.getData().getKeys().getCategory_key();
                                String mAPIArea = settingsApi.getData().getKeys().getArea_key();
                                String mAPIVendor = settingsApi.getData().getKeys().getVendor_key();
                                if (mCategoryKey == null && mAreaKey == null && mVendorKey == null) {
                                    editor.putString(category_key, mAPICatergory); // store category value
                                    editor.putString(area_key, mAPIArea); // store area value
                                    editor.putString(vendor_key, mAPIVendor); // store vendor value
                                    editor.commit();  // save session
                                    new CategoryApiCall().execute();      // CategoryList Api
                                    new AreaListThread().execute();      // AreaList Api
                                    new VendorThread().execute();  // VendorApi
                                    new CategoryLinkThread().execute();  // CategoryLink Api
                                    new AreaLinkListThread().execute();  // AreaLinkListApi
                                    new VendorGalleryThread().execute();// VendorGallery Api
                                    mProgressDialog.dismiss();
                                } else if (!mCategoryKey.equalsIgnoreCase(mAPICatergory) && !mVendorKey.equalsIgnoreCase(mAPIVendor) && !mAreaKey.equalsIgnoreCase(mAPIArea)) {
                                    editor.putString(category_key, mAPICatergory); // store category value
                                    editor.putString(area_key, mAPIArea); // store area value
                                    editor.putString(vendor_key, mAPIVendor); // store vendor value
                                    editor.commit();  // save session
                                    SQLConfig.mCategoryDao.deleteAll();
                                    SQLConfig.mVendorCategoryLinkDao.deleteAll();
                                    SQLConfig.mVendorDao.deleteAll();
                                    SQLConfig.mVendorPhotoDao.deleteAll();
                                    SQLConfig.mVendorAreaListDao.deleteAll();
                                    SQLConfig.mVendorAreaLinkDao.deleteAll();
                                    new CategoryApiCall().execute();      // CategoryList Api
                                    new CategoryLinkThread().execute();  // CategoryLink Api
                                    new AreaListThread().execute();      // AreaList Api
                                    new AreaLinkListThread().execute();  // AreaLinkListApi
                                    new VendorGalleryThread().execute();// VendorGallery Api
                                    new VendorThread().execute();  // VendorApi
                                    mProgressDialog.dismiss();
                                } else if (!mCategoryKey.equalsIgnoreCase(mAPICatergory) && !mVendorKey.equalsIgnoreCase(mAPIVendor)) {
                                    editor.putString(category_key, mAPICatergory); // store category value
                                    editor.putString(vendor_key, mAPIVendor); // store vendor value
                                    editor.commit();  // save session
                                    SQLConfig.mCategoryDao.deleteAll();
                                    SQLConfig.mVendorCategoryLinkDao.deleteAll();
                                    SQLConfig.mVendorDao.deleteAll();
                                    SQLConfig.mVendorPhotoDao.deleteAll();
                                    SQLConfig.mVendorAreaLinkDao.deleteAll();
                                    new CategoryApiCall().execute();      // CategoryList Api
                                    new CategoryLinkThread().execute();  // CategoryLink Api
                                    new VendorGalleryThread().execute();// VendorGallery Api
                                    new VendorThread().execute();  // VendorApi
                                    new AreaLinkListThread().execute();  // AreaLinkListApi
                                    mProgressDialog.dismiss();
                                } else if (!mCategoryKey.equalsIgnoreCase(mAPICatergory) && !mAreaKey.equalsIgnoreCase(mAPIArea)) {
                                    editor.putString(category_key, mAPICatergory); // store category value
                                    editor.putString(area_key, mAPIArea); // store area value
                                    editor.commit();  // save session
                                    SQLConfig.mCategoryDao.deleteAll();
                                    SQLConfig.mVendorCategoryLinkDao.deleteAll();
                                    SQLConfig.mVendorAreaListDao.deleteAll();
                                    SQLConfig.mVendorAreaLinkDao.deleteAll();
                                    new CategoryApiCall().execute();      // CategoryList Api
                                    new CategoryLinkThread().execute();  // CategoryLink Api
                                    new AreaListThread().execute();      // AreaList Api
                                    new AreaLinkListThread().execute();  // AreaLinkListApi
                                    CategoryLocalDB();
                                    mProgressDialog.dismiss();
                                } else if (!mVendorKey.equalsIgnoreCase(mAPIVendor) && !mAreaKey.equalsIgnoreCase(mAPIArea)) {
                                    editor.putString(vendor_key, mAPIVendor); // store vendor value
                                    editor.putString(area_key, mAPIArea); // store area value
                                    editor.commit();  // save session
                                    SQLConfig.mVendorAreaListDao.deleteAll();
                                    SQLConfig.mVendorAreaLinkDao.deleteAll();
                                    SQLConfig.mVendorDao.deleteAll();
                                    SQLConfig.mVendorPhotoDao.deleteAll();
                                    SQLConfig.mVendorCategoryLinkDao.deleteAll();
                                    new AreaListThread().execute();      // AreaList Api
                                    new AreaLinkListThread().execute();  // AreaLinkListApi
                                    new VendorGalleryThread().execute();// VendorGallery Api
                                    new VendorThread().execute();  // VendorApi
                                    new CategoryLinkThread().execute();  // CategoryLink Api
                                    mProgressDialog.dismiss();
                                } else if (!mCategoryKey.equalsIgnoreCase(mAPICatergory)) {
                                    editor.putString(category_key, mAPICatergory); // store category value
                                    editor.commit();  // save session
                                    SQLConfig.mCategoryDao.deleteAll();
                                    SQLConfig.mVendorCategoryLinkDao.deleteAll();
                                    new CategoryApiCall().execute();      // CategoryList Api
                                    new CategoryLinkThread().execute();  // CategoryLink Api
                                    CategoryLocalDB();
                                    mProgressDialog.dismiss();
                                } else if (!mVendorKey.equalsIgnoreCase(mAPIVendor)) {
                                    editor.putString(vendor_key, mAPIVendor); // store vendor value
                                    editor.commit();  // save session
                                    SQLConfig.mVendorDao.deleteAll();
                                    SQLConfig.mVendorPhotoDao.deleteAll();
                                    SQLConfig.mVendorAreaLinkDao.deleteAll();
                                    SQLConfig.mVendorCategoryLinkDao.deleteAll();
                                    new VendorGalleryThread().execute();// VendorGallery Api
                                    new VendorThread().execute();  // VendorApi
                                    new AreaLinkListThread().execute();  // AreaLinkListApi
                                    new CategoryLinkThread().execute();  // CategoryLink Api
                                } else if (!mAreaKey.equalsIgnoreCase(mAPIArea)) {
                                    editor.putString(area_key, mAPIArea); // store area value
                                    editor.commit();  // save session
                                    SQLConfig.mVendorAreaListDao.deleteAll();
                                    SQLConfig.mVendorAreaLinkDao.deleteAll();
                                    new AreaListThread().execute();      // AreaList Api
                                    new AreaLinkListThread().execute();  // AreaLinkListApi
                                    mProgressDialog.dismiss();
                                }
                                editor.putString(terms_conditions, settingsApi.getData().getTerms()); // store area value
                                editor.commit();  // save session
                            } else {
                                mProgressDialog.dismiss();
                                Toast.makeText(ChooseLanguage.this, settingsApi.getMessage(), Toast.LENGTH_SHORT).show();   // error toast message fires
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            error.printStackTrace();
                            mProgressDialog.dismiss();
                        }
                    }

            );
        } else {
            mProgressDialog.dismiss();
            CategoryLocalDB();  // without internet connection local db called.
        }
    }

    // CategoryLocalDB() fires local db
    private void CategoryLocalDB() {
        if (LocalContactDB.GetCategory().size() > 0) {
            startActivity(new Intent(ChooseLanguage.this, CategoryActivity.class));
        } else {
            Toast.makeText(ChooseLanguage.this, "Category list is empty", Toast.LENGTH_SHORT).show();
        }
    }

    //This method handles language based on layout view changes. i.e. Right to left  and Left to right
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }


    //This method fires language code based on mobile locale settings to set
    public String Chooselanguage(String languageCode) {
        String languageToLoad = languageCode;
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        return languageToLoad;
    }


    private class VendorThread extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            if (CommonFunctions.getInstance().isNetworkAvailable(ChooseLanguage.this)) {
                VendorListApi.getInstance().Callresponse(AppConfig.getLanguage_code(), new Callback<VendorListApi.VendorDetailList>() {
                    @Override
                    public void success(VendorListApi.VendorDetailList vendorDetailList, Response response) {
                        if (vendorDetailList.getData().getVendor().size() > 0) {
                            for (int vendor = 0; vendor < vendorDetailList.getData().getVendor().size(); vendor++) {
                                LocalContactDB.addVendor(vendorDetailList.getData().getVendor().get(vendor).getVendor_id(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_logo(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_name_en(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_name_ar(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_description_en(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_description_ar(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_phone1(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_phone2(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_youtube_video(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_social_instagram(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_social_twitter(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_location(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_address_text_en(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_address_text_ar(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_account_start_date(),
                                        vendorDetailList.getData().getVendor().get(vendor).getVendor_account_end_date(),
                                        vendorDetailList.getData().getVendor().get(vendor).getSort_order()
                                );

                            }
                        } else {
                            Toast.makeText(ChooseLanguage.this, "list is empty", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                    }
                });

            }
            return null;
        }
    }

    private class AreaListThread extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            if (CommonFunctions.getInstance().isNetworkAvailable(ChooseLanguage.this)) {
                AreaListApi.getInstance().Callresponse(AppConfig.getLanguage_code(), new Callback<AreaListApi.AreaDetailsLists>() {
                    @Override
                    public void success(AreaListApi.AreaDetailsLists areaDetailsLists, Response response) {
                        if (areaDetailsLists.getCode() == 200) {
                            for (int arealist = 0; arealist < areaDetailsLists.getData().getVendors().size(); arealist++) {
                                LocalContactDB.addAreaList(areaDetailsLists.getData().getVendors().get(arealist).getId(),
                                        areaDetailsLists.getData().getVendors().get(arealist).getArea_name_en(),
                                        areaDetailsLists.getData().getVendors().get(arealist).getArea_name_ar());
                            }
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                    }
                });

            }

            return null;
        }
    }


    private class AreaLinkListThread extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            if (CommonFunctions.getInstance().isNetworkAvailable(ChooseLanguage.this)) {
                AreaLinksApi.getInstance().Callresponse(new Callback<AreaLinksApi.AreaLinksLists>() {
                    @Override
                    public void success(AreaLinksApi.AreaLinksLists areaLinksLists, Response response) {
                        if (areaLinksLists.getCode() == 200) {
                            for (int areaLink = 0; areaLink < areaLinksLists.getData().getArea_link().size(); areaLink++) {
                                LocalContactDB.addAreaLinkLists(areaLinksLists.getData().getArea_link().get(areaLink).getLink_id(),
                                        areaLinksLists.getData().getArea_link().get(areaLink).getVendor_id(),
                                        areaLinksLists.getData().getArea_link().get(areaLink).getArea_id());
                            }

                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                    }
                });
            }


            return null;
        }
    }

    private class CategoryLinkThread extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            CategoryLinksApi.getInstance().Callresponse(new Callback<CategoryLinksApi.CategoryListLink>() {
                @Override
                public void success(CategoryLinksApi.CategoryListLink categoryListLink, Response response) {
                    if (categoryListLink.getCode() == 200) {
                        for (int categoryLinks = 0; categoryLinks < categoryListLink.getData().getCategory_link().size(); categoryLinks++) {
                            CategoryLinksApi.Category_link mCategoryLinks = categoryListLink.getData().getCategory_link().get(categoryLinks);
                            LocalContactDB.addCategoryListLinks(mCategoryLinks.getLink_id(), mCategoryLinks.getVendor_id(), mCategoryLinks.getCategory_id());
                        }
                    } else {
                        Toast.makeText(ChooseLanguage.this, categoryListLink.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    error.printStackTrace();
                }
            });

            return null;
        }
    }

    private class VendorGalleryThread extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            if (CommonFunctions.getInstance().isNetworkAvailable(ChooseLanguage.this)) {
                VendorGalleryApi.getInstance().Callresponse(new Callback<VendorGalleryApi.VendorGalleryDetails>() {
                    @Override
                    public void success(VendorGalleryApi.VendorGalleryDetails vendorGalleryDetails, Response response) {
                        if (vendorGalleryDetails.getCode() == 200) {
                            for (int gallery = 0; gallery < vendorGalleryDetails.getData().getVendor_gallery().size(); gallery++) {
                                LocalContactDB.addVendorGallery(vendorGalleryDetails.getData().getVendor_gallery().get(gallery).getPhoto(),
                                        vendorGalleryDetails.getData().getVendor_gallery().get(gallery).getVendor_id(),
                                        vendorGalleryDetails.getData().getVendor_gallery().get(gallery).getGallery_id());
                            }
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                    }
                });
            }
            return null;
        }
    }

    private class CategoryApiCall extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            if (CommonFunctions.getInstance().isNetworkAvailable(ChooseLanguage.this)) {
                CategoryListApi.getInstance().Callresponse(AppConfig.getLanguage_code(), new Callback<CategoryListApi.CategoryDetailsList>() {
                    @Override
                    public void success(CategoryListApi.CategoryDetailsList categoryDetailsList, Response response) {
                        if (categoryDetailsList.getCode() == 200) {
                            for (int category = 0; category < categoryDetailsList.getData().getCategories().size(); category++) {
                                LocalContactDB.add(categoryDetailsList.getData().getCategories().get(category).getCategory_id(),
                                        categoryDetailsList.getData().getCategories().get(category).getCategory_name_en(),
                                        categoryDetailsList.getData().getCategories().get(category).getCategory_name_ar(),
                                        categoryDetailsList.getData().getCategories().get(category).getParent_category_id(),
                                        categoryDetailsList.getData().getCategories().get(category).getCategory_vendors_filterable_by_area().equalsIgnoreCase("0") ? false : true,
                                        "",
                                        "",
                                        "");
                            }
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mProgressDialog.dismiss();
                    }
                });

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            LocalContactDB.GetCategory();
        }
    }


    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    public boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }

        }
        return true;
    }

    public String UserID() {

        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                Log.d("debug", "User:" + userId);
                player_id = userId;

                if (registrationId != null) {
                    Log.d("debug", "registrationId:" + registrationId);

                }

            }
        });
        return player_id;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        languageSelected = -1;
        finish();
    }
}
