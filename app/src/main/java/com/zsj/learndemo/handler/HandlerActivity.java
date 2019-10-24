package com.zsj.learndemo.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zsj.learndemo.R;

public class HandlerActivity extends AppCompatActivity {

    MyHandler myHandler;
    private static final String TAG = "zsj-handler";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        myHandler = new MyHandler();

        //        Message message = Message.obtain();
//        message.what = 101;
//        message.obj = "zsj";
//        myHandler.sendMessage(message);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
                Message message = Message.obtain();
                message.what = 101;
                message.obj = "zsj";
                handler.sendMessage(message);
            }
        }).start();
    }

    static class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 101){
                Log.d(TAG, "handleMessage: "+msg.obj);
            }
        }
    }
}
