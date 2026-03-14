package com.newsapp;

import android.os.Bundle;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class WebAppActivity extends AppCompatActivity {
    private WebView webView;
    private LocalWebServer webServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webapp);

        // Enable WebView debugging
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        // Start local web server
        webServer = new LocalWebServer(this, 8080);
        try {
            webServer.start();
            android.util.Log.d("WebAppActivity", "Server started on port 8080");
        } catch (IOException e) {
            Toast.makeText(this, "Failed to start server: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        webView = findViewById(R.id.webView);

        // Enable JavaScript and other settings
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        // Enable debugging
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                android.util.Log.d("WebView", consoleMessage.lineNumber() + ": " + consoleMessage.message());
                return true;
            }
        });

        // Keep navigation within the WebView
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(android.webkit.WebView view, int errorCode, String description, String failingUrl) {
                android.util.Log.e("WebView", "Error: " + description + " at " + failingUrl);
                Toast.makeText(WebAppActivity.this, "Error: " + description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                android.util.Log.d("WebView", "Page loaded: " + url);
            }
        });

        // Load from local server instead of file://
        webView.loadUrl("http://localhost:8080/");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webServer != null) {
            webServer.stop();
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
