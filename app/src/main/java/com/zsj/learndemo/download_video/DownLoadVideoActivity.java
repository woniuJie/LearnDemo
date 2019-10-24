package com.zsj.learndemo.download_video;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.zsj.learndemo.R;
import com.zsj.learndemo.utils.ToastUtils;

public class DownLoadVideoActivity extends AppCompatActivity {

    private static final String TAG = "zsj-download";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_video);
    }

    public void onDownload(View view) {

        FileDownloader.setup(DownLoadVideoActivity.this);

        String url = "https://crossec-cdn.cumart.net/project/ShopIn/Video/Goods/20190926/10100_5d8c7afe68b6e.mp4";
        String title = "10100_5d8c7afe68b6e";

        DownLoadHelper.startDownload(url,title, new DownLoadListener() {

            @Override
            public void onStartDownload() {
                ToastUtils.showToast(DownLoadVideoActivity.this, "开始下载", Toast.LENGTH_SHORT);
            }

            @Override
            public void onCompleted(String path) {
                ToastUtils.showToast(DownLoadVideoActivity.this, "下载完成", Toast.LENGTH_SHORT);
                AlbumNotifyHelper.insertVideoToMediaStore(DownLoadVideoActivity.this, path);

            }

            @Override
            public void onError(String message) {
                ToastUtils.showToast(DownLoadVideoActivity.this, "下载失败-" + message, Toast.LENGTH_SHORT);
            }
        });

        Intent intent = new Intent(this, DownloadVideoService.class);
        startService(intent);
    }

}
