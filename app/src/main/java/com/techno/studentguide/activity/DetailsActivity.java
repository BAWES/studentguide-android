package com.techno.studentguide.activity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.techno.studentguide.R;
import com.techno.studentguide.adapter.GalleryCoverFlowAdapter;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class DetailsActivity extends AppCompatActivity {
    GalleryCoverFlowAdapter aGalleryCoverFlowAdapter;
    int[] galleryImages = {R.drawable.flower1, R.drawable.flower2, R.drawable.flower3, R.drawable.flower4};
    FeatureCoverFlow mGalleryFlow;
    VideoView videoview;
    Button mOpenVideoPlayer;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the layout from activity_details.xml
        setContentView(R.layout.activity_details);
        // Find your IDS in your activity_details.xml layout
        mGalleryFlow = (FeatureCoverFlow) findViewById(R.id.gallery_cover_flow);
        videoview = (VideoView) findViewById(R.id.video_view);
        mOpenVideoPlayer = (Button) findViewById(R.id.AD_BT_open_video);
        //Load images to GalleryFlow Adapter
        aGalleryCoverFlowAdapter = new GalleryCoverFlowAdapter(DetailsActivity.this, galleryImages);
        mGalleryFlow.setAdapter(aGalleryCoverFlowAdapter);
        mOpenVideoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Create a progressbar
                    pDialog = new ProgressDialog(DetailsActivity.this);
                    // Set progressbar message
                    pDialog.setMessage("Buffering...");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    // Show progressbar
                    pDialog.show();
                    // Start the MediaController
                    MediaController mediacontroller = new MediaController(
                            DetailsActivity.this);
                    mediacontroller.setAnchorView(videoview);
                    // Get the URL from String VideoURL
                    Uri video = Uri.parse("http://www.androidbegin.com/tutorial/AndroidCommercial.3gp");
                    videoview.setMediaController(mediacontroller);
                    videoview.setVideoURI(video);

                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }

//                videoview.requestFocus();
                videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    // Close the progress bar and play the video
                    public void onPrepared(MediaPlayer mp) {
                        mOpenVideoPlayer.setVisibility(View.GONE);
                        pDialog.dismiss();
                        videoview.start();
                    }
                });
            }
        });

    }
}
