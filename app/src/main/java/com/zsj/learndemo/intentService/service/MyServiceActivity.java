package com.zsj.learndemo.intentService.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zsj.learndemo.R;

public class MyServiceActivity extends AppCompatActivity {

    private MyService.MyBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

//        Intent intent = new Intent(this,MyService.class);
//        intent.putExtra("name","zsj");
//        startService(intent);
//
//        stopService(intent);

        Intent intent1 = new Intent(this, MyService.class);
        intent1.putExtra("name", "zsj");
        bindService(intent1, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //绑定成功
            myBinder = (MyService.MyBinder) service;
            myBinder.doSomeThing();


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //绑定失败
        }
    };
}
