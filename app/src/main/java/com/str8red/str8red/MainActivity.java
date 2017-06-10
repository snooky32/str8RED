package com.str8red.str8red;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    WebView wv;

    //Volley test
    Button button;
    TextView textView;
    String server_url = "https://str8red.com/loggedincheck";
    //End of volley test


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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Secton Added for volley test
        button = (Button)findViewById(R.id.bn);
        textView = (TextView)findViewById(R.id.txt);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                wv.evaluateJavascript("fromAndroid()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        textView.setText(value);
                    }
                });
            }
        });
        //End of volley test




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
        //wv.setWebViewClient(new WebViewClient());

        wv.setWebViewClient(new myWebClient());



        textView.setText("CUNT:");

    }

    public class myWebClient extends WebViewClient {


        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            wv.evaluateJavascript("fromAndroid()", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    textView.setText(value);
                }
            });





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