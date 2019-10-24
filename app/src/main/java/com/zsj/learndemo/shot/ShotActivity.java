package com.zsj.learndemo.shot;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.trello.rxlifecycle2.components.RxActivity;
import com.zsj.learndemo.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShotActivity extends RxActivity {
    private ImageView imageView;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot);
        imageView = findViewById(R.id.iv_iii);
        layout = findViewById(R.id.ll_iii);

        layout.setVisibility(View.GONE);

        RxScreenshotDetector.start(this)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(path ->
                                showImage(path),
                        Throwable::printStackTrace);

    }

    public void showImage(String path){
        Bitmap bitmap = autoResizeFromLocalFile(imageView, path);

        imageView.setImageBitmap(bitmap);
        layout.setVisibility(View.VISIBLE);
        Animation animation = new AlphaAnimation(0f,1f);
        animation.setDuration(1000);
        layout.startAnimation(animation);
        imageView.startAnimation(animation);
    }

    public static Bitmap autoResizeFromLocalFile(ImageView imageView, String picturePath) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(picturePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, imageView.getWidth(), imageView.getHeight());
        //options.inSampleSize = 10;
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(picturePath, options);

    }

    //计算图片缩放比例
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
