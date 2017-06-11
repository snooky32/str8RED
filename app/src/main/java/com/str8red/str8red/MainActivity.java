package com.str8red.str8red;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends AppCompatActivity {
    WebView wv;

    // When Back Pressed Go Back
    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv = (WebView) findViewById(R.id.wv);
        //Enable JavaScript
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setFocusable(true);
        wv.setFocusableInTouchMode(true);
        //Set Render Priority To High
        wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv.getSettings().setDomStorageEnabled(true);
        wv.getSettings().setDatabaseEnabled(true);
        wv.getSettings().setAppCacheEnabled(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        //Load Url
        wv.loadUrl("https://str8red.com/");
        wv.setWebViewClient(new myWebClient());
    }

    public class myWebClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            String CurrentURL = wv.getUrl();

            if (CurrentURL == "https://str8red.com/") {
                wv.evaluateJavascript("fromAndroid()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        String[] separated = value.split(" ");
                        //separated[0]; // logged in True Or False
                        //separated[1]; // Notifications 1 or 0
                        //separated[2]; // More Notifications or 1 or 0
                        String loggedIn = separated[0].replace("\"", "");
                        String Notify1 = separated[1].replace("\"", "");
                        String Notify2 = separated[2].replace("\"", "");
                    }
                });
            }
        }
    }

    public void btnSettings_onClick(View view) {
        Intent intent=new Intent(this,SettingsActivity.class);
        startActvity(intent);
    }

    private void startActvity(Intent intent) {
        startActivity(intent);
    }

}