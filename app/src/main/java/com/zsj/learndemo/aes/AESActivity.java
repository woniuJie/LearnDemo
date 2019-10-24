package com.zsj.learndemo.aes;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zsj.learndemo.R;

import org.minidns.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESActivity extends AppCompatActivity {

    private static final String TAG = "zsj-aes";
    String s = "a";
    String key = "01234567890123456789012345678912";
    String iv =  "3c28c1bef9e73000";

    String md5 = "1234567890123456";
    //9-24
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes);

        String ss = MD5Utils.MD5Encode(md5,"utf-8",false).substring(8,24);
        Log.d(TAG, "onCreate: "+ss);

        try {
            String jiami = AesCBC.getInstance().encrypt(s,"utf-8",key,iv);
            Log.d(TAG, "onCreate: 加密--"+jiami);

            String jiemi = AesCBC.getInstance().decrypt("eanSkv1lZA41kEMy7WZMqQ==","utf-8",key,iv);

            Log.d(TAG, "onCreate: 解密--"+jiemi);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
