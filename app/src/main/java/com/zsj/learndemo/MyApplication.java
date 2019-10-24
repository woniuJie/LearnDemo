package com.zsj.learndemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import com.zsj.learndemo.game2048.PreloadWebView;
import com.zsj.learndemo.utils.ToastUtils;

/**
 * Created by zhangshijie on 2019/8/14;
 */
public class MyApplication extends Application {
    private static MyApplication application;

    @Override
    public void onCreate() {
        application = this;
        super.onCreate();

        PreloadWebView.getInstance().preload();

        AppStateTracker.track(this, new AppStateTracker.AppStateChangeListener() {
            @Override
            public void appTurnIntoForeground() {
                ToastUtils.showToast(getApplicationContext(),"前台", Toast.LENGTH_LONG);
            }

            @Override
            public void appTurnIntoBackGround() {
                ToastUtils.showToast(getApplicationContext(),"后台", Toast.LENGTH_LONG);
            }
        });

    }

    public static MyApplication getInstance() {
        return application;
    }
}
