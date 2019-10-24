package com.zsj.learndemo.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zsj.learndemo.R;

public class BroadCastActivity extends AppCompatActivity {
    MyBroadCastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);


    }

    public void onSendBroad(View view){
        Intent intent = new Intent();
        intent.setAction("ZSJ");
        intent.putExtra("hhh","hhh");
        sendBroadcast(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("ZSJ");

        receiver = new MyBroadCastReceiver();
        registerReceiver(receiver, filter);

        IntentFilter filter1 = new IntentFilter();
        filter1.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver,filter1);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}
