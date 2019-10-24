package com.zsj.learndemo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class BinderPoolService extends Service {
    Binder binder = new BinderPoolImpl();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
