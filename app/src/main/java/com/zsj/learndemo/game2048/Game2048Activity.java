package com.zsj.learndemo.game2048;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.zsj.learndemo.R;

public class Game2048Activity extends AppCompatActivity {

    WebView webView;
    private static final String URL = "file:///android_asset/game/index.html";
    private static final String TAG = "zsj-webview";

    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    private long startTime;
    private long endTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game2048);
        startTime = System.currentTimeMillis();
        linearLayout = findViewById(R.id.ll_game);

        webView = new WebView(this);
        linearLayout.addView(webView);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);


        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                endTime = System.currentTimeMillis();
                Log.d(TAG, "onPageStarted: "+(endTime - startTime));
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

                endTime = System.currentTimeMillis();
                Log.d(TAG, "shouldInterceptRequest: "+(endTime - startTime)+"---"+request.getUrl().toString());

                return super.shouldInterceptRequest(view, request);

            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if (newProgress < 100) {
                    if (progressBar.getVisibility() == View.GONE) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(newProgress);
                } else {
                    progressBar.setVisibility(View.GONE);
                }

            }
        });
        webView.loadUrl("https://m.cumart.net/index");
    }
}
