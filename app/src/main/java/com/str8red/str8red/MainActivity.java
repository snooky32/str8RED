package com.str8red.str8red;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.Context;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    WebView wv;
    Boolean fish;
    Boolean shark;

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
        // wv.setWebViewClient(new myWebClient());
        wv.setWebViewClient(new myWebClient(this));

        SharedPreferences.Editor prefs = PreferenceManager.getDefaultSharedPreferences(this).edit();
        prefs.putBoolean("notifications_team_pick",false);
        prefs.putBoolean("notifications_results", false);

        prefs.commit();




    }

    public class myWebClient extends WebViewClient {

        private Context context;

        public myWebClient(Context context) {
            this.context = context;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            String CurrentURL = wv.getUrl();

            Log.d("step 0", CurrentURL);

            if (CurrentURL.equals("https://str8red.com/welcome/") ) {
                Log.d("step 1", CurrentURL);
                wv.evaluateJavascript("fromAndroid()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.d("step 2", "2");
                        String[] separated = value.split(" ");
                        String loggedIn = separated[0].replace("\"", "");
                        String Notify1 = separated[1].replace("\"", "");
                        String Notify2 = separated[2].replace("\"", "");

                        SharedPreferences.Editor prefs = PreferenceManager.getDefaultSharedPreferences(context).edit();
                        prefs.putBoolean("notifications_team_pick", Boolean.valueOf(Notify1));
                        prefs.putBoolean("notifications_results",  Boolean.valueOf(Notify2));

                        prefs.commit();
                    }
                });
            }
        }
    }

    //Settings Button
    public void btnSettings_onClick(View view) {
        Intent intent=new Intent(this,SettingsActivity.class);
        startActvity(intent);
    }

    private void startActvity(Intent intent) {
        startActivity(intent);
    }
    //End of Settings Button

    //Play Button

    public void btnPlay_onClick(View view) {
        wv.loadUrl("https://str8red.com/selectteams/0/0");
        //wv.setWebViewClient(new myWebClient());
    }
    //End of Play Button

}