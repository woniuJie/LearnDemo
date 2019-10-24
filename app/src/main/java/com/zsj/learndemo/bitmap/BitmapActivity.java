package com.zsj.learndemo.bitmap;

import android.bluetooth.BluetoothSocket;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.zsj.learndemo.R;

import java.lang.ref.WeakReference;

public class BitmapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        ImageView imageView = null;
        BitmapWorkerTask task = new BitmapWorkerTask(imageView);
        task.execute(R.mipmap.ic_launcher);

    }

    class A extends BitmapDrawable{

    }


}
