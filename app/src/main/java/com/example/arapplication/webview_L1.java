package com.example.arapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview_L1 extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview__l1);

        webview = (WebView)findViewById(R.id.webview_L1);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://www.britannica.com/science/human-skeleton/Hands-and-feet");

    }

    @Override
    public void onBackPressed() {

        Intent loginIntent = new Intent(webview_L1.this, Ligaments.class);
        startActivity(loginIntent);
        finish();

    }
}