package com.zsj.learndemo.download_video;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.zsj.learndemo.A;

import java.util.ArrayList;
import java.util.List;

public class DownLoadManager {

    private VideoItemBean videoItem;

    private ArrayList<DownLoadStatusUpdater> mLists = new ArrayList<>();

    private DownLoadManager() {

    }

    private final static class HolderClass {
        private final static DownLoadManager INSTANCE = new DownLoadManager();
    }

    public static DownLoadManager getInstance() {
        return HolderClass.INSTANCE;
    }

    public void addUpdater(final DownLoadStatusUpdater updater) {
        if (!mLists.contains(updater)) {
            mLists.add(updater);
        }
    }

    public boolean removeUpdater(final DownLoadStatusUpdater updater) {
        return mLists.remove(updater);
    }

    public int startDownload(String url, String path,String title) {
        int id = FileDownloader.getImpl().create(url)
                .setPath(path)
                .setCallbackProgressTimes(300)
                .setMinIntervalUpdateSpeed(400)
                .setListener(listener)
                .setTag(title)
                .start();
        return id;
    }

    private FileDownloadListener listener = new FileDownloadListener() {
        @Override
        protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            updateDownloadInfo(task);
        }

        @Override
        protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            updateDownloadInfo(task);

        }

        @Override
        protected void completed(BaseDownloadTask task) {
            updateDownloadInfo(task);

        }

        @Override
        protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            updateDownloadInfo(task);

        }

        @Override
        protected void error(BaseDownloadTask task, Throwable e) {
            updateDownloadInfo(task);

        }

        @Override
        protected void warn(BaseDownloadTask task) {
            updateDownloadInfo(task);

        }
    };

    private void updateDownloadInfo(BaseDownloadTask task) {
        ArrayList<DownLoadStatusUpdater> updaters = (ArrayList<DownLoadStatusUpdater>) mLists.clone();
        for (DownLoadStatusUpdater updater : updaters) {
            updater.update(task);
        }
    }

    public interface DownLoadStatusUpdater {
        void update(BaseDownloadTask task);
    }

}
