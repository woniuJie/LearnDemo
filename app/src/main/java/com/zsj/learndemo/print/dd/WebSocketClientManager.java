package com.zsj.learndemo.print.dd;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by zhangshijie on 2019/7/15;
 */
public class WebSocketClientManager extends WebSocketClient {
    static WebSocketClientManager webSocket = null;
    String TAG = "zsj";

//    public static void main(String[] args) throws URISyntaxException {
//        String uri = "ws://127.0.0.1:13528";
//        webSocket = new WebSocketClientManager(new URI(uri), new Draft_6455());
//        //建立连接
//        webSocket.connect();
//
//    }

    public WebSocketClientManager(URI serverUri, Draft draft) {
        super(serverUri, draft);
        String uri = "ws://192.168.1.7:13528";
        try {
            webSocket = new WebSocketClientManager(new URI(uri), new Draft_6455());
            webSocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        //获取打印机列表
        String getPrinterListCmd = "{\"requestID\":\"12345678901234567890\",\"verson\":\"1.0\",\"cmd\":\"getPrinters\"}";
        webSocket.send(getPrinterListCmd);

        //发送打印任务
        String printCmd = "打印任务报文，内容过长此处不粘贴";
        webSocket.send(printCmd);
    }

    //WebSocket回调函数
    @Override
    public void onMessage(String message) {
        //TODO 对打印服务返回的数据进行处理
        Log.d(TAG, "onMessage: "+message);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        Log.d(TAG, "onClose: ");
    }

    @Override
    public void onError(Exception e) {
        Log.d(TAG, "onError: "+e.getMessage());
    }
}
