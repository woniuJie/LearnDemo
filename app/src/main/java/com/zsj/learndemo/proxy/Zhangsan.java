package com.zsj.learndemo.proxy;

import android.util.Log;

public class Zhangsan implements ILawsuit{
    private static final String TAG = "zsjproxy-zhangsan";

    @Override
    public void submit() {
        Log.d(TAG, "submit: ");
    }

    @Override
    public void burden() {
        Log.d(TAG, "burden: ");
    }

    @Override
    public void defend() {
        Log.d(TAG, "defend: ");
    }

    @Override
    public void finish() {
        Log.d(TAG, "finish: ");
    }
}
