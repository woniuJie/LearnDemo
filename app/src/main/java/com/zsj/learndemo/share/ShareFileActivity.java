package com.zsj.learndemo.share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zsj.learndemo.R;

import java.io.File;
import java.io.IOException;

public class ShareFileActivity extends AppCompatActivity {
    private File mPrivateRootDir;
    // The path to the "images" subdirectory
    private File mImagesDir;
    // Array of files in the images subdirectory
    File[] mImageFiles;
    // Array of filenames corresponding to mImageFiles
    String[] mImageFilenames;
    private Intent mResultIntent;
    private String TAG = "zsj";
    Uri fileUri;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_file);
        layout = findViewById(R.id.ll_share_image);
        mResultIntent =
                new Intent("com.zsj.learndemo.ACTION_RETURN_FILE");
        // Get the files/ subdirectory of internal storage
        mPrivateRootDir = Environment.getExternalStorageDirectory();
        // Get the files/images subdirectory;
        mImagesDir = new File(mPrivateRootDir, "temp");

//        String mTempPhotoPath = mImagesDir + "/" + System.currentTimeMillis() + ".jpg";
//        File file = new File(mTempPhotoPath);
//        if (!file.exists()) {
//            file.mkdirs();
//        }

        // Get the files in the images subdirectory
        mImageFiles = mImagesDir.listFiles();
        // Set the Activity's result to null to begin with
        setResult(Activity.RESULT_CANCELED, null);
        Log.d(TAG, "onCreate: " + mImageFiles);

        for (File fileeee : mImageFiles){
            Uri uri = Uri.fromFile(fileeee);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                ImageView imageView = new ImageView(ShareFileActivity.this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200,200);
                imageView.setLayoutParams(layoutParams);
                imageView.setImageBitmap(bitmap);
                layout.addView(imageView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        fileUri = FileProvider.getUriForFile(ShareFileActivity.this, getPackageName() + ".fileprovider", mImageFiles[0]);


    }

    public void onSHeee(View view) {
        if (fileUri != null) {
            // Grant temporary read permission to the content URI
            mResultIntent.addFlags(
                    Intent.FLAG_GRANT_READ_URI_PERMISSION);

            mResultIntent.setDataAndType(
                    fileUri,
                    getContentResolver().getType(fileUri));
            // Set the result
            ShareFileActivity.this.setResult(Activity.RESULT_OK,
                    mResultIntent);
        } else {
            mResultIntent.setDataAndType(null, "");
            ShareFileActivity.this.setResult(RESULT_CANCELED,
                    mResultIntent);
        }

        finish();
    }


}
