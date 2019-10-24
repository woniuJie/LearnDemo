package com.zsj.learndemo.download_video;

import java.io.Serializable;
import java.util.Date;

public class VideoItemBean implements Serializable {
    /**
     * 下载链接
     */
    private String url;
    /**
     * 标题
     */
    private String title;
    /**
     * 任务的状态
     */
    private int status;
    /**
     * 下载ID
     */
    private int downloadId;
    /**
     * 已下载的大小
     */
    private int soFarBytes;
    /**
     * 进度
     */
    private int progress;
    /**
     * 总共的大小
     */
    private int totalFarBytes;
    /**
     * 开始下载的时间
     */
    private Date addDownloadDate;
    /**
     * 下载完成的时间
     */
    private Date finishedDownloadDate;
    /**
     * 下载的速度
     */
    private long speed;


    public Date getAddDownloadDate() {
        return addDownloadDate;
    }

    public void setAddDownloadDate(Date addDownloadDate) {
        this.addDownloadDate = addDownloadDate;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getSoFarBytes() {
        return soFarBytes;
    }

    public void setSoFarBytes(int soFarBytes) {
        this.soFarBytes = soFarBytes;
    }

    public int getTotalFarBytes() {
        return totalFarBytes;
    }

    public void setTotalFarBytes(int totalFarBytes) {
        this.totalFarBytes = totalFarBytes;
    }

    public Date getFinishedDownloadDate() {
        return finishedDownloadDate;
    }

    public void setFinishedDownloadDate(Date finishedDownloadDate) {
        this.finishedDownloadDate = finishedDownloadDate;
    }

    public int getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(int downloadId) {
        this.downloadId = downloadId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public String getDownLoadPath() {
        return SDCardUtils.DOWNLOAD_VIDEO_PATH + getTitle();
    }

}
