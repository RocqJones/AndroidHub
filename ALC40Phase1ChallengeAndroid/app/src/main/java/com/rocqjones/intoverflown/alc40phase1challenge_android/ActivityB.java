package com.rocqjones.intoverflown.alc40phase1challenge_android;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        WebView mview = (WebView) findViewById(R.id.webView);
        mview.getSettings().setJavaScriptEnabled(true);
        //String url = "https://www.pexels.com/";
        String url = "https://andela.com/alc/";

        WebViewClient webviewclient = new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                Log.d("Ssl Error:", handler.toString() + "error:" + error);
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        };
        mview.setWebViewClient(webviewclient);
        mview.loadUrl(url);
    }
}
