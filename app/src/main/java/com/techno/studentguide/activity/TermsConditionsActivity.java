package com.techno.studentguide.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.techno.studentguide.R;
import com.techno.studentguide.customview.CustomProgressDialog;

public class TermsConditionsActivity extends AppCompatActivity {
    // Showing Terms and Conditions URL Page
    WebView vTerms;

    // Showing Progress bar
    CustomProgressDialog vCPD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6d0e10")));
            getSupportActionBar().setTitle(getString(R.string.txt_termscondition));
            getSupportActionBar().setIcon(R.drawable.ic_back_arrow);
        } catch (Exception e) {
            e.printStackTrace();
        }

       // getSupportActionBar().hide();

        initView();
        vTerms.setBackgroundColor(0);
        vTerms.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        WebSettings webSettings = vTerms.getSettings();
        vTerms.getSettings().setLoadWithOverviewMode(true);
        vTerms.getSettings().setUseWideViewPort(true);
        vTerms.getSettings().setBuiltInZoomControls(true);
        vTerms.getSettings().setPluginState(WebSettings.PluginState.ON);
        vTerms.getSettings().setJavaScriptEnabled(true);
        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
    }

    // Initialize view
    private void initView()
    {
        vTerms = (WebView) findViewById(R.id.ATC_WV_terms_conditions);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_terms_condition, menu);
        menu.findItem(R.id.action_settings).setVisible(false);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        showWebview("https://www.technoduce.com/");
    }

    // Showing Webview
    private void showWebview(String mLoadUrl)
    {
        vCPD = CustomProgressDialog.getInstance();
        vCPD.show(this, "", getString(R.string.loading));
        vTerms.loadUrl(mLoadUrl);
        vTerms.setWebViewClient(new MyWebViewClient());
    }

    // Dismiss the progress bar
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            if (vCPD != null) {
                if (vCPD.isShowing()) {
                    vCPD.dismiss();
                }
            }

            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
