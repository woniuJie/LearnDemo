package com.zsj.learndemo.intentService.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyMessagerService extends Service {
    private static final int CLIENT_TO_SERVICE = 101;
    private static final int SERVICE_TO_CLIENT = 102;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    final Messenger mMessenger = new Messenger(new MyHandler());

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case CLIENT_TO_SERVICE:
                    Log.e("zsj", "服务器接受到客户端的消息:" + message.getData().getString("msg"));
                    //获取客户端传递过来的Messenger，通过这个Messenger回传消息给客户端
                    Messenger client = message.replyTo;
                    //当然，回传消息还是要通过message
                    Message msg = Message.obtain(null, SERVICE_TO_CLIENT);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "你好，客户端，我收到消息了");
                    msg.setData(bundle);
                    try {
                        client.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(message);
                    break;
            }


        }
    }
}
