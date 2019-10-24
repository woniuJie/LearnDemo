package com.zsj.learndemo.download_video;

import android.text.TextUtils;

import com.liulishuo.filedownloader.model.FileDownloadStatus;

import java.io.File;
import java.util.Date;

public class DownLoadHelper {

    public static void startDownload(String url,String title, DownLoadListener downLoadListener) {
        if (TextUtils.isEmpty(url)) {
            if (downLoadListener != null) {
                downLoadListener.onError("URL不存在");
            }
        }

        String path = getDownLoadPath(title);
        File toFile = new File(path);
        if (toFile.exists() && toFile.length() > 0) {
            if (downLoadListener != null) {
                downLoadListener.onError("已经下载过了，请看下载目录");
            }
        }

        int id = DownLoadManager.getInstance().startDownload(url, path,title);

        if (downLoadListener != null) {
            downLoadListener.onStartDownload();
        }

        DownLoadManager.getInstance().addUpdater(task -> {
            if(task.getStatus() == FileDownloadStatus.completed){
                if(downLoadListener!=null){
                    downLoadListener.onCompleted(path);
                }
            }
        });

    }

    public static String getDownLoadPath(String title) {
        return SDCardUtils.DOWNLOAD_VIDEO_PATH + title+".mp4";
    }

}
