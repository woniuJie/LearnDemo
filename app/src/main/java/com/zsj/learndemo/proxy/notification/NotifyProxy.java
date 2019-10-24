package com.zsj.learndemo.proxy.notification;

import android.content.Context;
import android.os.Build;

public class NotifyProxy extends Notify {
    private Notify notify;

    public NotifyProxy(Context mContext) {
        super(mContext);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notify = new NotifyHeadsUp(mContext);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            notify = new NotifyBig(mContext);
        } else {
            notify = new NotifyNormal(mContext);
        }
    }

    @Override
    public void send() {
        notify.send();
    }

    @Override
    public void cancel() {
        notify.cancel();
    }
}
