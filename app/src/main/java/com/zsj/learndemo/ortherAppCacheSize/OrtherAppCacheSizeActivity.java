package com.zsj.learndemo.ortherAppCacheSize;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zsj.learndemo.R;

import java.lang.reflect.Method;
import java.util.List;

public class OrtherAppCacheSizeActivity extends AppCompatActivity {

    PackageManager mPackageManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orther_app_cache_size);
        mPackageManager = getPackageManager();

    }


}
