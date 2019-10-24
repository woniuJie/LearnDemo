package com.zsj.learndemo.intentService.myintent;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by zhangshijie on 2019/8/9;
 */
public class MyIntentService extends IntentService {

    private static final String TAG = "zsj";
    public static final String BROADCAST_ACTION =
            "com.example.android.threadsample.BROADCAST";
    public static final String EXTENDED_DATA_STATUS =
            "com.example.android.threadsample.STATUS";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: ");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String dateStr = intent.getDataString();
        Log.d(TAG, "onHandleIntent: "+dateStr);

        Intent intentFilter = new Intent(BROADCAST_ACTION)
                .putExtra(EXTENDED_DATA_STATUS,dateStr);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intentFilter);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
