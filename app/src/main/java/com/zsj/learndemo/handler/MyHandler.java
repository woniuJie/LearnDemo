package com.zsj.learndemo.handler;

import android.os.Handler;
import android.os.Looper;

public class MyHandler {

    ThreadLocal<Looper> threadLocal = new ThreadLocal<>();

    public void ds(){
        Looper looper = Looper.getMainLooper();
        threadLocal.set(looper);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

}
