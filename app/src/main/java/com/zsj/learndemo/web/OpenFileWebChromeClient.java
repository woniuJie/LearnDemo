package com.zsj.learndemo.web;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by zhangshijie on 2019/8/1;
 */
public class OpenFileWebChromeClient extends WebChromeClient {
    public String TAG = "OpenFileWebChromeClient";
    public static final int REQUEST_FILE_PICKER = 1;
    public ValueCallback<Uri> mFilePathCallback;
    public ValueCallback<Uri[]> mFilePathCallbacks;
    private Activity mContext;
    private TextView textView;

    public OpenFileWebChromeClient(Activity mContext) {
        super();
        this.mContext = mContext;
    }

    /**
     * Android < 3.0 调用这个方法
     */
    public void openFileChooser(ValueCallback<Uri> filePathCallback) {
        mFilePathCallback = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        mContext.startActivityForResult(Intent.createChooser(intent, "File Chooser"),
                REQUEST_FILE_PICKER);
    }

    /**
     * 3.0 + 调用这个方法
     */
    public void openFileChooser(ValueCallback filePathCallback,
                                String acceptType) {
        mFilePathCallback = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        mContext.startActivityForResult(Intent.createChooser(intent, "File Chooser"),
                REQUEST_FILE_PICKER);
    }

    /**
     * js上传文件的<input type="file" name="fileField" id="fileField" />事件捕获
     */

    /**
     * Android > 4.1.1 调用这个方法
     */
    @Deprecated
    public void openFileChooser(ValueCallback<Uri> filePathCallback,
                                String acceptType, String capture) {
        mFilePathCallback = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        mContext.startActivityForResult(Intent.createChooser(intent, "File Chooser"),
                REQUEST_FILE_PICKER);
    }

    @Override
    public boolean onShowFileChooser(WebView webView,
                                     ValueCallback<Uri[]> filePathCallback,
                                     WebChromeClient.FileChooserParams fileChooserParams) {
        mFilePathCallbacks = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        mContext.startActivityForResult(Intent.createChooser(intent, "File Chooser"),
                REQUEST_FILE_PICKER);
        return true;
    }
}
