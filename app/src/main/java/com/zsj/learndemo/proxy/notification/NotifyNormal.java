package com.zsj.learndemo.proxy.notification;

import android.app.Notification;
import android.content.Context;
import android.widget.RemoteViews;

import com.zsj.learndemo.R;

public class NotifyNormal extends Notify {
    public NotifyNormal(Context mContext) {
        super(mContext);
    }

    @Override
    public void send() {
        Notification n = builder.build();
        n.contentView = new RemoteViews(mContext.getPackageName(), R.layout.cv_layout_calendar_view);
        nm.notify(0, n);
    }

    @Override
    public void cancel() {
        nm.cancel(0);
    }
}
