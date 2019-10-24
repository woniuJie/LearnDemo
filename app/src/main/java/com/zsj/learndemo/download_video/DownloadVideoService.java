package com.zsj.learndemo.download_video;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.format.Formatter;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.model.FileDownloadStatus;

public class DownloadVideoService extends Service implements DownLoadManager.DownLoadStatusUpdater {
    NotificationUtils notificationUtils;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationUtils = new NotificationUtils(getBaseContext());
        DownLoadManager.getInstance().addUpdater(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public void update(BaseDownloadTask task) {
        updateNotification(task);
    }

    public void updateNotification(BaseDownloadTask task) {

        int soFarBytes = task.getSmallFileSoFarBytes();
        int totalBytes = task.getSmallFileTotalBytes();

        int progress = 0;
        if (totalBytes > 0) {
            progress = (int) (((float) soFarBytes / totalBytes) * 100);
        }

        String title = (String) task.getTag();

        String fileSize = Formatter.formatFileSize(DownloadVideoService.this, soFarBytes).replace("MB", "") + "/ " + Formatter.formatFileSize(DownloadVideoService.this, totalBytes);

        switch (task.getStatus()) {
            case FileDownloadStatus.completed:
                notificationUtils.sendNotificationProgress(title, "下载完成", progress, null);
                stopSelf();
                break;
            case FileDownloadStatus.progress:
                notificationUtils.sendNotificationProgress(title, fileSize, progress, null);
                break;
            default:
                break;
        }
    }
}
