package com.zsj.learndemo.web;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebResourceResponse;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class WebDataHelper {
    private Map<String, String> mMap;

    public WebDataHelper() {
        mMap = new HashMap<>();
        initData();
    }

    private void initData() {
        String imageDir = "images/";
        String jsDir = "js/";
//
        mMap.put("https://crossec-cdn.cumart.net/project/app/css/swiper.min.css",
                jsDir + "swiper.min.css");


    }

    public boolean hasLocalResource(String url) {
        return mMap.containsKey(url);
    }

    public WebResourceResponse getReplacedWebResourceResponse(Context context, String url) {
        String localResourcePath = mMap.get(url);
        if (TextUtils.isEmpty(localResourcePath)) {
            return null;
        }
        InputStream is = null;
        try {
            is = context.getApplicationContext().getAssets().open(localResourcePath);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String mimeType;
        if (url.endsWith("css")) {
            mimeType = "text/css";
        } else if (url.endsWith("jpg")) {
            mimeType = "image/jpeg";
        } else if (url.endsWith("js")) {
            mimeType = "application/javascript";
        } else if (url.endsWith("png")) {
            mimeType = "image/png";
        } else if (url.endsWith("gif")) {
            mimeType = "image/gif";
        } else if (url.endsWith("svg")) {
            mimeType = "image/svg+xml";
        } else {
            mimeType = "text/html";
        }
        WebResourceResponse response = new WebResourceResponse(mimeType, "utf-8", is);
        return response;
    }

}
