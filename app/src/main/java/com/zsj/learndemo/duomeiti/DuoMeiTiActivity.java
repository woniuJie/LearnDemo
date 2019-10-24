package com.zsj.learndemo.duomeiti;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.zsj.learndemo.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DuoMeiTiActivity extends AppCompatActivity {
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_VIDEO_CAPTURE = 3;

    static final String TAG = "zsj";
    private ImageView imageView;
    String mCurrentPhotoPath;
    private Uri imageUri;
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duo_mei_ti);
        imageView = findViewById(R.id.iv_paizhao);
        mVideoView = findViewById(R.id.videoVIew);
    }

    public void OnPaiZhao(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            mCurrentPhotoPath = Environment.getExternalStorageDirectory() + "/temp/" + System.currentTimeMillis() + ".jpg";
            File file = new File(mCurrentPhotoPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if(Build.VERSION.SDK_INT>=24){
                imageUri = FileProvider.getUriForFile(DuoMeiTiActivity.this,getPackageName()+".fileprovider",file);
            }else{
                imageUri = Uri.fromFile(file);
            }
            // Continue only if the File was successfully created
            if (file != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        imageUri);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    public void OnLuXiang(View view){
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == REQUEST_TAKE_PHOTO){
                Log.d(TAG, "onActivityResult: "+data);
//                Bundle extras = data.getExtras();
//                Bitmap imageBitmap = (Bitmap) extras.get("data");
//                imageView.setImageBitmap(imageBitmap);

                galleryAddPic();
                File temp = new File(mCurrentPhotoPath);
                Uri uri = Uri.fromFile(temp);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else if(requestCode == REQUEST_VIDEO_CAPTURE){
                Uri videoUri = data.getData();
                mVideoView.setVideoURI(videoUri);
                mVideoView.start();
                mVideoView.requestFocus();
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

}
