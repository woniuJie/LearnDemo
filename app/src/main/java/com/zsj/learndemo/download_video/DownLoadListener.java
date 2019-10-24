package com.zsj.learndemo.download_video;

public interface DownLoadListener {
    void onStartDownload();
    void onCompleted(String path);
    void onError(String message);
}
