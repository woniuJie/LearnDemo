package com.zsj.learndemo.web;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.ServiceWorkerClient;
import android.webkit.ServiceWorkerController;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.zsj.learndemo.BuildConfig;
import com.zsj.learndemo.R;

import java.io.File;
import java.util.UUID;

public class WebActivity extends AppCompatActivity {

    LinearLayout layout;
    WebView webView;
    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private static final int REQUEST_CODE_ALBUM = 0x01;
    private static final int REQUEST_CODE_CAMERA = 0x02;
    private static final int REQUEST_CODE_PERMISSION_CAMERA = 0x03;
    private String mCurrentPhotoPath;
    private String mLastPhothPath;

    private Thread mThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        layout = findViewById(R.id.web_container);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        webView = new WebView(this);
        webView.setLayoutParams(params);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        layout.addView(webView);
        webView.loadUrl("http://192.168.0.2:8081/input.html");

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }

            //For Android  >= 5.0
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {

                uploadMessageAboveL = filePathCallback;
                uploadPicture();
                return true;
            }


            //For Android  >= 4.1
            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
                uploadMessage = valueCallback;
                uploadPicture();
            }

        });
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            takePhoto();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults == null && grantResults.length == 0) {
            return;
        }

        if (requestCode == REQUEST_CODE_PERMISSION_CAMERA) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePhoto();
            } else {
                // Permission Denied
                new AlertDialog.Builder(this)
                        .setTitle("无法拍照")
                        .setMessage("您未授予拍照权限")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent localIntent = new Intent();
                                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                localIntent.setData(Uri.fromParts("package", getPackageName(), null));
                                startActivity(localIntent);
                            }
                        }).create().show();
            }

        }

    }

    /**
     * 选择相机或者相册
     */
    public void uploadPicture() {

        AlertDialog.Builder builder = new AlertDialog.Builder(WebActivity.this);
        builder.setTitle("请选择图片上传方式");

        //取消对话框
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

                //一定要返回null,否则<input type='file'>
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }
                if (uploadMessageAboveL != null) {
                    uploadMessageAboveL.onReceiveValue(null);
                    uploadMessageAboveL = null;

                }
            }
        });


        builder.setPositiveButton("相机", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if(!TextUtils.isEmpty(mLastPhothPath)){
                    //上一张拍照的图片删除
                    mThread = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            File file = new File(mLastPhothPath);
                            if(file!= null){
                                file.delete();
                            }
                            mHandler.sendEmptyMessage(1);

                        }
                    });

                    mThread.start();


                }else{

                    //请求拍照权限
                    if (ActivityCompat.checkSelfPermission(WebActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        takePhoto();
                    } else {
                        ActivityCompat.requestPermissions(WebActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSION_CAMERA);
                    }
                }







            }
        });
        builder.setNegativeButton("相册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                chooseAlbumPic();


            }
        });

        builder.create().show();

    }

    /**
     * 拍照
     */
    private void takePhoto() {

        StringBuilder fileName = new StringBuilder();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileName.append(UUID.randomUUID()).append("_upload.png");
        File tempFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName.toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri uri = FileProvider.getUriForFile(WebActivity.this, getPackageName()+ ".fileprovider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        mCurrentPhotoPath = tempFile.getAbsolutePath();
        startActivityForResult(intent, REQUEST_CODE_CAMERA);


    }

    /**
     * 选择相册照片
     */
    private void chooseAlbumPic() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Image Chooser"), REQUEST_CODE_ALBUM);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ALBUM || requestCode == REQUEST_CODE_CAMERA) {

            if (uploadMessage == null && uploadMessageAboveL == null) {
                return;
            }

            //取消拍照或者图片选择时
            if (resultCode != RESULT_OK) {
                //一定要返回null,否则<input file> 就是没有反应
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }
                if (uploadMessageAboveL != null) {
                    uploadMessageAboveL.onReceiveValue(null);
                    uploadMessageAboveL = null;

                }
            }

            //拍照成功和选取照片时
            if (resultCode == RESULT_OK) {
                Uri imageUri = null;

                switch (requestCode) {
                    case REQUEST_CODE_ALBUM:

                        if (data != null) {
                            imageUri = data.getData();
                        }

                        break;
                    case REQUEST_CODE_CAMERA:

                        if (!TextUtils.isEmpty(mCurrentPhotoPath)) {
                            File file = new File(mCurrentPhotoPath);
                            Uri localUri = Uri.fromFile(file);
                            Intent localIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri);
                            sendBroadcast(localIntent);
                            imageUri = Uri.fromFile(file);
                            mLastPhothPath = mCurrentPhotoPath;
                        }
                        break;
                }


                //上传文件
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(imageUri);
                    uploadMessage = null;
                }
                if (uploadMessageAboveL != null) {
                    uploadMessageAboveL.onReceiveValue(new Uri[]{imageUri});
                    uploadMessageAboveL = null;

                }

            }

        }


    }

}
