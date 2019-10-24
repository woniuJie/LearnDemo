package com.zsj.learndemo.cainiao;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zsj.learndemo.R;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class CainiaoActivity extends AppCompatActivity {
    private WebSocketClient webSocketClient;
    private String TAG = "zsj";
    private StringBuilder sb = new StringBuilder();
    private TextView showMessage;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            sb.append("\n");
            sb.append("服务器返回数据：");
            sb.append(msg.obj.toString());
            sb.append("\n");
            showMessage.setText(sb.toString());
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cainiao);
        showMessage = findViewById(R.id.tv_show_cainiao);
        URI uri = URI.create("ws://192.168.1.7:13528");
        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {

            }

            @Override
            public void onMessage(String message) {
                Log.d(TAG, "onMessage: " + message);
                Message handlerMessage = Message.obtain();
                handlerMessage.obj = message;
                handler.sendMessage(handlerMessage);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.d(TAG, "onClose: ");
            }

            @Override
            public void onError(Exception ex) {
                Log.d(TAG, "onError: " + ex.getMessage());
            }
        };

        webSocketClient.connect();

    }

    public void OnGetPrinters(View view) {
        if (webSocketClient.isClosed() || webSocketClient.isClosing()) {
            webSocketClient.connect();
        }
        webSocketClient.send("{\"requestID\":\"12345678901234567890\",\"verson\":\"1.0\",\"cmd\":\"getPrinters\"}");
    }

    public void OnGetPrinterConfig(View view) {
        if (webSocketClient.isClosed() || webSocketClient.isClosing()) {
            webSocketClient.connect();
        }
        webSocketClient.send("{\n" +
                "  \"cmd\":\"getPrinterConfig\",\n" +
                "  \"printer\":\"HP LaserJet Professional M1213nf MFP\",\n" +
                "  \"version\":\"1.0\",\n" +
                "  \"requestID\":\"12345678901234567890\"\n" +
                "}");
    }

    public void OnPrint(View view) {
        if (webSocketClient.isClosed() || webSocketClient.isClosing()) {
            webSocketClient.connect();
        }
        webSocketClient.send("{\n" +
                "\t\"cmd\": \"print\",\n" +
                "\t\"requestID\": \"12345678901234567890\",\n" +
                "\t\"version\": \"1.0\",\n" +
                "\t\"task\": {\n" +
                "\t\t\"taskID\": \"7293666\",\n" +
                "\t\t\"preview\": false,\n" +
                "\t\t\"printer\": \"HP LaserJet Professional M1213nf MFP\",\n" +
                "\t\t\"previewType\": \"pdf\",\n" +
                "\t\t\"firstDocumentNumber\": 10,\n" +
                "\t\t\"totalDocumentCount\": 100,\n" +
                "\t\t\"documents\": [{\n" +
                "\t\t\t\"documentID\": \"0123456789\",\n" +
                "\t\t\t\"contents\": [{\n" +
                "\t\t\t\t\"data\": {\n" +
                "\t\t\t\t\t\"nick\": \"张三\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"templateURL\": \"http://cloudprint.cainiao.com/template/standard/278250/1\"\n" +
                "\t\t\t}]\n" +
                "\t\t}]\n" +
                "\t}\n" +
                "}");
    }
}
