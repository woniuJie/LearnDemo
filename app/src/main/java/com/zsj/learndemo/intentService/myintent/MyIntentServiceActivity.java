package com.zsj.learndemo.intentService.myintent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.zsj.learndemo.R;

import static com.zsj.learndemo.intentService.myintent.MyIntentService.BROADCAST_ACTION;
import static com.zsj.learndemo.intentService.myintent.MyIntentService.EXTENDED_DATA_STATUS;

public class MyIntentServiceActivity extends AppCompatActivity {
    private static final String TAG = "zsj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_intent_service);

        IntentFilter mStatusIntentFilter = new IntentFilter(
                BROADCAST_ACTION);

//        mStatusIntentFilter.addDataScheme("http");

        MyBroadCast myBroadCast = new MyBroadCast();
        LocalBroadcastManager.getInstance(this).registerReceiver(myBroadCast,mStatusIntentFilter);

        Intent intent = new Intent(this, MyIntentService.class);
        intent.setData(Uri.parse("www.baidu.com"));
        startService(intent);


        ImageView imageView = findViewById(R.id.photoview_aa);
        imageView.setImageDrawable(getResources().getDrawable(R.mipmap.icon_gift));
    }

    public class MyBroadCast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: "+intent.getStringExtra(EXTENDED_DATA_STATUS));
            Log.d(TAG, "onReceive: "+intent.getAction());
        }
    }
}
