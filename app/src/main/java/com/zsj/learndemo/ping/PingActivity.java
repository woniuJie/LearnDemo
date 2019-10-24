package com.zsj.learndemo.ping;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.qiniu.android.dns.local.AndroidDnsServer;
import com.zsj.learndemo.R;

import org.minidns.dnsmessage.DnsMessage;
import org.minidns.dnssec.DnssecQueryResult;
import org.minidns.hla.DnssecResolverApi;
import org.minidns.hla.ResolverResult;
import org.minidns.record.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.util.Set;

public class PingActivity extends AppCompatActivity {

    private static final String TAG = "zsj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        new Thread(){
            @Override
            public void run() {
                super.run();
                ResolverResult<A> result = null;
                try {
                    AndroidDnsServer.getByCommand();
                    AndroidDnsServer.getByReflection();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }
}
