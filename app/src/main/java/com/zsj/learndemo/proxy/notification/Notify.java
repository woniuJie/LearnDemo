package com.zsj.learndemo.proxy.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.zsj.learndemo.proxy.ProxyActivity;

public abstract class Notify {
    protected Context mContext;
    protected NotificationManager nm;
    protected NotificationCompat.Builder builder;

    public Notify(Context mContext) {
        this.mContext = mContext;
        nm = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(mContext)
                .setContentIntent(PendingIntent.getActivity(mContext,0,new Intent(mContext, ProxyActivity.class),PendingIntent.FLAG_UPDATE_CURRENT));
    }

    public abstract void send();
    public abstract void cancel();
}
