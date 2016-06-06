package com.techno.studentguide.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import com.techno.studentguide.R;
import com.techno.studentguide.adapter.GalleryCoverFlowAdapter;
import com.techno.studentguide.api.AppConfig;
import com.techno.studentguide.customview.CustomTextView;
import com.techno.studentguide.db.Vendor;
import com.techno.studentguide.db.VendorPhoto;
import com.techno.studentguide.utils.CommonFunctions;
import com.techno.studentguide.utils.ControllerClass;
import com.techno.studentguide.utils.LocalContactDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class DetailsActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    GalleryCoverFlowAdapter aGalleryCoverFlowAdapter;
    FeatureCoverFlow mGalleryFlow;
    CustomTextView mHeading, mAddress, phoneNumber1, phoneNumber2, description, mGalleryNotFound, mVideoNotFound, mHeadingDesc, mHeadingGallery, mHeadingVideo;
    WebView mVendorVideo;
    ImageView mTwitter, mInstagram, mBack, mCall, mLocation, mVendorLogo, vHome, vMessage;
    List<VendorPhoto> mVendorPhotoList = new ArrayList<VendorPhoto>();
    List<Vendor> mVendorList = new ArrayList<Vendor>();
    public static final String API_KEY = "AIzaSyDwtI6LU_-utUnl3McgpcoIROQ6x1516dk";
    public String VIDEO_ID = null;
    // Google Map
    private GoogleMap googleMap;
    YouTubePlayerView youTubePlayerView;
    LinearLayout mAddressLayout, phoneNumberLayout;
    View line1, line2;
    FrameLayout mVideoFrame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppConfig.getLanguage_code().equalsIgnoreCase("en")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        }
        // Get the layout from activity_details.xml
        setContentView(R.layout.activity_details);
        // Find your IDS in your activity_details.xml layout
        initViews();

        if (AppConfig.getLanguage_code().equalsIgnoreCase("en")) {
            Typeface custom_english = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Bold.ttf");
            mHeading.setTypeface(custom_english);
            mHeading.setText(AppConfig.getVendor_name());
        } else {
            Typeface custom_arabic = Typeface.createFromAsset(getAssets(), "fonts/DroidKufi-Bold.ttf");
            mHeading.setTypeface(custom_arabic);
            mHeading.setText(AppConfig.getVendor_name());
        }

        /** Initializing YouTube player view **/
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);


        mVendorList = LocalContactDB.GetVendorList(AppConfig.getVendor_id());
        showVendorGallery(AppConfig.getVendor_id());
        initilizeMap();
        vHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Controller class handles another class name to call a class.
                ControllerClass.CallHome(DetailsActivity.this, ChooseLanguage.class);
            }
        });
        vMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerClass.CallContactus(DetailsActivity.this, ContactUsActivity.class);
            }
        });

        for (int vendorDetails = 0; vendorDetails < mVendorList.size(); vendorDetails++) {
            if (mVendorList.get(vendorDetails).getVendor_id().equalsIgnoreCase(AppConfig.getVendor_id())) {
                if (mVendorList.get(vendorDetails).getVendor_address_text_en().isEmpty() && mVendorList.get(vendorDetails).getVendor_address_text_ar().isEmpty()) {
                    mAddressLayout.setVisibility(View.GONE);
                } else {
                    mAddress.setText(AppConfig.getLanguage_code().equalsIgnoreCase("en") ? mVendorList.get(vendorDetails).getVendor_address_text_en() :
                            mVendorList.get(vendorDetails).getVendor_address_text_ar());
                }

                phoneNumber1.setText(mVendorList.get(vendorDetails).getVendor_phone1());


                if (mVendorList.get(vendorDetails).getVendor_phone2().isEmpty()) {
                    phoneNumberLayout.setVisibility(View.GONE);
                } else {
                    phoneNumber2.setText(mVendorList.get(vendorDetails).getVendor_phone2());
                }

                if (mVendorList.get(vendorDetails).getVendor_description_en().isEmpty() && mVendorList.get(vendorDetails).getVendor_description_ar().isEmpty()) {
                    mHeadingDesc.setVisibility(View.GONE);
                    description.setVisibility(View.GONE);
                    line1.setVisibility(View.GONE);
                } else {
                    description.setText(AppConfig.getLanguage_code().equalsIgnoreCase("en") ? mVendorList.get(vendorDetails).getVendor_description_en() :
                            mVendorList.get(vendorDetails).getVendor_description_ar());
                }

                if (CommonFunctions.getInstance().isNetworkAvailable(DetailsActivity.this)) {
                    youTubePlayerView.initialize(API_KEY, this);
                } else {
                    mVideoFrame.setVisibility(View.GONE);
                }

                try {
                    if (CommonFunctions.getInstance().isNetworkAvailable(DetailsActivity.this)) {
                        if (!mVendorList.get(vendorDetails).getVendor_youtube_video().isEmpty() || mVendorList.get(vendorDetails).getVendor_youtube_video() != null) {
                            String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
                            Pattern compiledPattern = Pattern.compile(pattern);
                            Matcher matcher = compiledPattern.matcher(mVendorList.get(vendorDetails).getVendor_youtube_video());
                            if (matcher.find()) {
                                VIDEO_ID = matcher.group();
                            }
                        } else {
                            mVideoFrame.setVisibility(View.GONE);
                        }
                    } else {
                        mVideoFrame.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (CommonFunctions.getInstance().isNetworkAvailable(DetailsActivity.this)) {
                    if (!mVendorList.get(vendorDetails).getVendor_logo().isEmpty()) {
                        Picasso.with(DetailsActivity.this).load(mVendorList.get(vendorDetails).getVendor_logo())
                                .placeholder(R.drawable.no_photo_available).resize(50, 50).config(Bitmap.Config.ARGB_8888)
                                .centerCrop().into(mVendorLogo);
                    } else {
                        Picasso.with(DetailsActivity.this).load(R.drawable.no_photo_available).fit().into(mVendorLogo);
                    }

                } else {
                    Picasso.with(DetailsActivity.this).load(R.drawable.no_photo_available).fit().into(mVendorLogo);
                }
                final int finalVendorDetails = vendorDetails;
                if (mVendorList.get(finalVendorDetails).getVendor_social_twitter().isEmpty()) {
                    mTwitter.setVisibility(View.GONE);
                }
                mTwitter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (CommonFunctions.getInstance().isNetworkAvailable(DetailsActivity.this)) {
                            if (!LocalContactDB.GetVendorList(AppConfig.getCategory_id()).get(finalVendorDetails).getVendor_social_twitter().isEmpty()) {
                                String url = LocalContactDB.GetVendorList(AppConfig.getCategory_id()).get(finalVendorDetails).getVendor_social_twitter();
//                            String url = "https://www.google.com";
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);
                            } else {

                            }

                        } else {
                            Toast.makeText(DetailsActivity.this, "Please enable internet connection.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                try {
                    StringTokenizer tokens = new StringTokenizer(mVendorList.get(vendorDetails).getVendor_location(), ",");
                    double latitude = 11.009579;
                    latitude = Double.valueOf(tokens.nextToken());
                    double longitude = 76.947347;
                    longitude = Double.valueOf(tokens.nextToken());
                    // create marker
                    MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(AppConfig.getLanguage_code().equalsIgnoreCase("en") ? mVendorList.get(vendorDetails).getVendor_name_en() :
                            mVendorList.get(vendorDetails).getVendor_name_ar());
                    // adding marker
                    googleMap.addMarker(marker);
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(
                            new LatLng(latitude, longitude)).zoom(12).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final int finalVendorDetails1 = vendorDetails;
                mLocation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (CommonFunctions.getInstance().isNetworkAvailable(DetailsActivity.this)) {
                            if (!mVendorList.get(finalVendorDetails1).getVendor_location().isEmpty()) {
                                StringTokenizer tokens = new StringTokenizer(mVendorList.get(finalVendorDetails1).getVendor_location(), ",");
                                double latitude = 11.009579;
                                latitude = Double.valueOf(tokens.nextToken());
                                double longitude = 76.947347;
                                longitude = Double.valueOf(tokens.nextToken());
                                String map = "http://maps.google.co.in/maps?q=" + latitude + "," + longitude;
                                String uri = String.format(Locale.ENGLISH, map);
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                startActivity(intent);
                            } else {
                                Toast.makeText(DetailsActivity.this, "No geo location", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(DetailsActivity.this, "Please enable internet connection.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                if (mVendorList.get(finalVendorDetails).getVendor_social_instagram().isEmpty()) {
                    mInstagram.setVisibility(View.GONE);
                }
                mInstagram.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (CommonFunctions.getInstance().isNetworkAvailable(DetailsActivity.this)) {
                            if (!LocalContactDB.GetVendorList(AppConfig.getCategory_id()).get(finalVendorDetails).getVendor_social_instagram().isEmpty()) {
                                String url = LocalContactDB.GetVendorList(AppConfig.getCategory_id()).get(finalVendorDetails).getVendor_social_instagram();
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);
                            }

                        } else {
                            Toast.makeText(DetailsActivity.this, "Please enable internet connection.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                final int finalVendorDetails2 = vendorDetails;
                mCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mVendorList.get(finalVendorDetails2).getVendor_phone1().isEmpty()) {
                            String mPhoneNumber = mVendorList.get(finalVendorDetails2).getVendor_phone1();
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + mPhoneNumber));
                            startActivity(callIntent);
                        } else {
                            Toast.makeText(DetailsActivity.this, "Phone number not exists.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }


        }

        if (AppConfig.getLanguage_code().equalsIgnoreCase("en")) {
            mBack.setImageResource(R.drawable.ic_back_arrow);
        } else {
            mBack.setImageResource(R.drawable.right_arrow);
        }

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mVendorPhotoList = LocalContactDB.GetVendorGallery(AppConfig.getVendor_id());
        //Load images to GalleryFlow Adapter
        if (mVendorPhotoList.size() > 0) {
            aGalleryCoverFlowAdapter = new GalleryCoverFlowAdapter(DetailsActivity.this, mVendorPhotoList);
            mGalleryFlow.setAdapter(aGalleryCoverFlowAdapter);
            mGalleryFlow.clearCache();
            mGalleryFlow.computeScroll();

        } else {
            mGalleryFlow.setVisibility(View.GONE);
            mHeadingGallery.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);
        }
    }

    private void initViews() {
        mGalleryFlow = (FeatureCoverFlow) findViewById(R.id.gallery_cover_flow);
        mHeading = (CustomTextView) findViewById(R.id.AD_TV_title);
        mGalleryNotFound = (CustomTextView) findViewById(R.id.not_found);
        mVideoNotFound = (CustomTextView) findViewById(R.id.not_video_found);
        mBack = (ImageView) findViewById(R.id.AD_IV_back);
        mAddress = (CustomTextView) findViewById(R.id.AD_TV_address);
        phoneNumber1 = (CustomTextView) findViewById(R.id.AD_TV_first_phone_number);
        phoneNumber2 = (CustomTextView) findViewById(R.id.AD_TV_second_phone_number);
        description = (CustomTextView) findViewById(R.id.Description_contect);
        mVendorVideo = (WebView) findViewById(R.id.video_view);
        mTwitter = (ImageView) findViewById(R.id.AD_IV_twitter);
        mInstagram = (ImageView) findViewById(R.id.AD_IV_instagram);
//        mLocation = (ImageView) findViewById(R.id.AD_IV_map);
        mCall = (ImageView) findViewById(R.id.AD_IV_call);
        mVendorLogo = (ImageView) findViewById(R.id.AD_IV_vendor_logo);
        mLocation = (ImageView) findViewById(R.id.AD_IV_google_map);
        vHome = (ImageView) findViewById(R.id.LFT_IV_home);
        vMessage = (ImageView) findViewById(R.id.LFT_IV_message);
        mAddressLayout = (LinearLayout) findViewById(R.id.ll_parent1);
        phoneNumberLayout = (LinearLayout) findViewById(R.id.phone_number_2);
        mHeadingDesc = (CustomTextView) findViewById(R.id.Description);
        line1 = (View) findViewById(R.id.view);
        line2 = (View) findViewById(R.id.view1);
        mHeadingGallery = (CustomTextView) findViewById(R.id.Gallery);
        mHeadingVideo = (CustomTextView) findViewById(R.id.Video);
        mVideoFrame = (FrameLayout) findViewById(R.id.frame);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        /** Start buffering **/
        try {
            /** add listeners to YouTubePlayer instance **/
            youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
            youTubePlayer.setPlaybackEventListener(playbackEventListener);
            if (!wasRestored) {
                if (VIDEO_ID == null) {
                    mVideoFrame.setVisibility(View.GONE);
                    mHeadingVideo.setVisibility(View.GONE);
                } else {
                    youTubePlayer.cueVideo(VIDEO_ID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    private void showVendorGallery(final String vendor_id) {
        if (LocalContactDB.GetVendorGallery(vendor_id).size() > 0) {
            mVendorPhotoList = LocalContactDB.GetVendorGallery(vendor_id);
        }
    }

    /**
     * function to load map. If map is not created it will create it for you
     */
    private void initilizeMap() {
        if (googleMap == null) {
        /*    map= ((SupportMapFragment) getSupportFragmentManager().findFragmentById(
                    R.id.AD_IV_map)).getMap();*/
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.AD_IV_map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    private PlaybackEventListener playbackEventListener = new PlaybackEventListener() {

        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }

    };

    private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onError(ErrorReason arg0) {
        }

        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onVideoStarted() {
        }
    };

    /*@Override
    public void onClick(View v) {
        //Handle click event for home and message button bottom toolbar
        switch (v.getId()) {
            case R.id.LFT_IV_home:
                //Controller class handles another class name to call a class.
                ControllerClass.CallScreen(DetailsActivity.this, ChooseLanguage.class);
                break;
            case R.id.LFT_IV_message:
                //Controller class handles another class name to call a class.
                ControllerClass.CallScreen(DetailsActivity.this, ContactUsActivity.class);
                break;
        }
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
