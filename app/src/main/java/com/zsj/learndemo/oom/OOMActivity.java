package com.zsj.learndemo.oom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zsj.learndemo.R;

/**
 * 内存泄漏
 */
public class OOMActivity extends AppCompatActivity {

    private TextView textView;
    //    private MyHandler myHandler;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oom);
        textView = findViewById(R.id.tv_oom);

        button = findViewById(R.id.bt_oom);
        Message message = Message.obtain();
        message.what = 0;
        message.obj = "zsj";

//        myHandler = new MyHandler();
//        myHandler.sendMessage(message);

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                textView.setText((String) msg.obj);
            }
        };

        handler.sendMessage(message);

        IntentFilter intentFilter = new IntentFilter();
        registerReceiver(broadcastReceiver, intentFilter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spawnThread();
            }
        });
    }

    public void spawnThread() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true);
            }
        }.start();
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText((String) msg.obj);
        }
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    };
}
