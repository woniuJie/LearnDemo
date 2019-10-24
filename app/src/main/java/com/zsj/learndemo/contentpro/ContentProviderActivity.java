package com.zsj.learndemo.contentpro;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zsj.learndemo.R;

/**
 *  进程内访问ContentProvider
 */
public class ContentProviderActivity extends AppCompatActivity {

    private static final String TAG = "zsjprovider";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        Uri uri = Uri.parse("content://com.zsj.myprovider/user");

        ContentResolver resolver = getContentResolver();

        ContentValues values = new ContentValues();
        values.put("_id",3);
        values.put("name","lisi");

        resolver.insert(uri,values);

        Cursor cursor = resolver.query(uri,new String[]{"_id","name"},null,null,null);
        while (cursor.moveToNext()){
            Log.d(TAG, "onCreate: "+cursor.getInt(0)+"--"+cursor.getString(1));
        }

        cursor.close();

    }
}
